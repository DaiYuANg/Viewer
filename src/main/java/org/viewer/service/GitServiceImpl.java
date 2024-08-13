package org.viewer.service;

import io.minio.PutObjectArgs;
import io.minio.UploadObjectArgs;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.eventbus.EventBus;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.Transport;
import org.eclipse.jgit.transport.URIish;
import org.viewer.config.ViewerGitConfig;
import org.viewer.converter.CloneHistoryConverter;
import org.viewer.repository.CloneHistoryRepository;
import org.viewer.store.MutinyMinioClient;
import org.viewer.util.gzip.GzipCompress;

import java.io.File;
import java.io.FileInputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;

import static io.smallrye.mutiny.Uni.createFrom;
import static io.smallrye.mutiny.unchecked.Unchecked.function;
import static org.eclipse.jgit.transport.Transport.open;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
public class GitServiceImpl implements GitService {

  private final ViewerGitConfig viewerGitConfig;

  private final MutinyMinioClient mutinyMinioClient;

  private final Vertx vertx;

  private final CloneHistoryConverter cloneHistoryConverter;

  private final CloneHistoryRepository cloneHistoryRepository;

  private final EventBus eventBus;

  @Override
  @WithTransaction
  public Uni<Void> cloneRepo(String url) {
    return createFrom().item(Git.cloneRepository())
      .call(cloneCommand -> vertx.fileSystem().createTempDirectory(String.valueOf(Math.abs(url.hashCode())))
        .invoke(tempDir -> log.atDebug().log("Git Local Repository:{}", tempDir))
        .invoke(tempDir ->
          cloneCommand
            .setDirectory(new File(tempDir))
            .setURI(url)
        )
      )
      .flatMap(function(cloneCommand -> vertx.executeBlocking(cloneCommand)))
      .flatMap(
        function((git -> vertx.fileSystem().createTempFile("test", ".tar.gz")))
      )
      .log("TempFile")
      .invoke(t -> GzipCompress.compressDir(viewerGitConfig.repositoryLocation().toFile().getAbsolutePath(), t))
      .flatMap(function(tempFile -> {
        val args = UploadObjectArgs.builder().filename(tempFile)
          .bucket("test")
          .object(url)
          .build();
        return mutinyMinioClient.uploadObject(args);
      }))
      .log("Upload Result")
      .map(cloneHistoryConverter::create)
      .flatMap(cloneHistoryRepository::persist)
      .replaceWithVoid();
  }

  @Override
  public Uni<Boolean> isOpensourceRepository(String url) throws URISyntaxException {
    return createFrom().item(new URIish(url))
      .map(function(t -> open(t)))
      .invoke(Transport::close)
      .onFailure()
      .invoke(t -> log.atError().log(t.getLocalizedMessage()))
      .log("Transport")
      .onItem()
      .transform(t -> false);
  }
}

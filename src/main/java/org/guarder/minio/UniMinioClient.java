package org.guarder.minio;

import io.minio.*;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import static io.smallrye.mutiny.Uni.createFrom;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
public class UniMinioClient {

  private final MinioAsyncClient minioAsyncClient;

  @SneakyThrows
  public Uni<ObjectWriteResponse> put(PutObjectArgs putObjectArgs) {
    return createFrom().completionStage(minioAsyncClient.putObject(putObjectArgs));
  }

  @SneakyThrows
  public Uni<Boolean> bucketExists(BucketExistsArgs bucketExistsArgs) {
    return createFrom().completionStage(minioAsyncClient.bucketExists(bucketExistsArgs));
  }

  @SneakyThrows
  public Uni<GetObjectResponse> getObject(GetObjectArgs getObjectArgs) {
    return createFrom().completionStage(minioAsyncClient.getObject(getObjectArgs));
  }

  @SneakyThrows
  public Uni<String> getPreSignedObjectUrl(GetPresignedObjectUrlArgs args) {
    return createFrom().item(minioAsyncClient.getPresignedObjectUrl(args));
  }

  @SneakyThrows
  public Uni<Void> makeBucket(MakeBucketArgs makeBucketArgs){
    return createFrom().completionStage(minioAsyncClient.makeBucket(makeBucketArgs));
  }
}

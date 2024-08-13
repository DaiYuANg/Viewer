package org.viewer.util.gzip;

import io.vavr.control.Try;
import lombok.Cleanup;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.google.common.base.Preconditions.checkArgument;

@UtilityClass
@Slf4j
public class GzipCompress {

  public void compressDir(String sourceDir, String target) {
    val nioPath = Path.of(sourceDir);
    log.atDebug().log("target:{}", target);
    val dir = nioPath.toFile();
    checkArgument(dir.exists(), "Source directory does not exist");
    checkArgument(dir.isDirectory(), "Source path is not a directory");

    Try.run(() -> {
      @Cleanup val fos = new FileOutputStream(target);
      @Cleanup val gcos = new GzipCompressorOutputStream(fos);
      @Cleanup val taos = new TarArchiveOutputStream(gcos);
      val context = GzipCompressContextBuilder.builder()
        .file(dir)
        .parent("")
        .taos(taos)
        .build();
      addFilesToTarGz(context);
    }).getOrElseThrow(e -> new RuntimeException("Error compressing directory", e));
  }

  private void addFilesToTarGz(@NotNull GzipCompressContext context) throws IOException {
    val file = context.file();
    val taos = context.taos();
    val parent = context.parent();

    val entryName = parent + file.getName();

    val newContext = GzipCompressContextBuilder.builder()
      .file(file)
      .parent(entryName)
      .taos(taos)
      .build();

    if (file.isFile()) {
      processFile(newContext);
      return;
    }

    if (file.isDirectory()) {
      processDirectory(newContext);
    }
  }

  private void processDirectory(@NotNull GzipCompressContext context) throws IOException {
    @Cleanup val paths = Files.list(context.file().toPath());
    paths.forEach(path -> Try.of(() -> GzipCompressContextBuilder.builder()
        .file(path.toFile())
        .parent(context.parent() + "/")
        .taos(context.taos())
        .build())
      .andThenTry(GzipCompress::addFilesToTarGz)
      .onFailure(IOException.class, io -> {
        throw new UncheckedIOException(io);
      }));
  }

  private void processFile(@NotNull GzipCompressContext context) throws IOException {
    val file = context.file();
    val taos = context.taos();
    val entry = new TarArchiveEntry(file, context.parent() + file.getName());

    taos.putArchiveEntry(entry);
    @Cleanup val fis = new FileInputStream(file);
    val buffer = new byte[1024];
    int count;
    while ((count = fis.read(buffer)) != -1) {
      taos.write(buffer, 0, count);
    }
    taos.closeArchiveEntry();
  }
}

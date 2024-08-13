package org.viewer.util.gzip;

import io.vavr.control.Try;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.Files.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class GzipCompressTest {
  private static final Faker faker = new Faker();
  private Path tempDir;
  private File sourceDir;
  private String targetFile;

  @BeforeEach
  void setUp() throws IOException {
    // 创建临时目录和文件，用于测试
    tempDir = createTempDirectory(faker.lorem().word());
    sourceDir = tempDir.toFile();
    targetFile = tempDir.resolve("test.tar.gz").toString();
    log.atInfo().log("targetFile:{}", targetFile);
    // 创建一些文件和子目录
    createFile(tempDir.resolve("file1.txt"));
    createFile(tempDir.resolve("file2.txt"));
    Files.createDirectories(tempDir.resolve("subdir"));
    createFile(tempDir.resolve("subdir/file3.txt"));
  }


  @Test
  void compressDir() {
// 调用压缩方法
    Try.run(() -> GzipCompress.compressDir(sourceDir.getAbsolutePath(), targetFile))
      .getOrElseThrow(e -> new RuntimeException("Compression failed", e));

    // 验证目标文件是否存在
    val tarGzFile = new File(targetFile);
    assertTrue(tarGzFile.exists(), "The compressed file should exist");
  }

  @AfterEach
  void tearDown() throws IOException {
    // 清理临时目录和文件
    @Cleanup val walk = Files.walk(tempDir);
    val deleteCount = walk.map(Path::toFile)
      .map(File::delete)
      .filter(v -> v)
      .count();
    log.atInfo().log("Clear Test File:{}", deleteCount);
  }
}
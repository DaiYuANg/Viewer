package org.guarder.config;

import io.minio.MinioAsyncClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
public class AsyncClientProduces {

  private final MinioConfiguration minioConfiguration;

  @Produces
  MinioAsyncClient asyncClient(){
    return MinioAsyncClient.builder()
      .endpoint(minioConfiguration.url())
      .credentials(minioConfiguration.accessKey(), minioConfiguration.secretKey())
      .build();
  }
}

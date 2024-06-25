package org.guarder.config;

import io.smallrye.config.ConfigMapping;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ConfigMapping(prefix = "quarkus.minio")
public interface MinioConfiguration {
  @ConfigProperty(name = "url")
  String url();

  @ConfigProperty(name = "port")
  String port();

  @ConfigProperty
  String accessKey();

  @ConfigProperty
  String secretKey();
}

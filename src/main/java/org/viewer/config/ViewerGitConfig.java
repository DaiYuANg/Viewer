package org.viewer.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

import java.nio.file.Path;

@ConfigMapping(prefix = "viewer.git")
public interface ViewerGitConfig {

  @WithDefault("repository")
  Path repositoryLocation();
}

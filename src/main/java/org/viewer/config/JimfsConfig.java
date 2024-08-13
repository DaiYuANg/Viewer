package org.viewer.config;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.FileSystem;

@ApplicationScoped
@Slf4j
public class JimfsConfig {

  @Produces
  @Named
  FileSystem memroryFileSystem() {
    return Jimfs.newFileSystem(Configuration.unix());
  }
}

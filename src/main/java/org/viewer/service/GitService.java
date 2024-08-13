package org.viewer.service;

import io.smallrye.mutiny.Uni;

import java.net.URISyntaxException;

public interface GitService {
  Uni<Void> cloneRepo(String url);

  Uni<Boolean> isOpensourceRepository(String url) throws URISyntaxException;
}

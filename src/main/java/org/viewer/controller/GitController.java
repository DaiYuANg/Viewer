package org.viewer.controller;

import io.smallrye.mutiny.Uni;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.viewer.service.GitService;

@Path("/git")
@RequiredArgsConstructor
@Timed
public class GitController {
  private final GitService gitService;

  @Path("/clone")
  @GET
  public Uni<Void> cloneRepository(@Valid @NotEmpty @QueryParam("url") String url) {
    return gitService.cloneRepo(url);
  }

  @SneakyThrows
  @Path("/is/opensource/repo")
  @GET
  public Uni<Boolean> isOpensourceRepository(@QueryParam("url") String url) {
    return gitService.isOpensourceRepository(url);
  }
}

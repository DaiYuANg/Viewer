package org.viewer.controller;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.viewer.repository.InternalUserRepository;

@Path("/sys")
@Slf4j
@RequiredArgsConstructor
public class SystemController {

  private final InternalUserRepository internalUserRepository;

  @GET
  @Path("/first/access")
  public Uni<Boolean> test() {
    return internalUserRepository.count().map(count -> count == 0);
  }
}

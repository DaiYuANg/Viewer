package org.guarder.controller;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import nl.basjes.parse.useragent.UserAgent;
import org.guarder.repository.UserRepository;

@Path("/sys")
@Slf4j
@RequiredArgsConstructor
public class SystemController {

  private final UserRepository userRepository;

  @GET
  @Path("/first/access")
  public Uni<Boolean> test() {
    return userRepository.count().map(count -> count == 0);
  }
}

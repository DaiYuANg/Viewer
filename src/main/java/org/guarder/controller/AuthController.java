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
import nl.basjes.parse.useragent.UserAgentAnalyzer;

@Path("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

  private final Uni<UserAgentAnalyzer> userAgentAnalyzerUni;

  @GET
  public Uni<UserAgent.ImmutableUserAgent> test(@Context HttpHeaders httpHeaders) {
    val useragent = httpHeaders.getHeaderString("User-Agent");
    return userAgentAnalyzerUni.map(userAgentAnalyzer -> userAgentAnalyzer.parse(useragent)).log();
  }
}

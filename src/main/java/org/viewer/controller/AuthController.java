package org.viewer.controller;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.viewer.model.AuthenticateResult;
import org.viewer.model.UsernameAndPasswordForm;
import org.viewer.service.AuthService;

@Path("/auth")
@Slf4j
@RequiredArgsConstructor
@Timed
public class AuthController {

  private final AuthService authService;

  @Path("/login")
  @POST
  public Uni<AuthenticateResult> loginWithUsernameAndPassword(UsernameAndPasswordForm usernameAndPasswordForm) {
    return authService.login(usernameAndPasswordForm);
  }
}

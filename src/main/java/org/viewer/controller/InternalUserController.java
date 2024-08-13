package org.viewer.controller;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.viewer.model.UsernameAndPasswordForm;
import org.viewer.service.InternalUserService;

@Path("/internal/user")
@Timed
@RequiredArgsConstructor
public class InternalUserController {

  private final InternalUserService internalUserService;

  @Path("/register")
  @POST
  public Uni<Void> register(UsernameAndPasswordForm usernameAndPasswordForm) {
    return internalUserService.register(usernameAndPasswordForm);
  }
}

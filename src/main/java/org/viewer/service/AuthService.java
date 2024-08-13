package org.viewer.service;

import io.smallrye.mutiny.Uni;
import org.viewer.model.AuthenticateResult;
import org.viewer.model.UsernameAndPasswordForm;

public interface AuthService {
  Uni<AuthenticateResult> login(UsernameAndPasswordForm usernameAndPasswordForm);
}

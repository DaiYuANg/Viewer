package org.viewer.service;

import io.smallrye.mutiny.Uni;
import org.viewer.model.UsernameAndPasswordForm;

public interface InternalUserService {
  Uni<Void> register(UsernameAndPasswordForm usernameAndPasswordForm);
}

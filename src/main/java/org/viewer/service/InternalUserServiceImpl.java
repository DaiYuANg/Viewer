package org.viewer.service;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.viewer.converter.InternalUserConverter;
import org.viewer.model.UsernameAndPasswordForm;
import org.viewer.repository.InternalUserRepository;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
public class InternalUserServiceImpl implements InternalUserService {

  private final InternalUserRepository internalUserRepository;

  private final InternalUserConverter internalUserConverter;

  @Override
  @WithTransaction
  public Uni<Void> register(UsernameAndPasswordForm usernameAndPasswordForm) {
    return Uni.createFrom().item(usernameAndPasswordForm)
      .log("Register form")
      .map(internalUserConverter::usernameAndPassword2InternalUser)
      .log("Register user")
      .map(internalUserRepository::persist)
      .replaceWithVoid();
  }
}

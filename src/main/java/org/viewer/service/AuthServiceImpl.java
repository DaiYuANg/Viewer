package org.viewer.service;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.mailer.reactive.ReactiveMailer;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.eclipse.microprofile.jwt.Claims;
import org.jetbrains.annotations.NotNull;
import org.viewer.model.AuthenticateResult;
import org.viewer.model.AuthenticateResultBuilder;
import org.viewer.model.UsernameAndPasswordForm;
import org.viewer.repository.InternalUserSimpleQuery;

import java.util.Arrays;
import java.util.HashSet;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final ReactiveMailer reactiveMailer;

  private final InternalUserSimpleQuery internalUserSimpleQuery;

  @Override
  @WithTransaction
  public Uni<AuthenticateResult> login(@NotNull UsernameAndPasswordForm usernameAndPasswordForm) {
    return internalUserSimpleQuery
      .findByUsername(usernameAndPasswordForm.username())
      .onFailure(NoResultException.class)
      .recoverWithNull()
      .log("Find User")
      .onItem()
      .ifNotNull()
      .transform(user -> {
        val jwt = Jwt.issuer("https://example.com/issuer")
          .upn("jdoe@quarkus.io")
          .groups(new HashSet<>(Arrays.asList("User", "Admin")))
          .claim(Claims.birthdate.name(), "2001-07-13")
          .sign();
        return AuthenticateResultBuilder.builder()
          .token(jwt)
          .build();
      });
  }
}

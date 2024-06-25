package org.guarder.service;

import io.quarkus.mailer.reactive.ReactiveMailer;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final ReactiveMailer reactiveMailer;
}

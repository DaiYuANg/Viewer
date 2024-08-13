package org.viewer.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.viewer.entity.InternalUser;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
public class InternalUserRepository implements PanacheRepository<InternalUser> {
}

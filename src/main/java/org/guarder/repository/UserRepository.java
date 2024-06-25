package org.guarder.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.guarder.entity.InternalUser;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
public class UserRepository implements PanacheRepository<InternalUser> {
}

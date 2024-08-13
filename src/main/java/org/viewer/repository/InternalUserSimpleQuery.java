package org.viewer.repository;

import io.smallrye.mutiny.Uni;
import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.processing.CheckHQL;
import org.hibernate.annotations.processing.Find;
import org.hibernate.reactive.mutiny.Mutiny;
import org.viewer.entity.InternalUser;

@CheckHQL
public interface InternalUserSimpleQuery {
  @Find
  Uni<InternalUser> findByUsername(String username);
}

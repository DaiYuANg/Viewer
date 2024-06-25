package org.guarder.entity;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table
@Getter
@Setter
@Cacheable
@Accessors(chain = true)
public class InternalUser extends BaseEntity {
  private String email;

  private String username;

  private String password;
}

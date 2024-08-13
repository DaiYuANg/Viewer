package org.viewer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
public class Workspace extends BaseEntity {
  private Integer userId;

  private String gitUrl;
}

package org.viewer.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@Accessors(chain = true)
public class BaseEntity extends PanacheEntity {

  @CreationTimestamp
  @Comment("Create Time")
  private Date createAt;

  @Version
  private Long version;
}

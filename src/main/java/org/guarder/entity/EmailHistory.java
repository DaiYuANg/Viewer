package org.guarder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Table
@Entity
@Getter
@Setter
public class EmailHistory extends BaseEntity {

  @Comment("email address")
  private String email;
}

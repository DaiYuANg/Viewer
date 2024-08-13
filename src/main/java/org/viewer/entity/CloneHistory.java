package org.viewer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Table
@Entity
@Getter
@Setter
public class CloneHistory extends BaseEntity {

  private String url;

  private String bucket;

  private String object;

}

package com.ymmihw.javax.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Department {
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "table-generator")
  @TableGenerator(name = "table-generator", table = "dep_ids", pkColumnName = "seq_id",
      valueColumnName = "seq_value")
  private long depId;

}

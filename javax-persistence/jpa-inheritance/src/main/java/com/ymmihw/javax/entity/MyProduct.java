package com.ymmihw.javax.entity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.INTEGER)
// @DiscriminatorFormula("case when author is not null then 1 else 2 end")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MyProduct {
  @Id
  private long productId;

  private String name;
}

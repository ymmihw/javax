package com.ymmihw.javax.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

  @Id
  @GeneratedValue(generator = "prod-generator")
  @GenericGenerator(name = "prod-generator",
      parameters = @Parameter(name = "prefix", value = "prod"),
      strategy = "com.ymmihw.javax.generator.MyGenerator")
  private String prodId;
}

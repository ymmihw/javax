package com.ymmihw.javax.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Polymorphism(type = PolymorphismType.EXPLICIT)
@Getter
@Setter
@AllArgsConstructor
public class Bag implements Item {

  @Id
  private long bagId;

  private String type;
}

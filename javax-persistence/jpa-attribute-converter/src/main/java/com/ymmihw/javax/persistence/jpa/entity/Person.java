package com.ymmihw.javax.persistence.jpa.entity;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Person {

  @Id
  @GeneratedValue
  private Long id;

  @Convert(converter = PersonNameConverter.class)
  private PersonName personName;

}

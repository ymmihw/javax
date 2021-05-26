package com.ymmihw.javax.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Setter
@Getter
public class Owner {
  private String name;
  private String surname;
}

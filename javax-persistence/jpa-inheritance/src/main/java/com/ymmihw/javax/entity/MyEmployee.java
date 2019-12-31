package com.ymmihw.javax.entity;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class MyEmployee extends Person {
  private String company;

  public MyEmployee(long personId, String name, String company) {
    super(personId, name);
    this.company = company;
  }
}

package com.ymmihw.javax.entity.auto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Auto2 {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long studentId;

  private String name;

  private int age;

}

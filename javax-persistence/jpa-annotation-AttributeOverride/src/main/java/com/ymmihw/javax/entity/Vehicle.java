package com.ymmihw.javax.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class Vehicle {
  @Id @GeneratedValue private Integer id;
  private String identifier;
  private Integer numberOfWheels;
}

package com.ymmihw.javax.entity;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Car extends Vehicle {
  private String engine;

  public Car(long vehicleId, String manufacturer, String engine) {
    super(vehicleId, manufacturer);
    this.engine = engine;
  }
}

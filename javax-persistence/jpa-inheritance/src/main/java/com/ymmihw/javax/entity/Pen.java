package com.ymmihw.javax.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("2")
@NoArgsConstructor
@Getter
@Setter
public class Pen extends MyProduct {
  private String color;

  public Pen(long productId, String name, String color) {
    super(productId, name);
    this.color = color;
  }
}

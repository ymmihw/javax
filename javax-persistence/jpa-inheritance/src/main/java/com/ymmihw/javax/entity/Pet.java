package com.ymmihw.javax.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "petId")
@NoArgsConstructor
@Getter
@Setter
public class Pet extends Animal {
  private String name;

  public Pet(long animalId, String species, String name) {
    super(animalId, species);
    this.name = name;
  }
}

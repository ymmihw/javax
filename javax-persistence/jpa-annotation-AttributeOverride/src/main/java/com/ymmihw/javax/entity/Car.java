package com.ymmihw.javax.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Map;

@Entity
@AttributeOverride(name = "identifier", column = @Column(name = "VIN"))
@Setter
@Getter
public class Car extends Vehicle {

  private String model;
  private String name;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "name", column = @Column(name = "BRAND_NAME", length = 5)),
    @AttributeOverride(name = "address.name", column = @Column(name = "ADDRESS_NAME"))
  })
  private Brand brand;

  @ElementCollection
  @AttributeOverrides({
    @AttributeOverride(name = "key.name", column = @Column(name = "OWNER_NAME")),
    @AttributeOverride(name = "key.surname", column = @Column(name = "OWNER_SURNAME")),
    @AttributeOverride(name = "value.name", column = @Column(name = "ADDRESS_NAME")),
  })
  Map<Owner, Address> owners;
}

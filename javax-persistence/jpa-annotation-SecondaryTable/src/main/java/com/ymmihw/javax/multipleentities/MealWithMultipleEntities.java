package com.ymmihw.javax.multipleentities;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "meal")
@Getter
@Setter
public class MealWithMultipleEntities {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "price")
  private BigDecimal price;

  @OneToOne(mappedBy = "meal")
  private AllergensAsEntity allergens;

  @Override
  public String toString() {
    return "MealWithMultipleEntities [id=" + id + ", name=" + name + ", description=" + description
        + ", price=" + price + ", allergens=" + allergens + "]";
  }

}

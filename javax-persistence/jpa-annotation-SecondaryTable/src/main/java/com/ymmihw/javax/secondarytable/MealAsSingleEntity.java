package com.ymmihw.javax.secondarytable;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "meal")
@SecondaryTable(name = "allergens", pkJoinColumns = @PrimaryKeyJoinColumn(name = "meal_id"))
@Getter
@Setter
public class MealAsSingleEntity {

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

  @Column(name = "peanuts", table = "allergens")
  private boolean peanuts;

  @Column(name = "celery", table = "allergens")
  private boolean celery;

  @Column(name = "sesame_seeds", table = "allergens")
  private boolean sesameSeeds;


  @Override
  public String toString() {
    return "MealAsSingleEntity [id=" + id + ", name=" + name + ", description=" + description
        + ", price=" + price + ", peanuts=" + peanuts + ", celery=" + celery + ", sesameSeeds="
        + sesameSeeds + "]";
  }

}

package com.ymmihw.javax.multipleentities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import com.ymmihw.javax.secondarytable.MealAsSingleEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "allergens")
@Getter
@Setter
public class AllergensAsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "meal_id")
  private Long mealId;

  @OneToOne
  @PrimaryKeyJoinColumn(name = "meal_id")
  private MealAsSingleEntity meal;

  @Column(name = "peanuts")
  private boolean peanuts;

  @Column(name = "celery")
  private boolean celery;

  @Column(name = "sesame_seeds")
  private boolean sesameSeeds;

  @Override
  public String toString() {
    return "AllergensAsEntity [peanuts=" + peanuts + ", celery=" + celery + ", sesameSeeds="
        + sesameSeeds + "]";
  }

}

package com.ymmihw.javax.secondarytable.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class AllergensAsEmbeddable {

  @Column(name = "peanuts", table = "allergens")
  private boolean peanuts;

  @Column(name = "celery", table = "allergens")
  private boolean celery;

  @Column(name = "sesame_seeds", table = "allergens")
  private boolean sesameSeeds;

  @Override
  public String toString() {
    return "AllergensAsEmbeddable [peanuts=" + peanuts + ", celery=" + celery + ", sesameSeeds="
        + sesameSeeds + "]";
  }

}

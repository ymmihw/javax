package com.ymmihw.javax.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("1")
@NoArgsConstructor
@Getter
@Setter
public class Book extends MyProduct {
  private String author;

  public Book(long productId, String name, String author) {
    super(productId, name);
    this.author = author;
  }
}

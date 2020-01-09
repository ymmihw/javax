package com.ymmihw.javax.models;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class NullableItem {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private BigDecimal price;
}

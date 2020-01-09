package com.ymmihw.javax.models;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class NotNullItem {

  @Id
  @GeneratedValue
  private Long id;

  @NotNull
  private BigDecimal price;
}

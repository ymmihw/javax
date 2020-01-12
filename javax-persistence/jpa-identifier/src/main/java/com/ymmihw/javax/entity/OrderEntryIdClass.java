package com.ymmihw.javax.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(OrderEntryPK.class)
@Getter
@Setter
public class OrderEntryIdClass {
  @Id
  private long orderId;
  @Id
  private long productId;
}

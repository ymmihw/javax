package com.ymmihw.javax.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class OrderEntryPK implements Serializable {
  private static final long serialVersionUID = 1L;
  private long orderId;
  private long productId;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderEntryPK pk = (OrderEntryPK) o;
    return Objects.equals(orderId, pk.orderId) && Objects.equals(productId, pk.productId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderId, productId);
  }
}

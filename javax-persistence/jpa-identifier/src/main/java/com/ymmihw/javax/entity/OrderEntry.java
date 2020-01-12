package com.ymmihw.javax.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderEntry {

  @EmbeddedId
  private OrderEntryPK entryId;

}

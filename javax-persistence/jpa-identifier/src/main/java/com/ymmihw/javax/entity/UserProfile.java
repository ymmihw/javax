package com.ymmihw.javax.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserProfile {
  @Id
  private long profileId;

  @OneToOne
  @MapsId
  private User user;
}

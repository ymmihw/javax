package com.ymmihw.javax.entity;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Course {
  @Id
  @GeneratedValue
  private UUID courseId;
}

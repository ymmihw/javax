package com.ymmihw.javax.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
  @Id
  @GeneratedValue(generator = "sequence-generator")
  @GenericGenerator(name = "sequence-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "user_sequence"),
          @Parameter(name = "initial_value", value = "4"),
          @Parameter(name = "increment_size", value = "1")})
  private long userId;

}

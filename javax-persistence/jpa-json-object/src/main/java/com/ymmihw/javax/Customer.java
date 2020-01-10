package com.ymmihw.javax;

import java.util.Map;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {

  @Id
  private int id;

  private String firstName;

  private String lastName;

  @Convert(converter = HashMapConverter.class)
  private Map<String, Object> customerAttributes;

}

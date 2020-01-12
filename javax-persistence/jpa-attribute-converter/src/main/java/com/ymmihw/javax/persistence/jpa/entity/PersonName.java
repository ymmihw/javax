package com.ymmihw.javax.persistence.jpa.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonName implements Serializable {

  private static final long serialVersionUID = 7883094644631050150L;

  private String name;

  private String surname;


}

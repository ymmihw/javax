package com.ymmihw.javax;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserNotNull {

  @NotNull(message = "Name is mandatory")
  private final String name;


  @Override
  public String toString() {
    return "User{" + "name=" + name + "}";
  }
}

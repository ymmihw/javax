package com.ymmihw.javax;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserNotBlank {

  @NotBlank(message = "Name is mandatory")
  private final String name;

  @Override
  public String toString() {
    return "User{" + "name=" + name + "}";
  }
}

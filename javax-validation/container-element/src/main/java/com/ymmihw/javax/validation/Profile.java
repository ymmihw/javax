package com.ymmihw.javax.validation;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class Profile {
  @NotBlank private String companyName;
}

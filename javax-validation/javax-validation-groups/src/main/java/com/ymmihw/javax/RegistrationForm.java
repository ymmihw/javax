package com.ymmihw.javax;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RegistrationForm {
  @NotBlank(groups = BasicInfo.class)
  private String firstName;
  @NotBlank(groups = BasicInfo.class)
  private String lastName;
  @Email(groups = BasicInfo.class)
  private String email;
  @NotBlank(groups = BasicInfo.class)
  private String phone;

  @NotBlank(groups = {BasicInfo.class, AdvanceInfo.class})
  private String captcha;

  @NotBlank(groups = AdvanceInfo.class)
  private String street;
  @NotBlank(groups = AdvanceInfo.class)
  private String houseNumber;
  @NotBlank(groups = AdvanceInfo.class)
  private String zipCode;
  @NotBlank(groups = AdvanceInfo.class)
  private String city;
  @NotBlank(groups = AdvanceInfo.class)
  private String country;

}

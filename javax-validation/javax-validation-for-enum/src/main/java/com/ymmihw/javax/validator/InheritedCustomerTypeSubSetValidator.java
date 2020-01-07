package com.ymmihw.javax.validator;

import com.ymmihw.javax.annotation.CustomerTypeSubset;
import com.ymmihw.javax.model.CustomerType;

public class InheritedCustomerTypeSubSetValidator
    extends EnumSubSetValidator<CustomerTypeSubset, CustomerType> {
  @Override
  public void initialize(CustomerTypeSubset constraint) {
    super.initialize(constraint.anyOf());
  }
}

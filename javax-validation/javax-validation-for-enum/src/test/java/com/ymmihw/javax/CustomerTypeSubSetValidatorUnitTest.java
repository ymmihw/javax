package com.ymmihw.javax;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.ymmihw.javax.model.Customer;
import com.ymmihw.javax.model.CustomerType;

public class CustomerTypeSubSetValidatorUnitTest {

  private static Validator validator;

  @BeforeAll
  public static void setupValidatorInstance() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Test
  public void whenEnumAnyOfSubset_thenShouldNotReportConstraintViolations() {
    Customer customer = new Customer.Builder().withCustomerTypeOfSubset(CustomerType.NEW).build();
    Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
    assertThat(violations.isEmpty()).isTrue();
  }

  @Test
  public void whenEnumNotAnyOfSubset_thenShouldGiveOccurrenceOfConstraintViolations() {
    Customer customer =
        new Customer.Builder().withCustomerTypeOfSubset(CustomerType.DEFAULT).build();
    Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
    assertThat(violations.size()).isEqualTo(1);

    assertThat(violations).anyMatch(CustomerUnitTest.havingPropertyPath("customerTypeOfSubset")
        .and(CustomerUnitTest.havingMessage("must be any of [NEW, OLD]")));
  }
}

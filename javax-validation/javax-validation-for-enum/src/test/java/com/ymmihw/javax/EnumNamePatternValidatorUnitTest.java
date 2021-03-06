package com.ymmihw.javax;

import static com.ymmihw.javax.model.CustomerType.DEFAULT;
import static com.ymmihw.javax.model.CustomerType.OLD;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.ymmihw.javax.model.Customer;

public class EnumNamePatternValidatorUnitTest {

  private static Validator validator;

  @BeforeAll
  public static void setupValidatorInstance() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Test
  public void whenEnumMatchesRegex_thenShouldNotReportConstraintViolations() {
    Customer customer = new Customer.Builder().withCustomerTypeMatchesPattern(DEFAULT).build();
    Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
    assertThat(violations.isEmpty()).isTrue();
  }

  @Test
  public void whenEnumNull_thenShouldNotReportConstraintViolations() {
    Customer customer = new Customer.Builder().withCustomerTypeMatchesPattern(null).build();
    Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
    assertThat(violations.isEmpty()).isTrue();
  }

  @Test
  public void whenEnumDoesNotMatchRegex_thenShouldGiveOccurrenceOfConstraintViolations() {
    Customer customer = new Customer.Builder().withCustomerTypeMatchesPattern(OLD).build();
    Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
    assertThat(violations.size()).isEqualTo(1);

    assertThat(violations)
        .anyMatch(CustomerUnitTest.havingPropertyPath("customerTypeMatchesPattern")
            .and(CustomerUnitTest.havingMessage("must match \"NEW|DEFAULT\"")));
  }
}

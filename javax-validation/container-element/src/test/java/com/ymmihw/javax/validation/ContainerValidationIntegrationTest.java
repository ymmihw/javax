package com.ymmihw.javax.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Collections;
import java.util.OptionalInt;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.ymmihw.javax.validation.extractor.ProfileValueExtractor;

public class ContainerValidationIntegrationTest {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.byDefaultProvider().configure()
        .addValueExtractor(new ProfileValueExtractor()).buildValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void whenEmptyAddress_thenValidationFails() {
    Customer customer = new Customer();
    customer.setName("John");
    customer.setAddresses(Collections.singletonList(" "));
    Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
    assertEquals(1, violations.size());
    assertEquals("Address must not be blank", violations.iterator().next().getMessage());
  }

  @Test
  public void whenInvalidEmail_thenValidationFails() {
    CustomerMap map = new CustomerMap();
    map.setCustomers(Collections.singletonMap("john", new Customer()));
    Set<ConstraintViolation<CustomerMap>> violations = validator.validate(map);
    assertEquals(1, violations.size());
    assertEquals("Must be a valid email", violations.iterator().next().getMessage());
  }

  @Test
  public void whenAgeTooLow_thenValidationFails() {
    Customer customer = new Customer();
    customer.setName("John");
    customer.setAge(15);
    Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
    assertEquals(1, violations.size());
  }

  @Test
  public void whenAgeNull_thenValidationSucceeds() {
    Customer customer = new Customer();
    customer.setName("John");
    Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
    assertEquals(0, violations.size());
  }

  @Test
  public void whenNumberOrdersValid_thenValidationSucceeds() {
    Customer customer = new Customer();
    customer.setName("John");
    customer.setNumberOfOrders(OptionalInt.of(1));
    Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
    assertEquals(0, violations.size());
  }

  @Test
  public void whenProfileCompanyNameBlank_thenValidationFails() {
    Customer customer = new Customer();
    customer.setName("John");
    Profile profile = new Profile();
    profile.setCompanyName(" ");
    customer.setProfile(profile);
    Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
    assertEquals(1, violations.size());
    System.out.println(violations);
  }

}

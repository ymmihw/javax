package com.ymmihw.javax.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.ymmihw.javax.validation.model.Customer;
import com.ymmihw.javax.validation.model.Reservation;
import com.ymmihw.javax.validation.model.ReservationManagement;

public class ValidationIntegrationTest {

  private ExecutableValidator executableValidator;

  @BeforeEach
  public void getExecutableValidator() {

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    this.executableValidator = factory.getValidator().forExecutables();
  }

  @Test
  public void whenValidationWithInvalidMethodParameters_thenCorrectNumberOfVoilations()
      throws NoSuchMethodException {

    ReservationManagement object = new ReservationManagement();
    Method method = ReservationManagement.class.getMethod("createReservation", LocalDate.class,
        int.class, Customer.class);
    Object[] parameterValues = {LocalDate.now(), 0, null};
    Set<ConstraintViolation<ReservationManagement>> violations =
        executableValidator.validateParameters(object, method, parameterValues);

    assertEquals(3, violations.size());
  }

  @Test
  public void whenValidationWithValidMethodParameters_thenZeroVoilations()
      throws NoSuchMethodException {

    ReservationManagement object = new ReservationManagement();
    Method method = ReservationManagement.class.getMethod("createReservation", LocalDate.class,
        int.class, Customer.class);
    Object[] parameterValues = {LocalDate.now().plusDays(1), 1, new Customer("John", "Doe")};
    Set<ConstraintViolation<ReservationManagement>> violations =
        executableValidator.validateParameters(object, method, parameterValues);

    assertEquals(0, violations.size());
  }

  @Test
  public void whenCrossParameterValidationWithInvalidParameters_thenCorrectNumberOfVoilations()
      throws NoSuchMethodException {

    ReservationManagement object = new ReservationManagement();
    Method method = ReservationManagement.class.getMethod("createReservation", LocalDate.class,
        LocalDate.class, Customer.class);
    Object[] parameterValues = {LocalDate.now(), LocalDate.now(), new Customer("John", "Doe")};
    Set<ConstraintViolation<ReservationManagement>> violations =
        executableValidator.validateParameters(object, method, parameterValues);

    assertEquals(1, violations.size());
  }

  @Test
  public void whenCrossParameterValidationWithValidParameters_thenZeroVoilations()
      throws NoSuchMethodException {

    ReservationManagement object = new ReservationManagement();
    Method method = ReservationManagement.class.getMethod("createReservation", LocalDate.class,
        LocalDate.class, Customer.class);
    Object[] parameterValues =
        {LocalDate.now().plusDays(1), LocalDate.now().plusDays(2), new Customer("John", "Doe")};
    Set<ConstraintViolation<ReservationManagement>> violations =
        executableValidator.validateParameters(object, method, parameterValues);

    assertEquals(0, violations.size());
  }

  @Test
  public void whenValidationWithInvalidConstructorParameters_thenCorrectNumberOfVoilations()
      throws NoSuchMethodException {

    Constructor<Customer> constructor = Customer.class.getConstructor(String.class, String.class);
    Object[] parameterValues = {"John", "Doe"};
    Set<ConstraintViolation<Customer>> violations =
        executableValidator.validateConstructorParameters(constructor, parameterValues);

    assertEquals(2, violations.size());
  }

  @Test
  public void whenValidationWithValidConstructorParameters_thenZeroVoilations()
      throws NoSuchMethodException {

    Constructor<Customer> constructor = Customer.class.getConstructor(String.class, String.class);
    Object[] parameterValues = {"William", "Smith"};
    Set<ConstraintViolation<Customer>> violations =
        executableValidator.validateConstructorParameters(constructor, parameterValues);

    assertEquals(0, violations.size());
  }

  @Test
  public void whenCrossParameterValidationWithInvalidConstructorParameters_thenCorrectNumberOfVoilations()
      throws NoSuchMethodException {

    Constructor<Reservation> constructor = Reservation.class.getConstructor(LocalDate.class,
        LocalDate.class, Customer.class, int.class);
    Object[] parameterValues =
        {LocalDate.now(), LocalDate.now(), new Customer("William", "Smith"), 1};
    Set<ConstraintViolation<Reservation>> violations =
        executableValidator.validateConstructorParameters(constructor, parameterValues);

    assertEquals(1, violations.size());
  }

  @Test
  public void whenCrossParameterValidationWithValidConstructorParameters_thenZeroVoilations()
      throws NoSuchMethodException {

    Constructor<Reservation> constructor = Reservation.class.getConstructor(LocalDate.class,
        LocalDate.class, Customer.class, int.class);
    Object[] parameterValues = {LocalDate.now().plusDays(1), LocalDate.now().plusDays(2),
        new Customer("William", "Smith"), 1};
    Set<ConstraintViolation<Reservation>> violations =
        executableValidator.validateConstructorParameters(constructor, parameterValues);

    assertEquals(0, violations.size());
  }

  @Test
  public void whenValidationWithInvalidReturnValue_thenCorrectNumberOfVoilations()
      throws NoSuchMethodException {

    ReservationManagement object = new ReservationManagement();
    Method method = ReservationManagement.class.getMethod("getAllCustomers");
    Object returnValue = Collections.<Customer>emptyList();
    Set<ConstraintViolation<ReservationManagement>> violations =
        executableValidator.validateReturnValue(object, method, returnValue);

    assertEquals(1, violations.size());
  }

  @Test
  public void whenValidationWithValidReturnValue_thenZeroVoilations() throws NoSuchMethodException {

    ReservationManagement object = new ReservationManagement();
    Method method = ReservationManagement.class.getMethod("getAllCustomers");
    Object returnValue = Collections.singletonList(new Customer("William", "Smith"));
    Set<ConstraintViolation<ReservationManagement>> violations =
        executableValidator.validateReturnValue(object, method, returnValue);

    assertEquals(0, violations.size());
  }

  @Test
  public void whenValidationWithInvalidConstructorReturnValue_thenCorrectNumberOfVoilations()
      throws NoSuchMethodException {

    Constructor<Reservation> constructor = Reservation.class.getConstructor(LocalDate.class,
        LocalDate.class, Customer.class, int.class);
    Reservation createdObject =
        new Reservation(LocalDate.now(), LocalDate.now(), new Customer("William", "Smith"), 0);
    Set<ConstraintViolation<Reservation>> violations =
        executableValidator.validateConstructorReturnValue(constructor, createdObject);

    assertEquals(1, violations.size());
  }

  @Test
  public void whenValidationWithValidConstructorReturnValue_thenZeroVoilations()
      throws NoSuchMethodException {

    Constructor<Reservation> constructor = Reservation.class.getConstructor(LocalDate.class,
        LocalDate.class, Customer.class, int.class);
    Reservation createdObject = new Reservation(LocalDate.now().plusDays(1),
        LocalDate.now().plusDays(2), new Customer("William", "Smith"), 1);
    Set<ConstraintViolation<Reservation>> violations =
        executableValidator.validateConstructorReturnValue(constructor, createdObject);

    assertEquals(0, violations.size());
  }

  @Test
  public void whenValidationWithInvalidCascadedValue_thenCorrectNumberOfVoilations()
      throws NoSuchMethodException {

    ReservationManagement object = new ReservationManagement();
    Method method = ReservationManagement.class.getMethod("createReservation", Reservation.class);
    Customer customer = new Customer();
    customer.setFirstName("John");
    customer.setLastName("Doe");
    Reservation reservation =
        new Reservation(LocalDate.now().plusDays(1), LocalDate.now().plusDays(2), customer, 1);
    Object[] parameterValues = {reservation};
    Set<ConstraintViolation<ReservationManagement>> violations =
        executableValidator.validateParameters(object, method, parameterValues);

    assertEquals(2, violations.size());
  }

  @Test
  public void whenValidationWithValidCascadedValue_thenCorrectNumberOfVoilations()
      throws NoSuchMethodException {

    ReservationManagement object = new ReservationManagement();
    Method method = ReservationManagement.class.getMethod("createReservation", Reservation.class);
    Customer customer = new Customer();
    customer.setFirstName("William");
    customer.setLastName("Smith");
    Reservation reservation =
        new Reservation(LocalDate.now().plusDays(1), LocalDate.now().plusDays(2), customer, 1);
    Object[] parameterValues = {reservation};
    Set<ConstraintViolation<ReservationManagement>> violations =
        executableValidator.validateParameters(object, method, parameterValues);

    assertEquals(0, violations.size());
  }

}

package com.ymmihw.javax.validation;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ymmihw.javax.validation.model.Customer;
import com.ymmihw.javax.validation.model.Reservation;
import com.ymmihw.javax.validation.model.ReservationManagement;

@SpringBootTest(classes = {MethodValidationConfig.class})
public class ContainerValidationIntegrationTest {

  @Autowired
  ReservationManagement reservationManagement;

  @Test
  public void whenValidationWithInvalidMethodParameters_thenConstraintViolationException() {
    assertThrows(ConstraintViolationException.class,
        () -> reservationManagement.createReservation(LocalDate.now(), 0, null));
  }

  @Test
  public void whenValidationWithValidMethodParameters_thenNoException() {

    reservationManagement.createReservation(LocalDate.now().plusDays(1), 1,
        new Customer("William", "Smith"));
  }

  @Test
  public void whenCrossParameterValidationWithInvalidParameters_thenConstraintViolationException() {

    assertThrows(ConstraintViolationException.class,
        () -> reservationManagement.createReservation(LocalDate.now(), LocalDate.now(), null));
  }

  @Test
  public void whenCrossParameterValidationWithValidParameters_thenNoException() {

    reservationManagement.createReservation(LocalDate.now().plusDays(1),
        LocalDate.now().plusDays(2), new Customer("William", "Smith"));
  }

  @Test
  public void whenValidationWithInvalidReturnValue_thenConstraintViolationException() {
    assertThrows(ConstraintViolationException.class, () -> reservationManagement.getAllCustomers());
  }

  @Test
  public void whenValidationWithInvalidCascadedValue_thenConstraintViolationException() {

    Customer customer = new Customer();
    customer.setFirstName("John");
    customer.setLastName("Doe");
    Reservation reservation =
        new Reservation(LocalDate.now().plusDays(1), LocalDate.now().plusDays(2), customer, 1);
    assertThrows(ConstraintViolationException.class,
        () -> reservationManagement.createReservation(reservation));
  }

  @Test
  public void whenValidationWithValidCascadedValue_thenCNoException() {

    Customer customer = new Customer();
    customer.setFirstName("William");
    customer.setLastName("Smith");
    Reservation reservation =
        new Reservation(LocalDate.now().plusDays(1), LocalDate.now().plusDays(2), customer, 1);

    reservationManagement.createReservation(reservation);
  }
}

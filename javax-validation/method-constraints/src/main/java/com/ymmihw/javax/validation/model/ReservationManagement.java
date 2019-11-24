package com.ymmihw.javax.validation.model;

import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import com.ymmihw.javax.validation.constraints.ConsistentDateParameters;

@Controller
@Validated
public class ReservationManagement {

  @ConsistentDateParameters
  public void createReservation(LocalDate begin, LocalDate end, @NotNull Customer customer) {

    // ...
  }

  public void createReservation(@NotNull @Future LocalDate begin, @Min(1) int duration,
      @NotNull Customer customer) {

    // ...
  }

  public void createReservation(@Valid Reservation reservation) {

    // ...
  }

  @NotNull
  @Size(min = 1)
  public List<@NotNull Customer> getAllCustomers() {

    return null;
  }

  @Valid
  public Reservation getReservationById(int id) {

    return null;
  }
}

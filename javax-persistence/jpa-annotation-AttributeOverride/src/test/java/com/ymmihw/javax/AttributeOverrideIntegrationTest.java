package com.ymmihw.javax;

import com.ymmihw.javax.entity.Address;
import com.ymmihw.javax.entity.Brand;
import com.ymmihw.javax.entity.Car;
import com.ymmihw.javax.repository.CarRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest(classes = {Application.class})
public class AttributeOverrideIntegrationTest {

  private static final LocalDate FORD_FOUNDATION_DATE = LocalDate.parse("1903-06-16");
  @Autowired CarRepository carRepository;

  @Test
  @Transactional
  public void whenInsertingCar_thenEmbeddedAndMappedFieldsArePopulated() {

    Car fordMustang = createMustang();

    carRepository.save(fordMustang);
    Car actualCar = carRepository.getOne(fordMustang.getId());

    Assertions.assertThat(actualCar).isEqualTo(fordMustang);
  }

  private Car createMustang() {
    Address address = new Address();
    address.setName("Ford United States");
    address.setCity("Dearborn");

    Brand ford = new Brand();
    ford.setName("Ford");
    ford.setFoundationDate(FORD_FOUNDATION_DATE);

    Car fordMustang = new Car();
    fordMustang.setIdentifier("WP1AB29P88LA47599");
    fordMustang.setModel("Ford");
    fordMustang.setName("My car");
    fordMustang.setBrand(ford);
    return fordMustang;
  }
}

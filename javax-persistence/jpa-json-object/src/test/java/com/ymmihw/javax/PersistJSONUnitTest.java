package com.ymmihw.javax;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import com.ymmihw.javax.entity.Customer;
import com.ymmihw.javax.entity.CustomerWithAnotherField;
import com.ymmihw.javax.repo.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@TestPropertySource("classpath:hibernate-persistjson.properties")
@Transactional
public class PersistJSONUnitTest {
  @Autowired
  private CustomerRepository customerRepository;

  @Test
  public void givenCustomer_whenCallingSerializeCustomerAttributes_thenAttributesAreConverted()
      throws IOException {

    CustomerWithAnotherField customer = new CustomerWithAnotherField();
    customer.setFirstName("first name");
    customer.setLastName("last name");

    Map<String, Object> attributes = new HashMap<>();
    attributes.put("address", "123 Main Street");
    attributes.put("zipcode", 12345);

    customer.setCustomerAttributes(attributes);

    customer.serializeCustomerAttributes();

    String serialized = customer.getCustomerAttributeJSON();

    customer.setCustomerAttributeJSON(serialized);
    customer.deserializeCustomerAttributes();

    Map<String, Object> deserialized = customer.getCustomerAttributes();

    assertEquals("123 Main Street", deserialized.get("address"));
  }

  @Test
  public void givenCustomer_whenSaving_thenAttributesAreConverted() {

    Customer customer = new Customer();
    customer.setFirstName("first name");
    customer.setLastName("last name");

    Map<String, Object> attributes = new HashMap<>();
    attributes.put("address", "123 Main Street");
    attributes.put("zipcode", 12345);

    customer.setCustomerAttributes(attributes);

    customer = customerRepository.save(customer);

    customer = customerRepository.getOne(customer.getId());

    assertEquals(2, customer.getCustomerAttributes().size());
  }

}

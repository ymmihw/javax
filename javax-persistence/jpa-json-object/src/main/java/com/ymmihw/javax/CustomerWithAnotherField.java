package com.ymmihw.javax;

import java.io.IOException;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CustomerWithAnotherField {

  @Id
  private int id;

  private String firstName;

  private String lastName;

  private String customerAttributeJSON;

  @Transient
  private Map<String, Object> customerAttributes;

  private static final ObjectMapper objectMapper = new ObjectMapper();

  public void serializeCustomerAttributes() throws JsonProcessingException {
    this.customerAttributeJSON = objectMapper.writeValueAsString(customerAttributes);
  }

  @SuppressWarnings("unchecked")
  public void deserializeCustomerAttributes() throws IOException {
    this.customerAttributes = objectMapper.readValue(customerAttributeJSON, Map.class);
  }

}

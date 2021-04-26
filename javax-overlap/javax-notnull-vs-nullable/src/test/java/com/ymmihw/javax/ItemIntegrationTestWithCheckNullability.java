package com.ymmihw.javax;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.hibernate.PropertyValueException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import com.ymmihw.javax.daos.NotNullItemRepository;
import com.ymmihw.javax.daos.NullableItemRepository;
import com.ymmihw.javax.models.NotNullItem;
import com.ymmihw.javax.models.NullableItem;

@SpringBootTest(classes = NotNullVsNullableApplication.class)
@TestPropertySource("classpath:application.check_nullability.properties")
public class ItemIntegrationTestWithCheckNullability extends LocaleAwareUnitTest {

  @Autowired
  private NotNullItemRepository notNullItemRepository;

  @Autowired
  private NullableItemRepository nullableItemRepository;

  @Test
  public void shouldNotAllowToPersistNullItemsPrice() {
    assertThatThrownBy(() -> notNullItemRepository.save(new NotNullItem()))
        .hasRootCauseInstanceOf(PropertyValueException.class)
        .hasStackTraceContaining("not-null property references a null or transient value");
  }

  @Test
  public void shouldNotAllowToPersistNullableItemsPrice() {
    assertThatThrownBy(() -> nullableItemRepository.save(new NullableItem()))
        .hasRootCauseInstanceOf(PropertyValueException.class)
        .hasStackTraceContaining("not-null property references a null or transient value");
  }

}

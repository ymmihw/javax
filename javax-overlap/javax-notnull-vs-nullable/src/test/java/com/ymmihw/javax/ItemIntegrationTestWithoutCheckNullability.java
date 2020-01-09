package com.ymmihw.javax;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import javax.validation.ConstraintViolationException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.ymmihw.javax.daos.NotNullItemRepository;
import com.ymmihw.javax.daos.NullableItemRepository;
import com.ymmihw.javax.models.NotNullItem;
import com.ymmihw.javax.models.NullableItem;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NotNullVsNullableApplication.class)
public class ItemIntegrationTestWithoutCheckNullability extends LocaleAwareUnitTest {

  @Autowired
  private NotNullItemRepository notNullItemRepository;

  @Autowired
  private NullableItemRepository nullableItemRepository;

  @Test
  public void shouldNotAllowToPersistNullItemsPrice() {
    assertThatThrownBy(() -> notNullItemRepository.save(new NotNullItem()))
        .hasRootCauseInstanceOf(ConstraintViolationException.class)
        .hasStackTraceContaining("must not be null");
  }

  @Test
  public void shouldNotAllowToPersistNullableItemsPrice() {
    assertThatThrownBy(() -> nullableItemRepository.save(new NullableItem()))
        .hasRootCauseInstanceOf(JdbcSQLIntegrityConstraintViolationException.class)
        .hasStackTraceContaining("NULL not allowed for column \"PRICE\"");
  }

}

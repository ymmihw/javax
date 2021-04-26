package com.ymmihw.javax;

import static org.assertj.core.api.Assertions.assertThat;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ymmihw.javax.multipleentities.MealWithMultipleEntities;
import com.ymmihw.javax.secondarytable.MealAsSingleEntity;
import com.ymmihw.javax.secondarytable.embeddable.MealWithEmbeddedAllergens;

@SpringBootTest(classes = App.class)
public class MultipleTablesIntegrationTest {
  @Autowired
  private EntityManager em;

  @Test
  public void entityManager_shouldLoadMealAsSingleEntity() {
    // given

    // when
    MealAsSingleEntity meal = em.find(MealAsSingleEntity.class, 1L);

    // then
    assertThat(meal).isNotNull();
    assertThat(meal.getId()).isEqualTo(1L);
    assertThat(meal.isPeanuts()).isFalse();
    assertThat(meal.isCelery()).isTrue();
  }

  @Test
  public void entityManager_shouldLoadMealWithEmbeddedAllergens() {
    // given

    // when
    MealWithEmbeddedAllergens meal = em.find(MealWithEmbeddedAllergens.class, 1L);

    // then
    assertThat(meal).isNotNull();
    assertThat(meal.getId()).isEqualTo(1L);
    assertThat(meal.getAllergens()).isNotNull();
    assertThat(meal.getAllergens().isPeanuts()).isFalse();
    assertThat(meal.getAllergens().isCelery()).isTrue();
  }

  @Test
  public void entityManager_shouldLoadMealWithAllergensEntity() {
    // given

    // when
    MealWithMultipleEntities meal = em.find(MealWithMultipleEntities.class, 1L);

    // then
    assertThat(meal).isNotNull();
    assertThat(meal.getId()).isEqualTo(1L);
    assertThat(meal.getAllergens()).isNotNull();
    assertThat(meal.getAllergens().isPeanuts()).isFalse();
    assertThat(meal.getAllergens().isCelery()).isTrue();
  }


}

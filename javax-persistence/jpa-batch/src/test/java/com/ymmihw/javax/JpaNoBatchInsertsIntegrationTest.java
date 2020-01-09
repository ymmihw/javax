package com.ymmihw.javax;

import static com.ymmihw.javax.TestObjectHelper.createSchool;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.ymmihw.javax.model.School;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@ActiveProfiles("batchinserts")
@TestPropertySource(properties = "spring.jpa.properties.hibernate.jdbc.batch_size=-1")
public class JpaNoBatchInsertsIntegrationTest {

  @PersistenceContext
  private EntityManager entityManager;

  @Test
  public void whenNotConfigured_ThenSendsInsertsSeparately() {
    for (int i = 0; i < 10; i++) {
      School school = createSchool(i);
      entityManager.persist(school);
    }
  }

  @After
  public void tearDown() {
    entityManager.flush();
  }
}

package com.ymmihw.javax.generator;

import static org.assertj.core.api.Assertions.assertThat;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.ymmihw.javax.App;
import com.ymmihw.javax.entity.Course;
import com.ymmihw.javax.entity.OrderEntry;
import com.ymmihw.javax.entity.OrderEntryIdClass;
import com.ymmihw.javax.entity.OrderEntryPK;
import com.ymmihw.javax.entity.Product;
import com.ymmihw.javax.entity.Student;
import com.ymmihw.javax.entity.User;
import com.ymmihw.javax.entity.UserProfile;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@Transactional
public class IdentifiersIntegrationTest {

  @Autowired
  private EntityManager entityManager;

  @Test
  public void whenSaveSimpleIdEntities_thenOk() {
    Student student = new Student();
    entityManager.persist(student);
    User user = new User();
    entityManager.persist(user);

    assertThat(student.getStudentId()).isEqualTo(1L);
    assertThat(user.getUserId()).isEqualTo(4L);

    Course course = new Course();
    entityManager.persist(course);

  }

  @Test
  public void whenSaveCustomGeneratedId_thenOk() {
    Product product = new Product();
    entityManager.persist(product);
    Product product2 = new Product();
    entityManager.persist(product2);

    assertThat(product2.getProdId()).isEqualTo("prod-2");
  }

  @Test
  public void whenSaveCompositeIdEntity_thenOk() {
    OrderEntryPK entryPK = new OrderEntryPK();
    entryPK.setOrderId(1L);
    entryPK.setProductId(30L);

    OrderEntry entry = new OrderEntry();
    entry.setEntryId(entryPK);
    entityManager.persist(entry);

    assertThat(entry.getEntryId().getOrderId()).isEqualTo(1L);
  }

  @Test
  public void whenSaveIdClassEntity_thenOk() {
    OrderEntryIdClass entry = new OrderEntryIdClass();
    entry.setOrderId(1L);
    entry.setProductId(30L);
    entityManager.persist(entry);

    assertThat(entry.getOrderId()).isEqualTo(1L);
  }

  @Test
  public void whenSaveDerivedIdEntity_thenOk() {
    User user = new User();
    entityManager.persist(user);

    UserProfile profile = new UserProfile();
    profile.setUser(user);
    entityManager.persist(profile);

    assertThat(profile.getProfileId()).isEqualTo(user.getUserId());
  }

}

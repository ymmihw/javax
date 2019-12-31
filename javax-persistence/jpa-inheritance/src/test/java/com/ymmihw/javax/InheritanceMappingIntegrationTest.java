package com.ymmihw.javax;

import static org.assertj.core.api.Assertions.assertThat;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import com.ymmihw.javax.entity.Bag;
import com.ymmihw.javax.entity.Book;
import com.ymmihw.javax.entity.Car;
import com.ymmihw.javax.entity.MyEmployee;
import com.ymmihw.javax.entity.Pen;
import com.ymmihw.javax.entity.Pet;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@Transactional
public class InheritanceMappingIntegrationTest {

  @Autowired
  private EntityManager em;

  @Test
  @Rollback
  public void givenSubclasses_whenQuerySingleTableSuperclass_thenOk() {
    Book book = new Book(1, "1984", "George Orwell");
    em.persist(book);
    Pen pen = new Pen(2, "my pen", "blue");
    em.persist(pen);


    assertThat(em.createQuery("from MyProduct").getResultList().size()).isEqualTo(2);
  }

  @Test
  @Rollback
  public void givenSubclasses_whenQueryMappedSuperclass_thenOk() {
    MyEmployee emp = new MyEmployee(1, "john", "baeldung");
    em.persist(emp);

    assertThat(em.createQuery("from com.ymmihw.javax.entity.Person").getResultList().size()).isEqualTo(1);
  }

  @Test
  @Rollback
  public void givenSubclasses_whenQueryJoinedTableSuperclass_thenOk() {
    Pet pet = new Pet(1, "dog", "lassie");
    em.persist(pet);

    assertThat(em.createQuery("from Animal").getResultList().size()).isEqualTo(1);
  }

  @Test
  @Rollback
  public void givenSubclasses_whenQueryTablePerClassSuperclass_thenOk() {
    Car car = new Car(1, "audi", "xyz");
    em.persist(car);

    assertThat(em.createQuery("from Vehicle").getResultList().size()).isEqualTo(1);
  }

  @Test
  @Rollback
  public void givenSubclasses_whenQueryNonMappedInterface_thenOk() {
    Bag bag = new Bag(1, "large");
    em.persist(bag);

    assertThat(em.createQuery("from com.ymmihw.javax.entity.Item").getResultList().size()).isEqualTo(0);
  }
}

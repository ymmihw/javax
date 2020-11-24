package com.ymmihw.javax.persistence.jpa;

import static org.junit.Assert.assertEquals;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.ymmihw.javax.persistence.jpa.entity.Person;
import com.ymmihw.javax.persistence.jpa.entity.PersonName;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@Transactional
public class PersonNameConverterTest {

  @Autowired
  private EntityManager entityManager;

  @Test
  public void givenPersonName_WhenSaving_ThenNameAndSurnameConcat() {
    final String name = "name";
    final String surname = "surname";

    PersonName personName = new PersonName();
    personName.setName(name);
    personName.setSurname(surname);

    Person person = new Person();
    person.setPersonName(personName);

    entityManager.persist(person);

    entityManager.flush();
    entityManager.clear();

    String dbPersonName = (String) entityManager
        .createNativeQuery("select p.person_name from person p where p.id = :id")
        .setParameter("id", person.getId()).getSingleResult();

    assertEquals(surname + ", " + name, dbPersonName);

    Person dbPerson = (Person) entityManager
        .createNativeQuery("select * from person p where p.id = :id", Person.class)
        .setParameter("id", person.getId()).getSingleResult();

    assertEquals(dbPerson.getPersonName().getName(), name);
    assertEquals(dbPerson.getPersonName().getSurname(), surname);
  }

  @Test
  public void givenPersonNameNull_WhenSaving_ThenNullStored() {
    final String name = null;
    final String surname = null;

    PersonName personName = new PersonName();
    personName.setName(name);
    personName.setSurname(surname);

    Person person = new Person();
    person.setPersonName(personName);

    entityManager.persist(person);

    entityManager.flush();
    entityManager.clear();

    String dbPersonName = (String) entityManager
        .createNativeQuery("select p.person_name from person p where p.id = :id")
        .setParameter("id", person.getId()).getSingleResult();

    assertEquals("", dbPersonName);

    Person dbPerson = (Person) entityManager
        .createNativeQuery("select * from person p where p.id = :id", Person.class)
        .setParameter("id", person.getId()).getSingleResult();

    assertEquals(dbPerson.getPersonName(), null);
  }

  @Test
  public void givenPersonNameWithoutName_WhenSaving_ThenNotNameStored() {
    final String name = null;
    final String surname = "surname";

    PersonName personName = new PersonName();
    personName.setName(name);
    personName.setSurname(surname);

    Person person = new Person();
    person.setPersonName(personName);

    entityManager.persist(person);

    entityManager.flush();
    entityManager.clear();

    String dbPersonName = (String) entityManager
        .createNativeQuery("select p.person_name from person p where p.id = :id")
        .setParameter("id", person.getId()).getSingleResult();

    assertEquals("surname, ", dbPersonName);

    Person dbPerson = (Person) entityManager
        .createNativeQuery("select * from person p where p.id = :id", Person.class)
        .setParameter("id", person.getId()).getSingleResult();

    assertEquals(dbPerson.getPersonName().getName(), name);
    assertEquals(dbPerson.getPersonName().getSurname(), surname);
  }

  @Test
  public void givenPersonNameWithoutSurName_WhenSaving_ThenNotSurNameStored() {
    final String name = "name";
    final String surname = null;

    PersonName personName = new PersonName();
    personName.setName(name);
    personName.setSurname(surname);

    Person person = new Person();
    person.setPersonName(personName);

    entityManager.persist(person);

    entityManager.flush();
    entityManager.clear();

    String dbPersonName = (String) entityManager
        .createNativeQuery("select p.person_name from person p where p.id = :id")
        .setParameter("id", person.getId()).getSingleResult();

    assertEquals("name", dbPersonName);

    Person dbPerson = (Person) entityManager
        .createNativeQuery("select * from person p where p.id = :id", Person.class)
        .setParameter("id", person.getId()).getSingleResult();

    assertEquals(dbPerson.getPersonName().getName(), name);
    assertEquals(dbPerson.getPersonName().getSurname(), surname);
  }

  @Test
  public void givenPersonNameEmptyFields_WhenSaving_ThenFielsNotStored() {
    final String name = "";
    final String surname = "";

    PersonName personName = new PersonName();
    personName.setName(name);
    personName.setSurname(surname);

    Person person = new Person();
    person.setPersonName(personName);

    entityManager.persist(person);

    entityManager.flush();
    entityManager.clear();

    String dbPersonName = (String) entityManager
        .createNativeQuery("select p.person_name from person p where p.id = :id")
        .setParameter("id", person.getId()).getSingleResult();

    assertEquals("", dbPersonName);

    Person dbPerson = (Person) entityManager
        .createNativeQuery("select * from person p where p.id = :id", Person.class)
        .setParameter("id", person.getId()).getSingleResult();

    assertEquals(dbPerson.getPersonName(), null);
  }

}

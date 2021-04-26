package com.ymmihw.javax;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ymmihw.javax.entity.User;
import com.ymmihw.javax.service.ExtendedPersistenceContextUserService;
import com.ymmihw.javax.service.TransctionPersistenceContextUserService;

@SpringBootTest(classes = PersistenceContextDemoApplication.class)
public class PersistenceContextIntegrationTest {

  @Autowired
  private TransctionPersistenceContextUserService transctionPersistenceContext;
  @Autowired
  private ExtendedPersistenceContextUserService extendedPersistenceContext;

  @Test
  public void testThatWhenUserSavedWithTransctionPersistenceContextThenUserShouldGetSavedInDB() {
    User user = new User(121L, "Devender", "admin");
    transctionPersistenceContext.insertWithTransaction(user);

    User userFromTransctionPersistenceContext = transctionPersistenceContext.find(user.getId());
    assertNotNull(userFromTransctionPersistenceContext);

    User userFromExtendedPersistenceContext = extendedPersistenceContext.find(user.getId());
    assertNotNull(userFromExtendedPersistenceContext);
  }

  @Test
  public void testThatUserSaveWithoutTransactionThrowException() {
    User user = new User(122L, "Devender", "admin");
    assertThrows(TransactionRequiredException.class,
        () -> transctionPersistenceContext.insertWithoutTransaction(user));
  }

  @Test
  public void testThatWhenUserSavedWithExtendedPersistenceContextWithoutTransactionThenUserShouldGetCached() {
    User user = new User(123L, "Devender", "admin");
    extendedPersistenceContext.insertWithoutTransaction(user);

    User userFromExtendedPersistenceContext = extendedPersistenceContext.find(user.getId());
    assertNotNull(userFromExtendedPersistenceContext);

    User userFromTransctionPersistenceContext = transctionPersistenceContext.find(user.getId());
    assertNull(userFromTransctionPersistenceContext);
  }

  @Test
  public void testThatPersistUserWithSameIdentifierThrowException() {
    User user1 = new User(126L, "Devender", "admin");
    User user2 = new User(126L, "Devender", "admin");
    assertThrows(EntityExistsException.class, () -> {
      extendedPersistenceContext.insertWithoutTransaction(user1);
      extendedPersistenceContext.insertWithoutTransaction(user2);
    });
  }

  @Test
  public void testThatWhenUserSavedWithExtendedPersistenceContextWithTransactionThenUserShouldSaveEntityIntoDB() {
    User user = new User(127L, "Devender", "admin");
    extendedPersistenceContext.insertWithTransaction(user);

    User userFromDB = transctionPersistenceContext.find(user.getId());
    assertNotNull(userFromDB);
  }

  @Test
  public void testThatWhenUserSavedWithExtendedPersistenceContextWithTransactionThenUserShouldFlushCachedEntityIntoDB() {
    User user1 = new User(124L, "Devender", "admin");
    extendedPersistenceContext.insertWithoutTransaction(user1);

    User user2 = new User(125L, "Devender", "admin");
    extendedPersistenceContext.insertWithTransaction(user2);

    User user1FromTransctionPersistenceContext = transctionPersistenceContext.find(user1.getId());
    assertNotNull(user1FromTransctionPersistenceContext);

    User user2FromTransctionPersistenceContext = transctionPersistenceContext.find(user2.getId());
    assertNotNull(user2FromTransctionPersistenceContext);
  }

}

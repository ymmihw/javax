package com.ymmihw.javax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.ymmihw.javax.model.Comment;
import com.ymmihw.javax.model.Post;
import com.ymmihw.javax.model.User;
import com.ymmihw.javax.repo.PostRepository;

public class PostRepositoryIntegrationTest {

  private static PostRepository postRepository = null;

  @BeforeAll
  public static void once() {
    postRepository = new PostRepository();
  }

  @Test
  public void find() {

    assertThrows(LazyInitializationException.class, () -> {
      Post post = postRepository.find(1L);
      assertNotNull(post.getUser());
      String email = post.getUser().getEmail();
      assertNull(email);
    });

  }

  @Test
  public void findWithEntityGraph() {
    Post post = postRepository.findWithEntityGraph(1L);
    assertNotNull(post.getUser());
    String email = post.getUser().getEmail();
    assertNotNull(email);
  }

  @Test
  public void findWithEntityGraph_Comment_Without_User() {


    assertThrows(LazyInitializationException.class, () -> {
      Post post = postRepository.findWithEntityGraph(1L);
      assertNotNull(post.getUser());
      String email = post.getUser().getEmail();
      assertNotNull(email);
      assertNotNull(post.getComments());
      assertEquals(post.getComments().size(), 2);
      Comment comment = post.getComments().get(0);
      assertNotNull(comment);
      User user = comment.getUser();
      user.getEmail();
    });

  }

  @Test
  public void findWithEntityGraph2_Comment_With_User() {
    Post post = postRepository.findWithEntityGraph2(1L);
    assertNotNull(post.getComments());
    assertEquals(post.getComments().size(), 2);
    Comment comment = post.getComments().get(0);
    assertNotNull(comment);
    User user = comment.getUser();
    assertNotNull(user);
    assertEquals(user.getEmail(), "user2@test.com");
  }

  @AfterAll
  public static void destroy() {
    postRepository.clean();
  }
}

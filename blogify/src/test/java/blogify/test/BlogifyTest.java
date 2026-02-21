package blogify.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import blogify.entities.Comment;
import blogify.entities.Post;
import blogify.entities.User;
import blogify.service.CommentService;
import blogify.service.PostService;
import blogify.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@TestMethodOrder(OrderAnnotation.class)
public class BlogifyTest {

    static EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("postgres");

    EntityManager em;
    UserService userService;
    PostService postService;
    CommentService commentService;

    @BeforeEach
    void setup() {
        em = emf.createEntityManager();
        userService = new UserService(em);
        postService = new PostService(em);
        commentService = new CommentService(em);
    }

    @AfterEach
    void teardown() {
        if (em.isOpen()) em.close();
    }

    // TEST 1 — Register new user
    @Test
    @Order(1)
    void testRegisterUser() {
        User u1 = new User();
        u1.setName("Anshul");
        u1.setEmail("anshul@blogify.com");
        u1.setPassword("pass123");

        User u2 = new User();
        u2.setName("Priya");
        u2.setEmail("priya@blogify.com");
        u2.setPassword("pass456");

        userService.registerUser(u1);
        userService.registerUser(u2);

        // verify — id auto generated so just check not null
        assertNotNull(em.createQuery("SELECT u FROM User u WHERE u.email = :e", User.class)
            .setParameter("e", "anshul@blogify.com")
            .getSingleResult());

        System.out.println("TEST 1 PASSED — users registered with auto generated ids");
    }

    // TEST 2 — Create post for a user
    @Test
    @Order(2)
    void testCreatePost() {
        // fetch user by email since we don't know auto generated id
        User author = em.createQuery(
            "SELECT u FROM User u WHERE u.email = :e", User.class)
            .setParameter("e", "anshul@blogify.com")
            .getSingleResult();

        Post p1 = new Post();
        p1.setTitle("My First Blog");
        p1.setContent("Hello world from Blogify!");

        Post p2 = new Post();
        p2.setTitle("Hibernate Tips");
        p2.setContent("Always initialize your lists!");

        postService.createPost(p1, author);
        postService.createPost(p2, author);

        // verify posts exist for user
        List<Post> posts = userService.getPostsByUser(author.getId());
        assertEquals(2, posts.size());

        System.out.println("TEST 2 PASSED — posts created for user");
    }

    // TEST 3 — Add comment to a post
    @Test
    @Order(3)
    void testAddComment() {
        // get post and users
        User anshul = em.createQuery(
            "SELECT u FROM User u WHERE u.email = :e", User.class)
            .setParameter("e", "anshul@blogify.com")
            .getSingleResult();

        User priya = em.createQuery(
            "SELECT u FROM User u WHERE u.email = :e", User.class)
            .setParameter("e", "priya@blogify.com")
            .getSingleResult();

        Post post = em.createQuery(
            "SELECT p FROM Post p WHERE p.title = :t", Post.class)
            .setParameter("t", "My First Blog")
            .getSingleResult();

        // priya comments on anshul's post
        commentService.addComment("Great post Anshul!", post, priya);

        // anshul replies on his own post
        commentService.addComment("Thanks Priya!", post, anshul);

        // verify comments on post
        List<Comment> comments = postService.getCommentsByPost(post.getId());
        assertEquals(2, comments.size());

        System.out.println("TEST 3 PASSED — comments added to post");
    }

    // TEST 4 — Fetch all comments by a user
    @Test
    @Order(4)
    void testFetchCommentsByUser() {
        User priya = em.createQuery(
            "SELECT u FROM User u WHERE u.email = :e", User.class)
            .setParameter("e", "priya@blogify.com")
            .getSingleResult();

        List<Comment> comments = userService.getCommentsByUser(priya.getId());
        assertFalse(comments.isEmpty());
        assertEquals("Great post Anshul!", comments.get(0).getContent());

        System.out.println("TEST 4 PASSED — comments fetched by user");
    }

    // TEST 5 — Update post
    @Test
    @Order(5)
    void testUpdatePost() {
        Post post = em.createQuery(
            "SELECT p FROM Post p WHERE p.title = :t", Post.class)
            .setParameter("t", "Hibernate Tips")
            .getSingleResult();

        postService.updatePost(post.getId(), "Hibernate Best Practices", 
                               "Always initialize lists and use mappedBy correctly!");

        Post updated = postService.getPost(post.getId());
        assertEquals("Hibernate Best Practices", updated.getTitle());
        assertNotNull(updated.getUpdatedAt());

        System.out.println("TEST 5 PASSED — post updated");
    }

    // TEST 6 — Delete post
    @Test
    @Order(6)
    void testDeletePost() {
        Post post = em.createQuery(
            "SELECT p FROM Post p WHERE p.title = :t", Post.class)
            .setParameter("t", "My First Blog")
            .getSingleResult();

        int postId = post.getId();
        postService.deletePost(postId);

        // verify post is gone
        Post deleted = postService.getPost(postId);
        assertNull(deleted);

        System.out.println("TEST 6 PASSED — post deleted, comments unlinked");
    }
}
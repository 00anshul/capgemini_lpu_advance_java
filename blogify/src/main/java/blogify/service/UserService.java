package blogify.service;

import java.util.List;
import javax.persistence.EntityManager;
import blogify.dao.UserDAO;
import blogify.entities.Comment;
import blogify.entities.Post;
import blogify.entities.User;

public class UserService {

    private EntityManager em;
    private UserDAO userDAO;

    public UserService(EntityManager em) {
        this.em = em;
        this.userDAO = new UserDAO(em);
    }

    // register new user
    public void registerUser(User user) {
        em.getTransaction().begin();
        userDAO.saveUser(user);
        em.getTransaction().commit();
        System.out.println("User registered: " + user);
    }

    public User getUser(int id) {
        return userDAO.findUser(id);
    }

    // fetch all posts by a user
    public List<Post> getPostsByUser(int userId) {
        List<Post> posts = userDAO.findPostsByUser(userId);
        System.out.println("Posts by user " + userId + ":");
        for (Post p : posts) {
            System.out.println("  " + p);
        }
        return posts;
    }

    // fetch all comments written by a user
    public List<Comment> getCommentsByUser(int userId) {
        List<Comment> comments = userDAO.findCommentsByUser(userId);
        System.out.println("Comments by user " + userId + ":");
        for (Comment c : comments) {
            System.out.println("  " + c);
        }
        return comments;
    }

    public void deleteUser(int id) {
        em.getTransaction().begin();
        userDAO.deleteUser(id);
        em.getTransaction().commit();
        System.out.println("User deleted: " + id);
    }
}
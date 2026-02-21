package blogify.dao;

import java.util.List;
import javax.persistence.EntityManager;
import blogify.entities.User;

public class UserDAO {

    private EntityManager em;

    public UserDAO(EntityManager em) {
        this.em = em;
    }

    public void saveUser(User user) {
        em.persist(user);
    }

    public User findUser(int id) {
        return em.find(User.class, id);
    }

    public void updateUser(User user) {
        em.merge(user);
    }

    public void deleteUser(int id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }

    // fetch all posts by a user using JPQL
    public List<blogify.entities.Post> findPostsByUser(int userId) {
        return em.createQuery(
            "SELECT p FROM Post p WHERE p.author.id = :uid", 
             blogify.entities.Post.class)
            .setParameter("uid", userId)
            .getResultList();
    }

    // fetch all comments written by a user using JPQL
    public List<blogify.entities.Comment> findCommentsByUser(int userId) {
        return em.createQuery(
            "SELECT c FROM Comment c WHERE c.author.id = :uid", 
             blogify.entities.Comment.class)
            .setParameter("uid", userId)
            .getResultList();
    }
}
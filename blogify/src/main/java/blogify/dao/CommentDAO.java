package blogify.dao;

import java.util.List;
import javax.persistence.EntityManager;
import blogify.entities.Comment;

public class CommentDAO {

    private EntityManager em;

    public CommentDAO(EntityManager em) {
        this.em = em;
    }

    public void saveComment(Comment comment) {
        em.persist(comment);
    }

    public Comment findComment(int id) {
        return em.find(Comment.class, id);
    }

    // fetch all comments of a post using JPQL
    public List<Comment> findCommentsByPost(int postId) {
        return em.createQuery(
            "SELECT c FROM Comment c WHERE c.post.id = :pid", 
             Comment.class)
            .setParameter("pid", postId)
            .getResultList();
    }
}
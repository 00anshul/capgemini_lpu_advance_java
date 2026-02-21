package blogify.service;

import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import blogify.dao.CommentDAO;
import blogify.entities.Comment;
import blogify.entities.Post;
import blogify.entities.User;

public class CommentService {

    private EntityManager em;
    private CommentDAO commentDAO;

    public CommentService(EntityManager em) {
        this.em = em;
        this.commentDAO = new CommentDAO(em);
    }

    // add comment to a post by a user
    public void addComment(String content, Post post, User author) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setPost(post);
        comment.setAuthor(author);
        comment.setCreatedAt(LocalDateTime.now());

        em.getTransaction().begin();
        commentDAO.saveComment(comment);
        em.getTransaction().commit();
        System.out.println("Comment added: " + comment);
    }

    public Comment getComment(int id) {
        return commentDAO.findComment(id);
    }
}
package blogify.service;

import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import blogify.dao.CommentDAO;
import blogify.dao.PostDAO;
import blogify.entities.Comment;
import blogify.entities.Post;
import blogify.entities.User;
import java.util.List;

public class PostService {

    private EntityManager em;
    private PostDAO postDAO;
    private CommentDAO commentDAO;

    public PostService(EntityManager em) {
        this.em = em;
        this.postDAO = new PostDAO(em);
        this.commentDAO = new CommentDAO(em);
    }

    // create post for a user
    public void createPost(Post post, User author) {
        post.setAuthor(author);
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        em.getTransaction().begin();
        postDAO.savePost(post);
        em.getTransaction().commit();
        System.out.println("Post created: " + post);
    }

    public Post getPost(int id) {
        return postDAO.findPost(id);
    }

    // update post content
    public void updatePost(int postId, String newTitle, String newContent) {
        em.getTransaction().begin();
        Post post = postDAO.findPost(postId);
        post.setTitle(newTitle);
        post.setContent(newContent);
        post.setUpdatedAt(LocalDateTime.now());   // update timestamp
        em.getTransaction().commit();
        System.out.println("Post updated: " + post);
    }

    // delete post — comments must be cleaned first
    public void deletePost(int postId) {
        em.getTransaction().begin();

        // null out post reference in comments first
        List<Comment> comments = commentDAO.findCommentsByPost(postId);
        for (Comment c : comments) {
            c.setPost(null);
        }

        postDAO.deletePost(postId);
        em.getTransaction().commit();
        System.out.println("Post deleted: " + postId);
    }

    // fetch all comments of a post
    public List<Comment> getCommentsByPost(int postId) {
        List<Comment> comments = commentDAO.findCommentsByPost(postId);
        System.out.println("Comments on post " + postId + ":");
        for (Comment c : comments) {
            System.out.println("  " + c);
        }
        return comments;
    }
}
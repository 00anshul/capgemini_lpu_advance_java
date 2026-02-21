package blogify.dao;

import java.util.List;
import javax.persistence.EntityManager;
import blogify.entities.Post;

public class PostDAO {

    private EntityManager em;

    public PostDAO(EntityManager em) {
        this.em = em;
    }

    public void savePost(Post post) {
        em.persist(post);
    }

    public Post findPost(int id) {
        return em.find(Post.class, id);
    }

    public void updatePost(Post post) {
        em.merge(post);
    }

    public void deletePost(int id) {
        Post post = em.find(Post.class, id);
        if (post != null) {
            em.remove(post);
        }
    }
}
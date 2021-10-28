package pl.recordit.data;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Component
public class BlogItemRepository {
    private final EntityManagerFactory factory
            = Persistence.createEntityManagerFactory("blog");

    public void save(BlogItem item){
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
        em.close();
    }

    public BlogItem findById(long id){
        EntityManager em = factory.createEntityManager();
        final BlogItem blogItem = em.find(BlogItem.class, id);
        em.close();
        return blogItem;
    }

    public List<BlogItem> findAll(){
        EntityManager em = factory.createEntityManager();
        final List<BlogItem> items = em.createQuery("select b from BlogItem b", BlogItem.class).getResultList();
        em.close();
        return items;
    }




}

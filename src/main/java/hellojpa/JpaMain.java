package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Movie movie = new Movie();
            movie.setDirector("폴 그린그래스");
            movie.setActor("맷 데이먼");
            movie.setName("제이슨 본");
            movie.setPrice(20000);

            em.persist(movie);

            em.flush();
            em.clear();

            Item findItem = em.find(Item.class, movie.getId());
            System.out.println("findItem = " + findItem);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}

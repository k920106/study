package hellojpa;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");

            em.persist(member);

            // flush -> commit, query

//            em.createNativeQuery("SELECT MEMBER_ID, city, street, zipcode, USERNAME " +
//                    "                FROM MEMBER").getResultList();
            List<Member> resultList =
                    em.createNativeQuery("SELECT MEMBER_ID, city, street, zipcode, USERNAME " +
                            "                FROM MEMBER", Member.class).getResultList();

            for (Member m : resultList) {
                System.out.println("m = " + m.getClass());
            }

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

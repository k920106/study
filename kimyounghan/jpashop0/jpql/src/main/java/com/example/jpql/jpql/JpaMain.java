package com.example.jpql.jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.Collection;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Team team = new Team();

            em.persist(team);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.changeTeam(team);

            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.changeTeam(team);

            em.persist(member2);

            em.flush();
            em.clear();

//            String query = "SELECT t.members FROM Team t";
            String query = "SELECT m.username FROM Team t join t.members m";
            Collection result = em.createQuery(query, Collection.class).getResultList();
            System.out.println("result = " + result);

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

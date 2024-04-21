package com.example.jpql.jpql;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Team team = new Team();
            team.setName("team1");

            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.setType(MemberType.ADMIN);

            member.changeTeam(team);

            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setAge(10);
            member2.setType(MemberType.ADMIN);

            em.persist(member2);

            em.flush();
            em.clear();

//            String query = "SELECT FUNCTION('GROUP_CONCAT', m.username) FROM Member m";
            String query = "SELECT GROUP_CONCAT(m.username) FROM Member m";
            List<String> result = em.createQuery(query, String.class).getResultList();
            for (String s : result) {
                System.out.println(s);
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

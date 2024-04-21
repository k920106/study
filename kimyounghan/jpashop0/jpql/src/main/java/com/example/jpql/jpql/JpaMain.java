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
//            member.setUsername("member1");
//            member.setUsername(null);
            member.setUsername("관리자");
            member.setAge(10);
            member.setType(MemberType.ADMIN);

            member.changeTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

//            String query = "SELECT CASE WHEN m.age <= 10 THEN '학생요금' " +
//                           "            WHEN m.age >= 60 THEN '경로요금' " +
//                           "            ELSE '일반요금' " +
//                           "            END" +
//                           " FROM Member m";
//            String query = "SELECT COALESCE(m.username, '이름 없는 회원') FROM Member m";
            String query = "SELECT NULLIF(m.username, '관리자') FROM Member m";
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

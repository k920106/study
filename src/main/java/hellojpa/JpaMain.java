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
            Member member = new Member();
            member.setUsername("member1");
//            member.changeTeam(team);

            em.persist(member);

            Team team = new Team();
            team.setName("TeamA");
//            team.getMembers().add(member);

            em.persist(team);

            team.addMember(member);

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

//            System.out.println("=====");
            for (Member m : members) {
                System.out.println("m.username = " + m.getUsername());
            }
//            System.out.println("=====");

            System.out.println("=====");
            System.out.println("findTeam = " + findTeam);
            System.out.println("=====");

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

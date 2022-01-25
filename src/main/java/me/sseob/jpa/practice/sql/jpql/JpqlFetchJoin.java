package me.sseob.jpa.practice.sql.jpql;

import me.sseob.jpa.practice.basic.Member;
import me.sseob.jpa.practice.basic.MemberType;
import me.sseob.jpa.practice.basic.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/*
	jpql은 객체지향 sql이다. 테이블이 아닌 객체 중심으로 대상을 검색할 수 있다.
 */
public class JpqlFetchJoin {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sseob");
		EntityManager em = entityManagerFactory.createEntityManager(); // entity manager 생성하여 얻음

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		try {

			Team team = new Team("Chelsea");
			em.persist(team);
			
			Team team2 = new Team("Manchester City");
			em.persist(team2);
			
			for (int i = 0; i < 10; i++) {

				if (i > 4) {
					Member member = new Member("현섭" + i);
					member.setAge(i * 10);
					member.setMemberType(MemberType.ADMIN);
					member.setTeam(team);
					em.persist(member);
					
				} else {
					Member member = new Member("현섭" + i);
					member.setAge(i * 10);
					member.setMemberType(MemberType.ADMIN);
					member.setTeam(team2);
					em.persist(member);
				}
				
			}

			em.flush();
			em.clear();

			// fetch join으로 인해 실행된 sql query는 FetchType.EAGER 와 같다. 즉시로딩처럼 미리 Team table을 join 하여 결과를 select한다.
			// N + 1 문제를 유연하게 극복 가능한 핵심 기능이다.
			
			// N + 1 문제 발생. hibernate proxy는 Team 객체의 getName이 호출되면 지연로딩 설정에 의해 뒤늦게 Team select query를 날린다.
			// N + 1 문제는 즉시 로딩으로 설정 되어있더라도 발생할 수 있다. 
			// String query = "select m from Member m";
			String query = "select m from Member m join fetch m.team";
			List<Member> resultList = em.createQuery(query, Member.class).getResultList();
			for (Member m : resultList) {
				System.out.println("result = " + m.getTeam().getName() + ", " + m.getName());
			}
			
			String teamQuery = "select t from Team t join fetch t.members ";
			List<Team> resultList2 = em.createQuery(teamQuery, Team.class).getResultList();
			for (Team t : resultList2) {
				System.out.println("result = " + t.getMembers() + ", " + t.getName());
			}
			
			transaction.commit();
		} catch (Exception e){
			transaction.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		entityManagerFactory.close();
	}
}

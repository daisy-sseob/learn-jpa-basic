package me.sseob.jpa.practice.basic.main;

import me.sseob.jpa.practice.basic.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class FetchStrategy {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sseob");
		EntityManager entityManager = entityManagerFactory.createEntityManager(); // entity manager 생성하여 얻음

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {

			Team team = new Team("team !");
			entityManager.persist(team);
			
			Member member = new Member("sseob");
			member.setCreatedBy("심현섭");
			member.setCreatedDate(LocalDateTime.now());
			member.setTeam(team);
			entityManager.persist(member);

			// Fetch전략이 즉시로딩일 경우 join하여 즉시 로딩한다.
			List<Member> resultList = entityManager.createQuery("select m from Member m ", Member.class).getResultList();
			resultList.forEach(System.out::println);

			transaction.commit();
		} catch (Exception e){
			transaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		entityManagerFactory.close();
	}
}

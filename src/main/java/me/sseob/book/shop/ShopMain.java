package me.sseob.book.shop;

import me.sseob.RoleType;
import me.sseob.book.shop.domain.Member;
import me.sseob.book.shop.domain.Team;

import javax.lang.model.SourceVersion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ShopMain {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sseob");
		EntityManager entityManager = entityManagerFactory.createEntityManager(); // entity manager 생성하여 얻음

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {

//			Team tottenham = new Team("tottenham");
//			entityManager.persist(tottenham);
//			Member member = new Member("sseob22");
//			member.setTeam(team);
//			entityManager.persist(member);
			
//			Member findMember = entityManager.find(Member.class, 2L);
//			System.out.println("===================" + findMember.getName());
//
//			for (Member member : findMember.getTeam().getMembers()) {
//				System.out.println(member.getName());
//			}
			
			transaction.commit();
		} catch (Exception e){
			transaction.rollback();
		} finally {
			entityManager.close();
		}
		entityManagerFactory.close();
	}
}

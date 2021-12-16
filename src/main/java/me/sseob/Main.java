package me.sseob;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sseob");
		EntityManager entityManager = entityManagerFactory.createEntityManager(); // entity manager 생성하여 얻음

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {

			/*
			간단하게 insert
			Member member = new Member();
			member.setId(1L);
			member.setName("sseob");
			entityManager.persist(member);
			 */
			
			// 수정해보자
			Member findMember = entityManager.find(Member.class, 1L);
			findMember.setName("sseob modify"); 
			entityManager.persist(findMember);
			
			transaction.commit();
		} catch (Exception e){
			transaction.rollback();
		} finally {
			entityManager.close();
		}
		entityManagerFactory.close();
	}
}

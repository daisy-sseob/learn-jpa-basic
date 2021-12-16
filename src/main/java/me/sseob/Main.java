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
		
		Member member = new Member();
		member.setId(1L);
		member.setName("sseob");
		entityManager.persist(member);
		
		transaction.commit();
		
		entityManager.close();
		entityManagerFactory.close();
	}
}

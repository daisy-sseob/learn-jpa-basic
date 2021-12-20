package me.sseob;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class AccountMain {

	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sseob");
		EntityManager entityManager = entityManagerFactory.createEntityManager(); // entity manager 생성하여 얻음

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {

//			Account account = new Account();
//			account.setId(1L);
//			account.setName("account");
//			entityManager.persist(account); // 영속화
			
			Account account = entityManager.find(Account.class, 22L);
			Account account2 = entityManager.find(Account.class, 22L);
			System.out.println( "equals ? " + (account == account2));

			transaction.commit();
		} catch (Exception e){
			transaction.rollback();
		} finally {
			entityManager.close();
		}
		entityManagerFactory.close();
	}
}

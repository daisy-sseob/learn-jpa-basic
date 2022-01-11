package me.sseob.book.shop.casecade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CasecadeMain {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sseob");
		EntityManager entityManager = entityManagerFactory.createEntityManager(); // entity manager 생성하여 얻음

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {

			Child child = new Child("child");
			Child child2 = new Child("child2");
			
			Parent parent = new Parent("parent");
			parent.addChild(child);
			parent.addChild(child2);

			entityManager.persist(parent);
			
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

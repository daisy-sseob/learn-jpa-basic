package me.sseob.book.shop.item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ItemMain {
	
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sseob");
		EntityManager entityManager = entityManagerFactory.createEntityManager(); // entity manager 생성하여 얻음

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {

			Movie movie = new Movie();
			movie.setDirector("sseob");
			movie.setActor("심현섭");
			movie.setPrice(10000);
			movie.setName("쇼생크탈출");
			
			entityManager.persist(movie);
			entityManager.flush();
			entityManager.clear();
			
			Movie movie1 = entityManager.find(Movie.class, movie.getId());
			System.out.println("movie1 = " + movie1);

			transaction.commit();
		} catch (Exception e){
			transaction.rollback();
		} finally {
			entityManager.close();
		}
		entityManagerFactory.close();
	}
}

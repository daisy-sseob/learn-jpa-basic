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

			/*
				getReference() 가 실행되는 시점에는 select query가 실행되지 않는다.
				하지만 아래 print하는 코드가 있다면 select query를 뒤늦게 실행한다.
			 */
			Movie reference = entityManager.getReference(Movie.class, movie.getId());
			System.out.println("movie1.getName() = " + reference.getClass()); // hibernate proxy class가 print 된다. (가짜 entity 객체) 
			System.out.println("movie1.getName() = " + reference.getName());
			System.out.println("movie1.getDirector() = " + reference.getDirector());

//			Movie movie1 = entityManager.find(Movie.class, movie.getId());
//			System.out.println("movie1.getName() = " + movie1.getName());
//			System.out.println("movie1.getDirector() = " + movie1.getDirector());

			transaction.commit();
		} catch (Exception e){
			transaction.rollback();
		} finally {
			entityManager.close();
		}
		entityManagerFactory.close();
	}
}

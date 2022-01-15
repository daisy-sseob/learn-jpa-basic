package me.sseob.jpa.practice.sql.jpql;

import me.sseob.jpa.practice.basic.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/*
	jpql은 객체지향 sql이다. 테이블이 아닌 객체 중심으로 대상을 검색할 수 있다.
 */
public class JpqlMain {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sseob");
		EntityManager em = entityManagerFactory.createEntityManager(); // entity manager 생성하여 얻음

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		try {

			String jpql = "select m from Member as m where m.name like '%현섭%'";
			List<Member> findMemberList = em.createQuery(jpql, Member.class).getResultList();
			
			findMemberList.forEach(System.out::println);

			transaction.commit();
		} catch (Exception e){
			transaction.rollback();
		} finally {
			em.close();
		}
		entityManagerFactory.close();
	}
}

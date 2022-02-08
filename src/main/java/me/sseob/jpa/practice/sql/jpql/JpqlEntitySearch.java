package me.sseob.jpa.practice.sql.jpql;

import me.sseob.jpa.practice.basic.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpqlEntitySearch {
	
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sseob");
		EntityManager em = entityManagerFactory.createEntityManager(); // entity manager 생성하여 얻음

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		try {

			Member member = new Member("현섭");
			member.setAge(29);
			em.persist(member);
			
			Member sseob = new Member("sseob");
			sseob.setAge(29);
			em.persist(sseob);
			
			em.flush();
			em.clear();

			// entity로 직접 조회해도 식별자인 id를 이용해 조회를 하게 된다.
			List<Member> resultList = em.createQuery("select m from Member as m where m = :member", Member.class)
					.setParameter("member", sseob)
					.getResultList();
			
			resultList.forEach(System.out::println);
			
			transaction.commit();
		} catch (Exception e){
			transaction.rollback();
		} finally {
			em.close();
		}
		entityManagerFactory.close();
	}
}

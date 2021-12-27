package me.sseob;

import javax.persistence.*;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sseob");
		EntityManager entityManager = entityManagerFactory.createEntityManager(); // entity manager 생성하여 얻음

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {

//			간단하게 insert
//			Member member = new Member();
//			member.setId(2L);
//			member.setName("sseob22");
//			entityManager.persist(member);
			
			Member findMember = entityManager.find(Member.class, 1L);
			// 수정해보자 jpa가 관리를 해주기 때문에 저장메소드를 실행시키기 않아도 jpa가 update쿼리를 생성하여 실행 후 commit 한다.
			findMember.setUsername("sseob");

			List<Member> findMembers = entityManager.createQuery("select m from Member as m", Member.class)
					.setFirstResult(0)
					.setMaxResults(10)
					.getResultList();

			for (Member member : findMembers) {
				System.out.println("name = " + member.getUsername());
			}
			
			transaction.commit();
		} catch (Exception e){
			transaction.rollback();
		} finally {
			entityManager.close();
		}
		entityManagerFactory.close();
	}
}

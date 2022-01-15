package me.sseob.jpa.practice.sql.jpql;

import me.sseob.jpa.practice.basic.Member;

import javax.persistence.*;
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

			Member member = new Member("현섭이에요");
			em.persist(member);
			
			/*
				jpql이 아닌 JPA 스펙과 관련없는 select행위(connection객체를 통해 직접 select 하는 경우와 같은..)를 하는 경우
				persist를 한다고 헤서 바로 database에 insert가 되는게 아니고 commit 시점에 종합적으로 동작하기 때문에 적절한 타이밍에 
				강제로 flush해주는 행위가 필요하다. (실제 insert하고나서 select하도록)
				jpql은 알아서 강제 flush가 발생해서 아래 코드는 정상적으로 insert쿼리가 발생하고 그뒤에 select된다.
			 */

			String nameParam = "sseob";
			String jpql = "select m from Member as m where m.name = :name";
			TypedQuery<Member> query = em.createQuery(jpql, Member.class)
					.setParameter("name", nameParam)
					;
			List<Member> findMemberList = query.getResultList();
			
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

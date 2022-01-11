package me.sseob.book.shop.basic;

import javax.lang.model.SourceVersion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class BasicMain {
	
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sseob");
		EntityManager entityManager = entityManagerFactory.createEntityManager(); // entity manager 생성하여 얻음

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		try {

			Team team = new Team("team !");
			entityManager.persist(team);
			
			Member member = new Member("sseob");
			member.setHomeAddress(new Address("seoul", "홍대", "123123"));
			member.setWorkAddress(new Address("goyang", "도래울 1로", "8020"));
			member.setWorkPeriod(new Period(LocalDateTime.now(), LocalDateTime.now().minusDays(20)));
			member.setCreatedBy("심현섭");
			member.setCreatedDate(LocalDateTime.now());
			member.setTeam(team);
			entityManager.persist(member);
			
			entityManager.flush();
			entityManager.clear();

			Member member1 = entityManager.find(Member.class, member.getId());
			System.out.println("member1 = " + member1.getName()); // Lock, Team을 지연로딩으로 설정후 조회하지 않으면 Member만 조회하는 Select query실행.
			System.out.println("member1.getTeam() = " + member1.getTeam().getClass()); // team hibernate proxy객체가 출력된다. 즉 지연로딩으로 설정하여 proxy객체를 참조하게 만든다. proxy객체 출력후 select query가 실행되는걸 볼 수 있음.
			System.out.println("member1.getTeam() = " + member1.getTeam().getName()); // 지연 로딩에 의해 나중에 Team이 join된다. 실제 사용될 때 proxy instance 초기화.
			// 반대로 FetchType.EAGER 즉시로딩을 설정하게 되면 애초에 member, team을 join하여 select한다. 프록시 객체를 사용하지 않아도 된다.

			// 즉시로딩의 N + 1 문제는 지연로딩 + fetch join으로 해결할 수 있다.
			List<Member> resultList = entityManager.createQuery("select m from Member m join fetch m.team", Member.class).getResultList();
			resultList.forEach(System.out::println);

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

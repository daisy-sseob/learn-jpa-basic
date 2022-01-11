package me.sseob.book.shop.basic;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseEntity{

	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long id;

	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY) // 지연 로딩. proxy객체를 조회한다.
	@JoinColumn(name = "team_id")
	private Team team;

	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<>();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "locker_id")
	private Locker locker;

	@Embedded
	private Period workPeriod;
	
	@Embedded
	private Address homeAddress;
	
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "city", column =@Column(name = "work_city")),
			@AttributeOverride(name = "street", column =@Column(name = "work_street")),
			@AttributeOverride(name = "zipcode", column =@Column(name = "work_zipcode"))
	}) // embedded type이 겹칠 경우 컬럼명을 재정의 해준다.
	private Address workAddress;

	public Member(String name) {
		this.name = name;
	} 

	public Member() {
		
	}

	public Address getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(Address workAddress) {
		this.workAddress = workAddress;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Locker getLocker() {
		return locker;
	}

	public void setLocker(Locker locker) {
		this.locker = locker;
	}

	public Period getWorkPeriod() {
		return workPeriod;
	}

	public void setWorkPeriod(Period workPeriod) {
		this.workPeriod = workPeriod;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

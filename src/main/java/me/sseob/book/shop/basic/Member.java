package me.sseob.book.shop.basic;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member extends BaseEntity {

	@Id
	@GeneratedValue
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

	
	/*
		collection을 table에 저장할 수 없기 때문에 하위 테이블을 만들어 관리.
		값 type collection들을 Member와 life cycle이 같다. 별로도 persist할 필요없다.
	 */
	@ElementCollection
	@CollectionTable(name = "ADDRESS", joinColumns =
		@JoinColumn(name = "member_id")
	) // table명
	private List<Address> addressHistory = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "FAVORITE_FOOD", joinColumns =
		@JoinColumn(name = "member_id")
	)
	@Column(name = "food_name")
	private Set<String> favoriteFoods = new HashSet<>();

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "city", column = @Column(name = "work_city")),
			@AttributeOverride(name = "street", column = @Column(name = "work_street")),
			@AttributeOverride(name = "zipcode", column = @Column(name = "work_zipcode"))
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

	public List<Address> getAddressHistory() {
		return addressHistory;
	}

	public void setAddressHistory(List<Address> addressHistory) {
		this.addressHistory = addressHistory;
	}

	public Set<String> getFavoriteFoods() {
		return favoriteFoods;
	}

	public void setFavoriteFoods(Set<String> favoriteFoods) {
		this.favoriteFoods = favoriteFoods;
	}
}

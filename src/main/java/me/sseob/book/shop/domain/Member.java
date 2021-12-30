package me.sseob.book.shop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<>();
	
	private String name;
	private String city;
	private String street;
	private String zipcode;

	public Member(String name) {
		this.name = name;
	} 

	public Member() {
		
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}

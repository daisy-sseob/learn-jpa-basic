package me.sseob.book.shop.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Team {

	@Id
	@GeneratedValue
	@Column(name = "team_id")
	private Long id;
	private String name;
	
	@OneToMany(mappedBy = "team")
	private Set<Member> members = new HashSet<>();

	public Team(String name) {
		this.name = name;
	}

	public Team() {

	}

	public Set<Member> getMembers() {
		return members;
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
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

package me.sseob;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Member") // jpa에서 관리하는 객체.
//@Table(name = "MBR") // MBR 이라는 테이블에 매핑하게 된다.
public class Member {
	@Id
	private Long id;
	private String name;
	private int age;

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

package me.sseob.book.shop.domain;

import javax.persistence.*;

@Entity
public class Locker {

	@Id @GeneratedValue
	@Column(name = "locker_id")
	private Long id;

	private String name;

	@OneToOne(mappedBy = "locker")
	private Member member;
}

package me.sseob.book.shop.basic;

import javax.persistence.*;

@Entity
public class Delivery extends BaseEntity{

	@Id @GeneratedValue
	@Column(name = "delivery_id")
	private Long id;

	// 1:1 연관관계
	@OneToOne(mappedBy = "delivery")
	private Order order;
	
	private String city;
	private String street;
	private String zipcode;
	private DeliveryStatus status;
}

package me.sseob.book.shop.item;

import me.sseob.book.shop.domain.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 객체 상속타입을 토대로 Table 상속 타입을 정할 수 있다.
public class Item {

	@Id
	@GeneratedValue
	@Column(name = "item_id")
	private Long id;
	private String name;
	private int price;
	private int stockQuantity;
	
	@ManyToMany(mappedBy = "items")
	private List<Category> categories = new ArrayList<>();

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}


	@Override
	public String toString() {
		return "Item{" +
				"id=" + id +
				", name='" + name + '\'' +
				", price=" + price +
				", stockQuantity=" + stockQuantity +
				", categories=" + categories +
				'}';
	}
}

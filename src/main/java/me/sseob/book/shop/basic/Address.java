package me.sseob.book.shop.basic;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Address {
	
	private String city;
	private String street;
	private String zipcode;

	public Address() {
	}

	public Address(String city, String street, String zipcode) {
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getZipcode() {
		return zipcode;
	}

	// equals와 hashCode method를 통해 값 타입의 비교를 지원한다. 객체를 비교하는게 아니라 값을 비교하도록 설계한다.
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Address address = (Address) o;
		return Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(zipcode, address.zipcode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, street, zipcode);
	}
}

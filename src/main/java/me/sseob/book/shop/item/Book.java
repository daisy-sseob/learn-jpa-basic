package me.sseob.book.shop.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
public class Book extends Item{

	private String author;
	private String isbn; 
}

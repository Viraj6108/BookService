package com.Book.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String categoryName;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL, orphanRemoval = true)

	private List<Book> bookList = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(Long id, String categoryName, List<Book> bookList) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		//this.bookList = bookList;
	}

	public Category(long l, String string) {
		super();
		this.id = l;
		this.categoryName = string;
		// TODO Auto-generated constructor stub
	}
}

package com.Book.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
public class Admin extends User{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Name cannot be blank")
	@Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "First letter should be capital,Name can only contain letters and spaces")
	private String name;

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

	public Admin(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

}

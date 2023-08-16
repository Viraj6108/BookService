package com.Book.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Customer extends User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = "Username must not be empty")
	@Column(unique = true, nullable = false)
	@Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters long")
	@Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Username may only contain alphanumeric characters, underscores, and hyphens")
	private String username;

	@NotBlank(message = "Address is required")
    private String address;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Cart cart ;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Orders> orderList = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<Orders> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Long id, String username, String address, Cart cart, List<Orders> orderList) {
		super();
		this.id = id;
		this.username = username;
		this.address = address;
		this.cart = cart;
		this.orderList = orderList;
	}
}

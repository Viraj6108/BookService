package com.Book.Entity;


import com.fasterxml.jackson.annotation.JsonProperty;
public class logInDto {

	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public logInDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public logInDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
}

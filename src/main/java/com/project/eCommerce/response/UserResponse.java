package com.project.eCommerce.response;

import javax.persistence.Column;

import com.project.eCommerce.entities.User;

public class UserResponse {

	private Long userId;

	private String name;

	private String surname;

	private String username;

	private String email;

	private String password;

	@Column(name = "role", columnDefinition = "varchar(255) default 'USER'")
	private String role;

	public UserResponse(User entity) {
      this.userId=entity.getUserId();
      this.role=entity.getRole();
		this.name = entity.getName();
		this.surname = entity.getSurname();
		this.username = entity.getUsername();
		this.email = entity.getEmail();
		this.password = entity.getPassword();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
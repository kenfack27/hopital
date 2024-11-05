package com.example.demo.dtos;

import com.example.demo.enums.UserRole;

import lombok.Data;

@Data
public class UserDto {
	
	private Long Id;
	private String name;
	private String email;
	private String password;
	private String role;
	private com.example.demo.enums.UserRole UserRole;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public com.example.demo.enums.UserRole getUserRole() {
		return UserRole;
	}
	public void setUserRole(com.example.demo.enums.UserRole userRole) {
		UserRole = userRole;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
	


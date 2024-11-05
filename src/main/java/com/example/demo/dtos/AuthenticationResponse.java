package com.example.demo.dtos;

import com.example.demo.enums.UserRole;

import lombok.Data;

@Data 
public class AuthenticationResponse {
	//Declaration des privates des reponses  verifiant l'authentication.
	private String jwt;
	private UserRole userRole;
	private long userId;
	
	//Generation auto du getters et du setters pour les type de variables declarer.
	
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	

}

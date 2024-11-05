package com.example.demo.dtos;

public class AuthenticationRequest {
   
//Declaration des privates des requettes String verifiant l'authentication.
	
	private String email;
	private String password;
	
//Generation auto du getters et du setters pour les type de variables declarer.
	
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
}


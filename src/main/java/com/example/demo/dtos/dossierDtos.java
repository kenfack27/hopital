package com.example.demo.dtos;

import com.example.demo.modeles.User;

import lombok.Data;

@Data
public class dossierDtos {
private Long Id;
	
	private String Nom;
    private User user;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}

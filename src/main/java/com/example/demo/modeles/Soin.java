package com.example.demo.modeles;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table
@Entity
public class Soin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String nomsoins;
	private String descriptions;
	private int prix;
	
	@ManyToOne
	private DossierMedical dossier;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNomsoins() {
		return nomsoins;
	}

	public void setNomsoins(String nomsoins) {
		this.nomsoins = nomsoins;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public DossierMedical getDossier() {
		return dossier;
	}

	public void setDossier(DossierMedical dossier) {
		this.dossier = dossier;
	}
	
	
}

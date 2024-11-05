package com.example.demo.Service;

import java.util.List;

import com.example.demo.dtos.dossierDtos;
import com.example.demo.modeles.DossierMedical;

public interface DossierService {

	public DossierMedical saveDossier(DossierMedical dossier);
	public List<DossierMedical> viewALLDossier();
}

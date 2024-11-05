package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.dossierDtos;
import com.example.demo.modeles.DossierMedical;
import com.example.demo.repository.DossierRepository;

@Service
public class DossierServiceImpl implements DossierService{

	@Autowired
 private DossierRepository dossierRepo;
 
	@Override
	public DossierMedical saveDossier(DossierMedical dossier) {
		
		return dossierRepo.save(dossier);
	}

	@Override
	public List<DossierMedical> viewALLDossier() {
		// TODO Auto-generated method stub
		return dossierRepo.findAll();
	}
	

	
}

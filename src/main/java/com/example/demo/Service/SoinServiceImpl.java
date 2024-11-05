package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modeles.Soin;
import com.example.demo.repository.SoinsRepository;

@Service
public class SoinServiceImpl implements SoinsService {
	
	@Autowired
	private SoinsRepository soinRepo;

	@Override
	public Soin saveSoin(Soin dossier) {
		// TODO Auto-generated method stub
		return soinRepo.save(dossier);
	}

	@Override
	public List<Soin> viewALLSoinsbyDossier(long dossier) {
		// TODO Auto-generated method stub
		return soinRepo.findByDossierId(dossier);
	}

	@Override
	public List<Soin> showSoin() {
		// TODO Auto-generated method stub
		return soinRepo.findAll();
	}

}

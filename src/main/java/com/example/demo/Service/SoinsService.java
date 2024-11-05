package com.example.demo.Service;

import java.util.List;

import com.example.demo.modeles.Soin;



public interface SoinsService {
	public Soin saveSoin(Soin dossier);
	public List<Soin> viewALLSoinsbyDossier(long dossier);
	public List<Soin> showSoin();
}

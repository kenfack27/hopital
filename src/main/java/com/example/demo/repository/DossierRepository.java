package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modeles.DossierMedical;

public interface DossierRepository  extends JpaRepository<DossierMedical, Long> {

}

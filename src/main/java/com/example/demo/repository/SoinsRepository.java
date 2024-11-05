package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modeles.Soin;

@Repository
public interface SoinsRepository  extends JpaRepository<Soin, Long>{
    List<Soin> findByDossierId(Long id);

}

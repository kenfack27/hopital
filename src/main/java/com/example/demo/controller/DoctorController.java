package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.AuthServiceImplementation;
import com.example.demo.Service.DoctorServiceImpl;
import com.example.demo.Service.DossierServiceImpl;
import com.example.demo.Service.SoinServiceImpl;
import com.example.demo.dtos.UserDto;
import com.example.demo.modeles.DossierMedical;
import com.example.demo.modeles.Soin;
import com.example.demo.modeles.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/auth/")

public class DoctorController {
	@Autowired
	private UserRepository userRepository ;

	@Autowired
	private DoctorServiceImpl doctorService;
	
	@Autowired
	private DossierServiceImpl dossierServ;
	@Autowired
	private AuthServiceImplementation AuthServ;
	
	@Autowired
	private SoinServiceImpl soinServ;
	
	@PostMapping("/saveDoctor")

	public UserDto saveDoctor(@Validated @RequestBody User doctor){
		return doctorService.saveDoctor(doctor);
	}
	
	
	
// UPdate operation 
	
	@GetMapping("/doctor")
	
	public List<User> fetchCategoriesList(){
		return doctorService.Doctor();
	}
@GetMapping("/client")
	
	public List<User> fetchCategoriesLists(){
		return doctorService.client();
	}

@GetMapping("/nbDoctor")

public int NBdoctor(){
	return userRepository.showDoctor();
}
@GetMapping("/nbClient")

public int NBclient(){
	return userRepository.showCustomer();
}


	
@DeleteMapping("/doctor/delete/{rollNumber}")
	
	public String deleteCategoriesByRollNumber(@PathVariable("rollNumber") Long RollNumber) {
		doctorService.deleteDoctorByRollNumber(RollNumber);
		return "Deleted SucessFully";
	}
@GetMapping("/doctor/{id}")
public ResponseEntity<User> getEmployeeById(@PathVariable("id") Long id) {
	   return userRepository.findById(id) .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
}

@PostMapping("AddDossier")
public DossierMedical addCategorie(@RequestBody DossierMedical dossier) {
    return dossierServ.saveDossier(dossier);
}
@GetMapping("ViewDossier")
public List<DossierMedical> fetchDossier(){
	return dossierServ.viewALLDossier();
}
//soins

@PostMapping("AddSoin")
public Soin addSoin(@RequestBody Soin soin) {
    return soinServ.saveSoin(soin);
}
@GetMapping("/soin/{id}")
public List<Soin> getProduitsByCategorie(@PathVariable long id) {
    return soinServ.viewALLSoinsbyDossier(id);
}
@GetMapping("ViewSoin")
public List<Soin> fetchSoin(){
	return soinServ.showSoin();
}

}

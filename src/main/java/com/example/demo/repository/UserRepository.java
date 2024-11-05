package com.example.demo.repository;

import  com.example.demo.modeles.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 
	
	Optional<User> findFirstByEmail(String email);
	@Query(value = "select * from user where user_role =2", nativeQuery = true)
    public List<User> showMedecin();
	@Query(value = "select * from user where user_role =1", nativeQuery = true)
    public List<User> showClient();
	@Query(value = "select COUNT(*) as a from user where user_role=1;", nativeQuery = true)
	   int showCustomer();
	@Query(value = "select COUNT(*) as b from user where user_role=2;", nativeQuery = true)
	   int showDoctor();



}

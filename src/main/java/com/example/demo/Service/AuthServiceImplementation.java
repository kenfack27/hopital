package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.dtos.SignupRequest;
import com.example.demo.dtos.UserDto;
import com.example.demo.enums.UserRole;
import com.example.demo.modeles.User;
import com.example.demo.repository.UserRepository;

@Service
public class AuthServiceImplementation implements AuthService{

	@Autowired
 private final UserRepository userRepository;
 
 public AuthServiceImplementation(UserRepository userRepository){
  this.userRepository = userRepository;
 }

@Override
public UserDto createUser(SignupRequest signupRequest) {
	
	// TODO Auto-generated method stub permettant d'identifier le Role du user a l'authentification.
	User user = new User();
	user.setName(signupRequest.getName());
    user.setEmail(signupRequest.getEmail());
	user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
	user.setUserRole(UserRole.ADMIN);
	User createdUser = userRepository.save(user);
	UserDto createdUserDto = new UserDto();
	createdUserDto = new UserDto();
	createdUserDto.setId(createdUser.getId());
	createdUserDto.setName(createdUser.getName());
	createdUserDto.setEmail(createdUser.getEmail());
	createdUserDto.setUserRole(createdUser.getUserRole());
	
	return createdUserDto;
}

@Override
public List<User> listAllUSer() {
	// TODO Auto-generated method stub
	 return userRepository.showClient();
}

 
 }

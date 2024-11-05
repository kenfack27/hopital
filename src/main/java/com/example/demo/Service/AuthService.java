package com.example.demo.Service;

import java.util.List;

import com.example.demo.dtos.SignupRequest;
import com.example.demo.dtos.UserDto;
import com.example.demo.modeles.User;

public interface AuthService {
	UserDto createUser(SignupRequest signupRequest);
	
	List<User> listAllUSer();
	

}

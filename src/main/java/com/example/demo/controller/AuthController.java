 package com.example.demo.controller;


import com.example.demo.dtos.AuthenticationRequest;
import com.example.demo.dtos.AuthenticationResponse;
import com.example.demo.dtos.SignupRequest;
import com.example.demo.dtos.UserDto;
import com.example.demo.modeles.User;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import com.example.demo.Service.AuthService;
import com.example.demo.Service.jwt.UserDetailsServiceImpl;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController{
	private final AuthService authService;
	
	private final AuthenticationManager authenticationManager;
	
	private final UserDetailsServiceImpl userDetailsService;
	
	private final com.example.demo.Util.jwtUtil jwtUtil ;
	
	private final UserRepository userRepository;

	//  Auto-generations des methodes constructor stub pour tout les variables private de l'api d'authentification.
	public AuthController(AuthService authService,AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService,com.example.demo.Util.jwtUtil jwtUtil, UserRepository userRepository){
	  this.authService =  authService;
	  this.authenticationManager = authenticationManager;
	  this.userDetailsService = userDetailsService;
	  this.jwtUtil = jwtUtil;
	  this.userRepository = userRepository;
	}
	
//Aplication de la methode @PostMapping permettant l'inscription du User en utilisant une boucle .
	
    @PostMapping("/signup")
    public ResponseEntity<?>signupUser(@RequestBody SignupRequest signupRequest){
      UserDto createdUserDto = authService.createUser(signupRequest);
    if (createdUserDto == null) {
    	return new ResponseEntity<>("User not created.Come again later", HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }
  //Aplication de la methode @PostMapping permettant l'authentification du User en utilisant une boucle .
    
    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException {
    	try {
    	     authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
    	} catch (BadCredentialsException e){
    		throw new BadCredentialsException("incorrect username or password");
        } catch (DisabledException disableException) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not active");
        	
        }
    	final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
    	final String jwt = jwtUtil.generateToken(userDetails.getUsername());
    	Optional<User> OptionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
    	AuthenticationResponse authenticationResponse = new AuthenticationResponse();
    	if(OptionalUser.isPresent()) {
			authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserRole(OptionalUser.get().getUserRole());
            authenticationResponse.setUserId(OptionalUser.get().getId());
             
    	}
		return authenticationResponse;
    }
}

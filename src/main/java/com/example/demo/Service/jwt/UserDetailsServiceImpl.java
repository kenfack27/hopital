package com.example.demo.Service.jwt;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	
	private final UserRepository userRepository;
	/**
	 * @param userRepository
	 */
		public UserDetailsServiceImpl(UserRepository userRepository) {
		      this.userRepository = userRepository;
	}

		// TODO Auto-generated constructor stub

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//write logic to get User from db
		Optional<com.example.demo.modeles.User> OptionalUser = userRepository.findFirstByEmail(email);
		if (OptionalUser.isEmpty()) throw new UsernameNotFoundException("user not found", null);
		return new org.springframework.security.core.userdetails.User(OptionalUser. get().getEmail(),OptionalUser.get().getPassword(),new ArrayList<>());
		
	}


		

}

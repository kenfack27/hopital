package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.Service.jwt.UserService;
import com.example.demo.enums.UserRole;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class WebSecurityConfiguration {
	//Declaration des variables private definisant la configuration de la securiter de l'authentification.
	private final JwtAuthenticationFilter jwtAuthenticationFilter ;
	private final UserService userService;
	
     public WebSecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter, UserService userService) {
		super();
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.userService = userService;
	}
     // Generation d'une methode constructeur des variables declarées dans la classe .
	@Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    	http.csrf(AbstractHttpConfigurer::disable)
    
    	.authorizeHttpRequests(request -> request.requestMatchers("/api/auth/**"). permitAll().requestMatchers("/api/admin/**").hasAnyAuthority(UserRole.ADMIN.name()).
    			anyRequest().authenticated())
    	
    .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    .authenticationProvider(authenticationProvider()).addFilterBefore(
    		jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
     return http.build();
     }
     private  AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userService.userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	@Bean
	 public  PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder(); 
     }
     @Bean
     public  AuthenticationManager authentificationManager(AuthenticationConfiguration configuration)throws Exception{ 
    	 return configuration.getAuthenticationManager();
     
     }
}

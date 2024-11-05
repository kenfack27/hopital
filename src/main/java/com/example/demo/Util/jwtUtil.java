package com.example.demo.Util;


import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class jwtUtil {
	
	public static final String SECRET ="JSZT127YIAH648DHCHF3644T4R3F8ZJ2135V7C9CCJNDUZ211234GD0CDNE75N1A2Z3E4R5T6Y7U8I9O0PKM9H7B4C3D21SQ6F0J2C8N4W7S6V3W9NCGSOET5394VYC6S9ZN7FNCF2XSQ7S0DTNDT1FVFN0V0NH";
	
	public String generateToken(String email ) {
	 HashMap<String,Object> claims = new HashMap<>();
	return createToken(claims,email);
	}

   private String createToken(Map<String, Object> claims, String email){
	 return Jwts.builder()
		  .setClaims(claims)
	      .setSubject(email)
	      .setIssuedAt(new Date(System.currentTimeMillis()))
	      .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
	      .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
			 
   }
   private Key getSignKey() {
	   byte[] KeyBytes = Decoders.BASE64.decode(SECRET);
	   return Keys.hmacShaKeyFor(KeyBytes);
   }
	
   public String extractUserName(String token) {
	return extractClaims(token, Claims::getSubject);
}


public boolean isTokenValid(String token, UserDetails userDetails) {
	   final String userName = extractUserName(token);
	   return ( userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
   }
private boolean isTokenExpired(String token) {
	// TODO Auto-generated method stub
	return extractExpiration(token).before(new Date());
}
  
   private <T> T extractClaims(String token, Function<Claims, T> claimsResolvers) {
	   final Claims claims = extractAllClaims(token);
	   return claimsResolvers.apply(claims);
   }
   
   private Date extractExpiration(String token) {
	   return extractClaims(token, Claims::getExpiration);
   }
   

private Claims extractAllClaims(String token) {
	   return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token)
			   .getBody();
   }


}

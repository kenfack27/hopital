package com.example.demo.configuration;

import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.demo.Service.jwt.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter<jwtUtil> extends OncePerRequestFilter{

	
	private final com.example.demo.Util.jwtUtil jwtUtil;
	private final UserService userService;
	
	public JwtAuthenticationFilter(com.example.demo.Util.jwtUtil jwtUtil, UserService userService) {
		super();
		this.jwtUtil = jwtUtil;
		this.userService = userService;
	}
// creation d'une methode Override et d'implementation des variables de la clase caracteriser pas des importations du dessus du code permettant la configuration de l'authentification.
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer")) {
		filterChain.doFilter(request, response);
		return;
		}
		jwt = authHeader.substring(7);
		userEmail = jwtUtil.extractUserName(jwt);
		if (StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
		UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
		if (jwtUtil.isTokenValid(jwt, userDetails)) {
			SecurityContext context = SecurityContextHolder.createEmptyContext();
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			context.setAuthentication(authToken);
			SecurityContextHolder.setContext(context);
		}
		}
		filterChain.doFilter(request, response);
	}
}

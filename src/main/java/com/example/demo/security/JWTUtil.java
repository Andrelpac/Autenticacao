package com.example.demo.security;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.demo.service.AutorService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	@Autowired
	AutorService service;

	public String generateToken(String username) {
		return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token != null) {
			String user = Jwts.parser().setSigningKey("serratec".getBytes()).parseClaimsJws(token.replace("Bearer", ""))
					.getBody().getSubject();
			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
			}
		}
		System.out.println("Token invalido");
		return null;
	}

	public String getCreditials(String token) {
		if (token != null) {
			String user = Jwts.parser().setSigningKey("serratec".getBytes()).parseClaimsJws(token.replace("Bearer", ""))
					.getBody().getSubject();
			if (user != null) {
				return service.getAutor(user).getNome();
			}
		}
		return null;
	}

}

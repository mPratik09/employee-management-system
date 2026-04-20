package com.employee.management.system.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTService
{

	private static final Logger log = LoggerFactory.getLogger(JWTService.class);

	public static String secretKey = "GK7xEbnq96rQzDTQzSdeYkktTQD2Tz1Kb7YSFjMoXt8=";

	public String generateToken(String userName)
	{

		String jwtToken = Jwts.builder().setSubject(userName).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();

		log.info("Generated JWT Token:: {} || For User:: {}", jwtToken, userName);

		return jwtToken;

	}

	public String extractUsername(String token)
	{
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public Claims extractClaims(String token)
	{
		return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
	}

	public boolean isTokenValid(String token, String username)
	{
		String extractedUser = extractUsername(token);
		return (extractedUser.equals(username) && !isTokenExpired(token));
	}

	public boolean isTokenExpired(String token)
	{
		return extractClaims(token).getExpiration().before(new Date());
	}

}

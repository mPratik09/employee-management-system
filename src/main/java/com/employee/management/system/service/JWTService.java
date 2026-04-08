package com.employee.management.system.service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTService
{

	private static final Logger log = LoggerFactory.getLogger(JWTService.class);

	public static String secretKey;

	public JWTService()
	{
		try
		{
			KeyGenerator kGen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sKey = kGen.generateKey();
			secretKey = Base64.getEncoder().encodeToString(sKey.getEncoded());

			log.info("Secret Key:: {}", secretKey);

		} catch (NoSuchAlgorithmException e)
		{
			throw new RuntimeException(e);
		}
	}

	public String generateToken(String userName)
	{

		String jwtToken = Jwts.builder().setSubject(userName).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();

		log.info("Generated JWT Token:: {} || For User:: {}", jwtToken, userName);

		return jwtToken;

	}

}

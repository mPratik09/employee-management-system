package com.employee.management.system.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.management.system.entity.User;
import com.employee.management.system.repo.AuthRepo;

@Service
public class AuthService
{

	private static final Logger log = LoggerFactory.getLogger(AuthService.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	AuthRepo authRepo;

	public User verifyUser(String email, String rawPassword)
	{
		Optional<User> userOptional = getByUserEmail(email);

		if (!userOptional.isPresent())
		{
			log.info("User not found with email: {}", email);
			return null;
		}

		User user = userOptional.get();

		if (!doPasswordsMatch(rawPassword, user.getPassword()))
		{
			log.info("Password did not match..");
//			redirectAttributes.addFlashAttribute("error", "Invalid email or password");
			return null;
		}

		String checkStatus = authRepo.checkStatus(user.getId());
		log.info("checkStatus:\t{}", checkStatus);

		authRepo.checkDepartment(user.getId());

		return user;
	}

	public Optional<User> getByUserEmail(String email)
	{
		Optional<User> user = authRepo.findByUserEmail(email);

		return user;
	}

	public boolean doPasswordsMatch(String rawPassword, String encodedPassword)
	{
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

}

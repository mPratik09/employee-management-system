package com.employee.management.system.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.system.entity.User;
import com.employee.management.system.repo.UserRepo;
import com.employee.management.system.response.dto.UserResponseDTO;
import com.employee.management.system.validator.UserValidator;

@Service
public class UserService
{

	@Autowired
	UserRepo userRepo;

	private static Logger log = LoggerFactory.getLogger(UserService.class);

	public UserResponseDTO saveUser(User user)
	{
//		UserRepo userRepo = new UserRepo();

		UserValidator validator = new UserValidator();

		if (!validator.isUserValid(user))
		{
			log.info("Please enter Valid credentials..");
			throw new IllegalArgumentException("Invalid credentials");
		}

		UserResponseDTO savedUser = userRepo.saveUser(user);

		return savedUser;
	}

	public Optional<User> getByUserEmail(String email)
	{

		Optional<User> user = userRepo.findByUserEmail(email);

		return user;

	}

	public Optional<List<Map<String, Object>>> fetchRecords()
	{

		Optional<List<Map<String, Object>>> pendingUsers = userRepo.fetchRecords();

		return pendingUsers;
	}

}

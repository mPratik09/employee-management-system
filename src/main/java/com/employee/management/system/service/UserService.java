package com.employee.management.system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.system.entity.User;
import com.employee.management.system.repo.UserRepo;
import com.employee.management.system.response.dto.UserResponseDTO;

@Service
public class UserService
{

	@Autowired
	UserRepo userRepo;

	private static Logger log = LoggerFactory.getLogger(UserService.class);

	public UserResponseDTO saveUser(User user)
	{
//		UserRepo userRepo = new UserRepo();

		UserResponseDTO savedUser = userRepo.saveUser(user);

		return savedUser;
	}

}

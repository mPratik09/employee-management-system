package com.employee.management.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.employee.management.system.entity.User;
import com.employee.management.system.mapper.UserMapper;
import com.employee.management.system.request.dto.UserRequestDTO;
import com.employee.management.system.service.UserService;

@Controller
public class UserController
{

	@Autowired
	UserMapper userMapper;

	@Autowired
	UserService userService;

	private static Logger log = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/saveUser")
	public String persistUser(@ModelAttribute UserRequestDTO userRequestDTO)
	{

		User user = userMapper.userMapper(userRequestDTO);

		userService.saveUser(user);

		return "savedUser";
	}

}

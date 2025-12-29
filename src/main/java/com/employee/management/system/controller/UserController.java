package com.employee.management.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.employee.management.system.entity.User;
import com.employee.management.system.mapper.UserMapper;
import com.employee.management.system.request.dto.UserRequestDTO;
import com.employee.management.system.response.dto.UserResponseDTO;
import com.employee.management.system.service.UserService;

@Controller
public class UserController
{

	@Autowired
	UserMapper userMapper;

	@Autowired
	UserService userService;

	private static Logger log = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/registerUser")
	public String registerUser()
	{
		return "registerUser";
	}

	@PostMapping("/saveUser")
	public String persistUser(@ModelAttribute UserRequestDTO userRequestDTO, RedirectAttributes redirectAttributes)
	{
		try
		{
			User user = userMapper.userMapper(userRequestDTO);
			UserResponseDTO savedUser = userService.saveUser(user);

			log.info("User has been saved with id:\t{}", savedUser.getId());
			log.info("USER:\t{}", savedUser);

		} catch (IllegalArgumentException e)
		{
			log.info("INVALID CREDENTIALS...");
			redirectAttributes.addFlashAttribute("msg", e.getMessage());
			return "redirect:/registerUser";
		}

		return "savedUser";
	}

}

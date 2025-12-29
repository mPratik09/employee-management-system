package com.employee.management.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.employee.management.system.service.UserService;

@Controller
public class AuthController
{

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	private static Logger log = LoggerFactory.getLogger(AuthController.class);

	@GetMapping("/registerUser")
	public String registerUser()
	{
		return "registerUser";
	}

	@GetMapping("/showLogin")
	public String login()
	{
		return "login";
	}

	@GetMapping("/loggedIn")
	public String loggedIn()
	{
		return "loggedIn";
	}

	@PostMapping("/verifyLogin")
	public String verifyPassword(@RequestParam("email") String email, @RequestParam("password") String rawPassword,
			RedirectAttributes redirectAttributes)
	{
		String password = userService.getByUserEmail(email);

		if (!doPasswordsMatch(rawPassword, password))
		{
			log.info("Password did not match..");
			redirectAttributes.addFlashAttribute("error", "Invalid email or password");
			return "redirect:/showLogin";
		}

		return "redirect:/loggedIn";
	}

	public boolean doPasswordsMatch(String rawPassword, String encodedPassword)
	{
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

}

package com.employee.management.system.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.employee.management.system.entity.User;
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
			RedirectAttributes redirectAttributes, HttpSession httpSession, Model model)
	{
		Optional<User> optionalUser = userService.getByUserEmail(email);

		if (!optionalUser.isPresent())
		{
			log.info("User not found with email: {}", email);
			return "login";
		}

		User user = optionalUser.get();

		if (!doPasswordsMatch(rawPassword, user.getPassword()))
		{
			log.info("Password did not match..");
			redirectAttributes.addFlashAttribute("error", "Invalid email or password");
			return "redirect:/showLogin";
		}

		if (user.getStatus().equals("ASSIGNED"))
		{

			User userWithSession = createSession(httpSession, user);

			if (userWithSession.getRole().equals("SUPPORT"))
			{
				Optional<List<Map<String, Object>>> pendingUser = userService.fetchRecords();
				List<Map<String, Object>> pendingUserList = pendingUser.orElse(Collections.emptyList());

				log.info("Pending Users List: {}", pendingUserList);
				model.addAttribute("pendingUser", pendingUserList);

				return "support";
			} else if (userWithSession.getRole().equals("ADMIN"))
			{
				model.addAttribute("user", userWithSession);
				return "admin";
			} else
			{
				model.addAttribute("user", userWithSession);
				return "employee";
			}
		}

		log.info("User in Auth: {}", user);
		model.addAttribute("user", user);

		return "loggedIn";
	}

	@PostMapping("/logout")
	public String logout(HttpSession httpSession)
	{
		httpSession.invalidate();
		log.info("Session invalidated. User logged out.");

		return "redirect:/showLogin";
	}

	private User createSession(HttpSession httpSession, User user)
	{

		httpSession.setAttribute("userSession", user);

		User userSession = (User) httpSession.getAttribute("userSession");

		return userSession;
	}

	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, HttpServletResponse response)
	{

		if (session.getAttribute("userSession") == null)
		{
			return "redirect:/showLogin";
		}

		// this prevents browser caching
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		return "dashboard";
	}

	public boolean doPasswordsMatch(String rawPassword, String encodedPassword)
	{
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

}

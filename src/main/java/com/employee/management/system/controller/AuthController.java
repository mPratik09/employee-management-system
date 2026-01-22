package com.employee.management.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.employee.management.system.config.DepartmentContext;
import com.employee.management.system.entity.Department;
import com.employee.management.system.entity.User;
import com.employee.management.system.request.dto.ViewUsersDTO;
import com.employee.management.system.service.AuthService;
import com.employee.management.system.service.DepartmentService;
import com.employee.management.system.service.UserService;

@Controller
public class AuthController
{

	private static Logger log = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private AuthService authService;

	@Autowired
	DepartmentService departService;

	@Autowired
	UserService userService;

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

		User user = authService.verifyUser(email, rawPassword);

		if (user == null)
		{
			model.addAttribute("error", "Invalid email or password");
			return "login";
		}

		createSession(httpSession, user);
//		String redirectTo = departService.getLandingViewForUser(user);

		model.addAttribute("user", user);
		switch (user.getStatus()) {
		case UNASSIGNED:
			List<Department> fetchedDepartments = departService.fetchDepartments();

			model.addAttribute("departmentsList", fetchedDepartments);
			return "makeRequest";
		case PENDING:
			model.addAttribute("reqPendingMsg", "Your request is still pending.");
			return "reqPending";
		case REJECTED:
			return "login?rejected";
		case APPROVED:

			DepartmentContext roleBasedRedirect = departService.roleBasedRedirect(user.getId());
			List<ViewUsersDTO> loadUsersByAllowedView = userService
					.loadUsersByAllowedView(roleBasedRedirect.getAllowedView());

			model.addAttribute("usersList", loadUsersByAllowedView);

			return roleBasedRedirect.getLandingPage();
		}

		model.addAttribute("user", user);
		return "";

		/*
		 * Query:Could it be a situation where a user exists with an APPROVED status but
		 * does not have an entry in the "emp_temp"(M:N) table??
		 */

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

}

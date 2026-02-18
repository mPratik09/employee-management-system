package com.employee.management.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.employee.management.system.config.DepartmentContext;
import com.employee.management.system.entity.Department;
import com.employee.management.system.entity.User;
import com.employee.management.system.entity.UserPrincipal;
import com.employee.management.system.request.dto.ViewUsersDTO;
import com.employee.management.system.response.dto.UserResponseDTO;
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

	@GetMapping("/url/support_dashboard")
	public String support_dashboard(Authentication authentication, ModelMap modelMap)
	{

		UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

		DepartmentContext roleBasedRedirect = departService.roleBasedRedirect(principal.getUserId());

		List<ViewUsersDTO> loadUsersByAllowedView = userService
				.loadUsersByAllowedView(roleBasedRedirect.getAllowedView());

		modelMap.addAttribute("usersList", loadUsersByAllowedView);
		return "support_dashboard";
	}

	@GetMapping("/makeRequest")
	public String makeRequest(Model model)
	{
		List<Department> fetchedDepartments = departService.fetchDepartments();
		model.addAttribute("departmentsList", fetchedDepartments);

		log.info("List of Departments: {}", fetchedDepartments);

		return "makeRequest";
	}

	@GetMapping("/url/{redirectPage}")
	public String hrPortal(ModelMap modelMap, @AuthenticationPrincipal UserPrincipal userPrincipal, Model model)
	{
		String department = userPrincipal.getDepartments().get(0).getDepartment();
		List<UserResponseDTO> fetchEmployeesByDepartment = userService.fetchEmployeesByDepartment(department);

		modelMap.addAttribute("employeesList", fetchEmployeesByDepartment);
		return userPrincipal.getDepartments().get(0).getLandingPage();
	}

	@PostMapping("/url/logout")
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

package com.employee.management.system.restcontroller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.system.restresponse.ApiResponse;
import com.employee.management.system.service.AuthService;
import com.employee.management.system.service.DepartmentService;
import com.employee.management.system.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController
{

	private static final Logger log = LoggerFactory.getLogger(AuthRestController.class);

	@Autowired
	private AuthService authService;

	@Autowired
	private DepartmentService departService;

	@Autowired
	private UserService userService;

	@PostMapping("/logout")
	public ResponseEntity<ApiResponse<Void>> logout(HttpSession session)
	{
		session.invalidate();
		log.info("Session invalidated");

		return ResponseEntity.ok(new ApiResponse<>(true, "Logged out successfully", null));
	}

	@GetMapping("/dashboard")
	public ResponseEntity<ApiResponse<String>> dashboard(HttpSession session)
	{

		if (session.getAttribute("userSession") == null)
		{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ApiResponse<>(false, "Session expired or not logged in", null));
		}

		return ResponseEntity.ok(new ApiResponse<>(true, "Session active", "dashboard"));
	}
}

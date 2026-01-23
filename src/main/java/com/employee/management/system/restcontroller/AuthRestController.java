package com.employee.management.system.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.system.config.DepartmentContext;
import com.employee.management.system.entity.User;
import com.employee.management.system.request.dto.UserRequestDTO;
import com.employee.management.system.request.dto.ViewUsersDTO;
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

	@PostMapping("/verifyLogin")
	public ResponseEntity<ApiResponse<?>> verifyLogin(@RequestBody UserRequestDTO request, HttpSession session)
	{

		User user = authService.verifyUser(request.getEmail(), request.getPassword());
		log.info("Email: {}", request.getEmail());
		if (user == null)
		{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ApiResponse<>(false, "Invalid email or password", null));
		}

		session.setAttribute("userSession", user);

		switch (user.getStatus()) {

		case UNASSIGNED:
			return ResponseEntity.ok(new ApiResponse<>(true, "User is unassigned. Choose department first.",
					departService.fetchDepartments()));

		case PENDING:
			return ResponseEntity.ok(new ApiResponse<>(true, "Your request is still pending.", null));

		case REJECTED:
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body(new ApiResponse<>(false, "Your request has been rejected.", null));

		case APPROVED:
			DepartmentContext context = departService.roleBasedRedirect(user.getId());

			List<ViewUsersDTO> users = userService.loadUsersByAllowedView(context.getAllowedView());

			Map<String, Object> approvedPayload = new HashMap<>();
			approvedPayload.put("landingPage", context.getLandingPage());
			approvedPayload.put("users", users);

			return ResponseEntity.ok(new ApiResponse<>(true, "Employee logged in successfully..", approvedPayload));
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiResponse<>(false, "Unexpected error..", null));
	}

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

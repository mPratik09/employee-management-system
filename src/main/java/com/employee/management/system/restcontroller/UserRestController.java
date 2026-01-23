package com.employee.management.system.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.system.entity.User;
import com.employee.management.system.mapper.UserMapper;
import com.employee.management.system.request.dto.UserRequestDTO;
import com.employee.management.system.request.dto.ViewUsersDTO;
import com.employee.management.system.response.dto.UserResponseDTO;
import com.employee.management.system.response.dto.UserUpdateDTO;
import com.employee.management.system.restresponse.ApiResponse;
import com.employee.management.system.service.StatusService;
import com.employee.management.system.service.UserService;

@RestController
@RequestMapping("/api/employees")
public class UserRestController
{

	private static final Logger log = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private StatusService statusService;

	@PostMapping("/saveUser")
	public ResponseEntity<ApiResponse<UserResponseDTO>> registerUser(@RequestBody UserRequestDTO userRequestDTO)
	{

		try
		{
			User user = userMapper.userMapper(userRequestDTO);
//			log.info("Status:\t{}", user.getStatus());
			UserResponseDTO savedUser = userService.saveUser(user);

			log.info("User saved with id: {}", savedUser.getId());

			return ResponseEntity.ok(new ApiResponse<>(true, "User registered successfully", savedUser));

		} catch (IllegalArgumentException e)
		{
			log.error("Invalid credentials..", e);

			return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
		}
	}

	@PostMapping("/makeRequest")
	public ResponseEntity<ApiResponse<String>> makeRequest(@RequestParam("empId") int empId, @RequestParam int departId)
	{

		userService.changeStatusToPending(empId);
		statusService.checkStatus(empId);

		userService.makeRequest(empId, departId);

		return ResponseEntity.ok(new ApiResponse<>(true,
				"Request sent to support person with empId: " + empId + " || departId: " + departId, null));
	}

	@PostMapping("/approveReq")
	public ResponseEntity<ApiResponse<List<ViewUsersDTO>>> approveRequest(@RequestParam int empId,
			@RequestParam int departId)
	{
		log.info("Approving request. empId={} ||\tdepartId={}", empId, departId);

		userService.approveRequest(empId, departId);
		userService.updateStatusToApproved(empId);

		userService.deleteEntryFromDepartReq(empId, departId);

		List<ViewUsersDTO> updatedPendingUsers = userService.fetchPendingUsers();

		return ResponseEntity.ok(new ApiResponse<>(true, "Request approved successfully", updatedPendingUsers));
	}

	@GetMapping("/updateEmp/{empId}")
	public ResponseEntity<ApiResponse<UserUpdateDTO>> fetchUserForUpdate(@PathVariable int empId)
	{

		UserUpdateDTO updateEmployee = userService.updateEmployee(empId);

		log.info("User to be updated\t: {}", updateEmployee);

		return ResponseEntity.ok(new ApiResponse<>(true, "User fetched successfully..", updateEmployee));
	}

	@PutMapping("/saveUpdatedEmp")
	public ResponseEntity<ApiResponse<List<ViewUsersDTO>>> saveUpdatedUser(@RequestBody UserUpdateDTO userUpdateDTO)
	{

		userService.saveUpdatedEmployee(userUpdateDTO);

		List<ViewUsersDTO> pendingUsers = userService.fetchPendingUsers();

		return ResponseEntity.ok(new ApiResponse<>(true, "User updated successfully..:\t{}", pendingUsers));
	}
}

package com.employee.management.system.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.employee.management.system.entity.User;
import com.employee.management.system.entity.UserPrincipal;
import com.employee.management.system.mapper.UserMapper;
import com.employee.management.system.repo.UserRepo;
import com.employee.management.system.request.dto.UserRequestDTO;
import com.employee.management.system.request.dto.ViewUsersDTO;
import com.employee.management.system.response.dto.UserResponseDTO;
import com.employee.management.system.response.dto.UserUpdateDTO;
import com.employee.management.system.service.StatusService;
import com.employee.management.system.service.UserService;

@Controller
public class UserController
{

	@Autowired
	UserMapper userMapper;

	@Autowired
	UserService userService;

	@Autowired
	UserRepo userRepo;

	@Autowired
	private StatusService statusService;

	private static Logger log = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/saveUser")
	public String persistUser(@ModelAttribute UserRequestDTO userReqDTO, RedirectAttributes redirectAttributes)
	{
		try
		{
			User user = userMapper.userMapper(userReqDTO);

			UserResponseDTO savedUser = userService.saveUser(user);

			log.info("User has been saved with id:\t{}", savedUser.getId());
			log.info("USER:\t{}", savedUser);

		} catch (IllegalArgumentException e)
		{
			log.info("INVALID CREDENTIALS...");
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/registerUser";
		}

		return "redirect:/savedUser";
	}

	@PostMapping("/makeRequest")
	public String makeRequest(@RequestParam("departId") int departId,
			@AuthenticationPrincipal UserPrincipal userPrincipal, Model model)
	{

		userService.changeStatusToPending(userPrincipal.getUserId());

		statusService.checkStatus(userPrincipal.getUserId());

		userService.makeRequest(userPrincipal.getUserId(), departId);

		model.addAttribute("reqPendingMsg", "Your request sent to Suport person..");
		return "reqPending";
	}

	@PostMapping("/url/approveReq")
	public String approveDepartRequest(@RequestParam("empId") int empId, @RequestParam("departId") int departId,
			Model model)
	{

		log.info("User Id: {} || Department Id {}", empId, departId);

		userService.approveRequest(empId, departId);

		userService.updateStatusToApproved(empId);

		userService.deleteEntryFromDepartReq(empId, departId);

		List<ViewUsersDTO> updatedPendingUsersList = userService.fetchPendingUsers();
		model.addAttribute("usersList", updatedPendingUsersList);

		return "support_dashboard";
	}

	@PostMapping("/url/updateEmp")
	public String updateEmpployee(@RequestParam("empId") int empId, Model model)
	{
		UserUpdateDTO updateEmployee = userService.updateEmployee(empId);

		log.info("User to be updated: {}", updateEmployee);
		model.addAttribute("updateEmployee", updateEmployee);

		return "updateEmpForm";
	}

	@PostMapping("/saveUpdatedEmp")
	public String saveUpdatedEmp(@ModelAttribute UserUpdateDTO userUpdateDTO, ModelMap modelMap)
	{
		userService.saveUpdatedEmployee(userUpdateDTO);

		List<ViewUsersDTO> pendingUsersList = userService.fetchPendingUsers();
		modelMap.addAttribute("msg", "User updated succesfully..");
		modelMap.addAttribute("usersList", pendingUsersList);

		return "support_dashboard";
	}
}

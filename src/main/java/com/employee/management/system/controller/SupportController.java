package com.employee.management.system.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.management.system.entity.User;
import com.employee.management.system.repo.SupportRepo;
import com.employee.management.system.service.UserService;

@Controller
public class SupportController
{

	private static final Logger log = LoggerFactory.getLogger(SupportController.class);

	@Autowired
	SupportRepo supportRepo;

	@Autowired
	UserService userService;

	@PostMapping("/assignRole")
	public String assignRole(Model model)
	{
//		model.addAttribute("msg", "Your request sen to Suport person..");
		return "assignRole";
	}

	@PostMapping("/roleAssign")
	public String roleAssignment(@RequestParam("id") int id, @RequestParam("role") String role, Model model)
	{
		log.info("Inside role assign controller");
		supportRepo.roleAssign(id);

		log.info("Id {} with role {}", id, role);
		List<User> pendingUsers = userService.getPendingUsers();
		model.addAttribute("pendingUser", pendingUsers);
		return "support";
	}

}

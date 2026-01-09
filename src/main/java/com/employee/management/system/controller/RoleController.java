package com.employee.management.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.management.system.service.RoleService;
import com.employee.management.system.service.UserService;

@Controller
public class RoleController
{

	private static final Logger log = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@PostMapping("/makeRequest")
	public String makeRequest(@RequestParam("depart_code") String departCode, @RequestParam("user_id") int userId,
			Model model)
	{
		roleService.roleRequest(departCode);

		userService.changeStatus(userId);

		model.addAttribute("msg", "Your request sent to Suport person..");

		return "reqPending";
	}

}

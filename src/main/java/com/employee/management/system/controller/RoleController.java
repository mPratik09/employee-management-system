package com.employee.management.system.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.management.system.service.DepartmentService;
import com.employee.management.system.service.RoleService;
import com.employee.management.system.service.StatusService;
import com.employee.management.system.service.UserService;

@Controller
public class RoleController
{

	private static final Logger log = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private DepartmentService departmentService;

	@PostMapping("/makeRequest")
	public String makeRequest(@RequestParam("departIds") List<Integer> departIds, @RequestParam("empId") int empId,
			Model model)
	{
		userService.changeStatus(empId);

		statusService.checkStatus(empId);

		departmentService.makeRequest(empId, departIds);

		model.addAttribute("reqPendingMsg", "Your request sent to Suport person..");
		return "reqPending";
	}

}

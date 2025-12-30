package com.employee.management.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoleController
{

	private static final Logger log = LoggerFactory.getLogger(RoleController.class);

	@PostMapping("/adminReq")
	public String adminReq(@RequestParam("id") int id, Model model)
	{
		log.info("Inside ADMIN REQ..");
		log.info("User id..{}", id);

		model.addAttribute("msg", "USer registered as an ADMIN");

		return "assignRole";
	}

	@PostMapping("/employeeReq")
	public String employeeReq(@RequestParam("id") int id, Model model)
	{
		log.info("Inside EMPLOYEE REQ..");
		log.info("User id..{}", id);

		model.addAttribute("msg", "USer registered as an EMPLOYEE");

		return "assignRole";
	}

}

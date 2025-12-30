package com.employee.management.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SupportController
{

	@PostMapping("/assignRole")
	public String assignRole(Model model)
	{
//		model.addAttribute("msg", "Your request sen to Suport person..");
		return "assignRole";
	}

}

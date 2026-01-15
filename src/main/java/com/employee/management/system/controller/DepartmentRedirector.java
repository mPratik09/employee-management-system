package com.employee.management.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;

public class DepartmentRedirector
{

	private static final Logger log = LoggerFactory.getLogger(DepartmentRedirector.class);

	@GetMapping("/Admin")
	public String adminPage()
	{
		return "admin";
	}

	@GetMapping("/Support")
	public String supportPage()
	{
		return "Support";
	}

	@GetMapping("/Human Resources")
	public String supporthumanResourcePage()
	{
		return "";
	}

	@GetMapping("/Finance")
	public String financePage()
	{
		return "finance";
	}

}

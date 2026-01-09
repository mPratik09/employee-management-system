package com.employee.management.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.system.repo.RoleRepo;

@Service
public class RoleService
{
	@Autowired
	private RoleRepo roleRepo;

	public void roleRequest(String departCode)
	{
		roleRepo.roleRequest(departCode);
	}

}

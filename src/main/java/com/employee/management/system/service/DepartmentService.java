package com.employee.management.system.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.system.entity.Department;
import com.employee.management.system.repo.DepartmentRepo;

@Service
public class DepartmentService
{

	private static final Logger log = LoggerFactory.getLogger(DepartmentService.class);

	@Autowired
	DepartmentRepo departRepo;

	public List<Department> fetchLinkedDepartments(int userId)
//	public List<Map<String, Object>> fetchLinkedDepartments(int userId) throws Exception
	{
		List<Department> fetchLinkedDepartments = departRepo.fetchLinkedDepartments(userId);
//		List<Map<String, Object>> fetchLinkedDepartments = departRepo.fetchLinkedDepartments(userId);

		log.info("Size of associated deparments:\t{}", fetchLinkedDepartments.size());

		return fetchLinkedDepartments;
	}

}

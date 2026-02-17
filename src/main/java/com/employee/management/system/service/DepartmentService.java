package com.employee.management.system.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.system.config.DepartmentContext;
import com.employee.management.system.entity.Department;
import com.employee.management.system.entity.User;
import com.employee.management.system.repo.DepartmentRepo;
import com.employee.management.system.request.dto.ViewUsersDTO;

@Service
public class DepartmentService
{

	private static final Logger log = LoggerFactory.getLogger(DepartmentService.class);

	@Autowired
	DepartmentRepo departRepo;

	@Autowired
	private UserService userService;

	public List<String> fetchLinkedDepartments(int userId)
//	public List<Map<String, Object>> fetchLinkedDepartments(int userId) throws Exception
	{
		List<String> fetchLinkedDepartments = departRepo.fetchLinkedDepartments(userId);
//		List<Map<String, Object>> fetchLinkedDepartments = departRepo.fetchLinkedDepartments(userId);

		log.info("Size of associated deparments:\t{}", fetchLinkedDepartments.size());

		return fetchLinkedDepartments;
	}

//	UNUSED METHOD	A-1
	public String getLandingViewForUser(User user)
	{

//		switch (user.getStatus()) {
//		case UNASSIGNED:
//			return "makeRequest";
//		case PENDING:
//			return "reqPending";
//		case REJECTED:
//			return "login?rejected";
//		case APPROVED:
//			return roleBasedRedirect(user.getId());
//		}
		String fetchLinkedDepartments = departRepo.getLandingViewForUser(user.getId());
		return fetchLinkedDepartments;
	}

	public DepartmentContext roleBasedRedirect(int userId)
	{
		DepartmentContext fetchDepartmentContext = departRepo.fetchDepartmentContext(userId);

		return fetchDepartmentContext;
	}

//	UNUSED METHOD
	public String loadVisibleUsers(DepartmentContext fetchDepartmentContext)
	{
		List<ViewUsersDTO> loadUsersByAllowedView = userService
				.loadUsersByAllowedView(fetchDepartmentContext.getAllowedView());

		return fetchDepartmentContext.getLandingPage();
	}

	public List<Department> fetchDepartments()
	{
		List<Department> departmentsList = departRepo.fetchDepartments();
		log.info("List of All Departments: {}", departmentsList);
		return departmentsList;
	}

	public void departmentExists(int departId)
	{
		if (!departRepo.checkDepartmentExists(departId))
		{
			log.info("Department Not Found..");
			throw new IllegalArgumentException("Invalid department..");
		}

	}
}

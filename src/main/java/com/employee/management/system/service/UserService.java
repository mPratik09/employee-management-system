package com.employee.management.system.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.system.entity.User;
import com.employee.management.system.repo.UserRepo;
import com.employee.management.system.request.dto.ViewUsersDTO;
import com.employee.management.system.response.dto.UserResponseDTO;
import com.employee.management.system.response.dto.UserUpdateDTO;
import com.employee.management.system.validator.UserValidator;

@Service
public class UserService
{

	@Autowired
	UserRepo userRepo;

	private static Logger log = LoggerFactory.getLogger(UserService.class);

	public UserResponseDTO saveUser(User user)
	{
//		UserRepo userRepo = new UserRepo();

		UserValidator validator = new UserValidator();

		if (!validator.isUserValid(user))
		{
			log.info("Please enter Valid credentials..");
			throw new IllegalArgumentException("Invalid credentials");
		}

		UserResponseDTO savedUser = userRepo.saveUser(user);

		return savedUser;
	}

	public List<User> getPendingUsers()
	{

		List<User> usersList = userRepo.getPendingUsers();

		return usersList;
	}

	/*
	 * not have caller from controller but has calling method inside repo
	 */
	public void roleRequest(int userId, int deptId)
	{
		userRepo.roleRequest(userId, deptId);

	}

	public void changeStatusToPending(int userId)
	{
		userRepo.updateStatusToPending(userId);
	}

	public void changeStatusToApproved(int userId)
	{
		userRepo.updateStatusToApproved(userId);
	}

	public List<ViewUsersDTO> fetchRecords()
	{
		List<ViewUsersDTO> pendingUsers = userRepo.fetchAllUsers();

		return pendingUsers;
	}

	public List<ViewUsersDTO> loadUsersByAllowedView(String allowedView)
	{
		switch (allowedView) {
		case "ALL_USERS":
			return userRepo.fetchAllUsers();
		case "PENDING_USERS":
			return userRepo.fetchPendingUsers();
		case "HR_USERS":
			return userRepo.fetchEmployees();
		}
		return null;
	}

	public void makeRequest(int userId, int departIds)
	{
		userRepo.makeRequest(userId, departIds);
	}

	public void approveRequest(int userId, int departId)
	{
		userRepo.approveRequest(userId, departId);
	}

	public void updateStatusToApproved(int empId)
	{
		userRepo.updateStatusToApproved(empId);
	}

	public void deleteEntryFromDepartReq(int empId, int departId)
	{
		userRepo.deleteEntryFromDepartReq(empId, departId);
	}

	public List<ViewUsersDTO> fetchPendingUsers()
	{
		List<ViewUsersDTO> pendingUsersList = userRepo.fetchPendingUsers();
		return pendingUsersList;
	}

	public UserUpdateDTO updateEmployee(int empId)
	{
		UserUpdateDTO updateEmployee = userRepo.updateEmployee(empId);
		return updateEmployee;
	}

}

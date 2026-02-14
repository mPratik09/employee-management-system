package com.employee.management.system.dto.auth;

import java.util.List;

import com.employee.management.system.entity.Department;
import com.employee.management.system.entity.Status;

public class UserAuthDTO
{
	private int id;
	private String email;
	private String password;
	private Status status;

	private List<Department> roles;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}

	public List<Department> getRoles()
	{
		return roles;
	}

	public void setRoles(List<Department> roles)
	{
		this.roles = roles;
	}

	@Override
	public String toString()
	{
		return "\nUserAuthDTO [id=" + id + ", email=" + email + ", status=" + status + ", roles=" + roles + "]";
	}

}

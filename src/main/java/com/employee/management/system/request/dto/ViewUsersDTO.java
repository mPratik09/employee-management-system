package com.employee.management.system.request.dto;

import com.employee.management.system.entity.Status;

public class ViewUsersDTO
{
	private int id;
	private int empId;
	private String firstName;
	private String lastName;
	private String email;
	private String department;
	private Status status;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getEmpId()
	{
		return empId;
	}

	public void setEmpId(int empId)
	{
		this.empId = empId;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment(String department)
	{
		this.department = department;
	}

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}

	@Override
	public String toString()
	{
		return "\nDepartmentRequestDTO [id=" + id + ", empId=" + empId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", department=" + department + ", status=" + status + "]";
	}

}

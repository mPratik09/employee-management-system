package com.employee.management.system.request.dto;

public class DepartmentRequestDTO
{
	private String departCode;
	private String department;

	public String getDepartCode()
	{
		return departCode;
	}

	public void setDepartCode(String departCode)
	{
		this.departCode = departCode;
	}

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment(String department)
	{
		this.department = department;
	}

	@Override
	public String toString()
	{
		return "DepartmentRequestDTO [departCode=" + departCode + ", department=" + department + "]";
	}

}

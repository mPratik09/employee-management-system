package com.employee.management.system.entity;

public class Department
{
	private int id;
	private String departCode;
	private String department;
	private String landingView;
	private String allowedView;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

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

	public String getAllowedView()
	{
		return allowedView;
	}

	public void setAllowedView(String allowedView)
	{
		this.allowedView = allowedView;
	}

	@Override
	public String toString()
	{
		return "Department [id=" + id + ", departCode=" + departCode + ", department=" + department + ", landingView="
				+ landingView + ", allowedView=" + allowedView + "]";
	}

}

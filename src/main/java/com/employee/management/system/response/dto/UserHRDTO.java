package com.employee.management.system.response.dto;

public class UserHRDTO
{
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String contactNum;
	private String department;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
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

	public String getContactNum()
	{
		return contactNum;
	}

	public void setContactNum(String contactNum)
	{
		this.contactNum = contactNum;
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
		return "\nUserHRDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", contactNum=" + contactNum + ", department=" + department + "]";
	}

}

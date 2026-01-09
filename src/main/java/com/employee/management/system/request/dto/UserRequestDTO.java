package com.employee.management.system.request.dto;

public class UserRequestDTO
{
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String reEnterPassword;
	private String contactNum;
	private String status;
	private String role;

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

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getReEnterPassword()
	{
		return reEnterPassword;
	}

	public void setReEnterPassword(String reEnterPassword)
	{
		this.reEnterPassword = reEnterPassword;
	}

	public String getContactNum()
	{
		return contactNum;
	}

	public void setContactNum(String contactNum)
	{
		this.contactNum = contactNum;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	@Override
	public String toString()
	{
		return "UserRequestDTO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", reEnterPassword=" + reEnterPassword + ", contactNum=" + contactNum + ", status="
				+ status + ", role=" + role + "]";
	}

}

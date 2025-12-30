package com.employee.management.system.entity;

import java.time.LocalDateTime;

public class User
{
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String contactNum;
	private String status;
	private String role;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

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

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
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

	public LocalDateTime getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt)
	{
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt()
	{
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt)
	{
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString()
	{
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", contactNum=" + contactNum + ", status=" + status + ", role=" + role + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

}

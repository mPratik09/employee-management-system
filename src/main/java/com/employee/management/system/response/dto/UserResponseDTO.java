package com.employee.management.system.response.dto;

import java.time.LocalDateTime;

public class UserResponseDTO
{

	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String contactNum;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

//	public UserResponseDTO(Integer id, String firstName, String lastName, String email, String contactNum)
//	{
//		super();
//		this.id = id;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.email = email;
//		this.contactNum = contactNum;
//	}

//	do getter-setters require here?? specially getters??

	public Integer getId()
	{
		return id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public String getContactNum()
	{
		return contactNum;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public void setContactNum(String contactNum)
	{
		this.contactNum = contactNum;
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
		return "UserResponseDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", contactNum=" + contactNum + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}

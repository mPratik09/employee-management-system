package com.employee.management.system.response.dto;

public class UserResponseDTO
{

	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String contactNum;

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

	@Override
	public String toString()
	{
		return "UserResponseDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", contactNum=" + contactNum + "]";
	}

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

}

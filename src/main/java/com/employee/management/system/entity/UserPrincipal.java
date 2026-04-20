package com.employee.management.system.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.employee.management.system.dto.auth.UserAuthDTO;

public class UserPrincipal implements UserDetails
{

	private static final Logger log = LoggerFactory.getLogger(UserPrincipal.class);

	private UserAuthDTO userAuthDto;

	public UserPrincipal()
	{
	}

	public UserPrincipal(UserAuthDTO userAuthDto)
	{
		this.userAuthDto = userAuthDto;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Department dept : userAuthDto.getRoles())
		{
			authorities.add(new SimpleGrantedAuthority("ROLE_" + dept.getDepartment().toUpperCase()));
		}

		return authorities;
	}

	@Override
	public String getPassword()
	{
		return userAuthDto.getPassword();
	}

	@Override
	public String getUsername()
	{
		return userAuthDto.getEmail();
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		return true;
	}

	public Status getStatus()
	{
		return userAuthDto.getStatus();
	}

	public List<Department> getDepartments()
	{
		return userAuthDto.getRoles();
	}

	public int getUserId()
	{
		return userAuthDto.getId();
	}

	@Override
	public String toString()
	{
		return "UserPrincipal [userAuthDto=" + userAuthDto + "]";
	}

}

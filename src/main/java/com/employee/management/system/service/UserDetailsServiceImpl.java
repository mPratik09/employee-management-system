package com.employee.management.system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.employee.management.system.dto.auth.UserAuthDTO;
import com.employee.management.system.entity.UserPrincipal;
import com.employee.management.system.repo.AuthRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{

	private static Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private AuthRepo authRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{

		UserAuthDTO userAuthDto = authRepo.findUserByEmail(username);
		log.info("UserAuthDTO:: {}", userAuthDto);
		if (userAuthDto == null)
		{
			throw new UsernameNotFoundException("User not found");
		}

		return new UserPrincipal(userAuthDto);
	}

}

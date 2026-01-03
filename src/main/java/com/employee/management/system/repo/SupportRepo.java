package com.employee.management.system.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.employee.management.system.entity.User;

@Repository
public class SupportRepo
{

	private static final Logger log = LoggerFactory.getLogger(SupportRepo.class);

	@Value("${ASSIGN_ROLE}")
	private String assign_role;

	@Autowired
	JdbcTemplate jdbcTemplate;

	public User roleAssign(int id)
	{

		jdbcTemplate.update(assign_role, "ASSIGNED", "ADMINN", id);
		return null;
	}

}

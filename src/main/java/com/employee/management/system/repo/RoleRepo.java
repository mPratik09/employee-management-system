package com.employee.management.system.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepo
{

	private static final Logger log = LoggerFactory.getLogger(RoleRepo.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Value("${GET_REQUEST_FOR_ROLE}")
	private String get_request_for_role;

	public void roleRequest(String departCode)
	{
		log.info("Department codes:\t{}", departCode);
	}

}

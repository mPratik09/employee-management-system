package com.employee.management.system.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class StatusRepo
{
	private static final Logger log = LoggerFactory.getLogger(StatusRepo.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private StatusRepo statusRepo;

	@Value("${FETCH_STATUS}")
	private String fetch_status;

	public String checkStatus(Integer id)
	{
		String status = jdbcTemplate.queryForObject(fetch_status, String.class, id);

		return status;
	}

	public String checkUpdatedStatus(int userId)
	{
		String updatedStatus = statusRepo.checkStatus(userId);
		log.info("Check Updated Status:\t{}", updatedStatus);

		return updatedStatus;
	}

}

package com.employee.management.system.repo;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.employee.management.system.entity.User;

@Repository
public class AuthRepo
{

	private static final Logger log = LoggerFactory.getLogger(AuthRepo.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Value("${FIND_BY_EMAILID}")
	private String find_by_emailid;

	@Value("${EMPLOYEE_LOGIN}")
	private String employee_login;

	@Value("${FETCH_STATUS}")
	private String fetch_status;

	public Optional<User> findByUserEmail(String email)
	{

		StringBuilder findByEmailid = new StringBuilder(find_by_emailid);
		findByEmailid.append("?");

		log.info("SQL query - FIND_BY_EMAILID: {} || email: {}", findByEmailid, email);

//		TODO: fetch the whole user object insted of only password
		try
		{
			User user = jdbcTemplate.queryForObject(findByEmailid.toString(), new BeanPropertyRowMapper<>(User.class),
					email);
			return Optional.of(user);
		} catch (EmptyResultDataAccessException e)
		{
			return Optional.empty();
		}

	}

	public String checkDepartment(int userId)
	{
		List<String> queryForList = jdbcTemplate.queryForList(employee_login, String.class, userId);

		log.info("List for Departments:{}", queryForList);
		return "ASSIGNED";
	}

	public String checkStatus(Integer id)
	{
		String status = jdbcTemplate.queryForObject(fetch_status, String.class, id);
		return status;
	}

}

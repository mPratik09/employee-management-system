package com.employee.management.system.repo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.employee.management.system.dto.auth.UserAuthDTO;
import com.employee.management.system.entity.Department;
import com.employee.management.system.entity.Status;

@Repository
public class AuthRepo
{

	private static final Logger log = LoggerFactory.getLogger(AuthRepo.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Value("${FIND_BY_EMAILID}")
	private String find_by_emailid;

	@Value("${AUTHENTICATE_USER}")
	private String authenticate_user;

	@Value("${EMPLOYEE_LOGIN}")
	private String employee_login;

	@Value("${FETCH_STATUS}")
	private String fetch_status;

	public UserAuthDTO findUserByEmail(String email)
	{

		log.info("SQL QUERY - FIND_BY_EMAILID: {} || email: {}", authenticate_user, email);

//		TODO: fetch the whole user object insted of only password
		try
		{
//			UserAuthDTO user = jdbcTemplate.queryForObject(authenticate_user,
//					new BeanPropertyRowMapper<>(UserAuthDTO.class), email);
//			return user;

			return jdbcTemplate.query(authenticate_user, new Object[] { email }, rs ->
			{

				UserAuthDTO user = null;
				List<Department> departments = new ArrayList<>();

				while (rs.next())
				{

					if (user == null)
					{
						user = new UserAuthDTO();
						user.setId(rs.getInt("id"));
						user.setEmail(rs.getString("email"));
						user.setPassword(rs.getString("password"));
						user.setStatus(Status.valueOf(rs.getString("status")));
					}

					int deptId = rs.getInt("dept_id");
					if (deptId > 0)
					{
						Department dept = new Department();
						dept.setId(deptId);
						dept.setDepartment(rs.getString("department"));
						dept.setLandingPage(rs.getString("landing_page"));
						dept.setAllowedView(rs.getString("roles"));

						departments.add(dept);
					}
				}

				if (user != null)
				{
					user.setRoles(departments);
				}

				return user;
			});

		} catch (EmptyResultDataAccessException e)
		{
			log.info("USER NOT FOUND WITH EMAIL: {}", email);
			throw new UsernameNotFoundException("User not found eith email {}" + email);
		}

	}

	public String checkDepartment(int userId)
	{
		List<String> queryForList = jdbcTemplate.queryForList(employee_login, String.class, userId);

		log.info("List for Departments:{}", queryForList);
		return "ASSIGNED";
	}

}

package com.employee.management.system.repo;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.employee.management.system.entity.User;
import com.employee.management.system.mapper.UserMapper;
import com.employee.management.system.request.dto.DepartmentRequestDTO;
import com.employee.management.system.response.dto.UserResponseDTO;

@Repository
public class UserRepo
{

	private static final Logger log = LoggerFactory.getLogger(UserRepo.class);

	@Value("${PERSIST_USER}")
	private String persist_user;

	@Value("${FETCH_USER}")
	private String fetch_user;

	@Value("${FETCH_USERS}")
	private String fetch_users;

	@Value("${FETCH_PENDING_USERS}")
	private String fetch_pending_users;

	@Value("${ROLE_REQUEST}")
	private String role_request;

	@Value("${ROLE_ASSIGN}")
	private String role_assign;

	@Value("${CHANGE_STATUS}")
	private String change_status;

	@Value("${EMPLOYEES_LIST_FOR_HR}")
	private String employees_list_for_hr;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	UserMapper userMapper;

	public UserResponseDTO saveUser(User user)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection ->
		{
			PreparedStatement ps = connection.prepareStatement(persist_user, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail().toLowerCase());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getContactNum());
			return ps;
		}, keyHolder);

//		sets generated id into user ONJECT from database
		Integer userId = keyHolder.getKey().intValue();
		user.setId(userId);

//		fetches row from database by id 
		User savedUser = jdbcTemplate.queryForObject(fetch_user, new BeanPropertyRowMapper<>(User.class), userId);

		return userMapper.userDtoMapper(savedUser);

	}

	public List<User> getPendingUsers()
	{
		StringBuilder findByStatus = new StringBuilder(fetch_pending_users);

		log.info("SQL query - FETCH_PENDING_USERS:\t{}", findByStatus);

		List<User> pendingUsers = jdbcTemplate.query(fetch_pending_users, new BeanPropertyRowMapper<>(User.class));
		return pendingUsers;
	}

	public void roleRequest(int userId, int departId)
	{
		jdbcTemplate.update(role_request, userId);
//		jdbcTemplate.update(role_assign, );
		return;
	}

	public void updateStatus(int userId)
	{
		jdbcTemplate.update(change_status, userId);
	}

	public List<User> fetchAllUsers()
	{
		List<User> pendingUsers = jdbcTemplate.query(fetch_users, new BeanPropertyRowMapper<>(User.class));

		log.info("All Users:\t{}", pendingUsers);

		return pendingUsers;

	}

	public List<DepartmentRequestDTO> fetchPendingUsers()
	{
		List<DepartmentRequestDTO> pendingUsers = jdbcTemplate.query(fetch_pending_users,
				new BeanPropertyRowMapper<>(DepartmentRequestDTO.class));
		log.info("Fethced Pending Users:\t" + pendingUsers);

		return pendingUsers;
	}

	public List<User> fetchEmployees()
	{
		List<User> employeesList = jdbcTemplate.query(employees_list_for_hr, new BeanPropertyRowMapper<>(User.class));
		log.info("EMployee for HR: {}", employeesList);

		return employeesList;
	}

}

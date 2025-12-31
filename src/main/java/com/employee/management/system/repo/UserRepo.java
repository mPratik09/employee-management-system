package com.employee.management.system.repo;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.employee.management.system.entity.User;
import com.employee.management.system.mapper.UserMapper;
import com.employee.management.system.response.dto.UserResponseDTO;

@Repository
public class UserRepo
{

	private static final Logger log = LoggerFactory.getLogger(UserRepo.class);

	@Value("${PERSIST_USER}")
	private String persist_user;

	@Value("${FETCH_USER}")
	private String fetch_user;

	@Value("${FIND_BY_EMAILID}")
	private String find_by_emailid;

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
			ps.setString(3, user.getEmail());
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

	public Optional<User> findByUserEmail(String email)
	{

		StringBuilder findByEmailid = new StringBuilder(find_by_emailid);
		findByEmailid.append("?");

		log.info("find by email query:\t" + findByEmailid);

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

}

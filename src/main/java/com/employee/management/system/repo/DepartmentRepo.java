package com.employee.management.system.repo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.employee.management.system.entity.Department;

@Repository
public class DepartmentRepo
{

	private static final Logger log = LoggerFactory.getLogger(DepartmentRepo.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Value("${FETCH_LINKED_DEPARTMENT}")
	private String fetch_linked_department;

	public List<Department> fetchLinkedDepartments(int userId)
//	public List<Map<String, Object>> fetchLinkedDepartments(int userId)
	{
		List<Department> linkedDepartmnets = jdbcTemplate.query(fetch_linked_department,
				new BeanPropertyRowMapper<>(Department.class), userId);
//		List<Map<String, Object>> linkedDepartmnets = jdbcTemplate.queryForList(fetch_linked_department, userId,
//				String.class);
		log.info("Associated Departments: {}", linkedDepartmnets);

		return linkedDepartmnets;
	}

}

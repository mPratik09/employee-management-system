package com.employee.management.system.repo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.employee.management.system.config.DepartmentContext;

@Repository
public class DepartmentRepo
{

	private static final Logger log = LoggerFactory.getLogger(DepartmentRepo.class);

	@Value("${FETCH_LANDING_PAGE}")
	private String fetch_landing_page;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Value("${FETCH_LINKED_DEPARTMENT}")
	private String fetch_linked_department;

	public List<String> fetchLinkedDepartments(int userId)
//	public List<Map<String, Object>> fetchLinkedDepartments(int userId)
	{
		List<String> linkedDepartmnets = jdbcTemplate.queryForList(fetch_linked_department, String.class, userId);
		log.info("Associated Departments: {}", linkedDepartmnets);

		return linkedDepartmnets;
	}

//	UNUSED METHOD	A-2
	public String getLandingViewForUser(int userId)
	{
		String landingPage = jdbcTemplate.queryForObject(fetch_landing_page, String.class, userId);
		log.info("Landing Page: {}", landingPage);

		return landingPage;
	}

//	this mthod retrieved "alloed users" and "landing page"
	public DepartmentContext fetchDepartmentContext(int userId)
	{
		DepartmentContext departmentContext = jdbcTemplate.queryForObject(fetch_landing_page,
				new BeanPropertyRowMapper<>(DepartmentContext.class), userId);

		log.info("Fetched Department:\t{}", departmentContext);

		return departmentContext;
	}

}

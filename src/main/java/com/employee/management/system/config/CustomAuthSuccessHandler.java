package com.employee.management.system.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.employee.management.system.entity.Department;
import com.employee.management.system.entity.Status;
import com.employee.management.system.entity.UserPrincipal;
import com.employee.management.system.service.JWTService;

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler
{

	@Autowired
	private JWTService jwtService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException
	{
		UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

		String token = jwtService.generateToken(principal.getUsername());

		Cookie cookie = new Cookie("JWT_TOKEN", token);
		cookie.setHttpOnly(true);
		cookie.setSecure(false);
		cookie.setPath("/");
		cookie.setMaxAge(60 * 10);

		response.addCookie(cookie);

		Status status = principal.getStatus();

		if (status == Status.UNASSIGNED)
		{
			response.sendRedirect(request.getContextPath() + "/makeRequest");
			return;
		}

		if (status == Status.PENDING)
		{
			request.getSession().setAttribute("user", principal);
			response.sendRedirect(request.getContextPath() + "/reqPending");
			return;
		}

		if (status == Status.REJECTED)
		{
			response.sendRedirect("/showLogin?rejected");
			return;
		}

		if (status == Status.APPROVED)
		{
			List<Department> departments = principal.getDepartments();

			if (departments == null || departments.isEmpty())
			{
				response.sendRedirect(request.getContextPath() + "/errorPage");
				return;
			}

			if (departments.size() > 1)
			{
				response.sendRedirect(request.getContextPath() + "/url/" + "chooseDepartment");
				return;
			}

			Department dept = departments.get(0);

			response.sendRedirect(request.getContextPath() + "/url/" + dept.getLandingPage());
		}

	}
}

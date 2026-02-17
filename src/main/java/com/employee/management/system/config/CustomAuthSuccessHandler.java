package com.employee.management.system.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.employee.management.system.entity.Department;
import com.employee.management.system.entity.Status;
import com.employee.management.system.entity.UserPrincipal;

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler
{

	private static final Logger log = LoggerFactory.getLogger(CustomAuthSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException
	{
		UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

		Status status = principal.getStatus();

		if (status == Status.UNASSIGNED)
		{
			response.sendRedirect(request.getContextPath() + "/makeRequest");
			return;
		}

		if (status == Status.PENDING)
		{
			response.sendRedirect("/reqPending");
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
				response.sendRedirect("/chooseDepartment");
				return;
			}

			Department dept = departments.get(0);

			response.sendRedirect(request.getContextPath() + "/" + dept.getLandingPage());

		}

//		log.info("11. Authentication:\t{}", authentication);
//		log.info("22. Authentication:\t{}", authentication.getAuthorities());
//		HttpSession session = request.getSession();
//
//		// 1️⃣ Get logged-in username (email)
//		String email = authentication.getName();
//
//		// 2️⃣ Load full User object from DB
//		UserAuthDTO user = authService.getByUserEmail(email);
//
//		// 3️⃣ Store user in session
//		session.setAttribute("user", user);
//
//		// 4️⃣ Switch based on status
//		switch (user.getStatus()) {
//
//		case UNASSIGNED:
//			response.sendRedirect(request.getContextPath() + "/makeRequest");
//			break;
//
//		case PENDING:
//			response.sendRedirect(request.getContextPath() + "/reqPending");
//			break;
//
//		case REJECTED:
//			response.sendRedirect(request.getContextPath() + "/login?rejected");
//			break;
//
//		case APPROVED:
//
//			DepartmentContext roleBasedRedirect = departService.roleBasedRedirect(user.getId());
//
//			// Store required values in session
//			session.setAttribute("allowedView", roleBasedRedirect.getAllowedView());
//			log.info("LAnding PAge:\t{}", roleBasedRedirect.getLandingPage());
//			response.sendRedirect(request.getContextPath() + roleBasedRedirect.getLandingPage());
//			break;
//
//		default:
//			response.sendRedirect(request.getContextPath() + "/login");
//		}

	}
}

package com.employee.management.system.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.employee.management.system.config.SecurityConfig;
import com.employee.management.system.entity.UserPrincipal;
import com.employee.management.system.service.JWTService;
import com.employee.management.system.service.UserDetailsServiceImpl;

@Component
public class JwtFilter extends OncePerRequestFilter
{

	private static Logger log = LoggerFactory.getLogger(JwtFilter.class);

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private BlacklistService blacklistService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException
	{

		SecurityContextHolder.clearContext();

		String authHeader = request.getHeader("Authorization");

		String token = null;
		String username = null;

		if (authHeader != null && authHeader.startsWith("Bearer "))
		{
			token = authHeader.substring(7);
		}

		if (token == null && request.getCookies() != null)
		{
			for (Cookie cookie : request.getCookies())
			{
				if ("JWT_TOKEN".equals(cookie.getName()))
				{
					token = cookie.getValue();
					break;
				}
			}
		}

		try
		{
			if (token != null)
			{
				if (blacklistService.isBlacklisted(token))
				{
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					response.getWriter().write("Token is logged out");
					return;
				}

				username = jwtService.extractUsername(token);
				log.info("USERNAME: {}", username);
			}
		} catch (Exception e)
		{
			log.error("Invalid token. Clearing cookie.");

			Cookie cookie = new Cookie("JWT_TOKEN", null);
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);

			filterChain.doFilter(request, response);
			return;
		}

		Cookie[] cookies = request.getCookies();

		if (cookies != null)
		{
			for (Cookie c : cookies)
			{
				log.info("Cookie: Name: {} || Value: {}", c.getName(), c.getValue());
			}
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
		{

			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			if (jwtService.isTokenValid(token, userDetails.getUsername()))
			{

				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());

				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}

		filterChain.doFilter(request, response);
	}

	public String getTokenFromCookie(HttpServletRequest request)
	{
		if (request.getCookies() != null)
		{
			for (Cookie cookie : request.getCookies())
			{
				if ("JWT_TOKEN".equals(cookie.getName()))
				{
					return cookie.getValue();
				}
			}
		}
		return null;
	}

}
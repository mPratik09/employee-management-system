package com.employee.management.system.filter;

public interface BlacklistService
{
	void blacklistToken(String token);

	boolean isBlacklisted(String token);
}

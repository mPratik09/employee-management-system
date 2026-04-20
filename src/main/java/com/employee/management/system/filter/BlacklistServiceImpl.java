package com.employee.management.system.filter;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class BlacklistServiceImpl implements BlacklistService
{

	private Set<String> blacklist = ConcurrentHashMap.newKeySet();

	@Override
	public void blacklistToken(String token)
	{

		blacklist.add(token);

	}

	@Override
	public boolean isBlacklisted(String token)
	{
		return blacklist.contains(token);
	}

}

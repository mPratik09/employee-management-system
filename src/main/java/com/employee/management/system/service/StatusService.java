package com.employee.management.system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.system.repo.StatusRepo;

@Service
public class StatusService
{
	private static final Logger log = LoggerFactory.getLogger(StatusService.class);

	@Autowired
	private StatusRepo statusRepo;

	public String checkStatus(int id)
	{
		String checkStatus = statusRepo.checkStatus(id);
		return checkStatus;

	}

}

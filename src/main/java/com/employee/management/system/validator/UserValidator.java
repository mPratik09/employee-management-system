package com.employee.management.system.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.employee.management.system.entity.User;

public class UserValidator
{

	private static final Logger log = LoggerFactory.getLogger(UserValidator.class);

	public boolean isValidEmail(String email)
	{
		String regex = "^(?=.*[a-z])(?!.*[._-]{2})[a-zA-Z][a-zA-Z0-9._-]*$";

		String[] localpart = email.split("@");

		if (localpart[0].matches(regex) && email.endsWith("@nebula.co.in"))
		{
			log.info("Valid Email id..");
			return true;
		}

		log.info("Invalid Email id..");
		return false;
	}

	public boolean isValidPassword(String password)
	{

		String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,20}$";
		log.info("Password:\t" + password);
		if (password.matches(regex))
		{
			log.info("Valid Password..");
			return true;
		}

		log.info("Password must contain atleast 1 uppercase, lowercase, digit and special character..");
		return false;
	}

	public boolean isValidContactNum(String contactNum)
	{

		String regex = "[6-9][0-9]{9}$";

		if (contactNum.matches(regex))
		{
			log.info("Valid contact number..");
			return true;
		}
		log.info("Invalid contact number..");
		return false;
	}

	public boolean isUserValid(User user)
	{
		if ((isValidEmail(user.getEmail())) && (isValidContactNum(user.getContactNum())))
		{
			log.info("Valid User..");
			return true;
		}
		log.info("Invalid User..");
		return false;
	}
}

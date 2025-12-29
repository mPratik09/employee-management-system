package com.employee.management.system.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;

import com.employee.management.system.entity.User;
import com.employee.management.system.request.dto.UserRequestDTO;
import com.employee.management.system.response.dto.UserResponseDTO;
import com.employee.management.system.validator.UserValidator;

@Component
public class UserMapper
{

	private static Logger log = LoggerFactory.getLogger(UserMapper.class);

	public User userMapper(UserRequestDTO userRequestDTO)
	{
		User user = new User();
		UserValidator userValidator = new UserValidator();

		if (!userRequestDTO.getPassword().equals(userRequestDTO.getReEnterPassword()))
		{
			log.info("Passwords does not match..");
			throw new IllegalArgumentException("PASSWORDS DOES NOT MATCH..");
		} else if (!userValidator.isValidPassword(userRequestDTO.getPassword()))
		{
			log.info("Password is not strong enough...");
			throw new IllegalArgumentException("PASSWORD IS NOT STRONG ENOUGH..");
		}

		user.setFirstName(userRequestDTO.getFirstName());
		user.setLastName(userRequestDTO.getLastName());
		user.setEmail(userRequestDTO.getEmail());

		user.setPassword(
				PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(userRequestDTO.getPassword()));
		user.setContactNum(userRequestDTO.getContactNum());

		return user;
	}

//	public static UserResponseDTO userDtoMapper(User user)
//	{
//		UserResponseDTO userDto = new UserResponseDTO(user.getId(), user.getFirstName(), user.getLastName(),
//				user.getEmail(), user.getContactNum());
//		return userDto;
//	}

	public UserResponseDTO userDtoMapper(User user)
	{
		UserResponseDTO userResponseDto = new UserResponseDTO();

		userResponseDto.setId(user.getId());
		userResponseDto.setFirstName(user.getFirstName());
		userResponseDto.setLastName(user.getLastName());
		userResponseDto.setEmail(user.getEmail());
		userResponseDto.setContactNum(user.getContactNum());
		userResponseDto.setCreatedAt(user.getCreatedAt());
		userResponseDto.setUpdatedAt(user.getUpdatedAt());

		return userResponseDto;
	}

}

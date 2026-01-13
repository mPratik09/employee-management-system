package com.employee.management.system.mapper;

import com.employee.management.system.entity.Department;
import com.employee.management.system.request.dto.DepartmentRequestDTO;
import com.employee.management.system.response.dto.DepartmentResponseDTO;

public class DepartmentMapper
{

// @Autowired
//	Department department;

	public Department departmentMapper(DepartmentRequestDTO departmentReqDTO)
	{

		Department department = new Department();

		department.setDepartment(departmentReqDTO.getDepartment());
		department.setDepartCode(departmentReqDTO.getDepartCode());

		return department;
	}

	public DepartmentResponseDTO departmentDtoMapper(Department department)
	{
		DepartmentResponseDTO departmentRespDto = new DepartmentResponseDTO();

		departmentRespDto.setDepartCode(department.getDepartCode());
		departmentRespDto.setDepartment(department.getDepartment());

		return departmentRespDto;
	}
}

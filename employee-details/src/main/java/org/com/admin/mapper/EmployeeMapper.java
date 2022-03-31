package org.com.admin.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.com.admin.dto.EmployeeDTO;
import org.com.admin.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	EmployeeDTO toEmployeeDTO(Employee emp);
	List<EmployeeDTO> toEmployeeDTO(List<Employee> emps);
	Employee toEmployee(EmployeeDTO empDTO);
	

}

package org.com.extraction.mapper;

import java.util.List;

import org.com.extraction.dto.ExtractionDTO;
import org.com.extraction.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExtractionMapper {
	ExtractionDTO toExtractionDTO(Employee emp);
	List<ExtractionDTO> toExtractionDTOList(List<Employee> emps);
	Employee toEmployee(ExtractionDTO empDTO);

}

package org.com.admin.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.com.admin.dto.EmployeeDTO;
import org.com.admin.model.Employee;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-29T08:23:21+0530",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDTO toEmployeeDTO(Employee emp) {
        if ( emp == null ) {
            return null;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setEmployeeId( emp.getEmployeeId() );
        employeeDTO.setUserName( emp.getUserName() );
        employeeDTO.setPassword( emp.getPassword() );
        employeeDTO.setCategory( emp.getCategory() );
        employeeDTO.setName( emp.getName() );
        employeeDTO.setEmailAddress( emp.getEmailAddress() );
        employeeDTO.setPhoneNumber( emp.getPhoneNumber() );

        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> toEmployeeDTO(List<Employee> emps) {
        if ( emps == null ) {
            return null;
        }

        List<EmployeeDTO> list = new ArrayList<EmployeeDTO>( emps.size() );
        for ( Employee employee : emps ) {
            list.add( toEmployeeDTO( employee ) );
        }

        return list;
    }

    @Override
    public Employee toEmployee(EmployeeDTO empDTO) {
        if ( empDTO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setEmployeeId( empDTO.getEmployeeId() );
        employee.setUserName( empDTO.getUserName() );
        employee.setPassword( empDTO.getPassword() );
        employee.setCategory( empDTO.getCategory() );
        employee.setName( empDTO.getName() );
        employee.setEmailAddress( empDTO.getEmailAddress() );
        employee.setPhoneNumber( empDTO.getPhoneNumber() );

        return employee;
    }
}

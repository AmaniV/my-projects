package org.com.admin.service;

import java.util.List;

import org.com.admin.model.Employee;

public interface EmployeeService {
	
	public Employee saveEmployee(Employee employee);

	public List<Employee> getAllEmployee();

	public Employee findEmployee(Long employeeNo);


}

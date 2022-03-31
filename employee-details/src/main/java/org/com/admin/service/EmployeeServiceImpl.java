package org.com.admin.service;

import java.util.List;

import javax.transaction.Transactional;

import org.com.admin.dao.EmployeeDao;
import org.com.admin.model.Employee;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import static org.com.admin.util.EmployeeDetailsConstants.SERVICE_LOG;;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService { 

	
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Employee saveEmployee(Employee employee) {
		SERVICE_LOG.info("Employee save started");
		return employeeDao.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		SERVICE_LOG.info("Employee fetch started");
		return employeeDao.findAll();
	}
	
	@Override
	public Employee findEmployee(Long employeeNo) {
		SERVICE_LOG.info("Finding Employee started");
		return employeeDao.getOne(employeeNo);
	}
}

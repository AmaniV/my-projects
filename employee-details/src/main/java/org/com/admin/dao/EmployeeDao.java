package org.com.admin.dao;


import org.com.admin.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("employeeDao")
public interface EmployeeDao extends JpaRepository<Employee, Long>{

}

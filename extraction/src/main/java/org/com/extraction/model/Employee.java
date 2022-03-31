package org.com.extraction.model;

/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;*/
/*import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity*/
public class Employee {
	
	
	private long employeeId;
	
	private String userName;
	
	private String password;
	private String category;
	private String name;
	
	private String emailAddress;
	
	private String phoneNumber;
	
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public Employee(long employeeId,String userName, String password, String category, String name, String emailAddress, String phoneNumber) {
		this.employeeId=employeeId;
		this.userName = userName;
		this.password = password;
		this.category = category;
		this.name = name;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
	}
	
	public Employee() {
		
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", userName=" + userName + ", password=" + password
				+ ", category=" + category + ", name=" + name + ", emailAddress=" + emailAddress + ", phoneNumber=" + phoneNumber + "]";
	}
	

}

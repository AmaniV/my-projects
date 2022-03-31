package com.spring.batch.vo;

import lombok.Data;

@Data
public class SprinBatchInputVO {
	private String firstName;
	private String lastName;
	
	public SprinBatchInputVO() {
		
	}
	
	public SprinBatchInputVO(String firstname, String lastname) {
		super();
		firstName = firstname;
		lastName = lastname;
		
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstname(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return "Details [firstName=" + firstName + ", lastName=" + lastName + "]";
	}


}

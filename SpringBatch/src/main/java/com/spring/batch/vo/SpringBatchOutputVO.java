package com.spring.batch.vo;

import lombok.Data;

@Data
public class SpringBatchOutputVO {
private String fullName;
	
	public SpringBatchOutputVO() {
		
	}
	
	public SpringBatchOutputVO(String fullname) {
		super();
		fullName = fullname;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	@Override
	public String toString() {
		return "Details [Name=" + fullName + "]";
	}

}

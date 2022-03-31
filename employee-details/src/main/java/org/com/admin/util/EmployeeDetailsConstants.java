package org.com.admin.util;

import org.com.admin.controller.EmployeeController;
import org.com.admin.service.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EmployeeDetailsConstants {
	
	public static final int SUCCESS_CODE = 200;
	public static final int SERVICE_NOT_AVAILABLE =500;
	public static final String SUCCESS_MSG ="Success";
	public static final String SERVICE_NOT_AVAILABLE_ERROR_MSG ="Service Unavailable";
	public static final String BAD_REQUEST_MSG = "Bad Request";
	public static final String EMPLOYEE_ENDPOINT = "/employee";
	public static final String ERROR_MSG = "Sorry! No Accounts Available in Db!";
	public static final String ERROR_MSG_DISPLAY = "Sorry! No Such Account ID exists in Db!";
	public static final String HEALTHCHECK_ENDPOINT = "/healthcheck1";
	public static final String GET ="GET";
	public static final Logger SERVICE_LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	public static final Logger CONTROLLER_LOG = LoggerFactory.getLogger(EmployeeController.class);
	public static int CODE=0;
	
	
	

}

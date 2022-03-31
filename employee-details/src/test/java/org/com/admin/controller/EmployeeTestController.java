package org.com.admin.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.com.admin.model.Employee;
import org.com.admin.service.EmployeeService;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.mockito.Mockito;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value=EmployeeController.class)
@WithMockUser
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class EmployeeTestController {
	//private Logger log = LoggerFactory.getLogger(EmployeeTestController.class);

	@Autowired
	private MockMvc mockMvc;

	
	@MockBean
	private EmployeeService employeeTestService;

	Employee mockEmployee1 = new Employee(12789, "qwerdsert234", "abcdeft@202aV", "Accountant", "qwerdsert", "qwerdsert.yrt@gmail.com","9121156438");
	Employee mockEmployee2 = new Employee(123456, "qwe234", "abcft@176aV", "Specialist", "aerwetsr", "aerwetsr.tert@gmail.com","9121157448");

	List<Employee> mockEmployeeList = new ArrayList<>(Arrays.asList(mockEmployee1,mockEmployee2));

	String employeeJson = "{\"employeeId\": 12789 ,\"userName\":\"qwerdsert234\",\"password\": \"abcdeft@202aV\",\"category\": \"Accountant\", \"name\":\"qwerdsert\",\"emailAddress\": \"qwerdsert.yrt@gmail.com\",\"phoneNumber\": \"9121156438\"}";

	@Test
	public void retriveEmployeeDetails() throws Exception {
		Mockito.when(employeeTestService.getAllEmployee()).thenReturn((List<Employee>) mockEmployeeList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/api/v1/employee")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult res = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse reply = res.getResponse();
		assertEquals(200,reply.getStatus());

	}
	
	
	@Test
	public void createEmployeeDetails() throws Exception{
		Employee mockEmployee = new Employee(12789, "qwerdsert234", "abcdeft@202aV", "Accountant", "qwerdsert", "qwerdsert.yrt@gmail.com","9121156438");
	  
	  Mockito.when(employeeTestService.saveEmployee(Mockito.any(Employee.class))).thenReturn(mockEmployee); 
	  RequestBuilder requestBuilder = MockMvcRequestBuilders
			  .post("/api/v1/employee")
			  .accept(MediaType.APPLICATION_JSON).content(employeeJson)
			  .contentType(MediaType.APPLICATION_JSON); 
	  MvcResult result =mockMvc.perform(requestBuilder).andReturn(); 
	  MockHttpServletResponse response = result.getResponse();
	  assertEquals(200,response.getStatus()); 
	 }
	 
		

}


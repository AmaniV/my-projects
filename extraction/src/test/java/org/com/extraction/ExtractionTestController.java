package org.com.extraction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

//@RunWith(SpringRunner.class)
//@WebMvcTest(value=ExtractionTestController.class)
//@WithMockUser
public class ExtractionTestController {
	@Autowired
	private MockMvc mockMvc;
	
	//@MockBean
	//private SprinDataloadService sprinDataloadService;
	
	//Employee mockEmployee = new Employee(1,"Amani123","Mar@2022AV","Senior Consultant","Amani","vodnala.amani@gmail.com","9100656438");

}

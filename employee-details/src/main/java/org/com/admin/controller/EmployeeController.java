package org.com.admin.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.validation.Valid;

import org.com.admin.service.EmployeeService;
import org.com.admin.util.EmployeeDetailsConstants;
import org.com.admin.dto.EmployeeDTO;
import org.com.admin.mapper.EmployeeMapper;
import org.com.admin.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.com.admin.util.EmployeeDetailsConstants.HEALTHCHECK_ENDPOINT;
import static org.com.admin.util.EmployeeDetailsConstants.EMPLOYEE_ENDPOINT;
import static org.com.admin.util.EmployeeDetailsConstants.SERVICE_NOT_AVAILABLE;
import static org.com.admin.util.EmployeeDetailsConstants.SUCCESS_CODE;
import static org.com.admin.util.EmployeeDetailsConstants.BAD_REQUEST_MSG;
import static org.com.admin.util.EmployeeDetailsConstants.ERROR_MSG;
import static org.com.admin.util.EmployeeDetailsConstants.ERROR_MSG_DISPLAY;
import static org.com.admin.util.EmployeeDetailsConstants.SERVICE_NOT_AVAILABLE_ERROR_MSG;
import static org.com.admin.util.EmployeeDetailsConstants.SUCCESS_MSG;
import static org.com.admin.util.EmployeeDetailsConstants.GET;
import static org.com.admin.util.EmployeeDetailsConstants.CODE;
import static org.com.admin.util.EmployeeDetailsConstants.CONTROLLER_LOG;


@RefreshScope
@ComponentScan("org.com.admin.mapper")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
// Fetching the property value by connecting to config-server at : http://localhost:8070
	
	@Value("${extraction.url}")
	private String EXTRACTION_INSTANCE;
	
	@Autowired
	private EmployeeService employeeService;
	
   // @Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired(required=true)
	public void setEmployeeMapper(EmployeeMapper employeeMapper) {
		this.employeeMapper=employeeMapper;
	}
	
	@GetMapping(EMPLOYEE_ENDPOINT)
	public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
		List<Employee> employees = employeeService.getAllEmployee();
		List<EmployeeDTO> empListDTO = employeeMapper.toEmployeeDTO(employees);

		if (employees == null || employees.isEmpty()) {
			CONTROLLER_LOG.info("Employee list is empty");
			return new ResponseEntity(ERROR_MSG, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<EmployeeDTO>>(empListDTO, HttpStatus.OK);
	}

	@GetMapping(EMPLOYEE_ENDPOINT + "/{employeeNo}")
	public ResponseEntity<EmployeeDTO> findEmployee(@Valid @PathVariable("employeeNo") Long employeeNo) {
		Employee employee = employeeService.findEmployee(employeeNo);
		EmployeeDTO empDTO = employeeMapper.toEmployeeDTO(employee);
		if (employee == null) {
			CONTROLLER_LOG.info("Employee id is not present");
			return new ResponseEntity(ERROR_MSG_DISPLAY, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<EmployeeDTO>(empDTO, HttpStatus.OK);
	}

	@PostMapping(EMPLOYEE_ENDPOINT)
	public ResponseEntity<EmployeeDTO> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
		Employee employee = employeeMapper.toEmployee(employeeDTO);

		Employee employeesRes = employeeService.saveEmployee(employee);

		if (employeesRes == null)
			return new ResponseEntity(ERROR_MSG, HttpStatus.NOT_FOUND);

		return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);
	}

	@GetMapping(HEALTHCHECK_ENDPOINT)
	public ResponseEntity<String> hCheck() throws IOException {
		
		try {
			CONTROLLER_LOG.info("url: " + EXTRACTION_INSTANCE);
			URL restUrl = new URL(EXTRACTION_INSTANCE);
            HttpURLConnection hc = (HttpURLConnection) restUrl.openConnection();
			hc.setRequestMethod(GET);
			hc.connect();
			CODE = hc.getResponseCode();

		} catch (Exception e) {
			System.out.println(e);
			CODE = 500;
		}
		if (CODE == SUCCESS_CODE) {
			return ResponseEntity.status(HttpStatus.OK).body(SUCCESS_MSG);
		} else if (CODE == SERVICE_NOT_AVAILABLE) {
			return new ResponseEntity<String>(SERVICE_NOT_AVAILABLE_ERROR_MSG,HttpStatus.SERVICE_UNAVAILABLE);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST_MSG);

		}
	}

}
package org.com.extraction.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.com.extraction.dto.ExtractionDTO;
import org.com.extraction.feignUtil.FeignServiceUtil;
import org.com.extraction.mapper.ExtractionMapper;
import org.com.extraction.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static org.com.extraction.util.ExtractionConstants.CIRCUIT_NAME;
import static org.com.extraction.util.ExtractionConstants.FALLBACK_METHOD;
import static org.com.extraction.util.ExtractionConstants.FEIGN_RETRIEVAL_ENDPOINT;
import static org.com.extraction.util.ExtractionConstants.MSG;
import static org.com.extraction.util.ExtractionConstants.RETRIEVAL_ENDPOINT;
import static org.com.extraction.util.ExtractionConstants.LOG;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RefreshScope
@ComponentScan("org.com.admin.mapper")
@RestController
@RequestMapping("/api2/v2")
public class ExtractionController {
	@Value("${employe.instance.url}")
	private String INSTANCE_OF_EMPLOYEE;
	
      @Autowired
      FeignServiceUtil feignServiceUtil;
      @Autowired
      ExtractionMapper extractionMapper;
      
	 @GetMapping(RETRIEVAL_ENDPOINT)
	 @CircuitBreaker(name = CIRCUIT_NAME, fallbackMethod=FALLBACK_METHOD)
	 public ResponseEntity<String> hCheck() throws IOException {
		 LOG.info("Accessing the first instance");
		 RestTemplate restTemplate = new RestTemplate();
		 String resourceUrl= INSTANCE_OF_EMPLOYEE;
		 ResponseEntity<String> response= restTemplate.getForEntity(resourceUrl, String.class);
		 return response;
	 }
	 
	 @GetMapping(FEIGN_RETRIEVAL_ENDPOINT)
	 @CircuitBreaker(name = CIRCUIT_NAME, fallbackMethod=FALLBACK_METHOD)
	 public ResponseEntity<List<ExtractionDTO>> fetch() throws IOException {
		 LOG.info("Accessing the first instance using Feignclient"); 
		 ResponseEntity<List<Employee>> resp=feignServiceUtil.getAll();
		 List<Employee> employeeExtracted =resp.getBody();
		List<ExtractionDTO> extractedListDTO = extractionMapper.toExtractionDTOList( employeeExtracted);
		return new ResponseEntity<List<ExtractionDTO>>(extractedListDTO, HttpStatus.OK);
		
				
	}
	 
	public ResponseEntity<String> extractFallback(Exception e){
		LOG.error("Service is unavailable / invalid service");
		return new ResponseEntity<String>(MSG, HttpStatus.OK);
		
		 
	 }
}

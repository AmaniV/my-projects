package org.com.gateway.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {
	
	@GetMapping("/fallback")
	public ResponseEntity<String> fallbackMethod() {
		return new ResponseEntity<String>("Service is down", HttpStatus.OK);
	} 

}

package org.com.admin.apidemo.stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.assertj.core.api.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

public class GetStepDefinition {
	 private ValidatableResponse validatableResponse;
	  
	    private String endpoint = "http://localhost:8086/api/v1/employee/1";
	    private String addURI;
	    private HttpHeaders headers=null;
	    private RestTemplate restTemplate;
        private ResponseEntity<String> response;
        private String responseBody;
        private String responseBodyPOST;
	   
	    @Given("I send a request to the URL to get user details")
	    public void sendRequest(){
	        validatableResponse = given().contentType(ContentType.JSON)
	                .when().get(endpoint).then();   
	          
	        System.out.println("Response :"+validatableResponse.extract().asString());
	    }
	   
	   
	    @Then("the response will return userName {string} and password {string} and category {string} and name {string} and emailAddress {string} and phoneNumber {string}")
	    public void verifyStatus(String userName, String password, String category, String name, String emailAddress, String phoneNumber){
	          
	        validatableResponse.assertThat().body("userName",equalTo(userName));
	          
	        validatableResponse.assertThat().body("password",equalTo(password));
	          
	        validatableResponse.assertThat().body("category",equalTo(category));
	          
	        validatableResponse.assertThat().body("name",equalTo(name));
	        
	        validatableResponse.assertThat().body("emailAddress",equalTo(emailAddress));
	        
	        validatableResponse.assertThat().body("phoneNumber",equalTo(phoneNumber));
	        
	        
	          
	    }
	    
	    @Given("^I Set POST employee service api endpoint$")
	    public void setPostEndpoint(){
	        addURI = "http://dummy.restapiexample.com/api/v1/create";
	        System.out.println("Add URL :"+addURI);
	    }
	    
	    @When("^I Set request HEADER")
	    public void setHeader() {
	       headers = new HttpHeaders();
	       headers.add("Accept", "application/json");
	       headers.add("Content_Type","application/json");
	    }
	    
	    @When ("^Send a POST HTTP request$")
	    public void sendPostRequest(){
	    	String employeeJson = "{\"employeeId\": 12789 ,\"userName\":\"qwerdsert234\",\"password\": \"abcdeft@202aV\",\"category\": \"Accountant\", \"name\":\"qwerdsert\",\"emailAddress\": \"qwerdsert.yrt@gmail.com\",\"phoneNumber\": \"9121156438\"}";
	        System.out.println("\n\n" + employeeJson);
	        HttpEntity<String>entity = new HttpEntity<String>(employeeJson, headers);       
	        //POST Method to Add New Employee
	        restTemplate = new RestTemplate ();
	        response = restTemplate.postForEntity(addURI, entity, String.class);
	    }
	    
	    @Then ("^I receive valid Response$")
	    public void verifyPostResponse(){
	        responseBodyPOST = response.getBody();
	        // Write response to file
	        responseBody = response.getBody().toString();
	        System.out.println("responseBody --->" + responseBody);
	        
	        // Check if the status code is 200
	        assertEquals(200,response.getStatusCodeValue()); 
	    }

}

package org.com.admin.test.mock;
import java.net.URISyntaxException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.Assert;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class EmployeeControllerWireMockTest {
    private static final String HOST = "localhost";
	private static final int PORT = 8086;
	private static WireMockServer wireMockServer = new WireMockServer(PORT);
	static String employeeJson = "{\"employeeId\": 12789 ,\"userName\":\"qwerdsert234\",\"password\": \"abcdeft@202aV\",\"category\": \"Accountant\", \"name\":\"qwerdsert\",\"emailAddress\": \"qwerdsert.yrt@gmail.com\",\"phoneNumber\": \"9121156438\"}";
	
	@BeforeClass
	public static void setup() {
		 wireMockServer.start();
		 WireMock.configureFor(HOST, PORT);
		 
		 ResponseDefinitionBuilder response = new ResponseDefinitionBuilder();
		 response.withStatus(200);
		 response.withBody(employeeJson);
		 
		 ResponseDefinitionBuilder response2 = new ResponseDefinitionBuilder();
		 response2.withStatus(200);
		 response2.withHeader("Content-Type", "application/json");
		 
		 WireMock.stubFor(WireMock.get("/api/v1/employee")
				                   .willReturn(response));
		 WireMock.stubFor(WireMock.post("/api/v1/employee")
				 .willReturn(response2));
		}

	
	  @Test 
	  public void testGetEndpoint() throws URISyntaxException {
		  Response res= RestAssured
				        .given()
				          .get("http://localhost:" +PORT+ "/api/v1/employee")
				        .then()
				           .statusCode(200)
				            .extract().response();
		 Assert.assertEquals(res.getBody().jsonPath().get("userName").toString(),"qwerdsert234");
		  
		}
	  
	  @Test
	  public void testPostEndpoint() throws URISyntaxException
	  {
		  Response postRes = RestAssured
				           .given()
				             .post("http://localhost:" +PORT+ "/api/v1/employee")
				            .then()
				              .statusCode(200)
				                .extract().response();
		  Assert.assertEquals(200, postRes.getStatusCode());
	  }
	 
	
	@AfterClass
	public static void tearDown() {
		 wireMockServer.stop();
		
	}
}

package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.*;
import io.cucumber.junit.Cucumber;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.junit.Assert.*;

import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

@RunWith(Cucumber.class)
public class StepDefination extends Utils {
	RequestSpecification req;
	ResponseSpecification response;
	Response respec;
	 static String place_id;
	TestDataBuild data = new TestDataBuild();
	

	@Given("Add Place Payload with {string},{string},{string}")
	public void add_place_payload_with(String name, String address, String langauges) throws IOException {

		
		req = given().spec(requestSpecification()).body(data.addPlacePayLoad(name ,address,langauges));

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		APIResources responseAPI =APIResources.valueOf(resource);
	System.out.println(responseAPI.getResource());	
		response = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST")) 
			respec =req.when().post(responseAPI.getResource());

			else if(method.equalsIgnoreCase("GET")) 
				respec =req.when().get(responseAPI.getResource());
	}

	@Then("the API is call is success with status code {int}")
	public void the_api_is_call_is_success_with_status_code(Integer int1) {
		assertEquals(respec.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
		 assertEquals(getJsonPath(respec,keyValue),Expectedvalue);
	}
	@Then("Verify place id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		   // requestSpec
	 place_id=getJsonPath(respec,"place_id");
		req = given().spec(requestSpecification()).queryParam("place_id",place_id); 
		user_calls_with_http_request(resource,"GET");	
		  String actualName=getJsonPath(respec,"name");
		  assertEquals(actualName,expectedName);	 
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	 req =given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}


}

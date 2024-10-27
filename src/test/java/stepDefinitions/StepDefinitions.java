package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
//import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitions extends Utils {

	RequestSpecification req;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id; 
	JsonPath js;
	
	static String addPlaceURI = "/maps/api/place/add/json";
	static String getPlaceAPI = "/maps/api/place/get/json";
	static String deletePlaceAPI = "/maps/api/place/delete/json";

	@Given("Ad Place Payload with {string} {string} {string}")
	public void ad_place_payload_with(String name, String language, String address) throws IOException { 
		
		req = given().spec(requestSpecification()).body(data.addPlacePayLoad(name, language, address));
	}

	@When("user calls {string} http request")
	public void user_calls_with_http_request( String method) {
		//constructor will be called with value of resources which you pass
		//APIResources resourceAPI = APIResources.valueOf(resource);
		//System.out.println(resourceAPI.getResource());
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
		
		response = req.when().post(addPlaceURI);
		
		else if(method.equalsIgnoreCase("DELETE"))
			
			response = req.when().delete(deletePlaceAPI);
		
		else if(method.equalsIgnoreCase("GET"))
		
			response = req.when().get(getPlaceAPI);
		
		
	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is_ok(String keyValue, String ExpectedValue) {

		assertEquals(getJsonPath(response,keyValue),ExpectedValue);

	}
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException{
		place_id = getJsonPath(response, "place_id");
		req = given().spec(requestSpecification()).queryParam("place_id",place_id );
		user_calls_with_http_request("GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName,expectedName);
		
		}
	
	@Given("deletePlaceAPI")
	public void delete_place_api() throws IOException {
		System.out.println(place_id);
		req = given().spec(requestSpecification()).body(data.deletePlacePayLoad(place_id));
	
		
	   
	}
}

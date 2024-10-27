package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before ("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		StepDefinitions sd = new StepDefinitions();
		if(StepDefinitions.place_id==null)
		{
		sd.ad_place_payload_with("kris", "tamil", "asia");
		sd.user_calls_with_http_request( "POST");
		sd.verify_place_id_created_maps_to_using("kris", "getPlaceAPI");
		}
	}
	

}

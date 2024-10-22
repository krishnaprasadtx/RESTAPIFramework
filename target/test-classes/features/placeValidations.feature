Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if Place is being succssfully added using AddPlaceAPI

Given Ad Place Payload with "<name>" "<language>" "<address>"
When user calls "AddPlaceAPI" with "Post" http request
Then the API call is success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_id created maps to "<name>" using "getPlaceAPI"

Examples:
	|name|language|address|
	|krishna|english|plano|
	#|Prasad|Telugu|Frisco|
	#|Arni|Hindi|Prosper|

@DeletePlace	@Regression
Scenario: Verify if DeletePlace functionality is working

Given deletePlaceAPI
When user calls "deletePlaceAPI" with "Post" http request
Then the API call is success with status code 200
And "status" in response body is "OK"
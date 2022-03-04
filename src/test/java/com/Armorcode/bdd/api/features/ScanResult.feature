Feature:  Validate the  fetch scan result get api functionalities

  Background: 
    Background: 
    Given I set up request specification
      | HeaderName   | HeaderValue      |
      | Accept       | application/json |
      | Content-Type | application/json |
    And I add oauth2 authentication "3b87b2db-f7df-4d18-a3ee-fbc16edac027"

  @ScanResult @all
  Scenario: getSacn Result- validate the scan result by scan result api response
    When I send the GET request to "ScanResult" endpoint with query parameters
      | QueryParamName | QueryParamValue |
      | sort   			   |  createdAt,desc |
      | page   			   |  0							 |
      | size   			   |  10						 |
    Then I should see the response code as "200"
    And contentType as "application/json"
    And the fields in response should match with expected values
      | JPath 											| Value  							|
      | $.content.[0].scanStatus 		| COMPLETED  					|
      | $.content.[0].tool			 		| Netsparker  				|
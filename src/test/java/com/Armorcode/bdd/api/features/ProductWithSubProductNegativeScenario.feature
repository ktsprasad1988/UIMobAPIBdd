Feature: validation of create subProduct api funtionalities

  Background: 
    Given I set up request specification
      | HeaderName   | HeaderValue      |
      | Accept       | application/json |
      | Content-Type | application/json |
    And I add oauth2 authentication "3b87b2db-f7df-4d18-a3ee-fbc16edac027"

  @NegativeSubProduct @all
  Scenario Outline: - Validating the product creation api
    When I modify the field values in "CreateProduct" payload
      | JPath         | Value         |
      | $.name        | <name>        |
      | $.description | <description> |
    And send the post request to endpoint
    Then I should see the response code as "200"
    And the fields in response should match with expected values
      | JPath         | Value                            |
      | $.createdBy   | "krishna.chaitanya@armorcode.io" |
      | $.updatedBy   | "krishna.chaitanya@armorcode.io" |
      | $.name        | <name>                           |
      | $.description | <description>                    |
    And I capture the Productid "$.product.id"
    And I create subProduct with "CreateSubProduct" with same Id "$.product.id"
      | JPath         | Value       	   |
      | $.name        | FixedName        |
      | $.description | FixedDesc				 |
		And send the post request to endpoint
    Then I should see the response code as "400"
    And the fields in response should match with expected values
      | JPath         | Value                            |
      | $.status	    | "BAD_REQUEST" 									 |
      | $.message	    | "Name already exits" 						 |
    Examples: 
      | name     						 | description              |
      | TestAutomationTry15  | Description for TestAuto |
   #  | TestAuto1 					 | Description for TestAuto |

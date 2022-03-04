Feature: validation of scan configuration api funtionalities

  Background: 
    Given I set up request specification
      | HeaderName   | HeaderValue      |
      | Accept       | application/json |
      | Content-Type | application/json |
    And I add oauth2 authentication "3b87b2db-f7df-4d18-a3ee-fbc16edac027"

  @NegativeScanConfiguration @all
  Scenario Outline: - Validating the product creation api
    When I modify the field values in "CreateProduct" payload
      | JPath         | Value         |
      | $.name        | <name>        |
      | $.description | <description> |
    And send the post request to endpoint
    Then I should see the response code as "200"
    And the fields in response should match with expected values
      | JPath         | Value                            |
      | $.name        | <name>                           |
      | $.description | <description>                    |
    And I capture the Productid "$.id"
    And I create subProduct with "CreateSubProduct" with same Id "$.product.id"
      | JPath         | Value       	   |
      | $.name        | <subName>        |
      | $.description | <subDescription> |
		And send the post request to endpoint
    Then I should see the response code as "200"
    And the fields in response should match with expected values
      | JPath         | Value                            |
      | $.createdBy   | "krishna.chaitanya@armorcode.io" |
      | $.updatedBy   | "krishna.chaitanya@armorcode.io" |
    And I create scanConfiguration with "ScanConfiguration"
      | JPath         			 | Value         				|
      | $.[0].websiteName        | <websiteName>        |
      | $.[0].product_name 			 | <name> 							|
      | $.[0].sub_product_name 	 |"subProduct" 					|
      | $.[0].environment				 |<environment>					|
      | $.[0].tool_name 				 |<toolname>  					|
    And send the post request to endpoint
    Then I should see the response code as "400"
    And the fields in response should match with expected values
      | JPath   		 | Value             													  |
      | $.status		 | "BAD_REQUEST" 														  	|
      | $.message		 | "Sub Product \"subProduct\" does not exists" |
      
    Examples: 
      | name     						 | description              |subName				|subDescription								|websiteName|environment|toolname |
      | TestAutomationTry40  | Description for TestAuto |SubTestAuto40	|Description for SubtestuAuto1|Demo				|Production |netsparker|
   #   | TestAuto1 | Description for TestAuto |SubTestAuto1	|Description for SubtestuAuto1|

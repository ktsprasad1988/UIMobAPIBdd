
Feature: Customer flow in armorcode application
 User flow in the armorcode application with different scenarios
 
 
 
 
Background: Customer is logged in the application
	Given User opens the required broswer 
	And  User redirects to the website 
	Then User clicks on Sign in using Google Account
	And User enters the valid emailID
	And User enters the valid password
	And User enters the Home page
@Smoke
  Scenario: User creates product in armorcode
  Given User opens the required broswer
  And  User redirects to the website
    When User clicks on Products from the menu
    Then User clicks in New Product
    And User enters data Name as "TestA" and description as "AutomationCheck"
    And User click on Next button
    And User click on Next button
    And User click on Next button
    Then User click on Submit button
  

  Scenario: User creates sub-product in armorcode
    When User clicks on SubProducts from the menu
   Then User clicks in New SubProduct
    And User chooses Parent Product as "TestA" and enters SubProduct as "SubTestA" and description as "AutomationCheck"
    And User click on Next button
    And User click on Next button
    And User click on Next button
    And User click on Next button
    Then User click on Submit button
  
 
  Scenario: User starts scanning for their website using Security tool and see scan results
  	When User clicks on Security Tools from the menu
  	And User chooses Netsparker from different security tools
  	And User adds his website by clicking Add Website
  	And User chooses Website from the dropdown as "Demo" and chooses the product as "TestA" and chooses SubProduct as "SubTestA" and  chooses environment as "Production"
  	Then User clicks on Save button
  	And User clicks on Scans button from the menu bar
  	And User checks the result from the total findings
  	And User verifies the scan results after every fifteen minutes
 
  	

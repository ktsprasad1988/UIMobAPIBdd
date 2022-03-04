Feature: PICK UP FROM LOCKER- POSITIVE FLOW 
 
    Background: 
    Given User should open Decathlon Mobile app
    When Verify the Activity splash screen
    Given Set the instore location using appium
    Then User Click on Allow button on alert pop up for the Camera permissions
    Then User Click on Allow button on alert pop up for the location permissions
    And Verify welcome screen
    And User Click on next button at instore - online
    And User Click on next button at scan in store
    And User Click on next button at Pay in app
    And User Click on next button at skip the line
    And User Click on finish button at shopping made simple
    And Enter Mobile number as below in enter email or phone number testbox
      | mobile_number |
      |    9948101014 |
    Then User Click on Get OTP button
    And Enter OTP into four  text boxes with in respective seconds      
    Then User Click on Submit button
    And Verify whether user navigated sucessfully to the home screen
  
  
  Scenario: The user can place a Pick-up from Locker order only for the eligible items
    Then User clicks on the Search Product
    And Enter the Model ID of any of the product
      | Model Number |
      |      8351755 |
    Then Verify that related product is displayed in the search list
      | Product Name            |
      | AQUAVEST TROPICAL BLACK |
    When User clicks on the product
      | Product Name            |
      | AQUAVEST TROPICAL BLACK |
    Then Verify that the Product details screen is displayed
    And Click on the Add to Bag button on the product details screen
    And select the convenience as PickFromStore option and select the size
    Then Verify that the Product is added in the cart
    And Click on the Proceed to Check button in the cart page
    And Select the shipping address and click on the Proceed to shipping choice button
    And the Pick-up from Locker option should be available
		When the user selects the Pickup from Locker option and makes the payment
		Then the Order for Pickup from Locker should be placed
		
	
  Scenario: The user can place a Pick-up from Locker order only for the eligible items
    Then User clicks on the Search Product
    And Enter the Model ID of any of the product
      | Model Number |
      |      8351755 |
    Then Verify that related product is displayed in the search list
      | Product Name            |
      | AQUAVEST TROPICAL BLACK |
    When User clicks on the product
      | Product Name            |
      | AQUAVEST TROPICAL BLACK |
    Then Verify that the Product details screen is displayed
    And Click on the Add to Bag button on the product details screen
    And select the convenience as PickFromStore option and select the size
    Then Verify that the Product is added in the cart
    And Click on the Proceed to Check button in the cart page
    And Select the shipping address and click on the Proceed to shipping choice button
    And the Pick-up from Locker option should be available
    And the Extra price of Rs49should be applied
		When the user selects the Pickup from Locker option and makes the payment
		Then the Order for Pickup from Locker should be placed
		And the Extra price of Rs49 should be applied in OrderDetails
	
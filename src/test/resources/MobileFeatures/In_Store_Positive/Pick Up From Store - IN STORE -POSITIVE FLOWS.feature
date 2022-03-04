Feature: Pick Up From Store - IN STORE - POSITIVE FLOW

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
  
  @IOSAPP
  Scenario: Placing the Click and collect orders by scanning QR code Signage inside the store & making payment via UPI (Google Pay) payment mode
    Then User clicks on the Search Product
    And Enter the Model ID of any of the product
      | Model Number |
      |      8405262 |
    Then Verify that related product is displayed in the search list
      | Product Name            |
      | AQUAVEST TROPICAL BLACK |
    When User clicks on the product
      | Product Name            |
      | AQUAVEST TROPICAL BLACK |
    Then Verify that the Product details screen is displayed
    And Click on the Add to Bag button on the product details screen
    And select the convenience as Home Delivery option and select the size
    Then Verify that the Product is added in the cart
    And Click on the Proceed to Check button in the cart page
    And Select the shipping address and click on the Proceed to shipping choice button
    And click on the HomeDelivery option
    When user clicks Proceed to check out button
    Then Verify that Razor pay screen is displayed with a valid amount
    When User makes the Payment using UPI Google Pay
    Then Verify that feedback screen is displayed
    When user select the feedback
    Then verify that the new bill details page is displayed
  
  @IOSAPP1
  Scenario: Placing the click and collect order by scanning Bar code inside the store & making payment via Card payment mode and also verify the order in the new bill details, purchase history and DB
    Then User clicks on the Search Product
    And Enter the Model ID of any of the product
      | Model Number |
      |      8405262 |
    Then Verify that related product is displayed in the search list
      | Product Name            |
      | AQUAVEST TROPICAL BLACK |
    When User clicks on the product
      | Product Name            |
      | AQUAVEST TROPICAL BLACK |
    Then Verify that the Product details screen is displayed
    And Click on the Add to Bag button on the product details screen
    And select the convenience as Home Delivery option and select the size
    Then Verify that the Product is added in the cart
    And Click on the Proceed to Check button in the cart page
    And Select the shipping address and click on the Proceed to shipping choice button
    And click on the HomeDelivery option
    When user clicks Proceed to check out button
    Then Verify that Razor pay screen is displayed with a valid amount
    When User makes the Payment using Card
    Then Verify that feedback screen is displayed
    When user select the feedback
    Then verify that the new bill details page is displayed
    When the user navigates to the Purchase history
    Then Verify that purchased order is displayed in the purchase history
  
  @IOSAPP
  Scenario: Placing the takeaway order by searching the product using search option inside the store & making the Payment using UPI(PhonePe)
    Then User clicks on the Search Product
    And Enter the Model ID of any of the product
      | Model Number |
      |      8405262 |
    Then Verify that related product is displayed in the search list
      | Product Name            |
      | AQUAVEST TROPICAL BLACK |
    When User clicks on the product
      | Product Name            |
      | AQUAVEST TROPICAL BLACK |
    Then Verify that the Product details screen is displayed
    And Click on the Add to Bag button on the product details screen
    And select the convenience as Home Delivery option and select the size
    Then Verify that the Product is added in the cart
    And Click on the Proceed to Check button in the cart page
    And Select the shipping address and click on the Proceed to shipping choice button
    And click on the HomeDelivery option
    When user clicks Proceed to check out button
    Then Verify that Razor pay screen is displayed with a valid amount
    When User makes the Payment using Card
    Then Verify that feedback screen is displayed
    When user select the feedback
    Then verify that the new bill details page is displayed

  @IOSAPP
  Scenario: The delivery charges are not applied for the pick and collect orders
    Then User clicks on the Search Product
    And Enter the Model ID of any of the product
      | Model Number |
      |      8405262 |
    Then Verify that related product is displayed in the search list
      | Product Name            |
      | AQUAVEST TROPICAL BLACK |
    When User clicks on the product
      | Product Name            |
      | AQUAVEST TROPICAL BLACK |
    Then Verify that the Product details screen is displayed
    And Click on the Add to Bag button on the product details screen
    And select the convenience as Home Delivery option and select the size
    Then Verify that the Product is added in the cart
    And Click on the Proceed to Check button in the cart page
    And Select the shipping address and click on the Proceed to shipping choice button
    And click on the Pickup from Store option
    Then Verify that Delivery charge is not applied for the order
 
 @IOSAPP
 Scenario: The user can change store address for Click and Collect Shipping Choice.
    Then User clicks on the Search Product
    And Enter the Model ID of any of the product
      | Model Number |
      |      8405262 |
    Then Verify that related product is displayed in the search list
      | Product Name            |
      | AQUAVEST TROPICAL BLACK |
    When User clicks on the product
      | Product Name            |
      | AQUAVEST TROPICAL BLACK |
    Then Verify that the Product details screen is displayed
    And Click on the Add to Bag button on the product details screen
    And select the convenience as Home Delivery option and select the size
    Then Verify that the Product is added in the cart
    And Click on the Proceed to Check button in the cart page
    And Select the shipping address and click on the Proceed to shipping choice button
    And click on the Pickup from Store option
    Then Store for Pickup from Store should be selected
		When the user Clicks on the Change button and selects another store
		Then the changed store name for Pickup from Store should be selected
   
  @IOSAPP
  Scenario: The Delivery charges should not be applied for the Pick-up from store orders.
    Then User clicks on the Search Product
    And Enter the Model ID of any of the product
      | Model Number |
      |      8405262 |
    Then Verify that related product is displayed in the search list
      | Product Name            |
      | AQUAVEST TROPICAL BLACK |
    When User clicks on the product
      | Product Name            |
      | AQUAVEST TROPICAL BLACK |
    Then Verify that the Product details screen is displayed
    And Click on the Add to Bag button on the product details screen
    And select the convenience as Home Delivery option and select the size
    Then Verify that the Product is added in the cart
    And Click on the Proceed to Check button in the cart page
    And Select the shipping address and click on the Proceed to shipping choice button
    And click on the Pickup from Store option
    Then Store for Pickup from Store should be selected
		When the user Clicks on the Change button and selects another store
		Then the changed store name for Pickup from Store should be selected
		And the Delivery should be FREE
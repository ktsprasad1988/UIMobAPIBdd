Feature: Take Away - IN STORE - POSITIVE FLOW

  Background: 
    Given User should open Decathlon Mobile app
    When Verify the Activity splash screen
    Then Set the instore location using appium
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
  Scenario: Placing the takeaway order by scanning signage QRcode inside the store & making payment via UPI (Google Pay) payment mode & Verify that Store name is displayed in the bill details
    Then Empty The Cart Items
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
    And select the convenience as aTake away option and select the size
    Then Verify that the Product is added in the cart
    And Click on the Proceed to Check button in the cart page
    #Delivery option click functionality to be added
    Then Verify that Razor pay screen is displayed with a valid amount
    When User makes the Payment using UPI Google Pay
    Then Verify that feedback screen is displayed
    When user select the feedback
    Then verify that the new bill details page is displayed with QR code generated
  @IOSAPP
  Scenario: Placing the takeaway order by scanning Bar code inside the store & making payment via Card payment mode and also verify the order in the new bill details ,purchase history and DB
    Then Empty The Cart Items
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
    And select the convenience as aTake away option and select the size
    Then Verify that the Product is added in the cart
    And Click on the Proceed to Check button in the cart page
    Then Verify that Razor pay screen is displayed with a valid amount
    When User makes the Payment using Card
    Then Verify that feedback screen is displayed
    When user select the feedback
    Then verify that the new bill details page is displayed with QR code generated
  @IOSAPP
  Scenario: Placing the takeaway order by searching the product using search option inside the store & making the Payment using UPI(PhonePe)
    Then Empty The Cart Items
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
    And select the convenience as aTake away option and select the size
    Then Verify that the Product is added in the cart
    And Click on the Proceed to Check button in the cart page
    Then Verify that Razor pay screen is displayed with a valid amount
    When User makes the Payment using UPI Google Pay
    Then Verify that feedback screen is displayed
    When user select the feedback
    Then verify that the new bill details page is displayed with QR code generated
  
   @IOSAPP  
  Scenario: The QR code disappears from the bill details in the purchase history after 1 hour of placing an order
    Then Empty The Cart Items
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
    And select the convenience as aTake away option and select the size
    Then Verify that the Product is added in the cart
    And Click on the Proceed to Check button in the cart page
    Then Verify that Razor pay screen is displayed with a valid amount
    When User makes the Payment using UPI Google Pay
    Then Verify that feedback screen is displayed
    When user select the feedback
    Then verify that the new bill details page is displayed with QR code generated
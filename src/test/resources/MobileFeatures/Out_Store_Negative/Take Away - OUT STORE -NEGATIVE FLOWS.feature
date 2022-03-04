Feature: Take Away - IN STORE - NEGATIVE FLOW

  Background: 
    Given User should open Decathlon Mobile app
    When Verify the Activity splash screen
    Given Set the instore location using appium
    Then User Click on Allow button on alert pop up for the Camera permissions
    Then User Click on Deny button on alert pop up for the location permissions
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
  Scenario: The user cannot place the take away order when user is out side the store
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
    Then Verify that take away option is not available
#this scenario needs to be moved
@IOSAPP
Scenario: The user cannot change the Convenience of a Delivery product to Take Away outside the store.
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
    Then the product should added to the cart in the Delivery section
    When the user goes to the Edit Cart screen of that product
    Then the user should not be able to edit the Convenience to Take Away
  
  
  
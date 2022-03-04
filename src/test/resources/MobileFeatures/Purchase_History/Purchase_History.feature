Feature: Purchase History
 
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
  
      
  Scenario: Validate Order ID, Paid status and store name is displayed in the purchase history for take away order
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
    And select the convenience as aTake away option and select the size
    Then Verify that the Product is added in the cart
    And Click on the Proceed to Check button in the cart page
    Then Verify that Razor pay screen is displayed with a valid amount
    When User makes the Payment using UPI Google Pay
    Then Verify that feedback screen is displayed
    When user select the feedback
    Then verify that the new bill details page is displayed with QR code generated
    And Capture Order Details to Verify in Purchase History Screen
    When the user navigates to the Purchase history
    Then verify Purchase Paid Status
    Then verify Purchase Details Order ID, Order Type
	
     
 Scenario: Validate the purchase history for home delivery orders
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
    And Capture Order Id and Order Type to Verify in Purchase History Screen
    When the user navigates to the Purchase history
    Then verify Purchase Paid Status
    Then verify Purchase Details Order ID, Order Type
  
       
 Scenario: Validate the purchase history for Click and collect orders
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
    And select the convenience as PickFromStore option and select the size
    Then Verify that the Product is added in the cart
    And Click on the Proceed to Check button in the cart page
    And Select the shipping address and click on the Proceed to shipping choice button
    And click on the Pickup from Store option
    When user clicks Proceed to check out button
    Then Verify that Razor pay screen is displayed with a valid amount
    When User makes the Payment using UPI Google Pay
    Then Verify that feedback screen is displayed
    When user select the feedback
    Then verify that the new bill details page is displayed
    And Capture Order Id and Order Type to Verify in Purchase History Screen
    When the user navigates to the Purchase history
    Then verify Purchase Paid Status
    Then verify Purchase Details Order ID, Order Type
  
        
 Scenario: Validate the purchase history for locker orders
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
    When select the shipping choice as Pick up from Locker
    Then Verify that Razor pay screen is displayed with a valid amount
    When User makes the Payment using UPI Google Pay
    Then Verify that feedback screen is displayed
    When user select the feedback
    Then verify that the new bill details page is displayed
    And Capture Order Id and Order Type to Verify in Purchase History Screen
    When the user navigates to the Purchase history
    Then verify Purchase Paid Status
    Then verify Purchase Details Order ID, Order Type

   
Scenario: Validate the purchase history for mixed order
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
    And select the convenience as Home Delivery option and select the size
    And Click on the Add to Bag button on the product details screen    
    And select the convenience as aTake away option and select the size
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
    And Capture Multiple Order Details to Verify in Purchase History Screen
    When the user navigates to the Purchase history
    Then verify Purchase Paid Status
    Then verify Multiple Order Details in Purchase History
    
 Scenario: Verify the Order ID should be same in the purchase history screen as Fresh bill details
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
    And Capture Order Id and Order Type to Verify in Purchase History Screen
    When the user navigates to the Purchase history 
    Then verify Purchase Details Order ID, Order Type
    
 Scenario: The user can navigate the product details screen by clicking on product and Buy again button
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
    And Capture Order Id and Order Type to Verify in Purchase History Screen
    When the user navigates to the Purchase history 
    When the user clicks on the product
		And navigate the product details screen
		And click on the product/Buy again button
		Then Verify that the Product details screen is displayed
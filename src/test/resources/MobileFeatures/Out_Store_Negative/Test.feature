Feature: Login Tests

  
  Scenario: SandeepTest
    Given User should open Decathlon Mobile app
    When Verify the Activity splash screen
    Then Set the out location using appium
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
      |    9948101015 |
    And User clicks on the system back button

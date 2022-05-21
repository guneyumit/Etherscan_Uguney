Feature: Register


    @try
    Scenario: Verify that user can fill out the registration form
      When user creates a new email address from temp mail website
      And user opens a new tab
      Given user goes to the register page
      When user fills out the registration form with valid credentials
      And user selects "Terms and Conditions" checkbox
      And user selects "Subscription" checkbox
      When user hits the submit button
      Then "Registration has been submitted" message should be displayed






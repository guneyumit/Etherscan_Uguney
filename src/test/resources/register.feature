Feature: Register


  Background:
    Given user is on the register page


  Scenario: Verify that user can fill out the registration form
    When user creates a new email address from temp mail website
    And user opens a new tab
    Given user is on the register page
    When user fills out the registration form with valid credentials
    And user selects "Terms and Conditions" checkbox
    And user selects "Subscription" checkbox
    When user hits the submit button
    Then Registration has been submitted message should be displayed

  @ValidRegistration
  Scenario: Verify that user can fill out the registration form with valid credentials
    When user enters a valid username
    And user enters a valid email address twice
    And user enters a valid password twice
    And user selects "Terms and Conditions" checkbox
    And user selects "Subscription" checkbox
    And user hits the submit button
    Then verify each element works fine in the registration form


  @EmptyInputboxes
  Scenario: User should see error messages when all inputs are left empty
    When user hits the submit button
    Then registration should fail with error messages under the form elements


  @usernameNegative-1
  Scenario Outline: User should not be able to register with a username with invalid length
    When user enters different invalid username types below "<invalidUsername>"
    Then verify "username" is invalid error message should be displayed

    Examples:
      | invalidUsername |
      | asd7            |
      | a2k             |
      | @@1             |
      | ##12314##       |

  @usernameMoreThan30chars
  Scenario: User should see error messages when all inputs are left empty
    When user tries to enter more than 30 alphanumeric characters into the username input box
    Then verify username input box doesn't allow to write more than 30


  @NotAgreeToTerms
  Scenario: User should not be able to register without clicking on agree to the terms and conditions checkbox
    When user enters a valid username
    And user enters a valid email address twice
    And user enters a valid password twice
    And user selects "Subscription" checkbox
    And user hits the submit button
    Then verify "Terms and Conditions" error message should be displayed

  @MismatchingConfirmPass
  Scenario: User should not be able to register by putting mismatching password in confirm password
    When user enters a valid username
    And user enters a valid email address twice
    And user enters a valid password
    And user enters a different password in confirm password input box
    Then password doesn't match error should be displayed

  @EmailInvalid1 @try
  Scenario: User should not be able to register with an invalid email address format
    When user enters a valid username
    And enters "abc@abc" in email address input
    And enters same password in confirm password input
    And selects agreeing to the terms and conditions
    And passes reCaptcha manually
    And clicks Create an Account button
    Then registration should fail with a warning message for invalid email address








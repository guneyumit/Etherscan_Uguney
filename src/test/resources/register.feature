Feature: Register


  Background:
    Given user is on the register page

@ValidRegistrationWithTempMail
  Scenario: Verify that user can fill out the registration form after taking a new temporary mail address
    When user creates a new email address from temp mail website
    And user opens a new tab
    Given user is on the register page
    When user fills out the registration form with valid credentials
    And user selects "Terms and Conditions" checkbox
    And user selects "Subscription" checkbox
    When user hits the submit button
    Then verify each element works fine in the registration form

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
    Then verify "username" error message should be displayed

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


  @PasswordInvalidLength
  Scenario Outline: User should not be able to register with a password has less than 5 characters
    When user enters a valid username
    And user enters a valid email address twice
    And enters a password with a length of <length>
    Then password should be at least 5 characters long error is displayed under "password" input
    And enters same password in confirm password input
    Then password should be at least 5 characters long error is displayed under "confirm password" input
    Examples:
      | length |
      | 1      |
      | 4      |


  @EmailInvalid1
  Scenario Outline: User should not be able to register with an invalid email address format
    When user enters a valid username
    And enters "<invalid email>" types into email input box
    Then verify "email" error message should be displayed
    When user enters "<invalid email>" types into confirm email input box
    Then verify "confirm email" error message should be displayed
    When user enters a valid password twice
    And user selects "Terms and Conditions" checkbox
    And user selects "Subscription" checkbox
    And user hits the submit button
    Then verify "email" error message should be displayed
    Then verify "confirm email" error message should be displayed
    Examples:
      | invalid email |
      | abc@abc.      |
      | abc@          |
      | @abc          |
      | 1234567       |
      | mail.com      |
      | mail.com@     |
      | mail@.com     |


  @EmptyConfirmPassword
  Scenario: User should not be able to register without putting password in confirm password
    When user enters a valid username
    And user enters a valid email address twice
    And user enters a valid password
    And user selects "Terms and Conditions" checkbox
    And user selects "Subscription" checkbox
    And user hits the submit button
    Then verify "confirm password" error message should be displayed


  @EmptyConfirmEmail
  Scenario: User should not be able to register without putting email address in confirm email input box
    When user enters a valid username
    And user enters a valid email address
    And user enters a valid password twice
    And user selects "Terms and Conditions" checkbox
    And user selects "Subscription" checkbox
    And user hits the submit button
    Then verify "confirm email" error message should be displayed











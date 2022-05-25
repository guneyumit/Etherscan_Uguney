
@smoke
Feature: Register

  Background:
    Given user is on the register page


# We skip the captcha since it requires to be disabled in test env, and so verify by seeing invalid captcha response error.
# If there is no error without captcha error, it means registration form works fine.
  @TC-1 @ValidRegistration
  Scenario: Verify that user can fill out the registration form with valid credentials
    When user enters a valid username
    And user enters a valid email address twice
    And user enters a valid password twice
    And user selects "Terms and Conditions" checkbox
    And user selects "Subscription" checkbox
    And user hits the submit button
    Then verify each element works fine in the registration form


  @TC-2 @ValidRegistrationWithTempMail
  Scenario: Verify that user can fill out the registration form after taking a new temporary mail address
    When user creates a new email address from temp mail website
    And user opens a new tab
    Given user is on the register page
    When user fills out the registration form with valid credentials
    And user selects "Terms and Conditions" checkbox
    And user selects "Subscription" checkbox
    When user hits the submit button
    Then verify each element works fine in the registration form


  @TC-3 @EmptyInputboxes
  Scenario: User should see error messages under all input boxes when all inputs are left empty
    When user hits the submit button
    Then registration should fail with error messages under the form elements


  @TC-4 @InvalidUsername
  Scenario Outline: User should not be able to register with an invalid username
    When user enters different "<invalidUsername>" types below
    Then verify "username" error message should be displayed
    When user enters a valid email address twice
    And user enters a valid password twice
    And user selects "Terms and Conditions" checkbox
    And user selects "Subscription" checkbox
    And user hits the submit button
    Then verify "username" error message should be displayed

    Examples:
      | invalidUsername |
      | asd7            |
      | a2k             |
      | @@1             |
      | ##12314##       |


# in this TC, we send more than 30 chars into the username input, and then we pick up that text, count the chars and verify
# if it equals 30 or not
  @TC-5 @UsernameMoreThan30chars
  Scenario: User should not be allowed to write more than 30 alphanumeric characters into the username input box
    When user inserts more than 30 alphanumeric characters into the username input box
    Then verify username input box doesn't allow to write more than 30


  @TC-6 @NotAgreeToTerms
  Scenario: User should not be able to register without clicking on agree to the terms and conditions checkbox
    When user enters a valid username
    And user enters a valid email address twice
    And user enters a valid password twice
    And user selects "Subscription" checkbox
    And user hits the submit button
    Then verify "Terms and Conditions" error message should be displayed


  @TC-7 @MismatchingConfirmPassword
  Scenario: User should not be able to register by putting mismatching password in confirm password input box
    When user enters a valid username
    And user enters a valid email address twice
    And user enters a valid password
    And user enters a different password in confirm password input box
    Then password doesn't match error should be displayed
    And user selects "Terms and Conditions" checkbox
    And user selects "Subscription" checkbox
    And user hits the submit button
    Then password doesn't match error should be displayed


  @TC-8 @PasswordInvalidLength
  Scenario Outline: User should not be able to register with a password has less than 5 characters
    When user enters a valid username
    And user enters a valid email address twice
    And enters a password with a length of <length>
    Then password should be at least 5 characters long error is displayed under "password" input
    And enters same password in confirm password input
    Then password should be at least 5 characters long error is displayed under "confirm password" input
    And user hits the submit button
    Then password should be at least 5 characters long error is displayed under "password" input
    Then password should be at least 5 characters long error is displayed under "confirm password" input
    Examples:
      | length |
      | 1      |
      | 4      |


  @TC-9 @EmailInvalid
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


  @TC-10 @EmptyConfirmPassword
  Scenario: User should not be able to register without putting password in confirm password
    When user enters a valid username
    And user enters a valid email address twice
    And user enters a valid password
    And user selects "Terms and Conditions" checkbox
    And user selects "Subscription" checkbox
    And user hits the submit button
    Then verify "confirm password" error message should be displayed


  @TC-11 @EmptyConfirmEmail
  Scenario: User should not be able to register without putting email address in confirm email input box
    When user enters a valid username
    And user enters a valid email address
    And user enters a valid password twice
    And user selects "Terms and Conditions" checkbox
    And user selects "Subscription" checkbox
    And user hits the submit button
    Then verify "confirm email" error message should be displayed


  @TC-12 @PasswordStrength-Weak
  Scenario: When users enter weak password, they should see "Strength: Weak!" warning
    When user enters a valid username
    And user enters a valid email address
    And user enters a valid password as "asdfg"
    Then verify "Strength: Weak!" warning is displayed


  @TC-13 @PasswordStrength-Medium
  Scenario: When users enter a password with special characters and numbers, they should see "Strength: Medium!" warning
    When user enters a valid username
    And user enters a valid email address
    And user enters a valid password as "asdfg?_22"
    Then verify "Strength: Medium!" warning is displayed


  @TC-14 @PasswordStrength-Strong
  Scenario: Once users enter a password with special characters, numbers and capital letters,
  they should see "Strength: Strong!" warning

    When user enters a valid username
    And user enters a valid email address
    And user enters a valid password as "asdfg?_22__ASD"
    Then verify "Strength: Strong!" warning is displayed







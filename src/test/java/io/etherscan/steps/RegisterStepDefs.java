package io.etherscan.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.etherscan.pagesOfEtherscan.RegisterPage;


public class RegisterStepDefs {

    RegisterPage registerPage = new RegisterPage();



    @Given("user creates a new email address from temp mail website")
    public void userCreatesANewEmailAddressFromTempMailWebsite() {

        registerPage.getTemporaryMail();
    }

    @And("user opens a new tab")
    public void userOpensANewTab() {

        registerPage.openANewTab();
    }

    @Given("user is on the register page")
    public void userIsOnTheRegisterPage() {

        registerPage.goToRegisterPage();
        registerPage.verifyRegistrationPageLoaded();
        registerPage.acceptCookies();
    }

    @When("user fills out the registration form with valid credentials")
    public void userFillsOutTheRegistrationFormWithValidCredentials() {

        registerPage.enterTheInformationNeeded();
    }

    @And("user selects {string} checkbox")
    public void userSelectsCheckbox(String checkbox) {

        registerPage.verifyCheckboxIsNotSelected(checkbox);
        registerPage.markTheCheckbox(checkbox);
    }

    @When("user hits the submit button")
    public void userHitsTheSubmitButton() {

        registerPage.hitTheSubmitButton();
    }

    @Then("Registration has been submitted message should be displayed")
    public void messageShouldBeDisplayed() {

        registerPage.verifyRegistrationMessageIsDisplayed();
    }

    @When("user enters a valid username")
    public void userEntersAValidUsername() {

        registerPage.getRandomUsernameAndInsert();
    }

    @And("user enters a valid email address twice")
    public void userEntersAValidEmailAddressTwice() {

        registerPage.getRandomEmailAndInsertTwice();
    }

    @And("user enters a valid password twice")
    public void userEntersAValidPasswordTwice() {

        registerPage.getRandomPasswordAndInsertTwice();
    }

    @Then("verify each element works fine in the registration form")
    public void verifyEachElementWorksFineInTheRegistrationForm() {

        // Since captcha cannot be automated in this environment, we should see "invalid captcha response" error
        // in this way, we make sure that form can be filled out in a success by user
        registerPage.verifySeeingInvalidCaptchaError();
    }

    @Then("registration should fail with error messages under the form elements")
    public void registrationShouldFailWithErrorMessagesUnderTheFormElements() {

        registerPage.verifyAllErrorMessagesAreDisplayed();
    }

    @When("user enters different {string} types below")
    public void userEntersDifferentInvalidUsernameTypesBelow(String username) {

        registerPage.enterInvalidUsernames(username);
    }

    @Then("verify {string} error message should be displayed")
    public void verifyErrorMessageShouldBeDisplayed(String inputboxName) {

        registerPage.verifyErrorMessageIsDisplayed(inputboxName);
    }

    @When("user inserts more than {int} alphanumeric characters into the username input box")
    public void userInsertsMoreThanAlphanumericCharactersIntoTheUsernameInputBox(int length) {

        registerPage.enterUsernameLongerThanExpected(length);
    }

    @Then("verify username input box doesn't allow to write more than {int}")
    public void verifyUsernameInputBoxDoesnTAllowToWriteMoreThan(int length) {

        registerPage.verifyAvailableUsernameEquals30(length);
    }

    @And("user enters a valid password")
    public void userEntersAValidPassword() {
        registerPage.enterPassword();
    }

    @And("user enters a different password in confirm password input box")
    public void userEntersADifferentPasswordInConfirmPasswordInputBox() {

        registerPage.enterDifferentPassword();
    }

    @Then("password doesn't match error should be displayed")
    public void passwordDoesnTMatchErrorShouldBeDisplayed() {

        registerPage.verifyMismatchingErrorIsDisplayed();
    }

    @And("enters a password with a length of {int}")
    public void entersAPasswordWithALengthOfLength(int length) {

        registerPage.enterPasswordLessThan(length);
    }

    @And("enters same password in confirm password input")
    public void entersSamePasswordInConfirmPasswordInput() {

        registerPage.enterSamePassword();
    }

    @Then("password should be at least {int} characters long error is displayed under {string} input")
    public void passwordShouldBeAtLeastCharactersLongErrorIsDisplayedUnderInput(int length, String inputboxName) {

        registerPage.verifyErrorMessageIsDisplayed(inputboxName);
    }

    @And("enters {string} types into email input box")
    public void entersTypes(String invalidEmailType) {

        registerPage.enterInvalidEmail(invalidEmailType);
    }

    @When("user enters {string} types into confirm email input box")
    public void userEntersTypesIntoConfirmEmailInputBox(String invalidEmailType) {

        registerPage.enterInvalidEmailIntoConfirmEmail(invalidEmailType);
    }

    @And("user enters a valid email address")
    public void userEntersAValidEmailAddress() {

        registerPage.getRandomEmailAndInsert();
    }

    @And("user enters a valid password as {string}")
    public void userEntersAValidPasswordAs(String password) {

        registerPage.enterPasswordAs(password);
    }

    @Then("verify {string} warning is displayed")
    public void verifyWarningIsDisplayed(String passwordStrength) {

        registerPage.verifyPasswordStrengthIsDisplayed(passwordStrength);
    }
}

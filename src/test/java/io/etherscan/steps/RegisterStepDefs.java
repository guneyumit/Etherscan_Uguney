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

    @Given("user goes to the register page")
    public void userGoesToTheRegisterPage() {

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

    @Then("{string} message should be displayed")
    public void messageShouldBeDisplayed() {

        registerPage.verifyRegistrationMessageIsDisplayed();
    }
}

package io.etherscan.pagesOfEtherscan;

import io.etherscan.Utilities.ConfigReader;
import io.etherscan.Utilities.WebUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Random;

import static io.etherscan.Utilities.Driver.driver;
import static org.junit.Assert.*;

public class RegisterPage {

    public RegisterPage() {
        PageFactory.initElements(driver(), this);
    }

    String newEmailAddress, winHandleOfTempMail, winHandleOfRegisterPage;
    ArrayList<String> tabList;

    public static String validUsername, validEmail, invalidUsername, invalidEmail;

    public static int validPassword;

    public static String password;


    @FindBy(id = "ContentPlaceHolder1_txtUserName-error")
    public WebElement usernameError;

    @FindBy(id = "ContentPlaceHolder1_txtEmail-error")
    public WebElement emailError;

    @FindBy(id = "ContentPlaceHolder1_txtConfirmEmail-error")
    public WebElement confirmEmailError;

    @FindBy(id = "ContentPlaceHolder1_txtPassword-error")
    public WebElement passwordError;

    @FindBy(id = "ContentPlaceHolder1_txtPassword2-error")
    public WebElement confirmPasswordError;

    @FindBy(id = "ctl00$ContentPlaceHolder1$MyCheckBox-error")
    public WebElement IAgreeToTheTermsAndCondError;


    @FindBy(css = "[id='btnCookie']")
    private WebElement acceptCookiesButton;

    @FindBy(xpath = "//p[.='Fill out the form to get started.']")
    private WebElement fillOutForm;

    @FindBy(css = "[id='ContentPlaceHolder1_txtUserName']")
    private WebElement usernameInput;

    @FindBy(css = "[id='ContentPlaceHolder1_txtEmail']")
    private WebElement emailAddressInput;

    @FindBy(css = "[id='ContentPlaceHolder1_txtConfirmEmail']")
    private WebElement confirmEmailAddressInput;

    @FindBy(css = "[id='ContentPlaceHolder1_txtPassword']")
    private WebElement passwordInput;

    @FindBy(css = "[id='ContentPlaceHolder1_txtPassword2']")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//div/div/input[@id='ContentPlaceHolder1_MyCheckBox']")
    private WebElement IAgreeTermsAndConditions;

    @FindBy(xpath = "//div/div/input[@id='ContentPlaceHolder1_SubscribeNewsletter']")
    private WebElement IAgreeSubscription;

    @FindBy(id = "ContentPlaceHolder1_btnRegister")
    private WebElement submitButton;

    @FindBy(xpath = "//div[contains(text(),'Invalid captcha')]")
    private WebElement invalidCaptchaError;

    @FindBy(xpath = "//div[normalize-space(text())='Your account registration has been submitted and is pending email verification']")
    public WebElement successfulRegistrationMessage;


    public void verifyRegistrationPageLoaded() {
        String registrationPageTitle = "Etherscan Registration Page";

        assertEquals(registrationPageTitle, driver().getTitle());
        assertTrue(fillOutForm.isDisplayed());
    }


    public void acceptCookies() {

        acceptCookiesButton.click();
    }

    public void getTemporaryMail() {

        driver().get(ConfigReader.get("tempMailWebsiteUrl"));
        newEmailAddress = driver().findElement(By.id("i-email")).getAttribute("value");
        System.out.println("newEmail = " + newEmailAddress);
        winHandleOfTempMail = driver().getWindowHandle();
    }


    public void openANewTab() {

        ((JavascriptExecutor) driver()).executeScript("window.open()");
        WebUtils.waitFor(1);
        tabList = new ArrayList<String>(driver().getWindowHandles());
        driver().switchTo().window(tabList.get(1));
    }

    public void goToRegisterPage() {

        WebUtils.waitFor(1);
        driver().get(ConfigReader.get("url"));
    }

    public void enterTheInformationNeeded() {

        winHandleOfRegisterPage = driver().getWindowHandle();

        usernameInput.clear();
        usernameInput.sendKeys("etherscantest" + new Random().nextInt(10000));
        WebUtils.waitFor(1);
        emailAddressInput.clear();
        emailAddressInput.sendKeys(newEmailAddress);
        WebUtils.waitFor(1);
        confirmEmailAddressInput.clear();
        confirmEmailAddressInput.sendKeys(newEmailAddress);
        WebUtils.waitFor(1);
        passwordInput.clear();
        passwordInput.sendKeys("pw1234567");
        WebUtils.waitFor(1);
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys("pw1234567");
        WebUtils.waitFor(1);
    }

    public void verifyCheckboxIsNotSelected(String checkbox) {

        switch (checkbox) {
            case "Terms and Conditions":
                assertFalse(IAgreeTermsAndConditions.isSelected());
                break;
            case "Subscription":
                assertFalse(IAgreeSubscription.isSelected());
                break;
        }
    }

    public void markTheCheckbox(String checkbox) {

        switch (checkbox) {
            case "Terms and Conditions":
                JavascriptExecutor jse = (JavascriptExecutor) driver();
                jse.executeScript("arguments[0].click();", IAgreeTermsAndConditions);
                break;
            case "Subscription":
                JavascriptExecutor jse2 = (JavascriptExecutor) driver();
                jse2.executeScript("arguments[0].click();", IAgreeSubscription);
                break;
        }
    }

    public void hitTheSubmitButton() {

        submitButton.click();
    }

    public void verifyRegistrationMessageIsDisplayed() {

        Assert.assertTrue(successfulRegistrationMessage.isDisplayed());
    }

    public void getRandomUsernameAndInsert() {

        validUsername = WebUtils.generateAlphaNumericUsername(10);
        usernameInput.sendKeys(validUsername);
    }

    public void getRandomEmailAndInsertTwice() {

        validEmail = WebUtils.generateEmailAddress();
        emailAddressInput.sendKeys(validEmail);
        confirmEmailAddressInput.sendKeys(validEmail);
    }

    public void getRandomPasswordAndInsertTwice() {

        validPassword = new Random().nextInt(1000000) + 10000;
        passwordInput.sendKeys(Integer.toString(validPassword));
        confirmPasswordInput.sendKeys(Integer.toString(validPassword));
    }

    public void verifySeeingInvalidCaptchaError() {

        assertTrue(invalidCaptchaError.isDisplayed());
    }

    public void verifyAllErrorMessagesAreDisplayed() {

        assertTrue(usernameError.isDisplayed());
        assertTrue(emailError.isDisplayed());
        assertTrue(confirmEmailError.isDisplayed());
        assertTrue(passwordError.isDisplayed());
        assertTrue(confirmPasswordError.isDisplayed());
        assertTrue(IAgreeToTheTermsAndCondError.isDisplayed());
    }

    public void enterInvalidUsernames(String username) {

        usernameInput.sendKeys(username);
    }

    public void verifyErrorMessageIsDisplayed(String inputboxName) {

        switch (inputboxName) {
            case "username":
                assertTrue(usernameError.isDisplayed());
                break;
            case "email":
                assertTrue(emailError.isDisplayed());
                break;
            case "confirm email":
                assertTrue(confirmEmailError.isDisplayed());
                break;
            case "password":
                assertTrue(passwordError.isDisplayed());
                break;
            case "confirm password":
                assertTrue(confirmPasswordError.isDisplayed());
                break;
            case "Terms and Conditions":
                assertTrue(IAgreeToTheTermsAndCondError.isDisplayed());
                break;
        }

    }

    public void enterUsernameLongerThanExpected(int length) {

        String randomUsername = WebUtils.generateAlphaNumericUsername(length + 7);
        usernameInput.sendKeys(randomUsername);
    }

    public void verifyAvailableUsernameEquals30(int length) {

        assertEquals(length, usernameInput.getAttribute("value").length());
    }

    public void enterPassword() {

        password = "asdf1234";
        passwordInput.sendKeys(password);
    }

    public void enterDifferentPassword() {

        confirmPasswordInput.sendKeys(password + "extra");
    }

    public void verifyMismatchingErrorIsDisplayed() {

        Assert.assertEquals("Password does not match, please check again.",confirmPasswordError.getText());
    }
}

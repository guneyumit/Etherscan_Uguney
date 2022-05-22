package io.etherscan.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

import static io.etherscan.Utilities.Driver.driver;

public class WebUtils {

    private static final Random random = new Random();


    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForMilliSeconds(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForPresenceOfElement(By by, long time) {
        new WebDriverWait(driver(), time).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver()).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'end', inline: 'nearest'});", element);
    }

    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(driver(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(driver(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) driver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", element);
    }

    public static String generateAlphaNumericUsername(int length){
        String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder username = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            username.append(chars.charAt(random.nextInt(chars.length())));
        }
        System.out.println(username.toString());
        return username.toString();
    }
    public static String generateEmailAddress(){
        String chars = "0123456789abcdefghijklmnopqrstuvwxyz";
        StringBuilder email = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            email.append(chars.charAt(random.nextInt(chars.length())));
        }
        email.append("@hello.hello");
        return email.toString();
    }
}

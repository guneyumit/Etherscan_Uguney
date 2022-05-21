package io.etherscan.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.etherscan.Utilities.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

import static io.etherscan.Utilities.Driver.driver;

public class setUp {

    @Before
    public void setup(){
        driver().manage().window().maximize();
        driver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }


    @After
    public void finish(Scenario scenario){

        if(scenario.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) driver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "image");
        }
        Driver.closeDriver();
    }
}

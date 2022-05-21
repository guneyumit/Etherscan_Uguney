package io.etherscan.runTests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = "io/etherscan/steps",
        stepNotifications = true,
        dryRun = false,
        tags = "@try"
)

public class TestRunner {
}

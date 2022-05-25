package io.etherscan.runTests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber.json",
                "html:target/default-html-reports",
                "rerun:target/rerun.txt"},
        features = "src/test/resources",
        glue = "io/etherscan/steps",
        stepNotifications = true,
        dryRun = false,
        tags = "@smoke"
)

public class TestRunner {
}

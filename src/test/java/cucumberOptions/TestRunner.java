package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",
glue = { "stepDefinations" },
plugin = { "json:target/jsonReports/cucumber-report.json", "html:target/cucumber-report/cucumber.html"},
tags =  "@Regression"
)
public class TestRunner {

}

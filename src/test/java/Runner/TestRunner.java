package Runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features",
		glue = "StepDefinitions",
		plugin = {"pretty", 
				  "html:target/cucumber-reports.html", 
				  "junit:target/cucumber-reports.xml",
				  "json:target/cucumber-reports.json"}, // Added for advanced reporting
		monochrome = true
		)
public class TestRunner {

}
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
				  "json:target/cucumber-reports/cucumber.json", // MUST match pom.xml input
				  "rerun:target/failed.txt"},
		monochrome = true
		//tags = "@focus" // Note: tags here will be OVERRIDDEN by your Maven Profile tags (-PFocus)
		)
public class TestRunner {

}
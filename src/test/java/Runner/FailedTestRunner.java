package Runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "@target/failed.txt", //Executes failed scenarios only
		glue = "StepDefinitions", 
		plugin = { "pretty" }
)
public class FailedTestRunner {

}

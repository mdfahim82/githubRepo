package stepdefinations;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
	    features = "src/test/resources/features", // Path to your feature files
	    glue = {"stepdefinations"}, // Package where your step definitions are located
	    plugin = {"pretty", "html:Results/cucumber-reports"}
		)
public class TestRunner extends AbstractTestNGCucumberTests{

	public TestRunner() {
		
	}

}

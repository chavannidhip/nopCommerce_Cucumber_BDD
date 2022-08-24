package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
//import cucumber.api.*;


@RunWith(Cucumber.class)
@CucumberOptions(
		features=".//Features",
		glue="stepDefinitions",
		stepNotifications=true,
		dryRun=false,
		plugin= {"pretty","html:target/test-output.html"},
		monochrome=true,
		tags= "@sanity and  not @regression"
		)
public class TestRun {

}

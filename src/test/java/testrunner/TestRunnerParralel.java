package testrunner;


import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/resources/features/Demo1.feature",
        glue = "stepdefinitions",
        plugin = {"pretty","html:target/site/cucumber-report-default","json:target/site/cucumber.json"},
        snippets = SnippetType.CAMELCASE)

public class TestRunnerParralel {
}

package testrunner;


import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/resources/features",
        glue = "stepdefinitions",
        plugin = {"pretty","html:target/site/cucumber-report-default","json:target/site/cucumber.json","com.epam.reportportal.cucumber.ScenarioReporter"},
        snippets = SnippetType.CAMELCASE,
        tags = {"@kichban1"})

public class TestRunnerReportPortal {
}

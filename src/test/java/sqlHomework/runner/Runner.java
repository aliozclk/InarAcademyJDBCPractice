package sqlHomework.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/features",
        glue = {"sqlHomework/runner/stepDefs"},
        plugin = "json:target/jsonReports/cucumber-report.json",
        tags = "@db"
)
public class Runner {
}

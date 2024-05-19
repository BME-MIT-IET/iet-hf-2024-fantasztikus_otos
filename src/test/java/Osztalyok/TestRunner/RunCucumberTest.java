package Osztalyok.TestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // path to your feature files
        glue = "Osztalyok.StepDefinitions", // package where step definitions are located
        plugin = {"pretty"}
)
public class RunCucumberTest {
}

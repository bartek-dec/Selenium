package Cucumber.zadanie6_And_7;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/Cucumber/Features/zadanie6/",
        plugin = {"pretty", "html:out"})
public class NewAccountTest {
}

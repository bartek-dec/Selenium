package Cucumber.LogInToAccount;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/Cucumber/Features/zadanie_dodatkowe_6/positive/",
        plugin = {"pretty", "html:out"})
public class ZadanieDodatkowe_6_PositiveTest {
}

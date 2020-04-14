package POP.LoginToAccount;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/Cucumber/Features/POP/login.feature",
        plugin = {"pretty", "html:out"})
public class LoginToAccountTest {

}

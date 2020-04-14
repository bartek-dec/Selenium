package POP.LoginToAccount;

import POP.AccountPage;
import POP.LoginPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginToAccount {
    private WebDriver driver;

    @Given("an open browser with https://prod-kurs\\.coderslab\\.pl/index\\.php\\?controller=authentication&back=my-account")
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication&back=my-account");
    }

    @When("insert correct email (.*) and password (.*)")
    public void enterCredentials(String email, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputData(email, password);
    }

    @And("click submit button")
    public void submit() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn();
    }

    @Then("user name (.*) displayed")
    public void checkUserName(String userName) {
        AccountPage accountPage = new AccountPage(driver);

        Assert.assertEquals(userName, accountPage.getUserName());
        driver.close();
    }
}

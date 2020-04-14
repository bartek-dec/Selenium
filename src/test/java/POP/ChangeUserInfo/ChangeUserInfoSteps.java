package POP.ChangeUserInfo;

import POP.AccountPage;
import POP.LoginPage;
import POP.UserInfoPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ChangeUserInfoSteps {

    private WebDriver driver;
    private UserInfoPage userInfoPage;
    private AccountPage accountPage;
    private LoginPage loginPage;

    @Given("User is logged in to CodersLab shop")
    public void userIsLoggedToAccount() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication&back=my-account");
        userInfoPage = new UserInfoPage(driver);
        accountPage = new AccountPage(driver);
        loginPage = new LoginPage(driver);

        loginPage.inputData("bartek@mail.com", "qwerty");
        loginPage.signIn();
    }

    @When("User goes to UserInformationPage")
    public void userGoesToInfoPage() {
        accountPage.getIntoInformation();
    }

    @And("User sign up for newsletter")
    public void signUpForNewsletter() {
        userInfoPage.signInForNewsletter();
    }

    @And("Change birthday")
    public void userChangesBirthdate() {
        userInfoPage.setBirthdate("05/31/2000");
    }

    @And("User saves information")
    public void userSavesInfo() {
        userInfoPage.submitUserInfo();
        userInfoPage.saveInfo();
    }

    @Then("User sees (.*)")
    public void userSees(String message) {
        Assert.assertEquals(message, userInfoPage.getUpdateInformation());
    }

    @And("user closes browser")
    public void tearDown() {
        driver.close();
    }
}

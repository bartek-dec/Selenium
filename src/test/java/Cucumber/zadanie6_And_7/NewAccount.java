package Cucumber.zadanie6_And_7;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class NewAccount {

    private WebDriver driver;

    @Given("an open browser with https://qloapps.coderslab.pl/")
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://qloapps.coderslab.pl/");
    }

    @When("a Sign in button is pressed")
    public void pressSignIn() {
        WebElement button = driver.findElement(By.cssSelector("a.user_login.navigation-link"));
        button.click();
    }

    @And("email address (.*) is provided")
    public void enterEmail(String email) {
        WebElement field = driver.findElement(By.id("email_create"));
        field.sendKeys(email);
    }

    @And("create an account button is pressed")
    public void pressCreateAccount() {
        WebElement button = driver.findElement(By.id("SubmitCreate"));
        button.click();
    }

    @And("personal information (.*), (.*), (.*), (.*), (.*), (.*) are provided")
    public void enterPersonalInformation(String fName,
                                         String lName,
                                         String pass,
                                         String day,
                                         String month,
                                         String year) {
        WebElement title = driver.findElement(By.id("id_gender1"));
        WebElement firstName = driver.findElement(By.id("customer_firstname"));
        WebElement lastName = driver.findElement(By.id("customer_lastname"));
        WebElement password = driver.findElement(By.id("passwd"));
        Select days = new Select(driver.findElement(By.id("days")));
        Select months = new Select(driver.findElement(By.id("months")));
        Select years = new Select(driver.findElement(By.id("years")));

        title.click();
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        password.sendKeys(pass);
        days.selectByValue(day);
        months.selectByValue(month);
        years.selectByValue(year);
    }

    @And("register button is pressed")
    public void pressRegister() {
        WebElement register = driver.findElement(By.id("submitAccount"));
        register.click();
    }

    @Then("account has been created confirmation is displayed")
    public void inspectConfirmation() {
        Assert.assertTrue(driver.findElement(By.cssSelector("p.alert.alert-success"))
                .isDisplayed());
    }

    @And("close the browser")
    public void closeBrowser() {
        driver.close();
    }
}

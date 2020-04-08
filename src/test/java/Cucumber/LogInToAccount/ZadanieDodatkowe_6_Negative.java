package Cucumber.LogInToAccount;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ZadanieDodatkowe_6_Negative {

    private WebDriver driver;

    @Given("open browser with https://prod-kurs\\.coderslab\\.pl/index\\.php\\?controller=authentication&back=my-account/")
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication&back=my-account");
    }

    @When("incorrect (.*) or (.*) are provided")
    public void fillInData(String email, String password) {
        List<WebElement> inputs = driver.findElements(By.className("form-control"));

        WebElement emailAddress = inputs.get(0);
        emailAddress.sendKeys(email);

        WebElement pass = inputs.get(1);
        pass.sendKeys(password);
    }

    @And("button signIn pressed")
    public void pressSignIn() {
        WebElement signIn = driver.findElement(By.id("submit-login"));
        signIn.click();
    }

    @Then("error showed")
    public void displayAccount() {
        Assert.assertTrue(driver.findElement(By.cssSelector("li.alert.alert-danger")).isDisplayed());
    }

    @And("browser closed")
    public void signOutAndClose() {
        driver.close();
    }
}

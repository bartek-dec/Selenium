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

public class ZadanieDodatkowe_6_Positive {

    private WebDriver driver;

    @Given("an open browser with https://prod-kurs\\.coderslab\\.pl/index\\.php\\?controller=authentication&back=my-account/")
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication&back=my-account");
    }

    @When("an (.*) and (.*) are provided")
    public void fillInData(String email, String password) {
        List<WebElement> inputs = driver.findElements(By.className("form-control"));

        WebElement emailAddress = inputs.get(0);
        emailAddress.sendKeys(email);

        WebElement pass = inputs.get(1);
        pass.sendKeys(password);
    }

    @And("button sign in pressed")
    public void pressSignIn() {
        WebElement signIn = driver.findElement(By.id("submit-login"));
        signIn.click();
        sleepThread();
    }

    @Then("users account is displayed")
    public void displayAccount() {
        Assert.assertTrue(driver.findElement(By.className("links")).isDisplayed());
    }

    @And("user is sign out and browser closed")
    public void signOutAndClose() {
        WebElement signOut = driver.findElement(By.cssSelector("a.logout.hidden-sm-down"));
        signOut.click();

        driver.close();
    }

    private void sleepThread() {
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

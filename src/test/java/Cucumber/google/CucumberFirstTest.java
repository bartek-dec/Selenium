package Cucumber.google;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CucumberFirstTest {
    private WebDriver driver;

    @Given("an open browser with google.com")
    public void openGoogleSearch() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.google.com");
    }

    @When("a keyword (.*) is entered in input field")
    public void enterKeyword(String keyword) {
        WebElement element = driver.findElement(By.name("q"));
        element.clear();
        element.sendKeys(keyword);
        element.submit();
    }

    @Then("the first one should contain (.*)")
    public void theFirstOneShouldContainKeyword(String expectedText) {
        System.out.println(expectedText);
    }

    @And("close browser")
    public void closeBrowser() {
        driver.close();
    }
}


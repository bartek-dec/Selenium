package POP.NewAddress;

import POP.AccountPage;
import POP.AddressPage;
import POP.LoginPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class AddNewAddress {

    private WebDriver driver;

    @Given("user logged in to his account")
    public void logInToAccount() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication&back=my-account");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputData("bartek@mail.com", "qwerty");
        loginPage.signIn();
    }

    @When("user goes to addresses page")
    public void goToAddressPage() {
        AccountPage accountPage = new AccountPage(driver);
        accountPage.getIntoAddress();
    }

    @And("provides (.*) and (.*) and (.*) and (.*) and saves")
    public void fillInAddressData(String address, String city, String postalCode, String country) {
        AddressPage addressPage = new AddressPage(driver);

        try {
            addressPage.createNewAddress();

            addressPage.setAddress(address);
            addressPage.setCity(city);
            addressPage.setZipCode(postalCode);
            addressPage.setCountry(country);

            addressPage.save();
        } catch (NoSuchElementException e) {
            addressPage.setAddress(address);
            addressPage.setCity(city);
            addressPage.setZipCode(postalCode);
            addressPage.setCountry(country);

            addressPage.save();
        }
    }

    @Then("new address is added")
    public void createAddress() {
        AddressPage addressPage = new AddressPage(driver);

        Assert.assertTrue(addressPage.getAlertSuccess().isDisplayed());
    }

    @And("user closes the browser")
    public void closeBrowser() {
        driver.close();
    }

}

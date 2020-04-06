package registration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class ZadanieDodatkowe_2_Test {

    private WebDriver driver;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Random random;
    private StringBuilder builder;
    private int randomNumber;
    private String[] addresses = {"Address 1", "Address 2", "Address 3", "Address 4", "Address 5"};
    private String[] cities = {"Warszawa", "Lublin", "Wrocław", "Kraków", "Gdańsk"};
    private String[] postalCodes = {"12345", "56789", "13579", "24680", "34596"};

    @Before
    public void setUp() {
        //prepare Selenium properties
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication&create_account=1");

        //initiate user data
        firstName = "Janek";
        lastName = "Kowalski";
        password = "password123";

        email = generateEmail();
    }

    private String generateEmail() {
        //generate random number
        random = new Random();
        randomNumber = random.nextInt(5);

        //create email string -> userName123@mail.com
        builder = new StringBuilder();
        builder.append(firstName).append(randomNumber).append("@mail.com");

        return builder.toString();
    }

    @Test
    public void testRegistrationFOrm() {

        //loop is used to register new user
        boolean flag = true;
        while (flag) {
            List<WebElement> elements = driver.findElements(By.cssSelector("div.form-group.row "));

            //select web elements
            WebElement socialTitle = elements.get(0).findElement(By.className("custom-radio"));
            WebElement firstName = elements.get(1).findElement(By.className("form-control"));
            WebElement lastName = elements.get(2).findElement(By.className("form-control"));
            WebElement email = elements.get(3).findElement(By.className("form-control"));
            WebElement password = elements.get(4).findElement(By.tagName("input"));

            WebElement button = driver.findElement(By
                    .cssSelector("button.btn.btn-primary.form-control-submit.float-xs-right"));

            //insert user data
            socialTitle.click();

            fillInInputs(firstName, this.firstName);
            fillInInputs(lastName, this.lastName);
            fillInInputs(email, this.email);
            fillInInputs(password, this.password);

            button.click();

            //checking if alert shows
            try {
                WebElement alert = driver.findElement(By.className("help-block"));

                if (alert.isDisplayed()) {//if alert shows, generate new email and repeat loop
                    this.email = generateEmail();
                }
            } catch (NoSuchElementException e) {//if alert doesn't show (new user has been created), finish loop
                flag = false;
            }
        }

        //Navigate to New address page
        WebElement account = driver.findElement(By.className("account"));
        account.click();

        WebElement addressLink = driver.findElement(By.id("address-link"));
        addressLink.click();

        //select web elements
        List<WebElement> elements = driver.findElements(By.cssSelector("div.form-group.row"));

        WebElement address = elements.get(5).findElement(By.className("form-control"));
        WebElement postalCode = elements.get(7).findElement(By.className("form-control"));
        WebElement city = elements.get(8).findElement(By.className("form-control"));
        Select country = new Select(driver.findElement(By.name("id_country")));
        WebElement save = driver.findElement(By.cssSelector("button.btn.btn-primary.float-xs-right"));

        //fill in address details
        int addressIndex = randomNumber % addresses.length;
        address.sendKeys(addresses[addressIndex]);

        int cityIndex = randomNumber % cities.length;
        city.sendKeys(cities[cityIndex]);

        int postalIndex = randomNumber % postalCodes.length;
        postalCode.sendKeys(postalCodes[postalIndex]);

        country.selectByValue("17");

        save.click();

        //Assert
        Assert.assertTrue(driver.findElement(By
                .cssSelector("div.col-lg-4.col-md-6.col-sm-6")).isDisplayed());
    }

    private void fillInInputs(WebElement element, String keys) {
        element.clear();
        element.sendKeys(keys);
    }

    @After
    public void tearDown() {
        driver.close();
    }
}

package order;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ZadanieDodatkowe_4_Test {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://prod-kurs.coderslab.pl/index.php");
    }

    @Test
    public void putOrderTest() {
        int counter = 0;
        int index = 0;

        //add two products to the basket
        while (counter < 3) {
            //go from main page to "all products" page
            WebElement allProductsButton = driver.findElement(By.cssSelector("a.all-product-link.float-xs-left.float-md-right.h4"));
            allProductsButton.click();

            //select all products
            List<WebElement> products = driver.findElements(By.cssSelector("a.thumbnail.product-thumbnail"));

            //select product form list
            WebElement product = products.get(index);
            product.click();

            //check if product is available
            String availability = driver.findElement(By.id("product-availability")).getText();

            //if not available -> repeat previous steps with next product from list
            if (!availability.equals("")) {
                index++;
                WebElement goToHomePage = driver.findElement(By.cssSelector("img.logo.img-responsive"));
                goToHomePage.click();
            } else {
                //if product available -> add to basket and repeat previous steps
                counter++;
                index++;
                WebElement addToChart = driver.findElement(By.cssSelector("button.btn.btn-primary.add-to-cart"));
                addToChart.click();

                if (counter < 3) {
                    WebElement continueShopping = driver.findElement(By.cssSelector("button.btn.btn-secondary"));
                    continueShopping.click();

                    WebElement goToHomePage = driver.findElement(By.cssSelector("img.logo.img-responsive"));
                    goToHomePage.click();
                } else {
                    //when to products in the basket -> got to the basket
                    WebElement checkout = driver.findElement(By.cssSelector("a.btn.btn-primary"));
                    checkout.click();
                }
            }
        }

        //compare number of products in the basket with number of added (during loop) products
        String orderSummary = driver.findElement(By.cssSelector("span.label.js-subtotal")).getText();
        String[] strings = orderSummary.split("");
        int numberOfProducts = Integer.parseInt(strings[0]);

        Assert.assertEquals(numberOfProducts, counter);
    }

    @After
    public void tearDown() {
        driver.close();
    }
}

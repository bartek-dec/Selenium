package priceFiltr;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ZadanieDodatkowe_3_Test {

    private WebDriver driver;
    private final String sectionName = "price";

    @Before
    public void setUp() {
        //prepare Selenium properties
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://prod-kurs.coderslab.pl/index.php?id_category=2&controller=category");

    }

    @Test
    public void priceFilterTest() {
        //select sections from side menu
        List<WebElement> sections = driver.findElements(By.cssSelector("section.facet.clearfix"));

        //find price section
        WebElement priceSection = findSection(sections, sectionName);

        //select labels from price section
        List<WebElement> labels = priceSection.findElements(By.className("facet-label"));

        //choose price range which will be tested
        WebElement label = choosePriceRange(labels);

        //select checkbox
        WebElement checkbox = label.findElement(By.tagName("input"));

        //calculate price range
        List<Double> range = getPriceRange(label);

        //click selected price range
        checkbox.click();

        //wait until website refresh itself
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.urlContains("Price")));

        //select filtered products
        List<WebElement> foundProducts = driver.findElements(By.className("thumbnail-container"));

        //get price range (lower and upper)
        Double lowerBound = range.get(0);
        Double upperBound = range.get(1);

        //check each element found whether it is within price range or not
        for (WebElement product : foundProducts) {
            WebElement price = product.findElement(By.className("price"));
            Double productPrice = convertNumber(price.getText());

            if (productPrice < lowerBound) {
                Assert.fail();
            }

            if (productPrice > upperBound) {
                Assert.fail();
            }
        }
    }

    private WebElement findSection(List<WebElement> list, String s) {
        for (WebElement element : list) {
            WebElement sectionTitle = element.findElement(By
                    .cssSelector("p.h6.facet-title.hidden-sm-down"));
            if (s.equals(sectionTitle.getText().toLowerCase())) {
                return element;
            }
        }
        return null;
    }

    private WebElement choosePriceRange(List<WebElement> list) {
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        int index = randomNumber % list.size();

        return list.get(index);
    }

    private List<Double> getPriceRange(WebElement element) {
        String[] strings = element.getText().split(" ");
        List<Double> priceRange = new ArrayList<>();

        priceRange.add(convertNumber(strings[0]));
        priceRange.add(convertNumber(strings[2]));

        return priceRange;
    }

    private Double convertNumber(String s) {
        String stringNumber = s.substring(1);
        return Double.parseDouble(stringNumber);
    }

    @After
    public void tearDown() {
        driver.close();
    }
}

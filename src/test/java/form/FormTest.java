package form;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class FormTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://katalon-test.s3.amazonaws.com/demo-aut/dist/html/form.html");
    }

    @Test
    public void testForm() {
        //Arrange
        List<WebElement> elements = driver.findElements(By.className("form-group"));

        WebElement firstName = elements.get(0).findElement(By.className("form-control"));
        WebElement lastName = elements.get(1).findElement(By.className("form-control"));
        WebElement gender = elements.get(2).findElement(By.className("radio-inline"));
        WebElement dateOfBirth = elements.get(3).findElement(By.className("form-control"));
        WebElement address = elements.get(4).findElement(By.className("form-control"));
        WebElement email = elements.get(5).findElement(By.className("form-control"));
        WebElement password = elements.get(6).findElement(By.className("form-control"));
        WebElement company = elements.get(7).findElement(By.className("form-control"));
        List<WebElement> waysOfDevelopment = driver.findElements(
                By.cssSelector(".checkbox label"));
        WebElement comment = elements.get(11).findElement(By.className("form-control"));
        WebElement submit = elements.get(12).findElement(By.id("submit"));

        //Act
        WebElement firstNameLabel = driver.findElement(By.xpath("//*[@for='first-name']"));
        String name = firstName.getTagName();
        if (firstName.isEnabled()) {
            firstName.sendKeys("Karol");
        } else {
            Assert.fail();
        }

        System.out.println(firstNameLabel.getText() + " : " + firstName.getAttribute("value"));

        lastName.sendKeys("Kowalski");
        gender.click();
        dateOfBirth.sendKeys("05/22/2010");
        address.sendKeys("Prosta 51");
        email.sendKeys("karol.kowalski@mailinator.com");
        password.sendKeys("Pass123");
        company.sendKeys("Coders Lab");

        List<String> waysToChoose = new ArrayList<>();
        waysToChoose.add("Take online courses");
        waysToChoose.add("Join tech cons");
        waysToChoose.add("Via discovery and experiment");
        for (WebElement checkbox : waysOfDevelopment) {
            String text = checkbox.getText();
            if (waysToChoose.contains(text))
                checkbox.click();
        }
        comment.sendKeys("To jest mój pierwszy automat testowy");

        submit.click();

        //Assert
        Assert.assertTrue(elements.get(12).findElement(By
                .id("submit-msg")).isDisplayed());

        Assert.assertTrue(elements.get(12).findElement(By
                .id("submit-msg")).getText()
                .contains("Successfully submitted!"));

    }

    @After
    public void rearDown() {
        driver.close();
    }
}

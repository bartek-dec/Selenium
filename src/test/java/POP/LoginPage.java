package POP;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {

    private WebDriver driver;

    @FindBy(className = "form-control")
    List<WebElement> inputs;
    @FindBy(id = "submit-login")
    private WebElement signIn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void inputData(String email, String password) {
        inputs.get(0).sendKeys(email);
        inputs.get(1).sendKeys(password);
    }

    public void signIn() {
        signIn.click();
    }
}

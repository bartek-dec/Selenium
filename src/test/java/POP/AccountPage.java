package POP;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AccountPage {

    private WebDriver driver;

    @FindBy(css = "a.col-lg-4.col-md-6.col-sm-6.col-xs-12")
    private List<WebElement> links;

    @FindBy(id = "identity-link")
    private WebElement information;

    @FindBy(className = "account")
    private WebElement userButton;

    public AccountPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void getIntoAddress() {
        links.get(1).click();
    }

    public void getIntoInformation() {
        information.click();
    }

    public String getUserName() {
        return userButton.getText();
    }
}

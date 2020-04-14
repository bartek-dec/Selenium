package POP;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserInfoPage {

    private WebDriver driver;

    @FindBy(name = "birthday")
    private WebElement birthdayInput;

    @FindBy(name = "newsletter")
    private WebElement newsletterCheckbox;

    @FindBy(css = ".btn.btn-primary.form-control-submit.float-xs-right")
    private WebElement submitButton;

    @FindBy(css = ".alert.alert-success")
    private WebElement successInformation;

    @FindBy(name = "password")
    private WebElement passwordInput;

    public UserInfoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void signInForNewsletter() {
        if (!newsletterCheckbox.isSelected()) {
            newsletterCheckbox.click();
        }
    }

    public void signOutFromNewsletter() {
        if (newsletterCheckbox.isSelected()) {
            newsletterCheckbox.click();
        }
    }

    public void setBirthdate(String birthdate) {
        birthdayInput.clear();
        birthdayInput.sendKeys(birthdate);
    }

    public void submitUserInfo() {
        passwordInput.sendKeys("qwerty");
    }

    public void saveInfo() {
        submitButton.click();
    }

    public String getUpdateInformation() {
        return successInformation.getText();
    }
}

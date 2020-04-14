package POP;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AddressPage {

    private WebDriver driver;

    @FindBy(css = "div.form-group.row")
    private List<WebElement> addressDetails;

    @FindBy(partialLinkText = "Create new address")
    private WebElement createNewAddressButton;

    @FindBy(css = "button.btn.btn-primary.float-xs-right")
    private WebElement save;

    @FindBy(css = "article.alert.alert-success")
    private WebElement alertSuccess;

    public AddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setAddress(String address) {
        addressDetails.get(5).findElement(By.className("form-control")).sendKeys(address);
    }

    public void setZipCode(String zipCode) {
        addressDetails.get(7).findElement(By.className("form-control")).sendKeys(zipCode);
    }

    public void setCity(String city) {
        addressDetails.get(8).findElement(By.className("form-control")).sendKeys(city);
    }

    public void setCountry(String value) {
        new Select(driver.findElement(By.name("id_country"))).selectByValue(value);
    }

    public void createNewAddress() {
        createNewAddressButton.click();
    }

    public WebElement getCreateNewAddressButton() {
        return createNewAddressButton;
    }

    public WebElement getAlertSuccess() {
        return alertSuccess;
    }

    public void save() {
        save.click();
    }
}

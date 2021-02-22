package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YandexLoginPage extends BasePage {

    @FindBy(xpath = "//*[@id='passp-field-login']")
    private WebElement loginField;

    @FindBy(xpath = "//*[@id='passp-field-passwd']")
    private WebElement passwordField;

    @FindBy(css = ".passp-sign-in-button > button")
    private WebElement signInButton;

    @FindBy(xpath = "//span[text()='Пропустить']")
    private WebElement skipPhoto;

    public boolean isInitialized() {
        return loginField.isDisplayed() && signInButton.isDisplayed();
    }

    public YandexLoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterLogin(String login) {
        driver.findElement(By.xpath("//*[@id='passp-field-login']")).sendKeys(login);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickSignInButton() {
        signInButton.click();
    }
}

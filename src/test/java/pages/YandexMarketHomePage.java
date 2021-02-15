package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageObject;

public class YandexMarketHomePage extends PageObject {

    @FindBy(linkText = "Войти")
    private WebElement loginButton;

    public YandexMarketHomePage(WebDriver driver) {
        super(driver);
    }

    public void clickLoginButton() {
        loginButton.click();
    }
}

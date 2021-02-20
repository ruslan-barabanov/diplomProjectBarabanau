package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YandexMarketHomePage extends PageObject {

    public YandexMarketHomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Войти")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(text(),'Компьютерная техника')]")
    private WebElement electronicButton;

    @FindBy(xpath = "//span[contains(text(),'Ноутбук ASUS')]")
    private WebElement laptop;

    public void choosingALaptop() {
        laptop.click();
    }

    public void clickElectronicButton() {
        electronicButton.click();
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String findСityMoskau() {
        return driver.findElement(By.xpath("//span[contains(text(),'Москва')]")).getText();
    }


}

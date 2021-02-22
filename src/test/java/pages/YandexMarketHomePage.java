package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class YandexMarketHomePage extends PageObject {

    public YandexMarketHomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Войти")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(text(),'Компьютерная техника')]")
    private WebElement electronicButton;

    @FindBy(xpath = "//span[contains(text(),'Ноутбук ')]")
    private WebElement laptop;

    @FindBy(xpath = "//input[@id='header-search']")
    private WebElement searchGeneral;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//span[@data-autotest-currency='₽']")
    private WebElement prices;

    @FindBy(xpath = "//span[contains(text(),'Игрова" +
            "я приставка')]")
    private WebElement sony;

    public void clickSony5(){
        sony.click();
    }

    public List<WebElement> allIphonePrices(){
        return driver.findElements(By.xpath("//span[@data-autotest-currency='₽']"));
    }
    public void searchAiphone(){
        searchGeneral.sendKeys("айфон");
    }
    public void searchSony5(){
        searchGeneral.sendKeys("Sony PlayStation");
    }

    public void clickSubmitButton(){
        submitButton.click();
    }

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

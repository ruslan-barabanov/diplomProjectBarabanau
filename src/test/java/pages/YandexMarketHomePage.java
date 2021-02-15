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

    @FindBy(xpath = "/html/body/div[3]/div[3]/noindex/div/div/div[2]/div[1]/div/button/span/div/div")
    private WebElement electronicButton;

    @FindBy(xpath = "//a[contains(text(),'Игрушки и игры')]")
    private WebElement gemeAndGames;


    public void clickGameAndGamesButton() {
        gemeAndGames.click();
    }

    public void clickElectronicButton() {
        electronicButton.click();
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String findСityMinsk(){
        return driver.findElement(By.xpath("//div[contains(text(),'В Минск мы пока не доставляем')]")).getText();
    }


}

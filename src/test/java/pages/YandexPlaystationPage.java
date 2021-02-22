package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YandexPlaystationPage extends BasePage {

    public YandexPlaystationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//img[@class='_2gUfndCf6w']")
    private WebElement sonyPicture;

    public String getNameProduct(){
        return driver.findElement(By.xpath("//h1[@data-tid='c0924aa2']")).getText();
    }

    public void sonyPictureClick(){
        sonyPicture.click();
    }
}

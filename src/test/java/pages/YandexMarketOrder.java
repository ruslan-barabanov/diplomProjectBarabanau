package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YandexMarketOrder extends PageObject {
    public YandexMarketOrder(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//span[contains(text(),'Добавить')]")
    private WebElement addLaptop;

    @FindBy(xpath = "//span[contains(text(),'Перейти')]")
    private WebElement goToCartButton;

    @FindBy(xpath = "//span[@class='b_12n3uzP6sY']")
    private WebElement quantity;

    public void goToCart(){
        goToCartButton.click();
    }
    public void addToCard() {
        addLaptop.click();
    }
    public String quantityOfGoods(){
        return quantity.getText();
    }
}

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

    @FindBy(xpath = "//div[@class='b_2ll3z2LP8N b_3FXWn4lKOO']//span[contains(text(),'+')]")
    private WebElement addNumberOfItemsOfGoods;

    public void addGoods(int quantity){
        int i = 1;
        while (i < quantity) {
            addNumberOfItemsOfGoods.click();
            i++;
        }

    }
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

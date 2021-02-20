package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.PathsProperties;
import utils.ScreenProperties;
import java.io.IOException;
import java.util.*;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class YandexMarketTest extends BaseTest {

    private final Properties properties = PathsProperties.readFile();
    private final String login = properties.getProperty("login");
    private final String password = properties.getProperty("password");
    private List<String> displayedCategoriesLinks = new ArrayList<>();

    @Test(description = "Открываем страницу и преходим на логин страницу")
    public void openLoginPage() {
        YandexMarketHomePage homePage = new YandexMarketHomePage(driver);
        homePage.clickLoginButton();
        switchToTheRightHandle();
        assertEquals(driver.getTitle(), "Авторизация");
    }

    @Test(description = "Авторизуемся")
    public void signIn() {
        YandexMarketHomePage homePage = new YandexMarketHomePage(driver);
        homePage.clickLoginButton();
        switchToTheRightHandle();
        YandexLoginPage loginPage = new YandexLoginPage(driver);
        assertTrue(loginPage.isInitialized());
        loginPage.enterLogin(login);
        loginPage.clickSignInButton();
        loginPage.enterPassword(password);
        loginPage.clickSignInButton();
        switchToTheRightHandle();
        YandexMarketHomeAuthorizedPage authorizedHomePage = new YandexMarketHomeAuthorizedPage(driver);
        assertTrue(authorizedHomePage.isAuthorized());
    }

    @Test(description = "Получаем список видимых категории и открываем страницу случайной категории")
    public void getAllDisplayedCategories() {
        YandexMarketHomeAuthorizedPage authorizedHomePage = new YandexMarketHomeAuthorizedPage(driver);
        displayedCategoriesLinks = authorizedHomePage.getDisplayedCategoryLinks();
        assertTrue(displayedCategoriesLinks.size() > 0);
        int randomInt = new Random().nextInt(displayedCategoriesLinks.size() - 1);
        String randomCategoryLink = displayedCategoriesLinks.get(randomInt);
        driver.get(randomCategoryLink);
        assertEquals(driver.getCurrentUrl(), randomCategoryLink);
    }

    @Test(description = "Смотрим компьютер в Москве")
    public void watchComputersInMoscow() {
        YandexMarketHomePage homePage = new YandexMarketHomePage(driver);
        homePage.clickElectronicButton();
        String moskau = homePage.findСityMoskau();
        assertEquals(moskau, "Москва");
    }

    @Test(description = "Заказываем компьютер в Москве")
    public void orderAComputerInMoscow() {
        YandexMarketHomePage homePage = new YandexMarketHomePage(driver);
        YandexMarketOrder order = new YandexMarketOrder(driver);
        homePage.clickElectronicButton();
        homePage.choosingALaptop();
        switchToTheRightHandle();
        order.addToCard();
        order.goToCart();
        String actualQuantity = order.quantityOfGoods();
        assertEquals("1", actualQuantity);
    }

    @Test(description = "Заказываем компьютер в Москве 5 штук")
    public void orderAComputerInMoscow5Phones() {
        YandexMarketHomePage homePage = new YandexMarketHomePage(driver);
        YandexMarketOrder order = new YandexMarketOrder(driver);
        homePage.clickElectronicButton();
        homePage.choosingALaptop();
        switchToTheRightHandle();
        order.addToCard();
        int i = 1;
        while (i < 5) {
            order.addGoods();
            i++;
        }
        order.goToCart();
        String actualQuantity = order.quantityOfGoods();
        assertEquals("5", actualQuantity);
    }

    @Test(description = "Находим самый дорогой айфон в Москве")
    public void findTheMostExpensivePhone() {
        YandexMarketHomePage homePage = new YandexMarketHomePage(driver);
        homePage.searchAiphone();
        homePage.clickSubmitButton();
        Set<String> webElementsList = new HashSet<>();
        List<WebElement> list = homePage.allIphonePrices();
        for (WebElement element : list) {
            webElementsList.add(element.getText());
        }
        System.out.println(Collections.max(webElementsList));
        Assert.assertNotNull(webElementsList);
    }

    @Test(description = "Находим sony playstation 5 и делаем скрин")
    public void findPlaystation5() throws IOException {
        YandexPlaystationPage playstationPage = new YandexPlaystationPage(driver);
        YandexMarketHomePage homePage = new YandexMarketHomePage(driver);
        homePage.searchSony5();
        homePage.clickSubmitButton();
        homePage.clickSony5();
        switchToTheRightHandle();
        playstationPage.sonyPictureClick();
        ScreenProperties.makeScreenPage(driver);
        String actualName = playstationPage.getNameProduct();
        String expectedName = "Игровая приставка Sony PlayStation 5 825 Гб";
        assertEquals(expectedName, actualName);
    }

    private void switchToTheRightHandle() {
        List<String> tabHandles = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabHandles.get(tabHandles.size() - 1));
    }
}

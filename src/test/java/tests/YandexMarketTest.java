package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.PathsProperties;
import utils.ScreenProperties;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class YandexMarketTest extends BaseTest {

    private final Properties properties = PathsProperties.readFile();
    private final String login = properties.getProperty("login");
    private final String password = properties.getProperty("password");
    private List<String> displayedCategoriesLinks = new ArrayList<>();
    private static final Logger log = Logger.getLogger(String.valueOf(YandexMarketTest.class));

    @Test(description = "Открываем страницу и преходим на логин страницу")
    public void openLoginPage() {
        YandexMarketHomePage homePage = new YandexMarketHomePage(driver);
        homePage.clickLoginButton();
        switchToTheRightHandle();
        assertEquals(driver.getTitle(), "Авторизация");
        log.info("Login page is open.");
        System.out.println(driver.getPageSource());
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
        log.info("Login entered.");
        loginPage.enterPassword(password);
        loginPage.clickSignInButton();
        switchToTheRightHandle();
        log.info("Password entered.");
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
        log.info("Opened a random category page.");
        assertEquals(driver.getCurrentUrl(), randomCategoryLink);
    }

    @Test(description = "Смотрим компьютер в Москве")
    public void watchComputersInMoscow() {
        YandexMarketHomePage homePage = new YandexMarketHomePage(driver);
        homePage.clickElectronicButton();
        String moskau = homePage.findСityMoskau();
        log.info("The page with the city of Moscow is open.");
        assertEquals(moskau, "Москва");
    }

    @Test(description = "Заказываем компьютер в Москве")
    public void orderAComputerInMoscow() {
        YandexMarketHomePage homePage = new YandexMarketHomePage(driver);
        YandexMarketOrder order = new YandexMarketOrder(driver);
        homePage.clickElectronicButton();
        homePage.choosingALaptop();
        log.info("Choosing a Laptop.");
        switchToTheRightHandle();
        order.addToCard();
        log.info("Add product to cart.");
        order.goToCart();
        String actualQuantity = order.quantityOfGoods();
        log.info("We check that 1 item has been added to the cart.");
        assertEquals("1", actualQuantity);
    }

    @Test(description = "Заказываем компьютер в Москве 5 штук")
    public void orderAComputerInMoscow5Phones() {
        YandexMarketHomePage homePage = new YandexMarketHomePage(driver);
        YandexMarketOrder order = new YandexMarketOrder(driver);
        homePage.clickElectronicButton();
        homePage.choosingALaptop();
        log.info("Choosing a Laptop.");
        switchToTheRightHandle();
        order.addToCard();
        log.info("Add product to cart.");
        order.addGoods(5);
        log.info("Add the product 5 times to the cart.");
        order.goToCart();
        String actualQuantity = order.quantityOfGoods();
        log.info("We check that 5 items have been added to the cart.");
        assertEquals("5", actualQuantity);
    }

    @Test(description = "Находим самый дорогой айфон в Москве")
    public void findTheMostExpensivePhone() {
        YandexMarketHomePage homePage = new YandexMarketHomePage(driver);
        homePage.searchAiphone();
        log.info("Looking for an iPhone.");
        homePage.clickSubmitButton();
        Set<String> webElementsList = new HashSet<>();
        List<WebElement> list = homePage.allIphonePrices();
        for (WebElement element : list) {
            webElementsList.add(element.getText());
        }
        log.info("The cost of the most expensive iPhone in Moscow = " + Collections.max(webElementsList));
        Assert.assertNotNull(webElementsList);
    }

    @Test(description = "Находим sony playstation 5 и делаем скрин")
    public void findPlaystation5() throws IOException {
        YandexPlaystationPage playstationPage = new YandexPlaystationPage(driver);
        YandexMarketHomePage homePage = new YandexMarketHomePage(driver);
        homePage.searchSony5();
        log.info("Looking PlayStation 5.");
        homePage.clickSubmitButton();
        homePage.clickSony5();
        switchToTheRightHandle();
        playstationPage.sonyPictureClick();
        log.info("Opening the photo of the model.");
        ScreenProperties.makeScreenPage(driver);
        log.info("Making a screenshot of the model.");
        String actualName = playstationPage.getNameProduct();
        String expectedName = "Игровая приставка Sony PlayStation 5 825 Гб";
        assertEquals(expectedName, actualName);
    }

    private void switchToTheRightHandle() {
        List<String> tabHandles = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabHandles.get(tabHandles.size() - 1));
    }
}

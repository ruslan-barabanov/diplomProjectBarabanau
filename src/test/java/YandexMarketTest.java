
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class YandexMarketTest extends BaseTest {

    private final Properties properties = PathProperties.readFile();
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

    @Test(description = "Получаем спсиок видимых категории и открываем страницу случайной категории")
    public void getAllDisplayedCategories() {
        switchToTheRightHandle();
        YandexMarketHomeAuthorizedPage authorizedHomePage = new YandexMarketHomeAuthorizedPage(driver);
        displayedCategoriesLinks = authorizedHomePage.getDisplayedCategoryLinks();
        assertTrue(displayedCategoriesLinks.size() > 0);
        int randomInt = new Random().nextInt(displayedCategoriesLinks.size() - 1);
        String randomCategoryLink = displayedCategoriesLinks.get(randomInt);
        driver.get(randomCategoryLink);
        assertEquals(driver.getCurrentUrl(), randomCategoryLink);
    }

    private void switchToTheRightHandle() {
        List<String> tabHandles = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabHandles.get(tabHandles.size() - 1));
    }
}


import org.testng.annotations.Test;

import java.io.*;
import java.util.*;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class YandexMarketTest extends BaseTest {

    private Properties properties = new Properties();
    private List<String> displayedCategoriesLinks = new ArrayList<>();
    PathProperties pathProperties = new PathProperties();

    @Test(description = "Открываем страницу и преходим на логин страницу", priority = 1)
    public void openLoginPage() {
        YandexMarketHomePage homePage = new YandexMarketHomePage(driver);
        homePage.clickLoginButton();
        switchToTheRightHandle();
        assertEquals(driver.getTitle(), "Авторизация");
    }

    @Test(description = "Авторизуемся", priority = 2)
    public void signIn() throws IOException {
        YandexMarketHomePage homePage = new YandexMarketHomePage(driver);
        homePage.clickLoginButton();
        switchToTheRightHandle();
        YandexLoginPage loginPage = new YandexLoginPage(driver);
        assertTrue(loginPage.isInitialized());
        FileInputStream fileInputStream = new FileInputStream(pathProperties.getPATH_TO_PROPERTIES());
        properties.load(fileInputStream);
        String login = properties.getProperty("login");
        loginPage.enterLogin(login);
        loginPage.clickSignInButton();
        String password = properties.getProperty("password");
        loginPage.enterPassword(password);
        loginPage.clickSignInButton();
        switchToTheRightHandle();
        YandexMarketHomeAuthorizedPage authorizedHomePage = new YandexMarketHomeAuthorizedPage(driver);
        assertTrue(authorizedHomePage.isAuthorized());
    }

    @Test(description = "Получаем спсиок видимых категории и открываем страницу случайной категории", priority = 3)
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

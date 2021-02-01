
import org.testng.Assert;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class YandexMarketTest extends BaseTest {
    private Properties properties = new Properties();

    @Test(description = "Открываем страницу и преходим на логин страницу", priority = 1)
    public void openLoginPage() {
        YandexMarketHomePage homePage = new YandexMarketHomePage(driver);
        homePage.clickLoginButton();
        switchToTheRightHandle();
        Assert.assertEquals(driver.getTitle(), "Авторизация");
    }

    @Test(description = "Авторизуемся", priority = 2)
    public void signIn() throws IOException {
        YandexLoginPage loginPage = new YandexLoginPage(driver);
//        Assert.assertTrue(loginPage.isInitialized());
        YandexMarketHomePage homePage = new YandexMarketHomePage(driver);
        homePage.clickLoginButton();
        switchToTheRightHandle();
        properties.load(ClassLoader.getSystemResourceAsStream("conf.properties"));
        loginPage.enterLogin(properties.getProperty("login.path"));
        loginPage.clickSignInButton();
        loginPage.enterPassword("password");
        loginPage.clickSignInButton();
        switchToTheRightHandle();
        YandexMarketHomeAuthorizedPage authorizedHomePage = new YandexMarketHomeAuthorizedPage(driver);
        Assert.assertTrue(authorizedHomePage.isAuthorized());
    }

    private void switchToTheRightHandle() {
        List<String> tabHandles = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabHandles.get(tabHandles.size() - 1));
    }
}

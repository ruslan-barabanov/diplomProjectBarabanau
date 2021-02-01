
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class BaseTest {

    public WebDriver driver;

    Properties properties = new Properties();

    @BeforeMethod
    public void startTest() throws IOException {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
//        properties.load(ClassLoader.getSystemResourceAsStream("conf.properties"));
//        driver.get(properties.getProperty("yandex.market.page"));
//        String yandexPage = properties.getProperty("yandex");
//      driver.get(yandexPage);
        driver.get("https://market.yandex.by/");
    }

    @AfterMethod
    public void afterClass() {
        driver.quit();
    }

}

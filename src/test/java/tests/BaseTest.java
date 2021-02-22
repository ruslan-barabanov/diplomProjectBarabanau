package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.PathsProperties;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;
    private final Properties properties = PathsProperties.readFile();
    private final String site = properties.getProperty("site");
//    private final String chromeDriver = properties.getProperty("site");

    @BeforeMethod
    public void startTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Framework\\diplomProjectBarabanau\\src\\test\\resources\\chromedriver.exe");
//      WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(site);
    }

    @AfterMethod
    public void afterClass() {
        driver.quit();
    }
}


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class BaseTest {

    public WebDriver driver;
    PathProperties pathProperties = new PathProperties();
    private Properties properties = new Properties();

    @BeforeMethod
    public void startTest() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        FileInputStream fileInputStream = new FileInputStream(pathProperties.getPATH_TO_PROPERTIES());
        properties.load(fileInputStream);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(properties.getProperty("site"));
    }

    @AfterMethod
    public void afterClass() {
        driver.quit();
    }
}

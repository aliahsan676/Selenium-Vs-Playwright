package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExtentManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class SeleniumBaseTest {
    protected WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        ExtentManager.initReport();
    }

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void afterSuite() {
        ExtentManager.flushReport();
    }
}

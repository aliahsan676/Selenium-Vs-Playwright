package base;

import com.microsoft.playwright.*;
import org.testng.annotations.*;
import utils.ExtentManager;

import java.awt.*;

public class PlaywrightBaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    @BeforeSuite
    public void beforeSuite() {
        ExtentManager.initReport();
    }

    @BeforeClass
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(width, height));

        page = context.newPage();
    }

    @AfterClass
    public void teardown() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    @AfterSuite
    public void afterSuite() {
        ExtentManager.flushReport();
    }
}

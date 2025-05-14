package tests;

import base.PlaywrightBaseTest;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.playwright.PlaywrightProfitCalculatorPage;
import utils.ConfigReader;
import utils.ExtentManager;

public class PlaywrightProfitCalculatorTest extends PlaywrightBaseTest {

    @Test
    public void verifyProfitCalculation() {
        // Start Extent Report test
        ExtentTest test = ExtentManager.startTest("Playwright - Profit Calculator");

        // Create page object
        PlaywrightProfitCalculatorPage pageObj = new PlaywrightProfitCalculatorPage(page);

        // Login using credentials from config
        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");
        pageObj.login(username, password);

        // Perform profit calculation
        pageObj.calculateProfit();
        String actualProfit = pageObj.getProfitResult();

        // Validate result using soft assertions
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualProfit, "$72,400", "Profit amount mismatch!");
        softAssert.assertAll();
    }
}

package tests;

import base.SeleniumBaseTest;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.selenium.SeleniumProfitCalculatorPage;
import utils.ExtentManager;

public class  SeleniumProfitCalculatorTest extends SeleniumBaseTest {

    @Test
    public void verifyProfitCalculation() {

        // Start Extent Report test
        ExtentTest test = ExtentManager.startTest("Selenium - Profit Calculator");

        // Create page object
        SeleniumProfitCalculatorPage page = new SeleniumProfitCalculatorPage(driver);

        // Login using credentials from config
        page.login();

        // Perform profit calculation
        page.calculateProfit();
        String actual = page.getProfitResult();

        // Validate result using soft assertions
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actual, "$72,400", "Profit amount mismatch!");
        softAssert.assertAll();
    }

}

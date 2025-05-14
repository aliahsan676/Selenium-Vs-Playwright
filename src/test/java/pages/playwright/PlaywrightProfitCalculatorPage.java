package pages.playwright;

import com.microsoft.playwright.Page;
import utils.ConfigReader;
import utils.ExtentManager;
import utils.General;

public class PlaywrightProfitCalculatorPage {

    private Page page;

    public PlaywrightProfitCalculatorPage(Page page) {
        this.page = page;
    }

    public void login(String username, String password) {
        String baseUrl = ConfigReader.get("url");

        ExtentManager.getTest().info("Attempting to log in with username: " + username);

        page.navigate(baseUrl);
        page.locator("//input[@id='basic_email']").fill(username);
        page.locator("//input[@id='basic_password']").fill(password);
        page.locator("//button[@id='login-btn-gtag']").click();

        ExtentManager.getTest().info("Login attempted for user: " + username);
    }

    public void calculateProfit() {
        ExtentManager.getTest().info("Navigating to the profit calculator");

        page.locator("//li[@class='sidebar__nav-item']//span[contains(@class, 'sidebar__nav-link-title') and contains(., 'Calculator')]\n").click();
        page.locator("//span[normalize-space()='Profit/Loss Calculator']").click();
        page.locator("label:has-text('Instrument')").locator("..").locator("div.ant-select").click();
        page.locator("div.ant-select-item-option-content:has-text('AUDCAD')").click();
        page.locator("(//div[@class='ant-select-selector'])[5]").click();
        page.locator("//div[@class='ant-select-item-option-content'][normalize-space()='Buy']").click();
        page.locator("//input[@placeholder='Enter Entry Price']").fill("20");
        page.locator("//input[@placeholder='Enter Exit Price']").fill("22");
        page.locator("//input[@placeholder='Enter Number of lots']").fill("0.5");
        page.locator("(//span[contains(text(),'Calculate')])[2]").click();
        General.waitForDomStable();

        ExtentManager.getTest().info("Profit calculation completed for the selected parameters");
    }

    public String getProfitResult() {
        String profitAmount = page.locator("(//div[@class='tw-text-3xl break-words tw-text-[#3C9D00] p-2'])[1]").innerText();

        ExtentManager.getTest().info("Profit result: " + profitAmount);

        return profitAmount;
    }
}

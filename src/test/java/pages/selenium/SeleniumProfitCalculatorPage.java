package pages.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import utils.General;

import java.time.Duration;

public class SeleniumProfitCalculatorPage {
    private WebDriver driver;

    public SeleniumProfitCalculatorPage(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void login() {
        driver.manage().window().maximize();
        String url = ConfigReader.get("url");
        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");

        driver.get(url);
        driver.findElement(By.id("basic_email")).sendKeys(username);
        driver.findElement(By.id("basic_password")).sendKeys(password);
        driver.findElement(By.id("login-btn-gtag")).click();
    }

    public void calculateProfit() {
        driver.findElement(By.xpath("//li[9]")).click();
        driver.findElement(By.xpath("//span[normalize-space()='Profit/Loss Calculator']")).click();

        WebElement dropdown = driver.findElement(By.xpath("//span[contains(text(),'Select a pair')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html[1]/body[1]/main[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/span[1]/span[1]/input[1]")  // use exact input field XPath
        ));
        inputField.sendKeys("AUDCAD");
        inputField.sendKeys(Keys.ENTER);


        WebElement tradeTypeDropdown = driver.findElement(By.xpath("//span[contains(text(),'Select Trade Type')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tradeTypeDropdown);


        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement tradeTypeInput = wait2.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html[1]/body[1]/main[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/span[1]/span[1]/input[1]")
        ));
        tradeTypeInput.sendKeys("Buy");  // or "Forward", etc.
        tradeTypeInput.sendKeys(Keys.ENTER);


        driver.findElement(By.xpath("//input[@placeholder='Enter Entry Price']")).sendKeys("20");
        driver.findElement(By.xpath("//input[@placeholder='Enter Exit Price']")).sendKeys("22");
        driver.findElement(By.xpath("//input[@placeholder='Enter Number of lots']")).sendKeys("0.5");
        driver.findElement(By.xpath("(//span[contains(text(),'Calculate')])[2]")).click();
        General.waitForDomStable();
    }

    public String getProfitResult() {
        return driver.findElement(By.xpath("(//div[@class='tw-text-3xl break-words tw-text-[#3C9D00] p-2'])[1]")).getText();
    }
}
package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BrowserManager {
    protected WebDriver driver;

    private WebDriver getDriver(){
    System.setProperty("webdriver.chrome.driver","drivers/chromedriverv91.exe");
    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize();
    return driver;
}

    @BeforeMethod
    public void beforeMethod(){
    driver = getDriver();
    driver.get("http://the-internet.herokuapp.com/");
    }

    @AfterMethod
    public void afterMethod(){
    driver.quit();
    }
}

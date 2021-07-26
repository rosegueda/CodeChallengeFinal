package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class FrequentCommands {
    protected WebDriver driver;
    public FrequentCommands(WebDriver driver) {
        this.driver = driver;
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void clickNTimes(By locator, int N){
        for (int i = 0; i < N; i++){
            driver.findElement(locator).click();
        }
    }
    public void rightClick(By locator){
        Actions actions = new Actions(driver);
        WebElement elementLocator = driver.findElement(locator);
        actions.contextClick(elementLocator).perform();
    }
    public void type(String inputText, By locator) {
        driver.findElement(locator).sendKeys(inputText);
    }
    public void slideMove(By locator,int xOffset, int yOffset){
        WebElement slider = driver.findElement(locator);
        Actions move = new Actions(driver);
        Action action = (Action) move.dragAndDropBy(slider, xOffset, yOffset).build();
        action.perform();
    }
}

package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.FrequentCommands;

import java.util.List;
import java.util.Random;

public class HerokuappPage extends FrequentCommands {
    public HerokuappPage(WebDriver driver) {
        super(driver);}

    private By itemsLocator = By.xpath("//ul//a[@href]");

    //Elements on "Add/Remove Elements" Subpage

    private By addElementLocator = By.xpath("//button[@onclick='addElement()']");
    private By deleteButtonLocator = By.xpath("//button[@onclick='deleteElement()']");
    public By getFloatingMenuButtonsLocator() {
        return floatingMenuButtonsLocator;
    }

    //Elements on "Floating Menu" Subpage

    private By floatingMenuButtonsLocator = By.xpath("//li/a[@href]");
    //Elements on "Login Page" Subpage

    private By usernameLocator = By.id("username");
    private By passwordLocator = By.id("password");
    private By loginBtnLocator = By.xpath("//button[@type='submit']");

    //Elements on "horizontal_Slider" Subpage

    private By horizontalSlider = By.xpath("//input[@type='range']");

    public By getHorizontalSlider() {
        return horizontalSlider;
    }

    public By getClickHereLocator() {
        return clickHereLocator;
    }

    //elements on "Multiple Windows" subpage
    private By clickHereLocator = By.xpath("//a[text()='Click Here']");

    public By getItemsLocator() {
        return itemsLocator;
    }

    public By getAddElementLocator() {
        return addElementLocator;
    }

    public By getDeleteButtonLocator(){
        return deleteButtonLocator;
    }

    public void clickItemByName (String inputName){
    //By item = By.linkText(inputName);
    By itemLocator = By.xpath("//ul//a[@href][text()='"+inputName+"']");
    click(itemLocator);
}
    public String randomItemName(){
        List<WebElement> items = driver.findElements(itemsLocator);
        Random r =new Random();
        int randomValue = r.nextInt(items.size());
        String randomElementName = items.get(randomValue).getText();
        return randomElementName;
    }
    public String getSubDirectory(){
        String url = driver.getCurrentUrl();
        String sub = url.substring(url.lastIndexOf(".com")+ 4);
        return sub;
    }

    public void clickAndCompareURL(String OriginalURL, By Locators){
        int itemsCount = driver.findElements(Locators).size();
        for (int i = 0; i <itemsCount; i++) {
            List<WebElement> buttons = driver.findElements(Locators);
            buttons.get(i).click();
            String urlAfterClick = driver.getCurrentUrl();
            if (OriginalURL.contentEquals(urlAfterClick)) {
                System.out.println("Url Not Change");
            } else {
                System.out.println("URL Change: " + urlAfterClick);
            }
            driver.navigate().back();
        }
    }
    public void logInOutClick(String username,String password, String message){
    type(username,usernameLocator);
    type(password,passwordLocator);
    click(loginBtnLocator);
        //String LogInMessage = (driver.findElement(By.id("flash")).getText());
        Assert.assertTrue(driver.findElement(By.id("flash")).getText().contains(message));
        System.out.println(message);
    }
}



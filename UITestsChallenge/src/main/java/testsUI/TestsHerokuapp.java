package testsUI;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.HerokuappPage;
import utilities.BrowserManager;
import utilities.FrequentCommands;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestsHerokuapp extends BrowserManager {



    @Test (priority = 0)
    public void Click_All_Items(){
        System.out.println("1) Build a method to select by name all items from the main list of the web page");
        HerokuappPage hp =new HerokuappPage(driver);
        String url = driver.getCurrentUrl();
        hp.clickAndCompareURL(url,hp.getItemsLocator());
//        int itemsCount = driver.findElements(hp.getItemsLocator()).size();
//        for (int i = 0; i < itemsCount; i++) {
//            List<WebElement> items = driver.findElements(hp.getItemsLocator());
//            items.get(i).click();
//            System.out.println(driver.getCurrentUrl());
//            driver.navigate().back();
//        }
        }
    @Test (priority = 8)
    public void ItemsList(){
        System.out.println("- Generate Item List of the web page");
        List<WebElement> items = driver.findElements(By.xpath("//ul//a[@href]"));
        String url = driver.getCurrentUrl();
        System.out.println(url);
        System.out.println(items.size());
        for(WebElement el:items){
            System.out.println(el.getText());

        }
    }

    @Test (priority = 7)
    public void Click_Random_Item(){
        System.out.println("- Click on an Item of the web page");
        HerokuappPage hp =new HerokuappPage(driver);
        System.out.println("1) Select a Random item from the main list of the web page: ");
        String randomName = hp.randomItemName();
        System.out.println("Random Item: "+randomName);
        hp.clickItemByName(randomName);
        Assert.assertNotEquals(driver.getCurrentUrl(),"http://the-internet.herokuapp.com/","Failed Test");
        driver.navigate().back();

    }
    @Test (priority = 1)
    public void Add_Remove_Elements(){
        HerokuappPage hp = new HerokuappPage(driver);
        FrequentCommands fc = new FrequentCommands(driver);
        System.out.println("2) Click on “Add/Remove Element”, then add 9 elements and finally remove them all");
        hp.clickItemByName("Add/Remove Elements");
        int amountClicks = 9;
        fc.clickNTimes(hp.getAddElementLocator(),amountClicks);
        List<WebElement> deleteButtons = driver.findElements(hp.getDeleteButtonLocator());
        Assert.assertTrue(deleteButtons.size()==amountClicks,"Test Failed: Elements found are "+deleteButtons.size());
        fc.clickNTimes(hp.getDeleteButtonLocator(),amountClicks);
        deleteButtons = driver.findElements(hp.getDeleteButtonLocator());
        Assert.assertTrue(deleteButtons.size()==0,"Test Failed: Elements found are "+deleteButtons.size());
    }
    @Test (priority = 2)
    public void Context_Menu(){
        HerokuappPage hp =new HerokuappPage(driver);
        hp.clickItemByName("Context Menu");
        FrequentCommands fc = new FrequentCommands(driver);
        System.out.println("3) Click on “Context Menu”, then do a right click on the box and close the alert");
        fc.rightClick(By.id("hot-spot"));
        Assert.assertEquals(driver.switchTo().alert().getText(),"You selected a context menu","Test Failed");
        driver.switchTo().alert().accept();
    }
    @Test (priority = 3)
    public void Floating_Menu(){
        HerokuappPage hp =new HerokuappPage(driver);
        hp.clickItemByName("Floating Menu");
        System.out.println("4) Click on “Floating Menu”, then click on every button of the menu at the top and check if the URL change");
        String url = driver.getCurrentUrl();
        System.out.println("Original URL: "+url);
        hp.clickAndCompareURL(url,hp.getFloatingMenuButtonsLocator());
        Assert.assertEquals(url,driver.getCurrentUrl());
    }

    @Test (priority = 4,dataProvider = "autenticationData")
    public void Form_Authentication(String username, String password, String message){
        System.out.println("5) Click on “Form Authentication”, then do a successful login with the credentials given on the website, and then fail the login and check what happens");
        HerokuappPage hp =new HerokuappPage(driver);
        hp.clickItemByName("Form Authentication");
        hp.logInOutClick(username,password,message);
    }
    @DataProvider(name = "autenticationData")
    public Object[][] getData(){
        Object[][]data = new Object[3][3];
        data[0][0] ="tomsmith";data[0][1] = "SuperSecretPassword!";data[0][2] = "You logged into a secure area!";
        data[1][0] ="user2";data[1][1] = "pass2";data[1][2] = "Your username is invalid!";
        data[2][0] ="tomsmith";data[2][1] = "pass2";data[2][2] = "Your password is invalid!";
        return data;
    }

    @Test (priority = 5)
    public void Multiple_Windows(){
        HerokuappPage hp =new HerokuappPage(driver);
        FrequentCommands fc = new FrequentCommands(driver);
        System.out.println("6) Click on “Multiple Windows”, then click where says “Click here” 5 times, finally manage and close all the additional tabs or windows");
        hp.clickItemByName("Multiple Windows");
        int amountClicks = 5;
        fc.clickNTimes(hp.getClickHereLocator(),amountClicks);
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        for (int i = 0; i < amountClicks; i++){
            driver.switchTo().window(tabs.get(i+1));
            driver.close();
        }
        driver.switchTo().window(tabs.get(0));
        Assert.assertEquals(true,driver.findElement(hp.getClickHereLocator()).isDisplayed(),"Test Failed");
    }
    @Test (priority = 6)
    public void Horizontal_Slider(){
        HerokuappPage hp =new HerokuappPage(driver);
        hp.clickItemByName("Horizontal Slider");
        System.out.println("7) Click on “Horizontal Slider”, then click on the slider and get the result of 4.5");
        FrequentCommands fq = new FrequentCommands(driver);
        fq.slideMove(hp.getHorizontalSlider(),45,0);
        Assert.assertEquals("4.5",driver.findElement(By.id("range")).getText(),"Test Failed");
        System.out.println("Result: "+driver.findElement(By.id("range")).getText());
    }
}

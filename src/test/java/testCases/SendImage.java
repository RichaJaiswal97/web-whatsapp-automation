package testCases;

import baseSetup.BaseBrowserSetup;
import dataProvider.CsvDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pageObject.MainMenu;
import utility.ConfigFileReader;

import java.time.Duration;

public class SendImage extends BaseBrowserSetup {
WebDriverWait wait;
    MainMenu mainMenu;
    ConfigFileReader configReader = new ConfigFileReader();
    Actions actions;
    @Test(dataProvider = "MobileNo" , dataProviderClass = CsvDataProvider.class)
    public void sendImage(String WhatsapNo) throws InterruptedException {
        mainMenu = new MainMenu(driver);
        actions = new Actions(driver);
        driver.get("https://wa.me/"+WhatsapNo+"");
        wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        WebElement continueChat = driver.findElement(By.linkText("Continue to Chat"));
        wait.until(ExpectedConditions.visibilityOf(continueChat));
        actions.click(continueChat).build().perform();
        WebElement useWebApp = driver.findElement(By.xpath("//span[contains(text(),'use WhatsApp Web')]"));
        wait.until(ExpectedConditions.visibilityOf(useWebApp));
        actions.click(useWebApp).build().perform();
        wait.until(ExpectedConditions.visibilityOf(mainMenu.MessageBox));
        actions.click(mainMenu.MessageBox).sendKeys(Keys.chord(Keys.CONTROL,"a")).sendKeys(Keys.BACK_SPACE).build().perform();
        Thread.sleep(2000);
          actions.sendKeys(configReader.getProperty("TextMessage")).build().perform();
        //mainMenu.MessageBox;
        wait.until(ExpectedConditions.elementToBeClickable(mainMenu.SendMessage));
        actions.click(mainMenu.SendMessage).build().perform();
        Thread.sleep(10000);
    }
}

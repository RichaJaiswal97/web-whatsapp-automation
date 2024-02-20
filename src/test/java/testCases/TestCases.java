package testCases;

import baseSetup.BaseBrowserSetup;
import dataProvider.CsvDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pageObject.MainMenu;
import utility.ConfigFileReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class TestCases extends BaseBrowserSetup {
    ConfigFileReader configReader = new ConfigFileReader();
    MainMenu mainMenu;
    Actions actions;
    WebDriverWait wait;
    static final Logger logger = Logger.getLogger(TestCases.class);

    @Test(dataProvider = "CSV", dataProviderClass = CsvDataProvider.class)
    public void sendTextTo(String WhatsapNo) throws InterruptedException {

        mainMenu = new MainMenu(driver);
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(500));
        actions.click(mainMenu.SearchBox);
        actions.sendKeys(WhatsapNo).build().perform();

        logger.info("Search for given Contact");
        wait.until(ExpectedConditions.visibilityOfAllElements(mainMenu.SearchResult));

        for (WebElement expectdResult : mainMenu.SearchResult) {
            logger.info(expectdResult.getText());
            if (expectdResult.getText().equals(WhatsapNo)) {
                assertEquals(expectdResult.getText(), WhatsapNo);

                Assert.assertTrue(expectdResult.getText()
                        .equals(WhatsapNo));
                actions.click(expectdResult).build().perform();
                logger.info("Clicked on given contact");
                wait.until(ExpectedConditions.elementToBeClickable(mainMenu.MessageBox));
                break;

            }
        }

        actions.click(mainMenu.MessageBox).sendKeys(Keys.chord(Keys.CONTROL,"a")).sendKeys(Keys.BACK_SPACE).build().perform();

        mainMenu.MessageBox.sendKeys(configReader.getProperty("TextMessage"));
        wait.until(ExpectedConditions.elementToBeClickable(mainMenu.SendMessage));
        WebElement attachment = driver.findElement(By.xpath("//*[@data-icon='attach-menu-plus']"));
        actions.click(attachment).build().perform();

        WebElement attachImage = driver.findElement(By.xpath("//span[contains(text(),'Photos & Videos')]"));
        actions.click(attachImage).build().perform();
        // attachImage.sendKeys("./src/main/resources/Image.jpg");
      WebElement img =   driver.findElement(By.cssSelector("input[type='file']"));
      img.sendKeys("./src/main/resources/Image.jpg");
    //    actions.sendKeys(attachImage, "./src/main/resources/Image.jpg").build().perform();
       // actions.click(mainMenu.SendMessage).build().perform();
        logger.info("Message Sent");

        wait.until(ExpectedConditions.visibilityOf((mainMenu.SearchBox)));
        actions.click(mainMenu.SearchBox);

    }

}

package testCases;

import baseSetup.BaseBrowserSetup;
import dataProvider.CsvDataProvider;
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
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        actions.click(mainMenu.SearchBox);
        actions.sendKeys(WhatsapNo).build().perform();

        logger.info("Search for given Contact");
        wait.until(ExpectedConditions.visibilityOfAllElements(mainMenu.SearchResult));

        for (WebElement expectdResult : mainMenu.SearchResult) {

            if (expectdResult.getText().equals(WhatsapNo)) {
                assertEquals(expectdResult.getText(), WhatsapNo);
                actions.click(expectdResult).build().perform();
                logger.info("Clicked on given contact");
                wait.until(ExpectedConditions.elementToBeClickable(mainMenu.MessageBox));
                break;

            }
        }

        actions.click(mainMenu.MessageBox);
        mainMenu.MessageBox.sendKeys(configReader.getProperty("TextMessage"));
        wait.until(ExpectedConditions.elementToBeClickable(mainMenu.SendMessage));
        actions.click(mainMenu.SendMessage).build().perform();
        logger.info("Message Sent");

        wait.until(ExpectedConditions.visibilityOf((mainMenu.SearchBox)));
        actions.click(mainMenu.SearchBox);

    }

}

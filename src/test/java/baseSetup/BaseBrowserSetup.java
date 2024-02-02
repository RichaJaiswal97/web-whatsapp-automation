package baseSetup;


import pageObject.MainMenu;
import utility.BrowserLaunch;
import utility.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.log4testng.Logger;

import java.time.Duration;

public class BaseBrowserSetup {
    ConfigFileReader configReader = new ConfigFileReader();
    protected static WebDriver driver;
    WebDriverWait wait;
    MainMenu mainMenu;
    static final Logger logger = Logger.getLogger(BaseBrowserSetup.class);

    @BeforeTest
    public void baseSetup() throws InterruptedException {
        BrowserLaunch launch = new BrowserLaunch();
        driver = launch.launchBrowser(configReader);
        logger.info("Browser is Launched");
        driver.get(configReader.getProperty("Url"));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        mainMenu = new MainMenu(driver);
        wait.until(ExpectedConditions.visibilityOf((mainMenu.SearchBox)));
        logger.info("Web-WhatsApp is logged in");
    }

    @AfterTest
    public void closeBrowser() {
        driver.close();
    }
}
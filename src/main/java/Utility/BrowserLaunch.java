package Utility;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class BrowserLaunch {

    public WebDriver launchBrowser(ConfigFileReader configReader) throws InterruptedException {
        if (configReader.getProperty("Browser").contains("chrome")) {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("user-data-dir=C:/Users/Rishabh Jaiswal/AppData/Local/Google/Chrome/User Data/Default");
            options.addArguments("profile-direcory=Default");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            WebDriver driver = new ChromeDriver(options);
            driver.get(configReader.getProperty("Url"));

            return driver;


        }

     throw new IllegalArgumentException("Unsupported Browser type ");
    }
}

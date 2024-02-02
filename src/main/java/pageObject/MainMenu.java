package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainMenu {
    WebDriver driver;

    public MainMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//*[@title = 'Search input textbox']")
    public WebElement SearchBox;

   @FindAll( {@FindBy(xpath = "//*[@role= 'listitem']//div[@role='gridcell']/div[1]")})
   public List<WebElement> SearchResult;

   @FindBy(xpath = "//*[@title='Type a message']")
    public WebElement MessageBox;

   @FindBy(xpath = "//button/span[@data-icon='send']")
    public WebElement SendMessage;
}

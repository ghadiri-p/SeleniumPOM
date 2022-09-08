package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
    WebDriver driver = null;


    @FindBy(xpath = "//ol[@class='nav-primary']/li[@class='level0 nav-2 parent']")
    WebElement mensLabel;

    @FindBy(xpath = "(//ul[@class='level0']/descendant::a[text()='New Arrivals'])[2]")
    WebElement newArrivals;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver , this);
    }


    public void MoveToMensLabel() {
        Actions action = new Actions(driver);
        action.moveToElement(mensLabel).perform();
    }

    public void clickNewArrivals() {
        newArrivals.click();
    }

    /**
     * This method first clicks on the mens option and then selects newArrivals.
     */
    public void clickArrival(){
        MoveToMensLabel();
        clickNewArrivals();
    }
}

package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    WebDriver driver;

    @FindBy(xpath = "//span[@class = 'label' and text()='Account']")
    WebElement account;

    @FindBy(xpath = "//div[@id='header-account']/descendant::a[@title='Log In']")
    WebElement loginTab;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver ,this);
    }


    public void selectAccount() {
        account.click();
    }


    public void selectLogin() {
        loginTab.click();
    }


    /**
     * This method will enter the login page.
     */
    public void goToLoginPage() {
        selectAccount();
        selectLogin();
    }
}

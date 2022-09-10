package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver = null;


    @FindBy(how = How.XPATH , using = "//ul[@class='form-list']/descendant::*[@id='email']")
    WebElement userName;

    @FindBy(xpath = "//ul[@class='form-list']/descendant::*[@id='pass']")
    WebElement password;

    @FindBy(xpath = "//button[@type='submit']/descendant::*[text()='Login']")
    WebElement loginButton;

    @FindBy(xpath = "//li[@class='error-msg']/descendant::span")
    WebElement message;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver ,this);
    }


    public void setUserName(String uName) {
        userName.sendKeys(uName);
    }


    public void setPassword(String pass) {
        password.sendKeys(pass);
    }


    public void clickLoginButton() {
        loginButton.click();
    }


    public String getMessage() {
        return message.getText();
    }


    /**
     * This method is used to login to the website.
     * @param userName
     * @param password
     */
    public void login (String userName,String password ){

        this.setUserName(userName);
        this.setPassword(password);
        this.clickLoginButton();
    }
}

package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.BasePage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class LoginPageTest {
    WebDriver driver = null;
    BasePage basePage;
    LoginPage loginPage;
    User user;
    private static Logger logger = LogManager.getLogger(LoginPageTest.class);


    /**
     * Creates a chrome driver and maximizes it,
     * Creates objects from the classes we need and
     * Logged in to the website.
     */
    @BeforeMethod
    @Parameters({"websiteAddress", "UserName", "Password"})
    public void setup(@Optional("http://demo-store.seleniumacademy.com/") String url,
                      @Optional("ghadiri.p11@gmail.com") String userName, @Optional("@12345a") String password) {
        logger.info("Chrome browser Preparation...");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        basePage = new BasePage(driver);
        loginPage = new LoginPage(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
        basePage.goToLoginPage();
        user = new User(userName, password);
    }


    /**
     * If My Account page is found, it means that the login is done correctly
     * and the user has logged into their user page Otherwise, we will have error .
     */
    @Test(priority = 0)
    public void testValidLogin() {
        logger.info("Entering UserName and Password...");
        loginPage.login(user.getUserName(), user.getPassWord());
        Assert.assertEquals(driver.getTitle(), "My Account");
        logger.info("login was done correctly");
    }


    /**
     * This method checks if the password is incorrect, we cannot enter the account page.
     */
    @Test(priority = 1)
    public void testInvalidLogin() {
        logger.info("Entering UserName and wrong Password...");
        loginPage.login(user.getUserName(), "12h345");
        Assert.assertEquals(driver.getTitle(), "Customer Login");
        logger.info(loginPage.getMassage());
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

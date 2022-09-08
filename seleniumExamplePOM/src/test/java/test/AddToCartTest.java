package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import model.Goods;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.util.concurrent.TimeUnit;

public class AddToCartTest {
    WebDriver driver = null;
    BasePage basePage;
    LoginPage loginPage;
    Cart cart;
    AccountPage accountPage;
    NewArrivalsPage newArrivals;
    private static Logger logger = LogManager.getLogger(AddToCartTest.class);


    /**
     * Creates a chrome driver and maximizes it,
     * Creates objects from the classes we need and
     * Logged in to the website.
     */
    @BeforeTest
    @Parameters({"websiteAddress", "UserName", "Password"})
    public void setup(@Optional("http://demo-store.seleniumacademy.com/") String url,
                      @Optional("ghadiri.p11@gmail.com") String userName, @Optional("@12345a") String password) {
        logger.info("Chrome browser Preparation...");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        basePage = new BasePage(driver);
        loginPage = new LoginPage(driver);
        cart = new Cart(driver);
        newArrivals = new NewArrivalsPage(driver);
        accountPage = new AccountPage(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get(url);
        basePage.goToLoginPage();
        User user = new User(userName, password);
        logger.info("login to the page");
        loginPage.login(user.getUserName(), user.getPassWord());
    }


    /**
     * Checks that the purchase information is equal to the information of the goods placed in the cart,
     * otherwise we will have error.
     */
    @Test
    public void addToCartTest() {
        ProductDetails productDetails = new ProductDetails(driver);
        Goods myGood;
        Goods cartGoods;

        try {
            logger.info("Empty the cart");
            cart.removeCartObjects();

            logger.info("Performing the steps to buy clothes");
            accountPage.clickArrival();
            newArrivals.selectOneProduct();
            myGood = productDetails.purchase();

            logger.info("Get information about the goods located in the cart");
            cartGoods = cart.getCartValues();

            logger.info("Checking whether the information on the goods located in the cart is the same with our purchase");
            Assert.assertTrue(myGood.getName().equals(cartGoods.getName())
                    && myGood.getSize().equals(cartGoods.getSize())
                    && myGood.getColor().equals(cartGoods.getColor())
                    && (myGood.getPrice() == cartGoods.getPrice())
                    && (myGood.getCount() == cartGoods.getCount())
                    && (cartGoods.getTotalPrice() == myGood.getCount() * myGood.getPrice()));
            logger.info("the Shopping is located in the cart.");

        } catch (Exception e) {
            if (e instanceof ElementNotInteractableException
                    || e instanceof NoSuchElementException) {
                logger.error(e.getMessage() + "\ncheck your Xpath code.");
            } else if (e instanceof InterruptedException) {
                logger.error(e.getMessage());
            } else {
                logger.error("Purchase failed.");
            }
        }
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

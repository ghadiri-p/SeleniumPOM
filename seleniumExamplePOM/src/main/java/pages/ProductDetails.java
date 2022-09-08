package pages;

import model.Goods;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductDetails {
    WebDriver driver = null;

    @FindBy(xpath = "//span[text()='Khaki Bowery Chino Pants']")
    WebElement khakiBowery;

    @FindBy(xpath = "//ul[@id='configurable_swatch_color']/descendant::*[@alt='Khaki']")
    WebElement color;

    @FindBy(xpath = "//ul[@id='configurable_swatch_size']/descendant::*[@name='30']")
    WebElement size;

    @FindBy(xpath = "//input[@title='Qty']")
    WebElement qty;

    @FindBy(xpath = "//div[@class='price-info']/descendant::span[text()='$140.00']")
    WebElement price;

    @FindBy(xpath = "(//button[@class='button btn-cart'])[2]")
    WebElement addToCart;


    public ProductDetails(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    /**
     *  This method makes the purchase of one of the products and stores and returns its information in a goods-type object.
     *
     * @return goods
     */
    public Goods purchase() {
        Goods goods = new Goods();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,200)");

        goods.setName(khakiBowery.getText());

        color.click();
        goods.setColor(color.getAttribute("alt"));

        size.click();
        goods.setSize(size.getText());

        qty.click();
        goods.setCount(Integer.valueOf(qty.getAttribute("value")));

        goods.setPrice(Double.valueOf(price.getAttribute("innerHTML").replace("$", "")));

        addToCart.click();

        return goods;
    }
}

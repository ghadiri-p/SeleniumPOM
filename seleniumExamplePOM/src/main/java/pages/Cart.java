package pages;

import model.Goods;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cart {
    WebDriver driver = null;


    @FindBy(xpath = "//*[text() = 'Cart']")
    WebElement cart;

    @FindBy(name = "View Shopping Cart")
    WebElement viewShoppingCart;

    @FindBy(xpath = "//*[@class ='product-cart-info']/child::*[@class = 'product-name']")
    WebElement productName;

    @FindBy(xpath = "//*[@class ='product-cart-info']/child::*[@class = 'item-options']/dd[1]")
    WebElement productColor;

    @FindBy(xpath = "//*[@class ='product-cart-info']/child::*[@class = 'item-options']/dd[2]")
    WebElement productSize;

    @FindBy(xpath = "//span[@class='cart-price']//span")
    WebElement productPrice;

    @FindBy(xpath = "//*[@class='product-cart-actions']/input")
    WebElement productCount;

    @FindBy(xpath = "//*[@class='product-cart-total']/span[@class='cart-price']/span[@class='price']")
    WebElement productTotalPrice;

    @FindBy(xpath = "//div[@class='header-minicart']//*[@class='count']")
    WebElement cartCount;

    @FindBy(xpath = "//div[@class='minicart-actions']/a")
    WebElement viewShoppingCarts;

    @FindBy(xpath = "//td[@class='a-right cart-footer-actions last']/descendant::*[text()='Empty Cart']")
    WebElement emptyCart;


    public Cart(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    /**
     * Returns the properties of the goods on the card.
     *
     * @return Goods
     */
    public Goods getCartValues() {
        Goods cartGoods = new Goods();

        if (!driver.getCurrentUrl().contains("/checkout/cart/")) {
            cart.click();
            viewShoppingCart.click();
        }

        cartGoods.setName(productName.getText());
        cartGoods.setColor(productColor.getText());
        cartGoods.setSize(productSize.getText());
        cartGoods.setPrice(Double.valueOf(productPrice.getAttribute("innerHTML").replace("$", "")));
        cartGoods.setCount(Integer.valueOf(productCount.getAttribute("value")));
        cartGoods.setTotalPrice(Double.valueOf(productTotalPrice.getAttribute("innerHTML").replace("$", "")));
        return cartGoods;
    }


    /**
     * Checks the cart, if its value is greater than zero
     * , this method removes all purchases it already had.
     */
    public void removeCartObjects() {

        while (Integer.valueOf(cartCount.getAttribute("innerHTML")) > 0) {
            cart.click();
            viewShoppingCarts.click();
            emptyCart.click();
        }
    }
}

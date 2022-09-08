package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewArrivalsPage {
    WebDriver driver = null;


    @FindBy(xpath = "//*[@class ='products-grid products-grid--max-4-col first last odd']")
    WebElement products;

    @FindBy(xpath = "(//a[@title='View Details'])[2]")
    WebElement viewDetailButton;


    public NewArrivalsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver , this);
    }

    public WebElement getProducts() {
        return products;
    }


    public void ViewDetailButton() {
        viewDetailButton.click();
    }


    /**
     *  This method shows the information of one of the products.
     */
    public void selectOneProduct(){
        getProducts();
        ViewDetailButton();
    }
}

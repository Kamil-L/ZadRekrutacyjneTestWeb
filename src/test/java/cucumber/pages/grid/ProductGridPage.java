package cucumber.pages.grid;

import cucumber.pages.BasePage;
import cucumber.pages.products.ProductViewPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductGridPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(ProductGridPage.class);

    @FindBy(xpath = "//div[@class='grid-child']//div[contains(@class,'StyledProductsGridWrap')]")
    private WebElement productsGrid;

    @FindBy(xpath = "//*[@data-qa='LST_ProductCard0']/a")
    private WebElement productFirstCard;

    public WebElement getProductsGrid() {
        return productsGrid;
    }

    public boolean isProductsGridDisplayed() {
        waitForPageLoadComplete();
        waitToBeClickable(productsGrid);
        try {
            boolean isProductsGridDisplayed = productsGrid.isDisplayed();
            log.info("Product grid element is displayed: {}", productsGrid.getText());
            return isProductsGridDisplayed;
        } catch (Exception e) {
            log.error("Exception while checking if Product grid is displayed: {}", e.getMessage());
            return false;
        }
    }

    public ProductViewPage clickProductFirstCard() {
        waitForPageLoadComplete();
        moveToElement(productsGrid);
        jsExecutorScrollIntoView(productFirstCard, "//*[@data-qa='LST_ProductCard0']/a[@href]");
        click(productFirstCard);
        log.info("<<<< Clicked on product card button >>>>");
        return new ProductViewPage(driver);
    }

    public ProductGridPage(WebDriver driver) {
        super(driver);
    }
}
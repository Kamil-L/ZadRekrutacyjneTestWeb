package cucumber.pages.products;

import cucumber.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductViewPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(ProductViewPage.class);

    @FindBy(xpath = "//section[contains(@class,'StyledDetailsPanel')]")
    private WebElement productView;

    @FindBy(xpath = "//*[@data-qa='PRD_ProductName']")
    private WebElement productName;

    public WebElement getProductName() {
        return productName;
    }

    public boolean isProductsViewDisplayed() {
        waitForPageLoadComplete();
        waitToBeVisible(productView);
        try {
            boolean isProductsViewDisplayed = productView.isDisplayed();
            log.info("Product view element is displayed: {}", productView);
            return isProductsViewDisplayed;
        } catch (Exception e) {
            log.error("Exception while checking if Product view is displayed: {}", e.getMessage());
            return false;
        }
    }

    public ProductViewPage(WebDriver driver) {
        super(driver);
    }
}
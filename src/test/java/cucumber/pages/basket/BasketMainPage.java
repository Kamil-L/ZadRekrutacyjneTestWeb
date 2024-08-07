package cucumber.pages.basket;

import cucumber.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasketMainPage extends BasePage {
    private static final Logger log = LoggerFactory.getLogger(BasketMainPage.class);

    @FindBy(xpath = "//div[contains(@class,'basketHeader')]/h1[contains(@class,'basketHeaderText')]")
    private WebElement basketName;

    @FindBy(xpath = "//*[@data-qa='BKT_ItemTitle0']/h2")
    private WebElement productName;

    public WebElement getProductName() {
        return productName;
    }

    public boolean isBasketMainPageDisplayed() {
        waitForPageLoadComplete();
        waitToBeVisible(basketName);
        try {
            boolean isBasketMainPageDisplayed = basketName.isDisplayed();
            log.info("Basket main page element is displayed: {}", basketName.getText());
            return isBasketMainPageDisplayed;
        } catch (Exception e) {
            log.error("Exception while checking if Basket main page is displayed: {}", e.getMessage());
            return false;
        }
    }

    public BasketMainPage(WebDriver driver) {
        super(driver);
    }
}
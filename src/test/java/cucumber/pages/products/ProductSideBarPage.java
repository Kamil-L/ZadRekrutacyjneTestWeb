package cucumber.pages.products;

import cucumber.pages.BasePage;
import cucumber.pages.basket.BasketMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductSideBarPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(ProductSideBarPage.class);

    @FindBy(xpath = "//span[contains(@class,'grid-child')]//*[@data-qa='PRD_AddToBasket']")
    private WebElement addToBasketBtn;

    @FindBy(xpath = "//span[contains(@class,'grid-child')]//*[@data-qa='PRD_TotalUpfront']")
    private WebElement initialPaymentPrice;

    @FindBy(xpath = "//span[contains(@class,'grid-child')]//div[contains(@class,'priceRightSection')]//div[contains(@class,'dt_price_change')]/*[1]")
    private WebElement monthlyPaymentPrice;

    public WebElement getAddToBasketBtn() {
        return addToBasketBtn;
    }

    public WebElement getInitialPaymentPrice() {
        return initialPaymentPrice;
    }

    public WebElement getMonthlyPaymentPrice() {
        return monthlyPaymentPrice;
    }

    public BasketMainPage clickAddToBasketBtn() {
        waitForPageLoadComplete();
        waitToBeVisible(addToBasketBtn);
        click(addToBasketBtn);
        log.info("<<<< Clicked on add to basket button >>>>");
        return new BasketMainPage(driver);
    }

    public ProductSideBarPage(WebDriver driver) {
        super(driver);
    }
}
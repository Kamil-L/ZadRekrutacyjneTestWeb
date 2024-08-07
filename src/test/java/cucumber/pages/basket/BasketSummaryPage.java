package cucumber.pages.basket;

import cucumber.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasketSummaryPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(BasketSummaryPage.class);

    @FindBy(xpath = "//*[@data-qa='BKT_TotalupFront']")
    private WebElement initialPaymentTotalValue;

    @FindBy(xpath = "//*[@data-qa='BKT_TotalupFrontSymbol']")
    private WebElement initialPaymentTotalCurrency;

    @FindBy(xpath = "//*[@data-qa='BKT_TotalMonthly']")
    private WebElement monthlyPaymentTotalValue;

    @FindBy(xpath = "//*[@data-qa='BKT_TotalMonthlySymbol']")
    private WebElement monthlyPaymentTotalCurrency;

    @FindBy(xpath = "//*[@id='basketSummary']")
    private WebElement basketSummaryPanel;

    public WebElement getInitialPaymentTotalValue() {
        return initialPaymentTotalValue;
    }

    public WebElement getInitialPaymentTotalCurrency() {
        return initialPaymentTotalCurrency;
    }

    public WebElement getMonthlyPaymentTotalValue() {
        return monthlyPaymentTotalValue;
    }

    public WebElement getMonthlyPaymentTotalCurrency() {
        return monthlyPaymentTotalCurrency;
    }

    public boolean isBasketSummaryPageDisplayed() {
        waitForPageLoadComplete();
        waitToBeVisible(initialPaymentTotalValue);
        try {
            boolean isBasketSummaryPageDisplayed = initialPaymentTotalValue.isDisplayed();
            log.info("Basket summary page element is displayed: {}", initialPaymentTotalValue);
            return isBasketSummaryPageDisplayed;
        } catch (Exception e) {
            log.error("Exception while checking if Basket summary page is displayed: {}", e.getMessage());
            return false;
        }
    }

    public BasketSummaryPage(WebDriver driver) {
        super(driver);
    }
}
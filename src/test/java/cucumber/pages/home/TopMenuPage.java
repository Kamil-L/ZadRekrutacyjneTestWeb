package cucumber.pages.home;

import cucumber.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopMenuPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(TopMenuPage.class);

    @FindBy(xpath = "//*[@id='main-menu']//button[contains(text(), 'Urządzenia')]")
    private WebElement productsBtn;

    @FindBy(xpath = "//div[contains(@class,'flex ml-auto lg:mt-auto')]//*[contains(@class,'group-hover/basket')]")
    private WebElement menuBasketIcon;

    @FindBy(xpath = "//div[contains(@class,'flex ml-auto lg:mt-auto group')]//*[@data-ma='menu-basket']/div")
    private WebElement menuBasketIconCounter;

    @FindBy(xpath = "//*[@data-ga-ea='nav-links - Urządzenia/Bez abonamentu/Smartfony']")
    private WebElement withoutAboSmartphonesBtn;

    @FindBy(xpath = "(//div[contains(@class,'hidden menu-dropdown-submenu')])[1]")
    private WebElement subMenuDropdown;

    public TopMenuPage clickWithoutAboSmartphonesBtn() {
        waitForPageLoadComplete();
        jsExecutorClick(withoutAboSmartphonesBtn, "//*[@data-ga-ea='nav-links - Urządzenia/Bez abonamentu/Smartfony']");
        log.info("<<<< Clicked on without abonament smartphones button >>>>");
        return this;
    }

    public boolean isSubMenuDropdownDisplayed() {
        waitForPageLoadComplete();
        try {
            boolean isSubMenuDropdownDisplayed = subMenuDropdown.isDisplayed();
            log.info("SubMenu dropdown element is displayed: {}", subMenuDropdown);
            return isSubMenuDropdownDisplayed;
        } catch (Exception e) {
            log.error("Exception while checking if SubMenu dropdown is displayed: {}", e.getMessage());
            return false;
        }
    }

    public WebElement getMenuBasketIconCounter() {
        return menuBasketIconCounter;
    }

    public TopMenuPage clickProductsBtn() {
        waitForPageLoadComplete();
        moveToElement(productsBtn);
        log.info("<<<< Clicked on Products button >>>>");
        return this;
    }

    public boolean isMenuBasketIconDisplayed() {
        waitForPageLoadComplete();
        waitToBeVisible(menuBasketIcon);
        try {
            boolean isMenuBasketIconDisplayed = menuBasketIcon.isDisplayed();
            log.info("Menu basket icon element is displayed: {}", menuBasketIcon);
            return isMenuBasketIconDisplayed;
        } catch (Exception e) {
            log.error("Exception while checking if Menu basket icon is displayed: {}", e.getMessage());
            return false;
        }
    }

    public TopMenuPage(WebDriver driver) {
        super(driver);
    }
}
package cucumber.pages.home;

import cucumber.config.ConfigReader;
import cucumber.pages.BasePage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(HomePage.class);

    @FindBy(xpath = "//*[@id='content-main']")
    private WebElement contentMain;

    @FindBy(xpath = "//*[@id='didomi-notice-agree-button']")
    private WebElement cookiesAgreeBtn;

    public HomePage acceptAllCookies() {
        waitForPageLoadComplete();
        waitToBeClickable(cookiesAgreeBtn);
        if (cookiesAgreeBtn.isDisplayed() && cookiesAgreeBtn.isEnabled()) {
            jsExecutorClick(cookiesAgreeBtn, "//*[@id='didomi-notice-agree-button']");
            log.info("<<<< Clicked on Cookies agree button >>>>");
        }
        return this;
    }

    public HomePage goToHomePage() {
        driver.get(ConfigReader.getProperty("appUrl"));
        Assertions.assertThat(isHomePageLoaded()).isTrue();
        return this;
    }

    public boolean isHomePageLoaded() {
        waitForPageLoadComplete();
        waitToBeVisible(contentMain);
        try {
            boolean isContentDisplayed = contentMain.isDisplayed();
            log.info("Content main element is displayed: {}", isContentDisplayed);
            return isContentDisplayed;
        } catch (Exception e) {
            log.error("Exception while checking if the page is loaded: {}", e.getMessage());
            return false;
        }
    }

    public HomePage(WebDriver driver) {
        super(driver);
    }
}
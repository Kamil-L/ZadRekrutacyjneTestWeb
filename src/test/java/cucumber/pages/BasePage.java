package cucumber.pages;

import cucumber.config.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.function.Function;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public abstract class BasePage {

    private static final Logger log = LoggerFactory.getLogger(BasePage.class);

    public WebDriver driver;
    public Actions actions;
    public FluentWait<WebDriver> wait;
    public JavascriptExecutor jsExecutor;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
        long waitTime = Long.parseLong(ConfigReader.getProperty("webelementTimeout"));
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(waitTime))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class);
    }

    protected WebElement waitForElement(Function<WebDriver, WebElement> condition) {
        return wait.until(condition);
    }

    public void waitForPageLoadComplete() {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void waitToBeClickable(WebElement element) {
        if (wait != null) {
            waitForPageLoadComplete();
            waitForElement(elementToBeClickable(element));
            log.info("<<<<< Wait for element: " + element.getText() + " to be clickable >>>>>");
        }
    }

    public void click(WebElement element) {
        waitToBeClickable(element);
        log.info("<<<<< Clicking on: " + element.getText() + " >>>>>");
        element.click();
    }

    public void moveToElement(WebElement element) {
        waitToBeClickable(element);
        actions.moveToElement(element).perform();
        log.info("<<<<< Move to element: " + element.getText() + " >>>>>");
    }

    public void waitToBeVisible(WebElement element) {
        if (wait != null) {
            waitForElement(visibilityOf(element));
            log.info("<<<<< Wait for element: " + element.getText() + " to be visible >>>>>");
        }
    }

    public boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            log.debug("No such element: " + element);
            return false;
        }
    }

    public void jsExecutorClick(WebElement element, String path) {
        element = driver.findElement(By.xpath(path));
        String javascript = "arguments[0]. click()";
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(javascript, element);
    }

    public void jsExecutorScrollIntoView(WebElement element, String path) {
        element = driver.findElement(By.xpath(path));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
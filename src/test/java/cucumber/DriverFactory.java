package cucumber;

import cucumber.config.ConfigReader;
import cucumber.utils.TestContext;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DriverFactory {
    private static Logger log = LoggerFactory.getLogger(DriverFactory.class);

    public static WebDriver driver;

    private TestContext testContext;

    public DriverFactory(TestContext testContext) {
        this.testContext = testContext;
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigReader.getProperty("browserName");
            log.info("BrowserName: " + browser);
            switch (browser.toLowerCase().strip()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalStateException("Incorrect browser type! Please check your configuration");
            }
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static void quitDriver() {
        driver.quit();
        driver = null;
    }

    public static void processExecutedScenario(Scenario scenario) throws IOException {
        if (scenario.isFailed() && driver != null) {
            quitDriver();
        }
        log.debug("Executed scenario failed: " + scenario);
    }
}
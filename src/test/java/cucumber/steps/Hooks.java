package cucumber.steps;

import cucumber.DriverFactory;
import cucumber.utils.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Hooks {

    static Logger log = LoggerFactory.getLogger(Hooks.class);

    private TestContext testContext;

    public void testSetup(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before
    static void setDriver() {
        DriverFactory.getDriver();
        log.info("<<<<< Driver initialized >>>>");
    }

    @After
    static void tearDown(Scenario scenario) throws IOException {
        DriverFactory.processExecutedScenario(scenario);
        log.info("<<<<< Driver closed >>>>>");
    }
}
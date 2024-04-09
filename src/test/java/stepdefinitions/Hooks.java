package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverFactory;

public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.getDriver(); // Initialize the WebDriver
    }

    @After
    public void tearDown() {
        DriverFactory.closeDriver(); // Close and quit the WebDriver
    }
}

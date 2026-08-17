package org.selenium.com.weathershopper;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.selenium.com.weathershopper.pages.LandingPage;
import org.selenium.com.weathershopper.utils.BrowserManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    WebDriver driver;
    protected LandingPage page;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod(alwaysRun = true) // because if we run a selected groups then they will not run before method
    public void setup() {

        driver = BrowserManager.doBrowserSetUp();
        page = new LandingPage(driver);
        page.open();
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() throws IOException {
        if (driver != null) {
            driver.quit();
        }

    }

    // @DataProvider
    // public Object[][] getData() {

    // HashMap<Object, Object> map = new HashMap<>();
    // return new Object[][] { {} };

    // }

}

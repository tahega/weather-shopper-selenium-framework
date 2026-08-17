package org.selenium.com.uiautomation.utils;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserManager {

    public static WebDriver doBrowserSetUp() {

        String browser = PropertyLoader.loadProperty("browser.name");
        WebDriver driver = null;

        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().browserVersion("151").setup();

            ChromeOptions option = new ChromeOptions();
            option.addArguments("--disable-notifications");
            String headless = System.getProperty("headless");
            if (headless != null && headless.equalsIgnoreCase("true")) {
                option.addArguments("--headless");
            }
            driver = new ChromeDriver(option);

        } else if (browser.equalsIgnoreCase("firefox")) {

            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions option = new FirefoxOptions();
            option.addArguments("--disable-notifications");
            driver = new FirefoxDriver(option);

        }

        if (driver == null)
            throw new InvalidElementStateException("Webdriver not assigned");
        driver.manage().window().maximize();

        return driver;

    }

}

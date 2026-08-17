package org.bp.com.uiautomation.pages;

import org.bp.com.uiautomation.base.BasePage;
import org.bp.com.uiautomation.utils.PropertyLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends BasePage {

    public static final String URL = PropertyLoader.loadProperty("base.url");

    private By _buyMoisturizerButton = By.xpath("//a[@href='/moisturizer']/button");
    private By _buySunScreenButton = By.xpath("//a[@href='/sunscreen']/button");
    private By _tempDisplay = By.cssSelector("span[id=\"temperature\"]");

    public LandingPage(WebDriver driver) {

        super(driver);

    }

    public void open() {
        this.driver.get(URL);

    }

    public String getTitle() {
        return this.driver.getTitle();
    }

    public boolean isBuyMoisturizerButtonEnabled() {

        return waitForVisibility(_buyMoisturizerButton).isEnabled();
    }

    public boolean IsbuySunscreenButtonEnabled() {

        return waitForVisibility(_buySunScreenButton).isEnabled();
    }

    public MositurizerPage clickBuyMositurizer() {

        waitForVisibility(_buyMoisturizerButton).click();
        return new MositurizerPage(driver); // encapsulation

    }

    public SunscreenPage clickSunScreenButton() {

        waitForVisibility(_buySunScreenButton).click();
        return new SunscreenPage(driver); // encapsulation

    }

    public boolean isTemperatureDisplayed() {
        return waitForVisibility(_tempDisplay).isDisplayed();
    }

    public String getTemp() {

        return waitForVisibility(_tempDisplay).getText();
    }

}

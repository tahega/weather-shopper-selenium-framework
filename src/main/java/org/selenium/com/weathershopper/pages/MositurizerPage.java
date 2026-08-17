package org.selenium.com.weathershopper.pages;

import org.openqa.selenium.WebDriver;
import org.selenium.com.weathershopper.base.BasePage;
import org.selenium.com.weathershopper.utils.Endpoint;

public class MositurizerPage extends BasePage {

    public MositurizerPage(WebDriver driver) {

        super(driver);
    }

    public void pageLoad() {
        waitForUrlContains(Endpoint.MOISTURIZER);
        waitForVisibility(goToCartButton);
    }

}

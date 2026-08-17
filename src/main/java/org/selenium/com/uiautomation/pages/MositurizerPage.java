package org.selenium.com.uiautomation.pages;

import org.openqa.selenium.WebDriver;
import org.selenium.com.uiautomation.base.BasePage;
import org.selenium.com.uiautomation.utils.Endpoint;

public class MositurizerPage extends BasePage {

    public MositurizerPage(WebDriver driver) {

        super(driver);
    }

    public void pageLoad() {
        waitForUrlContains(Endpoint.MOISTURIZER);
        waitForVisibility(goToCartButton);
    }

}

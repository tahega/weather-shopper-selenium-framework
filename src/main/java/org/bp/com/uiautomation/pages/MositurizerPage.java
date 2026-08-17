package org.bp.com.uiautomation.pages;

import org.bp.com.uiautomation.base.BasePage;
import org.bp.com.uiautomation.utils.Endpoint;
import org.openqa.selenium.WebDriver;

public class MositurizerPage extends BasePage {

    public MositurizerPage(WebDriver driver) {

        super(driver);
    }

    public void pageLoad() {
        waitForUrlContains(Endpoint.MOISTURIZER);
        waitForVisibility(goToCartButton);
    }

}

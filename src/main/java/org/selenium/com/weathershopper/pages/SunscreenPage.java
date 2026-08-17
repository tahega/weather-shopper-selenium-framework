package org.selenium.com.weathershopper.pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.selenium.com.weathershopper.base.BasePage;
import org.selenium.com.weathershopper.utils.Endpoint;

public class SunscreenPage extends BasePage {

    private By _allscreenProducts = By.xpath("//div[@class='text-center col-4']");
    // private By _addButton = By.xpath("//button[normalize-space()='Add']");
    private final By PRODUCT_NAME = By.tagName("p");
    private final By PRODUCT_HEADING = By.cssSelector("div.container h2");
    private final By PRODUCT_IMAGE = By.tagName("img");
    private final By PRODUCT_PRICE = By.xpath("p[2]");
    private final By ADD_BUTTON = By.tagName("button");

    public SunscreenPage(WebDriver driver) {
        super(driver);
    }

    public void pageLoad() {
        waitForUrlContains(Endpoint.SUNSCREEN);
        waitForVisibility(goToCartButton);
    }

    public String getPageHeading() {

        return waitForVisibility(PRODUCT_HEADING).getText();

    }

    public List<WebElement> allSunScreenProducts() {

        return waitForAllVisible(_allscreenProducts);
    }

    public List<String> getAllProductName() {

        return allSunScreenProducts().stream()
                .map(element -> element.findElement(PRODUCT_NAME).getText())
                .toList();
    }

    public List<WebElement> getAllProductImage() {

        return allSunScreenProducts().stream()
                .map(element -> element.findElement(PRODUCT_IMAGE))
                .toList();
    }

    public List<String> getAllProductPrice() {

        return allSunScreenProducts().stream()
                .map(element -> element.findElement(PRODUCT_PRICE).getText())
                .toList();
    }

    public WebElement getProductByName(String sunscreenName) {

        return allSunScreenProducts().stream()
                .filter(element -> element.findElement(PRODUCT_NAME).getText().equals(sunscreenName))
                .findFirst().orElse(null);
    }

    public void selectProduct(String sunscreenName) {
        WebElement product = getProductByName(sunscreenName);
        if (product == null) {
            throw new NoSuchElementException("Product not found: " + sunscreenName);
        }
        waitForVisibility(product.findElement(ADD_BUTTON)).click();
    }

    @Override
    public int getCountOfItemsSelected() {
        return super.getCountOfItemsSelected();
    }

}

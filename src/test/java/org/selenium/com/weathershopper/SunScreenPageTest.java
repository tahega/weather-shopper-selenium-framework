package org.selenium.com.weathershopper;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.selenium.com.weathershopper.pages.SunscreenPage;
import org.selenium.com.weathershopper.utils.Endpoint;
import org.selenium.com.weathershopper.listeners.Retry;
import org.selenium.com.weathershopper.utils.TestDataProviders;
import org.selenium.com.weathershopper.utils.pojo.ProductPojo;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SunScreenPageTest extends BaseTest {

    @Test
    public void verifySunscreenPageLoadsSuccessfully() {

        SunscreenPage sunscreenPage = page.clickSunScreenButton();
        sunscreenPage.pageLoad();
        String currentUrl = driver.getCurrentUrl();
        String expectedHeading = "Sunscreens";

        Assert.assertTrue(currentUrl.contains(Endpoint.SUNSCREEN),
                "Expected url to contain sunscrenn but found :" + currentUrl);

        Assert.assertEquals(sunscreenPage.getPageHeading(), expectedHeading,
                "Expected page heading " + expectedHeading + " but found " + sunscreenPage.getPageHeading());

    }

    @Test
    public void verifyExpectedProductsAreDisplayed() throws InterruptedException {

        SunscreenPage sunscreenPage = page.clickSunScreenButton();
        sunscreenPage.pageLoad();

        List<String> expectedProducts = TestDataProviders.sunscreenProducts();
        List<String> allProductNames = sunscreenPage.getAllProductName();
        Assert.assertEquals(allProductNames, expectedProducts,
                "Expected products " + expectedProducts + " but found " + allProductNames);

    }

    @Test(retryAnalyzer = Retry.class)
    public void verifyAllimagesAndPricesAreLoaded() {

        SunscreenPage sunscreenPage = page.clickSunScreenButton();
        sunscreenPage.pageLoad();

        // verify images
        List<WebElement> image = sunscreenPage.getAllProductImage();
        List<String> prices = sunscreenPage.getAllProductPrice();

        JavascriptExecutor executor = (JavascriptExecutor) driver;

        for (WebElement img : image) {
            long naturalHeight = (long) executor.executeScript(
                    "return arguments[0].naturalHeight;", img);

            Assert.assertTrue(naturalHeight > 0,
                    "Expected image to be loaded (naturalHeight > 0) but found naturalHeight = " + naturalHeight);
        }

        // verify prices

        Pattern pricePattern = Pattern.compile("\\d+(\\.\\d+)?");

        for (String priceText : prices) {

            Assert.assertTrue(priceText.startsWith("Price: Rs."),
                    "Expected price to start with 'Price: Rs.' but found " + priceText);

            Matcher priceMatcher = pricePattern.matcher(priceText);
            if (priceMatcher.find()) {
                String price = priceMatcher.group();
                double pricevalue = Double.parseDouble(price);

                Assert.assertTrue(pricevalue > 0, "Expected price value greater then 0 but found: " + pricevalue);

            }

        }

    }

    @Test(retryAnalyzer = Retry.class)
    public void verifyProductCanBeAddedToCart() {

        SunscreenPage sunscreenPage = page.clickSunScreenButton();
        sunscreenPage.pageLoad();

        List<ProductPojo> listOfItems = TestDataProviders.selectProducts();

        for (ProductPojo item : listOfItems) {

            sunscreenPage.selectProduct(item.getName());
        }

        Assert.assertEquals(sunscreenPage.getCountOfItemsSelected(), listOfItems.size(),
                "Expected selected item count to match selected products count");

    }

}

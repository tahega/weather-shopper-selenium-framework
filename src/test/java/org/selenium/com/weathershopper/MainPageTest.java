package org.selenium.com.weathershopper;

import org.testng.annotations.Test;
import org.selenium.com.weathershopper.pages.MositurizerPage;
import org.selenium.com.weathershopper.pages.SunscreenPage;

import org.testng.Assert;

public class MainPageTest extends BaseTest {

        @Test(groups = "smoke")
        public void verifyHomePageLoads() {

                String expectedTitle = "Current Temperature";

                Assert.assertTrue(page.isTemperatureDisplayed(),
                                "Expected Temperature widget to display temp but not found");

                Assert.assertEquals(page.getTitle(), expectedTitle,
                                "Expected Title " + expectedTitle + " but found " + page.getTitle());

        }

        @Test
        public void verifyCurrentTemperatureIsDisplayed() {

                String currentTemp = page.getTemp();
                String[] tempAttributes = currentTemp.trim().split("\\s+");

                Assert.assertTrue(tempAttributes[1].endsWith("C"),
                                "Expected current Temp displayed to be in °C but found " + currentTemp);

                Assert.assertTrue(tempAttributes[0].matches("\\d+"),
                                "Expected current Temp displayed to be in \u00B0C but found " + currentTemp);

        }

        @Test
        public void verifyBuyMositurizerButtonFunctionality() {

                Assert.assertTrue(page.isBuyMoisturizerButtonEnabled(),
                                "Expected Buy Moisturizer button to be enabled before clicking");

                MositurizerPage moisturizerPage = page.clickBuyMositurizer();
                moisturizerPage.pageLoad();

        }

        @Test
        public void verifyBuySunscreenButtonFunctionality() {

                Assert.assertTrue(page.IsbuySunscreenButtonEnabled(),
                                "Expected Buy Sunscreen button to be enabled before clicking");

                SunscreenPage sunscreen = page.clickSunScreenButton();
                sunscreen.pageLoad();

        }

        @Test
        public void verifyCorrectRecommendationBasedOnTemperature() {

                String currentTemp = page.getTemp();
                String[] tempAttributes = currentTemp.trim().split("\\s+");

                int temp = Integer.parseInt(tempAttributes[0]);

                if (temp >= 30) {

                        Assert.assertTrue(page.IsbuySunscreenButtonEnabled(),
                                        "Expected Suncreen button to be enabled as temp >= 30");
                } else {
                        Assert.assertTrue(page.isBuyMoisturizerButtonEnabled(),
                                        "Expected Suncreen button to be enabled as temp < 30");

                }

        }

}

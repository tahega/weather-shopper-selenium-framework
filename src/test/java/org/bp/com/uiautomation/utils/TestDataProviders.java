package org.bp.com.uiautomation.utils;

import java.util.List;
import org.testng.annotations.*;
import com.fasterxml.jackson.core.type.TypeReference;

import org.bp.com.uiautomation.utils.pojo.ProductPojo;

public class TestDataProviders {

        public static List<String> sunscreenProducts() {

                List<String> products = JsonReader.readValue("testData/SunScreenProduct.json",
                                new TypeReference<List<String>>() {
                                });
                return products;
        }

        public static List<ProductPojo> selectProducts() {

                List<ProductPojo> products = JsonReader.readValue("testData/SelectSunScreenProduct.json",
                                new TypeReference<List<ProductPojo>>() {
                                });
                return products;
        }

        @DataProvider(name = "moisturizerProducts")
        public Object[][] moisturizerProducts() {

                List<String> products = JsonReader.readValue("testData/MoisturizerProduct.json",
                                new TypeReference<List<String>>() {
                                });

                return products.stream()
                                .map(product -> new Object[] { product })
                                .toArray(Object[][]::new);
        }

}

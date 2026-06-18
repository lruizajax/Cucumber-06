package com.magento.utils;

import com.magento.driver.DriverFactory;
import com.magento.driver.DriverManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setup() {
        DriverFactory.createDriver();
        DriverManager.initWait();
    }

    @After
    public void tearDown() {
        DriverManager.cleanUp();
    }
}

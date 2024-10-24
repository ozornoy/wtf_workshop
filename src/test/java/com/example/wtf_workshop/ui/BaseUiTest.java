package com.example.wtf_workshop.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.example.wtf_workshop.api.config.Config;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import java.util.Map;

public class BaseUiTest {
    @BeforeSuite(alwaysRun = true)
    public void setupUiTest() {
        Configuration.browser = Config.getProperty("browser");
        Configuration.baseUrl = "http://" + Config.getProperty("host");
        Configuration.remote = Config.getProperty("remote");
        Configuration.browserSize = Config.getProperty("browserSize");
        Configuration.browserCapabilities.setCapability("selenoid.options",
                Map.of("enableVNC", true,
                        "enableLog", true));
    }

    @AfterMethod(alwaysRun = true)
    public void closeWebDriver() {
        Selenide.closeWebDriver();
    }

}

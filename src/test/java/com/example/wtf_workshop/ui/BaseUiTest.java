package com.example.wtf_workshop.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.example.wtf_workshop.BaseTest;
import com.example.wtf_workshop.api.config.Config;
import com.example.wtf_workshop.api.models.User;
import com.example.wtf_workshop.ui.pages.LoginPage;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import java.util.HashMap;
import java.util.Map;

import static com.example.wtf_workshop.api.enums.Endpoint.USERS;

public class BaseUiTest extends BaseTest {
    @BeforeSuite(alwaysRun = true)
    public void setupUiTest() {
        Configuration.browser = Config.getProperty("browser");
        Configuration.baseUrl = "http://" + Config.getProperty("host");
        Configuration.remote = Config.getProperty("remote");
        Configuration.browserSize = Config.getProperty("browserSize");

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", true);
        selenoidOptions.put("enableLog", true);
        options.setCapability("selenoid:options", selenoidOptions);

        Configuration.browserCapabilities = options;
    }

    @AfterMethod(alwaysRun = true)
    public void closeWebDriver() {
        Selenide.closeWebDriver();
    }

    protected void loginAs(User user) {
        superUserCheckedRequests.getRequest(USERS).create(user);
        LoginPage.open().login(user);
    }

}

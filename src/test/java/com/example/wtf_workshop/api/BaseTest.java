package com.example.wtf_workshop.api;

import com.example.wtf_workshop.api.requests.CheckedRequests;
import com.example.wtf_workshop.api.spec.Specifications;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    protected SoftAssert softy;
    protected CheckedRequests superUserCheckedRequests = new CheckedRequests(Specifications.superUserSpec());
    
    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        softy = new SoftAssert();
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest() {
        softy.assertAll();
    }
}

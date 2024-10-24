package com.example.wtf_workshop;

import com.example.wtf_workshop.api.generators.TestDataStorage;
import com.example.wtf_workshop.api.models.TestData;
import com.example.wtf_workshop.api.requests.CheckedRequests;
import com.example.wtf_workshop.api.spec.Specifications;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import static com.example.wtf_workshop.api.generators.TestDataGenerator.generate;

public class BaseTest {
    protected SoftAssert softy;
    protected CheckedRequests superUserCheckedRequests = new CheckedRequests(Specifications.superUserSpec());
    protected TestData testData;
    
    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        softy = new SoftAssert();
        testData = generate();
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest() {
        softy.assertAll();
        TestDataStorage.getStorage().deleteCreatedEntity();
    }
}

package com.example.wtf_workshop.api;


import com.example.wtf_workshop.api.models.*;
import com.example.wtf_workshop.api.requests.CheckedRequests;
import com.example.wtf_workshop.api.spec.Specifications;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static com.example.wtf_workshop.api.enums.Endpoint.*;
import static com.example.wtf_workshop.api.generators.TestDataGenerator.generate;
import static org.awaitility.Awaitility.await;
import static org.testng.AssertJUnit.assertEquals;

@Test(groups = {"Regression"})
public class BuildTest extends BaseApiTest {
    @Test(description = "User should be able to start build", groups = {"Positive", "Execution"})
    public void userStartsBuildTest() {
        var userCheckedRequests = new CheckedRequests(Specifications.authSpec(testData.getUser()));
        var stepProperty1 = generate(Property.class, "use.custom.script", "true");
        var stepProperty2 = generate(Property.class, "script.content", "echo 'Hello World!'");
        var stepProperty3 = generate(Property.class, "teamcity.step.mode", "default");
        testData.getBuildType().getSteps().getStep().get(0).getProperties().setProperty(Arrays.asList(stepProperty1, stepProperty2, stepProperty3));

        superUserCheckedRequests.getRequest(USERS).create(testData.getUser());
        userCheckedRequests.getRequest(PROJECTS).create(testData.getProject());
        userCheckedRequests.getRequest(BUILD_TYPES).create(testData.getBuildType());

        var buildQueue = generate(BuildQueue.class, generate(BuildTypeId.class, testData.getBuildType().getId()));
        userCheckedRequests.getRequest(BUILD_QUEUE).startBuild(buildQueue);

        await().atMost(5, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).until(() -> {
            var builds = userCheckedRequests.<Builds>getRequest(BUILDS)
                    .search("buildType:" + testData.getBuildType().getId());
            if (builds.getCount() > 0) {
                String state = builds.getBuild().get(0).getState();
                return "finished".equals(state);
            }
            return false;
        });

        var build = userCheckedRequests.<Builds>getRequest(BUILDS)
                .search("buildType:" + testData.getBuildType().getId()).getBuild().get(0);
        assertEquals("Expected build status to be SUCCESS. Actual status: " + build.getStatus(),
                "SUCCESS", build.getStatus());
    }
}

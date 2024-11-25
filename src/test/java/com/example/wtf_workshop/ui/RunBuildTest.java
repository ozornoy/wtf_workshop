package com.example.wtf_workshop.ui;

import com.codeborne.selenide.Condition;
import com.example.wtf_workshop.api.models.Builds;
import com.example.wtf_workshop.api.models.Property;
import com.example.wtf_workshop.ui.pages.BuildPage;
import com.example.wtf_workshop.ui.pages.ProjectPage;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static com.example.wtf_workshop.api.enums.Endpoint.*;
import static com.example.wtf_workshop.api.generators.TestDataGenerator.generate;
import static org.awaitility.Awaitility.await;

@Test(groups = {"Regression"})
public class RunBuildTest extends BaseUiTest{
    @Test(description = "User should be able to run build type", groups = {"Positive"})
    public void userRunsBuildTest() {
        loginAs(testData.getUser());
        var stepProperty1 = generate(Property.class, "use.custom.script", "true");
        var stepProperty2 = generate(Property.class, "script.content", "echo 'Hello World!'");
        var stepProperty3 = generate(Property.class, "teamcity.step.mode", "default");
        testData.getBuildType().getSteps().getStep().get(0).getProperties().setProperty(Arrays.asList(stepProperty1, stepProperty2, stepProperty3));
        superUserCheckedRequests.getRequest(PROJECTS).create(testData.getProject());
        superUserCheckedRequests.getRequest(BUILD_TYPES).create(testData.getBuildType());

        var buildTypeElement = ProjectPage.open(testData.getProject().getId()).getBuildTypes().get(0)
                .clickOnDetailsButton();
        var buildElementsSize = buildTypeElement.getBuilds().size();
        buildTypeElement.runBuild()
                .checkBuildsCount(buildElementsSize + 1);

        await().atMost(30, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).until(() -> {
            var builds = superUserCheckedRequests.<Builds>getRequest(BUILDS_SEARCH)
                    .search("buildType:" + testData.getBuildType().getId());
            if (builds.getCount() > 0) {
                String state = builds.getBuild().get(0).getState();
                return "finished".equals(state);
            }
            return false;
        });

        buildTypeElement.getBuilds().get(0)
                .successStatusIconShouldBeVisible()
                .clickOnDetailsButton()
                .clickOnDetailsTab("Build Log");
        var buildStepLogMessage = new BuildPage().getLogs().stream()
                .filter(log -> log.getLogText().has(Condition.text("Step 1/1:")))
                .findFirst().get();
        buildStepLogMessage.clickOnCollapseButton().checkSubLogText(2, "Hello World!");
    }
}

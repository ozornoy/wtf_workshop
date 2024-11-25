package com.example.wtf_workshop.ui;

import com.example.wtf_workshop.ui.pages.ProjectPage;
import com.example.wtf_workshop.ui.pages.admin.CreateBuildTypePage;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.example.wtf_workshop.api.enums.Endpoint.*;

@Test(groups = {"Regression"})
public class CreateBuildTypeTest extends BaseUiTest{
    private static final String REPO_URL = "https://github.com/ozornoy/wtf_workshop";

    @Test(description = "User should be able to create build type", groups = {"Positive"})
    public void userCreatesBuildTypeTest() {
        superUserCheckedRequests.getRequest(PROJECTS).create(testData.getProject());
        loginAs(testData.getUser());

        ProjectPage.open(testData.getProject().getId());
        CreateBuildTypePage.open(testData.getProject().getId())
                .createForm(REPO_URL)
                .setupBuildType(testData.getBuildType().getName());

        var buildTypeExists = ProjectPage.open(testData.getProject().getId())
                .getBuildTypes().stream()
                .filter(buildTypeElement -> buildTypeElement.getName().text().equals(testData.getBuildType().getName()))
                .limit(2).count() == 1;
        softy.assertTrue(buildTypeExists);

        var createdBuildType = superUserCheckedRequests.getRequest(BUILD_TYPES)
                .read("name:" + testData.getBuildType().getName());
        softy.assertNotNull(createdBuildType);
    }

    @Test(description = "User should not na able to create build type without name", groups = {"Negative"})
    public void userCreatesProjectWithoutNameTest() {
        superUserCheckedRequests.getRequest(PROJECTS).create(testData.getProject());
        loginAs(testData.getUser());

        var createBuildTypePage = CreateBuildTypePage.open(testData.getProject().getId())
                .createForm(REPO_URL);
        createBuildTypePage.setupBuildType("");

        createBuildTypePage.checkBuildTypeNameErrorText("Build configuration name must not be empty");

        superUserUnCheckedRequests.getRequest(BUILD_TYPES)
                .read("name:" + testData.getBuildType().getName())
                .then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }
}

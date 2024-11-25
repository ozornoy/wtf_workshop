package com.example.wtf_workshop.ui;

import com.codeborne.selenide.Condition;
import com.example.wtf_workshop.api.enums.Endpoint;
import com.example.wtf_workshop.api.generators.TestDataStorage;
import com.example.wtf_workshop.api.models.Project;
import com.example.wtf_workshop.ui.pages.ProjectPage;
import com.example.wtf_workshop.ui.pages.ProjectsPage;
import com.example.wtf_workshop.ui.pages.admin.CreateProjectPage;
import org.testng.annotations.Test;

import static com.example.wtf_workshop.api.enums.Endpoint.PROJECTS;
import static org.hamcrest.Matchers.equalTo;

@Test(groups = {"Regression"})
public class CreateProjectTest extends BaseUiTest{
    private static final String REPO_URL = "https://github.com/ozornoy/wtf_workshop";

    @Test(description = "User should be able to create project", groups = {"Positive"})
    public void userCreatesProjectTest() {
        loginAs(testData.getUser());

        CreateProjectPage.open("_Root")
                .createForm(REPO_URL)
                .setupProject(testData.getProject().getName(), testData.getBuildType().getName());
        TestDataStorage.getStorage().addCreatedEntity(PROJECTS, testData.getProject());

        var projectExists = ProjectsPage.open()
                .getProjects().stream()
                .anyMatch(project -> project.getName().text().equals(testData.getProject().getName()));
        softy.assertTrue(projectExists);

        var createdProject = superUserCheckedRequests.<Project>getRequest(Endpoint.PROJECTS)
                .read("name:" + testData.getProject().getName());
        softy.assertNotNull(createdProject);

        ProjectPage.open(createdProject.getId())
                .title.shouldHave(Condition.exactText(testData.getProject().getName()));

    }


    @Test(description = "User should not na able to create project without name", groups = {"Negative"})
    public void userCreatesProjectWithoutNameTest() {
        loginAs(testData.getUser());

        var createProjectPage = CreateProjectPage.open("_Root").createForm(REPO_URL);
        createProjectPage.setupProject("", testData.getBuildType().getName());
        TestDataStorage.getStorage().addCreatedEntity(PROJECTS, testData.getProject());

        createProjectPage.checkProjectNameErrorText("Project name must not be empty");

        superUserUnCheckedRequests.getRequest(Endpoint.PROJECTS)
                .search(testData.getProject().getName())
                .then().assertThat().body("count", equalTo(0));
    }
}

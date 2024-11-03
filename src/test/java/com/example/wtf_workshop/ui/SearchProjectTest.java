package com.example.wtf_workshop.ui;

import com.codeborne.selenide.CollectionCondition;
import com.example.wtf_workshop.ui.pages.ProjectsPage;
import org.testng.annotations.Test;

import static com.example.wtf_workshop.api.enums.Endpoint.PROJECTS;

@Test(groups = {"Regression"})
public class SearchProjectTest extends BaseUiTest{
    @Test(description = "User should be able to search for a project by its name", groups = {"Positive"})
    public void userSearchesProjectByNameTest() {
        superUserCheckedRequests.getRequest(PROJECTS).create(testData.getProject());
        loginAs(testData.getUser());

        var projectPage = ProjectsPage.open();
        projectPage.getSidebar().searchProject(testData.getProject().getName());

        projectPage.getSidebar().getProjectElements().shouldHave(CollectionCondition.size(1));
        var projectExists = projectPage.getSidebar().getSidebarProjects().stream()
                .anyMatch(project -> project.getName().text().equals(testData.getProject().getName()));
        softy.assertTrue(projectExists);
    }
}

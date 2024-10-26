package com.example.wtf_workshop.ui;

import com.example.wtf_workshop.ui.pages.admin.CreateProjectPage;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;

@Test(groups = {"Regression"})
public class CreateProjectTest extends BaseUiTest{
    private static final String REPO_URL = "https://github.com/ozornoy/wtf_workshop";

    @Test(description = "User should be able to create project", groups = {"Positive"})
    public void userCreatesProject() {
        // подготовка окружения
        loginAs(testData.getUser());

        // взаимодействие с UI
        CreateProjectPage.open("_Root")
                .createForm(REPO_URL)
                .setupProject(testData.getProject().getName(), testData.getBuildType().getName());

        // проверка состояния API
        // (корректность отправки данных с UI на API)
        step("Check that all entites (project & buildType) was successfully created with correct data on Api level");

        // проверка состояния UI
        // (корректность считывания данных и отображение данных на UI)
        step("Check that project visible on Projects Page (http://localhost:8111/favorite/projects)");
    }


    @Test(description = "User should not na able to create project without name", groups = {"Negative"})
    public void userCreatesProjectWithoutName() {
        // подготовка окружения
        step("Login as user");
        step("Check number of projects");

        // взаимодействие с UI
        step("Open 'Create Project Page' (http://localhost:8111/admin/createObjectMenu.html)");
        step("Send all project parameters (Repository Url)");
        step("CLick 'Proceed'");

        // проверка состояния API
        // (корректность отправки данных с UI на API)
        step("Check that number of projects did not change");

        // проверка состояния UI
        // (корректность считывания данных и отображение данных на UI)
        step("Check that errors appears 'Project name must not be empty'");
    }
}

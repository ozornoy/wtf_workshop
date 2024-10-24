package com.example.wtf_workshop.ui;

import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;

@Test(groups = {"Regression"})
public class CreateProjectTest extends BaseUiTest{
    @Test(description = "User should be able to create project", groups = {"Positive"})
    public void userCreatesProject() {
        // подготовка окружения
        step("Login as User");

        // взаимодействие с UI
        step("Open 'Create Project Page' (http://localhost:8111/admin/createObjectMenu.html)");
        step("Send all project parameters (Repository Url)");
        step("CLick 'Proceed'");
        step("Fix Project name and Build Type name values");
        step("CLick 'Proceed'");

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

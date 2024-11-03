package com.example.wtf_workshop.ui.pages.admin;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.wtf_workshop.api.generators.TestDataStorage;
import com.example.wtf_workshop.ui.pages.ProjectsPage;

import static com.codeborne.selenide.Selenide.$;
import static com.example.wtf_workshop.api.enums.Endpoint.PROJECTS;

public class CreateProjectPage extends CreateBasePage {
    public static final String PROJECT_SHOW_MODE = "createProjectMenu";

    private SelenideElement projectNameInput = $("#projectName");
    public static SelenideElement projectNameError = $("#error_projectName");

    public static CreateProjectPage open(String projectId) {
        return Selenide.open(CREATE_URL.formatted(projectId, PROJECT_SHOW_MODE), CreateProjectPage.class);
    }

    public CreateProjectPage createForm(String url) {
        baseCreateForm(url);
        return this;
    }

    public void setupProject(String projectName, String buildTypeName) {
        projectNameInput.val(projectName);
        buildTypeUInput.val(buildTypeName);
        proceedButton.click();
    }

    public void checkProjectNameErrorText(String expectedErrorMessage) {
        projectNameError.shouldHave(Condition.text(expectedErrorMessage));
    }
}

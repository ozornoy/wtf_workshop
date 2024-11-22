package com.example.wtf_workshop.ui.pages.admin;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CreateBuildTypePage extends CreateBasePage{
    public static final String BUILD_TYPE_SHOW_MODE = "createBuildTypeMenu";

    private final SelenideElement buildTypeNameInput = $("#buildTypeName");
    private final SelenideElement buildTypeNameError = $("#error_buildTypeName");

    public static CreateBuildTypePage open(String projectId) {
        return Selenide.open(CREATE_URL.formatted(projectId, BUILD_TYPE_SHOW_MODE), CreateBuildTypePage.class);
    }

    public CreateBuildTypePage createForm(String url) {
        baseCreateForm(url);
        return this;
    }

    public void setupBuildType(String buildTypeMame) {
        buildTypeNameInput.val(buildTypeMame);
        proceedButton.click();
    }

    public void checkBuildTypeNameErrorText(String expectedErrorMessage) {
        buildTypeNameError.shouldHave(Condition.text(expectedErrorMessage));
    }
}

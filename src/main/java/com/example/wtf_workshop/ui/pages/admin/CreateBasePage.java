package com.example.wtf_workshop.ui.pages.admin;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import com.example.wtf_workshop.ui.pages.BasePage;

import static com.codeborne.selenide.Selenide.$;

public abstract class CreateBasePage extends BasePage {
    protected static final String CREATE_URL = "/admin/createObjectMenu.html?projectId=%s&showMode=%s";

    protected SelenideElement urlInput = $("#url");
    protected SelenideElement proceedButton = $(Selectors.byAttribute("value", "Proceed"));
    protected SelenideElement buildTypeUInput = $("#buildTypeName");

    protected void baseCreateForm(String url) {
        urlInput.val(url);
        proceedButton.click();
    }
}

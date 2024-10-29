package com.example.wtf_workshop.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProjectPage extends BasePage{
    private static final String projectUrl = "/project/%s";

    public SelenideElement title = $("[class*=ProjectPageHeader__title]");

    public static ProjectPage open(String projectId) {
        return Selenide.open(projectUrl.formatted(projectId), ProjectPage.class);
    }
}

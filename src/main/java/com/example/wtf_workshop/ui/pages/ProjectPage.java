package com.example.wtf_workshop.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.wtf_workshop.ui.elements.BuildElement;
import com.example.wtf_workshop.ui.elements.BuildTypeElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProjectPage extends BasePage{
    private static final String projectUrl = "/project/%s";

    public SelenideElement title = $("[class*=ProjectPageHeader__title]");
    public ElementsCollection buildTypeElements = $$("[class*=BuildTypes__item]");

    public ProjectPage() {
        title.shouldBe(Condition.visible, BASE_WAITING);
    }

    public static ProjectPage open(String projectId) {
        return Selenide.open(projectUrl.formatted(projectId), ProjectPage.class);
    }

    public List<BuildTypeElement> getBuildTypes() {
        return generatePageElements(buildTypeElements, BuildTypeElement::new);
    }
}

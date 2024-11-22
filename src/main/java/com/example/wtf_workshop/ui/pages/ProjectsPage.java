package com.example.wtf_workshop.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.wtf_workshop.ui.elements.ProjectElement;
import com.example.wtf_workshop.ui.elements.SidebarProjectsElement;
import lombok.Getter;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Getter
public class ProjectsPage extends BasePage{
    public static final String PROJECTS_URL = "/favorite/projects";

    private ElementsCollection projectElements = $$("[class*=Subproject__container]");
    private SelenideElement header = $("[data-test=\"overview-header\"]");

    private SidebarProjectsElement sidebar;

    public ProjectsPage() {
        header.should(Condition.visible, BASE_WAITING);
        this.sidebar = new SidebarProjectsElement($("[data-test=sidebar]"));
    }

    public static ProjectsPage open() {
        return Selenide.open(PROJECTS_URL, ProjectsPage.class);
    }

    public List<ProjectElement> getProjects() {
        return generatePageElements(projectElements, ProjectElement::new);
    }

}

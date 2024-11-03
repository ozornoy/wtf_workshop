package com.example.wtf_workshop.ui.elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import java.util.List;


@Getter
public class SidebarProjectsElement extends BasePageElement{
    private SelenideElement searchInput;
    private ElementsCollection projectElements;

    public SidebarProjectsElement(SelenideElement element) {
        super(element);
        this.searchInput = find("[data-test=sidebar-search]");
        this.projectElements = findAll("[data-test-itemtype=project]");
    }

    public List<SidebarProjectElement> getSidebarProjects() {
        return generatePageElements(this.projectElements, SidebarProjectElement::new);
    }

    public SidebarProjectsElement searchProject(String projectName) {
        this.searchInput.val(projectName);
        return this;
    }
}

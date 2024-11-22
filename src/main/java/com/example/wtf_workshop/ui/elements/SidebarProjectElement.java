package com.example.wtf_workshop.ui.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class SidebarProjectElement extends BasePageElement{
    private SelenideElement name;
    private SelenideElement link;
    private SelenideElement collapseButton;

    public SidebarProjectElement(SelenideElement element) {
        super(element);
        this.name = find("[class*=ProjectsTreeItem__name]");
        this.link = find("a");
        this.collapseButton = find("[data-test=collapse-button]");
    }
}

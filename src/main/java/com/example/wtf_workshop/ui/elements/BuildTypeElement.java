package com.example.wtf_workshop.ui.elements;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import java.util.List;

@Getter
public class BuildTypeElement extends BasePageElement{
    private SelenideElement name;
    private SelenideElement link;
    private SelenideElement details_button;
    private SelenideElement runBuildButton;
    private ElementsCollection buildElements;

    public BuildTypeElement(SelenideElement element) {
        super(element);
        this.name = find("[class*=MiddleEllipsis__searchable]");
        this.link = find("a");
        this.details_button = find("[class*=Details__button]");
        this.runBuildButton = find("[data-test=run-build]");
        this.buildElements = findAll("[class*=buildContainer]");
    }

    public BuildTypeElement clickOnDetailsButton() {
        this.details_button.click();
        return this;
    }

    public BuildTypeElement runBuild() {
        this.runBuildButton.click();
        return this;
    }

    public List<BuildElement> getBuilds() {
        return generatePageElements(this.buildElements, BuildElement::new);
    }

    public BuildTypeElement checkBuildsCount(int sizeNUmber) {
        this.buildElements.shouldHave(CollectionCondition.size(sizeNUmber));
        return this;
    }
}

package com.example.wtf_workshop.ui.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.wtf_workshop.ui.pages.BuildPage;
import lombok.Getter;

import java.util.Objects;

@Getter
public class BuildElement extends BasePageElement{
    private SelenideElement status;
    private SelenideElement detailsButton;
    private ElementsCollection detailsTabElements;

    public BuildElement(SelenideElement element) {
        super(element);
        this.status = find("[class*=Build__status]");
        this.detailsButton = find("[class*=BuildDetails__button]");
        this.detailsTabElements = findAll("[class*=BuildDetailsTabs__link]");
    }

    public BuildElement clickOnDetailsButton() {
        this.detailsButton.click();
        return this;
    }

    public BuildPage clickOnDetailsTab(String tabName) {
        this.detailsTabElements.findBy(Condition.exactText(tabName)).click();
        return Selenide.page(BuildPage.class);
    }

    public BuildElement checkStatus(String status_name) {
        var status_icon =  Objects.equals(status_name, "Running")
                ? "[data-test-icon=running_green]"
                : "[data-test-icon=finished_green]";
        find(status_icon).shouldBe(Condition.visible, BASE_WAITING);
        return this;
    }
}

package com.example.wtf_workshop.ui.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class BuildLogElement extends BasePageElement{
    private SelenideElement element;
    private SelenideElement logText;
    private SelenideElement collapseButton;

    public BuildLogElement(SelenideElement element) {
        super(element);
        this.element = element;
        this.logText = find("[class*=LogMessage__treeMessage]");
        this.collapseButton = find("[class*=LogMessage__collapseButton]");
    }

    public BuildLogElement clickOnCollapseButton() {
        this.collapseButton.click();
        return this;
    }

    public void checkSubLogText(int subLogIndex, String subLogText) {
        this.element.sibling(subLogIndex).shouldHave(Condition.text(subLogText));
    }
}

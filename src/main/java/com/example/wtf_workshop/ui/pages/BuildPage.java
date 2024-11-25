package com.example.wtf_workshop.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.wtf_workshop.ui.elements.BuildElement;
import com.example.wtf_workshop.ui.elements.BuildLogElement;
import com.example.wtf_workshop.ui.elements.BuildTypeElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BuildPage extends BasePage{
    public ElementsCollection logElements = $$("[class*=BuildLogMessages__messageFocus]");
    public SelenideElement logsTimeLine = $("[data-test-build-log-timeline=true]");

    public BuildPage() {
        this.logsTimeLine.shouldBe(Condition.visible, BASE_WAITING);
    }

    public List<BuildLogElement> getLogs() {
        return generatePageElements(logElements, BuildLogElement::new);
    }
}

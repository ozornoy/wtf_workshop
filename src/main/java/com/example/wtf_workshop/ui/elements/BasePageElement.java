package com.example.wtf_workshop.ui.elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public abstract class BasePageElement {
    protected static final Duration BASE_WAITING = Duration.ofSeconds(30);
    private final SelenideElement element;

    public BasePageElement(SelenideElement element) {
        this.element = element;
    }

    protected SelenideElement find(By selector) {
        return element.$(selector);
    }

    protected SelenideElement find(String cssSelector) {
        return element.$(cssSelector);
    }

    protected ElementsCollection findAll(By selector) {
        return element.$$(selector);
    }

    protected ElementsCollection findAll(String cssSelector) {
        return element.$$(cssSelector);
    }

    protected <T extends BasePageElement> List<T> generatePageElements(
            ElementsCollection collection, Function<SelenideElement, T> creator
    ) {
        return collection.stream().map(creator).toList();
    }
}

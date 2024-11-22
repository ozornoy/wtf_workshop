package com.example.wtf_workshop.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.wtf_workshop.api.models.User;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {
    private static final String LOGIN_URL = "/login.html";

    private final SelenideElement inputUserName = $("#username");
    private final SelenideElement inputPassword = $("#password");
    private final SelenideElement inputSubmitLogin = $(".loginButton");

    public static LoginPage open() {
        return Selenide.open(LOGIN_URL, LoginPage.class);
    }

    public ProjectsPage login(User user) {
        inputUserName.val(user.getUsername());
        inputPassword.val(user.getPassword());
        inputSubmitLogin.click();

        return Selenide.page(ProjectsPage.class);
    }

}

package com.udacity.jwdnd.course1.superduperdrive;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(css = "#inputUsername")
    private WebElement usernameField;

    @FindBy(css = "#inputPassword")
    private WebElement passwordField;

    @FindBy(css = "#submit-button")
    private WebElement submitButton;

    @FindBy(id = "login-error")
    private WebElement loginError;

    @FindBy(id = "logout")
    private WebElement logout;

    @FindBy(id = "login-logout")
    private WebElement loginLogout;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void login(String username, String password) {
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password);
        this.submitButton.click();
    }

    public void loginError(String username, String password) {
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password);
        this.submitButton.click();
        Assertions.assertTrue(this.loginError.isEnabled());
    }

    public void logout() {
        this.logout.click();
    }
}

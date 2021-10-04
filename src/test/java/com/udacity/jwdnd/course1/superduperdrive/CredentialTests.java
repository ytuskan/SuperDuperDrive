package com.udacity.jwdnd.course1.superduperdrive;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialTests {
    @LocalServerPort
    public int port;
    public String baseURL;
    public WebDriver driver;

    @BeforeEach
    public void beforeAll() {
        WebDriverManager.operadriver().setup();
        driver = new OperaDriver();
        baseURL = "http://localhost:" + port;

        String firstName = "firstName";
        String lastName = "lastName";
        String username = "username";
        String password = "password";

        driver.get(baseURL + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup(firstName, lastName, username, password);

        driver.get(baseURL + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

    }

    @AfterEach
    public void afterAll() {
        driver.quit();
        driver = null;
    }

    @Test
    public void testAddCredential() throws InterruptedException {
        CredentialPage credentialPage = new CredentialPage(driver);
        credentialPage.openCredentials();

        credentialPage.addCredential(driver);
        driver.get(baseURL + "/home");
        credentialPage.openCredentials();
        Thread.sleep(2000);
    }

    @Test
    public void testUpdateCredential() throws InterruptedException {
        CredentialPage credentialPage = new CredentialPage(driver);
        credentialPage.openCredentials();

        credentialPage.addCredential(driver);
        driver.get(baseURL + "/home");
        credentialPage.openCredentials();

        credentialPage.updateCredential(driver);
        driver.get(baseURL + "/home");
        credentialPage.openCredentials();
        Thread.sleep(2000);
    }

    @Test
    public void testDeleteCredential() throws InterruptedException {
        CredentialPage credentialPage = new CredentialPage(driver);
        credentialPage.openCredentials();

        credentialPage.addCredential(driver);
        driver.get(baseURL + "/home");
        credentialPage.openCredentials();

        credentialPage.deleteCredential(driver);
        driver.get(baseURL + "/home");
        credentialPage.openCredentials();
        Thread.sleep(2000);
    }
}

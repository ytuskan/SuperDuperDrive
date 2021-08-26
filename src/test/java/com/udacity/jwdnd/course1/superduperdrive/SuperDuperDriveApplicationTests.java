package com.udacity.jwdnd.course1.superduperdrive;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SuperDuperDriveApplicationTests {

    @LocalServerPort
    public int port;
    public String baseURL;

    public static WebDriver driver;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.operadriver().setup();
        driver = new OperaDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
        driver = null;
    }


    @BeforeEach
    public void beforeEach() {
        baseURL = "http://localhost:" + port;
    }

    @Test
    public void testGetHomePage() {
        driver.get(baseURL + "/home");
    }

    @Test
    public void testLoginAndSignupPage() throws InterruptedException {
        String firstName = "firstName";
        String lastName = "lastName";
        String username = "username";
        String password = "password";

        driver.get(baseURL + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginError(username, password);

        driver.get(baseURL + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup(firstName, lastName, username, password);

        driver.get(baseURL + "/login");
        loginPage.login(username, password);

        driver.get(baseURL + "/home");

        loginPage.logout();

        driver.get(baseURL + "/home");

        Thread.sleep(5000);
    }

}

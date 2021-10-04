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
    public void testGetHomePageWithoutLogin() throws InterruptedException {
        driver.get(baseURL + "/home");
        Assertions.assertTrue(driver.getCurrentUrl().equals(baseURL+"/login"));
        Thread.sleep(2000);
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
        Thread.sleep(2000);

        driver.get(baseURL + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup(firstName, lastName, username, password);
        Thread.sleep(2000);

        loginPage.login(username, password);
        Thread.sleep(2000);

        loginPage.logout();
        Thread.sleep(2000);

        driver.get(baseURL + "/home");
        Assertions.assertTrue(driver.getCurrentUrl().equals(baseURL + "/login"));

        Thread.sleep(2000);
    }

}

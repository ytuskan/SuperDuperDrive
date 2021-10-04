package com.udacity.jwdnd.course1.superduperdrive;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteTests {
    @LocalServerPort
    public  int port;
    public String baseURL;

    public WebDriver driver;

    @BeforeEach
    public  void beforeAll() {
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
    public  void afterAll() {
        driver.quit();
        driver = null;
    }

    @Test
    public void testAddNote() throws InterruptedException {
        NotePage notePage = new NotePage(driver);
        notePage.openNotes();

        notePage.addNote(driver);
        driver.get(baseURL + "/home");
        notePage.openNotes();
        Thread.sleep(2000);
    }

    @Test
    public void testUpdateNote() throws InterruptedException {
        NotePage notePage = new NotePage(driver);
        notePage.openNotes();

        notePage.addNote(driver);
        driver.get(baseURL + "/home");
        notePage.openNotes();

        notePage.updateNote(driver);
        driver.get(baseURL + "/home");
        notePage.openNotes();
        Thread.sleep(2000);
    }

    @Test
    public void testDeleteNote() throws InterruptedException {
        NotePage notePage = new NotePage(driver);
        notePage.openNotes();

        notePage.addNote(driver);
        driver.get(baseURL + "/home");
        notePage.openNotes();

        notePage.deleteNote(driver);
        driver.get(baseURL + "/home");
        notePage.openNotes();
        Thread.sleep(2000);
    }
}

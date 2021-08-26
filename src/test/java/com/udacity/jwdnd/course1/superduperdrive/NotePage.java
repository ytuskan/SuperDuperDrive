package com.udacity.jwdnd.course1.superduperdrive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotePage {
    @FindBy(id = "nav-notes-tab")
    private WebElement navNote;

    @FindBy(id = "add-note-button")
    private WebElement addNoteButton;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "save-note-button")
    private WebElement saveNoteButton;

    @FindBy(id = "edit-note-button")
    private WebElement editNoteButton;

    @FindBy(id = "delete-note-button")
    private WebElement deleteNoteButton;

    public NotePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void openNotes() {
        this.navNote.click();
    }

    public void addNote(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.elementToBeClickable(addNoteButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(noteTitle)).sendKeys("title");
        wait.until(ExpectedConditions.elementToBeClickable(noteDescription)).sendKeys("description");
        wait.until(ExpectedConditions.elementToBeClickable(saveNoteButton)).click();

    }

    public void updateNote(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.elementToBeClickable(editNoteButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(noteTitle)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(noteTitle)).sendKeys("newTitle");
        wait.until(ExpectedConditions.elementToBeClickable(noteDescription)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(noteDescription)).sendKeys("newDescription");
        wait.until(ExpectedConditions.elementToBeClickable(saveNoteButton)).click();
    }

    public void deleteNote(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.elementToBeClickable(deleteNoteButton)).click();
    }
}

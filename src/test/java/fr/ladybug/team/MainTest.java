package fr.ladybug.team;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    WebDriver driver = null;

    @BeforeAll
    public static void init() {
        System.setProperty("webdriver.chrome.driver", "/opt/WebDriver/bin/chromedriver");
    }

    @BeforeEach
    public void initDriver() {
        driver = new ChromeDriver();
        driver.get("localhost:8081");
        var loginPage = new LoginPage(driver, "root", "root");
        loginPage.login();
    }

    @Test
    public void commonFormIssueTest() {
        final String currentSummary = "Some summary";
        final String currentDescription = "Some description";
        Main.createIssue(new IssuesPage.IssueInfo(currentSummary, currentDescription));
        try {
            Thread.sleep(1000); // Wait for YouTrack to make DB a transaction
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        var issuesPage = new IssuesPage(driver);
        issuesPage.enter();
        IssuesPage.IssueInfo info = issuesPage.getIssueInfo();


        assertEquals(info.getDescription(), currentDescription);
        assertEquals(info.getSummary(), currentSummary);
        issuesPage.removeExistingIssue();
    }

    @Test
    public void noDescIssueTest() {
        final String currentSummary = "Some summary";
        final String currentDescription = "";
        Main.createIssue(new IssuesPage.IssueInfo(currentSummary, currentDescription));
        try {
            Thread.sleep(1000); // Wait for YouTrack to make DB a transaction
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        var issuesPage = new IssuesPage(driver);
        issuesPage.enter();
        IssuesPage.IssueInfo info = issuesPage.getIssueInfo();


        assertEquals(info.getDescription(), currentDescription.equals("") ? "No description" : currentDescription);
        assertEquals(info.getSummary(), currentSummary);
        issuesPage.removeExistingIssue();
    }

    @Test
    public void wrongIssueCreationTest() {
        final String currentSummary = "";
        final String currentDescription = "Whatever";
        Main.createIssue(new IssuesPage.IssueInfo(currentSummary, currentDescription));
        try {
            Thread.sleep(1000); // Wait for YouTrack to make DB a transaction
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        var issuesPage = new IssuesPage(driver);
        issuesPage.enter();
        IssuesPage.IssueInfo info = issuesPage.getIssueInfo();
        assertNotEquals(info.getDescription(), currentDescription);
    }

    @AfterEach
    public void DisposeDriver() {
        driver.quit();
    }

}
package fr.ladybug.team;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    WebDriver driver = null;

    @BeforeAll
    public static void init() {
        System.setProperty("webdriver.chrome.driver", "/opt/WebDriver/bin/chromedriver");
    }

    @BeforeEach
    public void initDriver() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("localhost:8081");
        var loginPage = new LoginPage(driver, "root", "root");
        loginPage.login();
        var issuesPage = new IssuesPage(driver);
        issuesPage.enter();
        while (issuesPage.getIssuesCount() > 0) {
            issuesPage.getIssueInfo();
            issuesPage.removeExistingIssue();
            issuesPage.enter();
        }
    }

    @Test
    public void commonFormIssueTest() {
        final String currentSummary = "Some summary";
        final String currentDescription = "Some description";
        Main.createIssue(new IssuesPage.IssueInfo(currentSummary, currentDescription));
        var issuesPage = new IssuesPage(driver);
        issuesPage.enter();
        await().atMost(1, TimeUnit.SECONDS).until(() -> issuesPage.getIssuesCount() > 0);
        IssuesPage.IssueInfo info = issuesPage.getIssueInfo();


        assertEquals(currentDescription.equals("") ? "No description" : currentDescription, info.getDescription());
        assertEquals(currentSummary, info.getSummary());
        issuesPage.removeExistingIssue();
    }

    @Test
    public void noDescIssueTest() {
        final String currentSummary = "Some summary";
        final String currentDescription = "";
        Main.createIssue(new IssuesPage.IssueInfo(currentSummary, currentDescription));
        var issuesPage = new IssuesPage(driver);
        issuesPage.enter();
        await().atMost(1, TimeUnit.SECONDS).until(() -> issuesPage.getIssuesCount() > 0);
        IssuesPage.IssueInfo info = issuesPage.getIssueInfo();

        assertEquals(currentDescription.equals("") ? "No description" : currentDescription, info.getDescription());
        assertEquals(currentSummary, info.getSummary());
        issuesPage.removeExistingIssue();
    }

    @Test
    public void visibleASCIIDescIssueTest() {
        final String currentSummary = "Some summary";
        final String currentDescription = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz{|}";

        Main.createIssue(new IssuesPage.IssueInfo(currentSummary, currentDescription));
        var issuesPage = new IssuesPage(driver);
        issuesPage.enter();
//        assertEquals(0, issuesPage.getIssuesCount());
        await().atMost(1, TimeUnit.SECONDS).until(() -> issuesPage.getIssuesCount() > 0);
        IssuesPage.IssueInfo info = issuesPage.getIssueInfo();

        assertEquals(currentDescription.equals("") ? "No description" : currentDescription, info.getDescription());
        assertEquals(currentSummary, info.getSummary());
        issuesPage.removeExistingIssue();
    }

    @Test
    public void asDescIssueTest() {
        final String currentSummary = "Some summary";

        var builder = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            builder.append('a');
        }

        final String currentDescription = builder.toString();

        Main.createIssue(new IssuesPage.IssueInfo(currentSummary, currentDescription));
        var issuesPage = new IssuesPage(driver);
        issuesPage.enter();
//        assertEquals(0, issuesPage.getIssuesCount());
        await().atMost(1, TimeUnit.SECONDS).until(() -> issuesPage.getIssuesCount() > 0);
        IssuesPage.IssueInfo info = issuesPage.getIssueInfo();

        assertEquals(currentDescription.equals("") ? "No description" : currentDescription, info.getDescription());
        assertEquals(currentSummary, info.getSummary());
        issuesPage.removeExistingIssue();
    }

    @Test
    public void aDescIssueTest() {
        final String currentSummary = "Some summary";

        var builder = new StringBuilder();
        for (int i = 0; i < 1; i++) {
            builder.append('a');
        }

        final String currentDescription = builder.toString();

        Main.createIssue(new IssuesPage.IssueInfo(currentSummary, currentDescription));
        var issuesPage = new IssuesPage(driver);
        issuesPage.enter();
//        assertEquals(0, issuesPage.getIssuesCount());
        await().atMost(1, TimeUnit.SECONDS).until(() -> issuesPage.getIssuesCount() > 0);
        IssuesPage.IssueInfo info = issuesPage.getIssueInfo();

        assertEquals(currentDescription.equals("") ? "No description" : currentDescription, info.getDescription());
        assertEquals(currentSummary, info.getSummary());
        issuesPage.removeExistingIssue();
    }

    @Test
    public void asSummaryIssueTest() {
        final String currentDescription = "";

        var builder = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            builder.append('a');
        }

        final String currentSummary = builder.toString();

        Main.createIssue(new IssuesPage.IssueInfo(currentSummary, currentDescription));
        var issuesPage = new IssuesPage(driver);
        issuesPage.enter();
//        assertEquals(0, issuesPage.getIssuesCount());
        await().atMost(1, TimeUnit.SECONDS).until(() -> issuesPage.getIssuesCount() > 0);
        IssuesPage.IssueInfo info = issuesPage.getIssueInfo();

        assertEquals(currentDescription.equals("") ? "No description" : currentDescription, info.getDescription());
        assertEquals(currentSummary, info.getSummary());
        issuesPage.removeExistingIssue();
    }

    @Test
    public void aSummaryIssueTest() {
        final String currentDescription = "";

        var builder = new StringBuilder();
        for (int i = 0; i < 1; i++) {
            builder.append('a');
        }

        final String currentSummary = builder.toString();

        Main.createIssue(new IssuesPage.IssueInfo(currentSummary, currentDescription));
        var issuesPage = new IssuesPage(driver);
        issuesPage.enter();
//        assertEquals(0, issuesPage.getIssuesCount());
        await().atMost(1, TimeUnit.SECONDS).until(() -> issuesPage.getIssuesCount() > 0);
        IssuesPage.IssueInfo info = issuesPage.getIssueInfo();

        assertEquals(currentDescription.equals("") ? "No description" : currentDescription, info.getDescription());
        assertEquals(currentSummary, info.getSummary());
        issuesPage.removeExistingIssue();
    }

    @Test
    public void spacesSummaryIssueTest() {
        final String currentDescription = "";

        var builder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            builder.append(' ');
        }

        final String currentSummary = builder.toString();

        Main.createIssue(new IssuesPage.IssueInfo(currentSummary, currentDescription));
        var issuesPage = new IssuesPage(driver);
        issuesPage.enter();
//        assertEquals(0, issuesPage.getIssuesCount());
        await().atMost(1, TimeUnit.SECONDS).until(() -> issuesPage.getIssuesCount() > 0);
        IssuesPage.IssueInfo info = issuesPage.getIssueInfo();

        assertEquals(currentDescription.equals("") ? "No description" : currentDescription, info.getDescription());
        assertEquals(currentSummary, info.getSummary());
        issuesPage.removeExistingIssue();
    }

    @Test
    public void wrongIssueCreationTest() {
        final String currentSummary = "";
        final String currentDescription = "Whatever";
        Main.createIssue(new IssuesPage.IssueInfo(currentSummary, currentDescription));
        var issuesPage = new IssuesPage(driver);
        issuesPage.enter();
        assertEquals(0, issuesPage.getIssuesCount());
    }

    @AfterEach
    public void DisposeDriver() {
        driver.quit();
    }

}
package fr.ladybug.team;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

    public static void main(String[] args) {

    }

    public static void createIssue(IssuesPage.IssueInfo info) {
        System.setProperty("webdriver.chrome.driver", "/opt/WebDriver/bin/chromedriver");
        var driver = new ChromeDriver();
        driver.get("localhost:8081");
        var loginPage = new LoginPage(driver, "root", "root");
        loginPage.login();
        var form = new IssueCreationForm(driver);
        form.enter();
        form.createIssue(info);
        driver.close();
    }
}

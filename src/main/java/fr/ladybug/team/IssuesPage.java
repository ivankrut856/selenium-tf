package fr.ladybug.team;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static fr.ladybug.team.Utils.waitUntilElement;
import static fr.ladybug.team.Utils.waitUntilFullLoad;

public class IssuesPage {

    private WebDriver driver;

    public IssuesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enter() {
        waitUntilFullLoad(driver);
        waitUntilElement(driver, By.className("ring-menu__item__i"));
        var enterButton = driver.findElement(By.className("ring-menu__item__i"));
        enterButton.click();
    }

    public IssueInfo getIssueInfo() {
        waitUntilFullLoad(driver);
        waitUntilElement(driver, By.className("issue-summary"));
        var lastIssue = driver.findElement(By.className("issue-summary"));
        lastIssue.click();

        waitUntilFullLoad(driver);
        waitUntilElement(driver, By.id("id_l.I.ic.icr.it.issSum"));
        var currentSummary = driver.findElement(By.id("id_l.I.ic.icr.it.issSum"));

        waitUntilElement(driver, By.id("id_l.I.ic.icr.d.description"));
        var currentDescription = driver.findElement(By.id("id_l.I.ic.icr.d.description"));

        return new IssueInfo(currentSummary.getText(), currentDescription.getText());
    }

    public void removeExistingIssue() {
        waitUntilElement(driver, By.id("id_l.I.tb.deleteIssueLink"));
        var deleteButton = driver.findElement(By.id("id_l.I.tb.deleteIssueLink"));
        deleteButton.click();

        driver.switchTo().alert().accept();
    }

    public static class IssueInfo {
        private String summary;
        private String description;

        public IssueInfo(String summary, String description) {
            this.summary = summary;
            this.description = description;
        }

        public String getSummary() {
            return summary;
        }

        public String getDescription() {
            return description;
        }
    }
}

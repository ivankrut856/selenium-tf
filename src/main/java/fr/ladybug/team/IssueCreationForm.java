package fr.ladybug.team;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static fr.ladybug.team.Utils.waitUntilElement;
import static fr.ladybug.team.Utils.waitUntilFullLoad;

public class IssueCreationForm {

    private WebDriver driver;

    public IssueCreationForm(WebDriver driver) {
        this.driver = driver;
    }


    public void enter() {
        waitUntilFullLoad(driver);
        waitUntilElement(driver, By.className("yt-header__create-btn"));
        var createButton = driver.findElement(By.className("yt-header__create-btn"));
        createButton.click();
    }

    public void createIssue(IssuesPage.IssueInfo info) {
        waitUntilFullLoad(driver);

        waitUntilElement(driver, By.id("id_l.D.ni.ei.eit.summary"));
        var summaryText = driver.findElement(By.id("id_l.D.ni.ei.eit.summary"));
        summaryText.clear();
        summaryText.sendKeys(info.getSummary());

        waitUntilElement(driver, By.id("id_l.D.ni.ei.eit.description"));
        var descriptionText = driver.findElement(By.id("id_l.D.ni.ei.eit.description"));
        descriptionText.clear();
        descriptionText.sendKeys(info.getDescription());


        waitUntilElement(driver, By.id("id_l.D.ni.ei.submitButton_74_0"));
        var submitButton = driver.findElement(By.id("id_l.D.ni.ei.submitButton_74_0"));
        submitButton.click();
    }
}

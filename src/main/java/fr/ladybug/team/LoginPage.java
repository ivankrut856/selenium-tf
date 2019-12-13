package fr.ladybug.team;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private String login, password;

    public LoginPage(WebDriver driver, String login, String password) {
        this.driver = driver;
        this.login = login;
        this.password = password;
    }

    private void waitUntilFullLoad() {
        new WebDriverWait(driver, 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public boolean login() {
        waitUntilFullLoad();
        var loginText = driver.findElement(By.id("id_l.L.login"));
        loginText.sendKeys(login);
        var passwordText = driver.findElement(By.id("id_l.L.password"));
        passwordText.sendKeys(password);
        var submitButton = driver.findElement(By.id("id_l.L.loginButton"));
        submitButton.click();

        return true;
    }
}

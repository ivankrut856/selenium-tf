package fr.ladybug.team;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {
    public static void waitUntilFullLoad(WebDriver driver) {
        new WebDriverWait(driver, 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
    public static void waitUntilElement(WebDriver driver, By byTag) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(byTag));
    }
}

package task4.pages;

import managers.DriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import task4.PageManager;

import java.io.IOException;
import java.time.Duration;

public class BasePage {

    protected DriverManager driverManager = DriverManager.getInstance();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), Duration.ofSeconds(10), Duration.ofMillis(1000));


    protected PageManager pageManager = PageManager.getInstance();

    public BasePage(){
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected void scrollToElementJs(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driverManager.getDriver();
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected boolean hasAttribute(WebElement element, String attr) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driverManager.getDriver();
        Object result = javascriptExecutor.executeScript("return arguments[0].hasAttribute(arguments[1]);", element, attr);
        return (boolean) result;
    }


    protected void clickJS(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driverManager.getDriver();
        javascriptExecutor.executeScript("arguments[0].click();", element);
    }

    protected WebElement waitUntilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitUntilElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitUntilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void moveToElement(WebElement element) {
        Actions action = new Actions(driverManager.getDriver());
        action.moveToElement(element).build().perform();
    }

    boolean containsIgnoreCaseAndAccents(String str, String searchStr) {
        String normalizedStr = StringUtils.stripAccents(str).toLowerCase();
        String normalizedSearchStr = StringUtils.stripAccents(searchStr).toLowerCase();

        return normalizedStr.contains(normalizedSearchStr) || normalizedStr.contains(translateToEnglish(normalizedSearchStr));
    }

    private String translateToEnglish(String text) {
        return text.replace("а", "a")
                .replace("б", "b")
                .replace("в", "v")
                .replace("г", "g")
                .replace("д", "d")
                .replace("е", "e")
                .replace("ё", "yo")
                .replace("ж", "zh")
                .replace("з", "z")
                .replace("и", "i")
                .replace("й", "y")
                .replace("к", "k")
                .replace("л", "l")
                .replace("м", "m")
                .replace("н", "n")
                .replace("о", "o")
                .replace("п", "p")
                .replace("р", "r")
                .replace("с", "s")
                .replace("т", "t")
                .replace("у", "u")
                .replace("ф", "f")
                .replace("х", "kh")
                .replace("ц", "ts")
                .replace("ч", "ch")
                .replace("ш", "sh")
                .replace("щ", "shch")
                .replace("ъ", "")
                .replace("ы", "y")
                .replace("ь", "")
                .replace("э", "e")
                .replace("ю", "yu")
                .replace("я", "ya");
    }


}


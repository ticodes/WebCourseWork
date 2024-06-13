package task1.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StartPage extends BasePage{
    private static final Logger logger = Logger.getLogger(StartPage.class);

    @FindBy(xpath = "//span[@class='ng-binding']")
    private WebElement remainingCountElement;

    @FindBy(xpath = "//input[@type='checkbox']")
    private List<WebElement> checkboxList;

    @FindBy(xpath = "//input[@type='text']")
    private WebElement itemInputField;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement addItemButton;

    private int remainingItems = 5;
    private int totalItems = 5;


    @Step("Проверить, что присутствует текст '5 of 5 remaining'")
    public StartPage verifyRemainingCountText() {
        waitUntilElementToBeVisible(remainingCountElement);
        String expectedText = String.format("%d of %d remaining", remainingItems, totalItems);
        Assert.assertEquals("Текст некорректный", expectedText, remainingCountElement.getText().trim());
        logger.info("Проверка присутствия текста '5 of 5 remaining'");
        return this;
    }

    @Step("Проверить, что элемент списка '{itemName}' не зачеркнут")
    public StartPage verifyItemIsNotCompleted(String itemName){
        for (WebElement checkbox: checkboxList){
            WebElement itemElement = checkbox.findElement(By.xpath("./..//span"));
            if (itemElement.getText().trim().equals(itemName)) {
                Assert.assertEquals("Элемент списка " + itemName + " зачеркнут","done-false", itemElement.getAttribute("class"));
                logger.info("Проверка того, что элемент списка '" + itemName + "' не зачеркнут");
                return this;
            }
        }
        Assert.fail("Элемент списка '" + itemName + "' не был найден на cтранице");
        return this;
    }

    @Step("Поставить галочку у элемента списка '{itemName}'")
    public StartPage markItemAsCompleted(String itemName){
        for (WebElement checkbox: checkboxList){
            WebElement item = checkbox.findElement(By.xpath("./..//span"));
            if (item.getText().trim().equals(itemName)) {
                checkbox.click();
                remainingItems -= 1;
                String text = String.format("%s of %s remaining", remainingItems, totalItems);
                Assert.assertEquals("Элемент списка " + itemName + " не зачеркнут","done-true", item.getAttribute("class"));
                Assert.assertEquals("Число оставшихся элементов не уменьшилось на 1", text, remainingCountElement.getText());
                logger.info("Поставить галочку у элемента списка '" + itemName + "'");
                return this;
            }
        }
        Assert.fail("Элемент списка '" + itemName + "' не был найден на cтранице!");
        return this;
    }

    @Step("Добавить новый элемент списка '{itemName}'")
    public StartPage addNewItem(String itemName){
        itemInputField.click();
        itemInputField.sendKeys(itemName);
        addItemButton.click();
        totalItems++;
        remainingItems++;
        for (WebElement checkbox: checkboxList){
            WebElement itemElement = checkbox.findElement(By.xpath("./..//span"));
            if (itemElement.getText().trim().equals(itemName)) {
                Assert.assertEquals("Элемент списка " + itemName + " зачеркнут","done-false", itemElement.getAttribute("class"));
            }
        }
        String expectedText = String.format("%s of %s remaining", remainingItems, totalItems);
        Assert.assertEquals("Число оставшихся и общее число элементов не увеличилось на 1", expectedText, remainingCountElement.getText());
        logger.info("Добавление нового элемента списка '" + itemName + "'");
        return this;
    }

}

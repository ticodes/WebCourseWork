package task3.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StartPage extends BasePage{

    private static final Logger logger = Logger.getLogger(StartPage.class);

    @FindBy(xpath = "//h2[@class='A6UH0']")
    private WebElement title;

    @FindBy(xpath = "//div[@data-baobab-name='catalog']/button")
    private WebElement catalogButton;

    @FindBy(xpath = "//li[@data-zone-name='category-link']/a")
    private List<WebElement> categoryList;

    @FindBy(xpath = "//ul[@data-autotest-id]//li//a")
    private List<WebElement> menuItemList;

    @Step("Проверка открытия главной страницы")
    public StartPage checkOpenPage(){
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                "Рекомендованные товары",
                title.getText());
        logger.info("Проверка открытия страницы");
        return this;
    }

    @Step("Нажать на каталог")
    public StartPage clickOnCatalog() {
        waitUntilElementToBeClickable(catalogButton).click();
        logger.info("Клик на каталог");
        return this;
    }

    @Step("Навести на категорию '{category}'")
    public StartPage moveToCategory(String category) {
        for (WebElement element: categoryList) {
            waitUntilElementToBeVisible(element);
            moveToElement(element);
            if (element.findElement(By.xpath("./span")).getText().equals(category)) {
                logger.info("Навести на категорию '" + category + "'");
                return this;
            }
        }
        Assert.fail("Нет категории '" + category + "'");
        return this;
    }

    @Step("Нажать на пункт меню '{menuItem}'")
    public TabletPage clickOnMenuItem(String menuItem) {
        for (WebElement item: menuItemList) {
            if (waitUntilElementToBeVisible(item).getText().equals(menuItem)){
                moveToElement(item);
                item.click();
                logger.info("Переход на страницу с планшетами");
                return pageManager.getTabletPage();
            }
        }
        Assert.fail("Не найден пункт меню " + menuItem);
        return pageManager.getTabletPage();
    }

}

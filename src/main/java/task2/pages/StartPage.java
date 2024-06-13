package task2.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StartPage extends BasePage {
    private static final Logger logger = Logger.getLogger(StartPage.class);

    @FindBy(xpath = "//h1")
    private WebElement pageTitle;

    @FindBy(xpath = "//button[@class='hamburger']")
    private WebElement hamburgerMenuButton;

    @FindBy(xpath = "//a[contains(@class, 'main-nav')]")
    private List<WebElement> menuItemList;

    @Step("Проверка открытия главной страницы")
    public StartPage checkPageOpened(){
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                "Московский Политех",
                pageTitle.getText());
        logger.info("Проверка открытия страницы");
        return this;
    }

    @Step("Нажать на гамбургер-меню, чтобы открыть меню")
    public StartPage clickHamburgerMenu() {
        waitUntilElementToBeClickable(hamburgerMenuButton).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("Открытие меню");
        return this;
    }

    @Step("Навести на пункт меню '{nameMenuItem}'")
    public StartPage hoverOverMenuItem(String nameMenuItem){
        for (WebElement element: menuItemList) {
            if (element.getAttribute("title").equalsIgnoreCase(nameMenuItem)) {
                waitUntilElementToBeVisible(element);
                moveToElement(element);
                logger.info("Навести на пункт меню " + nameMenuItem);
                return this;
            }
        }
        Assert.fail("Не пунка меню с названием " + nameMenuItem);
        return this;
    }

    @Step("Клик по пункту меню '{nameMenuItem}'")
    public SchedulePage clickMenuItem(String nameMenuItem){
        for (WebElement element: menuItemList) {
            if (element.getAttribute("title").equalsIgnoreCase(nameMenuItem)) {
                waitUntilElementToBeClickable(element).click();
                logger.info("Переход на страницу с расписанием");
                return pageManager.getSchedulePage();
            }
        }
        Assert.fail("Нет пункта меню с названием " + nameMenuItem);
        return pageManager.getSchedulePage();
    }

}

package task2.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SchedulePage extends BasePage{

    private static final Logger logger = Logger.getLogger(SchedulePage.class);

    @FindBy(xpath = "//h1")
    private WebElement pageTitle;

    @FindBy(xpath = "//a[@href='https://rasp.dmami.ru/session']")
    private WebElement viewScheduleButton;


    @Step("Проверка открытия страницы Расписания")
    public SchedulePage checkPageOpened(){
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                "Расписания",
                pageTitle.getText());
        logger.info("Проверка открытия страницы");
        return this;
    }
    @Step("Нажать на кнопку 'Смотреть на сайте'")
    public ScheduleGroupPage clickViewScheduleButton() {
        scrollToElementJs(viewScheduleButton);
        waitUntilElementToBeClickable(viewScheduleButton).click();
        moveToNewTab();
        logger.info("Переход на страницу с расписанием групп");
        return pageManager.getScheduleGroupPage();
    }
}

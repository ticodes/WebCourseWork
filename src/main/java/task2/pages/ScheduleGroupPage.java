package task2.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ScheduleGroupPage extends BasePage {

    private static final Logger logger = Logger.getLogger(ScheduleGroupPage.class);

    @FindBy(tagName = "h4")
    private WebElement pageTitle;

    @FindBy(xpath = "//input[@placeholder='группа ...']")
    private WebElement groupInput;

    @FindBy(xpath = "//div[contains(@class, 'found-groups')]/*")
    private List<WebElement> foundGroupsList;

    @FindBy(xpath = "//div[contains(@class, 'schedule-day_today')]/div[contains(@class, 'title')]")
    private WebElement currentDayTitle;

    @Step("Проверить открытие страницы 'Расписание занятий'")
    public ScheduleGroupPage checkPageOpened() {
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                "Расписание зачетов и экзаменов",
                pageTitle.getText());
        logger.info("Проверка открытия страницы");
        return this;
    }

    @Step("Ввести группу {groupNumber}")
    public ScheduleGroupPage enterGroupNumber(String groupNumber) {
        waitUntilElementToBeClickable(groupInput).click();
        groupInput.sendKeys(groupNumber);
        groupInput.sendKeys(Keys.ENTER);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals("В результатах поиска отображается больше одной группы или вовсе не отображается", 1, foundGroupsList.size());
        Assert.assertEquals("В результатах поиска не отображается искомая группа", groupNumber, findGroup(foundGroupsList, groupNumber));
        logger.info("Ввод группы " + groupNumber);
        return this;
    }

    @Step("Нажать на найденную группу {groupNumber}")
    public ScheduleGroupPage clickFoundGroup(String groupNumber) {
        for (WebElement group : foundGroupsList) {
            if (group.getAttribute("id").equals(groupNumber)) {
                group.click();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Assert.assertEquals("Не открылось расписание выбранной группы", "Расписание " + groupNumber, driverManager.getDriver().getTitle());
                Assert.assertTrue("Текущий день недели не выделен цветом", currentDayTitle.getText().contains(getCurrentDayOfWeek()));
                logger.info("Клик по найденной группе " + groupNumber);
                return this;
            }
        }
        Assert.fail("Не нашли группу");
        return this;
    }
}

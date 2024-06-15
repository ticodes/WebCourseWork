package task4.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.List;

public class ChannelPage extends BasePage {

    private static final Logger logger = Logger.getLogger(task4.pages.SearchPage.class);

    @FindBy(xpath = "//yt-tab-shape[1]")
    private WebElement title;

    @FindBy(xpath = "//button[@aria-label='Подписаться']")
    private WebElement followButton;

    @FindBy(tagName = "tp-yt-iron-dropdown")
    private WebElement popUp;

    @FindBy(xpath = "//tp-yt-iron-dropdown//yt-formatted-string")
    private List<WebElement> popUpText;

    @Step("Проверка открытия страницы канала")
    public ChannelPage checkOpenPage(){
        Assert.assertTrue("Заголовок отсутствует/не соответствует требуемому", Boolean.parseBoolean(title.getAttribute("aria-selected")));
        logger.info("Проверка открытия страницы с результатами поиска");
        return this;
    }

    @Step("Нажать на кнопку 'Подписаться'")
    public ChannelPage clickFollowButton(){
        followButton.click();
        Assert.assertTrue("Плашка не отобразилась", hasAttribute(popUp, "focused"));
        String str = "";
        for (WebElement text: popUpText) {
            str += " " + text.getText();
        }

        Assert.assertEquals("Текст в плашке не соответствует ожидаемому", "Хотите подписаться на этот канал? Тогда войдите в аккаунт.", str.trim());
        logger.info("Вышла плашка с информацией о том, что пользователь не авторизован");
        return this;
    }
}

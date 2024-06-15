package task4.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends BasePage{

    private static final Logger logger = Logger.getLogger(task4.pages.SearchPage.class);

    @FindBy(xpath = "//ytd-guide-section-renderer[1]/div/ytd-guide-entry-renderer[1]")
    private WebElement title;

    @FindBy(xpath = "//ytd-video-renderer")
    private List<WebElement> videos;

    @FindBy(xpath = "//input[@id='search']")
    private WebElement searchForm;

    @FindBy(id = "search-icon-legacy")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@id='content-section']")
    private List<WebElement> channels;

    protected String videoTitle;
    protected String videoAuthor;

    @Step("Проверка открытия страницы с результатами поиска")
    public SearchPage checkOpenPage(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertFalse("Заголовок отсутствует/не соответствует требуемому", hasAttribute(title, "active"));
        logger.info("Проверка открытия страницы с результатами поиска");
        return this;
    }

    @Step("Проверка названия видео на введенный текст в поисковой строке")
    public void checkTitle(String text){
        for (int i = 0; i < 5; i++){
            WebElement video = videos.get(i);
            moveToElement(video);

            String videoTitle = video.findElement(By.xpath(".//a[@id='video-title']")).getAttribute("title");

            if (!containsIgnoreCaseAndAccents(videoTitle, text)) {
                Assert.fail("В названии видео не содержится искомая строка " + videoTitle);
            }
        }
        logger.info("Проверены названия видео на введенный текст в поисковой строке");
    }


    @Step("Вывести в лог названия и автора 5 первых видео и запомнить 2-ое видео")
    public SearchPage logVideos(){
        for (int i = 0; i < 5; i++){
            WebElement video = videos.get(i);
            moveToElement(video);

            String title = video.findElement(By.xpath(".//a[@id='video-title']")).getAttribute("title");
            String author = video.findElement(By.xpath(".//*[contains(@class, 'ytd-channel-name complex-string')]")).getAttribute("title");

            if (i == 1) {
                videoTitle = title;
                videoAuthor = author;
            }

            logger.info("Название: " + title + " , Автор: " + author);
        }
        return this;
    }

    @Step("Найти второе видео")
    public void searchVideo() {
        searchForm.click();
        searchForm.clear();
        searchForm.sendKeys(videoTitle);
        searchButton.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement video = videos.get(0);
        String title = video.findElement(By.xpath(".//a[@id='video-title']")).getAttribute("title");
        String author = video.findElement(By.xpath(".//*[contains(@class, 'ytd-channel-name complex-string')]")).getAttribute("title");

        Assert.assertEquals("Названия не совпадают", videoTitle, title);
        Assert.assertEquals("Авторы не совпадают", videoAuthor, author);
        logger.info("Найдено второе видео");
    }

    @Step("Нажать на первый канал")
    public ChannelPage clickChannel() {
        channels.get(0).click();
        logger.info("Первый канал нажат");
        return pageManager.getChannelPage();
    }
}

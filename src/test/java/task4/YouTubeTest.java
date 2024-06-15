package task4;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import task4.pages.StartPage;

public class YouTubeTest extends BaseTest {

    @Test
    @DisplayName("Проверка поиска по произвольному тексту")
    public void test1(){
        StartPage startPage  = new StartPage();
        startPage.checkOpenPage()
                .search("педро")
                .checkOpenPage()
                .checkTitle("педро");
    }

    @Test
    @DisplayName("Проверка поисковой строки")
    public void test2(){
        StartPage startPage  = new StartPage();
        startPage.checkOpenPage()
                .search("Московский Политех")
                .checkOpenPage()
                .logVideos()
                .searchVideo();
    }

    @Test
    @DisplayName("Проверка подписки на канал с неавторизованным пользователем")
    public void test3(){
        StartPage startPage  = new StartPage();
        startPage.checkOpenPage()
                .search("Московский Политех")
                .checkOpenPage()
                .clickChannel()
                .checkOpenPage()
                .clickFollowButton();

    }
}

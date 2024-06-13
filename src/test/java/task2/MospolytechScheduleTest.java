package task2;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import task2.pages.*;

public class MospolytechScheduleTest extends BaseTest {

    @Test
    @DisplayName("Тестирование страницы расписания на сайте Мосполитеха")
    public void test(){
        StartPage startPage  = new StartPage();
        startPage.checkPageOpened().
                clickHamburgerMenu()
                .hoverOverMenuItem("Обучающимся")
                .clickMenuItem("Расписания")
                .checkPageOpened()
                .clickViewScheduleButton()
                .checkPageOpened()
                .enterGroupNumber("221-361")
                .clickFoundGroup("221-361");
    }
}

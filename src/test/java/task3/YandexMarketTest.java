package task3;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import task3.pages.StartPage;

public class YandexMarketTest extends BaseTest {

    @Test
    @DisplayName("Яндекс Маркет: проверка поиска товара")
    public void test(){
        StartPage startPage  = new StartPage();
        startPage.checkOpenPage()
                .clickOnCatalog()
                .moveToCategory("Ноутбуки и компьютеры")
                .clickOnMenuItem("Планшеты")
                .setSamsungFilter()
                .setFilterCheaper()
                .logProducts()
                .searchTablet();
    }
}

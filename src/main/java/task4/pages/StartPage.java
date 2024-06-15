package task4.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class StartPage extends BasePage {

    private static final Logger logger = Logger.getLogger(StartPage.class);

    @FindBy(xpath = "//ytd-guide-section-renderer[1]/div/ytd-guide-entry-renderer[1]")
    private WebElement title;

   @FindBy(xpath = "//input[@id='search']")
   private WebElement searchForm;

   @FindBy(id = "search-icon-legacy")
   private WebElement searchButton;


    @Step("Проверка открытия главной страницы")
    public StartPage checkOpenPage(){
        Assert.assertTrue("Заголовок отсутствует/не соответствует требуемому", hasAttribute(title, "active"));
        logger.info("Проверка открытия страницы");
        return this;
    }

    @Step("Поиск в поисковой строке")
    public SearchPage search(String text){
        searchForm.click();
        searchForm.sendKeys(text);
        searchButton.click();
        logger.info("Произошел поиск");
        return pageManager.getSearchPage();
    }






}

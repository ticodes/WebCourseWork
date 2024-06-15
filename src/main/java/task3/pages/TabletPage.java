package task3.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TabletPage extends BasePage{

    private static final Logger logger = Logger.getLogger(TabletPage.class);

    @FindBy(xpath = "//h1")
    private WebElement title;

    @FindBy(xpath = "//div[@data-auto-themename='listDetailed']")
    private List<WebElement> productList;

    @FindBy(xpath = "//div[@data-filter-value-id='153061']//label")
    private WebElement samsungFilter;

    @FindBy(xpath = "//*[@data-autotest-id='aprice']")
    private WebElement cheaperFilter;

    @FindBy(xpath = "//*[@id='header-search']")
    private WebElement headerSearchButton;

    @FindBy(xpath = "//*[@data-auto='search-button']")
    private WebElement searchButton;

    protected String productTitle;
    private String productPrice;

    @Step("Проверка открытия страницы 'Планшеты'")
    public TabletPage checkOpenPage(){
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                "Планшеты",
                title.getText());
        logger.info("Проверка открытия страницы");
        return this;
    }

    @Step("В меню фильтров 'Производитель' выбрать 'Samsung'")
    public TabletPage setSamsungFilter() {
        moveToElement(samsungFilter);
        samsungFilter.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("Показаны планшеты производства Samsung");
        return this;
    }

    @Step("Выбрать фильтр подешевле")
    public TabletPage setFilterCheaper() {
        moveToElement(cheaperFilter);
        cheaperFilter.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("Применена сортировка начиная с дешевых продуктов, заканчивая дорогими");
        return this;
    }

    @Step("Вывести в лог первые 5 найденных товаров (название и цену)")
    public TabletPage logProducts() {
        for (int i = 0; i < 5 && i < productList.size(); i++) {
            WebElement product = productList.get(i);
            moveToElement(product);

            String title = product.findElement(By.xpath(".//h3")).getText();
            String price = product.findElement(By.xpath(".//span[@data-auto='snippet-price-current']/span[1]")).getText();

            //Запоминание второй позиции
            if (i == 1) {
                productTitle = title;
                productPrice = price;
            }

            logger.info("Название: " + title + ". Цена: " + price);
        }
        return this;
    }

    @Step("Найти второй по порядку товар")
    public void searchTablet() {
        headerSearchButton.click();
        headerSearchButton.sendKeys(productTitle);
        searchButton.click();
        waitUntilElementToBeVisible(title);
        WebElement tablet = productList.get(0);
        moveToElement(tablet);

        String title = tablet.findElement(By.xpath(".//h3")).getText();
        String price = tablet.findElement(By.xpath(".//span[@data-auto='snippet-price-current']/span[1]")).getText();

        Assert.assertEquals("Названия не совпадают", productTitle, title);
        Assert.assertEquals("Цены не совпадают", productPrice, price);
        logger.info("Найден второй по порядку товар");
    }





}

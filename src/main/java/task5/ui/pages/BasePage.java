package task5.ui.pages;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import managers.DriverManager;

import java.time.Duration;

import static io.restassured.RestAssured.given;

public class BasePage {

    protected DriverManager driverManager = DriverManager.getInstance();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), Duration.ofSeconds(10), Duration.ofMillis(1000));

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected WebElement waitUntilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void moveToElement(WebElement element) {
        Actions action = new Actions(driverManager.getDriver());
        action.moveToElement(element).build().perform();
    }


    protected void checkOpenPage(String pageName, WebElement title) {
        waitUntilElementToBeVisible(title);
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                pageName,
                title.getText());
    }

    protected String get(String request) {
        String response = given()
                .when()
                .get(request)
                .then()
                .extract().response().asPrettyString();
        if (response.equals("{\n" +
                "    \n" +
                "}")) {
            response = "{}";
        }
        return response;
    }

    protected int getStatusCode(String request) {
        return given()
                .when()
                .get(request)
                .then()
                .extract().statusCode();
    }

    protected String post(String request, String requestBody) {
        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(request)
                .then()
                .extract().response().asPrettyString();
    }

    protected int postStatusCode(String request, String requestBody) {
        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(request)
                .then()
                .extract().statusCode();
    }

    protected String delete(String request) {
        return given()
                .when()
                .delete(request)
                .then()
                .extract().response().asPrettyString();
    }

    protected int deleteStatusCode(String request) {
        return given()
                .when()
                .delete(request)
                .then()
                .extract().statusCode();
    }

    protected String put(String request, String requestBody) {
        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(request)
                .then()
                .extract().response().asPrettyString();
    }

    protected int putStatusCode(String request, String requestBody) {
        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(request)
                .then()
                .extract().statusCode();
    }

    protected String patch(String request, String requestBody) {
        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch(request)
                .then()
                .extract().response().asPrettyString();
    }

    protected int pathStatusCode(String request, String requestBody) {
        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch(request)
                .then()
                .extract().statusCode();
    }

    protected void compareResponses(String expectedResponse, String actualResponse) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode expectedJson = mapper.readTree(expectedResponse);
            JsonNode actualJson = mapper.readTree(actualResponse);

            ObjectNode expectedObjectNode = (ObjectNode) expectedJson;
            ObjectNode actualObjectNode = (ObjectNode) actualJson;

            expectedObjectNode.remove("id");
            expectedObjectNode.remove("createdAt");
            expectedObjectNode.remove("updatedAt");
            actualObjectNode.remove("id");
            actualObjectNode.remove("createdAt");
            actualObjectNode.remove("updatedAt");

            Assert.assertEquals("Результат не совпал с результатом API", expectedJson, actualJson);
        } catch (Exception e) {
            Assert.fail("Произошла ошибка при сравнении ответов: " + e.getMessage());
        }
    }

}


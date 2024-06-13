package task1;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import task1.pages.*;

public class LambdaTestSampleAppTest extends BaseTest {

    @Test
    @DisplayName("Тестирование списка дел \"LambdaTest Sample App\"")
    public void test(){
        StartPage startPage  = new StartPage();
        startPage.verifyRemainingCountText()
                .verifyItemIsNotCompleted("First Item")
                .markItemAsCompleted("First Item")
                .verifyItemIsNotCompleted("Second Item")
                .markItemAsCompleted("Second Item")
                .verifyItemIsNotCompleted("Third Item")
                .markItemAsCompleted("Third Item")
                .verifyItemIsNotCompleted("Fourth Item")
                .markItemAsCompleted("Fourth Item")
                .verifyItemIsNotCompleted("Fifth Item")
                .markItemAsCompleted("Fifth Item")
                .addNewItem("Sixth Item")
                .markItemAsCompleted("Sixth Item");
    }


}

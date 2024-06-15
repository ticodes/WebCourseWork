package task4;

import managers.DriverManager;
import managers.InitManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class BaseTest {
    private final DriverManager driverManager = DriverManager.getInstance();

    @Before
    public void before(){
        InitManager.initFramework();
        driverManager.getDriver().get("https://www.youtube.com/");;

    }
    @AfterClass
    public static void after(){
        InitManager.quitFramework();
    }

}

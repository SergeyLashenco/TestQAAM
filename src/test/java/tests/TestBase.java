package tests;

import appmanager.ApplicationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.BrowserType;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

@Listeners(MyTestListener.class)
public class TestBase {

    Logger logger = LogManager.getLogger(TestBase.class);
    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    @BeforeSuite
    public void setUp(ITestContext context) throws IOException {
        app.init();
        context.setAttribute("app" , app);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public  void  logTestStart(Method m, Object[] p  ){
        logger.info("Start " + m.getName() + " with parameters " + Arrays.asList(p));

    }
    @AfterMethod(alwaysRun = true)
    public  void  logTestStop(Method m){
        logger.info("Stop " + m.getName());

    }

}

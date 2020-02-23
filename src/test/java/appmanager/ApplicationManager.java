package appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final Properties properties;
    private WebDriver wb;
    private String browser;
    private HelperBase helperBase;
    private SessionHelper sessionHelper;



    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        File file = new File("./src/drivers/chromedriver");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        switch (browser) {
            case BrowserType.FIREFOX:
                wb = new FirefoxDriver();
                break;
            case BrowserType.CHROME:
                wb = new ChromeDriver();
                break;
            case BrowserType.IE:
                wb = new InternetExplorerDriver();
                break;
        }

        wb.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wb.get(properties.getProperty("web.baseUrl"));
        helperBase = new HelperBase(wb);
        sessionHelper = new SessionHelper(wb);
    }

    public void stop() {
        wb.quit();
    }

    public HelperBase getHelperBase() {
        return helperBase;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public HttpSession newSession (){
        //Создается новый екземпляр помошника
        return new  HttpSession (this);
    }
    public String getProperty(String key){
        return properties.getProperty(key);
    }

}

package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver wb) {
        super(wb);
    }


    public void login(String userName, String password) {

        try {
            wb.get("http://localhost/mantisbt-2.23.0/login_page.php");
            type(By.name("username") , userName); // administrator
            click(By.xpath(".//input[@type='submit']"));
            type( By.name("password") , password ); //root
            click(By.xpath(".//input[@type='submit']"));
        } catch (Exception ex) {
            System.out.println(" На данный момент не запущен XAMP авторизация не возможна");
            ex.printStackTrace();
            wb.quit();
        }
    }
}

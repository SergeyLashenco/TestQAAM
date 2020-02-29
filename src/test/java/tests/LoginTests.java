package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    private By allProjects = By.xpath(".//ul//li[@id='dropdown_projects_menu']");
    private By messageOfError = By.xpath("//div[@class ='alert alert-danger']//p[text() ='Возможно, ваша учётная запись заблокирована, или введённое регистрационное имя/пароль неправильны.']");

    @Test
    public void loginTest(){
        app.getSessionHelper().login("administrator","root");
       // Assert.assertTrue(app.getHelperBase().iseEmpty(allProjects));

    }
    @Test
    public void invalidLoginTest(){
        app.getSessionHelper().login("invalidName", "invalidPass");
        //Assert.assertTrue(app.getHelperBase().fiendElement(messageOfError));
    }
}

package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProbTestLogin extends TestBase {

    private By allProjects = By.xpath(".//ul//li[@id='dropdown_projects_menu']");

    @Test
    public void trialTestLogin(){
        app.getSessionHelper().login("administrator","root33");
        Assert.assertTrue(app.getHelperBase().iseEmpty(allProjects));

    }
}

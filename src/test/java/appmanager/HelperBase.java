package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HelperBase {
    WebDriver wb;

    public HelperBase(WebDriver wb) {
        this.wb = wb;
    }

    protected void click(By locator) {
        wb.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wb.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wb.findElement(locator).clear();
                wb.findElement(locator).sendKeys(text);
            }
        }
    }

    public Boolean iseEmpty (By locator) {
        try {
            wb.findElement(locator);
            return true;
        }catch (NoSuchElementException ex ){
            return false;
        }
    }
}

package tests;

import appmanager.ApplicationManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.annotations.Attachment;

public class MyTestListener implements ITestListener {
   @Override
   public void onTestStart(ITestResult result) {

   }

   @Override
   public void onTestSuccess(ITestResult result) {

   }

   @Override
   public void onTestFailure(ITestResult result) {

      //Контекст выполнения тестов (Получитить доступ к контексту можно через result )
      ApplicationManager app = (ApplicationManager) result.getTestContext().getAttribute("app");
      saveScreenshot(app.takeScreenshot());

   }

   @Attachment(value = "Page screenshot", type = "image/png")
   public byte[] saveScreenshot(byte[] screenShot) {
      return screenShot;
   }

   @Override
   public void onTestSkipped(ITestResult result) {

   }

   @Override
   public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

   }

   @Override
   public void onStart(ITestContext context) {

   }

   @Override
   public void onFinish(ITestContext context) {

   }
}

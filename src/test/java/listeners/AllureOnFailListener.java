package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.IOException;
import static listeners.ScreenShoter.makeScreenshot;


public class AllureOnFailListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {}

    @Override
    public void onTestSuccess(ITestResult result) {}

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            makeScreenshot("ScreenShot from " + result.getMethod().getMethodName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        try {
            makeScreenshot("ScreenShot from " + result.getMethod().getMethodName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onFinish(ITestContext context) {}

}

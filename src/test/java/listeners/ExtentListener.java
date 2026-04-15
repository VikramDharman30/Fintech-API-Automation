package listeners;

import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;

public class ExtentListener implements ITestListener {

    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {

        test = ExtentManager
                .getInstance()
                .createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.fail("Test Failed");
        test.fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test.skip("Test Skipped");
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {

        ExtentManager
                .getInstance()
                .flush();
    }
}
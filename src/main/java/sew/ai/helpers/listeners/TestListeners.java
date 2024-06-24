package sew.ai.helpers.listeners;

import org.testng.*;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.helpers.reporters.ExtentReport;

import java.util.Arrays;
import java.util.Set;

public class TestListeners implements ITestListener, ISuiteListener {
    @Override
    public void onFinish(ITestContext context) {
        Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
        for (ITestResult temp : failedTests) {
            ITestNGMethod method = temp.getMethod();
            if (context.getFailedTests().getResults(method).size() > 1) {
                failedTests.remove(temp);
            } else {
                if (context.getPassedTests().getResults(method).size() > 0) {
                    failedTests.remove(temp);
                }
            }
        }
    }

    @Override
    public void onStart(ISuite suite) {
        ExtentReport.initReports();
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentReport.flushReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(
                result.getMethod().getMethodName() +
                        " || " + result.getMethod().getDescription()
        );
        ExtentReport.addAuthors(
                result.getMethod().getConstructorOrMethod()
                        .getMethod().getAnnotation(FrameworkAnnotations.class)
                        .author()
        );
        ExtentReport.addCategories(
                result.getMethod().getConstructorOrMethod()
                        .getMethod().getAnnotation(FrameworkAnnotations.class)
                        .category()
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass("The test " + result.getMethod().getMethodName() + " is passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            ExtentLogger.fail(result.getMethod().getMethodName() + " is failed", true);
            ExtentLogger.fail(result.getThrowable().toString());
            ExtentLogger.fail(Arrays.toString(result.getThrowable().getStackTrace()));
        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentLogger.skip(result.getMethod().getMethodName() + " is skipped");
        ExtentLogger.skip(result.getMethod().getMethodName() + " is failed", true);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onStart(ITestContext context) {
    }
}


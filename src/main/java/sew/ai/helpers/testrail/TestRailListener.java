package sew.ai.helpers.testrail;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import sew.ai.config.Configuration;

import java.lang.reflect.Method;

public class TestRailListener implements ITestListener {
    Boolean debugInfo = true;

    public void onTestStart(ITestResult arg0) {
    }

    public void onTestSuccess(ITestResult result) {
        Integer testRailTestRunId = Integer.parseInt(Configuration.toString("testrailRunId"));
        String testRailPlatform = Configuration.toString("testrailPlatform");
        String browserName = Configuration.toString("browser");
        String deviceName = Configuration.toString("model");
        System.out.println("");
        System.out.println("===============================================");
        // IF POSTING NEEDS TO BE DONE IN TESTRAIL
        if (Common.postResultsToTestRail) {
            ITestNGMethod method = result.getMethod();
            // GET METHOD NAME
            String methodName = method.getMethodName();
            System.out.println("Run ID =======> " + testRailTestRunId);
            System.out.println("Platform =====> " + testRailPlatform);
            // INIT TEST RUN ID
            String testRailTestRunIdStr = method.getXmlTest().getParameter("testRailTestRunId");
            // VERIFY TEST RUN ID PRESENCE IN XML
            if (testRailTestRunIdStr != null && ! testRailTestRunIdStr.isEmpty()) {
                testRailTestRunId = Integer.parseInt(testRailTestRunIdStr);
            }
            // INIT TEST CASE DESCRIPTION
            int[] testCaseIds = returnTestCaseId(result);
            // MARKING STATUS IN TESTRAIL
            if (testCaseIds != null && testRailTestRunId != 0) {
                // the comment that will be added to the Test Run result
                Object commentFromTest = result.getTestContext().getAttribute("Comment");
                if (debugInfo)
                    System.out.println("Comment attribute = " + commentFromTest);
                String additionalComment;
                if (commentFromTest == null) {
                    additionalComment = "";
                }
                else {
                    additionalComment = "\n\nAdditional info - " + commentFromTest;
                }
                String testRailComment = "Automated TestNG Test - PASS; \n\n" +
                        "Test method name = " + methodName + "\n" +
                        "Browser name = " + browserName + "\n" +
                        "Device name = " + deviceName + "\n"
                        + additionalComment;
                System.out.println(testRailComment);
                // add the result to TestRail
                for (int testCaseId : testCaseIds) {
                    System.out.println(testCaseId);
                    try {
                        TestRailAPI.addResult(
                                1,
                                testRailComment,
                                testRailPlatform,
                                testRailTestRunId,
                                testCaseId
                        );
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                // if the Test Case Id or Test Run Id have not been set correctly record a
                // message to the log
                System.out.println("Test results are not being recorded to TestRail for test "
                        + methodName + " on browser " + browserName);
            }
        }
        System.out.println("===============================================");
    }

    public void onTestFailure(ITestResult arg0) {
        Integer testRailTestRunId = Integer.parseInt(Configuration.toString("testrailRunId"));
        String testRailPlatform = Configuration.toString("testrailPlatform");
        String browserName = Configuration.toString("browser");
        String deviceName = Configuration.toString("model");
        System.out.println("");
        System.out.println("===============================================");
        // INIT ERROR MESSAGE
        String errorMessage = arg0.getThrowable().toString();
        // IS TESTRAIL POSTING FLAG IS TRUE OR NOT
        if (Common.postResultsToTestRail) {
            ITestNGMethod m = arg0.getMethod();
            // INIT TEST METHOD NAME
            String methodName = m.getMethodName();
            System.out.println("Run ID =====> " + testRailTestRunId);
            System.out.println("Platform =====> " + testRailPlatform);
            // INIT TEST RUN ID
            String testRailTestRunIdStr = m.getXmlTest().getParameter("testRailTestRunId");
            // IF TEST RUN ID PARAMETER PRESENT IN XML
            if (testRailTestRunIdStr != null && ! testRailTestRunIdStr.isEmpty()) {
                testRailTestRunId = Integer.parseInt(testRailTestRunIdStr);
            }
            // INIT TEST CASE ID AND TEST CASE DESCRIPTION
            int[] testCaseIds = returnTestCaseId(arg0);
            // IF TEST CASE ID AND TEST RUN ID BOTH SET
            if (testCaseIds != null && testRailTestRunId != 0) {
                // INIT TEST CASE COMMENT
                Object commentFromTest = arg0.getTestContext().getAttribute("Comment");
                if (debugInfo)
                    System.out.println("Comment attribute = " + commentFromTest);
                String additionalComment;
                if (commentFromTest == null) {
                    additionalComment = "";
                }
                else {
                    additionalComment = "\nAdditional info - " + commentFromTest;
                }
                String testRailComment = "Automated TestNG Test - FAIL\n\n" +
                        "Test method name = " + methodName + "\n" +
                        "Browser name = " + browserName + "\n" +
                        "Device name = " + deviceName + "\n\n" +
                        "Failure Exception = " + errorMessage + "\n"
                        + additionalComment;
                // add the result to TestRail
                for (int testCaseId : testCaseIds) {
                    System.out.println(testCaseId);
                    try {
                        TestRailAPI.addResult(
                                5,
                                testRailComment,
                                testRailPlatform,
                                testRailTestRunId,
                                testCaseId
                        );
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                // IF TEST CASE AND TEST RUN ID HAS NOT BEEN SET PROPERLY
                System.out.println("Test results are not being recorded to TestRail for test "
                        + methodName + " on browser " + browserName);
            }
        }
        System.out.println("=============================================== ");
    }

    public void onTestSkipped(ITestResult arg0) {
    }

    public void onFinish(ITestContext arg0) {
    }

    public void onStart(ITestContext arg0) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
    }

    public int[] returnTestCaseId(ITestResult result) {
        ITestNGMethod testNGMethod = result.getMethod();
        Method method = testNGMethod.getConstructorOrMethod().getMethod();
        TestRail testRailAnnotation = method.getAnnotation(TestRail.class);
        int[] testCaseIds;
        try {
            testCaseIds = testRailAnnotation.testCaseId();
        }
        catch (Exception ex) {
            return null;
        }
        return testCaseIds;
    }
}

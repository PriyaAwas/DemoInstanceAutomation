package sew.ai.helpers.reporters;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {
    private static ThreadLocal<ExtentTest> extTest = new ThreadLocal<>();

    private ExtentManager() {
    }
    public static ExtentTest getExtentTest() {
        return extTest.get();
    }
    public static void setExtentTest(ExtentTest test) {
        extTest.set(test);
    }
    public static void unload() {
        extTest.remove();
    }
}

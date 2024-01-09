package sew.ai.helpers.reporters;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import sew.ai.helpers.props.ApplicationProps;
import sew.ai.utils.ScreenshotUtils;

public final class ExtentLogger {

    ExtentLogger() {
    }

    public static void pass(String message) {
        ExtentManager.getExtentTest().pass(message);
    }

    public static void fail(String message) {
        ExtentManager.getExtentTest().fail(message);
    }

    public static void skip(String message) {
        ExtentManager.getExtentTest().skip(message);
    }

    public static void pass(String message, boolean isScreenshotNeeded) {
        System.out.println(ApplicationProps.PASSED_STEPS_SCREENSHOT);
        if (ApplicationProps.PASSED_STEPS_SCREENSHOT.equalsIgnoreCase("yes") && isScreenshotNeeded) {
            ExtentManager.getExtentTest().pass(message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
        }
        else {
            pass(message);
        }
    }

    public static void fail(String message, boolean isScreenshotNeeded) {
        if (ApplicationProps.FAILED_STEPS_SCREENSHOT.equalsIgnoreCase("yes") && isScreenshotNeeded) {
            ExtentManager.getExtentTest().fail(
                    message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build()
            );
        }
        else {
            fail(message);
        }
    }

    public static void skip(String message, boolean isScreenshotNeeded) {
        if (ApplicationProps.SKIPPED_STEPS_SCREENSHOT.equalsIgnoreCase("yes") && isScreenshotNeeded) {
            ExtentManager.getExtentTest().skip(message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
        }
        else {
            skip(message);
        }
    }

    public static void logInfo(String message) {
        ExtentManager.getExtentTest().log(Status.INFO, message);
    }

    public static void logWarning(String message) {
        ExtentManager.getExtentTest().log(Status.WARNING, message);
    }

    public static void logPass(String message) {
        ExtentManager.getExtentTest().log(Status.PASS, message);
    }

    public static void logFail(String message) {
        ExtentManager.getExtentTest().log(Status.FAIL, message);
    }

    public static void logException(Exception e) {
        ExtentManager.getExtentTest().fail(e);
    }

    public static void logSkip(String message) {
        ExtentManager.getExtentTest().log(Status.SKIP, message);
    }
}

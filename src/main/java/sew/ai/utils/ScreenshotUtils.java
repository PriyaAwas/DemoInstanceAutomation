package sew.ai.utils;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import sew.ai.runner.TestRunner;

public final class ScreenshotUtils {
	private ScreenshotUtils() {
	}

	public static String getBase64Image() {
		return ((TakesScreenshot) TestRunner.driver).getScreenshotAs(OutputType.BASE64);
	}

	public static String captureSnapshot(WebDriver driver) throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String timestamp = String.valueOf(System.currentTimeMillis());
		String destinationFilePath = "reports" + File.separator + "screenshot" + timestamp + ".png";
		File destinationFile = new File(destinationFilePath);
		FileUtil.copyFileToDirectory(srcFile, destinationFile);
		return destinationFile.getAbsolutePath();
	}

	public static String getScreenshot(WebDriver driver) {
		String screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		return screenshotBase64;
	}

}
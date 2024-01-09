package alectra.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import sew.ai.helpers.exceptions.IOException;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.helpers.reporters.ExtentManager;
import sew.ai.utils.ExcelUtils;
import sew.ai.utils.PropertiesUtil;
import sew.ai.utils.ScreenshotUtils;
import alectra.pageObjects.ToNeethuPage;


public class ToNeethuSteps extends ToNeethuPage {
	private static final Logger log = LogManager.getLogger(ToNeethuSteps.class);
	public static PropertiesUtil loginTextProp;

	public static String failStatus = "FAIL";
	public static String passStatus = "PASS";
	public static String path = "";
	public static String excelPath = "C:\\Users\\egeorge013\\Documents\\SCM10SFunctionalTests\\src\\test\\resources\\excel\\AlectraLoginTestData.xlsx";

	public ToNeethuSteps(WebDriver driver) {
		super(driver);
		loginTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.LOGIN_TXT_FILENAME);
		}
//	public void verifyTheBillHistoryPageObject(SoftAssert softAssert) {
//        softAssert.assertTrue(isBillDateVisible(), "Bill Date is not present.");
//    }
//	
//	public void verifyThePaymentHistoryPageObject(SoftAssert softAssert) {      
//        softAssert.assertTrue(isTransactionDateVisible(), "Transaction Date is not present.");
//    }
		
	public void GreenButtonConnectPage(SoftAssert softAssert) throws InterruptedException {
		waitForPageToLoad();
        clickOnAccountHeader();
        ExtentLogger.logInfo("Click on Account Dropdown.");
        clickOnGreenButtonOption();
        ExtentLogger.logInfo("Click on Green Button Option.");
        Thread.sleep(10000);
		softAssert.assertEquals(getURLforGreenButton(), "https://u2-alectra.smartcmobile.net/portal/#/GreenButtonConnect" );
		Thread.sleep(3000);
		softAssert.assertEquals(getGreenText(), "Green Button Connect");
		Thread.sleep(3000);
		softAssert.assertAll();
	}
	
	public void SettingDefaultCard(SoftAssert softAssert) throws InterruptedException {
		waitForPageToLoad();
		clickOnAccountHeader();
        ExtentLogger.logInfo("Click on Account Dropdown.");
        clickOnPaymentInfoOption();
        ExtentLogger.logInfo("Click on Payment Info Option.");
        Thread.sleep(10000);
		softAssert.assertEquals(getURLforPaymentInfo(), "https://u2-alectra.smartcmobile.net/portal/#/PaymentMethod" );
		clickDOTButton();
		ExtentLogger.logInfo("Click on three dots Option.");
		clickSetAsDefaultButton();
		ExtentLogger.logInfo("Click on set as default.");
		softAssert.assertEquals(getYesBoxText(), "Yes");
		clickYesButton();
		Thread.sleep(5000);
		softAssert.assertEquals(getToastText(), "Your payment information has been updated successfully.");
		Thread.sleep(3000);
		softAssert.assertAll();
	}
}
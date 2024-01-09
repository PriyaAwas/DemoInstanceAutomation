package demo.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import alectra.pageObjects.LoginPage;
import alectra.steps.scp.LoginAlectraSteps;
import demo.pageobjects.notificationPreferencesPage;
import sew.ai.config.Configuration;
import sew.ai.helpers.exceptions.IOException;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.helpers.reporters.ExtentManager;
import sew.ai.models.RegistrationConfig;
import sew.ai.models.RegistrationMailsConfig;
import sew.ai.models.User;
import sew.ai.pageObjects.scp.RegistrationUGIPage;
import sew.ai.utils.ExcelUtils;
import sew.ai.utils.PropertiesUtil;
import sew.ai.utils.RandomUtil;
import sew.ai.utils.ScreenshotUtils;

import org.testng.Assert;

public class notificationPreferencesSteps extends notificationPreferencesPage{
	
	private static final Logger log = LogManager.getLogger(notificationPreferencesSteps.class);
	public static PropertiesUtil loginTextProp;

	public static String failStatus = "FAIL";
	public static String passStatus = "PASS";
	public static String path = "";
	public static String excelPath = "C:\\Users\\egeorge013\\eclipse-workspace\\ForDemo\\src\\test\\resources\\excel\\LoginTestDataNeethu.xlsx";

	public notificationPreferencesSteps(WebDriver driver) {
		super(driver);
		loginTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.LOGIN_TXT_FILENAME);
		}
	
	public void  notifiprefmodule() throws InterruptedException {
		try {
		waitForPageToLoad();
		ExtentLogger.logInfo("Read Input Data From Excel");
		ExcelUtils.openExcelFile(excelPath);
		Sheet sheet = ExcelUtils.getSheetName(1);
		int k = 0;
		for (int i = 1; i <= ExcelUtils.getRowCount(sheet); i++) {
			ExtentLogger.logInfo("Enter User Name. ");
			String UserName = (ExcelUtils.getValueByColName(sheet, i, "UserName"));
			populateUsername(UserName);

			ExtentLogger.logInfo("Enter Password. ");
			String Password = (ExcelUtils.getValueByColName(sheet, i, "Password"));
			populatpassword(Password);
			
			ExtentLogger.logInfo("Click on Log In Button. ");
			clickLogInButton();
			waitForPageToLoad();
			
			isHomeLinkDisplayed();
			ExtentLogger.logInfo("home link visible succesfully. ");
			
			Thread.sleep(10000);
			ExtentLogger.logInfo("Click Account Dropdown. ");
			clickAccountDropDownHome();	
			waitForPageToLoad();
	
	ExtentLogger.logInfo("Click Notification Preference hyperlink. ");
	waitForPageToLoad();
	clickNotiPrefHyperLink();
	waitForPageToLoad();
	
	ExtentLogger.logInfo("Select Daily Usage Down Arrow ");
	clickDailyUsgDwnArrw();
    waitForPageToLoad();
    
    ExtentLogger.logInfo("Select Daily Usage Email Chkbox ");
	clickDailyUsgEmailChkbox();
    waitForPageToLoad();
    
    ExtentLogger.logInfo("Select Daily Usage Save Opt ");
	clickDailyUsgEmailSave();
	Thread.sleep(3000);
	waitForPageToLoad();
	
	ExtentLogger.logInfo("Click Edit Quiet Hrs Opt. ");
	waitForPageToLoad();
	clickEditQutHrsOpt();
	waitForPageToLoad();
				
    ExtentLogger.logInfo("Select Enable Quiet Hrs Chkbox ");
	clickEnblQutHrsChkbox();
    waitForPageToLoad();
    
    ExtentLogger.logInfo("Select Qut Hrs Save Opt ");
	clickQutHrsSave();
	Thread.sleep(3000);
	waitForPageToLoad();
		}
		}
		catch (Exception e) {
			ExtentLogger.logException(e);
			ExtentManager.getExtentTest().addScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot(driver),"Failure capture" );
			
		}
	

}
}

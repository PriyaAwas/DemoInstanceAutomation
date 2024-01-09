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
import demo.pageobjects.guestUserPage;
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

public class guestUserSteps extends guestUserPage{
	
	private static final Logger log = LogManager.getLogger(guestUserSteps.class);
	public static PropertiesUtil loginTextProp;

	public static String failStatus = "FAIL";
	public static String passStatus = "PASS";
	public static String path = "";
	public static String excelPath = "C:\\Users\\egeorge013\\eclipse-workspace\\ForDemo\\src\\test\\resources\\excel\\LoginTestDataNeethu.xlsx";

	public guestUserSteps(WebDriver driver) {
		super(driver);
		loginTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.LOGIN_TXT_FILENAME);
		}
	
	public void  guestusermodule() throws InterruptedException {
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
			
			ExtentLogger.logInfo("Click Guest user hyperlink. ");
			waitForPageToLoad();
			clickGuestUserHyperLink();
			waitForPageToLoad();
			
			Thread.sleep(5000);
			ExtentLogger.logInfo("Click Invite Guestuser Link. ");
			clickInvtGuestUsrLnk();
		    waitForPageToLoad();
		   
		    Thread.sleep(5000);
			ExtentLogger.logInfo("Click Select Acct Down Arrow. ");
			clickSelectAcctArrow();
		    waitForPageToLoad();
    
		    ExtentLogger.logInfo("Select Acct Available from dropdown ");
			clickSelectAcctAvlb();
		    waitForPageToLoad();		    
			
			ExtentLogger.logInfo("Enter Guest Name. ");
			String GuestName = (ExcelUtils.getValueByColName(sheet, i, "GuestName"));
			populateGuestName(GuestName);

			ExtentLogger.logInfo("Enter Guest Email Address. ");
			String GuestEmailAddress = (ExcelUtils.getValueByColName(sheet, i, "GuestEmailAddress"));
			populateGuestEmailAdd(GuestEmailAddress);
		    
		    ExtentLogger.logInfo("Click Select Role Down Arrow. ");
			clickSelectRoleArrow();
		    waitForPageToLoad();
		    
		    ExtentLogger.logInfo("Select Access Expiration date from calendar ");
			clickAccExpDate();
		    waitForPageToLoad();
		    
		    ExtentLogger.logInfo("Click T&C Checkbox for guest user ");
			scrollandclickTnCchkbox();
		    waitForPageToLoad();
		    
		    ExtentLogger.logInfo("Click save guest user button");
			clickSaveGustUsrBttn();
		    waitForPageToLoad();
		    Thread.sleep(3000);
			waitForPageToLoad();
			ExtentManager.getExtentTest().addScreenCaptureFromBase64String(
		    ScreenshotUtils.getScreenshot(driver), "Invite Guest User success message captured");
			
			Thread.sleep(3000);
			ExtentLogger.logInfo("Click Three dot option. ");
			waitForPageToLoad();
			clickThreeDotOpt();
			waitForPageToLoad();
			
			Thread.sleep(2000);
			ExtentLogger.logInfo("Click Edit Guest option. ");
			waitForPageToLoad();
			clickEditGustUsrOpt();
			waitForPageToLoad();
			
			Thread.sleep(3000);
			ExtentLogger.logInfo("Click Update Guest option. ");
			waitForPageToLoad();
			scrollandclickUpdateUsrOpt();
			waitForPageToLoad();
			
			ExtentLogger.logInfo("Select Cancel update button ");
			clickCancelUpdt();
		    waitForPageToLoad();
			
			Thread.sleep(5000);
			ExtentLogger.logInfo("Click Three dot option. ");
			waitForPageToLoad();
			clickThreeDotOpt();
			waitForPageToLoad();
			
			ExtentLogger.logInfo("Click Resend Activation Link option. ");
			waitForPageToLoad();
			clickResendActLnk();
			Thread.sleep(3000);
			waitForPageToLoad();
			ExtentManager.getExtentTest().addScreenCaptureFromBase64String(
		    ScreenshotUtils.getScreenshot(driver), "Resend Activation Lnk message captured successfully ");
		}
		}
		catch (Exception e) {
			ExtentLogger.logException(e);
			ExtentManager.getExtentTest().addScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot(driver),"Failure capture" );
			
		}
	}
}


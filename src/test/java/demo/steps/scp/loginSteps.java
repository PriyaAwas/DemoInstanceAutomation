package demo.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import demo.pageobjects.loginPage;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.helpers.reporters.ExtentManager;
import sew.ai.utils.ExcelUtils;
import sew.ai.utils.PropertiesUtil;
import sew.ai.utils.ScreenshotUtils;

import org.testng.Assert;

public class loginSteps extends loginPage{
	
	private static final Logger log = LogManager.getLogger(loginSteps.class);
	public static PropertiesUtil loginTextProp;

	public static String failStatus = "FAIL";
	public static String passStatus = "PASS";
	public static String path = "";
	public static String excelPath = "C:\\Users\\egeorge013\\eclipse-workspace\\ForDemo\\src\\test\\resources\\excel\\LoginAlectraDataAradhana.xlsx";

	public loginSteps(WebDriver driver) {
		super(driver);
		loginTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.LOGIN_TXT_FILENAME);
		}
	
	public void myAlectra() throws InterruptedException {
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

			waitForPageToLoad();
			Thread.sleep(10000);
			clickcacountDropdown();
			ExtentLogger.logInfo("Clicked Account module from Header ");

			clickcMyAlectraLink();
			ExtentLogger.logInfo("Clicked My Alectra submodule successfully. ");
			waitForPageToLoad();

			scrollPageToElement(pen_icon);
			clickonPenIcon();
			ExtentLogger.logInfo("Clicked Pen Icon successfully ");

			clearemailId();
			ExtentLogger.logInfo("Cleared Email Id Field successfully.");

			ExtentLogger.logInfo("Enter Email Id. ");
			String EmailID = (ExcelUtils.getValueByColName(sheet, i, "EmailID"));
			populateemailID(EmailID);
			

			ExtentLogger.logInfo("Enter Email Address. ");
			String EmailAdd = (ExcelUtils.getValueByColName(sheet, i, "EmailAdd"));
			populateemailadd(EmailAdd);

			ExtentLogger.logInfo("Enter Current Password. ");
			String CurrentPassword = (ExcelUtils.getValueByColName(sheet, i, "CurrentPassword"));
			populatcrctpswrd(CurrentPassword);

			clickonsavebtn();
			ExtentLogger.logInfo("Clicked Save Button Successfully ");
			waitForPageToLoad();
			Thread.sleep(5000);
			ExtentManager.getExtentTest().addScreenCaptureFromBase64String(
			ScreenshotUtils.getScreenshot(driver), "InvalidEmailFailure captured for " + i);
			
			clickonplusicon();
			ExtentLogger.logInfo("Clicked on plus icon successfully. ");
			waitForPageToLoad();
			
			
			ExtentLogger.logInfo("Enter Business Phone No. ");
			String BusinessPhNo = (ExcelUtils.getValueByColName(sheet, i, "BusinessPhNo"));
			populateBusPhNo(BusinessPhNo);
			
			clickonSaveButton();
			ExtentLogger.logInfo("Clicked on save button successfully. ");
			Thread.sleep(5000);
			
			clickonctnbtn();
			ExtentLogger.logInfo("Clicked on Continue button successfully. ");
			waitForPageToLoad();
						
}
}


	public void linkandunlinkaccount() throws InterruptedException {
		waitForPageToLoad();
		ExtentLogger.logInfo("Read Input Data From Excel");
		ExcelUtils.openExcelFile(excelPath);
		Sheet sheet = ExcelUtils.getSheetName(2);
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

			waitForPageToLoad();
			Thread.sleep(10000);
			clickcacountDropdown();
			ExtentLogger.logInfo("Clicked Account module from Header ");

			clickonaccInfo();
			ExtentLogger.logInfo("Clicked Account Information link Successfully ");

			clickonThreedots_Icon();
			ExtentLogger.logInfo("Clicked Three dots icon Successfully");

			clickonunlinkOption();
			ExtentLogger.logInfo("Clicked Unlink Option Successfully");

			clickonCancelButton();
			ExtentLogger.logInfo("Clicked Cancel Button Successfull");

			clickonLinkacc();
			ExtentLogger.logInfo("Clicked Link Account option Successfully");

			ExtentLogger.logInfo("Enter First Name. ");
			String FirstName = (ExcelUtils.getValueByColName(sheet, i, "FirstName"));
			populatFirstName(FirstName);

			ExtentLogger.logInfo("Enter Last Name. ");
			String LastName = (ExcelUtils.getValueByColName(sheet, i, "LastName"));
			populatLastName(LastName);

			clickoncancelButton();
			ExtentLogger.logInfo("Clicked Cancel Button Successfully");
		}
	}
}

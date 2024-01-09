package sew.steps.scp;

import java.util.concurrent.TimeUnit;

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
import sew.pageObjects.scp.LoginSewPage;
import org.testng.Assert;

public class LoginSewSteps extends LoginSewPage {
	private static final Logger log = LogManager.getLogger(LoginSewSteps.class);
	public static PropertiesUtil loginTextProp;

	public static String failStatus = "FAIL";
	public static String passStatus = "PASS";
	public static String path = "";
	public static String excelPath = "C:\\Users\\pawasthi008\\Desktop\\Automation\\backup\\SCM10SFunctionalTests\\SCM10SFunctionalTests\\src\\test\\resources\\excel\\LoginTestData.xlsx";

	public LoginSewSteps(WebDriver driver) {
		super(driver);
		loginTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.LOGIN_TXT_FILENAME);
		}

	public void makePayment() throws InterruptedException {

		waitForPageToLoad();
		ExtentLogger.logInfo("Read Input Data From Excel");
		ExcelUtils.openExcelFile(excelPath);
		Sheet sheet = ExcelUtils.getSheetName(0);
		int k = 0;
		for (int i = 1; i <= ExcelUtils.getRowCount(sheet); i++) {
			ExtentLogger.logInfo("Enter User Name. ");
			String UserName = (ExcelUtils.getValueByColName(sheet, i, "UserName"));
			populateUsername(UserName);

			ExtentLogger.logInfo("Enter Password. ");
			String Password = (ExcelUtils.getValueByColName(sheet, i, "Password"));
			populatpassword(Password);

			ExtentLogger.logInfo("Click on Sign In Button. ");
			clickSignInButton();
			waitForPageToLoad();

			ExtentLogger.logInfo("Click On Payment Button. ");
			clickPaymentButton();

			ExtentLogger.logInfo("Scroll and select Other Amount. ");
			ScrollAndSelect();

			ExtentLogger.logInfo("Enter the bill amount as 1. ");
			String amount = ("1");
			EnterOtherAmmount(amount);
			waitForPageToLoad();

			Thread.sleep(1000);
			ClickOnPaymentMethod();
			ExtentLogger.logInfo("Selected existing CC/DC Payment Method. ");

			clickNextPayementButton();
			ExtentLogger.logInfo("Click on Next Button. ");

			Assert.assertEquals(getPaymentStep2Text(), "STEP 2: REVIEW & CONFIRM");
			ExtentLogger.logInfo("Verify that User is on Payment Step2");

			clickPaymentSubmitBtn();
			ExtentLogger.logInfo("Click on submit Button. ");
			waitForPageToLoad();

			clickYesButton();
			ExtentLogger.logInfo("Click on Yes. ");
			
			clickDoneButton();
			ExtentLogger.logInfo("Verify that user click on done button on Payment successful Page. ");

			k = k + 1;
		}
	}	
	public void usageOverview() throws InterruptedException, java.io.IOException {
	    waitForPageToLoad();
	    ExtentLogger.logInfo("Read Input Data From Excel");

	    // Open the Excel file and get the sheet
	    ExcelUtils.openExcelFile(excelPath);
	    Sheet sheet = ExcelUtils.getSheetName(0);
	    int k = 0;

	    for (int i = 1; i <= ExcelUtils.getRowCount(sheet); i++) {
	        // Log user-related info
	        ExtentLogger.logInfo("Enter User Name.");
	        String UserName = ExcelUtils.getValueByColName(sheet, i, "UserName");
	        populateUsername(UserName);

	        ExtentLogger.logInfo("Enter Password.");
	        String Password = ExcelUtils.getValueByColName(sheet, i, "Password");
	        populatpassword(Password);

	        clickSignInButton();
	        ExtentLogger.logInfo("Click on Sign In Button.");

	        waitForPageToLoad();
	        clickUsageDropdown();
	        ExtentLogger.logInfo("Click on Usage Dropdown.");

	        clickUsageOverviewHeader();
	        ExtentLogger.logInfo("Click on UsageOverView Header.");

	        waitForPageToLoad();
	        isPowerLinkVisible();
	        ExtentLogger.logInfo("Verify that POWER link is visible.");

	        isMeterDropDownVisible();
	        ExtentLogger.logInfo("Verify that Meter Dropdown is visible.");
	        
	        waitForPageToLoad();
	        clickKwhUnit();
	        ExtentLogger.logInfo("Select Kwh Unit.");

	        if (isKwhUnitToggleSelected()) {
	            ExtentLogger.logInfo("Kwh UnitToggle is selected.");
	            // Verify that the selected unit is Kwh
	            Assert.assertEquals(getKwhUnitToggleLabel(), "Kwh");
	            ExtentLogger.logInfo("Verify that selected as Kwh Unit.");

	            try {
	                path = excelPath;
	                ExcelUtils.updateStatus(path, passStatus, i);
	                ExtentLogger.logInfo("Pass Status Updated In Sheet");
	                ExtentManager.getExtentTest().addScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot(driver), "Passed for " + i);

	            } catch (IOException e) {
	                ExtentLogger.logInfo("<---------------issue found for Pass Status--------------------->");
	                e.printStackTrace();
	            }

	            ExtentLogger.logInfo("Validation for row " + k + " of UI is done");
	        } else {
	            // Update FAIL Status back to the excel file
	            ExtentLogger.logInfo("Update FAIL Status back to excel file");
	            try {
	                path = excelPath;
	                ExcelUtils.updateStatus(path, failStatus, i);
	                ExtentLogger.logInfo("Fail Status Updated In Sheet");
	                ExtentManager.getExtentTest().addScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot(driver), "Failure captured for " + i);
	            } catch (IOException e) {
	                ExtentLogger.logInfo("<---------------issue found--------------------->");
	                e.printStackTrace();
	            }
	        }
	        k = k + 1;
	    }
	}

		
		
		public void BillingQuery() throws InterruptedException {
			waitForPageToLoad();
			ExtentLogger.logInfo("Read Input Data From Excel");
			ExcelUtils.openExcelFile(excelPath);
			Sheet sheet = ExcelUtils.getSheetName(0);
			int k = 0;
			for (int i = 1; i <= ExcelUtils.getRowCount(sheet); i++) {
				ExtentLogger.logInfo("Enter User Name. ");
				String UserName = (ExcelUtils.getValueByColName(sheet, i, "UserName"));
				populateUsername(UserName);

				ExtentLogger.logInfo("Enter Password. ");
				String Password = (ExcelUtils.getValueByColName(sheet, i, "Password"));
				populatpassword(Password);

				clickSignInButton();
				ExtentLogger.logInfo("Click on Sign In Button. ");

				waitForPageToLoad();
				clickBillingDropdown();
				ExtentLogger.logInfo("Click on Usage Dropdown. ");

				clickOnBillingQueries();
				ExtentLogger.logInfo("Click on UsageOverView Header. ");

				k = k + 1;
			}
	}
}

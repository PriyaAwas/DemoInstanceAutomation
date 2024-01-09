package sew.ai.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import sew.ai.config.Configuration;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegistrationUGISteps extends RegistrationUGIPage {
	private static final Logger log = LogManager.getLogger(RegistrationUGISteps.class);
	public static PropertiesUtil registrationTextProp;
	String isPaperlessOpted = null;
	Map<String, RegistrationMailsConfig> registrationMails = new HashMap<>();
	Map<String, RegistrationConfig> registrationConfig = new HashMap<>();
	HashMap<String, Map> accountDetails = new HashMap<>();
	Map<String, String> fieldsMinLength = new HashMap<>();
	Map<String, String> fieldsMaxLength = new HashMap<>();
	String verifyCase = null, lastCase = null;
	String subject = null, actValidation = null;
	public static User user;
	public static String loginToken;
	public static String jwtToken;
	public static String failStatus = "FAIL";
	public static String passStatus = "PASS";
    public static String path = "";
    public static String ExcelPath = "C:\\Users\\pawasthi008\\Desktop\\Automation\\backup\\SCM10SFunctionalTests\\SCM10SFunctionalTests\\src\\test\\resources\\excel\\RegistrationTestData.xlsx";


	Boolean isFieldVisible, isFieldMandatory, isFieldValidateCis;
	String utilityAccountNumber, zipCode, usernameTaken, mobilePhone, drivingLicense, meterNo, streetAddress, ssnNo,
			fidTin, addressType, customerID;
	
	public RegistrationUGISteps(WebDriver driver) {
		super(driver);
		registrationTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.REGISTRATION_TXT_FILENAME);
	}

	public void fillRegFormWithPaperLessBilling() throws IOException {
		ExtentLogger.logInfo("Click On Ragister Button for New Account Registration");
		click(By.cssSelector("#btnResidentialType"));
		waitForImgLoadingInvisibility();

		ExtentLogger.logInfo("Closed Feedback Popup");
		try {
			WebElement feedbackPopUp = driver.findElement(By.id("fsrFocusFirst"));
			if (feedbackPopUp.isDisplayed() == true) {
				feedbackPopUp.click();
				log.info("feedbackPopUp Canceled");
			} else {
				log.info("feedbackPopUp not Found");
			}
		} catch (NoSuchElementException e) {
			log.info("feedback popup not displayed");
		}
		ExtentLogger.logInfo("Read Input Data From Excel");
		ExcelUtils.openExcelFile(
				"C:\\Users\\pawasthi008\\Desktop\\Automation\\backup\\SCM10SFunctionalTests\\SCM10SFunctionalTests\\src\\test\\resources\\excel\\RegistrationTestData.xlsx");
		Sheet sheet = ExcelUtils.getSheetName(0);
		int k = 0;
		for (int i = 1; i <= ExcelUtils.getRowCount(sheet); i++) {
			ExtentLogger.logInfo("Closed Feedback Popup");
			try {
				WebElement feedbackPopUp = driver.findElement(By.id("fsrFocusFirst"));
				if (feedbackPopUp.isDisplayed() == true) {
					feedbackPopUp.click();
					log.info("feedbackPopUp Canceled");
				} else {
					log.info("feedbackPopUp not Found");
				}
			} catch (NoSuchElementException e) {
				log.info("feedback popup not displayed");
			}
			ExtentLogger.logInfo("Enter account number");
			String AccountNumber = (ExcelUtils.getValueByColName(sheet, i, "AccountNumber"));
			populateUtilityAccountNumber(AccountNumber);

			ExtentLogger.logInfo("Enter email address");
			String emailID = ExcelUtils.getValueByColName(sheet, i, "AccountNumber") + "@yopmail.com";
			populateEmailid(emailID);

			ExtentLogger.logInfo("Enter ZIP");
			populatePostalCode(ExcelUtils.getValueByColName(sheet, i, "ZipCode"));
			clickNextBtn();

			waitForImgLoadingInvisibility();
			if (isMobileNumberVisible()) {
				String mobileNumber = RandomUtil.generateMobileNumber();
				ExtentLogger.logInfo("Enter mobile number");

				populateMobileNumber(mobileNumber);

				ExtentLogger.logInfo("Enter userID");
				populateUserId((ExcelUtils.getValueByColName(sheet, i, "AccountNumber") + "_UAT"));

				ExtentLogger.logInfo("Enter password");
				populatePassword("Demo@123");

				ExtentLogger.logInfo("Enter confirm password");
				populateConfirmPassword("Demo@123");

				ExtentLogger.logInfo("check for Correspondence ");
				String emailCorrespondence = ExcelUtils.getValueByColName(sheet, i, "Correspondence");
				if (!emailCorrespondence.equalsIgnoreCase("true")) {
					if (isCheckBoxEmailCorrespondanceChecked()) {
						checkChkBoxEmailCorrespondance();
					}
				}
				ExtentLogger.logInfo("check for PaperLessBill");
				String paperlessBilling = ExcelUtils.getValueByColName(sheet, i, "PaperLessBill");
				if (!paperlessBilling.equalsIgnoreCase("true")) {
					if (isChkBoxReceivePaperBillVisible()) {
						checkChkBoxReceivePaperBill();
					}
				}
				ExtentLogger.logInfo("select terms and condition and click on register Button");
				//checkChkBoxTermsConditions();
				//RegisterBtn();
				
				ExtentLogger.logInfo("Update PASS Status back to excel file");
				try {
					path =  ExcelPath;
					ExcelUtils.updateStatus(path, passStatus, i);
					ExtentLogger.logInfo("Pass Status Updated In Sheet");
				} catch (IOException e) {
					ExtentLogger.logInfo("<---------------issue found for Pass Status--------------------->");
					e.printStackTrace();
				}
				ExtentLogger.logInfo("Validation the " + k + " row of UI is done");
				ExtentLogger.logInfo("Navigating back to Registration Page ");
				driver.get(Configuration.toString("ScpReg.url"));
				k = k + 1;
			} else {
				ExtentLogger.logInfo("Update FAIL Status back to excel file");
				try {
					path = ExcelPath;
					ExcelUtils.updateStatus(path, failStatus, i);
					ExtentLogger.logInfo("Fail Status Updated In Sheet");
			        ExtentManager.getExtentTest().addScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot(driver), "Failure captured for" + i);

					//ExtentManager.getExtentTest().addScreenCaptureFromPath(ScreenshotUtils.captureSnapshot(driver));
								} 
				catch (IOException e) {
					ExtentLogger.logInfo("<---------------issue found--------------------->");
					e.printStackTrace();
				}
				ExtentLogger.logInfo(
						"Account Number not correct: " + AccountNumber + " Invalid data Entered in Row Number " + i);
			}
		}
	}
}
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
import demo.pageobjects.paymentInfoPage;
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

public class paymentInfoSteps extends paymentInfoPage {

	private static final Logger log = LogManager.getLogger(paymentInfoSteps.class);
	public static PropertiesUtil loginTextProp;

	public static String failStatus = "FAIL";
	public static String passStatus = "PASS";
	public static String path = "";
	public static String excelPath = "C:\\Users\\pawasthi008\\Documents\\Automation\\DemoAutomation\\TestAutomation_DemoInstance\\src\\test\\resources\\excel\\DemoTestData.xlsx";

	public paymentInfoSteps(WebDriver driver) {
		super(driver);
		loginTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.LOGIN_TXT_FILENAME);
	}

	public void accPaymentInformation() throws InterruptedException {
try {
		waitForPageToLoad();
		ExtentLogger.logInfo("TO PERFORM SANITY ON PAYMENT INFORMATION SUB MODULE");
		ExtentLogger.logInfo("Read Input Data From Excel");
		ExcelUtils.openExcelFile(excelPath);
		Sheet sheet = ExcelUtils.getSheetName(4);
		int k = 0;
		for (int i = 1; i <= ExcelUtils.getRowCount(sheet); i++) {
			String UserName = (ExcelUtils.getValueByColName(sheet, i, "UserName"));
			populateUsername(UserName);
			ExtentLogger.logInfo("Entered User Name. ");

			String Password = (ExcelUtils.getValueByColName(sheet, i, "Password"));
			populatpassword(Password);
			ExtentLogger.logInfo("Entered Password. ");

			clickLogInButton();
			waitForPageToLoad();
			ExtentLogger.logInfo("Clicked Log In Button. ");

			waitForPageToLoad();
			clickAccountDropDownHome();
			ExtentLogger.logInfo("Clicked Account Dropdown. ");

			clickPaymentInformationHyperLink();
			waitForPageToLoad();
			ExtentLogger.logInfo("Clicked Payment information hyperlink. ");

			// Adding Payment Information

			ExtentLogger.logInfo("ADDING Credit card from Payemtinformation ");

			clickAddPaymentMethod();
			Thread.sleep(15000);

			selectCCPaymentMethod();
			waitForPageToLoad();
			ExtentLogger.logInfo("Click Add Payment Method hyperlink. ");
			Thread.sleep(20000);

			String NameOnTheCard = (ExcelUtils.getValueByColName(sheet, i, "NameOnTheCard"));
			populateNameOnCard(NameOnTheCard);
			ExtentLogger.logInfo("Entered Name on the card. ");

			String CardNumber = (ExcelUtils.getValueByColName(sheet, i, "CardNumber"));
			populateCardNumber(CardNumber);
			ExtentLogger.logInfo("Entered card number. ");

			SelectMonthFromDropDown("Dec");
			ExtentLogger.logInfo("Clicked month dropdown. ");

			selectYearFromDropDown(9);
			ExtentLogger.logInfo("clicked and selected Year . ");

			String CVV = (ExcelUtils.getValueByColName(sheet, i, "CVV"));
			populateCVV(CVV);
			ExtentLogger.logInfo("Entered cvv. ");

			String FistName = (ExcelUtils.getValueByColName(sheet, i, "FirstName"));
			populateFirstName(FistName);
			ExtentLogger.logInfo("Entered FirstName. ");

			String LastName = (ExcelUtils.getValueByColName(sheet, i, "LastName"));
			populateLastName(LastName);
			ExtentLogger.logInfo("Entered LastName. ");

			String Address = (ExcelUtils.getValueByColName(sheet, i, "Address"));
			populateAddress(Address);
			ExtentLogger.logInfo("Entered Address. ");

			String City = (ExcelUtils.getValueByColName(sheet, i, "City"));
			populateCity(City);
			ExtentLogger.logInfo("Entered City. ");

			String State = (ExcelUtils.getValueByColName(sheet, i, "State"));
			populateState(State);
			ExtentLogger.logInfo("Entered State. ");

			String Zipcode = (ExcelUtils.getValueByColName(sheet, i, "Zipcode"));
			populateZipcode(Zipcode);
			ExtentLogger.logInfo("Entered Zipcode. ");
			
			ExtentLogger.logInfo("Updated card and billing details Sucessfully. ");

			clickOnAddButton();
			waitForPageToLoad();
			ExtentLogger.logInfo("Added sucessfully. ");
			Thread.sleep(5000);

			// need to do assertions
			// Assert.assertEquals(VerifySucessMsg(), "STEP 2: REVIEW & CONFIRM");
			
			// Payment done from Added CC

			ExtentLogger.logInfo("Navigated to billig module. ");
			clickBiling();
			ExtentLogger.logInfo("Click On billing dropdown. ");

			clickCurrentBill();
			ExtentLogger.logInfo("Click On currentBill. ");

			clickMakePaymentButton();
			ExtentLogger.logInfo("Click On Payment Button. ");
			ScrollAndSelect();
			ExtentLogger.logInfo("Scroll and select Other Amount. ");

			String amount = ("1");
			EnterOtherAmmount(amount);
			ExtentLogger.logInfo("Entered the bill amount as 1. ");

			waitForPageToLoad();
			Thread.sleep(1000);
			ClickOnPaymentMethod();
			ExtentLogger.logInfo("Selected newly added existing CC/DC Payment Method. ");
			String card = "AMERICAN_EXPRESS - 0131 - Exp. (12/32)";
			SelectAddedCard(card);

			clickNextPayementButton();
			ExtentLogger.logInfo("Click on Next Button. ");

			Assert.assertEquals(getPaymentStep2Text(), "STEP 2: REVIEW & CONFIRM");
			ExtentLogger.logInfo("Verify that User is on Payment Step2");

			clickPaymentSubmitBtn();
			ExtentLogger.logInfo("Click on submit Button. ");
			waitForPageToLoad();

			clickYesButton();
			ExtentLogger.logInfo("Click on Yes. ");
			
			ExtentManager.getExtentTest().addScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot(driver),
					"Screen Capture of Success Toast - Adding Payment information" + i);
			ExtentLogger.logInfo("Add success toast is displayed and Screenshot is captured. ");
			
			clickDoneButton();
			ExtentLogger.logInfo("Verify that user click on done button on Payment successful Page. ");	

		}
	}
	  catch (Exception e) { //ExtentLogger.logException(e);
	  ExtentManager.getExtentTest().addScreenCaptureFromBase64String(
	  ScreenshotUtils.getScreenshot(driver),"Failure capture" );
	  
	  }}
	 

	public void makePaymentwithExistingCreditCard() throws InterruptedException {
		try {
			waitForPageToLoad();
			ExtentLogger.logInfo("TO PERFORM PAYEMT WITH EXISTING CREDIT CARD");
			ExtentLogger.logInfo("Read Input Data From Excel");
			ExcelUtils.openExcelFile(excelPath);
			Sheet sheet = ExcelUtils.getSheetName(4);
			int k = 0;
			for (int i = 1; i <= ExcelUtils.getRowCount(sheet); i++) {
				String UserName = (ExcelUtils.getValueByColName(sheet, i, "UserName"));
				populateUsername(UserName);
				ExtentLogger.logInfo("Entered User Name. ");

				String Password = (ExcelUtils.getValueByColName(sheet, i, "Password"));
				populatpassword(Password);
				ExtentLogger.logInfo("Entered Password. ");

				clickLogInButton();
				waitForPageToLoad();
				ExtentLogger.logInfo("Clicked Log In Button. ");
				
				ExtentLogger.logInfo("Navigated to billig module. ");
				clickBiling();
				ExtentLogger.logInfo("Click On billing dropdown. ");

				clickCurrentBill();
				ExtentLogger.logInfo("Click On currentBill. ");
				//clickAccountdrop();
				//String AccountNumber = clickAndgetaccountnum();

				clickMakePaymentButton();
				ExtentLogger.logInfo("Click On Payment Button. ");				
				
				String amount = ("1");
				EnterOtherAmmount(amount);
				ExtentLogger.logInfo("Entered the bill amount as 1. ");
				ClickOnPaymentMethod();
				clickOnSelectedCard();
				
				clickNextPayementButton();
				ExtentLogger.logInfo("Click on Next Button. ");

				Assert.assertEquals(getPaymentStep2Text(), "STEP 2: REVIEW & CONFIRM");
				ExtentLogger.logInfo("Verify that User is on Payment Step2");

				String accNumOnPayemtPage = getAccountNum();
				String billamount = getBillAmmount();

				String transfee = getTranFee();

				String tottlefee = getTotleFee();
				String paymentdate = getPayementDate();
				
				clickPaymentSubmitBtn();
				ExtentLogger.logInfo("Click on submit Button. ");
				waitForPageToLoad();

				clickYesButton();
				ExtentLogger.logInfo("Click on Yes. ");
								
			}
		} catch (Exception e) {
			ExtentLogger.logException(e);
			ExtentManager.getExtentTest().addScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot(driver),
					"Failure capture");

		}
	}

}

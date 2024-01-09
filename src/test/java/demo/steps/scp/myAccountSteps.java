package demo.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import demo.pageobjects.myAccountPage;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.helpers.reporters.ExtentManager;
import sew.ai.utils.ExcelUtils;
import sew.ai.utils.PropertiesUtil;
import sew.ai.utils.ScreenshotUtils;

public class myAccountSteps extends myAccountPage{
	
	private static final Logger log = LogManager.getLogger(myAccountSteps.class);
	public static PropertiesUtil loginTextProp;

	public static String failStatus = "FAIL";
	public static String passStatus = "PASS";
	public static String path = "";
	//public static String excelPath = "C:\\Users\\egeorge013\\eclipse-workspace\\ForDemo\\src\\test\\resources\\excel\\LoginTestDataNeethu.xlsx";
	public static String excelPath = "C:\\Users\\pawasthi008\\Downloads\\ForDemo 1\\ForDemo\\src\\test\\resources\\excel\\AlectraTestData.xlsx";
	public myAccountSteps(WebDriver driver) {
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
	
	// My Alectra page functionalities sanity tests
	public void myAlectra() throws InterruptedException {
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


	// Account Information Linking and Unlinking Process test
	public void linkandunlinkaccount() throws InterruptedException {
		waitForPageToLoad();
		ExtentLogger.logInfo("Read Input Data From Excel");
		ExcelUtils.openExcelFile(excelPath);
		Sheet sheet = ExcelUtils.getSheetName(3);
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
	
public void  accPaymentInformation() throws InterruptedException {
		
        try {
		waitForPageToLoad();
		ExtentLogger.logInfo("TO PERFORM SANITY ON PAYMENT INFORMATION SUB MODULE");
		ExcelUtils.openExcelFile(excelPath);
		Sheet sheet = ExcelUtils.getSheetName(4);
		ExtentLogger.logInfo("Read Input Data From Excel");


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
			Thread.sleep(15000);
			clickAccountDropDownHome();
			ExtentLogger.logInfo("Clicked Account Dropdown. ");
			
			clickPaymentInformationHyperLink();
			waitForPageToLoad();
			ExtentLogger.logInfo("Clicked Payment information hyperlink. ");
			
			//Adding Payment Information 
			
			ExtentLogger.logInfo("ADDING PAYMENT INFORMATION ");
			
			clickAddPaymentMethod();
			waitForPageToLoad();
			ExtentLogger.logInfo("Click Add Payment Method hyperlink. ");
			
			String NameOnTheCard = (ExcelUtils.getValueByColName(sheet, i, "NameOnTheCard"));
			populateNameOnCard(NameOnTheCard);
			
			ExtentLogger.logInfo("Entered Name on the card. ");
			
			String CardNumber = (ExcelUtils.getValueByColName(sheet, i, "CardNumber"));
			populateCardNumber(CardNumber);
			ExtentLogger.logInfo("Entered card number. ");
			
			String CVV = (ExcelUtils.getValueByColName(sheet, i, "CVV"));
			populateCVV(CVV);
			ExtentLogger.logInfo("Entered cvv. ");
			
			clickMonthDropDown();
			ExtentLogger.logInfo("Clicked month dropdown. ");
			
			clickMonthFromOptions();
			ExtentLogger.logInfo("selected month . ");
			
			clickYearDropDown();
			ExtentLogger.logInfo("Clicked Year dropdown. ");
			
			clickYearFromOptions();
			ExtentLogger.logInfo("selected Year . ");
			
			clickSaveButton();
			ExtentLogger.logInfo("clicked Save button. ");
			waitForPageToLoad();
			Thread.sleep(5000);   
			ExtentManager.getExtentTest().addScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot(driver), "Screen Capture of Success Toast - Adding Payment information" + i);
		    ExtentLogger.logInfo("Add success toast is displayed and Screenshot is captured. ");
		    
		    //Editing Payment Information
		    
		    ExtentLogger.logInfo("EDITING PAYMENT INFORMATION");
		    
		    Thread.sleep(5000);
		    clickPaymentThreeDotIcon();
			waitForPageToLoad();
			ExtentLogger.logInfo("Clicked Payment information Three dot Icon. ");
			
			clickEditOptionPay();
			waitForPageToLoad();
			ExtentLogger.logInfo("Clicked Edit option. ");
			
			String CVV1 = (ExcelUtils.getValueByColName(sheet, i, "CVV1"));
			populateCVV1(CVV1);
			ExtentLogger.logInfo("Entered cvv. ");
			
			clickMonthDropDown();
			ExtentLogger.logInfo("Clicked month dropdown. ");
			
			clickMonthFromOptions();
			ExtentLogger.logInfo("selected month . ");
			
			clickYearDropDown();
			ExtentLogger.logInfo("Clicked Year dropdown. ");
			
			clickYearFromOptions();
			ExtentLogger.logInfo("selected Year . ");
			
			clickSaveButton();
			waitForPageToLoad();
			ExtentLogger.logInfo("clicked Save button ");
			
			Thread.sleep(3000);
			ExtentManager.getExtentTest().addScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot(driver), "Screen Capture of Success Toast - Editing Payment information " + i);
			ExtentLogger.logInfo("Edit Success toast is displayed and Screen shot is captured. ");	
			
			//Set Payment information as Default
			
			ExtentLogger.logInfo("SET PAYMENT INFORMATION AS DEFAULT");
			clickPaymentThreeDotIcon();
			waitForPageToLoad();
			ExtentLogger.logInfo("Clicked Payment information Three dot Icon. ");
			
			clickSetAsDefaultOptionPay();
			waitForPageToLoad();
			ExtentLogger.logInfo("Clicked set as default option option. ");
			
			clickOkButton();
			waitForPageToLoad();
			ExtentLogger.logInfo("clicked ok button");
			
			isDefaultButtonTextDisplayed();
			ExtentLogger.logInfo("Default text is displayed ");
			
			//Add Payment information -- Leaves mandatory fields blank
			
			ExtentLogger.logInfo("ADD PAYMENT INFORMATION -- LEAVES MANDATORY FIELDS BLANK --NEGATIVE ");
			
			clickAddPaymentMethod();
			waitForPageToLoad();
			ExtentLogger.logInfo("Clicked Add Payment Method hyperlink. ");
			
			clickSaveButton();
			waitForPageToLoad();
			ExtentLogger.logInfo("click Save button");
			
			isBlankErrorMessageDisplayed();
			ExtentManager.getExtentTest().addScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot(driver), "Failure capture -- Add Payment information -- Leaves mandatory fields blank  " + i);
			ExtentLogger.logInfo("Error message is displayed and screenshot is captured. ");
			
			//Add Payment information -- Enters Card details which is non acceptable
			
			ExtentLogger.logInfo("ADD PAYMENT INFORMATION -- ENTERS NON ACCEPTABLE CARD DETAILS -- NEGATIVE ");
			
			String NameOnTheCard1 = (ExcelUtils.getValueByColName(sheet, i, "NameOnTheCard1"));
			populateNameOnCard1(NameOnTheCard1);
			ExtentLogger.logInfo("Entered Name on the card. ");
			
			String CardNumber1 = (ExcelUtils.getValueByColName(sheet, i, "CardNumber1"));
			populateCardNumber1(CardNumber1);
			ExtentLogger.logInfo("Entered card number. ");
			
			String CVV2 = (ExcelUtils.getValueByColName(sheet, i, "CVV2"));
			populateCVV2(CVV2);
			ExtentLogger.logInfo("Entered cvv. ");
			
			clickMonthDropDown();
			ExtentLogger.logInfo("Clicked month dropdown. ");
			
			clickMonthFromOptions();
			ExtentLogger.logInfo("selected month . ");
			
			clickYearDropDown();
			ExtentLogger.logInfo("Clicked Year dropdown. ");
			
			clickYearFromOptions();
			ExtentLogger.logInfo("selected Year . ");
			
			clickSaveButton();
			ExtentLogger.logInfo("clicked Save button ");
			waitForPageToLoad();
			
			isCardNonAcceptedErrorDisplayed();
			ExtentManager.getExtentTest().addScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot(driver), "Failure captured -- Add Payment information -- Enters Card details which is non acceptable" + i);
		    ExtentLogger.logInfo("Card not acceptable error is displayed and screenshot is captured. ");
		    
		    clickCancelButton();
		    ExtentLogger.logInfo("Clicked Cancel Button ");
		    waitForPageToLoad();
		    
		    //Editing Payment information -- Leaves mandatory field blank
		    
		    ExtentLogger.logInfo("EDITING PAYMENT INFORMATION -- LEAVES MANDATORY FIELDS BLANK --NEGATIVE");
		    
		    Thread.sleep(5000);
		    clickPaymentThreeDotIcon();
			waitForPageToLoad();
			ExtentLogger.logInfo("Clicked Payment information Three dot Icon. ");
			
		    clickEditOptionPay();
			waitForPageToLoad();
			ExtentLogger.logInfo("Clicked Edit option. ");
			
			clickSaveButton();
			waitForPageToLoad();
			ExtentLogger.logInfo("clicked Save button ");
			
			isEnterCVVErrorDisplayed();
			ExtentManager.getExtentTest().addScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot(driver), "Failure captured -- Editing Payment information -- Leaves mandatory field blank" + i);
			ExtentLogger.logInfo("Enter CVV Error message displayed");
			
			clickCancelButton();
		    ExtentLogger.logInfo("Clicked Cancel Button ");
		    
		    //Deleting Payment information 
		    
		    ExtentLogger.logInfo("DELETING PAYMENT INFORMATION ");
		    
		    clickPaymentThreeDotIcon();
			waitForPageToLoad();
			ExtentLogger.logInfo("Clicked Payment information Three dot Icon. ");
			
			clickDeleteOptionPay();
			waitForPageToLoad();
			ExtentLogger.logInfo("Clicked delete option. ");
			
			clickRemoveButton();
			waitForPageToLoad();
			ExtentLogger.logInfo("clicked Remove button. ");
			
			Thread.sleep(2000);
			ExtentManager.getExtentTest().addScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot(driver), "Success capture of deleting payment information  " + i);
			ExtentLogger.logInfo("Delete Success toast is displayed and Screen shot is captured ");
			
			
	    		    
	}
  }
        catch (Exception e) {
			ExtentLogger.logException(e);
			ExtentManager.getExtentTest().addScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot(driver),"Failure capture" );
			
		}
 }
public void GreenButtonConnectPage(SoftAssert softAssert) throws InterruptedException {
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
		Thread.sleep(10000);
		clickOnAccountHeader();
        ExtentLogger.logInfo("Click on Account Dropdown.");
        clickOnGreenButtonOption();
        ExtentLogger.logInfo("Click on Green Button Option.");
        Thread.sleep(10000);
		Assert.assertEquals(getURLforGreenButton(), "https://u2-alectra.smartcmobile.net/portal/#/GreenButtonConnect" );
		Thread.sleep(3000);
//		softAssert.assertNotEquals(getGreenText(), "Green Button Connect");
//		Thread.sleep(3000);
		ExtentManager.getExtentTest().addScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot(driver), "Capture of Green Button Screenshot  " + i);
//		softAssert.assertAll();	
		}
	}
	catch (Exception e) {
		ExtentLogger.logException(e);
		ExtentManager.getExtentTest().addScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot(driver),"Failure capture" );
		
	}
}


}


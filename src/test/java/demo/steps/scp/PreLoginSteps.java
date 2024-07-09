package demo.steps.scp;

import static org.testng.Assert.assertEquals;
import static sew.ai.steps.scp.DashboardSteps.dashboardTextProp;
import static sew.ai.steps.scp.LoginSteps.loginTextProp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import demo.pageobjects.PreLoginPage;
import sew.ai.config.CSPConfiguration;
import sew.ai.config.Configuration;
import sew.ai.driver.DriverFactory;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.SignOutSteps;
import sew.ai.utils.PropertiesUtil;

public class PreLoginSteps extends PreLoginPage {

	private static final Logger log = LogManager.getLogger(PreLoginSteps.class);
	public static PropertiesUtil loginTextProp;
	public static PropertiesUtil preLoginConnectMeProp;
	public static PropertiesUtil signOutTextProp;
	public static PropertiesUtil preLoginPaymentLocationsProp;
	public static PropertiesUtil preLoginPaymentProp;
	public static PropertiesUtil preLoginWaysToSaveProp;

	public PreLoginSteps(WebDriver driver) {
		super(driver);
		loginTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.LOGIN_TXT_FILENAME);
		preLoginConnectMeProp = new PropertiesUtil(
				FilePaths.SCP_TEXT_PROPERTIES + Constants.PRE_LOG_CONNECT_ME_TXT_FILENAME);
		signOutTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.SIGNOUT_TXT_FILENAME);
		preLoginPaymentLocationsProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.SCM_PAYMENT_LOCATIONS_TXT_FILENAME);
		preLoginPaymentProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.SCM_PAYMENT_TXT_FILENAME);
		preLoginWaysToSaveProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.SCM_WAYSTOSAVE_TXT_FILENAME);
	}

	public void populateLoginForm(String userName, String password) {
		populateUserName(userName);
		populatePassword(password);
		ExtentLogger.logInfo("username and password populated");
	}

	public String loginWithBlankCreds() {
		clickSignInBtn();
		String errMsg = getToastMessageWithoutWait();
		clickToastCloseBtn();
		ExtentLogger.logInfo("cannot login with blank creds");
		return errMsg;
	}

	public String loginWithInvalidCreds(String userName, String password) {
		populateLoginForm(userName, password);
		clickSignInBtn();
		String errMsg = getToastMessage();
		ExtentLogger.logInfo("cannot login with invalid creds");
		return errMsg;
	}

	public String loginWithBlankUsername(String password) {
		clearUsernameField();
		populatePassword(password);
		clickSignInBtn();
		String errMsg = getCommonValidationMsg();
		ExtentLogger.logInfo("cannot login with blank username");
		return errMsg;
	}

	public String loginWithWrongUsername(String userName, String password) {
		populateLoginForm(userName, password);
		clearUsernameField();
		populateUserName("invalid@username.com");
		clickSignInBtn();
		String errMsg = getToastMessage();
		ExtentLogger.logInfo("cannot login with wrong username");
		return errMsg;
	}

	public String loginWithWrongPassword(String userName, String password) {
		populateLoginForm(userName, password);
		clearPasswordField();
		populatePassword("Invalid@Pass123");
		clickSignInBtn();
		String errMsg = getToastMessage();
		ExtentLogger.logInfo("cannot login with wrong password");
		return errMsg;
	}

	public String loginWithInvalidCreds() {
		populateUserName("Invalid@Username");
		populatePassword("Invalid@Password");
		clickSignInBtn();
		String errMsg = getToastMessage();
		ExtentLogger.logInfo("cannot login with invalid creds");
		return errMsg;
	}

	public String loginWithBlankPassword(String userName) {
		populateUserName(userName);
		clearPasswordField();
		clickSignInBtn();
		String errMsg = getCommonValidationMsg();
		ExtentLogger.logInfo("cannot login with blank password");
		return errMsg;
	}

	public DashboardSteps loginIntoTheApplication(String userName, String password) {
		ExtentLogger.logInfo("Logging into the application.");
		DashboardSteps dashboardSteps;
		waitForPageToLoad();
		populateLoginForm(userName, password);
		clickSignInBtn();
		waitForPageLoader();
		dashboardSteps = new DashboardSteps(driver);
		ExtentLogger.logInfo("Dashboard page url : " + dashboardTextProp.getPropValue("dashboardPageUrl"));
		ExtentLogger.logInfo("Dashboard page title : " + dashboardTextProp.getPropValue("dashboardPageHeader"));
		Assert.assertTrue(dashboardSteps.isDashboardPage(dashboardTextProp.getPropValue("dashboardPageUrl"),
				dashboardTextProp.getPropValue("dashboardPageHeader")), "Not navigated to dashboard page.");
		ExtentLogger.logPass("Logged into the application successfully.");
		return dashboardSteps;
	}

	public void loginIntoTheApplicationWrongCreds(SoftAssert softAssert) {
		// Init user model
		String user = Configuration.toString("userName");
		String password = Configuration.toString("password");
		// Verify login with blank creds
			softAssert.assertEquals(loginWithBlankCreds(), loginTextProp.getPropValue("loginWithBlankCredsErrMsg"),
				"Login with Blank creds error message not matched.");
		// Verify login with blank password
		softAssert.assertEquals(loginWithBlankPassword(user),
				loginTextProp.getPropValue("loginWithBlankPasswordErrMsg"),
				"Blank username field validation not correct.");
		// Verify login with blank username
		softAssert.assertEquals(loginWithBlankUsername(password),
				loginTextProp.getPropValue("loginWithBlankUsernameErrMsg"),
				"Blank username field validation not correct.");
		// Verify login with wrong username
		softAssert.assertEquals(loginWithWrongUsername(user, password),
				loginTextProp.getPropValue("invalidCredentialsErrMsg"),
				"Wrong toast when login using the wrong username.");
		// Verify login with wrong password
		softAssert.assertEquals(loginWithWrongPassword(password, user),
				loginTextProp.getPropValue("invalidCredentialsErrMsg"),
				"Wrong toast when login using the wrong password.");
		// Verify login with wrong username and password.
		softAssert.assertEquals(loginWithInvalidCreds(), loginTextProp.getPropValue("invalidCredentialsErrMsg"),
				"Wrong toast when login using invalid creds.");
	}

	public void verifyTheLoginPageObject(SoftAssert softAssert) {
		waitForPageToLoad();
		Assert.assertTrue(
				isLoginPage(loginTextProp.getPropValue("loginPageUrl"), loginTextProp.getPropValue("loginPageTitle")));
		softAssert.assertTrue(isCompanyLogoVisible(), "Company logo is not present.");
		softAssert.assertTrue(isLanguageDropdownVisible(), "Language dropdown option is not visible.");
		softAssert.assertEquals(getLanguageDropdownSelectedOption(),
				loginTextProp.getPropValue("optionInLanguageDropdown"),
				"Option selected in the language drop-down not matched.");
		softAssert.assertEquals(getLoginPageHeader(), loginTextProp.getPropValue("loginPageHeader"),
				"Login page header not matched.");
		softAssert.assertTrue(isUsernameTxtVisible(), "Username text field is not visible.");
		softAssert.assertEquals(getUsernameLabel(), loginTextProp.getPropValue("lblUsername"),
				"Label for username is not matched.");
		softAssert.assertTrue(isPasswordTxtVisible(), "Password text field is not visible.");
		softAssert.assertEquals(getPasswordLabel(), loginTextProp.getPropValue("lblPassword"),
				"Label for password is not matched.");
		softAssert.assertTrue(isRememberMeChbVisible(), "Remember me check box is not visible.");
		softAssert.assertEquals(getSignInBtnLabel(), loginTextProp.getPropValue("lblSignInBtn"),
				"Label for sign in button is not matched.");
		softAssert.assertEquals(getForgotUsernameLabel(), loginTextProp.getPropValue("lblForgotUsername"),
				"Label for forgot username is not matched.");
		softAssert.assertEquals(getForgotPasswordLabel(), loginTextProp.getPropValue("lblForgotPassword"),
				"Label for forgot password is not matched.");
		softAssert.assertEquals(getProblemSigningInLabel(), loginTextProp.getPropValue("lblProblemSignIn"),
				"Label for problem sign in is not matched.");
		softAssert.assertEquals(getRegisterLinkLabel(), loginTextProp.getPropValue("lblRegisterLnk"),
				"Label for register link is not matched.");
		//softAssert.assertEquals(getAdvancedServicesLinkLabel(), loginTextProp.getPropValue("lnkAdvancedServices"),
			//	"Label for advanced service link is not matched.");
		softAssert.assertEquals(getPayBillLinkLabel(), loginTextProp.getPropValue("lnkPayBill"),
				"Label for pay bill link is not matched.");
		softAssert.assertEquals(getOutagesLinkLabel(), loginTextProp.getPropValue("lnkOutages"),
				"Label for outages link is not matched.");		
		softAssert.assertEquals(getWaysToSaveLinkLabel(), loginTextProp.getPropValue("lnkWaysToSave"),
				"Label for ways to save link is not matched.");
		softAssert.assertEquals(getPaymentLocationsLinkLabel(), loginTextProp.getPropValue("lnkPaymentLocations"),
				"Label for payment locations link is not matched.");
		softAssert.assertEquals(getContactUsLinkLabel(), loginTextProp.getPropValue("lnkContactUs"),
				"Label for contact us link is not matched.");
		softAssert.assertEquals(getReportLeaksLinkLabel(), loginTextProp.getPropValue("lnkReportLeaks"),
				"Label for report leaks link is not matched.");
		softAssert.assertEquals(getFaqsLinkLabel(), loginTextProp.getPropValue("lnkFaqs"),
				"Label for ways to faqs link is not matched.");
		softAssert.assertEquals(getFooterContactUsLinkLabel(), loginTextProp.getPropValue("lnkFooterContactUs"),
				"Label for footer contact us link is not matched.");
		softAssert.assertEquals(getTermsAndConditionsLinkLabel(), loginTextProp.getPropValue("lnkTermsAndConditions"),
				"Label for terms and conditions link is not matched.");
		softAssert.assertEquals(getPrivacyPolicyLinkLabel(), loginTextProp.getPropValue("lnkPrivacyPolicy"),
				"Label for privacy policy link is not matched.");
	}

	public DashboardSteps loginIntoTheAppByCheckingRememberMe(String userName, String password) {
		DashboardSteps dashboardSteps;
		populateLoginForm(userName, password);
		checkRememberMe();
		clickSignInBtn();
		waitForPageLoader();
		dashboardSteps = new DashboardSteps(driver);
		Assert.assertTrue(dashboardSteps.isDashboardPage(dashboardTextProp.getPropValue("dashboardPageUrl"),
				dashboardTextProp.getPropValue("dashboardPageHeader")));
		dashboardSteps.waitForImageProfileIcon();
		return dashboardSteps;
	}

	public Boolean verifyUsernameAndRememberMeCheckboxStatus(DashboardSteps dashboardSteps, String userName) {
		SignOutSteps signOutSteps;
		dashboardSteps.clickImageProfileIco();
		dashboardSteps.bypassTheAboutMyHomePage();
		dashboardSteps.clickSignOutLnk();
		signOutSteps = new SignOutSteps(driver);
		signOutSteps.waitForSignOutSuccessLbl();
		signOutSteps.clickSignInAgainBtn();
		waitForUserNameFieldVisibility();
		assertEquals(getPopulatedUsernameValue(), userName,
				"Username is not auto populating on checking remember me.");
		return isRememberMeChbChecked();
	}

	public boolean isPreLogConnectMePage(String url, String title) {
		Boolean isForgetPasswordPage = false;
		log.info("Checking that the current page is ForgetPassword Page");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isForgetPasswordPage = true;
		log.info("The current page is Connect me Page {}: " + isForgetPasswordPage);
		log.info("The current page is oo {}: " + getCurrentUrl());
		log.info("The current page is 00 {}: " + getCurrentTitle());

		return isForgetPasswordPage;
	}

	public void verifyPreLogConnectMeObject(SoftAssert softAssert) {
		clickContactUsLnk();
		pause(1000);
		selectlstConnectMeOptions("Rebates");
		if (selectlstConnectMeOptions("Rebates")) {
		}
		Assert.assertTrue(isPreLogConnectMePage(preLoginConnectMeProp.getPropValue("ConnectMePageUrl"),
				(preLoginConnectMeProp.getPropValue("ConnectMePageTitleTxt"))), "Page Title & URL does not Match");
		
		softAssert.assertTrue(isPageHeaderPostVisible(), "Contact Us Page Header is not visible");
		softAssert.assertTrue(isSocialMediaVisible(), "Social Media Tab is not visibility");
		softAssert.assertTrue(isContactusVisible(), "Contact Us Tab is not visibility");
		softAssert.assertTrue(isTrackRequestVisible(), "Track Request Tab is not visibility");
		softAssert.assertTrue(isSavedFormVisible(), "Saved Form tab is not visibility");
		softAssert.assertTrue(isSubmitBtnVisible(), "Submit button is not visibility");
		softAssert.assertTrue(isNextBtnVisible(), "Next button is not visibility");
		softAssert.assertTrue(isCustomerNameTxtVisible(), "Customer Name text Box is not visibility");
		softAssert.assertTrue(isServiceAccNoTxtVisible(), "Service Acc No text Box is not visibility");
		softAssert.assertTrue(isEmailAddressTxtVisible(), "Email Address text Box is not visibility");
		softAssert.assertTrue(istxtSubjectTxtVisible(), "Subject button is text Box visibility");
		softAssert.assertTrue(isCommentsTxtVisible(), "Comments text Box is not visibility");
		softAssert.assertTrue(isChooseFileBtnVisible(), "Choose File text Box is not visibility");
		softAssert.assertTrue(isCustomerlblVisible(), "Instagram Tab is not visibility");
		@SuppressWarnings("unused")
		List<String> list = getdropdownOptions();
	}

	public void verifyPreLogConnectMeObjectForWaterLeaks(SoftAssert softAssert) {
		clickWaterLeakLnk();
		pause(1000);
		selectlstConnectMeOptions("Rebates");
		if (selectlstConnectMeOptions("Rebates")) {
		}
//        softAssert.assertTrue(isPreLogConnectMePage(preLoginConnectMeProp.getPropValue("ConnectMePageUrl"),
//         		 (preLoginConnectMeProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");    
		softAssert.assertTrue(isPageHeaderPostVisible(), "Contact Us Page Header is not visible");
		softAssert.assertTrue(isSocialMediaVisible(), "Social Media Tab is not visibility");
		softAssert.assertTrue(isContactusVisible(), "Contact Us Tab is not visibility");
		softAssert.assertTrue(isTrackRequestVisible(), "Track Request Tab is not visibility");
		softAssert.assertTrue(isSavedFormVisible(), "Saved Form tab is not visibility");
		softAssert.assertTrue(isSubmitBtnVisible(), "Submit button is not visibility");
		softAssert.assertTrue(isNextBtnVisible(), "Next button is not visibility");
		softAssert.assertTrue(isCustomerNameTxtVisible(), "Customer Name text Box is not visibility");
		softAssert.assertTrue(isServiceAccNoTxtVisible(), "Service Acc No text Box is not visibility");
		softAssert.assertTrue(isEmailAddressTxtVisible(), "Email Address text Box is not visibility");
		softAssert.assertTrue(istxtSubjectTxtVisible(), "Subject button is text Box visibility");
		softAssert.assertTrue(isCommentsTxtVisible(), "Comments text Box is not visibility");
		softAssert.assertTrue(isChooseFileBtnVisible(), "Choose File text Box is not visibility");
		softAssert.assertTrue(isCustomerlblVisible(), "Instagram Tab is not visibility");
		@SuppressWarnings("unused")
		List<String> list = getdropdownOptions();
	}

	public void verifyCreatePreContactRequest(SoftAssert softAssert) {
		String AccNum = Configuration.toString("utilityAccountNumber");
		String user = Configuration.toString("userName");
		selectlstConnectMeOptions("Billing");
		waitForPageToLoad();
		if (selectlstConnectMeOptions("Billing")) {
		}
		if (isServiceAccNoTxtVisible()) {
			populateServiceAccNo(AccNum);
			pause(500);
		}
		if (isCustomerNameTxtVisible()) {
			populateCustomerName(user);
			pause(500);
		}
		if (isEmailAddressTxtVisible()) {
			populateEmailAddress("test@test.com");
			pause(500);
		}
//	 	 	   if(isChooseFileBtnVisible()) {
//	 	 		   String validAttachmentFileName = "meter-reading.jpg";
//	 	 	        addAttachmentToChooseFile(FILE_UPLOAD_PATH + validAttachmentFileName);
//	 	 	      pause(500);
//	 	 	   }
		if (istxtSubjectTxtVisible()) {
			populateSubject("Testing");
			pause(500);
		}
		if (isCommentsTxtVisible()) {
			populateComments("Testing");
			pause(500);
		}
		btnClickNext();
	}

	public void verifyCreatePreContactRequestForWaterLeaks(SoftAssert softAssert) {
		clickWaterLeakLnk();
		String AccNum = Configuration.toString("utilityAccountNumber");
		String user = Configuration.toString("userName");
		selectlstConnectMeOptions("Report Water Waste");
		waitForPageToLoad();
		if (selectlstConnectMeOptions("Report Water Waste")) {
		}
		if (isServiceAccNoTxtVisible()) {
			populateServiceAccNo(AccNum);
			pause(500);
		}
		if (isCustomerNameTxtVisible()) {
			populateCustomerName(user);
			pause(500);
		}
		if (isEmailAddressTxtVisible()) {
			populateEmailAddress("test@test.com");
			pause(500);
		}
//	 	 	   if(isChooseFileBtnVisible()) {
//	 	 		   String validAttachmentFileName = "meter-reading.jpg";
//	 	 	        addAttachmentToChooseFile(FILE_UPLOAD_PATH + validAttachmentFileName);
//	 	 	      pause(500);
//	 	 	   }
		if (istxtSubjectTxtVisible()) {
			populateSubject("Testing");
			pause(500);
		}
		if (isCommentsTxtVisible()) {
			populateComments("Testing");
			pause(500);
		}
		btnClickNext();
	}

	public void verifyPreConnectMePreviewYourFormDetails(SoftAssert softAssert) {
		log.info("Test Case execution for - verifyPreviewYourFormDetails - is Initiated");
		String user = Configuration.toString("userName");
		// String validAttachmentFileName = "meter-reading.jpg";
		List<WebElement> previewYourFormColumn = listPreviewYourFormColumn();
		List<WebElement> previewYourFormValue = listPreviewYourFormValue();
		int counter = 0;
		for (WebElement columnLabelEle : previewYourFormColumn) {
			String key = columnLabelEle.getText().trim();
			String value = previewYourFormValue.get(counter).getText().trim();
			switch (key) {
			case "Topic":
				softAssert.assertEquals(value, "Billing", "Topic value not matched on preview your form.");
				pause(1000);
				break;
			case "Customer Name":
				assertEquals(value, user, "Customer name not matched on preview your form.");
				pause(1000);
				break;
			case "Email Address":
				softAssert.assertEquals(value, "test@test.com", "Email address not matched on preview your form");
				pause(1000);
				break;
			case "Subject":
				softAssert.assertEquals(value, "Testing", "Subject not matched on preview your form");
				pause(1000);
				break;
//	               case "Attachment":
//	                   softAssert.assertEquals(value, validAttachmentFileName, "Attachment name is not matched on preview your form");
//	                   pause(1000);
//	                   break;
			case "Comments (Optional)":
				softAssert.assertEquals(value, "Testing", "Comments not matched on preview your form page.");
				pause(1000);
				break;
			default:
			}
			counter++;
		}
	}

	public void verifyPreConnectMePreviewYourFormDetailsForWaterLeaks(SoftAssert softAssert) {
		log.info("Test Case execution for - verifyPreviewYourFormDetailsForWaterLeaks - is Initiated");
		String user = Configuration.toString("userName");
		// String validAttachmentFileName = "meter-reading.jpg";
		List<WebElement> previewYourFormColumn = listPreviewYourFormColumn();
		List<WebElement> previewYourFormValue = listPreviewYourFormValue();
		int counter = 0;
		for (WebElement columnLabelEle : previewYourFormColumn) {
			String key = columnLabelEle.getText().trim();
			String value = previewYourFormValue.get(counter).getText().trim();
			switch (key) {
			case "Topic":
				softAssert.assertEquals(value, "Report Water Waste", "Topic value not matched on preview your form.");
				pause(1000);
				break;
			case "Customer Name":
				assertEquals(value, user, "Customer name not matched on preview your form.");
				pause(1000);
				break;
			case "Email Address":
				softAssert.assertEquals(value, "test@test.com", "Email address not matched on preview your form");
				pause(1000);
				break;
			case "Subject":
				softAssert.assertEquals(value, "Testing", "Subject not matched on preview your form");
				pause(1000);
				break;
//	               case "Attachment":
//	                   softAssert.assertEquals(value, validAttachmentFileName, "Attachment name is not matched on preview your form");
//	                   pause(1000);
//	                   break;
			case "Comments":
				softAssert.assertEquals(value, "Testing", "Comments not matched on preview your form page.");
				pause(1000);
				break;
			default:
			}
			counter++;
		}
	}

	public String verifyPreConnectMeSubmitForm(SoftAssert softAssert) {
		isSubmitBtnVisible();
		btnClickSubmit();
		isLblPopupThankYouVisible();
		String popupContent = getlblPopupThankYou().trim();
		String referenceId = popupContent.substring(popupContent.indexOf(":") + 1).trim();
		clickContactUsPopupOk();
		pause(5000);
		ExtentLogger.logInfo(referenceId);
		return referenceId;
	}

	public void verifyPreLogChatBox(SoftAssert softAssert) throws SQLException {
		clickChatBox();
		isChatBoxHeaderVisible();
		softAssert.assertEquals(getChatBoxHeader(), loginTextProp.getPropValue("lblChatWindowHeading"),
				"Warning message do not match");
		softAssert.assertTrue(isScmLOgoChatBoxVisible(), "SCM Logo on Chat Box is not visible");
		isChatTextBoxVisible();
		String text = "Testing";
		enterDataInChatTextBox(text);
		clickSendBtn();

		clickEndChatBtn();
		clickConfirmEndChatBtn();

	}

	public void verifySignOutPage(SoftAssert softAssert) throws SQLException {
		clickImageProfileIco();
		clickSignOutLnk();
		Assert.assertTrue(isSignOutPage(signOutTextProp.getPropValue("signOutPageUrl"),
				signOutTextProp.getPropValue("signOutPageTitle")));
		waitForSignOutSuccessLbl();
		softAssert.assertEquals(getSignOutSuccessfullyLbl(), signOutTextProp.getPropValue("lblYouHaveSignedOut"),
				"You have signed out successfully message not matched.");
		clickSignInAgainBtn();

	}
	
	public boolean isPreLogPaymentLocationsPage(String url, String title) {
		Boolean isForgetPasswordPage = false;
		log.info("Checking that the current page is ForgetPassword Page");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isForgetPasswordPage = true;
		log.info("The current page is ForgetPassword Page {}: " + isForgetPasswordPage);
		return isForgetPasswordPage;
	}
	
	public void verifyPaymentLocations(SoftAssert softAssert) {
		clickPaymentLocationsLnk();
		pause(5000);
		Assert.assertTrue(isPreLogPaymentLocationsPage(preLoginPaymentLocationsProp.getPropValue("preLoginPageUrl"),
				(preLoginPaymentLocationsProp.getPropValue("preLoginPageTitle"))), "Page Title & URL does not Match");
		
	}
	
	public void populatePaymentFormStepOne(String accountNumber, String phoneNumber) {
		populateAccountNumber(accountNumber);
		populatePhoneNumber(phoneNumber);
		ExtentLogger.logInfo("account number and phone number populated");
	}
	
	public String payWithInvalidDetails() {
		populateAccountNumber("123456789012");
		populatePhoneNumber("1234567890");
		clickNextBtn();
		String errMsg = getToastMessage();
		ExtentLogger.logInfo("cannot pay with invalid details");
		return errMsg;
	}
	
	public String payWithBlankDetails() {
		clickNextBtn();
		String errMsg = getToastMessageWithoutWait();
		clickToastCloseBtn();
		ExtentLogger.logInfo("cannot pay with blank details");
		return errMsg;
	}
	
	public String payWithBlankAccountNumber() {
		populatePhoneNumber("1234567890");
		clickNextBtn();		
		String errMsg = getErrorMessage();
		clearPhoneNumberField();
		ExtentLogger.logInfo("cannot pay with blank account number");
		return errMsg;
	}
	
	public String payWithBlankPhoneNumber() {
		populateAccountNumber("123456789012");
		clickNextBtn();
		String errMsg = getErrorMessage();
		clearAccountNumberField();
		ExtentLogger.logInfo("cannot pay with blank phone number");
		return errMsg;
	}
	
	public void payTheApplicationWrongCreds(SoftAssert softAssert) {
		clickPaymentsLnk();
		// Verify login with blank creds
		softAssert.assertEquals(payWithBlankDetails(), preLoginPaymentProp.getPropValue("payWithBlankCredsErrMsg"),
				"Pay with Blank creds error message not matched.");
		// Verify login with blank account number
		softAssert.assertEquals(payWithBlankAccountNumber(),
				preLoginPaymentProp.getPropValue("payWithBlankAccountNumber"),
				"Blank username field validation not correct.");
		
		// Verify login with blank phone number
		softAssert.assertEquals(payWithBlankPhoneNumber(),
				preLoginPaymentProp.getPropValue("payWithBlankPhoneNumber"),
				"Blank username field validation not correct.");
		// Verify login with wrong username and password.
		softAssert.assertEquals(payWithInvalidDetails(), preLoginPaymentProp.getPropValue("payWithInvalidsCredsErrMsg"),
				"Wrong toast when paying using invalid creds.");
	}
	
	public boolean isPreLogWaysToSavePage(String url, String title) {
		Boolean isForgetPasswordPage = false;
		log.info("Checking that the current page is ForgetPassword Page");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isForgetPasswordPage = true;
		log.info("The current page is ForgetPassword Page {}: " + isForgetPasswordPage);
		return isForgetPasswordPage;
	}
	
	public void verifyWaysToSave(SoftAssert softAssert) {
		clickWaysToSaveLnk();
		pause(5000);
		Assert.assertTrue(isPreLogWaysToSavePage(preLoginWaysToSaveProp.getPropValue("preLoginPageUrl"),
				(preLoginWaysToSaveProp.getPropValue("preLoginPageTitle"))), "Page Title & URL does not Match");
		softAssert.assertEquals(getRebatesLabel(),
				preLoginWaysToSaveProp.getPropValue("rebatesLbl"),
				"Blank username field validation not correct.");
		softAssert.assertEquals(getProgramsLabel(),
				preLoginWaysToSaveProp.getPropValue("programsLbl"),
				"Blank username field validation not correct.");
		softAssert.assertEquals(getSavingsLabel(),
				preLoginWaysToSaveProp.getPropValue("savingLbl"),
				"Blank username field validation not correct.");
		softAssert.assertEquals(getEducationalLabel(),
				preLoginWaysToSaveProp.getPropValue("educationalLbl"),
				"Blank username field validation not correct.");
		
	}


	public void verifyLanguageSwitchFeature(SoftAssert softAssert) {
		Assert.assertTrue(isLanguageDropdownVisible(), "Language dropdown is not visible on the login page.");
		clickLanguageDropdown();
		clickSpanishLanguageOption();
		softAssert.assertEquals(getRegisterLinkLabel(), loginTextProp.getPropValue("txLblRegistrationEspanol"));
		clickLanguageDropdown();
		clickFrenchLanguageOption();
		softAssert.assertEquals(getRegisterLinkLabel(), loginTextProp.getPropValue("txLblRegistrationFrance"));
		clickLanguageDropdown();
		clickEnglishLanguageOption();
		softAssert.assertEquals(getRegisterLinkLabel(), loginTextProp.getPropValue("txLblRegistrationEnglish"));
	}

}
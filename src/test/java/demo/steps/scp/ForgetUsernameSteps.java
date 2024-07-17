package demo.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import sew.ai.config.Configuration;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import demo.pageobjects.ForgotUsernamePage;
import sew.ai.steps.scp.LoginSteps;
import sew.ai.utils.PropertiesUtil;

import static sew.ai.steps.scp.LoginSteps.loginTextProp;

public class ForgetUsernameSteps extends ForgotUsernamePage {
	private static final Logger log = LogManager.getLogger(ForgetUsernameSteps.class);

	public static PropertiesUtil ForgotUsernameTextProp;

	public ForgetUsernameSteps(WebDriver driver) {
		super(driver);
		ForgotUsernameTextProp = new PropertiesUtil(
				FilePaths.SCP_TEXT_PROPERTIES + Constants.FORGET_USERNAME_IN_TXT_FILENAME);
		loginTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.LOGIN_TXT_FILENAME);
	}

	public void verifyForgotUsernameInObject(SoftAssert softAssert) {
		LoginSteps loginSteps;
		loginSteps = new LoginSteps(driver);
		Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
				loginTextProp.getPropValue("loginPageTitle")));

		loginSteps.clickForgotUsernameLnk();
		softAssert.assertTrue(
				isForgotUsernameInPage(ForgotUsernameTextProp.getPropValue("ForgotUsernamePageUrl"),
						(ForgotUsernameTextProp.getPropValue("ForgotUsernamePageTitle"))),
				"Page Title & URL does not Match");
		softAssert.assertTrue(isEmailAddressTxtVisible(), "Email Address Text Box is not visibility");
		softAssert.assertTrue(isPagelabelHeaderVisible(), "Page Label Header is not visibility");
		softAssert.assertTrue(isLblPleaseEnterPrimaryEmailVisible(), "Please Enter Primary Email is not visibility");
		softAssert.assertTrue(isLblTxtBoxEmailAddressVisible(), "Txt Box Email Address is not visible");
		softAssert.assertTrue(isIcoEmailAddressInfoVisible(), "Ico Email Address Info is not visible");
		softAssert.assertTrue(isCancelBtnVisible(), "Cancel Button Info is not visible");
		softAssert.assertTrue(isSubmitBtnVisible(), "Submit Button Info is is not visible");
	}

	public boolean isForgotUsernameInPage(String url, String title) {
		Boolean isForgetPasswordPage = false;
		log.info("Checking that the current page is Forget Username Page");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isForgetPasswordPage = true;
		log.info("The current page is ForgotUsername Page {}: " + isForgetPasswordPage);
		return isForgetPasswordPage;
	}

	public void emailFeildVerification() {
		// Click Submit with blank email add
		clickSubmitBtn();
		Assert.assertEquals(getlblGenericErrorMessage(),
				ForgotUsernameTextProp.getPropValue("txtMsgBlankEmailAddress"));

		// Enter Invalid Email address
		populateEmailAddress("hdddd");
		clickSubmitBtn();
		Assert.assertEquals(getlblGenericErrorMessage(),
				ForgotUsernameTextProp.getPropValue("txtLblEnterInvalidEmailAddress"));
		clearEmailAddressField();

		// Enter Valid Email address
		populateEmailAddress("User@pwc.com");
		clickSubmitBtn();
		String SuccessToasterMsg = getToastMessage();
		ExtentLogger.logInfo("Link send to user  " + SuccessToasterMsg);

	}

	public void validEmailFeildVerification() {

		// Enter Valid Email address
		populateEmailAddress("User@pwc.com");
		clickSubmitBtn();
		String SuccessToasterMsg = getToastMessage();
		ExtentLogger.logInfo("Link send to user  " + SuccessToasterMsg);
		Assert.assertEquals(getToastMessage(), ForgotUsernameTextProp.getPropValue("txtSuccessToasterMsg"));

	}

	public void cancelBttnVerification() {

		// Enter Valid Email address
		populateEmailAddress("User@pwc.com");
		clickCancelBtn();
		LoginSteps loginSteps;
		loginSteps = new LoginSteps(driver);
		Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
				loginTextProp.getPropValue("loginPageTitle")));
		ExtentLogger.logInfo("checkCancelFunctnUsername Passed");

	}

	public void homeIconVerification() {

		// Enter Valid Email address
		populateEmailAddress("User@pwc.com");
		clickHomeIcon();
		LoginSteps loginSteps;
		loginSteps = new LoginSteps(driver);
		Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
				loginTextProp.getPropValue("loginPageTitle")));
		ExtentLogger.logInfo("checkHomeIconFunctnUsername Passed");
	}

	public void populateLoginForm(String userName, String password) {
		populateUserName(userName);
		populatePassword(password);
		ExtentLogger.logInfo("username and password populated");
	}

	public String loginWithBlankUsername(String password) {
		clearUsernameField();
		populatePassword(password);
		clickSignInBtn();
		String errMsg = getCommonValidationMsg();
		ExtentLogger.logInfo("cannot login with blank username");
		return errMsg;
	}

	@FindBy(css = "[id='txtLogin']")
	private WebElement txt_username;

	public void populateUserName(String userName) {
		log.info("Populating username {} :" + userName);
		sendKeys(txt_username, userName);
		log.info("Username populated successfully.");
	}

	public void clearUsernameField() {
		clear(txt_username);
		log.info("Username field cleared {}");
	}

	@FindBy(css = "[id='txtpwd']")
	private WebElement txt_password;

	public void populatePassword(String password) {
		log.info("Populating password {} :" + password);
		sendKeys(txt_password, password);
		log.info("Password populated successfully.");
	}

	public void clearPasswordField() {
		clear(txt_password);
		log.info("Password field cleared {}");
	}

	@FindBy(css = "#btnlogin")
	private WebElement btn_sign_in;

	public void clickSignInBtn() {
		log.info("Clicking the sign in button.");
		click(btn_sign_in);
		log.info("Sign in button clicked successfully.");
	}

	public String loginWithWrongUsername(String userName, String password) {
		populateLoginForm(userName, password);
		clearUsernameField();
		populateUserName("invalid@username.com");
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

	public void loginWithInvalidCreds() {
		populateUserName("Invalid@Username");
		populatePassword("Invalid@Password");
		clickSignInBtn();
		/*
		 * String errMsg = getToastMessage();
		 * ExtentLogger.logInfo("cannot login with invalid creds"); return errMsg;
		 */
	}

//	public void loginIntoTheApplicationWrongCreds(SoftAssert softAssert) {
//		String username = null;
//		String Pass = null;
//		int totalAttempts = 6;
//		while(totalAttempts !=0) {
//			
//		if(username == invaliduser && Pass = invalidpas )
//		
//		{
//			
//			
//				Assert.assertEquals(getToastMessage(), loginTextProp.getPropValue("invalidCredentialsErrMsg"));
//
//			}
//			}
			
			//Assert.assertEquals(loginWithInvalidCreds(), loginTextProp.getPropValue("invalidCredentialsErrMsg"));
		
			
		//}
		/*
		 * // Attempt 1 to login with invalid username and password.
		 * Assert.assertEquals(loginWithInvalidCreds(),
		 * loginTextProp.getPropValue("invalidCredentialsErrMsg"));
		 * 
		 * // Attempt 2 to login with invalid username and password.
		 * Assert.assertEquals(loginWithInvalidCreds(),
		 * loginTextProp.getPropValue("invalidCredentialsErrMsg"));
		 * 
		 * // Attempt 3 to login with invalid username and password.
		 * Assert.assertEquals(loginWithInvalidCreds(),
		 * loginTextProp.getPropValue("invalidCredentialsErrMsg"));
		 * 
		 * // Attempt 4 to login with invalid username and password.
		 * Assert.assertEquals(loginWithInvalidCreds(),
		 * loginTextProp.getPropValue("invalidCredentialsErrMsg"));
		 * 
		 * // Attempt 5 to login with invalid username and password.
		 * Assert.assertEquals(loginWithInvalidCreds(),
		 * loginTextProp.getPropValue("invalidCredentialsErrMsg"));
		 * 
		 * // Attempt 6 to login with invalid username and password.
		 * Assert.assertEquals(loginWithInvalidCreds(),
		 * loginTextProp.getPropValue("acctIPLockedErrMsg"));
		 * 
		 * log.info("IP lock functionality tested successfully");
		 */

	

public String verifyAccountIPLockFunctionality(SoftAssert softAssert ) {
	 int attempt = 6;
       for (int i = 0; i < attempt; i++) {
           pause(500);
           populateLoginForm(Configuration.toString("userName"), "invalid@password");
           clickSignInBtn();
           softAssert.assertEquals(getToastMessage(), loginTextProp.getPropValue("acctIPLockedErrMsg"),
                   "Invalid credential error message not matched.");
       }
       pause(500);
       populateLoginForm(Configuration.toString("userName"), "invalid@password");
       clickSignInBtn();
       return getToastMessage();
  
}
}

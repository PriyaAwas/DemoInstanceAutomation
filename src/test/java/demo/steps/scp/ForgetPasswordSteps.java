package demo.steps.scp;
import static sew.ai.steps.scp.LoginSteps.loginTextProp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import sew.ai.config.Configuration;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import demo.pageobjects.ForgotPasswordPage;
import sew.ai.steps.scp.LoginSteps;
import sew.ai.utils.PropertiesUtil;

public class ForgetPasswordSteps extends ForgotPasswordPage {
	private static final Logger log = LogManager.getLogger(ForgetPasswordSteps.class);

	public static PropertiesUtil ForgotPasswordTextProp;

	public ForgetPasswordSteps(WebDriver driver) {
		super(driver);
		ForgotPasswordTextProp = new PropertiesUtil(
				FilePaths.SCP_TEXT_PROPERTIES + Constants.FORGET_PASSWORD_TXT_FILENAME);
	}

	public void verifyForgetPasswordObject(SoftAssert softAssert) {
		LoginSteps loginSteps;
		loginSteps = new LoginSteps(driver);
		  Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue(
		  "loginPageUrl"), loginTextProp.getPropValue("loginPageTitle")));
		 
		clickForgotPasswordLnk();
		softAssert.assertTrue(
				isForgetPasswordPage(ForgotPasswordTextProp.getPropValue("ForgetPasswordPageUrl"),
						(ForgotPasswordTextProp.getPropValue("ForgetPasswordPageTitle"))),
				"Page Title & URL does not Match");
		softAssert.assertTrue(isUserNameTxtVisible(), "Username Text Box is not visibility");
		softAssert.assertTrue(isPagelabelHeaderVisible(), "Forget Password Page header label is not visibility");
		softAssert.assertTrue(isSubmitBtnVisible(), "Submit Button is not visible");
		softAssert.assertTrue(isCancelBtnVisible(), "Cancel Button is not visible");
	}
	
	public LoginSteps verifyPreLoginUrlTitle(SoftAssert softAssert) {
		LoginSteps loginSteps;
		loginSteps = new LoginSteps(driver);
		loginSteps.verifyTheLoginPageObject(softAssert);
		return loginSteps;

	}

	public boolean isForgetPasswordPage(String url, String title) {
		Boolean isForgetPasswordPage = false;
		log.info("Checking that the current page is ForgetPassword Page");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isForgetPasswordPage = true;
		log.info("The current page is ForgetPassword Page {}: " + isForgetPasswordPage);
		return isForgetPasswordPage;
	}

	/**
	 * To verify the validation message if user left Mandatory field blank To verify
	 * the functionality of Cancel button.
	 */
	public void verifySubmitBlankUserName(SoftAssert softAssert) {
		clickForgotPasswordLnk();
		clickSubmitBtn();
		String errMsg = getlblErrorMsgEnterUsername();
		Assert.assertEquals(errMsg, ForgotPasswordTextProp.getPropValue("submitBlankCredsErrMsg"));
		ExtentLogger.logInfo("Black senerio is working fine");
		clickCancelBtn();
		LoginSteps loginSteps;
		loginSteps = new LoginSteps(driver);
		loginSteps.waitForUserNameFieldVisibility();
		Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
				loginTextProp.getPropValue("loginPageTitle")));
		ExtentLogger.logInfo("Bug exist");
	}
	/**
	 * To verify the validation for invalid and valid user name
	 */

	public void verifyInvalidUserName(SoftAssert softAssert) {
		clickForgotPasswordLnk();
		populateUserName(Configuration.toString("userName"));	
		pause(500);
		clickSubmitBtn();
		String SuccessToasterMsg = getToastMessage();
		ExtentLogger.logInfo("Email send with valid username" + SuccessToasterMsg);
		ExtentLogger.logInfo("Email send notification is working fine");
		clickForgotPasswordLnk();
		clickSubmitBtn();
		populateUserName("Invalid@Username....12");
		clickSubmitBtn();
		String errMsg = getToastMessage();
		//Assert.assertEquals(errMsg, ForgotPasswordTextProp.getPropValue("InvalidUserNameToastMsg"));
		LoginSteps loginSteps;
		loginSteps = new LoginSteps(driver);
		loginSteps.waitForUserNameFieldVisibility();
		Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
				loginTextProp.getPropValue("loginPageTitle")));
			}

	

}

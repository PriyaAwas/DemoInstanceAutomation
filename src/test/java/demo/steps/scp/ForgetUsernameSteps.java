package demo.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
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
		Assert.assertEquals(getlblGenericErrorMessage(), ForgotUsernameTextProp.getPropValue("txtMsgBlankEmailAddress"));
		
		// Enter Invalid Email address
		populateEmailAddress("hdddd");
		clickSubmitBtn();
		 Assert.assertEquals(getlblGenericErrorMessage(),ForgotUsernameTextProp.getPropValue("txtLblEnterInvalidEmailAddress"));
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
		 Assert.assertEquals(getToastMessage(),ForgotUsernameTextProp.getPropValue("txtSuccessToasterMsg"));


	}
	
}

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
import sew.ai.steps.scp.LoginSteps;
import demo.pageobjects.ProblemSignInPage;
import sew.ai.utils.PropertiesUtil;

public class ProblemSigningInSteps extends ProblemSignInPage {
	private static final Logger log = LogManager.getLogger(ProblemSigningInSteps.class);

	public static PropertiesUtil ProblemSigningInTextProp;

	public ProblemSigningInSteps(WebDriver driver) {
		super(driver);
		ProblemSigningInTextProp = new PropertiesUtil(
				FilePaths.SCP_TEXT_PROPERTIES + Constants.PROBLEM_SIGNING_IN_TXT_FILENAME);
	}

	/**
	 * To verify that upon clicking on Problem Signing In link user is redirected to
	 * Problem Signing In page
	 **/
	public void verifyProblemSigningInObject(SoftAssert softAssert) {
		LoginSteps loginSteps;
		loginSteps = new LoginSteps(driver);
		Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
				loginTextProp.getPropValue("loginPageTitle")));
		loginSteps.clickProblemSignInLnk();
		softAssert.assertTrue(
				isProblemSigningInPage(ProblemSigningInTextProp.getPropValue("ProblemSignInPageUrl"),
						(ProblemSigningInTextProp.getPropValue("ProblemSignInPageTitle"))),
				"Page Title & URL does not Match");
		softAssert.assertTrue(istxtBoxAccountNumberVisible(), "Account Number Text Box is not visibility");
		softAssert.assertTrue(istxtBoxEmailAddressVisible(), "Email Address Text Box is not visibility");
		softAssert.assertTrue(istxtBoxCommentsVisible(), "Comments Text Box is not visibility");
		softAssert.assertTrue(isCancelBtnVisible(), "Cancel Button is not visible");
		softAssert.assertTrue(isSubmitBtnVisible(), "Next Button is not visible");
	}

	public void verifyProblemSigningInFormwithInValidData() {
		LoginSteps loginSteps;
		loginSteps = new LoginSteps(driver);
		String user = Configuration.toString("scmAccountNumber");
		// Verify the message displayed for blank Email address field.
		enterAccountNumberTxtBox("P00000200322");
		enterCommentTxtBoxTxtBox("Test Comments");
		isclickSubmitBtn();
		String errMsgEmail = getlblGenericErrorMessage();
    	 Assert.assertEquals(errMsgEmail , ProblemSigningInTextProp.getPropValue("txtMsgBlankEmailAddress"));

		// Verify the message displayed for blank Account number field.
		cleartxtBoxAccountNumber();
		enterEmailAddressTxtBox("testemail@sus.com");
		enterCommentTxtBoxTxtBox("Test Comments");
		isclickSubmitBtn();
		String errMsgAccountnumber = getlblGenericErrorMessage();
		Assert.assertEquals(errMsgAccountnumber, ProblemSigningInTextProp.getPropValue("txtEnterValidAccountNo"));

		// Verify the message displayed for blank Comments.
		cleartxtBoxComment();
		enterAccountNumberTxtBox("P00000200322");
		enterEmailAddressTxtBox("testemail@sus.com");
		isclickSubmitBtn();
		String errMsgComments = getlblGenericErrorMessage();
		Assert.assertEquals(errMsgComments, ProblemSigningInTextProp.getPropValue("txtMsgBlankComments"));

		// To verify that application will not allow user to submit request with INVALID
		// Account Number
		enterAccountNumberTxtBox("Z002002003");
		enterEmailAddressTxtBox("testemail@sus.com");
		enterCommentTxtBoxTxtBox("Test Comments");
		isclickSubmitBtn();
		String toasterMsgInvalidAccountNo = getToastMessage();
		Assert.assertEquals(toasterMsgInvalidAccountNo ,ProblemSigningInTextProp.getPropValue("txtLblErrorMsgInvalidAccountNumber"));

		// To verify that the 'Home' icon is displaying next to language drop-down on
		// the top right corner of the Problem Sign In page.
		isicoHomeVisible();
		String iconHomeTitle = geticoHomeTitle();
		Assert.assertEquals(iconHomeTitle, ProblemSigningInTextProp.getPropValue("txtTitleHomeIcon"));
		clickIconHome();
		loginSteps.waitForUserNameFieldVisibility();
		Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
				loginTextProp.getPropValue("loginPageTitle")));
		// Submit blank form
		clickProblemSignInLnk();
		isclickSubmitBtn();
		String toasterMsg = getToastMessage();
		Assert.assertEquals(toasterMsg, ProblemSigningInTextProp.getPropValue("txtLblMandatoryErrorMessage"));
		// waitForCancelBtnclickable();
		// isclickCancelBtn();
		// Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
		// loginTextProp.getPropValue("loginPageTitle")));
		// ExtentLogger.logInfo("Bug Exist - It is not navigating to login page");
	}

	public boolean isProblemSigningInPage(String url, String title) {
		Boolean isForgetPasswordPage = false;
		log.info("Checking that the current page is ForgetPassword Page");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isForgetPasswordPage = true;
		log.info("The current page is ForgetPassword Page {}: " + isForgetPasswordPage);
		return isForgetPasswordPage;
	}

	public void verifyProblemSigningInFormwithValidData() {
		String email = Configuration.toString("scmEmailAdd");

		String sComments = "Not able to login with my credentials.";
		String pp = Configuration.toString("userName");
		String accno = Configuration.toString("scmAccountNumber");
		// Verify the message displayed for blank Email address field.
		enterAccountNumberTxtBox(accno);
		enterCommentTxtBoxTxtBox(sComments);
		isclickSubmitBtn();
		String errMsgEmail = getlblGenericErrorMessage();
		Assert.assertEquals(errMsgEmail, ProblemSigningInTextProp.getPropValue("txtMsgBlankEmailAddress"));
		
		// Verify the message displayed for blank Account number field.
		cleartxtBoxAccountNumber();
		enterEmailAddressTxtBox(email);
		enterCommentTxtBoxTxtBox(sComments);
		isclickSubmitBtn();
		String errMsgAccountnumber = getlblGenericErrorMessage();
		Assert.assertEquals(errMsgAccountnumber, ProblemSigningInTextProp.getPropValue("txtEnterValidAccountNo"));
		
		// Verify the message displayed for blank Comments.
		cleartxtBoxComment();
		enterAccountNumberTxtBox(accno);
		enterEmailAddressTxtBox(email);
		isclickSubmitBtn();
		String errMsgComments = getlblGenericErrorMessage();
		Assert.assertEquals(errMsgComments, ProblemSigningInTextProp.getPropValue("txtMsgBlankComments"));
		
		// To verify that after providing all valid details user is able to submit request.
		enterAccountNumberTxtBox(accno);
		enterEmailAddressTxtBox(email);
		enterCommentTxtBoxTxtBox("Test Comments");
		isclickSubmitBtn();
		String toasterSuccessMsg = getToastMessage();
		Assert.assertEquals(toasterSuccessMsg, ProblemSigningInTextProp.getPropValue("txtSuccessToasterMsg"));
		pause(1000);
		LoginSteps loginSteps;
		loginSteps = new LoginSteps(driver);
		loginSteps.waitForUserNameFieldVisibility();
		Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
				loginTextProp.getPropValue("loginPageTitle")));
	}

	public void verifyMinMaxValueOfTextBox(SoftAssert softAssert) {
		String user = Configuration.toString("scmAccountNumber");
		String email = Configuration.toString("scmEmailAdd");
		String accountNumberMaxLength = getTxtBoxAccountNumberMaxLength();
		String accountNumberMinLength = getTxtBoxAccountNumberMinLength();
		String emailAdressMaxLength = getTxtBoxEmailAddressMaxLength();
		softAssert.assertEquals(accountNumberMaxLength, "12", "Max length username field not matched.");
		softAssert.assertEquals(accountNumberMinLength, "5", "Max length password field not matched.");
		softAssert.assertEquals(emailAdressMaxLength, "50", "Max length password field not matched.");
		// Enter Minimum length Account Number
		enterAccountNumberTxtBox("Z0");
		enterEmailAddressTxtBox(email);
		enterCommentTxtBoxTxtBox("Test Comments");
		isclickSubmitBtn();
		String enterValidAccountNo = getlblGenericErrorMessage();
		Assert.assertEquals(enterValidAccountNo, ProblemSigningInTextProp.getPropValue("txtEnterValidAccountNo"));

		// Verify Invalid Error Message for Account Number
		enterAccountNumberTxtBox("0000000000");
		enterEmailAddressTxtBox(email);
		enterCommentTxtBoxTxtBox("Test Comments");
		isclickSubmitBtn();
		String toasterSuccessMsg = getlblGenericErrorMessage();
		Assert.assertEquals(toasterSuccessMsg, ProblemSigningInTextProp.getPropValue("txtEnterValidAccountNo"));

		// Verify valid Error Message for Email
		enterAccountNumberTxtBox(user);
		enterEmailAddressTxtBox("Test");
		enterCommentTxtBoxTxtBox("Test Comments");
		isclickSubmitBtn();
		String InvalidEmailMsg = getlblGenericErrorMessage();
		Assert.assertEquals(InvalidEmailMsg, ProblemSigningInTextProp.getPropValue("txtInvalidEmail"));
		ExtentLogger.logInfo("verifyMinMaxValueOfTextBox Passed");
	}

}

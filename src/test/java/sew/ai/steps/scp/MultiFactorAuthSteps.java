package sew.ai.steps.scp;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static sew.ai.steps.scp.LoginSteps.loginTextProp;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import de.redsix.pdfcompare.ui.Display;
import sew.ai.api.endpoints.auth.RegistrationEndpoints;
import sew.ai.config.SCPConfiguration;
import sew.ai.enums.RegistrationPreference;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.ResourcePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.Customer;
import sew.ai.models.User;
import sew.ai.pageObjects.scp.MultiFactorAuthPage;
import sew.ai.pageObjects.scp.PostLogConnectMePage;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.MFAUtil;
import sew.ai.utils.PropertiesUtil;

public class MultiFactorAuthSteps extends MultiFactorAuthPage {
	private static final Logger log = LogManager.getLogger(MultiFactorAuthSteps.class);
	public static PropertiesUtil multiFactorAuthTextProp;
	private DashboardSteps dashboardSteps;

	public MultiFactorAuthSteps(WebDriver driver) {
		super(driver);
		multiFactorAuthTextProp = new PropertiesUtil(
				FilePaths.SCP_TEXT_PROPERTIES + Constants.MULTI_FACTOR_AUTH_TEXT_FILENAME);

	}

	String username = "";
	String password = "";
	String pEmail = "";
	String pMobile = "";
	// String addressType = "Residential";
	String addressType = "Commercial";

	public void verifyMFAUIAndValidations(SoftAssert softAssert) throws Exception {
		// Registration using API
		RegistrationEndpoints registrationEndpoints = new RegistrationEndpoints();
		Customer customer = SCPConfiguration.initCustomerConfig("John Doe", "1");
		registrationEndpoints.buildRequestSetRegistrationAdd(ResourcePaths.SCP_COMMON_PATH_URI, customer);
		registrationEndpoints.hitSetRegistration();

		// Valid Login
		LoginSteps loginSteps;
		loginSteps = new LoginSteps(driver);
		dashboardSteps = loginSteps.loginIntoTheApplication(username, password);
		Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
				loginTextProp.getPropValue("loginPageTitle")));
		Assert.assertTrue(
				isMultiFactorAuthPage(multiFactorAuthTextProp.getPropValue("expectedMFAPageUrl"),
						(multiFactorAuthTextProp.getPropValue("expectedMFAPageTitle"))),
				"Page Title & URL does not Match");
		softAssert.assertTrue(isPagelabelHeaderVisible(), "Page label Header is not visibility");
		softAssert.assertTrue(isWhereWouldYouSendLabelVisible(),
				"Where Would You Send Label Visible is not visibility");
		softAssert.assertTrue(isEmailRadioBtnVisible(), "Email Radio Btn Visible is not visibility");
		softAssert.assertTrue(isSendCodeToEmailLabelVisible(), "Send Code To Email Label is not visibility");
		softAssert.assertTrue(isRadioBtnTextVisible(), "Email Radio Btn is not visibility");
		softAssert.assertTrue(isCancelBtnVisible(), "Cancel Btn is not visibility");
		softAssert.assertTrue(isNextBtnVisible(), "Next Btn is not visibility");
		// Verify Cancel button functionality at MFA Screen1
		clickCancelBtn();
		Thread.sleep(2000);
		Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
				loginTextProp.getPropValue("loginPageTitle")));
		// Valid Login
		loginSteps = new LoginSteps(driver);
		dashboardSteps = loginSteps.loginIntoTheApplication(username, password);
		Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
				loginTextProp.getPropValue("loginPageTitle")));
		Assert.assertTrue(
				isMultiFactorAuthPage(multiFactorAuthTextProp.getPropValue("expectedMFAPageUrl"),
						(multiFactorAuthTextProp.getPropValue("expectedMFAPageTitle"))),
				"Page Title & URL does not Match");
		softAssert.assertTrue(isPagelabelHeaderVisible(), "Page label Header is not visibility");
		softAssert.assertTrue(isWhereWouldYouSendLabelVisible(),
				"Where Would You Send Label Visible is not visibility");
		softAssert.assertTrue(isEmailRadioBtnVisible(), "Email Radio Btn Visible is not visibility");
		softAssert.assertTrue(isSendCodeToEmailLabelVisible(), "Send Code To Email Label is not visibility");
		softAssert.assertTrue(isRadioBtnTextVisible(), "Email Radio Btn is not visibility");
		softAssert.assertTrue(isCancelBtnVisible(), "Cancel Btn is not visibility");
		softAssert.assertTrue(isNextBtnVisible(), "Next Btn is not visibility");
		clickEmailRadioBtn();
		clickRadioBtnTextBtn();
		isTextTermsConditionVisible();
		islnkTermsConditionVisible();
		String textTandC = gettxtTermsCondition();
		Assert.assertEquals(textTandC, multiFactorAuthTextProp.getPropValue("txtLnkTermsCondition"));
		clicklnkTermsCondition();
		isClickbtnTandCOK();
		clickbtnNext();
		Thread.sleep(2000);

		// Verify MFA 2nd screen
		String multiFatorAuth = getlblMultiFatorAuth();
		Assert.assertEquals(multiFatorAuth, multiFactorAuthTextProp.getPropValue("txtLblHeaderMfap"));
		String codeSentOnEmail = getlblCodeSentOnEmail();
		codeSentOnEmail.contains(multiFactorAuthTextProp.getPropValue("txtwejustSendCodeTo"));
		isOTPCancelBtnVisible();
		isSubmitBtnVisible();
		int otpFields = countWebElements(getInputOTPFields());
		Assert.assertEquals(otpFields, 6);
		isTimerVisible();
		islnkResendOTPCodeVisible();
		String resendAuth = gettxtResendOTPCode();
		Assert.assertEquals(resendAuth, multiFactorAuthTextProp.getPropValue("txtLnkResendOTPCodeMfap"));
		while (!(getlblOTPTimer()).equals("0")) {
			String value = gettxtResendOTPCodeClass();
			assertEquals(value, "isDisabled");
			Thread.sleep(12000);
		}
		String value = gettxtResendOTPCodeClass();
		assertEquals(value, "");
		System.out.println("<=====>" + value + "<======>");
		clicklnkResendOTPCode();
		String codeHasSend = gettxtResendOTPCode();
		Assert.assertEquals(codeHasSend, multiFactorAuthTextProp.getPropValue("txtLblCodeSentOnEmailMfap"));
		assertTrue(getlblOTPTimer() != "0");
		clickbtnSubmitOTP();
		String toasterMsg = getToastMessage();
		Assert.assertEquals(toasterMsg, multiFactorAuthTextProp.getPropValue("txtBlankSubmitErrorMsgMfap"));
		// Verify incomplete and non-numeric OTP submission validation
		populateRequestIdBoxOTP("123abc");
		clickbtnSubmitOTP();
		String nonNumericToasterMsg = getToastMessage();
		Assert.assertEquals(nonNumericToasterMsg, multiFactorAuthTextProp.getPropValue("txtBlankSubmitErrorMsgMfap"));
		// Verify Cancel button functionality at MFA Screen2
		clickOTPCancelBtn();
		pause(1000);
		Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
				loginTextProp.getPropValue("loginPageTitle")));
	}

	public void verifyMFAwithEmailoRText(String email_OR_text) throws Exception {
		// Registration using API
		RegistrationEndpoints registrationEndpoints = new RegistrationEndpoints();
		Customer customer = SCPConfiguration.initCustomerConfig("John Doe", "1");
		registrationEndpoints.buildRequestSetRegistrationAdd(ResourcePaths.SCP_COMMON_PATH_URI, customer);
		registrationEndpoints.hitSetRegistration();
		String otp = "";

		// Valid Login
		LoginSteps loginSteps;
		loginSteps = new LoginSteps(driver);
		dashboardSteps = loginSteps.loginIntoTheApplication(username, password);
		Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
				loginTextProp.getPropValue("loginPageTitle")));
		Assert.assertTrue(
				isMultiFactorAuthPage(multiFactorAuthTextProp.getPropValue("expectedMFAPageUrl"),
						(multiFactorAuthTextProp.getPropValue("expectedMFAPageTitle"))),
				"Page Title & URL does not Match");

		String txt = getlblEmailRadioBtnTxtMfap();
		String[] arr = txt.split(" ");
		String maskedEmail = arr[arr.length - 1];
		String[] arr1 = maskedEmail.split("@");
		String[] emailDB = pEmail.split("@");

		String expEmail = "";
		if (emailDB[0].length() > 3) {
			char[] ch = emailDB[0].toCharArray();
			for (int i = 0; i < ch.length; i++) {
				if (i == 0 || i == ch.length - 1)
					expEmail = expEmail + ch[i];
				else {
					expEmail = expEmail + "*";
				}
			}
			System.out.println("Expected masked email:" + expEmail);
			assertTrue(arr1[0].equals(expEmail));
		} else {
			assertTrue(arr1[0].equals(emailDB[0]));
		}

		getlblEmailRadioBtnTxtMfap().contains(pEmail.substring(pEmail.length() - pEmail.indexOf("@") - 1));
		getlblSendTextToMob().contains("(XXX) XXX-");
		getlblSendTextToMob().contains(pMobile.substring(pMobile.length() - 4));
		isradioBtnEmailVisible();
		isradioBtntxtMfapVisible();
		isClickRadioBtntxt();

		if (email_OR_text.equals("email")) {
			clickbtnNext();
			Thread.sleep(10000);
			otp = MFAUtil.getMFAEmailOTP(pEmail);
			System.out.println("Email OTP: " + otp);

		} else if (email_OR_text.equals("text")) {
			clickRadioBtnTextBtn();
			ischeckBoxVisibleTC();
			islnkTermsConditionVisible();
			String textTandC = gettxtTermsCondition();
			Assert.assertEquals(textTandC, multiFactorAuthTextProp.getPropValue("txtLnkTermsCondition"));
			clicklnkTermsCondition();
			isClickbtnTandCOK();
			clickbtnNext();
			Thread.sleep(10000);
			otp = MFAUtil.getMFATextOTP(pMobile);
			System.out.println("Text OTP: " + otp);

		}
		// Verify MFA OTP is length is 6
		assertTrue(otp.length() == 6);
		// Verify MFA OTP is Number
		try {
			Integer.parseInt(otp);
		} catch (NumberFormatException e) {
			System.out.println("MFA OTP is not Number.");
		}

		// Verify MFA 2nd screen

		String multiFatorAuth = getlblMultiFatorAuth();
		Assert.assertEquals(multiFatorAuth, multiFactorAuthTextProp.getPropValue("txtLblHeaderMfap"));
		String codeSentOnEmail = getlblCodeSentOnEmail();
		codeSentOnEmail.contains(multiFactorAuthTextProp.getPropValue("txtwejustSendCodeTo"));
		isOTPCancelBtnVisible();
		isSubmitBtnVisible();

		if (email_OR_text.equals("email")) {
			getlblCodeSentOnEmail().contains(pEmail.substring(pEmail.length() - pEmail.indexOf("@") - 1));
			if (expEmail.equals("")) {
				getlblCodeSentOnEmail().contains(expEmail);
			} else {
				getlblCodeSentOnEmail().contains(emailDB[0]);
			}

		} else if (email_OR_text.equals("text")) {
			String st = "(XXX) XXX-";
			getlblCodeSentOnEmail().contains(st);
			getlblCodeSentOnEmail().contains(pMobile.substring(pMobile.length() - 4));
		}

		int otpFields = countWebElements(getInputOTPFields());
		Assert.assertEquals(otpFields, 6);
		isTimerVisible();
		islnkResendOTPCodeVisible();
		String resendAuth = gettxtResendOTPCode();
		Assert.assertEquals(resendAuth, multiFactorAuthTextProp.getPropValue("txtLnkResendOTPCodeMfap"));

		// Verify MFA token status
		Map<String, String> getTwo_FactorTableData = getTwo_FactorTableData(username);
		String token = getTwo_FactorTableData.get("token");
		String client = getTwo_FactorTableData.get("client");
		String token_status = getTwo_FactorTableData.get("token_status");
		String unique_key = getTwo_FactorTableData.get("unique_key");

		// when OTP code is only generated the MFA Token Status should be 1
		assertTrue(token_status.equals("1"));
		assertTrue(client.equals("1"));
		assertTrue(token.equals(otp));
		assertTrue(unique_key.contains("Chrome"));
		assertTrue(unique_key.contains("Windows"));
		assertTrue(unique_key.contains(String.valueOf(getUserIdDB(username))));

		populateRequestIdBoxOTP(otp);
		clickbtnSubmitOTP();

		// After submission of MFA OTP its status should be 2
		String tokenStatus = getMFATokenStatus(username);
		assertTrue(tokenStatus.equals("2"));
		pause(20000);

		if (isWelcomePopupHeadinVisible()) {
			ClickbtnCloseAboutMyHome();
			pause(2000);
		}
		Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
				loginTextProp.getPropValue("loginPageTitle")));
		// Welcome screens appears need to validate and close the same
//		pageUtil.click(dashboardPage.getBtnCancelWelcomePopUp());
//		pageUtil.waitForPageToLoad();
//
//		pageUtil.click(dashboardPage.getImgProfileIcon());
//		pageUtil.explicitWaitForWebElement(driver, dashboardPage.getLnkSignOut());
//		pageUtil.pause(1000);
//		pageUtil.click(dashboardPage.getLnkSignOut());
//		pageUtil.explicitWaitForWebElement(driver, signOutPage.getLblYouHaveSignedOut());
//		pageUtil.verifyCurrentPageTitle(Utils.getRbTextValue("expectedSignOutPageTitle"));
//		pageUtil.assertCurrentPageUrl(Utils.getRbTextValue("expectedSignOutPageUrl"));
//		pageUtil.assertObjectLabel(signOutPage.getLblYouHaveSignedOut(),
//				Utils.getRbTextValue("txtLblYouHaveSignedOutSop"));
//		pageUtil.click(signOutPage.getLnkSignInAgain());
//
//		pageUtil.waitForPageToLoad();
//		// Valid Login
//		pageUtil.enterTextValue(loginPage.getTxtBoxUserName(), username);
//		pageUtil.enterTextValue(loginPage.getTxtBoxUserPwd(), password);
//		// pageUtil.click(loginPage.getBtnSignIn());
//		JsExecutorUtil.click(driver, loginPage.getBtnSignIn());
//		pageUtil.pause(200);
//
//		pageUtil.waitForPageToLoad();
//
//		pageUtil.verifyCurrentPageTitle(Utils.getRbTextValue("expectedDashboardPageTitle"));
//		pageUtil.assertCurrentPageUrl(Utils.getRbTextValue("expectedDashboardPageUrl"));
//
//		logger.info("Test Case execution for - verifyMFAwithEmailORText - is Completed.");
//		Runner.test.log(Status.PASS, "Verify Multi Factor Authentication with Email or Text message");

	}

	public void verifyContactMethodsOnMFAScreen(SoftAssert softAssert) throws Exception {
		// Registration using API
		RegistrationEndpoints registrationEndpoints = new RegistrationEndpoints();
		Customer customer = SCPConfiguration.initCustomerConfig("John Doe", "1");
		registrationEndpoints.buildRequestSetRegistrationAdd(ResourcePaths.SCP_COMMON_PATH_URI, customer);
		registrationEndpoints.hitSetRegistration();
		String otp = "";
		LoginSteps loginSteps;
		loginSteps = new LoginSteps(driver);
		dashboardSteps = new DashboardSteps(driver);
		dashboardSteps = loginSteps.loginIntoTheApplication(username, password);
		pause(200);
		loginSteps = new LoginSteps(driver);
		Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
				loginTextProp.getPropValue("loginPageTitle")));
		Assert.assertTrue(
				isMultiFactorAuthPage(multiFactorAuthTextProp.getPropValue("expectedMFAPageUrl"),
						(multiFactorAuthTextProp.getPropValue("expectedMFAPageTitle"))),
				"Page Title & URL does not Match");
		try {
			// Update Email Of User and make it blank in DB and Set Contact Type 2(Mobile)
			DataBaseUtils.executeUpdateDeleteSQLQuery(SQLQueries.getQueryUpdateUserPrimaryEmailAddress("", username));
			DataBaseUtils.executeUpdateDeleteSQLQuery(SQLQueries.getQueryUpdateUserPrimaryContactType("2", username));
			// Valid Login
			dashboardSteps = loginSteps.loginIntoTheApplication(username, password);
			pause(200);
			loginSteps = new LoginSteps(driver);
			Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
					loginTextProp.getPropValue("loginPageTitle")));
			Assert.assertTrue(
					isMultiFactorAuthPage(multiFactorAuthTextProp.getPropValue("expectedMFAPageUrl"),
							(multiFactorAuthTextProp.getPropValue("expectedMFAPageTitle"))),
					"Page Title & URL does not Match");
			// Verify Cancel button functionality at MFA Screen1
			clickCancelBtn();
			Thread.sleep(2000);
			Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
					loginTextProp.getPropValue("loginPageTitle")));
			// Update Contact Type and set it to Landline(1)
			DataBaseUtils.executeUpdateDeleteSQLQuery(SQLQueries.getQueryUpdateUserPrimaryContactType("1", username));
			// Valid Login
			dashboardSteps = loginSteps.loginIntoTheApplication(username, password);
			pause(200);
			loginSteps = new LoginSteps(driver);
			Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
					loginTextProp.getPropValue("loginPageTitle")));
			Assert.assertTrue(
					isMultiFactorAuthPage(multiFactorAuthTextProp.getPropValue("expectedMFAPageUrl"),
							(multiFactorAuthTextProp.getPropValue("expectedMFAPageTitle"))),
					"Page Title & URL does not Match");
			softAssert.assertTrue(isPagelabelHeaderVisible(), "Page label Header is not visibility");
			softAssert.assertTrue(isWhereWouldYouSendLabelVisible(),
					"Where Would You Send Label Visible is not visibility");
			softAssert.assertTrue(isEmailRadioBtnVisible(), "Email Radio Btn Visible is not visibility");
			softAssert.assertTrue(isSendCodeToEmailLabelVisible(), "Send Code To Email Label is not visibility");
			softAssert.assertTrue(isRadioBtnTextVisible(), "Email Radio Btn is not visibility");
			softAssert.assertTrue(isCancelBtnVisible(), "Cancel Btn is not visibility");
			softAssert.assertTrue(isNextBtnVisible(), "Next Btn is not visibility");

			// Click on Cancel button
			clickCancelBtn();
			Thread.sleep(2000);
			Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
					loginTextProp.getPropValue("loginPageTitle")));

			// Update Contact Type and set it to Mobile(2) and Delete Contact Number DB(Set
			// = "")
			DataBaseUtils.executeUpdateDeleteSQLQuery(SQLQueries.getQueryUpdateUserPrimaryContactNumber("", username));
			DataBaseUtils.executeUpdateDeleteSQLQuery(SQLQueries.getQueryUpdateUserPrimaryContactType("2", username));
			dashboardSteps = loginSteps.loginIntoTheApplication(username, password);
			pause(200);
			softAssert.assertTrue(
					isMultiFactorAuthPage(multiFactorAuthTextProp.getPropValue("expectedMFAPageUrl"),
							(multiFactorAuthTextProp.getPropValue("expectedMFAPageTitle"))),
					"Page Title & URL does not Match");
//			assertTrue(pageUtil.isElementNotDisplayed(multiFactorAuthenticationPage.getLblWherLikeToSendMfap()));
//			pageUtil.verifyTextValueInTextBox(multiFactorAuthenticationPage.getBtnCancelMfap(), Utils.getRbTextValue("txtBtnCancelMfap"));
//			assertTrue(pageUtil.isElementNotDisplayed(multiFactorAuthenticationPage.getRadioBtnEmailMfap()));
//			assertTrue(pageUtil.isElementNotDisplayed(multiFactorAuthenticationPage.getRadioBtnTextMfap()));
//			assertTrue(pageUtil.isElementNotDisplayed(multiFactorAuthenticationPage.getLblEmailRadioBtnTxtMfap()));
//			assertTrue(pageUtil.isElementNotDisplayed(multiFactorAuthenticationPage.getLblRadioBtnTextMfap()));			
//			assertTrue(pageUtil.isElementNotDisplayed(multiFactorAuthenticationPage.getBtnNextMfap()));			
//			pageUtil.verifyObjectLabel(multiFactorAuthenticationPage.getLblNoEmailMobileMessageMfap(), Utils.getRbTextValue("txtLblNoEmailMobileMessageMfap"));	

			// Click on Cancel button
			clickCancelBtn();
			Thread.sleep(2000);
			Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
					loginTextProp.getPropValue("loginPageTitle")));

			// Set Primary EmailID in DB and Make sure Contact Type is 2(Mobile) and Contact
			// number is Blank
			DataBaseUtils.executeUpdateDeleteSQLQuery(SQLQueries.getQueryUpdateUserPrimaryEmailAddress(pEmail, username));
			DataBaseUtils.executeUpdateDeleteSQLQuery(SQLQueries.getQueryUpdateUserPrimaryContactNumber("", username));
			DataBaseUtils.executeUpdateDeleteSQLQuery(SQLQueries.getQueryUpdateUserPrimaryContactType("2", username));
			dashboardSteps = loginSteps.loginIntoTheApplication(username, password);
			pause(200);
			softAssert.assertTrue(isPagelabelHeaderVisible(), "Page label Header is not visibility");
			softAssert.assertTrue(isWhereWouldYouSendLabelVisible(),
					"Where Would You Send Label Visible is not visibility");
			Thread.sleep(2000);
			softAssert.assertTrue(isEmailRadioBtnVisible(), "Email Radio Btn Visible is not visibility");
			softAssert.assertTrue(isSendCodeToEmailLabelVisible(), "Send Code To Email Label is not visibility");
			softAssert.assertTrue(isRadioBtnTextVisible(), "Email Radio Btn is not visibility");
			softAssert.assertTrue(isCancelBtnVisible(), "Cancel Btn is not visibility");
			softAssert.assertTrue(isNextBtnVisible(), "Next Btn is not visibility");
			log.info("Test Case execution for - verifyContactMethodOnMFAScreen - is Completed.");
		} finally {
			DataBaseUtils.executeUpdateDeleteSQLQuery(SQLQueries.getQueryUpdateUserPrimaryEmailAddress(pEmail, username));
			DataBaseUtils.executeUpdateDeleteSQLQuery(SQLQueries.getQueryUpdateUserPrimaryContactNumber(pMobile, username));
			DataBaseUtils.executeUpdateDeleteSQLQuery(SQLQueries.getQueryUpdateUserPrimaryContactType("2", username));
		}
	}

	public void verifyMFAExpirationFeatures(SoftAssert softAssert) throws Exception {
		// Registration using API
		RegistrationEndpoints registrationEndpoints = new RegistrationEndpoints();
		Customer customer = SCPConfiguration.initCustomerConfig("John Doe", "1");
		registrationEndpoints.buildRequestSetRegistrationAdd(ResourcePaths.SCP_COMMON_PATH_URI, customer);
		registrationEndpoints.hitSetRegistration();

		LoginSteps loginSteps;
		loginSteps = new LoginSteps(driver);
		dashboardSteps = new DashboardSteps(driver);
		dashboardSteps = loginSteps.loginIntoTheApplication(username, password);
		pause(200);
		loginSteps = new LoginSteps(driver);
		Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
				loginTextProp.getPropValue("loginPageTitle")));
		Assert.assertTrue(
				isMultiFactorAuthPage(multiFactorAuthTextProp.getPropValue("expectedMFAPageUrl"),
						(multiFactorAuthTextProp.getPropValue("expectedMFAPageTitle"))),
				"Page Title & URL does not Match");
		softAssert.assertTrue(isPagelabelHeaderVisible(), "Page label Header is not visibility");
		softAssert.assertTrue(isWhereWouldYouSendLabelVisible(),
				"Where Would You Send Label Visible is not visibility");
		softAssert.assertTrue(isEmailRadioBtnVisible(), "Email Radio Btn Visible is not visibility");
		softAssert.assertTrue(isSendCodeToEmailLabelVisible(), "Send Code To Email Label is not visibility");
		softAssert.assertTrue(isRadioBtnTextVisible(), "Email Radio Btn is not visibility");
		softAssert.assertTrue(isCancelBtnVisible(), "Cancel Btn is not visibility");
		softAssert.assertTrue(isNextBtnVisible(), "Next Btn is not visibility");

		clickRadioBtnTextBtn();
		isTextTermsConditionVisible();
		islnkTermsConditionVisible();
		String textTandC = gettxtTermsCondition();
		Assert.assertEquals(textTandC, multiFactorAuthTextProp.getPropValue("txtLnkTermsCondition"));
		clicklnkTermsCondition();
		isClickbtnTandCOK();
		clickbtnNext();

		// Verify MFA 2nd screen
		pause(1000);
		String startTime = getlblOTPTimer();
		System.out.println("startTime: " + startTime);
		// pageUtil.verifyObjectLabelEquals("60", startTime);
		getlblOTPTimer().equals("60");

		String lblHeaderMFA = getlblMultiFatorAuth();
		Assert.assertEquals(lblHeaderMFA, multiFactorAuthTextProp.getPropValue("txtLblHeaderMfap"));
		String codeSentOnEmail = getlblCodeSentOnEmail();
		codeSentOnEmail.contains(multiFactorAuthTextProp.getPropValue("txtwejustSendCodeTo"));
		isOTPCancelBtnVisible();
		isSubmitBtnVisible();
		int otpFields = countWebElements(getInputOTPFields());
		Assert.assertEquals(otpFields, 6);
//				String otp = MFAUtil.getMFAEmailOTP(pEmail);
//				System.out.println("Email OTP: "+otp);

		String otp = MFAUtil.getMFATextOTP(pMobile);
		System.out.println("Text OTP: " + otp);

		while (!(getlblOTPTimer()).equals("0")) {
			String value = gettxtResendOTPCodeClass();
			assertEquals(value, "isDisabled");
			Thread.sleep(12000);
		}

		String value = gettxtResendOTPCodeClass();
		assertEquals(value, "");
		System.out.println("<=====>" + value + "<======>");
		populateRequestIdBoxOTP(otp);
		String toasterMsg = getToastMessage();
		Assert.assertEquals(toasterMsg, multiFactorAuthTextProp.getPropValue("txtExpiredOTPMsgMfap"));

		Map<String, String> mfaData = getTwo_FactorTableData(username);
		String mfaID = mfaData.get("two_factor_id");
		String mfaUserID = mfaData.get("user_id");
		String mfaOTP = mfaData.get("token");
		String mfaPlatform = mfaData.get("client");
		String mfaOTPStatus = mfaData.get("token_status");
		String mfaOTPCreatedDate = mfaData.get("date_created");
		String mfaOTPUpdatedDate = mfaData.get("date_updated");
		String mfaUniqueKey = mfaData.get("unique_key");
		String mfaWrongOTPAttempt = mfaData.get("tfa_attempt");
		assertTrue(mfaUserID.equals(Integer.toString(getUserIdDB(username))));
		assertTrue(mfaPlatform.equals("1"));
		assertTrue(mfaOTP.equals(otp));
		assertTrue(mfaOTPStatus.equals("1"));
		assertTrue(mfaUniqueKey.contains(mfaUserID + "||"));
		assertTrue(mfaUniqueKey.contains("||Chrome||"));
		assertTrue(mfaUniqueKey.contains("||Windows"));

		populateRequestIdBoxOTP("222222");
		String incorrectToasterMsg = getToastMessage();
		Assert.assertEquals(incorrectToasterMsg, multiFactorAuthTextProp.getPropValue("txtIncorrectOTPMsgMfap"));
		waitForToastMessageInvisibility();

		assertTrue(getTwo_FactorTableData(username).get("tfa_attempt").equals("1"));
		populateRequestIdBoxOTP("222222");
		String incorectToasterMsg = getToastMessage();
		Assert.assertEquals(incorectToasterMsg, multiFactorAuthTextProp.getPropValue("txtIncorrectOTPMsgMfap"));

		assertTrue(getTwo_FactorTableData(username).get("tfa_attempt").equals("2"));
		clicklnkResendOTPCode();
		Thread.sleep(10000);

		// New MFA Data
		mfaData = getTwo_FactorTableData(username);
		assertFalse(mfaData.get("two_factor_id").equals(mfaID));
		assertFalse(mfaData.get("token").equals(mfaOTP));
		assertTrue(mfaData.get("user_id").equals(mfaUserID));
		assertTrue(mfaData.get("client").equals("1"));
		assertTrue(mfaData.get("token_status").equals("1"));
		assertTrue(mfaData.get("token_status").equals("1"));

		// OLD MFA Data
		mfaData = getTwo_FactorTableData(username, mfaID);
		assertTrue(mfaData.get("token_status").equals("3"));
		Thread.sleep(2000);
		Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
				loginTextProp.getPropValue("loginPageTitle")));
	}

	public void verifyUserNotLockedForMaxWrongAttemptsMFA(SoftAssert softAssert) throws Exception {
		// Registration using API
		RegistrationEndpoints registrationEndpoints = new RegistrationEndpoints();
		Customer customer = SCPConfiguration.initCustomerConfig("John Doe", "1");
		registrationEndpoints.buildRequestSetRegistrationAdd(ResourcePaths.SCP_COMMON_PATH_URI, customer);
		registrationEndpoints.hitSetRegistration();

		LoginSteps loginSteps;
		loginSteps = new LoginSteps(driver);
		dashboardSteps = new DashboardSteps(driver);
		dashboardSteps = loginSteps.loginIntoTheApplication(username, password);
		pause(200);
		loginSteps = new LoginSteps(driver);
		Assert.assertTrue(isMultiFactorAuthPage(multiFactorAuthTextProp.getPropValue("expectedMFAPageUrl"),(multiFactorAuthTextProp.getPropValue("expectedMFAPageTitle"))),"Page Title & URL does not Match");
		softAssert.assertTrue(isPagelabelHeaderVisible(), "Page label Header is not visibility");
		softAssert.assertTrue(isWhereWouldYouSendLabelVisible(),"Where Would You Send Label Visible is not visibility");
		softAssert.assertTrue(isEmailRadioBtnVisible(), "Email Radio Btn Visible is not visibility");
		softAssert.assertTrue(isSendCodeToEmailLabelVisible(), "Send Code To Email Label is not visibility");
		softAssert.assertTrue(isRadioBtnTextVisible(), "Email Radio Btn is not visibility");
		softAssert.assertTrue(isCancelBtnVisible(), "Cancel Btn is not visibility");
		softAssert.assertTrue(isNextBtnVisible(), "Next Btn is not visibility");

		clickRadioBtnTextBtn();
		isTextTermsConditionVisible();
		islnkTermsConditionVisible();
		String textTandC = gettxtTermsCondition();
		Assert.assertEquals(textTandC, multiFactorAuthTextProp.getPropValue("txtLnkTermsCondition"));
		clicklnkTermsCondition();
		isClickbtnTandCOK();
		clickbtnNext();

        //Verify MFA 2nd screen
		pause(1000);
		String startTime = getlblOTPTimer();
		System.out.println("startTime: " + startTime);
        //pageUtil.verifyObjectLabelEquals("60", startTime);
		getlblOTPTimer().equals("60");

		String lblHeaderMFA = getlblMultiFatorAuth();
		Assert.assertEquals(lblHeaderMFA, multiFactorAuthTextProp.getPropValue("txtLblHeaderMfap"));
		String codeSentOnEmail = getlblCodeSentOnEmail();
		codeSentOnEmail.contains(multiFactorAuthTextProp.getPropValue("txtwejustSendCodeTo"));
		isOTPCancelBtnVisible();
		isSubmitBtnVisible();
		int otpFields = countWebElements(getInputOTPFields());
		Assert.assertEquals(otpFields, 6);
       // String otp = MFAUtil.getMFAEmailOTP(pEmail);
       // System.out.println("Email OTP: "+otp);

		String otp = MFAUtil.getMFATextOTP(pMobile);
		System.out.println("Text OTP: " + otp);

		populateRequestIdBoxOTP("222222");
		String incorrectToasterMsg = getToastMessage();
		Assert.assertEquals(incorrectToasterMsg, multiFactorAuthTextProp.getPropValue("txtIncorrectOTPMsgMfap"));
		waitForToastMessageInvisibility();

		populateRequestIdBoxOTP("222222");
		String incorrectToasterMsgOne = getToastMessage();
		Assert.assertEquals(incorrectToasterMsgOne, multiFactorAuthTextProp.getPropValue("txtIncorrectOTPMsgMfap"));
		waitForToastMessageInvisibility();

		populateRequestIdBoxOTP("222222");
		String incorrectToasterMsgTwo = getToastMessage();
		Assert.assertEquals(incorrectToasterMsgTwo, multiFactorAuthTextProp.getPropValue("txtIncorrectOTPMsgMfap"));
		waitForToastMessageInvisibility();

		populateRequestIdBoxOTP("222222");
		String incorrectToasterMsgThree = getToastMessage();
		Assert.assertEquals(incorrectToasterMsgThree, multiFactorAuthTextProp.getPropValue("txtIncorrectOTPMsgMfap"));
		waitForToastMessageInvisibility();

		String actualTxt = getlblExceededResendOTP().replace("\n", " ");
		Assert.assertEquals(multiFactorAuthTextProp.getPropValue("txtMaxAttemptToResendOTPMfap"), actualTxt);

		pause(1000);
		Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
				loginTextProp.getPropValue("loginPageTitle")));
		// Validate DB values for User Locked Status
		String sQuery = SQLQueries.getUserDetailsQuery(username);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		if (rs.next()) {
			assertTrue(!rs.getString("Status").equals("5"));
			assertTrue(!rs.getString("IsLocked").equals("1"));
		}

		// Valid Login
		dashboardSteps = loginSteps.loginIntoTheApplication(username, password);
		pause(200);
		Assert.assertTrue(isMultiFactorAuthPage(multiFactorAuthTextProp.getPropValue("expectedMFAPageUrl"),(multiFactorAuthTextProp.getPropValue("expectedMFAPageTitle"))),"Page Title & URL does not Match");
		softAssert.assertTrue(isPagelabelHeaderVisible(), "Page label Header is not visibility");
		softAssert.assertTrue(isWhereWouldYouSendLabelVisible(),"Where Would You Send Label Visible is not visibility");
		softAssert.assertTrue(isEmailRadioBtnVisible(), "Email Radio Btn Visible is not visibility");
		softAssert.assertTrue(isSendCodeToEmailLabelVisible(), "Send Code To Email Label is not visibility");
		softAssert.assertTrue(isRadioBtnTextVisible(), "Email Radio Btn is not visibility");
		softAssert.assertTrue(isCancelBtnVisible(), "Cancel Btn is not visibility");
		softAssert.assertTrue(isNextBtnVisible(), "Next Btn is not visibility");
		log.info("Test Case execution for - verifyUserNotLockedForMaxWrongAttemptMFA - is Completed.");
	}
	
	public void verifyExceededResendOTPForMFA_Email_And_Text() throws Exception {
		// Registration using API
				RegistrationEndpoints registrationEndpoints = new RegistrationEndpoints();
				Customer customer = SCPConfiguration.initCustomerConfig("John Doe", "1");
				registrationEndpoints.buildRequestSetRegistrationAdd(ResourcePaths.SCP_COMMON_PATH_URI, customer);
				registrationEndpoints.hitSetRegistration();

				LoginSteps loginSteps;
				loginSteps = new LoginSteps(driver);
				dashboardSteps = new DashboardSteps(driver);
				dashboardSteps = loginSteps.loginIntoTheApplication(username, password);
				pause(200);
				loginSteps = new LoginSteps(driver);
				Assert.assertTrue(isMultiFactorAuthPage(multiFactorAuthTextProp.getPropValue("expectedMFAPageUrl"),(multiFactorAuthTextProp.getPropValue("expectedMFAPageTitle"))),"Page Title & URL does not Match");
				Assert.assertEquals(isPagelabelHeaderVisible(), "Page label Header is not visibility");
				Assert.assertEquals(isWhereWouldYouSendLabelVisible(),"Where Would You Send Label Visible is not visibility");
				Assert.assertEquals(isEmailRadioBtnVisible(), "Email Radio Btn Visible is not visibility");
				Assert.assertEquals(isSendCodeToEmailLabelVisible(), "Send Code To Email Label is not visibility");
				Assert.assertEquals(isRadioBtnTextVisible(), "Email Radio Btn is not visibility");
				Assert.assertEquals(isCancelBtnVisible(), "Cancel Btn is not visibility");
				Assert.assertEquals(isNextBtnVisible(), "Next Btn is not visibility");

//		if(email_OR_text.equals("email")) {			
//			clickbtnNext();	
//	
//			String startTime = getlblOTPTimer();
//			System.out.println("startTime: "+startTime);
//			getlblOTPTimer().equals("120");
//			Thread.sleep(10000);			
//			otp = MFAUtil.getMFAEmailOTP(pEmail);
//			System.out.println("Email OTP: "+otp);
//
//		}else if(email_OR_text.equals("text")) {		
//			pageUtil.selectRadio(multiFactorAuthenticationPage.getRadioBtnTextMfap());
//
//			assertTrue(pageUtil.verifyRadioButtonSelected(multiFactorAuthenticationPage.getRadioBtnTextMfap()));
//
//			assertTrue(pageUtil.isElementPresent(multiFactorAuthenticationPage.getChkboxTermsConditionMfap()));
//			pageUtil.verifyObjectLabel(multiFactorAuthenticationPage.getLblTermsConditionMfap(), Utils.getRbTextValue("txtLblTermsConditionMfap"));
//			pageUtil.verifyObjectLabel(multiFactorAuthenticationPage.getLnkTermsConditionMfap(), Utils.getRbTextValue("txtLnkTermsConditionMfap"));
//
//			pageUtil.verifyRadioButtonNotSelected(multiFactorAuthenticationPage.getChkboxTermsConditionMfap());
//
//			pageUtil.clickElementUsingJsExecutor(multiFactorAuthenticationPage.getChkboxTermsConditionMfap());
//
//			pageUtil.click(multiFactorAuthenticationPage.getBtnNextMfap());
//
//			pageUtil.explicitWaitForWebElement(driver, multiFactorAuthenticationPage.getLblOTPTimerMfap());		
//			String startTime = pageUtil.getObjectLabel(multiFactorAuthenticationPage.getLblOTPTimerMfap());
//			System.out.println("startTime: "+startTime);
//			pageUtil.verifyObjectLabelEquals("60", startTime);
//
//			Thread.sleep(10000);
//
//			otp = MFAUtil.getMFATextOTP(pMobile);
//
//			System.out.println("Text OTP: "+otp);
//
//		}
//
//		//Verify MFA 2nd screen		
//		pageUtil.verifyObjectLabel(multiFactorAuthenticationPage.getLblHeaderMfap(), Utils.getRbTextValue("txtLblHeaderMfap"));
//		pageUtil.verifyObjectLabelContains(multiFactorAuthenticationPage.getLblCodeSentOnEmailMfap(), "We've just sent a code to");
//		pageUtil.verifyObjectLabelContains(multiFactorAuthenticationPage.getLblCodeSentOnEmailMfap(), "Enter that code below.");
//
//		pageUtil.verifyTextValueInTextBox(multiFactorAuthenticationPage.getBtnCancelOTPMfap(), Utils.getRbTextValue("txtBtnCancelMfap"));
//		pageUtil.verifyTextValueInTextBox(multiFactorAuthenticationPage.getBtnSubmitOTPMfap(), Utils.getRbTextValue("txtBtnSubmitOTPMfap"));
//
//		int otpFields = pageUtil.countAllSimilarElements(driver, multiFactorAuthenticationPage.getInputOTPFieldsMfap());
//
//
//		assertEquals(otpFields, 6);
//
//		assertTrue(pageUtil.isElementDisplayed(multiFactorAuthenticationPage.getLblOTPTimerMfap()));
//		pageUtil.verifyObjectLabel(multiFactorAuthenticationPage.getLnkResendOTPCodeMfap(), Utils.getRbTextValue("txtLnkResendOTPCodeMfap"));
//
//		for(int i =1; i<=3; i++)
//		{
//			while(!pageUtil.getObjectLabel(multiFactorAuthenticationPage.getLblOTPTimerMfap()).equals("0")) {			
//				String value = pageUtil.getAttributeValue(multiFactorAuthenticationPage.getLnkResendOTPCodeMfap(), "class");
//				assertEquals(value, "isDisabled");
//				Thread.sleep(12000);			
//			}
//
//			String value = pageUtil.getAttributeValue(multiFactorAuthenticationPage.getLnkResendOTPCodeMfap(), "class");
//			assertEquals(value, "");
//			System.out.println("Resend OTP Number=> "+i);
//
//			pageUtil.clickElementUsingJsExecutor(multiFactorAuthenticationPage.getLnkResendOTPCodeMfap());
//			Thread.sleep(10000);
//			if(i==3)
//				break;
//			pageUtil.expWaitForEleInvisibility(multiFactorAuthenticationPage.getDivPageLoaderBsp());
//			
//			 String startTime = pageUtil.getObjectLabel(multiFactorAuthenticationPage.getLblOTPTimerMfap());
//
//			System.out.println("startTime: "+startTime);
//			int seconds =Integer.parseInt(startTime); 
//
//			if(email_OR_text.equals("email")) {			
//				//assertTrue(seconds>=105 && seconds<120);
//			}else if(email_OR_text.equals("text")){
//				assertTrue(seconds>=40 && seconds<60);
//			}
//
//			Thread.sleep(5000);
//		}
//
//		String actualTxt = pageUtil.getLocatorText(multiFactorAuthenticationPage.getLblExceededResendOTPMfap()).replace("\n", " ");
//		pageUtil.verifyObjectLabelEquals(Utils.getRbTextValue("txtMaxAttemptToResendOTPMfap"), actualTxt);	
//
//		pageUtil.verifyObjectLabel(multiFactorAuthenticationPage.getLnkClickToLoginAginMfap(), Utils.getRbTextValue("txtMaxAttemptLoginAgainLinkMfap"));
//
//
//		pageUtil.clickElementUsingJsExecutor(multiFactorAuthenticationPage.getLnkClickToLoginAginMfap());
//
//		pageUtil.waitForPageToLoad();
//
//		pageUtil.verifyCurrentPageTitle(Utils.getRbTextValue("expectedLoginPageTitle"));
//		pageUtil.assertCurrentPageUrl(Utils.getRbTextValue("expectedLoginPageUrl"));
//
//		// Verify DB that user is not Locked
//		//
//		//
//
//		//Valid Login
//		pageUtil.enterTextValue(loginPage.getTxtBoxUserName(), username);
//		pageUtil.enterTextValue(loginPage.getTxtBoxUserPwd(), password);
//		JsExecutorUtil.click(driver, loginPage.getBtnSignIn());
//		pageUtil.pause(200);
//
//		pageUtil.expWaitForEleInvisibility(loginPage.getDivPageLoaderBsp());
//
//		pageUtil.verifyCurrentPageTitle(Utils.getRbTextValue("expectedMFAPageTitle"));
//		pageUtil.assertCurrentPageUrl(Utils.getRbTextValue("expectedMFAPageUrl"));
//
//		Thread.sleep(2000);
//
//		pageUtil.waitForPageToLoad();
//
//		pageUtil.explicitWaitForWebElement(driver, multiFactorAuthenticationPage.getLblHeaderMfap());
//
//		pageUtil.verifyObjectLabel(multiFactorAuthenticationPage.getLblHeaderMfap(), Utils.getRbTextValue("txtLblHeaderMfap"));
//		pageUtil.verifyObjectLabel(multiFactorAuthenticationPage.getLblWherLikeToSendMfap(), Utils.getRbTextValue("txtLblWherLikeToSendMfap"));
//		pageUtil.verifyObjectLabelContains(multiFactorAuthenticationPage.getLblEmailRadioBtnTxtMfap(), Utils.getRbTextValue("txtLblEmailRadioBtnTxtMfap"));
//		pageUtil.verifyObjectLabelContains(multiFactorAuthenticationPage.getLblRadioBtnTextMfap(), Utils.getRbTextValue("txtLblTextRadioBtnTxtMfap"));
//		pageUtil.verifyTextValueInTextBox(multiFactorAuthenticationPage.getBtnCancelMfap(), Utils.getRbTextValue("txtBtnCancelMfap"));
//		pageUtil.verifyTextValueInTextBox(multiFactorAuthenticationPage.getBtnNextMfap(), Utils.getRbTextValue("txtBtnNextMfap"));
//
//
//		logger.info("Test Case execution for - verifyExceededResendOTPForMFA - is Completed.");
//		Runner.test.log(Status.PASS, "Verify user Exceeded Resend OTP For MFA");

	}

	public Boolean isMultiFactorAuthPage(String url, String title) {
		Boolean isMultiFactorAuthPage = false;
		log.info("Checking that the current page is Registration page.");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isMultiFactorAuthPage = true;
		log.info("The current page is registration page {}: " + isMultiFactorAuthPage);
		return isMultiFactorAuthPage;
	}

	public boolean verifyRadioButtonSelected(By locator) {
		WebElement rdoBtn = driver.findElement(locator);
		boolean rdoBtnValue = rdoBtn.isSelected();
		log.info("The Radio button value is: " + rdoBtnValue);
		Assert.assertTrue(rdoBtnValue);
		return rdoBtnValue;
	}

	public int countAllSimilarElements(WebDriver driver, By locator) {
		int countElements;
		List<WebElement> elementList;
		elementList = driver.findElements(locator);
		countElements = elementList.size();
		return countElements;
	}

	public int countWebElements(List<WebElement> list) {
		int countElements;
		List<WebElement> elementList;
		elementList = driver.findElements((By) list);
		countElements = elementList.size();
		return countElements;
	}

	public String getObjectLabel(By locator) {
		String label = null;
		try {
			// driver.findElement(locator).isDisplayed();
			label = driver.findElement(locator).getText().trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return label;
	}

	public static Map<String, String> getTwo_FactorTableData(String username) throws Exception {
		String sQuery = SQLQueries.getTwo_FactorTableQuery(username);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		Map<String, String> map = new HashMap();
		while (rs.next()) {
			map.put("two_factor_id", rs.getString("two_factor_id"));
			map.put("user_id", rs.getString("user_id"));
			map.put("token", rs.getString("token"));
			map.put("client", rs.getString("client"));
			map.put("token_status", rs.getString("token_status"));
			map.put("date_created", rs.getString("date_created"));
			map.put("dates_updated", rs.getString("date_updated"));
			map.put("unique_key", rs.getString("unique_key"));
			map.put("account_number", rs.getString("account_number"));
			map.put("tfa_attempt", rs.getString("tfa_attempt"));
		}
		return map;
	}

	public static int getUserIdDB(String username) {
		String getUserIdQuery = SQLQueries.getUserId(username);
		String userId = null;
		try {
			ResultSet rs = DataBaseUtils.getResultSet(getUserIdQuery);
			rs.next();
			userId = rs.getString("UserId");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Integer.parseInt(userId);
	}

	public static String getMFATokenStatus(String username) {
		String value = "";
		String sQuery = SQLQueries.getMfaToken_StatusQuery(username);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		try {
			if (rs.next()) {
				value = rs.getString("token_status");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public static Map<String, String> getTwo_FactorTableData(String username, String two_factor_id) throws Exception {
		String sQuery = SQLQueries.getTwo_FactorTableQuery(username, two_factor_id);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		Map<String, String> map = new HashMap();
		while (rs.next()) {
			map.put("two_factor_id", rs.getString("two_factor_id"));
			map.put("user_id", rs.getString("user_id"));
			map.put("token", rs.getString("token"));
			map.put("client", rs.getString("client"));
			map.put("token_status", rs.getString("token_status"));
			map.put("date_created", rs.getString("date_created"));
			map.put("date_updated", rs.getString("date_updated"));
			map.put("unique_key", rs.getString("unique_key"));
			map.put("account_number", rs.getString("account_number"));
			map.put("tfa_attempt", rs.getString("tfa_attempt"));
		}
		return map;
	}
}

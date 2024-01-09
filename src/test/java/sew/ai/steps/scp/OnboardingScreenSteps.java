package sew.ai.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import sew.ai.api.endpoints.auth.LoginEndpoint;
import sew.ai.api.endpoints.auth.RegistrationEndpoints;
import sew.ai.api.utils.AuthUtils;
import sew.ai.config.CSPConfiguration;
import sew.ai.config.Configuration;
import sew.ai.config.SCPConfiguration;
import sew.ai.driver.DriverFactory;
import sew.ai.driver.Page;
import sew.ai.enums.RegistrationPreference;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.ResourcePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.models.Customer;
import sew.ai.models.RegistrationConfig;
import sew.ai.models.RegistrationMailsConfig;
import sew.ai.models.User;
import sew.ai.pageObjects.scp.AppOnBoardingPage;
import sew.ai.pageObjects.scp.DashboardPage;
import sew.ai.pageObjects.scp.HomePage;
import sew.ai.pageObjects.scp.LoginPage;
import sew.ai.pageObjects.scp.MultiFactorAuthPage;
import sew.ai.pageObjects.scp.RegistrationPage;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.PropertiesUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OnboardingScreenSteps extends AppOnBoardingPage {
	private static final Logger log = LogManager.getLogger(OnboardingScreenSteps.class);
	public static PropertiesUtil onboardingTextProp;
	RegistrationEndpoints registrationEndpoint = new RegistrationEndpoints();
	List<String> columnImgLabels = new ArrayList<String>();
	HashMap<String, String> hashMap = new HashMap<>();
	HashMap<String, String> authMap = new HashMap<>();
	ResultSet resultSet;

	public static enum NotificationType {
		OutageText, OutageWhatsapp, OutageEmail, OutageIVR, BillingText, BillingEmail, BillingIVR, BudgetText,
		BudgetEmail, BudgetIVR, DRText, DREmail, DRIVR, ConnectMeText, ConnectMeEmail, ConnectMeIVR, ServiceText,
		ServiceEmail, ServiceIVR, LeakAlertText, LeakAlertEmail, LeakAlertIVR, HighUsageText, HighUsageEmail,
		HighUsageIVR
	}

	public OnboardingScreenSteps(WebDriver driver) {
		super(driver);
		onboardingTextProp = new PropertiesUtil(
				FilePaths.SCP_TEXT_PROPERTIES + Constants.ONBOARDING_SCREEN_TEXT_FILENAME);
	}

	public Boolean isOnboardingPage(String url, String title) {
		Boolean isOnboardingPage = false;
		log.info("Checking that the current page is Onboarding page.");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isOnboardingPage = true;
		log.info("The current page is Onboarding page {}: " + isOnboardingPage);
		return isOnboardingPage;
	}

	/**
	 * This test case verifies navigating the registration page and clicking the
	 * register button without any entry.
	 * 
	 * @throws InterruptedException
	 * @throws SQLException
	 */

	public void welcomeScreenResidentialUser(SoftAssert sAssert, Customer customer)
			throws InterruptedException, SQLException {
		// Logging

		log.info("Test Case execution for - verifyWelcomeScreenResidentialUser - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		// Initializing registration type a). Residential or b). Commercial
		String resourcePathURI = ResourcePaths.SCP_COMMON_PATH_URI;
		hashMap = getRegistrationData("1", "3");
		authMap = registrationEndpoint.buildRequestSetRegistrationAdd(resourcePathURI, customer, hashMap);
		registrationEndpoint.hitSetRegistration();
		String customerVerificationID = registrationEndpoint.getAPIResponseCustomerVerificationId();
		registrationEndpoint.buildRequestCustomerVerificationAdd(resourcePathURI, customerVerificationID);
		registrationEndpoint.hitCustVerificationRegistration();
		// Login with newly registered user.
		loginIntoTheApplication(authMap.get("userName"), authMap.get("password"));
		// Wait for page to load

		// Verify navigation to welcome screen.
		verifyWelcomeScreen(softAssert, customer, authMap);
		/*
		 * C65388 - Verify if user has opted in for preferences while registration all
		 * the email check-box should come selected and populated with primary email
		 * address
		 */

		clickchkEmailAll();
		List<WebElement> emailCheckBoxElements = getObjectemailCheckBoxElementsList();
		for (WebElement element : emailCheckBoxElements) {
			String email = element.getAttribute("value");
			String custemail = authMap.get("emailID");
			softAssert.assertEquals(email, custemail, "Primary Email and Notification Email are not same.");
		}
		/*
		 * C65387 - Verify if pop-up is not closed and user navigates to some other page
		 * using URL pop-up gets displayed again login or going back to dashboard page.
		 */
		String newUrl = onboardingTextProp.getPropValue("expectedLoginPageUrl");
		DriverFactory.goToPage(newUrl);
		// driver.get(newUrl);
		isOnboardingPage("expectedAccountPageUrl", "expectedAccountPageTitle");

		sAssert.assertTrue(islblWelcomePopupHeadingVisible(), "Wlecome popup heading is not visible");
		// Verify navigation to welcome screen.
		verifyWelcomeScreen(softAssert, customer, authMap);
		// Clear the cache
		DriverFactory.deleteAllCookies();
		// Re-login to application and verify Welcome screen
		DriverFactory.goToPage(onboardingTextProp.getPropValue("expectedLoginPageUrl"));
		// Login to the application.
		loginSteps.loginIntoTheApplication(authMap.get("userName"), authMap.get("password"));
		// Verify navigation to welcome screen.
		verifyWelcomeScreen(softAssert, customer, authMap);
		// Verify navigation to notification preferences page.
		verifyNotificationPrefScreen(softAssert);
		// Verify edit preferences feature.
		verifyErrMsgEditPrefFeature(softAssert, customer);
		// Verify accept terms agree feature.
		verifyAcceptTermsAgreeFeature(softAssert);
		// Verify saved Notification preferences
		// verifySavedNotificationPrefDB(softAssert);
		// Verify navigation to about my home page.
		verifyNavToAboutMyHome(softAssert);
		// Verify about my home contents
		// verifyAboutMyHomeContents(softAssert, addressType);
		// Assert all the verifications.
		softAssert.assertAll();
		// Logging

		log.info("Test Case execution for - verifyWelcomeScreenResidentialUser - is Completed");

	}

	public void welcomeWithoutTCPACompliance(SoftAssert sAssert, Customer customer)
			throws InterruptedException, SQLException {
		// Logging
		log.info("This test method verifies below tests: " + "C74935, C74949, C74958, C74960, C74961, C74962 "
				+ "C74963, C74964, C74965, C74966, C74969");
		log.info("Test Case execution for - verifyAppOnBoardWithoutTCPACompliance - is Initiated");
		log.info("Test Case execution for - verifyWelcomeScreenResidentialUser - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		// Initializing registration type a). Residential or b). Commercial
		String resourcePathURI = ResourcePaths.SCP_COMMON_PATH_URI;
		hashMap = getRegistrationData("1", "3");
		authMap = registrationEndpoint.buildRequestSetRegistrationAdd(resourcePathURI, customer, hashMap);
		registrationEndpoint.hitSetRegistration();
		String customerVerificationID = registrationEndpoint.getAPIResponseCustomerVerificationId();
		registrationEndpoint.buildRequestCustomerVerificationAdd(resourcePathURI, customerVerificationID);
		registrationEndpoint.hitCustVerificationRegistration();
		// Login with newly registered user.
		loginIntoTheApplication(authMap.get("userName"), authMap.get("password"));
		// Wait for page to load
		// Login with newly registered user.
		// Verify navigation to welcome screen.
		verifyWelcomeScreen(softAssert, customer, authMap);
		// Verify that TCPA compliance popup is not displaying.
		verifyTCPANotDisplaying(softAssert);
		clickSubmit_AhmButton();
		// Verify About My Home page get disappear
		sAssert.assertFalse(islbl_PageHeader_AHMvisible());
		// C11975 - To verify that 'Application on Board' popup will not display again
		// after first login.
		// pageUtil.click(appOnBoardingPage.getBtnDropdownMenu());
		pause(500);
		GuestuserSteps guestuserSteps = new GuestuserSteps(driver);
		// Logout and Login Again with same user, No Welcome screen come
		guestuserSteps.SignOutAndBackToLoginPage(driver);
		// Again login into the application.
		loginSteps.loginIntoTheApplication(authMap.get("userName"), authMap.get("password"));

		// sAssert.assertTrue(islblWelcomePopupHeadingVisible());
		// Logging
		log.info("This test method verifies below tests: "
				+ "C74935, C74949, C74958, C74960, C74961, C74962 C74963, C74964, C74965, C74966, C74969");
		log.info("Test Case execution for - verifyAppOnBoardWithoutTCPACompliance - is Completed");
	}

	public void disagreeOfTCPATermsFeature(SoftAssert sAssert, Customer customer)
			throws SQLException, InterruptedException {
		// Logging
		log.info("Test Case : C11959 - Notification Preference | To verify the flow when "
				+ "user disagree TCPA compliance.");
		log.info("Test Case execution for - verifyDisagreeOfTCPATermsFeature - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		String resourcePathURI = ResourcePaths.SCP_COMMON_PATH_URI;
		hashMap = getRegistrationData("1", "3");
		authMap = registrationEndpoint.buildRequestSetRegistrationAdd(resourcePathURI, customer, hashMap);
		registrationEndpoint.hitSetRegistration();
		String customerVerificationID = registrationEndpoint.getAPIResponseCustomerVerificationId();
		registrationEndpoint.buildRequestCustomerVerificationAdd(resourcePathURI, customerVerificationID);
		registrationEndpoint.hitCustVerificationRegistration();
		// Login with newly registered user.
		loginIntoTheApplication(authMap.get("userName"), authMap.get("password"));
		// Login with newly registered user.

		// Verify navigation to welcome screen.
		verifyWelcomeScreen(softAssert, customer, authMap);
		// Verify navigation to notification preferences page.
		verifyNotificationPrefScreen(softAssert);
		// Editing Text and Ivr text boxes so that TCPA compliance pop up window
		// displayed
		checkTCPACompliance();
		// Verifying notification alert popup heading
		String expAlertPopupHeading = onboardingTextProp.getPropValue("txtLblAcceptNotificationPopupHeadingAos");
		String actAlertPopupHeading = getLabelAcceptNotificationTerms();
		softAssert.assertEquals(actAlertPopupHeading, expAlertPopupHeading,
				"Notification alert popup heading not matched");
		// Verifying notification alert popup body title
		String expBodyTitleAlertPopup = onboardingTextProp.getPropValue("txtLblNotificationBodyTitleAos");
		String actBodyTitleAlertPopup = getLabelNotificationBodyTitle();
		softAssert.assertEquals(actBodyTitleAlertPopup, expBodyTitleAlertPopup,
				"Notification alert popup body title not matched.");
		// Verifying visibility of agree and disagree button.
		softAssert.assertTrue(isDisAgreeButtonVisible(),
				"I agree button is not displaying on the notification alert popup");
		softAssert.assertTrue(isAgreeButtonVisible(),
				"I disagree button is not displaying on the notification alert popup");
		// Click agree button
		clickDisagreeButton();
		/*
		 * String actToastMsg = getToastMessage(); // Verifying notification preferences
		 * saved successfully. String expToastMsg =
		 * onboardingTextProp.getPropValue("txtLblNotificationPrefSavedAos");
		 * softAssert.assertEquals(actToastMsg, expToastMsg,
		 * "Successful saved toast message not matched");
		 */
		softAssert.assertAll();
		// Logging
		log.info("Test Case : C11959 - Notification Preference | To verify the "
				+ "flow when user disagree TCPA compliance.");
		log.info("Test Case execution for - verifyDisagreeOfTCPATermsFeature - is Completed");

	}

	public void aboutMyBusinessScreen(SoftAssert sAssert, Customer customer) throws InterruptedException, SQLException {
		log.info("Test Case : C11921, C11955, C11940, C11941, C11944, C11948 "
				+ ", C11949, C11950 - To validate about my business screen.");
		log.info("Test Case execution for - verifyAboutMyBusinessScreen - is Initiated");

		LoginSteps loginSteps = new LoginSteps(driver);
		// Initializing registration type a). Residential or b). Commercial
		String resourcePathURI = ResourcePaths.SCP_COMMON_PATH_URI;
		hashMap = getRegistrationData("2", "3");
		authMap = registrationEndpoint.buildRequestSetRegistrationAdd(resourcePathURI, customer, hashMap);
		registrationEndpoint.hitSetRegistration();
		String customerVerificationID = registrationEndpoint.getAPIResponseCustomerVerificationId();
		registrationEndpoint.buildRequestCustomerVerificationAdd(resourcePathURI, customerVerificationID);
		registrationEndpoint.hitCustVerificationRegistration();
		// Login with newly registered user.
		loginIntoTheApplication(authMap.get("userName"), authMap.get("password"));
		// Verify navigation to welcome screen.
		verifyWelcomeScreen(sAssert, customer, authMap);
		// Navigation to the set preferences screen and validate skip button
		// functionality.
		verifySkipBtnFeatureOnSetPrefScreen(sAssert);
		// Verify navigation to about my business
		verifyNavToAboutMyBusiness(sAssert);
		// Verify about my business content
		// verifyAboutMyBusinessContent(sAssert);
		// Verifying Employees in workplace fields accepts only numeric values
		gettxtNoEmployeesWorking("20000000");
		pause(200);
		String actLen = Integer.toString(getAttributetxtNoEmployeesWorking("value").length());
		String expLen = getAttributetxtNoEmployeesWorking("maxlength");
		sAssert.assertEquals(actLen, expLen, "Employees in workplace fields Max length mismatch");
		// Verifying Office space area fields accepts only numeric values
		gettxtOfficeSpaceArea("16790000");
		pause(200);
		actLen = Integer.toString(getAttributetxtOfficeSpaceArea("value").length());
		expLen = getAttributetxtOfficeSpaceArea("maxlength");
		sAssert.assertEquals(actLen, expLen, "Office space area fields Max length mismatch");
		// Verifying Lot size fields accepts only numeric values
		gettxtLotSizeAmb("5000000000");
		pause(200);
		actLen = Integer.toString(getAttributetxtLotSizeAmb("value").length());
		expLen = getAttributetxtLotSizeAmb("maxlength");
		sAssert.assertEquals(actLen, expLen, "Lot size fields Max length mismatch");
		// Verifying number of floors fields accepts only numeric values
		gettxtNoFloors("1000000000");
		pause(200);
		actLen = Integer.toString(getAttributetxtNoFloors("value").length());
		expLen = getAttributetxtNoFloors("maxlength");
		sAssert.assertEquals(actLen, expLen, "Number of floors fields Max length mismatch");
		// Verifying number of restrooms fields accepts only numeric values
		gettxtNoRestrooms("2000000");
		pause(200);
		actLen = Integer.toString(getAttributetxtNoRestrooms("value").length());
		expLen = getAttributetxtNoRestrooms("maxlength");
		sAssert.assertEquals(actLen, expLen, "Number of restrooms fields Max length mismatch");
		// Verifying landscape area fields accepts only numeric values
		gettxtLandscapeAreaAmb("40000000");
		pause(200);
		actLen = Integer.toString(getAttributetxtLandscapeAreaAmb("value").length());
		expLen = getAttributetxtLandscapeAreaAmb("maxlength");
		sAssert.assertEquals(actLen, expLen, "Landscape area fields Max length mismatch");
		// Verifying Solar panel field accepts only numeric values
		gettxtSolarCellPanels("100000");
		pause(200);
		actLen = Integer.toString(getAttributetxtSolarCellPanels("value").length());
		expLen = getAttributetxtSolarCellPanels("maxlength");
		sAssert.assertEquals(actLen, expLen, "Solar panel fields Max length mismatch");
		// Submit About My Business Page with valid values
		isSubmitAboutMyBusinessPopUpVisible();
		clickSubmitAboutMyBusinessPopUp();
		// Verify About My Business page get disappear
		pause(5000);
		sAssert.assertFalse(islbl_PageHeader_AHMvisible(), "page header is visible");
		// C11975 To verify that 'Application on Board' popup will not display again
		// after first login.
		// pageUtil.click(appOnBoardingPage.getBtnDropdownMenu());
		pause(500);
		// Logout and Login Again with same user, No Welcome screen come

		SignOutAndBackToLoginPage(driver);

		loginSteps.loginIntoTheApplication(authMap.get("userName"), authMap.get("password"));
		// pageUtil.pause(5000);

		// assertTrue(pageUtil.isElementNotDisplayed(appOnBoardingPage.getLblWelcomePopupHeadingDbp()));

		// Logging
		log.info("Test Case : C11921, C11955, C11940, C11941, C11944, C11948 "
				+ ", C11949, C11950 - To validate about my business screen.");
		log.info("Test Case execution for - verifyAboutMyBusinessScreen - is Completed");

	}

	public void SignOutAndBackToLoginPage(WebDriver driver) {
		DashboardPage dashboardpage = new DashboardPage(driver);
		LoginPage loginpage = new LoginPage(driver);
		pause(5000);
		dashboardpage.clickSignOutDdLink();
		pause(5000);
		dashboardpage.clickSignOutLnk();
		pause(2000);
		loginpage.clickLinkHomeButton();
		pause(1000);
	}

	public void loginIntoTheApplication(String userName, String password) throws InterruptedException {
		ExtentLogger.logInfo("Logging into the application.");
		LoginSteps loginsteps = new LoginSteps(driver);
		ResultSet resultSet;
		String otp = "";

		loginsteps.populateLoginForm(userName, password);
		loginsteps.clickSignInBtn();
		waitForPageLoader();
		DashboardSteps dashboardSteps = new DashboardSteps(driver);
		
		submitOTP();
		dashboardSteps.clickCloseAboutMyHomeBtn();
		
		/*if(dashboardSteps.isCloseAboutMyHomeBtnVisible()) {
			dashboardSteps.clickCloseAboutMyHomeBtn();
			ExtentLogger.logInfo("MFA Screen is not coming, While user First Time Login");
		}
		else {
			submitOTP();
			dashboardSteps.clickCloseAboutMyHomeBtn();
		}*/
		
		// waitForPageLoader();
		pause(10000);

	}

	public void submitOTP() throws InterruptedException {
		MultiFactorAuthPage multiAuth = new MultiFactorAuthPage(driver);
		multiAuth.clickbtnNext();
		ResultSet resultSet;
		String otp = "";
		pause(10000);
		try {

			resultSet = DataBaseUtils.getResultSet(SQLQueries.getLoginMFAOtp());
			while (resultSet.next()) {
				otp = resultSet.getString("token");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<WebElement> otpTxtBoxs = multiAuth.getInputOTPFields();
		int i = 0;
		for (WebElement el : otpTxtBoxs) {
			String val = Character.toString(otp.charAt(i));
			sendKeys(el, val);
			i++;
		}
		multiAuth.clickbtnSubmitOTP();
		pause(5000);
	}

	public void verifyWelcomeScreen(SoftAssert softAssert, Customer customer, HashMap<String, String> authMap) {
		// Logging
		log.info("Test Case : C11921 - To validate navigation to Welcome to SCM" + "screen on app on boarding popup");
		log.info("Test Case execution for - verifyWelcomeScreen - is Initiated");
		pause(8000);
	 	// Verify pop-up welcome page.
		String expWelcomeStep1Heading = onboardingTextProp.getPropValue("txtLblWelcomePopupHeadingDbp");

		String actWelcomeStep1Heading = getPageHeader_AHMLabel();
		softAssert.assertEquals(actWelcomeStep1Heading, expWelcomeStep1Heading,
				"Welcome screen step 1 page heading not matched.");
		String expWelcomeStep2Heading = onboardingTextProp.getPropValue("txtLblWelcomePopupHeadingFirstDbp");
		String actWelcomeStep2Heading = getWelcomePopupHeadingLabel();
		softAssert.assertEquals(actWelcomeStep2Heading, expWelcomeStep2Heading);
		String expWelcomeStep2Content = onboardingTextProp.getPropValue("txtWelcomePopupContentDbp");
		String actWelcomeStep2Content = getWelcomePopupContentLabel();
		softAssert.assertTrue(actWelcomeStep2Content.contains(expWelcomeStep2Content));
		String firstName = authMap.get("firstName");
		String lastName = authMap.get("lastName");
		String expWelcomeText = "Hi " + firstName + " " + lastName;
		softAssert.assertEquals(getLablNameWelcomePopUp(), expWelcomeText);
		clickOnboardingNextButton();
		pause(2000);
		// Logging
		log.info("Test Case : C11921 - To validate navigation to Welcome to SCM" + "screen on app on boarding popup");
		log.info("Test Case execution for - verifyWelcomeScreen - is Completed");
	}

	/**
	 * Test 1.2: This test method covers below tests C11955 - This method is used to
	 * verify the notification preferences screen. C11957 - Notification Preference
	 * | To verify the object on Notification preference screen.
	 */
	public void verifyNotificationPrefScreen(SoftAssert softAssert) {
		// Logging
		log.info("Test Case : C11955, C11957 - Notification preference | "
				+ "To verify the redirection on notification preference page.");
		log.info("Test Case execution for - verifyNotificationPreferencesScreen - " + "is Initiated");
		String expStep1Heading = onboardingTextProp.getPropValue("txtLblSetYourAlertPopupHeadingFirstDbp");

		String actStep1Heading = getNotificationHeadingLabel();
		softAssert.assertEquals(actStep1Heading, expStep1Heading,
				"Notification pref heading pop-up  page heading not matched.");
		String expStep2Heading = onboardingTextProp.getPropValue("txtLblSetYourAlertPopupHeadingDbp");
		String actStep2Heading = getStep2PageHeaderLabel();
		softAssert.assertEquals(actStep2Heading, expStep2Heading, "Welcome pop-up step 2 page heading not matched.");

		String expFooterContent = onboardingTextProp.getPropValue("txtLblfooterContentDbp");
		String actFooterContent = getNotificationFooterLabel();
		softAssert.assertEquals(actFooterContent, expFooterContent, "Notification pre footer test is not matched.");
		softAssert.assertTrue(isSkipNotificationPreButtonVisible());

		// Verifying table headers text

		String expSetMyAlertsTableHeader = onboardingTextProp.getPropValue("txtLblSetYourAlertTableHeadingDbp");
		ArrayList<String> setMyAlertsGridHeader = new ArrayList<>();
		for (String header : expSetMyAlertsTableHeader.split(","))
			setMyAlertsGridHeader.add(header.trim());
		List<WebElement> tableHeaderParentElements = getObjectNotificationPrefTableHeaderParentList();
		List<WebElement> lblHeaderChildElements = new ArrayList<>();
		int counter = 0;
		for (WebElement tableHeaderTextEle : tableHeaderParentElements) {
			if (tableHeaderTextEle.isDisplayed()) {
				WebElement element = tableHeaderTextEle.findElement(getObjectlabelNotificationPrefTableHeaderChild());
				lblHeaderChildElements.add(element);
			}
		}
		for (WebElement lblHeaderChildEle : lblHeaderChildElements) {
			String actText = lblHeaderChildEle.getText().trim();
			// softAssert.assertTrue(setMyAlertsGridHeader.contains(actText),
			// "Set my alert table header not matched at iteration");
			counter++;
		}
		// Verifying table content
		List<WebElement> columnImgTextElements = getObjectsLablImgColumnSetPrefAppOnBoardScreen();
		// Adding column image labels to the list
		for (WebElement ele : columnImgTextElements) {
			columnImgLabels.add(ele.getText().trim());
		}
		// Verifying check boxes for each of the category of
		for (String str : columnImgLabels) {
			if (str.equals("Contact Us"))
				verifyNotificationsCheckboxesDisplaying(softAssert, "ConnectMe", "Ivr");
			else if (str.equals("Services"))
				verifyNotificationsCheckboxesDisplaying(softAssert, "Services", "Ivr");
			else if (str.equals("Leak Alert"))
				verifyNotificationsCheckboxesDisplaying(softAssert, "LeakAlert", "Ivr");
			else if (str.equals("High Usage Alert"))
				verifyNotificationsCheckboxesDisplaying(softAssert, "HighUsage", "Ivr");
			else if (str.equals("Demand Response"))
				verifyNotificationsCheckboxesDisplaying(softAssert, "DR", "Ivr");
			else
				log.info(str + " not matched to any specified category.");
		}
		// Logging
		log.info("Test Case : C11955, C11957 - Notification "
				+ "preference | To verify the redirection on notification preference page.");
		log.info("Test Case execution for - verifyNotificationPreferencesScreen - " + "is Initiated");
	}

	/**
	 * Test 1.3: This test method covers below tests C11957 - This test method is
	 * used to verify the edit preferences method. C11961 - To verify that
	 * application will prepopulate Text number only if provided Primary contact
	 * number type is of mobile C11962 - To verify that application will prepopulate
	 * Text number only if provided Primary contact number type is of mobile
	 */
	public void verifyErrMsgEditPrefFeature(SoftAssert softAssert, Customer customer) {
		// Logging
		log.info("Test Case : C11957, C11961, C11962 - "
				+ "To verify that if user clicks on 'I Agree', he shall display the "
				+ "changes made by user and receive the notification communication " + "on same.");
		log.info("Test Case execution for - verifyEditPreferences - is Initiated");
		String formattedPhoneNo = "";
		// String formattedPhoneNo =
		// PhoneNoUtil.getPhoneNumberAsString(registerAPIRequestBody.getPhoneNo());
		// Check the outage text, billing mail, budget Ivr checkbox if unchecked
		if (!checkCbOutageText()) {
			clickCbOutageText();
		}
		if (!checkCbBillingEmail()) {
			clickCbBillingEmail();
		}
		if (!checkCbBudgetIvr()) {
			clickCbBudgetIvr();
		}
		// Verifying default input value of text input field at the time of the
		// registration.
		String outageTextLocator = "#Txt" + columnImgLabels.get(0) + "Text";
		String outageTextInputValue = getAttribute(driver.findElement(By.cssSelector(outageTextLocator)), "value");
		String expOutageTextValue = formattedPhoneNo;
		softAssert.assertEquals(outageTextInputValue, expOutageTextValue,
				"Phone number provided at the time of registration is different from what is coming by "
						+ "default in the text input field");
		log.info("Test Case : C11961 - To verify the default email address provided "
				+ "during registration is displayed for all type of email notification when Notification Preferences "
				+ "option is checked during registration.");
		if (outageTextInputValue != null)
			clear(driver.findElement(By.cssSelector(outageTextLocator)));
		// Verifying default input value of email input field is the phone number
		// provided at the time of the registration.
		clickCbBillingEmail();
		String billingEmailLocator = "#Txt" + columnImgLabels.get(1) + "Email";
		String billingTextInputValue = getAttribute(driver.findElement(By.cssSelector(billingEmailLocator)), "value");
		String expBillingTextInputValue = customer.getEmailAddress();
		// softAssert.assertEquals(billingTextInputValue, expBillingTextInputValue,
		// "Email address provided at the time of registration is different from what is
		// coming " +
		// "by default in the email input field.");
		log.info("Test Case : C11962 - To verify that application will prepopulate "
				+ "text number only if provided Primary contact number type is of mobile.");
		if (billingTextInputValue != null) {
			clickCbBillingEmail();
			pause(1000);
			clickCbBillingEmail();
			clear(driver.findElement(By.cssSelector(billingEmailLocator)));
		}
		// Verifying default input value of ivr input field is the phone number provided
		// at the time of the registration.
		String budgetIvrLocator = "#Txt" + columnImgLabels.get(2) + "Ivr";
		String budgetTextInputValue = getAttribute(driver.findElement(By.cssSelector(budgetIvrLocator)), "value");
		String expBudgetTextInputValue = formattedPhoneNo;
		softAssert.assertEquals(budgetTextInputValue, expBudgetTextInputValue,
				"Phone number provided at the time of registration is different from what is coming "
						+ "by default in the Ivr input field");
		log.info("Test Case : C11961 - To verify the default email address provided "
				+ "during registration is displayed for all type of email notification when Notification Preferences "
				+ "option is checked during registration.");
		if (budgetTextInputValue != null)
			clear(driver.findElement(By.cssSelector(budgetIvrLocator)));
		// Clicking save and continue button
		clickSaveContinue_SmaButton();
		// Verifying the toast warning message for filling all the mandatory fields
		String actFillMandatoryFields = getToastMessage();
		String expFillMandatoryFields = onboardingTextProp.getPropValue("txtLblErrMsgFillMandatoryFieldsAos");
		softAssert.assertEquals(actFillMandatoryFields, expFillMandatoryFields,
				"Fill all mandatory fields on set your alerts popup.");
		pause(1000);
		// Verifying specific fields error messages for email field.
		// pageUtil.sendKeys(By.cssSelector(outageTextLocator),
		// registerAPIRequestBody.getPhoneNo());
		billingTextInputValue = getAttribute(driver.findElement(By.cssSelector(billingEmailLocator)), "value");
		if (billingTextInputValue != null)
			clear(driver.findElement(By.cssSelector(billingEmailLocator)));
		sendKeys(By.cssSelector(budgetIvrLocator), customer.getPhoneNumber());
		// Verifying invalid email error message on email input field
		sendKeys(By.cssSelector(billingEmailLocator), "invalid-email");
		clickSaveContinue_SmaButton();
		String actEmailErrorMsg = getlblGenericErrorMessage();
		String expEmailErrorMsg = onboardingTextProp.getPropValue("txtLblEmailErrorMsgSetMyPrefAos");
		// softAssert.assertEquals(actEmailErrorMsg, expEmailErrorMsg,
		// "Email field error message not matched on set my pref popup.");
		// Populating all the checked fields
		sendKeys(By.cssSelector(outageTextLocator), customer.getPhoneNumber());
		sendKeys(By.cssSelector(billingEmailLocator), customer.getEmailAddress() + "@mailinator.com");
		sendKeys(By.cssSelector(budgetIvrLocator), customer.getPhoneNumber());
		// Verify before sub
		NotificationType notificationTypeTxt = NotificationType.OutageText;
		String countTxt = "";
		try {
			resultSet = DataBaseUtils.getResultSet(SQLQueries.getCountPreferenceChangeDetails(
					customer.getUtilityAccountNumber(), NotificationType.OutageText.name()));
			resultSet.next();
			countTxt = resultSet.getString("NoRow");
		} catch (Exception e) {
			e.printStackTrace();
		}
		softAssert.assertEquals(countTxt, "0", "Mobile number for Outage Notification is already saved.");
		NotificationType notificationTypeEmail = NotificationType.BillingEmail;
		String countEmail = "";
		try {
			resultSet = DataBaseUtils.getResultSet(SQLQueries.getCountPreferenceChangeDetails(
					customer.getUtilityAccountNumber(), NotificationType.BillingEmail.name()));
			resultSet.next();
			countEmail = resultSet.getString("NoRow");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// softAssert.assertEquals(countEmail, "1",
		// "Email For Billing Notification is NOT already saved.");
		NotificationType notificationTypeIVR = NotificationType.BudgetIVR;
		String countIVR = "";
		try {
			resultSet = DataBaseUtils.getResultSet(SQLQueries.getCountPreferenceChangeDetails(
					customer.getUtilityAccountNumber(), NotificationType.BudgetIVR.name()));
			resultSet.next();
			countIVR = resultSet.getString("NoRow");
		} catch (Exception e) {
			e.printStackTrace();
		}
		softAssert.assertEquals(countIVR, "0", "IVR For Budget Notification is already saved.");
		clickSaveContinue_SmaButton();
		pause(500);
		// Logging
		log.info("C11957, C11961, C11962 - To verify that if user "
				+ "clicks on \"I Agree\", he shall display the changes made by user and receive "
				+ "the notification communication on same.");
		log.info("Test Case execution for - verifyEditPreferences - is Initiated");
	}

	/**
	 * Test 1.4: This test method covers below tests C11958 - To verify that if user
	 * clicks on "I Agree", he shall display the changes made by user and receive
	 * the notification communication on same. C11960 - To verify that when user
	 * save the changes made, the system shall display a success message
	 */
	public void verifyAcceptTermsAgreeFeature(SoftAssert softAssert) {
		// Logging
		log.info("Test Case : C11958 - To verify that if user clicks"
				+ " on \"I Agree\", he shall display the changes made by user and receive the "
				+ "notification communication on same.");
		log.info("Test Case execution for - verifyAcceptTermsPopupScreen - is Initiated");
		// Verifying notification alert popup heading
		String actAlertPopupHead = getLabelAcceptNotificationTerms();
		String expAlertPopupHead = onboardingTextProp.getPropValue("txtLblAcceptNotificationPopupHeadingAos");
		softAssert.assertEquals(actAlertPopupHead, expAlertPopupHead, "Notification alert popup heading not matched.");
		// Verifying notification alert popup body title
		String actBodyTitleAlertPopup = getLabelNotificationBodyTitle();
		String expBodyTitleAlertPopup = onboardingTextProp.getPropValue("txtLblNotificationBodyTitleAos");
		softAssert.assertEquals(actBodyTitleAlertPopup, expBodyTitleAlertPopup,
				"Notification alert popup body title not matched");
		// Verifying visibility of agree and disagree button.
		softAssert.assertTrue(isDisAgreeButtonVisible(),
				"I agree button is not displaying on the notification alert popup");
		softAssert.assertTrue(isAgreeButtonVisible(),
				"I disagree button is not displaying on the notification alert popup");
		// Click agree button
		clickAgreeButton();
		// Verifying notification preferences saved successfully.
		String actSuccessToastMsg = getToastMessage();
		String expSuccessToastMsg = onboardingTextProp.getPropValue("txtLblNotificationPrefSavedAos");
		softAssert.assertEquals(actSuccessToastMsg, expSuccessToastMsg, "Successful saved toast message not matched.");
		// Logging
		log.info("Test Case : C11958 - To verify that if user clicks "
				+ "on \"I Agree\", he shall display the changes made by user and receive the "
				+ "notification communication on same.");
		log.info("Test Case execution for - verifyAcceptTermsPopupScreen - is Completed");
	}

	/**
	 * Test 1.5: This test method covers below tests 1). C11923 - This method is
	 * used to verify the navigation to the about my home page. 2). C11924 - To
	 * verify that the application for residential account application will display
	 * 'About My Home' tab
	 */
	public void verifyNavToAboutMyHome(SoftAssert softAssert) {
		// Logging
		log.info("Test Case : C11923 - About My Home | To verify that user"
				+ " is able to redirect on about my home tab.");
		log.info("Test Case execution for - verifyNavToAboutMyHome - is Initiated");
		String actPageHead = getStep3PageHeaderLabel();
		String expPageHead = onboardingTextProp.getPropValue("txtLblAboutMyHomePageHeadingDbp");
		softAssert.assertEquals(actPageHead, expPageHead, "Welcome pop-up step 3 heading not matched.");
		softAssert.assertTrue(isSubmit_AhmButtonVisible(), "Submit button on about my home page is not displaying.");
		softAssert.assertTrue(isSubmitAboutMyBusinessPopUpVisible(),
				"Cancel button is not displaying on the about my home page.");

		clickSubmitAboutMyBusinessPopUp();
		// Logging
		log.info("Test Case : C11923 - About My Home | To verify that"
				+ " user is able to redirect on about my home tab.");
		log.info("Test Case execution for - verifyNavToAboutMyHome - is Completed");
	}

	/**
	 * Test 2.1: This test method verifies below tests: a). C11968 - Notification
	 * Preference | To verify the flow when user disagree TCPA compliance.
	 */
	public void verifyTCPANotDisplaying(SoftAssert softAssert) {
		// Logging
		log.info("Test Case : C11968 - Notification Preference | "
				+ "To verify the flow when user disagree TCPA compliance.");
		log.info("Test Case execution for - verifyAppOnboardWithoutTCPACompliance - is Initiated");
		// Click save and continue button
		clickSaveContinue_SmaButton();
		String actToastMsg = getToastMessage();
		String expToastMsg = onboardingTextProp.getPropValue("txtLblNotificationPrefSavedAos");
		softAssert.assertEquals(actToastMsg, expToastMsg, "Successful saved toast message not matched.");
		// Verifying that tcpa compliance popup is not displaying and user directly
		// navigates to about my home page
		softAssert.assertFalse(islblAcceptNotificationTermsVisible(),
				"TCPA compliance pop up is still displaying but whereas Text and Ivr checkbox are not checked.");
		softAssert.assertTrue(islbl_PageHeader_AHMvisible(), "About my home page is not getting displayed.");
		// Logging
		log.info("Test Case : C11968 - Notification Preference | To verify the flow when "
				+ "user disagree TCPA compliance.");
		log.info("Test Case execution for - verifyAppOnboardWithoutTCPACompliance - is Initiated");
	}

	/**
	 * This method is used to check the text and ivr check boxes.
	 */
	public void checkTCPACompliance() {
		// Check the outage text, billing mail, budget Ivr checkbox if unchecked
		if (!checkCbOutageText()) {
			clickCbOutageText();
		}
		if (!checkCbBillingEmail()) {
			clickCbBillingEmail();
		}
		if (!checkCbBudgetIvr()) {
			clickCbBudgetIvr();
		}
		clickSaveContinue_SmaButton();
		pause(500);
	}

	/**
	 * Test 4.1: This test method verifies below tests: 1). C11956 - Notification
	 * Preference | To verify the functionality of SKIP button
	 */
	public void verifySkipBtnFeatureOnSetPrefScreen(SoftAssert sAssert) {
		// Logging
		log.info("Test Case : C11921 - Notification Preference | " + "To verify the functionality of SKIP button.");
		log.info("Test Case execution for - verifySkipBtnFeatureOnSetPrefScreen - " + "is initiated");
		String expSetMyAlertsPageHeading = onboardingTextProp.getPropValue("txtLblSetYourAlertPopupHeadingDbp");

		sAssert.assertEquals(getLablSetYourAlertPopupHeading(), expSetMyAlertsPageHeading);
		sAssert.assertTrue(isButtonSkipSetMyPrefVisible(), "Skip set my pref button is not visible");
		clickButtonSkipSetMyPref();
		pause(1000);
		// Logging
		log.info("Test Case : C11921 - Notification Preference | To verify" + " the functionality of SKIP button.");
		log.info("Test Case execution for - verifySkipBtnFeatureOnSetPrefScreen - is completed.");
	}

	public HashMap<String, String> getRegistrationData(String addressType, String regStatus) throws SQLException {
		ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.getRegData(addressType, regStatus));
		while (resultSet.next()) {
			hashMap.put("utilityAccountNumber", resultSet.getString("utilityaccountnumber"));
			hashMap.put("zipCode", resultSet.getString("zipcode"));
			hashMap.put("customarID", resultSet.getString("customerid"));
		}
		return hashMap;
	}

	/**
	 * Test 4.2: This test method verifies below tests: 1) C11939 - About My
	 * Business | To verify that user is able to redirect on About My Business tab
	 * 2) C11940 - About My Business | To verify that the application for business
	 * account application will display 'About My Business' tab.
	 */
	public void verifyNavToAboutMyBusiness(SoftAssert softAssert) {
		// Logging
		log.info("Test Case : C11939 | C11940 - About My Business | To verify that user"
				+ " is able to redirect on About My Business tab.");
		log.info("Test Case execution for - verifyNavToAboutMyBusiness - is Initiated");
		String actPageHeading = getStep3PageHeaderLabel();
		String expPageHeading = onboardingTextProp.getPropValue("txtLblAboutMyBusinessPageHeadingDbp");
		softAssert.assertEquals(actPageHeading, expPageHeading, "About my business page heading not matched");
		softAssert.assertTrue(isSubmit_AhmButtonVisible(), "Submit button on about my home page is not displaying");
		softAssert.assertTrue(isCancelButtonVisible(), "Submit button on about my home page is not displaying");
		// Logging
		log.info("Test Case : C11939 - About My Business | To verify "
				+ "that user is able to redirect on About My Business tab.");
		log.info("Test Case execution for - verifyNavToAboutMyBusiness - is Completed");
	}

	/**
	 * Test 4.3: This test method verifies below tests: 1). C11941 - About My
	 * Business | To verify the objects on About My Business screen. 2). C11944 -
	 * About My Business | To verify the information displayed on information icon
	 * on field 3). C11948 - About My Business | Fields type dropdown for fields
	 * available in About My Business screen. 4). C11949 - About My Business |
	 * Fields type drop-down for fields available in About My Business screen. 5).
	 * C11950 - About My Business | To verify Toggle button of field listed Test
	 * case objective fields type toggle button for fields available in About My
	 * Business screen. 6). C11954 - About My Business | To verify the tool tip on
	 * about my business screen. 7). C11947 - About My Business | Fields should only
	 * accept the numeric values.
	 *
	 * @param softAssert
	 */
	public void verifyAboutMyBusinessContent(SoftAssert softAssert, Customer customer) {
		// Logging
		log.info("Test Case : C11941, C11944, C11948, C11949 "
				+ ", C11950, C11954, C11947 - About My Business | To verify the objects on"
				+ " About My Business screen.");
		log.info("Test Case execution for - verifyAboutMyBusinessContent - is Initiated");
		String inputMixedString = "NUM12";
		String expInputString = "12";
		// Service account number field
		String expAccountNoValue = customer.getUtilityAccountNumber();
		String actAccountNoValue = gettxtAccNoAboutMyBusiness();
		softAssert.assertEquals(actAccountNoValue, expAccountNoValue, "Service account number value not matched");
		String actAccountNo = getlblAccountNoAboutMyBusiness();
		String expAccountNo = onboardingTextProp.getPropValue("txtLblServiceAccountNumberAos");
		softAssert.assertEquals(actAccountNo, expAccountNo, "Service account number label not matched");
		// Business size field validation
		String actBusinessSizeLabel = getLabelBusinessSize();
		String expBusinessSizeLabel = onboardingTextProp.getPropValue("txtLblBusinessSizeAos");
		softAssert.assertEquals(actBusinessSizeLabel, expBusinessSizeLabel, "Business size field label not matched");
		// Business size field tooltip verification
		String actBusinessSizeToolTip = getAttributelstBusinessSize("title");
		String expBusinessSizeToolTip = "Business Size";
		softAssert.assertEquals(actBusinessSizeToolTip, expBusinessSizeToolTip,
				"Business size field tooltip not matched");
		List<String> actBusinessSizeOptions = getTextlstBusinessSizeList();
		List<String> expBusinessSizeOptions = onboardingTextProp.getMultipleRbTextValues("txtBusinessSizeDdOptionsAos");
		int counter = 0;
		if (actBusinessSizeOptions.size() == expBusinessSizeOptions.size()) {
			for (String actual : actBusinessSizeOptions) {
				for (int i = 0; i < expBusinessSizeOptions.size(); i++) {
					String act = actual.trim();
					String expected = actBusinessSizeOptions.get(i).trim();
					if (act.equals(expected)) {
						counter++;
					}
				}
			}
			softAssert.assertEquals(actBusinessSizeOptions.size(), counter,
					"Business size dropdown options are not matched.");
		} else {
			softAssert.assertTrue(false, "Business size dropdown options are not" + " matched with the expected");
			log.info("Business size dropdown options are not matched " + "with the expected");
		}
		// Business type field validation
		String actBusinessTypeLabel = getLabelBusinessType();
		String expBusinessTypeLabel = onboardingTextProp.getPropValue("txlLblBusinessTypeAos");
		softAssert.assertEquals(actBusinessTypeLabel, expBusinessTypeLabel, "Business type field label not matched");
		// Business type field tooltip verification
		String actBusinessTypeToolTip = getAttributeLabelBusinessType("title");
		String expBusinessTypeToolTip = onboardingTextProp.getPropValue("txlLblBusinessTypeAos");
		softAssert.assertEquals(actBusinessTypeToolTip, expBusinessTypeToolTip,
				"Business type field tooltip not matched");
		List<String> actBusinessTypeOptions = getTextlstBusinessTypeList();
		List<String> expBusinessTypeOptions = onboardingTextProp.getMultipleRbTextValues("txtBusinessTypeDdOptionsAos");
		counter = 0;
		if (actBusinessTypeOptions.size() == expBusinessTypeOptions.size()) {
			for (String actual : actBusinessTypeOptions) {
				for (int i = 0; i < expBusinessTypeOptions.size(); i++) {
					String act = actual.trim();
					String expected = actBusinessTypeOptions.get(i).trim();
					if (act.equals(expected)) {
						counter++;
					}
				}
			}
			softAssert.assertEquals(actBusinessTypeOptions.size(), counter,
					"Business type dropdown options are not matched.");
		} else {
			softAssert.assertTrue(false, "Business type dropdown options are" + " not matched with the expected");
			log.info("Business type dropdown options are not matched" + " with the expected");
		}
		// Employees in the workplace
		String actNoOfEmployee = getLabelNoEmployeesWorking();
		String expNoOfEmployee = onboardingTextProp.getPropValue("txtLblNoOfEmployeeAos");
		softAssert.assertEquals(actNoOfEmployee, expNoOfEmployee,
				"Number of employees working field label not matched");
		// Employees in the workplace field tooltip verification
		String actEmpWorkingToolTip = getAttributeNoEmployeesWorking("title");
		String expEmpWorkingToolTip = onboardingTextProp.getPropValue("txtLblNoOfEmployeeTitleAos");
		softAssert.assertEquals(actEmpWorkingToolTip, expEmpWorkingToolTip,
				"Employees in the workplace field tooltip not matched");
		// Verifying Employees in workplace fields accepts only numeric values
		gettxtNoEmployeesWorking("200");
		pause(200);
		String actValueNoOfEmpField = getAttributeNoEmployeesWorking("value");
		softAssert.assertEquals(actValueNoOfEmpField, "200",
				"Employees in workplace fields accepting alphanumeric values");
		// Office space area field label verification
		String actOfficeSpaceArea = getLabelOfficeSpaceArea();
		String expOfficeSpaceArea = onboardingTextProp.getPropValue("txtLblOfficeSpaceAreaAos");
		softAssert.assertEquals(actOfficeSpaceArea, expOfficeSpaceArea, "Office space area label not matched.");
		// Office space area field tooltip verification
		String actOfficeSpaceAreaToolTip = getAttributetxtOfficeSpaceArea("title");
		String expOfficeSpaceAreaToolTip = onboardingTextProp.getPropValue("txtLblOfficeSpaceAreaAos");
		softAssert.assertEquals(actOfficeSpaceAreaToolTip, expOfficeSpaceAreaToolTip,
				"Office space area field tooltip not matched");
		// Verifying Office space area fields accepts only numeric values
		enterLabelOfficeSpaceArea("1500");
		pause(200);
		String actValueOfficeSpaceField = getAttributetxtOfficeSpaceArea("value");
		softAssert.assertEquals(actValueOfficeSpaceField, "1500",
				"Office space area fields accepting alphanumeric values");
		// Lot size of the workplace
		String actLotSizeLabel = getLabelOfficeSpaceArea();
		String expLotSizeLabel = onboardingTextProp.getPropValue("txtLblLotSizeAreaAos");
		softAssert.assertEquals(actLotSizeLabel, expLotSizeLabel, "Lot size field label not matched.");
		// Lot size of the workplace field tooltip verification
		String actLotSizeToolTip = getAttributeLabelLotSizeAmb("title");
		String expLotSizeToolTip = onboardingTextProp.getPropValue("txtLblLotSizeAreaAos");
		softAssert.assertEquals(actLotSizeToolTip, expLotSizeToolTip,
				"Lot size of the workplace field tooltip not matched");
		// Verifying Lot size fields accepts only numeric values
		enterLabelLotSizeAmb("1400");
		pause(200);
		String actValueLotSizeField = getAttributeLabelLotSizeAmb("value");
		softAssert.assertEquals(actValueLotSizeField, "1400", "Lot size fields accepting alphanumeric values");
		// No of floors field validation
		String actNoOfFloorsLabel = getLabelNoFloors();
		String expNoOfFloorsLabel = onboardingTextProp.getPropValue("txtLblNoOfFloorsAos");
		softAssert.assertEquals(actNoOfFloorsLabel, expNoOfFloorsLabel, "No of floors field label not matched.");
		// No of floors field tooltip verification
		String actNoOfFloorsToolTip = getAttributeLabelNoFloors("title");
		String expNoOfFloorsToolTip = onboardingTextProp.getPropValue("txtLblNoOfFloorsAos");
		softAssert.assertEquals(actNoOfFloorsToolTip, expNoOfFloorsToolTip, "No of floors field tooltip not matched");
		// Verifying number of floors fields accepts only numeric values
		enterLabelNoFloors("4");
		pause(200);
		String actValueFloorsField = getAttributeLabelNoFloors("value");
		softAssert.assertEquals(actValueFloorsField, "4", "Number of floors fields accepting alphanumeric values");
		// No of Restrooms field validation
		String actNoOfRestroomsLabel = getLabelNoRestrooms();
		String expNoOfRestroomsLabel = onboardingTextProp.getPropValue("txtLblNoOfRestroomsToolTipAos");
		softAssert.assertEquals(actNoOfRestroomsLabel, expNoOfRestroomsLabel,
				"No of restrooms field label not matched.");
		// No of Restrooms field tooltip verification
		String actNoOfRestroomsToolTip = getAttributeLabelNoRestrooms("title");
		String expNoOfRestroomsToolTip = onboardingTextProp.getPropValue("txtLblNoOfRestroomsTitleAos");
		softAssert.assertEquals(actNoOfRestroomsToolTip, expNoOfRestroomsToolTip,
				"No of restrooms field tooltip not matched");
		// Verifying number of restrooms fields accepts only numeric values
		/*
		 * enterLabelNoRestrooms("20"); pause(200); String actValueRestroomsField =
		 * pageUtil.getAttributeValue(appOnBoardingPage .getTxtNoRestroomsAos(),
		 * "value"); softAssert.assertEquals(actValueRestroomsField, "20",
		 * "Number of Restrooms fields accepting alphanumeric values"); // Landscape
		 * area field validation String actLandscapeAreaLabel =
		 * pageUtil.getObjectLabel(appOnBoardingPage .getLblLandscapeAreaAmbAos());
		 * String expLandscapeAreaLabel =
		 * textPropJson.getStringJsonValue("txtLblLandscapeAreaAmbAos");
		 * softAssert.assertEquals(actLandscapeAreaLabel, expLandscapeAreaLabel,
		 * "Landscape area field label not matched."); // Landscape area field tooltip
		 * verification String actLandscapeAreaToolTip =
		 * pageUtil.getAttributeValue(appOnBoardingPage .getTxtLandscapeAreaAmbAos(),
		 * "title"); String expLandscapeAreaToolTip =
		 * textPropJson.getStringJsonValue("txtLblLandscapeAreaAmbAos");
		 * softAssert.assertEquals(actLandscapeAreaToolTip, expLandscapeAreaToolTip,
		 * "Landscape area field tooltip not matched");
		 */
		// Verifying landscape area fields accepts only numeric values
		/*
		 * pageUtil.enterTextValue(appOnBoardingPage.getTxtLandscapeAreaAmbAos(),
		 * "1800"); pause(200); String actValueLandscapeAreaField =
		 * pageUtil.getAttributeValue(appOnBoardingPage .getTxtLandscapeAreaAmbAos(),
		 * "value"); softAssert.assertEquals(actValueLandscapeAreaField, "1800",
		 * "Landscape area fields accepting alphanumeric values. " + "\nExp : " +
		 * expInputString + "\nAct : " + actValueLandscapeAreaField ); // Solar panel
		 * field validation String actHaveSolarCellLabel =
		 * pageUtil.getObjectLabel(appOnBoardingPage.getLblSolarCellPanelsAmbAos());
		 * String expHaveSolarCellLabel =
		 * textPropJson.getStringJsonValue("txtLblHaveSolarCellPanelAos");
		 * softAssert.assertEquals(actHaveSolarCellLabel, expHaveSolarCellLabel,
		 * "Solar cell panel field label not matched."); // Verifying Solar panel field
		 * accepts only numeric values
		 * pageUtil.enterTextValue(appOnBoardingPage.getTxtSolarCellPanelsAos(), "8");
		 * pageUtil.pause(200); String actValueSolarCellField =
		 * pageUtil.getAttributeValue(appOnBoardingPage.getTxtSolarCellPanelsAos(),
		 * "value"); softAssert.assertEquals(actValueSolarCellField, "8",
		 * "Solar Panel fields accepting alphanumeric values." + "\nExp : " +
		 * inputMixedString + "\nAct : " + actValueSolarCellField); // Have HVAC field
		 * validation String actHaveHVACLabel =
		 * pageUtil.getObjectLabel(appOnBoardingPage.getLblHaveHVACAos()); String
		 * expHaveHVACLabel = textPropJson.getStringJsonValue("txtLblHaveHVACAos");
		 * softAssert.assertTrue(actHaveHVACLabel.contains(expHaveHVACLabel),
		 * "Have HVAC field label not matched."); boolean haveHVAC =
		 * pageUtil.getWebElement(appOnBoardingPage.getTglHaveHVACAos()).isSelected();
		 * softAssert.assertTrue(haveHVAC, "Have HVAC toggle button is not working"); //
		 * Have electric system field validation String actHaveElectricalSystemLabel =
		 * pageUtil.getObjectLabel(appOnBoardingPage.getLblElectricSystemAos()); String
		 * expHaveElectricalSystemLabel =
		 * textPropJson.getStringJsonValue("txtLblHaveElectricalSystemAos");
		 * softAssert.assertTrue(actHaveElectricalSystemLabel.contains(
		 * expHaveElectricalSystemLabel),
		 * "Have electric system field label not matched."); boolean
		 * bHaveElectricSystemStatus =
		 * pageUtil.getWebElement(appOnBoardingPage.getTglElectricSystemAos())
		 * .isSelected(); softAssert.assertTrue(bHaveElectricSystemStatus,
		 * "Have Electric system toggle button is not working"); // Plumbing water
		 * system field validation String actPlumbingWaterLabel =
		 * pageUtil.getObjectLabel(appOnBoardingPage.getLblPlumbingWaterSystemAos());
		 * String expPlumbingWaterLabel =
		 * textPropJson.getStringJsonValue("txtLblHavePlumbingWaterSystemAos");
		 * softAssert.assertTrue(actPlumbingWaterLabel.contains(expPlumbingWaterLabel),
		 * "Have plumbing water system field label not matched."); boolean
		 * havePlumbingWaterSystemStatus =
		 * pageUtil.getWebElement(appOnBoardingPage.getTglPlumbingWaterSystemAos())
		 * .isSelected(); softAssert.assertTrue(havePlumbingWaterSystemStatus,
		 * "Have Plumbing water system toggle button is not working"); // Have server
		 * room field validation String actHaveServerLabel =
		 * pageUtil.getObjectLabel(appOnBoardingPage.getLblHaveServerRoomAos()); String
		 * expHaveServerLabel =
		 * textPropJson.getStringJsonValue("txtLblHaveServerRoomAos");
		 * softAssert.assertTrue(actHaveServerLabel.contains(expHaveServerLabel),
		 * "Have server room field label not matched."); boolean haveServerRoomStatus =
		 * pageUtil.getWebElement(appOnBoardingPage.getTglHaveServerRoomAos()).
		 * isSelected(); softAssert.assertTrue(haveServerRoomStatus,
		 * "Have Server room toggle button is not working"); // Have swimming pool field
		 * validation String actHaveSwimmingPoolLabel =
		 * pageUtil.getObjectLabel(appOnBoardingPage.getLblHaveSwimmingPoolAmbAos());
		 * String expHaveSwimmingPoolLabel =
		 * textPropJson.getStringJsonValue("txtLblHaveSwimmingPool");
		 * softAssert.assertTrue(actHaveSwimmingPoolLabel.contains(
		 * expHaveSwimmingPoolLabel), "Have swimming pool field label not matched.");
		 * boolean haveSwimmingPoolStatus =
		 * pageUtil.getWebElement(appOnBoardingPage.getTglHaveSwimmingPoolAmbAos())
		 * .isSelected(); softAssert.assertTrue(haveSwimmingPoolStatus,
		 * "Have Swimming pool toggle button is not working");
		 */
		// Logging
		log.info("Test Case : C11941, C11944, C11948, C11949 "
				+ ", C11950, C11954, C11947 - About My Business | To verify the objects on "
				+ "About My Business screen.");
		log.info("Test Case execution for - verifyAboutMyBusinessContent - is Completed");
	}

}

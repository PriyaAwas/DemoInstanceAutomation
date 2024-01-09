package sew.ai.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import sew.ai.api.endpoints.auth.RegistrationEndpoints;
import sew.ai.config.ModelsConfiguration;
import sew.ai.config.SCPConfiguration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.props.ResourcePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.helpers.testrail.TestRail;
import sew.ai.models.Customer;
import sew.ai.models.User;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.HomeSteps;
import sew.ai.steps.scp.LoginSteps;
import sew.ai.steps.scp.MyProfileSteps;
import sew.ai.steps.scp.OnboardingScreenSteps;
import sew.ai.steps.scp.RegistrationSteps;
import sew.ai.utils.DataBaseUtils;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class MyProfileTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(MyProfileTests.class);

	private MyProfileSteps myProfileSteps;
	HashMap<String, String> hashMap = new HashMap();
	HashMap<String, String> authMap = new HashMap();

	@TestRail(testCaseId = { 74482, 74483, 74484, 74487, 74499 })
	@FrameworkAnnotations(author = { "Gaurav Saxena" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(priority = 45, description = "To verify that UI And Objects on the My Account-> Profile Page.")
	public void verifyProfileInformationPageUI() {
		log.info("To verify that UI And Objects on the My Account-> Profile Page. "
				+ "C74482, C74483, C74484, C74487, C74499");
		SoftAssert softAssert = new SoftAssert();

		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		User user = SCPConfiguration.user;
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication("patricupdate","Demo@123");
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();

		// inti MyProfileSteps
		myProfileSteps = new MyProfileSteps(driver);
		// Call verifyProfileInformationPageUI Method
		myProfileSteps.verifyProfileInformationPageUI(softAssert);

		// Assert all the soft verification.
		// softAssert.assertAll();
		log.info("Test Case execution for - verifyProfileInformationPageUI - is Completed.");
	}
	
//	@TestRail(testCaseId = { 74482, 74483, 74484, 74487, 74499 })
//	@FrameworkAnnotations(author = { "Gaurav Saxena" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
//	@Test(priority = 45, description = "To verify that UI And Objects on the My Account-> Profile Page.")
//	public void verifyProfileInformationPageUI() {
//		log.info("To verify that UI And Objects on the My Account-> Profile Page. "
//				+ "C74482, C74483, C74484, C74487, C74499");
//		SoftAssert softAssert = new SoftAssert();
//
//		// Login into The Application and Navigate to My Profile Page
//		LoginSteps loginSteps = new LoginSteps(driver);
//		User user = SCPConfiguration.user;
//		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
//		HomeSteps homeSteps = new HomeSteps(driver);
//		homeSteps.navigateToMyProfilePage();
//
//		// inti MyProfileSteps
//		myProfileSteps = new MyProfileSteps(driver);
//		// Call verifyProfileInformationPageUI Method
//		myProfileSteps.verifyProfileInformationPageUI(softAssert);
//
//		// Assert all the soft verification.
//		// softAssert.assertAll();
//		log.info("Test Case execution for - verifyProfileInformationPageUI - is Completed.");
//	}

	
	//Modified by Ajit
	@TestRail(testCaseId = { 74703, 74704, 74718, 74719 })
	@FrameworkAnnotations(author = { "Gaurav Saxena" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(priority = 46, description = "To verify options available for Primary and Alternate Contact Type dropdown.")
	public void verifyPersonalInfoSection() {
		log.info("To verify options available for Primary and Alternate Contact Type dropdown."
				+ "C74703,C74704,C74718,C74719");
		SoftAssert softAssert = new SoftAssert();

		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		User user = SCPConfiguration.user;
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();
		// inti MyProfileSteps
		myProfileSteps = new MyProfileSteps(driver);
		// Call verifyProfileInformationPageUI Method
		myProfileSteps.verifyPersonalInfoSection(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyPersonalInfoSection - is Completed.");
	}

	@TestRail(testCaseId = { 74705, 74706, 74707, 74709, 74710, 74711, 74712, 74713, 74714, 80279, 113348 })
	@FrameworkAnnotations(author = { "Gaurav Saxena" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(priority = 47, description = "To verify validation message for Invalid Primary Contact Number,  Alternate contact Number , Primary Email Address and Alternate Email Address on My Account-> Profile Page.")
	public void verifyValidationsProfileInformation() {
		log.info(
				"To verify validation message for Invalid Primary Contact Number,  Alternate contact Number , Primary Email Address and Alternate Email Address on My Account-> Profile Page."
						+ "C74705,C74706, C74707, C74709, C74710, C74711, C74712, C74713, C74714, C80279,C113348");
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		User user = SCPConfiguration.user;
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();
		// inti MyProfileSteps
		myProfileSteps = new MyProfileSteps(driver);
		// verifyValidationsProfileInformation method Call
		myProfileSteps.verifyValidationsProfileInformation(softAssert);
		// softAssert.assertAll();
		log.info("Test Case execution for - verifyValidationsProfileInformation - is Completed.");
	}

//	@TestRail(testCaseId = { 74723 })
//	@FrameworkAnnotations(author = { "Gaurav Saxena" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
//	@Test(priority = 48, description = "To verify options available for Primary and Alternate Contact Type dropdown.")
//	public void verifyContactTypeProfileInformation() {
//		log.info("To verify options available for Primary and Alternate Contact Type dropdown" + "C74723");
//		SoftAssert softAssert = new SoftAssert();
//		// Login into The Application and Navigate to My Profile Page
//		LoginSteps loginSteps = new LoginSteps(driver);
//		User user = SCPConfiguration.user;
//		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
//		HomeSteps homeSteps = new HomeSteps(driver);
//		homeSteps.navigateToMyProfilePage();
//		// inti MyProfileSteps
//		myProfileSteps = new MyProfileSteps(driver);
//		// verifyValidationsProfileInformation method Call
//		myProfileSteps.verifyContactTypeProfileInformation(softAssert);
//		log.info(
//				"Test Case execution for- To verify options available for Primary and Alternate Contact Type dropdown.");
//	}
	
	//Modified by Ajit
	@TestRail(testCaseId = { 74723 })
	@FrameworkAnnotations(author = { "Gaurav Saxena" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(priority = 48, description = "To verify options available for Primary and Alternate Contact Type dropdown.")
	public void verifyContactTypeProfileInformation() {
		log.info("To verify options available for Primary and Alternate Contact Type dropdown" + "C74723");
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		User user = SCPConfiguration.user;
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication("patricupdate","Demo@123");
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();
		// inti MyProfileSteps
		myProfileSteps = new MyProfileSteps(driver);
		// verifyValidationsProfileInformation method Call
		myProfileSteps.verifyContactTypeProfileInformation(softAssert);
		log.info(
				"Test Case execution for- To verify options available for Primary and Alternate Contact Type dropdown.");
	}

//	@TestRail(testCaseId = { 74716, 74717, 74721, 74722, 80276, 80277, 80278 })
//	@FrameworkAnnotations(author = { "Gaurav Saxena" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
//	@Test(priority = 49, description = "To verify that user should be able to login from logout page.")
//	public void verifyUpdateProfileInformation() throws SQLException {
//		log.info("To verify that user should be able to login from logout page."
//				+ "C74716, C74717, C74721, C74722, C80276, C80277, C80278");
//		SoftAssert softAssert = new SoftAssert();
//		// Login into The Application and Navigate to My Profile Page
//		LoginSteps loginSteps = new LoginSteps(driver);
//		User user = SCPConfiguration.user;
//		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
//		HomeSteps homeSteps = new HomeSteps(driver);
//		homeSteps.navigateToMyProfilePage();
//		// inti MyProfileSteps
//		myProfileSteps = new MyProfileSteps(driver);
//		// verifyValidationsProfileInformation method Call
//		myProfileSteps.verifyUpdateProfileInformation(softAssert);
//		softAssert.assertAll();
//		log.info("Test Case execution for-verifyUpdateProfileInformation is completed");
//	}
	
	//Modified by Ajit
	@TestRail(testCaseId = { 74716, 74717, 74721, 74722, 80276, 80277, 80278 })
	@FrameworkAnnotations(author = { "Gaurav Saxena" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(priority = 49, description = "To verify that user should be able to login from logout page.")
	public void verifyUpdateProfileInformation() throws SQLException {
		log.info("To verify that user should be able to login from logout page."
				+ "C74716, C74717, C74721, C74722, C80276, C80277, C80278");
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		User user = SCPConfiguration.user;
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication("patricupdate","Demo@123");
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();
		// inti MyProfileSteps
		myProfileSteps = new MyProfileSteps(driver);
		// verifyValidationsProfileInformation method Call
		myProfileSteps.verifyUpdateProfileInformation(softAssert);
		softAssert.assertAll();
		log.info("Test Case execution for-verifyUpdateProfileInformation is completed");
	}

	@TestRail(testCaseId = { 74725, 74740, 74742, 80322, 80324 })
	@FrameworkAnnotations(author = { "Gaurav Saxena" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(priority = 50, description = "To verify that Change User ID popup should contain the following objects:")
	public void validateChangeUsername() {
		log.info("To verify that Change User ID popup should contain the following objects:"
				+ "C74725, C74740, C74742, C80322, C80324");
		// not in testRail C74740
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		User user = SCPConfiguration.user;
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();
		// inti MyProfileSteps
		myProfileSteps = new MyProfileSteps(driver);
		myProfileSteps.validateChangeUsername(softAssert);
		softAssert.assertAll();
		log.info("Test Case execution for-validateChangeUsername is completed ");
	}

	@TestRail(testCaseId = { 74699 })
	@FrameworkAnnotations(author = { "Gaurav Saxena" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(priority = 56, description = "Verify change username link is not available if email id as username is set in CSP.")
	public void verifyChangeUsernameNotAvailable() throws SQLException {
		log.info("Verify change username link is not available if email id as username is set in CSP." + "C74699");
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		User user = SCPConfiguration.user;
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();
		// inti MyProfileSteps
		myProfileSteps = new MyProfileSteps(driver);
		myProfileSteps.verifyChangeUsernameNotAvailable(softAssert);
		softAssert.assertAll();
		log.info("Test Case Execution for -verifyChangeUsernameNotAvailable is completed");
	}

	@TestRail(testCaseId = { 74744, 74745, 74746, 74747, 74748, 74749, 74754, 74732, 74733, 80323, 80326 })
	@FrameworkAnnotations(author = { "Gaurav Saxena" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(priority = 51, description = "This method is to verify the change username fields.")
	public void verifyChangeUsernameAcceptanceCriteria() throws SQLException {
		log.info("This method is to verify the change username fields."
				+ "C74744, C74745, C74746, C74747, C74748, C74749, C74754, C74732, C74733, C80323, C80326");
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		User user = SCPConfiguration.user;
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();
		// inti MyProfileSteps
		myProfileSteps = new MyProfileSteps(driver);
		myProfileSteps.verifyChangeUsernameAcceptanceCriteria(softAssert);
		softAssert.assertAll();
		log.info("Test Case Execution for -verifyChangeUsernameAcceptanceCriteria is completed");
	}

	@TestRail(testCaseId = { 116766, 74525, 74526, 74527, 113347, 113350, 116765, 119983, 113351 })
	@FrameworkAnnotations(author = { "Gaurav Saxena" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(priority = 57, description = "To verify that Default Service Address and its radio button selection, On changing default address it gets updated.")
	public void verifyDefaultServiceAddress() {
		log.info(
				"To verify that Default Service Address and its radio button selection, On changing default address it gets updated."
						+ "116766, 74525, 74526, 74527,113347,113350,116765,119983,113351");
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		User user = SCPConfiguration.user;
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
		
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();
		// inti MyProfileSteps
		myProfileSteps = new MyProfileSteps(driver);
		myProfileSteps.verifyDefaultServiceAddress(softAssert);
		softAssert.assertAll();
		log.info("Test Execution for - verifyDefaultServiceAddress is completed");
	}

	@TestRail(testCaseId = { 93650, 93651, 93653, 93654, 93655, 124842, 124843, 124844, 124845, 124846, 124847, 124848,
			124849, 124850, 124851, 130819, 132973 })
	@FrameworkAnnotations(author = { "Gaurav Saxena" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(priority = 52, description = "Verify that User is able to download personal information or Data.")
	public void verifyDownloadMyPersonalData() throws SQLException, IOException {
		log.info("Verify that User is able to download personal information or Data."
				+ "C93650, C93651, C93653, C93654, C93655,C124842,C124843,C124844,C124845,C124846,C124847,C124848,C124849,C124850,C124851,C130819,C132973");
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		User user = SCPConfiguration.user;
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();
		// inti MyProfileSteps
		myProfileSteps = new MyProfileSteps(driver);
		myProfileSteps.verifyDownloadMyPersonalData(softAssert);
		myProfileSteps.verifyDownloadMyDataPersonalInfoValidation(softAssert, "Personal Information");
		myProfileSteps.verifyDownloadMyDataMailingAddressValidation(softAssert, "Mailing Addresses");
		softAssert.assertAll();
		log.info("Test Execution for - Verify that User is able to download personal information or Data is Completed");
	}

	@TestRail(testCaseId = { 93652 })
	@FrameworkAnnotations(author = { "Gaurav Saxena" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(priority = 55, description = "Verify that newly registered user is able to download pessonal data.")
	public void verifyNewUserDownloadMyPersonalData() throws Exception {
		log.info("Verify that newly registered user is able to download pessonal data." + "C93652");
		SoftAssert softAssert = new SoftAssert();

		// Registration From API

		RegistrationEndpoints registrationEndpoints = new RegistrationEndpoints();
		Customer customer = ModelsConfiguration.readCustomerConfig().getCustomerByName("John Doe");
		String resourcePathURI = ResourcePaths.SCP_COMMON_PATH_URI;
		OnboardingScreenSteps onboardingScreenSteps = new OnboardingScreenSteps(driver);
		hashMap = onboardingScreenSteps.getRegistrationData("1", "3");
		authMap = registrationEndpoints.buildRequestSetRegistrationAdd(resourcePathURI, customer, hashMap);
		registrationEndpoints.hitSetRegistration();
		// Activation for the newly Registred User
		String customerVerificationID = registrationEndpoints.getAPIResponseCustomerVerificationId();
		registrationEndpoints.buildRequestCustomerVerificationAdd(resourcePathURI, customerVerificationID);
		registrationEndpoints.hitCustVerificationRegistration();
		// Login with newly registered user by submitting OTP.
		String newUserName = authMap.get("userName");
		String newPass = authMap.get("password");
		onboardingScreenSteps.loginIntoTheApplication(newUserName, newPass);
		// Handle Welcome Screen by clicking Next Button
		
		/*DashboardSteps dashboardSteps = new DashboardSteps(driver);
		dashboardSteps.clickNextBtnOnWelcomePopup();
		dashboardSteps.clickSaveAndNextNotifPref();
		dashboardSteps.selectApartment();
		dashboardSteps.clickSaveMyHomeBtn();*/

		myProfileSteps = new MyProfileSteps(driver);
		myProfileSteps.verifyNewUserDownloadMyPersonalData(softAssert, newUserName, newPass);

		softAssert.assertAll();
	}

	@TestRail(testCaseId = { 93657, 93658, 93659, 93660, 93661, 93662, 93663, 93664 })
	@FrameworkAnnotations(author = { "Gaurav Saxena" }, category = { CategoryType.SANITY ,CategoryType.SCP_MYACCOUNT})
	@Test(priority = 54, description = "Verify that User is able to submit Delete My Profile Request.")
	public void verifyDeleteMyProfile() {
		log.info("Test Case execution for - verifyDeleteMyProfile - is Initiated."
				+ "C93657, C93658, C93659, C93660, C93661, C93662, C93663, C93664");
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		User user = SCPConfiguration.user;
		// loginSteps.populateLoginForm(user.getUserName(),user.getPassword());
		// loginSteps.clickSignInBtn();
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();
		myProfileSteps = new MyProfileSteps(driver);
		// TODO need to Rework on it
		myProfileSteps.verifyDeleteMyProfile(softAssert);

		softAssert.assertAll();
	}

	@TestRail(testCaseId = { 98066, 74647, 98068, 102662 })
	@FrameworkAnnotations(author = { "Gaurav Saxena" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(priority = 53, description = "C11444 - Web to verify that application will allow user to application in it's preferred time Zone")
	public void verifyTimeZoneSaveFunctionality() throws SQLException {
		log.info("Test Case execution for - verifyTimeZoneSaveFunctionality - is Initiated.");
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		User user = SCPConfiguration.user;
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();
		myProfileSteps = new MyProfileSteps(driver);
		myProfileSteps.verifyTimeZoneSaveFunctionality(softAssert);
		softAssert.assertAll();
	}

	@TestRail(testCaseId = { 98067, 102522 })
	@FrameworkAnnotations(author = { "Gaurav Saxena" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(priority = 53, description = "Web to verify that application will allow user to application in it's preferred time Zone.")
	public void verifyDefaultTimeZoneOfNewRegisteredUser() throws Exception {

       SoftAssert softAssert = new SoftAssert();
		// Registration From API

		RegistrationEndpoints registrationEndpoints = new RegistrationEndpoints();
		Customer customer = ModelsConfiguration.readCustomerConfig().getCustomerByName("John Doe");
		String resourcePathURI = ResourcePaths.SCP_COMMON_PATH_URI;
		OnboardingScreenSteps onboardingScreenSteps = new OnboardingScreenSteps(driver);
		hashMap = onboardingScreenSteps.getRegistrationData("1", "3");
		authMap = registrationEndpoints.buildRequestSetRegistrationAdd(resourcePathURI, customer, hashMap);
		registrationEndpoints.hitSetRegistration();
		// Activation for the newly Registred User
		String customerVerificationID = registrationEndpoints.getAPIResponseCustomerVerificationId();
		registrationEndpoints.buildRequestCustomerVerificationAdd(resourcePathURI, customerVerificationID);
		registrationEndpoints.hitCustVerificationRegistration();
		// Login with newly registered user by submitting OTP.
		String newUserName = authMap.get("userName");
		String newPass = authMap.get("password");
		onboardingScreenSteps.loginIntoTheApplication(newUserName, newPass);
		
		
		// dashboardSteps.clickNextBtnOnWelcomePopup();
		// dashboardSteps.clickSaveAndNextNotifPref();
		// dashboardSteps.selectApartment();
		// dashboardSteps.clickSaveMyHomeBtn();
		// by pass About My Home Page Popup
		

		ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getAllLinkedAccountAccounts(customer.getUserName()));
		int rsSize = 0;
		try {
			while (rs.next()) {
				rsSize++;
			}
		} catch (Exception e) {
			rsSize = 0;
			System.out.println("Account not FOUND in DB for this user.");
		}
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();
		myProfileSteps = new MyProfileSteps(driver);
		myProfileSteps.verifyDefaultTimeZoneOfNewRegisteredUser(softAssert);

	}

	@TestRail(testCaseId = { 74726, 74727, 74728, 74729, 74730, 74731, 80325 })
	@FrameworkAnnotations(author = { "Gaurav Saxena" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(priority = 53, description = "Verify validation "
			+ "message for change username and change username successfully.")
	public void validationMsgAndChangeUsername() {
		log.info("Test Case execution for - validationMsgAndChangeUsername - is Initiated.");
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		User user = SCPConfiguration.user;
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();
		myProfileSteps = new MyProfileSteps(driver);
		myProfileSteps.validationMsgAndChangeUsername(softAssert);
	}

	@TestRail(testCaseId = { 119982 })
	@FrameworkAnnotations(author = { "Gaurav Saxena" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(priority = 53, description = "To Verify that Select Account dropdown is not present for Profile page and Account Information submodule  as it contains only user-level details..")
	public void verifyDropdownOnLinkDeLinkAccounts() {
		log.info(
				"C119982 -To Verify that Select Account dropdown is not present for Profile page and Account Information submodule  as it contains only user-level details.");
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		User user = SCPConfiguration.user;
		loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
		myProfileSteps=new MyProfileSteps(driver);
		myProfileSteps.verifyDropdownOnLinkDeLinkAccounts(softAssert);
	}
}

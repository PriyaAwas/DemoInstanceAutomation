package demo.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.config.Configuration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.HomeSteps;
import sew.ai.steps.scp.LoginSteps;
import demo.steps.scp.MyProfileSteps;
import java.io.IOException;

public class MyProfileTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(MyProfileTests.class);

	private MyProfileSteps MyProfileSteps;

	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SMOKE, CategoryType.SCP_MYACCOUNT })
	@Test(priority = 1, description = "To verify that UI And Objects on the My Account-> Profile Page.")
	public void verifyMyProfileInformationPageUI() throws InterruptedException {
		ExtentLogger.logInfo("Test Case execution for - verifyMyProfileInformationPageU");
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();

		// inti MyProfileSteps
		MyProfileSteps = new MyProfileSteps(driver);
		// Call verifyProfileInformationPageUI Method
		MyProfileSteps.verifyMyProfileInformationPageUI(softAssert);

		// Assert all the soft verification.
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyMyProfileInformationPageUI - is Completed.");
	}

	@FrameworkAnnotations(author = { "Priya" }, category = { CategoryType.SANITY, CategoryType.SCP_MYACCOUNT })
	@Test(priority = 1, description = "To verify options available for Primary and Alternate Contact Type dropdown.")
	public void verifyPersonalInfoSection() {
		log.info("To verify options available for Primary and Alternate Contact Type dropdown.");
		SoftAssert softAssert = new SoftAssert();

		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();
		// inti MyProfileSteps
		MyProfileSteps = new MyProfileSteps(driver);
		// Call verifyProfileInformationPageUI Method
		MyProfileSteps.verifyPersonalInfoSection(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyPersonalInfoSection - is Completed.");
	}

	@FrameworkAnnotations(author = { "Priya" }, category = { CategoryType.SANITY, CategoryType.SCP_MYACCOUNT })
	@Test(priority = 1, description = "To verify validation message for Invalid Primary Contact Number,  Alternate contact Number , Primary Email Address and Alternate Email Address on My Account-> Profile Page.")
	public void verifyValidationsProfileInformation() {
		log.info("To verify validation message for Invalid Primary Contact Number,  "
				+ "Alternate contact Number , Primary Email Address and Alternate Email Address on My Account-> Profile Page.");
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();
		// inti MyProfileSteps
		MyProfileSteps = new MyProfileSteps(driver);
		// verifyValidationsProfileInformation method Call
		MyProfileSteps.verifyValidationsProfileInformation(softAssert);
		// softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyValidationsProfileInformation - is Completed.");
	}

	@FrameworkAnnotations(author = { "Priya" }, category = { CategoryType.SANITY, CategoryType.SCP_MYACCOUNT })
	@Test(priority = 1, description = "To verify options available for Primary and Alternate Contact Type dropdown.")
	public void verifyContactTypeProfileInformation() {
		log.info("To verify options available for Primary and Alternate Contact Type dropdown");
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);

		loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));

		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();
		// inti MyProfileSteps
		MyProfileSteps = new MyProfileSteps(driver);
		// verifyValidationsProfileInformation method Call
		MyProfileSteps.verifyContactTypeProfileInformation(softAssert);
		ExtentLogger.logInfo(
				"Test Case execution for- To verify options available for Primary and Alternate Contact Type dropdown.");
	}

	@FrameworkAnnotations(author = { "Priya" }, category = { CategoryType.SANITY, CategoryType.SCP_MYACCOUNT })
	@Test(priority = 1, description = "To verify that Change User ID popup should contain the following objects:")
	public void validateChangeUsername() {
		log.info("To verify that Change User ID popup should contain the following objects");
		// not in testRail C74740
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);

		loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));

		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();
		// inti MyProfileSteps
		MyProfileSteps = new MyProfileSteps(driver);
		MyProfileSteps.validateChangeUsername(softAssert);
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for-validateChangeUsername is completed ");
	}

	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY, CategoryType.SCP_MYACCOUNT })
	@Test(priority = 1, description = "To verify Negative Validation on the My Account-> Profile Page.")
	public void verifyNegativeValidationForMyProfilePage() throws  InterruptedException {
		ExtentLogger.logInfo("Test Case execution for - verifyNegativeValidationForMyProfilePage.");

		SoftAssert softAssert = new SoftAssert();

		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();

		// inti MyProfileSteps
		MyProfileSteps = new MyProfileSteps(driver);
		// Call verifyNegativeValidationForMyProfile Method
		MyProfileSteps.verifyNegativeValidationForMyProfile(softAssert);

		// Assert all the soft verification.
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyNegativeValidationForMyProfilePage - is Completed.");
	}

	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY, CategoryType.SCP_MYACCOUNT })
	@Test(priority = 1, description = "To verify Negative Validation on the My Account-> Profile Page.")
	public void verifyChangeUsernameAcceptanceCriteria(){
		log.info("This method is to verify the change username fields.");
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();
		// inti MyProfileSteps
		MyProfileSteps = new MyProfileSteps(driver);
		MyProfileSteps.verifyChangeUsernameAcceptanceCriteria(softAssert);
		softAssert.assertAll();
		log.info("Test Case Execution for -verifyChangeUsernameAcceptanceCriteria is completed");
	}

	@FrameworkAnnotations(author = { "Priya" }, category = { CategoryType.SANITY, CategoryType.SCP_MYACCOUNT })
	@Test(priority = 1, description = "Verify that User is able to download personal information or Data.")
	public void verifyDownloadMyProfilePersonalData() throws InterruptedException, IOException {
		ExtentLogger.logInfo("Test Case execution for - verifyDownloadMyProfilePersonalData");
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();
		// inti MyProfileSteps
		MyProfileSteps = new MyProfileSteps(driver);
		// Call verifyDownloadMyProfileData Method
		MyProfileSteps.verifyDownloadMyProfilePersonalData(softAssert);
		// MyProfileSteps.verifyDownloadMyDataPersonalInfoValidation(softAssert,"Personal
		// Information");
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyDownloadMyProfileData - is Completed.");
	}

	@FrameworkAnnotations(author = { "Priya" }, category = { CategoryType.SANITY, CategoryType.SCP_MYACCOUNT })
	@Test(priority = 1, description = "Verify that User is able to submit Delete My Profile Request.")
	public void verifyDeleteMyProfile() {
		log.info("Test Case execution for - verifyDeleteMyProfile - is Initiated.");
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();
		MyProfileSteps = new MyProfileSteps(driver);
		MyProfileSteps.verifyDeleteMyProfile(softAssert);
		softAssert.assertAll();
	}

	@FrameworkAnnotations(author = { "Priya" }, category = { CategoryType.SANITY, CategoryType.SCP_MYACCOUNT })
	@Test(priority = 1, description = " - Web to verify that application will allow user to application in it's preferred time Zone")
	public void verifyTimeZoneSaveFunctionality(){
		log.info("Test Case execution for - verifyTimeZoneSaveFunctionality - is Initiated.");
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();
		MyProfileSteps = new MyProfileSteps(driver);
		MyProfileSteps.verifyTimeZoneSaveFunctionality(softAssert);
		softAssert.assertAll();
	}

	@FrameworkAnnotations(author = { "Priya" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the Marketing Pref functionality of the Profile Page.")
	public void verifyMarketingPreferencesObjects() {
		log.info("To verify the tests with the below test case id's");

		SoftAssert softAssert = new SoftAssert();
		// make Object For Navigate to the My Profile Page
		// Login into The Application
		LoginSteps loginSteps = new LoginSteps(driver);
		loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		// Navigate to Marketing Preference Page
		homeSteps.navigateToMarketingPreferencePage();
		// Init MarketingPreference steps
		MyProfileSteps = new MyProfileSteps(driver);
		// Login into the application to verify Marketing Preference Objects
		MyProfileSteps.verifyMarketingPreferencesObjects(softAssert);
		// Assert all the soft verification.
		// softAssert.assertAll();
		log.info("Test Case execution for - verifyMarketingPreferencesObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "Priya" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the Profile Banner question on Profile Page.")
	public void verifyProfileInfoBanner() {
		log.info("To verify the tests with the below test case id's");

		SoftAssert softAssert = new SoftAssert();
		// make Object For Navigate to the My Profile Page
		// Login into The Application
		LoginSteps loginSteps = new LoginSteps(driver);
		loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();
		MyProfileSteps = new MyProfileSteps(driver);
		// Login into the application to verify Marketing Preference Objects
		MyProfileSteps.verifyProfileInfoBanner(softAssert);
		// Assert all the soft verification.
		// softAssert.assertAll();
		log.info("Test Case execution for - verifyProfileInfoBanner - is Completed.");
	}
	
	@FrameworkAnnotations(author = { "Priya" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the ContactUs Details on the Profile Page.")
	public void verifyContactUsDetails() {
		log.info("To verify the tests with the below test case id's");

		SoftAssert softAssert = new SoftAssert();
		// make Object For Navigate to the My Profile Page
		// Login into The Application
		LoginSteps loginSteps = new LoginSteps(driver);
		loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		// Navigate to Marketing Preference Page
		homeSteps.navigateToMyProfilePage();
		// Init MarketingPreference steps
		MyProfileSteps = new MyProfileSteps(driver);
		// Login into the application to verify Marketing Preference Objects
		MyProfileSteps.verifyContactUsDetails(softAssert);
		// Assert all the soft verification.
		// softAssert.assertAll();
		log.info("Test Case execution for - verifyContactUsDetails - is Completed.");
	}
}
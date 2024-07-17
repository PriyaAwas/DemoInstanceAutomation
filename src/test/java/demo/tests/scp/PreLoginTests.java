package demo.tests.scp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import sew.ai.config.CSPConfiguration;
import sew.ai.config.Configuration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.helpers.testrail.TestRail;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.LoginSteps;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.PropertiesUtil;
import demo.steps.scp.PreLoginSteps;

import static sew.ai.steps.scp.LoginSteps.loginTextProp;

public class PreLoginTests extends TestRunner {

	private static final Logger log = LogManager.getLogger(PreLoginTests.class);
	private PreLoginSteps preLoginSteps;
	public static PropertiesUtil loginTextProp;

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SMOKE, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "User is able to Login with valid credentials.")
	public void verifyValidLogin() {
		String[] tc_id = { "Pre_Login_01" };
		ExtentLogger
				.logInfo("Test Case execution for - verifyValidLogin. Test Case id's -->  " + Arrays.toString(tc_id));

		log.info("To Verify Payment information fields and Add payment method pop-up");
		SoftAssert softAssert = new SoftAssert();
		// SCP- Application Login
		preLoginSteps = new PreLoginSteps(driver);
		preLoginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyValidLogin - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SMOKE, CategoryType.SCP_LOGIN })
	@Test(priority = 3, description = "To verify the login functionality with invalid credentials.")
	public void verifyInvalidLogin() {
		String[] tc_id = { "Pre_Login_02" };
		ExtentLogger
				.logInfo("Test Case execution for - verifyInvalidLogin. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		preLoginSteps = new PreLoginSteps(driver);
		preLoginSteps.loginIntoTheApplicationWrongCreds(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyInvalidLogin - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyInvalidLogin - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SMOKE, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To verify the login page objects.")
	public void verifyLoginPageObjects() {
		String[] tc_id = { "Pre_Login_03" };
		ExtentLogger.logInfo(
				"Test Case execution for - verifyLoginPageObjects. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		preLoginSteps = new PreLoginSteps(driver);
		// Go to the application and verify objects
		preLoginSteps.verifyTheLoginPageObject(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyLoginPageObjects - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyLoginPageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 4, description = "To verify the remember me login functionality.")
	public void verifyRememberMeLoginFunctionality() {
		String[] tc_id = { "Pre_Login_04", "Pre_Login_15" };
		ExtentLogger.logInfo("Test Case execution for - verifyRememberMeLoginFunctionality. Test Case id's -->  "
				+ Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		preLoginSteps = new PreLoginSteps(driver);
		// Init user model
		String user = Configuration.toString("userName");
		String password = Configuration.toString("password");
		// Logging in to the application by checking remember me checkbox.
		DashboardSteps dashboardSteps = preLoginSteps.loginIntoTheAppByCheckingRememberMe(user, password);
		// Verify remember me functionality
		Boolean isRememberMeChecked = preLoginSteps.verifyUsernameAndRememberMeCheckboxStatus(dashboardSteps, user);
		Assert.assertTrue(isRememberMeChecked);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyRememberMeLoginFunctionality - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyRememberMeLoginFunctionality - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY, CategoryType.SCP_CONNECTME })
	@Test(priority = 5, description = "To verify the Pre-Login Connect Me page.")
	public void verifyPreLogTrackContactReq() {
		String[] tc_id = { "Pre_Login_10" };
		ExtentLogger.logInfo("Test Case execution for - verifyRememberMeLoginFunctionality. Test Case id's -->  "
				+ Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		PreLoginSteps preLoginSteps = new PreLoginSteps(driver);
		String referenceId = null;
		// Verify Objects
		preLoginSteps.verifyPreLogConnectMeObject(softAssert);
		// Create Request
		preLoginSteps.verifyCreatePreContactRequest(softAssert);
		// Preview Contact Us Form
		preLoginSteps.verifyPreConnectMePreviewYourFormDetails(softAssert);
		// Submit Form
		referenceId = preLoginSteps.verifyPreConnectMeSubmitForm(softAssert);
		log.info("reference ID: " + referenceId);
		log.info("Test Case execution for - verifyPreLogTrackContactReq - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyPreLogTrackContactReq - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY, CategoryType.SCP_CONNECTME })
	@Test(priority = 5, description = "Verify User is able to Submit a Contact Us Request form for Water waste, theft without logging in.")
	public void verifyPreLogTrackContactReqForWaterWaste() {
		String[] tc_id = { "Pre_Login_13" };
		ExtentLogger.logInfo("Test Case execution for - verifyPreLogTrackContactReqForWaterWaste. Test Case id's -->  "
				+ Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		PreLoginSteps preLoginSteps = new PreLoginSteps(driver);
		String referenceId = null;
		// Verify Objects
//        preLoginSteps.verifyPreLogConnectMeObjectForWaterLeaks(softAssert);
		// Create Request
		preLoginSteps.verifyCreatePreContactRequestForWaterLeaks(softAssert);
		// Preview Contact Us Form
		preLoginSteps.verifyPreConnectMePreviewYourFormDetailsForWaterLeaks(softAssert);
		// Submit Form
		referenceId = preLoginSteps.verifyPreConnectMeSubmitForm(softAssert);
		log.info("reference ID: " + referenceId);
		ExtentLogger.logInfo("Reference Id: " + referenceId);
		log.info("Test Case execution for - verifyPreLogTrackContactReqForWaterWaste - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyPreLogTrackContactReqForWaterWaste - is Completed.");
	}

	@FrameworkAnnotations(author = { "Kavya BR" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To verify chat functionality.")
	public void verifyPreLogChatBox() throws SQLException {
		String[] tc_id = { "Pre_Login_14" };
		ExtentLogger.logInfo("Test Case execution for - verifyRememberMeLoginFunctionality. Test Case id's -->  "
				+ Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// SCP- Application Login
		preLoginSteps = new PreLoginSteps(driver);
		preLoginSteps.verifyPreLogChatBox(softAssert);
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyPreLogChatBox - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY })
	@Test(priority = 5, description = "To verify the Pre-Login Payment Locations page.")
	public void verifyPreLogPaymentLocations() {
		String[] tc_id = { "Pre_Login_11" };
		ExtentLogger.logInfo("Test Case execution for - verifyPreLogPaymentLocations. Test Case id's -->  "
				+ Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		PreLoginSteps preLoginSteps = new PreLoginSteps(driver);
		preLoginSteps.verifyPaymentLocations(softAssert);
		log.info("Test Case execution for - verifyPreLogPaymentLocations - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyPreLogPaymentLocations - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SMOKE })
	@Test(priority = 3, description = "To verify the payment functionality with invalid details.")
	public void verifyInvalidPaymentDetails() {
		String[] tc_id = { "Pre_Login_08" };
		ExtentLogger.logInfo(
				"Test Case execution for - verifyInvalidPaymentDetails. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		preLoginSteps = new PreLoginSteps(driver);
		preLoginSteps.payTheApplicationWrongCreds(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyInvalidPaymentDetails - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyInvalidPaymentDetails - is Completed.");
	}

	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SMOKE, CategoryType.SCP_WAYSTOSAVE })
	@Test(priority = 3, description = "To verify the ways to save page and it's sub-module.")
	public void verifyWaysToSavePage() {
		String[] tc_id = { "Pre_Login_12" };
		ExtentLogger.logInfo(
				"Test Case execution for - verifyWaysToSavePage. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		preLoginSteps = new PreLoginSteps(driver);
		preLoginSteps.verifyWaysToSave(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyWaysToSavePage - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyWaysToSavePage - is Completed.");
	}
	@FrameworkAnnotations(author = {"Eujin"}, category = { CategoryType.SMOKE, CategoryType.SCP_LOGIN })
	@Test(priority = 7, description = "To verify the language switch functionality on login page.")
	public void verifySwitchLanguageFunctionality() {
		SoftAssert softAssert = new SoftAssert();
		preLoginSteps = new PreLoginSteps(driver);
		preLoginSteps.verifyLanguageSwitchFeature(softAssert);
		softAssert.assertAll();
		log.info("Test Case execution for - verifySwitchLanguageFunctionality - is Completed.");
	}

	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SMOKE, CategoryType.SANITY })
	@Test(priority = 3, description = "To verify maximum length for username and password field.")
	public void verifyMaxValueOfUsernamePassFld() {
		ExtentLogger.logInfo("Test Case execution for - verifyMaxValueOfUsernamePassFld");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		preLoginSteps = new PreLoginSteps(driver);
		preLoginSteps.verifyMaxValueOfUsernamePassFld(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyMaxValueOfUsernamePassFld - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyMaxValueOfUsernamePassFld - is Completed.");
	}

	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SMOKE, CategoryType.SANITY })
	@Test(priority = 3, description = "To verify home icon functionality")
	public void verifyHomeIconFunction() {
		ExtentLogger.logInfo("Test Case execution for - verifyHomeIconFunction");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		preLoginSteps = new PreLoginSteps(driver);
		preLoginSteps.verifyHomeIconFunction(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyHomeIconFunction - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyHomeIconFunction - is Completed.");
	}
	
	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SMOKE, CategoryType.SCP_WAYSTOSAVE })
	@Test(priority = 3, description = "To verify the details on Rebates grid.")
	public void verifyDetailsONRebatesGrid() {
		String[] tc_id = { "Pre_Login_12" };
		ExtentLogger.logInfo(
				"Test Case execution for - verifyDetailsONRebatesGrid. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		preLoginSteps = new PreLoginSteps(driver);
		preLoginSteps.verifyDetailsONRebatesGrid(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyDetailsONRebatesGrid - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyDetailsONRebatesGrid - is Completed.");
	}
	
	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SMOKE, CategoryType.SCP_WAYSTOSAVE })
	@Test(priority = 3, description = "To verify the disclaimer on Ways To Save page.")
	public void verifyDisclaimerOnWaysToSave() {
		String[] tc_id = { "Pre_Login_12" };
		ExtentLogger.logInfo(
				"Test Case execution for - verifyDisclaimerOnWaysToSave. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		preLoginSteps = new PreLoginSteps(driver);
		preLoginSteps.verifyDisclaimerOnWaysToSave(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyDisclaimerOnWaysToSave - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyDisclaimerOnWaysToSave - is Completed.");
	}
	
	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SMOKE, CategoryType.SCP_WAYSTOSAVE })
	@Test(priority = 3, description = "To verify the details on Programs grid.")
	public void verifyDetailsONProgramsGrid() {
		String[] tc_id = { "Pre_Login_12" };
		ExtentLogger.logInfo(
				"Test Case execution for - verifyDetailsONProgramsGrid. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		preLoginSteps = new PreLoginSteps(driver);
		preLoginSteps.verifyDetailsONProgramsGrid(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyDetailsONProgramsGrid - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyDetailsONProgramsGrid - is Completed.");
	}
	
	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SMOKE, CategoryType.SCP_WAYSTOSAVE })
	@Test(priority = 3, description = "To verify the details on Savings Tips grid.")
	public void verifyDetailsONSavingTipsGrid() {
		String[] tc_id = { "Pre_Login_12" };
		ExtentLogger.logInfo(
				"Test Case execution for - verifyDetailsONSavingTipsGrid. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		preLoginSteps = new PreLoginSteps(driver);
		preLoginSteps.verifyDetailsONSavingTipsGrid(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyDetailsONSavingTipsGrid - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyDetailsONSavingTipsGrid - is Completed.");
	}
	
	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SMOKE, CategoryType.SCP_WAYSTOSAVE })
	@Test(priority = 3, description = "To verify the details on Educational Tips grid.")
	public void verifyDetailsONEducationalTipsGrid() {
		String[] tc_id = { "Pre_Login_12" };
		ExtentLogger.logInfo(
				"Test Case execution for - verifyDetailsONEducationalTipsGrid. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		preLoginSteps = new PreLoginSteps(driver);
		preLoginSteps.verifyDetailsONEducationalTipsGrid(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyDetailsONEducationalTipsGrid - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyDetailsONEducationalTipsGrid - is Completed.");
	}
}

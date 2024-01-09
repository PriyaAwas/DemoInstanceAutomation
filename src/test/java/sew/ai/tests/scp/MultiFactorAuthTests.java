package sew.ai.tests.scp;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import sew.ai.config.SCPConfiguration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.testrail.TestRail;
import sew.ai.models.User;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.LoginSteps;
import sew.ai.steps.scp.MultiFactorAuthSteps;
import sew.ai.steps.scp.PostLogServiceRequestSteps;
import sew.ai.steps.scp.PreLogServiceRequestSteps;
import sew.ai.steps.scp.RegistrationSteps;

public class MultiFactorAuthTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(MultiFactorAuthTests.class);
	private MultiFactorAuthSteps multiFactorAuthSteps;
    private DashboardSteps dashboardSteps;
	HashMap<String, String> customerData = new HashMap<>();
	
	
	@TestRail(testCaseId = {})
	@FrameworkAnnotations(author = { "Varad Nevase" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the service page objects after login.")
	public void verifyMFAUIAndValidation() throws Exception {
		log.info("To verify the tests with the below test case id's: " + "C74657, C74658");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		MultiFactorAuthSteps multiFactorAuthSteps = new MultiFactorAuthSteps(driver);
		dashboardSteps = new DashboardSteps(driver);
		// Init user model
		User user = SCPConfiguration.user;
		// Verify the user landed to the dashboard page
		multiFactorAuthSteps.verifyMFAUIAndValidations(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
	}

	@TestRail(testCaseId = {})
	@FrameworkAnnotations(author = { "Varad Nevase" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the service page objects after login.")
	public void verifyMFAwithEmailORText() throws Exception {
		log.info("To verify the tests with the below test case id's: " + "C74657, C74658");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		MultiFactorAuthSteps multiFactorAuthSteps = new MultiFactorAuthSteps(driver);
		dashboardSteps = new DashboardSteps(driver);
		// Init user model
		User user = SCPConfiguration.user;
		// Verify the user landed to the dashboard page
	   multiFactorAuthSteps.verifyMFAwithEmailoRText(null);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
	}

	@TestRail(testCaseId = {})
	@FrameworkAnnotations(author = { "Varad Nevase" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the service page objects after login.")
	public void verifyMFAExpirationFeature() throws Exception {
		log.info("To verify the tests with the below test case id's: " + "C74657, C74658");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		MultiFactorAuthSteps multiFactorAuthSteps = new MultiFactorAuthSteps(driver);
		dashboardSteps = new DashboardSteps(driver);
		// Init user model
		User user = SCPConfiguration.user;
		// Verify the user landed to the dashboard page
		multiFactorAuthSteps.verifyMFAExpirationFeatures(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
	}
	
	@TestRail(testCaseId = {})
	@FrameworkAnnotations(author = { "Varad Nevase" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the service page objects after login.")
	public void verifyContactMethodOnMFAScreen() throws Exception {
		log.info("To verify the tests with the below test case id's: " + "C74657, C74658");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		MultiFactorAuthSteps multiFactorAuthSteps = new MultiFactorAuthSteps(driver);
		dashboardSteps = new DashboardSteps(driver);
		// Init user model
		User user = SCPConfiguration.user;
		// Verify the user landed to the dashboard page
		multiFactorAuthSteps.verifyContactMethodsOnMFAScreen(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
	}
		
	@TestRail(testCaseId = {})
	@FrameworkAnnotations(author = { "Varad Nevase" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the service page objects after login.")
	public void verifyUserNotLockedForMaxWrongAttemptMFA() throws Exception {
		log.info("To verify the tests with the below test case id's: " + "C74657, C74658");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		MultiFactorAuthSteps multiFactorAuthSteps = new MultiFactorAuthSteps(driver);
		dashboardSteps = new DashboardSteps(driver);
		// Init user model
		User user = SCPConfiguration.user;
		// Verify the user landed to the dashboard page
		multiFactorAuthSteps.verifyUserNotLockedForMaxWrongAttemptsMFA(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
	}
	
	@TestRail(testCaseId = {})
	@FrameworkAnnotations(author = { "Varad Nevase" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the service page objects after login.")
	public void verifyExceededResendOTPForMFA_Email_And_Text() throws Exception {
		log.info("To verify the tests with the below test case id's: " + "C74657, C74658");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		MultiFactorAuthSteps multiFactorAuthSteps = new MultiFactorAuthSteps(driver);
		dashboardSteps = new DashboardSteps(driver);
		// Init user model
		User user = SCPConfiguration.user;
		// Verify the user landed to the dashboard page
	//	multiFactorAuthSteps.verifyExceededResendOTPForMFA_Email_And_Text(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
	}
}



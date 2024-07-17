package demo.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import sew.ai.config.Configuration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.LoginSteps;
import demo.steps.scp.UserDashboardSteps;

public class UserDashboardTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(UserDashboardTests.class);
	private UserDashboardSteps userdashboardSteps;

	@FrameworkAnnotations(author = {"Priya Awasthi"}, category = { CategoryType.SMOKE,CategoryType.SCP_DASHBOARD })
	@Test(priority = 1, description = "To verify the dashboard page objects after login.")
	public void verifyDashboardPageObjects() {
		SoftAssert softAssert = new SoftAssert();
		// Login into the application
		LoginSteps loginSteps = new LoginSteps(driver);
		loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		// Verify the user landed to the dashboard page
		UserDashboardSteps userdashboardSteps = new UserDashboardSteps(driver);
		softAssert.assertTrue(userdashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
		// Verify the dashboard page objects
		userdashboardSteps.verifyTheDashboardPageObjectSteps(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = {"Priya Awasthi"}, category = { CategoryType.SANITY,CategoryType.SCP_DASHBOARD })

	@Test(priority = 2, description = "To verify the dashboard page bill summary section objects after login.")
	public void verifyBillingSummarySectionObjects() {

		SoftAssert softAssert = new SoftAssert();
		// Login into the application
		LoginSteps loginSteps = new LoginSteps(driver);
		loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		// Verify the user landed to the dashboard page
		UserDashboardSteps userdashboardSteps = new UserDashboardSteps(driver);
		softAssert.assertTrue(userdashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
		// Verify the dashboard page objects
		userdashboardSteps.verifyBillingSummarySectionSteps(softAssert);
		softAssert.assertAll();
		log.info("Test Case execution for - verifyBillingSummarySectionPageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = {"Priya Awasthi"}, category = { CategoryType.SANITY,CategoryType.SCP_DASHBOARD })

	@Test(priority = 3, description = "To verify the usage feature on the dashboard page.")
	public void verifyUsageFeatureOnDashboard() {
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		// Verify the user landed to the dashboard page
		UserDashboardSteps userdashboardSteps = new UserDashboardSteps(driver);
		softAssert.assertTrue(userdashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
		userdashboardSteps.verifyDashboardUsages(softAssert); // Assert all the
		softAssert.assertAll();
		log.info("Test Case execution for - verifyUsageTileObjects - is Completed.");
	}

	@FrameworkAnnotations(author = {"Priya Awasthi"}, category = { CategoryType.SANITY,CategoryType.SCP_DASHBOARD })

	@Test(priority = 4, description = "To verify the AutoPayCarouselfeature on the dashboard page.")
	public void verifyAutoPayCarouselFeatureOnDashboard() {
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		// Verify the user landed to the dashboard page
		UserDashboardSteps userdashboardSteps = new UserDashboardSteps(driver);
		softAssert.assertTrue(userdashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
		userdashboardSteps.verifyAutoPayCarousel(softAssert); // Assert all the
		softAssert.assertAll();
		log.info("Test Case execution for - verifyAutoPayCarousel - is Completed.");
	}

	@FrameworkAnnotations(author = {"Priya Awasthi"}, category = { CategoryType.SANITY,CategoryType.SCP_DASHBOARD })

	@Test(priority = 5, description = "To verify the SmartHomeCarousel Feature on the dashboard page.")
	public void verifySmartHomeCarouselFeatureOnDashboard() {
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		// Verify the user landed to the dashboard page
		UserDashboardSteps userdashboardSteps = new UserDashboardSteps(driver);
		softAssert.assertTrue(userdashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
		userdashboardSteps.verifySmartHomeCarousel(softAssert); // Assert all the
		softAssert.assertAll();
		log.info("Test Case execution for - verifySmartHomeCarousel - is Completed.");
	}

	@FrameworkAnnotations(author = {"Prashant Kumar"}, category = { CategoryType.SANITY,CategoryType.SCP_DASHBOARD })

	@Test(priority = 5, description = "To Verify the objects on Bottom pane of the dashboard page.")
	public void verifyBottomPaneOnDashboard() {
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		// Verify the user landed to the dashboard page
		UserDashboardSteps userdashboardSteps = new UserDashboardSteps(driver);
		softAssert.assertTrue(userdashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
		userdashboardSteps.verifyBottompaneElements(softAssert); // Assert all the
		softAssert.assertAll();
		log.info("Test Case execution for - verifyBottompaneElements - is Completed.");
	}
	
	@FrameworkAnnotations(author = {"Prashant Kumar"}, category = { CategoryType.SANITY,CategoryType.SCP_DASHBOARD })

	@Test(priority = 5, description = "To Verify the objects on Bottom pane of the dashboard page.")
	public void verifyCompareConsumptionOnDashboard() {
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		// Verify the user landed to the dashboard page
		UserDashboardSteps userdashboardSteps = new UserDashboardSteps(driver);
		softAssert.assertTrue(userdashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
		userdashboardSteps.verifyBottompaneElements(softAssert); // Assert all the
		softAssert.assertAll();
		log.info("Test Case execution for - verifyBottompaneElements - is Completed.");
	}
}
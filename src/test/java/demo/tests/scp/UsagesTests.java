package demo.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.config.Configuration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.models.Meter;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.HomeSteps;
import sew.ai.steps.scp.LoginSteps;
import demo.steps.scp.UsageSteps;
import static demo.steps.scp.UsageSteps.usageTextProp;

public class UsagesTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(UsagesTests.class);
	private UsageSteps usageSteps;
	Meter[] meters;

	@FrameworkAnnotations(author = { "SatyaTiwari" }, category = {CategoryType.SMOKE,CategoryType.SANITY, CategoryType.SCP_USAGE })
	@Test(priority = 1, description = "To verify the electric usages page objects after login.")
	public void verifyElectricUsagePageObjects() {

		SoftAssert softAssert = new SoftAssert();
		// Init usages steps
		usageSteps = new UsageSteps(driver);
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		// Init dashboard steps
		DashboardSteps dashboardSteps;

		// Login into the application
		dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		// Verify the user landed to the dashboard page
		softAssert.assertTrue(dashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
		ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
		// Navigate to the usages page
		HomeSteps homeSteps = new HomeSteps(driver);

		homeSteps.navigateToUsageOverview();
		ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
		ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
		// Verify user navigated to the usages page
		Assert.assertTrue(dashboardSteps.isDashboardPage(usageTextProp.getPropValue("usagePageUrl"),
				usageTextProp.getPropValue("usagePageTitle")), "Not usages page.");
		ExtentLogger.logInfo("Navigated to usages page successfully.");

		// Verifying page Header
		softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
		softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
				"Usage page header label not matched.");
		// Verify account drop-down
		int linkedAccounts = 2;

		if (linkedAccounts > 1) {
			softAssert.assertTrue(usageSteps.isAccountDropDownButtonVisible(),
					"Account drop-down button is not visible.");
		}

		Meter[] meters = new Meter[3];
		int count = 0;
		for (int i = 0; i < 3; i++) {
			Meter meter = new Meter();
			// meter.setMeterId(resultSet1.getString("meterid"));
			if (i == 0) {
				meter.setAccountNumber("411001984953");
				meter.setMeterNumber("000000000059370255");
				meter.setMeterType("E");
				meter.setIsAmi(0);
			}
			if (i == 1) {
				meter.setAccountNumber("411001984953");
				meter.setMeterNumber("E214343238");
				meter.setMeterType("E");
				meter.setIsAmi(1);
			}
			if (i == 2) {
				meter.setAccountNumber("411001984953");
				meter.setMeterNumber("PV4042104173");
				meter.setMeterType("PV");
				meter.setIsAmi(1);
			}

			meters[count] = meter;
			count++;
		}
		// Check meters are linked to the account under test
		if (meters.length > 0) {
			ExtentLogger.logInfo("Meters are linked to the account.");
			// Verify the dashboard page objects
			usageSteps.verifyElectricUsagesObjects(softAssert, meters);
		}
		// Verify monthly electric usage data for Non AMI meter scenario
		else {
			ExtentLogger.logSkip("There is no meter linked to the account.");
		}

		// Assert all the soft verification.
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyElectricUsagePageObjects - is Completed.");
		log.info("Test Case execution for - verifyElectricUsagePageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "SatyaTiwari" }, category = {CategoryType.SMOKE, CategoryType.SANITY, CategoryType.SCP_USAGE })
	@Test(priority = 2, description = "To verify the water usages page objects after login.")
	public void verifyWaterUsagePageObjects() {

		SoftAssert softAssert = new SoftAssert();
		// Init usages steps
		usageSteps = new UsageSteps(driver);
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		// Init dashboard steps
		DashboardSteps dashboardSteps;

		// Login into the application
		dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		// Verify the user landed to the dashboard page
		softAssert.assertTrue(dashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
		ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
		// Navigate to the usages page
		HomeSteps homeSteps = new HomeSteps(driver);

		// Navigate to Usage overview
		homeSteps.navigateToUsageOverview();
		ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
		ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
		// Verify user navigated to the usages page
		Assert.assertTrue(dashboardSteps.isDashboardPage(usageTextProp.getPropValue("usagePageUrl"),
				usageTextProp.getPropValue("usagePageTitle")), "Not usages page.");
		ExtentLogger.logInfo("Navigated to usages page successfully.");

		// Verifying page Header
		softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
		softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
				"Usage page header label not matched.");
		// Verify account drop-down
		int linkedAccounts = 2;

		if (linkedAccounts > 1) {
			softAssert.assertTrue(usageSteps.isAccountDropDownButtonVisible(),
					"Account drop-down button is not visible.");
		}

		Meter[] meters = new Meter[2];
		int count = 0;
		for (int i = 0; i < 2; i++) {
			Meter meter = new Meter();
			// meter.setMeterId(resultSet1.getString("meterid"));
			if (i == 0) {
				meter.setAccountNumber("411001984953");
				meter.setMeterNumber("W2114343238");
				meter.setMeterType("W");
				meter.setIsAmi(0);
			}
			if (i == 1) {
				meter.setAccountNumber("411001984953");
				meter.setMeterNumber("W214346305");
				meter.setMeterType("W");
				meter.setIsAmi(1);
			}

			meters[count] = meter;
			count++;
		}
		// Check meters are linked to the account under test
		if (meters.length > 0) {
			ExtentLogger.logInfo("Meters are linked to the account.");
			// Verify the dashboard page objects
			usageSteps.verifyWaterUsagesObjects(softAssert, meters);
		} else {
			ExtentLogger.logSkip("There is no meter linked to the account.");
		}

		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyWaterUsagePageObjects - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyWaterUsagePageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "SatyaTiwari" }, category = { CategoryType.SMOKE,CategoryType.SANITY, CategoryType.SCP_USAGE })
	@Test(priority = 3, description = "To verify the gas usages page objects after login.")
	public void verifyGasUsagePageObjects() {
		
		SoftAssert softAssert = new SoftAssert();
		// Init usages steps
		usageSteps = new UsageSteps(driver);
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		// Init dashboard steps
		DashboardSteps dashboardSteps;

		// Login into the application
		dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		// Verify the user landed to the dashboard page
		softAssert.assertTrue(dashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
		ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
		// Navigate to the usages page
		HomeSteps homeSteps = new HomeSteps(driver);

		homeSteps.navigateToUsageOverview();
		ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
		ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
		// Verify user navigated to the usages page
		Assert.assertTrue(dashboardSteps.isDashboardPage(usageTextProp.getPropValue("usagePageUrl"),
				usageTextProp.getPropValue("usagePageTitle")), "Not usages page.");
		ExtentLogger.logInfo("Navigated to usages page successfully.");

		// Verifying page Header
		softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
		softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
				"Usage page header label not matched.");
		// Verify account drop-down
		int linkedAccounts = 2;

		if (linkedAccounts > 1) {
			softAssert.assertTrue(usageSteps.isAccountDropDownButtonVisible(),
					"Account drop-down button is not visible.");
		}

		Meter[] meters = new Meter[2];
		int count = 0;
		for (int i = 0; i < 2; i++) {
			Meter meter = new Meter();
			// meter.setMeterId(resultSet1.getString("meterid"));
			if (i == 0) {
				meter.setAccountNumber("411001984953");
				meter.setMeterNumber("G2914346305");
				meter.setMeterType("G");
				meter.setIsAmi(0);
			}
			if (i == 1) {
				meter.setAccountNumber("411001984953");
				meter.setMeterNumber("G214346305");
				meter.setMeterType("G");
				meter.setIsAmi(1);
			}

			meters[count] = meter;
			count++;
		}
		// Check meters are linked to the account under test
		if (meters.length > 0) {
			ExtentLogger.logInfo("Meters are linked to the account.");
			// Verify the dashboard page objects
			usageSteps.verifyGasUsagesObjects(softAssert, meters);
		} else {
			ExtentLogger.logSkip("There is no meter linked to the account.");
		}

		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyGasUsagePageObjects - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyGasUsagePageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "SatyaTiwari" }, category = {CategoryType.SMOKE, CategoryType.SANITY, CategoryType.SCP_USAGE })
	@Test(priority = 4, description = "To verify the solar usages page objects after login.")
	public void verifySolarUsagePageObjects() {
		
		SoftAssert softAssert = new SoftAssert();
		// Init usages steps
		usageSteps = new UsageSteps(driver);
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		// Init dashboard steps
		DashboardSteps dashboardSteps;

		// Login into the application
		dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		// Verify the user landed to the dashboard page
		// softAssert.assertTrue(dashboardSteps.isProfileIconVisible(), "User profile
		// icon not visible.");
		ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
		// Navigate to the usages page
		HomeSteps homeSteps = new HomeSteps(driver);

		// Navigate to Usage overview
		homeSteps.navigateToUsageOverview();
		ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
		ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
		// Verify user navigated to the usages page
		Assert.assertTrue(dashboardSteps.isDashboardPage(usageTextProp.getPropValue("usagePageUrl"),
				usageTextProp.getPropValue("usagePageTitle")), "Not usages page.");
		ExtentLogger.logInfo("Navigated to usages page successfully.");
		
		// Verifying page Header
		softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
		softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
				"Usage page header label not matched.");
		// Verify account drop-down
		int linkedAccounts = 2;

		if (linkedAccounts > 1) {
			softAssert.assertTrue(usageSteps.isAccountDropDownButtonVisible(),
					"Account drop-down button is not visible.");
		}

		Meter[] meters = new Meter[2];
		int count = 0;
		for (int i = 0; i < 2; i++) {
			Meter meter = new Meter();
			// meter.setMeterId(resultSet1.getString("meterid"));
			if (i == 0) {
				meter.setAccountNumber("411001984953");
				meter.setMeterNumber("PV40426305");
				meter.setMeterType("PV");
				meter.setIsAmi(0);
			}
			if (i == 1) {
				meter.setAccountNumber("411001984953");
				meter.setMeterNumber("PV4042104173");
				meter.setMeterType("PV");
				meter.setIsAmi(1);
			}

			meters[count] = meter;
			count++;
		}
		// Check meters are linked to the account under test
		if (meters.length > 0) {
			ExtentLogger.logInfo("Meters are linked to the account.");
			// Verify the dashboard page objects
			usageSteps.verifySolarUsagesObjects(softAssert, meters);
		} else {
			ExtentLogger.logSkip("There is no meter linked to the account.");
		}

		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifySolarUsagePageObjects - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifySolarUsagePageObjects - is Completed.");
	}

}

package sew.ai.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.config.CSPConfiguration;
import sew.ai.config.SCPConfiguration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.helpers.testrail.TestRail;
import sew.ai.models.Meter;
import sew.ai.models.User;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.HomeSteps;
import sew.ai.steps.scp.LoginSteps;
import sew.ai.steps.scp.UsageSteps;
import sew.ai.utils.DataBaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

import static sew.ai.steps.scp.UsageSteps.usageTextProp;

public class UsagesTests extends TestRunner {
    private static final Logger log = LogManager.getLogger(UsagesTests.class);
    private UsageSteps usageSteps;
    Meter[] meters;

    @TestRail(testCaseId = {76953, 76955, 76956, 76957, 76963, 76964, 76973, 76954, 76964,
            76991, 76992, 76998, 76999, 77005, 77016, 77017, 77022, 77023, 77029, 77036, 77037,
            77046})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the electric usages page objects after login.")
    public void verifyElectricUsagePageObjects() {
        log.info("To verify the tests with the below test case id's: " +
                "C76953, C76955, C76956, C76957, C76963, C76964, C76973, C76954, C76964, " +
                "C76991, C76992, C76998, C76999, C77005, C77016, C77017, C77022, C77023, " +
                "C77029, C77036, C77037, C77046");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C76953, C76955, C76956, C76957, C76963, C76964, C76973, C76954, C76964, " +
                "C76991, C76992, C76998, C76999, C77005, C77016, C77017, C77022, C77023, " +
                "C77029, C77036, C77037, C77046");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(
                dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isElectricModulePresent = CSPConfiguration.initCSPConfig().get("Power");
        ExtentLogger.logInfo("Electric commodity CSP enable/disable status : " + isElectricModulePresent);
        if (isUsageModulePresent == 1 && isElectricModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Verify account drop-down
            ResultSet resultSet;
            int linkedAccounts = 0;
            try {
                resultSet = DataBaseUtils.getResultSet(SQLQueries.getLinkedAccountNumberCount(user.getUserName()));
                resultSet.next();
                linkedAccounts = resultSet.getInt("count");
            } catch (SQLException e) {
                e.printStackTrace();
                ExtentLogger.logException(e);
            }
            if (linkedAccounts > 1) {
                softAssert.assertTrue(usageSteps.isAccountDropDownButtonVisible(),
                        "Account drop-down button is not visible.");
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
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifyElectricUsagePageObjects - is Completed.");
        log.info("Test Case execution for - verifyElectricUsagePageObjects - is Completed.");
    }

    @TestRail(testCaseId = {77717, 77718, 77719, 77720, 77721, 77722, 77723, 77727, 77728, 77737,
            77766, 77752, 77753, 77759, 77760, 77735, 77777, 77778, 77783, 77784, 77790, 107704,
            107705, 107714})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 2, description = "To verify the water usages page objects after login.")
    public void verifyWaterUsagePageObjects() {
        log.info("To verify the tests with the below test case id's: " +
                "C77717, C77718, C77719, C77720, C77721, C77722, C77723, C77727, C77728, C77737, " +
                "C77766, C77752, C77753, C77759, C77760, C77735, C77777, C77778, C77783, C77784, " +
                "C77790, C107704, C107705, C107714");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C77717, C77718, C77719, C77720, C77721, C77722, C77723, C77727, C77728, C77737, " +
                "C77766, C77752, C77753, C77759, C77760, C77735, C77777, C77778, C77783, C77784, " +
                "C77790, C107704, C107705, C107714");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isWaterModulePresent = CSPConfiguration.initCSPConfig().get("Water");
        ExtentLogger.logInfo("Water commodity CSP enable/disable status : " + isWaterModulePresent);
        if (isUsageModulePresent == 1 && isWaterModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Verify account drop-down
            ResultSet resultSet;
            int linkedAccounts = 0;
            try {
                resultSet = DataBaseUtils.getResultSet(SQLQueries.getLinkedAccountNumberCount(user.getUserName()));
                resultSet.next();
                linkedAccounts = resultSet.getInt("count");
            } catch (SQLException e) {
                e.printStackTrace();
                ExtentLogger.logException(e);
            }
            if (linkedAccounts > 1) {
                softAssert.assertTrue(usageSteps.isAccountDropDownButtonVisible(),
                        "Account drop-down button is not visible.");
            }
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the dashboard page objects
                usageSteps.verifyWaterUsagesObjects(softAssert, meters);
            }
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyWaterUsagePageObjects - is Completed.");
        ExtentLogger.logInfo("Test Case execution for - verifyWaterUsagePageObjects - is Completed.");
    }

    @TestRail(testCaseId = {77840, 77842, 77843, 77844, 77845, 77846, 77850, 77851, 77860, 77875,
            77876, 77882, 77883, 77889, 77898, 77899, 77904, 77905, 77911})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 3, description = "To verify the gas usages page objects after login.")
    public void verifyGasUsagePageObjects() {
        log.info("To verify the tests with the below test case id's: " +
                "C77840, C77842, C77843, C77844, C77845, C77846, C77850, C77851, C77860, C77875, " +
                "C77876, C77882, C77883, C77889, C77898, C77899, C77904, C77905, C77911");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C77840, C77842, C77843, C77844, C77845, C77846, C77850, C77851, C77860, C77875, " +
                "C77876, C77882, C77883, C77889, C77898, C77899, C77904, C77905, C77911");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isGasModulePresent = CSPConfiguration.initCSPConfig().get("Gas");
        ExtentLogger.logInfo("Gas commodity CSP enable/disable status : " + isGasModulePresent);
        if (isUsageModulePresent == 1 && isGasModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Verify account drop-down
            ResultSet resultSet;
            int linkedAccounts = 0;
            try {
                resultSet = DataBaseUtils.getResultSet(SQLQueries.getLinkedAccountNumberCount(user.getUserName()));
                resultSet.next();
                linkedAccounts = resultSet.getInt("count");
            } catch (SQLException e) {
                e.printStackTrace();
                ExtentLogger.logException(e);
            }
            if (linkedAccounts > 1) {
                softAssert.assertTrue(usageSteps.isAccountDropDownButtonVisible(),
                        "Account drop-down button is not visible.");
            }
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the dashboard page objects
                usageSteps.verifyGasUsagesObjects(softAssert, meters);
            }
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyGasUsagePageObjects - is Completed.");
        ExtentLogger.logInfo("Test Case execution for - verifyGasUsagePageObjects - is Completed.");
    }

    @TestRail(testCaseId = {77957, 77957, 77961, 77962, 77963, 77965, 77966, 77968, 77970,
            77972, 77973, 77980, 77960})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 4, description = "To verify the solar usages page objects after login.")
    public void verifySolarUsagePageObjects() {
        log.info("To verify the tests with the below test case id's: " +
                "C77957, C77957, C77961, C77962, C77963, C77965, C77966, C77968, C77970, " +
                "C77972, C77973, C77980, C77960");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C77957, C77957, C77961, C77962, C77963, C77965, C77966, C77968, C77970, " +
                "C77972, C77973, C77980, C77960");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(
                dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isSolarModulePresent = CSPConfiguration.initCSPConfig().get("Solar");
        ExtentLogger.logInfo("Solar commodity CSP enable/disable status : " + isSolarModulePresent);
        if (isUsageModulePresent == 1 && isSolarModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Verify account drop-down
            ResultSet resultSet;
            int linkedAccounts = 0;
            try {
                resultSet = DataBaseUtils.getResultSet(SQLQueries.getLinkedAccountNumberCount(user.getUserName()));
                resultSet.next();
                linkedAccounts = resultSet.getInt("count");
            } catch (SQLException e) {
                e.printStackTrace();
                ExtentLogger.logException(e);
            }
            if (linkedAccounts > 1) {
                softAssert.assertTrue(usageSteps.isAccountDropDownButtonVisible(),
                        "Account drop-down button is not visible.");
            }
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the dashboard page objects
                usageSteps.verifySolarUsagesObjects(softAssert, meters);
            }
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifySolarUsagePageObjects - is Completed.");
        ExtentLogger.logInfo("Test Case execution for - verifySolarUsagePageObjects - is Completed.");
    }

    @TestRail(testCaseId = {76958, 76959, 76960, 76965, 76966, 76970, 76971, 76972, 76974, 76975})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 5, description = "To verify the electric usages data for MONTHLY intervals.")
    public void verifyMonthlyElectricUsageData() {
        log.info("To verify the tests with the below test case id's: " +
                "C76958, C76959, C76960, C76965, C76966, C76970, C76971, C76972, C76974, C76975");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C76958, C76959, C76960, C76965, C76966, C76970, C76971, C76972, C76974, C76975");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(
                dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isElectricModulePresent = CSPConfiguration.initCSPConfig().get("Power");
        ExtentLogger.logInfo("Electric commodity CSP enable/disable status : " + isElectricModulePresent);
        if (isUsageModulePresent == 1 && isElectricModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the monthly electric usages data
                usageSteps.verifyMonthlyElectricUsageData(softAssert, user, meters);
            }
            // Verify monthly electric usage data for Non AMI meter scenario
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifyMonthlyElectricUsageData - is Completed.");
        log.info("Test Case execution for - verifyMonthlyElectricUsageData - is Completed.");
    }

    @TestRail(testCaseId = {76993, 76995, 77001, 77003, 77004, 77006})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 6, description = "To verify the electric usages data for DAILY intervals.")
    public void verifyDailyElectricUsageData() {
        log.info("To verify the tests with the below test case id's: " +
                "C76993, C76995, C77001, C77003, C77004, C77006");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C76993, C76995, C77001, C77003, C77004, C77006");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isElectricModulePresent = CSPConfiguration.initCSPConfig().get("Power");
        ExtentLogger.logInfo("Electric commodity CSP enable/disable status : " + isElectricModulePresent);
        if (isUsageModulePresent == 1 && isElectricModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the monthly electric usages data
                usageSteps.verifyDailyElectricUsageData(softAssert, user, meters);
            }
            // Verify monthly electric usage data for Non AMI meter scenario
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifyDailyElectricUsageData - is Completed.");
        log.info("Test Case execution for - verifyDailyElectricUsageData - is Completed.");
    }

    @TestRail(testCaseId = {77018, 77020, 77025, 77026, 77027, 77028, 77030})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 7, description = "To verify the electric usages data for HOURLY intervals.")
    public void verifyHourlyElectricUsageData() {
        log.info("To verify the tests with the below test case id's: " +
                "C77018, C77020, C77025, C77026, C77027, C77028, C77030");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C77018, C77020, C77025, C77026, C77027, C77028, C77030");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isElectricModulePresent = CSPConfiguration.initCSPConfig().get("Power");
        ExtentLogger.logInfo("Electric commodity CSP enable/disable status : " + isElectricModulePresent);
        if (isUsageModulePresent == 1 && isElectricModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the monthly electric usages data
                usageSteps.verifyHourlyElectricUsageData(softAssert, user, meters);
            }
            // Verify monthly electric usage data for Non AMI meter scenario
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifyHourlyElectricUsageData - is Completed.");
        log.info("Test Case execution for - verifyHourlyElectricUsageData - is Completed.");
    }

    @TestRail(testCaseId = {77038, 77038, 77040, 77042, 77043, 77044, 77045, 77047})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 8, description = "To verify the electric usages data for MINUTES intervals.")
    public void verifyMinutesElectricUsageData() {
        log.info("To verify the tests with the below test case id's: " +
                "C77038, C77038, C77040, C77042, C77043, C77044, C77045, C77047");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C77038, C77038, C77040, C77042, C77043, C77044, C77045, C77047");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isElectricModulePresent = CSPConfiguration.initCSPConfig().get("Power");
        ExtentLogger.logInfo("Electric commodity CSP enable/disable status : " + isElectricModulePresent);
        if (isUsageModulePresent == 1 && isElectricModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the monthly electric usages data
                usageSteps.verifyMinutesElectricUsageData(softAssert, user, meters);
            }
            // Verify monthly electric usage data for Non AMI meter scenario
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifyMinutesElectricUsageData - is Completed.");
        log.info("Test Case execution for - verifyMinutesElectricUsageData - is Completed.");
    }

    @TestRail(testCaseId = {77724, 77729, 77730, 77734, 77735, 77736, 77738, 77739})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 9, description = "To verify the water usages data for MONTHLY intervals.")
    public void verifyMonthlyWaterUsageData() {
        log.info("To verify the tests with the below test case id's: " +
                "C77724, C77729, C77730, C77734, C77735, C77736, C77738, C77739");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C77724, C77729, C77730, C77734, C77735, C77736, C77738, C77739");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(
                dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isWaterModulePresent = CSPConfiguration.initCSPConfig().get("Water");
        ExtentLogger.logInfo("Water commodity CSP enable/disable status : " + isWaterModulePresent);
        if (isUsageModulePresent == 1 && isWaterModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the monthly water usages data
                usageSteps.verifyMonthlyWaterUsageData(softAssert, user, meters);
            }
            // Verify monthly water usage data for Non AMI meter scenario
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifyMonthlyWaterUsageData - is Completed.");
        log.info("Test Case execution for - verifyMonthlyWaterUsageData - is Completed.");
    }

    @TestRail(testCaseId = {77754, 77756, 77762, 77763, 77764, 77765, 77767, 77768})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 10, description = "To verify the water usages data for DAILY intervals.")
    public void verifyDailyWaterUsageData() {
        log.info("To verify the tests with the below test case id's: " +
                "C77754, C77756, C77762, C77763, C77764, C77765, C77767, C77768");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C77754, C77756, C77762, C77763, C77764, C77765, C77767, C77768");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isWaterModulePresent = CSPConfiguration.initCSPConfig().get("Water");
        ExtentLogger.logInfo("Water commodity CSP enable/disable status : " + isWaterModulePresent);
        if (isUsageModulePresent == 1 && isWaterModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the monthly water usages data
                usageSteps.verifyDailyWaterUsageData(softAssert, user, meters);
            }
            // Verify monthly water usage data for Non AMI meter scenario
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifyDailyWaterUsageData - is Completed.");
        log.info("Test Case execution for - verifyDailyWaterUsageData - is Completed.");
    }

    @TestRail(testCaseId = {77779, 77781, 77786, 77787, 77788, 77789, 77791, 77792})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 11, description = "To verify the water usages data for HOURLY intervals.")
    public void verifyHourlyWaterUsageData() {
        log.info("To verify the tests with the below test case id's: " +
                "C77779, C77781, C77786, C77787, C77788, C77789, C77791, C77792");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C77779, C77781, C77786, C77787, C77788, C77789, C77791, C77792");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isWaterModulePresent = CSPConfiguration.initCSPConfig().get("Water");
        ExtentLogger.logInfo("Water commodity CSP enable/disable status : " + isWaterModulePresent);
        if (isUsageModulePresent == 1 && isWaterModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the monthly water usages data
                usageSteps.verifyHourlyWaterUsageData(softAssert, user, meters);
            }
            // Verify monthly water usage data for Non AMI meter scenario
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifyHourlyWaterUsageData - is Completed.");
        log.info("Test Case execution for - verifyHourlyWaterUsageData - is Completed.");
    }

    @TestRail(testCaseId = {107706, 107707, 107708, 107710, 107711, 107712, 107713,
            107715, 107717, 107718, 107719})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 12, description = "To verify the water usages data for MINUTES intervals.")
    public void verifyMinutesWaterUsageData() {
        log.info("To verify the tests with the below test case id's: " +
                "C107706, C107707, C107708, C107710, C107711, C107712, C107713, C107715, C107717, " +
                "C107718, C107719");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C107706, C107707, C107708, C107710, C107711, C107712, C107713, C107715, C107717, " +
                "C107718, C107719");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isWaterModulePresent = CSPConfiguration.initCSPConfig().get("Water");
        ExtentLogger.logInfo("Water commodity CSP enable/disable status : " + isWaterModulePresent);
        if (isUsageModulePresent == 1 && isWaterModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the monthly water usages data
                usageSteps.verifyMinutesWaterUsageData(softAssert, user, meters);
            }
            // Verify monthly water usage data for Non AMI meter scenario
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifyMinutesWaterUsageData - is Completed.");
        log.info("Test Case execution for - verifyMinutesWaterUsageData - is Completed.");
    }

    @TestRail(testCaseId = {77859, 77847, 77852, 77853, 77857, 77858, 77861, 77862})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 13, description = "To verify the gas usages data for MONTHLY intervals.")
    public void verifyMonthlyGasUsageData() {
        log.info("To verify the tests with the below test case id's: " +
                "C77859, C77847, C77852, C77853, C77857, C77858, C77861, C77862");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C77859, C77847, C77852, C77853, C77857, C77858, C77861, C77862");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(
                dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isGasModulePresent = CSPConfiguration.initCSPConfig().get("Gas");
        ExtentLogger.logInfo("Gas commodity CSP enable/disable status : " + isGasModulePresent);
        if (isUsageModulePresent == 1 && isGasModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the monthly gas usages data
                usageSteps.verifyMonthlyGasUsageData(softAssert, user, meters);
            }
            // Verify monthly gas usage data for Non AMI meter scenario
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifyMonthlyGasUsageData - is Completed.");
        log.info("Test Case execution for - verifyMonthlyGasUsageData - is Completed.");
    }

    @TestRail(testCaseId = {77877, 77879, 77885, 77886, 77887, 77888, 77890, 77891})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 14, description = "To verify the gas usages data for DAILY intervals.")
    public void verifyDailyGasUsageData() {
        log.info("To verify the tests with the below test case id's: " +
                "C77877, C77879, C77885, C77886, C77887, C77888, C77890, C77891");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C77877, C77879, C77885, C77886, C77887, C77888, C77890, C77891");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isGasModulePresent = CSPConfiguration.initCSPConfig().get("Gas");
        ExtentLogger.logInfo("Gas commodity CSP enable/disable status : " + isGasModulePresent);
        if (isUsageModulePresent == 1 && isGasModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the monthly gas usages data
                usageSteps.verifyDailyGasUsageData(softAssert, user, meters);
            }
            // Verify monthly gas usage data for Non AMI meter scenario
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifyDailyGasUsageData - is Completed.");
        log.info("Test Case execution for - verifyDailyGasUsageData - is Completed.");
    }

    @TestRail(testCaseId = {77900, 77901, 77902, 77907, 77908, 77909, 77910, 77912, 77913})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 15, description = "To verify the gas usages data for HOURLY intervals.")
    public void verifyHourlyGasUsageData() {
        log.info("To verify the tests with the below test case id's: " +
                "C77900, C77901, C77902, C77907, C77908, C77909, C77910, C77912, C77913");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C77900, C77901, C77902, C77907, C77908, C77909, C77910, C77912, C77913");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isGasModulePresent = CSPConfiguration.initCSPConfig().get("Gas");
        ExtentLogger.logInfo("Gas commodity CSP enable/disable status : " + isGasModulePresent);
        if (isUsageModulePresent == 1 && isGasModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the monthly gas usages data
                usageSteps.verifyHourlyGasUsageData(softAssert, user, meters);
            }
            // Verify monthly gas usage data for Non AMI meter scenario
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifyHourlyGasUsageData - is Completed.");
        log.info("Test Case execution for - verifyHourlyGasUsageData - is Completed.");
    }

    @TestRail(testCaseId = {107686, 107687, 107688, 107691, 107692, 120115, 107693, 107694, 107695,
            107696, 107697, 107698, 107699, 107700})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 16, description = "To verify the gas usages data for MINUTES intervals.")
    public void verifyMinutesGasUsageData() {
        log.info("To verify the tests with the below test case id's: " +
                "C107686, C107687, C107688, C107691, C107692, C120115, C107693, C107694, C107695, " +
                "C107696, C107697, C107698, C107699, C107700");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C107686, C107687, C107688, C107691, C107692, C120115, C107693, C107694, C107695, " +
                "C107696, C107697, C107698, C107699, C107700");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isGasModulePresent = CSPConfiguration.initCSPConfig().get("Gas");
        ExtentLogger.logInfo("Gas commodity CSP enable/disable status : " + isGasModulePresent);
        if (isUsageModulePresent == 1 && isGasModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the monthly gas usages data
                usageSteps.verifyMinutesGasUsageData(softAssert, user, meters);
            }
            // Verify monthly gas usage data for Non AMI meter scenario
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifyMinutesGasUsageData - is Completed.");
        log.info("Test Case execution for - verifyMinutesGasUsageData - is Completed.");
    }

    @TestRail(testCaseId = {77964, 77969, 77974, 77975, 77976, 77978, 77979})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 17, description = "To verify the solar usages data.")
    public void verifySolarUsageData() {
        log.info("To verify the tests with the below test case id's: " +
                "C77964, C77969, C77974, C77975, C77976, C77978, C77979");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C77964, C77969, C77974, C77975, C77976, C77978, C77979");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isSolarModulePresent = CSPConfiguration.initCSPConfig().get("Solar");
        ExtentLogger.logInfo("Solar commodity CSP enable/disable status : " + isSolarModulePresent);
        if (isUsageModulePresent == 1 && isSolarModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the monthly solar usages data
                usageSteps.verifySolarUsageData(softAssert, user, meters);
            }
            // Verify monthly solar usage data for Non AMI meter scenario
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifySolarUsageData - is Completed.");
        log.info("Test Case execution for - verifySolarUsageData - is Completed.");
    }

    @TestRail(testCaseId = {76961, 76962, 120104, 76996, 76997, 120105, 77021, 120106, 77041, 120107})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 18, description = "To verify the export to excel functionality.")
    public void verifyExportToExcelElectricData() {
        log.info("To verify the tests with the below test case id's: " +
                "C76961, C76962, C120104, C76996, C76997, C120105, C77021, C120106, C77041, C120107");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C76961, C76962, C120104, C76996, C76997, C120105, C77021, C120106, C77041, C120107");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isSolarModulePresent = CSPConfiguration.initCSPConfig().get("Solar");
        ExtentLogger.logInfo("Solar commodity CSP enable/disable status : " + isSolarModulePresent);
        if (isUsageModulePresent == 1 && isSolarModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the export to excel usages data
                usageSteps.verifyElectricExportToExcelFunctionality(softAssert, meters);
            }
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifySolarUsageData - is Completed.");
        log.info("Test Case execution for - verifySolarUsageData - is Completed.");
    }

    @TestRail(testCaseId = {77725, 77726, 120108, 77757, 77758, 120109, 77782, 120110, 107709})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 19, description = "To verify the export to excel functionality.")
    public void verifyExportToExcelWaterData() {
        log.info("To verify the tests with the below test case id's: " +
                "C77725, C77726, C120108, C77757, C77758, C120109, C77782, C120110, C107709");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C77725, C77726, C120108, C77757, C77758, C120109, C77782, C120110, C107709");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isSolarModulePresent = CSPConfiguration.initCSPConfig().get("Solar");
        ExtentLogger.logInfo("Solar commodity CSP enable/disable status : " + isSolarModulePresent);
        if (isUsageModulePresent == 1 && isSolarModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the export to excel usages data
                usageSteps.verifyWaterExportToExcelFunctionality(softAssert, meters);
            }
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifySolarUsageData - is Completed.");
        log.info("Test Case execution for - verifySolarUsageData - is Completed.");
    }

    @TestRail(testCaseId = {77848, 120112, 77849, 77880, 77881, 120113, 77903, 120114, 107690})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 20, description = "To verify the export to excel functionality.")
    public void verifyExportToExcelGasData() {
        log.info("To verify the tests with the below test case id's: " +
                "C77848, C120112, C77849, C77880, C77881, C120113, C77903, C120114, C107690");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C77848, C120112, C77849, C77880, C77881, C120113, C77903, C120114, C107690");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isSolarModulePresent = CSPConfiguration.initCSPConfig().get("Solar");
        ExtentLogger.logInfo("Solar commodity CSP enable/disable status : " + isSolarModulePresent);
        if (isUsageModulePresent == 1 && isSolarModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the export to excel usages data
                usageSteps.verifyGasExportToExcelFunctionality(softAssert, meters);
            }
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifySolarUsageData - is Completed.");
        log.info("Test Case execution for - verifySolarUsageData - is Completed.");
    }

    @TestRail(testCaseId = {77958, 77971, 120117, 77959})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 21, description = "To verify the export to excel functionality.")
    public void verifyExportToExcelGenerationData() {
        log.info("To verify the tests with the below test case id's: " +
                "C77958, C77971, C120117, C77959");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C77958, C77971, C120117, C77959");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isSolarModulePresent = CSPConfiguration.initCSPConfig().get("Solar");
        ExtentLogger.logInfo("Solar commodity CSP enable/disable status : " + isSolarModulePresent);
        if (isUsageModulePresent == 1 && isSolarModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the export to excel usages data
                usageSteps.verifyGenerationExportToExcelFunctionality(softAssert, meters);
            }
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifySolarUsageData - is Completed.");
        log.info("Test Case execution for - verifySolarUsageData - is Completed.");
    }

    @TestRail(testCaseId = {76984, 76988})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 22, description = "To verify the electric usage color logic.")
    public void verifyElectricUsageColorLogic() {
        log.info("To verify the tests with the below test case id's: " +
                "C76984, C76988");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C76984, C76988");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isSolarModulePresent = CSPConfiguration.initCSPConfig().get("Solar");
        ExtentLogger.logInfo("Solar commodity CSP enable/disable status : " + isSolarModulePresent);
        if (isUsageModulePresent == 1 && isSolarModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the electric usage color logic
                usageSteps.verifyElectricUsageColorLogic(softAssert, meters);
            }
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifyElectricUsageColorLogic - is Completed.");
        log.info("Test Case execution for - verifyElectricUsageColorLogic - is Completed.");
    }

    @TestRail(testCaseId = {77748, 77749})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 23, description = "To verify the water usage color logic.")
    public void verifyWaterUsageColorLogic() {
        log.info("To verify the tests with the below test case id's: " +
                "C77748, C77749");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C77748, C77749");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isSolarModulePresent = CSPConfiguration.initCSPConfig().get("Solar");
        ExtentLogger.logInfo("Solar commodity CSP enable/disable status : " + isSolarModulePresent);
        if (isUsageModulePresent == 1 && isSolarModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the export to excel usages data
                usageSteps.verifyWaterUsageColorLogic(softAssert, meters);
            }
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifyWaterUsageColorLogic - is Completed.");
        log.info("Test Case execution for - verifyWaterUsageColorLogic - is Completed.");
    }

    @TestRail(testCaseId = {77871, 77872})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 24, description = "To verify the gas usage color logic.")
    public void verifyGasUsageColorLogic() {
        log.info("To verify the tests with the below test case id's: " +
                "C77871, C77872");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C77871, C77872");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isSolarModulePresent = CSPConfiguration.initCSPConfig().get("Solar");
        ExtentLogger.logInfo("Solar commodity CSP enable/disable status : " + isSolarModulePresent);
        if (isUsageModulePresent == 1 && isSolarModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the export to excel usages data
                usageSteps.verifyGasUsageColorLogic(softAssert, meters);
            }
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifyGasUsageColorLogic - is Completed.");
        log.info("Test Case execution for - verifyGasUsageColorLogic - is Completed.");
    }

    @TestRail(testCaseId = {76977})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 25, description = "To verify the electric average and highest usages.")
    public void verifyAverageHighestElectricUsage() {
        log.info("To verify the tests with the below test case id's: " +
                "C76977");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C76977");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(
                dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isElectricModulePresent = CSPConfiguration.initCSPConfig().get("Power");
        ExtentLogger.logInfo("Electric commodity CSP enable/disable status : " + isElectricModulePresent);
        if (isUsageModulePresent == 1 && isElectricModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Verify account drop-down
            ResultSet resultSet;
            int linkedAccounts = 0;
            try {
                resultSet = DataBaseUtils.getResultSet(SQLQueries.getLinkedAccountNumberCount(user.getUserName()));
                resultSet.next();
                linkedAccounts = resultSet.getInt("count");
            } catch (SQLException e) {
                e.printStackTrace();
                ExtentLogger.logException(e);
            }
            if (linkedAccounts > 1) {
                softAssert.assertTrue(usageSteps.isAccountDropDownButtonVisible(),
                        "Account drop-down button is not visible.");
            }
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the dashboard page objects
                usageSteps.verifyAverageAndHighestForElectric(softAssert, user, meters);
            }
            // Verify monthly electric usage data for Non AMI meter scenario
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifyAverageHighestElectricUsage - is Completed.");
        log.info("Test Case execution for - verifyAverageHighestElectricUsage - is Completed.");
    }

    @TestRail(testCaseId = {77741, 77768, 77792})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 26, description = "To verify the water average and highest usages.")
    public void verifyAverageHighestWaterUsage() {
        log.info("To verify the tests with the below test case id's: " +
                "C77741, C77768, C77792");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C77741, C77768, C77792");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(
                dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isWaterModulePresent = CSPConfiguration.initCSPConfig().get("Water");
        ExtentLogger.logInfo("Water commodity CSP enable/disable status : " + isWaterModulePresent);
        if (isUsageModulePresent == 1 && isWaterModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Verify account drop-down
            ResultSet resultSet;
            int linkedAccounts = 0;
            try {
                resultSet = DataBaseUtils.getResultSet(SQLQueries.getLinkedAccountNumberCount(user.getUserName()));
                resultSet.next();
                linkedAccounts = resultSet.getInt("count");
            } catch (SQLException e) {
                e.printStackTrace();
                ExtentLogger.logException(e);
            }
            if (linkedAccounts > 1) {
                softAssert.assertTrue(usageSteps.isAccountDropDownButtonVisible(),
                        "Account drop-down button is not visible.");
            }
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the dashboard page objects
                usageSteps.verifyAverageAndHighestForWater(softAssert, user, meters);
            }
            // Verify monthly electric usage data for Non AMI meter scenario
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifyAverageHighestWaterUsage - is Completed.");
        log.info("Test Case execution for - verifyAverageHighestWaterUsage - is Completed.");
    }

    @TestRail(testCaseId = {77891, 77864, 77913})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 27, description = "To verify the gas average and highest usages.")
    public void verifyAverageHighestGasUsage() {
        log.info("To verify the tests with the below test case id's: " +
                "C77891, C77864, C77913");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "C77891, C77864, C77913");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(
                dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isGasModulePresent = CSPConfiguration.initCSPConfig().get("Gas");
        ExtentLogger.logInfo("Gas commodity CSP enable/disable status : " + isGasModulePresent);
        if (isUsageModulePresent == 1 && isGasModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Verify account drop-down
            ResultSet resultSet;
            int linkedAccounts = 0;
            try {
                resultSet = DataBaseUtils.getResultSet(SQLQueries.getLinkedAccountNumberCount(user.getUserName()));
                resultSet.next();
                linkedAccounts = resultSet.getInt("count");
            } catch (SQLException e) {
                e.printStackTrace();
                ExtentLogger.logException(e);
            }
            if (linkedAccounts > 1) {
                softAssert.assertTrue(usageSteps.isAccountDropDownButtonVisible(),
                        "Account drop-down button is not visible.");
            }
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the dashboard page objects
                usageSteps.verifyAverageAndHighestForGas(softAssert, user, meters);
            }
            // Verify monthly electric usage data for Non AMI meter scenario
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifyAverageHighestGasUsage - is Completed.");
        log.info("Test Case execution for - verifyAverageHighestGasUsage - is Completed.");
    }

    @TestRail(testCaseId = {})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 28, description = "To verify the electric projected and so far usages.")
    public void verifySoFarProjectedElectricUsage() {
        log.info("To verify the tests with the below test case id's: " +
                "");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(
                dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isElectricModulePresent = CSPConfiguration.initCSPConfig().get("Power");
        ExtentLogger.logInfo("Electric commodity CSP enable/disable status : " + isElectricModulePresent);
        if (isUsageModulePresent == 1 && isElectricModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Verify account drop-down
            ResultSet resultSet;
            int linkedAccounts = 0;
            try {
                resultSet = DataBaseUtils.getResultSet(SQLQueries.getLinkedAccountNumberCount(user.getUserName()));
                resultSet.next();
                linkedAccounts = resultSet.getInt("count");
            } catch (SQLException e) {
                e.printStackTrace();
                ExtentLogger.logException(e);
            }
            if (linkedAccounts > 1) {
                softAssert.assertTrue(usageSteps.isAccountDropDownButtonVisible(),
                        "Account drop-down button is not visible.");
            }
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the projected and so far this month
                usageSteps.verifyProjectedSoFarForElectric(softAssert, user, meters);
            }
            // Verify monthly electric usage data for Non AMI meter scenario
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifySoFarProjectedElectricUsage - is Completed.");
        log.info("Test Case execution for - verifySoFarProjectedElectricUsage - is Completed.");
    }

    @TestRail(testCaseId = {})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 29, description = "To verify the water projected and so far usages.")
    public void verifySoFarProjectedWaterUsage() {
        log.info("To verify the tests with the below test case id's: " +
                "");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(
                dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isWaterModulePresent = CSPConfiguration.initCSPConfig().get("Water");
        ExtentLogger.logInfo("Water commodity CSP enable/disable status : " + isWaterModulePresent);
        if (isUsageModulePresent == 1 && isWaterModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Verify account drop-down
            ResultSet resultSet;
            int linkedAccounts = 0;
            try {
                resultSet = DataBaseUtils.getResultSet(SQLQueries.getLinkedAccountNumberCount(user.getUserName()));
                resultSet.next();
                linkedAccounts = resultSet.getInt("count");
            } catch (SQLException e) {
                e.printStackTrace();
                ExtentLogger.logException(e);
            }
            if (linkedAccounts > 1) {
                softAssert.assertTrue(usageSteps.isAccountDropDownButtonVisible(),
                        "Account drop-down button is not visible.");
            }
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the dashboard page objects
                usageSteps.verifyProjectedSoFarForWater(softAssert, user, meters);
            }
            // Verify monthly electric usage data for Non AMI meter scenario
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifySoFarProjectedWaterUsage - is Completed.");
        log.info("Test Case execution for - verifySoFarProjectedWaterUsage - is Completed.");
    }

    @TestRail(testCaseId = {})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 30, description = "To verify the gas projected and so far usages.")
    public void verifySoFarProjectedGasUsage() {
        log.info("To verify the tests with the below test case id's: " +
                "");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(
                dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isGasModulePresent = CSPConfiguration.initCSPConfig().get("Gas");
        ExtentLogger.logInfo("Gas commodity CSP enable/disable status : " + isGasModulePresent);
        if (isUsageModulePresent == 1 && isGasModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Verify account drop-down
            ResultSet resultSet;
            int linkedAccounts = 0;
            try {
                resultSet = DataBaseUtils.getResultSet(SQLQueries.getLinkedAccountNumberCount(user.getUserName()));
                resultSet.next();
                linkedAccounts = resultSet.getInt("count");
            } catch (SQLException e) {
                e.printStackTrace();
                ExtentLogger.logException(e);
            }
            if (linkedAccounts > 1) {
                softAssert.assertTrue(usageSteps.isAccountDropDownButtonVisible(),
                        "Account drop-down button is not visible.");
            }
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the dashboard page objects
                usageSteps.verifyProjectedSoFarForGas(softAssert, user, meters);
            }
            // Verify monthly electric usage data for Non AMI meter scenario
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifySoFarProjectedGasUsage - is Completed.");
        log.info("Test Case execution for - verifySoFarProjectedGasUsage - is Completed.");
    }

    @TestRail(testCaseId = {})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 28, description = "To verify the weather overlay data for electric meter.")
    public void verifyWeatherOverlayElectricData() {
        log.info("To verify the tests with the below test case id's: " +
                "");
        ExtentLogger.logInfo("To verify the tests with the below test case id's: " +
                "");
        SoftAssert softAssert = new SoftAssert();
        // Init usages steps
        usageSteps = new UsageSteps(driver);
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init dashboard steps
        DashboardSteps dashboardSteps;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(
                dashboardSteps.isProfileIconVisible(),
                "User profile icon not visible."
        );
        ExtentLogger.logInfo("Dashboard profile icon visibility status : " + dashboardSteps.isProfileIconVisible());
        // Navigate to the usages page
        HomeSteps homeSteps = new HomeSteps(driver);
        int isUsageModulePresent = CSPConfiguration.initCSPConfig().get("Usage");
        ExtentLogger.logInfo("Usages module CSP enable/disable status : " + isUsageModulePresent);
        int isElectricModulePresent = CSPConfiguration.initCSPConfig().get("Power");
        ExtentLogger.logInfo("Electric commodity CSP enable/disable status : " + isElectricModulePresent);
        if (isUsageModulePresent == 1 && isElectricModulePresent == 1) {
            homeSteps.navigateToUsageOverview();
            ExtentLogger.logInfo("Usages page url : " + usageTextProp.getPropValue("usagePageUrl"));
            ExtentLogger.logInfo("Usages page title : " + usageTextProp.getPropValue("usagePageTitle"));
            // Verify user navigated to the usages page
            Assert.assertTrue(dashboardSteps.isDashboardPage(
                            usageTextProp.getPropValue("usagePageUrl"),
                            usageTextProp.getPropValue("usagePageTitle")),
                    "Not usages page."
            );
            ExtentLogger.logInfo("Navigated to usages page successfully.");
            // Init meter config
            meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
            // Verifying page Header
            softAssert.assertTrue(usageSteps.isPageHeaderVisible(), "Usage page header is not visible.");
            softAssert.assertEquals(usageSteps.getPageHeader(), usageTextProp.getPropValue("usagePageHeader"),
                    "Usage page header label not matched.");
            // Verify account drop-down
            ResultSet resultSet;
            int linkedAccounts = 0;
            try {
                resultSet = DataBaseUtils.getResultSet(SQLQueries.getLinkedAccountNumberCount(user.getUserName()));
                resultSet.next();
                linkedAccounts = resultSet.getInt("count");
            } catch (SQLException e) {
                e.printStackTrace();
                ExtentLogger.logException(e);
            }
            if (linkedAccounts > 1) {
                softAssert.assertTrue(usageSteps.isAccountDropDownButtonVisible(),
                        "Account drop-down button is not visible.");
            }
            // Check meters are linked to the account under test
            if (meters.length > 0) {
                ExtentLogger.logInfo("Meters are linked to the account.");
                // Verify the projected and so far this month
                usageSteps.verifyElectricWeatherOverlayData(softAssert, user, meters);
            }
            // Verify monthly electric usage data for Non AMI meter scenario
            else {
                ExtentLogger.logSkip("There is no meter linked to the account.");
            }
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifySoFarProjectedElectricUsage - is Completed.");
        log.info("Test Case execution for - verifySoFarProjectedElectricUsage - is Completed.");
    }
}

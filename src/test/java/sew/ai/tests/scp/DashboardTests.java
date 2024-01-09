package sew.ai.tests.scp;

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

public class DashboardTests extends TestRunner {
    private static final Logger log = LogManager.getLogger(DashboardTests.class);
    private DashboardSteps dashboardSteps;

    @TestRail(testCaseId = {77192, 77193, 77083, 77084, 77085, 77086, 77091, 77093, 77285, 77087})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyDashboardPageObjects() {
        log.info("To verify the tests with the below test case id's: " +
                "C77192, C77193, C77083, C77084, C77085, C77086, C77091, C77093, C77285, C77087");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
        // Verify the dashboard page objects
        dashboardSteps.verifyTheDashboardPageObjectSteps(softAssert, user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }

    @TestRail(testCaseId = {77286, 77287, 77289, 77290, 77341, 77342, 77343})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 2, description = "To verify the dashboard page bill summary section objects after login.")
    public void verifyBillingSummarySectionObjects() {
        log.info("To verify the tests with the below test case id's: " +
                "C77286, C77287, C77289, C77290, C77341, C77342, C77343");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
        // Verify the dashboard page objects
        dashboardSteps.verifyBillingSummarySectionSteps(softAssert, user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyBillingSummarySectionPageObjects - is Completed.");
    }

    @TestRail(testCaseId = {77339, 77340})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.REGRESSION})
    @Test(priority = 3, description = "To verify the dashboard page bill summary section with different accounts.")
    public void verifyBillingSectionWithMultipleAcc() {
        log.info("To verify the tests with the below test case id's: " +
                "C77339, C77340");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
        // Verify the dashboard page objects
        dashboardSteps.verifyBillingSectionWithMultipleAcc(softAssert, user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyBillingSectionWithMultipleAcc - is Completed.");
    }

    @TestRail(testCaseId = {77344})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.REGRESSION})
    @Test(priority = 4, description = "To verify the dashboard page bill summary section with user opts for pay" +
            " as you go.")
    public void verifyBillingSectionForPayAsYouGo() {
        log.info("To verify the tests with the below test case id's: " +
                "C77344");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
        // Verify the dashboard page objects
        dashboardSteps.verifyBillingSectionForPayAsYouGo(softAssert, user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyBillingSectionForPayAsYouGo - is Completed.");
    }

    @TestRail(testCaseId = {77366, 77380, 77381, 77382, 107740, 107744, 107745, 121509, 107746, 107749,
            107753, 121492})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 5, description = "To verify the usage feature on the dashboard page.")
    public void verifyUsageFeatureOnDashboard() {
        log.info("To verify the tests with the below test case id's: " +
                "C77366, C77380, C77381, C77382, C107740, C107744, C107745, C121509, C107746, C107749, " +
                "C107753, C121492");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
        // Verify the dashboard page objects
        dashboardSteps.verifyDashboardUsages(softAssert, user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyUsageTileObjects - is Completed.");
    }

    @TestRail(testCaseId = {77396, 77397, 77398, 77399, 77400})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 6, description = "To verify the autopay carousel feature on the dashboard page.")
    public void verifyAutoPayCarousel() {
        log.info("To verify the tests with the below test case id's: " +
                "C77396, C77397, C77398, C77399, C77400");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
        // Verify the dashboard page objects
        dashboardSteps.verifyAutoPayCarousel(softAssert, user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyAutoPayCarousel - is Completed.");
    }

    @TestRail(testCaseId = {77405})
    @FrameworkAnnotations(author = {"Rahul Rana"}, category = {CategoryType.SANITY})
    @Test(priority = 7, description = "To verify the smart home carousel feature on the dashboard page.")
    public void verifySmartHomeCarousel() {
        log.info("To verify the tests with the below test case id's: " +
                "C77405");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
        // Verify the dashboard page objects
        dashboardSteps.verifySmartHomeCarousel(softAssert);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifySmartHomeCarousel - is Completed.");
    }
}

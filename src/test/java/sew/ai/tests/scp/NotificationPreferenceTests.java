package sew.ai.tests.scp;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.config.SCPConfiguration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.testrail.TestRail;
import sew.ai.models.Customer;
import sew.ai.models.User;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.LoginSteps;
import sew.ai.steps.scp.NotificationPreferenceSteps;

public class NotificationPreferenceTests extends TestRunner {
    private static final Logger log = LogManager.getLogger(NotificationPreferenceTests.class);
    private NotificationPreferenceSteps  notificationPreferenceSteps;
    private DashboardSteps dashboardSteps;

    public NotificationPreferenceTests() {
        notificationPreferenceSteps = new NotificationPreferenceSteps(driver);
        
    }

    @TestRail(testCaseId = {74657, 74658})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyNotificationPreferencePageObjects() {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        notificationPreferenceSteps = new NotificationPreferenceSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        notificationPreferenceSteps.NotificationPreferencePageObjects(softAssert);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }

    
    @TestRail(testCaseId = {74659, 74660, 74661, 74662})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the TCPA Compliance Popup.")
    public void verifyTcpaCompliancePopup() {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init user model
        notificationPreferenceSteps = new NotificationPreferenceSteps(driver);
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the dashboard page objects
        notificationPreferenceSteps.TcpaCompliancePopup(softAssert);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyNotificationMobileNumberEmailAddres - is Completed.");
    }
    
    @TestRail(testCaseId = {74663, 74664, 74665})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyNotificationMobileNumberEmailAddress() throws SQLException {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        notificationPreferenceSteps = new NotificationPreferenceSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
        // Verify the dashboard page objects
        notificationPreferenceSteps.notificationMobileNumberEmailAddress(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyNotificationMobileNumberEmailAddres - is Completed.");
    }
    
    @TestRail(testCaseId = {74667, 78001, 78000,120091,120092,120093,120094})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyQuietHoursFunctionalityAndUI() throws SQLException {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        notificationPreferenceSteps = new NotificationPreferenceSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
        // Verify the dashboard page objects
       notificationPreferenceSteps.quietHoursFunctionalityAndUI(softAssert, user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    
    
    @TestRail(testCaseId = {78253, 79414})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyNotificationMaintainOnAccountLevel() throws SQLException {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        notificationPreferenceSteps = new NotificationPreferenceSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
        // Verify the dashboard page objects
       notificationPreferenceSteps.notificationMaintainOnAccountLevel(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    
    @TestRail(testCaseId = {78005, 78004, 74683, 74676})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyNotificationAlertStatusAndNoImpactOfQuietHOursOnEmail() throws SQLException {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        notificationPreferenceSteps = new NotificationPreferenceSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
        // Verify the dashboard page objects
        notificationPreferenceSteps.notificationAlertStatusAndNoImpactOfQuietHOursOnEmail(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    @TestRail(testCaseId = {74670, 78006, 74671})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyAddNotificationChannelFunctionality() {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        notificationPreferenceSteps = new NotificationPreferenceSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
        // Verify the dashboard page objects
       notificationPreferenceSteps.addNotificationChannelFunctionality(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    
    @TestRail(testCaseId = {79589})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyNotificationsChannelsValidations() {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        notificationPreferenceSteps = new NotificationPreferenceSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
        // Verify the dashboard page objects
       notificationPreferenceSteps.notificationsChannelsValidations(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    
    @TestRail(testCaseId = {74673, 74677, 74680, 74681})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyNotificationAlertsValidationMessages() throws SQLException {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        notificationPreferenceSteps = new NotificationPreferenceSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
        // Verify the dashboard page objects
         notificationPreferenceSteps.notificationAlertsValidationMessages(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    @TestRail(testCaseId = {78252})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyNotificationInboxOnNoPreferenceAlert() throws SQLException {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        notificationPreferenceSteps = new NotificationPreferenceSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
        // Verify the dashboard page objects
         notificationPreferenceSteps.notificationInboxOnNoPreferenceAlert(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    @TestRail(testCaseId = {78252})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void hideAndUnHideNotificationPreference () {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        notificationPreferenceSteps = new NotificationPreferenceSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
        // Verify the dashboard page objects
      ///  notificationPreferenceSteps.NotificationPreferencePageObjects();
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    @TestRail(testCaseId = {78003,78002})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyNotificationPreferenceForRegisteredUser() throws SQLException {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        notificationPreferenceSteps = new NotificationPreferenceSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
        Customer customer = SCPConfiguration.initCustomerConfig("John Doe","1");
        // Verify the dashboard page objects
        notificationPreferenceSteps.notificationPreferenceForRegisteredUser(softAssert,user,customer);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    
    @TestRail(testCaseId = {120095,120099})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyQuietHoursFunctionalityAndUIforEmailAddress() throws SQLException {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        notificationPreferenceSteps = new NotificationPreferenceSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(), "User profile icon not visible.");
        // Verify the dashboard page objects
        notificationPreferenceSteps.quietHoursFunctionalityAndUIforEmailAddress(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    

    }

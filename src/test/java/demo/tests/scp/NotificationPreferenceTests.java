package demo.tests.scp;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import sew.ai.config.Configuration;
import sew.ai.config.SCPConfiguration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.models.User;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.HomeSteps;
import sew.ai.steps.scp.LoginSteps;
import demo.steps.scp.NotificationPreferenceSteps;

public class NotificationPreferenceTests extends TestRunner {
    private static final Logger log = LogManager.getLogger(NotificationPreferenceTests.class);
    private NotificationPreferenceSteps  notificationPreferenceSteps;
    private DashboardSteps dashboardSteps;

    public NotificationPreferenceTests() {
        notificationPreferenceSteps = new NotificationPreferenceSteps(driver);
    }

    @FrameworkAnnotations(author = {"Priya Awasthi"}, category = {CategoryType.SANITY})
    @Test( description = "To verify Notification Preference PageObjects.")
    public void verifyNotificationPreferencePageObjects() {
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        notificationPreferenceSteps = new NotificationPreferenceSteps(driver);
        dashboardSteps   = new DashboardSteps(driver);
        // Login into the application
	   loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));		
		HomeSteps homeSteps = new HomeSteps(driver);
        notificationPreferenceSteps.notificationPreferencePageObjects(softAssert);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyNotificationPreferencePageObjects - is Completed.");
    }
    
    @FrameworkAnnotations(author = {"Priya"}, category = {CategoryType.SANITY})
    @Test( description = "To verify the TCPA Compliance Popup.")
    public void verifyTcpaCompliancePopup() {
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init user model
        notificationPreferenceSteps = new NotificationPreferenceSteps(driver);
        User user = SCPConfiguration.user;
        // Login into the application
 	   loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));		
        notificationPreferenceSteps.tcpaCompliancePopup(softAssert);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyTcpaCompliancePopup - is Completed.");
    }
     
    @FrameworkAnnotations(author = {"Priya Awasthi"}, category = {CategoryType.SANITY})
    @Test(description = "To verify QuietHoursFunctionality And UI.")
    public void verifyQuietHoursFunctionalityAndUI() throws SQLException {
         SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        notificationPreferenceSteps = new NotificationPreferenceSteps(driver);
        // Login into the application
   	   loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));		
         // Verify the dashboard page objects
       notificationPreferenceSteps.quietHoursFunctionalityAndUI(softAssert);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - QuietHoursFunctionality - is Completed.");
    }
       
    @FrameworkAnnotations(author = {"Priya Awasthi"}, category = {CategoryType.SANITY})
    @Test( description = "To verify the add NotificationChannelFunctionality.")
    public void verifyAddNotificationChannelFunctionality() {
              SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        notificationPreferenceSteps = new NotificationPreferenceSteps(driver);
        // Login into the application
    	   loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));		
       notificationPreferenceSteps.addNotificationChannelFunctionality(softAssert);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - NotificationChannelFunctionality - is Completed.");
    }
    
    @FrameworkAnnotations(author = {"Priya Awasthi"}, category = {CategoryType.SANITY})
    @Test(description = "To verify Notification Alerts Validati on Messages.")
    public void verifyNotificationAlertsValidationMessages() throws SQLException {
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        notificationPreferenceSteps = new NotificationPreferenceSteps(driver);
        // Login into the application
    	   loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));		
    notificationPreferenceSteps.notificationAlertsValidationMessages(softAssert);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyNotificationAlertsValidationMessages - is Completed.");
    }

    }

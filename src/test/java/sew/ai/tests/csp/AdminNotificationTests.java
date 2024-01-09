package sew.ai.tests.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.api.endpoints.connectme.ConnectMeEndpoints;
import sew.ai.config.Configuration;
import sew.ai.config.ModelsConfiguration;
import sew.ai.config.SCPConfiguration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.helpers.testrail.TestRail;
import sew.ai.models.ConnectMe;
import sew.ai.models.Services;
import sew.ai.models.User;
import sew.ai.runner.TestRunner;
import sew.ai.steps.csp.AdminHomeSteps;
import sew.ai.steps.csp.AdminLoginSteps;
import sew.ai.steps.csp.AdminNotificationSteps;
import sew.ai.utils.RandomUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static sew.ai.config.SCPConfiguration.user;
import static sew.ai.steps.csp.AdminNotificationSteps.adminNotificationTextProp;

public class AdminNotificationTests extends TestRunner {

    private static final Logger log = LogManager.getLogger(AdminNotificationTests.class);

    private AdminNotificationSteps adminNotificationSteps;

    public enum NotificationSubFolders{
        Inbox,Connect_Me,Billing,Outage,Services,Demand_Response,Leak_Alert,Login_Issues,Sent,Complete,Saved_Mail,Trash,Auto_Response,Send_Notification;
    }


    @TestRail(testCaseId = {80451, 80452, 80483})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80451, C80452, C80483 - Verfying homepage, Welcome message and Sign-out button")
    public void verifyNotificationUIAndSubTabs() {
        log.info("To verify the tests with the below test case id's: " +
                "C80451, C80452, C80483");
        SoftAssert softAssert = new SoftAssert();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        adminHomeSteps.navigateToNotifications();
        //Verifying the Sub Tabs
        adminNotificationSteps = new AdminNotificationSteps(driver);
        adminNotificationSteps.verifyNotificationSubTabs(softAssert);
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyNotificationUIAndSubTabs - is Completed.");
    }

    @TestRail(testCaseId = {80455, 80456, 80473})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80455, C80456, C80473 - Verify the Sent Mail Tab Table Headers")
    public void verifySentMailTab() {
        log.info("To verify the tests with the below test case id's: " +
                "C80455, C80456, C80473");
        SoftAssert softAssert = new SoftAssert();
        //Create ConnectMe Outage through API
        adminNotificationSteps = new AdminNotificationSteps(driver);
        String outageRequestID = adminNotificationSteps.generateConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Navigate to ConnectMe Folder
        adminNotificationSteps.navigateToNotificationByRequestId(NotificationSubFolders.Connect_Me.name(), outageRequestID);
        //Send Reply to Notification
        String replyText = "Send Reply to Test Notifications present on Sent Folder";
        adminNotificationSteps.sendReplyToNotification(softAssert, replyText);
        //Verify the Grid Headers
        adminNotificationSteps.verifyNotificationTableHeaders(NotificationSubFolders.Sent.name(), softAssert);
        //Set the page Number to 200.
        adminNotificationSteps.setPageSizeOfNotifications(200);
        //Navigate to Specific Notification Page by Request ID.
        adminNotificationSteps.navigateToNotificationSpecificPageByRequestId(outageRequestID);
        //Verify Sent Reply
        softAssert.assertEquals(adminNotificationSteps.getMessageFieldValueMsgDetail(), replyText,
                "Replied Text was not present on the Replied Notification on the Sent Folder.");
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifySentMailTab - is Completed.");
    }

    @TestRail(testCaseId = {80460, 80461, 99055, 114268})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80460, C80461, C99055, C114268 - Verify the Details screen of a notification")
    public void verifyMessageDetailsNotification() {
        log.info("To verify the tests with the below test case id's: " +
                "C80456");
        SoftAssert softAssert = new SoftAssert();
        //Create Outage via API
        adminNotificationSteps = new AdminNotificationSteps(driver);
        HashMap responseData = adminNotificationSteps.generateConnectMeOutageAPIRequest();
        String OutageRequestID = responseData.get("OutageRequestID").toString();
        String emailID = ConnectMeEndpoints.reportOutage.getFromEMail();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Navigate to Connect Me Sub Module
        adminNotificationSteps.navigateSubModule(NotificationSubFolders.Connect_Me.name());
        //Navigate to Specific Notification By ID
        adminNotificationSteps.navigateToNotificationByRequestId(OutageRequestID);
        //Verify the Message Details Notification Objects
        adminNotificationSteps.verifyMessageDetailsNotificationObjects(softAssert);
        //Verify the Message Details Field Values.
        adminNotificationSteps.verifyMessageDetailsValues(softAssert, OutageRequestID, emailID);
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyMessageDetailsNotification - is Completed.");
    }

    @TestRail(testCaseId = {80458, 80459})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80458, C80459  - Verify the read or unread properties of a notification")
    public void verifyReadUnreadMessagesText() {
        log.info("To verify the tests with the below test case id's: " +
                "C80458, C80459");
        SoftAssert softAssert = new SoftAssert();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Navigate to Connect Me Sub Module
        adminNotificationSteps = new AdminNotificationSteps(driver);
        adminNotificationSteps.navigateSubModule(NotificationSubFolders.Connect_Me.name());
        //Verify the Read or Unread properties of a notification
        adminNotificationSteps.verifyReadUnreadPropertiesOfNotification(softAssert);
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyReadUnreadMessagesText - is Completed.");
    }

    @TestRail(testCaseId = {101609})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C101609  - Verify the Checkbox on top pane and in-front of each notification")
    public void verifyCheckBoxOnNotifications() {
        log.info("To verify the tests with the below test case id's: " +
                "C101609");
        SoftAssert softAssert = new SoftAssert();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Verify Checkbox on Notifications.
        adminNotificationSteps = new AdminNotificationSteps(driver);
        adminNotificationSteps.verifyCheckBoxOnNotifications(softAssert, NotificationSubFolders.Inbox.name());
        adminNotificationSteps.verifyCheckBoxOnNotifications(softAssert, NotificationSubFolders.Connect_Me.name());
        adminNotificationSteps.verifyCheckBoxOnNotifications(softAssert, NotificationSubFolders.Billing.name());
        adminNotificationSteps.verifyCheckBoxOnNotifications(softAssert, NotificationSubFolders.Outage.name());
        adminNotificationSteps.verifyCheckBoxOnNotifications(softAssert, NotificationSubFolders.Services.name());
        adminNotificationSteps.verifyCheckBoxOnNotifications(softAssert, NotificationSubFolders.Demand_Response.name());
        adminNotificationSteps.verifyCheckBoxOnNotifications(softAssert, NotificationSubFolders.Leak_Alert.name());
        adminNotificationSteps.verifyCheckBoxOnNotifications(softAssert, NotificationSubFolders.Login_Issues.name());
//        adminNotificationSteps.verifyCheckBoxOnNotifications(softAssert,NotificationSubFolders.Sent.name()); //todo open issue [SCM10-11364]
        adminNotificationSteps.verifyCheckBoxOnNotifications(softAssert, NotificationSubFolders.Complete.name());
        adminNotificationSteps.verifyCheckBoxOnNotifications(softAssert, NotificationSubFolders.Saved_Mail.name());
        adminNotificationSteps.verifyCheckBoxOnNotifications(softAssert, NotificationSubFolders.Trash.name());
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyCheckBoxOnNotifications - is Completed.");
    }

    @TestRail(testCaseId = {80457})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80457  - Verify the Sorting on the Notification Grid Headers")
    public void verifyNotificationSorting() {
        log.info("To verify the tests with the below test case id's: " +
                "C80457");
        SoftAssert softAssert = new SoftAssert();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Init Admin Notification Steps
        adminNotificationSteps = new AdminNotificationSteps(driver);
        adminNotificationSteps.navigateSubModule(NotificationSubFolders.Connect_Me.name());
        //Verify Sorting on Notifications Headers
        adminNotificationSteps.verifyNotificationSortingGridHeaders(softAssert);
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyNotificationSorting - is Completed.");
    }

    @TestRail(testCaseId = {80482})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80482  - Verify the From Column Value on the Notification is not blank")
    public void verifyFromColumnNotificationInbox() {
        log.info("To verify the tests with the below test case id's: " +
                "C80482");
        SoftAssert softAssert = new SoftAssert();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Init Admin Notification Steps
        adminNotificationSteps = new AdminNotificationSteps(driver);
        //Verify From Column is not blank
        adminNotificationSteps.verifyFromColumnNotifications(softAssert, NotificationSubFolders.Inbox.name());
        adminNotificationSteps.verifyFromColumnNotifications(softAssert, NotificationSubFolders.Connect_Me.name());
        adminNotificationSteps.verifyFromColumnNotifications(softAssert, NotificationSubFolders.Billing.name());
        adminNotificationSteps.verifyFromColumnNotifications(softAssert, NotificationSubFolders.Outage.name());
        adminNotificationSteps.verifyFromColumnNotifications(softAssert, NotificationSubFolders.Services.name());
        adminNotificationSteps.verifyFromColumnNotifications(softAssert, NotificationSubFolders.Demand_Response.name());
        adminNotificationSteps.verifyFromColumnNotifications(softAssert, NotificationSubFolders.Leak_Alert.name());
        adminNotificationSteps.verifyFromColumnNotifications(softAssert, NotificationSubFolders.Login_Issues.name());
//        adminNotificationSteps.verifyFromColumnNotifications(softAssert, NotificationSubFolders.Sent.name());//todo uncomment this after defect SCM10-11364
//        adminNotificationSteps.verifyFromColumnNotifications(softAssert, NotificationSubFolders.Complete.name());//todo uncomment this after defect SCM10-11364
//        adminNotificationSteps.verifyFromColumnNotifications(softAssert, NotificationSubFolders.Saved_Mail.name());//todo uncomment this after defect SCM10-11364
//        adminNotificationSteps.verifyFromColumnNotifications(softAssert, NotificationSubFolders.Trash.name());//todo uncomment this after defect SCM10-11364
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyFromColumnNotificationInbox - is Completed.");
    }

    @TestRail(testCaseId = {80464})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80464  - Verify the Label Text Displayed for Notification Sub-Modules when no data was present.")
    public void verifyNoDataForTabNotification() {
        log.info("To verify the tests with the below test case id's: " +
                "C80464");
        SoftAssert softAssert = new SoftAssert();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Init Admin Notification Steps
        adminNotificationSteps = new AdminNotificationSteps(driver);
        //Verify the No Data Label Text displayed
        adminNotificationSteps.verifyNoDataLabelDisplayOnNotifySubModule(softAssert, NotificationSubFolders.Inbox.name());
        adminNotificationSteps.verifyNoDataLabelDisplayOnNotifySubModule(softAssert, NotificationSubFolders.Connect_Me.name());
        adminNotificationSteps.verifyNoDataLabelDisplayOnNotifySubModule(softAssert, NotificationSubFolders.Billing.name());
        adminNotificationSteps.verifyNoDataLabelDisplayOnNotifySubModule(softAssert, NotificationSubFolders.Outage.name());
        adminNotificationSteps.verifyNoDataLabelDisplayOnNotifySubModule(softAssert, NotificationSubFolders.Services.name());
        adminNotificationSteps.verifyNoDataLabelDisplayOnNotifySubModule(softAssert, NotificationSubFolders.Demand_Response.name());
        adminNotificationSteps.verifyNoDataLabelDisplayOnNotifySubModule(softAssert, NotificationSubFolders.Leak_Alert.name());
        adminNotificationSteps.verifyNoDataLabelDisplayOnNotifySubModule(softAssert, NotificationSubFolders.Login_Issues.name());
        adminNotificationSteps.verifyNoDataLabelDisplayOnNotifySubModule(softAssert, NotificationSubFolders.Sent.name());
        adminNotificationSteps.verifyNoDataLabelDisplayOnNotifySubModule(softAssert, NotificationSubFolders.Complete.name());
        adminNotificationSteps.verifyNoDataLabelDisplayOnNotifySubModule(softAssert, NotificationSubFolders.Saved_Mail.name());
        adminNotificationSteps.verifyNoDataLabelDisplayOnNotifySubModule(softAssert, NotificationSubFolders.Trash.name());
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyNoDataForTabNotification - is Completed.");
    }

    @TestRail(testCaseId = {99050})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C99050  - Verify Customer Name and Mobile values not clickable on Customer informations from notification. ")
    public void verifyCustNameMobileNotClickableNotification() {
        log.info("To verify the tests with the below test case id's: " +
                "C99050");
        SoftAssert softAssert = new SoftAssert();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Init Admin Notification Steps
        adminNotificationSteps = new AdminNotificationSteps(driver);
        //Verify the Mobile Number and Customer Name not clickable
        adminNotificationSteps.verifyCustomerNameMobileHeaderFieldValuesNotClickable(softAssert, NotificationSubFolders.Connect_Me.name(), "outage notification");
        adminNotificationSteps.verifyCustomerNameMobileHeaderFieldValuesNotClickable(softAssert, NotificationSubFolders.Services.name(), "Move In");
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyCustNameMobileNotClickableNotification - is Completed.");
    }

    @TestRail(testCaseId = {99044, 99045, 99046, 99047, 99048, 99054})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C99044,C99045,C99046,C99047,C99048,C99054  - Verify SearchBy Email and Account Number Funcitonality.")
    public void verifySearchByFeature() {
        log.info("To verify the tests with the below test case id's: " +
                "C99044,C99045,C99046,C99047,C99048,C99054");
        SoftAssert softAssert = new SoftAssert();
        //Create Outage via API
        adminNotificationSteps = new AdminNotificationSteps(driver);
        HashMap responseData = adminNotificationSteps.generateConnectMeOutageAPIRequest();
        String outageRequestID = responseData.get("OutageRequestID").toString();
        String emailID = ConnectMeEndpoints.reportOutage.getFromEMail();
        String utilityAccountNumber = ConnectMeEndpoints.reportOutage.getUtilityAccountNumber();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Verify the SearchBy
        adminNotificationSteps.verifySearchByObjects(NotificationSubFolders.Connect_Me.name());
        //Verify the SearchBy Email
        adminNotificationSteps.verifySearchByEmailSearchFunctionality(softAssert, emailID);
        //Clear the Search Results
        adminNotificationSteps.clearSearchResults();
        //Verify the SearchBy Service Account
        adminNotificationSteps.verifySearchByServiceAcctSearchFunctionality(softAssert, utilityAccountNumber, outageRequestID);
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifySearchByFeature - is Completed.");
    }

    @TestRail(testCaseId = {99051, 99052, 99053, 99059, 99060})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C99051, C99052, C99053, C99059, C99060  - Verify SearchBy Email and Account Number Validation Messages.")
    public void verifySearchByValidationMessages() {
        log.info("To verify the tests with the below test case id's: " +
                "C99051, C99052, C99053, C99059, C99060");
        SoftAssert softAssert = new SoftAssert();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Verify the SearchBy Objects
        adminNotificationSteps = new AdminNotificationSteps(driver);
        adminNotificationSteps.verifySearchByObjects(NotificationSubFolders.Connect_Me.name());
        //Verify the SearchBy Validation Messages.
        adminNotificationSteps.verifySearchByValidationMessages(softAssert);
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifySearchByValidationMessages - is Completed.");
    }

    @TestRail(testCaseId = {99063})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C99063  - Verify Clear Button Functionality of SearchBy.")
    public void verifyClearButtonSearchBy() {
        log.info("To verify the tests with the below test case id's: " +
                "C99063");
        SoftAssert softAssert = new SoftAssert();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Verify the SearchBy Objects
        adminNotificationSteps = new AdminNotificationSteps(driver);
        adminNotificationSteps.verifySearchByObjects(NotificationSubFolders.Connect_Me.name());
        //Verify the SearchBy Clear Button Functionality.
        adminNotificationSteps.verifyClearBtnFunctionality(softAssert);
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyClearButtonSearchBy - is Completed.");
    }

    @TestRail(testCaseId = {80468, 80469, 99049})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80468,C80469,C99049 - Verify the Inbox Customer Information Headers info with Customer360 Page Popup")
    public void verifyCustomerInformationInboxMail() {
        log.info("To verify the tests with the below test case id's: " +
                "C80468,C80469,C99049");
        SoftAssert softAssert = new SoftAssert();
        //Create Outage via API
        adminNotificationSteps = new AdminNotificationSteps(driver);
        HashMap responseData = adminNotificationSteps.generateConnectMeOutageAPIRequest();
        String outageRequestID = responseData.get("OutageRequestID").toString();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Verify the Notification Customer Header info with C360 Page
        adminNotificationSteps.verifyNotifyCustHeadersInfoWithC360Popup(softAssert, NotificationSubFolders.Inbox.name(), "outage notification");
        adminNotificationSteps.verifyNotifyCustHeadersInfoWithC360Popup(softAssert, NotificationSubFolders.Connect_Me.name(), outageRequestID);
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyCustomerInformationInboxMail - is Completed.");
    }

    @TestRail(testCaseId = {99062})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C99062 - Verify the Search Results refresh for search data")
    public void verifySearchResultsRefreshSearchBy() {
        log.info("To verify the tests with the below test case id's: " +
                "C99062");
        SoftAssert softAssert = new SoftAssert();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Verify the Search Results refresh for the Search Data.
        adminNotificationSteps = new AdminNotificationSteps(driver);
        adminNotificationSteps.verifySearchResultsRefreshForSearchData(softAssert, NotificationSubFolders.Connect_Me.name());
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifySearchResultsRefreshSearchBy - is Completed.");
    }

    @TestRail(testCaseId = {99042})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C99042 - Verify the Inbox shows both new and replied notifications")
    public void verifyInboxNewRepliedNotifications() {
        log.info("To verify the tests with the below test case id's: " +
                "C99062");
        SoftAssert softAssert = new SoftAssert();
        //Create Outage via API
        adminNotificationSteps = new AdminNotificationSteps(driver);
        adminNotificationSteps.generateConnectMeOutageAPIRequest();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Verify the Unread New Notifications on Inbox
        adminNotificationSteps.verifyNewUnreadNotificationsFontProperty(softAssert, NotificationSubFolders.Inbox.name());
        //Send Reply to notification
        adminNotificationSteps.sendReplyToFirstNotifications(softAssert, NotificationSubFolders.Inbox.name(), "Testing Reply Notifications");
        //Enable All Notifications Toggle view
        adminNotificationSteps.enableALlNotificationsView();
        //Verify the Replied Notification exist on the module.
        adminNotificationSteps.verifyRepliedNotificationPresent(softAssert, NotificationSubFolders.Inbox.name());
        //Verify the Read Notification property on Inbox
        adminNotificationSteps.verifyReadNotificationFontProperty(softAssert, NotificationSubFolders.Inbox.name());
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyInboxNewRepliedNotifications - is Completed.");
    }

    @TestRail(testCaseId = {99043})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C99043 - Verify the threads displayed on the notification modules.")
    public void verifyThreadsModuleNotifications() {
        log.info("To verify the tests with the below test case id's: " +
                "C99043");
        SoftAssert softAssert = new SoftAssert();
        //Create Outage via API
        adminNotificationSteps = new AdminNotificationSteps(driver);
        HashMap responseData = adminNotificationSteps.generateConnectMeOutageAPIRequest();
        String outageRequestID = responseData.get("OutageRequestID").toString();
        String serviceReqID = adminNotificationSteps.createMoveInServicesAPIRequest();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Navigate to the Specific ConnectMe Notification
        adminNotificationSteps.navigateToNotificationByRequestId(NotificationSubFolders.Connect_Me.name(), outageRequestID);
        //Send reply and Verify the thread and replied text for ConnectMe Notification
        adminNotificationSteps.verifyThreadModules(softAssert, NotificationSubFolders.Connect_Me.name(), "Test Reply to ConnectMe Notifications");
        //Navigate to the Specific Services Notification
        adminNotificationSteps.navigateToNotificationByRequestId(NotificationSubFolders.Services.name(), serviceReqID);
        //Send reply and Verify the thread and replied text for Services Notification
        adminNotificationSteps.verifyThreadModules(softAssert, NotificationSubFolders.Services.name(), "Test Reply to Services Notifications");
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyThreadsModuleNotifications - is Completed.");
    }

    @TestRail(testCaseId = {113236, 113238})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C113236,C113238 - Verify the Service Notification created on Inbox and Service sub-modules notification.")
    public void verifyInboxServiceNotifications() {
        log.info("To verify the tests with the below test case id's: " +
                "C113236,C113238");
        SoftAssert softAssert = new SoftAssert();
        //Create Services via API
        adminNotificationSteps = new AdminNotificationSteps(driver);
        String serviceReqID = adminNotificationSteps.createMoveInServicesAPIRequest();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Init the models
        User user = SCPConfiguration.user;
        Services services = ModelsConfiguration.readServices().getServicesByTopicName("MoveIn Services");
        //Verify the Inbox Move-In Service Notification
        adminNotificationSteps.verifyInboxMoveInServiceNotifications(softAssert, services, user);
        //Verify the Services Move-In Notification
        adminNotificationSteps.verifyServicesMoveInNotification(softAssert, serviceReqID, services);
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyInboxServiceNotifications - is Completed.");
    }

    @TestRail(testCaseId = {113233, 113234, 113235, 113237})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C113233,C113234,C113235,C113237 - Verify the ConnectMe Notification created on Inbox and ConnectMe sub-modules notification.")
    public void verifyInboxConnectMeNotifications() {
        log.info("To verify the tests with the below test case id's: " +
                "C113233,C113234,C113235,C113237 ");
        SoftAssert softAssert = new SoftAssert();
        //Create ConnectMe Programs via API
        adminNotificationSteps = new AdminNotificationSteps(driver);
        String programsRequestID = adminNotificationSteps.generateConnectMeProgramsAPIRequest();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Init the models
        User user = SCPConfiguration.user;
        ConnectMe connectMe = ModelsConfiguration.readConnectme().getConnectMeByTopicName("Programs");
        //Verify the Inbox ConnectMe Programs Notification
        adminNotificationSteps.verifyInboxConnectMeProgramsNotifications(softAssert, connectMe, user);
        //Verify the ConnectMe Programs Notification
        adminNotificationSteps.verifyConnectMeProgramsNotifications(softAssert, programsRequestID, connectMe);
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyInboxConnectMeNotifications - is Completed.");
    }

    @TestRail(testCaseId = {80467, 80470, 80471, 80479})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80467, C80470, C80471, C80479 - Verify the Send Notification module page objects and field validations")
    public void verifyNotificationMsgBoxLenCriteria() {
        log.info("To verify the tests with the below test case id's: " +
                "C80467, C80470, C80471, C80479");
        SoftAssert softAssert = new SoftAssert();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Verify the Send Notification Email
        adminNotificationSteps = new AdminNotificationSteps(driver);
        adminNotificationSteps.verifySendNotificationPageObjects(softAssert);
        adminNotificationSteps.verifySendNotificationPageFieldValidations(softAssert);
        adminNotificationSteps.verifyNotificationBodyCharacterLimit(softAssert, "Email");
        adminNotificationSteps.verifyNotificationBodyCharacterLimit(softAssert, "Text");
        adminNotificationSteps.verifyNotificationBodyCharacterLimit(softAssert, "Push Notification");
        adminNotificationSteps.verifyNotificationBodyCharacterLimit(softAssert, "Robo Call");
        adminNotificationSteps.verifyNotificationBodyCharacterLimit(softAssert, "WhatsApp");
        adminNotificationSteps.sendNotificationFileFormatValidation(softAssert);
        adminNotificationSteps.addValidAttachmentToSendNotification(softAssert);
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyNotificationMsgBoxLenCriteria - is Completed.");
    }


    @TestRail(testCaseId = {99061})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C99061 - Verify the Comments Field on Notification Inbox.")
    public void verifyCommentsFieldOnNotification() {
        log.info("To verify the tests with the below test case id's: " +
                "C99061");
        SoftAssert softAssert = new SoftAssert();
        AdminLoginSteps adminLoginSteps;
        AdminHomeSteps adminHomeSteps;
        //Scenario 1 - Verify Valid Comments on Inbox Outage request.
        //Create ConnectMe Outage API Request with Valid Comments
        adminNotificationSteps = new AdminNotificationSteps(driver);
        String outageRequestID1 = adminNotificationSteps.generateConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        // Init steps
        adminLoginSteps = new AdminLoginSteps(driver);
        adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Verify Comments field on Notification Inbox
        adminNotificationSteps.navigateToNotificationByRequestId(NotificationSubFolders.Connect_Me.name(), outageRequestID1);
        adminNotificationSteps.verifyCommentsFieldOnInboxNotification(softAssert, new ConnectMe().getMessageBody());
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();

        //Scenario 2 - Verify Blank Comments on Inbox Outage request.
        //Create ConnectMe Outage API Request with Valid Comments
        adminNotificationSteps = new AdminNotificationSteps(driver);
        String outageRequestID2 = adminNotificationSteps.generateConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        //Create ConnectMe Outage API Request with Valid Comments
        ConnectMeEndpoints connectMeEndpoints = new ConnectMeEndpoints();
        connectMeEndpoints.reportOutageConnectMeRequestWithBlankComments();
        // Init steps
        adminLoginSteps = new AdminLoginSteps(driver);
        adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Verify the Send Notification Email
        adminNotificationSteps = new AdminNotificationSteps(driver);
        //Verify Comments field on Notification Inbox
        adminNotificationSteps.navigateToNotificationByRequestId(NotificationSubFolders.Connect_Me.name(), outageRequestID2);
        adminNotificationSteps.verifyCommentsFieldOnInboxNotification(softAssert, "N/A");
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();

        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyCommentsFieldOnNotification - is Completed.");
    }

    @TestRail(testCaseId = {80465})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80465 - Verify user is able to delete notification permanently from Trash Folder.")
    public void verifyTrashTabFunctionality() {
        log.info("To verify the tests with the below test case id's: " +
                "C80465");
        SoftAssert softAssert = new SoftAssert();
        //Init Notification Steps
        adminNotificationSteps = new AdminNotificationSteps(driver);
        //Create ConnectMe Outage API Request
        HashMap responseData = adminNotificationSteps.generateConnectMeOutageAPIRequest();
        String outageRequestID = responseData.get("OutageRequestID").toString();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Delete Notification from ConnectMe
        adminNotificationSteps.deleteNotificationSubModuleByRequestID(softAssert, NotificationSubFolders.Connect_Me.name(), outageRequestID);
        //Delete Notification From Trash Folder
        adminNotificationSteps.deleteNotificationFromTrashByRequestID(softAssert, outageRequestID);
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyTrashTabFunctionality - is Completed.");
    }

    @TestRail(testCaseId = {80466})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80466 - Verify user is able to putback deleted notification from Trash to its source folder.")
    public void verifyPutBackFeatureNotification() {
        log.info("To verify the tests with the below test case id's: " +
                "C80466");
        SoftAssert softAssert = new SoftAssert();
        //Init Notification Steps
        adminNotificationSteps = new AdminNotificationSteps(driver);
        //Create ConnectMe Outage API Request
        HashMap responseData = adminNotificationSteps.generateConnectMeOutageAPIRequest();
        String outageRequestID = responseData.get("OutageRequestID").toString();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Delete Notification from ConnectMe to Trash
        adminNotificationSteps.deleteNotificationSubModuleByRequestID(softAssert, NotificationSubFolders.Connect_Me.name(), outageRequestID);
        //PutBack Notification From Trash Folder to Source
        adminNotificationSteps.putBackTrashDeletedNotificationToSource(softAssert, outageRequestID);
        //Verify Notification present on Source
        Assert.assertTrue(adminNotificationSteps.isNotificationPresentByRequestId(NotificationSubFolders.Connect_Me.name(), outageRequestID),
                "PutBack Notification from Trash is not present on the ConnectMe source Folder.");
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyPutBackFeatureNotification - is Completed.");
    }

    @TestRail(testCaseId = {80472, 80475})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80472, C80475  - Verify user is able to delete or put back multiple notifications from Trash Folder.")
    public void verifyAllEmailTabAndMultipleSelection() {
        log.info("To verify the tests with the below test case id's: " +
                "C80472, C80475");
        SoftAssert softAssert = new SoftAssert();
        //Init Notification Steps
        adminNotificationSteps = new AdminNotificationSteps(driver);
        //Create ConnectMe Outage API Request
        String outageRequestID1 = adminNotificationSteps.generateConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        String outageRequestID2 = adminNotificationSteps.generateConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        String outageRequestID3 = adminNotificationSteps.generateConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        String outageRequestID4 = adminNotificationSteps.generateConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        String outageRequestID5 = adminNotificationSteps.generateConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        String outageRequestID6 = adminNotificationSteps.generateConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        String[] outageRequestIDs = {outageRequestID1, outageRequestID2, outageRequestID3, outageRequestID4, outageRequestID5, outageRequestID6};
        List<String> outageRequestIdList = Arrays.asList(outageRequestIDs);
        Collections.reverse(outageRequestIdList);
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Delete Notification from ConnectMe to Trash
        adminNotificationSteps.deleteMultipleNotificationSubModuleByRequestID(softAssert, NotificationSubFolders.Connect_Me.name(), outageRequestIdList);
        adminNotificationSteps.deleteMultipleNotificationFromTrashByRequestID(softAssert, outageRequestIdList);
//        adminNotificationSteps.putBackTrashDeletedMultipleNotificationToSource(softAssert,outageRequestIdList);//todo SCM10-11474
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyAllEmailTabAndMultipleSelection - is Completed.");
    }

    @TestRail(testCaseId = {80474, 80478, 113239})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80474, C80478, C113239 - Verify replied admin notification on Customer portal inbox.")
    public void verifyReplySectionNotification() {
        log.info("To verify the tests with the below test case id's: " +
                "C80474, C80478, C113239 ");
        SoftAssert softAssert = new SoftAssert();
        //Init Notification Steps
        adminNotificationSteps = new AdminNotificationSteps(driver);
        //Create ConnectMe Outage API Request
        String outageRequestID = adminNotificationSteps.generateConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Verify Blank Message Send Reply Validation
        adminNotificationSteps.verifyBlankReplyNotificationValidation(softAssert, NotificationSubFolders.Connect_Me.name());
        //Verify Allowed File Types label present on Reply Notification page.
        adminNotificationSteps.verifyLblAllowedFileTypesSendReplyPage(softAssert);
        //Send Admin Reply to Notification from CSP.
        String replyText = "Test Reply to Notifications on SCP";
        adminNotificationSteps.enableALlNotificationsView();
        adminNotificationSteps.navigateToNotificationByRequestId(NotificationSubFolders.Connect_Me.name(), outageRequestID);
        adminNotificationSteps.sendReplyToNotification(softAssert, replyText);
        //Verify Reply Text on CSP Notifications
        adminNotificationSteps.verifySendReplyTextPresentOnReadNotifications(softAssert, outageRequestID, replyText);
        //Verify Reply Text on SCP Notification Inbox.
        adminNotificationSteps.clickServiceAccountNavtoC360Page();
        adminNotificationSteps.clickLoginToCustomerPortalNavToSCP(softAssert);
        adminNotificationSteps.verifyRepliedTxtPresentOnSCPNotificationInbox(softAssert, outageRequestID, replyText);
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyReplySectionNotification - is Completed.");
    }

    @TestRail(testCaseId = {99056, 99058})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C99056,C99058 - Verify the Customer Header Information is shown as NA for PreLogin ConnectMe and Services Request.")
    public void verifyNotifyPreLogConnectMeServices() {
        log.info("To verify the tests with the below test case id's: " +
                "C99056,C99058");
        SoftAssert softAssert = new SoftAssert();
        //Init Notification Steps
        adminNotificationSteps = new AdminNotificationSteps(driver);
        //Create PreLogin ConnectMe Outage API Request
        String outageRequestID = adminNotificationSteps.generatePreLogConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        //Create PreLogin Service Move-In API Request
        String serviceRequestID = adminNotificationSteps.createPreLogMoveInServicesAPIRequest();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Verify the PreLogin ConnectMe Outage request created on ConnectMe Folder.
        adminNotificationSteps.verifyConnectMePreLogOutageRequest(softAssert, NotificationSubFolders.Connect_Me.name(), outageRequestID);
        //Verify the PreLogin Services Move-In request created on Services Folder.
        adminNotificationSteps.verifyServicesPreLogMoveInRequest(softAssert, NotificationSubFolders.Services.name(), serviceRequestID);
        //Verify the ConnectMe and Service Template ID for Inbox from the DB
        adminNotificationSteps.verifyInboxConnectMeServiceTemplateIdFromDB(softAssert);
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyNotifyPreLogConnectMeServices - is Completed.");
    }

    @TestRail(testCaseId = {80480, 80481, 113570})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80480, C80481, C113570 - Verify the Pagination Functionality of the Notifications module.")
    public void verifyPaginationNotificationInbox() {
        log.info("To verify the tests with the below test case id's: " +
                "C80480, C80481, C113570");
        SoftAssert softAssert = new SoftAssert();
        //Init Notification Steps
        adminNotificationSteps = new AdminNotificationSteps(driver);
        //Create PreLogin ConnectMe Outage API Request
        String outageRequestID = adminNotificationSteps.generateConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        String utilityAccountNumber = user.getDefaultUtilityAccNum();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Verify the Pagination Functionality
        adminNotificationSteps.verifyPaginationFunctionality(softAssert, NotificationSubFolders.Connect_Me.name(), utilityAccountNumber, outageRequestID);
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyPaginationNotificationInbox - is Completed.");
    }

    @TestRail(testCaseId = {133275})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C133275 - Verify the ProblemsSigningInRequest created on the Login Issues Sub-Folder.")
    public void verifyProblemsSigningInCSPNotificationRequest() {
        log.info("To verify the tests with the below test case id's: " +
                "C133275");
        SoftAssert softAssert = new SoftAssert();
        //Init Notification Steps
        adminNotificationSteps = new AdminNotificationSteps(driver);
        //Create Problems Signing In API Request
        adminNotificationSteps.generateProblemSigningInAPIRequest();
        String email = user.getEmailId();
        String messageBody = adminNotificationSteps.generateProblemSigningInAPIRequest().getMessageBody();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Navigate to Login Issues Sub-Folder
        adminNotificationSteps.navigateSubModule(NotificationSubFolders.Login_Issues.name());
        //Verify the Problem Signing In Request on CSP Notifications
        adminNotificationSteps.verifyProblemSignInLoginIssuesNotification(softAssert, email, messageBody);
        //Verify the Problem Signing in Email Notifications.
        adminNotificationSteps.verifyProblemSigningInEmailNotification(softAssert, email, messageBody);
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyProblemsSigningInCSPNotificationRequest - is Completed.");
    }

    @TestRail(testCaseId = {80462, 80463})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80462, C80463 - Verify the saving/unsaving the notification will add/remove copy from the Saved Mail Folder")
    public void verifySaveMessageFunctionality() {
        log.info("To verify the tests with the below test case id's: " +
                "C80462, C80463");
        SoftAssert softAssert = new SoftAssert();
        //Init Notification Steps
        adminNotificationSteps = new AdminNotificationSteps(driver);
        //Create Outage ConnectMe API Request
        String outageRequestID = adminNotificationSteps.generateConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Verify the Validation Message for Saving Notification without selection
        adminNotificationSteps.verifyValidationMsgSaveUnselectedNotification(softAssert, NotificationSubFolders.Connect_Me.name());
        //Save Notification on ConnectMe Folder
        adminNotificationSteps.saveNotificationByRequestID(NotificationSubFolders.Connect_Me.name(), outageRequestID);
        //Verify Saved Notification on ConnectMe Folder
        softAssert.assertTrue(adminNotificationSteps.isNotificationSavedFromSaveIconByRequestID(NotificationSubFolders.Connect_Me.name(), outageRequestID),
                "Save Icon is not marked true on the Notification Request ID" + outageRequestID + " on the ConnectMe Folder.");
        softAssert.assertTrue(adminNotificationSteps.isNotificationPresentByRequestId(NotificationSubFolders.Connect_Me.name(), outageRequestID),
                "After Saving, the notification with Request ID " + outageRequestID + " is not present on the ConnectMe Folder.");
        //Verify Saved Notification on Saved Mail Folder
        softAssert.assertTrue(adminNotificationSteps.isNotificationPresentByRequestId(NotificationSubFolders.Saved_Mail.name(), outageRequestID),
                "After Saving, the notification with Request ID " + outageRequestID + " is not present on the Saved Mail Folder.");
        softAssert.assertTrue(adminNotificationSteps.isNotificationSavedFromSaveIconByRequestID(NotificationSubFolders.Saved_Mail.name(), outageRequestID),
                "Save Icon is not marked true on the Notification Request ID" + outageRequestID + " on the SavedMail Folder.");
        //UnSave Notification on ConnectMe Folder
        adminNotificationSteps.saveNotificationByRequestID(NotificationSubFolders.Connect_Me.name(), outageRequestID);
        //Verify Unsaved Notification on ConnectMe Folder
        softAssert.assertFalse(adminNotificationSteps.isNotificationSavedFromSaveIconByRequestID(NotificationSubFolders.Connect_Me.name(), outageRequestID),
                "Save Icon shows saved on the Notification Request ID" + outageRequestID + " on the ConnectMe Folder.");
        //Verify Saved Notification on Saved Mail Folder
        softAssert.assertFalse(adminNotificationSteps.isNotificationPresentByRequestId(NotificationSubFolders.Saved_Mail.name(), outageRequestID),
                "After Unsaving, the notification with Request ID " + outageRequestID + " is  present on the Saved Mail Folder.");
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifySaveMessageFunctionality - is Completed.");
    }

    @TestRail(testCaseId = {101614})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C101614 - Verify the Complete or Done Symbol is not seen on the Trash Sub-Folder.")
    public void verifyDoneFunctionalityTrashFolder() {
        log.info("To verify the tests with the below test case id's: " +
                "C101614");
        SoftAssert softAssert = new SoftAssert();
        //Init Notification Steps
        adminNotificationSteps = new AdminNotificationSteps(driver);
        //Create Outage ConnectMe API Request
        String outageRequestID = adminNotificationSteps.generateConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Delete Notification from Connect Me
        adminNotificationSteps.deleteNotificationSubModuleByRequestID(softAssert, NotificationSubFolders.Connect_Me.name(), outageRequestID);
        //Verify the Done Symbol not visible on Trash Folder
        adminNotificationSteps.verifyDoneSymbolOnTrashFolder(softAssert, NotificationSubFolders.Trash.name(), outageRequestID);
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDoneFunctionalityTrashFolder - is Completed.");
    }

    @TestRail(testCaseId = {101618})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C101618 - Verify the saving notification from Complete folders creates copy on Saved Mail Folder.")
    public void verifyCompleteFeatureToSavedFolder() {
        log.info("To verify the tests with the below test case id's: " +
                "C101618");
        SoftAssert softAssert = new SoftAssert();
        //Init Notification Steps
        adminNotificationSteps = new AdminNotificationSteps(driver);
        //Create Outage ConnectMe API Request
        String outageRequestID1 = adminNotificationSteps.generateConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        String outageRequestID2 = adminNotificationSteps.generateConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        String outageRequestID3 = adminNotificationSteps.generateConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        String[] outageRequestIDs = {outageRequestID1, outageRequestID2, outageRequestID3};
        List<String> outageRequestIdList = Arrays.asList(outageRequestIDs);
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Complete Multiple Notifications from Connect Me Folder
        adminNotificationSteps.completeMultipleNotifications(softAssert, NotificationSubFolders.Connect_Me.name(), outageRequestIdList);
        //Save Multiple Notifications from Complete Folder
        adminNotificationSteps.saveMultipleNotifications(softAssert, NotificationSubFolders.Complete.name(), outageRequestIdList);
        //Verify Saved Notifications still present on Complete Folder
        softAssert.assertTrue(adminNotificationSteps.isMultipleNotificationsPresent(NotificationSubFolders.Complete.name(), outageRequestIdList),
                "Saved Notifications from Complete is not present on Complete Folder.");
        //Verify Saved Notifications copied to Saved Folder
        softAssert.assertTrue(adminNotificationSteps.isMultipleNotificationsPresent(NotificationSubFolders.Saved_Mail.name(), outageRequestIdList),
                "Saved Notifications from Complete is not Copied to SavedMail Folder.");
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyCompleteFeatureToSavedFolder - is Completed.");
    }

    @TestRail(testCaseId = {101619})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C101619 - Verify on completing the saved notifications creates copy on the Complete Folder.")
    public void verifySavedFolderToCompleteFeature() {
        log.info("To verify the tests with the below test case id's: " +
                "C101619");
        SoftAssert softAssert = new SoftAssert();
        //Init Notification Steps
        adminNotificationSteps = new AdminNotificationSteps(driver);
        //Create Outage ConnectMe API Request
        String outageRequestID1 = adminNotificationSteps.generateConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        String outageRequestID2 = adminNotificationSteps.generateConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        String outageRequestID3 = adminNotificationSteps.generateConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        String[] outageRequestIDs = {outageRequestID1, outageRequestID2, outageRequestID3};
        List<String> outageRequestIdList = Arrays.asList(outageRequestIDs);
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Save Multiple Notifications from Connect Me to Saved Folder
        adminNotificationSteps.saveMultipleNotifications(softAssert, NotificationSubFolders.Connect_Me.name(), outageRequestIdList);
        //Complete Multiple Notifications from Saved Folder
        adminNotificationSteps.completeMultipleNotifications(softAssert, NotificationSubFolders.Saved_Mail.name(), outageRequestIdList);
        //Verify Completed Notifications still present on Saved Folder
        softAssert.assertTrue(adminNotificationSteps.isMultipleNotificationsPresent(NotificationSubFolders.Saved_Mail.name(), outageRequestIdList),
                "Completed Notifications from Saved is not present on the Saved Folder.");
        //Verify Completed Notifications copied to Complete Folder
        softAssert.assertTrue(adminNotificationSteps.isMultipleNotificationsPresent(NotificationSubFolders.Complete.name(), outageRequestIdList),
                "Completed Notifications from Saved is not Copied to Complete Folder.");
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifySavedFolderToCompleteFeature - is Completed.");
    }

    @TestRail(testCaseId = {101610, 101611, 101612, 101613, 101615, 101616, 101617})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C101610,C101611,C101612,C101613,C101615,C101616,C101617 - Verify the complete or uncompleted functionality for multiple notifications from ConnectMe folder")
    public void verifyCompleteFeatureConnectMeFolder() {
        log.info("To verify the tests with the below test case id's: " +
                "C101610,C101611,C101612,C101613,C101615,C101616,C101617");
        SoftAssert softAssert = new SoftAssert();
        //Init Notification Steps
        adminNotificationSteps = new AdminNotificationSteps(driver);

        //Create Outage ConnectMe API Request
        String outageRequestID1 = adminNotificationSteps.generateConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        String outageRequestID2 = adminNotificationSteps.generateConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        String outageRequestID3 = adminNotificationSteps.generateConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        String outageRequestID4 = adminNotificationSteps.generateConnectMeOutageAPIRequest().get("OutageRequestID").toString();
        String[] outageRequestIDs = {outageRequestID1, outageRequestID2, outageRequestID3};
        List<String> outageRequestIdList = Arrays.asList(outageRequestIDs);
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();

        ExtentLogger.logInfo("Done Functionality for Multiple Notifications has been initiated.");
        //Complete Multiple Notifications from Connect Me Folder
        adminNotificationSteps.completeMultipleNotifications(softAssert, NotificationSubFolders.Connect_Me.name(), outageRequestIdList);
        //Verify the Completed Multiple Notifications not present on Connect Me Folder.
        softAssert.assertFalse(adminNotificationSteps.isMultipleNotificationsPresent(NotificationSubFolders.Connect_Me.name(), outageRequestIdList),
                "Completed Notifications from ConnectMe is still present on the ConnectMe Folder.");
        //Verify the Completed Multiple Notifications present on Complete Folder.
        softAssert.assertTrue(adminNotificationSteps.isMultipleNotificationsPresent(NotificationSubFolders.Complete.name(), outageRequestIdList),
                "Completed Notifications from ConnectMe is not present on Complete Folder.");
        //Complete Multiple Notifications from Complete Folder
        adminNotificationSteps.completeMultipleNotifications(softAssert, NotificationSubFolders.Complete.name(), outageRequestIdList);
        //Verify the Completed Multiple Notifications not present on Complete Folder.
        softAssert.assertFalse(adminNotificationSteps.isMultipleNotificationsPresent(NotificationSubFolders.Complete.name(), outageRequestIdList),
                "Completed Notifications from Complete is still present on Complete Folder.");
        //Verify the Completed Multiple Notifications from Complete is moved to ConnectMe source folder.
        softAssert.assertTrue(adminNotificationSteps.isMultipleNotificationsPresent(NotificationSubFolders.Connect_Me.name(), outageRequestIdList),
                "Completed Notifications from Complete is not present on the ConnectMe Folder.");
        ExtentLogger.logInfo("Done Functionality for Multiple Notifications has been Completed.");

        ExtentLogger.logInfo("Done Functionality for Single Notifications has been Initiated.");
        //Complete Single Notification from Connect Me Folder
        adminNotificationSteps.completeNotificationByRequestID(softAssert, NotificationSubFolders.Connect_Me.name(), outageRequestID4);
        //Verify the Completed Single Notifications not present on Connect Me Folder.
        softAssert.assertFalse(adminNotificationSteps.isNotificationPresentByRequestId(NotificationSubFolders.Connect_Me.name(), outageRequestID4),
                "Completed Single Notification from ConnectMe is still present on the ConnectMe Folder.");
        //Verify the Completed Single Notifications present on Complete Folder.
        softAssert.assertTrue(adminNotificationSteps.isNotificationPresentByRequestId(NotificationSubFolders.Complete.name(), outageRequestID4),
                "Completed Notifications from ConnectMe is not present on Complete Folder.");
        //Complete Single Notifications from Complete Folder
        adminNotificationSteps.completeNotificationByRequestID(softAssert, NotificationSubFolders.Complete.name(), outageRequestID4);
        //Verify the Completed Single Notifications not present on Complete Folder.
        softAssert.assertFalse(adminNotificationSteps.isNotificationPresentByRequestId(NotificationSubFolders.Complete.name(), outageRequestID4),
                "Completed Notifications from Complete is still present on Complete Folder.");
        //Verify the Completed Single Notifications from Complete is moved to ConnectMe source folder.
        softAssert.assertTrue(adminNotificationSteps.isNotificationPresentByRequestId(NotificationSubFolders.Connect_Me.name(), outageRequestID4),
                "Completed Notifications from Complete is not present on the ConnectMe Folder.");
        ExtentLogger.logInfo("Done Functionality for Single Notifications has been Completed.");


        ExtentLogger.logInfo("Done Functionality Validation message, mouse Hover Text has been Initiated.");
        //Verify the Validation Message for Complete notification without selection
        adminNotificationSteps.verifyValidationMsgCompleteUnselectedNotification(softAssert, NotificationSubFolders.Connect_Me.name());
        //Verify the Header icons present on the sub-folders.
        adminNotificationSteps.verifySubFolderNotificationHeaderIconsPresent(NotificationSubFolders.Connect_Me.name());
        //Verify the done Header Icon Mouse Hover Text
        adminNotificationSteps.verifyMouseHoverMessageForDoneBtn(softAssert, NotificationSubFolders.Connect_Me.name());
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyCompleteFeatureConnectMeFolder - is Completed.");

    }


    @TestRail(testCaseId = {80476, 80477})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80476, C80477 - Verify the Auto-Response UI and save Auto-Response Text in Editor.")
    public void verifyNotificationAutoResponseEditor() {
        log.info("To verify the tests with the below test case id's: " +
                "C80476, C80477");
        SoftAssert softAssert = new SoftAssert();
        String autoResponseText = "SampleAutoResponseText" + RandomUtil.generateRandomString(4, RandomUtil.Mode.NUMERIC);
        //Init Notification Steps
        adminNotificationSteps = new AdminNotificationSteps(driver);
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Verify the Auto-Response UI Objects
        adminNotificationSteps.verifyAutoResponseUIObjects(softAssert);
        //Disable the Auto-Response.
        adminNotificationSteps.disableAutoResponseAndSubmitWithText(softAssert, autoResponseText);
        //Submit Auto-Response with Auto-Response Text
        adminNotificationSteps.enableAutoResponseAndSubmitWithText(softAssert, autoResponseText);
        //Verify AutoResponse CheckBox Enabled
        softAssert.assertTrue(adminNotificationSteps.isAutoResponseCheckBoxEnabled(), "Auto-Response Checkbox is not Enabled.");
        //Verify AutoResponse Text
        softAssert.assertEquals(adminNotificationSteps.getValueTxtBoxAutoResponseEditor(), autoResponseText, "Auto-Response Text is not Saved as provided.");
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyNotificationAutoResponseEditor - is Completed.");

    }

    @TestRail(testCaseId = {80453, 80454, 80484, 80485})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80453, C80454, C80484, C80485- Verify the Send Notification module page objects and field validations")
    public void verifySendNotificationTab() {
        log.info("To verify the tests with the below test case id's: " +
                "C80453, C80454, C80484, C80485");
        SoftAssert softAssert = new SoftAssert();
        //Init Notification Steps
        adminNotificationSteps = new AdminNotificationSteps(driver);
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Notifications Page
        adminHomeSteps.navigateToNotifications();
        //Verify the Notification Inbox page.
        softAssert.assertTrue(adminNotificationSteps.isAdminNotificationInboxPage(adminNotificationTextProp.getPropValue("adminNotificationPageUrl"),
                adminNotificationTextProp.getPropValue("adminNotificationPageTitle")), "Admin Notification Inbox page is not landed.");
        //Verify the Send Notification UI Objects.
        adminNotificationSteps.verifySendNotificationPageObjects(softAssert);
        //Verify the Send Notification Field level validations.
        adminNotificationSteps.verifySendNotificationPageFieldValidations(softAssert);
        //Click Notification Inbox from Send Notification.
        adminNotificationSteps.clickBtnNotificationInboxAndNavToInbox();
        //Verify Navigation to Notification Inbox Landing Page.
        softAssert.assertTrue(adminNotificationSteps.isAdminNotificationInboxPage(adminNotificationTextProp.getPropValue("adminNotificationPageUrl"),
                        adminNotificationTextProp.getPropValue("adminNotificationPageTitle")),
                "Notification Inbox page is not landed after clicking on Notification Inbox button from Send Notification");
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifySendNotificationTab - is Completed.");

    }


}

package demo.tests.scp;

import java.sql.SQLException;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.config.Configuration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.LoginSteps;
import demo.steps.scp.NotificationSteps;


public class NotificationTests extends TestRunner {
	
    private static final Logger log = LogManager.getLogger(NotificationTests.class);   
    private NotificationSteps notificationSteps;
    
    @FrameworkAnnotations(author = {"Kavya BR"}, category = {CategoryType.SANITY,CategoryType.CSP_NOTIFICATIONS})
    @Test(priority = 1, description = "To Navigate to Notification page")
    public void verifyNotification() throws SQLException, InterruptedException{
    	String[] tc_id = {"Notification_01"}; 
        ExtentLogger.logInfo("Test Case execution for - verifyValidLogin. Test Case id's -->  " + Arrays.toString(tc_id));
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        notificationSteps = new NotificationSteps(driver);
        notificationSteps.navtigateToNotifications();            
       log.info("Test Case execution for - verifyNotification - is Completed.");
     }
        
    @FrameworkAnnotations(author = {"Kavya BR"}, category = {CategoryType.SMOKE,CategoryType.CSP_NOTIFICATIONS})
    @Test(priority = 1, description = "To verify Different elements in Notification page")
    public void verifyNotificationsPageNavigationAndUI() throws SQLException, InterruptedException{
    	String[] tc_id = {"Notification_02, Notification_03,Notification_04"}; 
        ExtentLogger.logInfo("Test Case execution for - verifyValidLogin. Test Case id's -->  " + Arrays.toString(tc_id));
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        notificationSteps = new NotificationSteps(driver);
        notificationSteps.navtigateToNotifications(); 
        notificationSteps.verifyNotificationObject(softAssert);            
       log.info("Test Case execution for - verifyNotificationsPageNavigationAndUI - is Completed.");
     } 
    
    @FrameworkAnnotations(author = {"Kavya BR"}, category = {CategoryType.SANITY,CategoryType.CSP_NOTIFICATIONS})
    @Test(priority = 1, description = "To verify Delete multiple Notifications.")
    public void verifyDeleteMultipleNotifications() throws SQLException, InterruptedException{
    	String[] tc_id = {"Notification_05"}; 
        ExtentLogger.logInfo("Test Case execution for - verifyValidLogin. Test Case id's -->  " + Arrays.toString(tc_id));
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        notificationSteps = new NotificationSteps(driver); 
        notificationSteps.navtigateToNotifications(); 
        notificationSteps.deleteMiltipleNotification(softAssert);
       log.info("Test Case execution for - verifyDeleteMultipleNotifications - is Completed.");
     }
    
    @FrameworkAnnotations(author = {"Kavya BR"}, category = {CategoryType.SANITY,CategoryType.CSP_NOTIFICATIONS})
    @Test(priority = 1, description = "To verify Delete Notification.")
    public void verifyDeleteNotification() throws SQLException, InterruptedException{
    	String[] tc_id = {"Notification_06"}; 
        ExtentLogger.logInfo("Test Case execution for - verifyValidLogin. Test Case id's -->  " + Arrays.toString(tc_id));
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        notificationSteps = new NotificationSteps(driver);
        notificationSteps.navtigateToNotifications(); 
        notificationSteps.deleteNotification(softAssert);
       log.info("Test Case execution for - verifyDeleteNotification - is Completed.");
     }
    
    @FrameworkAnnotations(author = {"Kavya BR"}, category = {CategoryType.SANITY,CategoryType.CSP_NOTIFICATIONS})
    @Test(priority = 1, description = "To verify Save Multiple Notifications.")
    public void verifysaveMultipleNotifications() throws SQLException, InterruptedException{
    	String[] tc_id = {"Notification_14"}; 
        ExtentLogger.logInfo("Test Case execution for - verifyValidLogin. Test Case id's -->  " + Arrays.toString(tc_id));
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        notificationSteps = new NotificationSteps(driver);
        notificationSteps.navtigateToNotifications(); 
        notificationSteps.saveMultipleNotifications();
       log.info("Test Case execution for - verifysaveMultipleNotifications - is Completed.");
     }
    
    @FrameworkAnnotations(author = {"Kavya BR"}, category = {CategoryType.SANITY,CategoryType.CSP_NOTIFICATIONS})
    @Test(priority = 1, description = "To verify unsave all Notifications.")
    public void verifyUnsaveAllNotifications() throws SQLException, InterruptedException{
    	String[] tc_id = {"Notification_07"}; 
        ExtentLogger.logInfo("Test Case execution for - verifyValidLogin. Test Case id's -->  " + Arrays.toString(tc_id));
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        notificationSteps = new NotificationSteps(driver);
        notificationSteps.navtigateToNotifications(); 
        notificationSteps.unsaveAllNotifications(softAssert);
       log.info("Test Case execution for - verifyUnsaveAllNotifications - is Completed.");
     }
    
    @FrameworkAnnotations(author = {"Kavya BR"}, category = {CategoryType.SANITY,CategoryType.CSP_NOTIFICATIONS})
    @Test(priority = 1, description = "To verify Delete Notifications permanantly.")
    public void verifyDeleteNotificationsPermanantly() throws SQLException, InterruptedException{
    	String[] tc_id = {"Notification_08"}; 
        ExtentLogger.logInfo("Test Case execution for - verifyValidLogin. Test Case id's -->  " + Arrays.toString(tc_id));
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        notificationSteps = new NotificationSteps(driver);
        notificationSteps.navtigateToNotifications(); 
        notificationSteps.deleteNotificationsPermn(softAssert);
       log.info("Test Case execution for - verifyDeleteNotificationsPermanantly - is Completed.");
     }
    
    @FrameworkAnnotations(author = {"Kavya BR"}, category = {CategoryType.SANITY,CategoryType.CSP_NOTIFICATIONS})
    @Test(priority = 1, description = "To verify Put Back Notifications.")
    public void verifyputBackNotifications() throws SQLException, InterruptedException{
    	String[] tc_id = {"Notification_09"}; 
        ExtentLogger.logInfo("Test Case execution for - verifyValidLogin. Test Case id's -->  " + Arrays.toString(tc_id));
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        notificationSteps = new NotificationSteps(driver);
        notificationSteps.navtigateToNotifications(); 
        notificationSteps.putBackNotifications(softAssert);
       log.info("Test Case execution for - verifyputBackNotifications - is Completed.");
     }
  
    @FrameworkAnnotations(author = {"Kavya BR"}, category = {CategoryType.SMOKE,CategoryType.CSP_NOTIFICATIONS})
    @Test(priority = 1, description = "To verify Reply Notifications.")
    public void verifyReplyNotifications() throws SQLException, InterruptedException{
    	String[] tc_id = {"Notification_10"}; 
        ExtentLogger.logInfo("Test Case execution for - verifyValidLogin. Test Case id's -->  " + Arrays.toString(tc_id));
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        notificationSteps = new NotificationSteps(driver);
        notificationSteps.navtigateToNotifications(); 
        notificationSteps.replyToNotification(softAssert);
       log.info("Test Case execution for - verifyputBackNotifications - is Completed.");
     }
    
    @FrameworkAnnotations(author = {"Kavya BR"}, category = {CategoryType.SANITY,CategoryType.CSP_NOTIFICATIONS})
    @Test(priority = 1, description = "To verify Negative scenarios Notification.")
    public void verifyNegativeValidationsNotifications() throws SQLException, InterruptedException{
    	String[] tc_id = {"Notification_11,Notification_12,Notification_13"}; //change as per your TC id
        ExtentLogger.logInfo("Test Case execution for - verifyValidLogin. Test Case id's -->  " + Arrays.toString(tc_id));
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        notificationSteps = new NotificationSteps(driver);
        notificationSteps.navtigateToNotifications(); 
        notificationSteps.negativesceNotifications(softAssert);
       log.info("Test Case execution for - verifyNegativeValidationsNotifications - is Completed.");
     }
    
}

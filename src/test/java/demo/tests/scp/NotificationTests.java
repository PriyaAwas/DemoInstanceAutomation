package demo.tests.scp;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
    
    
    @FrameworkAnnotations(author = {"Kavya BR"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To Navigate to Notification page")
    public void verifyNotification() throws SQLException, InterruptedException{
    	log.info("Test Case execution - To Navigate to Notification page");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        notificationSteps = new NotificationSteps(driver);
        notificationSteps.navtigateToNotifications();            
       log.info("Test Case execution for - verifyNotification - is Completed.");
     }
        
    @FrameworkAnnotations(author = {"Kavya BR"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify Different elements in Notification page")
    public void verifyNotificationsPageNavigationAndUI() throws SQLException, InterruptedException{
    	log.info("Test Case execution - To verify Different elements in Notification page");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        notificationSteps = new NotificationSteps(driver);
        notificationSteps.navtigateToNotifications(); 
        notificationSteps.verifyNotificationObject(softAssert);            
       log.info("Test Case execution for - verifyNotificationsPageNavigationAndUI - is Completed.");
     } 
    
    @FrameworkAnnotations(author = {"Kavya BR"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify Delete multiple Notifications.")
    public void verifyDeleteMultipleNotifications() throws SQLException, InterruptedException{
    	log.info("Test Case execution - To verify Delete multiple Notifications");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        notificationSteps = new NotificationSteps(driver); 
        notificationSteps.navtigateToNotifications(); 
        notificationSteps.deleteMiltipleNotification(softAssert);
       log.info("Test Case execution for - verifyDeleteMultipleNotifications - is Completed.");
     }
    
    @FrameworkAnnotations(author = {"Kavya BR"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify Delete Notification.")
    public void verifyDeleteNotification() throws SQLException, InterruptedException{
    	log.info("Test Case execution - To verify Delete Notification");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        notificationSteps = new NotificationSteps(driver);
        notificationSteps.navtigateToNotifications(); 
        notificationSteps.deleteNotification(softAssert);
       log.info("Test Case execution for - verifyDeleteNotification - is Completed.");
     }
    
    @FrameworkAnnotations(author = {"Kavya BR"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify Save Multiple Notifications.")
    public void verifysaveMultipleNotifications() throws SQLException, InterruptedException{
    	log.info("Test Case execution - To verify save multiple Notifications");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        notificationSteps = new NotificationSteps(driver);
        notificationSteps.navtigateToNotifications(); 
        notificationSteps.saveMultipleNotifications();
       log.info("Test Case execution for - verifysaveMultipleNotifications - is Completed.");
     }
    
    @FrameworkAnnotations(author = {"Kavya BR"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify unsave all Notifications.")
    public void verifyUnsaveAllNotifications() throws SQLException, InterruptedException{
    	log.info("Test Case execution - To verify unsave all Notifications");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        notificationSteps = new NotificationSteps(driver);
        notificationSteps.navtigateToNotifications(); 
        notificationSteps.unsaveAllNotifications(softAssert);
       log.info("Test Case execution for - verifyUnsaveAllNotifications - is Completed.");
     }
    
    @FrameworkAnnotations(author = {"Kavya BR"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify Delete Notifications permanantly.")
    public void verifyDeleteNotificationsPermanantly() throws SQLException, InterruptedException{
    	log.info("Test Case execution - To verify Delete Notifications Permanantaly");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        notificationSteps = new NotificationSteps(driver);
        notificationSteps.navtigateToNotifications(); 
        notificationSteps.deleteNotificationsPermn(softAssert);
       log.info("Test Case execution for - verifyDeleteNotificationsPermanantly - is Completed.");
     }
    
    @FrameworkAnnotations(author = {"Kavya BR"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify Put Back Notifications.")
    public void verifyputBackNotifications() throws SQLException, InterruptedException{
    	log.info("Test Case execution - To verify Put Back Notifications ");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        notificationSteps = new NotificationSteps(driver);
        notificationSteps.navtigateToNotifications(); 
        notificationSteps.putBackNotifications(softAssert);
       log.info("Test Case execution for - verifyputBackNotifications - is Completed.");
     }
  
    @FrameworkAnnotations(author = {"Kavya BR"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify Reply Notifications.")
    public void verifyReplyNotifications() throws SQLException, InterruptedException{
    	log.info("Test Case execution - To verify Reply Notifications ");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        notificationSteps = new NotificationSteps(driver);
        notificationSteps.navtigateToNotifications(); 
        notificationSteps.replyToNotification(softAssert);
       log.info("Test Case execution for - verifyputBackNotifications - is Completed.");
     }
    
    @FrameworkAnnotations(author = {"Kavya BR"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify Negative scenarios Notification.")
    public void verifyNegativeValidationsNotifications() throws SQLException, InterruptedException{
    	log.info("Test Case execution - To verify Negative validations Notifications ");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        notificationSteps = new NotificationSteps(driver);
        notificationSteps.navtigateToNotifications(); 
        notificationSteps.negativesceNotifications(softAssert);
       log.info("Test Case execution for - verifyNegativeValidationsNotifications - is Completed.");
     }
 
}

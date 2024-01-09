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
import sew.ai.models.User;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.LoginSteps;
import sew.ai.steps.scp.NotificationSteps;
import sew.ai.steps.scp.PostLogConnectMeSteps;

public class NotificationTests extends TestRunner {
	
    private static final Logger log = LogManager.getLogger(NotificationTests.class);   
    private NotificationSteps notificationSteps;
        
    @TestRail(testCaseId = {})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the login page objects.")
    public void verifyNotificationsPageNavigationAndUI() throws SQLException{
    	log.info("Test Case execution for - verifyPostLoginConnectMePage - is Initiated");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
       //  Init Post Log Connect Me Steps
        notificationSteps = new NotificationSteps(driver);
       // verify objects on Connect me page
        notificationSteps.verifyNotificationObject(softAssert);            
       log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
     }
    
    @TestRail(testCaseId = {})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the login page objects.")
    public void verifyOutageNotificationMails() throws SQLException{
    	log.info("Test Case execution for - verifyPostLoginConnectMePage - is Initiated");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
       //  Init Post Log Connect Me Steps
        notificationSteps = new NotificationSteps(driver);
       // verify objects on Connect me page
        notificationSteps.verifyNotificationObject(softAssert);
        // 
       log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
     }
}

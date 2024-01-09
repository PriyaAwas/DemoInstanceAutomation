package sew.ai.tests.scp;

import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import sew.ai.config.SCPConfiguration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.helpers.testrail.TestRail;
import sew.ai.models.User;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.ForgetPasswordSteps;
import sew.ai.steps.scp.ForgetUsernameSteps;
import sew.ai.steps.scp.ProblemSigningInSteps;
import sew.ai.utils.DataBaseUtils;

public class ForgetUsernameTest extends TestRunner {
    private static final Logger log = LogManager.getLogger(ForgetUsernameTest.class);
    private ForgetUsernameSteps forgetUsernameSteps;

    @TestRail(testCaseId = {75040, 75041, 75042, 106511, 75043, 75044, 75048, 75055, 75056, 106456, 106457, 106458, 75045, 106512, 106513, 106514, 106515, 106516})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the Forget Username page objects.")
    public void validateResetUsername() throws Exception {
        log.info("To verify that following should be displayed on validate Forgot Username.");
        SoftAssert softAssert = new SoftAssert();
        // Init Forget Username Steps
        forgetUsernameSteps = new ForgetUsernameSteps(driver);
        // Redirect to Forget Username Steps
       forgetUsernameSteps.verifyForgotUsernameInObject(softAssert);        
        // Forget Username page
       forgetUsernameSteps.verifyForgotUsernameForm();      
        log.info("Test Case execution for - validateForgotUsername - is Completed.");
                  
      }
    
    @TestRail(testCaseId = {75050, 75049})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the Forget Username page objects.")
    public void resetUsernameUsingWrongEmail() {
        log.info("To verify that following should be displayed on validate Forgot Username.");
        SoftAssert softAssert = new SoftAssert();
        // Init Forget Username Steps
        forgetUsernameSteps = new ForgetUsernameSteps(driver);
        // Redirect to Forget Username Steps
        forgetUsernameSteps.verifyForgotUsernameInObject(softAssert);
        // invalid Email verification 
        forgetUsernameSteps.enterInvalidEmail();
        log.info("Test Case execution for - resetUsernameInvalidAccountOrEmail - is Completed.");                  
      }
    

    @TestRail(testCaseId = {75052})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the Forget Username page objects.")
    public void verifyMaxResetUsernameAccountBlock() throws Exception {
        log.info("To verify that following should be displayed on validate Forgot Username.");
        SoftAssert softAssert = new SoftAssert();        
        // Init Forget Username Steps
        forgetUsernameSteps = new ForgetUsernameSteps(driver);
        // Redirect to Forget Username Steps
        forgetUsernameSteps.verifyForgotUsernameInObject(softAssert);

        forgetUsernameSteps.maxAttemptUserLock();
      Thread.sleep(1100);
        log.info("Test Case execution for - verifyMaxResetUsernameAccountBlock - is Completed.");                  
      }
    
    
    @TestRail(testCaseId = {75046 ,75047})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the Forget Username page objects.")
    public void verifyMinMaxValueForgotUsername() throws Exception {
        log.info("To verify that following should be displayed on validate Forgot Username.");
        SoftAssert softAssert = new SoftAssert();        
        // Init Forget Username Steps
        forgetUsernameSteps = new ForgetUsernameSteps(driver);
        // Redirect to Forget Username Steps
        forgetUsernameSteps.verifyForgotUsernameInObject(softAssert);
        // Fetching Account Number link        
        forgetUsernameSteps.fetchUserNameFromMail();              
        log.info("Test Case execution for - verifyMinMaxValueForgotUsername - is Completed.");                  
      }
           
    @TestRail(testCaseId = {75054})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the Forget Username page objects.")
    public void verifyForgotUsernameWithInactiveAcc() {
        log.info("To verify that following should be displayed on validate Forgot Username.");
        SoftAssert softAssert = new SoftAssert();        
        // Init Forget Username Steps
        forgetUsernameSteps = new ForgetUsernameSteps(driver);
        // Redirect to Forget Username Steps
        forgetUsernameSteps.verifyForgotUsernameInObject(softAssert);
        // Inactive account details
        forgetUsernameSteps.verifyInActiveAccountInfo();      
        log.info("Test Case execution for - verifyForgotUsernameWithInactiveAcc - is Completed.");
                  
      }     
}

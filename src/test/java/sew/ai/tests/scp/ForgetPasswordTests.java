package sew.ai.tests.scp;

import static sew.ai.steps.scp.DashboardSteps.dashboardTextProp;
import static sew.ai.steps.scp.LoginSteps.loginTextProp;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import static sew.ai.steps.scp.ForgetPasswordSteps.ForgotPasswordTextProp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import sew.ai.config.SCPConfiguration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.helpers.testrail.TestRail;
import sew.ai.models.User;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.ForgetPasswordSteps;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.RandomUtil;


public class ForgetPasswordTests extends TestRunner {
    private static final Logger log = LogManager.getLogger(ForgetPasswordTests.class);
    private ForgetPasswordSteps forgetPasswordSteps;

    @TestRail(testCaseId = {75004, 75005, 75006, 75007, 75008, 75011, 75012, 106445})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the Forget Password page objects.")
    public void validateForgotPassword () {
        log.info("To verify that following should be displayed on Forget Password In page.");
        DataBaseUtils.callStoredProcedureUnblockAccountIp();
        User user = SCPConfiguration.user;
        String Password = SQLQueries.getPassword(user.getUserName());
        SoftAssert softAssert = new SoftAssert();
        forgetPasswordSteps = new ForgetPasswordSteps(driver);
       // Verify Forget Password Object
        forgetPasswordSteps.verifyForgetPasswordObject(softAssert);       
        //Submit Blank UserName 
        forgetPasswordSteps.verifySubmitBlankUserName(softAssert);          
        // Submit Invalid UserName 
        forgetPasswordSteps.verifyInvalidUserName(softAssert);      
        softAssert.assertAll();
        log.info("Test Case execution for - validateForgotPassword - is Completed.");
                  
      }
    
    @TestRail(testCaseId = {75014, 75019, 75019, 75019, 75023, 75026, 75027,  75028, 75029, 75030, 75031, 75032, 75033, 75034, 75035, 75037})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the Forget Password page objects.")
    public void verifyResetPasswordUsingEmail() {
    	log.info("Test Case execution for - verifyResetPasswordUsingEmail - is Initiated");
        SoftAssert softAssert = new SoftAssert();
        DataBaseUtils.callStoredProcedureUnblockAccountIp();
        User user = SCPConfiguration.user;
        String password = user.getPassword();
        setUserPasswordToGivenPassword(password);
        System.out.println("Password in DB: => " + password);
        // Verify Forget Password Object
        forgetPasswordSteps = new ForgetPasswordSteps(driver);
        forgetPasswordSteps.verifyForgetPasswordObject(softAssert);
        // Enter Valid Username
        forgetPasswordSteps.enterValidUserName(user.getUserName());
        String toastMsg = forgetPasswordSteps.enterValidUserName(user.getUserName());
        softAssert.assertEquals(toastMsg , loginTextProp
                .getPropValue("ValidUsernameToastMsg").replace("${username}", user.getUserName()),
        "Username Toaster message does not matched.");
     // Fetching reset password link from the database & verify Url/Title
     forgetPasswordSteps.navigateToUrl(forgetPasswordSteps.getForgetPasswordEmailLink());
    // Switch to new tab
    // forgetPasswordSteps.SwitchToNewtab();
    // Verify Reset Forget Password Object 
        forgetPasswordSteps.verifyResetForgetPasswordObject(softAssert);
     // Submit Blank Reset Password Page
      softAssert.assertAll();
        log.info("Test Case execution for - verifyResetPasswordUsingEmail - is Completed.");
                  
      }
    
    @TestRail(testCaseId = {75013})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the Forget Password page objects.")
    public void verifyForgotPwdWithInactiveAcc() {
    	log.info("Test Case execution for - verifyForgotPwdWithInactiveAcc - is Initiated");
        SoftAssert softAssert = new SoftAssert();
        DataBaseUtils.callStoredProcedureUnblockAccountIp();
        User user = SCPConfiguration.user;
        // Verify Forget Password Object
        forgetPasswordSteps = new ForgetPasswordSteps(driver);
        forgetPasswordSteps.verifyForgetPasswordObject(softAssert);
        // Verify PageTitle & URL 
        softAssert.assertTrue(forgetPasswordSteps.isForgetPasswordPage(ForgotPasswordTextProp.getPropValue("ForgetPasswordPageUrl"),
    		 (ForgotPasswordTextProp.getPropValue("ForgetPasswordPageTitle")))); 
        // Inactive Account
        forgetPasswordSteps.submitForgetPasswordWithInactiveAccountInfo();               
        softAssert.assertAll();
        log.info("Test Case execution for - verifyForgotPwdWithInactiveAcc - is Completed.");
        DataBaseUtils.callStoredProcedureUnblockAccountIp();       
      }
    
    
    @TestRail(testCaseId = {100554, 101452, 101453, 101455, 101456, 101457, 101458, 101459, 101460, 101461, 101462, 101463, 101467, 101468, 75025})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the Forget Password page objects.")
    public void verifyResetPasswordLinkFunctionality() throws Exception {
    	log.info("Test Case execution for - verifyResetPasswordLinkFunctionality - is Initiated");
        SoftAssert softAssert = new SoftAssert();
        DataBaseUtils.callStoredProcedureUnblockAccountIp();
        User user = SCPConfiguration.user;
        String password = user.getPassword();
        setUserPasswordToGivenPassword(password);
        // Verify Forget Password Object
        forgetPasswordSteps = new ForgetPasswordSteps(driver);
        forgetPasswordSteps.verifyForgetPasswordObject(softAssert);       
        String toastMsg = forgetPasswordSteps.enterValidUserName(user.getUserName());
        softAssert.assertEquals(toastMsg , ForgotPasswordTextProp
                .getPropValue("ValidUsernameToastMsg").replace("${username}", user.getUserName()),
        "toaster message not matched.");
     // Fetching reset password link from the database.
        forgetPasswordSteps.navigateToUrl(forgetPasswordSteps.getForgetPasswordEmailLink());  
    // Switch to new tab
    // forgetPasswordSteps.SwitchToNewtab();
    // Verify Reset Forget Password Object 
        forgetPasswordSteps.verifyResetForgetPasswordObject(softAssert);
     // Verify fill all mandatory fields 
        String submitBlankResetForgetPasswordMsg = forgetPasswordSteps.submitBlankResetForgetPassword();
        		 softAssert.assertEquals(submitBlankResetForgetPasswordMsg , ForgotPasswordTextProp
        	                .getPropValue("txtMsgBlankInfoApp"),
        	        "Mandatory fields toast message not matched.");	        		 
        String newPassword = RandomUtil.generateRandomString(10, RandomUtil.Mode.ALPHANUMERIC);
        newPassword = newPassword + "Demo@123";
        try {
      // Verify max length criteria of Password
       forgetPasswordSteps.verifyMaxLenNewAndConfirmPassword(softAssert);
       // Input New & Confirm Password
       forgetPasswordSteps.settingNewPassword(newPassword,newPassword);
     // Login into application with new password       
      DashboardSteps dashboardSteps = forgetPasswordSteps.loginIntotheApplication(user.getUserName(), newPassword);
       softAssert.assertTrue(dashboardSteps.isProfileIconVisible(),
        	                    "Profile icon is not visible.");  		         
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	 setUserPasswordToGivenPassword(password);
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyResetPasswordLinkFunctionality - is Completed.");
    }

       
    @TestRail(testCaseId = {101664, 107375})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the Forget Password page objects.")
    public void verifyUserStatusTempLock() {
    	log.info("Test Case execution for - verifyUserStatusTempLock - is Initiated");
        SoftAssert softAssert = new SoftAssert();
        DataBaseUtils.callStoredProcedureUnblockAccountIp();
        User user = SCPConfiguration.user;        
       DataBaseUtils.makeUserAccountTempLock(user.getUserName());
        // Verify Forget Password Object
        forgetPasswordSteps = new ForgetPasswordSteps(driver);
        forgetPasswordSteps.verifyForgetPasswordObject(softAssert);
        // Verify PageTitle & URL 
        softAssert.assertTrue(forgetPasswordSteps.isForgetPasswordPage(ForgotPasswordTextProp.getPropValue("ForgetPasswordPageUrl"),
    		 (ForgotPasswordTextProp.getPropValue("ForgetPasswordPageTitle"))));
     // Fetching reset password link from the database.
        forgetPasswordSteps.navigateToUrl(forgetPasswordSteps.getForgetPasswordEmailLink());                        
        DataBaseUtils.callStoredProcedureUnblockAccountIp();      
        softAssert.assertAll();
        log.info("Test Case execution for - verifyUserStatusTempLock - is Completed.");                  
      }
    
    /**
     * This method is to set the password of the user to the given password.
     *
     * @param sPassword
     */
    public void setUserPasswordToGivenPassword(String sPassword) {
    	User user = SCPConfiguration.user;
        try {
            String setDefaultAccount = "UPDATE [User] SET Password = '" + sPassword + "' WHERE UserName = '"
                    + user.getUserId() + "'";
            DataBaseUtils.executeUpdateDeleteSQLQuery(setDefaultAccount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
       
}

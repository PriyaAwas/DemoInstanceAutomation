package sew.ai.tests.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.config.Configuration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.testrail.TestRail;
import sew.ai.runner.TestRunner;
import sew.ai.steps.csp.AdminHomeSteps;
import sew.ai.steps.csp.AdminLoginSteps;

import static sew.ai.steps.csp.AdminLoginSteps.adminLoginTextProp;

public class AdminHomeTests extends TestRunner {
    private static final Logger log = LogManager.getLogger(AdminHomeTests.class);
    private AdminLoginSteps adminLoginSteps;
    private AdminHomeSteps adminHomeSteps;

    @TestRail(testCaseId = {102730, 102731})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C102730, C102731 - Verfying homepage, Welcome message and Sign-out button")
    public void verifyWelcomeMsgAndSignOut() {
        log.info("To verify the tests with the below test case id's: " +
                "C102730, C102731");
        SoftAssert softAssert = new SoftAssert();
        // Init steps
        adminLoginSteps = new AdminLoginSteps(driver);
        adminHomeSteps = new AdminHomeSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"),Configuration.toString("adminPassword"));
        //Verifying the Home Page Welcome message
        adminHomeSteps.verifyAdminWelcomeMessage(softAssert);
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Verifying the Landing Login page.
        adminLoginSteps.isAdminLoginPage(adminLoginTextProp.getPropValue("adminPortalURL"), adminLoginTextProp
                .getPropValue("adminPortalLoginPageTitle"));
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyWelcomeMsgAndSignOut - is Completed.");
    }

    @TestRail(testCaseId = { 102736, 102737, 102738, 102739, 102740, 102744 })
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C102736, C102737, C102738, C102739, C102740, C102744  - verfying changing the password")
    public void verifyChangePassword() {
        AdminLoginSteps adminLoginSteps;
        AdminHomeSteps adminHomeSteps;
        SoftAssert softAssert = new SoftAssert();
        log.info("To verify the tests with the below test case id's: " +
                "C102736, C102737, C102738, C102739, C102740, C102744");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        //Login to the Application
        adminLoginSteps.loginIntoAdminApplication("adminkrish16","Demo@123");
        //Navigate to the Change Password Popup
        adminHomeSteps = new AdminHomeSteps(driver);
        adminHomeSteps.navigateChangePasswordPopup();
        // Verify the Change Password Field Validation Messages
        adminHomeSteps.verifyChangePasswordFieldValidations(softAssert);
        //Getting the User Current Password from DB.
        String defaultPassword = adminHomeSteps.getAdminUserPasswordFromDB("adminkrish16");
        // Updating the Password
        String newPassword = adminHomeSteps.changePassword(softAssert, "Demo@123");
        //Sign Out Application
        adminHomeSteps.SignOutAdminApplication();
        //Login into the application with the New password
        adminLoginSteps.loginIntoAdminApplication("adminkrish16",newPassword);
        //TearDown
        adminHomeSteps.setAdminUserPasswordToGivenPassword("adminkrish16",defaultPassword);
        //Sign Out Application
        adminHomeSteps.SignOutAdminApplication();
        softAssert.assertAll();
        log.info("Test Case execution for - verifyChangePassword - is Completed.");
    }






}

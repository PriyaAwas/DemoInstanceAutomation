package sew.ai.tests.csp;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.testrail.TestRail;
import sew.ai.pageObjects.csp.AdminLoginPage;
import sew.ai.runner.TestRunner;
import sew.ai.steps.csp.AdminHomeSteps;
import sew.ai.steps.csp.AdminLoginSteps;
import sew.ai.steps.csp.ForgotPasswordSteps;

import static sew.ai.steps.csp.AdminLoginSteps.adminLoginTextProp;

public class ForgotPasswordTests extends TestRunner {
    private static final Logger log = LogManager.getLogger(ForgotPasswordTests.class);
    private ForgotPasswordSteps forgotPasswordSteps;

    @TestRail(testCaseId = { 72378, 72377, 72376 })
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72378, C72377, C72376 - Resetting the password for admin")
    public void verifyForgotPassword() {
        AdminLoginSteps adminLoginSteps;
        AdminLoginPage adminLoginPage ;
        AdminHomeSteps adminHomeSteps;
        SoftAssert softAssert = new SoftAssert();
        log.info("To verify the tests with the below test case id's: " +
                "C72378, C72377, C72376");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        adminLoginPage = new AdminLoginPage(driver);
        adminHomeSteps = new AdminHomeSteps(driver);
        forgotPasswordSteps = new ForgotPasswordSteps(driver);
        //Change Admin Password
        String newPassword = forgotPasswordSteps.changeAdminPassword(softAssert);
        //Verify the Login Landing page.
        adminLoginPage.isAdminLoginPage(adminLoginTextProp.getPropValue("adminPortalURL"), adminLoginTextProp
                .getPropValue("adminPortalLoginPageTitle"));
        // Login into the application with the Changed Password
        adminLoginSteps.loginIntoAdminApplication("adminkrish16", newPassword);
        //Sign Out Application
        adminHomeSteps.SignOutAdminApplication();
        softAssert.assertAll();
        log.info("Test Case execution for - verifyForgotPassword - is Completed.");
    }

}

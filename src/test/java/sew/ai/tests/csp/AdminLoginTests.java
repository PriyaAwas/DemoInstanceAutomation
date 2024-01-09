package sew.ai.tests.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.config.Configuration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.testrail.TestRail;
import sew.ai.runner.TestRunner;
import sew.ai.steps.csp.AdminCustomerSteps;
import sew.ai.steps.csp.AdminLoginSteps;

import static sew.ai.steps.csp.AdminLoginSteps.adminLoginTextProp;

public class AdminLoginTests extends TestRunner {
    private static final Logger log = LogManager.getLogger(AdminLoginTests.class);
    private AdminLoginSteps adminLoginSteps;

    @TestRail(testCaseId = {72381})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72381-Login CSP with valid Credentials")
    public void verifyValidAdminLogin() {
        log.info("To verify the tests with the below test case id's: " +
                "C72381");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        log.info("Test Case execution for - verifyValidAdminLogin - is Completed.");
    }

    @TestRail(testCaseId = {72382})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72382-Login CSP with invalid Credentials")
    public void verifyInvalidAdminLogin() {
        log.info("To verify the tests with the below test case id's: " +
                "C72382");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        softAssert.assertEquals(adminLoginSteps.adminLoginWithInvalidCreds(), adminLoginTextProp.getPropValue("txtMsgInvalidUserNamePassWord"),
                "Error Message for Invalid Username or Password is not as Expected.");
        softAssert.assertAll();
        log.info("Test Case execution for - verifyInvalidAdminLogin - is Completed.");
    }

    @TestRail(testCaseId = {72383})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72383-Login CSP with checking the Remember Me CheckBox")
    public void verifyCSPRememberMeCheckBox() {
        log.info("To verify the tests with the below test case id's: " +
                "C72383");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplicationWithRememberMeCheckbox(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        Assert.assertEquals(adminLoginSteps.verifyUsernameRememberCheckBoxAdminLogin(), Configuration.toString("adminUsername"),
                "CSP Username Remember Me Checkbox is not populated with the Expected Username");
        log.info("Test Case execution for - verifyCSPRememberMeCheckBox - is Completed.");
    }

    @TestRail(testCaseId = {72370, 72371, 73385})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72370, C72371, C73385 - Validating CSP Login page objects")
    public void verifyLoginObjectAndCommonElements() {
        log.info("To verify the tests with the below test case id's: " +
                "C72370, C72371, C73385");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        //Verifying the Login page objects.
        adminLoginSteps.verifyCSPLoginPageElements(softAssert);
        softAssert.assertAll();
        log.info("Test Case execution for - verifyLoginObjectAndCommonElements - is Completed.");
    }

    @TestRail(testCaseId = {72375, 72374, 72373})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72375, C72374, C72373 - Validating Alert messages for blank login fields")
    public void verifyAlertMessageBlankLoginFields() {
        log.info("To verify the tests with the below test case id's: " +
                "C72375, C72374, C72373");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        //Verifying the Login page objects.
        adminLoginSteps.verifyAlertMessageBlankLoginFields(softAssert);
        softAssert.assertAll();
        log.info("Test Case execution for - verifyAlertMessageBlankLoginFields - is Completed.");
    }

    @TestRail(testCaseId = {72372, 72380, 102733, 102734})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72372, C72380, C102733, C102734 - Validating the SEW logo and version of application")
    public void verifySEWlogoAndAppVersion() {
        log.info("To verify the tests with the below test case id's: " +
                "C72372, C72380, C102733, C102734");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        //Verify the Pre-Login Footer Objects
        adminLoginSteps.verifyPreLoginFooterObjects(softAssert);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Verifying the Login page objects.
        adminLoginSteps.verifyPostLoginFooterObjects(softAssert);
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifySEWlogoAndAppVersion - is Completed.");
    }
    
    @TestRail(testCaseId = { 72452,84069,84070 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72452,C84069,C84070-Unlock a IP from CSR workbench")
    public void verifyUnlockingUserIP() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        //adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminLoginSteps.verifyUnlockingUserIP();
        
        log.info("Test Case execution for - verifyUnlockingUserIP - is Completed.");
    }

 }

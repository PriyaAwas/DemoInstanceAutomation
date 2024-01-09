package sew.ai.tests.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.config.Configuration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.helpers.testrail.TestRail;
import sew.ai.runner.TestRunner;
import sew.ai.steps.csp.AdminHomeSteps;
import sew.ai.steps.csp.AdminLoginSteps;
import sew.ai.steps.csp.OutageSteps;

import static sew.ai.steps.csp.OutageSteps.outagesTextProp;

public class OutageTests extends TestRunner {

    private static final Logger log = LogManager.getLogger(OutageTests.class);
    private OutageSteps outageSteps;

    @TestRail(testCaseId = {93611, 93612, 93613, 93614})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C93611, C93612, C93613, C93614 - Verify the Objects present on the Outage Tab.")
    public void verifyObjectsAdminOutagePage() {
        log.info("To verify the tests with the below test case id's: " +
                "C93611, C93612, C93613, C93614");
        SoftAssert softAssert = new SoftAssert();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Outages Page
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        adminHomeSteps.navigateToOutages();
        //Verify the Outages Page.
        outageSteps = new OutageSteps(driver);
        Assert.assertTrue(outageSteps.isOutagesPage(outagesTextProp.getPropValue("outagesPageUrl"),
                outagesTextProp.getPropValue("outagesPageTitle")), "Outages Page Url and Title is not as Expected.");
        //Verify the Outage Page Objects.
        outageSteps.verifyObjectsAdminOutageTab(softAssert);
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyObjectsAdminOutagePage - is Completed.");
    }

    @TestRail(testCaseId = {93616, 93617, 93618, 93619, 93620})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C93616, C93617, C93618, C93619, C93620  - Verify the Objects present on the Outage Tab.")
    public void verifySelectedOutageDetails() {
        log.info("To verify the tests with the below test case id's: " +
                "C93616, C93617, C93618, C93619, C93620");
        SoftAssert softAssert = new SoftAssert();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Outages Page
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        adminHomeSteps.navigateToOutages();
        //Verify the Outages Page.
        outageSteps = new OutageSteps(driver);
        Assert.assertTrue(outageSteps.isOutagesPage(outagesTextProp.getPropValue("outagesPageUrl"),
                outagesTextProp.getPropValue("outagesPageTitle")), "Outages Page Url and Title is not as Expected.");
        //Verify the Outage Grid Headers.
        ExtentLogger.logInfo("C66135 - To verify that outage table has following fields.");
        outageSteps.verifyOutageGridHeaderList();
        //Verify the Outage Details Popup Page Objects.


        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifySelectedOutageDetails - is Completed.");
    }


}

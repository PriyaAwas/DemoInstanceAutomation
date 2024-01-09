package sew.ai.tests.scp;

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
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.FootPrintSteps;
import sew.ai.steps.scp.HomeSteps;
import sew.ai.steps.scp.LoginSteps;

import java.sql.SQLException;

import static sew.ai.helpers.reporters.ExtentLogger.logInfo;

public class FootPrintTests extends TestRunner {
    private static final Logger log = LogManager.getLogger(FootPrintTests.class);
    private FootPrintSteps footPrintSteps;
    @TestRail(testCaseId ={75348, 75349, 75350, 75352, 75353, 75354, 75355, 75356, 75359, 75366, 75367})
    @FrameworkAnnotations(author = {"Gaurav Saxena"}, category = {CategoryType.SANITY})
    @Test(priority = 56, description = "verify FootPrint Page objects.")
   public void  verifyFootPrintPageObjects() throws SQLException {
       log.info("verify FootPrint Page objects. " +
               "75348, 75349, 75350, 75352, 75353, 75354, 75355, 75356, 75359, 75366, 75367");
       SoftAssert softAssert = new SoftAssert();
       //make Object For Navigate to the My Profile Page
       //Login into The Application
       LoginSteps loginSteps = new LoginSteps(driver);
       User user = SCPConfiguration.user;
       DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
       //Navigate to FootPrint Page under WaysToSave
       HomeSteps homeSteps = new HomeSteps(driver);
       homeSteps.navigateToFootPrintPage();
       // Init FootPrintsteps
       footPrintSteps = new FootPrintSteps(driver);
       footPrintSteps.verifyFootPrintPageObjects(softAssert);
       softAssert.assertAll();
       logInfo("TestCase Execution for verifyFootPrintPageObjects--->> has been Completed successfully");
    }
    //75363 this Test Case id is not updated
    //75357- Selected category related all Footprints location should be pinned on a Map - only validated Label and count
    @TestRail(testCaseId ={75357,75358})
    @FrameworkAnnotations(author = {"Gaurav Saxena"}, category = {CategoryType.SANITY})
    @Test(priority = 57, description = "To verify the FootPrint details.")
    public void  verifyPinedPopUpDetails() throws Exception {
        log.info("To verify the FootPrint details. " +
                "75357,75358,75363");
        SoftAssert softAssert = new SoftAssert();
        //make Object For Navigate to the My Profile Page
        //Login into The Application
        LoginSteps loginSteps = new LoginSteps(driver);
        User user = SCPConfiguration.user;
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        //Navigate to FootPrint Page under WaysToSave
        HomeSteps homeSteps = new HomeSteps(driver);
        homeSteps.navigateToFootPrintPage();
        // Init FootPrintsteps
        footPrintSteps = new FootPrintSteps(driver);
        footPrintSteps.verifyPinedPopUpDetails(softAssert);
        softAssert.assertAll();
        logInfo("TestCase Execution for verifyPinedPopUpDetails--->> has been Completed successfully");
    }



}

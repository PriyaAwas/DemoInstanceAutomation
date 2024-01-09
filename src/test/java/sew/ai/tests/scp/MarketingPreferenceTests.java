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
import sew.ai.steps.scp.HomeSteps;
import sew.ai.steps.scp.LoginSteps;
import sew.ai.steps.scp.MarketingPreferenceSteps;

import java.sql.SQLException;

public class MarketingPreferenceTests extends TestRunner {
    private static final Logger log = LogManager.getLogger(MarketingPreferenceTests.class);
    private MarketingPreferenceSteps marketingPreferenceSteps;
    @TestRail(testCaseId = {74631, 74632, 74634, 74635, 74639, 74763})
    @FrameworkAnnotations(author = {"Gaurav Saxena"}, category = {CategoryType.SANITY})
    @Test(priority = 20, description = "To verify the Marketing Pref functionality of the Profile Page.")
    public void verifyMarketingPreferencesObjects() {
        log.info("To verify the tests with the below test case id's: " +
                "C74631, C74632, C74634, C74635, C74639, C74763");
        SoftAssert softAssert = new SoftAssert();
        //make Object For Navigate to the My Profile Page
        //Login into The Application 
        LoginSteps loginSteps = new LoginSteps(driver);
        User user = SCPConfiguration.user;
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        //Navigate to Marketing Preference Page
        HomeSteps homeSteps = new HomeSteps(driver);
        homeSteps.navigateToMarketingPreferencePage();
        
        // Init MarketingPreference steps
        marketingPreferenceSteps = new MarketingPreferenceSteps(driver);
        // Login into the application to verify Marketing Preference Objects
        marketingPreferenceSteps.verifyMarketingPreferencesObjects(softAssert);
        // Assert all the soft verification.
        //softAssert.assertAll();
        log.info("Test Case execution for - verifyMarketingPreferencesObjects - is Completed.");
    }
    @TestRail(testCaseId = {74633})
    @FrameworkAnnotations(author = {"Gaurav Saxena"}, category = {CategoryType.SANITY})
    @Test(priority = 21, description = "C74633 - To verify that by DEFAULT all Marketing preference.")
    public void verifyMarketingPreferencesNewUser() throws SQLException {
        log.info("To verify the tests with the below test case id's: " +
                "C74633");
        SoftAssert softAssert = new SoftAssert();
        //Login into The Application
        LoginSteps loginSteps = new LoginSteps(driver);
        User user = SCPConfiguration.user;
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        //Inti Navigation Method to Navigate Marketing Preference page
        HomeSteps homeSteps = new HomeSteps(driver);
        homeSteps.navigateToMarketingPreferencePage();
        // Init MarketingPreference steps
        marketingPreferenceSteps = new MarketingPreferenceSteps(driver);
        marketingPreferenceSteps.verifyMarketingPreferencesNewUser(softAssert);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyMarketingPreferencesNewUser - is Completed.");

    }
}

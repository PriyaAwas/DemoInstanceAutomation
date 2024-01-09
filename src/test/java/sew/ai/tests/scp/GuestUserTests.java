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
import sew.ai.steps.scp.GuestuserSteps;
import sew.ai.steps.scp.LoginSteps;


public class GuestUserTests extends TestRunner {
    private static final Logger log = LogManager.getLogger(GuestUserTests.class);
    private GuestuserSteps  guestuserSteps;
    private DashboardSteps dashboardSteps;

    @TestRail(testCaseId = {74755, 74756, 74757, 74758, 74759})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyGuestUserPageObject() {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        guestuserSteps = new GuestuserSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        guestuserSteps.guestUserPageObject(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    @TestRail(testCaseId = {74761, 74762, 74764, 74817})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyErrorMsgForInviteUserPopup() {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        guestuserSteps = new GuestuserSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        guestuserSteps.errorMsgForInviteUserPopup(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
 
    @TestRail(testCaseId = {74765, 74767, 74772, 74760, 74789, 74797, 74796,})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyInviteNewUserAsGuest() throws InterruptedException {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        guestuserSteps = new GuestuserSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        guestuserSteps.inviteNewUserAsGuest(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    
    @TestRail(testCaseId = {74770, 74776, 74777, 74778, 74779})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyEditAndResendGuestUserInvites() {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        guestuserSteps = new GuestuserSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        guestuserSteps.editAndResendGuestUserInvites(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    @TestRail(testCaseId = {74785})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyGuestUserEditPrimaryEmail() throws InterruptedException {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        guestuserSteps = new GuestuserSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        guestuserSteps.guestUserEditPrimaryEmail(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    @TestRail(testCaseId = {74787, 74791,74766, 74773, 74774, 74788, 74790, 74792, 74795, 74794})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyInviteExistingUserWithGuestAccess() {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        guestuserSteps = new GuestuserSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        guestuserSteps.inviteExistingUserWithGuestAccess(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    
    @TestRail(testCaseId = {74763, 74803})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyMaxNoGuestUserInvite() {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        guestuserSteps = new GuestuserSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        guestuserSteps.maxNoGuestUserInvite(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    
    @TestRail(testCaseId = {74799, 74801, 74804})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyPropertyManagerAccessExpireOnEndDate() throws InterruptedException {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        guestuserSteps = new GuestuserSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        guestuserSteps.propertyManagerAccessExpireOnEndDate(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    
    @TestRail(testCaseId = {99074,133461,133462,74775,102488})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyUpdateNickNameForGuestAccountCard() throws Exception {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        guestuserSteps = new GuestuserSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        guestuserSteps.updateNickNameForGuestAccountCard(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    
    @TestRail(testCaseId = {74765})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyLinkExpirationForNewUserAsGuest() throws Exception {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        guestuserSteps = new GuestuserSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        guestuserSteps.linkExpirationForNewUserAsGuest(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    /**
     * Test 9: This test method verifies below tests: 1). C74798 - Guest User |
     * Verification of guest user access post end date defined in the period.
     * 2). C74800 - Guest user | Verify If guest user has only one account which
     * is guest account, then its account shall be disabled post expiration of
     * period.
     */
    @TestRail(testCaseId = {74798, 74800, 74793, 102490})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyGuestAccessExpireOnEndDate() throws Exception {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        guestuserSteps = new GuestuserSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        guestuserSteps.guestAccessExpireOnEndDate(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
  
    @TestRail(testCaseId = {74780, 74782, 74783, 74784})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyGuestUserRegistrationFieldValidations() throws Exception {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        guestuserSteps = new GuestuserSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        guestuserSteps.guestUserRegistrationFieldValidations(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
  
}

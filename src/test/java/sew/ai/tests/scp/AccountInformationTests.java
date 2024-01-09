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
import sew.ai.models.Customer;
import sew.ai.models.User;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.AccountInformationSteps;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.LoginSteps;
import sew.ai.steps.scp.NotificationPreferenceSteps;

public class AccountInformationTests extends TestRunner {
    private static final Logger log = LogManager.getLogger(AccountInformationTests.class);
    private AccountInformationSteps  accountInformationSteps;
    private DashboardSteps dashboardSteps;

    public AccountInformationTests() {
    	accountInformationSteps = new AccountInformationSteps(driver);
        
    }

    @TestRail(testCaseId = {74542, 74544, 74545, 74548, 74549, 74550, 74551, 74552, 74553})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyLinkAccountUIAndValidation() {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        accountInformationSteps = new AccountInformationSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        accountInformationSteps.linkAccountUIAndValidation(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }

    @TestRail(testCaseId = {74546})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyLinkAlreadyRegisteredActiveAccount() {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        accountInformationSteps = new AccountInformationSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        accountInformationSteps.linkAlreadyRegisteredActiveAccount(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
   
    @TestRail(testCaseId = {74556})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyLinkInactiveAccount() {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        accountInformationSteps = new AccountInformationSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        accountInformationSteps.linkInactiveAccount(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    
    @TestRail(testCaseId = {74557})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyLinkRegisteredButItsActivationLinkExpiredAccount() throws SQLException {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        accountInformationSteps = new AccountInformationSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        accountInformationSteps.linkRegisteredButItsActivationLinkExpiredAccount(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    
    @TestRail(testCaseId = {74543, 74547, 74555, 74559, 74491, 102299, 102486, 102489,113316,113318})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyLinkAccountAndDeleteLinkedAccount() {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        accountInformationSteps = new AccountInformationSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        accountInformationSteps.linkAccountAndDeleteLinkedAccount(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    
    @TestRail(testCaseId = {65065,119982})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyDropdownOnLinkDeLinkAccounts() {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        accountInformationSteps = new AccountInformationSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        accountInformationSteps.dropdownOnLinkDeLinkAccounts(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    
    @TestRail(testCaseId = {74554})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyCISValidationMessageForLinkAccount() {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        accountInformationSteps = new AccountInformationSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        accountInformationSteps.cisValidationMessageForLinkAccount(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    
    
    @TestRail(testCaseId = {74558, 102647})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyUnableLinkResidentialAccountInCommercialSectionAndViceVersa() {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        accountInformationSteps = new AccountInformationSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        accountInformationSteps.unableLinkResidentialAccountInCommercialSectionAndViceVersa(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    
    
    @TestRail(testCaseId = {107197,107198,107200,107205,107201})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyMultiLinkAccountAndDeleteLinkedAccount() {
        log.info("To verify the tests with the below test case id's: " +
                "C107197,C107198,C107200,C107205,C107201");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        accountInformationSteps = new AccountInformationSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        accountInformationSteps.multiLinkAccountAndDeleteLinkedAccount(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    
    @TestRail(testCaseId = {107199,108222,139111})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyMultiLinkAccountAndDeleteCommericalLinkedAccount() {
        log.info("To verify the tests with the below test case id's: " +
                "C107197,C107198,C107200,C107205,C107201");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        accountInformationSteps = new AccountInformationSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        accountInformationSteps.multiLinkAccountAndDeleteCommericalLinkedAccount(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    @TestRail(testCaseId = {107199,107203,107204})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyMakeDefaultForMultiLinkAccount() {
        log.info("To verify the tests with the below test case id's: " +
                "C107199,C107203,C107204");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        accountInformationSteps = new AccountInformationSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        accountInformationSteps.makeDefaultForMultiLinkAccount(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }

    @TestRail(testCaseId = {107197,107198,107200,107205,107201,139112,139113})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyMultiLinkAccountAndDeleteAgainLinkedAccount() {
        log.info("To verify the tests with the below test case id's: " +
                "C107197,C107198,C107200,C107205,C107201,C139112,C139113");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        accountInformationSteps = new AccountInformationSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        accountInformationSteps.multiLinkAccountAndDeleteAgainLinkedAccount(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    @TestRail(testCaseId = {139114})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyMultiAccountDelinkAndregistration() {
        log.info("To verify the tests with the below test case id's: " +
                "C139114");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        accountInformationSteps = new AccountInformationSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        //accountInformationSteps.multiAccountregistrationandDelinkedAccount(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    
    @TestRail(testCaseId = {107197,107198,107200,107205,107201})
    @FrameworkAnnotations(author = {"Mukesh Pant"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the dashboard page objects after login.")
    public void verifyMultiAccountregistrationandDelinkedAccount() {
        log.info("To verify the tests with the below test case id's: " +
                "C139114");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        accountInformationSteps = new AccountInformationSteps(driver);
        dashboardSteps    = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        // Verify the user landed to the dashboard page
        accountInformationSteps.multiAccountregistrationandDelinkedAccount(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
    }
    }

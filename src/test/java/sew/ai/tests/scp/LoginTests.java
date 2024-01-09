package sew.ai.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.config.CSPConfiguration;
import sew.ai.config.Configuration;
import sew.ai.config.SCPConfiguration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.helpers.testrail.TestRail;
import sew.ai.models.User;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.LoginSteps;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.RandomUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

//import static sew.ai.steps.scp.DashboardSteps.dashboardTextProp;
import static sew.ai.steps.scp.LoginSteps.loginTextProp;

public class LoginTests extends TestRunner {
    private static final Logger log = LogManager.getLogger(LoginTests.class);
    private LoginSteps loginSteps;

    @TestRail(testCaseId = {75080, 101604})
    @FrameworkAnnotations(author = {"Ranjit Biswal"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the login page objects.", groups = {"sanity"})
    public void verifyLoginPageObjects() {
        log.info("To verify the tests with the below test case id's: " +
                "C75080, C101604");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        loginSteps = new LoginSteps(driver);
        // Login into the application
        loginSteps.verifyTheLoginPageObject(softAssert);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyLoginPageObjects - is Completed.");
    }

    //Modified by Ajit as part of COE
    @TestRail(testCaseId = {77197})
    @FrameworkAnnotations(author = {"Ranjit Biswal"}, category = {CategoryType.SANITY})
    @Test(priority = 2, description = "To verify the login functionality with valid credentials.")
    public void verifyValidLogin() {
        log.info("To verify the tests with the below test case id's: " +
                "C77197");
        SoftAssert softAssert = new SoftAssert();
        // Init user model
        User user = SCPConfiguration.user;
        // Init login steps
        loginSteps = new LoginSteps(driver);
        // Login into the application
//        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(
//                user.getUserName(),
//                user.getPassword()
//        );
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication("zytcllc1212","Test@1234");
        //DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        // Verify the salutation
//        softAssert.assertEquals(dashboardSteps.getSalutationCustomerName(), user.getFirstName(),
//                "Customer first name not matched.");
        softAssert.assertEquals(dashboardSteps.getSalutationCustomerName(), "TJB Management LLC",
                "Customer first name not matched.");
//        // Verify the account label
//        softAssert.assertEquals(dashboardSteps.getAccountLabel(), dashboardTextProp
//                        .getPropValue("accountDropDownLabel"),
//                "Account drop-down label not matched.");
//        // Verify the image profile icon.
//        softAssert.assertTrue(dashboardSteps.isProfileIconVisible(),
//                "Profile icon is not visible.");
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyValidLogin - is Completed.");
    }

    @TestRail(testCaseId = {75087, 75088})
    @FrameworkAnnotations(author = {"Ranjit Biswal"}, category = {CategoryType.SANITY})
    @Test(priority = 3, description = "To verify the login functionality with invalid credentials.")
    public void verifyInvalidLogin() {
        log.info("To verify the tests with the below test case id's: " +
                "C75087, C75088");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        loginSteps = new LoginSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Verify login with blank creds
        softAssert.assertEquals(loginSteps.loginWithBlankCreds(), loginTextProp
                        .getPropValue("loginWithBlankCredsErrMsg"),
                "Login with Blank creds error message not matched.");
        // Verify login with blank password
        softAssert.assertEquals(loginSteps.loginWithBlankPassword(user.getUserName()), loginTextProp
                        .getPropValue("loginWithBlankPasswordErrMsg"),
                "Blank username field validation not correct.");
        // Verify login with blank username
        softAssert.assertEquals(loginSteps.loginWithBlankUsername(user.getPassword()), loginTextProp
                        .getPropValue("loginWithBlankUsernameErrMsg"),
                "Blank username field validation not correct."
        );
        // Verify login with wrong username
        softAssert.assertEquals(loginSteps.loginWithWrongUsername(user.getUserName(), user.getPassword()),
                loginTextProp.getPropValue("invalidCredentialsErrMsg"),
                "Wrong toast when login using the wrong username.");
        // Verify login with wrong password
        softAssert.assertEquals(loginSteps.loginWithWrongPassword(user.getPassword(), user.getUserName()),
                loginTextProp.getPropValue("invalidCredentialsErrMsg"),
                "Wrong toast when login using the wrong password.");
        // Verify login with wrong username and password.
        softAssert.assertEquals(loginSteps.loginWithInvalidCreds(), loginTextProp
                        .getPropValue("invalidCredentialsErrMsg"),
                "Wrong toast when login using invalid creds.");
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyInvalidLogin - is Completed.");
    }

    @TestRail(testCaseId = {77198, 77199})
    @FrameworkAnnotations(author = {"Ranjit Biswal"}, category = {CategoryType.REGRESSION})
    @Test(priority = 4, description = "To verify the remember me login functionality.")
    public void verifyRememberMeLoginFunctionality() {
        log.info("To verify the tests with the below test case id's: " +
                "C75087, C75088");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        loginSteps = new LoginSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Logging in to the application by checking remember me checkbox.
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheAppByCheckingRememberMe(
                user.getUserName(),
                user.getPassword()
        );
        // Verify remember me functionality
        Boolean isRememberMeChecked = loginSteps.verifyUsernameAndRememberMeCheckboxStatus(
                dashboardSteps,
                user.getUserName()
        );
        Assert.assertTrue(isRememberMeChecked);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyRememberMeLoginFunctionality - is Completed.");
    }

    @TestRail(testCaseId = {75089})
    @FrameworkAnnotations(author = {"Ranjit Biswal"}, category = {CategoryType.REGRESSION})
    @Test(priority = 5, description = "To verify the login functionality with Deactive user.")
    public void verifyLoginWithDeactiveUser() {
        log.info("To verify the tests with the below test case id's: " +
                "C75089");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        loginSteps = new LoginSteps(driver);
        // Init Deactive user details
        String userName = "";
        String password = null;
        ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.getDeActiveAccount());
        ResultSet resultSet1 = DataBaseUtils.getResultSet(SQLQueries.getPassword(Configuration
                .toString("userName")));
        try {
            resultSet.next();
            userName = resultSet.getString("username");
            resultSet1.next();
            password = resultSet1.getString("password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Update password for the deactive user in the DB
        DataBaseUtils.executeUpdateDeleteSQLQuery(SQLQueries.updatePassword(userName, password));
        password = Configuration.toString("password");
        // Logging in to the application by a Deactive user cred.
        String errMsg = loginSteps.loginWithDeactiveUser(
                userName,
                password
        );
        softAssert.assertEquals(errMsg, loginTextProp.getPropValue("loginWithDeactiveUserErrMsg"),
                "Error message for Deactive user is not matched.");
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyLoginWithDeactiveUser - is Completed.");
    }

    @TestRail(testCaseId = {64841})
    @FrameworkAnnotations(author = {"Ranjit Biswal"}, category = {CategoryType.REGRESSION})
    @Test(priority = 6, description = "To verify the login functionality with closed accounts.")
    public void verifyLoginWithClosedAccounts() {
        log.info("To verify the tests with the below test case id's: " +
                "C64841");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        loginSteps = new LoginSteps(driver);
        // Init Deactive user details
        String userName = "";
        String password = null;
        ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.getDeActiveAccount());
        ResultSet resultSet1 = DataBaseUtils.getResultSet(SQLQueries.getPassword(Configuration
                .toString("userName")));
        try {
            resultSet.next();
            userName = resultSet.getString("username");
            resultSet1.next();
            password = resultSet1.getString("password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Update password for the deactive user in the DB
        DataBaseUtils.executeUpdateDeleteSQLQuery(SQLQueries.updatePassword(userName, password));
        password = Configuration.toString("password");
        // Logging in to the application by a Deactive user cred.
        String errMsg = loginSteps.loginWithDeactiveUser(
                userName,
                password
        );
        softAssert.assertEquals(errMsg, loginTextProp.getPropValue("loginWithDeactiveUserErrMsg"),
                "Error message for Deactive user is not matched.");
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyLoginWithClosedAccounts - is Completed.");
    }

    @TestRail(testCaseId = {36462})
    @FrameworkAnnotations(author = {"Ranjit Biswal"}, category = {CategoryType.REGRESSION})
    @Test(priority = 7, description = "To verify the language switch functionality on login page.")
    public void verifySwitchLanguageFunctionality() {
        log.info("To verify the tests with the below test case id's: " +
                "C36462");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        loginSteps = new LoginSteps(driver);
        // Init the default selected language
        String defaultLanguageDB = null;
        String defaultLanguageUI;
        try {
            ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.getDefaultLanguage());
            while (resultSet.next()) {
                defaultLanguageDB = resultSet.getString("languagedescription");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Verify language switch objects
        if (CSPConfiguration.languageConfig.size() > 1) {
            defaultLanguageUI = loginSteps.verifyLanguageSwitchObjects(softAssert);
            softAssert.assertEquals(defaultLanguageUI, defaultLanguageDB,
                    "Default language not matched.");
        }
        else {
            defaultLanguageUI = loginSteps.verifyLanguageSwitchObjects(softAssert);
            softAssert.assertEquals(defaultLanguageUI, loginTextProp.getPropValue("optionInLanguageDropdown"),
                    "Default language not matched.");
        }
        // Verify language switched successfully.
        loginSteps.verifyLanguageSwitchFeature(softAssert, defaultLanguageDB);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifySwitchLanguageFunctionality - is Completed.");
    }

    @TestRail(testCaseId = {77195})
    @FrameworkAnnotations(author = {"Ranjit Biswal"}, category = {CategoryType.REGRESSION})
    @Test(priority = 8, description = "To verify the account block functionality on login page.")
    public void verifyAccountBlockOnLogin() {
        log.info("To verify the tests with the below test case id's: " +
                "C77195");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        loginSteps = new LoginSteps(driver);
        // Init the account lock attempt and duration language
        int attemptsAccLock = 0;
        int durationAccLock = 0;
        ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.allUtilitySettingsQuery);
        try {
            resultSet.next();
            attemptsAccLock = resultSet.getInt("LoginAcctLockAttempt");
            durationAccLock = resultSet.getInt("LoginAcctLockDuration");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Run stored procedure to unblock account and IP if locked.
        DataBaseUtils.callStoredProcedureUnblockAccountIp();
        // Verify the account block feature
        String toastErrMsg = loginSteps.verifyAccountIPLockFunctionality(softAssert, attemptsAccLock);
        softAssert.assertEquals(toastErrMsg, loginTextProp.getPropValue("accountIPBlockedErrMsg"),
                "Invalid credential error message not matched.");
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyAccountBlockOnLogin - is Completed.");
    }

    @TestRail(testCaseId = {77194})
    @FrameworkAnnotations(author = {"Ranjit Biswal"}, category = {CategoryType.REGRESSION})
    @Test(priority = 9, description = "To verify the IP block functionality on login page.")
    public void verifyIPBlockOnLogin() {
        log.info("To verify the tests with the below test case id's: " +
                "C77194");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        loginSteps = new LoginSteps(driver);
        // Init the account lock attempt and duration language
        int attemptsIPLock = 0;
        int durationIPLock = 0;
        ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.allUtilitySettingsQuery);
        try {
            resultSet.next();
            attemptsIPLock = resultSet.getInt("LoginIPLockAttempt");
            durationIPLock = resultSet.getInt("LoginIPLockDuration");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Run stored procedure to unblock account and IP if locked.
        DataBaseUtils.callStoredProcedureUnblockAccountIp();
        // Verify the account block feature
        String toastErrMsg = loginSteps.verifyAccountIPLockFunctionality(softAssert, attemptsIPLock);
        softAssert.assertEquals(toastErrMsg, loginTextProp.getPropValue("accountIPBlockedErrMsg"),
                "Invalid credential error message not matched.");
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyIPBlockOnLogin - is Completed.");
    }

    @TestRail(testCaseId = {75086})
    @FrameworkAnnotations(author = {"Ranjit Biswal"}, category = {CategoryType.REGRESSION})
    @Test(priority = 10, description = "To verify the efficiency widgets program on login page.")
    public void verifyEfficiencyWidgetsProgram() {
        log.info("To verify the tests with the below test case id's: " +
                "C75086");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        loginSteps = new LoginSteps(driver);
        // Init the default selected language
        loginSteps.verifyEfficiencyWidgets(softAssert);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyEfficiencyWidgetsProgram - is Completed.");
    }

    @TestRail(testCaseId = {75078})
    @FrameworkAnnotations(author = {"Ranjit Biswal"}, category = {CategoryType.REGRESSION})
    @Test(priority = 11, description = "To verify the chat window on login page.")
    public void verifyChatWindow() {
        log.info("To verify the tests with the below test case id's: " +
                "C75078");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        loginSteps = new LoginSteps(driver);
        // Check the CSP configuration
        if (CSPConfiguration.cspConfig.get("Chat") == 1) {
            // Verify chat windows
            loginSteps.verifyChatWindow(softAssert);
        }
        else {
            log.info("Chat bot is not enabled from CSP");
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyChatWindow - is Completed.");
    }

    @TestRail(testCaseId = {75095})
    @FrameworkAnnotations(author = {"Ranjit Biswal"}, category = {CategoryType.REGRESSION})
    @Test(priority = 12, description = "To verify the login functionality for migrated user.")
    public void verifyLoginForMigratedUser() {
        log.info("To verify the tests with the below test case id's: " +
                "C75095");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        loginSteps = new LoginSteps(driver);
        // Init user
        User user = SCPConfiguration.user;
        // Init username
        String newUsername = "jane.carl09081990@yahoo.com";
        try {
            // Update username
            DataBaseUtils.executeUpdateDeleteSQLQuery(SQLQueries.updateUsernameQuery(user.getUserId(), newUsername));
            DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(newUsername, user.getPassword());
            softAssert.assertTrue(dashboardSteps.isProfileIconVisible(),
                    "Profile icon is not visible.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Update username
            DataBaseUtils.executeUpdateDeleteSQLQuery(SQLQueries.updateUsernameQuery(
                    user.getUserId(),
                    user.getUserName())
            );
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyLoginForMigratedUser - is Completed.");
    }

    @TestRail(testCaseId = {75105, 75106, 75107, 75108, 75109, 75110})
    @FrameworkAnnotations(author = {"Ranjit Biswal"}, category = {CategoryType.REGRESSION})
    @Test(priority = 13, description = "To verify the username and password acceptance criteria " +
            "on login page.")
    public void verifyUsernamePasswordAcceptanceCriteria() {
        log.info("To verify the tests with the below test case id's: " +
                "C75105, C75106, C75107, C75108, C75109, C75110");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        loginSteps = new LoginSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Init new username
        String newUsername = RandomUtil.generateRandomString(230, RandomUtil.Mode.ALPHANUMERIC);
        newUsername = newUsername + "(!@#$%^&*~,`.?/{[]})";
        try {
            // Update username
            DataBaseUtils.executeUpdateDeleteSQLQuery(SQLQueries.updateUsernameQuery(user.getUserId(), newUsername));
            // Verify max length criteria of username and password
            loginSteps.verifyUsernamePasswordMaxLengthCriteria(softAssert);
            // Verifying copy and paste of space character in username password field.
            String input = "J&DCo 09012020";
            loginSteps.verifyCopyPasteSpaceInUsernamePasswordField(softAssert, input);
            // Verifying type space in username password field.
            input = "Kirk Kaul001";
            loginSteps.verifyTypeSpaceInUsernamePasswordField(softAssert, input);
            // Login into the changed username
            DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(newUsername, user.getPassword());
            softAssert.assertTrue(dashboardSteps.isProfileIconVisible(),
                    "Profile icon is not visible.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Update username
            DataBaseUtils.executeUpdateDeleteSQLQuery(SQLQueries.updateUsernameQuery(
                    user.getUserId(),
                    user.getUserName())
            );
        }
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyLoginForMigratedUser - is Completed.");
    }
}

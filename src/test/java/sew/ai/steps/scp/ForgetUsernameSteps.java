package sew.ai.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import sew.ai.config.SCPConfiguration;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.User;
import sew.ai.pageObjects.scp.ForgotUsernamePage;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.PropertiesUtil;

import java.sql.ResultSet;
import java.util.HashMap;

import static sew.ai.steps.scp.LoginSteps.loginTextProp;

public class ForgetUsernameSteps extends ForgotUsernamePage {
    private static final Logger log = LogManager.getLogger(ForgetUsernameSteps.class);

    public static PropertiesUtil ForgotUsernameTextProp;

    public ForgetUsernameSteps(WebDriver driver) {
        super(driver);
        ForgotUsernameTextProp = new PropertiesUtil(
                FilePaths.SCP_TEXT_PROPERTIES + Constants.FORGET_USERNAME_IN_TXT_FILENAME
        );
    }

    public void verifyForgotUsernameInObject(SoftAssert softAssert) {
        LoginSteps loginSteps;
        loginSteps = new LoginSteps(driver);
        Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"), loginTextProp
                .getPropValue("loginPageTitle")));
        loginSteps.clickForgotUsernameLnk();
        softAssert.assertTrue(isForgotUsernameInPage(ForgotUsernameTextProp.getPropValue("ForgotUsernamePageUrl"),
                (ForgotUsernameTextProp.getPropValue("ForgotUsernamePageTitle"))), "Page Title & URL does not Match");
        softAssert.assertTrue(isEmailAddressTxtVisible(), "Email Address Text Box is not visibility");
        softAssert.assertTrue(isPagelabelHeaderVisible(), "Page Label Header is not visibility");
        softAssert.assertTrue(isLblPleaseEnterPrimaryEmailVisible(), "Please Enter Primary Email is not visibility");
        softAssert.assertTrue(isLblTxtBoxEmailAddressVisible(), "Txt Box Email Address is not visible");
        softAssert.assertTrue(isIcoEmailAddressInfoVisible(), "Ico Email Address Info is not visible");
        softAssert.assertTrue(isCancelBtnVisible(), "Cancel Button Info is not visible");
        softAssert.assertTrue(isSubmitBtnVisible(), "Submit Button Info is is not visible");
    }

    public boolean isForgotUsernameInPage(String url, String title) {
        Boolean isForgetPasswordPage = false;
        log.info("Checking that the current page is Forget Username Page");
        if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
            isForgetPasswordPage = true;
        log.info("The current page is ForgotUsername Page {}: " + isForgetPasswordPage);
        return isForgetPasswordPage;
    }

    public void verifyForgotUsernameForm() {
        User user = SCPConfiguration.user;
        // To verify the information displayed on info icon present next to the email address.
        String infoIcon = getIcoEmailDataOriginalTitle();
        Assert.assertEquals(infoIcon, ForgotUsernameTextProp.getPropValue("txtToolTipEmailAddress"));
        clickSubmitBtn();
        String errMsgEmail = getlblGenericErrorMessage();
        Assert.assertEquals(errMsgEmail, ForgotUsernameTextProp.getPropValue("txtMsgBlankEmailAddress"));
        clickCancelBtn();
        LoginSteps loginSteps;
        loginSteps = new LoginSteps(driver);
        loginSteps.waitForUserNameFieldVisibility();
        Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"), loginTextProp
                .getPropValue("loginPageTitle")));
        loginSteps.clickForgotUsernameLnk();
        Assert.assertTrue(isForgotUsernameInPage(ForgotUsernameTextProp.getPropValue("ForgotUsernamePageUrl"),
                (ForgotUsernameTextProp.getPropValue("ForgotUsernamePageTitle"))));
        //  Verify that user is able to retrieve Username with Valid account number and email.
        String sValidEmailAddress = user.getEmailId();
        String sPassword = user.getPassword();
        String sValidUtilityAccountNo = getDefaultAccountOfUser(user.getUserId());
        populateEmailAddress(sValidEmailAddress);
        clickSubmitBtn();
        String SuccessToasterMsg = getToastMessage();
        Assert.assertEquals(SuccessToasterMsg, ForgotUsernameTextProp.getPropValue("txtSuccessToasterMsg").replace("${Email}", sValidEmailAddress));
        // Verify that user is able to retrieve username with valid account number and email.
        String sUsernameFetchedFromMail = fetchUsernameFromEmail(sValidEmailAddress);
        loginSteps.loginIntoTheApplication(sUsernameFetchedFromMail, user.getPassword());
    }

    public void enterInvalidEmail() {
        User user = SCPConfiguration.user;
        clickSubmitBtn();
        String errMsgEmail = getlblGenericErrorMessage();
        Assert.assertEquals(errMsgEmail, ForgotUsernameTextProp.getPropValue("txtMsgBlankEmailAddress"));
        // Enter Invalid Email adress
        populateEmailAddress("invaliedemailsmartusys.com");
        clickSubmitBtn();
        String invalidEmail = getlblGenericErrorMessage();
        Assert.assertEquals(invalidEmail, ForgotUsernameTextProp.getPropValue("txtLblEnterInvalidEmailAddress"));
        clearEmailAddressField();
        // Enter Valid Email address
        populateEmailAddress(user.getEmailId());
        clickSubmitBtn();
        String SuccessToasterMsg = getToastMessage();
        Assert.assertEquals(SuccessToasterMsg, ForgotUsernameTextProp.getPropValue("txtSuccessToasterMsg").replace("${Email}", user.getEmailId()));
    }

    public void maxAttemptUserLock() {
        User user = SCPConfiguration.user;
        ResultSet rs;
        // This Query brings LoginIPLockAttempt from the Database.
        int iLoginAcctLockAttempt = 0;
        int iLoginAcctLockDuration = 0;
        try {
            rs = DataBaseUtils.getResultSet(SQLQueries.sAllUtilitySettingsQuery);
            rs.next();
            iLoginAcctLockAttempt = rs.getInt("LoginIPLockAttempt");
            iLoginAcctLockDuration = rs.getInt("LoginIPLockDuration");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Initially resets all the IP and Account block count
        DataBaseUtils.callStoredProcedureUnblockAccountIp();
        System.out.println(iLoginAcctLockAttempt);
        for (int i = 0; i <= iLoginAcctLockAttempt; i++) {
            populateEmailAddress(user.getEmailId());
            clickSubmitBtn();
            {
                break;
            }
        }
        DataBaseUtils.callStoredProcedureUnblockAccountIp();
        populateEmailAddress(user.getEmailId());
        clickSubmitBtn();
    }

    public String getDefaultAccountOfUser(String sUsername) {
        String sUtilityAccNo = null;
        try {
            ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries
                    .getDefaultAccount(sUsername));
            resultSet.next();
            sUtilityAccNo = resultSet.getString("UtilityAccountNumber");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sUtilityAccNo;
    }

    public void fetchUserNameFromMail() {
        User user = SCPConfiguration.user;
        LoginSteps loginSteps;
        loginSteps = new LoginSteps(driver);
        // Verify that user is able to retrieve username with valid account number and email.
        String sValidEmailAddress = "OneMailMultiAccnt@yopmail.com";
        populateEmailAddress(sValidEmailAddress);
        clickSubmitBtn();
        String SuccessToasterMsg = getToastMessage();
        Assert.assertEquals(SuccessToasterMsg, ForgotUsernameTextProp.getPropValue("txtSuccessToasterMsg").replace("${Email}", sValidEmailAddress));
        String sUsernameFetchedFromMail = clickAccountLinkUsernameFromEmail(sValidEmailAddress);
        loginSteps.loginIntoTheApplication(sUsernameFetchedFromMail, "Demo@12345");
    }

    public String clickAccountLinkUsernameFromEmail(String sEmailAddress) {
        String sUsername = null;
        User user = SCPConfiguration.user;
        // Exporting the reset Username link from the database.
        try {
            String sSubjectLine = ForgotUsernameTextProp.getPropValue("expectedForgotUsernameEmailSubject");
            String sResetPasswordEmailMsgQuery = SQLQueries.getUserNameAccountEmailMsg(sSubjectLine,
                    sEmailAddress);
            ResultSet resultSet = DataBaseUtils.getResultSet(sResetPasswordEmailMsgQuery);
            resultSet.next();
            String sUsernameAssistanceMsg = resultSet.getString("Message");
            System.out.println(sUsernameAssistanceMsg);
            String sIsEmailSent = resultSet.getString("IsNotify");
            // Check email is linked with single user or multiple users
            String query = "select * from [User] where EmailID = '" + sEmailAddress + "'";
            ResultSet resultSet1 = DataBaseUtils.getResultSet(query);
            int count = 0;
            while (resultSet1.next()) {
                count = count + 1;
                if (count > 1) {
                    break;
                }
            }
            // If multiple             
            if (count > 1) {
                String sStartingString = "https://";
                int length = sStartingString.length();
                String sEndingString = "'>Click here</a> to provide";
                String getUrl = sUsernameAssistanceMsg.substring(sUsernameAssistanceMsg.indexOf(sStartingString),
                        sUsernameAssistanceMsg.indexOf(sEndingString));
                navigateToUrl(getUrl);
                // verify open page and submit account number
                String sValidUtilityAccountNo = getDefaultAccountOfUser("OneMailMultiAccnt");
                enterAccountNumber(sValidUtilityAccountNo);
                clickSubmitBtnAccountNo();
                sResetPasswordEmailMsgQuery = SQLQueries.getUsernameAssistanceEmailMsg(sSubjectLine,
                        sEmailAddress);
                resultSet = DataBaseUtils.getResultSet(sResetPasswordEmailMsgQuery);
                resultSet.next();
                sUsernameAssistanceMsg = resultSet.getString("Message");
                System.out.println(sUsernameAssistanceMsg);
                sIsEmailSent = resultSet.getString("IsNotify");
                sStartingString = "<p>Your username for your Smart Energy Water Account is: ";
                length = sStartingString.length();
                sEndingString = "</p>\r\n" +
                        "<p><br>\r\n" +
                        "</p>\r\n" +
                        "<p><a href='https:";
                sUsername = sUsernameAssistanceMsg.substring(sUsernameAssistanceMsg.indexOf(sStartingString) + length,
                        sUsernameAssistanceMsg.indexOf(sEndingString));
                sUsername = sUsername.replace("</p>", "").replace("\n", "");
            }
            else {
                String sStartingString = "Your username for your Smart Energy Water Account is: ";
                int length = sStartingString.length();
                String sEndingString = "</p>\r\n" +
                        "<p><br>\r\n" +
                        "</p>\r\n" +
                        "<p><a href='https:";
                sUsername = sUsernameAssistanceMsg.substring(sUsernameAssistanceMsg.indexOf(sStartingString) + length,
                        sUsernameAssistanceMsg.indexOf(sEndingString));
                sUsername = sUsername.replace("</p>", "").replace("\n", "");
            }
            System.out.println("Found username---==========>" + sUsername);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sUsername;
    }

    /**
     * This method is to fetch the username from username assistance mail.
     *
     * @param sEmailAddress
     * @return
     */

    public String fetchUsernameFromEmail(String sEmailAddress) {
        String sUsername = null;
        User user = SCPConfiguration.user;
        // Exporting the reset Username link from the database.
        try {
            String sSubjectLine = ForgotUsernameTextProp.getPropValue("expectedForgotUsernameEmailSubject");
            String sResetPasswordEmailMsgQuery = SQLQueries.getUsernameAssistanceEmailMsg(sSubjectLine,
                    sEmailAddress);
            ResultSet resultSet = DataBaseUtils.getResultSet(sResetPasswordEmailMsgQuery);
            resultSet.next();
            String sUsernameAssistanceMsg = resultSet.getString("Message");
            System.out.println(sUsernameAssistanceMsg);
            String sIsEmailSent = resultSet.getString("IsNotify");
            // Check email is linked with single user or multiple users
            String query = "select * from [User] where EmailID = '" + sEmailAddress + "'";
            ResultSet resultSet1 = DataBaseUtils.getResultSet(query);
            int count = 0;
            while (resultSet1.next()) {
                count = count + 1;
                if (count > 1) {
                    break;
                }
            }
            // If multiple             
            if (count > 1) {
                String sStartingString = "https://";
                int length = sStartingString.length();
                String sEndingString = "'>Click here</a> to provide";
                String getUrl = sUsernameAssistanceMsg.substring(sUsernameAssistanceMsg.indexOf(sStartingString),
                        sUsernameAssistanceMsg.indexOf(sEndingString));
                navigateToUrl(getUrl);
                // verify open page and submit account number
                String sValidUtilityAccountNo = getDefaultAccountOfUser(user.getUserId());
                String sValidEmailAddress = user.getEmailId();
                populateEmailAddress(sValidEmailAddress);
                clickSubmitBtn();
                sResetPasswordEmailMsgQuery = SQLQueries.getUsernameAssistanceEmailMsg(sSubjectLine,
                        sEmailAddress);
                resultSet = DataBaseUtils.getResultSet(sResetPasswordEmailMsgQuery);
                resultSet.next();
                sUsernameAssistanceMsg = resultSet.getString("Message");
                System.out.println(sUsernameAssistanceMsg);
                sIsEmailSent = resultSet.getString("IsNotify");
                sStartingString = "<p>Your username for your Smart Energy Water Account is: ";
                length = sStartingString.length();
                sEndingString = "</p>\r\n" +
                        "<p><br>\r\n" +
                        "</p>\r\n" +
                        "<p><a href='https:";
                sUsername = sUsernameAssistanceMsg.substring(sUsernameAssistanceMsg.indexOf(sStartingString) + length,
                        sUsernameAssistanceMsg.indexOf(sEndingString));
                sUsername = sUsername.replace("</p>", "").replace("\n", "");
            }
            else {
                String sStartingString = "Your username for your Smart Energy Water Account is: ";
                int length = sStartingString.length();
                String sEndingString = "</p>\r\n" +
                        "<p><br>\r\n" +
                        "</p>\r\n" +
                        "<p><a href='https:";
                sUsername = sUsernameAssistanceMsg.substring(sUsernameAssistanceMsg.indexOf(sStartingString) + length,
                        sUsernameAssistanceMsg.indexOf(sEndingString));
                sUsername = sUsername.replace("</p>", "").replace("\n", "");
            }
            System.out.println("Found username---==========>" + sUsername);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sUsername;
    }

    /**
     * This method is used to hit the given url.
     *
     * @param url
     */
    public void navigateToUrl(String url) {
        try {
            driver.get(url);
        } catch (Exception exception) {
            System.err.format("No Element Found: " + exception);
        }
    }

    public void verifyInActiveAccountInfo() {
        HashMap<String, String> inactiveAccInfo = DataBaseUtils.getInactiveAccInfo();
        populateEmailAddress(inactiveAccInfo.get("EmailID"));
        clickSubmitBtn();
    }
}

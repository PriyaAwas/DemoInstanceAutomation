package sew.ai.steps.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import sew.ai.config.Configuration;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.pageObjects.csp.AdminHomePage;
import sew.ai.pageObjects.csp.AdminNotificationPage;
import sew.ai.pageObjects.csp.OutagePage;
import sew.ai.pageObjects.csp.WaysToSavePage;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.PropertiesUtil;
import sew.ai.utils.RandomUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminHomeSteps extends AdminHomePage {
    private static final Logger log = LogManager.getLogger(AdminHomeSteps.class);

    public static PropertiesUtil adminHomeTextProp;

    public AdminHomeSteps(WebDriver driver) {
        super(driver);
        adminHomeTextProp = new PropertiesUtil(FilePaths.CSP_TEXT_PROPERTIES + Constants.ADMIN_HOME_TXT_FILENAME);
    }

    public void SignOutAdminApplication() {
        clickDropdownAdminProfile();
        waitForLnkSignOutToBeVisible();
        clickLnkSignOut();
        waitForPageLoaderInvisibility();
    }

    public void verifyAdminWelcomeMessage(SoftAssert softAssert) {
        String actualWelcomeMessage = getWelcomeMessage().replace("\n", "").toUpperCase();
        String expectedWelcomeMessage = adminHomeTextProp.getPropValue("lblTxtWelcomeUsername").toUpperCase() + Configuration.toString("adminUsername").toUpperCase();
        softAssert.assertEquals(actualWelcomeMessage, expectedWelcomeMessage,
                "Welcome Message displayed on Admin Home Page is not as Expected.");

    }
    
    public void navigateCSRWorkBenchWidget() {
        try {
        	waitForLnkCSRWorkBench();
        	//pageUtil.expWaitForElePresence(adminhomePage.getLnkCSRWorkBench());
            try {
            	clickLnkCSRWorkBench();
				//pageUtil.click(adminhomePage.getLnkCSRWorkBench());
			} catch (Exception e) {
				e.printStackTrace();
			}
            waitForPageLoaderInvisibility();
            //pageUtil.expWaitForEleInvisibility(adminCsrPage.getDivPageLoaderBsp());
            //pageUtil.waitForPageToLoad();
            //pageUtil.pause(3000);
            //pageUtil.expWaitForEleInvisibility(adminCsrPage.getImgLoadingSpinner());
            //pageUtil.verifyCurrentPageTitle(Utils.getRbTextValue("expectedAdminCSRPageTitle"));
           // pageUtil.assertCurrentPageUrl(Utils.getRbTextValue("expectedAdminCSRPageUrl"));
            //Runner.test.log(Status.PASS, "User navigates to CSR Workbench page");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("The element CSR Workbench is not visible ");

        }
    }

    public void navigateChangePasswordPopup() {
        waitForLnkDropdownAdminProfileToBeVisible();
        clickDropdownAdminProfile();
        waitForLnkChangePasswordToBeVisible();
        clickLnkChangePassword();
        waitForSubmitBtnChangePasswordToBeVisible();
    }

    public void verifyChangePasswordFieldValidations(SoftAssert softAssert) {
        String actToastMsg;
        String newPassword = Configuration.toString("adminPassword") + RandomUtil.generateRandomString(2, RandomUtil.Mode.NUMERIC);
        System.out.println("The New Password for the Admin User is " + newPassword);
        //To Verify the Validation message for all mandatory fields.
        clickBtnSubmitChangePassword();
        actToastMsg = getToastMessageWithoutWait();
        softAssert.assertEquals(actToastMsg, adminHomeTextProp.getPropValue("txtAlertMsgAllBlankField"),
                "All Fields Mandatory Error Message is not as Expected.");
        waitForToastMessageInvisibility();
        //To Verify the Validation message for Existing Password
        clearExistingPasswordField();
        populateNewPassword(newPassword);
        populateConfirmPassword(newPassword);
        clickBtnSubmitChangePassword();
        actToastMsg = getToastMessageWithoutWait();
        softAssert.assertEquals(actToastMsg, adminHomeTextProp.getPropValue("lblTxtAlertExistingPwd"),
                "Existing Password Field Validation message is not as Expected.");
        waitForToastMessageInvisibility();
        //To Verify the Validation message for Wrong Existing Password
        clearExistingPasswordField();
        populateExistingPassword("demo1");
        populateNewPassword(newPassword);
        populateConfirmPassword(newPassword);
        clickBtnSubmitChangePassword();
        actToastMsg = getToastMessageWithoutWait();
        softAssert.assertEquals(actToastMsg, adminHomeTextProp.getPropValue("lblTxtToastExistPassWrong"),
                "Wrong Existing Password Validation message is not as Expected.");
        waitForToastMessageInvisibility();
        //To Verify the Validation message for New Password
        clearExistingPasswordField();
        populateExistingPassword(Configuration.toString("adminPassword"));
        clearNewPasswordField();
        populateConfirmPassword(newPassword);
        clickBtnSubmitChangePassword();
        actToastMsg = getToastMessageWithoutWait();
        softAssert.assertEquals(actToastMsg, adminHomeTextProp.getPropValue("lblTxtAlertNewPassword"),
                "New Password Field Validation message is not as Expected.");
        waitForToastMessageInvisibility();
        //To Verify the Validation message for Confirm Password
        populateNewPassword(newPassword);
        clearConfirmPasswordField();
        clickBtnSubmitChangePassword();
        actToastMsg = getToastMessageWithoutWait();
        softAssert.assertEquals(actToastMsg, adminHomeTextProp.getPropValue("lblTxtALertConfirmPwd"),
                "Confirm Password Field Validation message is not as Expected.");
        waitForToastMessageInvisibility();
        //To Verify the Validation message for New/Confirm password not matching.
        populateConfirmPassword(newPassword + RandomUtil.generateRandomString(2, RandomUtil.Mode.NUMERIC));
        clickBtnSubmitChangePassword();
        clickBtnSubmitChangePassword();
        actToastMsg = getToastMessageWithoutWait();
        softAssert.assertEquals(actToastMsg, adminHomeTextProp.getPropValue("lblTxtToastMismatchNewAndConfirmPasswd"),
                "New or Confirm Password not matching validation message is not as Expected.");
        waitForToastMessageInvisibility();
        //To Verify the New/Confirm password not meeting the password requirements.
        clearNewPasswordField();
        clearExistingPasswordField();
        populateExistingPassword(Configuration.toString("adminPassword"));
        populateNewPassword("demo12");
        populateConfirmPassword("demo12");
        clickBtnSubmitChangePassword();
        actToastMsg = getToastMessageWithoutWait();
        softAssert.assertEquals(actToastMsg, adminHomeTextProp.getPropValue("txtAlertMsgPassCriteriaNotMet"),
                "Validation message for New or Confirm Password not meeting the password requirements is not as Expected.");
        waitForToastMessageInvisibility();


    }
    
    public String getAdminUserPasswordFromDB(String userName) {
        String password;
        try {
            ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getAdminUserDetailsWithUsername(userName));
            rs.next();
            password = rs.getString("Password");
            System.out.println("The Previous Admin Password is " + password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return password;
    }

    public String changePassword(SoftAssert softAssert, String defaultPassword) {
        

            String newPassword = defaultPassword + RandomUtil.generateRandomString(2, RandomUtil.Mode.ALPHANUMERIC);
            System.out.println("The New Password for the Admin User is " + newPassword);
            clearExistingPasswordField();
            clearNewPasswordField();
            clearConfirmPasswordField();
            populateExistingPassword(defaultPassword);
            populateNewPassword(newPassword);
            populateConfirmPassword(newPassword);
            clickBtnSubmitChangePassword();
            String actToastMsg = getToastMessageWithoutWait();
            softAssert.assertEquals(actToastMsg, adminHomeTextProp.getPropValue("lblTxtToastPasswdSucessChange"),
                    "Password change success message for 1st Time is not as Expected.");
            waitForToastMessageInvisibility();
            return newPassword;

    }

    public void setAdminUserPasswordToGivenPassword(String userName, String password) {
        try {
            String query = SQLQueries.updateAdminUserPasswordWithGivenPassword(userName, password);
            DataBaseUtils.executeUpdateDeleteSQLQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navigateToNotifications() {
        AdminNotificationPage adminNotificationPage;
        adminNotificationPage = new AdminNotificationPage(driver);
        waitForLnkNotificationToBeVisible();
        clickLnkNotification();
        waitForPageLoaderInvisibility();
        waitForImgLoadingInvisibility();
        adminNotificationPage.waitForNextButtonToBeVisibile();
    }

    public void navigateToWaysToSave() {
        WaysToSavePage waysToSavePage;
        waysToSavePage = new WaysToSavePage(driver);
        waitForLnkWaysToSaveToBeVisible();
        clickLnkWaysToSave();
        waitForPageLoaderInvisibility();
        waysToSavePage.waitForBtnNextPaginationToBeVisible();
    }

    public void navigateToOutages() {
        OutagePage outagePage;
        outagePage = new OutagePage(driver);
        waitForLnkOutagesToBeVisible();
        clickLnkOutages();
        waitForPageLoaderInvisibility();
        waitForImgLoadingInvisibility();
        outagePage.waitForBtnNextOutageToBeVisible();
    }


}

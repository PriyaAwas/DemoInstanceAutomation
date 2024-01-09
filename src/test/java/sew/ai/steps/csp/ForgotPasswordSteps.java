package sew.ai.steps.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import sew.ai.driver.DriverFactory;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.pageObjects.csp.AdminLoginPage;
import sew.ai.pageObjects.csp.ForgotPasswordPage;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.PropertiesUtil;
import sew.ai.utils.RandomUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ForgotPasswordSteps extends ForgotPasswordPage {
    private static final Logger log = LogManager.getLogger(ForgotPasswordSteps.class);
    private AdminLoginPage adminLoginPage;
    public static PropertiesUtil forgotPasswordTextProp;
    public static PropertiesUtil adminLoginTextProp;


    public ForgotPasswordSteps(WebDriver driver) {
        super(driver);
        forgotPasswordTextProp = new PropertiesUtil(FilePaths.CSP_TEXT_PROPERTIES + Constants.ADMIN_FORGOT_PASSWORD_TXT_FILENAME);
        adminLoginTextProp = new PropertiesUtil(FilePaths.CSP_TEXT_PROPERTIES + Constants.ADMIN_LOGIN_TXT_FILENAME);


    }

    public String changeAdminPassword(SoftAssert softAssert) {
        adminLoginPage = new AdminLoginPage(driver);
        adminLoginPage.clickLnkForgetPassword();
        waitForPageLoaderInvisibility();
        Assert.assertTrue(isForgotPasswordPage(forgotPasswordTextProp.getPropValue("forgotPasswordPageUrl"),
                forgotPasswordTextProp.getPropValue("forgotPasswordPageTitle")));
        waitForBtnSubmitToBeVisible();
        clearEmailAddressTxtBox();
        populateEmailAddressTxtBox("krishna.prasath@smartenergywater.in");
        clickBtnSubmit();
        String actToastMsg = getToastMessageWithoutWait();
        softAssert.assertEquals(actToastMsg, forgotPasswordTextProp.getPropValue("txtLblAlertResetPasswordMail"),
                "Forgot Password Success message is not as Expected.");
        String url = getResetPassWordURLFromDB("krishna.prasath@smartenergywater.in");
//        System.out.println("The ResetPassword URL is "+url);
        DriverFactory.navigateToPage(url);
        waitForPageLoaderInvisibility();
        String newPasswd = RandomUtil.generateRandomString(10, RandomUtil.Mode.ALPHANUMERIC) + "!" + "1";
//        System.out.println("The New Password resetted is "+newPasswd);
        populateNewPasswordTxtBox(newPasswd);
        populateConfirmPasswordTxtBox(newPasswd);
        clickBtnSubmitResetPasswd();
        explicitWaitForAlertPopUp(driver);
        if (isAlertPresent()) {
            verifyAlertMessage(forgotPasswordTextProp.getPropValue("txtLblAlertPasswdChange"));
            acceptAlert();
        }
        adminLoginPage.waitForPasswordPasswordLinkToBeVisible();
        return newPasswd;
    }

    public String getResetPassWordURLFromDB(String emailId) {

        String msgBody = null;
        try {
            ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getResetPasswordLinkAdmin(emailId));
            rs.next();
            msgBody = rs.getString("Message");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sStartingString = "https://";
        String sEndingString = "'>Click here</a> to initiate";
        String getUrl = msgBody.substring(msgBody.indexOf(sStartingString),
                msgBody.indexOf(sEndingString));
        return getUrl;
    }


}

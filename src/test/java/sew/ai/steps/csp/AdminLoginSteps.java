package sew.ai.steps.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import sew.ai.config.Configuration;
import sew.ai.driver.DriverFactory;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.pageObjects.csp.AdminHomePage;
import sew.ai.pageObjects.csp.AdminLoginPage;
import sew.ai.steps.scp.LoginSteps;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.PropertiesUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoginSteps extends AdminLoginPage {
    private static final Logger log = LogManager.getLogger(LoginSteps.class);
    public static PropertiesUtil adminLoginTextProp;
    public static PropertiesUtil adminHomeTextProp;


    public AdminLoginSteps(WebDriver driver) {
        super(driver);
        adminLoginTextProp = new PropertiesUtil(FilePaths.CSP_TEXT_PROPERTIES + Constants.ADMIN_LOGIN_TXT_FILENAME);
        adminHomeTextProp = new PropertiesUtil(FilePaths.CSP_TEXT_PROPERTIES + Constants.ADMIN_HOME_TXT_FILENAME);
    }

    public void loginIntoAdminApplication(String username, String password) {
        DriverFactory.goToPage(Configuration.toString("csp.url"));
        AdminHomePage adminHomePage;
        waitForPageLoaderInvisibility();
        Assert.assertTrue(isAdminLoginPage(adminLoginTextProp.getPropValue("adminPortalURL"), adminLoginTextProp
                .getPropValue("adminPortalLoginPageTitle")));
        populateUserName(username);
        populatePassword(password);
        clickBtnSignIn();
        waitForPageLoaderInvisibility();
        adminHomePage = new AdminHomePage(driver);
        Assert.assertTrue(adminHomePage.isAdminHomePage(adminHomeTextProp.getPropValue("adminHomePageUrl"), adminHomeTextProp
                .getPropValue("adminHomePageTitle")));
    }

    public String adminLoginWithInvalidCreds() {
        populateUserName("Invalid@Username");
        populatePassword("Invalid@Password");
        clickBtnSignIn();
        waitForPageLoaderInvisibility();
        String lblErrorMsgInvalidUserNamePassWord = getLblErrorMsgInvalidUserNamePassWord();
        return lblErrorMsgInvalidUserNamePassWord;
    }

    public void loginIntoAdminApplicationWithRememberMeCheckbox(String username, String password) {
        AdminHomePage adminHomePage;
        Assert.assertTrue(isAdminLoginPage(adminLoginTextProp.getPropValue("adminPortalURL"), adminLoginTextProp
                .getPropValue("adminPortalLoginPageTitle")));
        populateUserName(username);
        populatePassword(password);
        checkRememberMeChkBox();
        clickBtnSignIn();
        waitForPageLoaderInvisibility();
        adminHomePage = new AdminHomePage(driver);
        Assert.assertTrue(adminHomePage.isAdminHomePage(adminHomeTextProp.getPropValue("adminHomePageUrl"), adminHomeTextProp
                .getPropValue("adminHomePageTitle")));
    }

    public String verifyUsernameRememberCheckBoxAdminLogin() {
        AdminHomePage adminHomePage = new AdminHomePage(driver);
        adminHomePage.clickDropdownAdminProfile();
        adminHomePage.waitForLnkSignOutToBeVisible();
        adminHomePage.clickLnkSignOut();
        waitForPageLoaderInvisibility();
        return getTextUserName();
    }

    public void verifyCSPLoginPageElements(SoftAssert softAssert) {
        softAssert.assertEquals(getUsernameFieldLabel(), adminLoginTextProp.getPropValue("txtLblUsername"),
                "Username Field Label is not as Expected on CSP Login Page.");
        softAssert.assertTrue(isUsernameTextBoxVisible(), "Username TextBox is not visible on CSP Login Page.");
        softAssert.assertEquals(getPasswordFieldLabel(), adminLoginTextProp.getPropValue("txtLblPassword"),
                "Password Field Label is not as Expected on CSP Login Page.");
        softAssert.assertTrue(isPasswordTextBoxVisible(), "Password TextBox is not visible on CSP Login Page.");
        softAssert.assertEquals(getRememberMeLabel(), adminLoginTextProp.getPropValue("txtLblRememberMe"),
                "Remember Me Label is not as Expected on CSP Login Page.");
        softAssert.assertTrue(isRememberMeChbVisible(), "RememberMe CheckBox is not visible on CSP Login Page.");
        softAssert.assertEquals(getSignInButtonLabel(), adminLoginTextProp.getPropValue("txtLblSignIn"),
                "SignIn Button Label is not as Expected on CSP Login Page.");
        softAssert.assertTrue(isBtnSignInVisible(), "SignIn Button is not visible on CSP Login Page.");
        softAssert.assertTrue(isLnkForgotPasswordVisible(), "Forgot Password Link is not visible on CSP Login Page.");
        softAssert.assertEquals(getLnkForgotPasswordLabel(), adminLoginTextProp.getPropValue("txtLblForgotPassword"),
                "Forgot Password Field Label is not as Expected on CSP Login Page.");

        softAssert.assertTrue(isCSPLogoVisible(), "CSP Logo is not visible on CSP Login Page.");

        softAssert.assertTrue(istxtMsgCSPHeadingVisible(), "New to CSP Heading is not visible on CSP Login Page.");
        softAssert.assertEquals(getNewCSPHeadingLabel(), adminLoginTextProp.getPropValue("txtMsgNewCSPHeading"),
                "New to CSP Heading Label is not as Expected on CSP Login Page.");
        softAssert.assertTrue(istxtMsgNewCSPDescriptionVisible(), "New to CSP Description is not visible on CSP Login Page.");
//        softAssert.assertEquals(getNewCSPDescriptionLabel(), adminLoginTextProp.getPropValue("txtMsgNewCSPDescription"),
//                "New to CSP Description Label is not as Expected on CSP Login Page.");
        //TODO remove this Harcoded text once the encoding issue resolves.
        softAssert.assertEquals(getNewCSPDescriptionLabel(), "With the Customer Service Portal, you will be able to respond to your customer’s queries, view dashboard and reports, manage content for the CX platform, run campaigns, monitor customer behavior, and derive intelligent insights powered by AI and ML analytics.",
                "New to CSP Description Label is not as Expected on CSP Login Page.");
        softAssert.assertTrue(istxt24By7CustomerSupportVisible(), "24By7 Customer Support Text is not visible on CSP Login Page.");
        softAssert.assertEquals(get24By7CustomerSupportLabel(), adminLoginTextProp.getPropValue("txtMsgCustomerSupport"),
                "24By7 Customer Support Label is not as Expected on CSP Login Page.");

        softAssert.assertTrue(isTxtSimplifiedAccessCustomerVisible(), "Simplified Access text is not visible on CSP Login Page.");
        softAssert.assertEquals(getSimplifiedAccessCustomerLabel(), adminLoginTextProp.getPropValue("txtMsgSimplifiedAccessCustomer"),
                "Simplified Access Label is not as Expected on CSP Login Page.");
        softAssert.assertTrue(isTxtQuickResolutionVisible(), "Quick Resolution text is not visible on CSP Login Page.");
        softAssert.assertEquals(getTxtQuickResolutionLabel(), adminLoginTextProp.getPropValue("txtMsgQuickResolution"),
                "Quick Resolution Label is not as Expected on CSP Login Page.");
        softAssert.assertTrue(isTxtCustomerEngagementVisible(), "Customer Engagement text is not visible on CSP Login Page.");
        softAssert.assertEquals(getTxtCustomerEngagementLabel(), adminLoginTextProp.getPropValue("txtMsgCustomerEngagement"),
                "Customer Engagement Label is not as Expected on CSP Login Page.");

    }

    public void verifyAlertMessageBlankLoginFields(SoftAssert softAssert) {

        //Verify the Alert Message without entering both username and password
        clickBtnSignIn();
        softAssert.assertEquals(getValidationMessageUsername(), adminLoginTextProp.getPropValue("txtAlertMsgUsername"),
                "Username Field Blank validation message is not as Expected on Login page.");
        softAssert.assertEquals(getValidationMessagePassword(), adminLoginTextProp.getPropValue("txtAlertMsgPassword"),
                "Password Field Blank validation message is not as Expected on Login page.");

        //Enter the Password field and verify validation message for Username field
        DriverFactory.refreshBrowser();
        waitForPageLoaderInvisibility();
        clearUsernameField();
        populatePassword("DemoPassword123");
        clickBtnSignIn();
        waitForUsernameValidationMsgToBeVisible();
        softAssert.assertEquals(getValidationMessageUsername(), adminLoginTextProp.getPropValue("txtAlertMsgUsername"),
                "Username Field Blank validation message is not as Expected on Login page.");

        //Enter the Username field and verify validation message for Password field
        DriverFactory.refreshBrowser();
        waitForPageLoaderInvisibility();
        clearPasswordField();
        populateUserName("DemoUsername");
        clickBtnSignIn();
        waitForPasswordValidationMsgToBeVisible();
        softAssert.assertEquals(getValidationMessagePassword(), adminLoginTextProp.getPropValue("txtAlertMsgPassword"),
                "Password Field Blank validation message is not as Expected on Login page.");


    }

    public void verifyPreLoginFooterObjects(SoftAssert softAssert) {

        //App Version
        softAssert.assertTrue(isAppVersionlabelVisible(), "App Version Label is not visible on the PreLogin page.");
        softAssert.assertEquals(getAppVersionLabel(), adminLoginTextProp.getPropValue("txtLblAppVersion"),
                "App Version Label text is not as Expected on the PreLogin page.");

        //Copy Rights Icon
        softAssert.assertTrue(isPreLoginCopyRightIconVisible(), "CopyRight Icon is not visible on the PreLogin page.");
//        softAssert.assertEquals(getPreLoginCopyRightIconLabel(), adminLoginTextProp.getPropValue("txtCopyRightIcon"),
//                "CopyRight Icon symbol is not as Expected on the PreLogin page.");
        System.out.println("The Prelogin Copyright symbols is "+getPreLoginCopyRightIconLabel());
        softAssert.assertEquals(getPreLoginCopyRightIconLabel(), "©",//TODO Remove this Copyright symbol Hardcoded once it resolves.
                "CopyRight Icon symbol is not as Expected on the PreLogin page.");

        //Copy Rights Label
        softAssert.assertTrue(isCopyRightsReservedLblVisible(), "CopyRight Label is not visible on the PreLogin page.");
        softAssert.assertTrue(getCopyRightsReservedLabel().contains(adminLoginTextProp.getPropValue("txtLblCopyRights")),
                "CopyRight Label Text is not as Expected on the PreLogin page.");

        //Footer Logo
        softAssert.assertTrue(isFooterLogoVisible(), "Footer Logo is not visible on the PreLogin page.");
        softAssert.assertEquals(getFooterLogoLabel(), adminLoginTextProp.getPropValue("txtLblLogo"),
                "CopyRights Label Text is not as Expected on the PreLogin page.");


    }

    public void verifyPostLoginFooterObjects(SoftAssert softAssert) {

        //App Version
        softAssert.assertTrue(isAppVersionlabelVisible(), "App Version Label is not visible on the PostLogin page.");
        softAssert.assertEquals(getAppVersionLabel(), adminLoginTextProp.getPropValue("txtLblAppVersion"),
                "App Version Label text is not as Expected on the PostLogin page.");

        //Copy Rights Icon
        softAssert.assertTrue(isPostLoginCopyRightIconVisible(), "CopyRight Icon is not visible on the PostLogin page.");
//        softAssert.assertEquals(getPostLoginCopyRightIconLabel(), adminLoginTextProp.getPropValue("txtCopyRightIcon"),
//                "CopyRight Icon symbol is not as Expected on the PostLogin page.");
        softAssert.assertEquals(getPostLoginCopyRightIconLabel(), "©",//TODO Remove this Copyright symbol Hardcoded once it resolves.
                "CopyRight Icon symbol is not as Expected on the PostLogin page.");

        //Copy Rights Label
        softAssert.assertTrue(isCopyRightsReservedLblVisible(), "CopyRight Label is not visible on the PostLogin page.");
        softAssert.assertTrue(getCopyRightsReservedLabel().contains(adminLoginTextProp.getPropValue("txtLblCopyRights")),
                "CopyRight Label Text is not as Expected on the PostLogin page.");

        //Footer Logo
        softAssert.assertTrue(isFooterLogoVisible(), "Footer Logo is not visible on the PostLogin page.");
        softAssert.assertEquals(getFooterLogoLabel(), adminLoginTextProp.getPropValue("txtLblLogo"),
                "CopyRights Label Text is not as Expected on the PostLogin page.");


    }
    
    public void verifyUnlockingUserIP() throws Exception {
		ExtentLogger.logInfo("Test Case execution for - verifyUnlockingUserIP- is Initiated");
		ExtentLogger.logInfo("C72452,C84069,C84070-Unlock a IP from CSR workbench");
//		int iLoginIpBlock = Integer.parseInt(loginIpBlock); 
		int iLoginIpBlock = Integer.parseInt(DataBaseUtils.getUtilitySettingDetails().get("LoginIPLockAttempt"));
		DataBaseUtils.callStoredProcedureUnblockAccountIp();
		
		for (int i = 0; i <= iLoginIpBlock; i++) {
			populateUserName("djjdnsjsss");
	        populatePassword("Inyguhid");
	        clickBtnSignIn();
	        waitForPageLoaderInvisibility();
	        String lblErrorMsgInvalidUserNamePassWord = getLblErrorMsgInvalidUserNamePassWord();
			if (lblErrorMsgInvalidUserNamePassWord.contains(adminLoginTextProp.getPropValue("txtErrMsgUnsuccessfulAttemptsLoginLgp"))) {
				break;
			}
		}
		// DB validation for the IP Lock check
		// EnvironmentUtil.getPublicIP();
		String IP = "40.83.171.213";
		// String IP=EnvironmentUtil.getSystemIp();
		Assert.assertEquals(getUserIsArchiveStatus(IP), "0");// 0-LockedIP
		//pageUtil.pause(10000);
		AdminCustomerSteps adminCustomerSteps= new AdminCustomerSteps(driver);
		adminCustomerSteps.checkRemoveIpLock(IP);
		//pageUtil.pause(2000);
		//pageUtil.verifyTextPresentInPage("No data found");
		// DB validation for Unlocking the IP of the user
		Assert.assertEquals(getUserIsArchiveStatus(IP), "1"); //1-UnlockedIP
		ExtentLogger.logInfo("Test Case execution for - verifyUnlockingUserIP - is finished");
	}
    
    public static String getUserIsArchiveStatus(String userIP) throws Exception {
		String iUserDetailsField = null;
		ResultSet rs;
		try {
			rs = DataBaseUtils.getResultSet(SQLQueries.getUserIsArchiveStatus(userIP));
			rs.next();
			iUserDetailsField = rs.getString("IsArchive");
		} catch (SQLException e) {
			e.printStackTrace();
	
		}
		return iUserDetailsField;
	}


}

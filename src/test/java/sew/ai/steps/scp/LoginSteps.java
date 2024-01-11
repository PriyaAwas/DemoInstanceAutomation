package sew.ai.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import sew.ai.config.CSPConfiguration;
import sew.ai.config.Configuration;
import sew.ai.config.SCPConfiguration;
import sew.ai.driver.DriverFactory;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.pageObjects.scp.LoginPage;
import sew.ai.utils.PropertiesUtil;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import static sew.ai.steps.scp.DashboardSteps.dashboardTextProp;

public class LoginSteps extends LoginPage {
    private static final Logger log = LogManager.getLogger(LoginSteps.class);
    public static PropertiesUtil loginTextProp;

    public LoginSteps(WebDriver driver) {
        super(driver);
        loginTextProp = new PropertiesUtil(
                FilePaths.SCP_TEXT_PROPERTIES + Constants.LOGIN_TXT_FILENAME
        );
    }

    public void populateLoginForm(String userName, String password) {
        populateUserName(userName);
        populatePassword(password);
    }

    public String loginWithBlankCreds() {
        clickSignInBtn();
        String errMsg = getToastMessageWithoutWait();
        clickToastCloseBtn();
        return errMsg;
    }

    public String loginWithBlankUsername(String password) {
        clearUsernameField();
        populatePassword(password);
        clickSignInBtn();
        String errMsg = getCommonValidationMsg();
        return errMsg;
    }

    public String loginWithWrongUsername(String userName, String password) {
        populateLoginForm(userName, password);
        clearUsernameField();
        populateUserName("invalid@username.com");
        clickSignInBtn();
        String errMsg = getToastMessage();
        return errMsg;
    }

    public String loginWithWrongPassword(String userName, String password) {
        populateLoginForm(userName, password);
        clearPasswordField();
        populatePassword("Invalid@Pass123");
        clickSignInBtn();
        String errMsg = getToastMessage();
        return errMsg;
    }

    public String loginWithInvalidCreds() {
        populateUserName("Invalid@Username");
        populatePassword("Invalid@Password");
        clickSignInBtn();
        String errMsg = getToastMessage();
        return errMsg;
    }

    public String loginWithBlankPassword(String userName) {
        populateUserName(userName);
        clearPasswordField();
        clickSignInBtn();
        String errMsg = getCommonValidationMsg();
        return errMsg;
    }

    public String loginWithDeactiveUser(String userName, String password) {
        populateLoginForm(userName, password);
        clickSignInBtn();
        String errMsg = getToastMessage();
        return errMsg;
    }

    public DashboardSteps loginIntoTheApplication(String userName, String password) {
        ExtentLogger.logInfo("Logging into the application.");
        DashboardSteps dashboardSteps;
//        Assert.assertTrue(isLoginPage(loginTextProp.getPropValue("loginPageUrl"), loginTextProp
//                .getPropValue("loginPageTitle")));
        // Handle MFA if enabled
        //SCPConfiguration.initLoginToken();
        waitForPageToLoad();
        populateLoginForm(userName, password);
        clickSignInBtn();
        waitForPageLoader();
        dashboardSteps = new DashboardSteps(driver);
        ExtentLogger.logInfo("Dashboard page url : " + dashboardTextProp.getPropValue("dashboardPageUrl"));
        ExtentLogger.logInfo("Dashboard page title : " + dashboardTextProp.getPropValue("dashboardPageHeader"));
        Assert.assertTrue(dashboardSteps.isDashboardPage(
                        dashboardTextProp.getPropValue("dashboardPageUrl"),
                        dashboardTextProp.getPropValue("dashboardPageHeader")),
                "Not navigated to dashboard page."
        );
        ExtentLogger.logPass("Logged into the application successfully.");
        return dashboardSteps;
    }

    public void verifyTheLoginPageObject(SoftAssert softAssert) {
        Assert.assertTrue(isLoginPage(loginTextProp.getPropValue("loginPageUrl"), loginTextProp
                .getPropValue("loginPageTitle")));
        softAssert.assertTrue(isCompanyLogoVisible(), "Company logo is not present.");
        softAssert.assertTrue(isLanguageDropdownVisible(), "Language dropdown option is not visible.");
        softAssert.assertEquals(getLanguageDropdownSelectedOption(), loginTextProp.getPropValue("optionInLanguageDropdown"),
                "Option selected in the language drop-down not matched.");
        softAssert.assertEquals(getLoginPageHeader(), loginTextProp.getPropValue("loginPageHeader"),
                "Login page header not matched.");
        softAssert.assertTrue(isUsernameTxtVisible(), "Username text field is not visible.");
        softAssert.assertEquals(getUsernameLabel(), loginTextProp.getPropValue("lblUsername"),
                "Label for username is not matched.");
        softAssert.assertTrue(isPasswordTxtVisible(), "Password text field is not visible.");
        softAssert.assertEquals(getPasswordLabel(), loginTextProp.getPropValue("lblPassword"),
                "Label for password is not matched.");
        softAssert.assertTrue(isRememberMeChbVisible(), "Remember me check box is not visible.");
        softAssert.assertEquals(getSignInBtnLabel(), loginTextProp.getPropValue("lblSignInBtn"),
                "Label for sign in button is not matched.");
        softAssert.assertEquals(getForgotUsernameLabel(), loginTextProp.getPropValue("lblForgotUsername"),
                "Label for forgot username is not matched.");
        softAssert.assertEquals(getForgotPasswordLabel(), loginTextProp.getPropValue("lblForgotPassword"),
                "Label for forgot password is not matched.");
        softAssert.assertEquals(getProblemSigningInLabel(), loginTextProp.getPropValue("lblProblemSignIn"),
                "Label for problem sign in is not matched.");
        softAssert.assertEquals(getRegisterLinkLabel(), loginTextProp.getPropValue("lblRegisterLnk"),
                "Label for register link is not matched.");
    }

    public String verifyLanguageSwitchObjects(SoftAssert softAssert) {
        Assert.assertTrue(isLanguageDropdownVisible(),
                "Language dropdown is not visible on the login page.");
        List<String> languageName = new ArrayList<>();
        List<WebElement> languageOptions = getLanguageDropdownOptions();
        for (int i = 0; i < languageOptions.size(); i++) {
            languageName.add(getAttribute(languageOptions.get(i), "innerText").toLowerCase());
        }
        ListIterator<String> iterator = CSPConfiguration.languageConfig.listIterator();
        while (iterator.hasNext()) {
            iterator.set(iterator.next().toLowerCase());
        }
        Collections.sort(languageName);
        Collections.sort(CSPConfiguration.languageConfig);
        softAssert.assertEquals(languageName, CSPConfiguration.languageConfig);
        softAssert.assertTrue(isDefaultLanguageVisible(), "Default language is not visible.");
        return getDefaultLanguage();
    }

    public void verifyLanguageSwitchFeature(SoftAssert softAssert, String defaultLanguage) {
        Assert.assertTrue(isLanguageDropdownVisible(),
                "Language dropdown is not visible on the login page.");
        List<String> languageName = new ArrayList<>();
        List<WebElement> languageOptions = getLanguageDropdownOptions();
        for (int i = 0; i < languageOptions.size(); i++) {
            languageName.add(getAttribute(languageOptions.get(i), "innerText").toLowerCase());
        }
        if (CSPConfiguration.languageConfig.size() > 1 && defaultLanguage
                .equalsIgnoreCase("English")) {
            softAssert.assertEquals(getRegisterLinkLabel(), loginTextProp
                    .getPropValue("txLblRegistrationEnglish"));
            if (languageName.contains("Español")) {
                softAssert.assertTrue(isLanguageDropdownVisible());
                clickLanguageDropdown();
                pause(500);
                clickSpanishLanguageOption();
                pause(1500);
                softAssert.assertEquals(getRegisterLinkLabel(), loginTextProp.getPropValue("txLblRegistrationEspanol"));
            }
            if (languageName.contains("Français")) {
                softAssert.assertTrue(isLanguageDropdownVisible());
                clickLanguageDropdown();
                pause(500);
                clickFrenchLanguageOption();
                pause(1500);
                softAssert.assertEquals(getRegisterLinkLabel(), loginTextProp.getPropValue("txLblRegistrationFrance"));
            }
        }
        if (CSPConfiguration.languageConfig.size() > 1 && defaultLanguage
                .equalsIgnoreCase("Español")) {
            softAssert.assertEquals(getRegisterLinkLabel(), loginTextProp
                    .getPropValue("txLblRegistrationEnglish"));
            if (languageName.contains("English")) {
                softAssert.assertTrue(isLanguageDropdownVisible());
                clickLanguageDropdown();
                pause(500);
                clickEnglishLanguageOption();
                pause(1500);
                softAssert.assertEquals(getRegisterLinkLabel(), loginTextProp.getPropValue("txLblRegistrationEnglish"));
            }
            if (languageName.contains("Français")) {
                softAssert.assertTrue(isLanguageDropdownVisible());
                clickLanguageDropdown();
                pause(500);
                clickFrenchLanguageOption();
                pause(1500);
                softAssert.assertEquals(getRegisterLinkLabel(), loginTextProp.getPropValue("txLblRegistrationFrance"));
            }
        }
        if (CSPConfiguration.languageConfig.size() > 1 && defaultLanguage
                .equalsIgnoreCase("Français")) {
            softAssert.assertEquals(getRegisterLinkLabel(), loginTextProp
                    .getPropValue("txLblRegistrationEnglish"));
            if (languageName.contains("Español")) {
                softAssert.assertTrue(isLanguageDropdownVisible());
                clickLanguageDropdown();
                pause(500);
                clickSpanishLanguageOption();
                pause(1500);
                softAssert.assertEquals(getRegisterLinkLabel(), loginTextProp.getPropValue("txLblRegistrationEspanol"));
            }
            if (languageName.contains("English")) {
                softAssert.assertTrue(isLanguageDropdownVisible());
                clickLanguageDropdown();
                pause(500);
                clickEnglishLanguageOption();
                pause(1500);
                softAssert.assertEquals(getRegisterLinkLabel(), loginTextProp.getPropValue("txLblRegistrationEnglish"));
            }
        }
        DriverFactory.deleteAllCookies();
        DriverFactory.goToPage(Configuration.toString("scp.url"));
        if (defaultLanguage.equalsIgnoreCase("English")) {
            softAssert.assertEquals(getRegisterLinkLabel(), loginTextProp
                    .getPropValue("txLblRegistrationEnglish"));
        }
        else if (defaultLanguage.equalsIgnoreCase("Español")) {
            softAssert.assertEquals(getRegisterLinkLabel(), loginTextProp
                    .getPropValue("txLblRegistrationEspanol"));
        }
        else if (defaultLanguage.equalsIgnoreCase("Français")) {
            softAssert.assertEquals(getRegisterLinkLabel(), loginTextProp
                    .getPropValue("txLblRegistrationFrance"));
        }
    }

    public DashboardSteps loginIntoTheAppByCheckingRememberMe(String userName, String password) {
        DashboardSteps dashboardSteps;
        SignOutSteps signOutSteps;
        Assert.assertTrue(isLoginPage(loginTextProp.getPropValue("loginPageUrl"), loginTextProp
                .getPropValue("loginPageTitle")));
        populateLoginForm(userName, password);
        checkRememberMe();
        clickSignInBtn();
        waitForPageLoader();
        dashboardSteps = new DashboardSteps(driver);
        Assert.assertTrue(dashboardSteps.isDashboardPage(dashboardTextProp.getPropValue("dashboardPageUrl"),
                dashboardTextProp.getPropValue("dashboardPageHeader")));
        dashboardSteps.waitForImageProfileIcon();
        return dashboardSteps;
    }

    /**
     * This method verify the username field value and remember me checkbox
     * field status.
     *
     * @param dashboardSteps
     * @param userName
     * @return
     */
    public Boolean verifyUsernameAndRememberMeCheckboxStatus(DashboardSteps dashboardSteps,
                                                             String userName) {
        SignOutSteps signOutSteps;
        dashboardSteps.clickImageProfileIco();
        dashboardSteps.bypassTheAboutMyHomePage();
        dashboardSteps.clickSignOutLnk();
        signOutSteps = new SignOutSteps(driver);
        signOutSteps.waitForSignOutSuccessLbl();
        signOutSteps.clickSignInAgainBtn();
        waitForUserNameFieldVisibility();
        Assert.assertEquals(getPopulatedUsernameValue(), userName,
                "Username is not auto populating on checking remember me.");
        return isRememberMeChbChecked();
    }

    /**
     * This method returns the toast message for the account and IP lock
     * functionality.
     *
     * @param softAssert soft assert all the verification.
     * @param attempt    the count configured in CSP for account and IP block.
     * @return returns the toast message for account and IP block.
     */
    public String verifyAccountIPLockFunctionality(SoftAssert softAssert, int attempt) {
        for (int i = 0; i < attempt; i++) {
            pause(500);
            populateLoginForm(Configuration.toString("userName"), "invalid@password");
            clickSignInBtn();
            softAssert.assertEquals(getToastMessage(), loginTextProp.getPropValue("invalidCredentialsErrMsg"),
                    "Invalid credential error message not matched.");
        }
        pause(500);
        populateLoginForm(Configuration.toString("userName"), "invalid@password");
        clickSignInBtn();
        return getToastMessage();
    }

    /**
     * This method verify the efficiency widgets feature on
     * login page.
     *
     * @param softAssert soft assert all the verification.
     */
    public void verifyEfficiencyWidgets(SoftAssert softAssert) {
        List<WebElement> efficiencyTips = getEfficiencyReadMoreTiles();
        int count = efficiencyTips.size();
        softAssert.assertTrue(count <= 3);
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                String parentWinHandle = DriverFactory.getMainWindowHandle();
                if (getAttribute(efficiencyTips.get(i), "isexternal").equals("true")) {
                    click(efficiencyTips.get(i));
                    pause(2000);
                    PreLogEfficiencySteps preLogEfficiencySteps = new PreLogEfficiencySteps(driver);
                    //preLogEfficiencySteps.closePreLogEfficiencyTipClose();
                    for (String handle : DriverFactory.getWindowHandles()) {
                        DriverFactory.switchToWindow(handle);
                        pause(3000);
                    }
                    driver.close();
                    DriverFactory.switchToWindow(parentWinHandle);
                }
                else {
                    click(efficiencyTips.get(i));
                    pause(2000);
                    PreLogEfficiencySteps preLogEfficiencySteps = new PreLogEfficiencySteps(driver);
                    //preLogEfficiencySteps.closePreLogEfficiencyTipClose();
                }
                pause(1500);
            }
        }
        else {
            log.info("Efficiency programs do not have any tips.");
        }
    }

    /**
     * This method verify the chatbot feature on login page.
     *
     * @param softAssert soft assert all the verification.
     */
    public void verifyChatWindow(SoftAssert softAssert) {
        pause(2000);
        waitForChatIconVisibility();
        softAssert.assertFalse(isWebChatVisible(), "Web chat is not closed.");
        clickChatIcon();
        pause(2000);
        softAssert.assertTrue(isWebChatVisible(), "Web chat is not opened.");
        softAssert.assertEquals(getTextChatWithUs(), loginTextProp.getPropValue("lblChatWindowHeading"),
                "Chat window heading label not matched.");
        clickCloseChatBtn();
        clickConfirmCloseChatBtn();
        pause(2000);
        softAssert.assertFalse(isWebChatVisible(), "Web chat is not closed.");
    }

    public void verifyUsernamePasswordMaxLengthCriteria(SoftAssert softAssert) {
        String maxLenUsername = getUsernameMaxLength();
        String maxLenPassword = getPasswordMaxLength();
        softAssert.assertEquals(maxLenUsername, "32",
                "Max length username field not matched.");
        softAssert.assertEquals(maxLenPassword, "50",
                "Max length password field not matched.");
    }

    public void verifyCopyPasteSpaceInUsernamePasswordField(SoftAssert softAssert, String input) {
        // Copying text from clipboard
        StringSelection stringSelection = new StringSelection(input);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        // Verifying username field handled space on copy and paste space into it.
        copyPasteSpaceInUsername();
        String populatedValue = getPopulatedUsernameValue();
        softAssert.assertEquals(populatedValue.length(), input.length(),
                "Username field is accepting space to be copy paste.");
        // Verifying password field handled space on copy and paste space into it.
        StringSelection stringSelection1 = new StringSelection(input);
        Clipboard clipboard1 = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard1.setContents(stringSelection1, null);
        // Verifying whether the user is able to input space in the password field
        copyPasteSpaceInPassword();
        // Verifying whether the user is able to input space in the password field
        populatedValue = getPopulatedPasswordValue();
        softAssert.assertEquals(populatedValue.length(), input.length(),
                "Password field is accepting space to be copy paste");
    }

    public void verifyTypeSpaceInUsernamePasswordField(SoftAssert softAssert, String input) {
        // Verifying username field handled space on typing space into it.
        clearUsernameField();
        populateUserName(" ");
        int prevLength = input.length();
        pause(2000);
        // Entering value with space using robot class.
        // RobotUtil.enterTextEightCharactersWithSpace();
        populateUserName(input);
        String populatedValue = getPopulatedUsernameValue();
        softAssert.assertEquals(populatedValue.length(), prevLength, "Space is allowed in username field.");
        clearUsernameField();
        pause(1000);
        // Verifying password field handled space on typing space into it.
        clearPasswordField();
        populatePassword(" ");
        pause(2000);
        // Entering value with space using robot class.
        // RobotUtil.enterTextEightCharactersWithSpace();
        populatePassword(input);
        populatedValue = getPopulatedPasswordValue();
        softAssert.assertEquals(populatedValue.length(), prevLength, "Space is allowed in password field.");
        clearPasswordField();
        pause(1000);
    }
    public String loginWithInvalidCreds(String userName, String password) {
		populateLoginForm(userName, password);
		clickSignInBtn();
		String errMsg = getToastMessage();
		return errMsg;
	}

	public void navigationToRegistrationPage() {
		// TODO Auto-generated method stub
		clickRegisterBtn();
	// waitForPageLoader();
		
				
	}
}

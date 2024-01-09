package sew.ai.pageObjects.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import sew.ai.pageObjects.scp.HomePage;

import java.util.List;

public class AdminLoginPage extends HomePage {

    private static final Logger log = LogManager.getLogger(AdminLoginPage.class);
    public AdminLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "[id='txtAdminLogin']")
    private WebElement txtBoxUserName;

    public void clearUsernameField() {
        clear(txtBoxUserName);
        log.info("Username field cleared {}");
    }

    public void populateUserName(String userName) {
        log.info("Populating username {} :" + userName);
        sendKeys(txtBoxUserName, userName);
        log.info("Username populated successfully.");
    }
    public Boolean isUsernameTextBoxVisible() {
        log.info("Checking the visibility of UserName Textbox on the page.");
        log.info("UserName Textbox visibility status {}: " + isElementVisible(txtBoxUserName));
        return isElementVisible(txtBoxUserName);
    }

    public String getTextUserName() {
        String label = getAttribute(txtBoxUserName,"value");
        log.info("UserName TextBox is having the field Text as " + label);
        return label;
    }

    @FindBy(css = "[id='txtpwd']")
    private WebElement txtBoxUserPwd;

    public void clearPasswordField() {
        clear(txtBoxUserPwd);
        log.info("Password field cleared {}");
    }
    public void populatePassword(String password) {
        log.info("Populating password {} :" + password);
        sendKeys(txtBoxUserPwd, password);
        log.info("Password populated successfully.");
    }
    public Boolean isPasswordTextBoxVisible() {
        log.info("Checking the visibility of Password Textbox on the page.");
        log.info("Password Textbox visibility status {}: " + isElementVisible(txtBoxUserPwd));
        return isElementVisible(txtBoxUserPwd);
    }
    @FindBy(css = ".btn-default-login")
    private WebElement btnSignIn;
    public void clickBtnSignIn() {
        click(btnSignIn);
        log.info("SignIn Button clicked successfully.");
    }
    public Boolean isBtnSignInVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(btnSignIn));
        return isElementVisible(btnSignIn);
    }
    public String getSignInButtonLabel() {
        String label = getAttribute(btnSignIn,"value");
        log.info("SignIn Button Label {}: " + label);
        return label;
    }
    @FindBy(xpath = "//*[@id='lblMsg']")
    private WebElement lblErrorMsgInvalidUserNamePassWord;
    public String getLblErrorMsgInvalidUserNamePassWord() {
        String label = getText(lblErrorMsgInvalidUserNamePassWord).trim();
        log.info("UserName or Password Error Message is" + label);
        return label;
    }

    @FindBys(@FindBy(css = "#rememberMeCheck"))
    private List<WebElement> chb_remember_mes;
    @FindBy(css = "#rememberMeCheck")
    private WebElement chxBoxRememberMe;

    public void checkRememberMeChkBox() {
        clickWithJSExecutor(chxBoxRememberMe);
        log.info("Remember Me Checkbox checked successfully.");
    }
    public Boolean isRememberMeChbVisible() {
        log.info("Remember me checkbox visible status {}: " + chb_remember_mes.size());
        return isElementVisibleAlt(chb_remember_mes);
    }
    @FindBy(xpath = "//*[@class='forget_text']")
    private WebElement lnkForgetMyPassWord;

    public void waitForPasswordPasswordLinkToBeVisible(){
        waitForElementToBeVisible(lnkForgetMyPassWord);
        log.info("Password Password Link is Visible on the Admin Login page.");
    }

    public void clickLnkForgetPassword() {
        click(lnkForgetMyPassWord);
        log.info("Forgot Password Link clicked successfully.");
    }
    public String getLnkForgotPasswordLabel() {
        String label = getText(lnkForgetMyPassWord).trim();
        log.info("Forgot Password Link label is" + label);
        return label;
    }
    public Boolean isLnkForgotPasswordVisible() {
        log.info("Checking the visibility of Forgot Password on the page.");
        log.info("Forgot Password Link visibility status {}: " + isElementVisible(lnkForgetMyPassWord));
        return isElementVisible(lnkForgetMyPassWord);
    }
    @FindBy(xpath = "//input[@id='txtAdminLogin']/..//label")
    private WebElement lblUsername;
    public String getUsernameFieldLabel() {
        String label = getText(lblUsername);
        log.info("Username Field Label {}: " + label);
        return label;
    }
    @FindBy(xpath = "//label[text()='Password']")
    private WebElement lblPassword;
    public String getPasswordFieldLabel() {
        String label = getText(lblPassword);
        log.info("Password Field Label {}: " + label);
        return label;
    }
    @FindBy(xpath = "//label[text()='Remember Me']")
    private WebElement lblRememberMe;
    public String getRememberMeLabel() {
        String label = getText(lblRememberMe);
        log.info("Remember Me Label {}: " + label);
        return label;
    }
    @FindBy(xpath = "//a[@id='logo-container']//img")
    private WebElement logoCSP;
    public Boolean isCSPLogoVisible() {
        log.info("Checking the visibility of CSP Logo on the page.");
        log.info("CSP Logo visibility status {}: " + isElementVisible(logoCSP));
        return isElementVisible(logoCSP);
    }
    @FindBy(xpath = "//div[@class='copy-right']")
    private WebElement txtCopyright;
    @FindBy(css = "#spnrightReserved")
    private WebElement txtRightsReserved;

    @FindBy(css = ".copyrightIcon")
    private WebElement preLoginCopyRightIcon;
    public Boolean isPreLoginCopyRightIconVisible() {
        log.info("Checking the visibility of CopyRights Icon on the PreLogin page.");
        log.info("CopyRights Icon visibility status on the PreLogin page {}: " + isElementVisible(preLoginCopyRightIcon));
        return isElementVisible(preLoginCopyRightIcon);
    }
    public String getPreLoginCopyRightIconLabel() {
        String label = getText(preLoginCopyRightIcon);
        log.info("CopyRights Icon Label on the PreLogin page {}: " + label);
        return label;
    }

    @FindBy(css = ".copyrightIconmain")
    private WebElement postLoginCopyRightIcon;
    public Boolean isPostLoginCopyRightIconVisible() {
        log.info("Checking the visibility of CopyRights Icon on the PostLogin page.");
        log.info("CopyRights Icon visibility status on the PostLogin page {}: " + isElementVisible(postLoginCopyRightIcon));
        return isElementVisible(postLoginCopyRightIcon);
    }
    public String getPostLoginCopyRightIconLabel() {
        String label = getText(postLoginCopyRightIcon);
        log.info("CopyRights Icon Label on the PostLogin page {}: " + label);
        return label;
    }

    @FindBy(css=".foot_copy_sec")
    private WebElement lblCopyRightsReserved;

    public Boolean isCopyRightsReservedLblVisible() {
        log.info("Checking the visibility of CopyRights Reserved Label on the page.");
        log.info("CopyRights Reserved Label visibility status {}: " + isElementVisible(lblCopyRightsReserved));
        return isElementVisible(lblCopyRightsReserved);
    }
    public String getCopyRightsReservedLabel() {
        String label = getText(lblCopyRightsReserved);
        log.info("CopyRights Reserved Label {}: " + label);
        return label;
    }

    @FindBy(css = ".footer-logo > img")
    private WebElement logoSEW;
    public Boolean isFooterLogoVisible() {
        log.info("Checking the visibility of Footer Logo on the page.");
        log.info("Footer Logo visibility status {}: " + isElementVisible(logoSEW));
        return isElementVisible(logoSEW);
    }
    public String getFooterLogoLabel() {
        String label = getAttribute(logoSEW,"title");
        log.info("Footer Logo Label {}: " + label);
        return label;
    }
    @FindBy(css = ".vrsn")
    private WebElement lblAppVersion;
    public Boolean isAppVersionlabelVisible() {
        log.info("Checking the visibility of AppVersion Label on the page.");
        log.info("AppVersion Label visibility status {}: " + isElementVisible(lblAppVersion));
        return isElementVisible(lblAppVersion);
    }
    public String getAppVersionLabel() {
        String label = getText(lblAppVersion);
        log.info("AppVersion Label {}: " + label);
        return label;
    }
    @FindBy(css = "#txtEmail")
    private WebElement txtBoxEmailForgotPasswd;
    @FindBy(css = "#btnSubmitForgotUserName")
    private WebElement btnSubmitForgotPasswd;
    @FindBy(css = "#txtpwd")
    private WebElement txtBoxNewPasswd;
    @FindBy(css = "#txtconfirmpwd")
    private WebElement txtBoxConfirmPasswd;
    @FindBy(css = "#btnSubmit")
    private WebElement btnSubmitResetPasswd;
    @FindBy(xpath = "//h3[@id='ErrorMessage']")
    private WebElement lblResetPasswordExpiry;
    @FindBy(css = ".error_messagecommon")
    private WebElement txtAlertMessage;
    @FindBy(css = "#pswd")
    private WebElement txtAlertMessagePwd;
    public String getValidationMessagePassword() {
        String label = getText(txtAlertMessagePwd).trim();
        log.info("Password Field Validation Message is: " + label);
        return label;
    }
    public void waitForPasswordValidationMsgToBeVisible(){
        waitForElementToBeVisible(txtAlertMessagePwd);
        log.info("Password Field Validation Message is Visible on the page.");
    }
    @FindBy(css = "#user")
    private WebElement txtAlertMessageAlUsername;
    public String getValidationMessageUsername() {
        String label = getText(txtAlertMessageAlUsername).trim();
        log.info("Username Field Validation Message is: " + label);
        return label;
    }

    public void waitForUsernameValidationMsgToBeVisible(){
        waitForElementToBeVisible(txtAlertMessageAlUsername);
        log.info("Username Field Validation Message is Visible on the page.");
    }

    @FindBy(xpath = "//b[text()='New to Customer Service Portal?']")
    private WebElement txtMsgNewCSPHeading;
    public Boolean istxtMsgCSPHeadingVisible() {
        log.info("Checking the visibility of NewToCSP Heading on the page.");
        log.info("NewToCSP Heading visibility status {}: " + isElementVisible(txtMsgNewCSPHeading));
        return isElementVisible(txtMsgNewCSPHeading);
    }
    public String getNewCSPHeadingLabel() {
        String label = getText(txtMsgNewCSPHeading);
        log.info("NewToCSP Heading Label {}: " + label);
        return label;
    }
    @FindBy(xpath = "//p[contains(text(),'powered by AI and ML analytics')]")
    private WebElement txtMsgNewCSPDescription;
    public Boolean istxtMsgNewCSPDescriptionVisible() {
        log.info("Checking the visibility of NewToCSP Description on the page.");
        log.info("NewToCSP Description visibility status {}: " + isElementVisible(txtMsgNewCSPDescription));
        return isElementVisible(txtMsgNewCSPDescription);
    }
    public String getNewCSPDescriptionLabel() {
        String label = getText(txtMsgNewCSPDescription).trim();
        log.info("NewToCSP Description Label {}: " + label);
        return label;
    }
    @FindBy(xpath = "//span[text()='24/7 Customer Support']")
    private WebElement txt24By7CustomerSupport;
    public Boolean istxt24By7CustomerSupportVisible() {
        log.info("Checking the visibility of 24By7 text on the page.");
        log.info("24By7 text visibility status {}: " + isElementVisible(txt24By7CustomerSupport));
        return isElementVisible(txt24By7CustomerSupport);
    }
    public String get24By7CustomerSupportLabel() {
        String label = getText(txt24By7CustomerSupport);
        log.info("24By7 text Label {}: " + label);
        return label;
    }
    @FindBy(xpath = "//span[text()='Simplified access to customer information']")
    private WebElement txtSimplifiedAccessCustomer;
    public Boolean isTxtSimplifiedAccessCustomerVisible() {
        log.info("Checking the visibility of SimplifiedAccess text on the page.");
        log.info("SimplifiedAccess text visibility status {}: " + isElementVisible(txtSimplifiedAccessCustomer));
        return isElementVisible(txtSimplifiedAccessCustomer);
    }
    public String getSimplifiedAccessCustomerLabel() {
        String label = getText(txtSimplifiedAccessCustomer);
        log.info("SimplifiedAccess text Label {}: " + label);
        return label;
    }
    @FindBy(xpath = "//span[text()='Quick resolution to customer queries']")
    private WebElement txtQuickResolution;
    public Boolean isTxtQuickResolutionVisible() {
        log.info("Checking the visibility of QuickResolution text on the page.");
        log.info("QuickResolution text visibility status {}: " + isElementVisible(txtQuickResolution));
        return isElementVisible(txtQuickResolution);
    }
    public String getTxtQuickResolutionLabel() {
        String label = getText(txtQuickResolution);
        log.info("QuickResolution text Label {}: " + label);
        return label;
    }
    @FindBy(xpath = "//span[text()='Customer Engagement Reports and Analytics']")
    private WebElement txtCustomerEngagement;
    public Boolean isTxtCustomerEngagementVisible() {
        log.info("Checking the visibility of CustomerEngagement text on the page.");
        log.info("CustomerEngagement text visibility status {}: " + isElementVisible(txtCustomerEngagement));
        return isElementVisible(txtCustomerEngagement);
    }
    public String getTxtCustomerEngagementLabel() {
        String label = getText(txtCustomerEngagement);
        log.info("CustomerEngagement text Label {}: " + label);
        return label;
    }
    @FindBy(css = "div.toast-message")
    private WebElement lblToastMessage;

    public Boolean isAdminLoginPage(String url, String title) {
        Boolean isAdminLoginPage = false;
        log.info("Checking that the current page is Admin login page.");
        if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
            isAdminLoginPage = true;
        log.info("The current page is Admin login page {}: " + isAdminLoginPage);
        return isAdminLoginPage;
    }


}

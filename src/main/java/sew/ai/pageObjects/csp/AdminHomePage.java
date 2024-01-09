package sew.ai.pageObjects.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sew.ai.pageObjects.scp.HomePage;


public class AdminHomePage extends HomePage {

    private static final Logger log = LogManager.getLogger(AdminHomePage.class);

    public AdminHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button#sidebarbutton")
    private WebElement lnkRightHamburgMenu;
    @FindBy(css = "[title='ADMINISTRATION'] span")
    private WebElement lnkAdminHeader;

//    @FindBy(css = " .tipTop .dropdown.UserInfo")
//    private WebElement lnkImgProfile;
    @FindBy(css = "#sidebar_wrapper_boxnew > div.right_nav_sec > div.user-area > div > ul > li > a")
    private WebElement lnkImgProfile;
    @FindBy(css = "select.goog-te-combo")
    private WebElement ddlSelectLanguage;
    @FindBy(css = ".pull-right [id='imgLogoff']")
    private WebElement lnkSignOut;
    public void waitForLnkSignOutToBeVisible(){
        waitForElementToBeVisible(lnkSignOut);
        log.info("SignOut Link is Visible on the page.");
    }
    public void clickLnkSignOut() {
        click(lnkSignOut);
        log.info("Admin User SignOut Link clicked successfully.");
    }
    @FindBy(css = "li.efficiecny > a")
    private WebElement lnkWaysToSave;

    public void waitForLnkWaysToSaveToBeVisible(){
        waitForElementToBeVisible(lnkWaysToSave);
        log.info("Ways To Save Link is Visible on the page.");
    }

    public void clickLnkWaysToSave() {
        clickElementUsingJsExecutor(lnkWaysToSave);
        log.info("Ways To Save Link clicked successfully.");
    }

    @FindBy(css = "li.outage > a")
    private WebElement lnkOutages;

    public void waitForLnkOutagesToBeVisible(){
        waitForElementToBeVisible(lnkOutages);
        log.info("Outages Link is Visible on the page.");
    }

    public void clickLnkOutages() {
        clickElementUsingJsExecutor(lnkOutages);
        log.info("Outages Link clicked successfully.");
    }

    @FindBy(css = "li.notification_home > a")
    private WebElement lnkNotification;
    public void waitForLnkNotificationToBeVisible(){
        waitForElementToBeVisible(lnkNotification);
        log.info("Notification Link is Visible on the Admin Home page.");
    }
    public void clickLnkNotification() {
        click(lnkNotification);
        log.info("Notification Link clicked successfully.");
    }
    @FindBy(css = ".csr a")
    private WebElement lnkCSRWorkBench;
    public void clickLnkCSRWorkBench() {
    	click(lnkCSRWorkBench);
    	log.info("CSR WorkBench is clicked successfully.");
    }
    public void waitForLnkCSRWorkBench(){
        waitForElementToBeVisible(lnkCSRWorkBench);
        log.info("CSR WorkBench is Visible on the page.");
    }
    @FindBy(css = "li.user_dash > a")
    private WebElement lnkAdministration;
    @FindBy(css = "li.field.serv > a")
    private WebElement lnkFieldServiceView;
    @FindBy(css = "li.cea > a")
    private WebElement lnkCusEngAnal;
    @FindBy(css = "li.DassiqLink > a")
    private WebElement lnkWateriQAnalytics;
    @FindBy(xpath = "//li[@class='dropdown UserInfo']//a[@class='dropdown-toggle']")
    private WebElement lnkDropdownAdminProfile;
    public void waitForLnkDropdownAdminProfileToBeVisible(){
        waitForElementToBeVisible(lnkDropdownAdminProfile);
        log.info("Admin User Profile Dropdown is Visible on the page.");
    }
    public void clickDropdownAdminProfile() {
        click(lnkDropdownAdminProfile);
        log.info("Admin User Profile dropdown clicked successfully.");
    }
    public String getWelcomeMessage() {
        String label = getText(lnkDropdownAdminProfile);
        log.info("Welcome message on the Admin Home page {}: " + label);
        return label;
    }
    @FindBy(xpath = "//li[@class='dropdown UserInfo']")
    private WebElement lnkUserProfile;
    @FindBy(css = ".chang_pwd_header")
    private WebElement lnkChangePassword;
    public void waitForLnkChangePasswordToBeVisible(){
        waitForElementToBeVisible(lnkChangePassword);
        log.info("Change Password Link is Visible on the page.");
    }

    public void clickLnkChangePassword() {
        click(lnkChangePassword);
        log.info("Change Password Link clicked successfully.");
    }
    @FindBy(css = "#txtNewPass")
    private WebElement txtBoxChangePasswdNew;
    public void clearNewPasswordField() {
        clear(txtBoxChangePasswdNew);
        log.info("New Password field cleared {}");
    }
    public void populateNewPassword(String password) {
        log.info("Populating New password {} :" + password);
        sendKeys(txtBoxChangePasswdNew, password);
        log.info("New Password field populated successfully.");
    }
    @FindBy(css = "#txtConfirmPass")
    private WebElement txtBoxChangePasswdConfirm;
    public void clearConfirmPasswordField() {
        clear(txtBoxChangePasswdConfirm);
        log.info("Confirm Password field cleared {}");
    }
    public void populateConfirmPassword(String password) {
        log.info("Populating Confirm password {} :" + password);
        sendKeys(txtBoxChangePasswdConfirm, password);
        log.info("Confirm Password field populated successfully.");
    }
    @FindBy(css = "#txtPass")
    private WebElement txtBoxChangePasswdExisting;
    public void clearExistingPasswordField() {
        clear(txtBoxChangePasswdExisting);
        log.info("Existing Password field cleared {}");
    }
    public void populateExistingPassword(String password) {
        log.info("Populating Existing password {} :" + password);
        sendKeys(txtBoxChangePasswdExisting, password);
        log.info("Existing Password field populated successfully.");
    }
    @FindBy(css = "#btnChangePassword")
    private WebElement btnSubmitChangePwd;
    public void waitForSubmitBtnChangePasswordToBeVisible(){
        waitForElementToBeVisible(btnSubmitChangePwd);
        log.info("Submit Button on Change Password Popup is Visible on the page.");
    }
    public void clickBtnSubmitChangePassword() {
        click(btnSubmitChangePwd);
        log.info("Submit Button on Change Password Popup is clicked successfully.");
    }
    @FindBy(xpath = "//div[@class='toast-message']")
    private WebElement toastMessageChangePasswd;
    @FindBy(xpath = "//a[@class='button_strength']")
    private WebElement lnkShowPasswdUpdate;
    @FindBy(xpath = "//div[@id='myBar']")
    private WebElement lblPasswordStrenghMeter;
    @FindBy(xpath = "//input[@id='btnCancel']")
    private WebElement btnCancelUpdatePasswd;
    @FindBy(css = "#Noti_count")
    private WebElement countTxtNotification;
    @FindBy(css = "#outage_count")
    private WebElement countTxtOutage;
    @FindBy(xpath = "//div[@id='Noti_cnt']")
    private WebElement toolTipTxtNotification;
    @FindBy(css = "#outage_cnt")
    private WebElement toolTipTxtOutage;
    @FindBy(css = "#cust_count")
    private WebElement countTxtCSR;
    @FindBy(css = "#cust_cnt")
    private WebElement toolTipTxtCSR;
    @FindBy(css = ".right_nav_sec .modal-dialog .modal-content .modal-header button")
    private WebElement btnCloseIconHambergerMenu;

    public Boolean isAdminHomePage(String url, String title) {
        Boolean isAdminHomePage = false;
        log.info("Checking that the current page is Admin Home page.");
        if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
            isAdminHomePage = true;
        log.info("The current page is Admin Home page {}: " + isAdminHomePage);
        return isAdminHomePage;
    }
}

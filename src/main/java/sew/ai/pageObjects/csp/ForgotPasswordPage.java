package sew.ai.pageObjects.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sew.ai.pageObjects.scp.HomePage;

public class ForgotPasswordPage extends HomePage {

    private static final Logger log = LogManager.getLogger(ForgotPasswordPage.class);

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='forget_text']")
    private WebElement lnkForgetMyPassWord;
    @FindBy(css = "#txtEmail")
    private WebElement txtBoxEmailForgotPasswd;

    public void clearEmailAddressTxtBox() {
        clear(txtBoxEmailForgotPasswd);
        log.info("Email Address field cleared {}");
    }
    public void populateEmailAddressTxtBox(String emailID) {
        log.info("Populating Email Address {} :" + emailID);
        sendKeys(txtBoxEmailForgotPasswd, emailID);
        log.info("EmailAddress populated successfully.");
    }
    @FindBy(css = "#btnSubmitForgotUserName")
    private WebElement btnSubmitForgotPasswd;
    public void clickBtnSubmit() {
        click(btnSubmitForgotPasswd);
        log.info("Submit Button clicked successfully.");
    }
    public void waitForBtnSubmitToBeVisible(){
        waitForElementToBeVisible(btnSubmitForgotPasswd);
        log.info("Submit Button on Forgot Password Page is Visible");
    }
    @FindBy(css = "#btnCancelForgotUserName")
    private WebElement btnCancelForgotPasswd;
    public void clickBtnCancel() {
        click(btnCancelForgotPasswd);
        log.info("Cancel Button clicked successfully.");
    }
    public void waitForBtnCancelToBeVisible(){
        waitForElementToBeVisible(btnCancelForgotPasswd);
        log.info("Cancel Button on Forgot Password Page is Visible");
    }
    @FindBy(css = "#txtpwd")
    private WebElement txtBoxNewPasswd;
    public void clearNewPasswordTxtBox() {
        clear(txtBoxNewPasswd);
        log.info("New Password field cleared {}");
    }
    public void populateNewPasswordTxtBox(String password) {
        log.info("Populating New Password {} :" + password);
        sendKeys(txtBoxNewPasswd, password);
        log.info("New Password textbox populated successfully.");
    }
    @FindBy(css = "#txtconfirmpwd")
    private WebElement txtBoxConfirmPasswd;
    public void clearConfirmPasswordTxtBox() {
        clear(txtBoxConfirmPasswd);
        log.info("Confirm Password field cleared {}");
    }
    public void populateConfirmPasswordTxtBox(String password) {
        log.info("Populating Confirm Password {} :" + password);
        sendKeys(txtBoxConfirmPasswd, password);
        log.info("Confirm Password textbox populated successfully.");
    }
    @FindBy(css = "#btnSubmit")
    private WebElement btnSubmitResetPasswd;

    public void waitForSubmitBtnResetPasswdToBeVisible(){
        waitForElementToBeVisible(btnSubmitResetPasswd);
        log.info("Submit Button on Reset Password Page is Visible");
    }
    public void clickBtnSubmitResetPasswd() {
        click(btnSubmitResetPasswd);
        log.info("Submit Button on Reset Password Page clicked successfully.");
    }

    @FindBy(xpath = "//h3[@id='ErrorMessage']")
    private WebElement lblResetPasswordExpiry;
    @FindBy(css = ".error_messagecommon")
    private WebElement txtAlertMessage;
    @FindBy(css = "#pswd")
    private WebElement txtAlertMessagePwd;
    @FindBy(css = "#user")
    private WebElement txtAlertMessageUsername;

    public Boolean isForgotPasswordPage(String url, String title) {
        Boolean isForgotPasswordPage = false;
        log.info("Checking that the current page is Forgot Password page.");
        if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
            isForgotPasswordPage = true;
        log.info("The current page is Forgot Password page {}: " + isForgotPasswordPage);
        return isForgotPasswordPage;
    }


}

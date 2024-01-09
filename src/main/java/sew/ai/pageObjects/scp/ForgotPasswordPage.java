package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage extends HomePage {
    private static final Logger log = LogManager.getLogger(ForgotPasswordPage.class);

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(css = "[id='LoginForgetPassword']")
    private WebElement lnkFogotPassword;

    public void clickForgotPasswordLnk() {
        click(lnkFogotPassword);
        log.info("Forgot password link clicked {}.");
    }
    
    public boolean isForgotPasswordLnkVisible() {
        log.info("Checking Forget password Link Visibility");
        return isElementVisible(lnkFogotPassword);
    }
    
    @FindBy(css = "#txtForgotPwdUserName")
    private WebElement txtForgotPwdUserName;

    public boolean isUserNameTxtVisible() {
        log.info("Checking that the Username field is visible on the Forget Password page.");
        return isElementVisible(txtForgotPwdUserName);
    }

    public void clearUserNameField() {
        clear(txtForgotPwdUserName);
        log.info("Username field cleared {}");
    }

    public String getUserNameMaxLength() {
        String maxLen = getAttribute(txtForgotPwdUserName, "maxlength");
        log.info("Max length of Username field is {} " + maxLen);
        return maxLen;
    }

    public String getPopulatedUsernameValue() {
        log.info("Fetching the auto populatedUsername");
        return getAttribute(txtForgotPwdUserName, "value");
    }

    public void waitForUserNameVisibility() {
        waitForElementToBeVisible(txtForgotPwdUserName);
        log.info("Wait for Username field to be displayed.");
    }

    public void copyPasteSpaceInUserName() {
        copyPasteUsingActionClass(txtForgotPwdUserName);
        log.info("Copy paste done into the Username field.");
    }
    
    public String getUserNamemessage() {
        String validatemessage = getAttribute(txtForgotPwdUserName, "validatemessage");
        log.info("validate Messageof Username field is {} " + validatemessage);
        return validatemessage;
    }
    
    public void populateUserName(String userName) {
        log.info("Populating username {} :" + userName);
        sendKeys(txtForgotPwdUserName, userName);
        log.info("Username populated successfully.");
    }
    
    public void enterUserName(String userName) {
        log.info("Populating username {} :" + userName);
        sendKeys(txtForgotPwdUserName, userName);
        log.info("Username populated successfully.");
    }
    
    @FindBy(css = ".loginpage-form>h1")
    private WebElement lblHeader;

    public String getForgetUsernameHeader() {
        log.info("Fetching the Forget Password page header.");
        String label = getText(lblHeader);
        log.info("Forget Password page header is {}: " + label);
        return label;
    }
    
    public boolean isPagelabelHeaderVisible() {
    	log.info("Forget Password Page header label visibility Status :" + lblHeader.isDisplayed());
    	return lblHeader.isDisplayed();
    }
    
    @FindBy(css = "[id='headerForgotPassword']")
    private WebElement lblPleaseEnterUserName;

    public String getlblPleaseEnterUserName() {
        log.info("Fetching Please enter Username text.");
        String label = getText(lblPleaseEnterUserName);
        log.info("Please enter Username is {}: " + label);
        return label;
    }
    
    public boolean islblPleaseEnterUserNameVisible() {
    	log.info("Please enter Username visibility Status :" + lblPleaseEnterUserName.isDisplayed());
    	return lblPleaseEnterUserName.isDisplayed();
    }
    

    @FindBy(css = "[class='error_messagecommon']")
    private WebElement lblErrorMsgEnterUsername;

    public String getlblErrorMsgEnterUsername() {
        log.info("Fetching the Please enter Username error message");
        String label = getText(lblErrorMsgEnterUsername);
        log.info("Please enter Username error message is {}: " + label);
        return label;
    }
    
    public boolean isErrorMsgEnterUsernameVisible() {
    	log.info("Please enter Username error message Status :" + lblErrorMsgEnterUsername.isDisplayed());
    	return lblErrorMsgEnterUsername.isDisplayed();
    }
    
    @FindBy(css = "#btnCancelForgotPassword")
    private WebElement btnCancel;

    public String getbtnCancel() {
        log.info("Fetching the Cancel button Text");
        String label = getText(btnCancel);
        log.info("Cancel button Text is {}: " + label);
        return label;
    }
    
    public boolean isCancelBtnVisible() {
    	log.info("Cancel Button Status :" + btnCancel.isDisplayed());
    	return btnCancel.isDisplayed();
    }
    
    public void clickCancelBtn() {
        click(btnCancel);
        log.info("Cancel Button clicked {}.");
    }
    
    @FindBy(css = "#btnSubmitForgotPassword")
    private WebElement btnNext;

    public String getbtnSubmit() {
        log.info("Fetching the Submit button Text");
        String label = getText(btnNext);
        log.info("Next button Text is {}: " + label);
        return label;
    }
       
    public boolean isSubmitBtnVisible() {
    	log.info("Submit Button Status :" + btnNext.isDisplayed());
    	return btnNext.isDisplayed();
    }
    
    public void clickSubmitBtn() {
        click(btnNext);
        log.info("Submit Button clicked {}.");
    }
    
    
    @FindBy(css = "input#txtpwd")
    private WebElement txtNewPassword;

    public String gettxtNewPasswordMaxLength() {
        String maxLen = getAttribute(txtNewPassword, "maxlength");
        log.info("Max length of New Password field is {} " + maxLen);
        return maxLen;
    }
    
    public void entertxtNewPassword(String password) {
        log.info("Populating username {} :" + password);
        sendKeys(txtNewPassword, password);
        log.info("New password populated successfully.");
    }
    
    public String gettxtNewPasswordMinLength() {
        String minLen = getAttribute(txtNewPassword, "minlength");
        log.info("Max length of New Password field is {} " + minLen);
        return minLen;
    }
    public void clicktxtNewPassword() {
        click(txtNewPassword);
        log.info("New Password text box clicked {}.");
    }
    
    public boolean istxtNewPasswordVisible() {
    	log.info("txt New Password Status :" + txtNewPassword.isDisplayed());
    	return txtNewPassword.isDisplayed();
    }
    
    @FindBy(css = "input#txtconfirmpwd")
    private WebElement txtConfirmPassword;

    public String gettxtConfirmPasswordMaxLength() {
        String maxLen = getAttribute(txtConfirmPassword, "maxlength");
        log.info("Max length of Confirm Password field is {} " + maxLen);
        return maxLen;
    }
    
    public void entertxtConfirmPassword(String password) {
        log.info("Populating username {} :" + password);
        sendKeys(txtConfirmPassword, password);
        log.info("Confirm password populated successfully.");
    }
    
    public String gettxtConfirmPasswordMinLength() {
        String minLen = getAttribute(txtConfirmPassword, "maxlength");
        log.info("Max length of Confirm Password field is {} " + minLen);
        return minLen;
    }
    
    public void clicktxtConfirmPassword() {
        click(txtConfirmPassword);
        log.info("Confirm Password text box clicked {}.");
    }
    
    public boolean isConfirmPasswordVisible() {
    	log.info("Confirm Password Status :" + txtConfirmPassword.isDisplayed());
    	return txtConfirmPassword.isDisplayed();
    }
    
    @FindBy(css = "input.btn-default-login-submit[style*='outline: none;']")
    private WebElement btnSubmitNewPwd;

    public void clickbtnSubmitNewPwd() {
        click(btnSubmitNewPwd);
        log.info("btn Submit New Pwd clicked {}.");
    }
    
    public boolean isbtnSubmitNewPwdVisible() {
    	log.info("btn Submit New Pwd Status :" + btnSubmitNewPwd.isDisplayed());
    	return btnSubmitNewPwd.isDisplayed();
    }
    
    @FindBy(xpath = "//*[@id = 'pswd_info'][contains(@style, 'block')]//span")
    private WebElement lblPasswordStrength;
    
    public String getlblPasswordStrength() {
        log.info("Fetching the auto populated Password Strength");
        return getAttribute(lblPasswordStrength, "value");
    }
    @FindBy(css = "[id='txtLogin']")
    private WebElement txt_username;

    public void populateUserNameSignIn(String userName) {
        log.info("Populating username {} :" + userName);
        sendKeys(txt_username, userName);
        log.info("Username populated successfully.");
    }
    
    @FindBy(css = "[id='txtpwd']")
    private WebElement txt_password;

    public void populatePassword(String password) {
        log.info("Populating password {} :" + password);
        sendKeys(txt_password, password);
        log.info("Password populated successfully.");
    }
    
    @FindBy(css = "#btnlogin")
    private WebElement btn_sign_in;

    public void clickSignInBtn() {
        log.info("Clicking the sign in button.");
        click(btn_sign_in);
        log.info("Sign in button clicked successfully.");
    }
    
    
    public boolean isLoginPage(String url, String title) {
        Boolean isLoginPage = false;
        log.info("Checking that the current page is login page.");
        if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
            isLoginPage = true;
        log.info("The current page is login page {}: " + isLoginPage);
        return isLoginPage;
    }
    
  //  txtBoxUsernameFpLsp                                      = #txtForgotPwdUserName
  //  lblForgotPasswordLsp                                     = .loginpage-form>h1
 //   lblPleaseEnterUserName                                     = [id='headerForgotPassword']
 //   lblErrorMsgEnterUsername                                    = [class='error_messagecommon']
 //   btnCancel                                                  = #btnCancelForgotPassword
  //  btnSubmit                                                  = #btnSubmitForgotPassword
}

package demo.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sew.ai.pageObjects.scp.HomePage;

public class ForgotUsernamePage extends HomePage {
    private static final Logger log = LogManager.getLogger(ForgotUsernamePage.class);

    public ForgotUsernamePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(css = "[id='txtEmailForgotUserName']")
    private WebElement txtEmailForgotUserName;

    public Boolean isEmailAddressTxtVisible() {
        log.info("Checking that the Email Address field is visible on the Forget Username page.");
        return isElementVisible(txtEmailForgotUserName);
    }

    public void clearEmailAddressField() {
        clear(txtEmailForgotUserName);
        log.info("Email Address field cleared {}");
    }

    public void populateEmailAddress(String emailAddress) {
        log.info("Email Address username {} :" + emailAddress);
        sendKeys(txtEmailForgotUserName, emailAddress);
        log.info("Email Address populated successfully.");
    }

    public String getEmailAddressMaxLength() {
        String maxLen = getAttribute(txtEmailForgotUserName, "maxlength");
        log.info("Max length of Email address field is {} " + maxLen);
        return maxLen;
    }

    public String getPopulatedEmailAddressValue() {
        log.info("Fetching the auto populated Email address.");
        return getAttribute(txtEmailForgotUserName, "value");
    }

    public void waitForEmailAddressFieldVisibility() {
        waitForElementToBeVisible(txtEmailForgotUserName);
        log.info("Wait for  Email address field to be displayed.");
    }

    public void copyPasteSpaceInEmailAddress() {
        copyPasteUsingActionClass(txtEmailForgotUserName);
        log.info("Copy paste done into the Email Address field.");
    }
    
    public String getEmailAddressvalidatemessage() {
        String validatemessage = getAttribute(txtEmailForgotUserName, "validatemessage");
        log.info("validate Messageof Email address field is {} " + validatemessage);
        return validatemessage;
    }
    
    @FindBy(xpath = "(//h1[text()='Forgot Username'])[1]")
    private WebElement lblHeader;

    public String getForgetUsernameHeader() {
        log.info("Fetching the Forget Username page header.");
        String label = getText(lblHeader);
        log.info("Forget Username page header is {}: " + label);
        return label;
    }
    
    public boolean isPagelabelHeaderVisible() {
    	log.info("Forget Username Page header label visibility Status :" + lblHeader.isDisplayed());
    	return lblHeader.isDisplayed();
    }

    @FindBy(css = "[id='headerForgotUserNameAccount']")
    private WebElement lblPleaseEnterPrimaryEmail;
    
    public String getlblPleaseEnterPrimaryEmail() {
        log.info("Fetching lable Please enter the primary email address associated with your account..");
        String label = getText(lblPleaseEnterPrimaryEmail);
        log.info("Please enter the primary email address associated with your account. Staus{}: " + label);
        return label;
    }
    
    public boolean isLblPleaseEnterPrimaryEmailVisible() {
    	log.info("Please enter the primary email address associated with your account. Status :" + lblPleaseEnterPrimaryEmail.isDisplayed());
    	return lblPleaseEnterPrimaryEmail.isDisplayed();
    }
    
    @FindBy(xpath = "//*[@id='txtEmailForgotUserName']/following::label")
    private WebElement lblTxtBoxEmailAddress;
    
    public String getlblTxtBoxEmailAddress() {
        log.info("Fetching lable Email Address.");
        String label = getText(lblTxtBoxEmailAddress);
        log.info("Lable Email Address. Staus{}: " + label);
        return label;
    }
    
    public boolean isLblTxtBoxEmailAddressVisible() {
    	log.info("lable Email Address. Status :" + lblTxtBoxEmailAddress.isDisplayed());
    	return lblTxtBoxEmailAddress.isDisplayed();
    }
       
    @FindBy(css = "#useronly > div > a")
    private WebElement icoEmailAddressInfo;
    
    public String getIcoEmailAddressInfo() {
        log.info("Fetching Text from Help Icon Email user details");
        String label = getText(icoEmailAddressInfo);
        log.info("Help Icon Email user details Staus{}: " + label);
        return label;
    }
    
    public boolean isIcoEmailAddressInfoVisible() {
    	log.info("Help Icon Email user details Status :" + icoEmailAddressInfo.isDisplayed());
    	return icoEmailAddressInfo.isDisplayed();
    }
    
    public String getIcoEmailDataOriginalTitle() {
        log.info("Fetching the auto populated Email address.");
        return getAttribute(icoEmailAddressInfo, "data-original-title");
    }
    
    @FindBy(css = "[class='error_messagecommon']")
    private WebElement lblEnterValidEmailAddress;
    
    public String getlblEnterValidEmailAddress() {
        log.info("Fetching error message Please enter valid email address.");
        String label = getText(lblEnterValidEmailAddress);
        log.info("Please enter valid email address. Staus{}: " + label);
        return label;
    }
    
    public boolean islblEnterValidEmailAddressVisible() {
    	log.info("Please enter valid email address. Status :" + lblEnterValidEmailAddress.isDisplayed());
    	return lblEnterValidEmailAddress.isDisplayed();
    }
    
    @FindBy(css = "[id='btnCancelForgotUserName']")
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
   
    
    @FindBy(css = "[id='btnSubmitForgotUserName']")
    private WebElement btnSubmit;

    public String getbtnSubmit() {
        log.info("Fetching the Next button Text");
        String label = getText(btnSubmit);
        log.info("Next button Text is {}: " + label);
        return label;
    }
    
    public boolean isSubmitBtnVisible() {
    	log.info("Next Button Status :" + btnSubmit.isDisplayed());
    	return btnSubmit.isDisplayed();
    }
    
    public void clickSubmitBtn() {
        click(btnSubmit);
        log.info("Submit Button clicked {}.");
    }
   
    @FindBy(css = ".grecaptcha-logo iframe")
    private WebElement reCaptcha;
   
    public boolean isreCaptchaVisible() {
    	log.info("Next Button Status :" + reCaptcha.isDisplayed());
    	return reCaptcha.isDisplayed();
    }
    
    @FindBy(css = "[class='error_messagecommon']")
    private WebElement lblGenericErrorMessage;

    public String getlblGenericErrorMessage() {
        log.info("Fetching the label Generic Error message");
        String label = getText(lblGenericErrorMessage);
        log.info("label Generic Error message {}: " + label);
        return label;
    }
    
    public boolean islblGenericErrorMessageVisible() {
    	log.info("Generic Error message visibility Status :" + lblGenericErrorMessage.isDisplayed());
    	return lblGenericErrorMessage.isDisplayed();
    	
    }
    
    @FindBy(css = "#txtUserAccountNumber")
    private WebElement txtBoxAccountNumber;

    public void enterAccountNumber(String accountNo) {
        log.info("Populating Account Number {} :" + accountNo);
        sendKeys(txtBoxAccountNumber, accountNo);
        log.info("Account Number populated successfully.");
    }
    
    @FindBy(css = "input.submit-button[type=\"submit\"]")
    private WebElement btnSubmitAcountNo;

    public void clickSubmitBtnAccountNo() {
        click(btnSubmitAcountNo);
        log.info("Submit Button clicked {}.");
    }
    
    
 //   txtEmailForgotUserName                 = [id='txtEmailForgotUserName']
 //   ForgetUsernameHeader		           = (//h1[text()='Forgot Username'])[1]
 //   lblPleaseEnterPrimaryEmail             = [id='headerForgotUserNameAccount']
 //   lblTxtBoxEmailAddressFuLsp             = //*[@id='txtEmailForgotUserName']/following::label
 //   icoEmailAddressInfoFu                  = #useronly > div > a    
 //     frameCaptchaLsp                        = .grecaptcha-logo iframe
 //   lblEnterValidEmailAddress              = [class='error_messagecommon']
 //    btnCancel                              = [id='btnCancelForgotUserName']
 //    btnSubmit                              = [id='btnSubmitForgotUserName']		
}
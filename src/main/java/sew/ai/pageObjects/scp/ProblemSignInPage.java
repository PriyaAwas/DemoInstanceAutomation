package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProblemSignInPage extends HomePage {
    private static final Logger log = LogManager.getLogger(ProblemSignInPage.class);

    public ProblemSignInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
	
          }
    
    @FindBy(css = "#txtAccNumberOtherLogin")
    private WebElement txtBoxAccountNumber;

    public Boolean istxtBoxAccountNumberVisible() {
        log.info("Checking that the Account Number field is visible on the Problem Signing In page.");
        return isElementVisible(txtBoxAccountNumber);
    }

    public void cleartxtBoxAccountNumber() {
        clear(txtBoxAccountNumber);
        log.info("Account Number field cleared {}");
    }

    public String getTxtBoxAccountNumberMaxLength() {
        String maxLen = getAttribute(txtBoxAccountNumber, "maxlength");
        log.info("Max length of Account Number field is {} " + maxLen);
        return maxLen;
    }
    
    public String getTxtBoxAccountNumberMinLength() {
        String maxLen = getAttribute(txtBoxAccountNumber, "minlength");
        log.info("Max length of Account Number field is {} " + maxLen);
        return maxLen;
    }

    public String getPopulatedAccountNumberValue() {
        log.info("Fetching the auto populated Account Number");
        return getAttribute(txtBoxAccountNumber, "value");
    }

    public void waitForAccountNumberFieldVisibility() {
        waitForElementToBeVisible(txtBoxAccountNumber);
        log.info("Wait for Account Number field to be displayed.");
    }

    public void copyPasteSpaceInAccountNumber() {
        copyPasteUsingActionClass(txtBoxAccountNumber);
        log.info("Copy paste done into the Account Number field.");
    }
    
    public String getAccountNumbervalidatemessage() {
        String validatemessage = getAttribute(txtBoxAccountNumber, "validatemessage");
        log.info("validate Message of Account Number field is {} " + validatemessage);
        return validatemessage;
    }
    
    public void clickAccountNumberTxtBox() {
        click(txtBoxAccountNumber);
        log.info("Account Number field is clicked {}.");
    }
    
    public void enterAccountNumberTxtBox(String accountNo) {
        log.info("Populating Account Number {} :" + accountNo);
        sendKeys(txtBoxAccountNumber, accountNo);
        log.info("Account Number populated successfully.");
    }
    
    @FindBy(css = "#txtEmailOtherLogin")
    private WebElement txtBoxEmailAddress;

    public Boolean istxtBoxEmailAddressVisible() {
        log.info("Checking that the Email Address field is visible on the Problem Signing In page.");
        return isElementVisible(txtBoxEmailAddress);
    }
    
    public void enterEmailAddressTxtBox(String emailAdress) {
        log.info("Populating Account Number {} :" + emailAdress);
        sendKeys(txtBoxEmailAddress, emailAdress);
        log.info("Email Address populated successfully.");
    }

    public void cleartxtBoxEmailAddress() {
        clear(txtBoxEmailAddress);
        log.info("Email Address field cleared on the Problem Signing In page.{}");
    }

    public String getTxtBoxEmailAddressMaxLength() {
        String maxLen = getAttribute(txtBoxEmailAddress, "maxlength");
        log.info("Max length of Email Address field on the Problem Signing In page is {} " + maxLen);
        return maxLen;
    }
    

    public String getPopulatedEmailAddressValue() {
        log.info("Fetching the auto populated Email address");
        return getAttribute(txtBoxEmailAddress, "value");
    }

    public void waitForEmailAddressFieldVisibility() {
        waitForElementToBeVisible(txtBoxEmailAddress);
        log.info("Wait for Email Address field to be displayed.");
    }

    public void copyPasteSpaceInEmailAddress() {
        copyPasteUsingActionClass(txtBoxEmailAddress);
        log.info("Copy paste done into the Email Address field.");
    }
    
    public String getEmailAddressValidateMessage() {
        String validatemessage = getAttribute(txtBoxEmailAddress, "validatemessage");
        log.info("validate Message of Email Address field is {} " + validatemessage);
        return validatemessage;
    }
    
    public void clickEmailAddressTxtBox() {
        click(txtBoxAccountNumber);
        log.info("Account Number field is clicked {}.");
    }
    
    @FindBy(css = "#txtComments")
    private WebElement txtBoxComment;

    public Boolean istxtBoxCommentsVisible() {
        log.info("Checking that the Comment field is visible on the Problem Signing In page.");
        return isElementVisible(txtBoxComment);
    }

    public void cleartxtBoxComment() {
        clear(txtBoxComment);
        log.info("Comment field cleared on the Problem Signing In page.{}");
    }

    public String getTxtBoxCommentMaxLength() {
        String maxLen = getAttribute(txtBoxComment, "maxlength");
        log.info("Max length of Comment field on the Problem Signing In page is {} " + maxLen);
        return maxLen;
    }

    public void waitForCommentFieldVisibility() {
        waitForElementToBeVisible(txtBoxComment);
        log.info("Wait for Comment field to be displayed.");
    }

    public void copyPasteSpaceInComment() {
        copyPasteUsingActionClass(txtBoxComment);
        log.info("Copy paste done into the Comment field.");
    }
    
    public String getCommentValidateMessage() {
        String validatemessage = getAttribute(txtBoxComment, "validatemessage");
        log.info("validate Message of Email Address field is {} " + validatemessage);
        return validatemessage;
    }
    
    public void clickCommentTxtBox() {
        click(txtBoxComment);
        log.info("Comment field is clicked {}.");
    }
       
    public void enterCommentTxtBoxTxtBox(String comment) {
        log.info("Populating Comment{} :" + comment);
        sendKeys(txtBoxComment, comment);
        log.info("Comment populated successfully.");
    }
    
    @FindBy(css = ".w2ui-centered")
    private WebElement lblResetPasswordMsgOld;

    public String getlblResetPasswordMsgOld() {
        log.info("Fetching the label Reset password message");
        String label = getText(lblResetPasswordMsgOld);
        log.info("Reset password message is {}: " + label);
        return label;
    }
    
    public boolean isResetPasswordMsgOldVisible() {
    	log.info("Page header label visibility Status :" + lblResetPasswordMsgOld.isDisplayed());
    	return lblResetPasswordMsgOld.isDisplayed();
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
    
    @FindBy(css = ".toast-message")
    private WebElement lblMandaoryErrorMessage;

    public String getlblMandaoryErrorMessage() {
        log.info("Fetching the label Mandaory Error Message ");
        String label = getText(lblMandaoryErrorMessage);
        log.info("label Mandaory Error Message {}: " + label);
        return label;
    }
    
    public boolean islblMandaoryErrorMessageVisibility() {
    	log.info("Mandaory Error Message visibility Status :" + lblMandaoryErrorMessage.isDisplayed());
    	return lblMandaoryErrorMessage.isDisplayed();
    }
    
    @FindBy(css = ".w2ui-tag-body .w2ui-tag-right")
    private WebElement toolTipMandaoryErrorMessage;

    public String getToolTipMandaoryErrorMessage() {
        log.info("Fetching the label Tool Tip Mandaory Error Message ");
        String label = getText(toolTipMandaoryErrorMessage);
        log.info("Tool Tip Mandaory Error Message{}: " + label);
        return label;
    }
    
    public boolean istoolTipMandaoryErrorMessageVisibility() {
    	log.info("Mandaory Error Message visibility Status :" + toolTipMandaoryErrorMessage.isDisplayed());
    	return toolTipMandaoryErrorMessage.isDisplayed();
    }
    
    @FindBy(css = ".w2ui-tag-body .w2ui-tag-right")
    private WebElement lblSuccessfulSubmit;

    public String getlblSuccessfulSubmit() {
        log.info("Fetching the label Successful Submit ");
        String label = getText(lblSuccessfulSubmit);
        log.info("Tool Tip Mandaory Error Message{}: " + label);
        return label;
    }
    
    public boolean islblSuccessfulSubmitVisibility() {
    	log.info("SuccessfulSubmit Status :" + lblSuccessfulSubmit.isDisplayed());
    	return lblSuccessfulSubmit.isDisplayed();
    }
    
    @FindBy(css = "#btnOtherLogin")
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
    
    public void isclickCancelBtn() {
        click(btnCancel);
        log.info("Submit button  is clicked {}.");
    }
    
    public void waitForCancelBtnclickable() {
        waitForElementToBeClickable(btnCancel);
        log.info("Wait for cancel button is clickable");
    }
    
    @FindBy(css = "#btnSubmitOtherLogin")
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
    
    public void isclickSubmitBtn() {
        click(btnSubmit);
        log.info("Submit button  is clicked {}.");
    }
    
    @FindBy(css = "[id='OuterHeader_homeAnchor']")
    private WebElement icoHome;

    public boolean isicoHomeVisible() {
    	log.info("icon Home visibility Status :" + icoHome.isDisplayed());
    	return icoHome.isDisplayed();
    	
    }
    
    public String geticoHomeTitle() {
        log.info("Fetching the auto populated icon Home Title");
        return getAttribute(icoHome, "title");
    }
    
    public void clickIconHome() {
        click(icoHome);
        log.info("icon Home clicked {}.");
    }
    
  //  txtBoxAccountNumberPsLsp                                 = #txtAccNumberOtherLogin
  //  txtBoxEmailAddressPsLsp                                  = #txtEmailOtherLogin
  //  txtBoxComment                                          = #txtComments
  //  lblResetPasswordMsgOldLsp                                = .w2ui-centered
  //  lblGenericErrorMessageLsp                                = [class='error_messagecommon']
  //  lblMandaoryErrorMessageLsp                               = .toast-message
  //  toolTipMandaoryErrorMessage                              = .w2ui-tag-body .w2ui-tag-right
  //  lblSuccessfulSubmitLsp                                   = .w2ui-centered.w2ui-alert-msg
//    btnCancel                                                = #btnOtherLogin
//    btnSubmit                                                = #btnSubmitOtherLogin
}  




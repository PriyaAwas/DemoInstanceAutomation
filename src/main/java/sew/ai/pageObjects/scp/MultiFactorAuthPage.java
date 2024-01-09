package sew.ai.pageObjects.scp;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class MultiFactorAuthPage extends HomePage {
	private static final Logger log = LogManager.getLogger(MultiFactorAuthPage.class);

	public MultiFactorAuthPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "h3.Loginpagehead")
	private WebElement lblHeader;

	public String getMFAPageHeader() {
		log.info("Fetching the MFA page header.");
		String label = getText(lblHeader);
		log.info("Login page header is {}: " + label);
		return label;
	}

	public boolean isPagelabelHeaderVisible() {
		log.info("Page header label visibility Status :" + lblHeader.isDisplayed());
		return lblHeader.isDisplayed();
	}

	@FindBy(css = "div#authenticationFirstStep>p>span")
	private WebElement lblWherLikeToSend;

	public String getlblWherLikeToSend() {
		log.info("Fetching lable Where would you like us to send your authentication code?.");
		String label = getText(lblWherLikeToSend);
		log.info("Where would you like us to send {}: " + label);
		return label;
	}

	public boolean isWhereWouldYouSendLabelVisible() {
		log.info(
				"Where would you like us to send your authentication code? Status :" + lblWherLikeToSend.isDisplayed());
		return lblWherLikeToSend.isDisplayed();
	}

	@FindBy(css = "[id='radioemailauthen']")
	private WebElement radioBtnEmail;

	public boolean isEmailRadioBtnVisible() {
		log.info("Email Radio button Status :" + radioBtnEmail.isDisplayed());
		return radioBtnEmail.isDisplayed();
	}

	public void clickEmailRadioBtn() {
		log.info("Clicking on the Email radio button.");
		click(radioBtnEmail);
		log.info("Email Radio button clicked successfully.");
	}

	@FindBy(css = "label[For = 'emailauthen']")
	private WebElement lblEmailRadioBtnTxt;

	public String getlblSendCodeToEmail() {
		log.info("Fetching lable Send a code to my email address");
		String label = getText(lblEmailRadioBtnTxt);
		log.info("Send Code to Email address {}: " + label);
		return label;
	}

	public boolean isSendCodeToEmailLabelVisible() {
		log.info("Send a code to my email address Status :" + lblEmailRadioBtnTxt.isDisplayed());
		return lblEmailRadioBtnTxt.isDisplayed();
	}

	@FindBy(css = "[id='radiombnoauth']")
	private WebElement radioBtnText;

	public String getRadioBtnText() {
		log.info("Fetching the Email Radio button Text");
		String label = getText(radioBtnText);
		log.info("Login page header is {}: " + label);
		return label;
	}

	public boolean isRadioBtnTextVisible() {
		log.info("Email Radio button Text Status :" + radioBtnText.isDisplayed());
		return radioBtnText.isDisplayed();
	}
	
	public void clickRadioBtnTextBtn() {
		log.info("Clicking on the Email radio button.");
		click(radioBtnText);
		log.info("Email Radio button clicked successfully.");
	}

	@FindBy(css = "label[label[For = 'mbnoauth']")
	private WebElement lblMobRadioBtnTxt;

	public String getlblSendTextToMob() {
		log.info("Fetching lable Send a text to my mobile phone number");
		String label = getText(lblMobRadioBtnTxt);
		log.info("Send a text to my mobile phone number {}: " + label);
		return label;
	}

	public boolean isSendTextToMobLabelVisible() {
		log.info("lable Send a text to my mobile phone number Status :" + lblMobRadioBtnTxt.isDisplayed());
		return lblMobRadioBtnTxt.isDisplayed();
	}

	@FindBy(css = "input#agrretrm")
	private WebElement chkboxTermsAndCondition;

	public boolean ischeckBoxVisibleTC() {
		log.info("I agree to the Terms & Conditions Check Box Status :" + chkboxTermsAndCondition.isDisplayed());
		return chkboxTermsAndCondition.isDisplayed();
	}

	public void clickCheckBoxTC() {
		log.info("Clicking on I agree to the Terms & Conditions Check Box");
		click(chkboxTermsAndCondition);
		log.info("Terms & Conditions Check Box clicked successfully.");
	}

	@FindBy(css = "[id='anchor_tc']")
	private WebElement lblTermsCondition;

	public String gettxtTermsCondition() {
		log.info("Fetching lable I agree to the Terms & Conditions Check Box.");
		String label = getText(lblTermsCondition);
		log.info("Label Terms & Conditions Check Box.{}: " + label);
		return label;
	}

	public boolean isTextTermsConditionVisible() {
		log.info("lable I agree to the Terms & Conditions Check Box. Status :" + lblTermsCondition.isDisplayed());
		return lblTermsCondition.isDisplayed();
	}

	@FindBy(css = "div.trmcondibox>span>a")
	private WebElement lnkTermsCondition;

	public boolean islnkTermsConditionVisible() {
		log.info("Terms & Conditions Link Status :" + lnkTermsCondition.isDisplayed());
		return lnkTermsCondition.isDisplayed();
	}

	public void clicklnkTermsCondition() {
		log.info("Clicking on I agree to the Terms & Conditions Link");
		click(lnkTermsCondition);
		log.info("I agree to the Terms & Conditions Link clicked successfully.");
	}

	@FindBy(css = "div.button_sec>input.cancel-button")
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
		log.info("Clicking on I agree to the Terms & Conditions Link");
		click(btnCancel);
		log.info("I agree to the Terms & Conditions Link clicked successfully.");
	}


	@FindBy(css = "div.button_sec>input.submit-button")
	private WebElement btnNext;

	public String getbtnNext() {
		log.info("Fetching the Next button Text");
		String label = getText(btnNext);
		log.info("Next button Text is {}: " + label);
		return label;
	}

	public void clickbtnNext() {
		log.info("Click Next button Text");
		click(btnNext);
	}

	public boolean isNextBtnVisible() {
		log.info("Next Button Status :" + btnNext.isDisplayed());
		return btnNext.isDisplayed();
	}

	@FindBy(css = "div.emailid_authen>p")
	private WebElement lblCodeSentOnEmail;

	public String getlblCodeSentOnEmail() {
		log.info("Fetching text We've just sent a code to Email");
		String label = getText(lblCodeSentOnEmail);
		log.info("We've just sent a code to Email is {}: " + label);
		return label;
	}

	public boolean islblCodeSentOnEmailVisible() {
		log.info("We've just sent a code to Email Status :" + lblCodeSentOnEmail.isDisplayed());
		return lblCodeSentOnEmail.isDisplayed();
	}

	@FindBys(@FindBy(css = "div#divOtp>input"))
	private List<WebElement> inputOTPFields;

	public List<WebElement> getInputOTPFields() {
		return inputOTPFields;
	}
	
	/*
	 * public boolean isinputOTPFieldsVisible() {
	 * log.info("Input OTP Field Status :" + inputOTPFields.isDisplayed()); return
	 * inputOTPFields.isDisplayed();
	 * 
	 * }
	 */
	@FindBy(css = "a#anchorResendOtp")
	private WebElement lnkResendOTPCode;

	public boolean islnkResendOTPCodeVisible() {
		log.info("Resend authentication code Link Status :" + lnkResendOTPCode.isDisplayed());
		return lnkResendOTPCode.isDisplayed();
	}

	public void clicklnkResendOTPCode() {
		log.info("Clicking on Resend authentication code Link");
		click(lnkResendOTPCode);
		log.info("Resend authentication code Link clicked successfully.");
	}
	
	public String gettxtResendOTPCode() {
		log.info("Fetching the OTP");
		String label = getText(lnkResendOTPCode);
		log.info("Text is {}: " + label);
		return label;
	}
	
	public String gettxtResendOTPCodeClass() {
        String timer = getAttribute(lnkResendOTPCode, "class");
        return timer;
    }
	
	
	@FindBy(css = ".txtcode_inpt input")
	private WebElement txtBoxMfaOtp;
    
	public void populateRequestIdBoxOTP(String requestIdOTP) {
        log.info("Registration MFA Otp {} :" + requestIdOTP);
        sendKeys(txtBoxMfaOtp,requestIdOTP);
        log.info("Username populated successfully.");
    }
	
	public boolean istxtBoxMfaOtpVisible() {
    	log.info("txt Box Mfa Otp visibility Status :" + txtBoxMfaOtp.isDisplayed());
    	return txtBoxMfaOtp.isDisplayed();    	
    }

	@FindBy(css = "#time")
	private WebElement lblOTPTimer;

	public String getlblOTPTimer() {
		log.info("Fetching the OTP Timer Text");
		String label = getText(lblOTPTimer);
		log.info("Timer Text is {}: " + label);
		return label;
	}

	public boolean isTimerVisible() {
		log.info("OTP Time Status :" + lblOTPTimer.isDisplayed());
		return lblOTPTimer.isDisplayed();
	}

	@FindBy(css = "span#spn_verificationMobiletxtRecived")
	private WebElement lblCodeHasBeenSentMsg;

	public String getlblCodeHasBeenSentMsg() {
		log.info("Fetching the Message code has send Text");
		String label = getText(lblCodeHasBeenSentMsg);
		log.info("Message code has send Text is {}: " + label);
		return label;
	}

	public boolean islblCodeHasBeenSentMsgVisible() {
		log.info("Message code has send Status :" + lblCodeHasBeenSentMsg.isDisplayed());
		return lblCodeHasBeenSentMsg.isDisplayed();
	}

	@FindBy(css = "input#BtnCancelOtp")
	private WebElement btnCancelOTP;

	public String getbtnCancelOTP() {
		log.info("Fetching the OTP Cancel button Text");
		String label = getText(btnCancelOTP);
		log.info("OTP Cancel button Text is {}: " + label);
		return label;
	}

	public boolean isOTPCancelBtnVisible() {
		log.info("OTP Cancel Button Status :" + btnCancelOTP.isDisplayed());
		return btnCancelOTP.isDisplayed();
	}
	
	public void clickOTPCancelBtn() {
		log.info("Clicking the Cancel button Text");
		click(btnCancelOTP);
	}


	@FindBy(css = "input#BtnSubmitOtp")
	private WebElement btnSubmitOTP;

	public String getbtnSubmitOTP() {
		log.info("Fetching the Submit button Text");
		String label = getText(btnSubmitOTP);
		log.info("Submit button Text is {}: " + label);
		return label;
	}

	public void clickbtnSubmitOTP() {
		log.info("Clicking the Submit button Text");
		click(btnSubmitOTP);
	}

	public boolean isSubmitBtnVisible() {
		log.info("OTP Submit Button Status :" + btnSubmitOTP.isDisplayed());
		return btnSubmitOTP.isDisplayed();
	}

	@FindBy(css = "div#authenticationThirdStep>p")
	private WebElement lblExceededResendOTP;

	public String getlblExceededResendOTP() {
		log.info("Fetching lable Exceeded Resend OTP");
		String label = getText(lblExceededResendOTP);
		log.info("Exceeded Resend OTP {}: " + label);
		return label;
	}

	public boolean isExceededResendOTPVisible() {
		log.info("lable Exceeded Resend OTP Status :" + lblExceededResendOTP.isDisplayed());
		return lblExceededResendOTP.isDisplayed();
	}

	@FindBy(css = "div#authenticationThirdStep>p>a")
	private WebElement lnkClickToLoginAgin;

	public boolean islnkClickToLoginAgin() {
		log.info("Click to Login again Link Status :" + lnkClickToLoginAgin.isDisplayed());
		return lnkClickToLoginAgin.isDisplayed();
	}

	public void clicklnkClickToLoginAgin() {
		log.info("Clicking on Login again Link");
		click(lnkClickToLoginAgin);
		log.info("Login again Link clicked successfully.");
	}

	@FindBy(css = "#ML_No_emailandPhone")
	private WebElement lblNoEmailMobileMessage;

	public String getlblNoEmailMobileMessage() {
		log.info("Fetching the No Email Mobile Message");
		String label = getText(lblNoEmailMobileMessage);
		log.info("No Email  Mobile Message Text is {}: " + label);
		return label;
	}

	public boolean islblNoEmailMobileMessageVisible() {
		log.info("No Email  Mobile Message Status :" + lblNoEmailMobileMessage.isDisplayed());
		return lblNoEmailMobileMessage.isDisplayed();
	}
	
	@FindBy(css = "[id='btnclose']")
	private WebElement btnTandCOK;

	public void isClickbtnTandCOK() {
		log.info("Clicking on Login again Link");
		click(btnTandCOK);
		log.info("Login again Link clicked successfully.");
	}
	
	@FindBy(css = "h3.Loginpagehead")
	private WebElement lblMultiFatorAuth;

	public String getlblMultiFatorAuth() {
		log.info("Fetching the No Email Mobile Message");
		String label = getText(lblMultiFatorAuth);
		log.info("No Email  Mobile Message Text is {}: " + label);
		return label;
	}
	
	@FindBy(css = "span#spn_verificationMobiletxtRecived")
	private WebElement lblCodeHasBeenSentMsgMfap;

	public String getlblCodeHasBeenSentMsgMfap() {
		log.info("Fetching Message");
		String label = getText(lblCodeHasBeenSentMsgMfap);
		log.info(" Text is {}: " + label);
		return label;
	}
	
	@FindBy(css = "label[For = 'emailauthen']")
	private WebElement lblEmailRadioBtnTxtMfap;

	public String getlblEmailRadioBtnTxtMfap() {
		log.info("Fetching Message");
		String label = getText(lblEmailRadioBtnTxtMfap);
		log.info(" Text is {}: " + label);
		return label;
	}
	
	@FindBy(css = "input#emailauthen")
	private WebElement radioBtnEmailMfap;

	public boolean isradioBtnEmailVisible() {
		log.info("Message code has send Status :" + radioBtnEmailMfap.isDisplayed());
		return radioBtnEmailMfap.isDisplayed();
	}
	
	public void isClickRadioBtnEmail() {
		log.info("Clicking on Login again Link");
		click(radioBtnEmailMfap);
		log.info("Login again Link clicked successfully.");
	}
	
	@FindBy(css = "input#mbnoauth")
	private WebElement radioBtntxtMfap;

	public boolean isradioBtntxtMfapVisible() {
		log.info("Message code has send Status :" + radioBtntxtMfap.isDisplayed());
		return radioBtntxtMfap.isDisplayed();
	}
	
	public void isClickRadioBtntxt() {
		log.info("Clicking on Login again Link");
		click(radioBtntxtMfap);
		log.info("Login again Link clicked successfully.");
	}
	
	@FindBy(css = ".wlcm_to_scm h3")
	private WebElement lblWelcomePopupHeading;

	public boolean isWelcomePopupHeadinVisible() {
		log.info("Message code has send Status :" + lblWelcomePopupHeading.isDisplayed());
		return lblWelcomePopupHeading.isDisplayed();
	}
	
	@FindBy(css = "button#btnclosepopup_abthome.close")
	private WebElement btnCloseAboutMyHome;
	
	public void ClickbtnCloseAboutMyHome() {
		log.info("Clicking on Login again Link");
		click(btnCloseAboutMyHome);
		log.info("Login again Link clicked successfully.");
	}
	
	
	//lblWelcomePopupHeadingDbp = .wlcm_to_scm h3
	//btnCloseAboutMyHomeDbp = button#btnclosepopup_abthome.close
	
	
	// lblHeaderMfap = h3.Loginpagehead
	// lblWherLikeToSendMfap = div#authenticationFirstStep>p>span
	// radioBtnEmailMfap = input#emailauthen
	// lblEmailRadioBtnTxtMfap = label[For = 'emailauthen']
	// radioBtnTextMfap = input#mbnoauth
	// lblRadioBtnTextMfap = label[For = 'mbnoauth']
	// chkboxTermsConditionMfap = input#agrretrm
	// lblTermsConditionMfap = div.trmcondibox>span
	// lnkTermsConditionMfap = div.trmcondibox>span>a
	// btnCancelMfap = div.button_sec>input.cancel-button
	// btnNextMfap = div.button_sec>input.submit-button
	// lblCodeSentOnEmailMfap = div.emailid_authen>p
	// inputOTPFieldsMfap = div#divOtp>input
	// lnkResendOTPCodeMfap = a#anchorResendOtp
	// lblOTPTimerMfap = #time
	// lblCodeHasBeenSentMsgMfap = span#spn_verificationMobiletxtRecived
	// btnCancelOTPMfap = input#BtnCancelOtp
	// btnSubmitOTPMfap = input#BtnSubmitOtp
	// lblExceededResendOTPMfap = div#authenticationThirdStep>p
	// lnkClickToLoginAginMfap = div#authenticationThirdStep>p>a
	// lblNoEmailMobileMessageMfap = #ML_No_emailandPhone
}

package demo.pageobjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.pageObjects.scp.HomePage;

public class PreLoginPage extends HomePage {
    private static final Logger log = LogManager.getLogger(PreLoginPage.class);

    public PreLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(css = "[id='txtAccountNumber']")
	private WebElement txt_accountNumber;
    
    public void populateAccountNumber(String accountNumber) {
		log.info("Populating username {} :" + accountNumber);
		sendKeys(txt_accountNumber, accountNumber);
		log.info("Username populated successfully.");
	}
    
    public void clearAccountNumberField() {
		clear(txt_accountNumber);
		log.info("Account Number field cleared {}");
	}
    
    @FindBy(css = "[id='txtPhoneNumber']")
	private WebElement txt_phoneNumber;
    
    public void populatePhoneNumber(String phoneNumber) {
		log.info("Populating username {} :" + phoneNumber);
		sendKeys(txt_phoneNumber, phoneNumber);
		log.info("Username populated successfully.");
	}
    
    public void clearPhoneNumberField() {
		clear(txt_phoneNumber);
		log.info("Phone Number field cleared {}");
	}

    @FindBy(css = "[id='txtLogin']")
	private WebElement txt_username;
    
    public void populateUserName(String userName) {
		log.info("Populating username {} :" + userName);
		sendKeys(txt_username, userName);
		log.info("Username populated successfully.");
	}
    
    public void clearUsernameField() {
		clear(txt_username);
		log.info("Username field cleared {}");
	}
    
    @FindBy(css = "[id='txtpwd']")
	private WebElement txt_password;
    
    public void populatePassword(String password) {
		log.info("Populating password {} :" + password);
		sendKeys(txt_password, password);
		log.info("Password populated successfully.");
	}
    
    public void clearPasswordField() {
		clear(txt_password);
		log.info("Password field cleared {}");
	}
    
    @FindBy(css = "#btnlogin")
	private WebElement btn_sign_in;
    
    public void clickSignInBtn() {
		log.info("Clicking the sign in button.");
		click(btn_sign_in);
		log.info("Sign in button clicked successfully.");
	}
    
    @FindBy(css = "#btnSubmitPayment")
	private WebElement btn_next;
    
    public void clickNextBtn() {
		log.info("Clicking the next button.");
		click(btn_next);
		log.info("Next button clicked successfully.");
	}
    
    @FindBy(css = ".error_messagecommon")
	private WebElement lbl_error_message;

	public String getErrorMessage() {
		String toast_msg = getText(lbl_error_message);
		log.info("Label on toast message {}: " + toast_msg);
		return toast_msg;
	}
    
    @FindBy(css = "#logo-container")
	private WebElement img_header_logo;

	public Boolean isCompanyLogoVisible() {
		log.info("Checking the visibility of company logo on the page.");
		log.info("Company logo visibility status {}: " + isElementVisible(img_header_logo));
		return isElementVisible(img_header_logo);
	}
	
	@FindBy(css = "div.tagove-livechat-widget .aio-widget-frame iframe")
	private WebElement iFrmOpenChatWindow;
	@FindBy(css = ".grecaptcha-logo")
	private WebElement captcha;

	public Boolean isLoginPage(String url, String title) {
		Boolean isLoginPage = false;
		log.info("Checking that the current page is login page.");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isLoginPage = true;
		log.info("The current page is login page {}: " + isLoginPage);
		return isLoginPage;
	}
	
	@FindBy(css = "#LanguageDrpdwn_sample")
	private WebElement dd_language;

	public Boolean isLanguageDropdownVisible() {
		log.info("Checking the visibility of language dropdown on the page.");
		log.info("Language dropdown visibility status {}: " + isElementVisible(dd_language));
		return isElementVisible(dd_language);
	}
	
	@FindBy(css = "i.lan_txt_hide")
	private WebElement dd_language_selected_option;

	public String getLanguageDropdownSelectedOption() {
		log.info("Checking the selected option in language dropdown on the page.");
		String option = getText(dd_language_selected_option);
		log.info("Language dropdown selected option value {}: " + option);
		return option;
	}
	
	@FindBy(css = ".loginPaper .Loginpagehead")
	private WebElement lbl_login_header;

	public String getLoginPageHeader() {
		log.info("Fetching the login page header.");
		String label = getText(lbl_login_header);
		log.info("Login page header is {}: " + label);
		return label;
	}
	
	
	public Boolean isUsernameTxtVisible() {
		log.info("Checking that the username field is visible on the login page.");
		return isElementVisible(txt_username);
	}
	
	@FindBy(css = "#txtLogin ~ label[class='effect_lbl']")
	private WebElement lbl_username;

	public String getUsernameLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_username);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(css = "#txtpwd ~ label[class='effect_lbl']")
	private WebElement lbl_password;
	

	public Boolean isPasswordTxtVisible() {
		log.info("Checking that the password field is visible on the login page.");
		return isElementVisible(txt_password);
	}

	public String getPasswordLabel() {
		log.info("Fetching the password placeholder.");
		String label = getText(lbl_password);
		log.info("Password placeholder {}: " + label);
		return label;
	}
	
	@FindBy(css = "input#rmbrme")
	private WebElement txt_remember_me;
	@FindBys(@FindBy(css = ".mtrl_chkbx_new span"))
	private List<WebElement> chb_remember_mes;
	@FindBy(css = ".mtrl_chkbx_new span")
	private WebElement chb_remember_me;

	public Boolean isRememberMeChbVisible() {
		log.info("Remember me checkbox visible status {}: " + chb_remember_mes.size());
		return isElementVisibleAlt(chb_remember_mes);
	}
	

	public String getSignInBtnLabel() {
		String label = getText(btn_sign_in);
		log.info("Sign in button label {}: " + label);
		return label;
	}
	
	@FindBy(linkText = "Forgot Username")
	private WebElement lnk_forgot_username;

	public String getForgotUsernameLabel() {
		String label = getText(lnk_forgot_username);
		log.info("Forgot Username Label {}: " + label);
		return label;
	}
	
	
	@FindBy(linkText = "Forgot Password")
	private WebElement lnk_forgot_password;
	
	public String getForgotPasswordLabel() {
		String label = getText(lnk_forgot_password);
		log.info("Forgot Username Label {}: " + label);
		return label;
	}
		
	@FindBy(css = ".sign_in_text")
	private WebElement lnk_problems_sign_in;

	public String getProblemSigningInLabel() {
		String label = getText(lnk_problems_sign_in);
		log.info("Forgot Username Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#btnResidentialType")
	private WebElement lnk_register;

	public String getRegisterLinkLabel() {
		String label = getAttribute(lnk_register, "value");
		log.info("Forgot Username Label {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[@id=\"form2\"]/section/div[2]/div/div[4]/ul/a[1]")
	private WebElement lnk_advancedservices;

	public String getAdvancedServicesLinkLabel() {
		String label = getText(lnk_advancedservices);
		log.info("Forgot Username Label {}: " + label);
		return label;
	}

	@FindBy(xpath = "//*[@onclick=\"PaymentOption()\"]")
	private WebElement lnk_paybill;

	public String getPayBillLinkLabel() {
		String label = getText(lnk_paybill);
		log.info("Forgot Username Label {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[@id=\"form2\"]/section/div[2]/div/div[4]/ul/a[4]")
	private WebElement lnk_outages;

	public String getOutagesLinkLabel() {
		String label = getText(lnk_outages);
		log.info("Forgot Username Label {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[@id=\"form2\"]/section/div[2]/div/div[4]/ul/a[5]")
	private WebElement lnk_waystosave;

	public String getWaysToSaveLinkLabel() {
		String label = getText(lnk_waystosave).replace("monetization_on", "");
		log.info("Forgot Username Label {}: " + label);
		return label;
	}
	
	public void clickWaysToSaveLnk() {
		log.info("Clicking the payment location button.");
		click(lnk_waystosave);
		log.info("contact us button clicked successfully.");
	}
	
	@FindBy(xpath = "//*[@id=\"efficiencyMenubar\"]/li[1]/a")
	private WebElement lnk_rebates;
	
	public String getRebatesLabel() {
		String label = getText(lnk_rebates);
		log.info("Forgot Username Label {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[@id=\"efficiencyMenubar\"]/li[2]/a")
	private WebElement lnk_programs;
	
	public String getProgramsLabel() {
		String label = getText(lnk_programs);
		log.info("Forgot Username Label {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[@id=\"efficiencyMenubar\"]/li[3]/a")
	private WebElement lnk_savings;
	
	public String getSavingsLabel() {
		String label = getText(lnk_savings);
		log.info("Forgot Username Label {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[@id=\"efficiencyMenubar\"]/li[4]/a")
	private WebElement lnk_educational;
	
	public String getEducationalLabel() {
		String label = getText(lnk_educational);
		log.info("Forgot Username Label {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[@id=\"form2\"]/section/div[2]/div/div[4]/ul/a[6]")
	private WebElement lnk_paymentlocations;

	public String getPaymentLocationsLinkLabel() {
		String label = getText(lnk_paymentlocations).replace("location_on", "");
		log.info("Forgot Username Label {}: " + label);
		return label;
	}
	
	public void clickPaymentLocationsLnk() {
		log.info("Clicking the payment location button.");
		click(lnk_paymentlocations);
		log.info("contact us button clicked successfully.");
	}
	
	public void clickPaymentsLnk() {
		log.info("Clicking the payment button.");
		click(lnk_paybill);
		log.info("contact us button clicked successfully.");
	}
	
	@FindBy(xpath = "//*[@id=\"form2\"]/section/div[2]/div/div[4]/ul/a[8]")
	private WebElement lnk_contactus;

	public String getContactUsLinkLabel() {
		String label = getText(lnk_contactus).replace("call", "");
		log.info("Forgot Username Label {}: " + label);
		return label;
	}
	
	public void clickContactUsLnk() {
		log.info("Clicking the contact us button.");
		click(lnk_contactus);
		log.info("contact us button clicked successfully.");
	}
	
	@FindBy(css = "#idReportWaterLeak")
	private WebElement lnk_reportleaks;

	public String getReportLeaksLinkLabel() {
		String label = getText(lnk_reportleaks);
		log.info("Forgot Username Label {}: " + label);
		return label;
	}
	
	public void clickWaterLeakLnk() {
		log.info("Clicking the contact us button.");
		click(lnk_reportleaks);
		log.info("contact us button clicked successfully.");
	}
	
	@FindBy(xpath = "//*[@id=\"faqlink\"]/a")
	private WebElement lnk_faqs;

	public String getFaqsLinkLabel() {
		String label = getText(lnk_faqs);
		log.info("Forgot Username Label {}: " + label);
		return label;
	}
	
	@FindBy(className = "PreloginConnectMe")
	private WebElement lnk_footercontactus;

	public String getFooterContactUsLinkLabel() {
		String label = getText(lnk_footercontactus);
		log.info("Forgot Username Label {}: " + label);
		return label;
	}
	
	@FindBy(className = "PreloginTnC")
	private WebElement lnk_termsandconditions;

	public String getTermsAndConditionsLinkLabel() {
		String label = getText(lnk_termsandconditions);
		log.info("Forgot Username Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#PreloginPrivacyPolicy")
	private WebElement lnk_privacypolicy;

	public String getPrivacyPolicyLinkLabel() {
		String label = getText(lnk_privacypolicy);
		log.info("Forgot Username Label {}: " + label);
		return label;
	}
	
	public Boolean isRememberMeChbChecked() {
		log.info("Remember me checkbox visible status {}: " + chb_remember_me.isDisplayed());
		return chb_remember_me.isDisplayed();
	}

	public void checkRememberMe() {
		check(chb_remember_me);
		log.info("Remember me checkbox checked.");
	}
	
	public void waitForUserNameFieldVisibility() {
		waitForElementToBeVisible(txt_username);
		log.info("Wait for username field to be displayed.");
	}
	
	public String getPopulatedUsernameValue() {
		log.info("Fetching the auto populated username.");
		return getAttribute(txt_username, "value");
	}
	
	
	@FindBy(css = "select#ML_NCR_DDL_Topic")
    private WebElement dropdown;
	
	public boolean selectlstConnectMeOptions(String option) {
    	boolean b = true;
        selectByVisibleText(dropdown, option);
        log.info("lstConnectMeOptions populated successfully.");
        return b;   
    }
	
	@FindBy(xpath = "//label[text()='Account Number']/..//input")
    private WebElement  txtServiceAccNo;
    
    public boolean isServiceAccNoTxtVisible() {
        log.info("Checking that the Service Acc No field is visible on the Contact Us page.");
        return isElementVisible(txtServiceAccNo);
    }
    
    public void populateServiceAccNo(String accountNo) {
        log.info("Subject {} :" + accountNo);
        sendKeys(txtServiceAccNo, accountNo);
        log.info("Subject populated successfully.");
    }
    
    @FindBy(css = "input[globalize='ML_Master_lbl_CustName']")
    private WebElement txtContactUsCustomerName;
    
    public boolean isCustomerNameTxtVisible() {
        log.info("Checking that the Customer Name field is visible on the Contact Us page.");
        return isElementVisible(txtContactUsCustomerName);
    }
    
    public void populateCustomerName(String customerName) {
        log.info("Customer {} :" + customerName);
        sendKeys(txtContactUsCustomerName, customerName);
        log.info("Customer populated successfully.");
    }
    
    @FindBy(xpath = "//label[text()='Email Address']/..//input")
    private WebElement txtEmailAddress;
    
    public boolean isEmailAddressTxtVisible() {
        log.info("Checking that the Email Address field is visible on the Contact Us page.");
        return isElementVisible(txtEmailAddress);
    }
    
    public void populateEmailAddress(String emailAddress) {
        log.info("Email Address {} :" + emailAddress);
        sendKeys(txtEmailAddress, emailAddress);
        log.info("Email Address populated successfully.");
    }
    
    
    @FindBy(css = "[id='FileUpload1']")
    private WebElement btnChooseFile;
    
    public boolean isChooseFileBtnVisible() {
    	log.info("Choose File Button Status :" + btnChooseFile.isDisplayed());
    	return btnChooseFile.isDisplayed();
    }
    
    public void addAttachmentToChooseFile(String value) {
        sendKeysWithoutCheckingVisibility(btnChooseFile, value);
        log.info("Choose File Button added successfully with File Value " + value);
        ExtentLogger.logInfo("Choose File Button added successfully with File Value " + value);
    }
    
    @FindBy(css = ".Subject.input_effect")
    private WebElement  txtSubject;
    
    public boolean istxtSubjectTxtVisible() {
        log.info("Checking that the Subject field is visible on the Contact Us page.");
        return isElementVisible(txtSubject);
    }
    
    public void populateSubject(String subject) {
        log.info("Subject {} :" + subject);
        sendKeys(txtSubject, subject);
        log.info("Subject populated successfully.");
    }
    
    @FindBy(xpath = "//label[contains(text(),'Comments')]/..//textarea[@globalize='ML_CONNECTME_Lbl_Comments']")
    private WebElement txtComments;
    
    public boolean isCommentsTxtVisible() {
        log.info("Checking that the Comments field is visible on the Contact Us page.");
        return isElementVisible(txtComments);
    }
    
    public void populateComments(String Comments) {
        log.info("Subject {} :" + Comments);
        sendKeys(txtComments, Comments);
        log.info("Comments populated successfully.");
    }
    
    @FindBy(css = "[id='btnNext']")
    private WebElement btnContactUsNext;
    
    public void btnClickNext() {
    	click(btnContactUsNext);
    	log.info("Next Button clicked {}.");
    }
    
    @FindBys(@FindBy(css = "#dvPreviewForm .column"))
    private List<WebElement> lbl_PreviewYourFormColumn;
    

    public List<WebElement> listPreviewYourFormColumn() {
        return lbl_PreviewYourFormColumn;
    }
    
    @FindBys(@FindBy(css = "#dvPreviewForm .value"))
    private List<WebElement> lbl_PreviewYourFormValue;
    
    public List<WebElement> listPreviewYourFormValue() {
        return lbl_PreviewYourFormValue;
    }
    
    @FindBy(css = "input#BtnSubmitCommentNew")
    private WebElement btnContactUsSubmit;
    
    public void btnClickSubmit() {
    	click(btnContactUsSubmit);
    	log.info("Submit Button clicked {}.");
    }
    
    public boolean isSubmitBtnVisible() {
    	log.info("Submit Button Status :" + btnContactUsSubmit.isDisplayed());
    	return btnContactUsSubmit.isDisplayed();
    }
    
    @FindBy(css = ".modal-body #txtBody")
    private WebElement txtPopupThankYou;
    
    public boolean isLblPopupThankYouVisible() {
    	log.info("Thank you lbl Status is:" + txtPopupThankYou.isDisplayed());
    	return txtPopupThankYou.isDisplayed();
    }
    
    
    public String getlblPopupThankYou() {
        log.info("Fetching Thank You lbl");
        String label = getText(txtPopupThankYou);
        log.info("Thank You lbl is {}: " + label);
        return label;
    }
    
    @FindBy(css = "[id='btnok']")
    private WebElement btnContactUsPopupOk;
    
    public void clickContactUsPopupOk() {
    	click(btnContactUsPopupOk);
    	log.info("Ok Button clicked {}.");
    }
    
    
    @FindBy(xpath = "(//span[@title='Contact Us'])[1]")
    private WebElement lbl_PageHeaderPost;
    
    public boolean isPageHeaderPostVisible() {
    	log.info("Page Header Post visibility Status :" + lbl_PageHeaderPost.isDisplayed());
    	return lbl_PageHeaderPost.isDisplayed();
    }
    
    
    @FindBy(css = "ul #social_newtb")
    private WebElement lnkSocialMedia;
    
    public boolean isSocialMediaVisible() {
    	log.info("Social Media visibility Status :" + lnkSocialMedia.isDisplayed());
    	return lnkSocialMedia.isDisplayed();
    }
    
    
    @FindBy(css = ".icon_contact.active a")
    private WebElement lnkContactus;
    
    public boolean isContactusVisible() {
    	log.info("Contact Us visibility Status :" + lnkContactus.isDisplayed());
    	return lnkContactus.isDisplayed();
    }
    
    @FindBy(css = "[globalize='ML_Track_Request']")
    private WebElement Btn_TrackRequest;
    
    public String getTrackRequest() {
        log.info("Fetching Track Request");
        String label = getText(Btn_TrackRequest);
        log.info("Track Request is {}: " + label);
        return label;
    }
    
    public boolean isTrackRequestVisible() {
    	log.info("Track Request Status :" + Btn_TrackRequest.isDisplayed());
    	return Btn_TrackRequest.isDisplayed();
    }
    
    @FindBy(css = ".icon_SavedForms a")
    private WebElement lnk_SavedForms;
    
    public void clickSavedForms() {
        click(lnk_SavedForms);
        log.info("Saved Forms clicked.");
    }
  
    public boolean isSavedFormVisible() {
    	log.info("Page Active Status :" + lnk_SavedForms.isDisplayed());
    	return lnk_SavedForms.isDisplayed();
    }
    
    public boolean isNextBtnVisible() {
    	log.info("Next Button Status :" + btnContactUsNext.isDisplayed());
    	return btnContactUsNext.isDisplayed();
    }
    
    @FindBy(css = "#lblCustomer")
    private WebElement lbl_Customer;
    
    public String getCustomerlbl() {
        log.info("Fetching Customer lbl");
        String label = getText(lbl_Customer);
        log.info("Customer lbl is {}: " + label);
        return label;
    }
    
    public boolean isCustomerlblVisible() {
    	log.info("Customer lbl Status :" + lbl_Customer.isDisplayed());
    	return lbl_Customer.isDisplayed();
    }
    
    @FindBys(@FindBy(css = "select#ML_NCR_DDL_Topic"))
    private WebElement dropdownOptions;
    
    public List<String> getdropdownOptions() {
        log.info("Fetching Post login Customer Service Contact No");
        List<String> dropdownList = getAllOptionsInListBox(dropdownOptions);
        log.info("Post login Customer Service Contact No is {}: " + dropdownList);
        return dropdownList;
    } 
    
    @FindBy(css = "#form2 > div.chatbot > a")
    private WebElement btnChatBox;
    
    public void clickChatBox() {
        click(btnChatBox);
    }
	
    @FindBy(css = ".helptext")
    private WebElement headerChatBox;
    
    public boolean isChatBoxHeaderVisible() {
    	return headerChatBox.isDisplayed();
    }
    public String getChatBoxHeader() {
		String label = getText(headerChatBox);
		return label;
	}
    @FindBy(css = ".logoarea")
    private WebElement logoSCMChatBox;
    
    public boolean isScmLOgoChatBoxVisible() {
    	return logoSCMChatBox.isDisplayed();
    }
    
    @FindBy(css = ".webchat__send-box__main")
    private WebElement txtBoxChatBox;
    
    public boolean isChatTextBoxVisible() {
    	return txtBoxChatBox.isDisplayed();
    }
    
    public void clickChatTextBox() {
        click(txtBoxChatBox);
    }
    
    public void populateTxtMessageBody(String Text) {
    	log.info("Waiting For Visiblity status of MessageBody Text Feild");
    	waitForElementToBeVisible(txtBoxChatBox);
    	log.info("Visiblity status of MessageBody Text Feild:"+ txtBoxChatBox.isDisplayed());
    	log.info("Populating Zip Code feild with" + Text);
      	sendKeys(txtBoxChatBox,Text);
    	}
    
    public WebElement elementTextMessageBody() {
       	log.info("Waiting For Visiblity status of Confirm Routing Number Text Feild");
    	waitForElementToBeVisible(txtBoxChatBox);
    	log.info("Visiblity status of Confirm Routing Number Text Feild:"+ txtBoxChatBox.isDisplayed());
    	return txtBoxChatBox;
    	}
    
    @FindBy(xpath = "//textarea[@data-id=\"webchat-sendbox-input\" and @placeholder = \"Type your message\"]")
    private WebElement txtBoxChattxt;
    
    public void enterDataInChatTextBox(String datafield) {
    	pause(10000);
    	waitForElementToBeVisible(txtBoxChattxt);
    	sendKeys(txtBoxChattxt, datafield);
		log.info("Entering data in thdo you have solar panels field.");
    }
    
    @FindBy(css = "button[title=\"Send\"]")
    private WebElement btnsend;
    
    public boolean isChatTextSendButtonVisible() {
    	return btnsend.isDisplayed();
    }
    public void clickSendBtn() {
        click(btnsend);
    }
    
    @FindBy(css = ".endchatme")
    private WebElement btnendchat;
    
    public void clickEndChatBtn() {
        click(btnendchat);
    }
    
    @FindBy(css = ".cnfrmchat")
    private WebElement btncnfrmchat;
    
    public void clickConfirmEndChatBtn() {
        click(btncnfrmchat);
    }
    
    public Boolean isSignOutPage(String url, String title) {
        Boolean isSignOutPage = false;
        log.info("Checking that the current page is login page.");
        if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
            isSignOutPage = true;
        log.info("The current page is login page {}: " + isSignOutPage);
        return isSignOutPage;
    }
    
    @FindBy(css = ".banner_logged_out h1")
    private WebElement lbl_you_signed_out;

    public String getSignOutSuccessfullyLbl() {
        return getText(lbl_you_signed_out);
    }

    public void waitForSignOutSuccessLbl() {
        waitForElementToBeVisible(lbl_you_signed_out);
    }
    
    @FindBy(css = "a[globalize=ML_signout_txt_signin]")
    private WebElement btn_sign_in_again;

    public void clickSignInAgainBtn() {
        click(btn_sign_in_again);
        log.info("Sign in again button clicked.");
    }
    
    @FindBy(css = "#dropdownMenuButton")
	private WebElement img_profile_icon;

	public void clickImageProfileIco() {
		click(img_profile_icon);
		log.info("Image profile icon clicked {}.");
	}
	
	@FindBy(css = "button.userbtn.logout_header")
	private WebElement lnk_sign_out;

	public Boolean isSignOutLnkVisible() {
		return lnk_sign_out.isDisplayed();
	}
	
	public String getSignOutLinkLabel() {
		return getText(lnk_sign_out);
	}

	public void clickSignOutLnk() {
		pause(500);
		click(lnk_sign_out);
		log.info("Sign out button is clicked.");
	}
}

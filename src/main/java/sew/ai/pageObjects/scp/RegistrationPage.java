package sew.ai.pageObjects.scp;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import sew.ai.driver.Page;

public class RegistrationPage extends LoginPage {

	private static final Logger log = LogManager.getLogger(RegistrationPage.class);

	public RegistrationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[mappingname='FirstName']")
	private WebElement txtBoxFirstName;

	public boolean isFirstNameVisible() {
		log.info("Checking the visibility of First Name on the page.");
		log.info("First Name visibility status : " + isElementVisible(txtBoxFirstName));
		return isElementVisible(txtBoxFirstName);
	}

	public String getAttributeFirstName(String attribute) {

		getAttribute(txtBoxFirstName, attribute);
		log.info("captured attribute value successfully.");
		return attribute;
	}

	public void clearFirstName() {
		clear(txtBoxFirstName);
		log.info("First Name field is cleared ");
	}

	public void populateFirstName(String FirstName) {
		log.info("Populating First Name {} :" + FirstName);
		sendKeys(txtBoxFirstName, FirstName);
		log.info("First Name populated successfully.");
	}

	@FindBy(css = "input[mappingname='LastName']")
	private WebElement txtBoxLastName;

	public boolean isLastNameVisible() {
		log.info("Checking the visibility of First Name on the page.");
		log.info("First Name visibility status : " + isElementVisible(txtBoxLastName));
		return isElementVisible(txtBoxLastName);
	}

	public String getAtrributeLastName(String attribute) {

		getAttribute(txtBoxLastName, attribute);
		log.info("captured attribute value successfully.");
		return attribute;
	}

	public void clearLastName() {
		clear(txtBoxLastName);
		log.info("Last Name field is cleared ");
	}

	public void populateLastName(String LastName) {
		log.info("Populating First Name {} :" + LastName);
		sendKeys(txtBoxLastName, LastName);
		log.info("Last Name populated successfully.");
	}

	@FindBy(css = "#txt_16")
	private WebElement txtBoxEmail;

	public boolean isTxtBoxEmail() {
		log.info("Checking the visibility of First Name on the page.");
		log.info("Email visibility status : " + isElementVisible(txtBoxEmail));
		return isElementVisible(txtBoxEmail);
	}

	public String getTxtBoxEmail(String attribute) {

		getAttribute(txtBoxEmail, attribute);
		log.info("captured attribute value successfully.");
		return attribute;
	}

	public void clearTxtBoxEmail() {
		clear(txtBoxEmail);
		log.info("Email field is cleared ");
	}

	public void populateTxtBoxEmail(String Email) {
		log.info("Populating First Name {} :" + Email);
		sendKeys(txtBoxEmail, Email);
		log.info("Email populated successfully.");
	}

	@FindBy(css = "input[mappingname='PostalCode']")
	private WebElement txtBoxPostalCode;

	public boolean isPostalCodeVisible() {
		log.info("Checking the visibility of PostalCode on the page.");
		log.info("PostalCode visibility status : " + isElementVisible(txtBoxPostalCode));
		return isElementVisible(txtBoxPostalCode);
	}

	public String getPostalCode(String attribute) {

		getAttribute(txtBoxPostalCode, attribute);
		log.info("captured attribute value successfully.");
		return attribute;
	}

	public void clearPostalCode() {
		clear(txtBoxPostalCode);
		log.info("PostalCode field is cleared ");
	}

	public void populatePostalCode(String postalcode) {
		log.info("Populating Postal Code {} :" + postalcode);
		sendKeys(txtBoxPostalCode, postalcode);
		log.info("Postal Code populated successfully.");
	}

	@FindBy(css = "input[mappingname='UserName']")
	private WebElement txtBoxUserName;

	public boolean isUserNameVisible() {
		log.info("Checking the visibility of UserName field on the page.");
		log.info("UserName Field visibility status : " + isElementVisible(txtBoxUserName));
		return isElementVisible(txtBoxUserName);
	}

	public String getAttributeUserName(String attribute) {

		getAttribute(txtBoxUserName, attribute);
		log.info("captured attribute value successfully.");
		return attribute;
	}

	public void populateUserName(String UserName) {
		log.info("Populating User Name {} :" + UserName);
		sendKeys(txtBoxUserName, UserName);
		log.info("User Name populated successfully.");
	}

	public void clearUsernameField() {
		clear(txtBoxUserName);
		log.info("Username field cleared {}");
	}

	public boolean populateUserName() {

		log.info("Checking the visibility of UserName field on the page.");
		log.info("UserName Field visibility status : " + isElementVisible(txtBoxUserName));
		return isElementVisible(txtBoxUserName);
	}

	@FindBy(css = "input[mappingname='Password']:not([style='display:none;']")
	private WebElement txtBoxPassword;

	public boolean isPasswordVisible() {
		log.info("Checking the visibility of Password field on the page.");
		log.info("Password Field visibility status : " + isElementVisible(txtBoxPassword));
		return isElementVisible(txtBoxPassword);
	}

	public String getAttributePassword(String attribute) {

		getAttribute(txtBoxPassword, attribute);
		log.info("captured attribute value successfully.");
		return attribute;
	}

	public void clearPassword() {
		clear(txtBoxPassword);
		log.info("Password field is cleared ");
	}

	public void populatePassword(String Password) {
		log.info("Populating Password {} :" + Password);
		sendKeys(txtBoxPassword, Password);
		log.info("Password populated successfully.");
	}

	@FindBy(css = "input[mappingname='ConfirmPassword']:not([style='display:none;'])")
	private WebElement txtBoxConfirmPassword;

	public Boolean isConfirmPasswordVisible() {
		log.info("Checking the visibility of Confirm Password field on the page.");
		log.info("Confirm Password Field visibility status : " + isElementVisible(txtBoxConfirmPassword));
		return isElementVisible(txtBoxConfirmPassword);
	}

	public String getConfirmPassword(String attribute) {

		getAttribute(txtBoxConfirmPassword, attribute);
		log.info("captured attribute value successfully.");
		return attribute;
	}

	public void clearConfirmPassword() {
		clear(txtBoxConfirmPassword);
		log.info("Confirm Password field is cleared ");
	}

	public void populateConfirmPassword(String ConfirmPassword) {
		log.info("Populating Cinfirm Password {} :" + ConfirmPassword);
		sendKeys(txtBoxConfirmPassword, ConfirmPassword);
		log.info("Confirm Password populated successfully.");
	}

	@FindBy(css = "input[mappingname = 'UtilityAccountNumber']")
	private WebElement txtBoxAccountNumber;

	public boolean isUtilityAccountNumberVisible() {
		log.info("Checking the visibility of UtilityAccountNumber on the page.");
		log.info("UtilityAccountNumber visibility status {}: " + isElementVisible(txtBoxAccountNumber));
		return isElementVisible(txtBoxAccountNumber);
	}

	public void clearUtilityAccountNumber() {
		clear(txtBoxAccountNumber);
		log.info("UtilityAccountNumber field is cleared ");
	}

	public void populateUtilityAccountNumber(String UtilityAccountNumber) {
		log.info("Populating UtilityAccountNumber {} :" + UtilityAccountNumber);
		sendKeys(txtBoxAccountNumber, UtilityAccountNumber);
		log.info("UtilityAccountNumber populated successfully.");
	}

	public String getAttributeUtilityAccountNumber(String attribute) {

		getAttribute(txtBoxAccountNumber, attribute);
		log.info("captured attribute value successfully.");
		return attribute;
	}

	@FindBy(css = "input[mappingname='HintAns']")
	private WebElement txtBoxSecurityAnswer1;

	public void populateTxtBoxSecurityAnswer1(String MeterNumber) {
		log.info("Populating txtBoxSecurityAnswer1 {} :" + MeterNumber);
		sendKeys(txtBoxSecurityAnswer1, MeterNumber);
		log.info("txtBoxSecurityAnswer1 populated successfully.");
	}

	@FindBy(css = "input[mappingname='HintsAns2']")
	private WebElement txtBoxSecurityAnswer2;

	public void populateTxtBoxSecurityAnswer2(String MeterNumber) {
		log.info("Populating txtBoxSecurityAnswer2 {} :" + MeterNumber);
		sendKeys(txtBoxSecurityAnswer2, MeterNumber);
		log.info("txtBoxSecurityAnswer2 populated successfully.");
	}

	@FindBy(css = "input[mappingname='MeterNumber']")
	private WebElement txtBoxMeterNumber;

	public Boolean isTxtBoxMeterNumberVisible() {
		log.info("Checking the visibility of Confirm Password field on the page.");
		log.info("Meter Number Field visibility status : " + isElementVisible(txtBoxConfirmPassword));
		return isElementVisible(txtBoxMeterNumber);
	}

	public String getAttributetxtBoxMeterNumber(String attribute) {

		getAttribute(txtBoxMeterNumber, attribute);
		log.info("captured attribute value successfully.");
		return attribute;
	}

	public void clearTxtBoxMeterNumber() {
		clear(txtBoxMeterNumber);
		log.info("Confirm Password field is cleared ");
	}

	public void populateTxtBoxMeterNumber(String MeterNumber) {
		log.info("Populating Meter Number {} :" + MeterNumber);
		sendKeys(txtBoxMeterNumber, MeterNumber);
		log.info("Meter Number populated successfully.");
	}

	@FindBy(css = "input[mappingname='SSNNumber']")
	private WebElement txtBoxSSN;

	public boolean isSSNNumberVisible() {
		log.info("Checking the visibility of SSN/ZIPNumber on the page.");
		log.info("SSNNumber visibility status {}: " + isElementVisible(txtBoxSSN));
		return isElementVisible(txtBoxSSN);
	}

	public String getAttributeSSNNumber(String attribute) {

		getAttribute(txtBoxSSN, attribute);
		log.info("captured attribute value successfully.");
		return attribute;
	}

	public void clearSSNNumber() {
		clear(txtBoxSSN);
		log.info("SSN Number field is cleared ");
	}

	public void populateSSNNumber(String SSNCode) {
		log.info("Populating SSN Number {} :" + SSNCode);
		sendKeys(txtBoxSSN, SSNCode);
		log.info("SSN Number populated successfully.");
	}

	@FindBy(css = "input[mappingname = 'EmailID']")
	private WebElement txtBoxEmailId;

	public boolean isEmailIdVisible() {
		log.info("Checking the visibility of EmailID on the page.");
		log.info("EmailID visibility status : " + isElementVisible(txtBoxEmailId));
		return isElementVisible(txtBoxEmailId);
	}

	public void clearEmailId() {
		clear(txtBoxEmailId);
		log.info("Email Id field is cleared ");
	}

	public void populateEmailid(String EmailId) {
		log.info("Populating SSN Number {} :" + EmailId);
		sendKeys(txtBoxEmailId, EmailId);
		log.info("Email Id populated successfully.");
	}

	@FindBy(css = "input[mappingname = 'EmailID']~label[class='effect_lbl']")
	private WebElement lbl_emailid;

	public String getEmailIdLabel() {
		log.info("Fetching the EmailID placeholder.");
		String label = getText(lbl_emailid);
		log.info("EmailID placeholder {}: " + label);
		return label;
	}

	@FindBy(css = "input[mappingname='MobileNumber']")
	private WebElement txtBoxMobileNumber;

	public boolean isMobileNumberVisible() {
		log.info("Checking the visibility of Mobile Number on the page.");
		log.info("Mobile Number visibility status : " + isElementVisible(txtBoxMobileNumber));
		return isElementVisible(txtBoxMobileNumber);
	}

	public void clearMobileNumber() {
		clear(txtBoxMobileNumber);
		log.info("Mobile Number field is cleared ");
	}

	public void populateMobileNumber(String MOBILENUMBER) {
		log.info("Populating MOBILE Number {} :" + MOBILENUMBER);
		sendKeys(txtBoxMobileNumber, MOBILENUMBER);
		log.info("MOBILE NUMBER populated successfully.");
	}

	@FindBy(css = "input[mappingname='DrivingLicence']")
	private WebElement txtBoxDrivingLicence;

	public boolean isDrivingLicenceVisible() {
		log.info("Checking the visibility of Driving Licence on the page.");
		log.info("Driving Licence visibility status : " + isElementVisible(txtBoxDrivingLicence));
		return isElementVisible(txtBoxDrivingLicence);
	}

	public String getAttributeDrivingLicence(String attribute) {

		getAttribute(txtBoxDrivingLicence, attribute);
		log.info("captured attribute value successfully.");
		return attribute;
	}

	public void clearDrivingLicence() {
		clear(txtBoxDrivingLicence);
		log.info("Driving Licence field is cleared ");
	}

	public void populateDrivingLicence(String DRIVINGLICENCE) {
		log.info("Populating DRIVINGLIENCE {} :" + DRIVINGLICENCE);
		sendKeys(txtBoxDrivingLicence, DRIVINGLICENCE);
		log.info("Driving Licence populated successfully.");
	}

	@FindBy(css = "input[mappingname='StreetNumber']")
	private WebElement txtBoxStreetNumber;

	public boolean isStreetNumberVisible() {
		log.info("Checking the visibility of Street Number on the page.");
		log.info("Street Number visibility status : " + isElementVisible(txtBoxStreetNumber));
		return isElementVisible(txtBoxStreetNumber);
	}

	public String getAttributeStreetNumber(String attribute) {

		getAttribute(txtBoxStreetNumber, attribute);
		log.info("captured attribute value successfully.");
		return attribute;
	}

	public void populateStreetNumber(String STREETNUMBER) {
		log.info("Populating Street Number {} :" + STREETNUMBER);
		sendKeys(txtBoxStreetNumber, STREETNUMBER);
		log.info("Street Number populated successfully.");
	}

	@FindBy(css = "input[mappingname='CustomerNo']")
	private WebElement txtBoxCustomerNo;

	public void populateCustomerNo(String CUSTOMERNO) {
		log.info("Populating Customer No {} :" + CUSTOMERNO);
		sendKeys(txtBoxCustomerNo, CUSTOMERNO);
		log.info("Customer No populated successfully.");
	}

	@FindBy(css = "input[globalize='ML_Registration_FID']")
	private WebElement txtBoxFidTin;

	public Boolean isTxtBoxFidTinVisible() {
		log.info("Checking the visibility of Confirm Password field on the page.");
		log.info("FID Number Field visibility status : " + isElementVisible(txtBoxFidTin));
		return isElementVisible(txtBoxFidTin);
	}

	public String getAttributeTxtBoxFidTin(String attribute) {

		getAttribute(txtBoxFidTin, attribute);
		log.info("captured attribute value successfully.");
		return attribute;
	}

	public void cleartxtBoxFidTin() {
		clear(txtBoxFidTin);
		log.info("FID Number field is cleared ");
	}

	public void populateTxtBoxFidTin(String fidTinNumber) {
		log.info("Populating Cinfirm Password {} :" + fidTinNumber);
		sendKeys(txtBoxFidTin, fidTinNumber);
		log.info("FID Number populated successfully.");
	}

	@FindBy(css = "input[mappingname='MobileNumber']")
	private WebElement txtBoxPrimaryContactNumber;

	public Boolean isTxtBoxPrimaryContactNumber() {
		log.info("Checking the visibility of Confirm Password field on the page.");
		log.info("Confirm Password Field visibility status : " + isElementVisible(txtBoxPrimaryContactNumber));
		return isElementVisible(txtBoxPrimaryContactNumber);
	}

	public String getAttributeTxtBoxPrimaryContactNumber(String attribute) {

		getAttribute(txtBoxPrimaryContactNumber, attribute);
		log.info("captured attribute value successfully.");
		return attribute;
	}

	public void cleartxtBoxPrimaryContactNumber() {
		clear(txtBoxPrimaryContactNumber);
		log.info("Primary Phone number field is cleared ");
	}

	public void populatetxtBoxPrimaryContactNumber(String primaryPhoneNumber) {
		log.info("Populating Primary Phone number{} :" + primaryPhoneNumber);
		sendKeys(txtBoxPrimaryContactNumber, primaryPhoneNumber);
		log.info("Primary Phone number populated successfully.");
	}

	@FindBy(xpath = "//div[contains(@id, 'Page')][not(contains(@style, 'none'))]//div[contains(@class, 'left_side_sec')]/input[not(contains(@style, 'none'))]")
	private List<WebElement> txtBoxAllActiveRegFields;

	public List<WebElement> getTxtBoxAllActiveRegFields() {
		log.info("Fetching all Active Fields Labels.");
		return txtBoxAllActiveRegFields;
	}

	@FindBy(css = ".txtcode_inpt input")
	private WebElement txtBoxMfaOtp;

	public void populateBoxMfaOtp(String mfaOtp) {
		log.info("Registration MFA Otp {} :" + mfaOtp);
		sendKeys(txtBoxMfaOtp, mfaOtp);
		log.info("Username populated successfully.");
	}

	@FindBy(css = "#time")
	private WebElement txtMfaOtpTimer;
	@FindBy(css = "id=btnResidentialType")
	private WebElement btnRegisterCommercial;

	public void registerCommercialBtn() {
		click(btnRegisterCommercial);
		log.info("RegisterCommercial Button clicked successfully.");
	}

	@FindBy(css = "input#NextBtn.submit-button")
	private WebElement btnRegister;

	public void RegisterBtn() {
		waitForElementToBeClickable(btnRegister);
		click(btnRegister);
		log.info("Register Button clicked successfully.");
	}

	public boolean isRegisterBtnVisible() {
		return isElementVisible(btnRegister);

	}

	public boolean isSubmitButtonEnable() {
		log.info("Checking the Submit button is enable on the page.");
		log.info("Submit button enable status : " + isElementEnabled(btnRegister));
		return isElementEnabled(btnRegister);
	}

	public void submitRegistration() {
		click(btnRegister);
		log.info("Registration submit button  clicked successfully.");
	}

	@FindBy(css = "[id='btnCancel']")
	private WebElement btnCancel;

	public void cancelRegistration() {
		scrollToElement(btnCancel);
		click(btnCancel);
		log.info("Cancel button  clicked successfully.");
	}

	@FindBy(css = " #btnPrev")
	private WebElement btnPrevious;

	public void clickPreviousBtn() {
		waitForElementToBeClickable(btnPrevious);
		click(btnPrevious);
		log.info("Previous button clicked successfully.");
	}

	@FindBy(css = ".toast-close-button")
	private WebElement btnCloseToastMessage;

	public void clickCloseToastMessage() {
		waitForElementToBeClickable(btnCloseToastMessage);
		click(btnCloseToastMessage);
		log.info("Close Toast Message button clicked successfully.");
	}

	@FindBy(css = " #btnNxt")
	private WebElement btnNext;

	public void clickNextBtn() {
		scrollToElement(btnNext);
		clickElementUsingJsExecutor(btnNext);
		log.info("Next Button clicked successfully.");
	}

	public boolean isNextBtnVisible() {
		return isElementVisible(btnNext);
	}

	@FindBy(css = ".toast.toast-error button")
	private WebElement btnCloseErrToastMsg;
	@FindBy(css = ".toast.toast-success button")
	private WebElement btnCloseSuccessToastMsg;
	@FindBy(css = " #myModal_termsTC .modal-header button[class*='close']")
	private WebElement btnCloseTermsDialogue;

	public void clickBtnCloseTermsDialogue() {
		log.info("Clicking the Close Terms Dialogue in button.");
		click(btnCloseTermsDialogue);
		log.info("Close Terms Dialogue clicked successfully.");
	}

	@FindBys(@FindBy(css = " #firststpbtn"))
	private List<WebElement> btnRegNext;
	@FindBy(css = " #firststpbtn")
	private WebElement btnRegNext1;

	public void clickbtnRegNextBtn() {
		scrollToElement(btnRegNext1);
		clickElementUsingJsExecutor(btnRegNext1);
		log.info("Next Button clicked successfully.");
	}

	public boolean isbtnRegNext1Visible() {
		return isElementVisible(btnRegNext1);
	}

	public boolean isbtnRegNextVisible() {
		return isElementVisibleAlt(btnRegNext);
	}

	@FindBy(css = " #BtnSubmitOtp")
	private WebElement btnSubmitOtp;

	public void clickSubmitOtp() {
		scrollToElement(btnSubmitOtp);
		clickElementUsingJsExecutor(btnSubmitOtp);
		log.info("Submit Otp clicked successfully.");
	}

	@FindBy(css = ".cancel-button.cancel_button_comm.stback2")
	private WebElement btnRegCancel;
	@FindBy(css = " #btnPrev")
	private WebElement btnRegPrev;
	@FindBy(css = ".emailid_authen.cancel-button.cancel_button_comm.stback2")
	private WebElement btnRegMFA_Cancel;

	@FindBy(css = "input[id='chk_62']")
	private WebElement chkBoxTermsConditions;

	public void checkChkBoxTermsConditions() {
		Page.scrollToTheBottomOfPage();
		check(chkBoxTermsConditions);
		log.info("Terms and conditions checkbox button is successfully.");
	}

	public boolean isChkBoxTermsConditionsVisible() {
		Page.scrollToTheBottomOfPage();
		log.info("Checking the visibility of terms and conditions on the page.");
		log.info("First Name visibility status : " + isElementVisible(chkBoxTermsConditions));
		return isElementVisible(chkBoxTermsConditions);
	}

	@FindBy(css = "span[globalize='ML_Registration_NotificationPreference'] input")
	private WebElement chkBoxNotificationPref;

	public void checkSubscribeForNotification(WebElement chkBoxNotificationPref) {
		log.info("Subscribe For Notification Check successfully.");
	}

	@FindBy(css = " span[globalize = 'ML_Registration_PaperlessBill'] input")
	private WebElement chkBoxReceivePaperBill;
	@FindBy(css = " div.rc - anchor.rc - anchor - normal.rc - anchor - light")
	private WebElement chkBoxCaptcha;
	@FindBy(css = " #ContentPlaceHolder1_rightpanel_chk_93")
	private WebElement chkBoxUserIDasMailID;
	@FindBy(css = " #ContentPlaceHolder1_rightpanel_chk_15")
	private WebElement chkBoxMadtMeterNum;
	@FindBy(css = " #ContentPlaceHolder1_rightpanel_chk_129")
	private WebElement chkBoxMadtFID;
	@FindBy(css = " #ContentPlaceHolder1_rightpanel_chk_39")
	private WebElement ChkBoxMadtContactNumber;
	@FindBy(css = " #ContentPlaceHolder1_rightpanel_chk_76")
	private WebElement chkBoxMadtDrvLic;
	@FindBy(css = " #ContentPlaceHolder1_rightpanel_chk_35")
	private WebElement chkBoxMandatory;
	@FindBy(css = " #rdBillTypelist_0")
	private WebElement rdbPaperlessBill;

	public boolean isRdbPaperlessBillVisible() {
		log.info("Checking the visibility of Paper less bill both on the page.");
		log.info("Paper less bill visibility status : " + isElementVisible(rdbPaperlessBill));
		return isElementVisible(rdbPaperlessBill);
	}

	public void checkRdbPaperlessBill() {
		check(rdbPaperlessBill);
		log.info("Paper less bill Radio button is successfully.");
	}

	@FindBy(css = " #rdBillTypelist_1")
	private WebElement rdbPaperBill;

	public boolean isRdbPaperBillVisible() {
		log.info("Checking the visibility of Paper bill both on the page.");
		log.info("Paper bill visibility status : " + isElementVisible(rdbPaperBill));
		return isElementVisible(rdbPaperBill);
	}

	public void checkRdbPaperBill() {
		check(rdbPaperBill);
		log.info("Paper bill Radio button is successfully checked.");
	}

	@FindBy(css = " #rdBillTypelist_2")
	private WebElement rdbPaperPaperlessBoth;

	public boolean isRdbPaperPaperlessBothVisible() {
		log.info("Checking the visibility of Paper Paper less both on the page.");
		log.info("Paper Paper less visibility status : " + isElementVisible(rdbPaperPaperlessBoth));
		return isElementVisible(rdbPaperPaperlessBoth);
	}

	public void checkRdbPaperPaperlessBoth() {
		check(rdbPaperPaperlessBoth);
		log.info("Paper Paper less Radio button is successfully.");
	}

	@FindBy(css = " #div_FAQ h5")
	private WebElement lnkFaq;
	@FindBy(css = " #div_termsNcondition a")
	private WebElement lnkTerms;

	public boolean isTermsNconditionVisible() {
		log.info("Checking the visibility of Paper Terms and condition on the page.");
		log.info("Terms and condition visibility status : " + isElementVisible(rdbPaperBill));
		return isElementVisible(lnkTerms);
	}

	public void clickTermsNcondition() {
		log.info("Clicking the click here to login in button.");
		click(lnkTerms);
		log.info("Terms and condition clicked successfully.");
	}

	@FindBy(css = "div#divlogin a")
	private WebElement lnkAccActivationClickHereToLogin;

	public void clickLnkAccActivationClickHereToLogin() {
		log.info("Clicking the click here to login in button.");
		click(lnkAccActivationClickHereToLogin);
		log.info("click here to login button clicked successfully.");
	}

	@FindBy(css = " #anchorResendOtp")
	private WebElement lnkRegResendOtp;
	@FindBy(css = " #authenticationThirdStep a")
	private WebElement lnkRegAgain;
	@FindBy(css = " #mbnoauth")
	private WebElement lnkPhoneNumber;

	@FindBy(css = "select[mappingname='SecurityQuestionId']")
	private WebElement lstBoxSecurityQuestion1;
	@FindBy(css = "select[mappingname='SecurityQuestionId2']")
	private WebElement lstBoxSecurityQuestion2;
	@FindBy(css = "select[id='ddlContactType133']")
	private WebElement lstBoxContact;
	@FindBy(css = " #ddlContactType133")
	private WebElement ddlContactType;

	public Boolean isDdlContactType() {
		log.info("Checking the visibility of Contact type field on the page.");
		log.info("Confirm Password Field visibility status : " + isElementVisible(ddlContactType));
		return isElementVisible(ddlContactType);
	}

	public void selectDdlContactType(String contactType) {
		log.info("Populating Cinfirm Password {} :" + contactType);
		selectByVisibleText(ddlContactType, contactType);
		log.info("contactType populated successfully.");
	}

	@FindBy(css = " #ContentPlaceHolder1_rightpanel_ddl_121")
	private WebElement ddlTypeUserName;
	@FindBys(@FindBy(xpath = "//div[contains(@id, 'Page')][not(contains(@style, 'none'))]//div[contains(@class, 'left_side_sec')]/select"))
	private List<WebElement> ddAllActiveRegFields;

	public List<WebElement> getDdAllActiveRegFields() {
		log.info("Fetching all Active Fields Labels.");
		return ddAllActiveRegFields;
	}

	@FindBy(css = ".toast-message")
	private WebElement lblErrorMandatoryMessage;
	@FindBy(css = ".w2ui-tag-body")
	private WebElement lblErrMessageValidateAccNumber;
	@FindBy(css = ".w2ui-tag-body")
	private WebElement lblErrMessageValidateUserName;
	@FindBy(css = ".w2ui-tag-body")
	private WebElement lblErrMessageValidatePassword;
	@FindBy(css = ".w2ui-tag-body")
	private WebElement lblErrMessageValidateMandatory;
	@FindBy(css = ".w2ui-tag-body.w2ui-tag-right")
	private WebElement lblToastErrorMsg;
	@FindBy(css = ".w2ui-tag-body")
	private WebElement lblToastErrorMsgUniversal;
	@FindBy(css = ".toast.toast-error div")
	private WebElement lblToastErrorMessage;
	@FindBy(css = ".toast-message")
	private WebElement lblToastSuccessMsg;
	@FindBy(xpath = "//div[contains(@id, 'Page')][not(contains(@style, 'none'))]//div[contains(@class, 'left_side_sec')]/label")
	private List<WebElement> lblAllRegActiveFields;

	public List<String> getLblAllRegActiveFields() {
		log.info("Fetching all Active Fields Labels.");
		return getAllElementsTextInList(lblAllRegActiveFields);
	}

	@FindBy(css = " #myModal_termsTC h4[class='modal-title']")
	private WebElement lblTermsDialogueHeading;

	public String getLblTermsDialogueHeading() {
		log.info("Fetching the Terms Dialogue Heading error message.");
		String label = getText(lblTermsDialogueHeading);
		log.info("Terms Dialogue Heading header is {}: " + label);
		return label;
	}

	@FindBy(css = " #myModal_termsTC.modal-body h2:nth-child(1) span")
	private WebElement lblTermsDialogueBodyTitle1;

	public String getLblTermsDialogueBodyTitle1() {
		log.info("Fetching the Terms Dialogue Body Title1 error message.");
		String label = getText(lblTermsDialogueBodyTitle1);
		log.info("Terms Dialogue Body Title1 header is {}: " + label);
		return label;
	}

	@FindBy(xpath = "//div[contains(text(),'Limited')]")
	private WebElement lblTermsDialogueBodyTitle2;

	public String getLblTermsDialogueBodyTitle2() {
		log.info("Fetching the Terms Dialogue Body Title2 error message.");
		String label = getText(lblTermsDialogueBodyTitle2);
		log.info("Terms Dialogue Body Title2 header is {}: " + label);
		return label;
	}

	@FindBy(css = "p[globalize='ML_Msg_CustomerVerification']")
	private WebElement lblAccActivationHeading;
	@FindBy(css = "span#lblMessage")
	private WebElement lblAccActivationMsg;

	public Boolean isLblAccActivationMsg() {
		log.info("Checking that the User Registration Activation message is visible on the login page.");
		return isElementVisible(lblAccActivationMsg);
	}

	public String getLblAccActivationMsg() {
		return getText(lblAccActivationMsg);
	}

	@FindBy(css = ".registerNewUi >h1")
	private WebElement lblUserRegistrationHeading;

	public Boolean isLblUserRegistrationHeading() {
		log.info("Checking that the User Registration Heading is visible on the login page.");
		return isElementVisible(lblUserRegistrationHeading);
	}

	public String getLblUserRegistrationHeading() {
		return getText(lblUserRegistrationHeading);
	}

	@FindBy(css = ".registerNewUi h1")
	private WebElement lblUserRegistrationTitle;

	public String getRegistrationLevel() {
		return getText(lblUserRegistrationTitle);
	}

	@FindBy(css = ".sprtPara")
	private WebElement lblUserRegMultiFac;
	@FindBy(css = " #divEmail p")
	private WebElement lblUserRegEmailAuth;
	@FindBy(css = " #divMobile p")
	private WebElement lblUserRegMobileAuth;
	@FindBy(css = " #sentModetxt")
	private WebElement lblUserRegsentMode;
	@FindBys(@FindBy(css = " #agrretrm1"))
	private List<WebElement> chkPhoneNumberTermandCon;

	public boolean isPhoneNumberTermandConVisible() {
		return isElementVisibleAlt(chkPhoneNumberTermandCon);
	}

	@FindBy(css = " #agrretrm1")
	private WebElement chkPhoneNumberTermandCon1;

	public void checkChkPhoneNumberTermandCon() {
		scrollToElement(chkPhoneNumberTermandCon1);
		clickElementUsingJsExecutor(chkPhoneNumberTermandCon1);
		log.info("Phone number term and condition clicked successfully.");
	}

	@FindBy(css = " #spn_verificationMobiletxtRecived")
	private WebElement lblverificationReceivedmsg;
	@FindBy(css = " #authenticationThirdStep p")
	private WebElement lblverificationexceedingmsg;

	@FindBy(css = "div.logo")
	private WebElement imgHeaderLogo;
	@FindBy(xpath = "//label[text()='SSN']/ancestor::div[@class='full_width_input_sec']/span[contains(@class, 'glyphicon-question-sign')]")
	private WebElement icoSSNInfo;
	@FindBy(xpath = "//label[text()='FID/TIN']/ancestor::div[@class='full_width_input_sec']/span[contains(@class, 'glyphicon-question-sign')]")
	private WebElement icoFIDTINInfo;

	@FindBys(@FindBy(css = "div[id *= 'Page_']"))
	private List<WebElement> divRegistrationPageCount;

	public List<WebElement> getDivRegistrationPageCount() {
		log.info("Fetching registration page count .");
		return divRegistrationPageCount;
	}

	@FindBy(css = " #crditdbthoopt")
	private WebElement rdbResidential;

	public void checkResidentialRdo() {
		check(rdbResidential);
		waitForPageLoader();
		log.info("Residential Radio button is successfully.");
	}

	@FindBy(css = " #bankchoseopt")
	private WebElement rdbCommercial;
	@FindBy(xpath = "//*[contains(text(),'Done')]")
	private WebElement lnkDoneRegistrationSucess;

	public void LnkDoneRegistrationSucessBtn() {
		click(lnkDoneRegistrationSucess);
		log.info("Done Registration Sucess Button clicked successfully.");
	}

	@FindBy(css = "#pnlLevel1 .error_messagecommon")
	private WebElement lblRegistrationErrormessage;

	public String getLblRegistrationErrormessage() {
		log.info("Fetching the registration page error message.");
		String label = getText(lblRegistrationErrormessage);
		log.info("Registration page header is {}: " + label);
		return label;
	}

	@FindBy(css = "#pnlLevel2 .error_messagecommon")
	private WebElement lblRegistrationErrormessage2;

	public String getLblRegistrationErrormessage2() {
		log.info("Fetching the registration page error message.");
		String label = getText(lblRegistrationErrormessage2);
		log.info("Registration page header is {}: " + label);
		return label;
	}
}

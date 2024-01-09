package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class MyProfilePage extends HomePage {
	private static final Logger log = LogManager.getLogger(NotificationPreferencePage.class);

	public MyProfilePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean isMyProfilePage(String url, String title) {
		boolean isMyProfilePage = false;
		log.info("Checking that the current page is My Profile page.");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isMyProfilePage = true;
		log.info("The current page is My Profile {}: " + isMyProfilePage);
		return isMyProfilePage;
	}

	@FindBy(css = ".MuiGrid-root.pageheading-box")
	private WebElement lblMyProfilePageHeader;

	public String getMyProfilePageHeaderLabel() {
		String label = getText(lblMyProfilePageHeader);
		log.info("MyProfile PageHeader Label  is : " + label);
		return label;
	}

	@FindBys(@FindBy(css = ".profiledata-row h3"))
	private List<WebElement> txtProfiledataName;

	public List<WebElement> getProfileDataName() {
		return txtProfiledataName;
	}

	@FindBy(xpath = " (//div[@class='col-lg-4 col-sm-3 col-xs-12 profileStatic']/h3)[1]")
	private WebElement lblName;

	public String getLblNameLabel() {
		String label = getText(lblName);
		log.info(" Name of the LBL Name is  {}: " + label);
		return label;
	}

	@FindBy(css = "#ContentPlaceHolder1_ContentPlaceHolderBody_lblName")
	private WebElement txtAccountHolderName;

	public String getValueNameLabel() {
		String label = getText(txtAccountHolderName);
		log.info(" Name of the User Label is  {}: " + label);
		return label;
	}

	public boolean isValueUserNameVisible() {
		log.info("Checking that visible of Name of the User on My Profile page." + txtAccountHolderName.isDisplayed());
		return isElementVisible(txtAccountHolderName);
	}

	@FindBy(xpath = "//h3[text() = 'Username']")
	private WebElement lblUserName;

	public boolean isUserNameVisible() {
		log.info("User Name visibility status : " + lblUserName.isDisplayed());
		return isElementVisible(lblUserName);
	}

	public String getUserNameLabel() {
		String label = getText(lblUserName);
		log.info("User Name Label  is : " + label);
		return label;
	}

	@FindBy(css = "div.lft_profile_sec #txtUserid")
	private WebElement lblEditUsername;

	public boolean isEditUserNameVisible() {
		log.info("Checking the Visibility of Non Edit User Name Status ." + lblEditUsername.isDisplayed());
		return isElementVisible(lblEditUsername);
	}

	public String getEditUserNameLabel() {
		log.info("Fetching the Edit User Name placeholder.");
		String label = getText(lblEditUsername);
		log.info(" Edit User Name placeholder {}: " + label);
		return label;
	}

	@FindBy(css = "#btneditUserId")
	private WebElement btnEditUsername;

	public boolean isUserNameEditButtonVisible() {
		log.info("Checking the Visibility of Edit User Name Button .");
		return isElementVisible(btnEditUsername);
	}

	public void clickUserNameEditButton() {
		click(btnEditUsername);
		log.info("Click on user Name Edit Button Successfully .");
	}

	@FindBy(css = "#txtNewUserId")
	private WebElement txtBoxUserNameField;

	public void clearUserNameTextField() {
		clear(txtBoxUserNameField);
		log.info("User Name Input Field is Cleared");
	}

	public void populateUserName(String userName) {
		log.info("Populating User Name} :" + userName);
		sendKeys(txtBoxUserNameField, userName);
		log.info("User Name  populated successfully.");
	}

	public boolean isUserNameInputFieldVisible() {
		log.info("Checking the Visibility of User Name Text Input Field.");
		return isElementVisible(txtBoxUserNameField);
	}

	public String getTxtBoxUserNameMaxLength() {
		String maxLen = getAttribute(txtBoxUserNameField, "maxlength");
		log.info("Max length of User Name field is {} " + maxLen);
		return maxLen;
	}

	public String getTxtBoxUserNameMinLength() {
		String maxLen = getAttribute(txtBoxUserNameField, "minlength");
		log.info("Min length of User Name field is {} " + maxLen);
		return maxLen;
	}

	public String getUserNameTxtAttribute() {
		String attribute = getAttribute(txtBoxUserNameField, "value");
		return attribute;
	}

	@FindBy(css = "#btnChangeUserId")
	private WebElement btnSaveUserName;

	public boolean isChangeUserNameSaveBtnVisible() {
		log.info("Checking the Visibility of Change User Name Save Button .");
		return isElementVisible(btnSaveUserName);
	}

	public void clickChangeUserNameSaveButton() {
		click(btnSaveUserName);
		log.info("Change User Name Save  Button is clicked");
	}

	@FindBy(css = "#btnCancelUserId")
	private WebElement btnCancelUserName;

	public boolean isChangeUserNameCancelBtnVisible() {
		log.info("Checking the Visibility of Change User Name Cancel Button .");
		return isElementVisible(btnCancelUserName);
	}

	public void clickChangeUserNameCancelButton() {
		click(btnCancelUserName);
		log.info("Change User Name Cancel  Button is clicked");
	}

	@FindBy(css = ".myaccount.dropdown")
	private WebElement lnkMyAccountHeader;

	public boolean isMyAccountHeaderVisible() {
		log.info("Checking the Visibility of My Account Link  .");
		return isElementVisible(lnkMyAccountHeader);
	}

	public void waitForAccountHeaderClickable(WebElement lnkMyAccountHeader) {
		waitForElementToBeClickable(lnkMyAccountHeader);
		log.info("Account Header link is clickable ");
	}

	@FindBy(xpath = "//h3[text()='Password']/following::span[1]")
	private WebElement valuePassword;

	public boolean isProfilePasswordVisible() {
		log.info("Checking that visible User Profile Password On  the My Profile page." + valuePassword.isDisplayed());
		return isElementVisible(valuePassword);
	}

	public String getProfilePasswordLabel() {
		log.info("Fetching the User Profile Password placeholder.");
		String label = getText(valuePassword);
		log.info(" User Profile Password placeholder is  {}: " + label);
		return label;
	}

	@FindBy(css = "#lbl_Phone")
	private WebElement valuePrimaryPhone;

	public String getPrimaryPhoneLabel() {
		log.info("Fetching the Primary Phone on placeholder.");
		String label = getText(valuePrimaryPhone);
		log.info("Primary Phone no placeholder {}: " + label);
		return label;
	}

	public String getPrimaryPhoneNumberMaxLength() {
		String maxLen = getAttribute(valuePrimaryPhone, "maxlength");
		log.info("Max length of Primary Phone Number field is {} " + maxLen);
		return maxLen;
	}

	public String getPrimaryPhoneNumberMinLength() {
		String minLen = getAttribute(valuePrimaryPhone, "minlength");
		log.info("Min length of Primary Phone Number field is {} " + minLen);
		return minLen;
	}

	@FindBy(css = "#btneditPPhone")
	private WebElement btnEditPrimaryPhone;

	public boolean isPrimaryPhoneNumberEditButtonVisible() {
		log.info("Checking that visible Edit button of Phone Number on My Profile page."
				+ btnEditPrimaryPhone.isDisplayed());
		return isElementVisible(btnEditPrimaryPhone);
	}

	public void clickPrimaryPhoneNumberEditButton() {
		click(btnEditPrimaryPhone);
		log.info("Phone Number Edit Button is Editable");
	}

	@FindBy(css = "input[inputtype='Phone']")
	private WebElement primaryPhoneInputField;

	public void clearPrimaryPhoneInputField() {
		clear(primaryPhoneInputField);
		log.info("Primary Phone number Input Field is cleared");
	}

	public void populatePrimaryPhone(String primaryNum) {
		log.info("Populating Primary Number{} :" + primaryNum);
		sendKeys(primaryPhoneInputField, primaryNum);
		log.info("Primary Phone Number populated successfully.");
	}

	public void waitForPrimaryInputFieldVisible() {
		waitForElementToBeVisible(primaryPhoneInputField);
	}

	@FindBy(css = "#btnPphonesave")
	private WebElement btnSavePrimaryPhone;

	public boolean isPrimaryPhoneNumberSaveButtonVisible() {
		log.info("Checking that visible  of Primary Phone Number Save Button on My Profile page."
				+ btnSavePrimaryPhone.isDisplayed());
		return isElementVisible(btnSavePrimaryPhone);
	}

	public void clickPrimaryPhoneNumberSaveButton() {
		click(btnSavePrimaryPhone);
		log.info("Primary Phone Number Save Button Successfully Clicked");
	}

	@FindBy(css = "#lblmob")
	private WebElement lblBoxAlternateNumber;

	public boolean isSecondaryPhoneNumberVisible() {
		log.info("Checking that visible Secondary Phone Number the On My Profile page."
				+ lblBoxAlternateNumber.isDisplayed());
		return isElementVisible(lblBoxAlternateNumber);
	}

	public String getSecondaryPhoneNumberLabel() {
		String label = getText(lblBoxAlternateNumber);
		return label;
	}

	

	@FindBy(css = "#btneditSPhone")
	private WebElement btnEditSecondaryPhone;

	public boolean isSecondaryPhoneNumberEditButtonVisible() {
		log.info("Checking that visible Secondary Phone number Edit Button on My Profile page."
				+ btnEditSecondaryPhone.isDisplayed());
		return isElementVisible(btnEditSecondaryPhone);
	}

	public void clickSecondaryPhoneNumberEditButton() {
		click(btnEditSecondaryPhone);
		log.info("Secondary Phone Number Edit Button is Editable");
	}

	@FindBy(css = "#txtmob")
	private WebElement valueSecondaryPhone;

	public String getSecondaryPhoneNumberMaxLength() {
		String maxLen = getAttribute(valueSecondaryPhone, "maxlength");
		log.info("Max length of Secondary Phone Number field is {} " + maxLen);
		return maxLen;
	}
	public void clearSecondaryPhoneTextField() {
		clear(valueSecondaryPhone);
		log.info("Secondary Phone Input Field is Cleared");
	}
	public String getSecondaryPhoneNumberAttributeByValue() {
		log.info("Fetching the Secondary Phone Number placeholder.");
		String value = getAttribute(valueSecondaryPhone, "Value");
		log.info("Password placeholder {}: " + value);
		return value;
	}
	public void populateSecondaryPhone(String secondaryP) {
		log.info("Populating Secondary Phone} :" + secondaryP);
		sendKeys(valueSecondaryPhone, secondaryP);
		log.info("Secondary Phone  populated successfully.");
	}

	@FindBy(css = "#btnSphonesave")
	private WebElement btnSaveSecondaryPhone;

	public boolean isSecondaryPhoneNumberSaveButtonVisible() {
		log.info("Checking that visible  of Secondary Phone Number Save Button on My Profile page."
				+ btnSaveSecondaryPhone.isDisplayed());
		return isElementVisible(btnSaveSecondaryPhone);
	}

	public void clickSecondaryPhoneNumberSaveButton() {
		click(btnSaveSecondaryPhone);
		log.info("Secondary Phone Number Save Button Successfully Clicked");
	}

	@FindBy(css = "#lblEmail")
	private WebElement lblBoxEmail;

	public boolean isPrimaryEmailVisible() {
		log.info("Checking that visible Primary Email Id On  the My Profile page." + lblBoxEmail.isDisplayed());
		return isElementVisible(lblBoxEmail);
	}

	public String getPrimaryEmailIdLabel() {
		log.info("Fetching the Primary Email Id placeholder.");
		String label = getText(lblBoxEmail);
		log.info("Primary Email Id  placeholder {}: " + label);
		return label;
	}

	@FindBy(css = "#btneditPEmail")
	private WebElement btnEditPrimaryEmail;

	public boolean isPrimaryEmailIdEditButtonVisible() {
		log.info("Checking that visible  of Primary Email Id's Edit Button on My Profile page."
				+ btnEditPrimaryEmail.isDisplayed());
		return isElementVisible(btnEditPrimaryEmail);
	}

	public void clickPrimaryEmailIdEditButton() {
		click(btnEditPrimaryEmail);
		log.info("Primary Email Id Edit Button is Editable");
	}

	@FindBy(css = "#txtEmail")
	private WebElement valuePrimaryEmail;

	public String getPrimaryEmailAttribute(String att) {
		log.info("Fetching the Attribute of Primary Email id ");
		String attribute = getAttribute(valuePrimaryEmail, att);
		return attribute;
	}

	public void clearPrimaryEmailTextBox() {
		clear(valuePrimaryEmail);
		log.info("Primary Email Id Input Field is Cleared");
	}

	public void populatePrimaryEmailId(String primaryEmail) {
		log.info("Populating Primary EmailId} :" + primaryEmail);
		sendKeys(valuePrimaryEmail, primaryEmail);
		log.info("Primary Phone EmailId populated successfully.");
	}

	public String getPrimaryEmailIdMaxLength() {
		String maxLen = getAttribute(valuePrimaryEmail, "maxlength");
		log.info("Max length of Primary Email Id  field is {} " + maxLen);
		return maxLen;
	}

	@FindBy(css = "#btnCancelUserId3")
	private WebElement btnEditEmailCancel;

	public void clickPrimaryEmailIdCancelButton() {
		click(btnEditEmailCancel);
		log.info("Primary Email Id Cancel Button is clicked");
	}

	@FindBy(css = "#btnEmailsave")
	private WebElement btnSavePrimaryEmail;

	public boolean isPrimaryEmailIdSaveButtonVisible() {
		log.info("Checking that visible  of Primary Email Id Save Button on My Profile page."
				+ btnSavePrimaryEmail.isDisplayed());
		return isElementVisible(btnSavePrimaryEmail);
	}

	public void clickPrimaryEmailIdSaveButton() {
		click(btnSavePrimaryEmail);
		log.info("Primary Email id  Save Button Successfully Clicked");
	}

	@FindBy(css = "#lblAltEmail")
	private WebElement valueSecondaryEmail;

	public String getSecondaryEmailIdLabel() {
		log.info("Fetching the SecondaryEmail Label on placeholder.");
		String label = getText(valueSecondaryEmail);
		log.info("Secondary Email ID placeholder {}: " + label);
		return label;
	}

	@FindBy(css = "#btneditSEmail")
	private WebElement btnEditSecondaryEmail;

	public boolean isSecondaryEmailIdEditButtonVisible() {
		log.info("Checking that visible  of Secondary Email Id's Edit Button on My Profile page."
				+ btnEditSecondaryEmail.isDisplayed());
		return isElementVisible(btnEditSecondaryEmail);
	}

	public void clickSecondaryEmailIdEditButton() {
		click(btnEditSecondaryEmail);
		log.info("Secondary Email Id Edit Button is Editable");
	}

	@FindBy(css = "#txtAltEmail")
	private WebElement txtBoxAlternateEmail;

	public String getAlternateEmailAttributewithoutTrim(String att) {
		waitForElementToBeVisible(txtBoxAlternateEmail);
		String attribute = txtBoxAlternateEmail.getAttribute(att);
		return attribute;
	}

	public boolean isSecondaryEmailTxtVisible() {
		log.info("Checking that visible Secondary Email Id On  the My Profile page."
				+ txtBoxAlternateEmail.isDisplayed());
		return isElementVisible(txtBoxAlternateEmail);
	}

	public String getSecondaryEmailIdTxtLabel() {
		log.info("Fetching the Secondary Email Id placeholder.");
		String label = getAttribute(txtBoxAlternateEmail, "value");
		log.info("Secondary Email Id  placeholder {}: " + label);
		return label;
	}

	public String getSecondaryEmailIdMaxLength() {
		String maxLen = getAttribute(txtBoxAlternateEmail, "maxlength");
		log.info("Max length of Secondary Email Id  field is {} " + maxLen);
		return maxLen;
	}

	public void clearSecondaryEmailTextBox() {
		clear(txtBoxAlternateEmail);
		log.info("Secondary Email Id Input Field is Cleared");
	}

	public void populateSecondaryEmailId(String secondaryemail) {
		log.info("Populating Secondary EmailId} :" + secondaryemail);
		sendKeys(txtBoxAlternateEmail, secondaryemail);
		log.info("Secondary EmailId populated successfully.");
	}

	@FindBy(css = "#btnCancelUserId2")
	private WebElement btnSecondaryCancel;

	public void clickSecondaryPhoneCancelButton() {
		click(btnSecondaryCancel);
		log.info("Secondary Phone No Cancel Button is clicked");
	}

	@FindBy(css = "#btnAltEmailsave")
	private WebElement btnSaveSecondaryEmail;

	public boolean isSecondaryEmailIdSaveButtonVisible() {
		log.info("Checking that visible  of Secondary Email id Save Button on My Profile page."
				+ btnSaveSecondaryEmail.isDisplayed());
		return isElementVisible(btnSaveSecondaryEmail);
	}

	public void clickSecondaryEmailIdSaveButton() {
		click(btnSaveSecondaryEmail);
		log.info("Secondary Email id Save Button Successfully Clicked");
	}

	// Toaster msg Validation Locataor
	@FindBy(css = "span.error_messagecommon[role]")
	private WebElement txtValidation;
	
	public String getCommanErrorToastMsg() {
		String toastMsg = getText(txtValidation);
		return toastMsg;
	}

	public String getPrimayPhoneToastmsgLabel() {
		String toastMsg = getText(txtValidation);
		return toastMsg;
	}
	@FindBy(css = "span.error_messagecommon[role]")
	private WebElement txtSecondarypValidation;

	public String getSecondaryPhoneToastmsgLabel() {
		String toastMsg = getText(txtSecondarypValidation);
		return toastMsg;
	}

	@FindBy(css = "span.error_messagecommon[role]")
	private WebElement txtpwdValidation;

	public String getPwdToastmsgLabel() {
		String toastMsg = getText(txtpwdValidation);

		return toastMsg;
	}

	@FindBy(css = "span.error_messagecommon[role]")
	private WebElement txtPEmailValidation;

	public String getPrimayEmailToastmsgLabel() {
		String toastMsg = getText(txtPEmailValidation);
		return toastMsg;
	}

	@FindBy(css = ".error_messagecommon[role]")
	private WebElement txtUserNameValidation;

	public String getUserNameToastmsgLabel() {
		String toastMsg = getText(txtUserNameValidation);
		return toastMsg;
	}

	@FindBy(css = ".toast-message")
	private WebElement txtToastMessage;

	public String getMandatoryToastMessage() {
		log.info("Fetching the Mandatory toast Message placeholder.");
		String label = getText(txtToastMessage);
		log.info(" Mandatory toast Message is   {}: " + label);
		return label;
	}

	@FindBy(xpath = "//*[contains(text(),'successfully updated.')]")
	private WebElement successToastMsg;
	public String getMandatoryToastMessageUsingContain() {
		log.info("Fetching the Mandatory toast Message placeholder.");
		String label = getText(successToastMsg);
		log.info(" Mandatory toast Message is   {}: " + label);
		return label;
	}
	@FindBy(css = ".toast-message")
	private WebElement txtReenterPwdToastMessage;

	public String getExistingPwdToastMessage() {
		log.info("Fetching the Mandatory toast Message if password is not Matching with Existing Password.");
		String label = getText(txtReenterPwdToastMessage);
		log.info(" Mandatory toast Message is for Wrong pass enter   {}: " + label);
		return label;
	}

	@FindBy(css = ".rightbannerimg")
	private WebElement lnkMyAccountPageBanner;

	public boolean isMyAccountPageBannerVisible() {
		log.info("MyAccount Page Banner visibility status : " + lnkMyAccountPageBanner.isDisplayed());
		return isElementVisible(lnkMyAccountPageBanner);
	}

	@FindBy(css = "#usertip")
	private WebElement iconUserTip;

	public String getUserTipIconAttribute() {
		log.info("Featching the Value of User Tips icon by Attribute");
		String attribute = getAttribute(iconUserTip, "data-original-title");
		return attribute;
	}

	public boolean isUserTipIconVisible() {
		log.info("Fetching the visibility of User Tips Icon ");
		return isElementVisible(iconUserTip);
	}

	@FindBy(css = ".my_acc_tbl.pro_add tbody tr:not(:first-child):not([style='display:none'])")
	private WebElement listServiceAddress;

	@FindBy(css = "h1 img")
	private WebElement lnkMyAccountModuleIcon;

	@FindBy(css = ".list-group li span")
	private WebElement lstMyAccountMenu;

	@FindBy(css = "#btnDeleteNotificationMail")
	private WebElement btnYesDeletePopUp;

	public void clickYesDeletePopUp() {
		click(btnYesDeletePopUp);
		log.info("Yes Delete Popup button Successfully .");
	}

	@FindBy(css = " #No")
	private WebElement btnNoDeletePopUp;

	public void clickNoDeletePopUp() {
		click(btnNoDeletePopUp);
		log.info("No Delete Popup button Successfully .");
	}

	@FindBy(css = "[inputtype='ZipCode']")
	private WebElement txtAddAccountModalZip;

	@FindBys(@FindBy(css = "#ddlContactType option"))
	private List<WebElement> listContactType;

	public List<WebElement> getListContactType() {
		return listContactType;
	}

	@FindBys(@FindBy(css = " #ddlContactType option"))
	private List<WebElement> contactTypePrimaryProfileInfo;

	public List<WebElement> getAllPrimaryPOptionsInListBox() {
		return contactTypePrimaryProfileInfo;
	}

	@FindBys(@FindBy(css = "#ddlContactType1 option"))
	private List<WebElement> contactTypeSecondaryProfileInfo;

	public List<WebElement> getAllSecondaryPOptionsInListBox() {
		return contactTypeSecondaryProfileInfo;
	}

	@FindBy(xpath = " (//div[@class='col-lg-4 col-sm-3 col-xs-12 profileStatic']/h3)[3]")
	private WebElement lblPassword;

	public String getPasswordLabel() {
		String label = getText(lblPassword);
		log.info(" Name of the Password Name is  {}: " + label);
		return label;
	}

	@FindBy(xpath = " (//div[@class='col-lg-4 col-sm-3 col-xs-12 profileStatic']/h3)[4]")
	private WebElement lblPrimaryContactNum;

	public String getPrimaryContactNumLabel() {
		String label = getText(lblPrimaryContactNum);
		log.info(" Primary Conatct Num PlaceHolder Name  {}: " + label);
		return label;
	}

	@FindBy(xpath = " //*[@id='ddlContactType']/following-sibling::label")
	private WebElement lblPrimaryContactType;

	@FindBy(xpath = " (//div[@class='col-lg-4 col-sm-3 col-xs-12 profileStatic']/h3)[5]")
	private WebElement lblSecondaryContactNum;

	public String getSecondaryContactNumLabel() {
		String label = getText(lblSecondaryContactNum);
		log.info("Secondary Contact Num PlaceHolder Name  {}: " + label);
		return label;
	}

	@FindBy(xpath = " //*[@id='ddlContactType1']/following-sibling::label")
	private WebElement lblSecondaryContactType;

	@FindBy(xpath = " (//div[@class='col-lg-4 col-sm-3 col-xs-12 profileStatic']/h3)[6]")
	private WebElement lblEmailAdd;

	@FindBy(xpath = " (//div[@class='col-lg-4 col-sm-3 col-xs-12 profileStatic']/h3)[7]")
	private WebElement lblAltEmailAdd;

	public String getAltEmailIdLabel() {
		String label = getText(lblAltEmailAdd);
		log.info("Alternet Email Id  PlaceHolder Name  {}: " + label);
		return label;
	}

	@FindBy(css = ".mysrvc_txt h5")
	private WebElement lblServiceAdd;

	@FindBy(css = " span[globalize$ = 'MyAccount_span_Default']")
	private WebElement lblDefaultSelected;

	@FindBy(css = "span[globalize$='MYACCOUNT_Lbl_Default']")
	private WebElement lblDefault;

	@FindBy(css = "span[globalize$='CONFIRM_BILL_Lbl_PropertyAddress']")
	private WebElement lblPropertyAdd;

	@FindBy(css = "span[globalize$='Default_Lbl_Account']")
	private WebElement lblServiceAccountNum;

	@FindBy(css = "span[globalize$='Setting_Lbl_Plan']")
	private WebElement lblPlan;

	@FindBy(css = " #TdRoles1 span[globalize$='Role']")
	private WebElement lblRole;

	@FindBy(css = ".delete_box span[globalize$='Role']")
	private WebElement lblDelete;

	@FindBy(css = " b span[globalize$='ML_SrvcRqust_div_MailAdd']")
	private WebElement lblMailingAddr;

	@FindBy(css = "span[globalize$='Default_Lbl_Address']")
	private WebElement lblAddress;

	@FindBy(css = "span[globalize$='Register_Lbl_SecurtyQustn']")
	private WebElement lblSecurityQuestion;

	@FindBy(xpath = "(//div[@class='col-lg-4 col-sm-3 col-xs-12 profileStatic']/h3)[8]")
	private WebElement lblSecurityQuestion1;

	@FindBy(xpath = "(//div[@class='col-lg-4 col-sm-3 col-xs-12 profileStatic']/h3)[9]")
	private WebElement lblSecurityQuestion2;

	@FindBy(css = ".cls_disclaimer")
	private WebElement lblDisclaimer;

	// @FindBy (css="input#btnSaveAll")
	// private WebElement btnSave;

	@FindBy(xpath = " //div[@class ='default-address-1']//input[@type= 'radio']")
	private WebElement rdbDefaultServiceAdd;

	@FindBy(xpath = " //div[@class ='default-address-1']//input[@type= 'radio' and not(@checked='checked')]/..")
	private WebElement rdbNotSelectedDefaultServiceAdd;

	@FindBy(xpath = " //div[@class ='default-address-1']//input[@type= 'radio' and (@checked='checked')]")
	private WebElement rdbSelectedDefaultServiceAdd;

	@FindBy(xpath = " (//div[@class='address_bottomp']/a[contains(@id,'divdefaultButton')][@style='visibility:show']/../../..//p/label)[1]")
	private WebElement lblDefaultSelectedServiceAdd;

	@FindBy(xpath = " (//div[@class='address_bottomp']/a[contains(@id,'divdefaultButton')][@style='visibility:show']/../../..//i)[2]/label")
	private WebElement lblDefaultSelectedAccountServiceAdd;

	@FindBy(xpath = " (//ul[@id='UlddlAddress']/li/a/text())[2]")
	private WebElement lblDefaultSelectedServiceAccount;

	@FindBy(xpath = " //div[@class ='default-address-1']//input[@type= 'radio' and (@checked='checked')]/ancestor::tr//div[@class= 'deleteaccount']")
	private WebElement btnDeleteDefaultServiceAdd;

	@FindBy(xpath = "//div[@class ='default-address-1']//input[@type= 'radio' and not(@checked='checked')]/ancestor::tr//div[@class= 'deleteaccount']")
	private WebElement btnDeleteNotDefaultServiceAdd;

	@FindBy(css = ".deleteaccount")
	private WebElement btnDelete;
	@FindBy(css = "#deletemyprofilepopup")
	private WebElement lnkDeleteMyProfile;

	public void clickDeleteMyProfile() {
		click(lnkDeleteMyProfile);
		log.info("Delete My Profile link has been successfully Clicked");

	}

	public String getDeleteMyProfileLable() {
		String label = getText(lnkDeleteMyProfile);
		return label;
	}

	@FindBy(css = "#frgme")
	private WebElement txtdeleteMyProfile1;

	public String getDeleteMyProfileLableWarningLabel() {
		String label = getText(txtdeleteMyProfile1);
		return label;
	}

	@FindBys(@FindBy(css = "#deleteWarning p"))
	private List<WebElement> lxtDeleteMyProfileWarning;

	public List<WebElement> getlxtDeleteMyProfileWarning() {
		return lxtDeleteMyProfileWarning;
	}

	public List<String> getDeleteMyProfileWarningLabel() {
		List<String> label = getAllElementsTextInList(lxtDeleteMyProfileWarning);
		return label;
	}

	@FindBy(css = "#btnDecline")
	private WebElement btnDeleteMyProfileCancelWarn;

	public String getDMPCancelButtonlabel() {
		String label = getText(btnDeleteMyProfileCancelWarn);
		return label;
	}

	public void clickDeleteMyProfileCancelWarn() {
		click(btnDeleteMyProfileCancelWarn);
	}

	public boolean isDeleteMyProfileCancelWarnbtnVisible() {
		return isElementVisible(btnDeleteMyProfileCancelWarn);
	}

	@FindBy(css = "#btnProceed")
	private WebElement btnDeleteMyProfileProceedWarn;

	public String getDMPProceedButtonlabel() {
		String label = getText(btnDeleteMyProfileProceedWarn);
		return label;
	}

	public boolean isDeleteMyProfileProceedWarnbtnVisible() {
		return isElementVisible(btnDeleteMyProfileProceedWarn);
	}

	@FindBy(css = "#divPlanTypemodal.modal-title")
	private WebElement lblPlanPopupHeading;

	@FindBy(css = "#divPlanType.closepopup")
	private WebElement btnPlanPopupClose;

	@FindBy(xpath = "//b[text() = 'Power Plan']")
	private WebElement lblPowerPlanPopup;

	@FindBy(xpath = "//b[text() = 'Water Plan']")
	private WebElement lblWaterPlanPopup;

	@FindBy(xpath = "//b[text() = 'Gas Plan']")
	private WebElement lblGasPlanPopup;

	@FindBy(xpath = "//b[text() = 'Solar Plan']")
	private WebElement lblSolarPlanPopup;

	@FindBy(xpath = "//div[@id= 'toast-container']/div[contains(@class, 'toast')]")
	private WebElement lblToastSuccessMsg;

	@FindBy(xpath = "//*[@class='lbldivaccont']")
	private WebElement lblLinkedAccountNumList;

	@FindBy(css = "h3 #titleBanner")
	private WebElement lblTitleBanner;

	public String getBannerTitleLabel() {
		String label = getText(lblTitleBanner);
		return label;
	}

	@FindBy(css = "#ddldropdown #dLabel#Change Email Address Popup")
	private WebElement lstBoxAccountAddress;

	public boolean isBoxAccountAddresPresent() {
		boolean b = true;
		if (lstBoxAccountAddress.isDisplayed()) {
			b = false;
		}
		return b;
	}

	@FindBy(xpath = "//input[@id = 'txtEmail']/..//a")
	private WebElement btnEditEmailAddress;

	@FindBy(css = "[class='modal-content editMain modal-lg']h4")
	private WebElement lblHeadingChangeEmailAddPopup;

	@FindBy(css = "[class='modal-content editMain modal-lg'].closepopup")
	private WebElement btnCloseChangeEmailAddPopup;

	@FindBy(css = "[class='modal-content editMain modal-lg'].modal-body label")
	private WebElement lblEmailAddChangeEmailAddPopup;

	@FindBy(css = "[class='modal-content editMain modal-lg'].modal-body input[id='txtEmailID']")
	private WebElement txtEmailAddChangeEmailAddPopup;

	@FindBy(css = "[class='modal-content editMain modal-lg'].modal-body p")
	private WebElement lblNoteAddChangeEmailAddPopup;

	@FindBy(css = "[class='modal-content editMain modal-lg'] #btnCancel")
	private WebElement btnCancelChangeEmailAddPopup;

	@FindBy(css = "[class='modal-content editMain modal-lg'] #btnEmailId")
	private WebElement btnSubmitChangeEmailAddPopup;

	@FindBy(css = ".w2ui-popup-btn.w2ui-btn")
	private WebElement lblMsgAlertChangeEmailAdd;

	@FindBy(css = ".w2ui-centered.w2ui-alert- msg")
	private WebElement lblMsgAlertChangeEmailAddPopup;

	@FindBy(xpath = " //div[@class='modal-dialog']//button[@class='closepopup']/following-sibling::h3")
	private WebElement lblDownloadMydata;

	public String getDownloadMyDataPopUpLabel() {
		log.info("Featching the Download my data Lable on pop up");
		String label = getText(lblDownloadMydata);
		return label;
	}

	@FindBy(xpath = " //div[@class ='modal-content']//div[@class ='modal-body']/div/h4")
	private WebElement lblDownloadMydataVerification;

	public String getDownloadMydataVerificationLabel() {
		log.info("Fetching the  DownloadMydata Verification label");
		String label = getText(lblDownloadMydataVerification);
		return label;
	}

	@FindBy(css = ".filter-option.pull-left")
	private WebElement lblSelectedAddInDropdown;

	@FindBy(css = " #ui-datepicker-div")
	private WebElement calendarControl;

	@FindBy(css = ".ui-datepicker-header")
	private WebElement lblCalendarHeading;

	@FindBy(css = ".ui-datepicker- header a[title ='Prev']")
	private WebElement btnCalendarPreviousButton;

	@FindBy(css = ".ui-datepicker- header a[title ='Next']")
	private WebElement btnCalendarNextButton;

	@FindBy(css = ".ui-datepicker-title>span[class ='ui-datepicker-month']")
	private WebElement lblCalenderMonth;

	@FindBy(css = ".ui-datepicker-title>span[class ='ui-datepicker-year']calenderTableCln                                         =.ui-datepicker- calendar")
	private WebElement lblCalenderYear;

	@FindBy(css = " span[class ='ui-state-default']")
	private WebElement lblDisabledDate;

	@FindBy(xpath = " //td[@data-handler='selectDay']/a##CCPA/GDPR")
	private WebElement lnkEnabledDate;

	@FindBy(css = "a[globalize='ML_ACCOUNT_Lnk_DownloadData']")
	private WebElement lnkDownloadData;

	public boolean isDownloadMyDataVisible() {
		log.info("Checking the visible of Download My data link on the My Profile page.");
		return isElementVisible(lnkDownloadData);
	}

	public String getDownloadMyDataLabel() {
		log.info("Featching the Lable of Download my data Link");
		String label = getText(lnkDownloadData);
		return label;
	}

	public void clickDownloadMyData() {
		click(lnkDownloadData);
		log.info("Download My Data link has been Successfully Clicked");
	}

	@FindBy(css = "#txtUserIdPassCnfm")
	private WebElement txtPwdForChngUsername;

	public boolean isPwdForUpdateUserNameVisible() {
		log.info("Checking the visibility of Existing password field for Update User Name  on the My Profile  page.");
		return isElementVisible(txtPwdForChngUsername);
	}

	public String getPwdForUpdateUserNameLabel() {
		log.info("Fetching the Password for Change User Name placeholder.");
		String label = getText(txtPwdForChngUsername);
		log.info(" Password for Update User Name  placeholder {}: " + label);
		return label;
	}

	public void clearPwdForChangeUserNameEditField() {
		clear(txtPwdForChngUsername);
		log.info(" Password for Update User Name has been cleared successfully.");
	}

	public void populatePwdForUpdateUserName(String password) {
		log.info("Populating Existing password {} :" + password);
		sendKeys(txtPwdForChngUsername, password);
		log.info("Existing Password for Update User Name populated successfully.");
	}

	@FindBy(css = "#txtPEmailPassCnfm")
	private WebElement txtPwdForChngPEmail;

	public void populatePwdForChngPEmail(String password) {
		log.info("Populating The password {} :" + password);
		sendKeys(txtPwdForChngPEmail, password);
		log.info("The Password for Update Email Id populated successfully.");
	}

	public void clearPwdForChngPEmailTextBox() {
		clear(txtPwdForChngPEmail);
		log.info("Primary Email Id Pwd Input Field is Cleared");
	}

	public boolean isPwdForUpdatePrimaryEmailVisible() {
		log.info(
				"Checking the visibility of Existing password field for Update Primary Email id on the My Profile  page.");
		return isElementVisible(txtPwdForChngPEmail);
	}

	public String getPwdForUpdateEmailLabel() {
		log.info("Fetching the Password for Update Primary  Email id placeholder.");
		String label = getText(txtPwdForChngPEmail);
		log.info(" Password for Update Primary Email id  placeholder {}: " + label);
		return label;
	}

	public void populatePwdForUpdateEmailid(String password) {
		log.info("Populating Existing password for Update Primary Email id {} :" + password);
		sendKeys(txtPwdForChngPEmail, password);
		log.info("Existing Password for Updating Primary Email id populated successfully.");
	}

	@FindBy(css = "label[for='txtpwd1']")
	private WebElement txtPwdForDownloadMyData1;

	public String getPwdForDownloadMyData1Label() {
		log.info("Featching the Label of PwdForDownloadMyData1");
		String label = getText(txtPwdForDownloadMyData1);
		return label;
	}

	@FindBy(css = "#txtpwd1")
	private WebElement txtPwdForDownloadMyData;

	public void switchToDownloadMyDataPwdPopup() {
		switchToFrame(txtPwdForDownloadMyData);
		log.info("Download My Data Password PopUp is Successfully Switched");
	}

	public void populatePwdForDownloadMyData(String password) {
		log.info("Populating password for Download My Data  {} :" + password);
		sendKeys(txtPwdForDownloadMyData, password);
		log.info("Password is Successfully Entered for Downloading My Data.");
	}

	@FindBy(css = "#btnSubmitPassword")
	private WebElement btnSubmitDownloadMyData;

	public boolean isSubmitDownloadEnable() {
		return btnSubmitDownloadMyData.isDisplayed();
	}

	public void clickDownloadMyDataSubmitbtn() {
		click(btnSubmitDownloadMyData);
		log.info("Download My Data Password hab been successfully submitted");
	}

	@FindBy(xpath = "//div[@class='modal-dialog']//button[@class='closepopup']")
	private WebElement btnClosePopup;

	public void closeDownloadMyDataPopup() {
		click(btnClosePopup);
		log.info("Download My Data Pop up has been Successfully Closed");
	}

	public boolean isCloseDownloadMyDataPopup() {
		log.info("Checking that visible of close button on Download my data popup on My Profile page."
				+ btnClosePopup.isDisplayed());
		return isElementVisible(btnClosePopup);
	}

	@FindBy(css = "#btnenterPasswordCancel")
	private WebElement btnCancelDownloadMyData;

	public boolean isCancelDownloadEnable() {
		return btnCancelDownloadMyData.isDisplayed();
	}

	public void cancelDownloadMyDataPopup() {
		click(btnCancelDownloadMyData);
		log.info("Download My Data Pop up has been successfully Canceled");
	}

	@FindBy(css = "#hideShowEye")
	private WebElement lnkHideShowEye;

	public void clickHideShowEys() {
		click(lnkHideShowEye);
	}

	public String getHideShowEyelbl() {
		return getText(lnkHideShowEye);
	}

	public String getHideShowEyelbl1() {
		return getText(lnkHideShowEye);
	}

	@FindBy(css = ".right_profile_sec.timezone_wrpp h4")
	private WebElement lblTimeZoneHeading;

	public boolean isTimeZoneVisible() {
		log.info("Checking that visible of Time Zone on the My Profile page." + lblTimeZoneHeading.isDisplayed());
		return isElementVisible(lblTimeZoneHeading);
	}

	public String getTimeZoneLabel() {
		log.info("Fetching the Time Zone placeholder.");
		String label = getText(lblTimeZoneHeading);
		log.info(" Time Zone  placeholder on Right Rail is  {}: " + label);
		return label;
	}

	@FindBy(css = "#ContentPlaceHolder1_lblTimeZone")
	private WebElement lblTimeZoneValue;

	public boolean isTimeZoneValueVisible() {
		log.info("Checking that visible of Time Zone Value on the My Profile page." + lblTimeZoneValue.isDisplayed());
		return isElementVisible(lblTimeZoneValue);
	}

	public String getTimeZoneValueLabel() {
		log.info("Fetching the Time Zone Value placeholder.");
		String label = getText(lblTimeZoneValue);
		log.info(" Time Zone Value placeholder on Right Rail is  {}: " + label);
		return label;
	}

	@FindBy(css = ".right_profile_sec.timezone_wrpp a")
	private WebElement btnEditTimeZone;

	public boolean isTimeZoneEditButtonVisible() {
		log.info("Checking that visible of Time Zone Edit Button on the My Profile page."
				+ btnEditTimeZone.isDisplayed());
		return isElementVisible(btnEditTimeZone);
	}

	public String getEditTimeZoneLabel() {
		String label = getText(btnEditTimeZone);
		return label;
	}

	public void clickEditTimeZone() {
		click(btnEditTimeZone);
		log.info("Edit Time Zone Button successfully clicked");
	}

	@FindBy(css = "#settimezonetitle")
	private WebElement LblTimeZonePopupHeading;

	public String getLblTimeZonePopupHeading() {
		String label = getText(LblTimeZonePopupHeading);
		return label;
	}

	public boolean isTimeZonePopupHeadingVisible() {
		return isElementVisible(LblTimeZonePopupHeading);
	}

	@FindBy(css = "#popupSettimezone i")
	private WebElement lblEditTimeZonePopup;

	public void switchToEditTimeZonePopup() {
		switchToFrame(lblEditTimeZonePopup);
		log.info("Edit Time Zone Pop up has been Successfully Switched");
	}

	public String getSetTimeZonePopupIconLabel() {
		log.info("Fetching the Set Time Zone Label Icon placeholder.");
		String label = getText(lblEditTimeZonePopup);
		log.info(" Label of Icon on Set Time Zone Popup  {}: " + label);
		return label;
	}

	@FindBy(css = "#popupSettimezone p")
	private WebElement lblQuiteHoursTimeZonePopUp;

	public String getLblQuiteHoursTimeZonePopUpApp() {
		String label = getText(lblQuiteHoursTimeZonePopUp);
		return label;
	}

	@FindBy(css = "#ddlTimeZone")
	private WebElement selectTimeZonePopupDropdown;

	public String getTimeZoneLabelOnPopup() {
		log.info("Fetching the Time Zone Label on placeholder.");
		String label = getText(selectTimeZonePopupDropdown);
		log.info(" Time Zone Label on Set Time Zone Pop up  {}: " + label);
		return label;
	}

	public void selectTimeZone(String timeZone) {
		//click(selectTimeZonePopupDropdown);
		
		selectByVisibleText(selectTimeZonePopupDropdown, timeZone);
		//click((By.xpath("//select[@id='ddlTimeZone']/*[text()='--Select--']")));
		log.info(timeZone + "-- Has benn Successfully Updated");
	}

	public String getSelectTimeZonePopupDropdown() {
		Select select = new Select(selectTimeZonePopupDropdown);
		String selectedTimeZone = select.getFirstSelectedOption().getText();
		return selectedTimeZone;
	}

	@FindBy(css = "#btnCancelTimezone")
	private WebElement btnInputCancelTimeZonePopup;

	public void clickCancelTimeZonePopup() {
		click(btnInputCancelTimeZonePopup);
		log.info("Time Zone Popup has been Successfully Canceled");
	}

	public String getbtnCancelTimezonelabel() {
		String label = getAttribute(btnInputCancelTimeZonePopup, "value");
		return label;
	}

	@FindBy(css = "#btnSaveTimezone")
	private WebElement btnInputSaveTimeZonePopup;

	public void clicksaveTimeZonePopup() {
		click(btnInputSaveTimeZonePopup);
		log.info("Time Zone Popup has been Successfully Saved");
	}

	public String getBtnInputSaveTimeZonePopupLabel() {
		String label = getAttribute(btnInputSaveTimeZonePopup, "value");
		return label;
	}

	@FindBy(css = "#popupSettimezone button.close")
	private WebElement btnCloseTimeZonePopup;

	public void clickCloseTimeZonePopup() {
		click(btnCloseTimeZonePopup);
		log.info("Time Zone Popup has been Successfully Closed");
	}

	public boolean isCloseTimeZonePopupVisible() {
		return isElementVisible(btnCloseTimeZonePopup);
	}

	@FindBy(css = "#ProfileDivCardHeader0")
	private WebElement lblNickNameAccountCard;

	@FindBy(xpath = "//a[@data-target = '#divNickNamePopup']")
	private WebElement btnEditNickName;
	@FindBy(css = "#nicknametitletxt")
	private WebElement lblEditNickNamePopupTitle;

	@FindBy(css = "#txtAccountNickName")
	private WebElement txtNickNameFieldPopup;

	@FindBy(css = "#NickNamebtnCancel")
	private WebElement btnCancelNickNamePopup;

	@FindBy(css = "#NickNamebtnsave")
	private WebElement btnContinueNickNamePopup;

	@FindBy(xpath = "//a[text() ='Edit Nickname']")
	private WebElement btnEditNickNameGuestAccount;

	@FindBy(css = "#btnEnroll0")
	private WebElement btnEditBillTypeKebabMenuOption;

	@FindBy(css = "#ppr_enrl_titletxt")
	private WebElement lblEditBillTypePopupHeading;

	@FindBy(css = "#divEnroll div.modal-body")
	private WebElement lblEditBillTypePopupBodyTxt;

	@FindBy(css = "#divEnroll div.modal-body [for='rdnPaperless']")
	private WebElement lblPaperlessBillOption;

	@FindBy(css = "#divEnroll div.modal-body [for='rdnPaperless']>input")
	private WebElement rbtnPaperlessBillOption;

	@FindBy(css = "#divEnroll div.modal-body [for='rdnPaper']")
	private WebElement lblPaperBillOption;

	@FindBy(css = "#divEnroll div.modal-body [for='rdnPaper']>input")
	private WebElement rbtnPaperBillOption;

	@FindBy(css = "#divEnroll div.modal-body [for='rdnPaperBoth']")
	private WebElement lblBothBillOption;

	@FindBy(css = "#divEnroll div.modal-body [for='rdnPaperBoth']>input")
	private WebElement rbtnBothBillOption;

	@FindBy(xpath = " //button[@id = 'SaveEnroll']/preceding-sibling::button")
	private WebElement btnCancelEditBillTypePopup;

	@FindBy(css = "#SaveEnroll")
	private WebElement btnContinueEditBillTypePopup;

	@FindBy(xpath = "//a[@class = 'defaultbtnp'][@style='visibility:show']/preceding-sibling::div/p/label")
	private WebElement lblBillTypeValueAtServiceAddressCard;

	public String getValuePrimaryEmail(String value) {
		String attributeValue = getAttribute(valuePrimaryEmail, value);
		log.info("Value of primary Email field is {} " + value);
		return attributeValue;
	}

	public void clearValuePrimaryEmailField() {
		clear(valuePrimaryEmail);
		log.info("value Primary Email field cleared {}");
	}

	public void populateValuePrimaryEmailField(String email) {
		log.info("Populating valuePrimaryEmail {} :" + email);
		sendKeys(valuePrimaryEmail, email);
		log.info("valuePrimaryEmail populated successfully.");
	}

	public void populatePwdForChangeUserName(String password) {
		log.info("Populating Existing password for Update Primary Email id {} :" + password);
		sendKeys(txtPwdForChngPEmail, password);
		log.info("Existing Password for Updating Primary Email id populated successfully.");
	}

	@FindBy(css = " #enterPasswordPoup")
	private WebElement LnkDownloadData;

	public String getLnkDownloadDataLabel() {
		String label = getText(LnkDownloadData);
		return label;
	}

	@FindBy(css = ".MuiGrid-root.pageheading-box")
	private WebElement lblAccountInformationPageHeader;

	public String getAccountInformationPageHeaderLabel() {
		String label = getText(lblAccountInformationPageHeader);
		log.info("Account Information PageHeader Label  is : " + label);
		return label;
	}

	@FindBy(css = "#usertip")
	private WebElement lblProfilePageGenericMsgForField;

	public String getLblProfilePageGenericErrorMsgForFieldLabel() {
		String label = getText(lblProfilePageGenericMsgForField);
		return label;
	}

	@FindBy(css = ".error_messagecommon[role]")
	private WebElement txtEmailToastMessage;

	public String getEmailToastMessage() {
		log.info("Fetching the Email toast Message placeholder.");
		String label = getText(txtEmailToastMessage);
		log.info(" Email toast Message is   {}: " + label);
		return label;
	}

	@FindBy(css = ".error_messagecommon[role]")
	private WebElement txtUserNameToastMessage;

	public String getUserNameToastMessage() {
		log.info("Fetching the UserName toast Message placeholder.");
		String label = getText(txtUserNameToastMessage);
		log.info(" UserName toast Message is   {}: " + label);
		return label;
	}

	@FindBys(@FindBy(xpath = "//*[contains(@id,'lbldhome')]"))
	private List<WebElement> listAllLinkAccount;

	public List<WebElement> getListAllLinkAccountElement() {
		return listAllLinkAccount;
	}

	public String getLinkAccountLabel(WebElement element) {
		String label = getText(element);
		return label;
	}

	@FindBys(@FindBy(css = ".toggle_nvp a[aria-label='click to expand kebab menu']"))
	private List<WebElement> listAllLinkAccountKebabMenu;

	public void clickLinkAccountKebabMenu(WebElement element) {
		click(element);
	}

	public void clickThreeDotsToggleBtn(String utilityAccountNumber) {
		click(By.xpath("//*[@class='lbldivaccont'][text()='" + utilityAccountNumber
				+ "']/../..//*[contains(@id,'demo-menu-lower-right')]"));
	}

	public void clickUnlinkAccountBtn(String utilityAccountNumber) {
		click(By.xpath(
				"//*[@class='lbldivaccont'][text()='" + utilityAccountNumber + "']/../..//*[text()='Unlink Account']"));
	}

	@FindBy(css = " #btnUnlinkAccount")
	private WebElement btnContinueDeletePopUp;

	public void clickContinueDeletePopup() {
		click(btnContinueDeletePopUp);
	}

	@FindBy(css = "label[for*=\"rdores\"]")
	private WebElement lnkAccResidentialPopup;

	public void clickLnkAccResidentialPopup() {
		click(lnkAccResidentialPopup);
	}

	@FindBy(css = " #divAddAccountmodal h4")
	private WebElement lblLinkAccountPopupHeading;

	public String getLinkAccountPopupHeadingLabel() {
		String label = getText(lblLinkAccountPopupHeading);
		return label;
	}

	public boolean getLinkAccountPopupHeadingVisible() {
		return isElementVisible(lblLinkAccountPopupHeading);
	}

	@FindBy(css = " input[globalize = 'ML_OTP_txt_AcctNo']")
	private WebElement txtAccountNumberPopup;

	public void populateAccountNumberInLinkAccountPopup(String accno) {
		sendKeys(txtAccountNumberPopup, accno);
	}

	@FindBy(css = " input[globalize = 'ML_SrvcRqust_txtbx_ZipCode1']")
	private WebElement txtZipCodePopup;

	public void populateZipCodeInLinkAccountPopup(String zip) {
		sendKeys(txtZipCodePopup, zip);
	}

	@FindBy(css = " div[id = 'divAddAccountmodal'][inputtype = 'MeterNumber']")
	private WebElement txtMeterNumberPopup;

	public void populateMeterNumberInLinkAccountPopup(String meterNumber) {
		sendKeys(txtMeterNumberPopup, meterNumber);
	}

	@FindBy(css = " input[globalize = 'ML_SrvcRqust_lbl_SSN']")
	private WebElement txtSSNPopup;

	public void populateSSNInLinkAccountPopup(String SSN) {
		sendKeys(txtSSNPopup, SSN);
	}

	@FindBy(css = " input[globalize = 'ML_Msg_StreetAddress']")
	private WebElement txtStreetAddPopup;

	public void populateStreetAddInLinkAccountPopup(String StreetAdd) {
		sendKeys(txtStreetAddPopup, StreetAdd);
	}

	@FindBy(css = " #SubmitBtn")
	private WebElement btnLinkAccPopupSubmit;

	public void clickLinkAccountSubmitBtn() {
		click(btnLinkAccPopupSubmit);
	}

	@FindBy(css = "a#lnkbtnaddaccount")
	private WebElement btnLinkAccount;

	public WebElement getLinkAccountBtnElement() {
		return btnLinkAccount;
	}

	public boolean isLinkAccountBtnVisible() {
		return isElementVisible(btnLinkAccount);
	}

	public String getLinkAccountBtnLabel() {
		String label = getText(btnLinkAccount);
		return label;
	}

	public void clickLinkAccountBtn() {
		click(btnLinkAccount);
	}

	@FindBy(css = "#btncloseForgetMepopup")
	private WebElement deleteMyProfileCrossBtn;

	public boolean isDMPCrossBtnVisible() {
		return isElementVisible(deleteMyProfileCrossBtn);
	}

	public void clickDMPCrossBtn() {
		click(deleteMyProfileCrossBtn);
	}

	public void clickDownloadMyDataCancelBtn() {
		click(btnCancelDownloadMyData);
		log.info("Download My Data Pop up has been successfully Canceled");
	}

	// Unused Locator
	@FindBy(css = ".icon_setting.list-group-item a")
	private WebElement lnkSettingsMenu;
	@FindBy(css = ".icon_profile.list-group-item a")
	private WebElement lnkProfileMenu;
	@FindBy(css = ".payment_info.list-group-item a")
	private WebElement lnkPaymentInfoMenu;
	@FindBy(css = ".nav_left .marketing_pref a")
	private WebElement lnkMarketingPrefMenu;
	@FindBy(css = ".icon_aboutmyhome.list-group-item a")
	private WebElement lnkAboutMyHomeMenu;
	@FindBy(css = ".nitiF_acc_ico.list-group-item a")
	private WebElement lnkNotificationPrefMenu;
	@FindBy(css = ".InviteUser_ico.list-group-item a")
	private WebElement lnkInviteGuestUserMenu;
	@FindBy(css = "#txtPhone")
	private WebElement txtBoxMobileNumber;

	@FindBy(css = "#ddlquestions1")
	private WebElement lstBoxSecurityQ1;
	@FindBy(css = "#ddlquestions2")
	private WebElement lstBoxSecurityQ2;
	@FindBy(css = "#txtSecurityAns1")
	private WebElement txtBoxSecurityAns1;
	@FindBy(css = "#txtSecurityAns2")
	private WebElement txtBoxSecurityAns2;

	@FindBy(css = "#lblQuestion1")
	private WebElement valueSecurityQ1;
	@FindBy(css = "#lblQuestion2")
	private WebElement valueSecurityQ2;

	@FindBy(xpath = "//div[@class='mdl-button mdl-js-button edit_clickp'])[7]")
	private WebElement btnEditSecurityQuestion1;

	@FindBy(xpath = "//div[@class='mdl-button mdl-js-button edit_clickp'])[8]")
	private WebElement btnEditSecurityQuestion2;

	@FindBy(css = "#btnSecurityQuest")
	private WebElement btnSaveSecurityQuestion1;

	@FindBy(css = "#btnSecurityQuest1")
	private WebElement btnSaveSecurityQuestion2;

	@FindBy(xpath = "//*[@class='cncl_btnp hide_wrapperboxp'])[4]")
	private WebElement btnCancelPrimaryEmail;

	@FindBy(xpath = "//*[@class='cncl_btnp hide_wrapperboxp'])[7]")
	private WebElement btnCancelSecurityQuestion1;

	@FindBy(xpath = "//*[@class='cncl_btnp hide_wrapperboxp'])[8]")
	private WebElement btnCancelSecurityQuestion2;

	@FindBy(xpath = "//*[@class='lbldivaccont']/../..//*[text()='Make Default'])[1]")
	private WebElement btnMakeDefaultServiceAddr;

	@FindBy(xpath = "//*[@class='lbldivaccont']/../..//*[text()='Unlink Account'])[1]")
	private WebElement btnUnlinkAccountServiceAddr;

	@FindBy(xpath = "//div[@class='address_bottomp']/a[contains(@id,'divdefaultButton')][@style='visibility:hidden']")
	private WebElement btnNotSelectedDefaultServiceAddr;

	@FindBy(css = "#btnDefaultpopupsave")
	private WebElement btnMakeDefaultAddr;

	@FindBy(css = "#lbldhome1")
	private WebElement lblFirstOptionServiceAccount;

	@FindBy(css = "#popupChangeEmailAsUserId .modal-header")
	private WebElement popUpChangeEmailAddress;

	@FindBy(xpath = "//button[@id='btnEmailId']")
	private WebElement btnContinueChngEmailAdrsPopUp;

	@FindBy(css = ".submit-button")
	private WebElement btnSave;

	@FindBy(css = "[id*='lblpropertyaddress']")
	private WebElement lblPropertyAddress;

	@FindBy(css = "[id='lnkbtnaddaccount']")
	private WebElement btnAddAccount;

	@FindBy(css = ".profile-details .sub-name:not(.lblCustAcco_mob) span")
	private WebElement txtServiceAccountNumber;

	@FindBy(css = ".mail_address_acc .address-icon2")
	private WebElement lnkMailAddressEdit;

	@FindBy(css = "#lnkbtnaddaccount")
	private WebElement btnAddAccountButton;

	@FindBy(css = ".modal-md .registration_btn:first-child")
	private WebElement btnAddAccPopupSubmitBtn;

	@FindBy(css = "[inputtype='Account']")
	private WebElement txtBoxAccNumberAddBtnPopup;

	@FindBy(css = ".w2ui-msg-buttons .btn")
	private WebElement btnAddAccPopUpOkBtn;

	@FindBy(css = "#lbldmailAddress0")
	private WebElement lblMailingAddress;

	@FindBy(css = "[id*='btnMakeDefault']")
	private WebElement listAllLinkAccountMakeDefault;

	@FindBy(css = "#divenrollButton0")
	private WebElement btnFirstEnroll;

	@FindBy(css = "[id *= divenrollButton]")
	private WebElement btnListEnroll;

	@FindBy(css = "[id*= btnEnroll]")
	private WebElement btnListEnrollviaMenu;

	@FindBy(css = "#SaveEnroll")
	private WebElement btnContinueEnroll;

	@FindBy(css = "#divAddressPopup_ChangePass .modal-title")
	private WebElement lblPopupHeadingMailingAddress;

	@FindBy(css = ".close.mailingaddressclose")
	private WebElement btnCloseCrossMailingAddress;

	@FindBy(css = ".editMain span:first-child .mdl-ripple--center")
	private WebElement rdbStreetAddMailingAddress;

	@FindBy(xpath = "//span[@class = 'mdl-radio__label'])[1]")
	private WebElement lblStreetAddMailingAddress;

	@FindBy(xpath = "//input[@id='msg_streetaddress']")
	private WebElement rdbInputTagStreetAddMailingAdd;

	@FindBy(css = ".editMain span:nth-child(2) .mdl-ripple--center")
	private WebElement rdbPOBoxMailingAddress;

	@FindBy(xpath = "(//span[@class = 'mdl-radio__label'])[2]")
	private WebElement lblPOBoxMailingAddress;

	@FindBy(xpath = "//input[@id='msg_streetaddress']")
	private WebElement rdbInputTagPOBoxMailingAddress;

	@FindBy(css = "label#Addline1")
	private WebElement lblAddLine1POBoxMailingAdd;

	@FindBy(css = "[id$='cust_address1']")
	private WebElement txtAddLine1POBoxMailingAdd;

	@FindBy(css = "label[ globalize$= 'AddLine2']")
	private WebElement lblAddressLine2MailingAddress;

	@FindBy(css = "[id$='cust_address2']")
	private WebElement txtAddressLine2MailAdd;

	@FindBy(xpath = "//input[@id = 'cust_Zip']/following-sibling::label")
	private WebElement lblZipCodeMailingAddress;

	@FindBy(xpath = "//input[@id = 'cust_Zip']")
	private WebElement txtZipcodeMailingAddress;

	@FindBy(xpath = "//input[@id = 'cust_City']/following-sibling::label")
	private WebElement lblCityMailingAddress;

	@FindBy(css = "[id='cust_City']")
	private WebElement txtCityMailingAddress;

	@FindBy(css = "[id='ddlState'] option")
	private WebElement lstStateMailingAddress;

	@FindBy(xpath = "//*[@id ='ddlState']/following-sibling::label")
	private WebElement lblStateMailingAddress;

	@FindBy(css = "input#chkIsTempMailingAddress")
	private WebElement chkBoxIsTemporaryMailingAdd;

	@FindBy(xpath = "//input[@id = 'chkIsTempMailingAddress']/../..")
	private WebElement lblIsTemporaryMailingAddress;

	@FindBy(xpath = "//div[@id = 'divCalender']/div/label")
	private WebElement lblMailingAddUpToMailingAdd;

	@FindBy(xpath = "//div[@id = 'divCalender']/div/input")
	private WebElement txtMailingAddUpToMailingAdd;

	@FindBy(css = "#btnUpdateAddress")
	private WebElement btnUpdateMailingAddress;

	@FindBy(css = "#ppr_enrl_titletxt")
	private WebElement lblPaperlessPopupTitle;

	@FindBy(xpath = "//h4[@id ='ppr_enrl_titletxt']//..//..//div[@class='modal-body']")
	private WebElement txtPaperlessPopup;

	@FindBy(css = "#SaveEnroll")
	private WebElement btnPaperlessContinue;

	@FindBy(css = "#btncancelPopup")
	private WebElement btnPaperlessCancel;

	@FindBy(css = ".editMain span:first-child.mdl-ripple--center")
	private WebElement rdbStreetAddressMailAdd;

	@FindBy(css = ".editMain span:nth-child(2) .mdl-ripple--center")
	private WebElement radioPOBoxMailingAddress;

	@FindBy(css = "[id$='cust_address1']")
	private WebElement txtAddressLine1MailAdd;

	@FindBy(css = ".ZipCode")
	private WebElement txtZipCodeMailingAddress;

	@FindBy(css = ".editMain:not(.modal-lg) .modal-footer.submit-button:nth-child(2)")
	private WebElement btnValidateBtnMailingAdd;

	@FindBy(css = ".editMain:not(.modal-lg) .modal-footer.submit-button:first-child")
	private WebElement btnUpdateBtnMailAdd;

	@FindBy(css = ".w2ui-centered")
	private WebElement lblAddAccountMsg;

	@FindBy(css = ".confi_flat_icon:not(.edit_icon_acc)")
	private WebElement lnkEditEmail;

	@FindBy(css = ".modal-lg.modal- header h4")
	private WebElement lblEditEmailModalHeader;

	@FindBy(css = ".modal-lg.modal-header.closepopup")
	private WebElement btnEditEmailModalClose;

	@FindBy(css = ".in.modal-md.input_box_eff_wrap input")
	private WebElement txtEditEmailModalEmail;

	@FindBy(css = ".modal-lg p")
	private WebElement lblEditEmailModalDisclaimer;

	@FindBy(css = ".in.footertextes input:first-child")
	private WebElement btnEditEmailModalCancel;

	@FindBy(css = ".in.footertextes input:nth-child(2)")
	private WebElement btnEditEmailModalSubmit;

	@FindBy(css = ".w2ui-centered")
	private WebElement lblEditEmailModalMsgText;

	@FindBy(css = ".w2ui-msg-buttons.btn")
	private WebElement btnEditEmailModalOk;

	@FindBy(css = ".default-address-1 .mdl-radio__outer-circle")
	private WebElement rdoBtnMailingAddress;

	@FindBy(css = ".address-1[id^='lblpropertyaddress']")
	private WebElement lblPropertyAddresses;

	@FindBy(css = "[id^='lblutilitynumber']")
	private WebElement lblServiceAccountNumbers;

	@FindBy(css = "[id^='lblutilityPlanType']")
	private WebElement lblPlanType;

	@FindBy(css = ".toast.toast-warning")
	private WebElement lblWarningMandatory;

	@FindBy(css = " #divAddAccountmodal.closepopup")
	private WebElement btnCloseCrossPopup;
	@FindBy(css = " label[for*='rdores']")
	private WebElement rdbLinkAccResidentialPopup;

	@FindBy(css = "label[for*='rdocomm']")
	private WebElement rdbLinkAccCommercialPopup;

	@FindBy(css = "input[globalize ='ML_OTP_txt_AcctNo']~")
	private WebElement lblAccountNumberPopup;

	@FindBy(css = "input[globalize='ML_SrvcRqust_txtbx_ZipCode1']~")
	private WebElement lblZipCodePopup;

	@FindBy(css = "div[id='divAddAccountmodal'][inputtype='MeterNumber']~")
	private WebElement lblMeterNumberPopup;

	@FindBy(css = "input[globalize ='ML_SrvcRqust_lbl_SSN']~")
	private WebElement lblSSNPopup;

	@FindBy(css = "input[globalize ='ML_Registration_FID']~")
	private WebElement lblFIDTINPopup;

	@FindBy(css = " input[globalize = 'ML_Registration_FID']")
	private WebElement txtFIDTINPopup;

	@FindBy(css = "div[id='divAddAccountmodal'][inputtype='DL']")
	private WebElement lblDLPopup;

	@FindBy(css = " div[id = 'divAddAccountmodal'][inputtype = 'DL']")
	private WebElement txtDLPopup;

	@FindBy(css = "input[globalize='ML_Msg_StreetAddress']~")
	private WebElement lblStreetAddPopup;

	@FindBy(css = " #btnclose Need to check and dethect this Section code")
	private WebElement btnCancelLinkAccount;

	@FindBy(css = "input[title='Click to enter First Nam']")
	private WebElement txtBoxFirstNameLinkAcModal;

	@FindBy(css = "input[title='Click to enter Last Name']")
	private WebElement txtBoxLastNameLinkAcModal;

	@FindBy(css = "input[mappingname='UtilityAccountNumber']")
	private WebElement txtBoxAcNumLinkAcModal;

	@FindBy(css = "input[mappingname='PostalCode']")
	private WebElement txtBoxZipLinkAcModal;

	@FindBy(css = "input[mappingname='MeterNumber']")
	private WebElement txtBoxMeterNumberLinkAcModal;

	@FindBy(css = "input[mappingname='SSNNumber']")
	private WebElement txtBoxSsnNumberLinkAcModal;

	@FindBy(css = "input[mappingname='StreetNumber']")
	private WebElement txtBoxStreetAddressLinkAcModal;

	@FindBy(css = "input[mappingname='CustomerNo']")
	private WebElement txtBoxCustomerNumberLinkAcModal;

	@FindBy(css = "input[mappingname='DrivingLicence']")
	private WebElement txtBoxDrivingLicenseLinkModal;

	@FindBy(css = ".w2ui-msg-body.w2ui- centered")
	private WebElement txtAddAccPopupAlreadyRegAcc;

	@FindBy(css = " span.result")
	private WebElement txtValidationBtnClickMailAdd;

	@FindBy(css = ".MuiGrid-root.pageheading- box")
	private WebElement lblProfileHeader;
	@FindBy(css = ".set_notif_alertbox a")
	private WebElement btnSetNotificationPref;
	@FindBy(css = "#lnkbtnaddaccount")
	private WebElement btnLinkAccountButton;

	@FindBys(@FindBy(xpath = "//div[@class ='toggle_nvp']"))
	private List<WebElement> BtnToggleThreeDots;

	public List<WebElement> BtnToggleThreeDots() {
		return BtnToggleThreeDots;
	}
	// @FindBy (xpath="//div[@class ='toggle_nvp'])[1]")
	// private WebElement btnFirstToggleThreeDots;

	// @Gaurav Saxena
	@FindAll({ @FindBy(xpath = "//label[text() = 'Guest User Access']/../../../preceding-sibling::div//a)[1]"),
			@FindBy(css = "#demo-menu-lower-right0") })
	private WebElement btnToggleThreeDotsGuestAccount;

	@FindBys(@FindBy(xpath = "//div[@class='address_bottomp']/a[contains(@id,'divdefaultButton')][@style='visibility:show']"))
	private List<WebElement> btnSelectedDefaultServiceAddr;

	public List<WebElement> getSelectedDefaultServiceAdds() {
		return txtProfiledataName;
	}
}

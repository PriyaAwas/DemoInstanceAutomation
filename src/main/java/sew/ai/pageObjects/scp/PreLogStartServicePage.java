package sew.ai.pageObjects.scp;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import sew.ai.helpers.reporters.ExtentLogger;

public class PreLogStartServicePage extends HomePage {
	private static final Logger log = LogManager.getLogger(PreLogStartServicePage.class);

	public PreLogStartServicePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".MuiGrid-root.pageheading-box h1")
	private WebElement bl_ServicesHeader;

	public Boolean isServicesHeaderVisible() {
		log.info("Checking the visibility of bl_ServicesHeader on the page.");
		log.info("bl_ServicesHeader visibility status {}: " + isElementVisible(bl_ServicesHeader));
		return isElementVisible(bl_ServicesHeader);
	}
	
	@FindBy(css = "(//a[@globalize='ML_Msg_ServiceTurnOnOff'])[2]")
	private WebElement linkServices;

	public void clickLinkServices() {
		log.info("Clicking the link Services");
		click(linkServices);
		log.info("Pre Log link Services clicked successfully.");
	}

	@FindBy(css = " .headEfficiency span")
	private WebElement lbl_PreLoginServicesHeader;

	@FindBy(css = " #new-request > div[class]:not([style*='display: none']) h2")
	private WebElement lbl_TopicName;

	@FindBy(xpath = " //div[@class='full_width_input_sec'][not(contains(@style, 'none'))]//label[text()='Primary Phone Number']")
	private WebElement lbl_PrimaryPhoneNumberCA;

	@FindBy(xpath = " //div[@class='full_width_input_sec'][not(contains(@style, 'none'))]//label[contains(text(),'Alternate Phone')]")
	private WebElement lbl_AlternatePhoneNumberCA;

	@FindBy(xpath = " //div[@class='full_width_input_sec'][not(contains(@style, 'none'))]//label[text()='Email Address']")
	private WebElement lbl_EmailAddressCA;

	@FindBy(xpath = " //div[@class='full_width_input_sec'][not(contains(@style, 'none'))]//label[text()='Street Number']")
	private WebElement lbl_StreetNumberMA;

	@FindBy(xpath = " //div[@class='full_width_input_sec'][not(contains(@style, 'none'))]//label[text()='Street Name']")
	private WebElement lbl_StreetNameMA;

	@FindBy(xpath = " //div[@class='full_width_input_sec'][not(contains(@style, 'none'))]//label[text()='Apt/Unit Number']")
	private WebElement lbl_AptUnitNumberMA;

	@FindBy(xpath = " //div[@class='full_width_input_sec'][not(contains(@style, 'none'))]//label[text()='City']")
	private WebElement lbl_CityMA;

	@FindBy(xpath = " //div[@class='full_width_input_sec'][not(contains(@style, 'none'))]//label[text()='State']")
	private WebElement lbl_StateMA;

	@FindBy(xpath = " //div[@class='full_width_input_sec'][not(contains(@style, 'none'))]//label[text()='Zip Code']")
	private WebElement lbl_ZipcodeMA;

	@FindBy(xpath = " //div[@class='full_width_input_sec'][not(contains(@style, 'none'))]//label[text()='Previous Meter read:']")
	private WebElement lbl_PreviousMeterReadPV;

	@FindBy(xpath = " //div[@class='full_width_input_sec'][not(contains(@style, 'none'))]//label[text()='Current Meter read:']")
	private WebElement lbl_CurrentMeterReadPV;

	@FindBy(xpath = " //div[@class='full_width_input_sec'][not(contains(@style, 'none'))]//label[text()='Add Attachment']")
	private WebElement lbl_AddAttachmentPV;

	@FindBy(xpath = " #confrmmsgpopup")
	private WebElement lbl_SubmitRequestPopupHeading;

	@FindBy(xpath = " .w2ui-centered.w2ui-alert-msg")
	private WebElement lbl_SubmitRequestPopupContent;

	@FindBy(xpath = " #DeleteNotificationMail .modal-dialog .modal-content .modal-body")
	private WebElement lbl_DeleteConfirmationPopupContent;

	@FindBy(xpath = " //div[@class='full_width_input_sec'][not(contains(@style, 'none'))]//label[text()='Additional Comments']")
	private WebElement lbl_AdditionalComments;

	@FindBy(css = " #tblSavedForms_info")
	private WebElement lbl_PaginationInfo;

	@FindBy(css = " #toast-container")
	private WebElement lbl_DeleteSuccessfullyToast;

	@FindBy(css = " #toast-container .toast-message")
	private WebElement lbl_ValidationToastMessage;

	@FindBy(css = " .w2ui-tag-body.w2ui-tag-right")
	private WebElement lbl_MoveOutDateValidationMsg;

	@FindBy(css = " #cmTopic")
	private WebElement lbl_RequestId;

	@FindBy(css = " #cmAccountNumber")
	private WebElement lbl_RequestType;

	@FindBy(css = " .emailDetails_inner > span:nth-child(3)")
	private WebElement lbl_Subject;

	@FindBy(css = " #cmSubject")
	private WebElement lbl_RequestRaisedOn;

	@FindBy(css = " .emailDetails_inner > span:nth-child(5)")
	private WebElement lbl_From;
	@FindBy(css = " .emailDetails_inner > span:nth-child(6)")
	private WebElement lbl_Details;
	@FindBy(css = " .Attachment-mail a")
	private WebElement lbl_Attachement;
	@FindBy(css = " #tblSubmittedForms th")
	private WebElement lbl_GridHeader;
	@FindBy(css = " #sidebar_wrapper_box h2")
	private WebElement lbl_SidebarTrackRequestHeader;
	@FindBy(css = " .wickedpicker__title")
	private WebElement lblCalendarScheduleTime;
	@FindBy(css = " #tblSavedForms tbody tr:nth-child(1) td:nth-child(1)")
	private WebElement lblSaveFormsServiceRequest;
	@FindBy(css = " #caseNumber")
	private WebElement lblCaseNumber;
	@FindBy(css = " div.toast-message")
	private WebElement lblToastMessage;
	@FindBy(css = " .heading3_formpreview")
	private WebElement lblformpreview;

	public void populateFormpreview(String formview) {
		log.info("Populating Formpreview {} :" + formview);
		sendKeys(lblformpreview, formview);
		log.info("Formpreview populated successfully.");
	}

	public String getFormpreview() {
		log.info("Checking theFormpreview  on the page.");
		String option = getText(lblformpreview);
		log.info("Formpreview value {}: " + option);
		return option;
	}

	@FindBys(@FindBy(css = " #tblData .value"))
	private List<WebElement> lblformpreviewValue;

	public List<WebElement> getObjectsFormpreviewValue() {
		return lblformpreviewValue;
	}

	@FindBy(css = " .secServiceTitle.without_separator div")
	private WebElement lblPreLoginSerMoveInmsg;
	@FindBy(css = " .secServiceTitle.separator div")
	private WebElement lblPreLoginSerMoveInCurrentAdd;
	@FindBy(xpath = " (//div[@class ='secServiceTitle separator'])[2]/div")
	private WebElement lblPreLoginSerMoveInStartSer;
	@FindBy(xpath = " (//div[@class ='secServiceTitle separator'])[3]/div")
	private WebElement lblPreLoginSerMoveInBilling;
	@FindBy(xpath = " (//div[@class ='secServiceTitle separator'])[4]/div")
	private WebElement lblPreLoginSerMoveInBillingAddress;
	@FindBy(css = " .toast-message")
	private WebElement lbltoastmsg;

	@FindBy(css = "#tblSavedForms_paginate li[class*='active'] a")
	private WebElement lnk_PageActive;
	@FindBy(css = "#tblSubmittedForms  tbody tr > td:nth-child(1) a")
	private WebElement lnk_RequestIdFiltered;
	@FindBy(css = ".tab button:nth-child(1)")
	private WebElement lnk_NewRequestTab;

	public Boolean isNewRequestTabVisible() {
		log.info("Checking the visibility of New Request tab on the page.");
		log.info("New Request tab visibility status {}: " + isElementVisible(lnk_NewRequestTab));
		return isElementVisible(lnk_NewRequestTab);
	}

	public String getAtrributeNewRequestTab(String value) {
		String newRequesttapvalue = getAttribute(lnk_NewRequestTab, value);
		log.info("New Request Tap is {} ", value);
		return newRequesttapvalue;
	}

	public String getNewRequestTab() {
		log.info("Fetching the new Request tab.");
		String label = getText(lnk_NewRequestTab);
		log.info("new Request tab {}: " + label);
		return label;
	}

	@FindBy(css = ".tab button:nth-child(2)")
	private WebElement lnk_TrackRequestTab;

	public Boolean isTrackRequestTabVisible() {
		log.info("Checking the visibility of New Request tab on the page.");
		log.info("New Request tab visibility status {}: " + isElementVisible(lnk_TrackRequestTab));
		return isElementVisible(lnk_TrackRequestTab);
	}

	@FindBy(css = ".tab button:nth-child(3)")
	private WebElement lnk_SavedFormsTab;

	public Boolean isSavedFormsTabVisible() {
		log.info("Checking the visibility of company logo on the page.");
		log.info("Company logo visibility status {}: " + isElementVisible(lnk_SavedFormsTab));
		return isElementVisible(lnk_SavedFormsTab);
	}

	public String getSavedFormsTab() {
		log.info("Fetching the password placeholder.");
		String label = getText(lnk_SavedFormsTab);
		log.info("Password placeholder {}: " + label);
		return label;
	}

	public String getSavedFormsTab(String value) {
		String saveFormvalue = getAttribute(lnk_SavedFormsTab, value);
		log.info("Save form value field is {} " + value);
		return saveFormvalue;
	}

	@FindBys(@FindBy(css = "#new-request > div[class]:not([style*='display: none'])"))
	private List<WebElement> lnk_ServicesTiles;

	public List<WebElement> getObjectsServicesTiles() {
		return lnk_ServicesTiles;
	}

	public boolean isObjectServicesImgTilesVisible(int value) {
		return isElementVisible(
				driver.findElements(By.cssSelector("#new-request > div[class]:not([style*='display: none'])"))
						.get(value).findElement(By.cssSelector(".lefticon")));
	}

	public boolean isObjectChildLabelServicesTilesVisible(int value) {
		return isElementVisible(
				driver.findElements(By.cssSelector("#new-request > div[class]:not([style*='display: none'])"))
						.get(value).findElement(By.cssSelector(".rightcontentservice h2")));
	}

	public boolean isObjectSubTitleLabelServicesTilesVisible(int value) {
		return isElementVisible(
				driver.findElements(By.cssSelector("#new-request > div[class]:not([style*='display: none'])"))
						.get(value).findElement(By.cssSelector("p")));
	}

	public String getObjectServicesImgTilesVisible(int value) {
		return getText(driver.findElements(By.cssSelector("#new-request > div[class]:not([style*='display: none'])"))
				.get(value).findElement(By.cssSelector(".lefticon")));
	}

	public String getObjectChildLabelServicesTilesVisible(int value) {
		return getText(driver.findElements(By.cssSelector("#new-request > div[class]:not([style*='display: none'])"))
				.get(value).findElement(By.cssSelector(".rightcontentservice h2")));
	}

	public void clickObjectChildLabelServicesTilesVisible(int value) {
		click(driver.findElements(By.cssSelector("#new-request > div[class]:not([style*='display: none'])")).get(value)
				.findElement(By.cssSelector(".rightcontentservice h2")));
	}

	public String getObjectSubTitleLabelServicesTilesVisible(int value) {
		return getText(driver.findElements(By.cssSelector("#new-request > div[class]:not([style*='display: none'])"))
				.get(value).findElement(By.cssSelector(".rightcontentservice h2")));
	}

	public void clickObjectServicesImgTilesVisible(int value) {
		click(driver.findElements(By.cssSelector("#new-request > div[class]:not([style*='display: none'])")).get(value)
				.findElement(By.cssSelector(".lefticon")));
	}

	@FindBy(css = "a[globalize='ML_OuterHeader_txt_BackToLogin']")
	private WebElement lnk_HomePageIcon;
	@FindBy(xpath = "//div[@id='ui-datepicker-div']//td[@data-event='click']/a")
	private WebElement lnk_DateElements;
	@FindBy(css = "#tblSavedForms_paginate")
	private WebElement lnk_PreviousPagination;
	@FindBy(css = "#tblSavedForms_next")
	private WebElement lnk_NextPagination;
	@FindBy(css = "#tblSavedForms_paginate li a")
	private WebElement lnk_Pages;
	@FindBy(css = "#closeRequestbox a")
	private WebElement lnkTrackRequestClose;

	@FindBy(css = " input[title='Person Available']")
	private WebElement txtBoxPersonAvailable;
	@FindBy(css = " input[class='Phone formattedinput ']")
	private WebElement txtBoxMobileNumber;
	@FindBy(css = " input[title='Schedule Time']")
	private WebElement txtBoxScheduleTime;
	@FindBy(css = " #tblSavedForms_filter input[type='search']")
	private WebElement txt_SearchRequestIdSavedForms;
	@FindBy(css = " #tblSavedForms th")
	private WebElement txt_SavedFormsGridHeader;
	@FindBy(xpath = " //input[@inputtype='Account']")
	private WebElement txt_ServiceAccountNumber;
	@FindBy(css = " #tblSavedForms_filter [type='search']")
	private WebElement txtSaveFormsServiceRequest;
	@FindBy(css = ".tracking_area #txtTrackingId")
	private WebElement txt_PreLogTrackReqSearch;

	public void clickPreLogTrackReqSearchBtn() {
		log.info("Clicking the sign in button.");
		click(txt_PreLogTrackReqSearch);
		log.info("Pre Log TrackRequest clicked successfully.");
	}

	public void populateTrackReqSearch(String trackRequest) {
		log.info("Populating Trackrequest {} :" + trackRequest);
		sendKeys(txt_PreLogTrackReqSearch, trackRequest);
		log.info("Username populated successfully.");
	}

	@FindBy(css = " .tracking_area #txtSavedId")
	private WebElement txtPreLoginSavedFormTrackReqSearch;

	public void populatePreLoginSavedFormTrackReqSearch(String trackRequest) {
		log.info("Populating PreLoginSavedFormTrackReqSearch {} :" + trackRequest);
		sendKeys(txtPreLoginSavedFormTrackReqSearch, trackRequest);
		log.info("Username populated successfully.");
	}

	@FindBy(css = " .info_connect_txt")
	private WebElement txtPreLoginSavedFormPopup;

	public String getPreLoginSavedFormPopup() {
		log.info("Checking PreLoginSavedFormPopup on the page.");
		String option = getText(txtPreLoginSavedFormPopup);
		log.info("PreLoginSavedFormPopup value {}: " + option);
		return option;
	}

	@FindBy(css = "[globalize='ML_OTP_txt_AcctNo']")
	private WebElement txtPreLoginMoveInAccountNumber;

	public Boolean isPreLoginMoveInAccountNumberVisible() {
		log.info("Checking the visibility of txtPreLoginMoveInAccountNumber on the page.");
		log.info("txtPreLoginMoveInAccountNumber visibility status {}: "
				+ isElementVisible(txtPreLoginMoveInAccountNumber));
		return isElementVisible(txtPreLoginMoveInAccountNumber);
	}

	@FindBy(css = "[globalize= 'ML_SrvcRqust_Date']")
	private WebElement txtPreLoginMoveInDate;

	public Boolean isPreLoginMoveInDateVisible() {
		log.info("Checking the visibility of txtPreLoginMoveInDate on the page.");
		log.info("txtPreLoginMoveInDate visibility status {}: " + isElementVisible(txtPreLoginMoveInDate));
		return isElementVisible(txtPreLoginMoveInAccountNumber);
	}

	@FindBy(css = "[globalize='ML_SrvcRqust_txtbx_FirstName']")
	private WebElement txtPreLoginSerMoveInFirstName;

	public Boolean isPreLoginSerMoveInFirstNameVisible() {
		log.info("Checking the visibility of txtPreLoginSerMoveInFirstName on the page.");
		log.info("txtPreLoginSerMoveInFirstName visibility status {}: "
				+ isElementVisible(txtPreLoginSerMoveInFirstName));
		return isElementVisible(txtPreLoginSerMoveInFirstName);
	}

	@FindBy(css = "[globalize='ML_SrvcRqust_txtbx_LastName']")
	private WebElement txtPreLoginSerMoveInLastName;

	public Boolean istxtPreLoginSerMoveInLastNameVisible() {
		log.info("Checking the visibility of txtPreLoginSerMoveInLastName on the page.");
		log.info(
				"txtPreLoginSerMoveInLastName visibility status {}: " + isElementVisible(txtPreLoginSerMoveInLastName));
		return isElementVisible(txtPreLoginSerMoveInLastName);
	}

	@FindBy(css = "[globalize='ML_CONNECTME_Lbl_ReporterEmail']")
	private WebElement txtPreLoginSerMoveInEmail;

	public Boolean isPreLoginSerMoveInEmailVisible() {
		log.info("Checking the visibility of txtPreLoginSerMoveInEmail on the page.");
		log.info("txtPreLoginSerMoveInLastName visibility status {}: " + isElementVisible(txtPreLoginSerMoveInEmail));
		return isElementVisible(txtPreLoginSerMoveInEmail);
	}

	@FindBy(css = "[globalize='ML_SrvcRqust_lbl_SSN.input_effect']")
	private WebElement txtPreLoginSerMoveInSSL;

	public Boolean istxtPreLoginSerMoveInSSLVisible() {
		log.info("Checking the visibility of txtPreLoginSerMoveInSSL on the page.");
		log.info("txtPreLoginSerMoveInSSL visibility status {}: " + isElementVisible(txtPreLoginSerMoveInSSL));
		return isElementVisible(txtPreLoginSerMoveInSSL);
	}

	@FindBy(css = "[globalize='ML_MYACCOUNT_ContactNum_ContactType']")
	private WebElement txtPreLoginSerMoveInContactType;

	public Boolean isPreLoginSerMoveInContactTypeVisibleVisible() {
		log.info("Checking the visibility of txtPreLoginSerMoveInSSL on the page.");
		log.info("txtPreLoginSerMoveInContactType visibility status {}: "
				+ isElementVisible(txtPreLoginSerMoveInContactType));
		return isElementVisible(txtPreLoginSerMoveInContactType);
	}

	@FindBy(css = "[globalize='ML_lowincome_Date_of_Birth']")
	private WebElement txtPreLoginSerMoveInDOB;

	public Boolean isPreLoginSerMoveInContactTypeVisible() {
		log.info("Checking the visibility of txtPreLoginSerMoveInDOB on the page.");
		log.info("txtPreLoginSerMoveInDOB visibility status {}: " + isElementVisible(txtPreLoginSerMoveInDOB));
		return isElementVisible(txtPreLoginSerMoveInDOB);
	}

	//@FindBy(css = "[globalize=' ML_MYACCOUNT_ContactNum_Primary.Phone.input_effect']")
	@FindBy(css = "[id='e76f462e-4a65-4e58-89e4-0ffeec096949']")
	private WebElement txtPreLoginSerMoveInPrimaryContactNo;

	public Boolean isPreLoginSerMoveInPrimaryContactNoVisible() {
		log.info("Checking the visibility of txtPreLoginSerMoveInPrimaryContactNo on the page.");
		log.info("txtPreLoginSerMoveInPrimaryContactNo visibility status {}: "
				+ isElementVisible(txtPreLoginSerMoveInPrimaryContactNo));
		return isElementVisible(txtPreLoginSerMoveInPrimaryContactNo);
	}

	@FindBy(css = ".full_width_input_sec div:nth-child(1) [globalize=' ML_SrvcRqust_p_StreetNo'.input_effect']")
	private WebElement txtPreLoginSerMoveInCurrentAddStreetNum;

	public Boolean isPreLoginSerMoveInCurrentAddStreetNumVisible() {
		log.info("Checking the visibility of txtPreLoginSerMoveInPrimaryContactNo on the page.");
		log.info("txtPreLoginSerMoveInCurrentAddStreetNum visibility status {}: "
				+ isElementVisible(txtPreLoginSerMoveInCurrentAddStreetNum));
		return isElementVisible(txtPreLoginSerMoveInCurrentAddStreetNum);
	}

	@FindBy(css = "(//input[@globalize ='ML_SrvcRqust_p_StrretName'])[1]")
	private WebElement txtPreLoginSerMoveInCurrentAddStreetName;

	public Boolean isPreLoginSerMoveInCurrentAddStreetNameVisible() {
		log.info("Checking the visibility of SerMoveInCurrentAddStreetName on the page.");
		log.info("SerMoveInCurrentAddStreetName visibility status {}: "
				+ isElementVisible(txtPreLoginSerMoveInCurrentAddStreetName));
		return isElementVisible(txtPreLoginSerMoveInCurrentAddStreetName);
	}

	@FindBy(xpath = "(//select[@globalize='ML_Service_li_Type'])[1]")
	private WebElement txtPreLoginSerMoveInCurrentAddApartment;

	@FindBy(xpath = "(//input[@globalize='ML_CONNECTME_Lbl_Apt'])[1]")
	private WebElement txtPreLoginSerMoveInCurrentAddUnitNo;

	public Boolean isPreLoginSerMoveInCurrentAddUnitNoVisible() {
		log.info("Checking the visibility of txtPreLoginSerMoveInCurrentAddUnitNo on the page.");
		log.info("txtPreLoginSerMoveInCurrentAddUnitNo visibility status {}: "
				+ isElementVisible(txtPreLoginSerMoveInCurrentAddUnitNo));
		return isElementVisible(txtPreLoginSerMoveInCurrentAddUnitNo);
	}

	@FindBy(xpath = "(//input[@globalize=' ML_SrvcRqust_txtbx_City'])[1]")
	private WebElement txtPreLoginSerMoveInCurrentAddCity;

	public Boolean isPreLoginSerMoveInCurrentAddCityVisible() {
		log.info("Checking the visibility of txtPreLoginSerMoveInCurrentAddUnitNo on the page.");
		log.info("PreLoginSerMoveInCurrentAddCity visibility status {}: "
				+ isElementVisible(txtPreLoginSerMoveInCurrentAddCity));
		return isElementVisible(txtPreLoginSerMoveInCurrentAddCity);
	}

	@FindBy(xpath = "(//input[@globalize='ML_SrvcRqust_P_ZipCode'])[1]")
	private WebElement txtPreLoginSerMoveInCurrentAddZipCode;

	public Boolean isPreLoginSerMoveInCurrentAddZipCodeVisible() {
		log.info("Checking the visibility of txtPreLoginSerMoveInCurrentAddZipCode on the page.");
		log.info("txtPreLoginSerMoveInCurrentAddZipCode visibility status {}: "
				+ isElementVisible(txtPreLoginSerMoveInCurrentAddZipCode));
		return isElementVisible(txtPreLoginSerMoveInCurrentAddZipCode);
	}

	@FindBy(css = "[globalize='ML_SrvcRqust_lbl_StrtDate']")
	private WebElement txtPreLoginSerMoveInRequestStartDate;

	@FindBy(xpath = " (//input[@globalize='ML_SrvcRqust_p_StreetNo'])[2]")
	private WebElement txtPreLoginSerMoveInRequestStartStreetNo;

	@FindBy(xpath = " (//input[@globalize='ML_SrvcRqust_p_StrretName'])[2]")
	private WebElement txtPreLoginSerMoveInRequestStartStreetName;

	@FindBy(xpath = " (//select[@globalize='ML_Service_li_Type'])[2]")
	private WebElement txtPreLoginSerMoveInRequestStartApartment;

	@FindBy(xpath = "(//input[@globalize='ML_CONNECTME_Lbl_Apt'])[2]")
	private WebElement txtPreLoginSerMoveInRequestStartUnit;

	@FindBy(xpath = " (//input[@globalize='ML_SrvcRqust_txtbx_City'])[2]")
	private WebElement txtPreLoginSerMoveInRequestStartCity;

	@FindBy(xpath = " (//input[@globalize='ML_SrvcRqust_txtbx_MState'])[2]")
	private WebElement txtPreLoginSerMoveInRequestStartState;

	@FindBy(xpath = " (//input[@globalize='ML_SrvcRqust_P_ZipCode'])[2]")
	private WebElement txtPreLoginSerMoveInRequestStartZipCode;

	@FindBy(css = " [globalize ='ML_SrvcRqust_lbl_SameNewAddr']")
	private WebElement txtPreLoginSerMoveInBillingAdd;
	@FindBy(css = " [globalize*='ML_DynamicForm_DynamicKey']")
	private WebElement txtPreLoginSerMoveInBillingAddStreetNo;
	@FindBy(css = " [globalize='ML_SrvcRqust_p_StrretName'].IsLocation")
	private WebElement txtPreLoginSerMoveInBillingAddStreetName;

	@FindBy(xpath = "  (//select[@globalize='ML_Service_li_Type'])[3]")
	private WebElement txtPreLoginSerMoveInBillingAddApartment;

	@FindBy(xpath = " (//input[@globalize='ML_CONNECTME_Lbl_Apt'])[3]")
	private WebElement txtPreLoginSerMoveInBillingAddUnitNo;

	@FindBy(xpath = "(//input[@globalize='ML_SrvcRqust_txtbx_City'])[3]")
	private WebElement txtPreLoginSerMoveInBillingAddCity;

	@FindBy(xpath = " (//input[@globalize='ML_SrvcRqust_txtbx_MState'])[3]")
	private WebElement txtPreLoginSerMoveInBillingAddState;

	@FindBy(xpath = " (//input[@globalize='ML_SrvcRqust_P_ZipCode'])[3]")
	private WebElement txtPreLoginSerMoveInBillingAddZipCode;

	@FindBy(css = " [globalize='ML_SrvcRqust_txtbx_Comment']")
	private WebElement txtPreLoginSerMoveInComment;

	@FindBy(css = " [globalize='ML_SERVICE_Lbl_PersonAvailable']")
	private WebElement txtPreLoginPersonalInfo;

	@FindBy(xpath = " //li//div[text()='CALIFORNIA']")
	private WebElement txtPreLoginState;
	@FindBy(xpath = " //li//div[text()='Irvine(CA)']")
	private WebElement txtPreLoginCity;
	@FindBy(css = " .choose_file_txt .att_disclaimer")
	private WebElement txtChooseFileDisclaimer;
	@FindBy(css = " [globalize =' ML_SrvcRqust_txtbx_Comment']")
	private WebElement txtAdditionalComment;
//
	@FindBy(css = "#toast-container button")
	private WebElement btn_CloseDeleteSuccessfullyToast;
	@FindBy(css = ".srvc_rqst_btn #btnNext")
	private WebElement btn_Next_sr;
	@FindBy(css = ".srvc_rqst_btn #btnSaveToDraft")
	private WebElement btn_Savesr;

	public Boolean isSavesrVisible() {
		log.info("Checking the visibility of company logo on the page.");
		log.info("Company logo visibility status {}: " + isElementVisible(btn_Savesr));
		return isElementVisible(btn_Savesr);
	}

	@FindBy(css = " #trackRequest")
	private WebElement btn_TrackRequest;

	public void clickTrackRequestBtn1() {
		log.info("Clicking the sign in button.");
		click(btn_TrackRequest);
		log.info("Track Request clicked successfully.");
	}

	@FindBy(css = " #btnNext")
	private WebElement btn_Next;
	@FindBy(css = " #btnSaveToDraft")
	private WebElement btn_Save;
	@FindBy(css = " #btnSubmitChanges")
	private WebElement btn_SubmitPYF;
	@FindBy(css = " #btnBack")
	private WebElement btn_BackPYF;
	@FindBy(xpath = " //button[text()='Ok']")
	private WebElement btn_SubmitRequestPopupOk;
	@FindBy(css = " .w2ui-popup-buttons #Yes")
	private WebElement btn_PopupYes;
	@FindBy(css = " .w2ui-popup-buttons #No")
	private WebElement btn_PopupNo;
	@FindBy(css = " #toast-container .toast-close-button")
	private WebElement btn_CloseValidationToast;
	@FindBy(css = " #sidebar_wrapper_box .wrapper_box_close")
	private WebElement btn_SidebarClose;
	@FindBy(css = " a[class='editConnect']")
	private WebElement btn_EditIcons;
	@FindBy(css = " a[class='deleteConnect']")
	private WebElement btn_DeleteIcons;
	@FindBy(css = " .submit-button")
	private WebElement btnSubmit;
	@FindBy(css = " .wickedpicker__close")
	private WebElement btnCloseCalendar;
	@FindBy(css = " .tracking_area #TrackingBtn")
	private WebElement btnTrackRequest;

	public void clickTrackRequestBtn() {
		log.info("Clicking the btnTrackRequest button.");
		click(btnTrackRequest);
		log.info("btnTrackRequest button clicked successfully.");
	}

	@FindBy(css = " [globalize= 'ML_Saved_Forms']")
	private WebElement btnSaveForms;

	public void clickSaveFormsBtn() {
		log.info("Clicking the btnSaveForms button.");
		click(btnSaveForms);
		log.info("btnSaveForms button clicked successfully.");
	}

	@FindBy(css = ".Email.input_effect")
	private WebElement txtEmailAddress;

	public void populateEmailAddress(String emailAddress) {
		log.info("Populating EmailAddress {} :" + emailAddress);
		sendKeys(txtEmailAddress, emailAddress);
		log.info("EmailAddress populated successfully.");
	}

	@FindBy(css = " .tracking_area #btnSaveSubmit")
	private WebElement btnPreLoginSaveFormsSubmit;

	public void clickPreLoginSaveFormsSubmitBtn() {
		log.info("Clicking the PreLoginSaveFormsSubmit button.");
		click(btnPreLoginSaveFormsSubmit);
		log.info("PreLoginSaveFormsSubmit button clicked successfully.");
	}

	@FindBy(css = " #btnNext.submit-button")
	private WebElement btnPreLoginSaveFormsNext;

	public void clickPreLoginSaveFormsNextBtn() {
		log.info("Clicking the btnPreLoginSaveFormsNext button.");
		click(btnPreLoginSaveFormsNext);
		log.info("btnPreLoginSaveFormsNext button clicked successfully.");
	}

	@FindBy(css = " #btnSubmitChanges.submit-button")
	private WebElement btnPreLoginSaveFormSubmit;

	public void clickPreLoginSaveFormSubmit() {
		click(btnPreLoginSaveFormSubmit);
		log.info("PreLoginSaveFormSubmit clicked successfully.");
	}

	@FindBy(css = " .srvc_rqst_btn #btnSaveToDraft")
	private WebElement btnSaveToDraft;
	@FindBy(css = " #btnNext[type='button']")
	private WebElement btnMoveInNext;

	public Boolean isMoveInNextVisible() {
		log.info("Checking the visibility of company logo on the page.");
		log.info("Company logo visibility status {}: " + isElementVisible(btnMoveInNext));
		return isElementVisible(btnMoveInNext);
	}

	public void clickMoveInNextBtn() {
		log.info("Clicking the sign in button.");
		click(btnMoveInNext);
		log.info("btnMoveInNext button clicked successfully.");
	}

	@FindBy(css = " .srvc_rqst_btn #btnSubmitChanges")
	private WebElement btnMoveInSubmit;
	@FindBy(css = " .multiselect.dropdown-toggle.btn.btn-default")
	private WebElement btnMultiSelectBillingAddress;
	@FindBy(css = " .srvc_rqst_btn #btnNext")
	private WebElement btnMoveInNextsrv;
	@FindBy(css = " #btnok")
	private WebElement btnMoveInPopupOk;
	@FindBy(css = "[globalize='ML_CONNECTME_Lbl_AddAttach']")
	private WebElement btnChooseFile;
	@FindBy(css = " #navbarDropdown i")
	private WebElement btnMorevertnavbar;
	@FindBy(css = " #FileUpload1")
	private WebElement btnChooseFile1;
	@FindBy(css = ".editConnect")
	private WebElement btnEditConnect;
//
//
	@FindBy(css = "#new-request > div[class]:not([style*='display: none']) .lefticon")
	private WebElement img_LeftIcon;
	@FindBy(css = "div.logo a img")
	private WebElement img_ServicesBanner;

	public Boolean isServicesBannerVisible() {
		log.info("Checking the visibility of company logo on the page.");
		log.info("Company logo visibility status {}: " + isElementVisible(img_ServicesBanner));
		return isElementVisible(img_ServicesBanner);
	}

	public String getServicesBanner(String value) {
		String maxLen = getAttribute(img_ServicesBanner, value);
		log.info("Services Banner value is {} " + value);
		return maxLen;
	}

	@FindBy(css = "div#headerlogo a img")
	private WebElement imgPostLoginServiceBanner;
	@FindBy(css = "#new-request > div[class]:not([style*='display: none']) p")
	private WebElement img_SubTitle;
	@FindBy(xpath = "//div[@class='full_width_input_sec'][not(contains(@style, 'none'))]//label[text()='When are you moving out?']/following-sibling::div/img")
	private WebElement ico_MovingOutDP;
	@FindBy(css = ".rc-anchor-normal-footer")
	private WebElement imgCaptchaV3;
	@FindBy(css = "iframe[title =' reCAPTCHA']")
	private WebElement iFrmCapthaV3;
	@FindBy(css = ".ui-menu-item-wrapper")
	private WebElement dd_StateIntellisenseOptions;
	
	@FindBy(css = "#ddl_Reason.select_effect")
	private WebElement dd_ServiceReason;

	public boolean isServiceReasonVisible() {
		log.info("Checking the visibility of dd_ServiceReason on the page.");
		log.info("dd_ServiceReason visibility status {}: " + isElementVisible(dd_ServiceReason));
		return isElementVisible(dd_ServiceReason);
	}

	public String getFirstOptionServiceReason() {
		log.info("Checking the visibility of dd_ServiceReason on the page.");
		log.info("dd_ServiceReason visibility status {}: " + isElementVisible(dd_ServiceReason));
		return getSelectedValueInDropBox(dd_ServiceReason);
	}

	@FindBy(css = "#ddl_Reason")
	private WebElement lstBoxReason;

	@FindBy(css = ".lefticon")
	private WebElement child_img_LeftIcon;

	public WebElement objectChildimgLeftIcon() {
		return child_img_LeftIcon;
	}

	@FindBy(css = ".rightcontentservice h2")
	private WebElement child_lbl_TopicName;
	
	public WebElement getChildLblTopicLabel() {
		return child_lbl_TopicName;
	}
	
	@FindBy(css = "p")
	private WebElement child_lbl_SubTitle;
	
	public WebElement getchildLblSubTitle() {
		return child_lbl_SubTitle;
	}
	
	@FindBy(css = "#cmTopic")
	private WebElement parent_SidebarRequestId;
	@FindBy(css = "#cmAccountNumber")
	private WebElement parent_SidebarRequestType;
	@FindBy(css = ".emailDetails_inner > span:nth-child(3)")
	private WebElement parent_SidebarSubject;
	@FindBy(css = "#cmSubject")
	private WebElement parent_SidebarRequestRaisedOn;
	@FindBy(css = ".emailDetails_inner > span:nth-child(5)")
	private WebElement parent_SidebarFrom;
	@FindBy(css = ".emailDetails_inner > span:nth-child(6)")
	private WebElement parent_SidebarDetails;
	@FindBy(css = "b")
	private WebElement child_SidebarLabel;
	@FindBy(css = "i")
	private WebElement child_SidebarValue;

	@FindBy(css = "#tblSavedForms .dataTables_empty")
	private WebElement td_NoMatchingResultFound;
	@FindBy(css = "#tblSubmittedForms tr > td:nth-child(1) a")
	private WebElement td_RequestId;
	@FindBy(css = "#tblSavedForms tbody tr td:nth-child(1)")
	private WebElement tdSaveformRequestId;

	@FindBy(xpath = "//input[@aria-label = 'Pets'][@type='checkbox']")
	private WebElement chkboxPets;
	@FindBy(xpath = "//input[@aria-label = 'Locked Gates'][@type='checkbox']")
	private WebElement cheboxLockedGate;
	
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
		
		@FindBy(css = "input#BtnCancelOtp")
	    private WebElement btnCancelTrackRequestOTP;
	    
	    public boolean isbtnCancelTrackRequestOTPVisible() {
	        log.info("btn Cancel Track Request OTP is visible on the Contact Us page.");
	        return isElementVisible(btnCancelTrackRequestOTP);
	    }
	    
	    public void clickbtnCancelTrackRequestOTP() {
	    	click(btnCancelTrackRequestOTP);
	    	log.info("Cancel Track Request OTP clicked");
	    }
	    
	    @FindBy(css = "#BtnSubmitOtp")
	    private WebElement btnSubmitTrackRequestOTP;
	    
	    public boolean isbtnSubmitTrackRequestOTPVisible() {
	        log.info("btn Submit Track Request OTP is visible on the Contact Us page.");
	        return isElementVisible(btnSubmitTrackRequestOTP);
	    }
	    
	    public void clickbtnSubmitTrackRequestOTP() {
	    	click(btnSubmitTrackRequestOTP);
	    	log.info("Submit Track Request OTP clicked");
	    }
	    
	    @FindBy(css = "#Tracklink")
	    private WebElement lnkTracklink;
	    
	    public void clickTrackLink() {
	        click(lnkTracklink);
	        log.info("Track link Clicked.");
	    }
	    
	    public boolean isTrackLinkVisible() {
	    	log.info("Track Link visibility Status :" + lnkTracklink.isDisplayed());
	    	return lnkTracklink.isDisplayed();
	    }
	    
	    @FindBy(css = ".contn_wrpper.thirdstep h2")
	    private WebElement lblAuthHeaderMsg;
	    
	    public String getlblAuthHeaderMsg() {
	        log.info("Fetching lbl Auth Header Msg");
	        String label = getText(lblAuthHeaderMsg);
	        log.info("lbl Auth Header Msg is {}: " + label);
	        return label;
	    }
	    
	    public boolean islblAuthHeaderMsgVisible() {
	    	log.info("lbl Auth Header Msg Status :" + lblAuthHeaderMsg.isDisplayed());
	    	return lblAuthHeaderMsg.isDisplayed();
	    }
	    
	    @FindBy(css = ".contn_wrpper.thirdstep p")
	    private WebElement lblAuthmsg;
	    
	    public String getlblAuthmsg() {
	        log.info("Fetching lbl Auth msg");
	        String label = getText(lblAuthmsg);
	        log.info("lbl Auth msg is {}: " + label);
	        return label;
	    }
	    
	    public boolean islblAuthmsgVisible() {
	    	log.info("lbl Auth msg Status :" + lblAuthmsg.isDisplayed());
	    	return lblAuthmsg.isDisplayed();
	    }
	    
	    @FindBy(css = "strong#time")
		private WebElement txtOtpTimer;
	 
	 public String getTxtOtpTimer() {
	        log.info("Fetching the label OTP Timer");
	        String label = getText(txtOtpTimer);
	        log.info("label OTP Timer: " + label);
	        return label;
	    }
	 
	 @FindBy(css = ".hdngsidepopup")
	    private WebElement lblTrackReqSidebarHeader;
	    
	    public String getTrackReqSidebarHeaderLbl() {
	        log.info("Fetching the Track Request Sidebar Header.");
	        String label = getText(lblTrackReqSidebarHeader);
	        log.info("Track Request Sidebar Header is {}: " + label);
	        return label;
	    }
	    
	    public boolean isTrackReqSidebarHeaderLblVisible() {
	    	log.info("Track Request Sidebar Header visibility Status :" + lblTrackReqSidebarHeader.isDisplayed());
	    	return lblTrackReqSidebarHeader.isDisplayed();
	    }
	    
	    public void addAttachmentToChooseFile(String value) {
	        sendKeysWithoutCheckingVisibility(btnChooseFile, value);
	        log.info("Choose File Button added successfully with File Value " + value);
	        ExtentLogger.logInfo("Choose File Button added successfully with File Value " + value);
	    }
}
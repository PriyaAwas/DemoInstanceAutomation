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

public class PostLogStartServicePage extends HomePage {
	private static final Logger log = LogManager.getLogger(PostLogStartServicePage.class);

	public PostLogStartServicePage(WebDriver driver) {
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
	
	public void clickServicesHeader() {
		log.info("Clicking the link Services");
		click(bl_ServicesHeader);
		log.info("post Log link Services clicked successfully.");
	}
	
	@FindBy(css = "(//a[@globalize='ML_Msg_ServiceTurnOnOff'])[2]")
	private WebElement linkServices;

	public void clickLinkServices() {
		log.info("Clicking the link Services");
		click(linkServices);
		log.info("post Log link Services clicked successfully.");
	}

	@FindBy(css = " .headEfficiency span")
	private WebElement lbl_postLoginServicesHeader;

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

	@FindBy(xpath = " //div[@class='full_width_input_sec'][not(contains(@style, 'none'))]//label[text()='postvious Meter read:']")
	private WebElement lbl_postviousMeterReadPV;

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
	
	@FindBys(@FindBy(css = "#new-request > div[class]:not([style*='display: none'])"))
	private List<WebElement> lblGridHeader;

	public List<WebElement> getGridHeader() {
		return lnk_ServicesTiles;
	}

	
	@FindBy(css = "#sidebar_wrapper_box h2")
	private WebElement lbl_SidebarTrackRequestHeader;
	@FindBy(css = " .wickedpicker__title")
	private WebElement lblCalendarScheduleTime;
	@FindBy(css = " #tblSavedForms tbody tr:nth-child(1) td:nth-child(1)")
	private WebElement lblSaveFormsServiceRequest;
	@FindBy(css = " #caseNumber")
	private WebElement lblCaseNumber;
	@FindBy(css = " div.toast-message")
	private WebElement lblToastMessage;
	@FindBy(css = " .heading3_formpostview")
	private WebElement lblformpostview;

	public void populateFormpostview(String formview) {
		log.info("Populating Formpostview {} :" + formview);
		sendKeys(lblformpostview, formview);
		log.info("Formpostview populated successfully.");
	}

	public String getFormpostview() {
		log.info("Checking theFormpostview  on the page.");
		String option = getText(lblformpostview);
		log.info("Formpostview value {}: " + option);
		return option;
	}

	@FindBys(@FindBy(css = " #tblData .value"))
	private List<WebElement> lblformpostviewValue;

	public List<WebElement> getObjectsFormpostviewValue() {
		return lblformpostviewValue;
	}

	@FindBy(css = " .secServiceTitle.without_separator div")
	private WebElement lblpostLoginSerMoveInmsg;
	@FindBy(css = " .secServiceTitle.separator div")
	private WebElement lblpostLoginSerMoveInCurrentAdd;
	@FindBy(xpath = " (//div[@class ='secServiceTitle separator'])[2]/div")
	private WebElement lblpostLoginSerMoveInStartSer;
	@FindBy(xpath = " (//div[@class ='secServiceTitle separator'])[3]/div")
	private WebElement lblpostLoginSerMoveInBilling;
	@FindBy(xpath = " (//div[@class ='secServiceTitle separator'])[4]/div")
	private WebElement lblpostLoginSerMoveInBillingAddress;
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

	public String getAtrributeNewRequestTab() {
	        String clas = getAttribute(lnk_NewRequestTab, "class");
	        log.info("Max length of Customer Name field is {} " + clas);
	        return clas;
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
	
	public void clickTrackRequestTab() {
		log.info("Clicking the link Services");
		click(lnk_TrackRequestTab);
		log.info("post Log link Services clicked successfully.");
	}
	
	 public String getTrackRequestTabAttribute() {
	        String clas = getAttribute(lnk_TrackRequestTab, "class");
	        log.info("Max length of Customer Name field is {} " + clas);
	        return clas;
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
	private WebElement lnk_postviousPagination;
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
	private WebElement txt_postLogTrackReqSearch;

	public void clickpostLogTrackReqSearchBtn() {
		log.info("Clicking the sign in button.");
		click(txt_postLogTrackReqSearch);
		log.info("post Log TrackRequest clicked successfully.");
	}

	public void populateTrackReqSearch(String trackRequest) {
		log.info("Populating Trackrequest {} :" + trackRequest);
		sendKeys(txt_postLogTrackReqSearch, trackRequest);
		log.info("Username populated successfully.");
	}

	@FindBy(css = "[class='form-control input-sm']")
	private WebElement txtpostLoginSavedFormTrackReqSearch;

	public void populatepostLoginSavedFormTrackReqSearch(String trackRequest) {
		log.info("Populating postLoginSavedFormTrackReqSearch {} :" + trackRequest);
		sendKeys(txtpostLoginSavedFormTrackReqSearch, trackRequest);
		log.info("Username populated successfully.");
	}

	@FindBy(xpath = "(//input[@class='form-control input-sm'])[2]")
	private WebElement txtTrackReqSearch;

	public void populatetxtTrackReqSearchSearch(String trackRequest) {
		log.info("Populating postLoginSavedFormTrackReqSearch {} :" + trackRequest);
		sendKeys(txtTrackReqSearch, trackRequest);
		log.info("Username populated successfully.");
	}
	
	@FindBy(css = ".info_connect_txt")
	private WebElement txtpostLoginSavedFormPopup;

	public String getpostLoginSavedFormPopup() {
		log.info("Checking postLoginSavedFormPopup on the page.");
		String option = getText(txtpostLoginSavedFormPopup);
		log.info("postLoginSavedFormPopup value {}: " + option);
		return option;
	}

	@FindBy(css = "[globalize='ML_OTP_txt_AcctNo']")
	private WebElement txtpostLoginMoveInAccountNumber;

	public Boolean ispostLoginMoveInAccountNumberVisible() {
		log.info("Checking the visibility of txtpostLoginMoveInAccountNumber on the page.");
		log.info("txtpostLoginMoveInAccountNumber visibility status {}: "
				+ isElementVisible(txtpostLoginMoveInAccountNumber));
		return isElementVisible(txtpostLoginMoveInAccountNumber);
	}

	@FindBy(css = "[globalize= 'ML_SrvcRqust_Date']")
	private WebElement txtpostLoginMoveInDate;

	public Boolean ispostLoginMoveInDateVisible() {
		log.info("Checking the visibility of txtpostLoginMoveInDate on the page.");
		log.info("txtpostLoginMoveInDate visibility status {}: " + isElementVisible(txtpostLoginMoveInDate));
		return isElementVisible(txtpostLoginMoveInAccountNumber);
	}

	@FindBy(css = "[globalize='ML_SrvcRqust_txtbx_FirstName']")
	private WebElement txtpostLoginSerMoveInFirstName;

	public Boolean ispostLoginSerMoveInFirstNameVisible() {
		log.info("Checking the visibility of txtpostLoginSerMoveInFirstName on the page.");
		log.info("txtpostLoginSerMoveInFirstName visibility status {}: "
				+ isElementVisible(txtpostLoginSerMoveInFirstName));
		return isElementVisible(txtpostLoginSerMoveInFirstName);
	}

	@FindBy(css = "[globalize='ML_SrvcRqust_txtbx_LastName']")
	private WebElement txtpostLoginSerMoveInLastName;

	public Boolean istxtpostLoginSerMoveInLastNameVisible() {
		log.info("Checking the visibility of txtpostLoginSerMoveInLastName on the page.");
		log.info(
				"txtpostLoginSerMoveInLastName visibility status {}: " + isElementVisible(txtpostLoginSerMoveInLastName));
		return isElementVisible(txtpostLoginSerMoveInLastName);
	}

	@FindBy(css = "[globalize='ML_CONNECTME_Lbl_ReporterEmail']")
	private WebElement txtpostLoginSerMoveInEmail;

	public Boolean ispostLoginSerMoveInEmailVisible() {
		log.info("Checking the visibility of txtpostLoginSerMoveInEmail on the page.");
		log.info("txtpostLoginSerMoveInLastName visibility status {}: " + isElementVisible(txtpostLoginSerMoveInEmail));
		return isElementVisible(txtpostLoginSerMoveInEmail);
	}

	@FindBy(css = "[globalize='ML_SrvcRqust_lbl_SSN.input_effect']")
	private WebElement txtpostLoginSerMoveInSSL;

	public Boolean istxtpostLoginSerMoveInSSLVisible() {
		log.info("Checking the visibility of txtpostLoginSerMoveInSSL on the page.");
		log.info("txtpostLoginSerMoveInSSL visibility status {}: " + isElementVisible(txtpostLoginSerMoveInSSL));
		return isElementVisible(txtpostLoginSerMoveInSSL);
	}

	@FindBy(css = "[globalize='ML_MYACCOUNT_ContactNum_ContactType']")
	private WebElement txtpostLoginSerMoveInContactType;

	public Boolean ispostLoginSerMoveInContactTypeVisibleVisible() {
		log.info("Checking the visibility of txtpostLoginSerMoveInSSL on the page.");
		log.info("txtpostLoginSerMoveInContactType visibility status {}: "
				+ isElementVisible(txtpostLoginSerMoveInContactType));
		return isElementVisible(txtpostLoginSerMoveInContactType);
	}

	@FindBy(css = "[globalize='ML_lowincome_Date_of_Birth']")
	private WebElement txtpostLoginSerMoveInDOB;

	public Boolean ispostLoginSerMoveInContactTypeVisible() {
		log.info("Checking the visibility of txtpostLoginSerMoveInDOB on the page.");
		log.info("txtpostLoginSerMoveInDOB visibility status {}: " + isElementVisible(txtpostLoginSerMoveInDOB));
		return isElementVisible(txtpostLoginSerMoveInDOB);
	}

	@FindBy(xpath = "(//input[@globalize='ML_MYACCOUNT_ContactNum_Primary'])[1]")
	//@FindBy(css = "[id='e76f462e-4a65-4e58-89e4-0ffeec096949']")
	private WebElement txtpostLoginSerMoveInPrimaryContactNo;

	public Boolean ispostLoginSerMoveInPrimaryContactNoVisible() {
		log.info("Checking the visibility of txtpostLoginSerMoveInPrimaryContactNo on the page.");
		log.info("txtpostLoginSerMoveInPrimaryContactNo visibility status {}: "
				+ isElementVisible(txtpostLoginSerMoveInPrimaryContactNo));
		return isElementVisible(txtpostLoginSerMoveInPrimaryContactNo);
	}

	@FindBy(css = "[globalize='ML_SrvcRqust_p_StreetNo']")
	private WebElement txtpostLoginSerMoveInCurrentAddStreetNum;

	public Boolean ispostLoginSerMoveInCurrentAddStreetNumVisible() {
		log.info("Checking the visibility of txtpostLoginSerMoveInPrimaryContactNo on the page.");
		log.info("txtpostLoginSerMoveInCurrentAddStreetNum visibility status {}: "
				+ isElementVisible(txtpostLoginSerMoveInCurrentAddStreetNum));
		return isElementVisible(txtpostLoginSerMoveInCurrentAddStreetNum);
	}

	@FindBy(xpath = "(//input[@globalize ='ML_SrvcRqust_p_StrretName'])[1]")
	private WebElement txtpostLoginSerMoveInCurrentAddStreetName;

	public Boolean ispostLoginSerMoveInCurrentAddStreetNameVisible() {
		log.info("Checking the visibility of SerMoveInCurrentAddStreetName on the page.");
		log.info("SerMoveInCurrentAddStreetName visibility status {}: "
				+ isElementVisible(txtpostLoginSerMoveInCurrentAddStreetName));
		return isElementVisible(txtpostLoginSerMoveInCurrentAddStreetName);
	}

	@FindBy(xpath = "(//select[@globalize='ML_Service_li_Type'])[1]")
	private WebElement txtpostLoginSerMoveInCurrentAddApartment;

	@FindBy(css = "[globalize='ML_CONNECTME_Lbl_Apt']")
	private WebElement txtpostLoginSerMoveInCurrentAddUnitNo;

	public Boolean ispostLoginSerMoveInCurrentAddUnitNoVisible() {
		log.info("Checking the visibility of txtpostLoginSerMoveInCurrentAddUnitNo on the page.");
		log.info("txtpostLoginSerMoveInCurrentAddUnitNo visibility status {}: "
				+ isElementVisible(txtpostLoginSerMoveInCurrentAddUnitNo));
		return isElementVisible(txtpostLoginSerMoveInCurrentAddUnitNo);
	}

	@FindBy(css = "[globalize='ML_SrvcRqust_txtbx_City']")
	private WebElement txtpostLoginSerMoveInCurrentAddCity;

	public Boolean ispostLoginSerMoveInCurrentAddCityVisible() {
		log.info("Checking the visibility of txtpostLoginSerMoveInCurrentAddUnitNo on the page.");
		log.info("postLoginSerMoveInCurrentAddCity visibility status {}: "
				+ isElementVisible(txtpostLoginSerMoveInCurrentAddCity));
		return isElementVisible(txtpostLoginSerMoveInCurrentAddCity);
	}

	@FindBy(css = "[globalize='ML_SrvcRqust_P_ZipCode']")
	private WebElement txtpostLoginSerMoveInCurrentAddZipCode;

	public Boolean ispostLoginSerMoveInCurrentAddZipCodeVisible() {
		log.info("Checking the visibility of txtpostLoginSerMoveInCurrentAddZipCode on the page.");
		log.info("txtpostLoginSerMoveInCurrentAddZipCode visibility status {}: "
				+ isElementVisible(txtpostLoginSerMoveInCurrentAddZipCode));
		return isElementVisible(txtpostLoginSerMoveInCurrentAddZipCode);
	}

	@FindBy(css = "[globalize='ML_SrvcRqust_lbl_StrtDate']")
	private WebElement txtpostLoginSerMoveInRequestStartDate;

	@FindBy(xpath = " (//input[@globalize='ML_SrvcRqust_p_StreetNo'])[2]")
	private WebElement txtpostLoginSerMoveInRequestStartStreetNo;

	@FindBy(xpath = " (//input[@globalize='ML_SrvcRqust_p_StrretName'])[2]")
	private WebElement txtpostLoginSerMoveInRequestStartStreetName;

	@FindBy(xpath = " (//select[@globalize='ML_Service_li_Type'])[2]")
	private WebElement txtpostLoginSerMoveInRequestStartApartment;

	@FindBy(xpath = "(//input[@globalize='ML_CONNECTME_Lbl_Apt'])[2]")
	private WebElement txtpostLoginSerMoveInRequestStartUnit;

	@FindBy(xpath = " (//input[@globalize='ML_SrvcRqust_txtbx_City'])[2]")
	private WebElement txtpostLoginSerMoveInRequestStartCity;

	@FindBy(xpath = " (//input[@globalize='ML_SrvcRqust_txtbx_MState'])[2]")
	private WebElement txtpostLoginSerMoveInRequestStartState;

	@FindBy(xpath = " (//input[@globalize='ML_SrvcRqust_P_ZipCode'])[2]")
	private WebElement txtpostLoginSerMoveInRequestStartZipCode;

	@FindBy(css = " [globalize ='ML_SrvcRqust_lbl_SameNewAddr']")
	private WebElement txtpostLoginSerMoveInBillingAdd;
	@FindBy(css = " [globalize*='ML_DynamicForm_DynamicKey']")
	private WebElement txtpostLoginSerMoveInBillingAddStreetNo;
	@FindBy(css = " [globalize='ML_SrvcRqust_p_StrretName'].IsLocation")
	private WebElement txtpostLoginSerMoveInBillingAddStreetName;

	@FindBy(xpath = "  (//select[@globalize='ML_Service_li_Type'])[3]")
	private WebElement txtpostLoginSerMoveInBillingAddApartment;

	@FindBy(xpath = " (//input[@globalize='ML_CONNECTME_Lbl_Apt'])[3]")
	private WebElement txtpostLoginSerMoveInBillingAddUnitNo;

	@FindBy(xpath = "(//input[@globalize='ML_SrvcRqust_txtbx_City'])[3]")
	private WebElement txtpostLoginSerMoveInBillingAddCity;

	@FindBy(xpath = " (//input[@globalize='ML_SrvcRqust_txtbx_MState'])[3]")
	private WebElement txtpostLoginSerMoveInBillingAddState;

	@FindBy(xpath = " (//input[@globalize='ML_SrvcRqust_P_ZipCode'])[3]")
	private WebElement txtpostLoginSerMoveInBillingAddZipCode;

	@FindBy(css = " [globalize='ML_SrvcRqust_txtbx_Comment']")
	private WebElement txtpostLoginSerMoveInComment;

	@FindBy(css = " [globalize='ML_SERVICE_Lbl_PersonAvailable']")
	private WebElement txtpostLoginPersonalInfo;

	@FindBy(xpath = " //li//div[text()='CALIFORNIA']")
	private WebElement txtpostLoginState;
	@FindBy(xpath = " //li//div[text()='Irvine(CA)']")
	private WebElement txtpostLoginCity;
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

	@FindBy(css = ".tracking_area #btnSaveSubmit")
	private WebElement btnpostLoginSaveFormsSubmit;

	public void clickpostLoginSaveFormsSubmitBtn() {
		log.info("Clicking the postLoginSaveFormsSubmit button.");
		click(btnpostLoginSaveFormsSubmit);
		log.info("postLoginSaveFormsSubmit button clicked successfully.");
	}

	@FindBy(css = "#btnNext.submit-button")
	private WebElement btnpostLoginSaveFormsNext;

	public void clickpostLoginSaveFormsNextBtn() {
		log.info("Clicking the btnpostLoginSaveFormsNext button.");
		click(btnpostLoginSaveFormsNext);
		log.info("btnpostLoginSaveFormsNext button clicked successfully.");
	}
	
	@FindBy(css = "[id='btnSubmitChanges']")
	private WebElement btnSaveFormsNext;

	public void clickSaveFormsSubmitBtn() {
		log.info("Clicking the btnpostLoginSaveFormsNext button.");
		click(btnSaveFormsNext);
		log.info("btnpostLoginSaveFormsNext button clicked successfully.");
	}

	@FindBy(css = " #btnSubmitChanges.submit-button")
	private WebElement btnpostLoginSaveFormSubmit;

	public void clickpostLoginSaveFormSubmit() {
		click(btnpostLoginSaveFormSubmit);
		log.info("postLoginSaveFormSubmit clicked successfully.");
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
	
	public void clickBtnMorevertnavbar() {
		log.info("Clicking the sign in button.");
		click(btnMorevertnavbar);
		log.info("btnMoveInNext button clicked successfully.");
	}
	
	@FindBy(css = "[name='FileUpload1']")
	private WebElement btnChooseFile1;
	
	 public void addAttachmentToChooseFile(String value) {
	        sendKeysWithoutCheckingVisibility(btnChooseFile1, value);
	        log.info("Choose File Button added successfully with File Value " + value);
	        ExtentLogger.logInfo("Choose File Button added successfully with File Value " + value);
	    }
	
	@FindBy(css = ".editConnect")
	private WebElement btnEditConnect;
	
	public void clickbtnEditConnect() {
		log.info("Clicking the sign in button.");
		click(btnEditConnect);
		log.info("btnMoveInNext button clicked successfully.");
	}
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
	
	@FindBys(@FindBy(css = ".lefticon"))
	private List<WebElement> serviceRequestIcon;

	public List<WebElement> getserviceRequestIconTiles() {
		return lnk_ServicesTiles;
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
	
	public String gettdSaveformRequestId() {
        log.info("Fetching lbl Auth Header Msg");
        String label = getText(tdSaveformRequestId);
        log.info("lbl Auth Header Msg is {}: " + label);
        return label;
    }

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
	       
	    
	    @FindBy(css = "[class='service']")
	    private WebElement lnk_service_header_menu;

	    public void clickServiceHeaderLink() {
	        click(lnk_service_header_menu);
	        log.info("Services header link clicked successfully {}.");
	    }

	    @FindBy(xpath = "(//a[@globalize = 'ML_Service_Overview'])[1]")
	    private WebElement lnk_service_overview;

	    public void clickServiceOverviewLink() {
	        click(lnk_service_overview);
	        log.info("Services overview link clicked successfully {}.");
	    }
	    
	    @FindBy(css = "(#tblSubmittedForms_filter input[type='search']")
	    private WebElement txtSearchRequest;

	    public void populatetxtSearchRequest(String trackRequest) {
			log.info("Populating txt Search Request {} :" + trackRequest);
			sendKeys(txtSearchRequest, trackRequest);
			log.info("txt Search Request populated successfully.");
		}
	    
	    public boolean istxtSearchRequestVisible() {
	    	log.info("Search Request Visible :" + txtSearchRequest.isDisplayed());
	    	return txtSearchRequest.isDisplayed();
	    }


}

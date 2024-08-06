package demo.pageobjects;

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
import sew.ai.pageObjects.scp.HomePage;




public class PostLogServicespage extends HomePage {


	private static final Logger log = LogManager.getLogger(PostLogServicespage.class);

	public PostLogServicespage(WebDriver driver) {
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

@FindBy(css = "#postloginServiceModule")
private WebElement linkServices;

public void clickLinkServices() {
	log.info("Clicking the link Services");
	click(linkServices);
	log.info("post Log link Services clicked successfully.");
}
@FindBy(css = "#module7 > a")
private WebElement Servicesmdl;

public void clickServices() {
	log.info("Clicking the link Services");
	click(Servicesmdl);
	log.info("post Log link Services clicked successfully.");
}
@FindBy(css = "#liService_Overview > a")
private WebElement Serviceoversmdl;

public void clickServicesoverview() {
	log.info("Clicking the link Services");
	click(Serviceoversmdl);
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

public void populateFormpostview(String formview) {
	log.info("Populating Formpostview {} :" + formview);
	sendKeys(lblformpreview, formview);
	log.info("Formpostview populated successfully.");
}

public String getFormpostview() {
	log.info("Checking theFormpostview  on the page.");
	String option = getText(lblformpreview);
	log.info("Formpostview value {}: " + option);
	return option;
}

@FindBys(@FindBy(css = ".formpreview_connectme .value"))
private List<WebElement> lblformpostviewValue;

public List<WebElement> getObjectsFormpostviewValue() {
	log.info("ii value {}: " + lblformpostviewValue);
	return lblformpostviewValue;

}

@FindBys(@FindBy(css = "myprofile-card mdl-card mdl-shadow--2dp"))
private List<WebElement> myProfileCards;

public List<WebElement> getMyProfileList() {
	log.info("List for the my ProfileCards value {}: " + myProfileCards);
	return myProfileCards;

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

public String getAtrributeNewRequestTab(String value) {
	String newRequesttapvalue = getAttribute(lnk_NewRequestTab, value);
	log.info("New Request Tap is {} ", value);
	return newRequesttapvalue;
}

public String getAtrributeNewRequestTab() {
	String clas = getAttribute(lnk_NewRequestTab, "class");
	log.info("Max length of Customer Name field is {} " + clas);
	return clas;
}
@FindBy(xpath = "//*[@id=\"tblSavedForms\"]/tbody")
private WebElement SavedFormsinfo;

public Boolean isSavedFormsinforVisible() {
	log.info("Checking the visibility of company logo on the page.");
	log.info("Company logo visibility status {}: " + isElementVisible(SavedFormsinfo));
	return isElementVisible(SavedFormsinfo);
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
@FindBy(css = "#tblSubmittedForms_filter > label > input")
private WebElement searchfield;

public void clicSearchBtn() {
	log.info("Clicking the sign in button.");
	click(searchfield);
}

public void populateTrackReqSearch(String trackRequest) {
	log.info("Populating Trackrequest {} :" + trackRequest);
	sendKeys(searchfield, trackRequest);
	log.info("Username populated successfully.");
}

@FindBy(css = "#tblSavedForms_filter > label > input")
private WebElement txtpostLoginSavedFormTrackReqSearch;

public void clickSavedformsBtn() {
	log.info("Clicking the sign in button.");
	click(txtpostLoginSavedFormTrackReqSearch);
}

public void populatepostLoginSavedFormTrackReqSearch(String trackRequest) {
	log.info("Populating postLoginSavedFormTrackReqSearch {} :" + trackRequest);
	sendKeys(txtpostLoginSavedFormTrackReqSearch, trackRequest);
	log.info("Username populated successfully.");
}
public void clearpostLoginSavedFormTrackReqSearch() {
	clear(txtpostLoginSavedFormTrackReqSearch);
}
public void clearstreet() {
	clear(txtpostLoginSerMoveInRequestStartStreetName);
}

@FindBy(css = " .info_connect_txt")
private WebElement txtpostLoginSavedFormPopup;

public String getpostLoginSavedFormPopup() {
	log.info("Checking postLoginSavedFormPopup on the page.");
	String option = getText(txtpostLoginSavedFormPopup);
	log.info("postLoginSavedFormPopup value {}: " + option);
	return option;
}

@FindBy(css = "[globalize='ML_OTP_txt_AcctNo']")
private WebElement txtpostLoginMoveInAccountNumber;

public void clickAccfield() {
	log.info("Clicking the sign in button.");
	click(txtpostLoginMoveInAccountNumber);
}


public Boolean ispostLoginMoveInAccountNumberVisible() {
	log.info("Checking the visibility of txtpostLoginMoveInAccountNumber on the page.");
	log.info("txtpostLoginMoveInAccountNumber visibility status {}: "
			+ isElementVisible(txtpostLoginMoveInAccountNumber));
	return isElementVisible(txtpostLoginMoveInAccountNumber);
}

@FindBy(css = "[globalize='ML_SrvcRqust_p_WenOut']")
private WebElement txtpostLoginMoveOutDate;

public Boolean ispostLoginMoveOutDateVisible() {
	log.info("Checking the visibility of txtpostLoginMoveOutDate on the page.");
	log.info("txtpostLoginMoveOutDate visibility status {}: " + isElementVisible(txtpostLoginMoveOutDate));
	return isElementVisible(txtpostLoginMoveOutDate);
}

@FindBy(css = "[globalize='	ML_SERVICE_Lbl_ScheduleDate']")
private WebElement txtpostLoginOtherSedDate;

public Boolean ispostLoginOtherScheduleDateVisible() {
	log.info("Checking the visibility of txtpostLoginOtherSedDate on the page.");
	log.info("txtpostLoginMoveOutDate visibility status {}: " + isElementVisible(txtpostLoginOtherSedDate));
	return isElementVisible(txtpostLoginOtherSedDate);
}

@FindBy(css = "[globalize=\"ML_OTP_txt_AcctNo\"]")
private WebElement txtpostLoginSTDate;

public Boolean ispostLoginSTDateVisible() {
	log.info("Checking the visibility of txtpostLoginSTDate on the page.");
	log.info("txtpostLoginSTDate visibility status {}: " + isElementVisible(txtpostLoginSTDate));
	return isElementVisible(txtpostLoginSTDate);
}

public void selectMoveOutDate() {
	click(txtpostLoginMoveOutDate);
	log.info("txtpostLoginMoveOutDate populated successfully.");

}
@FindBy(css = "[globalize='ML_SERVICE_Lbl_ScheduleDate']")
private WebElement txtotherSTDate;

public void selectDates() {
	click(txtotherSTDate);
	log.info("txtpostLoginMoveOutDate populated successfully.");

}
@FindBy(xpath = "//*[@id=\"toast-container\"]/div/div")
private WebElement errormsg;

public String getToastMessage() {
	// waitForPageLoader();
	pause(500);
	String toast_msg = getTextWithoutCheckingVisibility(errormsg);
	log.info("Label on toast message {}: " + toast_msg);
	clickElementUsingJsExecutor(errormsg);
	return toast_msg;
}

public void populateAccountNum(String accNo) {
	log.info("Populating EmailAddress {} :" + accNo);
	sendKeys(txtpostLoginMoveInAccountNumber, accNo);
	log.info("EmailAddress populated successfully.");
}

@FindBy(css = "[globalize= 'ML_SrvcRqust_Date']")
private WebElement txtpostLoginMoveInDate;

public Boolean ispostLoginMoveInDateVisible() {
	log.info("Checking the visibility of txtpostLoginMoveInDate on the page.");
	log.info("txtpostLoginMoveInDate visibility status {}: " + isElementVisible(txtpostLoginMoveInDate));
	return isElementVisible(txtpostLoginMoveInDate);
}

@FindBy(css = "[globalize='ML_SrvcRqust_txtbx_FirstName']")
private WebElement txtpostLoginSerMoveInFirstName;

public Boolean ispostLoginSerMoveInFirstNameVisible() {
	log.info("Checking the visibility of txtpostLoginSerMoveInFirstName on the page.");
	log.info("txtpostLoginSerMoveInFirstName visibility status {}: "
			+ isElementVisible(txtpostLoginSerMoveInFirstName));
	return isElementVisible(txtpostLoginSerMoveInFirstName);
}

public void populateFname(String name) {
	log.info("Populating name {} :" + name);
	sendKeys(txtpostLoginSerMoveInFirstName, name);
	log.info("name populated successfully.");
}

public WebElement elementFirstName() {
	waitForElementToBeVisible(txtpostLoginSerMoveInFirstName);
	log.info("Visiblity status of element Email Add:" + txtpostLoginSerMoveInFirstName.isDisplayed());
	return txtpostLoginSerMoveInFirstName;
}

public WebElement elementLastName() {
	waitForElementToBeVisible(txtpostLoginSerMoveInLastName);
	log.info("Visiblity status of element Email Add:" + txtpostLoginSerMoveInLastName.isDisplayed());
	return txtpostLoginSerMoveInLastName;
}

@FindBy(css = "[globalize='ML_SrvcRqust_txtbx_LastName']")
private WebElement txtpostLoginSerMoveInLastName;

public Boolean istxtpostLoginSerMoveInLastNameVisible() {
	log.info("Checking the visibility of txtpostLoginSerMoveInLastName on the page.");
	log.info(
			"txtpostLoginSerMoveInLastName visibility status {}: " + isElementVisible(txtpostLoginSerMoveInLastName));
	return isElementVisible(txtpostLoginSerMoveInLastName);
}

public void populateLname(String lastname) {
	log.info("Populating name {} :" + lastname);
	sendKeys(txtpostLoginSerMoveInLastName, lastname);
	log.info("lastname populated successfully.");
}

@FindBy(css = "[globalize='ML_CONNECTME_Lbl_ReporterEmail']")
private WebElement txtpostLoginSerMoveInEmail;

public Boolean ispostLoginSerMoveInEmailVisible() {
	log.info("Checking the visibility of txtpostLoginSerMoveInEmail on the page.");
	log.info("txtpostLoginSerMoveInLastName visibility status {}: " + isElementVisible(txtpostLoginSerMoveInEmail));
	return isElementVisible(txtpostLoginSerMoveInEmail);
}

@FindBy(css = "[globalize='ML_SrvcRqust_lbl_SSN']")
private WebElement txtpostLoginSerMoveInSSL;

public Boolean istxtpostLoginSerMoveInSSLVisible() {
	log.info("Checking the visibility of txtpostLoginSerMoveInSSL on the page.");
	log.info("txtpostLoginSerMoveInSSL visibility status {}: " + isElementVisible(txtpostLoginSerMoveInSSL));
	return isElementVisible(txtpostLoginSerMoveInSSL);
}

public void populateSSN(String ssn) {
	log.info("Populating ssn {} :" + ssn);
	sendKeys(txtpostLoginSerMoveInSSL, ssn);
	log.info("ssn populated successfully.");
}


public boolean istxt1234Visible (String option) {
	return isElementVisible(txtpostLoginSerMoveInSSL);	

}
public boolean istxtStreetNameVisible (String option) {
	return isElementVisible(txtpostLoginSerMoveInCurrentAddStreetName);	

}
public boolean istxtcityVisible (String option) {
	return isElementVisible(txtpostLoginSerMoveInCurrentAddCity);	

}
public boolean istxtStatesVisible (String option) {
	return isElementVisible(txtpostLoginSerMoveInRequestStartState);	

}
public boolean istxtZipcodeVisible (String option) {
	return isElementVisible(txtpostLoginSerMoveInRequestStartZipCode);	

}


@FindBys(@FindBy(css = ".ui-menu-item-wrapper"))
public List<WebElement> cities;

public void populatecity(String city) {
	log.info("Populating city {} :" + city);
	sendKeys(txtpostLoginSerMoveInCurrentAddCity, city);
	pause(5000);
	int count = autoSuggestion.size();
	autoSuggestion.get(count - 1).click();
	log.info("state selected");
}
public String getcity(String sname) {
	String Sname = getText(txtpostLoginSerMoveInCurrentAddCity);
	log.info("Label on toast message {}: " + Sname);
	return Sname;
}
	public Boolean istxtcityVisible() {
		log.info("Checking the visibility of txtpostLoginSerMoveInSSL on the page.");
		log.info("txtpostLoginSerMoveInSSL visibility status {}: " + isElementVisible(txtpostLoginSerMoveInCurrentAddCity));
		return isElementVisible(txtpostLoginSerMoveInCurrentAddCity);
	}
//	@FindBy(css = "[globalize='ML_SrvcRqust_txtbx_City']")
//	public By city1;
//
//	public void populatecity1(String city) {
//		log.info("Populating city {} :" + city);
//		sendKeys(city1, city);
//		pause(5000);
//		int count = autoSuggestion.size();
//		autoSuggestion.get(count - 1).click();
//		log.info("state selected");
//	}
//	@FindBy(xpath = "//*[@id=\"formfieldlist\"]/div[7]/div[1]")
//	public By state1;
//
//	public void populateState1(String city) {
//		log.info("Populating city {} :" + city);
//		sendKeys( state1, city);
//		pause(5000);
//		int count = autoSuggestion.size();
//		autoSuggestion.get(count - 1).click();
//		log.info("state selected");
//	}
	@FindBy(xpath = "//input[@type='cityAndStateType' and @searchmode='1' and @tcollectionid='2581' and @mandatory='1']")
	public WebElement state1;
	
	public Boolean isStatesVisible() {
		log.info("Checking the visibility of txtpostLoginSerMoveInCurrentAddUnitNo on the page.");
		log.info("postLoginSerMoveInCurrentAddCity visibility status {}: "
				+ isElementVisible((WebElement) state1));
		return isElementVisible((WebElement) state1);
	}

//	public void populateState1(String State) {
//		log.info("Populating city {} :" + State);
//		sendKeys((By) state1, State);
//		pause(5000);
//		int count = autoSuggestion.size();
//		autoSuggestion.get(count - 1).click();
//		log.info("state selected");
//	}
	@FindBy(xpath = "//ul[@class='ui-menu ui-widget ui-widget-content ui-autocomplete ui-front']/li")
	public List<WebElement> autoSuggestions;

	public void populatestate1(String states) {
		log.info("Populating state {} :" + states);
		sendKeys(state1, states);
		pause(3000);
		int count = autoSuggestion.size();
		autoSuggestion.get(count - 1).click();
		log.info("state selected");
	}
	/*
	 * for (WebElement Ccity : cities) { pause(5000); Ccity.click(); break; }
	 */
//	log.info("city populated successfully.");



public void populateSecondoryContactNo(String Snum) {
	log.info("Populating Cnum {} :" + Snum);
	sendKeys(txtpostLoginSerMoveOutSecondaryContactNum, Snum);
	log.info("Cnum populated successfully.");
}

@FindBy(css = "[globalize='ML_MYACCOUNT_ContactNum_ContactType']")
private WebElement txtpostLoginSerMoveInContactType;

public Boolean ispostLoginSerMoveInContactTypeVisibleVisible() {
	log.info("Checking the visibility of txtpostLoginSerMoveInSSL on the page.");
	log.info("txtpostLoginSerMoveInContactType visibility status {}: "
			+ isElementVisible(txtpostLoginSerMoveInContactType));
	return isElementVisible(txtpostLoginSerMoveInContactType);
}

public boolean SelectAddedContactType(String Ctype) {
	click(txtpostLoginSerMoveInContactType);
	selectByVisibleText(txtpostLoginSerMoveInContactType, Ctype);
	log.info("contact selected successfully.");
	return isElementVisible(txtpostLoginSerMoveInRequestStartState);
}

public String getAddedContactType(String Ctype) {
	String ctype = getText(txtpostLoginSerMoveInContactType);
	log.info("Label on toast message {}: " + ctype);
	return Ctype;
}
public WebElement getContactType() {
	return child_lbl_TopicName;
}

public void Selectcity(String city) {
	// click(dd_StateIntellisenseOptions);
	sendKeys(txtpostLoginSerMoveInCurrentAddCity, city);

	selectByVisibleText(dd_StateIntellisenseOptions, city);
	log.info("contact selected successfully.");
}

public void SelectHomeType(String type) {
	click(txtpostLoginSerMoveInCurrentAddApartment);
	selectByVisibleText(txtpostLoginSerMoveInCurrentAddApartment, type);
	log.info("txtpostLoginSerMoveInCurrentAddApartment selected successfully.");
}

@FindBys(@FindBy(xpath = "//ul[@class='ui-menu ui-widget ui-widget-content ui-autocomplete ui-front']/li"))
public List<WebElement> autoSuggestion;

public void populatestate(String state) {
	log.info("Populating state {} :" + state);
	sendKeys(txtpostLoginSerMoveInRequestStartState, state);
	pause(3000);
	int count = autoSuggestion.size();
	autoSuggestion.get(count - 1).click();
	log.info("state selected");
}
public String getstate(String sname) {
	String Sname = getText(txtpostLoginSerMoveInRequestStartState);
	log.info("Label on toast message {}: " + Sname);
	return Sname;
}
public Boolean ispopulatestateVisible() {
	log.info("Checking the visibility of txtpostLoginSerMoveInDOB on the page.");
	log.info("txtpostLoginSerMoveInDOB visibility status {}: " + isElementVisible(txtpostLoginSerMoveInRequestStartState));
	return isElementVisible(txtpostLoginSerMoveInRequestStartState);
}
public boolean istxtStateVisible (String option) {
	return isElementVisible(txtpostLoginSerMoveInRequestStartState);	

}

/*
 * for(WebElement option : Cstate){ pause(2000);
 * 
 * System.out.println(option); if(option.getText().equals("NEW YORK")) {
 * System.out.println("Trying to select: "+Cstate); option.click(); break;
 */

/*
 * for(WebElement states : Cstate) { click(states); //break;
 */
// log.info("state populated successfully.");

public void populatePrimarySreetName(String Sname) {
	log.info("Populating Cnum {} :" + Sname);
	sendKeys(txtpostLoginSerMoveInCurrentAddStreetName, Sname);
	log.info("Sname populated successfully.");
}
public Boolean ispopulatePrimarySreetNameVisible() {
	log.info("Checking the visibility of txtpostLoginSerMoveInDOB on the page.");
	log.info("txtpostLoginSerMoveInDOB visibility status {}: " + isElementVisible(txtpostLoginSerMoveInCurrentAddStreetName));
	return isElementVisible(txtpostLoginSerMoveInCurrentAddStreetName);
}
public String getPrimarySreetName(String sname) {
	String Sname = getText(txtpostLoginSerMoveInCurrentAddStreetName);
	log.info("Label on toast message {}: " + Sname);
	return Sname;
}
@FindBy(xpath = "(//input[@globalize='ML_lowincome_Date_of_Birth'])[1]")
private WebElement txtpostLoginSerMoveInDOB;
@FindBy(xpath = "//div[@class='datepicker-button datepicker-month-fast-next pull-right enabled']")
private WebElement yearselect;

public Boolean ispostLoginSerMoveInDateOfBirthVisible() {
	log.info("Checking the visibility of txtpostLoginSerMoveInDOB on the page.");
	log.info("txtpostLoginSerMoveInDOB visibility status {}: " + isElementVisible(txtpostLoginSerMoveInDOB));
	return isElementVisible(txtpostLoginSerMoveInDOB);
}

public void populatezipcode(String zipcode) {
	log.info("Populating Cnum {} :" + zipcode);
	sendKeys(txtpostLoginSerMoveInCurrentAddZipCode, zipcode);
	log.info("zipcode populated successfully.");
}
public void populatereqcity(String city) {
	log.info("Populating state {} :" + city);
	sendKeys(txtpostLoginSerMoveInRequestStartCity, city);
	pause(3000);
	int count = autoSuggestion.size();
	autoSuggestion.get(count - 1).click();
	log.info("state selected");
}

//public void populatereqcity(String city) {
//	log.info("Populating Cnum {} :" + city);
//	sendKeys(txtpostLoginSerMoveInRequestStartCity, city);
//	log.info("city populated successfully.");
//}

public void selectDate() throws InterruptedException {
	scrollPageToElement(txtpostLoginSerMoveInDOB);
	click(txtpostLoginSerMoveInDOB);
}

public void selectDateCurrentAdd() throws InterruptedException {
	scrollPageToElement(txtpostLoginSerMoveInCurrentAddDOB);
	click(txtpostLoginSerMoveInCurrentAddDOB);
}

public void selectStartServicedate() throws InterruptedException {
	scrollPageToElement(txtpostLoginSerMoveInRequestStartDate);
	click(txtpostLoginSerMoveInRequestStartDate);
}

public void populatereqstate(String state) {
	log.info("Populating state {} :" + state);
	sendKeys(txtpostLoginSerMoveInStartServiceState, state);
	pause(3000);
	int count = autoSuggestion.size();
	autoSuggestion.get(count - 1).click();
	log.info("state selected");
}

//public void populatereqstate(String state) {
//	log.info("Populating state {} :" + state);
//	sendKeys(txtpostLoginSerMoveInStartServiceState, state);
//	log.info("state populated successfully.");
//}

public void populatereqzipcode(String zipcode) {
	log.info("Populating Cnum {} :" + zipcode);
	sendKeys(txtpostLoginSerMoveInRequestStartZipCode, zipcode);
	log.info("city populated successfully.");
}
public String getreqzipcode(String sname) {
	String Sname = getText(txtpostLoginSerMoveInRequestStartZipCode);
	log.info("Label on toast message {}: " + Sname);
	return Sname;
}
public void clearreqzipcode() {
	clear(txtpostLoginSerMoveInRequestStartZipCode);
}

public void clcknextbtn() throws InterruptedException {
	scrollPageToElement(txtNextbtn);
	click(txtNextbtn);
}
@FindBy(xpath = "//*[@id=\"serviceCardPostLogin_30\"]/div")
private WebElement Movein;
public void clckmovein() throws InterruptedException {
	click(Movein);
}

@FindBy(xpath = "//input[@globalize='ML_OTP_Btn_Submit']")
private WebElement btnSubmitOnReviewAndConfirmPage;

public void clcksubmit() throws InterruptedException {
	scrollPageToElement(btnSubmitOnReviewAndConfirmPage);
	click(btnSubmitOnReviewAndConfirmPage);
}

public void selectYesNOforBillingAdd(String value) throws InterruptedException {
	scrollPageToElement(txtpostLoginSerMoveInBillingAdd);
	selectByVisibleText(txtpostLoginSerMoveInBillingAdd, value);
}

//public void populatedMoveInBillingCity(String city) {
//	log.info("Populating txtpostLoginSerMoveInBillingAddCity {} :" + city);
//	sendKeys(txtpostLoginSerMoveInBillingAddCity, city);
//	log.info("txtpostLoginSerMoveInBillingAddCity populated successfully.");
//}
public void populatedMoveInBillingCity(String city) {
	log.info("Populating state {} :" + city);
	sendKeys(txtpostLoginSerMoveInBillingAddCity, city);
	pause(3000);
	int count = autoSuggestion.size();
	autoSuggestion.get(count - 1).click();
	log.info("state selected");
	
}

public Boolean isMoveInBillingCityVisible() {
	log.info("Checking the visibility of txtpostLoginSerMoveInCurrentAddDOB on the page.");
	log.info("v visibility status {}: " + isElementVisible(txtpostLoginSerMoveInBillingAddCity));
	return isElementVisible(txtpostLoginSerMoveInBillingAddCity);
}


public void populatedMoveInBillingState(String state) {
	log.info("Populating state {} :" + state);
	sendKeys(txtpostLoginSerMoveInBillingAddState, state);
	pause(3000);
	int count = autoSuggestion.size();
	autoSuggestion.get(count - 1).click();
	log.info("state selected");
}

public void populatedMoveInBillingZipcode(String Zipcode) {
	log.info("Populating txtpostLoginSerMoveInBillingAddZipCode {} :" + Zipcode);
	sendKeys(txtpostLoginSerMoveInBillingAddZipCode, Zipcode);
	log.info("txtpostLoginSerMoveInBillingAddZipCode populated successfully.");
}

public void populatedComments(String comments) {
	log.info("Populating txtAdditionalComment {} :" + comments);
	sendKeys(txtAdditionalComment, comments);
	log.info("txtAdditionalComment populated successfully.");
}

public void selectdate(String date) throws InterruptedException {
	log.info("Populating date {} :" + date);
	scrollPageToElement(txtpostLoginSerMoveInDOB);
	click(txtpostLoginSerMoveInDOB);
	String xpath = String.format("//*[@class='day selectable' and @data-value='%s']", date);
	//driver.findElement(By.xpath(xpath)).click();

}
@FindBy(css = "[class='MoveIn Calendar input_effect form-control']")
private WebElement transferSerMoveInDOB;

public void selectdatein(String date) throws InterruptedException {
	log.info("Populating date {} :" + date);
	//scrollPageToElement(txtpostLoginSerMoveInDOB);
	click(transferSerMoveInDOB);
	String css = String.format("//*[@class='day selectable' and @data-value='%s']", date);
}

@FindBy(xpath = "(//input[@globalize='ML_lowincome_Date_of_Birth'])[1]")
private WebElement txtpostLoginSerMoveInCurrentAddDOB;

public Boolean ispostLoginSerMoveInCurrentAddDateOfBirthVisible() {
	log.info("Checking the visibility of txtpostLoginSerMoveInCurrentAddDOB on the page.");
	log.info("v visibility status {}: " + isElementVisible(txtpostLoginSerMoveInCurrentAddDOB));
	return isElementVisible(txtpostLoginSerMoveInCurrentAddDOB);
}

@FindBy(xpath = "//input[@type='text' and @inputtype='Phone' and @class='Phone input_effect']")
// @FindBy(css = "[id='e76f462e-4a65-4e58-89e4-0ffeec096949']")
private WebElement txtpostLoginSerMoveInPrimaryContactNo;

public Boolean ispostLoginSerMoveInPrimaryContactNoVisible() {
	log.info("Checking the visibility of txtpostLoginSerMoveInPrimaryContactNo on the page.");
	log.info("txtpostLoginSerMoveInPrimaryContactNo visibility status {}: "
			+ isElementVisible(txtpostLoginSerMoveInPrimaryContactNo));
	return isElementVisible(txtpostLoginSerMoveInPrimaryContactNo);
}

@FindBy(xpath = "//input[@globalize='ML_MYACCOUNT_ContactNum_Secondary']")
private WebElement txtpostLoginSerMoveOutSecondaryContactNum;

public Boolean ispostLoginSerMoveOutSecondaryContactNumVisible() {
	log.info("txtpostLoginSerMoveOutSecondaryContactNum visibility status {}: "
			+ isElementVisible(txtpostLoginSerMoveOutSecondaryContactNum));
	return isElementVisible(txtpostLoginSerMoveOutSecondaryContactNum);
}

@FindBy(xpath = "(//input[@globalize='ML_SrvcRqust_p_StreetNo'])[1]")
private WebElement txtpostLoginSerMoveInCurrentAddStreetNum;

public Boolean ispostLoginSerMoveInCurrentAddStreetNumVisible() {
	log.info("txtpostLoginSerMoveInCurrentAddStreetNum visibility status {}: "
			+ isElementVisible(txtpostLoginSerMoveInCurrentAddStreetNum));
	return isElementVisible(txtpostLoginSerMoveInCurrentAddStreetNum);
}
public void populateStreetno(String sname) {
	log.info("Populating name {} :" + sname);
	sendKeys(txtpostLoginSerMoveInCurrentAddStreetNum, sname);
	log.info("lastname populated successfully.");
}

public String getStreetno(String Ctype) {
	String ctype = getText(txtpostLoginSerMoveInCurrentAddStreetNum);
	log.info("Label on toast message {}: " + ctype);
	return Ctype;
}
@FindBy(xpath = "//input[@type='text' and @title='Schedule Time' and @tcollectionid='1762' ]")
protected WebElement txtscheduledTime;

public Boolean isscheduledTimeVisible() {
	log.info("txtpostLoginSerMoveInCurrentAddStreetNum visibility status {}: "
			+ isElementVisible(txtscheduledTime));
	return isElementVisible(txtscheduledTime);
}
public void clickscheduledTime() throws InterruptedException {
	//scrollPageToElement(txtscheduledTime);
	click(txtscheduledTime);
}
@FindBy(xpath = "//li[text()='08:00 AM - 09:00 AM']")
protected WebElement scheduledTime1;

public void clickscheduledTime1() throws InterruptedException {
	//scrollPageToElement(txtscheduledTime);
	click(scheduledTime1);
}

public Boolean isOtherFormPersonAvailableFeildVisible() {
	log.info("txtpostLoginPersonalInfo visibility status {}: " + isElementVisible(txtpostLoginPersonalInfo));
	return isElementVisible(txtpostLoginPersonalInfo);
}
public void populatePersonAvailable(String sname) {
	log.info("Populating name {} :" + sname);
	sendKeys(txtpostLoginPersonalInfo, sname);
	log.info("lastname populated successfully.");
}

@FindBy(xpath = "(//input[@globalize ='ML_SrvcRqust_p_StrretName'])[1]")
private WebElement txtpostLoginSerMoveInCurrentAddStreetName;

public Boolean ispostLoginSerMoveInCurrentAddStreetNameVisible() {
	log.info("Checking the visibility of SerMoveInCurrentAddStreetName on the page.");
	log.info("txtpostLoginSerMoveInCurrentAddStreetName visibility status {}: "
			+ isElementVisible(txtpostLoginSerMoveInCurrentAddStreetName));
	return isElementVisible(txtpostLoginSerMoveInCurrentAddStreetName);
}
public void populateStname(String sname) {
	log.info("Populating name {} :" + sname);
	sendKeys(txtpostLoginSerMoveInCurrentAddStreetName, sname);
	log.info("lastname populated successfully.");
}
public String getStname(String Ctype) {
	String ctype = getText(txtpostLoginSerMoveInCurrentAddStreetName);
	log.info("Label on toast message {}: " + ctype);
	return Ctype;
}


@FindBy(xpath = "(//select[@globalize='ML_Service_li_Type'])[1]")
private WebElement txtpostLoginSerMoveInCurrentAddApartment;

public Boolean ispostLoginSerMoveInTypeFieldIsVisible() {
	log.info("Checking the visibility of txtpostLoginSerMoveInPrimaryContactNo on the page.");
	log.info("txtpostLoginSerMoveInCurrentAddApartment visibility status {}: "
			+ isElementVisible(txtpostLoginSerMoveInCurrentAddApartment));
	return isElementVisible(txtpostLoginSerMoveInCurrentAddApartment);
}


public boolean isZipcodeVisible (String option) {
	return isElementVisible(txtpostLoginSerMoveInRequestStartZipCode);}	

public boolean ispostLoginSerMoveInTypeFieldoptnsVisible (String option) {
	return isElementVisible(txtpostLoginSerMoveInCurrentAddApartment);}	


public boolean ispostStreetVisible (String option) {
	return isElementVisible(txtpostLoginSerMoveInRequestStartStreetName);}

public boolean isHomeoptnVisible (String option) {
	return isElementVisible(txtpostLoginSerMoveInCurrentAddApartment);}	

@FindBy(xpath = "(//input[@globalize='ML_CONNECTME_Lbl_Apt'])[1]")
private WebElement txtpostLoginSerMoveInCurrentAddUnitNo;

public Boolean ispostLoginSerMoveInAptAndUnitNumFieldIsVisible() {
	log.info("Checking the visibility of txtpostLoginSerMoveInCurrentAddUnitNo on the page.");
	log.info("txtpostLoginSerMoveInCurrentAddUnitNo visibility status {}: "
			+ isElementVisible(txtpostLoginSerMoveInCurrentAddUnitNo));
	return isElementVisible(txtpostLoginSerMoveInCurrentAddUnitNo);
}

public Boolean ispostLoginSerMoveInCurrentAddUnitNoVisible() {
	log.info("Checking the visibility of txtpostLoginSerMoveInCurrentAddUnitNo on the page.");
	log.info("txtpostLoginSerMoveInCurrentAddUnitNo visibility status {}: "
			+ isElementVisible(txtpostLoginSerMoveInCurrentAddUnitNo));
	return isElementVisible(txtpostLoginSerMoveInCurrentAddUnitNo);
}

public void populateAptunit(String sname) {
	log.info("Populating name {} :" + sname);
	sendKeys(txtpostLoginSerMoveInCurrentAddUnitNo, sname);
	log.info("lastname populated successfully.");
}
public String getAptunit(String Ctype) {
	String ctype = getText(txtpostLoginSerMoveInCurrentAddUnitNo);
	log.info("Label on toast message {}: " + ctype);
	return Ctype;
}
@FindBy(css = "[globalize=\"ML_SrvcRqust_txtbx_City\"]")
private WebElement txtpostLoginSerMoveInCurrentAddCity;

public Boolean ispostLoginSerMoveInCurrentAddCityVisible() {
	log.info("Checking the visibility of txtpostLoginSerMoveInCurrentAddUnitNo on the page.");
	log.info("postLoginSerMoveInCurrentAddCity visibility status {}: "
			+ isElementVisible(txtpostLoginSerMoveInCurrentAddCity));
	return isElementVisible(txtpostLoginSerMoveInCurrentAddCity);
}
public void populateCityname(String sname) {
	log.info("Populating name {} :" + sname);
	sendKeys(txtpostLoginSerMoveInCurrentAddCity, sname);
	log.info("lastname populated successfully.");
}

@FindBy(xpath = "(//input[@globalize='ML_SrvcRqust_P_ZipCode'])[1]")
private WebElement txtpostLoginSerMoveInCurrentAddZipCode;

public Boolean ispostLoginSerMoveInCurrentAddZipCodeVisible() {
	log.info("Checking the visibility of txtpostLoginSerMoveInCurrentAddZipCode on the page.");
	log.info("txtpostLoginSerMoveInCurrentAddZipCode visibility status {}: "
			+ isElementVisible(txtpostLoginSerMoveInCurrentAddZipCode));
	return isElementVisible(txtpostLoginSerMoveInCurrentAddZipCode);
}

@FindBy(xpath = "//*[@id=\"formfieldlist\"]/div[12]/div/label")
private WebElement adulttoggle;

public Boolean isadulttoggleVisible() {
	log.info("Checking the visibility of adulttoggle on the page.");
	log.info("adulttoggle visibility status {}: " + isElementVisible(adulttoggle));
	return isElementVisible(adulttoggle);
}

@FindBy(css = ".toggle_lbl_class")
private WebElement txttoggle;

@FindBy(xpath = "//*[@id='formfieldlist']/div[6]/div[2]/label")
private WebElement LockedGatestoggle;


public Boolean isLockedGates() {
	log.info("Checking the visibility of txttoggle on the page.");
	log.info("txtpostLoginSerMoveInBillingAdd visibility status {}: " + isElementVisible(txttoggle));
	return isElementVisible(txttoggle);
}
public void clickLockedGates() throws InterruptedException {
	scrollPageToElement(LockedGatestoggle);
	click(LockedGatestoggle);
}
@FindBy(xpath = "//*[@id='formfieldlist']/div[5]/div[2]/label")
private WebElement Pettoggle;


public Boolean isPetToggleisvisible() {
	log.info("Checking the visibility of txttoggle on the page.");
	log.info("txtpostLoginSerMoveInBillingAdd visibility status {}: " + isElementVisible(txttoggle));
	return isElementVisible(txttoggle);
}
public void clickPetToggle() throws InterruptedException {
	scrollPageToElement(Pettoggle);
	click(Pettoggle);
}

public void clicktoggle() throws InterruptedException {
	scrollPageToElement(adulttoggle);
	click(adulttoggle);
}

public void populatestreetname(String streetname) {
	log.info("Populating ssn {} :" + streetname);
	sendKeys(txtpostLoginSerMoveInRequestStartStreetName, streetname);
	log.info("streetname populated successfully.");
}
@FindBy(xpath = "//input[@type='text' and @inputtype='StreetName' and contains(@class, 'IsLocation')]") 
private WebElement txtpostLoginSerMoveInRequestStartStreetName2;
public void clickstreetname2() {
	log.info("Clicking the sign in button.");
	click(txtpostLoginSerMoveInRequestStartStreetName2);
	log.info("Track Request clicked successfully.");
}
public void populatestreetname2(String streetname) {
	log.info("Populating streetname {} :" + streetname);
	sendKeys(txtpostLoginSerMoveInRequestStartStreetName2, streetname);
	log.info("streetname populated successfully.");
}
public void SelectHomeType2(String type) {
	click(txtpostLoginSerMoveInRequestStartStreetName2);
	selectByVisibleText(txtpostLoginSerMoveInRequestStartStreetName2, type);
	log.info("txtpostLoginSerMoveInCurrentAddApartment selected successfully.");
}
public Boolean ispostLoginSerMoveInBillingAddVisible() {
	log.info("Checking the visibility of txtpostLoginSerMoveInCurrentAddZipCode on the page.");
	log.info("txtpostLoginSerMoveInBillingAdd visibility status {}: "
			+ isElementVisible(txtpostLoginSerMoveInBillingAdd));
	return isElementVisible(txtpostLoginSerMoveInBillingAdd);
}

@FindBy(css = "[globalize='ML_SrvcRqust_lbl_StrtDate']")
private WebElement txtpostLoginSerMoveInRequestStartDate;

public Boolean ispostLoginSerMoveInRequestStartDateVisible() {
	log.info("Checking the visibility of txtpostLoginSerMoveInRequestStartDate on the page.");
	log.info("txtpostLoginSerMoveInRequestStartDate visibility status {}: "
			+ isElementVisible(txtpostLoginSerMoveInRequestStartDate));
	return isElementVisible(txtpostLoginSerMoveInRequestStartDate);
}

@FindBy(xpath = " (//input[@globalize='ML_SrvcRqust_p_StreetNo'])[2]")
private WebElement txtpostLoginSerMoveInRequestStartStreetNo;

public Boolean ispostLoginSerMoveInStartServiceStreetNoVisible() {
	log.info("Checking the visibility of txtpostLoginSerMoveInCurrentAddZipCode on the page.");
	log.info("txtpostLoginSerMoveInRequestStartStreetNo visibility status {}: "
			+ isElementVisible(txtpostLoginSerMoveInRequestStartStreetNo));
	return isElementVisible(txtpostLoginSerMoveInRequestStartStreetNo);
}

@FindBy(xpath = "(//input[@globalize='ML_SrvcRqust_p_StrretName'])[2]")
private WebElement txtpostLoginSerMoveInRequestStartStreetName;

public Boolean ispostLoginSerMoveInRequestStartStreetNameVisible() {
	log.info("Checking the visibility of txtpostLoginSerMoveInRequestStartStreetName on the page.");
	log.info("txtpostLoginSerMoveInRequestStartStreetName visibility status {}: "
			+ isElementVisible(txtpostLoginSerMoveInRequestStartStreetName));
	return isElementVisible(txtpostLoginSerMoveInRequestStartStreetName);
}

@FindBy(xpath = "(//select[@globalize='ML_Service_li_Type'])[2]")
private WebElement txtpostLoginSerMoveInRequestStartApartment;

public Boolean ispostLoginSerMoveInRequestStartApartmentVisible() {
	log.info("Checking the visibility of txtpostLoginSerMoveInRequestStartApartment on the page.");
	log.info("txtpostLoginSerMoveInRequestStartApartment visibility status {}: "
			+ isElementVisible(txtpostLoginSerMoveInRequestStartApartment));
	return isElementVisible(txtpostLoginSerMoveInRequestStartApartment);
}

@FindBy(xpath = "(//input[@globalize='ML_CONNECTME_Lbl_Apt'])[2]")
private WebElement txtpostLoginSerMoveInRequestStartUnit;

@FindBy(xpath = "(//input[@globalize='ML_SrvcRqust_txtbx_City'])[2]")
private WebElement txtpostLoginSerMoveInRequestStartCity;

public Boolean ispostLoginSerMoveInRequestStartCity() {
	log.info("txtpostLoginSerMoveInRequestStartCity visibility status {}: "
			+ isElementVisible(txtpostLoginSerMoveInRequestStartCity));
	return isElementVisible(txtpostLoginSerMoveInRequestStartCity);
}

@FindBy(xpath = " (//input[@globalize='ML_SrvcRqust_txtbx_MState'])[1]")
private WebElement txtpostLoginSerMoveInRequestStartState;

public Boolean ispostLoginSerMoveInCurrentAddStateVisible() {
	log.info("txtpostLoginSerMoveInRequestStartState visibility status {}: "
			+ isElementVisible(txtpostLoginSerMoveInRequestStartState));
	return isElementVisible(txtpostLoginSerMoveInRequestStartState);
}

@FindBy(xpath = "	(//input[@globalize='ML_SrvcRqust_txtbx_MState'])[2]")
private WebElement txtpostLoginSerMoveInStartServiceState;

public Boolean ispostLoginSerMoveInStartServiceStateVisible() {
	log.info("txtpostLoginSerMoveInStartServiceState visibility status {}: "
			+ isElementVisible(txtpostLoginSerMoveInStartServiceState));
	return isElementVisible(txtpostLoginSerMoveInStartServiceState);
}

@FindBy(xpath = "	//input[@type='text' and @globalize='ML_zip_code' and @tcollectionid='1812' and @mandatory='1']")
private WebElement txtpostLoginSerMoveInRequestStartZipCode;

public Boolean ispostLoginSerMoveInRequestStartZipCodeVisible() {
	log.info("txtpostLoginSerMoveInRequestStartZipCode visibility status {}: "
			+ isElementVisible(txtpostLoginSerMoveInRequestStartZipCode));
	return isElementVisible(txtpostLoginSerMoveInRequestStartZipCode);
}
public void populateZip(String sname) {
	log.info("Populating name {} :" + sname);
	sendKeys(txtpostLoginSerMoveInRequestStartZipCode, sname);
	log.info("lastname populated successfully.");
}

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

@FindBy(xpath = "(//input[@globalize='ML_SrvcRqust_P_ZipCode'])[2]")
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
@FindBy(xpath = "//*[@globalize=\"ML_SrvcRqust_txtbx_Comment\"]")
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

@FindBy(xpath = " //*[@id=\"devices\"]/div/div/div/div/div[2]/button[2]")
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
@FindBy(css = " #btnok")
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

@FindBy(css = "[globalize= 'ML_Saved_Forms']")
private WebElement btnSaveForms;

public void clickSaveFormsBtn() {
	log.info("Clicking the btnSaveForms button.");
	click(btnSaveForms);
	log.info("btnSaveForms button clicked successfully.");
}
@FindBy(xpath = "//*[@id=\"navbarDropdown\"]/i")
private WebElement btn3dot;

public void clickthreedotBtn() {
	log.info("Clicking the btnSaveForms button.");
	click(btn3dot);
	log.info("btnSaveForms button clicked successfully.");
}
@FindBy(xpath = "//*[@id=\"tblSavedForms\"]/tbody/tr[1]/td[4]/div/div/ul/li[1]/a")
private WebElement btnedit;

public void clickeditBtn() {
	log.info("Clicking the btnSaveForms button.");
	click(btnedit);
	log.info("btnSaveForms button clicked successfully.");
}
@FindBy(css = ".Email.input_effect")
private WebElement txtEmailAddress;

public void populateEmailAddress(String emailAddress) {
	log.info("Populating EmailAddress {} :" + emailAddress);
	sendKeys(txtEmailAddress, emailAddress);
	log.info("EmailAddress populated successfully.");
}

public WebElement elementEmailAdd() {
	waitForElementToBeVisible(txtEmailAddress);
	log.info("Visiblity status of element Email Add:" + txtEmailAddress.isDisplayed());
	return txtEmailAddress;
}

@FindBy(css = " .tracking_area #btnSaveSubmit")
private WebElement btnpostLoginSaveFormsSubmit;

public void clickpostLoginSaveFormsSubmitBtn() {
	log.info("Clicking the postLoginSaveFormsSubmit button.");
	click(btnpostLoginSaveFormsSubmit);
	log.info("postLoginSaveFormsSubmit button clicked successfully.");
}

@FindBy(css = " #btnNext.submit-button")
private WebElement btnpostLoginSaveFormsNext;

public void clickpostLoginSaveFormsNextBtn() {
	log.info("Clicking the btnpostLoginSaveFormsNext button.");
	click(btnpostLoginSaveFormsNext);
	log.info("btnpostLoginSaveFormsNext button clicked successfully.");
}

@FindBy(css = " #btnSubmitChanges.submit-button")
private WebElement btnpostLoginSaveFormSubmit;

public void clickpostLoginSaveFormSubmit() {
	click(btnpostLoginSaveFormSubmit);
}

public void clickOkbutton() {
	click(btnSubmit);
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

public void selectstreetAdd() throws InterruptedException {
	scrollPageToElement(btnMultiSelectBillingAddress);
	click(btnMultiSelectBillingAddress);
	click(driver
			.findElement(By.xpath("//*[@id=\"formfieldlist\"]/div[21]/div/div[1]/p/div/ul/li[1]/a/label/input")));
}

@FindBy(css = " .srvc_rqst_btn #btnNext")
private WebElement btnMoveInNextsrv;
@FindBy(css = " #btnok")
private WebElement btnMoveInPopupOk;
@FindBy(css = "[globalize='ML_Notification_Lbl_Attachment']")
private WebElement btnChooseFile;
@FindBy(css = "[globalize='ML_CONNECTME_Lbl_AddAttach']")
private WebElement btnChooseFiles;
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
@FindBy(css = "#tblSubmittedForms > tbody > tr > td")
private WebElement noRecord;

public String getNoRecordtxt() {
	String toast_msg = getText(noRecord);
	log.info("Label on toast message {}: " + toast_msg);
	return toast_msg;
}
@FindBy(xpath = "//*[@id='tblSavedForms']/tbody/tr/td")
private WebElement noRecords;

public String getNoRecordstxt() {
	String toast_msg = getText(noRecords);
	log.info("Label on toast message {}: " + toast_msg);
	return toast_msg;
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
	sendKeys(txtBoxMfaOtp, requestIdOTP);
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
public void addAttachmentToChooseFiles(String value) {
	sendKeysWithoutCheckingVisibility(btnChooseFiles, value);
	log.info("Choose File Button added successfully with File Value " + value);
	ExtentLogger.logInfo("Choose File Button added successfully with File Value " + value);
}

// disclaimer

@FindBy(xpath = "//*[@id='formfieldlist']/div[2]/div/div")
private WebElement txtContactInfo;

public String gettxtContactInfoDisclaimer() {
	log.info("Fetching gettxtContactInfoDisclaimer");
	String label = getText(txtContactInfo);
	log.info("Service Acc No page header is {}: " + label);
	return label;
}

@FindBy(css = "#formfieldlist > div:nth-child(12) > div > legend")
private WebElement txtadultBeLivingDisclairmer;

public String gettxtadultBeLivingDisclairmer() {
	log.info("Fetching gettxtContactInfoDisclaimer");
	String label = getText(txtadultBeLivingDisclairmer);
	log.info("txtadultBeLivingDisclairmer {}: " + label);
	return label;
}

@FindBy(xpath = "//*[@id='disclaimer']")
private WebElement txtUsethisFormDisclaimer;

public String gettxtUsethisFormDisclaimer() {
	log.info("Fetching txtUsethisFormDisclaimer");
	String label = getText(txtUsethisFormDisclaimer);
	log.info("txtUsethisFormDisclaimer {}: " + label);
	return label;
}

@FindBy(xpath = "//*[@id=\"formfieldlist\"]/div[2]/div/div")
private WebElement txtProcessedReq;

public String gettxtProcessedReqDisclaimer() {
	log.info("Fetching txtProcessedReq");
	String label = getText(txtProcessedReq);
	log.info("txtProcessedReq is on page {}: " + label);
	return label;
}

@FindBy(xpath = "//*[@id=\"formfieldlist\"]/div[3]/div/div")
private WebElement txtWhen;

public String gettxtWhenDisclaimer() {
	log.info("Fetching txtWhen");
	String label = getText(txtWhen);
	log.info("txtWhen is on page {}: " + label);
	return label;
}

@FindBy(xpath = "//*[@id=\"formfieldlist\"]/div[5]/div/div")
private WebElement txtContctInfo;

public String gettxtContcttInfoDisclaimer() {
	log.info("Fetching txtContctInfo");
	String label = getText(txtContctInfo);
	log.info("txtContctInfo is on page {}: " + label);
	return label;
}

@FindBy(xpath = "//*[@id=\"formfieldlist\"]/div[10]/div/div")
private WebElement txtMailingAdd;

public String gettxtMailingAddDisclaimer() {
	log.info("Fetching txtMailingAdd");
	String label = getText(txtMailingAdd);
	log.info("txtMailingAdd is on page {}: " + label);
	return label;
}

@FindBy(xpath = "//*[@class=\"att_disclaimer\"]")
private WebElement txtattachDic;

public String gettxtAttachmentDisclaimer() {
	log.info("Fetching txtattachDic");
	String label = getText(txtattachDic);
	log.info("txtattachDic is on page {}: " + label);
	return label;
}

// Cancel,save,next

@FindBy(xpath = "//input[@globalize='ML_LoginSupport_otherbtn_Cancel']")
private WebElement txtCancelbtn;

public Boolean ispostLoginSerMoveInPageCancelBtnVisible() {
	log.info("Checking the visibility of txtCancelbtn on the page.");
	log.info("txtCancelbtn visibility status {}: " + isElementVisible(txtCancelbtn));
	return isElementVisible(txtCancelbtn);
}

@FindBy(xpath = "//input[@globalize='ML_Service_btn_clkToSave']")
private WebElement txtSavebtn;

public Boolean ispostLoginSerMoveInPageSaveBtnVisible() {
	log.info("Checking the visibility of txtSavebtn on the page.");
	log.info("txtSavebtn visibility status {}: " + isElementVisible(txtSavebtn));
	return isElementVisible(txtSavebtn);
}
@FindBy(xpath = "//*[@id='btnSaveToDraft']")
private WebElement Savebtn;

public void clickSave() throws InterruptedException {
	log.info("Clicking the txtSavebtn Services");
	scrollPageToElement(Savebtn);
	click(Savebtn);
	log.info("post Log  txtSavebtn clicked successfully.");
}

public void clickSaveForm() {
	log.info("Clicking the txtSavebtn Services");
	click(txtSavebtn);
	log.info("post Log  txtSavebtn clicked successfully.");
}

@FindBy(xpath = "//*[@id='btnNext']")
private WebElement txtNextbtn;

public Boolean ispostLoginSerMoveInPageNexBtnVisible() {
	log.info("Checking the visibility of txtCancelbtn on the page.");
	log.info("txtNextbtn visibility status {}: " + isElementVisible(txtNextbtn));
	return isElementVisible(txtNextbtn);
}
@FindBy(css = "[class='acknowledgement']")
private WebElement Mailingcheckbox;

public Boolean isMailingCheckVisible() {
	log.info("Checking the visibility of txtCancelbtn on the page.");
	log.info("txtNextbtn visibility status {}: " + isElementVisible(Mailingcheckbox));
	return isElementVisible(Mailingcheckbox);
}
public void clickCheckbox() throws InterruptedException {
	log.info("Clicking the link Services");
	scrollPageToElement(Mailingcheckbox);
	click(Mailingcheckbox);
	log.info("post Log link Services clicked successfully.");
}
@FindBy(xpath = "//*[@id='formfieldlist']/div[16]/div[1]")
private WebElement Streetno;

public Boolean isStreetNumberVisible() {
	log.info("Checking the visibility of txtCancelbtn on the page.");
	log.info("txtNextbtn visibility status {}: " + isElementVisible(Streetno));
	return isElementVisible(Streetno);	
}
public void populateStreetnum(String sname) {
	log.info("Populating name {} :" + sname);
	sendKeys(Streetno, sname);
	log.info("lastname populated successfully.");
}

@FindBy(xpath = "//input[@type='text' and @inputtype='StreetName' and @tcollectionid='1777' and @mandatory='1']")
private WebElement Streetname;

public Boolean isStreetNameVisible() {
	log.info("Checking the visibility of txtCancelbtn on the page.");
	log.info("txtNextbtn visibility status {}: " + isElementVisible(Streetname));
	return isElementVisible(Streetname);
}
public void populateStreetname(String sname) {
	log.info("Populating name {} :" + sname);
	sendKeys(Streetname, sname);
	log.info("lastname populated successfully.");
}
public String getStreetname(String Ctype) {
	String ctype = getText(Streetname);
	log.info("Label on toast message {}: " + ctype);
	return Ctype;
}


@FindBy(xpath = "//input[@type='text' and @title='Click to enter Apt/Unit Number' and @tcollectionid='1791' and @mandatory='1']")
private WebElement Aptunit;

public Boolean isAptUnitVisible() {
	log.info("Checking the visibility of txtCancelbtn on the page.");
	log.info("txtNextbtn visibility status {}: " + isElementVisible(Aptunit));
	return isElementVisible(Aptunit);
}
public void populateAptUnit(String sname) {
	log.info("Populating name {} :" + sname);
	sendKeys(Aptunit, sname);
	log.info("lastname populated successfully.");
}
public String getAptUnit(String Ctype) {
	String ctype = getText(Aptunit);
	log.info("Label on toast message {}: " + ctype);
	return Ctype;
}

@FindBy(css = "[globalize='ML_Register_Lbl_City']")
private WebElement city;

public Boolean isCityVisible() {
	log.info("Checking the visibility of txtCancelbtn on the page.");
	log.info("txtNextbtn visibility status {}: " + isElementVisible(city));
	return isElementVisible(city);
}
@FindBy(xpath = "//input[@type='cityAndStateType' and @searchmode='1' and @tcollectionid='2582' and @mandatory='1']")
private WebElement state;

public Boolean isStateVisible() {
	log.info("Checking the visibility of txtCancelbtn on the page.");
	log.info("txtNextbtn visibility status {}: " + isElementVisible(state));
	return isElementVisible(state);
}
public void populatestate2(String states) {
	log.info("Populating state {} :" + states);
	sendKeys(state1, states);
	pause(3000);
	int count = autoSuggestion.size();
	autoSuggestion.get(count - 1).click();
	log.info("state selected");
}
@FindBy(xpath = "//*[@id=\"formfieldlist\"]/div[18]/div[2]")
private WebElement Zipcode;

public Boolean isZipcodeVisible() {
	log.info("Checking the visibility of txtCancelbtn on the page.");
	log.info("txtNextbtn visibility status {}: " + isElementVisible(Zipcode));
	return isElementVisible(Zipcode);
}
public void populateZipcode(String sname) {
	log.info("Populating name {} :" + sname);
	sendKeys(Zipcode, sname);
	log.info("lastname populated successfully.");
}
public String getZipcode(String Ctype) {
	String ctype = getText(Zipcode);
	log.info("Label on toast message {}: " + ctype);
	return Ctype;
}
@FindBy(css = "[globalize='ML_SrvcRqust_txtbx_Comment']")
private WebElement Addcmts;

public Boolean isAdditionalCommentsVisible() {
	log.info("Checking the visibility of txtCancelbtn on the page.");
	log.info("txtNextbtn visibility status {}: " + isElementVisible(Addcmts));
	return isElementVisible(Addcmts);
}
@FindBy(xpath = "//*[@id='FileUpload1']")
private WebElement Attachments;

public Boolean isAttachmentVisible() {
	log.info("Checking the visibility of txtCancelbtn on the page.");
	log.info("txtNextbtn visibility status {}: " + isElementVisible(Attachments));
	return isElementVisible(Attachments);
}



public String getpostpopulatedDate() {
	String label = getAttribute(txtpostLoginMoveInDate, "value");
	log.info("DATE {}: " + label);
	return label;

}

@FindBy(css = "	.error_messagecommon+span")
private WebElement asterisk;

public void vefifyAsterisk() {
	log.info("Waiting For Visiblity status of Last Name Text Feild Error Message");
	waitForElementToBeVisible(asterisk);
	log.info("asterisk is displayed:" + asterisk.isDisplayed());
}

@FindBy(css = "div:nth-child(1) > span.error_messagecommon")
private WebElement errorMsgsLeft;

@FindBy(css = "div:nth-child(2) > span.error_messagecommon")
private WebElement errorMsgsRight;

public String getErrMsgEmail() {
	log.info("Waiting For Visiblity status of getErrMsgEmail Text Feild Error Message");
	waitForElementToBeVisible(errorMsgsLeft);
	log.info("Visiblity status of Address Error Message:" + errorMsgsLeft.isDisplayed());
	return getText(errorMsgsLeft);
}

public String getErrMsgZipCode() {
	log.info("Waiting For Visiblity status of zipcode Text Feild Error Message");
	waitForElementToBeVisible(errorMsgsRight);
	log.info("Visiblity status of Address Error Message:" + errorMsgsRight.isDisplayed());
	return getText(errorMsgsRight);
}

public String getErrMsgSteetName() {
	log.info("Waiting For Visiblity status of Street name Text Feild Error Message");
	waitForElementToBeVisible(errorMsgsRight);
	log.info("Visiblity status Error Message:" + errorMsgsRight.isDisplayed());
	return getText(errorMsgsRight);
}

public String getErrMsgLastName() {
	log.info("Waiting For Visiblity status of Last name Text Feild Error Message");
	waitForElementToBeVisible(errorMsgsRight);
	log.info("Visiblity status Error Message:" + errorMsgsRight.isDisplayed());
	return getText(errorMsgsRight);
}

public String getErrMsgSecContNum() {
	log.info("Waiting For Visiblity status of Sec Cont Num Text Feild Error Message");
	waitForElementToBeVisible(errorMsgsRight);
	log.info("Visiblity status Error Message:" + errorMsgsRight.isDisplayed());
	return getText(errorMsgsRight);
}

public String getErrMsgContactTyp() {
	log.info("Waiting For Visiblity status of ContactTyp Text Feild Error Message");
	waitForElementToBeVisible(errorMsgsRight);
	log.info("Visiblity status Error Message:" + errorMsgsRight.isDisplayed());
	return getText(errorMsgsRight);
}

public String getErrMsgFisrtName() {
	log.info("Waiting For Visiblity status of FisrtName Text Feild Error Message");
	waitForElementToBeVisible(errorMsgsLeft);
	log.info("Visiblity status Error Message:" + errorMsgsLeft.isDisplayed());
	return getText(errorMsgsLeft);
}

}


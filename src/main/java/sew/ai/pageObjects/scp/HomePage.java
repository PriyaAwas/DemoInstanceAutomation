package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import sew.ai.driver.Page;
import sew.ai.helpers.reporters.ExtentLogger;

import java.time.Duration;
import java.util.List;

public class HomePage extends Page {
	private static final Logger log = LogManager.getLogger(HomePage.class);

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "h1#PageTitle")
	private WebElement lblPageHeader;

	public Boolean isPageHeaderVisible() {
		Boolean status = isElementVisible(lblPageHeader);
		log.info("Page header visibility status {} " + status);
		ExtentLogger.logInfo("Page header visibility status {} " + status);
		return status;
	}

	public String getPageHeader() {
		String header = getText(lblPageHeader);
		log.info("Page header label is {} " + header);
		ExtentLogger.logInfo("Page header label is {} " + header);
		return header;
	}

	@FindBy(css = "#lbllistbox")
	private WebElement lblSelectAccount;

	public Boolean isSelectAccountLabelVisible() {
		Boolean status = isElementVisible(lblSelectAccount);
		log.info("Select account visibility status {} " + status);
		ExtentLogger.logInfo("Select account visibility status {} " + status);
		return status;
	}

	public String getSelectAccountLabel() {
		String header = getText(lblSelectAccount);
		log.info("Select account label is {} " + header);
		ExtentLogger.logInfo("Select account label is {} " + header);
		return header;
	}

	@FindBy(css = "button#dLabel")
	private WebElement btnAccountDropDown;

	public Boolean isAccountDropDownButtonVisible() {
		Boolean status = isElementVisible(btnAccountDropDown);
		log.info("Account drop-down button visibility status {} " + status);
		ExtentLogger.logInfo("Account drop-down button visibility status {} " + status);
		return status;
	}

	public String getAccountDropDownButtonLabel() {
		String header = getText(btnAccountDropDown);
		log.info("Account drop-down button label is {} " + header);
		ExtentLogger.logInfo("Account drop-down button label is {} " + header);
		return header;
	}

	public void clickAccountDropDownButton() {
		click(btnAccountDropDown);
		log.info("Account drop-down button clicked successfully.");
		ExtentLogger.logInfo("Account drop-down button clicked successfully.");
	}

	@FindBy(css = "ul#UlddlAddress")
	private WebElement accountDropDownList;

	public Boolean isAccountDropDownListVisible() {
		Boolean status = isElementVisible(btnAccountDropDown);
		log.info("Account drop-down list visibility status {} " + status);
		ExtentLogger.logInfo("Account drop-down list visibility status {} " + status);
		return status;
	}

	@FindBys(@FindBy(css = "ul#UlddlAddress li a"))
	private List<WebElement> accountDropDownListItemLinks;

	public List<WebElement> getAccountDropDownListItemLinkElements() {
		log.info("Account drop-down list items size : " + accountDropDownListItemLinks.size());
		ExtentLogger.logInfo("Account drop-down list items size : " + accountDropDownListItemLinks.size());
		return accountDropDownListItemLinks;
	}

	public void clickListItemLinkWithGivenIndex(int index) {
		click(accountDropDownListItemLinks.get(index));
		log.info("Clicked account drop-down list item on index : " + index + " successfully.");
		ExtentLogger.logInfo("Clicked account drop-down list item on index : " + index + " successfully.");
	}

	public void clickAccountListItemLinkWithGivenAccount(String utilityAccNum) {
		click(driver.findElement(By.xpath("//ul[@id='UlddlAddress']/li/a[text()='" + utilityAccNum + "']")));
		log.info("Clicked account drop-down list item with utility account number : " + utilityAccNum);
		ExtentLogger.logInfo("Clicked account drop-down list item with utility account number : " + utilityAccNum);
	}

	@FindBy(css = "#page_loader")
	private WebElement page_loader;

	public void waitForPageLoader() {
		log.info("Wait for page loader visibility.");
		waitForElementToBeVisible(page_loader);
		log.info("Wait for page loader invisibility.");
		waitForElementToBeInVisible(page_loader);
	}

	public void waitForPageLoaderVisibility() {
		log.info("Wait for page loader visibility.");
		waitForElementToBeVisible(page_loader);
	}

	public void waitForPageLoaderInvisibility() {
		log.info("Wait for page loader invisibility.");
		waitForElementToBeInVisible(page_loader);
	}

	@FindBy(css = "#toast-container .toast-message")
	private WebElement lbl_toast_message;

	public String getToastMessageWithoutWait() {
		String toast_msg = getText(lbl_toast_message);
		log.info("Label on toast message {}: " + toast_msg);
		return toast_msg;
	}

	public void waitForToastMessageInvisibility() {
		log.info("Wait for toast message invisibility.");
		waitForElementToBeInVisible(lbl_toast_message);
	}

	public void waitForToastMessageVisibility() {
		log.info("Wait for toast message Visibility.");
		waitForElementToBeVisible(lbl_toast_message);
	}

	public Boolean istoastMessageVisible() {
		Boolean status = isElementVisible(lbl_toast_message);
		log.info("Page header visibility status {} " + status);
		ExtentLogger.logInfo("Page header visibility status {} " + status);
		return status;
	}

	public String getToastMessage() {
		// waitForPageLoader();
		pause(500);
		String toast_msg = getTextWithoutCheckingVisibility(lbl_toast_message);
		log.info("Label on toast message {}: " + toast_msg);
		clickElementUsingJsExecutor(btn_close_toast);
		return toast_msg;
	}

	public String getToastSuccessMessageWithoutCloseBtn() {
		pause(500);
		String toast_msg = getTextWithoutCheckingVisibility(lbl_toast_message);
		log.info("Label on toast message {}: " + toast_msg);
		return toast_msg;
	}

	@FindBy(css = "#toast-container .toast-close-button")
	private WebElement btn_close_toast;

	public void clickToastCloseBtn() {
		click(btn_close_toast);
		log.info("Toast close button clicked {}");
	}

	public void waitForBtnCloseToastToBeVisible(){
		waitForElementToBeVisible(btn_close_toast);
	}

	@FindBy(css = ".spinner")
	private WebElement imgLoadingSpinner;

	public void waitForImgLoadingInvisibility() {
		log.info("Wait for Spinner Loading Image invisibility.");
		waitForElementToBeInVisible(imgLoadingSpinner);
	}

	@FindBy(css = "span[class='error_messagecommon']")
	private WebElement common_field_validation;

	public String getCommonValidationMsg() {
		String validationMsg = getText(common_field_validation);
		log.info("Common field validation message is {}: " + validationMsg);
		return validationMsg;
	}

	public void waitForCommon_field_validationToBeVisible() {
		waitForElementToBeVisible(common_field_validation);
		log.info("Common field validation message is Visible on the page.");
	}



	@FindBy(css = "#faqlink")
	private WebElement lnk_faqs;

	public void clickFAQSLink() {
		click(lnk_faqs);
		log.info("FAQs link clicked successfully {} .");
	}

	@FindBy(css = ".footer[href = 'connect-me_template.aspprivate WebElement ele_text_ele;x']")
	private WebElement lnk_contact_us_footer;

	public void clickContactUsFooter() {
		click(lnk_contact_us_footer);
		log.info("Contact Us link clicked successfully {} .");
	}

	@FindBy(css = ".dashboard_home a[href *= 'Dashboard']")
	private WebElement lnk_home;

	public void clickHomeLink() {
		click(lnk_home);
		log.info("Home link clicked successfully {} .");
	}

	@FindBy(css = ".myaccount.dropdown > a")
	private WebElement lnk_my_account;

	public void clickMyAccountLink() {

		click(lnk_my_account);
		log.info("My account link clicked successfully.");
	}
	public void waitForLnk_my_accountToVisible() {
		waitForElementToBeVisible(lnk_my_account);
		log.info("My Account Link is now Appeared");
	}

	@FindBy(css = "a[globalize = 'ML_MYACCOUNT_Navigation_Profile']")
	private WebElement lnk_profile;

	public void clickProfileLink() {
		click(lnk_profile);
		log.info("My account link clicked successfully.");
	}

	@FindBy(css = "a[globalize = 'ML_MyAccount_div_PaymentInfo']")
	private WebElement lnk_payment_info;

	public void clickPaymentInfoLink() {
		click(lnk_payment_info);
		log.info("My account payment info link clicked successfully.");
	}

	@FindBy(css = ".icon_aboutmyhome.list-group - item a")
	private WebElement lnk_about_my_home_business;

	public void clickAboutMyHomeBusinessLink() {
		click(lnk_about_my_home_business);
		log.info("My account About My Home and Business link clicked successfully.");
	}

	@FindBy(css = ".nitiF_acc_ico.list-group-item a")
	private WebElement lnk_notification_pref;

	public void clickNotificationPreferenceLink() {
		click(lnk_notification_pref);
		log.info("My account Notification Preference info link clicked successfully.");
	}

	@FindBy(css = ".InviteUser_ico.list-group-item a")
	private WebElement lnk_guest_user;

	public void clickGuestUserLink() {
		click(lnk_guest_user);
		log.info("Guest user payment info link clicked successfully.");
	}

	@FindBy(css = "a[globalize = 'ML_Master_lnkbtn_Settings']")
	private WebElement lnk_account_settings;

	public void clickAccountSettingsLink() {
		click(lnk_account_settings);
		log.info("My account settings link clicked successfully.");
	}

	@FindBy(css = "li#AccountInfo > a")
	private WebElement lnk_account_information;

	public void waitForLnk_account_informationToVisible() {
		waitForElementToBeVisible(lnk_account_information);
		log.info("Account Information Link is now Appeared");
	}

	public void clickAccountInfoLink() {
		click(lnk_account_information);
		log.info("Account information link clicked successfully.");
	}

	@FindBy(css = ".billing.dropicon >a")
	private WebElement lnk_billing;

	public void clickBillingLink() {
		// click(lnk_billing);
		clickWithJSExecutor(lnk_billing);
		log.info("Billing link clicked successfully.");
	}

	@FindBy(css = "a[globalize='ML_BILLING_Navigation_BillDashboard']")
	private WebElement lnk_utility_bill;

	public Boolean isCurrentBillLinkVisible() {
		return lnk_utility_bill.isDisplayed();
	}

	public void clickCurrentBillLink() {
		click(lnk_utility_bill);
		log.info("Current bill link clicked successfully.");
	}

	@FindBy(css = ".recurring_bill.list-group-item a")
	private WebElement lnk_autopay;

	public void clickAutopayLink() {
		click(lnk_autopay);
		log.info("Autopay link clicked successfully.");
	}

	@FindBy(css = ".icon_payment_location.list-group - item a")
	private WebElement lnk_payment_location;

	public void clickPaymentLocationLink() {
		click(lnk_payment_location);
		log.info("Payment location link clicked successfully.");
	}

	@FindBy(css = "a[globalize='ML_BILLING_Navigation_BillingHistory']")
	private WebElement lnk_BillAndpayment_history;

	public void clickBillAndPaymentHistoryLink() {
		// click(lnk_BillAndpayment_history);
		clickWithJSExecutor(lnk_BillAndpayment_history);
		log.info("Bill & Payment History link clicked successfully.");
	}

	@FindBy(css = ".icon_budget_bill.list-group-item a")
	private WebElement lnk_budget_my_bill;

	public void clickBudgetMyBillLink() {
		click(lnk_budget_my_bill);
		log.info("Budget My Bill link clicked successfully.");
	}

	@FindBy(css = "#billQuery a")
	private WebElement lnk_billing_query;

	public void clickBillingQueryLink() {
		click(lnk_billing_query);
		log.info("Billing query link clicked successfully.");
	}

	@FindBy(css = "a[globalize='ML_Billing_lbl_RateAnalysis']")
	private WebElement lnk_rate_analysis;

	public void clickRateAnalysisLink() {
		click(lnk_rate_analysis);
		log.info("Clicked Rate Analysis link successfully.");
	}

	@FindBy(css = ".texttopay_ico a")
	private WebElement lnk_text_to_pay;

	public void clickTextToPayLink() {
		click(lnk_text_to_pay);
		log.info("Clicked Text To Pay link successfully.");
	}

	@FindBy(css = "a[globalize='ML_HeaderMenu_span_Usage']")
	private WebElement lnk_usage;

	public void clickUsageLink() {
		click(lnk_usage);
		log.info("Usage link clicked successfully.");
	}

	@FindBy(css = "#HeaderMenu_PUMenu")
	private WebElement lnk_usage_overview;

	public void clickUsageOverviewLink() {
		click(lnk_usage_overview);
		log.info("Usage Overview link clicked successfully.");
	}

	@FindBy(css = "[globalize = 'ML_HeaderMenu_span_Comparespending']")
	private WebElement lnk_compare_overview;

	public void clickCompareOverviewLink() {
		click(lnk_compare_overview);
		log.info("Compare Overview link clicked successfully.");
	}

	@FindBy(css = ".sidebar_dresponse.list-group - item a")
	private WebElement lnk_demand_response;

	//@FindBy(css = "#comparelink")
	@FindBy(css = "#BillForecast > a")
	private WebElement lnk_compare;

	public void clickCompareLink() {
		click(lnk_compare);
		log.info("Compare link clicked successfully.");
	}

	@FindBy(css = "//li[@class='connect dropicon']//*[text()='Contact Us']/..")
	private WebElement lnk_connect_me;

	public void clickConnectMeLink() {
		click(lnk_connect_me);
		log.info("Connect Me link clicked successfully {}.");
	}

	@FindBy(css = ".service a")
	private WebElement lnk_services;

	public void clickServicesLink() {
		click(lnk_services);
		log.info("Services link clicked successfully {}.");
	}

	// Replace locator #module11 by #module11 .material-icons
	@FindBy(css = "#module11 .material-icons")
	private WebElement lnk_ways_to_save;

	public void clickWaysToSaveLink() {
		click(lnk_ways_to_save);
		log.info("Ways To Save link clicked successfully {}.");
	}

	@FindBy(css = ".my_applications.list-group - item a")
	private WebElement lnk_my_application;

	public void clickLinkMyApplicationLink() {
		click(lnk_my_application);
		log.info("Link My Application link clicked successfully {}.");
	}

	@FindBy(css = ".icon_annual_goal.list-group - item a")
	private WebElement lnk_annual_goal;

	public void clickAnnualGoalLink() {
		click(lnk_annual_goal);
		log.info("Annual Goal link clicked successfully {}.");
	}

	@FindBy(css = ".icon_efficiency_rank.list-group - item a")
	private WebElement lnk_efficiency_rank;

	public void clickEfficiencyRankLink() {
		click(lnk_efficiency_rank);
		log.info("Efficiency link clicked successfully {}.");
	}

	@FindBy(css = ".icon_energy_report.list-group - item a")
	private WebElement lnk_my_home_report;

	public void clickMyHomeReportLink() {
		click(lnk_my_home_report);
		log.info("My Home Report link clicked successfully {}.");
	}

	@FindBy(css = "[globalize = 'ML_AboutMyHome_Header_AboutMyHome'] span")
	private WebElement lnk_about_my_home;

	public void clickAboutMyHomeLink() {
		click(lnk_about_my_home);
		log.info("About My Home link clicked successfully {}.");
	}

	@FindBy(css = ".low_income_icon.list-group - item a")
	private WebElement lnk_energy_assistant;

	public void clickEnergyAssistantLink() {
		click(lnk_energy_assistant);
		log.info("Energy Assistant link clicked successfully {}.");
	}

	@FindBy(css = ".icon_dr_effDemandResponse.list-group - item a")
	private WebElement lnk_efficiency_demand_response;

	public void clickEfficiencyDemandResponseLink() {
		click(lnk_efficiency_demand_response);
		log.info("Efficiency demand response link clicked successfully {}.");
	}

	// Replace Locator .footprint by .footprint span
	@FindBy(css = ".footprint span")
	private WebElement lnk_footprint;

	public void clickFootPrintLink() {
		clickWithJSExecutor(lnk_footprint);
		// click(lnk_footprint);
		log.info("Footprint link clicked successfully {}.");
	}

	@FindBy(css = ".sh a")
	private WebElement lnk_smart_home;

	public void clickSmartHomeLink() {
		click(lnk_smart_home);
		log.info("Smart Home link clicked successfully {}.");
	}

	@FindBy(css = ".ev a")
	private WebElement lnk_electric_vehicle;

	public void clickElectricVehicleLink() {
		click(lnk_electric_vehicle);
		log.info("Electric vehicle link clicked successfully {}.");
	}

	@FindBy(css = ".icon_ev_sidebar.list-group - item a")
	private WebElement lnk_sub_electric_vehicle;

	public void clickSubElectricVehicleLink() {
		click(lnk_sub_electric_vehicle);
		log.info("Sub Electric vehicle link clicked successfully {}.");
	}

	@FindBy(css = ".icon_charging_station.list-group - item a")
	private WebElement lnk_charging_station;

	public void clickChargingStationsLink() {
		click(lnk_charging_station);
		log.info("Charging station link clicked successfully {}.");
	}

	@FindBy(css = ".outages a")
	private WebElement lnk_outages;

	public void clickOutagesLink() {
		click(lnk_outages);
		log.info("Outages link clicked successfully {}.");
	}

	@FindBy(css = ".NotifAlertLink.badgealrt a")
	private WebElement lnk_notification;

	public void clickNotificationLink() {
		click(lnk_notification);
		log.info("Notifications link clicked successfully {}.");
	}

	@FindBy(css = "a[globalize = 'ML_HeaderMenu_span_Service']")
	private WebElement lnk_service_header_menu;

	public void clickServiceHeaderLink() {
		click(lnk_service_header_menu);
		log.info("Services header link clicked successfully {}.");
	}

	@FindBy(css = "a[globalize = 'ML_Service_Overview']")
	private WebElement lnk_service_overview;

	public void clickServiceOverviewLink() {
		click(lnk_service_overview);
		log.info("Services overview link clicked successfully {}.");
	}

	@FindBy(css = "a[globalize='ML_Service_dropdn_txt_MoveIn']")
	private WebElement lnk_move_in_service;

	public void clickMoveInServiceLink() {
		click(lnk_move_in_service);
		log.info("Move In Services link clicked successfully {}.");
	}

	@FindBy(css = "a[globalize='ML_Service_dropdn_txt_MoveOut']")
	private WebElement lnk_move_out_service;

	public void clickMoveOutServiceLink() {
		click(lnk_move_out_service);
		log.info("Move Out Services link clicked successfully {}.");
	}

	@FindBy(css = "a[globalize='ML_Service_dropdn_txt_ServiceTr']")
	private WebElement lnk_service_transfer_service;

	public void clickServiceTransferLink() {
		click(lnk_service_transfer_service);
		log.info("Service Transfer link clicked successfully {}.");
	}

	@FindBy(css = "a[globalize='ML_ServiceTopic_dropdn_txt_Others']")
	private WebElement lnk_other_service;

	public void clickOtherServicesLink() {
		click(lnk_other_service);
		log.info("Service Transfer link clicked successfully {}.");
	}

	@FindBy(css = "a[globalize='ML_ServiceTopic_dropdn_txt_SmartCityRequests']")
	private WebElement lnk_smart_cities_service;

	public void clickSmartCitiesLink() {
		click(lnk_smart_cities_service);
		log.info("Smart cities link clicked successfully {}.");
	}

	@FindBy(css = "#LanguageDrpdwn_preData")
	private WebElement lnk_language_dropdown;

	public void clickLanguageDropDownLink() {
		click(lnk_language_dropdown);
		log.info("Language drop-down link clicked successfully {}.");
	}

	@FindBy(xpath = "//ul[@id='LanguageDrpdwn_dp_language']/li[1]")
	private WebElement lnk_french;

	public void clickFrenchLink() {
		click(lnk_french);
		log.info("French link clicked successfully {}.");
	}

	@FindBy(xpath = "//ul[@id='LanguageDrpdwn_dp_language']/li[2]")
	private WebElement lnk_spanish;

	public void clickSpanishLink() {
		click(lnk_language_dropdown);
		log.info("Language drop-down link clicked successfully {}.");
	}

	@FindBy(css = "#lnkbtnSettings")
	private WebElement lnk_settings;

	public void clickSettingsLink() {
		click(lnk_settings);
		log.info("Settings link clicked successfully {}.");
	}

	@FindBy(css = "a[data - target = '#forget-me-divPopup']")
	private WebElement lnk_delete_my_profile;

	public void clickDeleteMyProfileLink() {
		click(lnk_delete_my_profile);
		log.info("Delete My Profile link clicked successfully {}.");
	}

	@FindBy(xpath = "//li[@class='connect dropicon']//*[text()='Contact Us']/..")
	private WebElement lnk_contact_us;
	
	public void clickContactUsLink() {
		click(lnk_contact_us);
		log.info("Contact Us link clicked successfully {}.");
	}

	@FindBy(css = "ul.group - links > li > a[globalize = 'ML_CONNECTME_Lbl_Terms']")
	private WebElement lnk_terms_and_conditions;

	public void clickTermsAndConditionsLink() {
		click(lnk_terms_and_conditions);
		log.info("Terms and conditions link clicked successfully {}.");
	}

	@FindBy(css = "ul.group - links > li > a[globalize = 'ML_Msg_PrivacyPolicy']")
	private WebElement lnk_privacy_policies;

	public void clickPrivacyPoliciesLink() {
		click(lnk_privacy_policies);
		log.info("Privacy policies link clicked successfully {}.");
	}

	@FindBy(css = "#UpcomBill > a")
	private WebElement lnk_view_your_usages;

	public void clickViewYourUsagesLink() {
		clickWithoutWait(lnk_view_your_usages);
		log.info("View your usages link clicked successfully {}.");
	}

	@FindBy(css = "a[href = 'compare-spending.aspx#Z']")
	private WebElement lnk_compare_click;

	public void clickCompareClickLink() {
		click(lnk_compare_click);
		log.info("Compare click link clicked successfully {}.");
	}

	@FindBy(css = "div.estimatedBox > a")
	private WebElement lnk_learn_more_level_pay;

	public void clickLevelPayLearnMoreLink() {
		click(lnk_learn_more_level_pay);
		log.info("Level Pay learn more link clicked successfully {}.");
	}

	@FindBy(css = "a[href = 'rebates.aspx']")
	private WebElement lnk_learn_more_saving_alert;

	public void clickSavingAlertLearnMoreLink() {
		click(lnk_learn_more_saving_alert);
		log.info("Saving Alert learn more link clicked successfully {}.");
	}

	@FindBy(xpath = "//a[@id='addev']")
	private WebElement lnk_add_remove_ev;

	public void clickAddRemoveLink() {
		click(lnk_add_remove_ev);
		log.info("Add remove EV link clicked successfully {}.");
	}

	@FindBy(css = "a[href *= ' / charging - stations.aspx']")
	private WebElement lnk_view_charging_station;

	public void clickViewChargingLink() {
		click(lnk_language_dropdown);
		log.info("Language drop-down link clicked successfully {}.");
	}

	@FindBy(css = "div.lnk_effi_bottom > a")
	private WebElement lnk_add_remove_devices;

	public void clickAddRemoveDevicesLink() {
		click(lnk_add_remove_devices);
		log.info("Add remove devices link clicked successfully {}.");
	}

	@FindBy(css = "a[globalize='ML_MYACCOUNT_Navigation_Profile']")
	private WebElement myProfileLink;

	public void clickMyProfileLink() {
		click(myProfileLink);
		log.info("My profile page Clicked Successfully");
	}

	public void waitForPageToLoad() {
		pause(1000);
		ExpectedCondition<Boolean> pageLoadCondition = driver -> ((JavascriptExecutor) driver)
				.executeScript("return document.readyState").equals("complete");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		wait.until(pageLoadCondition);
		pause(500);
	}

	@FindBy(css = ".toast-message")
	private WebElement txtToastMessage;

	public String getMandatoryToastMessage() {
		log.info("Fetching the Mandatory toast Message placeholder.");
		// String label = getText(txtToastMessage);
		String label = getTextWithoutCheckingVisibility(lbl_toast_message);
		log.info(" Mandatory toast Message is   {}: " + label);
		return label;
	}

	public String getMandatoryToastMessageByAttribute() {
		log.info("Fetching the Mandatory toast Message placeholder.");
		// String label = getText(txtToastMessage);
		String label = getAttributeInnerHtmlWithoutCheckingVisibility(lbl_toast_message);
		log.info(" Mandatory toast Message is   {}: " + label);
		return label;
	}

	@FindBy(css = ".toast-message")
	private WebElement txtInvalidPwdToastMessage;

	public String getInvalidPwdMandatoryFieldsLabel() {
		log.info("Fetching the invalld pwd Mandatory toast Message placeholder.");
		String label = getText(txtToastMessage);
		log.info(" invalid pwd Mandatory toast Message is   {}: " + label);
		return label;
	}

	public String getSubmittingReqToastMessage() {
		log.info("Fetching the Submitting Reqest toast Message placeholder.");
		String label = getText(txtToastMessage);
		log.info(" Submitting Reqest toast Message is   {}: " + label);
		return label;
	}

	@FindBy(css = ".toast-close-button")
	private WebElement btnCloseToastMessage;

	public void closeToastMandatoryMsg() {
		click(btnCloseToastMessage);
	}

	@FindBy(css = ".toast-message")
	private WebElement txtSuccessToastMessage;

	public String getSuccessfulToastMessage() {
		log.info("Fetching the  toast Message for Successful placeholder.");
		String label = getText(txtSuccessToastMessage);
		log.info("  Successful toast Message is   {}: " + label);
		return label;
	}

	@FindBy(css = "[globalize = 'ML_HeaderMenu_span_Home']")
	private WebElement lnk_about_my_home2;

	public void clickAboutMyHomeLink2() {
		click(lnk_about_my_home2);
		log.info("About My Home link clicked successfully {}.");
	}
	@FindBy(css = "a[globalize='ML_Billing_RecurringBill_Title']")
	private WebElement lnk_Autopayment_Billhistory;

	public void clickBillingAutoPaymentLink() {
		// click(lnk_BillAndpayment_history);
		clickWithJSExecutor(lnk_Autopayment_Billhistory);
		log.info("AutoPayment  link clicked successfully.");
	}
	
	@FindBy(css = "a[globalize='ML_LevelPlay_Heading']")
	private WebElement billinglnk_level_pay;

	public void clickBillingLevelPayLink() {
		click(billinglnk_level_pay);
		log.info("Billing Level pay link clicked successfully.");
	}
	
	@FindBy(css = ".icon_label_pay.list-group - item a")
	private WebElement lnk_level_pay;

	public void clickLevelPayLink() {
		click(lnk_level_pay);
		log.info("Level pay link clicked successfully.");
	}
	

}

package demo.pageobjects;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import sew.ai.driver.Page;
import sew.ai.pageObjects.scp.HomePage;

public class NotificationPreferencePage extends HomePage {
	private static final Logger log = LogManager.getLogger(NotificationPreferencePage.class);

	public NotificationPreferencePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".MuiGrid-root.pageheading-box #PageTitle")
	private WebElement lblNotifications;

	public boolean isLblNotificationsVisible() {
		log.info("Checking the visibility of Notification leval on the page.");
		log.info("Label Notification visibility status : " + isElementVisible(lblNotifications));
		return isElementVisible(lblNotifications);
	}

	public String getLevelNotification() {
		log.info("Fetching the Notification  header.");
		String label = getText(lblNotifications);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	@FindBy(css = ".nitiF_acc_ico.list-group-item a")
	private WebElement lnkNotificationPrefMenuApp;

	@FindBy(xpath = "//div[@class='col-md-12 col-12 noti_contentcommon']/p/text()[1]")
	private WebElement lblNotificationsOne;

	public String getLevelNotificationone() {
		log.info("Fetching the Notification  header First Line.");
		String label = getText(lblNotificationsOne);
		log.info("Notification page header First Line is {}: " + label);
		return label;
	}

	@FindBy(xpath = "//div[@class='col-md-12 col-12 noti_contentcommon']/p/text()[2]")
	private WebElement lblNotificationsTwo;

	public String getLevelNotificationTwo() {
		log.info("Fetching the Notification  header Second Line.");
		String label = getText(lblNotificationsTwo);
		log.info("Notification page header Second Line is {}: " + label);
		return label;
	}

	@FindBy(xpath = "//*[@id='ulNotificationPref']/li[1]")
	private WebElement lblNotificationsThree;
	@FindBy(xpath = "//*[@id='ulNotificationPref']/li[2]")
	private WebElement lblNotificationsFour;
	@FindBy(xpath = "//*[@id='ulNotificationPref']/li[3]")
	private WebElement lblNotificationsFive;
	@FindBy(xpath = "//*[@id='ulNotificationPref']/li[4]")
	private WebElement lblNotificationsSix;
	@FindBy(xpath = "//*[@id='ulNotificationPref']/li[5]")
	private WebElement lblNotificationsSeven;
	@FindBy(css = "h1 > span:nth-child(2)")
	private WebElement lblPageHeader;
	@FindBy(css = "h1 > span:nth-child(2)")
	private WebElement lblTextHeader;

	@FindBy(css = ".MuiGrid-root.pageheading-box #PageTitle")
	private WebElement lblNotification;

	@FindBy(xpath = "//*[@class='selector-text']/span[contains(text(),'Outage')]")
	private WebElement lblOutage;
	@FindBy(xpath = "//*[@class='selector-text']/span[contains(text(),'Billing')]")
	private WebElement lblBilling;
	@FindBy(xpath = "//*[@class='selector-text']/span[contains(text(),'Pay as you go')]")
	private WebElement lblPayAsYouGo;
	@FindBy(xpath = "//*[@class='selector-text']/span[contains(text(),'Budget')]")
	private WebElement lblBudget;
	@FindBy(xpath = "//*[@class='selector-text']/span[contains(text(),'Demand Response')]")
	private WebElement lblDemandResponse;
	@FindBy(xpath = "//*[@class='selector-text']/span[contains(text(),'Services')]")
	private WebElement lblService;
	@FindBy(xpath = "//*[@class='selector-text']/span[contains(text(),'Connect Me')]")
	private WebElement lblConnect;
	@FindBy(xpath = "//*[@id='leakalert']//div/span[contains(text(),'Leak Alert')]")
	private WebElement lblLeakAlert;
	@FindBy(xpath = "//*[@id='Usage']//div/span[contains(text(),'High Usage Alert')]")
	private WebElement lblHighUsageAlert;
	@FindBy(xpath = "//*[@class='budgetlimit']//div[contains(text(),'Budget Limit')]")
	private WebElement lblBudgetLimit;
	@FindBy(xpath = "//*//div/span[contains(text(),'Quiet Hours')]")
	private WebElement lblChkBoxQuietHours;
	@FindBy(css = ".setting_save_box #BtnNotificationPrefrence")
	private WebElement btnSave;
	@FindBy(css = ".txtDiv [globalize='ML_Notification_PrefrenceTooltip']")
	private WebElement lblChkBoxTextNotifications;
	@FindBy(css = ".divEmail [globalize='ML_Notification_PrefrenceTooltip']")
	private WebElement lblChkBoxEmailNotifications;
	@FindBy(css = ".divIVR [globalize='ML_Notification_PrefrenceTooltip']")
	private WebElement lblChkBoxVoiceCallNotifications;
	@FindBy(css = "#divagree li span")
	private WebElement lblTcpaPopupLeftColumnModule;
	@FindBy(css = "#divagree li small")
	private WebElement lblTcpaPopupRightColumnModule;
	@FindBy(css = "#enotificationagree")
	private WebElement lblTcpaPopupBodyMsg;

	@FindBy(xpath = "//div[contains(@class, 'w2ui-confirm-msg')]")
	private WebElement lblTcpaPopupConfirmationMsg;

	@FindBy(css = ".accordion-heading a")
	private WebElement linkNotificationTypes;
	@FindBy(css = "#BtnOutageNotificationPrefrence")
	private WebElement btnSaveOutageNotification;

	public void saveOutageNotificationBtn() {
		clickWithJSExecutor(btnSaveOutageNotification);
		log.info("SaveOutageNotification Button clicked successfully.");
	}

	@FindBy(css = ".icon_notifications")
	private WebElement imgNotification;
	@FindBy(css = ".icon_notif-outage")
	private WebElement imgOutage;
	@FindBy(css = ".icon_billing")
	private WebElement imgBilling;
	@FindBy(css = ".icon_thermolist")
	private WebElement imgBudget;

	@FindBy(css = ".icon_rate-analysis")
	private WebElement imgDemandResponse;
	@FindBy(css = ".icon_services")
	private WebElement imgService;
	@FindBy(css = ".icon_connectme")
	private WebElement imgConnect;
	@FindBy(css = ".icon_notif-leakalert")
	private WebElement imgLeakAlert;
	@FindBy(css = ".icon-ring-bell_high_usage")
	private WebElement imgHighUsageAlert;

	@FindBy(css = ".txtDiv .bootstraptooltip")
	private WebElement toolTipText;
	@FindBy(css = ".divEmail .bootstraptooltip")
	private WebElement toolTipEmail;
	@FindBy(css = ".divIVR .bootstraptooltip")
	private WebElement toolTipVoiceCall;

	@FindBy(css = "#chkTextAll")
	private WebElement chkBoxTextNotifications;
	@FindBy(css = ".txtDiv .mdl-ripple--center")
	private WebElement chkTextNotifications;
	@FindBy(css = "#chkOutageText0")
	private WebElement chkBoxTextOutage;
	@FindBy(css = "#outage .txtDiv .mdl-ripple--center")
	private WebElement chkTextOutage;
	@FindBy(css = "#chkBillingText")
	private WebElement chkBoxTextPayAsYouGo;
	@FindBy(css = "#billing .txtDiv .mdl-ripple--center")
	private WebElement chkTextPayAsYouGo;
	@FindBy(css = "#chkBudgetText")
	private WebElement chkBoxTextBudget;
	@FindBy(css = "#Budget  .txtDiv .mdl-ripple--center")
	private WebElement chkTextBudget;
	@FindBy(css = "#chkDRText")
	private WebElement chkBoxTextDemandResponse;
	@FindBy(css = "#divDR .txtDiv .mdl-ripple--center")
	private WebElement chkTextDemandResponse;
	@FindBy(css = "#chkConnectText")
	private WebElement chkBoxTextConnect;
	@FindBy(css = "#connectme .txtDiv .mdl-ripple--center")
	private WebElement chkTextConnect;
	@FindBy(css = "#chkServiceText")
	private WebElement chkBoxTextService;
	@FindBy(css = "#service .txtDiv .mdl-ripple--center")
	private WebElement chkTextService;
	@FindBy(css = "#chkLeakAlertText")
	private WebElement chkBoxTextLeakAlert;
	@FindBy(css = "#leakalert .txtDiv .mdl-ripple--center")
	private WebElement chkTextLeakAlert;
	@FindBy(css = "#chkUsageText")
	private WebElement chkBoxTextHighUsageAlert;
	@FindBy(css = "#Usage .txtDiv .mdl-ripple--center")
	private WebElement chkTextHighUsageAlert;

	@FindBy(css = "#chkEmailAll")
	private WebElement chkBoxEmailNotifications;
	@FindBy(css = ".divEmail .mdl-ripple--center")
	private WebElement chkEmailNotifications;
	@FindBy(css = "#chkOutageEmail")
	private WebElement chkBoxEmailOutage;
	@FindBy(css = "#outage .divEmail .mdl-ripple--center")
	private WebElement chkEmailOutage;
	@FindBy(css = "#chkBillingEmail")
	private WebElement chkBoxEmailPayAsYouGo;
	@FindBy(css = "#billing .divEmail .mdl-ripple--center")
	private WebElement chkEmailPayAsYouGo;
	@FindBy(css = "#chkBudgetEmail")
	private WebElement chkBoxEmailBudget;
	@FindBy(css = "#Budget  .divEmail .mdl-ripple--center")
	private WebElement chkEmailBudget;
	@FindBy(css = "#chkDREmail")
	private WebElement chkBoxEmailDemandResponse;
	@FindBy(css = "#divDR .divEmail .mdl-ripple--center")
	private WebElement chkEmailDemandResponse;
	@FindBy(css = "#chkConnectEmail")
	private WebElement chkBoxEmailConnect;
	@FindBy(css = "#connectme .divEmail .mdl-ripple--center")
	private WebElement chkEmailConnect;
	@FindBy(css = "#chkServiceEmail")
	private WebElement chkBoxEmailService;
	@FindBy(css = "#service .divEmail .mdl-ripple--center")
	private WebElement chkEmailService;
	@FindBy(css = "#chkLeakAlertEmail")
	private WebElement chkBoxEmailLeakAlert;
	@FindBy(css = "#leakalert .divEmail .mdl-ripple--center")
	private WebElement chkEmailLeakAlert;
	@FindBy(css = "#chkUsageEmail")
	private WebElement chkBoxEmailHighUsageAlert;
	@FindBy(css = "#Usage .divEmail .mdl-ripple--center")
	private WebElement chkEmailHighUsageAlert;
	@FindBy(css = "chkBillingEmail")
	private WebElement chkBoxEmailBilling;

	@FindBy(css = "#chkIvrAll")
	private WebElement chkBoxIvrNotifications;
	@FindBy(css = ".divIVR .mdl-ripple--center")
	private WebElement chkIvrNotifications;
	@FindBy(css = "#chkOutageIvr")
	private WebElement chkBoxIvrOutage;
	@FindBy(css = "#outage .divIVR .mdl-ripple--center")
	private WebElement chkIvrOutage;
	@FindBy(css = "#chkBillingIvr")
	private WebElement chkBoxIvrPayAsYouGo;
	@FindBy(css = "#billing .divIVR .mdl-ripple--center")
	private WebElement chkIvrPayAsYouGo;
	@FindBy(css = "#billing .divIVR .mdl-ripple--center")
	private WebElement chkBoxIvrBilling;
	@FindBy(css = "#chkBudgetIvr")
	private WebElement chkBoxIvrBudget;
	@FindBy(css = "#Budget  .divIVR .mdl-ripple--center")
	private WebElement chkIvrBudget;
	@FindBy(css = "#chkDRIvr")
	private WebElement chkBoxIvrDemandResponse;
	@FindBy(css = "#divDR .divIVR .mdl-ripple--center")
	private WebElement chkIvrDemandResponse;
	@FindBy(css = "#chkConnectIVR")
	private WebElement chkBoxIvrConnect;
	@FindBy(css = "#connectme .divIVR .mdl-ripple--center")
	private WebElement chkIvrConnect;
	@FindBy(css = "#chkServiceIVR")
	private WebElement chkBoxIvrService;
	@FindBy(css = "#service .divIVR .mdl-ripple--center")
	private WebElement chkIvrService;
	@FindBy(css = "#chkLeakAlertIVR")
	private WebElement chkBoxIvrLeakAlert;
	@FindBy(css = "#leakalert .divIVR .mdl-ripple--center")
	private WebElement chkIvrLeakAlert;
	@FindBy(css = "#chkUsageIVR")
	private WebElement chkBoxIvrHighUsageAlert;
	@FindBy(css = "#Usage .divIVR .mdl-ripple--center")
	private WebElement chkIvrHighUsageAlert;
	@FindBy(css = "#ContentPlaceHolder1_ContentPlaceHolderBody_chkPushAll")
	private WebElement chkBoxPushNotifications;
	@FindBy(css = "#chkOutagePush")
	private WebElement chkBoxPushOutage;
	@FindBy(css = "#chkBillingPush")
	private WebElement chkBoxPushPayAsYouGo;
	@FindBy(css = "#chkConnectPush")
	private WebElement chkBoxPushConnect;
	@FindBy(css = "#chkServicePush")
	private WebElement chkBoxPushService;
	@FindBy(css = "#chkLeakAlertPush")
	private WebElement chkBoxPushLeakAlert;
	@FindBy(css = "#chkUsagePush")
	private WebElement chkBoxPushHighUsageAlert;
	@FindBy(css = "#chkBudgetPush")
	private WebElement chkBoxPushBudgetAlert;

	@FindBy(css = "#TxtOutageEmail")
	private WebElement txtBoxOutageEmail;
	@FindBy(css = "#TxtOutageText")
	private WebElement txtBoxOutageText;
	@FindBy(css = "#TxtOutageIvr")
	private WebElement txtBoxOutageIvr;
	@FindBy(css = "#TxtBillingEmail")
	private WebElement txtBoxBillingEmail;
	@FindBy(css = "#TxtBillingText")
	private WebElement txtBoxBillingText;
	@FindBy(css = "#TxtBillingIvr")
	private WebElement txtBoxBillingIvr;
	@FindBy(css = "#TxtBudgetEmail")
	private WebElement txtBoxBudgetEmail;
	@FindBy(css = "#TxtBudgetText")
	private WebElement txtBoxBudgetText;
	@FindBy(css = "#TxtBudgetIvr")
	private WebElement txtBoxBudgetIvr;
	@FindBy(css = "#TxtDREmail")
	private WebElement txtBoxDemandResponseEmail;
	@FindBy(css = "#TxtDRText")
	private WebElement txtBoxDemandResponseText;
	@FindBy(css = "#TxtDRIvr")
	private WebElement txtBoxDemandResponseIvr;
	@FindBy(css = "#TxtConnectEmail")
	private WebElement txtBoxConnectEmail;
	@FindBy(css = "#TxtConnectText")
	private WebElement txtBoxConnectText;
	@FindBy(css = "#TxtConnectIVR")
	private WebElement txtBoxConnectIvr;
	@FindBy(css = "#TxtServiceEmail")
	private WebElement txtBoxServiceEmail;
	@FindBy(css = "#TxtServiceText")
	private WebElement txtBoxServiceText;
	@FindBy(css = "#TxtServiceIVR")
	private WebElement txtBoxServiceIvr;
	@FindBy(css = "#TxtLeakAlertEmail")
	private WebElement txtBoxLeakAlertEmail;
	@FindBy(css = "#TxtLeakAlertText")
	private WebElement txtBoxLeakAlertText;
	@FindBy(css = "#TxtLeakAlertIVR")
	private WebElement txtBoxLeakAlertIvr;
	@FindBy(css = "#TxtUsageEmail")
	private WebElement txtBoxHighUsageAlertEmail;
	@FindBy(css = "#TxtUsageText")
	private WebElement txtBoxHighUsageAlertText;
	@FindBy(css = "#TxtUsageIVR")
	private WebElement txtBoxHighUsageAlertIvr;
	@FindBy(css = "#txtAmount")
	private WebElement txtBoxBudgetLimit;
	@FindBy(css = "#TxtOutageText0")
	private WebElement txtFirstMobilenumber;

	public void populateFirstMobilenumber(String firstMobilenumber) {
		log.info("Populating Mobilenumber {} :" + firstMobilenumber);
		sendKeys(txtFirstMobilenumber, firstMobilenumber);
		log.info("First Mobilenumber populated successfully.");
	}

	@FindBy(css = "#TxtBudgetText0")
	private WebElement txtBugetFirstMobilenumber;

	public void populateBugetFirstMobilenumber(String bugetFirstMobilenumber) {
		log.info("Populating password {} :" + bugetFirstMobilenumber);
		sendKeys(txtBugetFirstMobilenumber, bugetFirstMobilenumber);
		log.info("bugetFirstMobilenumber populated successfully.");
	}

	@FindBy(css = "#TxtBudgetText1")
	private WebElement txtBugetSecondMobilenumber;

	public void populateBugetSecondMobilenumber(String bugetSecondMobilenumber) {
		log.info("Populating bugetSecondMobilenumber {} :" + bugetSecondMobilenumber);
		sendKeys(txtBugetSecondMobilenumber, bugetSecondMobilenumber);
		log.info("txtBugetSecondMobilenumber populated successfully.");
	}

	@FindBy(css = "#chkEnablequitehours")
	private WebElement chkBoxQuietHours;
	@FindBy(css = "label[for='chkEnablequitehours'] span.mdl-ripple--center")
	private WebElement chkQuietHours;
	@FindBy(css = "#ddlFrmAmpm")
	private WebElement lstQuietHoursFromAmPm;
	@FindBy(css = "#ddlToAmpm")
	private WebElement lstQuietHoursToAmPm;
	@FindBy(css = "#ddlFrmHours")
	private WebElement lstQuietHoursFrom;
	@FindBy(css = "#ddlToHours")
	private WebElement lstQuietHoursTo;
	@FindBy(css = "select#DropDownList1")
	private WebElement lstQuietHoursFromDisabled;
	@FindBy(css = "select#DropDownList4")
	private WebElement lstQuietHoursToDisabled;
	@FindBy(css = "button.toast-close-button")
	private WebElement btnCloseMsgOnHeader;

	public void clickbtnCloseMsgOnHeader() {
		scrollToElement(btnCloseMsgOnHeader);
		clickWithJSExecutor(btnCloseMsgOnHeader);
		log.info("btnCloseMsgOnHeader clicked successfully.");
	}

	@FindBy(css = ".in li")
	private WebElement lblAcceptNotificationTermsPopup;
	@FindBy(css = "tbody tr td.txtDiv .errorbox")
	private WebElement txtNotificationCommunicationsError;
	@FindBy(css = "#toast-container")
	private WebElement lblBudgetLimitErrorPanel;
	@FindBy(css = ".w2ui-tag-body")
	private WebElement lblErrorMessage;
	@FindBy(css = ".toast-message")
	private WebElement lblToastMessage;

	public String getLblToastMessage() {
		log.info("Fetching the Notification  header.");
		String label = getText(lblToastMessage);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	@FindBy(css = ".disclaimer_footer .cls_disclaimer")
	private WebElement lblDisclaimer;
	@FindBy(css = "#agree.submit-button")
	private WebElement btnIagreeOnAcptTerms;
	@FindBy(css = "#disagree.cancel-button")
	private WebElement btnIdisagreeOnAcptTerms;
	@FindBy(css = "#enotificationtitle")
	private WebElement lblAcceptNotificationTerm;
	@FindBy(css = ".w2ui-msg-body")
	private WebElement popUpDisagreeConfirmation;
	@FindBy(css = "#Yes")
	private WebElement btnYesDisagreeConfirmationPopUp;
	@FindBy(css = "#No")
	private WebElement btnNoDisagreeConfirmationPopUp;
	@FindBy(css = ".toast-message")
	private WebElement lblMsgSuccessfulConfigSetting;
	@FindBy(css = ".my_account_table tr td:first-child .selector-text span:not(.img_align_1):not([class^='head_icon_flat icon_'])")
	private WebElement lstNotificationCommunicationsLabels;
	@FindBy(css = "tbody tr td.txtDiv [type='checkbox']")
	private WebElement lstNotificationCommunicationsCheckboxes;
	@FindBy(css = "div.modal-body #divagree")
	private WebElement lblAgreeTextAcceptNotificationOptsOutPopU;
	@FindBy(css = "div.w2ui-centered")
	private WebElement lblDisagreeTextDisagreeConfirmationPopUp;

	@FindBy(css = "div#ContentPlaceHolder1_ContentPlaceHolderBody_OutageNotification h5")
	private WebElement lblOutageText;

	public boolean isLblOutageVisible() {
		log.info("Checking the visibility of Outage leval on the page.");
		log.info("Outage text visibility status : " + isElementVisible(lblOutageText));
		return isElementVisible(lblOutageText);
	}

	public String getLevelOutage() {
		log.info("Fetching the Outage  header.");
		String label = getText(lblOutageText);
		log.info("Outage page header is {}: " + label);
		return label;
	}

	public void clickLevelOutage() {
		scrollToElement(lblOutageText);
		clickWithJSExecutor(lblOutageText);
		log.info("Outage Text label clicked successfully.");

	}

	@FindBy(css = "span#ContentPlaceHolder1_ContentPlaceHolderBody_SpanOutageStatus")
	private WebElement IconOutageStatus;

	public boolean isIconOutageStatusVisible() {
		log.info("Checking the visibility of Icon Outage Status on the page.");
		log.info("Outage text visibility status : " + isElementVisible(IconOutageStatus));
		return isElementVisible(IconOutageStatus);
	}

	public String getLblIconOutageStatus() {
		return getText(IconOutageStatus);
	}

	@FindBy(css = ".binding_secOutage .input_Sec_noti:nth-of-type(1) [type='checkbox']")
	private WebElement chkBoxOutage;

	public boolean isChkBoxOutageVisible() {
		log.info("Checking the visibility of Check Box Outage leval on the page.");
		log.info("Check Box Outage  visibility status : " + isElementVisible(chkBoxOutage));
		return isElementVisible(chkBoxOutage);
	}

	public boolean verifyChkBoxOutage() {
		log.info("Checking the visibility of Check Box Outage leval on the page.");
		log.info("Check Box Outage  visibility status : " + isElementVisible(chkBoxOutage));
		return verifyCheck(chkBoxOutage);
	}

	public void unCheckChkBoxOutage() {

		unCheck(chkBoxOutage);
		log.info("uncheck Outage is successfully.");
	}

	public void checkChkBoxOutage() {

		check(chkBoxOutage);
		log.info("check Outage is successfully.");
	}

	public boolean isSelectedcheckChkBoxOutage() {
		log.info("verify check Outage is checked.");
		return verifyCheck(chkBoxOutage);

	}

	@FindBy(css = "#ddlOutageOption0")
	private WebElement lstOutageChannel;

	public boolean islstOutageChannelVisible() {
		log.info("Checking the visibility of First Outage Channel on the page.");
		log.info("Check Box Outage  visibility status : " + isElementVisible(lstOutageChannel));
		return isElementVisible(lstOutageChannel);
	}
	
	@FindBy(css = "#ddlOutageOption1")
	private WebElement secondOutageChannel;

	public void select2ndOutageChannel(String option) {
		selectByVisibleText(secondOutageChannel, option);
		log.info("secondOutageChannel populated successfully.");
	}


	public void selectlstOutageChannel(String option) {
		selectByVisibleText(lstOutageChannel, option);
		log.info("lstOutageChannel populated successfully.");
	}

	@FindBy(css = "input#TxtOutageText0")
	private WebElement lstOutageOptionsTxt;

	public void selectlstOutageOptionsTxt(String option) {
		selectByVisibleText(lstOutageOptionsTxt, option);
		log.info("lstOutageOptionsTxt populated successfully.");
	}

	public boolean islstOutageOptionsTxtVisible() {
		log.info("Checking the visibility of First Outage Channel on the page.");
		log.info("Check Box Outage  visibility status : " + isElementVisible(lstOutageOptionsTxt));
		return isElementVisible(lstOutageOptionsTxt);
	}

	@FindBy(css = "a#add_Outage > .material-icons")
	private WebElement iconOutageAdd;

	public boolean isIconOutageAddVisible() {
		log.info("Checking the visibility of Icon Outage Add on the page.");
		log.info("Check Box Outage  visibility status : " + isElementVisible(iconOutageAdd));
		return isElementVisible(iconOutageAdd);
	}

	public void clickIconOutageAdd() {
		click(iconOutageAdd);
		log.info("iconOutageAdd clicked successfully.");

	}

	@FindBy(css = "input#outageCancel")
	private WebElement btnOutageCancel;

	public boolean isBtnOutageCancelVisible() {
		log.info("Checking the visibility of outage cancel on the page.");
		log.info("Button Outage cancel  visibility status : " + isElementVisible(btnOutageCancel));
		return isElementVisible(btnOutageCancel);
	}

	@FindBy(css = "input#BtnOutageNotificationPrefrence")
	private WebElement btnOutageSave;

	public boolean isBtnOutageSaveVisible() {
		log.info("Checking the visibility of button Outage Save on the page.");
		log.info("Button Outage save  visibility status : " + isElementVisible(btnOutageSave));
		return isElementVisible(btnOutageSave);
	}

	public void btnOutageSaveBtn() {
		click(btnOutageSave);
		log.info("btnOutageSave Button clicked successfully.");
	}

	@FindBy(css = "#ddlOutageOption1")
	private WebElement lstOutageOptions2Row;

	public boolean islstOutageOptions2RowVisible() {
		log.info("Checking the visibility of first outage 2 row on the page.");
		log.info("first outage 2 row  visibility status : " + isElementVisible(lstOutageOptions2Row));
		return isElementVisible(lstOutageOptions2Row);
	}

	public void selectlstOutageOptions2Row(String option) {
		selectByVisibleText(lstOutageOptions2Row, option);
		log.info("lstOutageOptions2Row populated successfully.");
	}

	@FindBy(css = "input#TxtOutageText1")
	private WebElement lblOutageOptionText2Row;

	public String getlblOutageOptionText2Row() {
		log.info("Fetching the Billing  header.");
		scrollToElement(lblOutageOptionText2Row);
		String label = getText(lblOutageOptionText2Row);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	@FindBy(css = ".input_Sec_noti:nth-of-type(2) [type='checkbox']")
	private WebElement chkBoxOutage2Row;

	public void unChkBoxOutage2Row() {

		unCheck(chkBoxOutage2Row);
		log.info("chkBoxOutage2Row is successfully.");
	}

	public void checkchkBoxOutage2Row() {

		check(chkBoxOutage2Row);
		log.info("check Outage is successfully.");
	}

	@FindBy(css = "a#remove_Outage1 > .material-icons")
	private WebElement iconRemoveCircle1;
	@FindBys(@FindBy(xpath = "//a[contains(@id,'remove_Outage')]"))
	private List<WebElement> lstOutageRemoveIcons;

	public List<WebElement> getlstOutageRemoveIcons() {
		log.info("Fetching Outage remove Icons.");
		return lstOutageRemoveIcons;
	}

	@FindBy(css = "div#ContentPlaceHolder1_ContentPlaceHolderBody_BilingNotification h5")
	private WebElement lblBillingText;

	public boolean isLblBillingTextVisible() {
		log.info("Checking the visibility of Billing text on the page.");
		log.info("Billing text visibility status : " + isElementVisible(lblBillingText));
		return isElementVisible(lblBillingText);
	}

	public String getLevelBilling() {
		log.info("Fetching the Billing  header.");
		scrollToElement(lblBillingText);
		String label = getText(lblBillingText);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	public void linkLblBillingText() {
		scrollToElement(lblBillingText);
		click(lblBillingText);
		log.info("Label Billing Text Button clicked successfully.");
	}
	
	public void linkLblContactUsText() {
		scrollToElement(lblConnectMe);
		click(lblConnectMe);
		log.info("Label Billing Text Button clicked successfully.");
	}
	
	

	@FindBy(css = "span#ContentPlaceHolder1_ContentPlaceHolderBody_SpanbillingStatus")
	private WebElement IconBillingStatus;

	public boolean isIconBillingStatusVisible() {
		log.info("Checking the visibility of Icon Billing Status on the page.");
		log.info("Icon Billing Status visibility status : " + isElementVisible(IconBillingStatus));
		return isElementVisible(IconBillingStatus);
	}

	public String getIconBillingStatus() {
		log.info("Fetching the login page header.");
		String label = getText(IconBillingStatus);
		log.info("Icon Billing status is {}: " + label);
		return label;
	}

	@FindBy(css = ".binding_secBilling .input_Sec_noti:nth-of-type(1) [type='checkbox']")
	private WebElement chkBoxBilling;

	public boolean isChkBoxBillingVisible() {
		log.info("Checking the visibility of Billing checkbox on the page.");
		log.info("Billing checkbox visibility status : " + isElementVisible(chkBoxBilling));
		return isElementVisible(chkBoxBilling);
	}

	public void unCheckChkBoxBilling() {
		unCheck(chkBoxBilling);
		log.info("uncheck Outage is successfully.");
	}

	public void checkBoxBilling() {
		check(chkBoxBilling);
		log.info("uncheck Outage is successfully.");
	}

	public boolean verifycheckkBoxBilling() {
		log.info("Checking the visibility of Check Box Outage leval on the page.");
		log.info("Check Box Outage  visibility status : " + isElementVisible(chkBoxBilling));
		return verifyCheck(chkBoxBilling);
	}

	@FindBy(css = "#ddlBillingOption0")
	private WebElement lstBillingChannel;

	public boolean islstBillingChannelVisible() {
		log.info("Checking the visibility of lst Billing Channel on the page.");
		log.info("lst Billing Channel visibility status : " + isElementVisible(lstBillingChannel));
		return isElementVisible(lstBillingChannel);
	}

	public void selectlstBillingChannel(String billingChannel) {
		selectByVisibleText(lstBillingChannel, billingChannel);
		log.info("lstBillingChannel populated successfully.");
	}

	@FindBy(css = "input#TxtBillingText0")
	private WebElement lstBillingOptionsTxt;

	public boolean islstBillingOptionsTxtVisible() {
		log.info("Checking the visibility of lst Billing Options Text on the page.");
		log.info("lst Billing Options Text visibility status : " + isElementVisible(lstBillingOptionsTxt));
		return isElementVisible(lstBillingOptionsTxt);
	}

	public String getlstBillingOptionsTxt() {
		log.info("Fetching the Billing  header.");
		scrollToElement(lstBillingOptionsTxt);
		String label = getText(lstBillingOptionsTxt);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	public void clearlstBillingOptionsTxt() {
		log.info("clear the Billingoptions text.");
		scrollToElement(lstBillingOptionsTxt);
		clear(lstBillingOptionsTxt);

	}

	public void populatelstBillingOptionsTxt(String billingoptionstext) {
		log.info("Populating password {} :" + billingoptionstext);
		sendKeys(lstBillingOptionsTxt, billingoptionstext);
		log.info("billingoptionstext populated successfully.");
	}
	
	@FindBy(css = "input#TxtBillingText1")
	private WebElement SecondBillingOptionsTxt;

	public void populate2ndBillingOptionsTxt(String billingoptionstext) {
		log.info("Populating password {} :" + billingoptionstext);
		sendKeys(SecondBillingOptionsTxt, billingoptionstext);
		log.info("2ndBillingOptionsTxt populated successfully.");
	}

	@FindBy(css = "div#ContentPlaceHolder1_ContentPlaceHolderBody_BilingNotification h5")
	private WebElement lnkBillingNotification;

	public boolean islnkBillingNotificationVisible() {
		log.info("Checking the visibility of link Billing Notification on the page.");
		log.info("link Billing Notification visibility status : " + isElementVisible(lnkBillingNotification));
		return isElementVisible(lnkBillingNotification);
	}

	@FindBy(css = "div#ContentPlaceHolder1_ContentPlaceHolderBody_OutageNotification h5")
	private WebElement lnkOutageNotification;

	public boolean islnkOutageNotificationVisible() {
		log.info("Checking the visibility of link Outage Notification on the page.");
		log.info("link Billing Notification visibility status : " + isElementVisible(lnkOutageNotification));
		return isElementVisible(lnkOutageNotification);
	}

	@FindBy(css = "a#add_Billing > .material-icons")
	private WebElement iconBillingAdd;

	public boolean isIconBillingAddVisible() {
		log.info("Checking the visibility of Icon Billing Add on the page.");
		log.info("Icon Billing Add visibility status : " + isElementVisible(iconBillingAdd));
		return isElementVisible(iconBillingAdd);
	}

	@FindBy(css = "#billingCancel")
	private WebElement btnBillingCancel;

	public boolean isBtnBillingCancelVisible() {
		log.info("Checking the visibility of Cancel Billing button on the page.");
		log.info("Cancel billing button visibility status : " + isElementVisible(btnBillingCancel));
		return isElementVisible(btnBillingCancel);
	}

	@FindBy(css = "#BtnBillingNotificationPrefrence")
	private WebElement btnBillingSave;

	public boolean isBtnBillingSaveVisible() {
		log.info("Checking the visibility of Billing Save button on the page.");
		log.info("Billing Save button visibility status : " + isElementVisible(btnBillingSave));
		return isElementVisible(btnBillingSave);
	}

	public void billingSaveBtn() {
		click(btnBillingSave);
		log.info("BillingSave Button clicked successfully.");
	}

	@FindBy(css = "a#remove_Billing1")
	private WebElement btnBillingCloseIcon;

	public boolean isBtnBillingCloseIconVisible() {
		log.info("Checking the visibility of Billing Close Icon button on the page.");
		log.info("Billing Close Icon button visibility status : " + isElementVisible(btnBillingCloseIcon));
		return isElementVisible(btnBillingCloseIcon);
	}

	@FindBys(@FindBy(xpath = "//a[contains(@id,'remove_Billing')]"))
	private List<WebElement> lstBillingClose;

	public List<WebElement> listBillingClose() {
		return lstBillingClose;
	}

	@FindBy(css = "div#ContentPlaceHolder1_ContentPlaceHolderBody_BudgetNotification h5")
	private WebElement lblBudgett;

	public boolean isLblBudgettVisible() {
		log.info("Checking the visibility of Budget leval on the page.");
		log.info("Budget visibility status : " + isElementVisible(lblBudgett));
		return isElementVisible(lblBudgett);
	}

	@FindBy(css = "div#ContentPlaceHolder1_ContentPlaceHolderBody_BudgetNotification  a h5")
	private WebElement lblBudgetText;

	public boolean isLblBudgetTextVisible() {
		log.info("Checking the visibility of Notification leval on the page.");
		log.info("Budget text label visibility status : " + isElementVisible(lblBudgetText));
		return isElementVisible(lblBudgetText);
	}

	public String getLevelBudget() {
		log.info("Fetching the Budget  header.");
		String label = getText(lblBudgetText);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	public void linklevelBudget() {
		scrollToElement(lblBudgetText);
		clickWithJSExecutor(lblBudgetText);
		log.info("Label Budget Text clicked successfully.");
	}

	@FindBy(css = "span#ContentPlaceHolder1_ContentPlaceHolderBody_SpanBudgetStatus")
	private WebElement IconBudgetStatus;

	public boolean isIconBudgetStatusVisible() {
		log.info("Checking the visibility of Icon Budget Status leval on the page.");
		log.info("Icon Budget Status visibility status : " + isElementVisible(IconBudgetStatus));
		return isElementVisible(IconBudgetStatus);
	}

	public String getIconBudgetStatus() {
		scrollToElement(IconBudgetStatus);
		log.info("Fetching the Icon Budget Status.");
		return getText(IconBudgetStatus);
	}

	@FindBy(css = ".binding_secBudget .input_Sec_noti:nth-of-type(1) [type='checkbox']")
	private WebElement chkBoxBudget;

	public boolean isChkBoxBudgetVisible() {
		log.info("Checking the visibility of Check Box Budget on the page.");
		log.info("Check Box Budget visibility status : " + isElementVisible(chkBoxBudget));
		return isElementVisible(chkBoxBudget);
	}

	public void checkBoxBudget() {
		check(chkBoxBudget);
		log.info("check  box budget is checked successfully.");

	}

	public void unCheckBoxBudget() {
		unCheck(chkBoxBudget);
		log.info("check  box budget is un checked successfully.");

	}
	
	@FindBy(css = ".Billingddl.Billingddl0.select_effect")
	private WebElement lstBillingOptions;
	
	public void selectBillingOptions(String option) {
		scrollToElement(lstBillingOptions);
		selectByVisibleText(lstBillingOptions, option);
		log.info("lstBillingOptions selected successfully.");
	}
	
	@FindBy(css = ".Budgetddl.Budgetddl0.select_effect")
	private WebElement lstBudgetOptions;

	public boolean islstBudgetOptionsVisible() {
		log.info("Checking the visibility of lst Budget Options on the page.");
		log.info("lst Budget Options visibility status : " + isElementVisible(lstBudgetOptions));
		return isElementVisible(lstBudgetOptions);
	}

	public void selectBudgetOptions(String option) {
		scrollToElement(lstBudgetOptions);
		selectByVisibleText(lstBudgetOptions, option);
		log.info("lstBudgetOptions selected successfully.");
	}

	@FindBy(css = ".binding_secBudget .input_Sec_noti:nth-of-type(1) .input_effect")
	private WebElement lstBudgetOptionsTxt;

	public boolean islstBudgetOptionsTxtVisible() {
		log.info("Checking the visibility of lstBudgetOptionsTxt on the page.");
		log.info("lstBudgetOptionsTxt visibility status : " + isElementVisible(lstBudgetOptionsTxt));
		return isElementVisible(lstBudgetOptionsTxt);
	}

	public String verifyTextlstBudgetOptionsTxt(String value) {

		return getAttribute(lstBudgetOptionsTxt, value);
	}

	@FindBy(css = "a#add_Budget > .material-icons")
	private WebElement iconBudgetAdd;

	public boolean isIconBudgetAddVisible() {
		log.info("Checking the visibility of iconBudgetAdd on the page.");
		log.info("iconBudgetAdd visibility status : " + isElementVisible(iconBudgetAdd));
		return isElementVisible(iconBudgetAdd);
	}

	public void IconBudgetAdd() {
		scrollToElement(iconBudgetAdd);
		click(iconBudgetAdd);
		log.info("icon Budget Add Text Button clicked successfully.");
	}
	
	public void IconBillingAdd() {
		scrollToElement(iconBillingAdd);
		click(iconBillingAdd);
		log.info("icon iconBillingAdd Add Text Button clicked successfully.");
	}
	

	@FindBy(css = "input#budgetCancel")
	private WebElement btnBudgetCancel;

	public boolean isBtnBudgetCancelVisible() {
		log.info("Checking the visibility of btnBudgetCancel on the page.");
		log.info("btnBudgetCancel visibility status : " + isElementVisible(btnBudgetCancel));
		return isElementVisible(btnBudgetCancel);
	}

	@FindBy(css = "input#BtnBudgetNotificationPrefrence")
	private WebElement btnBudgetSave;

	public boolean isBtnBudgetSaveVisible() {
		log.info("Checking the visibility of btnBudgetSave on the page.");
		log.info("btnBudgetSave visibility status : " + isElementVisible(btnBudgetSave));
		return isElementVisible(btnBudgetSave);
	}

	public void BudgetSaveBtn() {
		click(btnBudgetSave);
		log.info("Budget Save Button clicked successfully.");
	}

	@FindBy(css = "#ddlBudgetOption1")
	private WebElement lstBudgetOption1;

	public boolean islstBudgetOption1Visible() {
		log.info("Checking the visibility of lstBudgetOption1 on the page.");
		log.info("lstBudgetOption1 visibility status : " + isElementVisible(lstBudgetOption1));
		return isElementVisible(lstBudgetOption1);
	}

	public void selectlstBudgetOption(String serviceOptions) {
		selectByVisibleText(lstBudgetOption1, serviceOptions);
		log.info("lstBudgetOption1 populated successfully.");
	}


	@FindBy(css = "#ddlBillingOption1")
	private WebElement lstBillingOption1;
	

	public void selectlstBillingOption(String serviceOptions) {
		selectByVisibleText(lstBillingOption1, serviceOptions);
		log.info("lstBillingOption1 populated successfully.");
	}
	
	
	@FindBy(css = "#ddlBudgetOption2")
	private WebElement lstBudgetOption2;

	public boolean islstBudgetOption2Visible() {
		log.info("Checking the visibility of lstBudgetOption2 on the page.");
		log.info("lstBudgetOption2 visibility status : " + isElementVisible(lstBudgetOption2));
		return isElementVisible(lstBudgetOption2);
	}

	@FindBy(css = "#ddlBudgetOption3")
	private WebElement lstBudgetOption3;

	public boolean islstBudgetOption3Visible() {
		log.info("Checking the visibility of lstBudgetOption3 on the page.");
		log.info("lstBudgetOption3 visibility status : " + isElementVisible(lstBudgetOption3));
		return isElementVisible(lstBudgetOption3);
	}

	@FindBy(css = "#TxtBudgetText1")
	private WebElement lstBudgetOption1Txt;
	@FindBy(css = "#TxtBudgetText2")
	private WebElement lstBudgetOption2Txt;
	@FindBy(css = "#TxtBudgetText3")
	private WebElement lstBudgetOption3Txt;
	@FindBy(css = "div:nth-of-type(2) > .RemoveIconbtn.clsBudget.removebtn > .material-icons")
	private WebElement iconBudgetClose1;
	@FindBy(css = "div:nth-of-type(3) > .RemoveIconbtn.clsBudget.removebtn > .material-icons")
	private WebElement iconBudgetClose2;
	@FindBy(css = "div:nth-of-type(4) > .RemoveIconbtn.clsBudget.removebtn > .material-icons")
	private WebElement iconBudgetClose3;
	@FindBy(css = "#add_Budget")
	private WebElement iconBudgetDisabled;

	public void clickIconBudgetDisabled() {
		clickWithJSExecutor(iconBudgetDisabled);
		log.info("iconBudgetDisabled clicked successfully.");

	}

	@FindBy(css = "#chkBudgetText1")
	private WebElement chkbox2Budget;

	public void unCheckChkbox2Budget() {

		unCheck(chkbox2Budget);
		log.info("chkbox2Budget is successfully.");
	}

	public void checkChkbox2Budget() {

		check(chkbox2Budget);
		log.info("chkbox2Budget is successfully.");
	}
	
	
	@FindBy(css = "#chkBillingText1")
	private WebElement chkbox2Billing;

	public void unCheckChkbox2Billing() {

		unCheck(chkbox2Billing);
		log.info("chkbox2Billing is successfully.");
	}

	public void checkChkbox2Billing() {

		check(chkbox2Billing);
		log.info("chkbox2Billing is successfully.");
	}
	
	
	
	
	

	@FindBy(css = "#chkBudgetText2")
	private WebElement chkbox3Budget;
	@FindBy(css = "#chkBudgetText3")
	private WebElement chkbox4Budget;
	@FindBys(@FindBy(xpath = "//a[contains(@id,'remove_Budget')]"))
	private List<WebElement> lstBudgetCloseIcons;

	public List<WebElement> listBudgetCloseIcons() {
		return lstBudgetCloseIcons;
	}

	@FindBy(css = "div#ContentPlaceHolder1_ContentPlaceHolderBody_DRNotification h5")
	private WebElement lblDR;
	@FindBy(css = "div#ContentPlaceHolder1_ContentPlaceHolderBody_DRNotification  a > span:nth-of-type(1)")
	private WebElement lblDRText;
	@FindBy(css = "span#ContentPlaceHolder1_ContentPlaceHolderBody_SpanDemandRsStatus")
	private WebElement IconDRStatus;
	@FindBy(css = ".binding_secDR .input_Sec_noti:nth-of-type(1) [type='checkbox']")
	private WebElement chkBoxDR;
	@FindBy(css = ".DRddl.DRddl0.select_effect")
	private WebElement lstDROptions;
	@FindBy(css = ".binding_secDR .input_Sec_noti:nth-of-type(1) .input_effect")
	private WebElement lstDROptionsTxt;
	@FindBy(css = "a#add_DR > .material-icons")
	private WebElement iconDRAdd;
	@FindBy(css = "input#drCancel")
	private WebElement btnDRCancel;
	@FindBy(css = "input#BtnDRNotificationPrefrence")
	private WebElement btnDRSave;
	@FindBy(xpath = "//a[contains(@id,'remove_DR')]")
	private WebElement lstDRCloseIcons;

	@FindBy(css = "div#ContentPlaceHolder1_ContentPlaceHolderBody_ConnectMeNotification h5")
	private WebElement lblConnectMe;

	public boolean islblConnectMeVisible() {
		log.info("Checking the visibility of lblConnectMe on the page.");
		log.info("lblConnectMe visibility status : " + isElementVisible(lblConnectMe));
		return isElementVisible(lblConnectMe);
	}

	public String getLevelConnectMe() {
		log.info("Fetching the lblConnectMe  header.");
		String label = getText(lblConnectMe);
		log.info("lblConnectMe page header is {}: " + label);
		return label;
	}

	@FindBy(css = "div#ContentPlaceHolder1_ContentPlaceHolderBody_ConnectMeNotification  a > h5")
	private WebElement lblContactUsText;

	public boolean isLblContactUsTextVisible() {
		log.info("Checking the visibility of lblConnectMeText on the page.");
		log.info("lblConnectMeText visibility status : " + isElementVisible(lblContactUsText));
		return isElementVisible(lblContactUsText);
	}

	public String getLevelContactUsText() {
		log.info("Fetching the Notification  header.");
		String label = getText(lblContactUsText);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	public void clickLevelContactUsText() {
		click(lblContactUsText);
		log.info("LevelConnectMeText Hours Edit clicked successfully.");

	}

	@FindBy(css = "div#ContentPlaceHolder1_ContentPlaceHolderBody_ServicesNotification  a > h5")
	private WebElement lblServiceText;

	public boolean isLblServiceTextVisible() {
		log.info("Checking the visibility of lblConnectMeText on the page.");
		log.info("lblConnectMeText visibility status : " + isElementVisible(lblServiceText));
		return isElementVisible(lblServiceText);
	}

	public String getLevelServiceText() {
		log.info("Fetching the Notification  header.");
		String label = getText(lblServiceText);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	public void clickLevelServiceText() {
		click(lblServiceText);
		log.info("LevelConnectMeText Hours Edit clicked successfully.");

	}
	@FindBy(css = "span#ContentPlaceHolder1_ContentPlaceHolderBody_SpanConnectMeStatus")
	private WebElement IconConnectMeStatus;

	public boolean isIconConnectMeStatusVisible() {
		log.info("Checking the visibility of IconConnectMeStatus on the page.");
		log.info("IconConnectMeStatus visibility status : " + isElementVisible(IconConnectMeStatus));
		return isElementVisible(IconConnectMeStatus);
	}

	public String getIconConnectMeStatus() {
		log.info("Fetching the IconConnectMeStatus.");
		scrollToElement(IconConnectMeStatus);
		String label = getText(IconConnectMeStatus);
		log.info("IconConnectMeStatus header is {}: " + label);
		return label;
	}

	@FindBy(css = "span#ContentPlaceHolder1_ContentPlaceHolderBody_SpanServicesStatus")
	private WebElement IconServiceStatus;

	public boolean isIconServiceStatusVisible() {
		log.info("Checking the visibility of IconConnectMeStatus on the page.");
		log.info("IconConnectMeStatus visibility status : " + isElementVisible(IconServiceStatus));
		return isElementVisible(IconServiceStatus);
	}

	public String getIconServiceStatus() {
		log.info("Fetching the IconConnectMeStatus.");
		scrollToElement(IconServiceStatus);
		String label = getText(IconServiceStatus);
		log.info("IconConnectMeStatus header is {}: " + label);
		return label;
	}
	
	
	//@FindBy(css = ".binding_secConnectMe .input_Sec_noti:nth-of-type(1) [type='checkbox']")
	@FindBy(css = ".binding_secContactUs .input_Sec_noti:nth-of-type(1) [type='checkbox']")

	private WebElement chkBoxConnectMe;

	public boolean ischkBoxConnectMeVisible() {
		log.info("Checking the visibility of chkBoxConnectMe on the page.");
		log.info("chkBoxConnectMe visibility status : " + isElementVisible(chkBoxConnectMe));
		return isElementVisible(chkBoxConnectMe);
	}
	@FindBy(css = ".binding_secServices .input_Sec_noti:nth-of-type(1) [type='checkbox']")
	private WebElement chkBoxService;

	public boolean ischkBoxServiceVisible() {
		log.info("Checking the visibility of chkBoxConnectMe on the page.");
		log.info("chkBoxConnectMe visibility status : " + isElementVisible(chkBoxService));
		return isElementVisible(chkBoxService);
	}

	public void unCheckConnectMe() {

		unCheck(chkBoxConnectMe);
		log.info("uncheck ConnectMe is successfully.");
	}

	public void checkConnectMe() {

		check(chkBoxConnectMe);
		log.info("chkBoxConnectMe is successfully.");
	}

	//@FindBy(css = ".ConnectMeddl.ConnectMeddl0.select_effect")
	
	@FindBy(css = "#ddlContactUsOption0")
	private WebElement lstConnectMeOptions;

	public boolean islstConnectMeOptionsVisible() {
		log.info("Checking the visibility of lstConnectMeOptions on the page.");
		log.info("lstConnectMeOptions visibility status : " + isElementVisible(lstConnectMeOptions));
		return isElementVisible(lstConnectMeOptions);
	}
	
	@FindBy(css = ".Servicesddl.Servicesddl0.select_effect")
	private WebElement lstServiceOptions;

	public boolean islstServiceOptionsVisible() {
		log.info("Checking the visibility of lstConnectMeOptions on the page.");
		log.info("lstConnectMeOptions visibility status : " + isElementVisible(lstServiceOptions));
		return isElementVisible(lstServiceOptions);
	}

	public void selectlstServiceOptions(String option) {
		selectByVisibleText(lstServiceOptions, option);
		log.info("lstConnectMeOptions populated successfully.");
	}
	
	
	public void selectlstConnectMeOptions(String option) {
		selectByVisibleText(lstConnectMeOptions, option);
		log.info("lstConnectMeOptions populated successfully.");
	}

	//@FindBy(css = ".binding_secConnectMe .input_Sec_noti:nth-of-type(1) .input_effect")
	@FindBy(css = ".binding_secContactUs .input_Sec_noti:nth-of-type(1) .input_effect")

	private WebElement lstConnectMeOptionsTxt;

	public boolean islstConnectMeOptionsTxtVisible() {
		log.info("Checking the visibility of lstConnectMeOptionsTxt on the page.");
		log.info("lstConnectMeOptionsTxt visibility status : " + isElementVisible(lstConnectMeOptionsTxt));
		return isElementVisible(lstConnectMeOptionsTxt);
	}
	
	@FindBy(css = ".binding_secServices .input_Sec_noti:nth-of-type(1) .input_effect")
	private WebElement lstServiceOptionsTxt;

	public boolean islstServiceOptionsTxtVisible() {
		log.info("Checking the visibility of lstConnectMeOptionsTxt on the page.");
		log.info("lstServiceTxt visibility status : " + isElementVisible(lstServiceOptionsTxt));
		return isElementVisible(lstServiceOptionsTxt);
	}
	

	//@FindBy(css = "a#add_ConnectMe > .material-icons")
	@FindBy(css = "	a#add_ContactUs > .material-icons")

	private WebElement iconConnectMeAdd;

	public boolean isIconContactUsAddVisible() {
		log.info("Checking the visibility of iconConnectMeAdd on the page.");
		log.info("iconConnectMeAdd visibility status : " + isElementVisible(iconConnectMeAdd));
		return isElementVisible(iconConnectMeAdd);
	}

//@FindBy(css = "input#connectMeCancel")
	@FindBy(css = "	input#contactUsCancel")

	private WebElement btnConnectMeCancel;

	public boolean isBtnContactUsCancel() {
		log.info("Checking the visibility of btnConnectMeCancel on the page.");
		log.info("iconConnectMeAdd visibility status : " + isElementVisible(btnConnectMeCancel));
		return isElementVisible(btnConnectMeCancel);
	}
	
	
	@FindBy(css = "input#serviceCancel")
	private WebElement btnServiceCancel;

	public boolean isBtnServiceCancel() {
		log.info("Checking the visibility of btnConnectMeCancel on the page.");
		log.info("iconConnectMeAdd visibility status : " + isElementVisible(btnServiceCancel));
		return isElementVisible(btnServiceCancel);
	}
	

	//@FindBy(css = "input#BtnConnectMeNotificationPrefrence")
	@FindBy(css = "	input#BtnContactUsNotificationPrefrence")

	private WebElement btnConnectMeSave;

	public boolean isBtnContactUsSave() {
		log.info("Checking the visibility of btnConnectMeSave on the page.");
		log.info("btnConnectMeSavevisibility status : " + isElementVisible(btnConnectMeSave));
		return isElementVisible(btnConnectMeSave);
	}

	public void clickBtnConnectMeSave() {
		click(btnConnectMeSave);
		log.info("btnConnectMeSave clicked successfully.");

	}

	@FindBy(css = "a#remove_ConnectMe1 > .material-icons")
	private WebElement btnConnectMeClose;

	public boolean isBtnConnectMeClose() {
		log.info("Checking the visibility of btnConnectMeClose on the page.");
		log.info("btnConnectMeClose status : " + isElementVisible(btnConnectMeClose));
		return isElementVisible(btnConnectMeClose);
	}

	//@FindBys(@FindBy(xpath = "//a[contains(@id,'remove_ConnectMe')]"))
	
	
	@FindBys(@FindBy(xpath = "//a[contains(@id,'remove_ContactUs')]"))
	private List<WebElement> lstConnectMeCloseIcons;

	public List<WebElement> getlstConnectMeCloseIcons() {
		log.info("Fetching lst lstConnectMeCloseIcons Icons Icons.");
		return lstConnectMeCloseIcons;
	}

	public boolean islstConnectMeCloseIcons() {
		log.info("Checking the visibility of lstConnectMeCloseIcons on the page.");
		log.info("lstConnectMeCloseIcons status : " + isElementVisibleAlt(lstConnectMeCloseIcons));
		return isElementVisibleAlt(lstConnectMeCloseIcons);
	}

	@FindBy(css = "div#ContentPlaceHolder1_ContentPlaceHolderBody_ServicesNotification h5")
	private WebElement lblServices;
	@FindBy(css = "div#ContentPlaceHolder1_ContentPlaceHolderBody_ServicesNotification  a > span:nth-of-type(1)")
	private WebElement lblServicesText;

	public void linkServicesText() {
		click(lblServicesText);
		log.info("Link Services text clicked successfully.");
	}

	@FindBy(css = "span#ContentPlaceHolder1_ContentPlaceHolderBody_SpanServicesStatus")
	private WebElement IconServicesStatus;

	public String getIconServicesStatus() {
		log.info("Fetching the Icon Services Status.");
		String label = getText(IconServicesStatus);
		log.info("Login page header is {}: " + label);
		return label;
	}

	@FindBy(css = ".binding_secServices .input_Sec_noti:nth-of-type(1) [type='checkbox']")
	private WebElement chkBoxServices;

	public void unCheckChkBoxServices() {

		unCheck(chkBoxServices);
		log.info("uncheck Outage is successfully.");
	}

	public void checkChkBoxServices() {

		check(chkBoxServices);
		log.info("check Outage is successfully.");
	}

	@FindBy(css = ".Servicesddl.Servicesddl0.select_effect")
	private WebElement lstServicesOptions;

	public void selectlstServicesOptions(String serviceOptions) {
		selectByVisibleText(lstServicesOptions, serviceOptions);
		log.info("serviceOptions populated successfully.");
	}

	@FindBy(css = ".binding_secServices .input_Sec_noti:nth-of-type(1) .input_effect")
	private WebElement lstServicesOptionsTxt;

	public String getServicesOptionsTxt(String value) {

		return getAttribute(lstServicesOptionsTxt, value);

	}

	public void selectservicesOptionsTxt(String serviceOptions) {
		selectByVisibleText(lstServicesOptionsTxt, serviceOptions);
		log.info("lstServicesOptionsTxt populated successfully.");
	}

	@FindBy(css = "a#add_Services > .material-icons")
	private WebElement iconServicesAdd;
	@FindBy(css = "input#serviceCancel")
	private WebElement btnServicesCancel;
	@FindBy(css = "input#BtnServicesNotificationPrefrence")
	private WebElement btnServicesSave;

	public void clickServicesSave() {
		click(btnServicesSave);
		log.info("Services save clicked successfully.");

	}

	@FindBy(css = "a#remove_Services1 > .material-icons")
	private WebElement btnServiceCloseIcon;
	@FindBys(@FindBy(xpath = "//a[contains(@id,'remove_Services')]"))
	private List<WebElement> lstServicesCloseIcons;

	public List<WebElement> getlstServicesCloseIcons() {
		log.info("Fetching lst Services Close Icons Icons.");
		return lstServicesCloseIcons;
	}

	@FindBy(css = "div#ContentPlaceHolder1_ContentPlaceHolderBody_LeakAlertNotification h5")
	private WebElement lblLeakalert;

	public boolean islblLeakalertVisible() {
		log.info("Checking the visibility of lblLeakalert text on the page.");
		log.info("lblLeakalert visibility status : " + isElementVisible(lblLeakalert));
		return isElementVisible(lblLeakalert);
	}

	public String getLeakalert() {
		log.info("Fetching the Leak Alert.");
		scrollToElement(lblLeakalert);
		String label = getText(lblLeakalert);
		log.info("lblLeakalert page header is {}: " + label);
		return label;
	}

	public void linkLeakalertText() {
		scrollToElement(lblLeakalert);
		click(lblLeakalert);
		log.info("lblLeakalert Text Button clicked successfully.");
	}

	@FindBy(css = "div#ContentPlaceHolder1_ContentPlaceHolderBody_LeakAlertNotification  a > span:nth-of-type(1)")
	private WebElement lblLeakAlertText;

	public String getlblLeakAlertText() {
		log.info("Fetching the Leak Alert.");
		scrollToElement(lblLeakAlertText);
		String label = getText(lblLeakAlertText);
		log.info("lblLeakAlertText page header is {}: " + label);
		return label;
	}

	public void linklblLeakAlertText() {
		scrollToElement(lblLeakAlertText);
		clickWithJSExecutor(lblLeakAlertText);
		log.info("lblLeakAlertText Text Button clicked successfully.");
	}

	@FindBy(css = "span#ContentPlaceHolder1_ContentPlaceHolderBody_SpanLeakAlertStatus")
	private WebElement IconLeakAlertStatus;

	public String getIconLeakAlertStatus() {
		log.info("Fetching the Leak Alert.");
		scrollToElement(IconLeakAlertStatus);
		String label = getText(IconLeakAlertStatus);
		log.info("IconLeakAlertStatus page header is {}: " + label);
		return label;
	}

	@FindBy(css = ".binding_secLeakAlert .input_Sec_noti:nth-of-type(1) [type='checkbox']")
	private WebElement chkBoxLeakAlert;

	public void unCheckchkBoxLeakAlert() {

		unCheck(chkBoxLeakAlert);
		log.info("unchekBox Leak Alert is successfully.");
	}

	public void checkchkBoxLeakAlert() {

		check(chkBoxLeakAlert);
		log.info("checkBox Leak Alert is successfully.");
	}

	@FindBy(css = ".LeakAlertddl.LeakAlertddl0.select_effect")
	private WebElement lstLeakAlertOptions;

	public void selectlstLeakAlertOptions(String option) {
		selectByVisibleText(lstLeakAlertOptions, option);
		log.info("lstLeakAlertOptions populated successfully.");
	}

	@FindBy(css = ".binding_secLeakAlert .input_Sec_noti:nth-of-type(1) .input_effect")
	private WebElement lstLeakAlertOptionsTxt;

	public void populatelstLeakAlertOptionsTxt(String testMail) {
		log.info("Populating lstLeakAlertOptionsTxt {} :" + testMail);
		sendKeys(lstLeakAlertOptionsTxt, testMail);
		log.info("Password populated successfully.");
	}

	@FindBy(css = "a#add_LeakAlert > .material-icons")
	private WebElement iconLeakAlertAdd;
	@FindBy(css = "input#leakCancel")
	private WebElement btnLeakAlertCancel;
	@FindBy(css = "input#BtnLeakAlertNotificationPrefrence")
	private WebElement btnLeakAlertSave;

	public void leakAlertSaveBtn() {
		scrollToElement(btnLeakAlertSave);
		click(btnLeakAlertSave);
		log.info("btnLeakAlertSave Button clicked successfully.");
	}

	@FindBy(css = "a#remove_LeakAlert1 > .material-icons")
	private WebElement btnLeakAlertCloseIcon;
	@FindBys(@FindBy(xpath = "//a[contains(@id,'remove_LeakAlert')]"))
	private List<WebElement> lstLeakAlertCloseIcons;

	public List<WebElement> lstLeakAlertCloseIcons() {
		return lstLeakAlertCloseIcons;
	}

	@FindBy(css = "div#ContentPlaceHolder1_ContentPlaceHolderBody_HighUsageNotification h5")
	private WebElement lblHighUsage;
	@FindBy(css = "div#ContentPlaceHolder1_ContentPlaceHolderBody_HighUsageNotification  a > span:nth-of-type(1)")
	private WebElement lblHighUsageText;
	@FindBy(css = "span#ContentPlaceHolder1_ContentPlaceHolderBody_SpanHighUsageStatus")
	private WebElement IconHighUsageStatus;
	@FindBy(css = ".binding_secHighUsage .input_Sec_noti:nth-of-type(1) [type='checkbox']")
	private WebElement chkBoxHighUsage;
	@FindBy(css = ".HighUsageddl.HighUsageddl0.select_effect")
	private WebElement lstHighUsageOptions;
	@FindBy(css = ".binding_secHighUsage .input_Sec_noti:nth-of-type(1) .input_effect")
	private WebElement lstHighUsageOptionsTxt;
	@FindBy(css = "a#add_HighUsage > .material-icons")
	private WebElement iconHighUsageAdd;
	@FindBy(css = "input#highUsageCancel")
	private WebElement btnHighUsageCancel;
	@FindBy(css = "input#BtnHighUsageNotificationPrefrence")
	private WebElement btnHighUsageSave;
	@FindBy(css = "a#remove_HighUsage1 > .material-icons")
	private WebElement btnHighUsageCloseIcon;
	@FindBy(xpath = "//a[contains(@id,'remove_HighUsage')]")
	private WebElement lstHighUsageRemoveIcons;

	@FindBy(css = ".quiteHour.quiteHourTop > h4")
	private WebElement lblQuietHours;

	public boolean isLblQuietHours() {
		log.info("Checking the visibility of lblQuietHours on the page.");
		log.info("Label Quiet Hours visibility status : " + isElementVisible(lblQuietHours));
		return isElementVisible(lblQuietHours);
	}

	@FindBy(css = "span#ContentPlaceHolder1_QuietHoursRange")
	private WebElement lblQUietHoursRange;

	public boolean isLblQUietHoursRange() {
		log.info("Checking the visibility of Quiet Hours Edit on the page.");
		log.info("Label Quiet Hours Range visibility status : " + isElementVisible(lblQUietHoursRange));
		return isElementVisible(lblQUietHoursRange);
	}

	public String getLblQUietHoursRange() {
		log.info("Fetching the lblQUietHoursRange header.");
		String label = getText(lblQUietHoursRange);
		log.info("Login page header is {}: " + label);
		return label;
	}

	@FindBy(css = "a#QuietHoursEdit")
	private WebElement lnkQuietHoursEdit;

	public boolean islnkQuietHoursEdit() {
		log.info("Checking the visibility of Quiet Hours Edit on the page.");
		log.info("Label Quiet Hours visibility status : " + isElementVisible(lnkQuietHoursEdit));
		return isElementVisible(lnkQuietHoursEdit);
	}

	public void clicklnkQuietHoursEdit() {
		click(lnkQuietHoursEdit);
		log.info("lnk Quiet Hours Edit clicked successfully.");

	}

	@FindBy(css = "div#popupQuietHours > .modal-content p")
	private WebElement txtQuietHours;

	public String getTxtQuietHours() {
		log.info("Fetching the login page header.");
		String label = getText(txtQuietHours);
		log.info("Quiet Hours header is {}: " + label);
		return label;
	}

	// Todo
	@FindBy(css = "#popupQuietHours [data-upgraded=',MaterialRipple']")
	private WebElement chkBoxEnableQuietHours;

	public boolean isChkBoxEnableQuietHours() {
		log.info("Checking the visibility of chkBoxEnableQuietHours on the page.");
		log.info("chkboxEnableQuietHour visibility status : " + isElementVisible(chkBoxEnableQuietHours));
		return isElementVisible(chkBoxEnableQuietHours);
	}

	public void ChkBoxEnableQuietHoursBtn() {
		click(chkBoxEnableQuietHours);
		log.info("ChkBoxEnableQuietHours Button clicked successfully.");
	}

	@FindBy(css = "select#ddlFromTime")
	private WebElement lstStartTimeQuietHours;

	public boolean islstStartTimeQuietHours() {
		log.info("Checking the visibility of lstStartTimeQuietHours on the page.");
		log.info("lstStartTimeQuietHours visibility status : " + isElementVisible(lstStartTimeQuietHours));
		return isElementVisible(lstStartTimeQuietHours);
	}

	public void selectlstStartTimeQuietHours(String option) {
		selectByVisibleText(lstStartTimeQuietHours, option);
		log.info("lstStartTimeQuietHours populated successfully.");
	}

	@FindBy(css = "select#ddlToTime")
	private WebElement lstEndTimeQuietHours;

	public boolean islstEndTimeQuietHours() {
		log.info("Checking the visibility of lstEndTimeQuietHours on the page.");
		log.info("lstEndTimeQuietHours visibility status : " + isElementVisible(lstEndTimeQuietHours));
		return isElementVisible(lstEndTimeQuietHours);
	}

	public void selectlstEndTimeQuietHours(String option) {
		selectByVisibleText(lstEndTimeQuietHours, option);
		log.info("lstEndTimeQuietHours populated successfully.");
	}

	@FindBy(css = "select#selectedTimeZone")
	private WebElement lstTimeZoneQuietHours;

	public boolean islstTimeZoneQuietHours() {
		log.info("Checking the visibility of lstTimeZoneQuietHours on the page.");
		log.info("lstEndTimeQuietHours visibility status : " + isElementVisible(lstTimeZoneQuietHours));
		return isElementVisible(lstTimeZoneQuietHours);
	}

	public void selectlstTimeZoneQuietHours(String option) {
		selectByVisibleText(lstTimeZoneQuietHours, option);
		log.info("lstTimeZoneQuietHours populated successfully.");
	}

	@FindBy(css = "div#popupQuietHours > .modal-content .cancel-button")
	private WebElement btnCancelQuietHours;

	public boolean isBtnCancelQuietHours() {
		log.info("Checking the visibility of btnCancelQuietHours on the page.");
		log.info("btnCancelQuietHours visibility status : " + isElementVisible(btnCancelQuietHours));
		return isElementVisible(btnCancelQuietHours);
	}

	@FindBy(css = "input#btnQuietHours")
	private WebElement btnSaveQuietHours;

	public boolean isBtnSaveQuietHours() {
		log.info("Checking the visibility of btnSaveQuietHours on the page.");
		log.info("btnSaveQuietHours visibility status : " + isElementVisible(btnSaveQuietHours));
		return isElementVisible(btnSaveQuietHours);
	}

	public void SaveQuietHoursBtn() {
		click(btnSaveQuietHours);
		log.info("BtnSaveQuietHours Button clicked successfully.");
	}

	@FindBy(css = "#popupQuietHours button")
	private WebElement linkCloseQuietHours;
	@FindBy(css = ".enable_checkbox")
	private WebElement txtQuietHoursCheckbox;
	@FindBy(css = ".enable_checkbox>label")
	private WebElement chkboxOuietHours;

	public String verifyChkboxOuietHours(String value) {

		return getAttribute(chkboxOuietHours, value);
	}

	@FindBy(css = ".right_profile_sec > h3")
	private WebElement lblContactUs;
	@FindBy(css = ".lft_contn_contc>p")
	private WebElement lblByPhone;
	@FindBy(css = "p:nth-of-type(2) > span")
	private WebElement lblContactResidetNo;
	@FindBy(xpath = "//i[text()='call']")
	private WebElement lblPhoneIcon;

	@FindBy(css = "#divtcpaPopupBody .input_box_eff_wrap")
	private WebElement lbltcpaPopupBody;

	public String getLbltcpaPopupBody() {
		log.info("Fetching the Notification  header.");
		String label = getText(lbltcpaPopupBody);
		log.info("Notification page header is {}: " + label);
		return label;
	}
	
	public String getFullTxtTcpaPopup() {
		log.info("Fetching the Notification  header.");
		String label = getText(lblAgreeTextAcceptNotificationOptsOutPopU);
		log.info("Notification page header is {}: " + label);
		return label;
	}
	@FindBy(css = "#divagree>#checkedType")
	private WebElement TxtCheckedType;
	
	public String getTxtchekedType() {
		log.info("Fetching the Notification  header.");
		String label = getText(TxtCheckedType);
		log.info("Notification TxtCheckedType page header is {}: " + label);
		return label;
	}
	
	@FindBy(css = "h4#notificationTcpaTitle")
	private WebElement lblTCPAacceptNotificatinTerms;

	public boolean isLblTCPAacceptNotificatinTerms() {
		log.info("Checking the visibility of lblTCPAacceptNotificatinTerms on the page.");
		log.info(
				"lblTCPAacceptNotificatinTerms visibility status : " + isElementVisible(lblTCPAacceptNotificatinTerms));
		return isElementVisible(lblTCPAacceptNotificatinTerms);
	}

	public String getLblTCPAacceptNotificatinTerms() {
		log.info("Fetching the Notification  header.");
		String label = getText(lblTCPAacceptNotificatinTerms);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	@FindBy(css = "div#checkedType")
	private WebElement lblTCPANotificationType;
	@FindBy(css = "button#btnagree")
	private WebElement lnkTCPAagree;

	public void lnkTCPAagreeBtn() {
		click(lnkTCPAagree);
		log.info("lnkTCPAagree Button clicked successfully.");
	}
	
	
	public boolean islnkTCPAagreeBtnIsVisible() {
		log.info("Checking the visibility of lnkTCPADisAgree on the page.");
		log.info("lnkTCPAagree visibility status : " + isElementVisible(lnkTCPAagree));
		return isElementVisible(lnkTCPAagree);
	}
	@FindBy(css = "#btnDisagreeCancel")
	private WebElement btnDisagreeCancel;

	
	
	public void btnDisagreeCancel() {
		click(btnDisagreeCancel);
		log.info("btnDisagreeCancel Button clicked successfully.");
	}
	
	@FindBy(css = "button#btndisagree")
	private WebElement lnkTCPADisAgree;

	public void lnkTCPDisAagreeBtn() {
		click(lnkTCPADisAgree);
		log.info("lnkTCPADisAgree Button clicked successfully.");
	}
	
	public boolean islnkTCPDisAagreeBtnVisible() {
		log.info("Checking the visibility of lnkTCPADisAgree on the page.");
		log.info("lnkTCPADisAgree visibility status : " + isElementVisible(lnkTCPADisAgree));
		return isElementVisible(lnkTCPADisAgree);
	}
	

	@FindBy(css = "#btnDisagreeOk")
	private WebElement btnDisagreeOk;

	public void btnDisagreeOk() {
		click(btnDisagreeOk);
		log.info("btnDisagreeOk Button clicked successfully.");
	}

	@FindBy(css = "button#dLabel")
	private WebElement lnkAccountDropdown;
}

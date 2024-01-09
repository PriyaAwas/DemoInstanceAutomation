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
import org.testng.asserts.SoftAssert;

public class AppOnBoardingPage extends HomePage {
	private static final Logger log = LogManager.getLogger(AppOnBoardingPage.class);

	public AppOnBoardingPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".submit-button.nxt_btn_abt_popup")
	private WebElement nextButtonWelcomePopUp;

	@FindBy(css = "#btnskipInfo")
	private WebElement btnSkipSetAlertPopUp;

	@FindBy(css = ".submit-button.save_cont_btn")
	private WebElement btnSaveSetAlertPopUp;

	@FindBy(css = "#btnSaveHomeInfo")
	private WebElement btnSubmitAboutMyBusinessPopUp;

	public boolean isSubmitAboutMyBusinessPopUpVisible() {
		return btnSubmitAboutMyBusinessPopUp.isDisplayed();
	}

	public void clickSubmitAboutMyBusinessPopUp() {
		click(btnSubmitAboutMyBusinessPopUp);
	}

	@FindBy(css = "[id='btnclosepopup_abthome']")
	private WebElement btnCancelWelcomePopUp;

	@FindBy(css = ".wlcm_text h5")
	private WebElement lblNameWelcomePopUp;

	public String getLablNameWelcomePopUp() {
		return getText(lblNameWelcomePopUp);
	}

	@FindBy(css = ".wlcm_text p")
	private WebElement lblTxtWelcomePopUp;
	@FindBy(css = "label.lblAccountNumber")
	private WebElement txtBoxAddressWelcomePopUpAboutMyHome;
	@FindBy(css = "#PU div a span.icon")
	private WebElement iconPowerUsage;
	@FindBy(css = ".electricity-area div#NoUsageDataDiv span")
	private WebElement lblNoPowerUsageData;
	@FindBy(css = ".gas-area div#NoUsageDataDiv span")
	private WebElement lblNoGasUsageData;
	@FindBy(css = ".water-area div#NoUsageDataDiv span")
	private WebElement lblNoWaterUsageData;
	@FindBy(css = "[globalize='ML_DASHBOARD_Anchor_PowerUsage']")
	private WebElement lblWidgetPower;
	@FindBy(css = "#PU select#ddlMultiMeter[name*='ddlMultiMeter']")
	private WebElement lstPowerMeter;
	@FindBy(css = "#WU select#ddlMultiMeterWater[name*='ddlMultiMeterWater']")
	private WebElement lstWaterMeter;
	@FindBy(css = "a:contains('Outages')")
	private WebElement lblWidgetOutages;
	@FindBy(css = "div.outage-area")
	private WebElement WidgetOutages;
	@FindBy(css = "div#EnergyEfficiencyModule")
	private WebElement WidgetEfficiency;
	@FindBy(css = "[globalize='ML_DASHBOARD_Anchor_Efficiency']")
	private WebElement lblWidgetEfficiency;
	@FindBy(css = ".icon.icon-icon-efficiency-sidebar")
	private WebElement iconEfficiencyWidget;
	@FindBy(css = "#efficiencyLoader")
	private WebElement lnkImgEfficiencyWidget;
	@FindBy(css = "#IDEfficiency")
	private WebElement imgEfficiencyWidget;
	@FindBy(css = "a#aUsageElectricD")
	private WebElement lnkViewInDollarPower;
	@FindBy(css = "a#aUsageElectrickWh")
	private WebElement lnkViewInKwhPower;

	@FindBy(xpath = "//span[contains(., 'Water')]")
	private WebElement lnkWaterWidgetWater;
	@FindBy(css = "#WU #aWaterUsageD")
	private WebElement lnkViewInDollarWater;

	@FindBy(css = "#WU #aWaterUsageHCF")
	private WebElement lnkViewInHcfWater;
	@FindBy(css = "#WU #aWaterUsageGL")
	private WebElement lnkViewInGalWater;
	@FindBy(css = "div#GU")
	private WebElement WidgetGasUsage;
	@FindBy(css = "#GU .icon.icon-gas-icon")
	private WebElement iconGasWidget;

	@FindBy(xpath = "//span[contains(., 'Gas')]")
	private WebElement lnkGasWidget;

	@FindBy(css = "#GU select#ddlMultiMeterGas[name*='ddlMultiMeterGas']")
	private WebElement lstGasMeter;
	@FindBy(css = "#WU .icon.icon-water-icon-1")
	private WebElement iconWaterWidgetWater;
	@FindBy(css = "#GU #aGasUsageD")
	private WebElement lnkViewInDollarGas;
	@FindBy(css = "#GU #aGasUsageCCF")
	private WebElement lnkViewInCcfGas;
	@FindBy(css = "div#divGasUsage .highcharts-point")
	private WebElement lblHistogramGraphGas;
	@FindBy(css = "div#divGasUsage .highcharts-tooltip > text")
	private WebElement lblHistogramGrpahGasTooltip;
	@FindBy(css = "[globalize='ML_POWERUSAGE_Navigation_Gas']")
	private WebElement lnkWidgetGasUsage;
	@FindBy(css = "div#divElectricityUsage .highcharts-point")
	private WebElement lblHistogramGraphPower;
	@FindBy(css = "div#divWaterUsage .highcharts-point")
	private WebElement lblHistogramGraphWater;
	@FindBy(css = "div#divElectricityUsage > div.highcharts-container > svg > g.highcharts-tooltip > text")
	private WebElement lblHistogramGrpahPowerTooltip;
	@FindBy(css = "div#divWaterUsage > div.highcharts-container > svg > g.highcharts-tooltip > text")
	private WebElement lblHistogramGrpahWaterTooltip;
	@FindBy(css = "#divWaterUsage .highcharts-xaxis-labels text")
	private WebElement lblHistogramGrpahWaterMonths;
	@FindBy(css = "[id='myModalLabel']")
	private WebElement lblModalChangePassword;
	@FindBy(css = "#changeUserId #myModalLabel")
	private WebElement lblModalChangeUsername;
	@FindBy(css = "span.glyphicon.glyphicon-info-sign.help_icon_img")
	private WebElement iconUsernameInfo;
	@FindBy(css = "[id='txtUserIdPassCnfm']")
	private WebElement txtBoxExistingPassword;

	@FindBy(xpath = "//label[text()='Existing Password']")
	private WebElement lblExistingPassword;
	@FindBy(css = "#changepwdbody #txtNewPass")
	private WebElement txtBoxNewPassword;

	@FindBy(xpath = "//label[text()='New Password']")
	private WebElement lblNewPassword;

	@FindBy(css = "#changepwdbody #txtConfirmPass")
	private WebElement txtBoxConfirmPassword;
	@FindBy(css = "//label[text()='Confirm Password']")
	private WebElement lblConfirmPassword;
	@FindBy(css = "[id='btnClear']")
	private WebElement btnClearChangePassword;
	@FindBy(css = "[id='btnChangePassword']")
	private WebElement btnSubmitChangePassword;
	@FindBy(css = "button#btnclosepopup.close")
	private WebElement btnClosePassword;
	@FindBy(css = "a.button_strength")
	private WebElement lnkShowPassword;
	@FindBy(css = "a.button_strength.hide_button_strength")
	private WebElement lnkHidePassword;
	@FindBy(css = "button#btnclosepopup2.close")
	private WebElement btnCloseChangeUserNameModal;
	@FindBy(css = "span.glyphicon.glyphicon-info-sign.help_icon_img")
	private WebElement iconInfoUserNameChange;
	@FindBy(css = ".tooltip.fade.right.indiv .tooltip-inner")
	private WebElement lblToolTipInfoUserNameChange;
	@FindBy(css = ".toast-message")
	private WebElement lblErrorMandatoryMessage;
	@FindBy(css = "div.w2ui-centered")
	private WebElement lblPasswordIncorrect;
	@FindBy(css = ".toast-close-button")
	private WebElement btnCloseToastMessage;
	@FindBy(css = ".w2ui-popup-btn.w2ui-btn")
	private WebElement btnOkPasswordPopUp;
	@FindBy(css = ".error_messagecommon")
	private WebElement lblGenericErrorMessage;

	public String getlblGenericErrorMessage() {
		return getText(lblGenericErrorMessage);
	}

	@FindBy(css = "[id='txtUserid']")
	private WebElement txtBoxExistingUsername;
	@FindBy(css = ".input_sec_leftp [id='txtNewUserId']")
	private WebElement txtBoxNewUsername;

	public String gettxtBoxNewUsernamelbl() {
		String label = getText(txtBoxNewUsername);
		return label;
	}

	public void populateNewUserName(String s) {
		sendKeys(txtBoxNewUsername, s);
	}

	public void cleartxtBoxNewUsername() {
		clear(txtBoxNewUsername);
	}

	public void copyPasteUsingActionClassNewUserName() {
		copyPasteUsingActionClass(txtBoxNewUsername);
	}

	@FindBy(css = ".btn_right_secp #btnCancelUserId")
	private WebElement btnCancelUserId;
	@FindBy(css = ".btn_right_secp [id='btnChangeUserId']")
	private WebElement btnSubmitUserId;

	public void clickbtnSubmitUserId() {
		click(btnSubmitUserId);
	}

	@FindBy(css = ".compare_text_mob")
	private WebElement WidgetCompare;
	@FindBy(css = ".compare_text_mob > a > img")
	private WebElement imgCompare;
	@FindBy(css = "[id='imgCompareSpendingPrev']")
	private WebElement btnComparePrevious;
	@FindBy(css = "[id ='imgCompareSpendingUtl']")
	private WebElement btnCompareNeighbour;
	@FindBy(css = "span#imgCompareSpendingZip.com_style_comm.com_zip_ico")
	private WebElement btnCompareZip;
	@FindBy(css = "select#drpCompare.compare_select_1")
	private WebElement lstBoxCompare;
	@FindBy(css = ".comparearesection i.material-icons")
	private WebElement iconMaterialCompare;
	@FindBy(css = ".divCompareSpendingPrev > div.compare_section > div")
	private WebElement imgHistogramComparePrevious;
	@FindBy(css = ".compare-area.divCompareSpendingZip > div.compare_section > div")
	private WebElement imgHistogramCompareZip;
	@FindBy(css = "div.compare-area.divCompareSpendingUtl > div.compare_section > div")
	private WebElement imgHistogramCompareNeighbour;
	@FindBy(css = ".compare-area.divCompareSpendingZip > i")
	private WebElement lblWidgetCompareOtherInZipCode;
	@FindBy(css = ".compare-area.divCompareSpendingUtl > i")
	private WebElement lblWidgetCompareOtherInNeighbour;
	@FindBy(css = ".compare-area.divCompareSpendingPrev > i")
	private WebElement lblWidgetComparePrevious;
	@FindBy(css = "a[title='Click to navigate to 'Compare' module ']")
	private WebElement lnkWidgetCompare;
	@FindBy(css = ".NoCompare")
	private WebElement lblNoCompareData;
	@FindBy(css = "[id='aWaterUsageD']")
	private WebElement lblDollarWater;
	@FindBy(css = "[id='aWaterUsageHCF']")
	private WebElement lblHcfWater;
	@FindBy(css = "[id='aWaterUsageGL']")
	private WebElement lblGalPower;
	@FindBy(css = "#PU [id='ddlMultiMeter']")
	private WebElement lstBoxPowerMultimeter;
	@FindBy(css = "#divCompareSpendingUtl span")
	private WebElement lblPercentageUsageMoreCompareNeighbourWidget;
	@FindBy(css = "#divCompareSpendingZip span")
	private WebElement lblPercentageUsageMoreCompareZipWidget;
	@FindBy(css = "#divCompareSpendingPrev span")
	private WebElement lblPercentageUsageMoreComparePreviousWidget;
	@FindBy(css = "[id='btnClear']")
	private WebElement btnClearChangeTemporaryPassword;
	@FindBy(xpath = "//div[@id='w2ui-tag-txtNewPass']/div/div")
	private WebElement lblPasswordFieldErrMsgRight;
	@FindBy(css = "[id='btnCancelUserId']")
	private WebElement btnClearUserId;
	@FindBys(@FindBy(css = "#NotificationData tr > th"))
	private List<WebElement> notificationPrefTableHeaderParent;

	public List<WebElement> getObjectNotificationPrefTableHeaderParentList() {
		return notificationPrefTableHeaderParent;
	}

	@FindBy(css = "div:nth-child(1) span[globalize*='ML']")
	private WebElement lblNotificationPrefTableHeaderChild;

	public By getObjectlabelNotificationPrefTableHeaderChild() {
		return By.cssSelector("div:nth-child(1) span[globalize*='ML']");
	}

	@FindBy(css = "#NotificationData th div:nth-child(1) span[globalize*='ML']")
	private WebElement lblSetMyPrefTableHeaderWp;
	@FindBy(css = "#outage")
	private WebElement div_OutageSMA;
	@FindBy(css = "#billing")
	private WebElement div_BillingSMA;
	@FindBy(css = "#Budget")
	private WebElement div_BudgetSMA;
	@FindBy(css = "#connectme")
	private WebElement div_ContactUsSMA;
	@FindBy(css = "#service")
	private WebElement div_ServicesSMA;
	@FindBy(css = "#leakalert")
	private WebElement div_LeakAlertSMA;
	@FindBy(css = "#Usage")
	private WebElement div_HighUsageAlertSMA;
	@FindBy(css = ".selector-text span[globalize]")
	private WebElement lbl_PreferencesTypeSMA;
	@FindBys(@FindBy(xpath = "//div[@id='NotificationData']/div[not(contains(@style, 'none'))]//td[@class='img_title1']//span[@globalize]"))
	private List<WebElement> lblImgColumnSetPrefAppOnBoardScreen;

	public List<WebElement> getObjectsLablImgColumnSetPrefAppOnBoardScreen() {
		return lblImgColumnSetPrefAppOnBoardScreen;
	}

	@FindBy(css = "#notificationTcpaTitle")
	private WebElement lblAcceptNotificationTerms;

	public boolean islblAcceptNotificationTermsVisible() {
		return lblAcceptNotificationTerms.isDisplayed();
	}

	public String getLabelAcceptNotificationTerms() {
		return getText(lblAcceptNotificationTerms);
	}

	@FindBy(css = "#divtcpaPopupBody .input_box_eff_wrap")
	private WebElement lblNotificationBodyTitle;

	public String getLabelNotificationBodyTitle() {
		return getText(lblNotificationBodyTitle);
	}

	@FindBy(css = ".w2ui-centered.w2ui-confirm-msg")
	private WebElement lblDisagreeConfirmationMsg;
	@FindBy(css = ".txtDiv input")
	private WebElement cb_TextMsg_SMA;
	@FindBy(css = ".divEmail span input")
	private WebElement cb_EmailAddress_SMA;
	@FindBy(css = ".divIVR span input")
	private WebElement cb_VoiceCall_SMA;
	@FindBy(css = "[for ='chkOutageText'] .mdl-checkbox__ripple-container.mdl-js-ripple-effect.mdl-ripple--center")
	private WebElement cbOutageText;

	public boolean checkCbOutageText() {
		return cbOutageText.isSelected();
	}

	public void clickCbOutageText() {
		clickWithJSExecutor(cbOutageText);
	}

	@FindBy(css = "#chkOutageWhatsapp")
	private WebElement cbOutageWhatsApp;
	@FindBy(css = "[for ='chkBillingEmail'] .mdl-checkbox__ripple-container.mdl-js-ripple-effect.mdl-ripple--center")
	private WebElement cbBillingEmail;

	public boolean checkCbBillingEmail() {
		return cbBillingEmail.isSelected();
	}

	public void clickCbBillingEmail() {
		clickWithJSExecutor(cbBillingEmail);
	}

	@FindBy(css = "[for ='chkBudgetIvr'] .mdl-checkbox__ripple-container.mdl-js-ripple-effect.mdl-ripple--center")
	private WebElement cbBudgetIvr;

	public boolean checkCbBudgetIvr() {
		return cbBudgetIvr.isSelected();
	}

	public void clickCbBudgetIvr() {
		clickWithJSExecutor(cbBudgetIvr);
	}

	@FindBy(css = "[for ='chkEmailAll'] .mdl-checkbox__ripple-container.mdl-js-ripple-effect.mdl-ripple--center")
	private WebElement cbchkEmailAll;

	public boolean checkchkEmailAll() {
		return cbchkEmailAll.isSelected();
	}

	public void clickchkEmailAll() {
		clickWithJSExecutor(cbchkEmailAll);
	}

	@FindBy(css = "#BtnNotificationPrefrence")
	private WebElement btn_SaveContinue_Sma;

	public void clickSaveContinue_SmaButton() {
		clickWithJSExecutor(btn_SaveContinue_Sma);
	}

	// div[@class='noti_sec_popup']/div/div/h3
	@FindBy(xpath = "//div[@class='noti_sec_popup']/div/div/h3")
	private WebElement lblSetYourAlertPopupHeading;

	public String getLablSetYourAlertPopupHeading() {
		return getText(lblSetYourAlertPopupHeading);
	}

	@FindBy(css = "#btnskipInfo")
	private WebElement btnSkipSetMyPref;

	public boolean isButtonSkipSetMyPrefVisible() {
		return btnSkipSetMyPref.isDisplayed();
	}

	public void clickButtonSkipSetMyPref() {
		click(btnSkipSetMyPref);
	}

	@FindBy(css = "#btnclosepopup_tcpa")
	private WebElement btnCloseNotificationTerms;
	@FindBy(css = "#btnagree")
	private WebElement btn_IAgree;

	public boolean isAgreeButtonVisible() {
		scrollToElement(btn_IAgree);
		return btn_IAgree.isDisplayed();
	}

	public void clickAgreeButton() {
		scrollToElement(btn_IAgree);
		click(btn_IAgree);
	}

	@FindBy(css = "#btndisagree")
	private WebElement btn_Disagree;

	public void clickDisagreeButton() {
		scrollToElement(btn_Disagree);
		click(btn_Disagree);
	}

	public boolean isDisAgreeButtonVisible() {
		scrollToElement(btn_Disagree);
		return btn_Disagree.isDisplayed();
	}

	@FindBy(css = "#Yes")
	private WebElement btnYesConfirmation;
	@FindBy(css = "#No")
	private WebElement btnNoConfirmation;
	@FindBy(css = "#btnSaveHomeInfo")
	private WebElement btnSubmitAboutMyHome;
	@FindBy(css = ".submit-button.nxt_btn_abt_popup")
	private WebElement btnOnboardingNext;

	public void clickOnboardingNextButton() {
		click(btnOnboardingNext);
	}

	@FindBy(css = ".setting_save_box #btnskipInfo")
	private WebElement btnOnboardingSkip;
	@FindBy(css = ".buttons_area #btnSaveHomeInfo")
	private WebElement btnSubmitHome;
	@FindBy(css = "[id *= ProfileDivCardHeader]")
	private WebElement lblNickNameAllAccountCard;
	@FindBy(css = "#Span_HomeBusiness")
	private WebElement lbl_PageHeader_AHM;

	public boolean islbl_PageHeader_AHMvisible() {
		return lbl_PageHeader_AHM.isDisplayed();
	}

	public String getPageHeader_AHMLabel() {
		String label = getText(lbl_PageHeader_AHM);
		log.info("lbl_PageHeader_AHM {}: " + label);
		return label;
	}

	@FindBy(css = "#Span_HomeBusiness")
	private WebElement lblStep1PageHeader;
	@FindBy(css = "#dashNotification .wlcmheadingfirst")
	private WebElement lblStep2PageHeader;

	public String getStep2PageHeaderLabel() {
		String label = getText(lblStep2PageHeader);
		log.info("lblStep2PageHeader {}: " + label);
		return label;
	}

	@FindBy(css = "#accountdetails .wlcmheadingfirst")
	private WebElement lblStep3PageHeader;

	public String getStep3PageHeaderLabel() {
		String label = getText(lblStep3PageHeader);
		log.info("lblStep3PageHeader {}: " + label);
		return label;
	}

	@FindBy(xpath = "//input[@globalize='ML_OTP_txt_AcctNo']/following-sibling::label")
	private WebElement lblServiceAccountNumber;
	@FindBy(xpath = "//select[@globalize='ML_DynamicForm_DynamicKey_197']/following-sibling::label")
	private WebElement lblTypeOfHomeLiveIn;
	@FindBy(xpath = "//select[@globalize='ML_DynamicForm_DynamicKey_198']/following-sibling::label")
	private WebElement lblNoOfPeoplesLiveIn;
	@FindBy(xpath = "//input[@globalize='ML_DynamicForm_DynamicKey_185']/following-sibling::label")
	private WebElement lblHaveSolarPanels;
	@FindBy(xpath = "//input[@globalize='ML_Default_Lbl_NoOfHomeSize']/following-sibling::label")
	private WebElement lblHomeSize;
	@FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_floors']/following-sibling::label")
	private WebElement lblFloorsInHome;
	@FindBy(xpath = "//label[contains(text(), 'Do you have a swimming pool?')]")
	private WebElement lblHaveSwimmingPool;
	@FindBy(xpath = "//label[text()='Do you own an electric vehicle?']")
	private WebElement lblHaveElectricVehicle;
	@FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_yearbuilt']/following-sibling::label")
	private WebElement lblYearOfHouseBuilt;
	@FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_numberofbathrooms']/following-sibling::label")
	private WebElement lblNoOfBathrooms;
	@FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_numberofhigheffiencyapp']/following-sibling::label")
	private WebElement lblNoOfEfficiencyAppliances;
	@FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_lotsize']/following-sibling::label")
	private WebElement lblLotSize;
	@FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_landscapearea']/following-sibling::label")
	private WebElement lblLandscapeArea;
	@FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_Splandscapearea']/following-sibling::label")
	private WebElement lblSpecialLandscapeArea;
	@FindBy(css = ".wlcm_text .wlcmheadingfirst")
	private WebElement lblWelcomePopupHeading;

	public boolean islblWelcomePopupHeadingVisible() {
		return lblWelcomePopupHeading.isDisplayed();
	}

	public String getWelcomePopupHeadingLabel() {
		String label = getText(lblWelcomePopupHeading);
		log.info("lblWelcomePopupHeading {}: " + label);
		return label;
	}

	@FindBy(css = ".wlcm_text p")
	private WebElement lblWelcomePopupContent;

	public String getWelcomePopupContentLabel() {
		String label = getText(lblWelcomePopupContent);
		log.info("lblWelcomePopupContent {}: " + label);
		return label;
	}

	@FindBys(@FindBy(css = "input.email"))
	private List<WebElement> emailCheckBoxElementsList;

	public List<WebElement> getObjectemailCheckBoxElementsList() {
		return emailCheckBoxElementsList;
	}

	@FindBy(css = "h4#Span_HomeBusiness")
	private WebElement lblNotificationHeading;

	public String getNotificationHeadingLabel() {
		String label = getText(lblNotificationHeading);
		log.info("lblNotificationHeading {}: " + label);
		return label;
	}

	@FindBy(css = ".pro_info_footer_inner")
	private WebElement lblNotificationFooter;

	public String getNotificationFooterLabel() {
		String label = getText(lblNotificationFooter);
		log.info("lblNotificationFooter {}: " + label);
		return label;
	}

	@FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_Splandscapearea']/following-sibling::span[contains(@class, 'glyphicon')]")
	private WebElement icoSpecialLandscapeArea;
	@FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_landscapearea']/following-sibling::span[contains(@class, 'glyphicon')]")
	private WebElement icoLandscapeArea;
	@FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_lotsize']/following-sibling::span[contains(@class, 'glyphicon')]")
	private WebElement icoLotSize;
	@FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_numberofhigheffiencyapp']/following-sibling::span[contains(@class, 'glyphicon')]")
	private WebElement icoHighEfficiencyAppliances;
	@FindBy(xpath = "//input[@globalize='ML_Default_Lbl_NoOfHomeSize']/following-sibling::span[contains(@class, 'glyphicon')]")
	private WebElement icoHomeSize;
	@FindBy(xpath = "//input[@globalize='ML_DynamicForm_DynamicKey_185']/following-sibling::a")
	private WebElement icoSolarCellPanels;
	@FindBy(xpath = "//input[@globalize='ML_AboutHome_txt_AcctNo']")
	private WebElement txtServiceAccountNumber;
	@FindBy(xpath = "//input[@globalize='ML_DynamicForm_DynamicKey_185']")
	private WebElement txtHaveSolarPanels;
	@FindBy(xpath = "//input[@globalize='ML_Default_Lbl_NoOfHomeSize']")
	private WebElement txtHomeSize;
	@FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_floors']")
	private WebElement txtFloorsInHome;
	@FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_yearbuilt']")
	private WebElement txtYearOfHouseBuilt;
	@FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_numberofbathrooms']")
	private WebElement txtNoOfBathrooms;
	@FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_numberofhigheffiencyapp']")
	private WebElement txtNoOfEfficiencyAppliances;
	@FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_lotsize']")
	private WebElement txtLotSize;
	@FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_landscapearea']")
	private WebElement txtLandscapeArea;
	@FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_Splandscapearea']")
	private WebElement txtSpecialLandscapeArea;
	@FindBy(xpath = "//label[contains(text(),'Do you have a swimming pool?')]/preceding-sibling::input")
	private WebElement tglHaveSwimmingPool;
	@FindBy(xpath = "//label[text()='Do you own an electric vehicle?']/preceding-sibling::input")
	private WebElement tglHaveElectricVehicle;
	@FindBy(xpath = "//select[@globalize='ML_DynamicForm_DynamicKey_197']")
	private WebElement ddTypeOfHomeLiveIn;
	@FindBy(xpath = "//select[@globalize='ML_DynamicForm_DynamicKey_198']")
	private WebElement ddNoOfPeoplesLiveIn;
	@FindBy(css = "#btnSaveHomeInfo")
	private WebElement btn_Submit_Ahm_;

	public boolean isSubmit_AhmButtonVisible() {
		return btn_Submit_Ahm_.isDisplayed();
	}

	public void clickSubmit_AhmButton() {
		click(btn_Submit_Ahm_);
	}

	@FindBy(css = "#btnCancelInfo")
	private WebElement btnCancelHome;

	public boolean isCancelButtonVisible() {
		return btnCancelHome.isDisplayed();
	}

	public void clickCancelButton() {
		click(btnCancelHome);
	}

	@FindBy(css = "#Yes")
	private WebElement btnOkNotificationPref;
	@FindBy(css = "#No")
	private WebElement btnCancelNotificationPref;
	@FindBy(css = "input#btnskipInfo")
	private WebElement btnSkipNotificationPre;

	public boolean isSkipNotificationPreButtonVisible() {

		log.info("checking visibility of skip notification pre button");
		return btnSkipNotificationPre.isDisplayed();

	}

	@FindBys(@FindBy(xpath = "//select[@globalize='ML_AboutMyBusiness_Lbl_BusinessType']"))
	private List<WebElement> lstBusinessType;

	public List<String> getTextlstBusinessTypeList() {
		return getAllElementsTextInList(lstBusinessType);
	}

	@FindBys(@FindBy(xpath = "//select[@globalize='ML_DynamicForm_DynamicKey_223']"))
	private List<WebElement> lstBusinessSize;

	public List<String> getTextlstBusinessSizeList() {
		return getAllElementsTextInList(lstBusinessSize);
	}

	public String getAttributelstBusinessSize(String value) {
		return getAttribute(lstBusinessSize.get(0), value);
	}

	@FindBy(css = "input[globalize='ML_CONNECTME_txtbx_ServiceAcct']")
	private WebElement txtAccNoAboutMyBusiness;

	public String gettxtAccNoAboutMyBusiness() {
		return getText(txtAccNoAboutMyBusiness);
	}

	@FindBy(xpath = "//input[@globalize='ML_DynamicForm_DynamicKey_29']")
	private WebElement txtNoEmployeesWorking;

	public void gettxtNoEmployeesWorking(String value) {
		sendKeys(txtNoEmployeesWorking, value);
	}

	public String getAttributeNoEmployeesWorking(String value) {
		return getAttribute(txtNoEmployeesWorking, value);
	}

	public String getAttributetxtNoEmployeesWorking(String value) {
		return getAttribute(txtNoEmployeesWorking, value);
	}

	@FindBy(xpath = "//input[@globalize='ML_AboutMyBusiness_Lbl_OfficeArea']")
	private WebElement txtOfficeSpaceArea;

	public void gettxtOfficeSpaceArea(String value) {
		sendKeys(txtOfficeSpaceArea, value);
	}

	public String getAttributetxtOfficeSpaceArea(String value) {
		return getAttribute(txtOfficeSpaceArea, value);
	}

	@FindBy(xpath = "//input[@globalize='ML_DynamicForm_DynamicKey_30']")
	private WebElement txtLotSizeAmb;

	public void gettxtLotSizeAmb(String value) {
		sendKeys(txtLotSizeAmb, value);
	}

	public String getAttributetxtLotSizeAmb(String value) {
		return getAttribute(txtLotSizeAmb, value);
	}

	@FindBy(xpath = "//input[@globalize='ML_DynamicForm_DynamicKey_31']")
	private WebElement txtNoFloors;

	public void gettxtNoFloors(String value) {
		sendKeys(txtNoFloors, value);
	}

	public String getAttributetxtNoFloors(String value) {
		return getAttribute(txtNoFloors, value);
	}

	@FindBy(xpath = "//input[@globalize='ML_AboutMyBusiness_Lbl_NoOfRestrooms']")
	private WebElement txtNoRestrooms;

	public void gettxtNoRestrooms(String value) {
		sendKeys(txtNoRestrooms, value);
	}

	public String getAttributetxtNoRestrooms(String value) {
		return getAttribute(txtNoRestrooms, value);
	}

	@FindBy(xpath = "//input[@globalize='ML_AboutMyBusiness_Lbl_LandcapeArea']")
	private WebElement txtLandscapeAreaAmb;

	public void gettxtLandscapeAreaAmb(String value) {
		sendKeys(txtLandscapeAreaAmb, value);
	}

	public String getAttributetxtLandscapeAreaAmb(String value) {
		return getAttribute(txtLandscapeAreaAmb, value);
	}

	@FindBy(xpath = "//input[@globalize='ML_DynamicForm_DynamicKey_185']")
	private WebElement txtSolarCellPanels;

	public void gettxtSolarCellPanels(String value) {
		sendKeys(txtSolarCellPanels, value);
	}

	public String getAttributetxtSolarCellPanels(String value) {
		return getAttribute(txtLandscapeAreaAmb, value);
	}

	@FindBy(xpath = "//input[@globalize='ML_CONNECTME_txtbx_ServiceAcct']/following-sibling::label")
	private WebElement lblAccountNoAboutMyBusiness;

	public String getlblAccountNoAboutMyBusiness() {
		return getText(lblAccountNoAboutMyBusiness);
	}

	@FindBy(xpath = "//select[@globalize='ML_DynamicForm_DynamicKey_223']/following-sibling::label")
	private WebElement lblBusinessSize;

	public String getLabelBusinessSize() {
		return getText(lblBusinessSize);
	}

	@FindBy(xpath = "//select[@globalize='ML_AboutMyBusiness_Lbl_BusinessType']/following-sibling::label")
	private WebElement lblBusinessType;

	public String getLabelBusinessType() {
		return getText(lblBusinessType);
	}

	public String getAttributeLabelBusinessType(String value) {
		return getAttribute(lblBusinessType, value);
	}

	@FindBy(xpath = "//input[@globalize='ML_DynamicForm_DynamicKey_29']/following-sibling::label")
	private WebElement lblNoEmployeesWorking;

	public String getLabelNoEmployeesWorking() {
		return getText(lblNoEmployeesWorking);
	}

	@FindBy(xpath = "//input[@globalize='ML_AboutMyBusiness_Lbl_OfficeArea']/following-sibling::label")
	private WebElement lblOfficeSpaceArea;

	public String getLabelOfficeSpaceArea() {
		return getText(lblOfficeSpaceArea);
	}

	public void enterLabelOfficeSpaceArea(String value) {
		sendKeys(lblOfficeSpaceArea, value);
	}

	@FindBy(xpath = "//input[@globalize='ML_DynamicForm_DynamicKey_30']/following-sibling::label")
	private WebElement lblLotSizeAmb;

	public String getAttributeLabelLotSizeAmb(String value) {
		return getAttribute(lblLotSizeAmb, value);
	}

	public void enterLabelLotSizeAmb(String value) {
		sendKeys(lblLotSizeAmb, value);
	}

	@FindBy(xpath = "//input[@globalize='ML_DynamicForm_DynamicKey_31']/following-sibling::label")
	private WebElement lblNoFloors;

	public String getLabelNoFloors() {
		return getText(lblNoFloors);
	}

	public String getAttributeLabelNoFloors(String value) {
		return getAttribute(lblNoFloors, value);
	}

	public void enterLabelNoFloors(String value) {
		sendKeys(lblNoFloors, value);
	}

	@FindBy(xpath = "//input[@globalize='ML_AboutMyBusiness_Lbl_NoOfRestrooms']/following-sibling::label")
	private WebElement lblNoRestrooms;

	public String getLabelNoRestrooms() {
		return getText(lblNoRestrooms);
	}

	public void enterLabelNoRestrooms(String value) {
		sendKeys(lblNoRestrooms, value);
	}

	public String getAttributeLabelNoRestrooms(String value) {
		return getAttribute(lblNoRestrooms, value);
	}

	@FindBy(xpath = "//input[@globalize='ML_AboutMyBusiness_Lbl_LandcapeArea']/following-sibling::label")
	private WebElement lblLandscapeAreaAmb;
	@FindBy(xpath = "//input[@globalize='ML_DynamicForm_DynamicKey_185']/following-sibling::label")
	private WebElement lblSolarCellPanels;
	@FindBy(xpath = "//label[contains(text(),'Does the workplace have Elevator?')]")
	private WebElement lblHasElevator;
	@FindBy(xpath = "//label[contains(text(),'Does the workplace have  HVAC System?')]")
	private WebElement lblHaveHVAC;
	@FindBy(xpath = "//label[contains(text(),'Does the workplace have Electrical System?')]")
	private WebElement lblElectricSystem;
	@FindBy(xpath = "//label[contains(text(),'Does the workplace have Plumbing/ Water System?')]")
	private WebElement lblPlumbingWaterSystem;
	@FindBy(xpath = "//label[contains(text(),'Does the workplace have Server Room?')]")
	private WebElement lblHaveServerRoom;
	@FindBy(xpath = "//label[contains(text(),'Does the workplace have swimming pool?')]")
	private WebElement lblHaveSwimmingPoolAmb;
	@FindBy(xpath = "//label[text()='Does the workplace have Elevator?']/preceding-sibling::input")
	private WebElement tglHasElevator;
	@FindBy(xpath = "//label[contains(text(),'Does the workplace have  HVAC System?')]/preceding-sibling::input")
	private WebElement tglHaveHVAC;
	@FindBy(xpath = "//label[contains(text(),'Does the workplace have Electrical System?')]/preceding-sibling::input")
	private WebElement tglElectricSystem;
	@FindBy(xpath = "//label[contains(text(),'Does the workplace have Plumbing/ Water System?')]/preceding-sibling::input")
	private WebElement tglPlumbingWaterSystem;
	@FindBy(xpath = "//label[contains(text(),'Does the workplace have Server Room?')]/preceding-sibling::input")
	private WebElement tglHaveServerRoom;
	@FindBy(xpath = "//label[contains(text(),'Does the workplace have swimming pool?')]/preceding-sibling::input")
	private WebElement tglHaveSwimmingPoolAmb;

	/**
	 * This method is used to verify the checkboxes for all the notification types.
	 *
	 * @param softAssert
	 * @param type
	 * @param suffix
	 */
	public void verifyNotificationsCheckboxesDisplaying(SoftAssert softAssert, String type, String suffix) {
		By cbText = By.cssSelector("label[for='chk" + type + "Text']");
		softAssert.assertTrue(driver.findElement(cbText).isDisplayed(),
				"Text notification checkbox is not " + "displaying for : " + type);
		By cbEmail = By.cssSelector("label[for='chk" + type + "Email']");
		softAssert.assertTrue(driver.findElement(cbEmail).isDisplayed(),
				"Email notification checkbox is not " + "displaying for : " + type);
		By cbIvr = By.cssSelector("label[for='chk" + type + suffix + "']");
		softAssert.assertTrue(driver.findElement(cbIvr).isDisplayed(),
				"Ivr notifications checkbox is not " + "displaying for : " + type);
	}

}

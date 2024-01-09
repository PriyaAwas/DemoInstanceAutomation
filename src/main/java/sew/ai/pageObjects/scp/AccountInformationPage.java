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

public class AccountInformationPage extends HomePage {
	private static final Logger log = LogManager.getLogger(GuestUserPage.class);

	public AccountInformationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#divAddAccountmodal .closepopup")
	private WebElement btnCloseCrossPopup;

	public void clickbtnCloseCrossPopup() {

		scrollToElement(btnCloseCrossPopup);
		click(btnCloseCrossPopup);
		log.info("Close Cross popup button clicked successfully.");

	}

	public boolean isbtnCloseCrossPopupVisible() {

		log.info("Checking the visibility of close cross popup on the page.");
		log.info("cross popup  visibility status : " + isElementVisible(btnCloseCrossPopup));
		return isElementVisible(btnCloseCrossPopup);
	}

	@FindBy(css = ".MuiGrid-root.pageheading-box #PageTitle")
	private WebElement lblSetting;

	public boolean isAccountInformationPageTitleVisible() {
		log.info("Account Information Page Title label visibility status : " + lblSetting.isDisplayed());
		return lblSetting.isDisplayed();
	}

	public String getAccountInformationPageTitleLabel() {
		String label = getText(lblSetting);
		log.info("Account Information  page header is : " + label);
		return label;
	}

	@FindBy(css = ".MuiGrid-root.pageheading-box")
	private WebElement lblProfileHeader;

	public String getProfileHeader() {
		String label = getText(lblProfileHeader);
		log.info("Profile  Header  page header is : " + label);
		return label;
	}

// Move From My Profile to Account Infromation
	@FindBy(xpath = "//a[text() = 'Edit Nickname']")
	private WebElement lblGuestAccountCardNickName;
	@FindBy(css = ".effect_lbl[globalize='ML_SETTING_Lbl_ConfigurePayment']")
	private WebElement lblConfigurePayment;
	@FindBy(css = "a[id*=configure_payment_test]")
	private WebElement btnConfigurePayment;
	@FindBy(css = "#txtPayment")
	private WebElement txtBoxConfigurePayment;
	@FindBy(css = "[id='ddlPayment']")
	private WebElement lstBoxPaymentTypeConfigPayment;
	@FindBy(css = "[id='chkTerm']")
	private WebElement chkBoxTermConfigPayment;
	@FindBy(css = "#configure_payment label[for='chkTerm']")
	private WebElement lblChkBoxtermConfigPayment;
	@FindBy(css = ".submit-button.frightbtn")
	private WebElement btnSaveConfigPayment;
	@FindBy(css = "#btnSave")
	private WebElement btnSave;
	@FindBy(css = "#PaperLessBill div.selector-text")
	private WebElement lblPaperlessBill;
	@FindBy(css = "	#PaperLessBill .help_icon_img")
	private WebElement lnkPaperlessBillInformationalIcon;
	@FindBy(css = ".effect_lbl[globalize='ML_Setting_Lbl_TimeZone']")
	private WebElement lblTimeZone;
	@FindBy(css = ".effect_lbl[globalize='ML_Setting_Lbl_Lang']")
	private WebElement lblLanguage;
	@FindBy(css = "#DivTheme .effect_lbl")
	private WebElement lblStyle;
	@FindBy(css = "ddl_styleList")
	private WebElement drpdwnStyle;
	@FindBy(css = "div.selector-text.settinghome")
	private WebElement lblHome;
	@FindBy(css = "[id='ddlLanguage']")
	private WebElement drpdwnLanguage;
	@FindBy(css = "[id='ddlTimeZone']")
	private WebElement drpdwnTimeZone;
	@FindBy(css = "[type='radio']")
	private WebElement btnRadioHome;
	@FindBy(css = ".bootstraptooltip .help_icon_img")
	private WebElement lnkPaperlessBillingInformationalIcon;
	@FindBy(css = ".modal-content.editMain.modal-lg")
	private WebElement mdlConfigurePayment;
	@FindBy(css = "[id='EbillBtn']")
	private WebElement chkBoxPaperlessBill;
	@FindBy(css = ".modal-md .modal-header h4")
	private WebElement lblConfigurePaymentModalHeader;
	@FindBy(css = ".modal-md .modal-header .closepopup")
	private WebElement btnConfigurePaymentModalClose;
	@FindBy(css = ".modal-md p:first-child")
	private WebElement lblConfigurePaymentModalDisclaimer;
	@FindBy(css = ".modal-md .frightbtn")
	private WebElement btnConfigurePaymentModalSave;

	@FindBy(css = ".modal-md p a")
	private WebElement lnkConfigurePaymentModal;

	@FindBy(css = ".modal-md .footertextes [type='checkbox']")
	private WebElement chkBoxConfigurePaymentModal;

	@FindBy(css = "input#txtPayment.input_effect")
	private WebElement txtConfigurePayment;
	@FindBy(css = ".toast-message")
	private WebElement lblMsgSuccessful;
	@FindBy(css = ".toast-message")
	private WebElement lblMsgWarning;
	@FindBy(css = ".toast-close-button")
	private WebElement btnCloseToastMessage;
	@FindBy(css = "[for=dash1] .mdl-ripple--center")
	private WebElement rdoBtnThemeOne;
	@FindBy(css = ".mdl-radio__ripple-container")
	private WebElement rdoBtnThemes;
	@FindBy(css = "input#chkTerm")
	private WebElement lblTermConditionCheckBox;
	@FindBy(css = "[for=dash2] .mdl-ripple--center")
	private WebElement rdoBtnThemeTwo;
	@FindBy(css = "[for=dash3] .mdl-ripple--center")
	private WebElement rdoBtnThemeThree;
	@FindBy(css = ".login_facebook h2")
	private WebElement lblSocialMediaLogin;
	@FindBy(css = "div[globalize='ML_MyAccount_div_HomeOption']")
	private WebElement lblHomePageView;
	@FindBy(css = ".modal-md [id='ddlPayment']")
	private WebElement drpdownConfigurePaymentModalPaymentType;
	@FindBy(css = "a#lnkbtnaddaccount")
	private WebElement btnAddaccount;

	public void clickBtnaccount() {

		scrollToElement(btnAddaccount);
		click(btnAddaccount);
		log.info("Add account button clicked successfully.");

	}
	@FindBy(css = "(//*[@class='lbldivaccont']/../../div)[1]")
	private WebElement defaultBtnToggleThreeDots;

	@FindBys(@FindBy(xpath = "//*[@class='lbldivaccont']"))
	private List<WebElement> lblLinkedAccountNumList;
	public List<String> getLabelLinkedAccountNumList()
	{
		log.info("fatching all text in list");
	 return getAllElementsTextInList(lblLinkedAccountNumList);
	}
	@FindBys(@FindBy(css = "[id *= ProfileDivCardHeader]"))
	private List<WebElement> lblNickNameAllAccountCard;
	
	public List<String> getLabelNickNameAllAccountCardList()
	{
		//lblNickNameAllAccountCard.size();
		log.info("fatching all text in list");
	 return getAllElementsTextInList(lblNickNameAllAccountCard);
	}

	
	@FindBys(@FindBy(xpath = "//*[@class='lbldivaccont']"))
	private List<WebElement> lbldivAccountList;

	public List<WebElement> getlbldivAccountList() {
		return lbldivAccountList;
	}
	

	@FindBys(@FindBy(css = ".toggle_nvp a[aria-label='click to expand kebab menu']"))
	private List<WebElement> listAllLinkAccountKebabMenu;

	public List<WebElement> getListAllLinkAccountKebabMenu() {
		return listAllLinkAccountKebabMenu;
	}

	public void clickListAllLinkAccountKebabMenu(int index) {
		clickWithJSExecutor(listAllLinkAccountKebabMenu.get(index));
	}

	public boolean isBtnaccountVisible() {

		log.info("Checking the visibility of account popup on the page.");
		log.info("account popup  visibility status : " + isElementVisible(btnAddaccount));
		return isElementVisible(btnAddaccount);

	}
	@FindBys(@FindBy(xpath= "//a[text() ='Edit Nickname']"))
	private List<WebElement> btnEditNickNameGuestAccount;
	
	public Boolean isbtnEditNickNameGuestAccountVisible(int i)
	{
		 scrollToElement(btnEditNickNameGuestAccount.get(i));
		 Boolean status = isElementVisible(btnEditNickNameGuestAccount.get(i));
	        log.info("edit button visibility status {} " + status);
	        ExtentLogger.logInfo("edit button visibility status {} " + status);
	        return status;
	}
	public void clickBtnEditNickNameGuestAccount(int index) {
		clickWithJSExecutor(btnEditNickNameGuestAccount.get(index));
	}


	@FindBys(@FindBy(css = "[id*='btnMakeDefault']"))
	private List<WebElement> listbtnMakeDefault;

	public void clickListbtnMakeDefault(int index) {
		clickWithJSExecutor(listbtnMakeDefault.get(index));
	}

	@FindBy(css = "#nicknametitletxt")
	private WebElement nicknametitletxt;
	
	public Boolean isNickNameTitletextVisible()
	{
		 Boolean status = isElementVisible(nicknametitletxt);
	        log.info("Nick Name title visibility status {} " + status);
	        ExtentLogger.logInfo("Nick Name title visibility status {} " + status);
	        return status;
	}
	
	@FindBy(css = "#NickNamebtnCancel")
	private WebElement nickNamebtnCancel;
	
	public Boolean isNickNamebtnCancelVisible()
	{
		 Boolean status = isElementVisible(nickNamebtnCancel);
	        log.info("Nick name cancel visibility status {} " + status);
	        ExtentLogger.logInfo("Nick Name cancel visibility status {} " + status);
	        return status;
	}
	@FindBy(css = "#NickNamebtnsave")
	private WebElement nickNamebtnsave;
	
	public Boolean isNickNamebtnsaveVisible()
	{
		 Boolean status = isElementVisible(nickNamebtnsave);
	        log.info("Nick name save visibility status {} " + status);
	        ExtentLogger.logInfo("Nick Name save visibility status {} " + status);
	        return status;
	}
	
	public void clickNickNamebtnsaveBtn() {
		log.info("Clicking the sign in button.");
		click(nickNamebtnsave);
		log.info("Sign in button clicked successfully.");
	}

	@FindBy(css = "#txtAccountNickName")
	private WebElement textAccountNickName;
	
	public void gettextAccountNickName(String nickName)
	{
		
			log.info("Populating Nick name {} :" +nickName);
			sendKeys(textAccountNickName,nickName);
			log.info("Nick namepopulated successfully.");
		

	}
	
	@FindBy(css = "#btnDefaultpopupsave")
	private WebElement btnDefaultpopupsave;

	public void clickBtnDefaultpopupsave() {

		clickWithJSExecutor(btnDefaultpopupsave);
	}

	@FindBy(css = "label[for*=\"rdores\"]")
	private WebElement rdbLinkAccResidentialPopup;

	public void clickRdbLinkAccResidentialPopup() {

		scrollToElement(rdbLinkAccResidentialPopup);
		click(rdbLinkAccResidentialPopup);
		log.info("Rdb Link Account Residential Popup clicked successfully.");

	}

	public boolean isRdbLinkAccResidentialPopupVisible() {

		log.info("Checking the visibility of Account residential popup on the page.");
		log.info("cross popup  visibility status : " + isElementVisible(rdbLinkAccResidentialPopup));
		return isElementVisible(rdbLinkAccResidentialPopup);
	}

	public String getRdbLinkAccResidentialPopup() {
		log.info("Fetching the Billing  header.");
		scrollToElement(rdbLinkAccResidentialPopup);
		String label = getText(rdbLinkAccResidentialPopup);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	@FindBy(css = ".error_messagecommon")
	private WebElement rdbMessageCommon;

	public String getRdbMessageCommon() {
		log.info("Fetching the Billing  header.");
		scrollToElement(rdbMessageCommon);
		String label = getText(rdbMessageCommon);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	@FindBy(css = "label[for*=\"rdocomm\"]")
	private WebElement rdbLinkAccCommercialPopup;

	public void lnkRdbLinkAccCommercialPopup() {
		scrollToElement(rdbLinkAccCommercialPopup);
		click(rdbLinkAccCommercialPopup);
		log.info("Rdb Link Account Commercial Popup clicked successfully.");
	}

	public boolean islnkRdbLinkAccCommercialPopupVisible() {
		log.info("Checking the visibility of Account commericial popup on the page.");
		log.info("cross popup  visibility status : " + isElementVisible(rdbLinkAccCommercialPopup));
		return isElementVisible(rdbLinkAccCommercialPopup);
	}

	public String getRdbLinkAccCommercialPopup() {
		log.info("Fetching the Billing  header.");
		scrollToElement(rdbLinkAccCommercialPopup);
		String label = getText(rdbLinkAccCommercialPopup);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	@FindBy(css = "#divAddAccountmodal h4")
	private WebElement addAccountmodal;

	public boolean isaddAccountmodalVisible() {
		log.info("Checking the visibility of add account modal visible on the page.");
		log.info("Billing text visibility status : " + isElementVisible(addAccountmodal));
		return isElementVisible(addAccountmodal);
	}

	public String getAccountmodal() {
		log.info("Fetching the Billing  header.");
		scrollToElement(addAccountmodal);
		String label = getText(addAccountmodal);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	public void clickBtnAddaccount() {

		scrollToElement(addAccountmodal);
		click(addAccountmodal);
		log.info("Add account button clicked successfully.");

	}

	@FindBy(css = "input[globalize = 'ML_OTP_txt_AcctNo'] ~label")
	private WebElement addAccountPopup;

	public String getAddAccountPopup() {
		log.info("Fetching the add account popup.");
		scrollToElement(addAccountPopup);
		String label = getText(addAccountPopup);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	@FindBy(css = "input[globalize = 'ML_OTP_txt_AcctNo']")
	private WebElement txtAccountPopup;

	public String txtAddAccountPopup() {
		log.info("Fetching the txt account popup.");
		scrollToElement(txtAccountPopup);
		String label = getText(txtAccountPopup);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	public String populateAddAccountPopup(String textAccount) {
		log.info("Populating username {} :" + textAccount);
		sendKeys(txtAccountPopup, textAccount);
		log.info("text account  populated successfully.");
		return textAccount;

	}

	public void clearAddAccountPopup() {
		clear(txtAccountPopup);
		log.info("text account  field cleared {}");
	}

	public boolean istxtAddAccountPopupVisible() {
		log.info("Checking the visibility of Billing text on the page.");
		log.info("Billing text visibility status : " + isElementVisible(txtAccountPopup));
		return isElementVisible(txtAccountPopup);
	}

	@FindBy(css = "input[globalize='ML_SrvcRqust_txtbx_ZipCode1'] ~label")
	private WebElement addlblZipCodePopup;

	public String getAddlblZipCodePopup() {
		log.info("Fetching the add label zip codepopup.");
		scrollToElement(addlblZipCodePopup);
		String label = getText(addlblZipCodePopup);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	public boolean isAddlblZipCodePopupVisible() {
		log.info("Checking the visibility of Billing text on the page.");
		log.info("Billing text visibility status : " + isElementVisible(addlblZipCodePopup));
		return isElementVisible(addlblZipCodePopup);
	}

	@FindBy(css = "input[globalize='ML_SrvcRqust_txtbx_ZipCode1']")
	private WebElement addtextZipCodePopup;

	public String populateZipCodePopup(String textZipCode) {
		log.info("Populating ZipCode {} :" + textZipCode);
		sendKeys(addtextZipCodePopup, textZipCode);
		log.info("text account  populated successfully.");
		return textZipCode;
	}

	public void clearZipCodePopup() {
		clear(addtextZipCodePopup);
		log.info("Zip Code field cleared {}");
	}

	@FindBy(css = "div[id='divAddAccountmodal'] [inputtype='MeterNumber'] ~label")
	private WebElement addlblMeterNumberPopup;

	public String getAddlblMeterNumberPopup() {
		log.info("Fetching the add label zip codepopup.");
		scrollToElement(addlblMeterNumberPopup);
		String label = getText(addlblMeterNumberPopup);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	@FindBy(css = "div[id='divAddAccountmodal'] [inputtype='MeterNumber']")
	private WebElement addMeterNumber;

	public String populateaddMeterNumber(String textMeterNumber) {
		log.info("Populating username {} :" + textMeterNumber);
		sendKeys(addMeterNumber, textMeterNumber);
		log.info("Meter Number  populated successfully.");
		return textMeterNumber;
	}

	public void clearMeterNumberField() {
		clear(addMeterNumber);
		log.info("Meter number field cleared {}");
	}

	public boolean isAddlblMeterNumberPopupVisible() {
		log.info("Checking the visibility of meter number on the page.");
		log.info("Meter number visibility status : " + isElementVisible(addlblMeterNumberPopup));
		return isElementVisible(addlblMeterNumberPopup);
	}

	@FindBy(css = "input[globalize = 'ML_SrvcRqust_lbl_SSN'] ~label")
	private WebElement addlblSrvcRqustlblSSN;

	public String getaddlblSrvcRqustlblSSN() {
		log.info("Fetching the add label zip codepopup.");
		scrollToElement(addlblSrvcRqustlblSSN);
		String label = getText(addlblSrvcRqustlblSSN);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	public boolean isAddaddlblSrvcRqustlblSSNVisible() {
		log.info("Checking the visibility of Billing text on the page.");
		log.info("Billing text visibility status : " + isElementVisible(addlblSrvcRqustlblSSN));
		return isElementVisible(addlblSrvcRqustlblSSN);
	}

	@FindBy(css = "input[globalize = 'ML_SrvcRqust_lbl_SSN'] ~label")
	private WebElement lblSSNPopupApp;

	public boolean isLblSSNPopupAppVisible() {
		log.info("Checking the visibility of SSN Popup App on the page.");
		log.info("SSN Popup App visibility status : " + isElementVisible(lblSSNPopupApp));
		return isElementVisible(lblSSNPopupApp);
	}

	@FindBy(css = "input[globalize = 'ML_SrvcRqust_lbl_SSN']")
	private WebElement txtSSNPopup;

	public String populatetxtSSNPopup(String SSNPopup) {
		log.info("Populating SSN number {} :" + SSNPopup);
		sendKeys(txtSSNPopup, SSNPopup);
		log.info("SSN number  populated successfully.");
		return SSNPopup;
	}

	public void cleartxtSSNPopupField() {
		clear(txtSSNPopup);
		log.info("SSN Popup field cleared {}");
	}

	public boolean isTxtSSNPopupAppVisible() {
		log.info("Checking the visibility of SSN Popup App on the page.");
		log.info("SSN Popup App visibility status : " + isElementVisible(txtSSNPopup));
		return isElementVisible(txtSSNPopup);
	}

	public String getTxtSSNPopupApp() {
		log.info("Fetching the SSN Popup App");
		scrollToElement(txtSSNPopup);
		String label = getText(txtSSNPopup);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	@FindBy(css = "input[globalize = 'ML_Registration_FID'] ~label")
	private WebElement lblFIDTINPopupApp;

	public boolean isLblFIDTINPopupVisible() {
		log.info("Checking the visibility of SSN Popup App on the page.");
		log.info("SSN Popup App visibility status : " + isElementVisible(lblFIDTINPopupApp));
		return isElementVisible(lblFIDTINPopupApp);
	}

	@FindBy(css = "input[globalize = 'ML_Registration_FID']")
	private WebElement txtFIDTINPopupApp;

	public String getTxtFIDTINPopupApp() {
		log.info("Fetching the SSN Popup App");
		scrollToElement(txtFIDTINPopupApp);
		String label = getText(txtFIDTINPopupApp);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	public String populateTxtFIDTINPopupApp(String textDl) {
		log.info("Populating FID TIN Popup  {} :" + textDl);
		sendKeys(txtFIDTINPopupApp, textDl);
		log.info("text FID TIN Popup  populated successfully.");
		return textDl;
	}

	@FindBy(css = "div[id='divAddAccountmodal'] [inputtype='DL'] ~label")
	private WebElement lblDLPopup;

	public boolean isLblDLPopupAppVisible() {
		log.info("Checking the visibility of DL Popup App on the page.");
		log.info("DL Popup App visibility status : " + isElementVisible(lblDLPopup));
		return isElementVisible(lblDLPopup);
	}

	public String getLblDLPopupApp() {
		log.info("Fetching the DL Popup App");
		scrollToElement(lblDLPopup);
		String label = getText(lblDLPopup);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	@FindBy(css = "div[id='divAddAccountmodal'] [inputtype='DL']")
	private WebElement txtDLPopup;

	public boolean isTxtDLPopupAppVisible() {
		log.info("Checking the visibility of DL Popup App on the page.");
		log.info("DL Popup App visibility status : " + isElementVisible(txtDLPopup));
		return isElementVisible(txtDLPopup);
	}

	public String getTxtDLPopupApp() {
		log.info("Fetching the DL Popup App");
		scrollToElement(txtDLPopup);
		String label = getText(txtDLPopup);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	public String populateDLPopupApp(String textDl) {
		log.info("Populating username {} :" + textDl);
		sendKeys(txtDLPopup, textDl);
		log.info("text DL Popup  populated successfully.");
		return textDl;
	}

	public void clearDLPopupAppField() {
		clear(txtDLPopup);
		log.info("txtDLPopup field cleared {}");
	}

	@FindBy(css = "input[globalize='ML_Msg_StreetAddress'] ~label")
	private WebElement lblStreetAddPopupApp;

	public boolean isLblStreetAddPopupAppVisible() {
		log.info("Checking the visibility of DL Popup App on the page.");
		log.info("DL Popup App visibility status : " + isElementVisible(lblStreetAddPopupApp));
		return isElementVisible(lblStreetAddPopupApp);
	}

	public String getLblStreetAddPopupApp() {
		log.info("Fetching the DL Popup App");
		scrollToElement(lblStreetAddPopupApp);
		String label = getText(lblStreetAddPopupApp);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	@FindBy(css = "input[globalize='ML_Msg_StreetAddress']")
	private WebElement txtStreetAddPopup;

	public boolean isTxtStreetAddPopupAppVisible() {
		log.info("Checking the visibility of DL Popup App on the page.");
		log.info("DL Popup App visibility status : " + isElementVisible(txtStreetAddPopup));
		return isElementVisible(txtStreetAddPopup);
	}

	public String getTxtStreetAddPopupApp() {
		log.info("Fetching the Street Popup App");
		scrollToElement(txtStreetAddPopup);
		String label = getText(txtStreetAddPopup);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	public String populateStreetAddPopupApp(String streetTxt) {
		log.info("Populating Street test {} :" + streetTxt);
		sendKeys(txtStreetAddPopup, streetTxt);
		log.info("Street text  populated successfully.");
		return streetTxt;
	}

	public void clearStreetAddField() {
		clear(txtStreetAddPopup);
		log.info("Street add popup field cleared {}");
	}

	@FindBy(css = "#SubmitBtn")
	private WebElement btnLinkAccPopupSubmit;

	public void clickbtnLinkAccPopupSubmit() {
		click(btnLinkAccPopupSubmit);
		log.info("btnLinkAccPopupSubmitApp clicked successfully.");

	}

	public boolean isclickbtnLinkAccPopupSubmitVisible() {
		log.info("Checking the visibility of link account  popup on the page.");
		log.info("cross popup  visibility status : " + isElementVisible(btnLinkAccPopupSubmit));
		return isElementVisible(btnLinkAccPopupSubmit);

	}

	public String getBtnLinkAccPopupSubmit(String attribute) {
		log.info("getting attribute value ");
		return getAttribute(btnLinkAccPopupSubmit, attribute);
	}

	@FindBy(css = "#btnclose")
	private WebElement btnCancelLinkAccount;

	public void clickBtnCancelLinkAccount() {
		clickWithJSExecutor(btnCancelLinkAccount);
		log.info("btnCancelLinkAccountApp clicked successfully.");

	}

	@FindBy(css = "#btnUnlinkAccount")
	private WebElement btnUnlinkAccount;

	public void clickBtnUnLinkAccount() {
		click(btnUnlinkAccount);
		log.info("btnUnlinkAccount clicked successfully.");

	}

	public String getBtnLinkAccPopupCancel(String attribute) {
		log.info("getting attribute value ");
		return getAttribute(btnCancelLinkAccount, attribute);
	}

	public boolean isclickBtnCancelLinkAccountVisible() {
		log.info("Checking the visibility of link account  popup on the page.");
		log.info("cross popup  visibility status : " + isElementVisible(btnLinkAccPopupSubmit));
		return isElementVisible(btnLinkAccPopupSubmit);

	}

	public void clickBtnCancelLinkAccountApp(String attribute) {
		getAttribute(btnCancelLinkAccount, attribute);
		log.info("attribute value got successfully");

	}

	public void clickBtnToggleThreeDots(String utilityAccountNumber) {
		By btnToggleThreeDots = By.xpath("//*[@class='lbldivaccont'][text()='" + utilityAccountNumber
				+ "']/../..//*[contains(@id,'demo-menu-lower-right')]");
		click(driver.findElement(btnToggleThreeDots));

	}

	public void clickBtnUnlinkAccount(String utilityAccountNumber) {
		By btnUnlinkAccount = By.xpath(
				"//*[@class='lbldivaccont'][text()='" + utilityAccountNumber + "']/../..//*[text()='Unlink Account']");
		click(driver.findElement(btnUnlinkAccount));

	}

	@FindBys(@FindBy(xpath = "//*[contains(@id,'lbldhome')]"))
	private List<WebElement> labelLinkAccount;

	public List<WebElement> getLabelLinkAccount() {
		return labelLinkAccount;
	}

	public String getLblNickNameOfLinkedAccount(String utilityAccountNumber) {
		By btnToggleThreeDots = By
				.xpath("//*[@class='lbldivaccont'][text()='" + utilityAccountNumber + "']/..//preceding-sibling::h5");
		log.info("Fetching the address by visible address.");
		String label = getText(driver.findElement(btnToggleThreeDots));
		log.info("Notification page header is {}: " + label);
		return label;
	}
}
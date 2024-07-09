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

public class PreLoginOutagesPage extends HomePage {
    private static final Logger log = LogManager.getLogger(PreLoginOutagesPage.class);

    public PreLoginOutagesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    
    public Boolean isPreLoginOutagesPage(String url, String title) {
		Boolean isLoginPage = false;
		log.info("Checking that the current page is login page.");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isLoginPage = true;
		log.info("The current page is login page {}: " + isLoginPage);
		return isLoginPage;
	}
    
    @FindBy(css = "#ZipOrCountyInputSearch[placeholder='Search by City or ZIP code']")
	private WebElement lbl_searchbox;

	public String getSearchBoxLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_username);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(css = "#ZipOrCountyInputSearch[placeholder='Search by City or ZIP code']")
	private WebElement lbl_search_box;
	
	public Boolean isSearchBoxVisible() {
		log.info("Checking that the username field is visible on the login page.");
		return isElementVisible(lbl_search_box);
	}
	
	@FindBy(css = "button[title='Show street map']")
	private WebElement lbl_map;

	public String getMapLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_map);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(css = "button[title='Show satellite imagery']")
	private WebElement lbl_satellite	;

	public String getSatelliteLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_satellite);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(css = "a[aria-label='Click to Current']")
	private WebElement lbl_current	;

	public String getCurrentLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_current);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(css = "a[aria-label='Click to Planned']")
	private WebElement lbl_planned	;

	public String getPlannedLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_planned);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	public void clickPlannedBtn() {
		log.info("Clicking the sign in button.");
		click(lbl_planned);
		log.info("Sign in button clicked successfully.");
	}
	
	@FindBy(xpath = "//*[@id=\"showDiv\"]/div[1]/div[1]/div[2]/ul[1]/li[1]/label")
	private WebElement lbl_outages	;

	public String getOutagesLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_outages);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[@id=\"showDiv\"]/div[1]/div[1]/div[2]/ul[1]/li[2]/label")
	private WebElement lbl_affected	;

	public String getAffectedLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_affected);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[@id=\"showDiv\"]/div[1]/div[1]/div[2]/ul[1]/li[3]/label")
	private WebElement lbl_accountsserved	;

	public String getAccountsServedLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_accountsserved);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[@id=\"accordion2\"]/div[1]/div[1]/a/span")
	private WebElement lbl_maplegend	;

	public String getMapLegendLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_maplegend);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	public Boolean isMapLegendVisible() {
		log.info("Checking that the username field is visible on the login page.");
		return isElementVisible(lbl_maplegend);
	}
	
	public void clickMapLegendBtn() {
		log.info("Clicking the sign in button.");
		click(lbl_maplegend);
		log.info("Sign in button clicked successfully.");
	}
	
	@FindBy(xpath = "//*[@id=\"accordion2\"]/div[2]/div[1]/a/span")
	private WebElement lbl_weather	;

	public String getWeatherLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_weather);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	public Boolean isWeatherVisible() {
		log.info("Checking that the username field is visible on the login page.");
		return isElementVisible(lbl_weather);
	}
	
	public void clickWeatherBtn() {
		log.info("Clicking the sign in button.");
		click(lbl_weather);
		log.info("Sign in button clicked successfully.");
	}
	
	@FindBy(css = "a[aria-label=\"Click to Report Outage\"]")
	private WebElement lbl_reportoutage	;

	public String getReportOutageLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_reportoutage);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	public void clickReportOutageBtn() {
		log.info("Clicking the sign in button.");
		click(lbl_reportoutage);
		log.info("Sign in button clicked successfully.");
	}
	
	@FindBy(xpath = "//*[@id=\"showDiv\"]/div[1]/div[1]/div[4]/ul/li[2]/p")
	private WebElement lbl_informationupdates	;

	public String getInformationUpdatesLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_informationupdates);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(css = "button[aria-label=\"Toggle fullscreen view\"]")
	private WebElement txt_fullscreen;
	
	public Boolean isFullscreenButtonVisible() {
		log.info("Checking that the username field is visible on the login page.");
		return isElementVisible(txt_fullscreen);
	}
	
	public void clickFullScreenBtn() {
		log.info("Clicking the sign in button.");
		click(txt_fullscreen);
		log.info("Sign in button clicked successfully.");
	}
	
	@FindBy(css = "button[aria-label='Zoom in']")
	private WebElement txt_zoomin;
	
	public Boolean isZoomInButtonVisible() {
		log.info("Checking that the username field is visible on the login page.");
		return isElementVisible(txt_zoomin);
	}
	
	public void clickZoomInBtn() {
		log.info("Clicking the sign in button.");
		click(txt_zoomin);
		log.info("Sign in button clicked successfully.");
	}
	
	@FindBy(css = "button[aria-label='Zoom out']")
	private WebElement txt_zoomout;
	
	public Boolean isZoomOutButtonVisible() {
		log.info("Checking that the username field is visible on the login page.");
		return isElementVisible(txt_zoomout);
	}
	
	public void clickZoomOutBtn() {
		log.info("Clicking the sign in button.");
		click(txt_zoomout);
		log.info("Sign in button clicked successfully.");
	}

	@FindBy(css = "#ZipOrCountySearch")
	private WebElement txt_search;

	public void clickSearchBtn() {
		log.info("Clicking the sign in button.");
		click(txt_search);
		log.info("Sign in button clicked successfully.");
	}

	public void enterInvalidZipInSearch(String accountNumber) {
		sendKeys(txt_search, accountNumber);
		log.info("Entered account no in the field.");
	}

	@FindBy(xpath = "//*[@id='toast-container']/div/div")
	private WebElement lnk_emptysearcherror;

	public String getEmptySearchLabel() {
		String label = getText(lnk_emptysearcherror);
		log.info("Forgot Username Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "a[aria-label='Click to Grid view']")
	private WebElement txt_gridview;
	
	public Boolean isGridViewButtonVisible() {
		log.info("Checking that the username field is visible on the login page.");
		return isElementVisible(txt_gridview);
	}
	
	@FindBy(css = "a[globalize='ML_HeaderMenu_span_Outages']")
	private WebElement btn_prelogin_outages;
    
    public void clickPreLoginOutagesBtn() {
		log.info("Clicking the sign in button.");
		click(btn_prelogin_outages);
		log.info("Sign in button clicked successfully.");
	}
    
    @FindBy(css = ".tbCurrent.active")
	private WebElement lnkCurrent;
	
	public Boolean isCurrentActive() {
		log.info("Checking that the username field is visible on the login page.");
		return isElementVisible(lnkCurrent);
	}
	
	@FindBy(css = ".tbPlanned.active")
	private WebElement lnkActive;
	
	public Boolean isPlannedActive() {
		log.info("Checking that the username field is visible on the login page.");
		return isElementVisible(lnkActive);
	}
	
	@FindBy(css = "a[aria-label='Click to Grid view']")
	private WebElement btn_listview_outages;
    
    public void clickListViewBtn() {
		log.info("Clicking the sign in button.");
		click(btn_listview_outages);
		log.info("Sign in button clicked successfully.");
	}
    
    @FindBy(css = "a[aria-label='Click to Map view']")
	private WebElement btn_mapview_outages;
    
    public void clickMapViewBtn() {
		log.info("Clicking the sign in button.");
		click(btn_mapview_outages);
		log.info("Sign in button clicked successfully.");
	}
    
    @FindBy(css = "a[aria-label='Click to County']")
	private WebElement lbl_city	;

	public String getCityLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_city);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(css = "a[aria-label='Click to Zip Code']")
	private WebElement lbl_zipcode	;

	public String getZipCodeLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_zipcode);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[text()='Area']")
	private WebElement lbl_area	;

	public String getAreaLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_area);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[text()='Out']")
	private WebElement lbl_out	;

	public String getOutLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_out);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[text()='Served']")
	private WebElement lbl_served	;

	public String getServedLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_served);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[@id=\"collapseSix\"]/div/div/div[1]/ul/li[1]")
	private WebElement lbl_multiple	;

	public String getMultipleLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_multiple);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[@id=\"collapseSix\"]/div/div/div[1]/ul/li[3]")
	private WebElement lbl_one	;

	public String get1Label() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_one);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[@id=\"collapseSix\"]/div/div/div[1]/ul/li[5]")
	private WebElement lbl_two	;

	public String get2Label() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_two);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[@id=\"collapseSix\"]/div/div/div[1]/ul/li[2]")
	private WebElement lbl_onewzeroone	;

	public String get101Label() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_onewzeroone);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[@id=\"collapseSix\"]/div/div/div[1]/ul/li[4]")
	private WebElement lbl_fivezeroone	;

	public String get501Label() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_fivezeroone);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[@id=\"collapseSix\"]/div/div/div[1]/ul/li[6]")
	private WebElement lbl_plustwo	;

	public String get2000Label() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_plustwo);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[@id=\"collapseSix\"]/div/div/div[2]/span")
	private WebElement lbl_sab	;

	public String getSABLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_sab);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[@id=\"collapseSeven\"]/div/div/div[2]/div[1]/span")
	private WebElement lbl_rain	;

	public String getRainLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_rain);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[@id=\"collapseSeven\"]/div/div/div[4]/div[1]/span")
	private WebElement lbl_snow	;

	public String getSnowLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_snow);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[@id=\"collapseSeven\"]/div/div/div[3]/div[1]/span")
	private WebElement lbl_frozen	;

	public String getFrozenLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_frozen);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[@id=\"collapseSeven\"]/div/div/div[5]/div[2]/i[1]")
	private WebElement lbl_light	;

	public String getLightLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_light);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[@id=\"collapseSeven\"]/div/div/div[5]/div[2]/i[2]")
	private WebElement lbl_heavy	;

	public String getHeavyLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_heavy);
		log.info("Username placeholder {}: " + label);
		return label;
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
    
    @FindBy(css = ".logo")
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
	
	@FindBy(css = "select.form-control")
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
	
	@FindBy(xpath = "//*[@id=\"form2\"]/section/div[2]/div/div[4]/ul/a[6]")
	private WebElement lnk_paymentlocations;

	public String getPaymentLocationsLinkLabel() {
		String label = getText(lnk_paymentlocations).replace("location_on", "");
		log.info("Forgot Username Label {}: " + label);
		return label;
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

	@FindBy(css = "#btnok")
	private WebElement BtnContactUsPopupOk;

	public void btnClickOk() {
		click(BtnContactUsPopupOk);
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

	@FindBy(xpath = " (//input[@globalize='ML_SrvcRqust_txtbx_MState'])[1]")
	private WebElement txtPreLoginSerMoveInRequestStartState;

	public Boolean isPreLoginSerMoveInCurrentAddStateVisible() {
		log.info("txtPreLoginSerMoveInRequestStartState visibility status {}: "
			+ isElementVisible(txtPreLoginSerMoveInRequestStartState));
		return isElementVisible(txtPreLoginSerMoveInRequestStartState);
	}

	@FindBys(@FindBy(xpath = "//ul[@class='ui-menu ui-widget ui-widget-content ui-autocomplete ui-front']/li"))
	public List<WebElement> autoSuggestion;

	public void populatestate(String state) {
		log.info("Populating state {} :" + state);
		sendKeys(txtPreLoginSerMoveInRequestStartState, state);
		pause(3000);
		int count = autoSuggestion.size();
		autoSuggestion.get(count - 1).click();
		log.info("state selected");
	}

	@FindBy(xpath = "//input[@globalize='ML_MakeOTP_txt_EmailId']")
	private WebElement txt_email;

	public void enterEmail(String accountNumber) {
		sendKeys(txt_email, accountNumber);
		log.info("Entered account no in the field.");
	}

	@FindBy(xpath = "//input[@globalize='ML_CONNECTME_Lbl_FName']")
	private WebElement txt_fname;

	public void enterFName(String accountNumber) {
		sendKeys(txt_fname, accountNumber);
		log.info("Entered account no in the field.");
	}

	@FindBy(xpath = "//input[@globalize='ML_Register_Lbl_LastName']")
	private WebElement txt_lname;

	public void enterLName(String accountNumber) {
		sendKeys(txt_lname, accountNumber);
		log.info("Entered account no in the field.");
	}

	@FindBy(xpath = "//input[@globalize='ML_SrvcRqust_txtbx_Contact']")
	private WebElement txt_ppnumber;

	public void enterPrimaryPhoneNumber(String accountNumber) {
		sendKeys(txt_ppnumber, accountNumber);
		log.info("Entered account no in the field.");
	}

	@FindBy(xpath = "//input[@globalize='ML_SrvcRqust_txtbx_StreetN']")
	private WebElement txt_streetname;

	public void enterStreetName(String accountNumber) {
		sendKeys(txt_streetname, accountNumber);
		log.info("Entered account no in the field.");
	}

	@FindBy(xpath = "//input[@globalize='ML_CONNECTME_Lbl_Zip']")
	private WebElement txt_zipcode;

	public void enterZipCodeForm(String accountNumber) {
		sendKeys(txt_zipcode, accountNumber);
		log.info("Entered account no in the field.");
	}

	@FindBy(xpath = "//input[@globalize='ML_CONNECTME_Lbl_NearestStreet']")
	private WebElement txt_ncrossstreet;

	public void enterNCrossStreet(String accountNumber) {
		sendKeys(txt_ncrossstreet, accountNumber);
		log.info("Entered account no in the field.");
	}

	@FindBy(css = "#ZipOrCountyInputSearch")
	private WebElement txt_bang;

	public void enterBangInSearch(String accountNumber) {
		sendKeys(txt_bang, accountNumber);
		log.info("Entered account no in the field.");
	}

	@FindBy(xpath = "/html/body/div[2]/div[2]/span[3]")
	private WebElement BtnOption;

	public void clickOption() {
		click(BtnOption);
		log.info("Submit Button clicked {}.");
	}

	public String getOptionLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(BtnOption);
		log.info("Username placeholder {}: " + label);
		return label;
	}
	
    
}

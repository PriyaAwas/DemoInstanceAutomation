package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import sew.ai.helpers.reporters.ExtentLogger;

import java.util.List;

public class LoginPage extends HomePage {
	private static final Logger log = LogManager.getLogger(LoginPage.class);

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#logo-container")
	private WebElement img_header_logo;

	public Boolean isCompanyLogoVisible() {
		log.info("Checking the visibility of company logo on the page.");
		log.info("Company logo visibility status {}: " + isElementVisible(img_header_logo));
		return isElementVisible(img_header_logo);
	}

	public void clickLinkHomeButton() {
		log.info("Clicking the sign in button.");
		click(lnkHomeButton);
		log.info("Home Button link clicked successfully.");
	}
	@FindBy(css = "i.lan_txt_hide")
	private WebElement dd_language_selected_option;

	public String getLanguageDropdownSelectedOption() {
		log.info("Checking the selected option in language dropdown on the page.");
		String option = getText(dd_language_selected_option);
		log.info("Language dropdown selected option value {}: " + option);
		return option;
	}

	@FindBy(css = "#LanguageDrpdwn_sample")
	private WebElement dd_language;

	public Boolean isLanguageDropdownVisible() {
		log.info("Checking the visibility of language dropdown on the page.");
		log.info("Language dropdown visibility status {}: " + isElementVisible(dd_language));
		return isElementVisible(dd_language);
	}

	public void clickLanguageDropdown() {
		click(dd_language);
		log.info("Language drop-down clicked successfully.");
	}

	@FindBys(@FindBy(css = "#LanguageDrpdwn_dp_language li a i"))
	private List<WebElement> dd_language_options;

	public List<WebElement> getLanguageDropdownOptions() {
		return dd_language_options;
	}

	@FindBy(css = ".loginPaper .Loginpagehead")
	private WebElement lbl_login_header;

	public String getLoginPageHeader() {
		log.info("Fetching the login page header.");
		String label = getText(lbl_login_header);
		log.info("Login page header is {}: " + label);
		return label;
	}

	@FindBy(css = ".container-fluid.slider-pan>h4")
	private WebElement lbl_what_can_we_help;
	@FindBy(css = "span[title='Login to view your account']")
	private WebElement lnkLogin;

	@FindBy(css = "#txtLogin ~ label[class='effect_lbl']")
	private WebElement lbl_username;

	public String getUsernameLabel() {
		log.info("Fetching the username placeholder.");
		String label = getText(lbl_username);
		log.info("Username placeholder {}: " + label);
		return label;
	}

	@FindBy(css = "[id='txtLogin']")
	private WebElement txt_username;

	public Boolean isUsernameTxtVisible() {
		log.info("Checking that the username field is visible on the login page.");
		return isElementVisible(txt_username);
	}

	public void clearUsernameField() {
		clear(txt_username);
		log.info("Username field cleared {}");
	}

	public void populateUserName(String userName) {
		log.info("Populating username {} :" + userName);
		sendKeys(txt_username, userName);
		log.info("Username populated successfully.");
	}

	public String getUsernameMaxLength() {
		String maxLen = getAttribute(txt_username, "maxlength");
		log.info("Max length of username field is {} " + maxLen);
		return maxLen;
	}

	public String getPopulatedUsernameValue() {
		log.info("Fetching the auto populated username.");
		return getAttribute(txt_username, "value");
	}

	public void waitForUserNameFieldVisibility() {
		waitForElementToBeVisible(txt_username);
		log.info("Wait for username field to be displayed.");
	}

	public void copyPasteSpaceInUsername() {
		copyPasteUsingActionClass(txt_username);
		log.info("Copy paste done into the username field.");
	}

	@FindBy(css = "#txtpwd ~ label[class='effect_lbl']")
	private WebElement lbl_password;
	@FindBy(css = "[id='txtpwd']")
	private WebElement txt_password;

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

	public void populatePassword(String password) {
		log.info("Populating password {} :" + password);
		sendKeys(txt_password, password);
		log.info("Password populated successfully.");
	}

	public String getPasswordMaxLength() {
		String maxLen = getAttribute(txt_password, "maxlength");
		log.info("Max length of password field is {} " + maxLen);
		return maxLen;
	}

	public void clearPasswordField() {
		clear(txt_password);
		log.info("Password field cleared {}");
	}

	public String getPopulatedPasswordValue() {
		log.info("Fetching the auto populated username.");
		return getAttribute(txt_password, "value");
	}

	public void copyPasteSpaceInPassword() {
		copyPasteUsingActionClass(txt_password);
		log.info("Copy paste done into the password field.");
	}

	@FindBy(css = "#btnlogin")
	private WebElement btn_sign_in;

	public String getSignInBtnLabel() {
		String label = getText(btn_sign_in);
		log.info("Sign in button label {}: " + label);
		return label;
	}

	public void clickSignInBtn() {
		log.info("Clicking the sign in button.");
		click(btn_sign_in);
		log.info("Sign in button clicked successfully.");
	}

	public Boolean isSignInBtnVisible() {
		log.info("Checking that the Sign In field is visible on the login page.");
		return isElementVisible(btn_sign_in);
	}

	@FindBy(linkText = "Forgot Username")
	private WebElement lnk_forgot_username;

	public String getForgotUsernameLabel() {
		String label = getText(lnk_forgot_username);
		log.info("Forgot Username Label {}: " + label);
		return label;
	}

	public void clickForgotUsernameLnk() {
		click(lnk_forgot_username);
		log.info("Forgot username link clicked {}.");
	}

	@FindBy(linkText = "Forgot Password")
	private WebElement lnk_forgot_password;

	public void clickForgotPasswordLnk() {
		click(lnk_forgot_password);
		log.info("Forgot password link clicked {}.");
	}

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

	public void clickProblemSignInLnk() {
		click(lnk_problems_sign_in);
		log.info("Problem sign in link clicked {}.");
	}

	@FindBy(css = "#btnResidentialType")
	private WebElement lnk_register;

	public String getRegisterLinkLabel() {
		String label = getAttribute(lnk_register, "value");
		log.info("Forgot Username Label {}: " + label);
		return label;
	}

	@FindBy(css = "h6[title=\"Click to Register a new account\"]")
	private WebElement lbl_do_not_have_account;

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

	public Boolean isRememberMeChbChecked() {
		log.info("Remember me checkbox visible status {}: " + chb_remember_me.isDisplayed());
		return chb_remember_me.isDisplayed();
	}

	public void checkRememberMe() {
		check(chb_remember_me);
		log.info("Remember me checkbox checked.");
	}

	public void unCheckRememberMe() {
		unCheck(chb_remember_me);
		log.info("Remember me checkbox unchecked.");
	}

	@FindBy(css = ".pw_logo_foot")
	private WebElement img_footer_logo;

	@FindBy(css = "ul a[globalize=\"ML_DASHBOARD_Lbl_PayBill\"]")
	private WebElement lnk_pay_bill;

	@FindBy(css = "[id='rdCusType_0']")
	private WebElement rdo_btn_residential;

	@FindBy(css = "[id='rdCusType_1']")
	private WebElement rdo_btn_commercial;

	@FindBy(css = ".frightbtn")
	private WebElement btn_next;

	@FindBy(css = "[id='lblMsg']")
	private WebElement lbl_invalid_login;

	@FindBy(css = "[id='drp-dwn']")
	private WebElement lnk_my_account;

	@FindBy(css = ".LoginLists a[globalize=\"ML_HeaderMenu_span_Outages\"]")
	private WebElement lnk_outages;

	@FindBy(css = ".LoginLists a[globalize=\"ML_Default_Msg_LearnTips\"]")
	private WebElement lnk_efficiency;

	@FindBy(css = ".LoginLists a[globalize=\"ML_Msg_ServiceTurnOnOff\"]")
	private WebElement lnk_service_turn_off;

	@FindBy(xpath = "//a[text()='Contact Us']")
	private WebElement lnk_contact_us;

	@FindBy(css = "small.icon-report_water.last-icn")
	private WebElement lnk_report_water_waste;

	@FindBy(css = "a[globalize=\"ML_Default_Msg_ViewPaymentLocations\"]")
	private WebElement lnk_payment_location;

	@FindBy(css = "#faqlink a.faq_header")
	private WebElement lnkFaqs;

	@FindBy(linkText = "Terms & Conditions")
	private WebElement lnk_terms_conditions;

	@FindBy(css = "li a[globalize=\"ML_Msg_PrivacyPolicy\"]")
	private WebElement lnk_privacy_policy;

	@FindBy(css = ".banner_logged_out .log_out_area .sign_in a")
	private WebElement btn_re_sign_in;

	@FindBy(css = "div.swiper-button-next")
	private WebElement lnk_forward_footer_menu;

	@FindBy(css = "div.swiper-button-prev")
	private WebElement lnk_backward_footer_menu;

	@FindBy(css = "a[href*=\"residential_tab\"]")
	private WebElement lnk_residential_tab;

	@FindBy(css = "[title=\"Signup\"]")
	private WebElement lnkOldRegistration;

	@FindBy(css = "a[href*=\"commercial_tab\"]")
	private WebElement lnkCommercialTab;

	@FindBy(id = "btnResidentialType")
	private WebElement btnRegister;

	public void clickRegisterBtn() {
		waitForElementToBeClickable(btnRegister);
		click(btnRegister);
		log.info(" Registration button is clicked {}.");
	}

	@FindBy(css = "[id='errorMsg']")
	private WebElement lblMsgBlankLogin;

	@FindBy(css = "span.error_messagecommon")
	private WebElement lblBlankPassword;

	@FindBy(css = "span.error_messagecommon")
	private WebElement lblBlankUsername;

	@FindBy(css = "#LanguageDrpdwn_preData i.lan_txt_hide")
	private WebElement lnkDefaultLanguage;

	public String getDefaultLanguage() {
		return getText(lnkDefaultLanguage);
	}

	public Boolean isDefaultLanguageVisible() {
		return lnkDefaultLanguage.isDisplayed();
	}

	@FindBy(xpath = "//*[@class='lan_txt_hide'][contains(text(),'English')]")
	private WebElement lnkEnglishLanguage;

	public void clickEnglishLanguageOption() {
		click(lnkEnglishLanguage);
		log.info("English language option clicked {}.");
	}

	@FindBy(css = "//*[@class='lan_txt_hide'][contains(text(),'Espa')]")
	private WebElement lnkSpanishLanguage;

	public void clickSpanishLanguageOption() {
		click(lnkSpanishLanguage);
		log.info("Spanish language option clicked {}.");
	}

	@FindBy(css = "//*[@class='lan_txt_hide'][contains(text(),'Fran')]")
	private WebElement lnkFrenchLanguage;

	public void clickFrenchLanguageOption() {
		click(lnkFrenchLanguage);
		log.info("French language option clicked {}.");
	}

	@FindBy(css = ".Login_box_tab>h4")
	private WebElement lblWelcome;
	@FindBy(css = ".copyright p")
	private WebElement lblCopyRights;
	@FindBy(css = "span.itemWid > small.icon-faq_login.last-icn")
	private WebElement lnkFaqInSlider;
	@FindBy(css = "label.effect_lbl[globalize='ML_LOGIN_Lbl_UserID']")
	private WebElement lblUserName;

	@FindBys(@FindBy(css = "a[globalize*='ML_OuterSavingTip_lnk_ReadMore']"))
	private List<WebElement> lnkEfficiencyReadMore;

	public List<WebElement> getEfficiencyReadMoreTiles() {
		return lnkEfficiencyReadMore;
	}

	@FindBy(css = ".toast.toast-error")
	private WebElement lblErrorLogin;
	@FindBy(css = ".toast-message")
	private WebElement msgToastHeader;
	@FindBy(css = ".menu-social a[globalize=\"ML_PL_Lnk_fb\"]")
	private WebElement lnkFaceBookSocial;
	@FindBy(css = ".menu-social a[globalize=\"ML_PL_LNk_twitter\"]")
	private WebElement lnkTwitterSocial;
	@FindBy(css = ".menu-social a[globalize=\"ML_PL_Lnk_YouTube\"]")
	private WebElement lnkYouTubeSocial;
	@FindBy(css = ".menu-social a[globalize=\"ML_PL_Lnk_Instagram\"]")
	private WebElement lnkInstagramSocial;
	@FindBy(css = "span[globalize='ML_Default_Msg_TurnOnOffService']")
	private WebElement lnkServiceTurnOnOff;
	@FindBy(css = "a[globalize='ML_OuterHeader_txt_BackToLogin']")
	private WebElement lnkHomeButton;
	@FindBy(xpath = "//a[contains(@href, 's-outage')]")
	private WebElement lnkOutagesHeader;
	public String getPreLogOutageAttribute(){
		ExtentLogger.logInfo("Fetching the Title of Prelogging Outage link");
		String attribute=getAttribute(lnkOutagesHeader,"title");
		return attribute;
	}
	public void clickPrelogOutagelink(){
		click(lnkOutagesHeader);
		ExtentLogger.logPass("Prelogging Outage link has been Successfully clicked");
	}
	@FindBy(css = "h4[globalize=\"ML_CorporateHQ\"]")
	private WebElement lblCorporateAddress;
	@FindBy(xpath = "//div[@class=\"footer_nav_left\"]//li[2]")
	private WebElement lblCorporateAddressOne;
	@FindBy(css = "//div[@class=\"footer_nav_left\"]//li[3]")
	private WebElement lblCorporateAddressTwo;
	@FindBy(css = "//div[@class=\"footer_nav_left\"]//li[4]")
	private WebElement lblCorporateAddressThree;
	@FindBy(css = "//div[@class=\"footer_nav_left\"]//li[4]/a")
	private WebElement lblCorporateAddressThreePhone;
	@FindBy(css = ".chatbot .plus")
	private WebElement chatIcon;

	public void waitForChatIconVisibility() {
		waitForElementToBeVisible(chatIcon);
		log.info("Icon chart is visible on the login page.");
	}

	public void mouseHoverIconChart() {
		mouseHover(chatIcon);
		log.info("Mouse hovered successful {}.");
	}

	public void clickChatIcon() {
		click(chatIcon);
		log.info("Chat icon clicked successfully.");
	}

	@FindBy(css = "#webchat .helptext")
	private WebElement lblChatWithUs;

	public String getTextChatWithUs() {
		log.info("Getting label chat with us on chat window. {}");
		return getText(lblChatWithUs);
	}

	@FindBy(css = "#webchat .endchatme")
	private WebElement btnCloseChat;

	public void clickCloseChatBtn() {
		click(btnCloseChat);
		log.info("Close chat button clicked {}.");
	}

	@FindBy(css = "#webchat .cnfrmchat")
	private WebElement btnConfirmCloseChat;

	public void clickConfirmCloseChatBtn() {
		click(btnConfirmCloseChat);
		log.info("Confirm close chat button clicked {}.");
	}

	@FindBy(css = "#webchat")
	private WebElement divWebChat;

	public Boolean isWebChatVisible() {
		return isElementVisible(divWebChat);
	}

	@FindBy(css = "svg.header-close-action-button-close-icon")
	private WebElement btnCloseChart;
	@FindBy(css = ".tagove_frame.size_medium.pos_bl.button_modern.status_thread_chat.thread_status_view.ui_status_max")
	private WebElement windowChart;
	@FindBy(css = ".agent.avatars")
	private WebElement imgAvtarChart;
	@FindBy(css = "div.tagove-livechat-widget .acquire-launcher-wrapper iframe")
	private WebElement iFrmChatWindow;

	public void switchToChatFrame() {
		switchToFrame(iFrmChatWindow);
	}

	@FindBy(css = "div.tagove-livechat-widget .aio-widget-frame iframe")
	private WebElement iFrmOpenChatWindow;
	@FindBy(css = ".grecaptcha-logo")
	private WebElement captcha;

	public Boolean isLoginPage(String url, String title) {
		Boolean isLoginPage = false;
		log.info("Checking that the current page is login page.");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isLoginPage = true;
		log.info("The current page is login page {}: " + isLoginPage);
		return isLoginPage;
	}
}

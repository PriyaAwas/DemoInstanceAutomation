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

public class PreLoginPage extends HomePage {
	private static final Logger log = LogManager.getLogger(PreLoginPage.class);

	public PreLoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[id='txtAccountNumber']")
	private WebElement txt_accountNumber;

	public void populateAccountNumber(String accountNumber) {
		log.info("Populating username {} :" + accountNumber);
		sendKeys(txt_accountNumber, accountNumber);
		log.info("Username populated successfully.");
	}

	public void clearAccountNumberField() {
		clear(txt_accountNumber);
		log.info("Account Number field cleared {}");
	}

	@FindBy(css = "[id='txtPhoneNumber']")
	private WebElement txt_phoneNumber;

	public void populatePhoneNumber(String phoneNumber) {
		log.info("Populating username {} :" + phoneNumber);
		sendKeys(txt_phoneNumber, phoneNumber);
		log.info("Username populated successfully.");
	}

	public void clearPhoneNumberField() {
		clear(txt_phoneNumber);
		log.info("Phone Number field cleared {}");
	}
	
	@FindBy(css = "#txtAccountNumber")
	private WebElement accField;

	public boolean isAccountNoFieldVisible() {
		log.info("Checking that Account No field is visible on the PayBill Step 1 page." + accField.isDisplayed());
		return isElementVisible(accField);
	}

	public void clickAccNoFld() {
		click(accField);
		log.info("Click on Acc No Field Successfully .");

	}

	public void enterAccNoInTheField(String accountNumber) {
		sendKeys(accField, accountNumber);
		log.info("Entered account no in the field.");
	}

	@FindBy(css = "#txtPhoneNumber")
	private WebElement primaryPhnField;

	public boolean isPrimaryPhoneNoField() {
		log.info("Checking that Primary Phone No field is visible on the PayBill Step 1 page."
				+ primaryPhnField.isDisplayed());
		return isElementVisible(primaryPhnField);
	}

	public void clickPrimaryPhnFld() {
		click(primaryPhnField);
		log.info("Click on Primary Phone Field Successfully .");

	}

	public void enterPrimayPhTheField(String primaryPh) {
		sendKeys(primaryPhnField, primaryPh);
		log.info("Entered primary phone no in the field.");
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

	public String getTxtBoxUsernameMaxLength() {
		String maxLen = getAttribute(txt_username, "maxlength");
		log.info("Max length of usernmae field is {} " + maxLen);
		return maxLen;
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

	public String getTxtBoxPasswordMaxLength() {
		String maxLen = getAttribute(txt_password, "maxlength");
		log.info("Max length of password field is {} " + maxLen);
		return maxLen;
	}

	@FindBy(css = "#btnlogin")
	private WebElement btn_sign_in;

	public void clickSignInBtn() {
		log.info("Clicking the sign in button.");
		click(btn_sign_in);
		log.info("Sign in button clicked successfully.");
	}

	@FindBy(css = "#btnSubmitPayment")
	private WebElement btn_next;

	public void clickNextBtn() {
		log.info("Clicking the next button.");
		click(btn_next);
		log.info("Next button clicked successfully.");
	}

	
	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[1]/div[2]/input[1]")
	private WebElement btn_next2;

	public void clickNextBtn2() {
		log.info("Clicking the next in page 2.");
		click(btn_next2);
		log.info("Next button clicked successfully.");
	}
    


	@FindBy(css = ".error_messagecommon")

	private WebElement lbl_error_message;

	public String getErrorMessage() {
		String toast_msg = getText(lbl_error_message);
		log.info("Label on toast message {}: " + toast_msg);
		return toast_msg;
	}

	@FindBy(css = "#logo-container")
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

	public Boolean isLoginPage(String url, String title) {
		Boolean isLoginPage = false;
		log.info("Checking that the current page is login page.");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isLoginPage = true;
		log.info("The current page is login page {}: " + isLoginPage);

		return isLoginPage;
	}

	@FindBy(css = "#LanguageDrpdwn_sample")
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

	@FindBy(css = "a[globalize='ML_HeaderMenu_span_Outages']")
	private WebElement lnk_outages;

	public String getOutagesLinkLabel() {
		String label = getText(lnk_outages);
		log.info("Forgot Username Label {}: " + label);
		return label;
	}

	@FindBy(css = "a[globalize='ML_Default_Msg_LearnTips']")
	private WebElement lnk_waystosave;

	public String getWaysToSaveLinkLabel() {
		String label = getText(lnk_waystosave).replace("monetization_on", "").trim();
		log.info("Forgot Username Label {}: " + label);
		return label;
	}

	public void clickWaysToSaveLnk() {
		log.info("Clicking the payment location button.");
		click(lnk_waystosave);
		log.info("contact us button clicked successfully.");
	}
	
	@FindBy(css = "#form1 > section.drHeading > div > div > div > div > div > div > button")
	private WebElement drop_acctype;

	public void clickAccTypeDrpDwn() {
		log.info("Clicking the payment location button.");
		click(drop_acctype);
		log.info("account type drop-down clicked successfully.");
	}
	
	@FindBy(css = "#form1 > section.drHeading > div > div > div > div > div > div > ul > li:nth-child(1)")
	private WebElement opt_All;

	public String getAllOptsLabel() {
		String label = getText(opt_All);
		log.info("All Options Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#form1 > section.drHeading > div > div > div > div > div > div > ul > li:nth-child(2)")
	private WebElement opt_Res;

	public String getResOptLabel() {
		String label = getText(opt_Res);
		log.info("Residential Option Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#form1 > section.drHeading > div > div > div > div > div > div > ul > li:nth-child(3)")
	private WebElement opt_Comm;

	public String getCommOptLabel() {
		String label = getText(opt_Comm);
		log.info("Commercial Option Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#form1 > section.drHeading > div > div > div > div > div > div > ul > li:nth-child(4)")
	private WebElement opt_Multi;

	public String getMultiOptLabel() {
		String label = getText(opt_Multi);
		log.info("Multi-Family Option Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#imgsrcId_6")
	private WebElement img_Rebates;

	public String getRebateImg() {
		String label = getText(img_Rebates);
		log.info("Rebates Image {}: " + label);
		return label;
	}
	
	@FindBy(css = "#title_6")
	private WebElement title_Rebates;

	public String getRebateTitleLabel() {
		String label = getText(title_Rebates);
		log.info("Rebates Title Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#description_6")
	private WebElement description_Rebates;

	public String getRebateDescptLabel() {
		String label = getText(description_Rebates);
		log.info("Rebates description Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#dvrebateCardData > div:nth-child(2) > div > div.mid_section > a")
	private WebElement readmore_Rebates;

	public String getRebateReadMoreLabel() {
		String label = getText(readmore_Rebates);
		log.info("Rebates Read more Label {}: " + label);
		return label;
	}
	
	public void clickRebatesReadMore() {
		click(readmore_Rebates);
		log.info("Rebates Read More option clicked {}.");
	}
	
	@FindBy(css = "#dvrebateCardData > div:nth-child(2) > div > div.bottom_efficiency > h4 > b:nth-child(2)")
	private WebElement views_Rebates;

	public String getRebateViewLabel() {
		String label = getText(views_Rebates);
		log.info("Rebates Views Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#dvrebateCardData > div:nth-child(2) > div > div.bottom_efficiency > h4 > b:nth-child(1)")
	private WebElement likes_Rebates;

	public String getRebateLikeLabel() {
		String label = getText(likes_Rebates);
		log.info("Rebates likes Label {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "/html/body/form/section[2]/div/div[1]/div[3]/div[3]/div/div[1]/h5/span")
	private WebElement title_RebatesReadmore;

	public String getRebateReadmoreTitleLabel() {
		String label = getText(title_RebatesReadmore);
		log.info("Rebates Title Label {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "/html/body/form/section[2]/div/div[1]/div[3]/div[3]/div/div[1]/div[3]/ul/li[3]/text()")
	private WebElement likes_RebatesReadmore;

	public String getRebateReadmoreLikeLabel() {
		String label = getText(likes_RebatesReadmore);
		log.info("Rebates likes Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#imgsrcId_2")
	private WebElement img_Programs;

	public String getProgramImg() {
		String label = getText(img_Programs);
		log.info("Programs Image {}: " + label);
		return label;
	}
	
	@FindBy(css = "#title_2")
	private WebElement title_Programs;

	public String getProgramTitleLabel() {
		String label = getText(title_Programs);
		log.info("Programs Title Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#description_2")
	private WebElement description_Programs;

	public String getProgramDescptLabel() {
		String label = getText(description_Programs);
		log.info("Programs description Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#dvProgramCardData > div:nth-child(1) > div > div.mid_section > a")
	private WebElement readmore_Programs;

	public String getProgramReadMoreLabel() {
		String label = getText(readmore_Programs);
		log.info("Programs Read more Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#dvProgramCardData > div:nth-child(1) > div > div.bottom_efficiency > h4 > b:nth-child(2)")
	private WebElement views_Programs;

	public String getProgramViewLabel() {
		String label = getText(views_Programs);
		log.info("Programs Views Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#dvProgramCardData > div:nth-child(1) > div > div.bottom_efficiency > h4 > b:nth-child(1)")
	private WebElement likes_Programs;

	public String getProgramLikeLabel() {
		String label = getText(likes_Programs);
		log.info("Programs likes Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#imgsrcId_4")
	private WebElement img_SavingTips;

	public String getSavingsTipImg() {
		String label = getText(img_SavingTips);
		log.info("Savings Tip Image {}: " + label);
		return label;
	}
	
	@FindBy(css = "#title_4")
	private WebElement title_SavingsTips;

	public String getSavingsTipTitleLabel() {
		String label = getText(title_SavingsTips);
		log.info("Savings Tip Title Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#description_4")
	private WebElement description_SavingsTip;

	public String getSavingsTipDescptLabel() {
		String label = getText(description_SavingsTip);
		log.info("Savings Tip description Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#dvSavingTipCardData > div:nth-child(1) > div > div.mid_section > a")
	private WebElement readmore_SavingsTips;

	public String getSavingsTipReadMoreLabel() {
		String label = getText(readmore_SavingsTips);
		log.info("Savings Tips Read more Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#dvSavingTipCardData > div:nth-child(1) > div > div.bottom_efficiency > h4 > b:nth-child(2)")
	private WebElement views_SavingsTip;

	public String getSavingsTipViewLabel() {
		String label = getText(views_SavingsTip);
		log.info("Savings Tip Views Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#dvSavingTipCardData > div:nth-child(1) > div > div.bottom_efficiency > h4 > b:nth-child(1)")
	private WebElement likes_SavingsTip;

	public String getSavingsTipLikeLabel() {
		String label = getText(likes_SavingsTip);
		log.info("Savings Tip likes Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#imgsrcId_1")
	private WebElement img_EducationalTips;

	public String getEducationalTipImg() {
		String label = getText(img_EducationalTips);
		log.info("Educational Tip Image {}: " + label);
		return label;
	}
	
	@FindBy(css = "#title_1")
	private WebElement title_EuducationalTips;

	public String getEducationalTipTitleLabel() {
		String label = getText(title_EuducationalTips);
		log.info("Educational Tip Title Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#description_1")
	private WebElement description_EducationalTip;

	public String getEducationalTipDescptLabel() {
		String label = getText(description_EducationalTip);
		log.info("Educational Tip description Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#dvEducationTipCardData > div:nth-child(1) > div > div.mid_section > a")
	private WebElement readmore_EducationalTips;

	public String getEducationalTipReadMoreLabel() {
		String label = getText(readmore_EducationalTips);
		log.info("Educational Tips Read more Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#dvEducationTipCardData > div:nth-child(1) > div > div.bottom_efficiency > h4 > b:nth-child(2)")
	private WebElement views_EducationalTip;

	public String getEducationalTipViewLabel() {
		String label = getText(views_EducationalTip);
		log.info("Educational Tip Views Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#dvEducationTipCardData > div:nth-child(1) > div > div.bottom_efficiency > h4 > b:nth-child(1)")
	private WebElement likes_EducationalTip;

	public String getEducationalTipLikeLabel() {
		String label = getText(likes_EducationalTip);
		log.info("Educational Tip likes Label {}: " + label);
		return label;
	}
	
	@FindBy(css = "#dvDataFound")
	private WebElement disclaimer_WaysToSave;
	
	public String getDisclaimer() {
		scrollToElement(disclaimer_WaysToSave);
		String label = getText(disclaimer_WaysToSave);
		log.info("Ways To Save Disclaimer Label {}: " + label);
		return label;
	}

	@FindBy(xpath = "//*[@id=\"efficiencyMenubar\"]/li[1]/a")
	private WebElement lnk_rebates;

	public String getRebatesLabel() {
		String label = getText(lnk_rebates);
		log.info("Forgot Username Label {}: " + label);
		return label;
	}

	@FindBy(xpath = "//*[@id=\"efficiencyMenubar\"]/li[2]/a")
	private WebElement lnk_programs;

	public String getProgramsLabel() {
		String label = getText(lnk_programs);
		log.info("Forgot Username Label {}: " + label);
		return label;
	}
	
	public void clickProgramsOptn() {
		log.info("Clicking the Programs Option.");
		click(lnk_programs);
		log.info("programs option clicked successfully.");
	}

	@FindBy(xpath = "//*[@id=\"efficiencyMenubar\"]/li[3]/a")
	private WebElement lnk_savings;

	public String getSavingsLabel() {
		String label = getText(lnk_savings);
		log.info("Forgot Username Label {}: " + label);
		return label;
	}
	
	public void clickSavingsTipOptn() {
		log.info("Clicking the Savings Tip Option.");
		click(lnk_savings);
		log.info("Savings Tip option clicked successfully.");
	}

	@FindBy(xpath = "//*[@id=\"efficiencyMenubar\"]/li[4]/a")
	private WebElement lnk_educational;

	public String getEducationalLabel() {
		String label = getText(lnk_educational);
		log.info("Forgot Username Label {}: " + label);
		return label;
	}
	
	public void clickEducationTipsOptn() {
		log.info("Clicking the Education Tips Option.");
		click(lnk_educational);
		log.info("Education Tips option clicked successfully.");
	}

	@FindBy(xpath = "//*[@id=\"preloginPaymentLocationModule\"]")
	private WebElement lnk_paymentlocations;

	public String getPaymentLocationsLinkLabel() {
		String label = getText(lnk_paymentlocations).replace("location_on", "").trim();
		log.info("Forgot Username Label {}: " + label);
		return label;
	}

	public void clickPaymentLocationsLnk() {
		log.info("Clicking the payment location button.");
		click(lnk_paymentlocations);
		log.info("contact us button clicked successfully.");
	}

	public void clickPaymentsLnk() {
		log.info("Clicking the payment button.");
		click(lnk_paybill);
		log.info("contact us button clicked successfully.");
	}

	@FindBy(css = "a[id='PreLoginContactUs']")
	private WebElement lnk_contactus;

	public String getContactUsLinkLabel() {
		String label = getText(lnk_contactus).replace("call", "").trim();
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
	private WebElement txtServiceAccNo;

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
	private WebElement txtSubject;

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

	// @FindBy(css = "#form2 > div.chatbot > a")
	@FindBy(css = "#Footer_pnlChatbotSH > div > a")

	private WebElement btnChatBox;

	public void clickChatBox() {
		click(btnChatBox);
	}

	@FindBy(css = ".helptext")
	private WebElement headerChatBox;

	public boolean isChatBoxHeaderVisible() {
		return headerChatBox.isDisplayed();
	}

	public String getChatBoxHeader() {
		String label = getText(headerChatBox);
		return label;
	}

	@FindBy(css = ".logoarea")
	private WebElement logoSCMChatBox;

	public boolean isScmLOgoChatBoxVisible() {
		return logoSCMChatBox.isDisplayed();
	}

	@FindBy(css = ".webchat__send-box__main")
	private WebElement txtBoxChatBox;

	public boolean isChatTextBoxVisible() {
		return txtBoxChatBox.isDisplayed();
	}

	public void clickChatTextBox() {
		click(txtBoxChatBox);
	}

	public void populateTxtMessageBody(String Text) {
		log.info("Waiting For Visiblity status of MessageBody Text Feild");
		waitForElementToBeVisible(txtBoxChatBox);
		log.info("Visiblity status of MessageBody Text Feild:" + txtBoxChatBox.isDisplayed());
		log.info("Populating Zip Code feild with" + Text);
		sendKeys(txtBoxChatBox, Text);
	}

	public WebElement elementTextMessageBody() {
		log.info("Waiting For Visiblity status of Confirm Routing Number Text Feild");
		waitForElementToBeVisible(txtBoxChatBox);
		log.info("Visiblity status of Confirm Routing Number Text Feild:" + txtBoxChatBox.isDisplayed());
		return txtBoxChatBox;
	}

	@FindBy(xpath = "//textarea[@data-id=\"webchat-sendbox-input\" and @placeholder = \"Type your message\"]")
	private WebElement txtBoxChattxt;

	public void enterDataInChatTextBox(String datafield) {
		pause(10000);
		waitForElementToBeVisible(txtBoxChattxt);
		sendKeys(txtBoxChattxt, datafield);
		log.info("Entering data in thdo you have solar panels field.");
	}

	@FindBy(css = "button[title=\"Send\"]")
	private WebElement btnsend;

	public boolean isChatTextSendButtonVisible() {
		return btnsend.isDisplayed();
	}

	public void clickSendBtn() {
		click(btnsend);
	}

	@FindBy(css = ".endchatme")
	private WebElement btnendchat;

	public void clickEndChatBtn() {
		click(btnendchat);
	}

	@FindBy(css = ".cnfrmchat")
	private WebElement btncnfrmchat;

	public void clickConfirmEndChatBtn() {
		click(btncnfrmchat);
	}

	public Boolean isSignOutPage(String url, String title) {
		Boolean isSignOutPage = false;
		log.info("Checking that the current page is login page.");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isSignOutPage = true;
		log.info("The current page is login page {}: " + isSignOutPage);
		return isSignOutPage;
	}

	@FindBy(css = ".banner_logged_out h1")
	private WebElement lbl_you_signed_out;

	public String getSignOutSuccessfullyLbl() {
		return getText(lbl_you_signed_out);
	}

	public void waitForSignOutSuccessLbl() {
		waitForElementToBeVisible(lbl_you_signed_out);
	}

	@FindBy(css = "a[globalize=ML_signout_txt_signin]")
	private WebElement btn_sign_in_again;

	public void clickSignInAgainBtn() {
		click(btn_sign_in_again);
		log.info("Sign in again button clicked.");
	}

	@FindBy(css = "#dropdownMenuButton")
	private WebElement img_profile_icon;

	public void clickImageProfileIco() {
		click(img_profile_icon);
		log.info("Image profile icon clicked {}.");
	}

	@FindBy(css = "button.userbtn.logout_header")
	private WebElement lnk_sign_out;

	public Boolean isSignOutLnkVisible() {
		return lnk_sign_out.isDisplayed();
	}

	public String getSignOutLinkLabel() {
		return getText(lnk_sign_out);
	}

	public void clickSignOutLnk() {
		pause(500);
		click(lnk_sign_out);
		log.info("Sign out button is clicked.");
	}

	@FindBy(css = "[id='LoginForgetPassword']")
	private WebElement lnkFogotPassword;

	public void clickForgotPasswordLnk() {
		click(lnkFogotPassword);
		log.info("Forgot password link clicked {}.");
	}

	@FindBy(xpath = "//*[@id=\"OuterHeader_homeAnchor\"]")
	private WebElement iconHome;

	public String geticonHomel() {
		log.info("Fetching the Cancel button Text");
		String label = getText(iconHome);
		log.info("Cancel button Text is {}: " + label);
		return label;
	}

	public boolean isHomeIcon() {
		log.info("Cancel Button Status :" + iconHome.isDisplayed());
		return iconHome.isDisplayed();
	}

	public void clickHomeIcon() {
		click(iconHome);
		log.info("Cancel Button clicked {}.");
	}

	
	@FindBys(@FindBy(css = "#LanguageDrpdwn_dp_language li a i"))
	private List<WebElement> dd_language_options;

	public List<WebElement> getLanguageDropdownOptions() {
		return dd_language_options;
	}

	/*
	 * @FindBy(css = "#LanguageDrpdwn_sample") private WebElement dd_language;
	 * 
	 * public Boolean isLanguageDropdownVisible() {
	 * log.info("Checking the visibility of language dropdown on the page.");
	 * log.info("Language dropdown visibility status {}: " +
	 * isElementVisible(dd_language)); return isElementVisible(dd_language); }
	 */

	public void clickLanguageDropdown() {
		click(dd_language);
		log.info("Language drop-down clicked successfully.");
	}

	@FindBy(xpath = "//*[@class='lan_txt_hide'][contains(text(),'Espa')]")
	private WebElement lnkSpanishLanguage;

	public void clickSpanishLanguageOption() {
		click(lnkSpanishLanguage);
		log.info("Spanish language option clicked {}.");
	}

	@FindBy(xpath = "//*[@class='lan_txt_hide'][contains(text(),'Fran')]")
	private WebElement lnkFrenchLanguage;

	public void clickFrenchLanguageOption() {
		click(lnkFrenchLanguage);
		log.info("French language option clicked {}.");
	}

	@FindBy(xpath = "//*[@class='lan_txt_hide'][contains(text(),'English')]")
	private WebElement lnkEnglishLanguage;

	public void clickEnglishLanguageOption() {
		click(lnkEnglishLanguage);
		log.info("English language option clicked {}.");
	}


	@FindBy(xpath = "//*[@id=\"containerDiv\"]/div[3]/div/div[1]/h2")
	private WebElement labelPayBillTwo;

	public boolean isPayBillHeaderVisibleTwo() {
		return isElementVisible(labelPayBillTwo);
	}

	public String getPayBillLabelTwo() {
		String label = getText(labelPayBillTwo);
		return label;
	}

	@FindBy(xpath = "//*[@id=\"containerDiv\"]/div[3]/div/div[1]/div/div[2]/div[1]/div[1]/div/div[2]/div[3]/span[2]")
	private WebElement labelZeroPayBillTwo;

	public String getPayBillLabelForZeroTwo() {
		String label = getText(labelZeroPayBillTwo);
		return label;
	}

	@FindBy(xpath = "//*[@class='error'][contains(text(),'Please enter Card Holder Name.')]")
	private WebElement labelBlankCardName;

	public String getBlankCardName() {
		String label = getText(labelBlankCardName);
		return label;
	}

	@FindBy(xpath = "//*[@class='error'][contains(text(),'Please enter Card Number')]")
	private WebElement labelBlankCardNumber;

	public String getBlankCardNumber() {
		String label = getText(labelBlankCardNumber);
		return label;
	}

	@FindBy(xpath = "//*[@class='error'][contains(text(),'Payment amount cannot be greater than $4000.')]")
	private WebElement labelOverExceedingPayment;

	public String getExceedingPaymentAmount() {
		String label = getText(labelOverExceedingPayment);
		return label;
	}

	@FindBy(xpath = "//*[@class='error'][contains(text(),'Please select Month')]")
	private WebElement labelBlankMonth;

	public String getBlankMonth() {
		String label = getText(labelBlankMonth);
		return label;
	}

	@FindBy(xpath = "//*[@class='error'][contains(text(),'Please select Year')]")
	private WebElement labelBlankYear;

	public String getBlankYear() {
		String label = getText(labelBlankYear);
		return label;
	}

	@FindBy(xpath = "//*[@class='error'][contains(text(),'Please enter 3 digit Security code')]")
	private WebElement labelBlankSecCode;

	public String getBlankSecCode() {
		String label = getText(labelBlankSecCode);
		return label;
	}

	@FindBy(xpath = "//*[@class='error'][contains(text(),'Please enter Last Name')]")
	private WebElement labelBlankLastName;

	public String getBlankLastName() {
		String label = getText(labelBlankLastName);
		return label;
	}

	@FindBy(xpath = "//*[@class='error'][contains(text(),'Please enter Address')]")
	private WebElement labelBlankAddress;

	public String getBlankAddress() {
		String label = getText(labelBlankAddress);
		return label;
	}

	@FindBy(xpath = "//*[@class='error'][contains(text(),'Please enter City')]")
	private WebElement labelBlankCity;

	public String getBlankCity() {
		String label = getText(labelBlankCity);
		return label;
	}

	@FindBy(xpath = "//*[@class='error'][contains(text(),'Please enter State Code.')]")
	private WebElement labelBlankState;

	public String getBlankState() {
		String label = getText(labelBlankState);
		return label;
	}

	@FindBy(xpath = "//*[@class='error'][contains(text(),'Please enter ZIP code')]")
	private WebElement labelBlankZipCode;

	public String getBlankZipCode() {
		String label = getText(labelBlankZipCode);
		return label;
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[1]/div[1]/div/div[2]/section/div[2]/label")
	private WebElement labelCardOption;
	public boolean isCardOptionVisible() {
		return isElementVisible(labelCardOption);
	}

	public void clickCardOption() {
		scrollToElement(labelCardOption);
		click(labelCardOption);
		log.info("Click on card option Successfully .");
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[1]/div[1]/div/div[2]/section/div[1]/label")
	private WebElement labelBankOption;

	public void clickBankOption() {
		scrollToElement(labelBankOption);
		click(labelBankOption);
		log.info("Click on card option Successfully .");
	}

	@FindBy(css = "#nmcrd")
	private WebElement cardHolderNameField;

	public boolean isCardHolderNameFieldVisible() {
		log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
				+ cardHolderNameField.isDisplayed());
		return isElementVisible(cardHolderNameField);
	}

	public void enterCardHolderNameInTheField(String accountNumber) {
		sendKeys(cardHolderNameField, accountNumber);
		log.info("Entered account no in the field.");
	}

	public void clearCardHolderNameInTheField() {
		log.info("clear the payment amount.");
		clear(cardHolderNameField);
	}

	@FindBy(css = "#crdno")
	private WebElement cardNumberField;

	public boolean isCardNumberFieldVisible() {
		log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
				+ cardNumberField.isDisplayed());
		return isElementVisible(cardNumberField);
	}

	public void clearCardNumberInTheField() {
		log.info("clear the payment amount.");
		clear(cardNumberField);
	}

	public void enterCardNumberInTheField(String accountNumber) {
		sendKeys(cardNumberField, accountNumber);
		log.info("Entered account no in the field.");
	}

	@FindBy(css = "#expMOnth")
	private WebElement monthField;

	public boolean isMonthFieldVisible() {
		log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
				+ monthField.isDisplayed());
		return isElementVisible(monthField);
	}

	public void clickMonthFld() {
		scrollToElement(monthField);
		click(monthField);
		log.info("Click on Payment Amount Field Successfully .");
	}


	@FindBy(css = "#expYear")
	private WebElement yearField;

	public boolean isYearFieldVisible() {
		log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
				+ yearField.isDisplayed());
		return isElementVisible(yearField);
	}

	public void clickYearFld() {
		scrollToElement(yearField);
		click(yearField);
		log.info("Click on Payment Amount Field Successfully .");
	}

	@FindBy(css = "#Security")
	private WebElement securityField;

	public boolean isSecurityCodeFieldVisible() {
		log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
				+ securityField.isDisplayed());
		return isElementVisible(securityField);
	}

	public void enterSecurityCodeInTheField(String accountNumber) {
		sendKeys(securityField, accountNumber);
		log.info("Entered account no in the field.");
	}

	public void clearSecCodeInTheField() {
		log.info("clear the payment amount.");
		clear(securityField);
	}

	@FindBy(css = "#st2_firstname")
	private WebElement firstNameField;

	public boolean isFirstNameFieldVisible() {
		log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
				+ firstNameField.isDisplayed());
		return isElementVisible(firstNameField);
	}

	public void enterFirstNameInTheField(String accountNumber) {
		sendKeys(firstNameField, accountNumber);
		log.info("Entered account no in the field.");
	}

	@FindBy(css = "#st2_lastname")
	private WebElement lastNameField;

	public boolean isLastNameFieldVisible() {
		log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
				+ lastNameField.isDisplayed());
		return isElementVisible(lastNameField);
	}

	public void enterLastNameInTheField(String accountNumber) {
		sendKeys(lastNameField, accountNumber);
		log.info("Entered account no in the field.");
	}

	public void clearLastNameInTheField() {
		log.info("clear the payment amount.");
		clear(lastNameField);
	}

	@FindBy(css = "#st2_address")
	private WebElement addressField;

	public boolean isAddressFieldVisible() {
		log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
				+ addressField.isDisplayed());
		return isElementVisible(addressField);
	}

	public void enterAddressInTheField(String accountNumber) {
		sendKeys(addressField, accountNumber);
		log.info("Entered account no in the field.");
	}

	public void clearAddressInTheField() {
		log.info("clear the payment amount.");
		clear(addressField);
	}

	@FindBy(css = "#st2_city")
	private WebElement cityField;

	public boolean isCityFieldVisible() {
		log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
				+ cityField.isDisplayed());
		return isElementVisible(cityField);
	}

	public void enterCityInTheField(String accountNumber) {
		sendKeys(cityField, accountNumber);
		log.info("Entered account no in the field.");
	}

	public void clearCityInTheField() {
		log.info("clear the payment amount.");
		clear(cityField);
	}

	@FindBy(css = "#st2_state")
	private WebElement stateField;

	public boolean isStateFieldVisible() {
		log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
				+ stateField.isDisplayed());
		return isElementVisible(stateField);
	}

	public void enterStateInTheField(String accountNumber) {
		sendKeys(stateField, accountNumber);
		log.info("Entered account no in the field.");
	}

	public void clearStateInTheField() {
		log.info("clear the payment amount.");
		clear(stateField);
	}

	@FindBy(css = "#st2_zipcode")
	private WebElement zipField;

	public boolean isZipCodeFieldVisible() {
		log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
				+ zipField.isDisplayed());
		return isElementVisible(zipField);
	}

	public void enterZipCodeInTheField(String accountNumber) {
		sendKeys(zipField, accountNumber);
		log.info("Entered account no in the field.");
	}

	public void clearZipCodeInTheField() {
		log.info("clear the payment amount.");
		clear(zipField);
	}


	@FindBy(xpath = "//*[@id=\"expMOnth\"]/option[6]")
	private WebElement lnkmonth;

	public void clickMonthOption() {
		click(lnkmonth);
		log.info("Spanish language option clicked {}.");
	}

	@FindBy(xpath = "//*[@id=\"expYear\"]/option[3]")
	private WebElement lnkyear;

	public void clickYearOption() {
		click(lnkyear);
		log.info("Spanish language option clicked {}.");
	}

	@FindBy(xpath = "//*[@id=\"expYear\"]/option[2]")
	private WebElement lnkpastyear;

	public void clickPastYearOption() {
		click(lnkpastyear);
		log.info("Spanish language option clicked {}.");
	}

	@FindBy(xpath = "//*[@id=\"expYear\"]/option[1]")
	private WebElement lnkyearclear;

	public void clearYearOption() {
		click(lnkyearclear);
		log.info("Spanish language option clicked {}.");
	}

	@FindBy(xpath = "//*[@id=\"expMOnth\"]/option[1]")
	private WebElement lnkmonthclear;

	public void clearMonthOption() {
		click(lnkmonthclear);
		log.info("Spanish language option clicked {}.");
	}

	@FindBy(css = "#ImgVisa")
	private WebElement cardVisa;

	public boolean isCardNumberVisa() {
		log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
				+ cardVisa.isDisplayed());
		return isElementVisible(cardVisa);
	}

	@FindBy(css = "#ImgMaster")
	private WebElement cardMc;

	public boolean isCardNumberMc() {
		log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
				+ cardMc.isDisplayed());
		return isElementVisible(cardMc);
	}

	@FindBy(css = "#ImgDiscov")
	private WebElement cardDiscover;

	public boolean isCardNumberDiscover() {
		log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
				+ cardDiscover.isDisplayed());
		return isElementVisible(cardDiscover);
	}

	@FindBy(css = "#Imgamex")
	private WebElement cardAmex;

	public boolean isCardNumberAmex() {
		log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
				+ cardAmex.isDisplayed());
		return isElementVisible(cardAmex);
	}

	@FindBy(css = "#ImgJcb")
	private WebElement cardJcb;

	public boolean isCardNumberJcb() {
		log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
				+ cardJcb.isDisplayed());
		return isElementVisible(cardJcb);
	}

	@FindBy(css = "#ImgCup")
	private WebElement cardCup;

	public boolean isCardNumberCup() {
		log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
				+ cardCup.isDisplayed());
		return isElementVisible(cardCup);
	}

	public void clickPayBillLink() {
		// click(lnk_billing);
		clickWithJSExecutor(lnk_paybill);
		log.info("Pay Bill link clicked successfully.");
	}

	@FindBy(css = "#labelheader")
	private WebElement labelPayBill;

	public boolean isPayBillHeaderVisible() {
		return isElementVisible(labelPayBill);
	}

	public String getPayBillLabel() {
		String label = getText(labelPayBill);
		return label;
	}

	@FindBy(xpath = "//*[@id='stepperDiv']/ol")
	private WebElement labelPayBillStepOne;

	public boolean isPayBillStepOneTitleVisible() {
		return isElementVisible(labelPayBillStepOne);
	}

	public String getPayBillStepOne() {
		String label = getText(labelPayBillStepOne);
		return label;
	}

	@FindBy(xpath = "//*[@id='btnCancelPayment']")
	private WebElement cancelStpOneField;

	public boolean isCancelBttnVisible() {
		log.info("Checking that Cancel Bttn is visible on the PayBill Step 1 page." + cancelStpOneField.isDisplayed());
		return isElementVisible(cancelStpOneField);
	}

	public void clickCanclStpOneBttn() {
		click(cancelStpOneField);
		log.info("Click on Cancel Bttn Successfully .");

	}

	@FindBy(css = "#btnSubmitPayment")
	private WebElement nextStpOneField;

	public boolean isNextBttnVisible() {
		log.info("Checking that Next Bttn is visible on the PayBill Step 1 page." + nextStpOneField.isDisplayed());
		return isElementVisible(nextStpOneField);
	}

	public void clickSNextButton() {
		click(nextStpOneField);
		log.info("Click on next button Successfully .");
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[1]/ol")
	private WebElement labelPayBillStepTwo;

	public boolean isPayBillStepTwoTitleVisible() {
		return isElementVisible(labelPayBillStepOne);
	}

	public String getPayBillStepTwo() {
		String label = getText(labelPayBillStepOne);
		return label;
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[1]/div[1]/div/div[1]/ul/li[1]/label")
	private WebElement nameField;

	public boolean isNameFieldVisible() {
		log.info("Checking that Name field is visible on the PayBill Step 2 page." + nameField.isDisplayed());
		return isElementVisible(nameField);

	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[1]/div[1]/div/div[1]/ul/li[2]/label")
	private WebElement accountTwoField;

	public boolean isAccTwoFieldVisible() {
		log.info("Checking that Account field is visible on the PayBill Step 2 page." + accountTwoField.isDisplayed());
		return isElementVisible(accountTwoField);
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[1]/div[1]/div/div[1]/ul/li[3]/label")
	private WebElement amtDueTwoField;

	public boolean isAmtDueTwoFieldVisible() {
		log.info(
				"Checking that Amount Due field is visible on the PayBill Step 2 page." + amtDueTwoField.isDisplayed());
		return isElementVisible(amtDueTwoField);
	}

	@FindBy(css = "#st2_phonenumber")
	private WebElement primPhoneTwoField;

	public boolean isPrimPhTwoFieldVisible() {
		log.info("Checking that Primary Ph field is visible on the PayBill Step 2 page."
				+ primPhoneTwoField.isDisplayed());
		return isElementVisible(primPhoneTwoField);
	}

	@FindBy(css = "#st2_email")
	private WebElement emailAddTwoField;

	public boolean isEmailAddTwoFieldVisible() {
		log.info("Checking that Email Add field is visible on the PayBill Step 2 page."
				+ emailAddTwoField.isDisplayed());
		return isElementVisible(emailAddTwoField);
	}

	@FindBy(css = "#st2_amount")
	private WebElement payAmtTwoField;

	public boolean isPayAmtTwoFieldVisible() {
		log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
				+ payAmtTwoField.isDisplayed());
		return isElementVisible(payAmtTwoField);
	}

	public void clickPayAmtFld() {
		scrollToElement(payAmtTwoField);
		click(payAmtTwoField);
		log.info("Click on Payment Amount Field Successfully .");
	}

	public void clearPayAmtFld() {
		log.info("clear the payment amount.");
		clear(payAmtTwoField);
	}

	public void enterPayAmtInTheField(String paymentAmt) {
		sendKeys(payAmtTwoField, paymentAmt);
		log.info("Entered payment amount in the field.");
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[1]/div[1]/div/div[2]/h3[3]")
	private WebElement labelPayInfoStepTwo;

	public boolean isPayInfoStepTwoLableVisible() {
		return isElementVisible(labelPayInfoStepTwo);
	}

	public String getPayInfoStepTwo() {
		String label = getText(labelPayInfoStepTwo);
		return label;
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[1]/div[1]/div/div[2]/section/div[1]/label")
	private WebElement bankAccOptTwoField;

	public boolean isBankAccOptTwoVisible() {
		scrollToElement(bankAccOptTwoField);
		log.info("Checking that Bank Acc optn is visible on the PayBill Step 2 page."
				+ bankAccOptTwoField.isDisplayed());
		return isElementVisible(bankAccOptTwoField);
	}

	public void clickBankAccOptTwoStp() {
		scrollToElement(bankAccOptTwoField);
		clickWithJSExecutor(bankAccOptTwoField);
		log.info("Click on Bank Acc Opt Successfully .");
	}

	@FindBy(css = "#accholrnm")
	private WebElement accHoldNameField;

	public boolean isAccHoldNameField() {
		log.info("Checking that Acc Holder Name field is visible on the PayBill Step 2 page."
				+ accHoldNameField.isDisplayed());
		return isElementVisible(accHoldNameField);
	}

	public void clickAccHoldNameFld() {
		click(accHoldNameField);
		log.info("Click on Acc Holder Name Field Successfully .");

	}

	public void enterAccHolderNameFld(String accHoldName) {
		sendKeys(accHoldNameField, accHoldName);
		log.info("Entered Acc Holder Name in the field.");
	}

	@FindBy(css = "#rtno")
	private WebElement routingNoField;

	public boolean isRoutNoField() {
		log.info(
				"Checking that Routing No field is visible on the PayBill Step 2 page." + routingNoField.isDisplayed());
		return isElementVisible(routingNoField);
	}

	public void clickRoutNoFld() {
		click(routingNoField);
		log.info("Click on Routing No Field Successfully .");

	}

	public void enterRoutNoFld(String routNo) {
		sendKeys(routingNoField, routNo);
		log.info("Entered Routing No in the field.");
	}

	@FindBy(css = "#crtno")
	private WebElement confRoutingNoField;

	public boolean isCongRoutNoField() {
		log.info("Checking that Confirming Routing No field is visible on the PayBill Step 2 page."
				+ confRoutingNoField.isDisplayed());
		return isElementVisible(confRoutingNoField);
	}

	public void clickConfRoutNoFld() {
		click(confRoutingNoField);
		log.info("Click on Confirming Routing No Field Successfully .");

	}

	public void enterConfRoutNoFld(String confRoutNo) {
		sendKeys(confRoutingNoField, confRoutNo);
		log.info("Entered Confirm Routing No in the field.");
	}

	@FindBy(css = "#bnkaccno")
	private WebElement bnkAccNoField;

	public boolean isBnkAccNoField() {
		log.info("Checking that Bank Account No field is visible on the PayBill Step 2 page."
				+ bnkAccNoField.isDisplayed());
		return isElementVisible(bnkAccNoField);
	}

	public void clickBnkAccNoFld() {
		click(bnkAccNoField);
		log.info("Click on Bank Account No Field Successfully .");

	}

	public void enterBnkAccNoFld(String bnkAccNo) {
		sendKeys(bnkAccNoField, bnkAccNo);
		log.info("Entered Bank Account No in the field.");
	}

	@FindBy(css = "#bnkaccnomask")
	private WebElement confBnkAccNoField;

	public boolean isConfBnkAccNoField() {
		log.info("Checking that  Confirm Bank Account No field is visible on the PayBill Step 2 page."
				+ confBnkAccNoField.isDisplayed());
		return isElementVisible(confBnkAccNoField);
	}

	public void clickConfBnkAccNoFld() {
		click(confBnkAccNoField);
		log.info("Click on Confirm Bank Account No Field Successfully .");

	}

	public void enterConfBnkAccNoFld(String confBnkAccNo) {
		sendKeys(confBnkAccNoField, confBnkAccNo);
		log.info("Entered Confirm Bank Account No in the field.");
	}

	@FindBy(css = "#st2_accountType")
	private WebElement sltAccTypField;

	public boolean isSelectAccTypField() {
		log.info("Checking that Select Account Type field is visible on the PayBill Step 2 page."
				+ sltAccTypField.isDisplayed());
		return isElementVisible(sltAccTypField);
	}

	public void clickSelectAccTypFld() {
		click(sltAccTypField);
		log.info("Click on Select Account Type Field Successfully .");

	}

	@FindBy(css = "#st2_accountType > option:nth-child(2)")
	private WebElement optAccTypField;

	public boolean isOptAccTypField() {
		log.info("Checking that Option Account Type field is visible on the PayBill Step 2 page."
				+ optAccTypField.isDisplayed());
		return isElementVisible(optAccTypField);
	}

	public void clickOptAccTypFld() {
		click(optAccTypField);
		log.info("Click on Option Account Type Field Successfully .");
	}

	@FindBy(css = "#st2_firstname")
	private WebElement frstNameField;

	public boolean isFirstNameField() {
		log.info("Checking that First Name field is visible on the PayBill Step 2 page." + frstNameField.isDisplayed());
		return isElementVisible(frstNameField);
	}

	public void clickFirstNameFld() {
		click(frstNameField);
		log.info("Click on First Name Field Successfully .");

	}

	public void enterFirstNameFld(String frstName) {
		sendKeys(frstNameField, frstName);
		log.info("Entered First Name in the field.");
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[1]/div[2]/input[2]")
	private WebElement cancelStpTwoField;

	public boolean isCnclTwoStpBttnVisible() {
		log.info("Checking that Cancel Bttn is visible on the PayBill Step 2 page." + cancelStpTwoField.isDisplayed());
		return isElementVisible(cancelStpTwoField);
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[1]/div[2]/input[1]")
	private WebElement nextStpTwoField;

	public boolean isNextBttnTwoVisible() {
		log.info("Checking that Next Bttn is visible on the PayBill Step 2 page." + nextStpTwoField.isDisplayed());
		return isElementVisible(nextStpTwoField);
	}

	public void clickNxtTwoBttn() {
		click(nextStpTwoField);
		log.info("Click on Next Bttn Successfully .");
	}

	@FindBy(css = "#toast-container > div > div")
	private WebElement allMandErrorMessage;

	public String getallMandErrorMessage() {
		log.info("Fetching the all mandatory fields error message.");
		String label = getText(allMandErrorMessage);
		log.info("All Mandatory error message is {}: " + label);
		return label;
	}

	@FindBy(css = "#logincredentials > div:nth-child(2) > div > span.error_messagecommon")
	private WebElement primPhErrMessage;

	public String getprimaryPhErrorMessage() {
		log.info("Fetching the primary Ph error message.");
		String label = getText(primPhErrMessage);
		log.info("Primary Ph Error is {}: " + label);
		return label;
	}

	@FindBy(css = "#logincredentials > div:nth-child(1) > div > span.error_messagecommon")
	private WebElement accNoErrMessage;

	public String getacctNoErrorMessage() {
		log.info("Fetching the account no. error message.");
		String label = getText(accNoErrMessage);
		log.info("Account No. Error is {}: " + label);
		return label;
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[2]/div[1]/div[1]/div/h5")
	private WebElement accNoThreeField;

	public boolean isAccNoThreeFldVisible() {
		log.info("Checking that Account Number field is visible on the PayBill Step 3 page."
				+ accNoThreeField.isDisplayed());
		return isElementVisible(accNoThreeField);

	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/h5")
	private WebElement servaddThreeField;

	public boolean isServAddThreeFldVisible() {
		log.info("Checking that Service Address field is visible on the PayBill Step 3 page."
				+ servaddThreeField.isDisplayed());
		return isElementVisible(servaddThreeField);
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[2]/div[1]/div[3]/div/h5")
	private WebElement billAmtThreeField;

	public boolean isBillAmtThreeFldVisible() {
		log.info("Checking that Bill Amount field is visible on the PayBill Step 3 page."
				+ billAmtThreeField.isDisplayed());
		return isElementVisible(billAmtThreeField);
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[2]/div[1]/div[4]/div/h5")
	private WebElement transFeeThreeField;

	public boolean isTransFeeThreeFldVisible() {
		log.info("Checking that Transaction Fees field is visible on the PayBill Step 3 page."
				+ transFeeThreeField.isDisplayed());
		return isElementVisible(transFeeThreeField);
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[2]/div[1]/div[5]/div/h5")
	private WebElement transAmtThreeField;

	public boolean isTransAmtThreeFldVisible() {
		log.info("Checking that Transaction Amount field is visible on the PayBill Step 3 page."
				+ transAmtThreeField.isDisplayed());
		return isElementVisible(transAmtThreeField);
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[2]/div[1]/div[6]/div/h5")
	private WebElement payDateThreeField;

	public boolean ispayDateThreeFldVisible() {
		log.info("Checking that Payment Date field is visible on the PayBill Step 3 page."
				+ payDateThreeField.isDisplayed());
		return isElementVisible(payDateThreeField);
	}

	@FindBy(xpath = "//*[@id='payment_type']")
	private WebElement bnkThreeField;

	public boolean isBankThreeFldVisible() {
		log.info("Checking that Bank field is visible on the PayBill Step 3 page." + bnkThreeField.isDisplayed());
		return isElementVisible(bnkThreeField);
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[2]/div[3]/input[2]")
	private WebElement backStpThreeBtn;

	public boolean isBackThreeStpBtnVisible() {
		log.info("Checking that Back Bttn is visible on the PayBill Step 3 page." + backStpThreeBtn.isDisplayed());
		return isElementVisible(backStpThreeBtn);
	}

	public void clickBackThreeBtn() {
		click(backStpThreeBtn);
		log.info("Click on Back Button Successfully .");
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[2]/div[3]/input[1]")
	private WebElement submitStpThreeBtn;

	public boolean isSubmitBtnThreeVisible() {
		log.info("Checking that Submit Bttn is visible on the PayBill Step 2 page." + submitStpThreeBtn.isDisplayed());
		return isElementVisible(submitStpThreeBtn);
	}

	public void clickSubmitThreeBtn() {
		click(submitStpThreeBtn);
		log.info("Click on Submit Button Successfully .");
	}

	@FindBy(css = ".dlg-action.ok-action")
	private WebElement submitStpAlertYesBtn;

	public void clickYesAlertBtn() {
		click(submitStpAlertYesBtn);
		log.info("Click on Submit Button Successfully .");
	}

	@FindBy(xpath = "//*[@id='transFeeLimit']")
	private WebElement discOneLableThreeStp;

	public String getDiscOneLblThreeMessage() {
		log.info("Fetching the disclaimer 1 lable message.");
		String label = getText(discOneLableThreeStp);
		log.info("Disclaimer 1 message is {}: " + label);
		return label;

	}

	@FindBy(xpath = "/html/body/form/section[1]/div/div/div[1]/div/section/div/div[3]/div/div[2]/div/div[1]/div[1]/span")
	private WebElement labelPaySuccessFinal;

	public boolean isPaySuccessVisible() {
		return isElementVisible(labelPaySuccessFinal);
	}

	public String getPaySuccessLabel() {
		String label = getText(labelPaySuccessFinal);
		return label;
	}

	@FindBy(css = "#accholrnm")
	private WebElement lbl_accountholdername;

	public String getAccountHolderName() {
		String label = getAttribute(lbl_accountholdername, "value");
		log.info("Forgot Username Label {}: " + label);
		return label;
	}

	@FindBy(css = "#nmcrd")
	private WebElement lbl_cardholdername;

	public String getCardHolderName() {
		String label = getAttribute(lbl_cardholdername, "value");
		log.info("Forgot Username Label {}: " + label);
		return label;
	}

	@FindBy(css = "#nmcrd")
	private WebElement lbl_useremail;

	public String getUserEmail() {
		String label = getAttribute(lbl_useremail, "value");
		log.info("Forgot Username Label {}: " + label);
		return label;
	}

	@FindBy(css = "#st2_email")
	private WebElement emailNumberField;

	public boolean isEmailFieldVisible() {
		log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
				+ emailNumberField.isDisplayed());
		return isElementVisible(emailNumberField);
	}

	public void clearEmailInTheField() {
		log.info("clear the payment amount.");
		clear(emailNumberField);
	}

	public void enterEmailInTheField(String accountNumber) {
		sendKeys(emailNumberField, accountNumber);
		log.info("Entered account no in the field.");
	}

	@FindBy(xpath = "//*[@id=\"containerDiv\"]/div[3]/div/div[1]/div/div[2]/div[1]/div[1]/div/div[2]/div[2]/span[2]")
	private WebElement labelInvalidEmail;

	public String getInvalidEmailLabelTwo() {
		String label = getText(labelInvalidEmail);
		return label;
	}

	@FindBy(xpath = "//*[@class='error'][contains(text(),'Please enter a valid 10-digit Phone Number..')]")
	private WebElement labelInvalidPhone;

	public String getInvalidPhoneLabelTwo() {
		String label = getText(labelInvalidPhone);
		return label;
	}

	@FindBy(css = "#st2_phonenumber")
	private WebElement phoneNumberField;

	public boolean isPhoneFieldVisible() {
		log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
				+ phoneNumberField.isDisplayed());
		return isElementVisible(phoneNumberField);
	}

	public void clearPhoneInTheField() {
		log.info("clear the payment amount.");
		clear(phoneNumberField);
	}

	public void enterPhoneInTheField(String accountNumber) {
		sendKeys(phoneNumberField, accountNumber);
		log.info("Entered account no in the field.");
	}

}

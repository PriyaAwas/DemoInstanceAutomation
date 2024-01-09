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

public class GuestUserPage extends HomePage {
	private static final Logger log = LogManager.getLogger(GuestUserPage.class);

	public GuestUserPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "a[href='InviteGuestUser.aspx']")
	private WebElement lnkGuestUserSideMenu;

	@FindBy(css = "#lblMessage")
	private WebElement labelExpiryUser;

	public String getExpiryUserLabel() {
		String label = getText(labelExpiryUser);
		log.info("Label Expiry {}: " + label);
		return label;
	}

	@FindBy(css = "a#lnkInviteUser")
	private WebElement lnkInviteUser;

	public void clickOnInviteUser() {
		click(lnkInviteUser);
		log.info("Invite User link clicked successfully.");
	}

	@FindBy(css = "select#drpInviteAccountNumber")
	private WebElement ddlAccountNumber;

	public boolean isAccountDropdownVisible() {
		log.info("Checking the visibility of Service Account Number on the page.");
		log.info("Service Account Number visibility status {}: " + isElementVisible(ddlAccountNumber));
		return isElementVisible(ddlAccountNumber);
	}

	public void selectServiceAccountnumber(String ServiceAccountNumber) {
		selectByVisibleText(ddlAccountNumber, ServiceAccountNumber);
		log.info("Selected Service Account Number " + ServiceAccountNumber);
	}

	public String getselectServiceAccountnumber() {
		String options = getSelectedValueInDropBox(ddlAccountNumber);
		log.info("Selected Service Account Number ");
		return options;
	}

	public void selectServiceAccountnumberByIndex(int index) {

		selectByIndex(ddlAccountNumber, index);
		log.info("Selected Service Account Number " + index);
	}

	@FindBy(css = "#txtInviteUserName")
	private WebElement txtInviteUserName;

	public Boolean isInviteUserNameVisible() {
		log.info("Checking the visibility of Invite User Name field on the page.");
		log.info("Invite User Name Field visibility status : " + isElementVisible(txtInviteUserName));
		return isElementVisible(txtInviteUserName);
	}

	public void populateGuestUserName(String GuestUserName) {
		log.info("Populating Guest User Name{} :" + GuestUserName);
		sendKeys(txtInviteUserName, GuestUserName);
		log.info("Guest User Name populated successfully.");
	}

	@FindBy(css = "#ddlInviteRole")
	private WebElement ddlInviteRole;

	public boolean isInviteRoleVisible() {
		log.info("Checking the visibility of Invite Role on the page.");
		log.info("Invite Role visibility status {}: " + isElementVisible(ddlInviteRole));
		return isElementVisible(ddlInviteRole);
	}

	public void selectGuestUserRole(String GuestUserRole) {
		selectByVisibleText(ddlInviteRole, GuestUserRole);
		log.info("Selected Guest User Role " + GuestUserRole);
	}

	public void selectGuestUserRoleByIndex(int index) {
		selectByIndex(ddlInviteRole, index);
		log.info("Selected Guest User Role " + index);
	}

	@FindBy(css = "input#txtInviteEmail")
	private WebElement txtInviteEmailId;

	public Boolean isGuestUserEmailIdVisible() {
		log.info("Checking the visibility of Invite Guest User Email Id field on the page.");
		log.info("Guest User Email Id Field visibility status : " + isElementVisible(txtInviteEmailId));
		return isElementVisible(txtInviteEmailId);
	}

	public void populateGuestUserEmailId(String GuestUserEmailId) {
		log.info("Populating Guest User Email Id{} :" + GuestUserEmailId);
		sendKeys(txtInviteEmailId, GuestUserEmailId);
		log.info("Guest User Email Id populated successfully.");
	}

	@FindBy(css = "input#txtInviteAccessPeriod")
	private WebElement txtInviteAccessPeriod;

	public Boolean isAccessPeriodVisible() {
		log.info("Checking the visibility of Invite Access Period field on the page.");
		log.info(
				"Guest User Invite Access Period Field visibility status : " + isElementVisible(txtInviteAccessPeriod));
		return isElementVisible(txtInviteAccessPeriod);
	}

	public void isAccessPeriodClickable() {
		log.info("Checking the Guest User's Access Period clickable or not");
		waitForElementToBeClickable(txtInviteAccessPeriod);
	}

	public void populateAccessPeriod(String InviteAccessPeriod) {
		log.info("Populating Guest user invite access period {} :" + InviteAccessPeriod);
		enterTextUsingJsExecutor(txtInviteAccessPeriod, InviteAccessPeriod);
		log.info("Guest User invite access period populated successfully.");
	}

	@FindBy(css = "#btnInviteUser")
	private WebElement btnInviteUser;

	public Boolean isInviteUserBtnVisible() {
		log.info("Checking that the Invite user field is visible on the login page.");
		return isElementVisible(btnInviteUser);
	}

	public void isSubmitButtonClickable() {
		log.info("Checking the Submit Button clickable or not");
		waitForElementToBeClickable(btnInviteUser);
	}

	public void clickSubmitButton() {
		waitForElementToBeClickable(btnInviteUser);
		click(btnInviteUser);
		log.info("Invite User  Submit Button Successfully Clicked");
	}

	@FindBy(css = "input#Button2")
	private WebElement btnCancel;

	public Boolean isCancelBtnVisible() {
		log.info("Checking that the Cancel field is visible on the login page.");
		return isElementVisible(btnCancel);
	}

	public void iscancelButtonClickable() {
		log.info("Checking the Cancel Button clickable or not");
		waitForElementToBeClickable(btnCancel);
	}

	public void clickCancelButton() {
		waitForElementToBeClickable(btnCancel);
		click(btnCancel);
		log.info("Invite Guest User Cancel Button Successfully Clicked");
	}

	@FindBy(css = "i.resend")
	private WebElement btnResend;

	@FindBy(css = ".editaccount img[src='images/edit.png']")
	private WebElement btnEditAccount;
	@FindBy(css = ".deleteaccount")
	private WebElement btnDeleteAccount;
	@FindBy(css = "#divinviteUsermodal > .modal-header h4")
	private WebElement lblInviteUser;

	public String getLabelInviteUser() {
		log.info("Fetching the Invite user.");
		String label = getText(lblInviteUser);
		log.info("Invite user label is {}: " + label);
		return label;
	}

	public boolean isLabelInviteUserVisible() {
		log.info("Fetching the Invite user.");
		log.info("Checking that the Invite user is visible on the login page.");
		return isElementVisible(lblInviteUser);

	}

	public void clickLabelInviteUser() {
		log.info("Clicking the sign in button.");
		clickWithJSExecutor(lblInviteUser);
		log.info("Sign in button clicked successfully.");
	}

	@FindBy(css = ".toast-message")
	private WebElement txtMszSuccessfulInvited;
	@FindBy(css = "#w2ui-tag-txtInviteUserName")
	private WebElement lblBlankAccountName;
	@FindBy(css = ".w2ui-tag-body")
	private WebElement lblErrorMsgInvalidEntry;
	@FindBy(css = "[id='Td4'] label[id*='lblutilityRoles']")
	private WebElement lblStatusValue;
	@FindBy(css = ".para_txt p")
	private WebElement lblInviteGuestUserMsg;
	@FindBy(xpath = "//*[@class='profile-details my_acc_tbl pro_add']//th[contains(text(),'Action')]")
	private WebElement clmnAction;

	public Boolean isClmnActionVisible() {
		log.info("Checking that the action field is visible on the login page.");
		return isElementVisible(clmnAction);
	}

	@FindBy(xpath = "//*[@id='lblutilityRoles1']")
	private WebElement clmnStatus;

	public Boolean isClmnStatusVisible() {
		log.info("Checking that the status field is visible on the login page.");
		return isElementVisible(clmnStatus);
	}

	@FindBy(xpath = "//div[@class='listing_wrapper']//span[contains(text(),'Service Account Number')]")
	private WebElement clmnServiceAccountNumber;

	public Boolean isClmnServiceAccountNumberVisible() {
		log.info("Checking that the Service account number field is visible on the login page.");
		return isElementVisible(clmnStatus);
	}

	@FindBy(xpath = "//div[@class='listing_wrapper']//label[contains(@id,'lblpropertyaddress_')]")
	private WebElement clmnName;

	public Boolean isClmnNameVisible() {
		log.info("Checking that the name field is visible on the login page.");
		return isElementVisible(clmnName);
	}

	@FindBy(xpath = "//div[@class='listing_wrapper']//strong[contains(text(),'Role')]")
	private WebElement clmnRole;

	public Boolean isClmnRoleVisible() {
		log.info("Checking that the Role field is visible on the login page.");
		return isElementVisible(clmnRole);
	}

	@FindBy(xpath = "//div[@class='listing_wrapper']//label[contains(@id,'lblutilitynumber_')][1]")
	private WebElement clmnEmail;

	public Boolean isClmnEmailVisible() {
		log.info("Checking that the Email field is visible on the login page.");
		return isElementVisible(clmnEmail);
	}

	@FindBy(xpath = "//div[@class='listing_wrapper']//span[contains(@globalize,'ML_Setting_Lbl_Plan')]")
	private WebElement clmnInvitationDate;

	public Boolean isClmnInvitationDateVisible() {
		log.info("Checking that the Invitation Date field is visible on the login page.");
		return isElementVisible(clmnInvitationDate);
	}

	@FindBy(xpath = "//div[@class='listing_wrapper']//span[contains(@globalize,'ML_Role')]")
	private WebElement clmnExpirationDate;

	public Boolean isClmnExpirationDateVisible() {
		log.info("Checking that the Expiration Date Date field is visible on the login page.");
		return isElementVisible(clmnExpirationDate);
	}

	@FindBy(xpath = "//tr/td")
	private WebElement clmnTestD;
	@FindBy(css = "[for=chkterm] .mdl-checkbox__focus-helper")
	private WebElement chkBoxAgreeTe;

	public void checkchkBoxAgreeTeBtn() {
		log.info("Clicking the term and condition button.");
		clickWithJSExecutor(chkBoxAgreeTe);
		log.info("term and condition checked successfully.");
	}

	@FindBy(css = "#drpInviteAccountNumber")
	private WebElement lstBoxServiceAc;

	public List<String> getAllOptionsinListBox() {
		log.info("getting all the invite account number options");
		List<String> listAccountNumberOpt = getAllOptionsInListBox(lstBoxServiceAc);
		return listAccountNumberOpt;
	}

	@FindBy(css = "#divinviteUsermodal > .modal-header button")
	private WebElement btnCloseInviteUserPopup;

	public Boolean isCloseInviteUserPopupVisible() {
		log.info("Close Invite User Popup field is visible on the login page.");
		return isElementVisible(btnCloseInviteUserPopup);
	}

	public void clickCloseInviteUserPopupBtn() {
		log.info("Clicking the Close Invite User Popup button.");
		click(btnCloseInviteUserPopup);
		log.info("Close Invite User Popup button clicked successfully.");
	}

	@FindBys(@FindBy(xpath = "//div[@id='RegisClass']//div[contains(@class,'input_box_eff_wrap')]//label"))
	private List<WebElement> lblNewGuestUserRegFields;

	public List<WebElement> getObjectsLabelNewGuestUserRegFields() {
		return lblNewGuestUserRegFields;
	}

	@FindBys(@FindBy(xpath = "//div[@id='RegisClass']//div[contains(@class,'input_box_eff_wrap')]/input[not(contains(@style, 'none'))]"))
	private List<WebElement> txtNewGuestUserRegFields;

	public List<WebElement> getObjectsTextNewGuestUserRegFields() {
		return txtNewGuestUserRegFields;
	}

	@FindBys(@FindBy(xpath = "//div[@id='RegisClass']//div[contains(@class,'input_box_eff_wrap')]/select"))
	private List<WebElement> ddNewGuestUserRegFields;

	public List<WebElement> getObjectsddNewGuestUserRegFields() {
		return ddNewGuestUserRegFields;
	}

	@FindBy(css = "#NextBtn")
	private WebElement btnRegisterNewGuest;

	public Boolean isRegisterNewGuestVisible() {
		log.info("New Guest Registration visible status {}: ");
		return isElementVisible(btnRegisterNewGuest);
	}

	public void clickRegisterNewGuestBtn() {
		log.info("Clicking the sign in button.");
		click(btnRegisterNewGuest);
		log.info("New Guest registration button clicked successfully.");
	}

	@FindBy(css = "#btnCancel")
	private WebElement btnCancelNewGuestReg;
	@FindBy(css = "#btnNxt")
	private WebElement btnNextNewGuestReg;

	public Boolean isNextNewGuestRegVisible() {
		log.info("New Guest Registration next visible status {}: ");
		return isElementVisible(btnNextNewGuestReg);
	}

	public void clickNextNewGuestRegistrationBtn() {
		log.info("Clicking the next new guest registration button.");
		click(btnNextNewGuestReg);
		log.info("New Guest registration button clicked successfully.");
	}

	@FindBy(css = "[globalize='ML_CONNECTME_Lbl_Terms'] [type ='checkbox']")
	private WebElement chkBoxTermsAndConditions;

	public void checkCheckBoxTermsAndConditions() {
		check(chkBoxTermsAndConditions);
		log.info("Term and conditions checkbox checked.");
	}

	@FindBys(@FindBy(css = "[id^='lbldRole']"))
	private List<WebElement> tdMyAccountRole;

	public List<WebElement> TdMyAccountRole() {
		return tdMyAccountRole;
	}

	@FindBys(@FindBy(css = "[id^='lbldhome']"))
	private List<WebElement> tdServiceAccountNomap;

	public List<WebElement> TdServiceAccountNomap() {
		return tdServiceAccountNomap;
	}

	@FindBys(@FindBy(css = ".listing_wrapper p:nth-chilD(6) label"))
	private List<WebElement> tdServiceAccountNo;

	public List<WebElement> getObjecttdServiceAccountNo() {
		return tdServiceAccountNo;
	}

	@FindBys(@FindBy(css = ".listing_wrapper p:nth-chilD(4) label"))
	private List<WebElement> tdEmailAddress;

	public List<WebElement> getObjecttdEmailAddress() {
		return tdEmailAddress;
	}

	@FindBy(css = ".my_account_table td .deleteaccount")
	private WebElement tdDeleteAction;
	@FindBy(css = ".resendaccount a i")
	private WebElement icoResendInvitation;
	@FindBy(xpath = "//button[@class='closepopup']/following-sibling::h4")
	private WebElement lblEditInvitePopupHeader;
	@FindBy(css = "#collapseAccount .InviteUser_ico")
	private WebElement lnkContainerGuestUser;
	@FindBy(css = ".bgtHdng")
	private WebElement lblBudgetBillPageHeadin;
	@FindBys(@FindBy(xpath = "//div[@class='listing_wrapper']"))
	private List<WebElement> tabsGuestUserDetails;

	public List<WebElement> getListTabsGuestUserDetails() {
		return tabsGuestUserDetails;
	}

	@FindBys(@FindBy(xpath = "//div[@class='listing_wrapper']/div/i"))
	private List<WebElement> btnToggleThreeDots;

	public List<WebElement> getListBtnToggleThreeDots() {
		return btnToggleThreeDots;
	}

	@FindBys(@FindBy(xpath = "(//a/span[contains(text(),'Remove')])[1]"))
	private List<WebElement> btnRemoveGuestAccount;

	public List<WebElement> getBtnRemoveGuestAccount() {
		return btnRemoveGuestAccount;
	}

	@FindBy(xpath = "//*[@id='DeleteInviteGuestUser']/div/div/div[2]")
	private WebElement confirmationPopupMsg;

	public String getConfirmationPopupMsg() {
		log.info("Fetching the confirmation popup message.");
		String label = getText(confirmationPopupMsg);
		log.info("Confirmation poup message is {}: " + label);
		return label;
	}

	@FindBy(css = "#btnInviteGuestUserDelete")
	private WebElement btnContinueConfirmationMsg;

	public void clickContinueConfirmationMsgBtn() {
		log.info("Clicking the Continue Confirmation Message button.");
		click(btnContinueConfirmationMsg);
		log.info("Sign in button clicked successfully.");
	}

	@FindBy(css = "[globalize='ML_MYACCOUNT_NotificationPreference']")
	private WebElement lnkNotificationPrefSideMenu;

	public void getBtnThreeDots(String sAccountNumber) {
		driver.findElement(By.xpath("//label[@id='lblAccountNo_" + sAccountNumber + "']/../../div/i")).click();

	}

	public WebElement btnResendInvitationIcon(String sAccountNumber) {
		By btnResendInvitationIcon = By
				.xpath("//span[@id='" + sAccountNumber + "']/a/span[text()='Resend Activation']");

		return driver.findElement(btnResendInvitationIcon);

	}

	public void clickBtnResendInvitationIcon(String sAccountNumber) {
		By btnResendInvitationIcon = By
				.xpath("//span[@id='" + sAccountNumber + "']/a/span[text()='Resend Activation']");

		driver.findElement(btnResendInvitationIcon).click();

	}

	public String getStatusOfInvitation(String sAccountNumber) {
		By statusOfInvitation = By
				.xpath("//label[@id='lblAccountNo_" + sAccountNumber + "']/../..//span[@id='lblutilityRoles1']");
		return driver.findElement(statusOfInvitation).getText();
	}

	public WebElement btnRemoveGuestAccount(String sAccountNumber) {
		By btnRemoveGuestAccount = By
				.xpath("//span[@id='" + sAccountNumber + "']/a/span[contains(text(),'Remove')]/..");
		return driver.findElement(btnRemoveGuestAccount);
	}
}

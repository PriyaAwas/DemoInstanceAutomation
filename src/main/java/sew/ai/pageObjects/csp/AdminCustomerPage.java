package sew.ai.pageObjects.csp;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import sew.ai.pageObjects.scp.HomePage;

public class AdminCustomerPage extends HomePage {

	private static final Logger log = LogManager.getLogger(AdminCustomerPage.class);

	public AdminCustomerPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".sidebar_ipinfo a")
    private WebElement lnkSideBarLockedCustomer;
	//By lnkSideBarLockedCustomer = By.cssSelector(Utils.getRbPropValue("lnkSideBarLockedCustomerAcp"));
	
	@FindBy(css = ".customtablefooterarea [href*='BlockedIP']")
    private WebElement lnkSideBarBlockedIp;
	//By lnkSideBarBlockedIp = By.cssSelector(Utils.getRbPropValue("lnkSideBarBlockedIpAcp"));
	public boolean isLnkSideBarBlockedIpDisplayed() {
		if (lnkSideBarBlockedIp.isDisplayed()) {
			log.info(lnkSideBarBlockedIp.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(lnkSideBarBlockedIp + " is not Displayed on the Page");
			return false;
		}
	}
	public void clickLnkSideBarBlockedIp() {
        click(lnkSideBarBlockedIp);
        waitForImgCustomerDetailsLoadingToInvisible();
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(xpath = "//li[@title='Customers']//a")
    private WebElement lnkCustomer;
	//By lnkCustomer = By.xpath(Utils.getRbPropValue("lnkSideBarCustomerAcp"));
	
	@FindBy(xpath = "//span[text()='Add User']")
    private WebElement lnkAddCustomer;
	//By lnkAddCustomer = By.xpath(Utils.getRbPropValue("lnkSideBarAddCustomerAcp"));
	public void explicitWaitForLnkAddCustomer() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.visibilityOf(lnkAddCustomer));
		} catch (NoSuchElementException e) {
			log.error("No Element Found after wait" + e);
		}
	}
	public void clickLnkAddCustomer() {
        click(lnkAddCustomer);
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(xpath = "//*[@class='menu-mavigation']")
    private WebElement lnkMenu;
	//By lnkMenu = By.xpath(Utils.getRbPropValue("lnkSideMenuAcp"));
	
	@FindBy(css = ".sidebar_startstopservice a")
    private WebElement lnkStartStopServices;
	//By lnkStartStopServices = By.cssSelector(Utils.getRbPropValue("lnkStartStopServicesAcp"));
	
	@FindBy(css = ".sidebar_onetimepayment a")
    private WebElement lnkOneTimePayment;
	//By lnkOneTimePayment = By.cssSelector(Utils.getRbPropValue("lnkOneTimePaymentAcp"));
	
	@FindBy(css = ".sidebar_servicerequestscp a")
    private WebElement lnkServiceRequest;
	//By lnkServiceRequest = By.cssSelector(Utils.getRbPropValue("lnkServiceRequestAcp"));
	
	@FindBy(css = ".sidebar_dashboardpayment a")
    private WebElement lnkPaymentDashBoard;
	//By lnkPaymentDashBoard = By.cssSelector(Utils.getRbPropValue("lnkPaymentDashBoardAcp"));
	
	@FindBy(css = ".sidebar_browsechat a")
    private WebElement lnkCoBrowseChart;
	//By lnkCoBrowseChart = By.cssSelector(Utils.getRbPropValue("lnkCoBrowseChartAcp"));
	
	@FindBy(css = ".sidebar_portfolioManagement a")
    private WebElement lnkPortfolioManagement;
	//By lnkPortfolioManagement = By.cssSelector(Utils.getRbPropValue("lnkPortfolioManagementAcp"));
	
	@FindBy(xpath = "//*[text()='SERVICE ACCOUNTS']/..//a")
    private WebElement lblActiveUser;
	//By lblActiveUser = By.xpath(Utils.getRbPropValue("lblActiveUserCountAcp"));
	
	@FindBy(xpath = "//*[@id='lblInactiveCount']")
    private WebElement lblInActiveUser;
	//By lblInActiveUser = By.xpath(Utils.getRbPropValue("lblInActiveUserCountAcp"));
	
	@FindBy(xpath = "//*[@id='lblNotRegisterCount']")
    private WebElement lblNotRegisteredUser;
	//By lblNotRegisteredUser = By.xpath(Utils.getRbPropValue("lblNotregisteredUserCountAcp"));
	
	@FindBy(xpath = "//*[@id='lblRegisterCount']")
    private WebElement lblRegisteredUser;
	//By lblRegisteredUser = By.xpath(Utils.getRbPropValue("lblRegisteredUserCountAcp"));
	
	@FindBy(xpath = "//div[@class='customtable']//tbody//tr[@class='ng-scope']")
    private WebElement listCustomer;
	//By listCustomer = By.xpath(Utils.getRbPropValue("listCustomerCountAcp"));
	
	@FindBys(@FindBy(css = ".topsectionbox .sectionbox_list1 a"))
    private List<WebElement> lblCountTotal;
	//By lblCountTotal = By.cssSelector(Utils.getRbPropValue("lblCountTotalAcp"));
	public List<WebElement> getLblCountTotal(){
		return lblCountTotal;
	}
	
	@FindBys(@FindBy(css = ".topsectionbox .sectionbox_list2 a"))
    private List<WebElement> lblCountTotalActive;
	//By lblCountTotalActive = By.cssSelector(Utils.getRbPropValue("lblCountTotalActiveAcp"));
	public List<WebElement> getLblCountTotalActive(){
		return lblCountTotalActive;
	}
	
	@FindBys(@FindBy(css = ".topsectionbox .sectionbox_list3 a"))
    private List<WebElement> lblCountTotalInActive;
	//By lblCountTotalInActive = By.cssSelector(Utils.getRbPropValue("lblCountTotalInActiveAcp"));
	public List<WebElement> getLblCountTotalInActive(){
		return lblCountTotalInActive;
	}
	
	@FindBy(css = "#txtBasicSearch")
    private WebElement txtSearchCustomer;
	//By txtSearchCustomer = By.cssSelector(Utils.getRbPropValue("txtSearchCustomerAcp"));
	public void waitForTxtSearchCustomer(){
        waitForElementToBeVisible(txtSearchCustomer);
        log.info("CSR WorkBench is Visible on the page.");
    }
	public void populateTxtSearchCustomer(String data) {
        log.info("Enter account number {} :" + data);
        clear(txtSearchCustomer);
        sendKeys(txtSearchCustomer, data);
        log.info("Account number entered successfully.");
    }
	public boolean isTxtSearchCustomerDisplayed() {
		if (txtSearchCustomer.isDisplayed()) {
			log.info(txtSearchCustomer.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(txtSearchCustomer + " is not Displayed on the Page");
			return false;
		}
	}
	
	@FindBy(css = "#BtnFilternew")
    private WebElement btnSearch;
	//By btnSearch = By.cssSelector(Utils.getRbPropValue("btnSearchAcp"));
	public void clickBtnSearch() {
        click(btnSearch);
        waitForImgCustomerDetailsLoadingToInvisible();
        log.info("Search Button clicked successfully.");
    }
	public boolean isBtnSearchDisplayed() {
		if (btnSearch.isDisplayed()) {
			log.info(btnSearch.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(btnSearch + " is not Displayed on the Page");
			return false;
		}
	}
	
	@FindBy(xpath = "//a[@id='btnSend']")
    private WebElement btnSendNotification;
	//By btnSendNotification = By.xpath(Utils.getRbPropValue("btnSendNotificationAcp"));
	public boolean isBtnSendNotificationDisplayed() {
		if (btnSendNotification.isDisplayed()) {
			log.info(btnSendNotification.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(btnSendNotification + " is not Displayed on the Page");
			return false;
		}
	}
	public void clickBtnSendNotification() {
        click(btnSendNotification);
        log.info("Forgot Password Link clicked successfully.");
    }
	
	@FindBy(xpath = "//a[text()='Export']")
    private WebElement btnExport;
	//By btnExport = By.xpath(Utils.getRbPropValue("btnExportAcp"));
	public void clickBtnExport() {
        click(btnExport);
        log.info("SignIn Button clicked successfully.");
    }
	public void explicitWaitForBtnExport() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.visibilityOf(btnExport));
		} catch (NoSuchElementException e) {
			log.error("No Element Found after wait" + e);
		}
	}
	
	@FindBy(xpath = "//input[@type='radio'][@title='Residential']")
    private WebElement rdoBtnResidential;
	//By rdoBtnResidential = By.xpath(Utils.getRbPropValue("rdoBtnResidentialAcp"));
	
	@FindBy(xpath = "//input[@type='radio'][@title='Commercial']")
    private WebElement rdoBtnCommercial;
	//By rdoBtnCommercial = By.xpath(Utils.getRbPropValue("rdoBtnCommercialAcp"));
	
	@FindBy(xpath = "//input[@id='btnCustomerType']")
    private WebElement btnNextCustomerType;
	//By btnNextCustomerType = By.xpath(Utils.getRbPropValue("btnCustomerTypePopUpNextAcp"));
	
	@FindBy(xpath = "//input[@mappingname='UtilityAccountNumber'][contains(text(),'Account Number')]")
    private WebElement txtAccountNumber;
	//By txtAccountNumber = By.xpath(Utils.getRbPropValue("txtAccountNumberAcp"));
	
	@FindBy(xpath = "//input[@mappingname='PostalCode'][contains(text(),'Zip Code')]")
    private WebElement txtzipCode;
	//By txtzipCode = By.xpath(Utils.getRbPropValue("txtZipCodeAcp"));
	
	@FindBy(xpath = "//input[@mappingname='EmailID'][contains(text(),'Email Address')]")
    private WebElement txtEmailID;
	//By txtEmailID = By.xpath(Utils.getRbPropValue("txtEmailIDAcp"));
	
	@FindBy(xpath = "//input[@mappingname='MeterNumber'][contains(text(),'Meter Number')]")
    private WebElement txtMeterNumber;
	//By txtMeterNumber = By.xpath(Utils.getRbPropValue("txtMeterNumberAcp"));
	
	@FindBy(xpath = "//input[@id='btnNxt']")
    private WebElement btnNextFirstPge;
	//By btnNextFirstPge = By.xpath(Utils.getRbPropValue("btnNextFirstPageAcp"));
	
	@FindBy(xpath = "//input[@id='btnPrev']")
    private WebElement btnPreviousFirstPage;
	//By btnPreviousFirstPage = By.xpath(Utils.getRbPropValue("btnPreviousFirstPageAcp"));
	
	@FindBy(xpath = "//input[@mappingname='UserName']")
    private WebElement txtUserName;
	//By txtUserName = By.xpath(Utils.getRbPropValue("txtUserNameAcp"));
	
	@FindBy(xpath = "//input[@mappingname='MobileNumber']")
    private WebElement txtMobileNumber;
	//By txtMobileNumber = By.xpath(Utils.getRbPropValue("txtMobileNoAcp"));
	
	@FindBy(xpath = "//input[@mappingname='HintAns']")
    private WebElement txtHintAns1;
	//By txtHintAns1 = By.xpath(Utils.getRbPropValue("txtHintAns1Acp"));
	
	@FindBy(xpath = "//input[@mappingname='HintsAns2']")
    private WebElement txtHintAns2;
	//By txtHintAns2 = By.xpath(Utils.getRbPropValue("txtHintAns2Acp"));
	
	@FindBy(xpath = "//div[@id='serAdvSearch']//label[text()='First Name:']/following-sibling::input")
    private WebElement txtFirstName;
	//By txtFirstName = By.xpath(Utils.getRbPropValue("txtFirstNameAccountServiceAcp"));
	
	@FindBy(xpath = "//div[@id='serAdvSearch']//label[text()='Last Name:']/following-sibling::input")
    private WebElement txtLastName;
	//By txtLastName = By.xpath(Utils.getRbPropValue("txtLastNameServiceAccountAcp"));
	
	@FindBy(xpath = "//input[@mappingname='SSNNumber']")
    private WebElement txtSSN;
	//By txtSSN = By.xpath(Utils.getRbPropValue("txtSSNAcp"));
	
	@FindBy(xpath = "//input[@mappingname='DrivingLicence']")
    private WebElement txtDrivingLicence;
	//By txtDrivingLicence = By.xpath(Utils.getRbPropValue("txtDrivingLicenceAcp"));
	
	@FindBy(xpath = "//input[@mappingname='StreetNumber']")
    private WebElement txtStreetName;
	//By txtStreetName = By.xpath(Utils.getRbPropValue("txtStreetNameAcp"));
	
	@FindBy(xpath = ".registration_btn[name=\"NextBtn\"]")
    private WebElement btnRegister;
	//By btnRegister = By.xpath(Utils.getRbPropValue("btnRegisterAcp"));
	
	@FindBy(xpath = "//select[@mappingname='SecurityQuestionId']")
    private WebElement ddlSecurityQuest1;
	//By ddlSecurityQuest1 = By.xpath(Utils.getRbPropValue("ddlSecurityQuest1Acp"));
	
	@FindBy(xpath = "//select[@mappingname='SecurityQuestionId2']")
    private WebElement ddlSecurityQuest2;
	//By ddlSecurityQuest2 = By.xpath(Utils.getRbPropValue("ddlSecurityQuest2Acp"));
	
	@FindBy(xpath = "//select[@ng-model='params.pageSize']")
    private WebElement ddlPageSize;
	//By ddlPageSize = By.xpath(Utils.getRbPropValue("ddlPageSizeAcp"));
	public boolean isDdlPageSizeDisplayed() {
		if (ddlPageSize.isDisplayed()) {
			log.info(ddlPageSize.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(ddlPageSize + " is not Displayed on the Page");
			return false;
		}
	}
	public WebElement getDdlPageSize() {
		return ddlPageSize;
	}
	private WebElement btnSignIn;
    public void clickDdlPageSize() {
        click(ddlPageSize);
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(xpath = "//*[@id='customtable_next']")
    private WebElement btnPageNext;
	//By btnPageNext = By.xpath(Utils.getRbPropValue("btnPageNextAcp"));
	public boolean isBtnPageNextDisplayed() {
		if (btnPageNext.isDisplayed()) {
			log.info(btnPageNext.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(btnPageNext + " is not Displayed on the Page");
			return false;
		}
	}
	public WebElement getBtnPageNext() {
		return btnPageNext;
	}
	
	@FindBy(xpath = "//*[@id='customtable_previous']")
    private WebElement btnPagePrevious;
	//By btnPagePrevious = By.xpath(Utils.getRbPropValue("btnPagePreviousAcp"));
	public boolean isBtnPagePreviousDisplayed() {
		if (btnPagePrevious.isDisplayed()) {
			log.info(btnPagePrevious.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(btnPagePrevious + " is not Displayed on the Page");
			return false;
		}
	}
	public WebElement getBtnPagePrevious() {
		return btnPagePrevious;
	}
	
	@FindBy(css = ".tooltiptd .material-icons")
    private WebElement lnkActionSCMTab;
	//By lnkActionSCMTab = By.cssSelector(Utils.getRbPropValue("lnkActionSCMTabAcp"));
	public void explicitWaitForLnkActionSCMTab() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.visibilityOf(lnkActionSCMTab));
		} catch (NoSuchElementException e) {
			log.error("No Element Found after wait" + e);
		}
	}
	public void clickLnkActionSCMTab() {
        click(lnkActionSCMTab);
        log.info("Forgot Password Link clicked successfully.");
    }
	public WebElement getlnkActionSCMTab() {
		return lnkActionSCMTab;
	}
	
	@FindBys(@FindBy(css = ".customtable  .tableUser tbody tr:nth-child(1) td:nth-child(1) li a"))
    private List<WebElement> lstActionsSCMActiveUsersTab;
	//By lstActionsSCMActiveUsersTab = By.cssSelector(Utils.getRbPropValue("lstActionsSCMActiveUsersTabAcp"));
	public List<WebElement> getLstActionsSCMActiveUsersTab() {
		return lstActionsSCMActiveUsersTab;
	}
	
	@FindBy(xpath = "//a[text()='Edit Record']")
    private WebElement lnkEditRecordActionUsers;
	//By lnkEditRecordActionUsers = By.xpath(Utils.getRbPropValue("lnkEditRecordActionUsersAcp"));
	public boolean isLnkEditRecordActionUsersDisplayed() {
		if (lnkEditRecordActionUsers.isDisplayed()) {
			log.info(lnkEditRecordActionUsers.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(lnkEditRecordActionUsers + " is not Displayed on the Page");
			return false;
		}
	}
	
	@FindBy(xpath = "//span[text()='Activate User']")
    private WebElement lnkActivateUserActionUsers;
	//By lnkActivateUserActionUsers = By.xpath(Utils.getRbPropValue("lnkActivateUserActionUsersAcp"));
	public boolean isLnkActivateUserActionUsersDisplayed() {
		if (lnkActivateUserActionUsers.isDisplayed()) {
			log.info(lnkActivateUserActionUsers.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(lnkActivateUserActionUsers + " is not Displayed on the Page");
			return false;
		}
	}
	
	@FindBy(xpath = "//span[text()='Resend Activation Link']")
    private WebElement lnkResendActivationLinkActionUsers;
	//By lnkResendActivationLinkActionUsers = By.xpath(Utils.getRbPropValue("lnkResendActivationLinkActionUsersAcp"));
	public boolean isLnkResendActivationLinkActionUsersDisplayed() {
		if (lnkResendActivationLinkActionUsers.isDisplayed()) {
			log.info(lnkResendActivationLinkActionUsers.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(lnkResendActivationLinkActionUsers + " is not Displayed on the Page");
			return false;
		}
	}
	public void explicitWaitForLnkResendActivationLinkActionUsers() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.visibilityOf(lnkResendActivationLinkActionUsers));
		} catch (NoSuchElementException e) {
			log.error("No Element Found after wait" + e);
		}
	}
	public void clickLnkResendActivationLinkActionUsers() {
        click(lnkResendActivationLinkActionUsers);
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(xpath = "//div[@id='page_loader']")
    private WebElement divPageLoad;
	//By divPageLoad = By.xpath(Utils.getRbPropValue("divPageLoadAcp"));
	
	@FindBy(xpath = "//div[@id='page_loaderpopup']")
    private WebElement divPageLoadPopUp;
	//By divPageLoadPopUp = By.xpath(Utils.getRbPropValue("divPageLoadPopUpAcp"));
	
	@FindBy(xpath = "//ul[@id='ulCustomerDetail']//li")
    private WebElement liUserGridFirst;
	//By liUserGridFirst = By.xpath(Utils.getRbPropValue("liUserGridFirstAcp"));
	
	@FindBy(xpath = "//*[@id=\"CustomerSection\"]/div/table[3]/tbody/tr[1]/td[1]/div/div/center/ul/li[2]/a")
    private WebElement lnkLockFirstCustomer;
	//By lnkLockFirstCustomer = By.xpath(Utils.getRbPropValue("lnkLockFirstCustomerAcp"));
	public void clickLnkLockFirstCustomer() {
        click(lnkLockFirstCustomer);
        log.info("Forgot Password Link clicked successfully.");
    }
	
	@FindBy(css = "#btnsaveLockStatus")
    private WebElement btnProceedLockUserPopup;
	//By btnProceedLockUserPopup = By.cssSelector(Utils.getRbPropValue("btnProceedLockUserPopupAcp"));
	public void explicitWaitForBtnProceedLockUserPopup() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.visibilityOf(btnProceedLockUserPopup));
		} catch (NoSuchElementException e) {
			log.error("No Element Found after wait" + e);
		}
	}
	public void clickBtnProceedLockUserPopup() {
        click(btnProceedLockUserPopup);
        log.info("Forgot Password Link clicked successfully.");
    }
	
	@FindBy(xpath = "//*[@id='CustomerSection']/div/table[3]/tbody/tr[1]/td[1]/div/div/center/ul/li[2]/a")
    private WebElement lnkUnLockFirstCustomer;
	//By lnkUnLockFirstCustomer = By.xpath(Utils.getRbPropValue("lnkUnLockFirstCustomerAcp"));
	
	@FindBy(xpath = "//input[@id='chkall']")
    private WebElement chkboxAllCheck;
	//By chkboxAllCheck = By.xpath(Utils.getRbPropValue("chkboxAllCheckAcp"));
	public WebElement getChkboxAllCheck() {
		return chkboxAllCheck;
	}
	
	@FindBy(xpath = "//select[@id='ddltypeofmessage']")
    private WebElement ddlTypeOfMessage;
	//By ddlTypeOfMessage = By.xpath(Utils.getRbPropValue("ddlTypeOfMessageAcp"));
	public void explicitWaitForDdlTypeOfMessage() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.visibilityOf(ddlTypeOfMessage));
		} catch (NoSuchElementException e) {
			log.error("No Element Found after wait" + e);
		}
	}
	public WebElement getDdlTypeOfMessage() {
		return ddlTypeOfMessage;
	}
	
	@FindBy(xpath = "//select[@id='ddlMessageMode']")
    private WebElement ddlMessageMode;
	//By ddlMessageMode = By.xpath(Utils.getRbPropValue("ddlMessageModeAcp"));
	public WebElement getDdlMessageMode() {
		return ddlMessageMode;
	}
	
	@FindBy(xpath = "//input[@id='txtmsgsubject']")
    private WebElement txtMessageReason;
	//By txtMessageReason = By.xpath(Utils.getRbPropValue("txtMessageReasonAcp"));
	public void clearTxtMessageReasonField() {
        clear(txtMessageReason);
        log.info("Password field cleared {}");
    }
    public void populateTxtMessageReason(String password) {
        log.info("Populating password {} :" + password);
        sendKeys(txtMessageReason, password);
        log.info("Password populated successfully.");
    }
	
	@FindBy(xpath = "//div[@class='note-editable']")
    private WebElement txtAreaMessageBody;
	//By txtAreaMessageBody = By.xpath(Utils.getRbPropValue("txtAreaMessageBodyAcp"));
	public void clearTxtAreaMessageBodyField() {
        clear(txtAreaMessageBody);
        log.info("Password field cleared {}");
    }
    public void populateTxtAreaMessageBody(String password) {
        log.info("Populating password {} :" + password);
        sendKeys(txtAreaMessageBody, password);
        log.info("Password populated successfully.");
    }
    
    @FindBy(css = "#fileupd")
    private WebElement btnChooseFileMail;                           
    public void populateBtnChooseFileMail(String userName) {
        log.info("Populating username {} :" + userName);
        sendKeys(btnChooseFileMail, userName);
        log.info("Username populated successfully.");
    }		
	
	@FindBy(css = "#txtMessage")
    private WebElement txtBoxRoboMessage;
	//By txtBoxRoboMessage = By.cssSelector(Utils.getRbPropValue("txtBoxRoboMessageAcp"));
	
	@FindBy(xpath = "//input[@id='btnSubmitReply']")
    private WebElement btnSendMessage;
	//By btnSendMessge = By.xpath(Utils.getRbPropValue("btnSendMessgeAcp"));
	public WebElement getBtnSendMessage() {
		return btnSendMessage;
	}
	public void clickBtnSendMessage() {
        click(btnSendMessage);
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(css = "#lblFileAllowExtension")
    private WebElement txtAllowedFileTypesCSRSendNotification;
	//By txtAllowedFileTypesCSRSendNotification = By.cssSelector(Utils.getRbPropValue("txtAllowedFileTypesCSRSendNotificationAcp"));
	
	@FindBy(xpath = "//a[@class='excel_icon']")
    private WebElement lnkExcelReport;
	//By lnkExcelReport = By.xpath(Utils.getRbPropValue("lnkExcelReportAcp"));
	public void explicitWaitForLnkExcelReport() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.visibilityOf(lnkExcelReport));
		} catch (NoSuchElementException e) {
			log.error("No Element Found after wait" + e);
		}
	}
	public void clickLnkExcelReport() {
        click(lnkExcelReport);
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(xpath = "//button[@class='close']")
    private WebElement btnClosePopUp;
	//By btnClosePopUp = By.xpath(Utils.getRbPropValue("btnClosePopUpAcp"));
	
	@FindBy(xpath = "//*[@id='bill_pay']//a")
    private WebElement lnkBillingTab;
	//By lnkBillingTab = By.xpath(Utils.getRbPropValue("lnkBillingTabAcp"));
	public Boolean isLnkBillingTabVisible() {
        log.info("Checking the visibility of Billing tab on the page.");
        log.info("Billing tab visibility status {}: " + isElementVisible(lnkBillingTab));
        return isElementVisible(lnkBillingTab);
    }
	public void clickBillingTab() {
        click(lnkBillingTab);
        log.info("Billing tab is clicked successfully.");
    }
	
	@FindBy(xpath = "//li[@id='UsageDiv']//a")
    private WebElement lnkUsageTab;
	//By lnkUsageTab = By.xpath(Utils.getRbPropValue("lnkUsageTabAcp"));
	public Boolean isLnkUsageTabVisible() {
        log.info("Checking the visibility of Usage tab on the page.");
        log.info("Usage tab visibility status {}: " + isElementVisible(lnkUsageTab));
        return isElementVisible(lnkUsageTab);
    }
	
	@FindBy(xpath = "//li[@id='CompareMeDiv']//a")
    private WebElement lnkCompareTab;
	//By lnkCompareTab = By.xpath(Utils.getRbPropValue("lnkCompareTabAcp"));
	public Boolean isLnkCompareTabVisible() {
        log.info("Checking the visibility of Compare tab on the page.");
        log.info("Compare tab visibility status {}: " + isElementVisible(lnkCompareTab));
        return isElementVisible(lnkCompareTab);
    }
	
	@FindBy(xpath = "//li[@id='Rebateprogram']//a")
    private WebElement lnkEfficiencyTab;
	//By lnkEfficiencyTab = By.xpath(Utils.getRbPropValue("lnkEfficiencyTabAcp"));
	public Boolean isLnkEfficiencyTabVisible() {
        log.info("Checking the visibility of Efficiency tab on the page.");
        log.info("Efficiency tab visibility status {}: " + isElementVisible(lnkEfficiencyTab));
        return isElementVisible(lnkEfficiencyTab);
    }
	public void clickLnkEfficiencyTab() {
        click(lnkEfficiencyTab);
        log.info("Efficiency clicked successfully.");
    }
	
	@FindBy(xpath = "//li[@id='primary']//a")
    private WebElement lnkProfileTab;
	//By lnkProfileTab = By.xpath(Utils.getRbPropValue("lnkProfileTabAcp"));
	public Boolean isLnkProfileTabVisible() {
        log.info("Checking the visibility of Profile tab on CSR 360");
        log.info("Profile tab visibility status {}: " + isElementVisible(lnkProfileTab));
        return isElementVisible(lnkProfileTab);
    }
	public void clickProfileTab() {
        click(lnkProfileTab);
        log.info("ProfileTab clicked successfully.");
    }
	
	@FindBy(xpath = "//li[@id='EVDiv']/a")
    private WebElement lnkEVTab;
	//By lnkEVTab = By.xpath(Utils.getRbPropValue("lnkEVTabAcp"));
	public Boolean isLnkEVTabVisible() {
        log.info("Checking the visibility of EV tab on CSR 360");
        log.info("EV tab visibility status {}: " + isElementVisible(lnkEVTab));
        return isElementVisible(lnkEVTab);
    }
	
	@FindBy(xpath = "//li[@id='OutageDiv']/a")
    private WebElement lnkOutageTab;
	//By lnkOutageTab = By.xpath(Utils.getRbPropValue("lnkOutageTabAcp"));
	public Boolean isLnkOutageTabVisible() {
        log.info("Checking the visibility of EV tab on CSR 360");
        log.info("EV tab visibility status {}: " + isElementVisible(lnkOutageTab));
        return isElementVisible(lnkOutageTab);
    }
	public void clickOutageTab() {
        click(lnkOutageTab);
        log.info("Outage tab is clicked successfully.");
    }
	
	@FindBy(xpath = "//li[@id='AutoPayDiv']/a")
    private WebElement lnkAutoPayTab;
	//By lnkAutoPayTab = By.xpath(Utils.getRbPropValue("lnkAutoPayTabAcp"));
	
	@FindBy(xpath = "//li[@id='connectMeRequest']/a")
    private WebElement lnkConnectMeRequestTab;
	//By lnkConnectMeRequestTab = By.xpath(Utils.getRbPropValue("lnkConnectMeRequestTabAcp"));
	public Boolean isLnkConnectMeRequestTabVisible() {
        log.info("Checking the visibility of ConnectMe tab on the page.");
        log.info("ConnectMe tab visibility status {}: " + isElementVisible(lnkConnectMeRequestTab));
        return isElementVisible(lnkConnectMeRequestTab);
    }
	
	@FindBy(xpath = "//li[@id='serviceRequest']/a")
    private WebElement lnkServiceRequestTab;
	//By lnkServiceRequestTab = By.xpath(Utils.getRbPropValue("lnkServiceRequestTabAcp"));
	public Boolean isLnkServiceRequestTabVisible() {
        log.info("Checking the visibility of Service Request tab on the page.");
        log.info("Service Request tab visibility status {}: " + isElementVisible(lnkServiceRequestTab));
        return isElementVisible(lnkServiceRequestTab);
    }
	
	@FindBy(xpath = "//li[@id='UserInteractionDiv']/a")
    private WebElement lnkUserInteractionTab;
	//By lnkUserInteractionTab = By.xpath(Utils.getRbPropValue("lnkUserInteractionTabAcp"));
	public Boolean isLnkUserInteractionTabVisible() {
        log.info("Checking the visibility of Interaction tab on the page.");
        log.info("Interaction tab visibility status {}: " + isElementVisible(lnkUserInteractionTab));
        return isElementVisible(lnkUserInteractionTab);
    }
	public void clickLnkUserInteractionTab() {
        click(lnkUserInteractionTab);
        log.info("Forgot Password Link clicked successfully.");
    }
	
	@FindBy(xpath = "//li[@id='OtherAccountDetails']/a")
    private WebElement lnkUserGuestTab;
	//By lnkUserGuestTab = By.xpath(Utils.getRbPropValue("lnkUserGuestTabAcp"));
	public Boolean isLnkUserGuestTabVisible() {
        log.info("Checking the visibility of UserGuest tab on the page.");
        log.info("UserGuest tab visibility status {}: " + isElementVisible(lnkUserGuestTab));
        return isElementVisible(lnkUserGuestTab);
    }
	public void clickLnkUserGuestTab() {
        click(lnkUserGuestTab);
        log.info("Guest user tab is clicked successfully.");
    }
	
	@FindBy(xpath = "//li[@id='notifications']/a")
    private WebElement lnkUserNotificationTab;
	//By lnkUserNotificationTab = By.xpath(Utils.getRbPropValue("lnkUserNotificationTabAcp"));
	public Boolean isLnkUserNotificationTabVisible() {
        log.info("Checking the visibility of Notifications tab on the page.");
        log.info("Notifications tab visibility status {}: " + isElementVisible(lnkUserNotificationTab));
        return isElementVisible(lnkUserNotificationTab);
    }
	
	@FindBy(xpath = "//li[@id='billpaymentDetails']//a")
    private WebElement lnkPaymentSubTab;
	//By lnkPaymentSubTab = By.xpath(Utils.getRbPropValue("lnkPaymentsSubTabAcp"));
	public Boolean isLnkPaymentSubTabVisible() {
        log.info("Checking the visibility of Payment tab on the page.");
        log.info("Payment tab visibility status {}: " + isElementVisible(lnkPaymentSubTab));
        return isElementVisible(lnkPaymentSubTab);
    }
	public void explicitWaitForLnkPaymentSubTab() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.visibilityOf(lnkPaymentSubTab));
		} catch (NoSuchElementException e) {
			log.error("No Element Found after wait" + e);
		}
	}
	public void clickLnkPaymentSubTab() {
        click(lnkPaymentSubTab);
        log.info("Forgot Password Link clicked successfully.");
    }
	
	@FindBy(xpath = "//li[@id='billpaybillingDetails']//a")
    private WebElement lnkBillingStatementSubTab;
	//By lnkBillingStatementSubTab = By.xpath(Utils.getRbPropValue("lnkBillingStatementSubTabAcp"));
	public Boolean isLnkBillingStatementSubTabVisible() {
        log.info("Checking the visibility of Billing sub tab on the page.");
        log.info("Billing tab visibility status {}: " + isElementVisible(lnkBillingStatementSubTab));
        return isElementVisible(lnkBillingStatementSubTab);
    }
	
	@FindBy(xpath = "//li[@id='property']/a")
    private WebElement lnkpropertyTab;
	//By lnkpropertyTab = By.xpath(Utils.getRbPropValue("lnkpropertyTabAcp"));
	public Boolean isLnkpropertyTabVisible() {
        log.info("Checking the visibility of Profile tab on CSR 360");
        log.info("Profile tab visibility status {}: " + isElementVisible(lnkpropertyTab));
        return isElementVisible(lnkpropertyTab);
    }
	public void clickLnkpropertyTab() {
        click(lnkpropertyTab);
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(xpath = "//div[@id='DivBasicDetails']")
	private WebElement lblPropertyDetail;
	public WebElement getLblPropertyDetail() {
		return lblPropertyDetail;
	}
	
	@FindBy(xpath = "//select[@id='usagetypepopup']")
    private WebElement ddlUsagePopUp;
	//By ddlUsagePopUp = By.xpath(Utils.getRbPropValue("ddlUsageTypeAcp"));
	
	@FindBy(xpath = "//select[@id='ddlMultiMeter']")
    private WebElement ddlMultiMeter;
	//By ddlMultiMeter = By.xpath(Utils.getRbPropValue("ddlMultiMeterAcp"));
	
	@FindBy(xpath = "//a[@id='lnkExporttoExcel']")
    private WebElement lnkExportToExcelUsage;
	//By lnkExportToExcelUsage = By.xpath(Utils.getRbPropValue("lnkExportToExcelUsageAcp"));
	
	@FindBy(xpath = "//ul[@id='duarationmode']//a[@mode='H']")
    private WebElement lnkHourlyUsage;
	//By lnkHourlyUsage = By.xpath(Utils.getRbPropValue("lnkHourlyUsageAcp"));
	
	@FindBy(xpath = "//ul[@id='duarationmode']//a[@mode='D']")
    private WebElement lnkDailyUsage;
	//By lnkDailyUsage = By.xpath(Utils.getRbPropValue("lnkDailyUsageAcp"));
	
	@FindBy(xpath = "//ul[@id='duarationmode']//a[@mode='M']")
    private WebElement lnkMonthlyUsage;
	//By lnkMonthlyUsage = By.xpath(Utils.getRbPropValue("lnkMonthlyUsageAcp"));
	
	@FindBy(xpath = "//ul[@id='duarationmode']//a[@mode='S']")
    private WebElement lnkSeasonalyUsage;
	//By lnkSeasonalyUsage = By.xpath(Utils.getRbPropValue("lnkSeasonalUsageAcp"));
	
	@FindBy(xpath = "//ul[@id='unitmode']//a[contains(@id,'aUsageElectric')][@mode='D']")
    private WebElement lnkUnitInDollar;
	//By lnkUnitInDollar = By.xpath(Utils.getRbPropValue("lnkUnitInDollarAcp"));
	
	@FindBy(xpath = "//ul[@id='unitmode']//a[@id='aUsageElectrickWh']")
    private WebElement lnkUnitInCCF;
	//By lnkUnitInCCF = By.xpath(Utils.getRbPropValue("lnkUnitInCCFAcp"));
	
	@FindBy(xpath = "//ul[@id='unitmode']//a[@id='aUsageElectrickWh']")
    private WebElement lnkUnitInKwh;
	//By lnkUnitInKwh = By.xpath(Utils.getRbPropValue("lnkUnitInKwhAcp"));
	
	@FindBy(xpath = "//ul[@id='unitmode']//a[@id='aUsageElectrickWh']")
    private WebElement lnkUnitInHCF;
	//By lnkUnitInHCF = By.xpath(Utils.getRbPropValue("lnkUnitInHCFAcp"));
	
	@FindBy(xpath = "//ul[@id='unitmode']//a[@id='aUsageWaterGl']")
    private WebElement lnkUnitInGal;
	//By lnkUnitInGal = By.xpath(Utils.getRbPropValue("lnkUbitInGalAcp"));
	
	@FindBy(xpath = "//ul[@id='duarationmode']//a[@mode='MI']")
    private WebElement lnkDurationMode15Min;
	//By lnkDurationMode15Min = By.xpath(Utils.getRbPropValue("lnkDurationMode15MinAcp"));
	
	@FindBy(xpath = "//ul[@id='duarationmode']//a[@mode='H']")
    private WebElement lnkDurationModeHourly;
	//By lnkDurationModeHourly = By.xpath(Utils.getRbPropValue("lnkDurationModeHourlyAcp"));
	
	@FindBy(xpath = "//ul[@id='duarationmode']//a[@mode='D']")
    private WebElement lnkDurationModeDaily;
	//By lnkDurationModeDaily = By.xpath(Utils.getRbPropValue("lnkDurationModeDailyAcp"));
	
	@FindBy(xpath = "//ul[@id='duarationmode']//a[@mode='M']")
    private WebElement lnkDurationModeMonthly;
	//By lnkDurationModeMonthly = By.xpath(Utils.getRbPropValue("lnkDurationModeMonthlyAcp"));
	
	@FindBy(xpath = "//ul[@id='duarationmode']//a[@mode='S']")
    private WebElement lnkDurationModeSeasonal;
	//By lnkDurationModeSeasonal = By.xpath(Utils.getRbPropValue("lnkDurationModeSeasonalAcp"));
	
	@FindBy(xpath = "//*[@class='highcharts-axis-title']")
    private WebElement lblUnitsConsumedOnUsageTab;
	//By lblUnitsConsumedOnUsageTab = By.xpath(Utils.getRbPropValue("lblUnitsConsumedOnUsageTabAcp"));
	
	@FindBy(css = ".filterBtn.btn_search.advncebtnlnk")
    private WebElement btnAdvanceSearch;
	//By btnAdvanceSearch = By.cssSelector(Utils.getRbPropValue("btnAdvanceSearchAcp"));
	public void explicitWaitForbtnAdvanceSearch() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.visibilityOf(btnAdvanceSearch));
		} catch (NoSuchElementException e) {
			log.error("No Element Found after wait" + e);
		}
	}
	public Boolean isBtnAdvanceSearchVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(btnAdvanceSearch));
        return isElementVisible(btnAdvanceSearch);
    }
	public void clickBtnAdvanceSearch() {
        click(btnAdvanceSearch);
        waitForImgCustomerDetailsLoadingToInvisible();
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(xpath = "//div[@id='serAdvSearch']//div[label='Account Type:']/select")
    private WebElement ddlAccountServiceAccountType;
	//By ddlAccountServiceAccountType = By.xpath(Utils.getRbPropValue("ddlAccountServiceAccountTypeAcp"));
	public Boolean isDdlAccountServiceAccountTypeVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(ddlAccountServiceAccountType));
        return isElementVisible(ddlAccountServiceAccountType);
    }
	public WebElement getDdlAccountServiceAccountType() {
		return ddlAccountServiceAccountType;
	}
	
	@FindBy(xpath = "//div[@id='serAdvSearch']//div[label='Account Status:']/select")
    private WebElement ddlAccountStatus;
	//By ddlAccountServiceAccountType = By.xpath(Utils.getRbPropValue("ddlAccountServiceAccountTypeAcp"));
	public Boolean isDdlAccountStatusVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(ddlAccountStatus));
        return isElementVisible(ddlAccountStatus);
    }
	
	
	@FindBy(xpath = "//div[@id='cusAdvSearch']//div[label='Account Type:']/select")
    private WebElement ddlAccountTypeCustomer;
	//By ddlAccountTypeCustomer = By.xpath(Utils.getRbPropValue("ddlAccountTypeCustomerAcp"));
	public Boolean isDdlAccountTypeCustomerVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(ddlAccountTypeCustomer));
        return isElementVisible(ddlAccountTypeCustomer);
    }
	public WebElement getDdlAccountTypeCustomer() {
		return ddlAccountTypeCustomer;
	}
	
	@FindBy(xpath = "//div[@id='scmUsrAdvSearch']//div[label='Account Type:']/select")
    private WebElement ddlAccountTypeUser;
	//By ddlAccountTypeUser = By.xpath(Utils.getRbPropValue("ddlAccountTypeUserAcp"));
	public Boolean isDdlAccountTypeUserVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(ddlAccountTypeUser));
        return isElementVisible(ddlAccountTypeUser);
    }
	public WebElement getDdlAccountTypeUser() {
		return ddlAccountTypeUser;
	}
	
	@FindBy(xpath = "//div[label='Status:']/select")
    private WebElement ddlStatusUsers;
	//By ddlAccountTypeUser = By.xpath(Utils.getRbPropValue("ddlAccountTypeUserAcp"));
	public Boolean isDdlStatusUsersVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(ddlStatusUsers));
        return isElementVisible(ddlStatusUsers);
    }
	
	@FindBy(css = "#scmUsrAdvSearch select[ng-options*='SearchOptions.status']")
    private WebElement ddlStatus;
	//By ddlStatus = By.cssSelector(Utils.getRbPropValue("ddlStatusAcp"));
	public WebElement getDdlStatus() {
		return ddlStatus;
	}
	
	@FindBy(xpath = "//div[@id='serAdvSearch']//div[label='Account Status:']/select")
    private WebElement ddlStatusService;
	//By ddlStatusService = By.xpath(Utils.getRbPropValue("ddlStatusServiceAcp"));
	public WebElement getDdlStatusService() {
		return ddlStatusService;
	}
	
	@FindBy(xpath = "//div[@id='scmUsrAdvSearch']//div[label='Account Status:']/select")
    private WebElement ddlStatusUser;
	//By ddlStatusUser = By.xpath(Utils.getRbPropValue("ddlStatusUserAcp"));
	
	@FindBy(xpath = "//div[label='User Type:']/select")
    private WebElement ddlUserType;
	//By ddlUserType = By.xpath(Utils.getRbPropValue("ddlUserTypeAcp"));
	public WebElement getDdlUserType() {
		return ddlUserType;
	}
	
	@FindBy(xpath = "//div[@id='serAdvSearch']//label[text()='Customer Number:']/following-sibling::input")
    private WebElement txtCustomerNumberServiceAccount;
	//By txtCustomerNumberServiceAccountAcp = By.xpath(Utils.getRbPropValue("txtCustomerNumberServiceAccountAcp"));
	public Boolean isTxtCustomerNumberServiceAccountVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtCustomerNumberServiceAccount));
        return isElementVisible(txtCustomerNumberServiceAccount);
    }
	public WebElement getTxtCustomerNumberServiceAccount() {
		return txtCustomerNumberServiceAccount;
	}
	
	@FindBy(xpath = "//div[@id='cusAdvSearch']//label[text()='Customer Number:']/following-sibling::input")
    private WebElement txtCustomerNumberCustomer;
	//By txtCustomerNumberCustomerAcp = By.xpath(Utils.getRbPropValue("txtCustomerNumberCustomerAcp"));
	public Boolean isTxtCustomerNumberCustomerVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtCustomerNumberCustomer));
        return isElementVisible(txtCustomerNumberCustomer);
    }
	public WebElement getTxtCustomerNumberCustomer() {
		return txtCustomerNumberCustomer;
	}
	
	@FindBy(xpath = "//div[@id='scmUsrAdvSearch']//label[text()='Customer Number:']/following-sibling::input")
    private WebElement txtCustomerNumberUser;
	//By txtCustomerNumberUserAcp = By.xpath(Utils.getRbPropValue("txtCustomerNumberUserAcp"));
	public Boolean isTxtCustomerNumberUserVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtCustomerNumberUser));
        return isElementVisible(txtCustomerNumberUser);
    }
	public WebElement getTxtCustomerNumberUser() {
		return txtCustomerNumberUser;
	}
	
	@FindBy(xpath = "//div[@id='serAdvSearch']//label[text()='Contact Number:']/following-sibling::input")
    private WebElement txtContactNoServiceAccount;
	//By txtContactNoServiceAccount = By.xpath(Utils.getRbPropValue("txtContactNoServiceAccountAcp"));
	
	@FindBy(xpath = "//div[@id='cusAdvSearch']//label[text()='Contact Number:']/following-sibling::input")
    private WebElement txtContactNoCustomer;
	//By txtContactNoCustomer = By.xpath(Utils.getRbPropValue("txtContactNoCustomerAcp"));
	public WebElement getTxtContactNoCustomer() {
		return txtContactNoCustomer;
	}
	
	@FindBy(xpath = "//div[@id='scmUsrAdvSearch']//label[text()='Contact Number:']/following-sibling::input")
    private WebElement txtContactNoUser;
	//By txtContactNoUser = By.xpath(Utils.getRbPropValue("txtContactNoUserAcp"));
	public WebElement getTxtContactNoUser() {
		return txtContactNoUser;
	}
	
	@FindBy(xpath = "//div[label='No. of Linked Users:']/select")
    private WebElement ddlNoOfLinkedUser;
	//By ddlNoOfLinkedUser = By.xpath(Utils.getRbPropValue("ddlNoOfLinkedUserAcp"));
	public Boolean isDdlNoOfLinkedUserVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(ddlNoOfLinkedUser));
        return isElementVisible(ddlNoOfLinkedUser);
    }
	public WebElement getDdlNoOfLinkedUser() {
		return ddlNoOfLinkedUser;
	}
	
	@FindBy(xpath = "//div[@id='scmUsrAdvSearch']//div[label='No. of Linked Users:']/select")
    private WebElement ddlNoOfLinkedAccountUser;
	//By ddlNoOfLinkedAccountUser = By.xpath(Utils.getRbPropValue("ddlNoOfLinkedAccountUserAcp"));
	
	@FindBy(xpath = "//div[@id='serAdvSearch']//div[label='No. of Linked Users:']/select")
    private WebElement ddlNoOfLinkedAccountService;
	//By ddlNoOfLinkedAccountService = By.xpath(Utils.getRbPropValue("ddlNoOfLinkedAccountServiceAcp"));
	public WebElement getDdlNoOfLinkedAccountService() {
		return ddlNoOfLinkedAccountService;
	}
	
	@FindBy(xpath = "//div[@id='serAdvSearch']//div[label='Opted for Paperless :']/select")
    private WebElement ddlPaperLess;
	//By ddlPaperLess = By.xpath(Utils.getRbPropValue("ddlPaperLessAcp"));
	public Boolean isDdlPaperLessVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(ddlPaperLess));
        return isElementVisible(ddlPaperLess);
    }
	public WebElement getDdlPaperLess() {
		return ddlPaperLess;
	}
	
	@FindBy(xpath = "//div[@id='serAdvSearch']//label[text()='ZIP Code:']/following-sibling::input")
    private WebElement txtZipcodeServiceAccount;
	//By txtZipcodeServiceAccount = By.xpath(Utils.getRbPropValue("txtZipcodeServiceAccountAcp"));
	public Boolean isTxtZipcodeServiceAccountVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtZipcodeServiceAccount));
        return isElementVisible(txtZipcodeServiceAccount);
    }
	public WebElement getTxtZipcodeServiceAccount() {
		return txtZipcodeServiceAccount;
	}
	
	@FindBy(xpath = "//div[@id='cusAdvSearch']//label[text()='ZIP Code:']/following-sibling::input")
    private WebElement txtZipcodeCustomer;
	//By txtZipcodeCustomer = By.xpath(Utils.getRbPropValue("txtZipcodeCustomerAcp"));
	public Boolean isTxtZipcodeCustomerVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtZipcodeCustomer));
        return isElementVisible(txtZipcodeCustomer);
    }
	public WebElement getTxtZipcodeCustomer() {
		return txtZipcodeCustomer;
	}
	
	@FindBy(xpath = "//div[@id='scmUsrAdvSearch']//label[text()='ZIP Code:']/following-sibling::input")
    private WebElement txtZipcodeUser;
	//By txtZipcodeUser = By.xpath(Utils.getRbPropValue("txtZipcodeUserAcp"));
	public void scrollToTxtZipcodeUser() {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", txtZipcodeUser);
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found: " + e);
			log.error("No Element Found after wait" + e);
		}
	}
	public Boolean isTxtZipcodeUserVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtZipcodeUser));
        return isElementVisible(txtZipcodeUser);
    }
	public WebElement getTxtZipcodeUser() {
		return txtZipcodeUser;
	}
	
	@FindBy(xpath = "//div[@id='serAdvSearch']//label[text()='City:']/following-sibling::input")
    private WebElement txtCityServiceAccount;
	//By txtCityServiceAccount = By.xpath(Utils.getRbPropValue("txtCityServiceAccountAcp"));
	public Boolean isTxtCityServiceAccountVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtCityServiceAccount));
        return isElementVisible(txtCityServiceAccount);
    }
	public WebElement getTxtCityServiceAccount() {
		return txtCityServiceAccount;
	}
	
	@FindBy(xpath = "//div[@id='cusAdvSearch']//label[text()='City:']/following-sibling::input")
    private WebElement txtCityCustomer;
	//By txtCityCustomer = By.xpath(Utils.getRbPropValue("txtCityCustomerAcp"));
	public Boolean isTxtCityCustomerVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtCityCustomer));
        return isElementVisible(txtCityCustomer);
    }
	public WebElement getTxtCityCustomer() {
		return txtCityCustomer;
	}
	
	@FindBy(xpath = "//div[@id='scmUsrAdvSearch']//label[text()='City:']/following-sibling::input")
    private WebElement txtCityUser;
	//By txtCityUser = By.xpath(Utils.getRbPropValue("txtCityUserAcp"));
	public Boolean isTxtCityUserVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtCityUser));
        return isElementVisible(txtCityUser);
    }
	public WebElement getTxtCityUser() {
		return txtCityUser;
	}
	
	@FindBy(xpath = "//div[@id='serAdvSearch']//label[text()='First Name:']/following-sibling::input")
    private WebElement txtFirstNameAccountService;
	//By txtFirstNameAccountService = By.xpath(Utils.getRbPropValue("txtFirstNameAccountServiceAcp"));
	
	@FindBy(xpath = "//div[@id='cusAdvSearch']//label[text()='First Name:']/following-sibling::input")
    private WebElement txtFirstNameCustomer;
	//By txtFirstNameCustomer = By.xpath(Utils.getRbPropValue("txtFirstNameCustomerAcp"));
	public Boolean isTxtFirstNameCustomerVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtFirstNameCustomer));
        return isElementVisible(txtFirstNameCustomer);
    }
	public WebElement getTxtFirstNameCustomer() {
		return txtFirstNameCustomer;
	}
	
	@FindBy(xpath = "//div[@id='scmUsrAdvSearch']//label[text()='First Name:']/following-sibling::input")
    private WebElement txtFirstNameUser;
	//By txtFirstNameUser = By.xpath(Utils.getRbPropValue("txtFirstNameUserAcp"));
	public Boolean isTxtFirstNameUserVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtFirstNameUser));
        return isElementVisible(txtFirstNameUser);
    }
	public WebElement getTxtFirstNameUser() {
		return txtFirstNameUser;
	}
	
	@FindBy(xpath = "//div[@id='serAdvSearch']//label[text()='Last Name:']/following-sibling::input")
    private WebElement txtLastNameServiceAccount;
	//By txtLastNameServiceAccount = By.xpath(Utils.getRbPropValue("txtLastNameServiceAccountAcp"));
	
	@FindBy(xpath = "//div[@id='cusAdvSearch']//label[text()='Last Name:']/following-sibling::input")
    private WebElement txtLastNameCustomer;
	//By txtLastNameCustomer = By.xpath(Utils.getRbPropValue("txtLastNameCustomerAcp"));
	public Boolean isTxtLastNameCustomerVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtLastNameCustomer));
        return isElementVisible(txtLastNameCustomer);
    }
	public WebElement getTxtLastNameCustomer() {
		return txtLastNameCustomer;
	}
	
	@FindBy(xpath = "//div[@id='scmUsrAdvSearch']//label[text()='Last Name:']/following-sibling::input")
    private WebElement txtLastNameUser;
	//By txtLastNameUser = By.xpath(Utils.getRbPropValue("txtLastNameUserAcp"));
	public Boolean isTxtLastNameUserVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtLastNameUser));
        return isElementVisible(txtLastNameUser);
    }
	public WebElement getTxtLastNameUser() {
		return txtLastNameUser;
	}
	
	@FindBy(xpath = "//div[@id='serAdvSearch']//label[text()='Email:']/following-sibling::input")
    private WebElement txtEmailAdvanceServiceAccount;
	//By txtEmailAdvanceServiceAccount = By.xpath(Utils.getRbPropValue("txtEmailAdvanceServiceAccountAcp"));
	
	@FindBy(xpath = "//div[@id='cusAdvSearch']//label[contains(text(),'Email')]/following-sibling::input")
    private WebElement txtEmailAdvanceCustomer;
	//By txtEmailAdvanceCustomer = By.xpath(Utils.getRbPropValue("txtEmailAdvanceCustomerAcp"));
	public Boolean isTxtEmailAdvanceCustomerVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtEmailAdvanceCustomer));
        return isElementVisible(txtEmailAdvanceCustomer);
    }
	public WebElement getTxtEmailAdvanceCustomer() {
		return txtEmailAdvanceCustomer;
	}
	
	@FindBy(xpath = "//div[@id='scmUsrAdvSearch']//label[text()='Email Address:']/following-sibling::input")
    private WebElement txtEmailAdvanceUser;
	//By txtEmailAdvanceUser = By.xpath(Utils.getRbPropValue("txtEmailAdvanceUserAcp"));
	public Boolean isTxtEmailAdvanceUserVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtEmailAdvanceUser));
        return isElementVisible(txtEmailAdvanceUser);
    }
	public WebElement getTxtEmailAdvanceUser() {
		return txtEmailAdvanceUser;
	}
	
	@FindBy(xpath = "//div[label='No. of Linked Accounts:']/select")
    private WebElement ddlLinkAccount;
	//By ddlLinkAccount = By.xpath(Utils.getRbPropValue("ddlLinkAccountAcp"));
	public Boolean isDdlLinkAccountVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(ddlLinkAccount));
        return isElementVisible(ddlLinkAccount);
    }
	public WebElement getDdlLinkAccount() {
		return ddlLinkAccount;
	}
	
	@FindBy(xpath = "//div[label='Role:']/select")
    private WebElement ddlRole;
	//By ddlRole = By.xpath(Utils.getRbPropValue("ddlRoleAcp"));
	public void scrollToDdlRole() {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ddlRole);
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found: " + e);
			log.error("No Element Found after wait" + e);
		}
	}
	public Boolean isDdlRoleVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(ddlRole));
        return isElementVisible(ddlRole);
    }
	public WebElement getDdlRole() {
		return ddlRole;
	}
	
	
	@FindBy(xpath = "//div[label='Is Social login enabled:']/select")
    private WebElement ddlSocialMedia;
	//By ddlSocialMedia = By.xpath(Utils.getRbPropValue("ddlSocialMediaAcp"));
	public Boolean isDdlSocialMediaVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(ddlSocialMedia));
        return isElementVisible(ddlSocialMedia);
    }
	public WebElement getDdlSocialMedia() {
		return ddlSocialMedia;
	}
	
	@FindBy(xpath = "//div[@id='scmUsrAdvSearch']//button[@class='submit-button']")
    private WebElement btnAdvanceSubmit;
	//By btnAdvanceSubmit = By.xpath(Utils.getRbPropValue("btnAdvanceSubmitAcp"));
	public void clickBtnAdvanceSubmit() {
        click(btnAdvanceSubmit);
        waitForImgCustomerDetailsLoadingToInvisible();
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(xpath = "//div[@id='cusAdvSearch']//button[@class='submit-button']")
    private WebElement btnAdvanceSubmitCustomers;
	//By btnAdvanceSubmit = By.xpath(Utils.getRbPropValue("btnAdvanceSubmitAcp"));
	public void clickBtnAdvanceSubmitCustomers() {
        click(btnAdvanceSubmitCustomers);
        waitForImgCustomerDetailsLoadingToInvisible();
        log.info("SignIn Button clicked successfully.");
    }	
	
	@FindBy(xpath = "//div[@id='serAdvSearch']//button[@class='submit-button']")
    private WebElement btnAdvanceSearchServiceAccount;
	//By btnAdvanceSearchServiceAccount = By.xpath(Utils.getRbPropValue("btnAdvanceSearchServiceAccountAcp"));
	public Boolean isBtnAdvanceSearchServiceAccountVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(btnAdvanceSearchServiceAccount));
        return isElementVisible(btnAdvanceSearchServiceAccount);
    }
	public void clickBtnAdvanceSearchServiceAccount() {
        click(btnAdvanceSearchServiceAccount);
        waitForImgCustomerDetailsLoadingToInvisible();
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(xpath = "//div[@id='serAdvSearch']//button[@class='cancel-button']")
    private WebElement btnAdvanceResetServiceAccount;
	//By btnAdvanceResetServiceAccount = By.xpath(Utils.getRbPropValue("btnAdvanceResetServiceAccountAcp"));
	public Boolean isBtnAdvanceResetServiceAccountVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(btnAdvanceResetServiceAccount));
        return isElementVisible(btnAdvanceResetServiceAccount);
    }
	public void clickBtnAdvanceResetServiceAccount() {
        click(btnAdvanceResetServiceAccount);
        waitForImgCustomerDetailsLoadingToInvisible();
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(xpath = "//div[@id='serAdvSearch']//div//button[text()='Search']")
    private WebElement btnAdvanceSearchSearch;
	//By btnAdvanceResetServiceAccount = By.xpath(Utils.getRbPropValue("btnAdvanceResetServiceAccountAcp"));
	public Boolean isBtnAdvanceSearchSearchVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(btnAdvanceSearchSearch));
        return isElementVisible(btnAdvanceSearchSearch);
    }
	public void clickBtnAdvanceSearchSearch() {
        click(btnAdvanceSearchSearch);
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(css = "#serAdvSearch > div > div > div.modal-header > button")
    private WebElement btnAdvanceSearchClose;
	//By btnAdvanceResetServiceAccount = By.xpath(Utils.getRbPropValue("btnAdvanceResetServiceAccountAcp"));
	public Boolean isBtnAdvanceSearchCloseVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(btnAdvanceSearchClose));
        return isElementVisible(btnAdvanceSearchClose);
    }
	
	
	@FindBy(xpath = "//label[text()='Registered Accounts:']")
    private WebElement lblRegisteredAcctAdvSearchServAcct;
	//By lblRegisteredAcctAdvSearchServAcct = By.xpath(Utils.getRbPropValue("lblRegisteredAcctAdvSearchServAcctAcp"));
	public Boolean isLblRegisteredAcctAdvSearchServAcctVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(lblRegisteredAcctAdvSearchServAcct));
        return isElementVisible(lblRegisteredAcctAdvSearchServAcct);
    }
	
	@FindBy(css = "select[ng-model='accountAdvanceSearchOptions.selectedRegisteredAccount']")
    private WebElement ddlRegisteredAcctAdvSearchServAcct;
	//By ddlRegisteredAcctAdvSearchServAcct = By.cssSelector(Utils.getRbPropValue("ddlRegisteredAcctAdvSearchServAcctAcp"));
	public Boolean isDdlRegisteredAcctAdvSearchServAcctVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(ddlRegisteredAcctAdvSearchServAcct));
        return isElementVisible(ddlRegisteredAcctAdvSearchServAcct);
    }
	public List<String> getAllOptionsInDdlRegisteredAcctAdvSearchServAcct() {
		List<String> options = new ArrayList<String>();
		for (WebElement option : new Select(ddlRegisteredAcctAdvSearchServAcct).getOptions()) {
			String sTxt = option.getText();
			if (option.getAttribute("value") != "")
				options.add(option.getText());
		}
		log.info("The list box contains the following values : " + options);
		return options;
	}
	public WebElement getDdlRegisteredAcctAdvSearchServAcct() {
		return ddlRegisteredAcctAdvSearchServAcct;
	}
	
	@FindBy(xpath = "//div[@id='cusAdvSearch']//button[@class='cancel-button']")
    private WebElement btnAdvanceResetCustomer;
	//By btnAdvanceResetCustomer = By.xpath(Utils.getRbPropValue("btnAdvanceResetCustomerAcp"));
	public void clickBtnAdvanceResetCustomer() {
        click(btnAdvanceResetCustomer);
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(xpath = "//div[@id='scmUsrAdvSearch']//button[@class='cancel-button']")
    private WebElement btnAdvanceResetUser;
	//By btnAdvanceResetUser = By.xpath(Utils.getRbPropValue("btnAdvanceResetUserAcp"));
	public void clickBtnAdvanceResetUser() {
        click(btnAdvanceResetUser);
        waitForImgCustomerDetailsLoadingToInvisible();
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(css = "a[data-target='.userDetails']")
    private WebElement lnkServiceAccountNumber;
	//By lnkServiceAccountNumber = By.cssSelector(Utils.getRbPropValue("lnkServiceAccountNumberAcp"));
	public void clickLnkServiceAccountNumber() {
        click(lnkServiceAccountNumber);
        log.info("Account Number link clicked successfully.");
    }
	
	@FindBy(xpath = "//table[contains(@class,'table dataTable no-footer table_customernew MailListing svcaccountable')]")
    private WebElement tableServiceAccount;
	//By tableServiceAccount = By.xpath(Utils.getRbPropValue("tableServiceAccountAcp"));
	public WebElement getTableServiceAccount() {
		return tableServiceAccount;
	}
	
	@FindBy(xpath = "//table[@class='table dataTable no-footer table_customernew MailListing tableCustomer']")
    private WebElement tableSearchServiceAccount;
	//By tableSearchServiceAccount = By.xpath(Utils.getRbPropValue("tableSearchServiceAccountAcp"));
	
	@FindBy(css = "a[href*='PendingRequests']")
    private WebElement linkDeleteProfileRequestAcp;
	//By linkDeleteProfileRequestAcp = By.cssSelector(Utils.getRbPropValue("linkDeleteProfileRequestAcp"));
	
	@FindBy(xpath = "//table[@class='table dataTable no-footer table_customernew MailListing tableUser']")
    private WebElement tableSearcUserhServiceAccount;
	//By tableSearcUserhServiceAccount = By.xpath(Utils.getRbPropValue("tableSearcUserhServiceAccountAcp"));
	
	@FindBy(css = "#searchColumn")
    private WebElement ddSearchItem;
	//By ddSearchItem = By.cssSelector(Utils.getRbPropValue("ddSearchItemAcp"));
	public void selectByVisibleTextDdSearchItem(String visibleText) {
		try {
			WebElement element = ddSearchItem;
			if (element.isDisplayed()) {
				Select selObj = new Select(element);
				if (visibleText.equals("")) {
					selObj.selectByVisibleText("---Select---");
				} else {
					selObj.selectByVisibleText(visibleText);
				}
				log.info("User selects the visible text as  " + visibleText + "from Dropdown");
				
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.err.format("No Element Found to perform selection in the dropdown" + e);
			
		}
	}
	public WebElement getDdSearchItem() {
		return ddSearchItem;
	}
	public boolean isDdSearchItemDisplayed() {
		if (ddSearchItem.isDisplayed()) {
			log.info(ddSearchItem.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(ddSearchItem + " is not Displayed on the Page");
			return false;
		}
	}
	
	@FindBy(css = ".logo")
    private WebElement logoUtility;
	//By logoUtility = By.cssSelector(Utils.getRbPropValue("logoUtilityAcp"));
	public boolean isLogoUtilityDisplayed() {
		if (logoUtility.isDisplayed()) {
			log.info(logoUtility.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(logoUtility + " is not Displayed on the Page");
			return false;
		}
	}
	
	@FindBy(css = ".button_nav")
    private WebElement iconHamburger;
	//By iconHamburger = By.cssSelector(Utils.getRbPropValue("iconHamburgerAcp"));
	public boolean isIconHamburgerDisplayed() {
		if (iconHamburger.isDisplayed()) {
			log.info(iconHamburger.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(iconHamburger + " is not Displayed on the Page");
			return false;
		}
	}
	
	@FindBy(css = ".ico_profileadmin")
    private WebElement iconProfile;
	//By iconProfile = By.cssSelector(Utils.getRbPropValue("iconProfileAcp"));
	public boolean isIconProfileDisplayed() {
		if (iconProfile.isDisplayed()) {
			log.info(iconProfile.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(iconProfile + " is not Displayed on the Page");
			return false;
		}
	}
	
	@FindBy(css = ".WelcomeClass")
    private WebElement txtWelcome;
	//By txtWelcome = By.cssSelector(Utils.getRbPropValue("txtWelcomeAcp"));
	public boolean isTxtWelcomeDisplayed() {
		if (txtWelcome.isDisplayed()) {
			log.info(txtWelcome.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(txtWelcome + " is not Displayed on the Page");
			return false;
		}
	}
	
	@FindBy(css = "#lblUserName")
    private WebElement txtAdminUserName;
	//By txtAdminUserName = By.cssSelector(Utils.getRbPropValue("txtAdminUserNameAcp"));
	public boolean isTxtAdminUserNameDisplayed() {
		if (txtAdminUserName.isDisplayed()) {
			log.info(txtAdminUserName.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(txtAdminUserName + " is not Displayed on the Page");
			return false;
		}
	}
	
	@FindBy(css = ".vrsn")
    private WebElement linkVersion;
	//By linkVersion = By.cssSelector(Utils.getRbPropValue("linkVersionAcp"));
	public boolean isLinkVersionDisplayed() {
		if (linkVersion.isDisplayed()) {
			log.info(linkVersion.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(linkVersion + " is not Displayed on the Page");
			return false;
		}
	}
	
	@FindBy(css = ".foot_copy_sec")
    private WebElement linkCopyright;
	//By linkCopyright = By.cssSelector(Utils.getRbPropValue("linkCopyrightAcp"));
	public boolean isLinkCopyrightDisplayed() {
		if (linkCopyright.isDisplayed()) {
			log.info(linkCopyright.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(linkCopyright + " is not Displayed on the Page");
			return false;
		}
	}
	
	@FindBy(css = "img[alt='Powered By SEW']")
    private WebElement txtSEW;
	//By txtSEW = By.cssSelector(Utils.getRbPropValue("txtSEWAcp"));
	public boolean isTxtSEWDisplayed() {
		if (txtSEW.isDisplayed()) {
			log.info(txtSEW.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(txtSEW + " is not Displayed on the Page");
			return false;
		}
	}
	
	@FindBy(css = "[aria-label*='Click to Service Account']")
    private WebElement tableServiceAcctServiceAccountNumbercln;
	//By tableServiceAcctServiceAccountNumbercln = By.cssSelector(Utils.getRbPropValue("tableServiceAcctServiceAccountNumberclnAcp"));
	public String getTableServiceAcctServiceAccountNumberclnLabel() {
		String label = null;
		try {
			// driver.findElement(locator).isDisplayed();
			label = tableServiceAcctServiceAccountNumbercln.getText().trim();
			log.info("User gets the test object Label as: " + label);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return label;
	}
	public void clickTableServiceAcctServiceAccountNumbercln() {
        click(tableServiceAcctServiceAccountNumbercln);
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBys(@FindBy(css = "[aria-label*='Click to Service Account']"))
	private List<WebElement> tblServiceAcccount;
	public List<WebElement> getTblServiceAcccount(){
		return tblServiceAcccount;
	}
	
	@FindBy(css = "[aria-label*='Click to '][data-target='.userDetails']")
    private WebElement tableServiceAcctServiceInactiveAccountNumbercln;
	//By tableServiceAcctServiceInactiveAccountNumbercln = By.cssSelector(Utils.getRbPropValue("tableServiceAcctServiceInactiveAccountNumberclnAcp"));
	
	@FindBys(@FindBy(xpath = "//table[contains(@class,'svcaccountable')]//td[5]"))
    private List<WebElement> tblServiceAcctCustNamecln;
	//By tblServiceAcctCustNamecln = By.xpath(Utils.getRbPropValue("tblServiceAcctCustNameclnAcp"));
	public List<WebElement> getTblServiceAcctCustNamecln(){
		return tblServiceAcctCustNamecln;
	}
	
	@FindBy(xpath = "//table[contains(@class,'svcaccountable')]//td[5]/span/a")
    private WebElement tblServiceAcctCustAddressAlone;
	//By tblServiceAcctCustAddressAlone = By.xpath(Utils.getRbPropValue("tblServiceAcctCustAddressAloneAcp"));
	
	@FindBys(@FindBy(xpath = "//table[contains(@class,'svcaccountable')]//td[6]"))
    private List<WebElement> tblServiceAcctEmailAddresscln;
	//By tblServiceAcctEmailAddresscln = By.xpath(Utils.getRbPropValue("tblServiceAcctEmailAddressclnAcp"));
	public List<WebElement> getTblServiceAcctEmailAddress(){
		return tblServiceAcctEmailAddresscln;
	}
	
	@FindBys(@FindBy(xpath = "//table[contains(@class,'svcaccountable')]//td[7]"))
    private List<WebElement> tblServiceAcctLinkedUserscln;
	//By tblServiceAcctLinkedUserscln = By.xpath(Utils.getRbPropValue("tblServiceAcctLinkedUsersclnAcp"));
	public List<WebElement> getTblServiceAcctLinkedUserscln() {
		return tblServiceAcctLinkedUserscln;
	}
	
	@FindBys(@FindBy(xpath = "//table[contains(@class,'svcaccountable')]//td[2]/span"))
    private List<WebElement> tblServiceAcctCustStatuscln;
	//By tblServiceAcctCustStatuscln = By.xpath(Utils.getRbPropValue("tblServiceAcctCustStatusclnAcp"));
	public List<WebElement> getTblServiceAcctCustStatuscln() {
		return tblServiceAcctCustStatuscln;
	}

	@FindBys(@FindBy(xpath = "//table[contains(@class,'svcaccountable')]//td[8]"))
    private List<WebElement> tblServiceAcctAccTypecln;
	//By tblServiceAcctAccTypecln = By.xpath(Utils.getRbPropValue("tblServiceAcctAccTypeclnAcp"));
	public List<WebElement> getTblServiceAcctAccTypecln() {
		return tblServiceAcctAccTypecln;
	}

	@FindBy(xpath = "//div[@class='customtable']//span[@class='Inactive']")
    private WebElement txtInactiveValueServiceAccountOtherstab;
	//By txtInactiveValueServiceAccountOtherstab = By.xpath(Utils.getRbPropValue("txtInactiveValueServiceAccountOtherstabAcp"));
	public void explicitWaitForTxtInactiveValueServiceAccountOtherstab() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.visibilityOf(txtInactiveValueServiceAccountOtherstab));
		} catch (NoSuchElementException e) {
			log.error("No Element Found after wait" + e);
		}
	}

	@FindBy(xpath = "//*[text()='USERS']/../../..")
    private WebElement liSCMUserTab;
	//By liSCMUserTab = By.xpath(Utils.getRbPropValue("liSCMUserTabAcp"));
	public void clickLiSCMUserTab() {
        click(liSCMUserTab);
        waitForImgCustomerDetailsLoadingToInvisible();
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(xpath = "//h3[text()='USERS']")
    private WebElement lnkSCMUserTab;
	//By lnkSCMUserTab = By.xpath(Utils.getRbPropValue("lnkSCMUserTabAcp"));
	public void clickLnkSCMUserTab() {
        click(lnkSCMUserTab);
        waitForImgCustomerDetailsLoadingToInvisible();
        log.info("SignIn Button clicked successfully.");
    }
	public boolean isLnkSCMUserTabDisplayed() {
		if (lnkSCMUserTab.isDisplayed()) {
			log.info(lnkSCMUserTab.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(lnkSCMUserTab + " is not Displayed on the Page");
			return false;
		}
	}
	
	@FindBy(xpath = "//ul[li[h3[text()='USERS']]]/li/div/label[text()='Active']")
    private WebElement lnkSCMUserActiveTab;
	//By lnkSCMUserActiveTab = By.xpath(Utils.getRbPropValue("lnkSCMUserActiveTabAcp"));
	public boolean isLnkSCMUserActiveTabDisplayed() {
		if (lnkSCMUserActiveTab.isDisplayed()) {
			log.info(lnkSCMUserActiveTab.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(lnkSCMUserActiveTab + " is not Displayed on the Page");
			return false;
		}
	}
	public void clickLnkSCMUserActiveTab() {
        click(lnkSCMUserActiveTab);
        waitForImgCustomerDetailsLoadingToInvisible();
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(xpath = "//ul[li[h3[text()='USERS']]]/li/div/label[text()='Others']")
    private WebElement lnkSCMUserOthersTab;
	//By lnkSCMUserOthersTab = By.xpath(Utils.getRbPropValue("lnkSCMUserOthersTabAcp"));
	public boolean isLnkSCMUserOthersTabDisplayed() {
		if (lnkSCMUserOthersTab.isDisplayed()) {
			log.info(lnkSCMUserOthersTab.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(lnkSCMUserOthersTab + " is not Displayed on the Page");
			return false;
		}
	}
	public void clickLnkSCMUserOthersTab() {
        click(lnkSCMUserOthersTab);
        waitForImgCustomerDetailsLoadingToInvisible();
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(xpath = "//*[text()='CUSTOMERS']/../../..")
    private WebElement liCustomerTab;
	//By liCustomerTab = By.xpath(Utils.getRbPropValue("liCustomerTabAcp"));
	
	@FindBy(xpath = "//*[text()='CUSTOMERS']/..//a")
    private WebElement lnkCustomerTab;
	//By lnkCustomerTab = By.xpath(Utils.getRbPropValue("lnkCustomerTabAcp"));
	public void clickLnkCustomerTab() {
        click(lnkCustomerTab);
        waitForPageLoaderInvisibility();
        log.info("Customer Button clicked successfully.");
    }
	public boolean isLnkCustomerTabDisplayed() {
		if (lnkCustomerTab.isDisplayed()) {
			log.info(lnkCustomerTab.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(lnkCustomerTab + " is not Displayed on the Page");
			return false;
		}
	}
	
	@FindBy(xpath = "//*[text()='CUSTOMERS']/../..//*[text()='Active']/../..")
    private WebElement lnkCustomerActiveTab;
	//By lnkCustomerActiveTab = By.xpath(Utils.getRbPropValue("lnkCustomerActiveTabAcp"));
	
	@FindBy(xpath = "//*[text()='SCM USERS']/../..//*[text()='Others']/../..")
    private WebElement lnkCustomerOthersTab;
	//By lnkCustomerOthersTab = By.xpath(Utils.getRbPropValue("lnkCustomerOthersTabAcp"));
	
	@FindBy(xpath = "//*[text()='SERVICE ACCOUNTS']/../..//*[text()='Active']/../..")
    private WebElement lnkServiceAccountActiveTab;
	//By lnkServiceAccountActiveTab = By.xpath(Utils.getRbPropValue("lnkServiceAccountActiveTabAcp"));
	public boolean isLnkServiceAccountActiveTabDisplayed() {
		if (lnkServiceAccountActiveTab.isDisplayed()) {
			log.info(lnkServiceAccountActiveTab.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(lnkServiceAccountActiveTab + " is not Displayed on the Page");
			return false;
		}
	}
	
	@FindBy(xpath = "//table[@class='table dataTable no-footer table_customernew MailListing svcaccountable']/tbody/tr/td")
    private WebElement lblNoDataFound;
	//By lblNoDataFound = By.xpath(Utils.getRbPropValue("lblNoDataFoundAcp"));
	public String getLblNoDataFoundLabel() {
		String label = null;
		try {
			// driver.findElement(locator).isDisplayed();
			label = lblNoDataFound.getText().trim();
			log.info("User gets the test object Label as: " + label);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return label;
	}
	
	@FindBy(xpath = "//table[@class='table dataTable no-footer table_customernew MailListing tableUser']/tbody/tr/td")
    private WebElement lblNoDataFoundUser;
	//By lblNoDataFoundUser = By.xpath(Utils.getRbPropValue("lblNoDataFoundUserAcp"));
	public String getLblNoDataFoundUserLabel() {
		String label = null;
		try {
			// driver.findElement(locator).isDisplayed();
			label = lblNoDataFoundUser.getText().trim();
			log.info("User gets the test object Label as: " + label);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return label;
	}
	
	@FindBy(xpath = "//table[@class='table dataTable no-footer table_customernew MailListing tableCustomer']/tbody/tr/td")
    private WebElement lblNoDataFoundCustomer;
	//By lblNoDataFoundCustomer = By.xpath(Utils.getRbPropValue("lblNoDataFoundCustomerAcp"));
	public String getLblNoDataFoundCustomerLabel() {
		String label = null;
		try {
			// driver.findElement(locator).isDisplayed();
			label = lblNoDataFoundCustomer.getText().trim();
			log.info("User gets the test object Label as: " + label);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return label;
	}
	
	@FindBy(xpath = "//*[text()='SERVICE ACCOUNTS']/../..//*[text()='Others']/../..")
    private WebElement lnkServiceAccountOthersTab;
	//By lnkServiceAccountOthersTab = By.xpath(Utils.getRbPropValue("lnkServiceAccountOthersTabAcp"));
	public boolean isLnkServiceAccountOthersTabDisplayed() {
		if (lnkServiceAccountOthersTab.isDisplayed()) {
			log.info(lnkServiceAccountOthersTab.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(lnkServiceAccountOthersTab + " is not Displayed on the Page");
			return false;
		}
	}
	public void clickLnkServiceAccountOthersTab() {
        click(lnkServiceAccountOthersTab);
        waitForImgCustomerDetailsLoadingToInvisible();
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(xpath = "//table[@class='table dataTable no-footer table_customernew MailListing svcaccountable']//th//input[@id='chkall']")
    private WebElement chkboxAllSelectServiceAccountTab;
	//By chkboxAllSelectServiceAccountTab = By.xpath(Utils.getRbPropValue("chkboxAllSelectServiceAccountTabAcp"));
	
	@FindBy(xpath = "//table[@class='table dataTable no-footer table_customernew MailListing tableCustomer']")
    private WebElement chkboxAllSelectCustomerTab;
	//By chkboxAllSelectCustomerTab = By.xpath(Utils.getRbPropValue("chkboxAllSelectCustomerTabAcp"));
	
	@FindBy(xpath = "//table[@class='table dataTable no-footer table_customernew MailListing tableUser']//th//input[@id='chkall2']")
    private WebElement chkboxAllSelectSCMUserTab;
	//By chkboxAllSelectSCMUserTab = By.xpath(Utils.getRbPropValue("chkboxAllSelectSCMUserTabAcp"));
	
	@FindBy(xpath = "//table[@class='table dataTable no-footer table_customernew MailListing svcaccountable']")
    private WebElement tableHeaderServiceAccountNumber;
	//By tableHeaderServiceAccountNumber = By.xpath(Utils.getRbPropValue("tableHeaderServiceAccountNumberAcp"));
	
	@FindBy(css = "#lnkToPortal")
    private WebElement btnLoginToCustomer;
	//By btnLoginToCustomer = By.cssSelector(Utils.getRbPropValue("btnLoginToCustomerAcp"));
	public boolean isBtnLoginToCustomerDisplayed() {
		if (btnLoginToCustomer.isDisplayed()) {
			log.info(btnLoginToCustomer.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(btnLoginToCustomer + " is not Displayed on the Page");
			return false;
		}
	}
	public void clickBtnLoginToCustomer() {
        click(btnLoginToCustomer);
        log.info("Forgot Password Link clicked successfully.");
    }
	
	@FindBy(css = "#lblCustName")
    private WebElement lblCustNameAcp;
	//By lblCustNameAcp = By.cssSelector(Utils.getRbPropValue("lblCustNameAcp"));
	
	@FindBy(xpath = "//a[@class='backmeCSR fancybox-item fancybox-close']")
    private WebElement btnCloseCustomerDetails;
	//By btnCloseCustomerDetails = By.xpath(Utils.getRbPropValue("btnCloseCustomerDetailsAcp"));
	
	@FindBy(css = "#fancybox-loading")
    private WebElement imgCustomerDetailsLoading;
	//By imgCustomerDetailsLoading = By.cssSelector(Utils.getRbPropValue("imgCustomerDetailsLoadingAcp"));
	public void waitForImgCustomerDetailsLoadingToInvisible() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.invisibilityOf(imgCustomerDetailsLoading));
			//wait.until(ExpectedConditions.invisibilityOfElementLocated(imgCustomerDetailsLoading));
		} catch (NoSuchElementException e) {
			// e.printStackTrace();
			System.err.format("No Element Found: " + e);
			log.error("No Element Found after wait" + e);
		}
	}
	
	@FindBy(css = ".spinner")
    private WebElement imgLoadingSpinner;
	//By imgLoadingSpinner = By.cssSelector(Utils.getRbPropValue("imgLoadingSpinnerAcp"));
	public void waitForImgLoadingSpinnerToInvisible() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.invisibilityOf(imgLoadingSpinner));
			//wait.until(ExpectedConditions.invisibilityOfElementLocated(imgCustomerDetailsLoading));
		} catch (NoSuchElementException e) {
			// e.printStackTrace();
			System.err.format("No Element Found: " + e);
			log.error("No Element Found after wait" + e);
		}
	}
	
	@FindBy(xpath = "//table[contains(@class,'table dataTable no-footer table_customernew MailListing tableCustomer')]")
    private WebElement tableCustomer;
	//By tableCustomer = By.xpath(Utils.getRbPropValue("tableCustomerAcp"));
	public WebElement getTableCustomer() {
		return tableCustomer;
	}
	
	@FindBy(xpath = "//table[contains(@class,'table dataTable no-footer table_customernew MailListing tableCustomer')]")
    private WebElement tableHeaderNameCustomer;
	//By tableHeaderNameCustomer = By.xpath(Utils.getRbPropValue("tableHeaderNameCustomerAcp"));
	public void clickTableHeaderNameCustomer() {
        click(tableHeaderNameCustomer);
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBys(@FindBy(xpath = "//tbody/tr[contains(@ng-repeat,'customerAccountGridData')]/td[1]"))
    private List<WebElement> tblCustomerCustNumcln;
	//By tblCustomerCustNumcln = By.xpath(Utils.getRbPropValue("tblCustomerCustNumclnAcp"));
	public List<WebElement> getTblCustomerCustNum() {
		return tblCustomerCustNumcln;
	}
	
	@FindBys(@FindBy(xpath = "//tbody/tr[contains(@ng-repeat,'customerAccountGridData')]/td[2]/a[1]"))
    private List<WebElement> tblCustomerCustomercln;
	//By tblCustomerCustomercln = By.xpath(Utils.getRbPropValue("tblCustomerCustomerclnAcp"));
	public List<WebElement> getTblCustomerCustomer() {
		return tblCustomerCustomercln;
	}
	
	@FindBys(@FindBy(xpath = "//tbody/tr[contains(@ng-repeat,'customerAccountGridData')]/td[3]"))
    private List<WebElement> tblCustomerMobilePhonecln;
	//By tblCustomerMobilePhonecln = By.xpath(Utils.getRbPropValue("tblCustomerMobilePhoneclnAcp"));
	public List<WebElement> getTblCustomerMobilePhone() {
		return tblCustomerMobilePhonecln;
	}
	
	@FindBys(@FindBy(xpath = "//tbody/tr[contains(@ng-repeat,'customerAccountGridData')]/td[4]"))
    private List<WebElement> tblCustomerPrimaryEmailcln;
	//By tblCustomerPrimaryEmailcln = By.xpath(Utils.getRbPropValue("tblCustomerPrimaryEmailclnAcp"));
	public List<WebElement> getTblCustomerPrimaryEmail() {
		return tblCustomerPrimaryEmailcln;
	}
	
	@FindBy(xpath = "//tbody/tr[contains(@ng-repeat,'customerAccountGridData')]/td[5]")
    private List<WebElement> tblCustomerLinkAcctscln;
	//By tblCustomerLinkAcctscln = By.xpath(Utils.getRbPropValue("tblCustomerLinkAcctsclnAcp"));
	public List<WebElement> getTblCustomerLinkAccts() {
		return tblCustomerLinkAcctscln;
	}

	@FindBy(xpath = "//table[contains(@class,'table dataTable no-footer table_customernew MailListing tableUser')]")
    private WebElement tableSCMUser;
	//By tableSCUser = By.xpath(Utils.getRbPropValue("tableSCMUserAcp"));
	public WebElement getTableSCMUser() {
		return tableSCMUser;
	}
	
	@FindBy(xpath = "//table[@class='table dataTable no-footer table_customernew MailListing tableUser']/tbody/tr/td[3]/a[1]")
    private WebElement tableSCMUserAddressCln;
	//By tableSCMUserAddressCln = By.xpath(Utils.getRbPropValue("tableSCMUserAddressClnAcp"));
	
	@FindBys(@FindBy(css = "table[class*='table dataTable no-footer table_customernew MailListing tableUser'] th"))
    private List<WebElement> tblSCMUsersHeaders;
	//By tblSCMUsersHeaders = By.cssSelector(Utils.getRbPropValue("tblSCMUsersHeadersAcp"));
	public List<WebElement> getTblSCMUsersHeaders() {
		return tblSCMUsersHeaders;
	}
	
	@FindBys(@FindBy(xpath = "//table[@class='table dataTable no-footer table_customernew MailListing tableUser']/thead//th[contains(@class,'sorting')]"))
    private List<WebElement> tblSCMUserSortableGridHeaders;
	//By tblSCMUserSortableGridHeaders = By.xpath(Utils.getRbPropValue("tblSCMUserSortableGridHeadersAcp"));
	public List<WebElement> getTblSCMUserSortableGridHeaders() {
		return tblSCMUserSortableGridHeaders;
	}
	
	@FindBys(@FindBy(css = "table[class*='table dataTable no-footer table_customernew MailListing tableUser']  tr td:nth-child(2)"))
    private List<WebElement> tblSCMUsersStatusCln;
	//By tblSCMUsersStatusCln = By.cssSelector(Utils.getRbPropValue("tblSCMUsersStatusClnAcp"));
	public List<WebElement> getTblSCMUsersStatusCln() {
		return tblSCMUsersStatusCln;
	}
	
	@FindBys(@FindBy(css = "table[class*='table dataTable no-footer table_customernew MailListing tableUser']  tr td:nth-child(3)"))
    private List<WebElement> tblSCMUsersUserCln;
	//By tblSCMUsersUserCln = By.cssSelector(Utils.getRbPropValue("tblSCMUsersUserClnAcp"));
	public List<WebElement> getTblSCMUsersUser() {
		return tblSCMUsersUserCln;
	}
	
	@FindBys(@FindBy(css = "table[class*='table dataTable no-footer table_customernew MailListing tableUser']  tr td:nth-child(4)"))
    private List<WebElement> tblSCMUsersUsernameCln;
	//By tblSCMUsersUsernameCln = By.cssSelector(Utils.getRbPropValue("tblSCMUsersUsernameClnAcp"));
	public List<WebElement> getTblSCMUsersUsername() {
		return tblSCMUsersUsernameCln;
	}
	
	@FindBys(@FindBy(css = "table[class*='table dataTable no-footer table_customernew MailListing tableUser']  tr td:nth-child(5)"))
    private List<WebElement> tblSCMUsersContactCln;
	//By tblSCMUsersContactCln = By.cssSelector(Utils.getRbPropValue("tblSCMUsersContactClnAcp"));
	public List<WebElement> getTblSCMUsersContact() {
		return tblSCMUsersContactCln;
	}
	
	@FindBys(@FindBy(css = "table[class*='table dataTable no-footer table_customernew MailListing tableUser']  tr td:nth-child(6) a"))
    private List<WebElement> tblSCMUsersLinkedAccountsCln;
	//By tblSCMUsersLinkedAccountsCln = By.cssSelector(Utils.getRbPropValue("tblSCMUsersLinkedAccountsClnAcp"));
	public List<WebElement> getTblSCMUsersLinkedAccounts() {
		return tblSCMUsersLinkedAccountsCln;
	}
	
	@FindBy(css = "#btnResendActivation")
    private WebElement btnProceedResendActivationLnkPopup;
	//By btnProceedResendActivationLnkPopup = By.cssSelector(Utils.getRbPropValue("btnProceedResendActivationLnkPopupAcp"));
	public void explicitWaitForBtnProceedResendActivationLnkPopup() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.visibilityOf(btnProceedResendActivationLnkPopup));
		} catch (NoSuchElementException e) {
			log.error("No Element Found after wait" + e);
		}
	}
	public void clickBtnProceedResendActivationLnkPopup() {
        click(btnProceedResendActivationLnkPopup);
        log.info("SignIn Button clicked successfully.");
    }

	@FindBys(@FindBy(xpath = "//*[text()=' Customer Rebates (Non Opted)']/preceding-sibling::ul//div[@class='details_box']/h5"))
    private List<WebElement> lblRebateOpted;
	//By lblRebateOpted = By.xpath(Utils.getRbPropValue("lblRebateOptedAcp"));
	public List<WebElement> getLblRebateOpted() {
        return lblRebateOpted;
    }
	
	@FindBys(@FindBy(xpath = "//*[text()=' Customer Rebates (Non Opted)']/following-sibling::ul//div[@class='details_box']/h5"))
    private List<WebElement> lblRebateNotOpted;
	//By lblRebateNotOpted = By.xpath(Utils.getRbPropValue("lblRebateNotOptedAcp"));
	public List<WebElement> getLblRebateNotOpted() {
        return lblRebateNotOpted;
    }
	
	@FindBys(@FindBy(xpath = "//*[text()='Customer Saving Tips (Non Opted)']/preceding-sibling::ul//div[@class='details_box']/h5"))
    private List<WebElement> lblSavingTipsOpted;
	//By lblSavingTipsOpted = By.xpath(Utils.getRbPropValue("lblSavingTipsOptedAcp"));
	public List<WebElement> getLblSavingTipsOpted() {
        return lblSavingTipsOpted;
    }
	
	@FindBys(@FindBy(xpath = "//*[text()='Customer Saving Tips (Non Opted)']/following-sibling::ul//div[@class='details_box']/h5"))
    private List<WebElement> lblSavingTipsNotOpted;
	//By lblSavingTipsNotOpted = By.xpath(Utils.getRbPropValue("lblSavingTipsNotOptedAcp"));
	public List<WebElement> getLblSavingTipsNotOpted() {
        return lblSavingTipsNotOpted;
    }

	@FindBys(@FindBy(xpath = "//*[text()='Show Details']"))
    private List<WebElement> btnShowDetailsEfficiency;
	//By btnShowDetailsEfficiency = By.xpath(Utils.getRbPropValue("btnShowDetailsEfficiencyAcp"));
	public List<WebElement> getBtnShowDetailsEfficiency() {
        return btnShowDetailsEfficiency;
    }
	
	@FindBy(xpath = "//div[@class='ShowDetailsDiv'][@style='display: block;']")
    private WebElement lblShowDetailsEfficiency;
	//By lblShowDetailsEfficiency = By.xpath(Utils.getRbPropValue("lblShowDetailsEfficiencyAcp"));
	public String getLblShowDetailsEfficiencyLabel() {
        String label = getText(lblShowDetailsEfficiency);
        log.info("BillTypeStatus Field Label {}: " + label);
        return label;
    }
	public void waitForLblShowDetailsEfficiency(){
        waitForElementToBeVisible(lblShowDetailsEfficiency);
        log.info("CSR WorkBench is Visible on the page.");
    }
	
	@FindBy(xpath = "//div[@class='ShowDetailsDiv'][@style='display: block;']")
    private WebElement lblShowDetailsEfficiencyHeading;
	//By lblShowDetailsEfficiencyHeading = By.xpath(Utils.getRbPropValue("lblShowDetailsEfficiencyHeadingAcp"));
	public String getLblShowDetailsEfficiencyHeadingLabel() {
        String label = getText(lblShowDetailsEfficiencyHeading);
        log.info("BillTypeStatus Field Label {}: " + label);
        return label;
    }
	
	@FindBy(xpath = "//*[text()='Click to Hide Details']")
    private WebElement btnHideDetailsEfficiency;
	//By btnHideDetailsEfficiency = By.xpath(Utils.getRbPropValue("btnHideDetailsEfficiencyAcp"));
	public void clickBtnHideDetailsEfficiency() {
        click(btnHideDetailsEfficiency);
        log.info("Forgot Password Link clicked successfully.");
    }
	public boolean isBtnHideDetailsEfficiencyDisplayed() {
		if (btnHideDetailsEfficiency.isDisplayed()) {
			log.info(btnHideDetailsEfficiency.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(btnHideDetailsEfficiency + " is not Displayed on the Page");
			return false;
		}
	}
	
	@FindBy(xpath = "(//div[@class='details_box']//span[@globalize='ML_ENERGY_EFFICIENCY_Lbl_Likes']/following-sibling::span)[1]")
    private WebElement lblLikesFirstEfficiency;
	//By lblLikesFirstEfficiency = By.xpath(Utils.getRbPropValue("lblLikesFirstEfficiencyAcp"));
	public String getLblLikesFirstEfficiencyLabel() {
        String label = getText(lblLikesFirstEfficiency);
        log.info("BillTypeStatus Field Label {}: " + label);
        return label;
    }
	
	@FindBy(xpath = "(//div[@class='details_box']//span[@globalize='ML_ENERGY_EFFICIENCY_Lbl_Viewed']/following-sibling::span)[1]")
    private WebElement lblViewsFirstEfficiency;
	//By lblViewsFirstEfficiency = By.xpath(Utils.getRbPropValue("lblViewsFirstEfficiencyAcp"));
	public String getLblViewsFirstEfficiency() {
        String label = getText(lblViewsFirstEfficiency);
        log.info("BillTypeStatus Field Label {}: " + label);
        return label;
    }
	
	@FindBy(xpath = "(//div[@class='details_box']//span[@globalize='ML_ENERGY_EFFICIENCY_Lbl_Added']/following-sibling::span)[1]")
    private WebElement lblAddedFirstEfficiency;
	//By lblAddedFirstEfficiency = By.xpath(Utils.getRbPropValue("lblAddedFirstEfficiencyAcp"));
	public String getLblAddedFirstEfficiency() {
        String label = getText(lblAddedFirstEfficiency);
        log.info("BillTypeStatus Field Label {}: " + label);
        return label;
    }
	
	@FindBy(xpath = "//*[@id='custName']")
    private WebElement lblCustomerName;
	//By lblCustomerName = By.xpath(Utils.getRbPropValue("lblCustomerNameAcp"));
	public Boolean isLblCustomerNameVisible() {
        log.info("Checking the visibility of Customer Name on CSR 360");
        log.info("Customer Name visibility status {}: " + isElementVisible(lblCustomerName));
        return isElementVisible(lblCustomerName);
    }
	public String getLblCustomerName() {
        String label = getText(lblCustomerName);
        log.info("BillTypeStatus Field Label {}: " + label);
        return label;
    }
	
	@FindBy(xpath = "//*[@id='lblZipCode']")
    private WebElement lblZipCode;
	//By lblZipCode = By.xpath(Utils.getRbPropValue("lblZipCodeAcp"));
	public Boolean isLblZipCodeVisible() {
        log.info("Checking the visibility of Zip code on CSR 360");
        log.info("Zip code visibility status {}: " + isElementVisible(lblZipCode));
        return isElementVisible(lblZipCode);
    }
	
	@FindBy(xpath = "//*[@id='lblLoginId']")
    private WebElement lblLoginId;
	//By lblLoginId = By.xpath(Utils.getRbPropValue("lblLoginIdAcp"));
	public Boolean isLblLoginIdVisible() {
        log.info("Checking the visibility of Username on CSR 360");
        log.info("Username visibility status {}: " + isElementVisible(lblLoginId));
        return isElementVisible(lblLoginId);
    }
	
	@FindBy(xpath = "//*[@id='statusUser']")
    private WebElement lblStatusUser;
	//By lblStatusUser = By.xpath(Utils.getRbPropValue("lblStatusUserAcp"));
	public Boolean isLblStatusUserVisible() {
        log.info("Checking the visibility of Username on CSR 360");
        log.info("Username visibility status {}: " + isElementVisible(lblStatusUser));
        return isElementVisible(lblStatusUser);
    }
	
	@FindBy(xpath = "//*[@id='lblEmailId']")
    private WebElement lblEmailId;
	//By lblEmailId = By.xpath(Utils.getRbPropValue("lblEmailIdAcp"));
	public Boolean isLblEmailIdVisible() {
        log.info("Checking the visibility of Primary Email on CSR 360");
        log.info("Primary Email visibility status {}: " + isElementVisible(lblEmailId));
        return isElementVisible(lblEmailId);
    }
	
	@FindBy(xpath = "//*[@id='accounttype']")
    private WebElement lblAccountType;
	//By lblAccountType = By.xpath(Utils.getRbPropValue("lblAccountTypeAcp"));
	public Boolean isLblAccountTypeVisible() {
        log.info("Checking the visibility of Account Type on CSR 360");
        log.info("Account Type visibility status {}: " + isElementVisible(lblAccountType));
        return isElementVisible(lblAccountType);
    }
	
	@FindBy(xpath = "//*[@id='lblAlternateEmailId']")
    private WebElement lblAlternateEmailId;
	//By lblAlternateEmailId = By.xpath(Utils.getRbPropValue("lblAlternateEmailIdAcp"));
	public Boolean isLblAlternateEmailIdVisible() {
        log.info("Checking the visibility of Secondary Email on CSR 360");
        log.info("Secondary Email visibility status {}: " + isElementVisible(lblAlternateEmailId));
        return isElementVisible(lblAlternateEmailId);
    }
	
	@FindBy(xpath = "//*[@id='libillmethod']")
    private WebElement lblBillingMethod;
	//By lblBillingMethod = By.xpath(Utils.getRbPropValue("lblBillingMethodAcp"));
	public String getLblBillingMethodAttributeValue(String attributeName) {
		String attributeValue = null;
		try {
			WebElement element = lblBillingMethod;
			if (element.isDisplayed()) {
				attributeValue = element.getAttribute(attributeName);
				log.info("User gets the value as  " + attributeValue + " for the webelement");
				//Runner.test.log(Status.PASS, "User gets the value as  " + attributeValue + "for the webelement");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.err.format("No Element Found to perform selection" + e);
			//Runner.test.log(Status.FAIL, "No Element Found to perform selection in the dropdown" + e);
		}
		return attributeValue;

	}
	
	@FindBy(css = "#lblBillMethod")
    private WebElement valueBillingMethod;
	//By valueBillingMethod = By.cssSelector(Utils.getRbPropValue("valueBillingMethodAcp"));
	public Boolean isValueBillingMethodVisible() {
        log.info("Checking the visibility of Status Bill on CSR 360");
        log.info("Status Bill visibility status {}: " + isElementVisible(valueBillingMethod));
        return isElementVisible(valueBillingMethod);
    }
	public String getValueBillingMethodLabel() {
        String label = getText(valueBillingMethod);
        log.info("BillTypeStatus Field Label {}: " + label);
        return label;
    }
	
	@FindBy(xpath = "//*[@id='textStatus']")
    private WebElement lblloginIP;
	//By lblloginIP = By.xpath(Utils.getRbPropValue("lblloginIPAcp"));
	public Boolean isLblloginIPVisible() {
        log.info("Checking the visibility of Last Login IP & Time on CSR 360");
        log.info("Last Login IP & Time visibility status {}: " + isElementVisible(lblloginIP));
        return isElementVisible(lblloginIP);
    }
	
	@FindBy(xpath = "//*[@id='lblMobile']")
    private WebElement lblMobileNumber;
	//By lblMobileNumber = By.xpath(Utils.getRbPropValue("lblMobileNumberAcp"));
	public Boolean isLblMobileNumberVisible() {
        log.info("Checking the visibility of Primary Phone Number on CSR 360");
        log.info("Primary Phone Number visibility status {}: " + isElementVisible(lblMobileNumber));
        return isElementVisible(lblMobileNumber);
    }
	
	@FindBy(xpath = "//*[@id='paperBill']")
    private WebElement lblPaperBill;
	//By lblPaperBill = By.xpath(Utils.getRbPropValue("lblPaperBillAcp"));
	public Boolean isLblPaperBillVisible() {
        log.info("Checking the visibility of Paper bill status on CSR 360");
        log.info("Paper bill status visibility status {}: " + isElementVisible(lblPaperBill));
        return isElementVisible(lblPaperBill);
    }
	
	@FindBy(xpath = "//*[@id='lblAlternateNumber']")
    private WebElement lblAlternateNumber;
	//By lblAlternateNumber = By.xpath(Utils.getRbPropValue("lblAlternateNumberAcp"));
	public Boolean isLblAlternateNumberVisible() {
        log.info("Checking the visibility of Secondary Phone Number on CSR 360");
        log.info("Secondary Phone Number visibility status {}: " + isElementVisible(lblAlternateNumber));
        return isElementVisible(lblAlternateNumber);
    }
	
	@FindBy(xpath = "//*[@id='lblEnrolledDate']")
    private WebElement lblCreateDate;
	//By lblCreateDate = By.xpath(Utils.getRbPropValue("lblCreateDateAcp"));
	public Boolean isLblCreateDateVisible() {
        log.info("Checking the visibility of Bill type Enrolled Date on CSR 360");
        log.info("Bill type Enrolled Date visibility status {}: " + isElementVisible(lblCreateDate));
        return isElementVisible(lblCreateDate);
    }
	
	@FindBy(xpath = "//*[@id='lblCity']")
    private WebElement lblCity;
	//By lblCity = By.xpath(Utils.getRbPropValue("lblCityAcp"));
	public Boolean isLblCityVisible() {
        log.info("Checking the visibility of City on CSR 360");
        log.info("City visibility status {}: " + isElementVisible(lblCity));
        return isElementVisible(lblCity);
    }
	
	@FindBy(xpath = "//*[@id='lblloginlocation']")
    private WebElement lblloginlocation;
	//By lblloginlocation = By.xpath(Utils.getRbPropValue("lblloginlocationAcp"));
	public Boolean isLblloginlocationVisible() {
        log.info("Checking the visibility of Last Login Location on CSR 360");
        log.info("Last Login Location visibility status {}: " + isElementVisible(lblloginlocation));
        return isElementVisible(lblloginlocation);
    }
	
	@FindBy(xpath = "//*[@id='Usertimezone']")
    private WebElement lblUsertimezone;
	//By lblUsertimezone = By.xpath(Utils.getRbPropValue("lblUsertimezoneAcp"));
	public Boolean isLblUsertimezoneVisible() {
        log.info("Checking the visibility of Time Zone on CSR 360");
        log.info("Time Zone visibility status {}: " + isElementVisible(lblUsertimezone));
        return isElementVisible(lblUsertimezone);
    }
	
	@FindBy(css = "#paperlessBillStatus")
    private WebElement lblBillTypeDetailsSection;
	//By lblBillTypeDetailsSection = By.cssSelector(Utils.getRbPropValue("lblBillTypeDetailsSectionAcp"));
	
	@FindBy(xpath = "(//h5[text()='Bill Type Details']/following::ul/li/label)[1]")
    private WebElement valueBillTypeStatus;
	//By valueBillTypeStatus = By.xpath(Utils.getRbPropValue("valueBillTypeStatusAcp"));
	public Boolean isValueBillTypeStatusVisible() {
        log.info("Checking the visibility of Status Bill on CSR 360");
        log.info("Status Bill visibility status {}: " + isElementVisible(valueBillTypeStatus));
        return isElementVisible(valueBillTypeStatus);
    }
	public String getValueBillTypeStatusFieldLabel() {
        String label = getText(valueBillTypeStatus);
        log.info("BillTypeStatus Field Label {}: " + label);
        return label;
    }
	
	@FindBy(xpath = "(//h5[text()='Bill Type Details']/following::ul/li/label)[2]")
    private WebElement valueBillTypeEnrolledDate;
	///By valueBillTypeEnrolledDate = By.xpath(Utils.getRbPropValue("valueBillTypeEnrolledDateAcp"));
	public Boolean isValueBillTypeEnrolledDateVisible() {
        log.info("Checking the visibility of Status Bill on CSR 360");
        log.info("Status Bill visibility status {}: " + isElementVisible(valueBillTypeEnrolledDate));
        return isElementVisible(valueBillTypeEnrolledDate);
    }
	public String getValueBillTypeEnrolledDateFieldLabel() {
        String label = getText(valueBillTypeEnrolledDate);
        log.info("BillTypeStatus Field Label {}: " + label);
        return label;
    }
	
	@FindBy(xpath = "//h5[text()='Service Plans']/following::ul[1]/li[@id='attrpower']")
    private WebElement lblPowerServicePlan;
	//By lblPowerServicePlan = By.xpath(Utils.getRbPropValue("lblPowerServicePlanAcp"));
	public boolean verifyLblPowerServicePlanElementPresent() {
		boolean bValue = false;
		WebElement element= lblPowerServicePlan;

		try {
			if (element.isDisplayed() || element.isEnabled())
				log.info("The element " + element.toString() + " is found");
			bValue = true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			log.info("The element is not found");
		    log.error("The element " + element.toString() + " is not found");
			bValue = false;
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			log.error("The element " + element.toString() + "is not available in Dom");
			log.info("The element is not available in Dom");
			bValue = false;
		}

		return bValue;

	}
	
	@FindBy(xpath = "//h5[text()='Service Plans']/following::ul[1]/li[@id='attrgas']")
    private WebElement lblGasServicePlan;
	//By lblGasServicePlan = By.xpath(Utils.getRbPropValue("lblGasServicePlanAcp"));
	public boolean verifyLblGasServicePlanElementPresent() {
		boolean bValue = false;
		WebElement element= lblGasServicePlan;

		try {
			if (element.isDisplayed() || element.isEnabled())
				log.info("The element " + element.toString() + " is found");
			bValue = true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			log.info("The element is not found");
		    log.error("The element " + element.toString() + " is not found");
			bValue = false;
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			log.error("The element " + element.toString() + "is not available in Dom");
			log.info("The element is not available in Dom");
			bValue = false;
		}

		return bValue;

	}
	
	@FindBy(xpath = "//h5[text()='Service Plans']/following::ul[1]/li[@id='attrwater']")
    private WebElement lblWaterServicePlan;
	//By lblWaterServicePlan = By.xpath(Utils.getRbPropValue("lblWaterServicePlanAcp"));
	public boolean verifyLblWaterServicePlanElementPresent() {
		boolean bValue = false;
		WebElement element= lblWaterServicePlan;

		try {
			if (element.isDisplayed() || element.isEnabled())
				log.info("The element " + element.toString() + " is found");
			bValue = true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			log.info("The element is not found");
		    log.error("The element " + element.toString() + " is not found");
			bValue = false;
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			log.error("The element " + element.toString() + "is not available in Dom");
			log.info("The element is not available in Dom");
			bValue = false;
		}

		return bValue;

	}
	
	@FindBy(xpath = "//h5[text()='Service Plans']/following::ul[1]/li[@id='attrsolor']")
    private WebElement lblSolarServicePlan;
	//By lblSolarServicePlan = By.xpath(Utils.getRbPropValue("lblSolarServicePlanAcp"));
	
	@FindBy(xpath = "//h5[text()='Service Plans']/following::ul[1]/li[@id='attrpower']/label")
    private WebElement valuePowerServicePlan;
	//By valuePowerServicePlan = By.xpath(Utils.getRbPropValue("valuePowerServicePlanAcp"));
	public String getValuePowerServicePlanLocatorText() {
		String locatorText = null;
		try {
			locatorText = valuePowerServicePlan.getText();
			log.info("Element Text is " + locatorText);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			log.info(locatorText + " : This element is not found");
			
		}
		return locatorText.trim();
	}
	
	@FindBy(xpath = "//h5[text()='Service Plans']/following::ul[1]/li[@id='attrgas']/label")
    private WebElement valueGasServicePlan;
	//By valueGasServicePlan = By.xpath(Utils.getRbPropValue("valueGasServicePlanAcp"));
	public String getValueGasServicePlanLocatorText() {
		String locatorText = null;
		try {
			locatorText = valueGasServicePlan.getText();
			log.info("Element Text is " + locatorText);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			log.info(locatorText + " : This element is not found");
			
		}
		return locatorText.trim();
	}
	
	@FindBy(xpath = "//h5[text()='Service Plans']/following::ul[1]/li[@id='attrwater']/label")
    private WebElement valueWaterServicePlan;
	//By valueWaterServicePlan = By.xpath(Utils.getRbPropValue("valueWaterServicePlanAcp"));
	public String getValueWaterServicePlanLocatorText() {
		String locatorText = null;
		try {
			locatorText = valueWaterServicePlan.getText();
			log.info("Element Text is " + locatorText);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			log.info(locatorText + " : This element is not found");
			
		}
		return locatorText.trim();
	}
	
	@FindBy(xpath = "//h5[text()='Service Plans']/following::ul[1]/li[@id='attrsolor']/label")
    private WebElement valueSolarServicePlan;
	//By valueSolarServicePlan = By.xpath(Utils.getRbPropValue("valueSolarServicePlanAcp"));

	@FindBy(xpath = "//*[@id='lblNewsRelease']/following-sibling::input[@type='checkbox']")
    private WebElement chkBoxNewsReleases;
	//By chkBoxNewsReleases = By.xpath(Utils.getRbPropValue("chkBoxNewsReleasesAcp"));
	public boolean verifyChkBoxNewsReleasesVisible() {

		try {
			chkBoxNewsReleases.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			e.getStackTrace();
			return false;
		}
	}
	public void verifyChkBoxNewsReleasesSelected() {
		try {
			boolean chkboxValue = chkBoxNewsReleases.isSelected();
			log.info("The checkbox value is: " + chkboxValue);
			Assert.assertTrue(chkboxValue);
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			
		}
	}
	public boolean getChkBoxNewsReleasesState() {
		boolean value = false;
		try {
			WebElement element = chkBoxNewsReleases;
			if (element.isDisplayed()) {
				value = element.isSelected();
				// System.out.println(value);
				log.info("User gets the value as  " + value + " from the check box");
				
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.err.format("No Element Found to perform selection" + e);
			
		}
		return value;

	}
	public void verifyChkBoxNewsReleasesNotSelected() {
		WebElement checkBox = chkBoxNewsReleases;
		boolean chkboxValue = checkBox.isSelected();
		log.info("The checkbox value is: " + chkboxValue);
		Assert.assertFalse(chkboxValue);
	}
	public void clickChkBoxNewsReleases() {
        click(chkBoxNewsReleases);
        log.info("Forgot Password Link clicked successfully.");
    }
	
	@FindBy(xpath = "//*[@id='lblSrvcOff']/following-sibling::input[@type='checkbox']")
    private WebElement chkBoxServiceOfferings;
	//By chkBoxServiceOfferings = By.xpath(Utils.getRbPropValue("chkBoxServiceOfferingsAcp"));
	public boolean verifyChkBoxServiceOfferingsVisible() {

		try {
			chkBoxServiceOfferings.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			e.getStackTrace();
			return false;
		}
	}
	public void verifyChkBoxServiceOfferingsSelected() {
		try {
			boolean chkboxValue = chkBoxServiceOfferings.isSelected();
			log.info("The checkbox value is: " + chkboxValue);
			Assert.assertTrue(chkboxValue);
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			
		}
	}
	
	@FindBy(xpath = "//*[@id='lblNewsletter']/following-sibling::input[@type='checkbox']")
    private WebElement chkBoxNewsletters;
	//By chkBoxNewsletters = By.xpath(Utils.getRbPropValue("chkBoxNewslettersAcp"));
	public boolean verifyChkBoxNewslettersVisible() {

		try {
			chkBoxNewsletters.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			e.getStackTrace();
			return false;
		}
	}
	public void verifyChkBoxNewslettersSelected() {
		try {
			boolean chkboxValue = chkBoxNewsletters.isSelected();
			log.info("The checkbox value is: " + chkboxValue);
			Assert.assertTrue(chkboxValue);
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			
		}
	}
	public boolean getChkBoxNewslettersState() {
		boolean value = false;
		try {
			WebElement element = chkBoxNewsletters;
			if (element.isDisplayed()) {
				value = element.isSelected();
				// System.out.println(value);
				log.info("User gets the value as  " + value + " from the check box");
				
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.err.format("No Element Found to perform selection" + e);
			
		}
		return value;

	}
	public void verifyChkBoxNewslettersNotSelected() {
		WebElement checkBox = chkBoxNewsletters;
		boolean chkboxValue = checkBox.isSelected();
		log.info("The checkbox value is: " + chkboxValue);
		Assert.assertFalse(chkboxValue);
	}
	public void clickChkBoxNewsletters() {
        click(chkBoxNewsletters);
        log.info("Forgot Password Link clicked successfully.");
    }
	
	
	@FindBy(xpath = "//*[@id='lblEnrgyToolkt']/following-sibling::input[@type='checkbox']")
    private WebElement chkBoxEnergySavingToolkits;
	//By chkBoxEnergySavingToolkits = By.xpath(Utils.getRbPropValue("chkBoxEnergySavingToolkitsAcp"));
	public boolean verifyChkBoxEnergySavingToolkitsVisible() {

		try {
			chkBoxEnergySavingToolkits.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			e.getStackTrace();
			return false;
		}
	}
	public void verifyChkBoxEnergySavingToolkitsSelected() {
		try {
			boolean chkboxValue = chkBoxEnergySavingToolkits.isSelected();
			log.info("The checkbox value is: " + chkboxValue);
			Assert.assertTrue(chkboxValue);
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			
		}
	}
	
	@FindBy(xpath = "//*[@id='lblBrochures']/following-sibling::input[@type='checkbox']")
    private WebElement chkBoxCoolTipsBrochures;
	//By chkBoxCoolTipsBrochures = By.xpath(Utils.getRbPropValue("chkBoxCoolTipsBrochuresAcp"));
	public boolean verifyChkBoxCoolTipsBrochuresVisible() {

		try {
			chkBoxCoolTipsBrochures.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			e.getStackTrace();
			return false;
		}
	}
	public void verifyChkBoxCoolTipsBrochuresSelected() {
		try {
			boolean chkboxValue = chkBoxCoolTipsBrochures.isSelected();
			log.info("The checkbox value is: " + chkboxValue);
			Assert.assertTrue(chkboxValue);
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			
		}
	}
	
	@FindBy(xpath = "//*[@id='lblCommBnftProgs']/following-sibling::input[@type='checkbox']")
    private WebElement chkBoxCommunityBenefitPrograms;
	//By chkBoxCommunityBenefitPrograms = By.xpath(Utils.getRbPropValue("chkBoxCommunityBenefitProgramsAcp"));
	public boolean verifyChkBoxCommunityBenefitProgramsVisible() {

		try {
			chkBoxCommunityBenefitPrograms.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			e.getStackTrace();
			return false;
		}
	}
	public void verifyChkBoxCommunityBenefitProgramsSelected() {
		try {
			boolean chkboxValue = chkBoxCommunityBenefitPrograms.isSelected();
			log.info("The checkbox value is: " + chkboxValue);
			Assert.assertTrue(chkboxValue);
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			
		}
	}
	
	@FindBy(xpath = "//input[@id='chkOutageText']")
    private WebElement chkBoxOutageText;
	//By chkBoxOutageText = By.xpath(Utils.getRbPropValue("chkBoxOutageTextAcp"));
	
	@FindBy(xpath = "//input[@id='chkOutageEmail']")
    private WebElement chkBoxOutageEmail;
	//By chkBoxOutageEmail = By.xpath(Utils.getRbPropValue("chkBoxOutageEmailAcp"));
	
	@FindBy(xpath = "//input[@id='chkOutageIvr']")
    private WebElement chkBoxOutageIvr;
	//By chkBoxOutageIvr = By.xpath(Utils.getRbPropValue("chkBoxOutageIvrAcp"));
	
	@FindBy(xpath = "//input[@id='chkBillingText']")
    private WebElement chkBoxBillingText;
	//By chkBoxBillingText = By.xpath(Utils.getRbPropValue("chkBoxBillingTextAcp"));
	
	@FindBy(xpath = "//input[@id='chkBillingEmail']")
    private WebElement chkBoxBillingEmail;
	//By chkBoxBillingEmail = By.xpath(Utils.getRbPropValue("chkBoxBillingEmailAcp"));
	
	@FindBy(xpath = "//input[@id='chkBillingIvr']")
    private WebElement chkBoxBillingIvr;
	//By chkBoxBillingIvr = By.xpath(Utils.getRbPropValue("chkBoxBillingIvrAcp"));
	
	@FindBy(xpath = "//input[@id='chkDRText']")
    private WebElement chkBoxDRText;
	//By chkBoxDRText = By.xpath(Utils.getRbPropValue("chkBoxDRTextAcp"));
	
	@FindBy(xpath = "//input[@id='chkDREmail']")
    private WebElement chkBoxDREmail;
	//By chkBoxDREmail = By.xpath(Utils.getRbPropValue("chkBoxDREmailAcp"));
	
	@FindBy(xpath = "//input[@id='chkDRIvr']")
    private WebElement chkBoxDRIvr;
	//By chkBoxDRIvr = By.xpath(Utils.getRbPropValue("chkBoxDRIvrAcp"));
	
	@FindBy(css = "#outageDiv")
    private WebElement panelOutage;
	//By panelOutage = By.cssSelector(Utils.getRbPropValue("panelOutageAcp"));
	
	@FindBy(css = "#billingDiv")
    private WebElement panelBilling;
	//By panelBilling = By.cssSelector(Utils.getRbPropValue("panelBillingAcp"));
	
	@FindBy(css = "#budgetDiv")
    private WebElement panelBudget;
	//By panelBudget = By.cssSelector(Utils.getRbPropValue("panelBudgetAcp"));
	
	@FindBy(css = "#demandResponseDiv")
    private WebElement panelDemandResponse;
	//By panelDemandResponse = By.cssSelector(Utils.getRbPropValue("panelDemandResponseAcp"));
	
	@FindBy(css = "#connectMeDiv")
    private WebElement panelConnectMe;
	//By panelConnectMe = By.cssSelector(Utils.getRbPropValue("panelConnectMeAcp"));
	
	@FindBy(css = "#servicesDiv")
    private WebElement panelServices;
	//By panelServices = By.cssSelector(Utils.getRbPropValue("panelServicesAcp"));
	
	@FindBy(css = "#leakAlertDiv")
    private WebElement panelLeakAlert;
	//By panelLeakAlert = By.cssSelector(Utils.getRbPropValue("panelLeakAlertAcp"));
	
	@FindBy(css = "#highUsageDiv")
    private WebElement panelHighUsage;
	//By panelHighUsage = By.cssSelector(Utils.getRbPropValue("panelHighUsageAcp"));
	
	@FindBy(xpath = "//input[@id='TxtOutageText']")
    private WebElement txtBoxOutageText;
	//By txtBoxOutageText = By.xpath(Utils.getRbPropValue("txtBoxOutageTextAcp"));
	
	@FindBy(xpath = "//input[@id='TxtOutageEmail']")
    private WebElement txtBoxOutageEmail;
	//By txtBoxOutageEmail = By.xpath(Utils.getRbPropValue("txtBoxOutageEmailAcp"));
	
	@FindBy(xpath = "//input[@id='TxtOutageIvr']")
    private WebElement txtBoxOutageIvr;
	//By txtBoxOutageIvr = By.xpath(Utils.getRbPropValue("txtBoxOutageIvrAcp"));
	
	@FindBy(xpath = "//input[@id='TxtBillingText']")
    private WebElement txtBoxBillingText;
	//By txtBoxBillingText = By.xpath(Utils.getRbPropValue("txtBoxBillingTextAcp"));
	
	@FindBy(xpath = "//input[@id='TxtBillingEmail']")
    private WebElement txtBoxBillingEmail;
	//By txtBoxBillingEmail = By.xpath(Utils.getRbPropValue("txtBoxBillingEmailAcp"));
	
	@FindBy(xpath = "//input[@id='TxtBillingIvr']")
    private WebElement txtBoxBillingIvr;
	//By txtBoxBillingIvr = By.xpath(Utils.getRbPropValue("txtBoxBillingIvrAcp"));
	
	@FindBy(xpath = "//input[@id='TxtDRText']")
    private WebElement txtBoxDRText;
	//By txtBoxDRText = By.xpath(Utils.getRbPropValue("txtBoxDRTextAcp"));
	
	@FindBy(xpath = "//input[@id='TxtDREmail']")
    private WebElement txtBoxDREmail;
	//By txtBoxDREmail = By.xpath(Utils.getRbPropValue("txtBoxDREmailAcp"));
	
	@FindBy(xpath = "//input[@id='TxtDRIvr']")
    private WebElement txtBoxDRIvr;
	//By txtBoxDRIvr = By.xpath(Utils.getRbPropValue("txtBoxDRIvrAcp"));
	
	@FindBy(xpath = "//select[@id='ddlElectricVehicle']")
    private WebElement ddlElectricVehicle;
	//By ddlElectricVehicle = By.xpath(Utils.getRbPropValue("ddlElectricVehicleAcp"));
	
	@FindBy(xpath = "//table[@id='jqxgridrequest_Grid']//th")
    private WebElement tblConnectMe;
	//By tblConnectMe = By.xpath(Utils.getRbPropValue("tblConnectMeAcp"));
	
	@FindBy(xpath = "//table[@id='jqxgridrequest_Grid']//tbody//td//a[text()='Click here']")
    private WebElement lnkClickHereConnectMe;
	//By lnkClickHereConnectMe = By.xpath(Utils.getRbPropValue("lnkClickHereConnectMeAcp"));
	
	@FindBy(xpath = "//table[@id='jqxgridrequest_Grid']//tbody//td//img")
    private WebElement lnkClickAttachment;
	//By lnkClickAttachment = By.xpath(Utils.getRbPropValue("lnkClickAttachmentAcp"));
	
	@FindBy(xpath = "//select[@id='ML_NCR_DDL_Topic']")
    private WebElement ddlTopic;
	//By ddlTopic = By.xpath(Utils.getRbPropValue("ddlTopicAcp"));
	
	@FindBy(xpath = "//input[@inputtype='Account']")
    private WebElement txtServiceAccount;
	//By txtServiceAccount = By.xpath(Utils.getRbPropValue("txtServiceAccountAcp"));
	
	@FindBy(xpath = "//input[@globalize='ML_LoginSupport_txtbx_Other_Email_ID']")
    private WebElement txtEmail;
	//By txtEmail = By.xpath(Utils.getRbPropValue("txtEmailAcp"));
	
	@FindBy(xpath = "//textarea[contains(@globalize,'ML_CONNECTME_Lbl_Comments')]")
    private WebElement txtAreaComments;
	//By txtAreaComments = By.xpath(Utils.getRbPropValue("txtAreaCommentsAcp"));
	
	@FindBy(xpath = "//table[@id='jqxServicegrid_Grid']//th")
    private WebElement tblService;
	//By tblService = By.xpath(Utils.getRbPropValue("tblServiceMeAcp"));
	
	@FindBy(xpath = "//table[@id='jqxServicegrid_Grid']//tbody//td//a[text()='Click here']")
    private WebElement lnkClickHereService;
	//By lnkClickHereService = By.xpath(Utils.getRbPropValue("lnkClickHereServiceAcp"));
	
	@FindBy(xpath = "//table[@id='jqxServicegrid_Grid']//tbody//td//img")
    private WebElement lnkClickAttachmentService;
	//By lnkClickAttachmentService = By.xpath(Utils.getRbPropValue("lnkClickAttachmentServiceAcp"));
	
	@FindBy(xpath = "//select[@id='ddl_Reason']")
    private WebElement ddlReasonService;
	//By ddlReasonService = By.xpath(Utils.getRbPropValue("ddlReasonServiceAcp"));
	
	@FindBy(xpath = "//div[@id='serAdvSearch']//label[text()='Service Account:']/following-sibling::input")
    private WebElement txtServiceAccountNumberService;
	//By txtServiceAccountNumberService = By.xpath(Utils.getRbPropValue("txtServiceAccountNumberServiceAcp"));
	public Boolean isTxtServiceAccountNumberServiceVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtServiceAccountNumberService));
        return isElementVisible(txtServiceAccountNumberService);
    }
	public WebElement getTxtServiceAccountNumberService() {
		return txtServiceAccountNumberService;
	}
	
	@FindBy(xpath = "//div[@id='cusAdvSearch']//label[text()='Service Account:']/following-sibling::input")
    private WebElement txtServiceAccountNumberCustomer;
	//By txtServiceAccountNumberCustomer = By.xpath(Utils.getRbPropValue("txtServiceAccountNumberCustomerAcp"));
	public Boolean isTxtServiceAccountNumberCustomerVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtServiceAccountNumberCustomer));
        return isElementVisible(txtServiceAccountNumberCustomer);
    }
	public WebElement getTxtServiceAccountNumberCustomer() {
		return txtServiceAccountNumberCustomer;
	}
	
	@FindBy(xpath = "//div[@id='scmUsrAdvSearch']//label[text()='Service Account:']/following-sibling::input")
    private WebElement txtServiceAccountNumberUser;
	//By txtServiceAccountNumberUser = By.xpath(Utils.getRbPropValue("txtServiceAccountNumberUserAcp"));
	public Boolean isTxtServiceAccountNumberUserVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtServiceAccountNumberUser));
        return isElementVisible(txtServiceAccountNumberUser);
    }
	public WebElement getTxtServiceAccountNumberUser() {
		return txtServiceAccountNumberUser;
	}
	
	@FindBy(xpath = "//input[@inputtype='Phone']")
    private WebElement txtPhoneNumberService;
	//By txtPhoneNumberService = By.xpath(Utils.getRbPropValue("txtPhoneNumberServiceAcp"));
	
	@FindBy(xpath = "//input[@inputtype='Email']")
    private WebElement txtEmailService;
	//By txtEmailService = By.xpath(Utils.getRbPropValue("txtEmailServiceAcp"));
	
	@FindBy(xpath = "//table[@id='jqxgridInteraction_Grid']//th")
    private WebElement tblInteraction;
	//By tblInteraction = By.xpath(Utils.getRbPropValue("tblInteractionAcp"));
	
	@FindBy(xpath = "//div[@id='jqxgridInteraction_Grid_info']")
    private WebElement lblInteractionCount;
	//By lblInteractionCount = By.xpath(Utils.getRbPropValue("lblInteractionCountAcp"));
	
	@FindBy(xpath = "//table[@id='Guest_Grid']//th")
    private WebElement tblGuest;
	//By tblGuest = By.xpath(Utils.getRbPropValue("tblGuestAcp"));
	
	@FindBy(xpath = "//div[@id='Guest_Grid_info']")
    private WebElement lblGuestCount;
	//By lblGuestCount = By.xpath(Utils.getRbPropValue("lblGuestCountAcp"));
	
	@FindBy(xpath = "//table[@id='Guest_Grid']//tbody//td[1]")
    private WebElement tblGuestName;
	//By tblGuestName = By.xpath(Utils.getRbPropValue("tblGuestNameAcp"));
	
	@FindBy(xpath = "//table[@id='Guest_Grid']//tbody//td[2]")
    private WebElement tblGuestEmailId;
	//By tblGuestEmailId = By.xpath(Utils.getRbPropValue("tblGuestEmailIdAcp"));

	@FindBy(xpath = "//select[@id='ddlAddress']")
    private WebElement ddlAddress;
	//By ddlAddress = By.xpath(Utils.getRbPropValue("ddlAddressAcp"));
	public List<String> getAllDdlAddress(){
		List<String> options = new ArrayList<String>();
		for (WebElement option : new Select(ddlAddress).getOptions()) {
			String sTxt = option.getText();
			if (option.getAttribute("value") != "")
				options.add(option.getText());
		}
		log.info("The list box contains the following values : " + options);
		return options;
	}

	@FindBy(xpath = "//*[@id='lblCharttitle']")
    private WebElement lblChartTitle;
	//By lblChartTitle = By.xpath(Utils.getRbPropValue("lblChartTitleAcp"));

	@FindBy(xpath = "//select[@id='Comparetype']")
    private WebElement ddlComparetype;
	//By ddlComparetype = By.xpath(Utils.getRbPropValue("ddlComparetypeAcp"));
	
	@FindBy(xpath = "//select[@id='ddlMonth']")
    private WebElement ddlCompareMonth;
	//By ddlCompareMonth = By.xpath(Utils.getRbPropValue("ddlCompareMonthAcp"));

	@FindBy(xpath = "//*[@class='highcharts-axis-title']")
    private WebElement lblConsumedUnitCompareTab;
	//By lblConsumedUnitCompareTab = By.xpath(Utils.getRbPropValue("lblCompareUnitAcp"));
	
	@FindBy(xpath = "//a[@id='imgKwh']")
    private WebElement lnkCompareUnitCCF;
	//By lnkCompareUnitCCF = By.xpath(Utils.getRbPropValue("lnkCompareUnitCCFAcp"));
	
	@FindBy(xpath = "//a[@globalize='ML_Compare_dollar']")
    private WebElement lnkCompareUnitDollar;
	//By lnkCompareUnitDollar = By.xpath(Utils.getRbPropValue("lnkCompareUnitDollarAcp"));
	
	@FindBy(xpath = "//a[@globalize='ML_Compare_KWH']")
    private WebElement lnkCompareUnitKWh;
	//By lnkCompareUnitKWh = By.xpath(Utils.getRbPropValue("lnkCompareUnitKWhAcp"));

	@FindBy(css = ".highcharts-tracker rect[stroke-width=\"1\"]")
    private WebElement lblgraphValueCompare;
	//By lblgraphValueCompare = By.cssSelector(Utils.getRbPropValue("lblgraphValueCompareAcp"));
	
	@FindBy(css = "#chartCompare .highcharts-tooltip text")
    private WebElement lblToolTipCompare;
	//By lblToolTipCompare = By.cssSelector(Utils.getRbPropValue("lblToolTipCompareAcp"));
	
	@FindBy(css = ".highcharts-tooltip text")
    private WebElement lblToolTipUsage;
	//By lblToolTipUsage = By.cssSelector(Utils.getRbPropValue("lblToolTipUsageAcp"));

	@FindBy(xpath = "//span[@id='CustomerDetailsPopUp_CompareSpendingUserControl_compareMe']//input")
    private WebElement lnkCompareMe;
	//By lnkCompareMe = By.xpath(Utils.getRbPropValue("lnkCompareMeAcp"));
	
	@FindBy(xpath = "//*[@id='btnZipcode']")
    private WebElement lnkCompareZip;
	//By lnkCompareZip = By.xpath(Utils.getRbPropValue("lnkCompareZipAcp"));
	
	@FindBy(xpath = "//*[@id='btnUtility']")
    private WebElement lnkCompareUtility;
	//By lnkCompareUtility = By.xpath(Utils.getRbPropValue("lnkCompareUtilityAcp"));
	
	@FindBy(xpath = "//*[@id='btnAll']")
    private WebElement lnkCompareAll;
	//By lnkCompareAll = By.xpath(Utils.getRbPropValue("lnkCompareAllAcp"));

	@FindBy(xpath = "//table[@id='jqxgridInteraction_Grid']//tbody//tr[1]/td[3]")
    private WebElement lblGridFirstRowEventName;
	//By lblGridFirstRowEventName = By.xpath(Utils.getRbPropValue("lblGridFirstRowEventNameAcp"));
	
	@FindBy(xpath = "//table[@id='jqxgridInteraction_Grid']//tbody//tr[1]/td[4]")
    private WebElement lblGridFirstRowEventDesp;
	//By lblGridFirstRowEventDesp = By.xpath(Utils.getRbPropValue("lblGridFirstRowEventDespAcp"));
	
	@FindBy(xpath = "//table[@id='jqxgridInteraction_Grid']//tbody//tr[1]/td[5]")
    private WebElement lblGridFirstRowEventDate;
	//By lblGridFirstRowEventDate = By.xpath(Utils.getRbPropValue("lblGridFirstRowEventDateAcp"));

	@FindBy(xpath = "//table[@id='jqxgridInteraction_Grid']//tbody//tr[1]/td[2]")
    private WebElement lblGridFirstRowUserName;
	//By lblGridFirstRowUserName = By.xpath(Utils.getRbPropValue("lblGridFirstRowUserNameAcp"));
	
	@FindBy(xpath = "//table[@id='jqxgridInteraction_Grid']//tbody//tr[1]/td[1]")
    private WebElement lblGridFirstRowModuleName;
	//By lblGridFirstRowModuleName = By.xpath(Utils.getRbPropValue("lblGridFirstRowModuleNameAcp"));

	@FindBy(xpath = "//div[@id='scmUsrAdvSearch']//h4")
    private WebElement advanceSearchPopup;
	//By advanceSearchPopup = By.xpath(Utils.getRbPropValue("advanceSearchPopupAcp"));
	
	@FindBy(xpath = "//div[@id='scmUsrAdvSearch']//div//button[text()='Search']")
    private WebElement btnSearchAdvanceSerachPopup;
	//By btnSearchAdvanceSerachPopup = By.xpath(Utils.getRbPropValue("btnSearchAdvanceSerachPopupAcp"));
	public void clickbtnSearchAdvanceSerachPopup() {
        click(btnSearchAdvanceSerachPopup);
        waitForImgCustomerDetailsLoadingToInvisible();
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(xpath = "//div[@class='customtable']//tbody//tr[@class='ng-scope']/td[6]")
    private WebElement username;
	//By username = By.xpath(Utils.getRbPropValue("usernameAcp"));
	
	@FindBy(xpath = "(//i[@title='Change Password'])[1]")
    private WebElement changePassword;
	//By changePassword = By.xpath(Utils.getRbPropValue("changePasswordAcp"));
	
	@FindBy(xpath = "//*[text()='Reset Password']")
    private WebElement resetPasswordPopup;
	//By resetPasswordPopup = By.xpath(Utils.getRbPropValue("resetPasswordPopupAcp"));
	
	@FindBy(css = "#btnsavePassword")
    private WebElement btnProceedResetPasswordPopup;
	//By btnProceedResetPasswordPopup = By.cssSelector(Utils.getRbPropValue("btnProceedResetPasswordPopupAcp"));
	
	@FindBy(xpath = "(//div[@id='outboxChangepass'])[2]//button")
    private WebElement btnCloseResetPasswordPopup;
	//By btnCloseResetPasswordPopup = By.xpath(Utils.getRbPropValue("btnCloseResetPasswordPopupAcp"));
	
	@FindBy(xpath = "(//div[@id='outboxChangepass'])[2]/div")
    private WebElement descriptionResetPasswordPopup;
	//By descriptionResetPasswordPopup = By.xpath(Utils.getRbPropValue("descriptionResetPasswordPopupAcp"));
	
	@FindBy(css = ".toast-message")
    private WebElement lblToastMessage;
	//By lblToastMessage = By.cssSelector(Utils.getRbPropValue("lblToastMessageAcp"));
	
	@FindBy(css = "#legends b")
    private WebElement lblRecordPagination;
	//By lblRecordPagination = By.cssSelector(Utils.getRbPropValue("lblRecordPaginationAcp"));
	public String getLblRecordPaginationLabel() {
		String label = null;
		try {
			// driver.findElement(locator).isDisplayed();
			label = lblRecordPagination.getText().trim();
			log.info("User gets the test object Label as: " + label);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return label;
	}
	
	@FindBy(xpath = "//div[label='Status:']/select")
    private WebElement ddlStatusOnly;
	//By ddlStatusOnly = By.xpath(Utils.getRbPropValue("ddlStatusOnlyAcp"));
	public WebElement getDdlStatusOnly() {
		return ddlStatusOnly;
	}
	
	@FindBy(css = "tr:nth-of-type(1)  .details.ng-scope")
    private WebElement lnkCustomerTabCustomerName;
	//By lnkCustomerTabCustomerName = By.cssSelector(Utils.getRbPropValue("lnkCustomerTabCustomerNameAcp"));

	public By getLnkCustomerNameTable(int index) throws UnknownHostException, SocketException  {
		By lblAccountType = By.xpath("//table[contains(@class,'table dataTable no-footer table_customernew MailListing tableCustomer')]//td[" + (index + 1)
				+ "]");
		return lblAccountType;
	}

	public By getLnkSCMUserTable(int index) throws UnknownHostException, SocketException {
		By lblAccountType = By.xpath("//table[contains(@class,'table dataTable no-footer table_customernew MailListing tableUser')]//td[" + index
				+ "]");
		return lblAccountType;
	}

	public By getLnkSCMUserTableCheckBox(int index) throws UnknownHostException, SocketException {
		By lblAccountType = By.xpath("//table[contains(@class,'table dataTable no-footer table_customernew MailListing tableUser')]//td[" + index
				+ "]/input");
		return lblAccountType;
	}

	public By getLblAccountType(int index) throws UnknownHostException, SocketException {
		By lblAccountType = By.xpath("//table[contains(@class,'table dataTable no-footer table_customernew MailListing svcaccountable')]//td[" + index
				+ "]");
		return lblAccountType;
	}

	public By getLblCustomerNameGrid(int index) throws UnknownHostException, SocketException {
		By lblCustomerNameGrid = By.xpath("(//*[@class='from-name-section cus_name_cust'])[" + index
				+ "]");
		return lblCustomerNameGrid;
	}

	public By getLblCustomerBillingDate(int index) throws UnknownHostException, SocketException {
		By lblCustomerBillingDate = By.xpath("//table[@id='jqxgridbill_Grid']//tr[" + index
				+ "]/td[1]");
		return lblCustomerBillingDate;
	}

	public By getLblCustomerPaymentgDate(int index) throws UnknownHostException, SocketException {
		By lblCustomerPaymentDate = By.xpath("//table[@id='jqxgridpayment_Grid']//tr[" + index
				+ "]/td[1]");
		return lblCustomerPaymentDate;
	}

	public By getLblCustomerBillingAmount(int index) throws UnknownHostException, SocketException {
		By lblCustomerBillingDate = By.xpath("//table[@id='jqxgridbill_Grid']//tr[" + index
				+ "]/td[2]");
		return lblCustomerBillingDate;
	}

	public By getLblCustomerPaymentAmount(int index) throws UnknownHostException, SocketException {
		By lblCustomerPaymentAmount = By.xpath("//table[@id='jqxgridpayment_Grid']//tr[" + index
				+ "]/td[2]");
		return lblCustomerPaymentAmount;
	}

	public By getLblCustomerName(int index) throws UnknownHostException, SocketException {
		By lblCustomerName = By.xpath("(//ul[@id='ulCustomerDetail']//li//*[contains(@class,'cus_name_cust')]//a)[" + index
				+ "]");
		return lblCustomerName;
	}

	public By getLblEmailID(int index) throws UnknownHostException, SocketException {
		By lblEmailID = By.xpath("(//ul[@id='ulCustomerDetail']//li//*[contains(@class,'email_id_cust')])[" + index
				+ "]");
		return lblEmailID;
	}

	public By getLblAccessType(int index) throws UnknownHostException, SocketException {
		By lblAccessType = By.xpath("(//ul[@id='ulCustomerDetail']//li//*[@class='from-name-section'])[" + index
				+ "]");
		return lblAccessType;
	}

	public By getListCustomerDetais(int index) throws UnknownHostException, SocketException {
		By liCustomer = By.xpath("(//ul[@id='ulCustomerDetail']//li)[" + index
				+ "]");
		return liCustomer;
	}

	public By getSearchResults(String value) {
		return By.xpath("//a[text()='" + value + "']");
	}

	@FindBy(css = ".lockimg.fa.fa-lock")
    private WebElement lnkLockCustomer;
	//By lnkLockCustomer = By.cssSelector(Utils.getRbPropValue("lnkLockCustomerAcp"));
	
	@FindBy(css = ".tooltiptd .material-icons")
    private WebElement lnkToggle;
	//By lnkToggle = By.cssSelector(Utils.getRbPropValue("lnkToggleAcp"));
	public void clickLnkToggle() {
        click(lnkToggle);
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(xpath = "//a[contains(text(),'Reset Password')]")
    private WebElement btnResetPassword;
	//By btnResetPassword = By.xpath(Utils.getRbPropValue("btnResetPasswordAcp"));
	public Boolean isBtnResetPasswordVisible() {
        log.info("Checking the visibility of UserName Textbox on the page.");
        log.info("UserName Textbox visibility status {}: " + isElementVisible(btnResetPassword));
        return isElementVisible(btnResetPassword);
    }
	
	@FindBy(css = ".topsectionbox_mainlist > li:nth-of-type(1)")
    private WebElement tabServiceAccounts;
	//By tabServiceAccounts = By.cssSelector(Utils.getRbPropValue("tabServiceAccountsAcp"));
	
	@FindBy(css = "li:nth-of-type(1) > .sectionbox_list  h3")
    private WebElement txtServiceAccounts;
	//By txtServiceAccounts = By.cssSelector(Utils.getRbPropValue("txtServiceAccountsAcp"));
	
	@FindBy(css = "li:nth-of-type(1) > .sectionbox_list  .sac_box > .ng-binding")
    private WebElement txtCountTotalServiceAccount;
	//By txtCountTotalServiceAccount = By.cssSelector(Utils.getRbPropValue("txtCountTotalServiceAccountAcp"));
	
	@FindBy(css = "li:nth-of-type(1) > .sectionbox_list > .sectionbox_list2 > .paddingbox > h4 > .ng-binding")
    private WebElement txtActiveCountServiceAccount;
	//By txtActiveCountServiceAccount = By.cssSelector(Utils.getRbPropValue("txtActiveCountServiceAccountAcp"));
	
	@FindBy(css = "li:nth-of-type(1) > .sectionbox_list > .sectionbox_list2 > .paddingbox > label")
    private WebElement txtActiveServiceAccount;
	//By txtActiveServiceAccount = By.cssSelector(Utils.getRbPropValue("txtActiveServiceAccountAcp"));
	
	@FindBy(css = "li:nth-of-type(1) > .sectionbox_list > .sectionbox_list3 > .paddingbox > label")
    private WebElement txtOtherServiceAccount;
	//By txtOtherServiceAccount = By.cssSelector(Utils.getRbPropValue("txtOtherServiceAccountAcp"));
	
	@FindBy(css = "li:nth-of-type(1) > .sectionbox_list > .sectionbox_list3 .ng-binding")
    private WebElement txtOtherCountServiceAccount;
	//By txtOtherCountServiceAccount = By.cssSelector(Utils.getRbPropValue("txtOtherCountServiceAccountAcp"));
	
	@FindBy(css = ".singleCustomers > .sectionbox_list  h3")
    private WebElement txtCustomers;
	//By txtCustomers = By.cssSelector(Utils.getRbPropValue("txtCustomersAcp"));
	
	@FindBy(css = ".singleCustomers > .sectionbox_list .ng-binding")
    private WebElement txtCountCustomers;
	//By txtCountCustomers = By.cssSelector(Utils.getRbPropValue("txtCountCustomersAcp"));
	
	@FindBy(css = "li:nth-of-type(3) > .sectionbox_list  h3")
    private WebElement txtUsers;
	//By txtUsers = By.cssSelector(Utils.getRbPropValue("txtUsersAcp"));
	public Boolean isTxtUsersVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtUsers));
        return isElementVisible(txtUsers);
    }
	
	@FindBy(css = "li:nth-of-type(3) > .sectionbox_list  .sac_box > .ng-binding")
    private WebElement txtTotalCountUsers;
	//By txtTotalCountUsers = By.cssSelector(Utils.getRbPropValue("txtTotalCountUsersAcp"));
	public String getTxtTotalCountUsersLabel() {
        String label = getText(txtTotalCountUsers);
        log.info("Current balance label is " + label);
        return label;
    }
	
	@FindBy(css = "li:nth-of-type(3) > .sectionbox_list > .sectionbox_list2 > .paddingbox > label")
    private WebElement txtActiveUsers;
	//By txtActiveUsers = By.cssSelector(Utils.getRbPropValue("txtActiveUsersAcp"));
	public void clickTxtActiveUsers() {
        click(txtActiveUsers);
        waitForImgCustomerDetailsLoadingToInvisible();
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(css = "li:nth-of-type(3) > .sectionbox_list > .sectionbox_list2 .ng-binding")
    private WebElement txtCountActiveUser;
	//By txtCountActiveUser = By.cssSelector(Utils.getRbPropValue("txtCountActiveUserAcp"));
	public String getTxtCountActiveUserLabel() {
        String label = getText(txtCountActiveUser);
        log.info("Current balance label is " + label);
        return label;
    }
	
	@FindBy(css = "li:nth-of-type(3) > .sectionbox_list > .sectionbox_list3 > .paddingbox > label")
    private WebElement txtOtherUsers;
	//By txtOtherUsers = By.cssSelector(Utils.getRbPropValue("txtOtherUsersAcp"));
	
	@FindBy(css = "li:nth-of-type(3) > .sectionbox_list > .sectionbox_list3 .ng-binding")
    private WebElement txtOtherCountUser;
	//By txtOtherCountUser = By.cssSelector(Utils.getRbPropValue("txtOtherCountUserAcp"));
	public String getTxtOtherCountUserLabel() {
        String label = getText(txtOtherCountUser);
        log.info("Current balance label is " + label);
        return label;
    }
	
	@FindBy(css = ".MailListing.dataTable.no-footer.svcaccountable.table.table_customernew > thead > tr > th")
    private WebElement txtServiceAccountGridHeaders;
	//By txtServiceAccountGridHeaders = By.cssSelector(Utils.getRbPropValue("txtServiceAccountGridHeadersAcp"));

	@FindBys(@FindBy(css = ".MailListing.dataTable.no-footer.svcaccountable.table.table_customernew > thead > tr > th[class*='sorting']"))
    private List<WebElement> txtServiceAccountSortableGridHeaders;
	//By txtServiceAccountSortableGridHeaders = By.cssSelector(Utils.getRbPropValue("txtServiceAccountSortableGridHeadersAcp"));
	public List<WebElement> getTxtServiceAccountSortableGridHeaders(){
		return txtServiceAccountSortableGridHeaders;
	}
	
	@FindBy(css = ".details.ng-scope")
    private WebElement linkServiceAccountNumber;
	//By linkServiceAccountNumber = By.cssSelector(Utils.getRbPropValue("linkServiceAccountNumberAcp"));
	
	@FindBy(css = "span#legends > .ng-binding")
    private WebElement txtTotalServiceAccountsPagination;
	//By txtTotalServiceAccountsPagination = By.cssSelector(Utils.getRbPropValue("txtTotalServiceAccountsPaginationAcp"));
	public String getTxtTotalServiceAccountsPaginationLabel() {
        String label = getText(txtTotalServiceAccountsPagination).trim();
        log.info("Forgot Password Link label is" + label);
        return label;
    }
	
	@FindBys(@FindBy(xpath = "//select[@ng-model='params.pageSize']/option"))
    private List<WebElement> txtPageSize;
	//By txtPageSize = By.xpath(Utils.getRbPropValue("txtPageSizeAcp"));
	public List<WebElement> getTxtPageSize(){
		return txtPageSize;
	}
	
	@FindBy(css = "#property>a")
    private WebElement lnkProperty360;
	//By lnkProperty360 = By.cssSelector(Utils.getRbPropValue("lnkProperty360Acp"));
	
	@FindBy(css = ".backmeCSR.fancybox-item.fancybox-close")
    private WebElement lnkBackToHome360;
	//By lnkBackToHome360 = By.cssSelector(Utils.getRbPropValue("lnkBackToHome360Acp"));
	
	@FindBy(css = "[ng-show] .ng-scope:nth-of-type(1) .cust_tooltip:nth-of-type(7) .ng-binding")
    private WebElement lnkLinkedUser;
	//By lnkLinkedUser = By.cssSelector(Utils.getRbPropValue("lnkLinkedUserAcp"));
	
	@FindBy(css = "li:nth-of-type(3) > .sectionbox_list > .sectionbox_list1")
    private WebElement linkUserTab;
	//By linkUserTab = By.cssSelector(Utils.getRbPropValue("linkUserTabAcp"));
	
	@FindBy(xpath = "//table[@ng-show ='sections.showCustomerSection']/thead/tr/th")
    private WebElement txtCustomerTabHeaders;
	//By txtCustomerTabHeaders = By.xpath(Utils.getRbPropValue("txtCustomerTabHeadersAcp"));
	
	@FindBys(@FindBy(css = ".tableCustomer > thead > tr > th[class*='sorting']"))
    private List<WebElement> txtCustomerTabSortableHeaders;
	//By txtCustomerTabSortableHeaders = By.cssSelector(Utils.getRbPropValue("txtCustomerTabSortableHeadersAcp"));
	public List<WebElement> getTxtCustomerTabSortableHeaders() {
		return txtCustomerTabSortableHeaders;
	}
	
	@FindBy(xpath = "//tbody//tr[@ng-repeat='item in customerAccountGridData']//*[@class='ng-binding']")
    private WebElement txtCustomersGridColumn;
	//By txtCustomersGridColumn = By.xpath(Utils.getRbPropValue("txtCustomersGridColumnAcp"));
	
	@FindBy(xpath = "//tbody//tr[@ng-repeat='item in customerAccountGridData']//td[@class='cust_tooltip']/a")
    private WebElement txtCustomersGridCustNameCol;
	//By txtCustomersGridCustNameCol = By.xpath(Utils.getRbPropValue("txtCustomersGridCustNameColAcp"));
	
	@FindBys(@FindBy(css = ".table.dataTable.no-footer.table_customernew.MailListing.tableUser > thead > tr > th"))
    private List<WebElement> txtUsersGridHeader;
	//By txtUsersGridHeader = By.cssSelector(Utils.getRbPropValue("txtUsersGridHeaderAcp"));
	public List<WebElement> getTxtUsersGridHeader() {
		return txtUsersGridHeader;
	}
	
	@FindBys(@FindBy(css = "#CustomerSection .material-icons"))
    private List<WebElement> lstIconsActionUser;
	//By lstIconsActionUser = By.cssSelector(Utils.getRbPropValue("lstIconsActionUserAcp"));
	public List<WebElement> getLstIconsActionUser() {
		return lstIconsActionUser;
	}
	
	@FindBy(css = ".Active")
    private WebElement lstIconActiveUser;
	//By lstIconActiveUser = By.cssSelector(Utils.getRbPropValue("lstIconActiveUserAcp"));
	
	@FindBys(@FindBy(css = ".details.ng-scope"))
    private List<WebElement> lstLnkUser;
	//By lstLnkUser = By.cssSelector(Utils.getRbPropValue("lstLnkUserAcp"));
	public List<WebElement> getLstLnkUser() {
		return lstLnkUser;
	}
	
	@FindBys(@FindBy(css = ".ng-binding.userNadmin"))
    private List<WebElement> lstTxtUsernameUser;
	//By lstTxtUsernameUser = By.cssSelector(Utils.getRbPropValue("lstTxtUsernameUserAcp"));
	public List<WebElement> getLstTxtUsernameUser() {
		return lstTxtUsernameUser;
	}
	
	@FindBys(@FindBy(xpath = "//tr[@class = 'ng-scope']/td[5]"))
    private List<WebElement> lstTxtContactUser;
	//By lstTxtContactUser = By.xpath(Utils.getRbPropValue("lstTxtContactUserAcp"));
	public List<WebElement> getLstTxtContactUser() {
		return lstTxtContactUser;
	}
	
	@FindBys(@FindBy(xpath = "//tr[@class = 'ng-scope']/td[6]/a"))
    private List<WebElement> lstLnkLinkedUser;
	//By lstLnkLinkedUser = By.xpath(Utils.getRbPropValue("lstLnkLinkedUserAcp"));
	public List<WebElement> getLstLnkLinkedUser() {
		return lstLnkLinkedUser;
	}
	
	@FindBy(xpath = "//a[@class='exportFooter']")
    private WebElement lstLnkFooterUser;
	//By lstLnkFooterUser = By.xpath(Utils.getRbPropValue("lstLnkFooterUserAcp"));
	
	@FindBys(@FindBy(css = "div[class='from-name-section action_cus dropdown-menu ng-scope show'] ul > li  a"))
    private List<WebElement> lstActionsUser;
	//By lstActionsUser = By.cssSelector(Utils.getRbPropValue("lstActionsUserAcp"));
	public List<WebElement> getLstActionsUser() {
		return lstActionsUser;
	}
	
	@FindBys(@FindBy(xpath = "//tr[@class = 'ng-scope']"))
    private List<WebElement> lstRowServiceAccounts;
	//By lstRowServiceAccounts = By.xpath(Utils.getRbPropValue("lstRowServiceAccountsAcp"));
	public List<WebElement> getLstRowServiceAccounts() {
		return lstRowServiceAccounts;
	}
	
	@FindBy(css = "input#txtFirstName")
    private WebElement txtEditUserFirstName;
	//By txtEditUserFirstName = By.cssSelector(Utils.getRbPropValue("txtEditUserFirstNameAcp"));
	public boolean verifyTxtEditUserFirstNameVisible() {

		try {
			txtEditUserFirstName.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			e.getStackTrace();
			return false;
		}
	}
	
	@FindBy(css = "input#txtLastName")
    private WebElement txtEditUserLastName;
	//By txtEditUserLastName = By.cssSelector(Utils.getRbPropValue("txtEditUserLastNameAcp"));
	public boolean verifyTxtEditUserLastNameVisible() {

		try {
			txtEditUserLastName.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			e.getStackTrace();
			return false;
		}
	}
	
	@FindBy(css = "input#txtMiddleName")
    private WebElement txtEditUserMiddleName;
	//By txtEditUserMiddleName = By.cssSelector(Utils.getRbPropValue("txtEditUserMiddleNameAcp"));
	public boolean verifyTxtEditUserMiddleNameVisible() {

		try {
			txtEditUserMiddleName.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			e.getStackTrace();
			return false;
		}
	}
	
	@FindBy(css = "input#txtEmailID")
    private WebElement txtEditUserEmailAddress;
	//By txtEditUserEmailAddress = By.cssSelector(Utils.getRbPropValue("txtEditUserEmailAddressAcp"));
	public boolean verifyTxtEditUserEmailAddressVisible() {

		try {
			txtEditUserEmailAddress.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			e.getStackTrace();
			return false;
		}
	}
	public WebElement getTxtEditUserEmailAddress() {
		return txtEditUserEmailAddress;
	}
	public void populateTxtEditUserEmailAddress(String userName) {
        log.info("Populating username {} :" + userName);
        sendKeys(txtEditUserEmailAddress, userName);
        log.info("Username populated successfully.");
    }
	
	@FindBy(css = "input#txtAltEmailId")
    private WebElement txtEditUserAlternateEmailAdd;
	//By txtEditUserAlternateEmailAdd = By.cssSelector(Utils.getRbPropValue("txtEditUserAlternateEmailAddAcp"));
	public boolean verifyTxtEditUserAlternateEmailAddVisible() {

		try {
			txtEditUserAlternateEmailAdd.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			e.getStackTrace();
			return false;
		}
	}
	
	@FindBy(css = "input#txtMobile")
    private WebElement txtEditUserPrimaryContactNum;
	//By txtEditUserPrimaryContactNum = By.cssSelector(Utils.getRbPropValue("txtEditUserPrimaryContactNumAcp"));
	public boolean verifyTxtEditUserPrimaryContactNumVisible() {

		try {
			txtEditUserPrimaryContactNum.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			e.getStackTrace();
			return false;
		}
	}
	
	@FindBy(css = "select#ddlContactType")
    private WebElement lstEditUserContactType;
	//By lstEditUserContactType = By.cssSelector(Utils.getRbPropValue("lstEditUserContactTypeAcp"));
	
	@FindBy(css = "input#txtLAndline")
    private WebElement txtEditUserSecondaryContactNum;
	//By txtEditUserSecondaryContactNum = By.cssSelector(Utils.getRbPropValue("txtEditUserSecondaryContactNumAcp"));
	public boolean verifyTxtEditUserSecondaryContactNumVisible() {

		try {
			txtEditUserSecondaryContactNum.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			e.getStackTrace();
			return false;
		}
	}
	
	@FindBy(css = ".alrt-paragrph")
	private WebElement popUp;
	public WebElement getPopUp() {
		return popUp;
	}
	public boolean isPopUpDisplayed() {
		if (popUp.isDisplayed()) {
			log.info(popUp.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(popUp + " is not Displayed on the Page");
			return false;
		}
	}
	public void explicitWaitForPopUp() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.visibilityOf(popUp));
		} catch (NoSuchElementException e) {
			log.error("No Element Found after wait" + e);
		}
	}
	
	@FindBy(css = "#submitBtn")
	private WebElement btnSubmitPopUp;
	public void clickBtnSubmitPopUp() {
        click(btnSubmitPopUp);
        log.info("Forgot Password Link clicked successfully.");
    }
	
	@FindBy(css = "select#Select1")
    private WebElement lstEditUserContactType2;
	//By lstEditUserContactType2 = By.cssSelector(Utils.getRbPropValue("lstEditUserContactType2Acp"));
	
	@FindBy(css = "input#cancelButton")
    private WebElement btnEditUserCancel;
	//By btnEditUserCancel = By.cssSelector(Utils.getRbPropValue("btnEditUserCancelAcp"));
	public WebElement getBtnEditUserCancel() {
		return btnEditUserCancel;
	}
	public void clickBtnEditUserCancel() {
        click(btnEditUserCancel);
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(css = "input#ResetBtn")
    private WebElement btnEditUserReset;
	//By btnEditUserReset = By.cssSelector(Utils.getRbPropValue("btnEditUserResetAcp"));
	
	@FindBy(css = "input#AddUserSaveBtn")
    private WebElement btnEditUserSave;
	//By brnEditUserSave = By.cssSelector(Utils.getRbPropValue("brnEditUserSaveAcp"));
	public void clickBtnEditUserSave() {
        click(btnEditUserSave);
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(css = ".w2ui-popup-btn")
    private WebElement btnOKEditCust;
	//By btnOKEditCust = By.cssSelector(Utils.getRbPropValue("btnOKEditCustAcp"));

	@FindBy(css = "input#btnDeleteUser")
    private WebElement btnDeleteRecordProceed;
	//By btnDeleteRecordProceed = By.cssSelector(Utils.getRbPropValue("btnDeleteRecordProceedAcp"));
	
	@FindBy(css = "#outboxDeleteUser button")
    private WebElement btnDeleteRecordCancel;
	//By btnDeleteRecordCancel = By.cssSelector(Utils.getRbPropValue("btnDeleteRecordCancelAcp"));
	
	@FindBy(css = ".DeleteUser.close")
    private WebElement iconDeleteRecordClose;
	//By iconDeleteRecordClose = By.cssSelector(Utils.getRbPropValue("iconDeleteRecordCloseAcp"));
	
	@FindBy(css = "div#PopupDeleteUser .modal-title")
    private WebElement txtDeleteRecordPopupHeader;
	//By txtDeleteRecordPopupHeader = By.cssSelector(Utils.getRbPropValue("txtDeleteRecordPopupHeaderAcp"));
	
	@FindBy(css = "div#outboxDeleteUser  p")
    private WebElement txtDeleteRecordPopup;
	//By txtDeleteRecordPopup = By.cssSelector(Utils.getRbPropValue("txtDeleteRecordPopupAcp"));
	
	@FindBy(xpath = "//div[label='Status:']/select")
    private WebElement lstUserAdvanceSearchStatus;
	//By lstUserAdvanceSearchStatus = By.xpath(Utils.getRbPropValue("lstUserAdvanceSearchStatusAcp"));
	public WebElement getLstUserAdvanceSearchStatus() {
		return lstUserAdvanceSearchStatus;
	}
	
	@FindBy(xpath = "//div[@id='scmUsrAdvSearch']//label[text()='Service Account:']/following-sibling::input")
    private WebElement txtServiceAccountNumberUserAdv;
	//By txtServiceAccountNumberUserAdv = By.xpath(Utils.getRbPropValue("txtServiceAccountNumberUserAdvAcp"));
	public WebElement getTxtServiceAccountNumberUserAdv() {
		return txtServiceAccountNumberUserAdv;
	}
	
	@FindBy(xpath = "//select[@ng-model = 'scmUserAdvanceSearchOptions.selectedAccountType']")
    private WebElement ddlAccountTypeUserAdv;
	//By ddlAccountTypeUserAdv = By.xpath(Utils.getRbPropValue("ddlAccountTypeUserAdvAcp"));
	public WebElement getDdlAccountTypeUserAdv() {
		return ddlAccountTypeUserAdv;
	}
	
	@FindBy(xpath = "//div[@id='scmUsrAdvSearch']//label[text()='Customer Number:']/following-sibling::input")
    private WebElement txtCustomerNumberUserAdv;
	//By txtCustomerNumberUserAdv = By.xpath(Utils.getRbPropValue("txtCustomerNumberUserAdvAcp"));
	public WebElement getTxtCustomerNumberUserAdv() {
		return txtCustomerNumberUserAdv;
	}
	
	@FindBy(xpath = "//div[@id='scmUsrAdvSearch']//label[text()='ZIP Code:']/following-sibling::input")
    private WebElement txtZipcodeUserAdv;
	//By txtZipcodeUserAdv = By.xpath(Utils.getRbPropValue("txtZipcodeUserAdvAcp"));
	public WebElement getTxtZipcodeUserAdv() {
		return txtZipcodeUserAdv;
	}
	
	@FindBy(xpath = "//div[@id='scmUsrAdvSearch']//label[text()='City:']/following-sibling::input")
    private WebElement txtCityUserAdv;
	//By txtCityUserAdv = By.xpath(Utils.getRbPropValue("txtCityUserAdvAcp"));
	public WebElement getTxtCityUserAdv() {
		return txtCityUserAdv;
	}
	
	@FindBy(xpath = "//div[@id='scmUsrAdvSearch']//label[text()='First Name:']/following-sibling::input")
    private WebElement txtFirstNameUserAdv;
	//By txtFirstNameUserAdv = By.xpath(Utils.getRbPropValue("txtFirstNameUserAdvAcp"));
	public WebElement getTxtFirstNameUserAdv() {
		return txtFirstNameUserAdv;
	}
	
	@FindBy(xpath = "//div[@id='scmUsrAdvSearch']//label[text()='Last Name:']/following-sibling::input")
    private WebElement txtLastNameUserAdv;
	//By txtLastNameUserAdv = By.xpath(Utils.getRbPropValue("txtLastNameUserAdvAcp"));
	public WebElement getTxtLastNameUserAdv() {
		return txtLastNameUserAdv;
	}
	
	@FindBy(xpath = "//div[@id='scmUsrAdvSearch']//label[contains(text(),'Email')]/following-sibling::input")
    private WebElement txtEmailUserAdv;
	//By txtEmailUserAdv = By.xpath(Utils.getRbPropValue("txtEmailUserAdvAcp"));
	public WebElement getTxtEmailUserAdv() {
		return txtEmailUserAdv;
	}
	
	@FindBys(@FindBy(xpath = "//table[@id = 'jqxgridbill_Grid']/tbody/tr/td[1]"))
	private List<WebElement> lstLblBillingBillDate;
	//By lstLblBillingBillDate = By.xpath(Utils.getRbPropValue("lstLblBillingBillDateAcp"));
	public List<WebElement> getLstLblBillingBillDate() {
        return lstLblBillingBillDate;
    }
	
	@FindBys(@FindBy(xpath = "//table[@id = 'jqxgridbill_Grid']/tbody/tr/td[2]"))
	private List<WebElement> lstLblBillingBillAmount;
	//By lstLblBillingBillAmount = By.xpath(Utils.getRbPropValue("lstLblBillingBillAmountAcp"));
	public List<WebElement> getLstLblBillingBillAmount() {
        return lstLblBillingBillAmount;
    }
	
	@FindBy(xpath = "//table[@id = 'jqxgridbill_Grid']/tbody/tr/td[3]")
	private List<WebElement> lstlnkBillingViewBillPdf;
	//By lstlnkBillingViewBillPdf = By.xpath(Utils.getRbPropValue("lstlnkBillingViewBillPdfAcp"));
	public List<WebElement> getLstlnkBillingViewBillPdf() {
        return lstlnkBillingViewBillPdf;
    }

	@FindBy(xpath = "//table[@id = 'jqxgridpayment_Grid']/tbody/tr/td[1]")
    private WebElement lstlblPaymentsDate;
	//By lstlblPaymentsDate = By.xpath(Utils.getRbPropValue("lstlblPaymentsDateAcp"));
	
	@FindBy(xpath = "//table[@id = 'jqxgridpayment_Grid']/tbody/tr/td[2]")
    private WebElement lstlblPaymentTransactionAmount;
	//By lstlblPaymentTransactionAmount = By.xpath(Utils.getRbPropValue("lstlblPaymentTransactionAmountAcp"));
	
	@FindBy(css = "div#jqxgridbill_Grid_info")
    private WebElement lblTotalEnteriesBilling;
	//By lblTotalEnteriesBilling = By.cssSelector(Utils.getRbPropValue("lblTotalEnteriesBillingAcp"));
	public String getTotalEnteriesBilling() {
		String label = null;
		try {
			// driver.findElement(locator).isDisplayed();
			label = lblTotalEnteriesBilling.getText().trim();
			log.info("User gets the test object Label as: " + label);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return label;
	}
	
	@FindBy(css = "#jqxgridpayment_Grid_info")
    private WebElement lblTotalEnteriesPaymentTransactions;
	//By lblTotalEnteriesPaymentTransactions = By.cssSelector(Utils.getRbPropValue("lblTotalEnteriesPaymentTransactionsAcp"));
	public String getLblTotalEnteriesPaymentTransactions() {
		String label = null;
		try {
			// driver.findElement(locator).isDisplayed();
			label = lblTotalEnteriesPaymentTransactions.getText().trim();
			log.info("User gets the test object Label as: " + label);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return label;
	}
	
	@FindBy(xpath = "//th[@aria-controls = 'jqxgridbill_Grid']")
    private WebElement lnkBillingGridHeader;
	//By lnkBillingGridHeader = By.xpath(Utils.getRbPropValue("lnkBillingGridHeaderAcp"));

	@FindBy(xpath = "//th[@aria-controls = 'jqxgridpayment_Grid']")
    private WebElement lnkPaymentGridHeader;
	//By lnkPaymentGridHeader = By.xpath(Utils.getRbPropValue("lnkPaymentGridHeaderAcp"));

	@FindBy(css = "label#statusUser")
    private WebElement lblAccountstatusPt;
	//By lblAccountstatusPt = By.cssSelector(Utils.getRbPropValue("lblAccountstatusPtAcp"));
	public String getLblAccountstatusPtLabel() {
		String sTitle = null;
		try {
			lblAccountstatusPt.isDisplayed();
			sTitle = lblAccountstatusPt.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}

	@FindBy(css = "label#custName")
    private WebElement lblCustomerNamePt;
	//By lblCustomerNamePt = By.cssSelector(Utils.getRbPropValue("lblCustomerNamePtAcp"));
	public String getLblCustomerNamePtLabel() {
		String sTitle = null;
		try {
			lblCustomerNamePt.isDisplayed();
			sTitle = lblCustomerNamePt.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}

	@FindBy(css = "label#lblLoginId")
    private WebElement lblUsernamePt;
	//By lblUsernamePt = By.cssSelector(Utils.getRbPropValue("lblUsernamePtAcp"));
	public String getLblUsernamePtLabel() {
		String sTitle = null;
		try {
			lblUsernamePt.isDisplayed();
			sTitle = lblUsernamePt.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}

	@FindBy(css = "label#accounttype")
    private WebElement lblAccountTypePt;
	//By lblAccountTypePt = By.cssSelector(Utils.getRbPropValue("lblAccountTypePtAcp"));
	public String getLblAccountTypePtLabel() {
		String sTitle = null;
		try {
			lblAccountTypePt.isDisplayed();
			sTitle = lblAccountTypePt.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}

	@FindBy(css = "label#lblEmailId")
    private WebElement lblPrimaryEmailIDPt;
	//By lblPrimaryEmailIDPt = By.cssSelector(Utils.getRbPropValue("lblPrimaryEmailIDPtAcp"));
	public String getLblPrimaryEmailIDPtLabel() {
		String sTitle = null;
		try {
			lblPrimaryEmailIDPt.isDisplayed();
			sTitle = lblPrimaryEmailIDPt.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}
	public WebElement getLblPrimaryEmailIDPt() {
		return lblPrimaryEmailIDPt;
	}

	@FindBy(css = "label#lblAlternateEmailId")
    private WebElement lblSecondaryEmailPt;
	//By lblSecondaryEmailPt = By.cssSelector(Utils.getRbPropValue("lblSecondaryEmailPtAcp"));
	public String getLblSecondaryEmailPtLabel() {
		String sTitle = null;
		try {
			lblSecondaryEmailPt.isDisplayed();
			sTitle = lblSecondaryEmailPt.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}
	

	@FindBy(css = "label#lblMobile")
    private WebElement lblPrimaryPhoneNumberPt;
	//By lblPrimaryPhoneNumberPt = By.cssSelector(Utils.getRbPropValue("lblPrimaryPhoneNumberPtAcp"));
	public String getLblPrimaryPhoneNumberPtLabel() {
		String sTitle = null;
		try {
			lblPrimaryPhoneNumberPt.isDisplayed();
			sTitle = lblPrimaryPhoneNumberPt.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}

	@FindBy(css = "label#lblAlternateNumber")
    private WebElement lblSecondaryPhoneNumberPt;
	//By lblSecondaryPhoneNumberPt = By.cssSelector(Utils.getRbPropValue("lblSecondaryPhoneNumberPtAcp"));
	public String getLblSecondaryPhoneNumberPtLabel() {
		String sTitle = null;
		try {
			lblSecondaryPhoneNumberPt.isDisplayed();
			sTitle = lblSecondaryPhoneNumberPt.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}

	@FindBy(css = "label#lblCity")
    private WebElement lblCityNamePt;
	//By lblCityNamePt = By.cssSelector(Utils.getRbPropValue("lblCityNamePtAcp"));
	public String getLblCityNamePtLabel() {
		String sTitle = null;
		try {
			lblCityNamePt.isDisplayed();
			sTitle = lblCityNamePt.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}

	@FindBy(css = "label#Usertimezone")
    private WebElement lblTimeZonePt;
	//By lblTimeZonePt = By.cssSelector(Utils.getRbPropValue("lblTimeZonePtAcp"));
	public String getLblTimeZonePtLabel() {
		String sTitle = null;
		try {
			lblTimeZonePt.isDisplayed();
			sTitle = lblTimeZonePt.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}

	@FindBy(css = "label#textStatus")
    private WebElement lblLastLoginTimePt;
	//By lblLastLoginTimePt = By.cssSelector(Utils.getRbPropValue("lblLastLoginTimePtAcp"));
	public String getLblLastLoginTimePtLabel() {
		String sTitle = null;
		try {
			lblLastLoginTimePt.isDisplayed();
			sTitle = lblLastLoginTimePt.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}

	@FindBy(css = "label#lblloginlocation")
    private WebElement lblLastLoginLocationPt;
	//By lblLastLoginLocationPt = By.cssSelector(Utils.getRbPropValue("lblLastLoginLocationPtAcp"));
	public String getLblLastLoginLocationPtLabel() {
		String sTitle = null;
		try {
			lblLastLoginLocationPt.isDisplayed();
			sTitle = lblLastLoginLocationPt.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}

	@FindBy(css = "label#lblSCMUserName")
    private WebElement lblRegisteredNamePt;
	//By lblRegisteredNamePt = By.cssSelector(Utils.getRbPropValue("lblRegisteredNamePtAcp"));

	@FindBy(css = "label#lblCustAccountNumber")
    private WebElement lblAccountNumberPt;
	//By lblAccountNumberPt = By.cssSelector(Utils.getRbPropValue("lblAccountNumberPtAcp"));
	
	@FindBy(css = "label#lblCustServiceAddress")
    private WebElement lblServiceAddressPt;
	//By lblServiceAddressPt = By.cssSelector(Utils.getRbPropValue("lblServiceAddressPtAcp"));
	
	@FindBy(css = "label#lblCustAccountStatus")
    private WebElement lblCustAccountStatusPt;
	//By lblCustAccountStatusPt = By.cssSelector(Utils.getRbPropValue("lblCustAccountStatusPtAcp"));
	
	@FindBy(css = "label#lblCustEmailPhone")
    private WebElement lblContactDetailsPt;
	//By lblContactDetailsPt = By.cssSelector(Utils.getRbPropValue("lblContactDetailsPtAcp"));

	@FindBy(css = "label#paperBill")
    private WebElement lblPaperBillStatusPt;
	//By lblPaperBillStatusPt = By.cssSelector(Utils.getRbPropValue("lblPaperBillStatusPtAcp"));

	@FindBy(css = "label#lblCreateDate")
    private WebElement lblCreatedDatePt;
	//By lblCreatedDatePt = By.cssSelector(Utils.getRbPropValue("lblCreatedDatePtAcp"));

	@FindBy(css = "label#lblZipCode")
    private WebElement lblZipCodePt;
	//By lblZipCodePt = By.cssSelector(Utils.getRbPropValue("lblZipCodePtAcp"));

	@FindBy(css = "label#lblpowerplan")
    private WebElement lblServicePlansPowerPt;
	//By lblServicePlansPowerPt = By.cssSelector(Utils.getRbPropValue("lblServicePlansPowerPtAcp"));

	@FindBy(css = "label#lblgasplan")
    private WebElement lblServicePlansGasPt;
	//By lblServicePlansGasPt = By.cssSelector(Utils.getRbPropValue("lblServicePlansGasPtAcp"));

	@FindBy(css = "label#lblwaterplan")
    private WebElement lblServicePlansWaterPt;
	//By lblServicePlansWaterPt = By.cssSelector(Utils.getRbPropValue("lblServicePlansWaterPtAcp"));

	@FindBy(css = "div#plans > ul > li:nth-of-type(4)")
    private WebElement lblServicePlansSolarPt;
	//By lblServicePlansSolarPt = By.cssSelector(Utils.getRbPropValue("lblServicePlansSolarPtAcp"));

	@FindBy(css = "//table[@id = 'jqxgridMeterNumber_Grid']/tbody/tr/td")
    private WebElement lstMetereDetailsPt;
	//By lstMetereDetailsPt = By.cssSelector(Utils.getRbPropValue("lstMetereDetailsPtAcp"));

	@FindBys(@FindBy(css = ".tableUser tr[ng-repeat*='scmAccountGridData']"))
    private List<WebElement> tblCSRCustomersRows;
	//private final By tblCSRCustomersRows = By.cssSelector(Utils.getRbPropValue("tblCSRCustomersRowsAcp"));
	public List<WebElement> getTblCSRCustomersRows() {
		return tblCSRCustomersRows;
	}

	@FindBy(css = ".tableUser tr[ng-repeat*='scmAccountGridData'] .dropdown-toggle")
    private WebElement lnkThreeDotsMenu;
	//private final By lnkThreeDotsMenu = By.cssSelector(Utils.getRbPropValue("lnkThreeDotsMenuAcp"));
	public void clickLnkThreeDotsMenu() {
        click(lnkThreeDotsMenu);
        log.info("SignIn Button clicked successfully.");
    }

	public By getLnkResetPassDynamic(String userId) {
		By lnkResetPassDynamic = By.cssSelector("#ResetXXXX a".replaceAll("XXXX", userId));
		return lnkResetPassDynamic;
	}

	@FindBy(css = "#PopupChangeCustomerPassword h4.modal-title")
    private WebElement lblResetPwdPopUpHead;
	//private final By lblResetPwdPopUpHead = By.cssSelector(Utils.getRbPropValue("lblResetPwdPopUpHeadAcp"));
	public WebElement getLblResetPwdPopUpHead() {
		return lblResetPwdPopUpHead;
	}

	@FindBy(css = "#PopupChangeCustomerPassword #outboxChangepass .form-group > p")
    private WebElement lblMsgResetPwdPopUp;
	//private final By lblMsgResetPwdPopUp = By.cssSelector(Utils.getRbPropValue("lblMsgResetPwdPopUpAcp"));
	public WebElement getLblMsgResetPwdPopUp() {
		return lblMsgResetPwdPopUp;
	}

	@FindBy(css = "#PopupChangeCustomerPassword .outage_sbt_box button")
    private WebElement btnCloseResetPwdPopUp;
	//private final By btnCloseResetPwdPopUp = By.cssSelector(Utils.getRbPropValue("btnCloseResetPwdPopUpAcp"));
	public Boolean isBtnCloseResetPwdPopUpVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(btnCloseResetPwdPopUp));
        return isElementVisible(btnCloseResetPwdPopUp);
    }

	@FindBy(css = "#btnsavePassword")
    private WebElement btnProceedResetPwdPopUp;
	//private final By btnProceedResetPwdPopUp = By.cssSelector(Utils.getRbPropValue("btnProceedResetPwdPopUpAcp"));
	public Boolean isBtnProceedResetPwdPopUpVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(btnProceedResetPwdPopUp));
        return isElementVisible(btnProceedResetPwdPopUp);
    }
	public void clickBtnProceedResetPwdPopUp() {
        click(btnProceedResetPwdPopUp);
        log.info("SignIn Button clicked successfully.");
    }

	@FindBy(css = ".backmeCSR")
    private WebElement lnkBackToCSRAcp;
	//By lnkBackToCSRAcp = By.cssSelector(Utils.getRbPropValue("lnkBackToCSRAcp"));

	@FindBy(css = ".top-header-area.top-header-areaCstEng h2")
    private WebElement lblDeleteProfileRequestsHeadingAcp;
	//By lblDeleteProfileRequestsHeadingAcp = By.cssSelector(Utils.getRbPropValue("lblDeleteProfileRequestsHeadingAcp"));

	@FindBy(css = "#txtBasicSearch")
    private WebElement inputSearchBoxAcp;
	//By inputSearchBoxAcp = By.cssSelector(Utils.getRbPropValue("inputSearchBoxAcp"));

	@FindBy(css = ".btn_search.filterBtn")
    private WebElement btnSearchAcp;
	//By btnSearchAcp = By.cssSelector(Utils.getRbPropValue("btnSearchAcp"));

	@FindBy(css = ".top_user_left_area .help_icon_img")
    private WebElement lnkInfoIconSearchboxAcp;
	//By lnkInfoIconSearchboxAcp = By.cssSelector(Utils.getRbPropValue("lnkInfoIconSearchboxAcp"));

	@FindBy(css = "#btnDeleteRequest")
    private WebElement lnkDeleteRequestAcp;
	//By lnkDeleteRequestAcp = By.cssSelector(Utils.getRbPropValue("lnkDeleteRequestAcp"));

	@FindBy(css = "#btnCancelRequest")
    private WebElement lnkRejestRequestAcp;
	//By lnkRejestRequestAcp = By.cssSelector(Utils.getRbPropValue("lnkRejestRequestAcp"));

	@FindBy(css = "#chkHeader")
    private WebElement inputAllCheckBoxAcp;
	//By inputAllCheckBoxAcp = By.cssSelector(Utils.getRbPropValue("inputAllCheckBoxAcp"));

	@FindBy(xpath = "//table[@id='pendingrequests_datatable']/thead/tr/th")
    private WebElement tblPendingRequestsHeaders;
	//By tblPendingRequestsHeaders = By.xpath(Utils.getRbPropValue("tblPendingRequestsHeadersAcp"));

	@FindBy(css = "#pendingrequests_datatable th:nth-child(2)")
    private WebElement tblHeaderFirstColomnAcp;
	//By tblHeaderFirstColomnAcp = By.cssSelector(Utils.getRbPropValue("tblHeaderFirstColomnAcp"));

	@FindBy(css = "#pendingrequests_datatable th:nth-child(3)")
    private WebElement tblHeaderSecondColomnAcp;
	//By tblHeaderSecondColomnAcp = By.cssSelector(Utils.getRbPropValue("tblHeaderSecondColomnAcp"));

	@FindBy(css = "#pendingrequests_datatable tbody .chkSelector")
    private WebElement tblRecordsCheckboxAcp;
	//By tblRecordsCheckboxAcp = By.cssSelector(Utils.getRbPropValue("tblRecordsCheckboxAcp"));

	@FindBy(css = "#pendingrequests_datatable tbody  td:nth-child(2)")
    private WebElement tblRecordsCustomerNameAcp;
	//By tblRecordsCustomerNameAcp = By.cssSelector(Utils.getRbPropValue("tblRecordsCustomerNameAcp"));

	@FindBy(css = "#pendingrequests_datatable tbody  td:nth-child(2)")
    private WebElement tblRecordsRequestDateAcp;
	//By tblRecordsRequestDateAcp = By.cssSelector(Utils.getRbPropValue("tblRecordsRequestDateAcp"));

	@FindBy(css = "#pendingrequests_datatable tbody  td:nth-child(3)")
    private WebElement tblRecordsName;
	//By tblRecordsName = By.cssSelector(Utils.getRbPropValue("tblRecordsNameAcp"));

	@FindBy(css = "#pendingrequests_datatable tbody  td:nth-child(4)")
    private WebElement tblRecordsUserName;
	//By tblRecordsUserName = By.cssSelector(Utils.getRbPropValue("tblRecordsUserNameAcp"));

	@FindBy(css = "#pendingrequests_datatable tbody  td:nth-child(5)")
    private WebElement tblRecordsEmail;
	//By tblRecordsEmail = By.cssSelector(Utils.getRbPropValue("tblRecordsEmailAcp"));

	@FindBy(css = "#pendingrequests_datatable_info")
    private WebElement lblShowingEntries;
	//By lblShowingEntries = By.cssSelector(Utils.getRbPropValue("lblShowingEntriesAcp"));

	@FindBy(css = ".pagination #pendingrequests_datatable_previous")
    private WebElement lnkPreviousPagination;
	//By lnkPreviousPagination = By.cssSelector(Utils.getRbPropValue("lnkPreviousPaginationAcp"));

	@FindBy(css = ".pagination #pendingrequests_datatable_next")
    private WebElement lnkNextPaginationAcp;
	//By lnkNextPaginationAcp = By.cssSelector(Utils.getRbPropValue("lnkNextPaginationAcp"));

	@FindBy(css = ".pagination .paginate_button.page-item.active")
    private WebElement lnkActivePageNumPaginationAcp;
	//By lnkActivePageNumPaginationAcp = By.cssSelector(Utils.getRbPropValue("lnkActivePageNumPaginationAcp"));

	@FindBy(css = "#PopupReject h4")
    private WebElement lblConfirmPopupHeading;
	//By lblConfirmPopupHeading = By.cssSelector(Utils.getRbPropValue("lblConfirmPopupHeadingAcp"));

	@FindBy(css = "#PopupReject button.close")
    private WebElement btnCloseCrossConfirmPopupHeading;
	//By btnCloseCrossConfirmPopupHeading = By.cssSelector(Utils.getRbPropValue("btnCloseCrossConfirmPopupHeadingAcp"));

	@FindBy(css = "#PopupReject p")
    private WebElement lblDetailMsgConfirmPopupHeading;
	//By lblDetailMsgConfirmPopupHeading = By.cssSelector(Utils.getRbPropValue("lblDetailMsgConfirmPopupHeadingAcp"));

	@FindBy(css = "#PopupReject button.back_btn_crm.chang_pwd_btn.DeleteUser")
    private WebElement btnCloseConfirmPopupHeading;
	//By btnCloseConfirmPopupHeading = By.cssSelector(Utils.getRbPropValue("btnCloseConfirmPopupHeadingAcp"));

	@FindBy(css = "#PopupReject input.submitBtn")
    private WebElement btnYesConfirmPopupHeading;
	//By btnYesConfirmPopupHeading = By.cssSelector(Utils.getRbPropValue("btnYesConfirmPopupHeadingAcp"));

	@FindBy(css = "#cancel-deletionRequest-comments .modal-dialog")
    private WebElement cancellationPopup;
	//By cancellationPopupAcp = By.cssSelector(Utils.getRbPropValue("cancellationPopupAcp"));

	@FindBy(css = "#cancel-deletionRequest-comments .modal-dialog .close")
    private WebElement btnCancellationPopupClose;
	//By btnCancellationPopupCloseAcp = By.cssSelector(Utils.getRbPropValue("btnCancellationPopupCloseAcp"));

	@FindBy(css = "#cancel-deletionRequest-comments .modal-dialog #myModalLabel")
    private WebElement lblHeadingCancellationPopup;
	//By lblHeadingCancellationPopupAcp = By.cssSelector(Utils.getRbPropValue("lblHeadingCancellationPopupAcp"));

	@FindBy(css = "#cancel-deletionRequest-comments .modal-dialog .modal-body .cmts_txt")
    private WebElement lblCommentsCancellationPopup;
	//By lblCommentsCancellationPopupAcp = By.cssSelector(Utils.getRbPropValue("lblCommentsCancellationPopupAcp"));

	@FindBy(css = "#txtMessageText")
    private WebElement inputCommentBoxCancellationPopup;
	//By inputCommentBoxCancellationPopupAcp = By.cssSelector(Utils.getRbPropValue("inputCommentBoxCancellationPopupAcp"));

	@FindBy(css = "#cancel-deletionRequest-comments .modal-dialog #btnCommentsSubmit")
    private WebElement inputSubmitButtonCancellationPopup;
	//By inputSubmitButtonCancellationPopupAcp = By.cssSelector(Utils.getRbPropValue("inputSubmitButtonCancellationPopupAcp"));

	@FindBy(css = ".dataTables_empty")
    private WebElement tblBodyEmpty;
	//By tblBodyEmptyAcp = By.cssSelector(Utils.getRbPropValue("tblBodyEmptyAcp"));

	@FindBy(css = "#jqxgridbill_Grid_next > a")
    private WebElement btnNextBilling;
	//By btnNextBillingAcp = By.cssSelector(Utils.getRbPropValue("btnNextBillingAcp"));
	public boolean isNextButtonEnabled() {
		WebElement element = btnNextBilling;
		if (element.isEnabled()) {
			log.info(btnNextBilling.getText() + " is enabled on the Page");
			return true;
		} else {
			log.error(btnNextBilling + " is not enabled on the Page");
			return false;
		}
	}

	@FindBy(xpath = "(//li[contains(@class,'paginate_button')]//a[text()='1'])[2]")
    private WebElement btnPg1Billing;
	//By btnPg1BillingAcp = By.xpath(Utils.getRbPropValue("btnPg1BillingAcp"));
	public void clickBtnPg1Billing() {
        click(btnPg1Billing);
        log.info("Forgot Password Link clicked successfully.");
    }

	@FindBys(@FindBy(xpath = "//table[@id = 'jqxgridbill_Grid']/tbody/tr/td[4]"))
    private List<WebElement> arrowExpandBilling;
	//By arrowExpandBillingAcp = By.xpath(Utils.getRbPropValue("arrowExpandBillingAcp"));
	public List<WebElement> getArrowExpandBilling() {
        return arrowExpandBilling;
    }

	@FindBys(@FindBy(xpath = "//div[@class='nestedcontainer']//td[contains(text(),'Previous Balance')]/following-sibling::td"))
    private List<WebElement> lblPreviousBalanceBilling;
	//By lblPreviousBalanceBillingAcp = By.xpath(Utils.getRbPropValue("lblPreviousBalanceBillingAcp"));
	public List<WebElement> getLblPreviousBalanceBilling() {
        return lblPreviousBalanceBilling;
    }
	
	@FindBys(@FindBy(xpath = "//div[@class='nestedcontainer']//td[contains(text(),'Payments Received')]/following-sibling::td"))
    private List<WebElement> lblPaymentReceivedBilling;
	//By lblPaymentReceivedBillingAcp = By.xpath(Utils.getRbPropValue("lblPaymentReceivedBillingAcp"));
	public List<WebElement> getLblPaymentReceivedBilling() {
        return lblPaymentReceivedBilling;
    }
	
	@FindBys(@FindBy(xpath = "//div[@class='nestedcontainer']//td[contains(text(),'Current Charges')]/following-sibling::td"))
    private List<WebElement> lblCurrentChargesBilling;
	//By lblCurrentChargesBillingAcp = By.xpath(Utils.getRbPropValue("lblCurrentChargesBillingAcp"));
	public List<WebElement> getLblCurrentChargesBilling() {
        return lblCurrentChargesBilling;
    }
	
	@FindBys(@FindBy(css = "table tbody:nth-child(1) tr:nth-child(4) > td:nth-child(2)"))
    private List<WebElement> lbltotalAmountDueBilling;
	//By lbltotalAmountDueBillingAcp = By.cssSelector(Utils.getRbPropValue("lbltotalAmountDueBillingAcp"));
	public List<WebElement> getLbltotalAmountDueBilling() {
        return lbltotalAmountDueBilling;
    }

	@FindBys(@FindBy(xpath = "//table[@id = 'jqxgridpayment_Grid']/tbody/tr/td[1]"))
    private List<WebElement> lblDatePayment;
	//By lblDatePaymentAcp = By.xpath(Utils.getRbPropValue("lblDatePaymentAcp"));
	public List<WebElement> getLblDatePayment() {
        return lblDatePayment;
    }
	
	@FindBys(@FindBy(xpath = "//table[@id = 'jqxgridpayment_Grid']/tbody/tr/td[2]"))
    private List<WebElement> lblPaymentTypePayment;
	//By lblPaymentTypePaymentAcp = By.xpath(Utils.getRbPropValue("lblPaymentTypePaymentAcp"));
	public List<WebElement> getLblPaymentTypePayment() {
        return lblPaymentTypePayment;
    }
	
	@FindBys(@FindBy(xpath = "//table[@id = 'jqxgridpayment_Grid']/tbody/tr/td[3]"))
    private List<WebElement> lblChannelPayment;
	//By lblChannelPaymentAcp = By.xpath(Utils.getRbPropValue("lblChannelPaymentAcp"));
	public List<WebElement> getLblChannelPayment() {
        return lblChannelPayment;
    }
	
	@FindBys(@FindBy(xpath = "//table[@id = 'jqxgridpayment_Grid']/tbody/tr/td[4]"))
    private List<WebElement> lblStatusPayment;
	//By lblStatusPaymentAcp = By.xpath(Utils.getRbPropValue("lblStatusPaymentAcp"));
	public List<WebElement> getLblStatusPayment() {
        return lblStatusPayment;
    }
	
	@FindBys(@FindBy(xpath = "//table[@id = 'jqxgridpayment_Grid']/tbody/tr/td[5]"))
    private List<WebElement> lblAmountPayment;
	//By lblAmountPaymentAcp = By.xpath(Utils.getRbPropValue("lblAmountPaymentAcp"));
	public List<WebElement> getLblAmountPayment() {
        return lblAmountPayment;
    }

	@FindBy(css = "#jqxgridpayment_Grid_next")
    private WebElement btnNextPayment;
	//By btnNextPaymentAcp = By.cssSelector(Utils.getRbPropValue("btnNextPaymentAcp"));
	public boolean isBtnNextPaymentEnabled() {
		WebElement element = btnNextPayment;
		if (element.isEnabled()) {
			log.info(element.getText() + " is enabled on the Page");
			return true;
		} else {
			log.error(element + " is not enabled on the Page");
			return false;
		}
	}
	public String getBtnNextPaymentAttributeValue(String attributeName) {
		String attributeValue = null;
		try {
			WebElement element = btnNextPayment;
			if (element.isDisplayed()) {
				attributeValue = element.getAttribute(attributeName);
				log.info("User gets the value as  " + attributeValue + " for the webelement");
				//Runner.test.log(Status.PASS, "User gets the value as  " + attributeValue + "for the webelement");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.err.format("No Element Found to perform selection" + e);
			//Runner.test.log(Status.FAIL, "No Element Found to perform selection in the dropdown" + e);
		}
		return attributeValue;

	}

	@FindBy(xpath = "(//li[contains(@class,'paginate_button')]//a[text()='1'])[3]")
    private WebElement btnPg1Payment;
	//By btnPg1PaymentAcp = By.xpath(Utils.getRbPropValue("btnPg1PaymentAcp"));
	public void clickBtnPg1Payment() {
        click(btnPg1Payment);
        log.info("Forgot Password Link clicked successfully.");
    }

	@FindBy(css = "#jqxgridpayment_Grid_previous")
    private WebElement btnPreviousPayment;
	//By btnPreviousPaymentAcp = By.cssSelector(Utils.getRbPropValue("btnPreviousPaymentAcp"));
	public String getBtnPreviousPaymentAttributeValue(String attributeName) {
		String attributeValue = null;
		try {
			WebElement element = btnPreviousPayment;
			if (element.isDisplayed()) {
				attributeValue = element.getAttribute(attributeName);
				log.info("User gets the value as  " + attributeValue + " for the webelement");
				//Runner.test.log(Status.PASS, "User gets the value as  " + attributeValue + "for the webelement");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.err.format("No Element Found to perform selection" + e);
			//Runner.test.log(Status.FAIL, "No Element Found to perform selection in the dropdown" + e);
		}
		return attributeValue;

	}

	@FindBy(css = "#jqxgridbill_Grid > thead > tr > th:nth-child(2)")
    private WebElement lblAmountDueHeaderBilling;
	//By lblAmountDueHeaderBillingAcp = By.cssSelector(Utils.getRbPropValue("lblAmountDueHeaderBillingAcp"));
	public void clickLblAmountDueHeaderBilling() {
        click(lblAmountDueHeaderBilling);
        log.info("Forgot Password Link clicked successfully.");
    }

	@FindBy(css = "#home > div.profileHead > div.profileRightHead > div:nth-child(3) > div:nth-child(2)")
    private WebElement lblChkBoxNewsReleases;
	//By lblChkBoxNewsReleases = By.cssSelector(Utils.getRbPage().getString("lblChkBoxNewsReleasesAcp"));c
	public boolean verifyLblChkBoxNewsReleasesVisible() {

		try {
			lblChkBoxNewsReleases.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			e.getStackTrace();
			return false;
		}
	}
	public String getLblChkBoxNewsReleasesLabel() {
		String sTitle = null;
		try {
			lblChkBoxNewsReleases.isDisplayed();
			sTitle = lblChkBoxNewsReleases.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}
	
	
	@FindBy(css = "#home > div.profileHead > div.profileRightHead > div:nth-child(3) > div:nth-child(5)")
    private WebElement lblChkBoxServiceOfferings;
	//By lblChkBoxServiceOfferings = By.cssSelector(Utils.getRbPage().getString("lblChkBoxServiceOfferingsAcp"));
	public boolean verifyLblChkBoxServiceOfferingsVisible() {

		try {
			lblChkBoxServiceOfferings.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			e.getStackTrace();
			return false;
		}
	}
	public String getLblChkBoxServiceOfferingsLabel() {
		String sTitle = null;
		try {
			lblChkBoxServiceOfferings.isDisplayed();
			sTitle = lblChkBoxServiceOfferings.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}
	
	@FindBy(css = "#home > div.profileHead > div.profileRightHead > div:nth-child(3) > div:nth-child(8)")
    private WebElement lblChkBoxNewsletters;
	//By lblChkBoxNewsletters = By.cssSelector(Utils.getRbPage().getString("lblChkBoxNewslettersAcp"));
	public boolean verifyLblChkBoxNewslettersVisible() {

		try {
			lblChkBoxNewsletters.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			e.getStackTrace();
			return false;
		}
	}
	public String getLblChkBoxNewslettersLabel() {
		String sTitle = null;
		try {
			lblChkBoxNewsletters.isDisplayed();
			sTitle = lblChkBoxNewsletters.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}
	
	@FindBy(css = "#home > div.profileHead > div.profileRightHead > div:nth-child(3) > div:nth-child(11)")
    private WebElement lblChkBoxEnergySavingsToolkits;
	//By lblChkBoxEnergySavingsToolkits = By.cssSelector(Utils.getRbPage().getString("lblChkBoxEnergySavingsToolkitsAcp"));
	public boolean verifyLblChkBoxEnergySavingsToolkitsVisible() {

		try {
			lblChkBoxEnergySavingsToolkits.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			e.getStackTrace();
			return false;
		}
	}
	public String getLblChkBoxEnergySavingsToolkitsLabel() {
		String sTitle = null;
		try {
			lblChkBoxEnergySavingsToolkits.isDisplayed();
			sTitle = lblChkBoxEnergySavingsToolkits.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}
	
	@FindBy(css = "#home > div.profileHead > div.profileRightHead > div:nth-child(3) > div:nth-child(14)")
    private WebElement lblChkBoxCoolTipsBrochures;
	//By lblChkBoxCoolTipsBrochures = By.cssSelector(Utils.getRbPage().getString("lblChkBoxCoolTipsBrochuresAcp"));
	public boolean verifyLblChkBoxCoolTipsBrochuresVisible() {

		try {
			lblChkBoxCoolTipsBrochures.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			e.getStackTrace();
			return false;
		}
	}
	public String getLblChkBoxCoolTipsBrochuresLabel() {
		String sTitle = null;
		try {
			lblChkBoxCoolTipsBrochures.isDisplayed();
			sTitle = lblChkBoxCoolTipsBrochures.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}
	
	@FindBy(css = "#home > div.profileHead > div.profileRightHead > div:nth-child(3) > div:nth-child(17)")
    private WebElement lblChkBoxCommunityBenefitPrograms;
	//By lblChkBoxCommunityBenefitPrograms = By.cssSelector(Utils.getRbPage().getString("lblChkBoxCommunityBenefitProgramsAcp"));
	public boolean verifyLblChkBoxCommunityBenefitProgramsVisible() {

		try {
			lblChkBoxCommunityBenefitPrograms.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			e.getStackTrace();
			return false;
		}
	}
	public String getLblChkBoxCommunityBenefitProgramsLabel() {
		String sTitle = null;
		try {
			lblChkBoxCommunityBenefitPrograms.isDisplayed();
			sTitle = lblChkBoxCommunityBenefitPrograms.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}

	@FindBy(css = "#outageDiv > div.panel-heading > h4 > a")
    private WebElement lblOutageText;
	//By lblOutageText = By.cssSelector(Utils.getRbPage().getString("lblOutageTextAcp"));
	
	@FindBy(css = "#OutageChips")
    private WebElement IconOutageStatus;
	//By IconOutageStatus = By.cssSelector(Utils.getRbPage().getString("IconOutageStatusAcp"));
	
	@FindBy(css = "#outageDiv > div.panel-heading > h4 > a")
    private WebElement lnkOutageExpand;
	//By lnkOutageExpand = By.cssSelector(Utils.getRbPage().getString("lnkOutageExpandAcp"));
	
	@FindBy(css = "#outageDivChkBox1")
    private WebElement chkBoxOutage;
	//By chkBoxOutage = By.cssSelector(Utils.getRbPage().getString("chkBoxOutageAcp"));
	
	@FindBy(css = "#outageDivSelect_1")
    private WebElement lstOutageChannel;
	//By lstOutageChannel = By.cssSelector(Utils.getRbPage().getString("lstOutageChannelAcp"));
	
	@FindBy(css = "#outageDivInput_1")
    private WebElement lstOutageOptionsTxt;
	//By lstOutageOptionsTxt = By.cssSelector(Utils.getRbPage().getString("lstOutageOptionsTxtAcp"));
	
	@FindBy(css = "#addoutageDiv > i")
    private WebElement iconOutageAdd;
	//By iconOutageAdd = By.cssSelector(Utils.getRbPage().getString("iconOutageAddAcp"));
	
	@FindBy(css = "#collapse1 > div > div > div.btnSection > button.btnCancel")
    private WebElement btnOutageCancel;
	//By btnOutageCancel = By.cssSelector(Utils.getRbPage().getString("btnOutageCancelAcp"));
	
	@FindBy(css = "#outageDiv_Outage")
    private WebElement btnOutageSave;
	//By btnOutageSave = By.cssSelector(Utils.getRbPage().getString("btnOutageSaveAcp"));
	
	@FindBy(css = "#outageDivSelect_2")
    private WebElement lstOutageOptions2Row;
	//By lstOutageOptions2Row = By.cssSelector(Utils.getRbPage().getString("lstOutageOptions2RowAcp"));
	
	@FindBy(css = "#outageDivInput_2")
    private WebElement lblOutageOptionText2Row;
	//By lblOutageOptionText2Row = By.cssSelector(Utils.getRbPage().getString("lblOutageOptionText2RowAcp"));
	
	@FindBy(css = "#outageDivChkBox2")
    private WebElement chkBoxOutage2Row;
	//By chkBoxOutage2Row = By.cssSelector(Utils.getRbPage().getString("chkBoxOutage2RowAcp"));
	
	@FindBy(css = "#removeoutageDiv_2 > i")
    private WebElement iconRemoveCircle1;
	//By iconRemoveCircle1 = By.cssSelector(Utils.getRbPage().getString("iconRemoveCircle1Acp"));
	
	@FindBy(xpath = "//*[contains(@id,\"removeoutageDiv\")]")
    private WebElement lstOutageRemoveIcons;
	//By lstOutageRemoveIcons = By.xpath(Utils.getRbPage().getString("lstOutageRemoveIconsAcp"));
	
	@FindBy(css = "#billingDiv > div.panel-heading > h4 > a")
    private WebElement lblBillingText;
	//By lblBillingText = By.cssSelector(Utils.getRbPage().getString("lblBillingTextAcp"));
	
	@FindBy(css = "#BillingChips")
    private WebElement IconBillingStatus;
	//By IconBillingStatus = By.cssSelector(Utils.getRbPage().getString("IconBillingStatusAcp"));
	
	@FindBy(css = "#billingDivChkBox1")
    private WebElement chkBoxBilling;
	//By chkBoxBilling = By.cssSelector(Utils.getRbPage().getString("chkBoxBillingAcp"));
	
	@FindBy(css = "#billingDivSelect_1")
    private WebElement lstBillingChannel;
	//By lstBillingChannel = By.cssSelector(Utils.getRbPage().getString("lstBillingChannelAcp"));
	
	@FindBy(css = "#billingDivInput_1")
    private WebElement lstBillingOptionsTxt;
	//By lstBillingOptionsTxt = By.cssSelector(Utils.getRbPage().getString("lstBillingOptionsTxtAcp"));
	
	@FindBy(css = "#billingDiv > div.panel-heading > h4 > a")
    private WebElement lnkBillingExpand;
	//By lnkBillingExpand = By.cssSelector(Utils.getRbPage().getString("lnkBillingExpandAcp"));
	
	@FindBy(css = "#addbillingDiv > i")
    private WebElement iconBillingAdd;
	//By iconBillingAdd = By.cssSelector(Utils.getRbPage().getString("iconBillingAddAcp"));
	
	@FindBy(css = "#collapse2 > div > div > div.btnSection > button.btnCancel")
    private WebElement btnBillingCancel;
	//By btnBillingCancel = By.cssSelector(Utils.getRbPage().getString("btnBillingCancelAcp"));
	
	@FindBy(css = "#billingDiv_Billing")
    private WebElement btnBillingSave;
	//By btnBillingSave = By.cssSelector(Utils.getRbPage().getString("btnBillingSaveAcp"));
	
	@FindBy(css = "#removebillingDiv_2 > i")
    private WebElement btnBillingCloseIcon;
	//By btnBillingCloseIcon = By.cssSelector(Utils.getRbPage().getString("btnBillingCloseIconAcp"));
	
	@FindBy(xpath = "//*[contains(@id,\"removebillingDiv\")]")
    private WebElement lstBillingClose;
	//By lstBillingClose = By.xpath(Utils.getRbPage().getString("lstBillingCloseAcp"));
	
	@FindBy(css = "#budgetDiv > div.panel-heading > h4 > a")
    private WebElement lblBudgetText;
	//By lblBudgetText = By.cssSelector(Utils.getRbPage().getString("lblBudgetTextAcp"));
	
	@FindBy(css = "#BudgetChips")
    private WebElement IconBudgetStatus;
	//By IconBudgetStatus = By.cssSelector(Utils.getRbPage().getString("IconBudgetStatusAcp"));
	
	@FindBy(css = "#budgetDivChkBox1")
    private WebElement chkBoxBudget;
	//By chkBoxBudget = By.cssSelector(Utils.getRbPage().getString("chkBoxBudgetAcp"));
	
	@FindBy(css = "#budgetDivSelect_1")
    private WebElement lstBudgetOptions;
	//By lstBudgetOptions = By.cssSelector(Utils.getRbPage().getString("lstBudgetOptionsAcp"));
	
	@FindBy(css = "#budgetDivInput_1")
    private WebElement lstBudgetOptionsTxt;
	//By lstBudgetOptionsTxt = By.cssSelector(Utils.getRbPage().getString("lstBudgetOptionsTxtAcp"));
	
	@FindBy(css = "#addbudgetDiv > i")
    private WebElement iconBudgetAdd;
	//By iconBudgetAdd = By.cssSelector(Utils.getRbPage().getString("iconBudgetAddAcp"));
	
	@FindBy(css = "#collapse3 > div > div > div.btnSection > button.btnCancel")
    private WebElement btnBudgetCancel;
	//By btnBudgetCancel = By.cssSelector(Utils.getRbPage().getString("btnBudgetCancelAcp"));
	
	@FindBy(css = "#budgetDiv_Budget")
    private WebElement btnBudgetSave;
	//By btnBudgetSave = By.cssSelector(Utils.getRbPage().getString("btnBudgetSaveAcp"));
	
	@FindBy(css = "#budgetDivSelect_2")
    private WebElement lstBudgetOption1;
	//By lstBudgetOption1 = By.cssSelector(Utils.getRbPage().getString("lstBudgetOption1Acp"));
	
	@FindBy(css = "#budgetDivSelect_3")
    private WebElement lstBudgetOption2;
	//By lstBudgetOption2 = By.cssSelector(Utils.getRbPage().getString("lstBudgetOption2Acp"));
	
	@FindBy(css = "#budgetDivSelect_4")
    private WebElement lstBudgetOption3;
	//By lstBudgetOption3 = By.cssSelector(Utils.getRbPage().getString("lstBudgetOption3Acp"));
	
	@FindBy(css = "#budgetDivInput_2")
    private WebElement lstBudgetOption1Txt;
	//By lstBudgetOption1Txt = By.cssSelector(Utils.getRbPage().getString("lstBudgetOption1TxtAcp"));
	
	@FindBy(css = "#budgetDivInput_3")
    private WebElement lstBudgetOption2Txt;
	//By lstBudgetOption2Txt = By.cssSelector(Utils.getRbPage().getString("lstBudgetOption2TxtAcp"));
	
	@FindBy(css = "#budgetDivInput_4")
    private WebElement lstBudgetOption3Txt;
	//By lstBudgetOption3Txt = By.cssSelector(Utils.getRbPage().getString("lstBudgetOption3TxtAcp"));
	
	@FindBy(css = "#removebudgetDiv_2 > i")
    private WebElement iconBudgetClose1;
	//By iconBudgetClose1 = By.cssSelector(Utils.getRbPage().getString("iconBudgetClose1Acp"));
	
	@FindBy(css = "#removebudgetDiv_3 > i")
    private WebElement iconBudgetClose2;
	//By iconBudgetClose2 = By.cssSelector(Utils.getRbPage().getString("iconBudgetClose2Acp"));
	
	@FindBy(css = "#removebudgetDiv_4 > i")
    private WebElement iconBudgetClose3;
	//By iconBudgetClose3 = By.cssSelector(Utils.getRbPage().getString("iconBudgetClose3Acp"));
	
	@FindBy(css = "#add_Budget")
    private WebElement iconBudgetDisabled;
	//By iconBudgetDisabled = By.cssSelector(Utils.getRbPage().getString("iconBudgetDisabledAcp"));
	
	@FindBy(css = "#budgetDivChkBox2")
    private WebElement chkbox2Budget;
	//By chkbox2Budget = By.cssSelector(Utils.getRbPage().getString("chkbox2BudgetAcp"));
	
	@FindBy(css = "#budgetDivChkBox3")
    private WebElement chkbox3Budget;
	//By chkbox3Budget = By.cssSelector(Utils.getRbPage().getString("chkbox3BudgetAcp"));
	
	@FindBy(css = "#budgetDivChkBox4")
    private WebElement chkbox4Budget;
	//By chkbox4Budget = By.cssSelector(Utils.getRbPage().getString("chkbox4BudgetAcp"));
	
	@FindBy(xpath = "//*[contains(@id,\"removebudgetDiv\")]")
    private WebElement lstBudgetCloseIcons;
	//By lstBudgetCloseIcons = By.xpath(Utils.getRbPage().getString("lstBudgetCloseIconsAcp"));
	
	@FindBy(css = "#demandResponseDiv > div.panel-heading > h4 > a")
    private WebElement lblDR;
	//By lblDR = By.cssSelector(Utils.getRbPage().getString("lblDRAcp"));
	
	@FindBy(css = "#demandResponseDiv > div.panel-heading > h4 > a")
    private WebElement lblDRText;
	//By lblDRText = By.cssSelector(Utils.getRbPage().getString("lblDRTextAcp"));
	
	@FindBy(css = "#DRChips")
    private WebElement IconDRStatus;
	//By IconDRStatus = By.cssSelector(Utils.getRbPage().getString("IconDRStatusAcp"));
	
	@FindBy(css = "#demandResponseDivChkBox1")
    private WebElement chkBoxDR;
	//By chkBoxDR = By.cssSelector(Utils.getRbPage().getString("chkBoxDRAcp"));
	
	@FindBy(css = "#demandResponseDivSelect_1")
    private WebElement lstDROptions;
	//By lstDROptions = By.cssSelector(Utils.getRbPage().getString("lstDROptionsAcp"));
	
	@FindBy(css = "#demandResponseDivInput_1")
    private WebElement lstDROptionsTxt;
	//By lstDROptionsTxt = By.cssSelector(Utils.getRbPage().getString("lstDROptionsTxtAcp"));
	
	@FindBy(css = "#adddrDiv > i")
    private WebElement iconDRAdd;
	//By iconDRAdd = By.cssSelector(Utils.getRbPage().getString("iconDRAddAcp"));
	
	@FindBy(css = "#collapse4 > div > div > div.btnSection > button.btnCancel")
    private WebElement btnDRCancel;
	//By btnDRCancel = By.cssSelector(Utils.getRbPage().getString("btnDRCancelAcp"));
	
	@FindBy(css = "#demandResponseDiv_DR")
    private WebElement btnDRSave;
	//By btnDRSave = By.cssSelector(Utils.getRbPage().getString("btnDRSaveAcp"));
	
	@FindBy(xpath = "//*[contains(@id,\"removedemandResponseDiv\")]")
    private WebElement lstDRCloseIcons;
	//By lstDRCloseIcons = By.xpath(Utils.getRbPage().getString("lstDRCloseIconsAcp"));
	
	@FindBy(css = "#connectMeDiv > div.panel-heading > h4 > a")
    private WebElement lblConnectMe;
	//By lblConnectMe = By.cssSelector(Utils.getRbPage().getString("lblConnectMeAcp"));
	
	@FindBy(css = "#connectMeDiv > div.panel-heading > h4 > a")
    private WebElement lblConnectMeText;
	//By lblConnectMeText = By.cssSelector(Utils.getRbPage().getString("lblConnectMeTextAcp"));
	
	@FindBy(css = "#ConnectMeChips")
    private WebElement IconConnectMeStatus;
	//By IconConnectMeStatus = By.cssSelector(Utils.getRbPage().getString("IconConnectMeStatusAcp"));
	
	@FindBy(css = "#connectMeDivChkBox1")
    private WebElement chkBoxConnectMe;
	//By chkBoxConnectMe = By.cssSelector(Utils.getRbPage().getString("chkBoxConnectMeAcp"));
	
	@FindBy(css = "#connectMeDivSelect_1")
    private WebElement lstConnectMeOptions;
	//By lstConnectMeOptions = By.cssSelector(Utils.getRbPage().getString("lstConnectMeOptionsAcp"));
	
	@FindBy(css = "#connectMeDivInput_1")
    private WebElement lstConnectMeOptionsTxt;
	//By lstConnectMeOptionsTxt = By.cssSelector(Utils.getRbPage().getString("lstConnectMeOptionsTxtAcp"));
	
	@FindBy(css = "#addconnectmeDiv > i")
    private WebElement iconConnectMeAdd;
	//By iconConnectMeAdd = By.cssSelector(Utils.getRbPage().getString("iconConnectMeAddAcp"));
	
	@FindBy(css = "#collapse5 > div > div > div.btnSection > button.btnCancel")
    private WebElement btnConnectMeCancel;
	//By btnConnectMeCancel = By.cssSelector(Utils.getRbPage().getString("btnConnectMeCancelAcp"));
	
	@FindBy(css = "#connectMeDiv_ConnectMe")
    private WebElement btnConnectMeSave;
	//By btnConnectMeSave = By.cssSelector(Utils.getRbPage().getString("btnConnectMeSaveAcp"));
	
	@FindBy(css = "#removeconnectMeDiv_2 > i")
    private WebElement btnConnectMeClose;
	//By btnConnectMeClose = By.cssSelector(Utils.getRbPage().getString("btnConnectMeCloseAcp"));
	
	@FindBy(xpath = "//*[contains(@id,\"removeconnectMeDiv\")]")
    private WebElement lstConnectMeCloseIcons;
	//By lstConnectMeCloseIcons = By.xpath(Utils.getRbPage().getString("lstConnectMeCloseIconsAcp"));
	
	@FindBy(css = "#servicesDiv > div.panel-heading > h4 > a")
    private WebElement lblServices;
	//By lblServices = By.cssSelector(Utils.getRbPage().getString("lblServicesAcp"));
	
	@FindBy(css = "#servicesDiv > div.panel-heading > h4 > a")
    private WebElement lblServicesText;
	//By lblServicesText = By.cssSelector(Utils.getRbPage().getString("lblServicesTextAcp"));
	
	@FindBy(css = "#ServicesChips")
    private WebElement IconServicesStatus;
	//By IconServicesStatus = By.cssSelector(Utils.getRbPage().getString("IconServicesStatusAcp"));
	
	@FindBy(css = "#servicesDivChkBox1")
    private WebElement chkBoxServices;
	//By chkBoxServices = By.cssSelector(Utils.getRbPage().getString("chkBoxServicesAcp"));
	
	@FindBy(css = "#servicesDivSelect_1")
    private WebElement lstServicesOptions;
	//By lstServicesOptions = By.cssSelector(Utils.getRbPage().getString("lstServicesOptionsAcp"));
	
	@FindBy(css = "#servicesDivInput_1")
    private WebElement lstServicesOptionsTxt;
	//By lstServicesOptionsTxt = By.cssSelector(Utils.getRbPage().getString("lstServicesOptionsTxtAcp"));
	
	@FindBy(css = "#addservicesDiv > i")
    private WebElement iconServicesAdd;
	//By iconServicesAdd = By.cssSelector(Utils.getRbPage().getString("iconServicesAddAcp"));
	
	@FindBy(css = "#collapse6 > div > div > div.btnSection > button.btnCancel")
    private WebElement btnServicesCancel;
	//By btnServicesCancel = By.cssSelector(Utils.getRbPage().getString("btnServicesCancelAcp"));
	
	@FindBy(css = "#servicesDiv_Services")
    private WebElement btnServicesSave;
	//By btnServicesSave = By.cssSelector(Utils.getRbPage().getString("btnServicesSaveAcp"));
	
	@FindBy(css = "#removeservicesDiv_2 > i")
    private WebElement btnServiceCloseIcon;
	//By btnServiceCloseIcon = By.cssSelector(Utils.getRbPage().getString("btnServiceCloseIconAcp"));
	
	@FindBy(xpath = "//*[contains(@id,\"removeservicesDiv\")]")
    private WebElement lstServicesCloseIcons;
	//By lstServicesCloseIcons = By.xpath(Utils.getRbPage().getString("lstServicesCloseIconsAcp"));
	
	@FindBy(css = "#leakAlertDiv > div.panel-heading > h4 > a")
    private WebElement lblLeakalert;
	//By lblLeakalert = By.cssSelector(Utils.getRbPage().getString("lblLeakalertAcp"));
	
	@FindBy(css = "#leakAlertDiv > div.panel-heading > h4 > a")
    private WebElement lblLeakAlertText;
	//By lblLeakAlertText = By.cssSelector(Utils.getRbPage().getString("lblLeakAlertTextAcp"));
	
	@FindBy(css = "#LeakAlertChips")
    private WebElement IconLeakAlertStatus;
	//By IconLeakAlertStatus = By.cssSelector(Utils.getRbPage().getString("IconLeakAlertStatusAcp"));
	
	@FindBy(css = "#leakAlertDivChkBox1")
    private WebElement chkBoxLeakAlert;
	//By chkBoxLeakAlert = By.cssSelector(Utils.getRbPage().getString("chkBoxLeakAlertAcp"));
	
	@FindBy(css = "#leakAlertDivSelect_1")
    private WebElement lstLeakAlertOptions;
	//By lstLeakAlertOptions = By.cssSelector(Utils.getRbPage().getString("lstLeakAlertOptionsAcp"));
	
	@FindBy(css = "#leakAlertDivInput_1")
    private WebElement lstLeakAlertOptionsTxt;
	//By lstLeakAlertOptionsTxt = By.cssSelector(Utils.getRbPage().getString("lstLeakAlertOptionsTxtAcp"));
	
	@FindBy(css = "#addleakalertDiv > i")
    private WebElement iconLeakAlertAdd;
	//By iconLeakAlertAdd = By.cssSelector(Utils.getRbPage().getString("iconLeakAlertAddAcp"));
	
	@FindBy(css = "#collapse7 > div > div > div.btnSection > button.btnCancel")
    private WebElement btnLeakAlertCancel;
	//By btnLeakAlertCancel = By.cssSelector(Utils.getRbPage().getString("btnLeakAlertCancelAcp"));
	
	@FindBy(css = "#leakAlertDiv_LeakAlert")
    private WebElement btnLeakAlertSave;
	//By btnLeakAlertSave = By.cssSelector(Utils.getRbPage().getString("btnLeakAlertSaveAcp"));
	
	@FindBy(css = "#removeleakAlertDiv_2")
    private WebElement btnLeakAlertCloseIcon;
	//By btnLeakAlertCloseIcon = By.cssSelector(Utils.getRbPage().getString("btnLeakAlertCloseIconAcp"));
	
	@FindBy(xpath = "//*[contains(@id,\"removeleakAlertDiv\")]")
    private WebElement lstLeakAlertCloseIcons;
	//By lstLeakAlertCloseIcons = By.xpath(Utils.getRbPage().getString("lstLeakAlertCloseIconsAcp"));
	
	@FindBy(css = "#highUsageDiv > div.panel-heading > h4 > a")
    private WebElement lblHighUsage;
	//By lblHighUsage = By.cssSelector(Utils.getRbPage().getString("lblHighUsageAcp"));
	
	@FindBy(css = "#highUsageDiv > div.panel-heading > h4 > a")
    private WebElement lblHighUsageText;
	//By lblHighUsageText = By.cssSelector(Utils.getRbPage().getString("lblHighUsageTextAcp"));
	
	@FindBy(css = "#HighChips")
    private WebElement IconHighUsageStatus;
	//By IconHighUsageStatus = By.cssSelector(Utils.getRbPage().getString("IconHighUsageStatusAcp"));
	
	@FindBy(css = "#highUsageDivChkBox1")
    private WebElement chkBoxHighUsage;
	//By chkBoxHighUsage = By.cssSelector(Utils.getRbPage().getString("chkBoxHighUsageAcp"));
	
	@FindBy(css = "#highUsageDivSelect_1")
    private WebElement lstHighUsageOptions;
	//By lstHighUsageOptions = By.cssSelector(Utils.getRbPage().getString("lstHighUsageOptionsAcp"));
	
	@FindBy(css = "#highUsageDivInput_1")
    private WebElement lstHighUsageOptionsTxt;
	//By lstHighUsageOptionsTxt = By.cssSelector(Utils.getRbPage().getString("lstHighUsageOptionsTxtAcp"));
	
	@FindBy(css = "#addhighDiv > i")
    private WebElement iconHighUsageAdd;
	//By iconHighUsageAdd = By.cssSelector(Utils.getRbPage().getString("iconHighUsageAddAcp"));
	
	@FindBy(css = "#collapse8 > div > div > div.btnSection > button.btnCancel")
    private WebElement btnHighUsageCancel;
	//By btnHighUsageCancel = By.cssSelector(Utils.getRbPage().getString("btnHighUsageCancelAcp"));
	
	@FindBy(css = "#highUsageDiv_HighUsage")
    private WebElement btnHighUsageSave;
	//By btnHighUsageSave = By.cssSelector(Utils.getRbPage().getString("btnHighUsageSaveAcp"));
	
	@FindBy(css = "#removehighUsageDiv_2 > i")
    private WebElement btnHighUsageCloseIcon;
	//By btnHighUsageCloseIcon = By.cssSelector(Utils.getRbPage().getString("btnHighUsageCloseIconAcp"));
	
	@FindBy(xpath = "//*[contains(@id,\"removehighUsageDiv\")]")
    private WebElement lstHighUsageRemoveIcons;
	//By lstHighUsageRemoveIcons = By.xpath(Utils.getRbPage().getString("lstHighUsageRemoveIconsAcp"));
	
	@FindBy(css = "h4#notificationTcpaTitle")
    private WebElement lblTCPAacceptNotificatinTerms;
	//By lblTCPAacceptNotificatinTerms = By.cssSelector(Utils.getRbPage().getString("lblTCPAacceptNotificatinTermsAcp"));
	
	@FindBy(css = "div#checkedType")
    private WebElement lblTCPANotificationType;
	//By lblTCPANotificationType = By.cssSelector(Utils.getRbPage().getString("lblTCPANotificationTypeAcp"));
	
	@FindBy(css = "button#btnagree")
    private WebElement lnkTCPAagree;
	//By lnkTCPAagree = By.cssSelector(Utils.getRbPage().getString("lnkTCPAagreeAcp"));
	
	@FindBy(css = "button#btndisagree")
    private WebElement lnkTCPADisAgree;
	//By lnkTCPADisAgree = By.cssSelector(Utils.getRbPage().getString("lnkTCPADisAgreeAcp"));
	
	@FindBy(css = "button#dLabel")
    private WebElement lnkAccountDropdown;
	//By lnkAccountDropdown = By.cssSelector(Utils.getRbPage().getString("lnkAccountDropdownAcp"));
	
	@FindBy(css = "#CustdetailsBackBtn")
    private WebElement lnkBackToSearch;
	public void clickLnkBackToSearch() {
        click(lnkBackToSearch);
        log.info("Back to Search clicked successfully.");
    }
	
	public void waitForPageToLoad() {
		pause(1000);
		ExpectedCondition<Boolean> pageLoadCondition = driver -> ((JavascriptExecutor) driver)
				.executeScript("return document.readyState").equals("complete");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		wait.until(pageLoadCondition);
		pause(500);
	}
	
	@FindBy(css = "#UpdateMarketingPrefernce")
    private WebElement btnUpdateMarketingPrefernce;
	public void clickBtnUpdateMarketingPrefernce() {
        click(btnUpdateMarketingPrefernce);
        log.info("UpdateMarketingPrefernce clicked successfully.");
    }
	
	@FindBy(css = "div.plans_boxtop > div:nth-child(1) > ul > li:nth-child(4) > span")
    private WebElement lblAccountStatus;
	public String getLblAccountStatusLabel() {
		String sTitle = null;
		try {
			lblAccountStatus.isDisplayed();
			sTitle = lblAccountStatus.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}
	
	@FindBy(css = "#lblCustAccountStatus")
    private WebElement lblValAccountStatus;
	public String getLblValAccountStatusLabel() {
		String sTitle = null;
		try {
			lblValAccountStatus.isDisplayed();
			sTitle = lblValAccountStatus.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}
	
	@FindBys(@FindBy(css = "#jqxgridInteraction_Grid > thead > tr > th"))
	private List<WebElement> lstInterationTableHeaders;
	public List<WebElement> getLstInterationTableHeaders() {
        return lstInterationTableHeaders;
    }
	
	@FindBys(@FindBy(xpath = "//table[@id='jqxgridInteraction_Grid']/tbody/tr/td[6]"))
	private List<WebElement> lstDateTimeInterationTable;
	public List<WebElement> getLstDateTimeInterationTable() {
        return lstDateTimeInterationTable;
    }
	
	@FindBy(css = "#CSRPaperlessBill > span")
    private WebElement lblStatus;
	public String getLblStatusLabel() {
		String sTitle = null;
		try {
			lblStatus.isDisplayed();
			sTitle = lblStatus.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}
	
	@FindBy(css = "#CSRPaperlessBillDate> span")
    private WebElement lblUpdatedDate;
	public String getLblUpdatedDateLabel() {
		String sTitle = null;
		try {
			lblUpdatedDate.isDisplayed();
			sTitle = lblUpdatedDate.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}
	
	@FindBy(css = "#paperBill")
    private WebElement lblPaperBillStatus;
	public String getLblPaperBillStatusLabel() {
		String sTitle = null;
		try {
			lblPaperBillStatus.isDisplayed();
			sTitle = lblPaperBillStatus.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}
	
	@FindBys(@FindBy(css = "#Guest_Grid > thead > tr > th"))
	private List<WebElement> lstGuestUserTableHeaders;
	public List<WebElement> getLstGuestUserTableHeaders() {
        return lstGuestUserTableHeaders;
    }
	
	@FindBy(css = "#Guest_Grid_info")
    private WebElement lblGuestUserEntries;
	public String getLblGuestUserEntriesLabel() {
		String sTitle = null;
		try {
			lblGuestUserEntries.isDisplayed();
			sTitle = lblGuestUserEntries.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}
	
	@FindBy(css = "#Guest_Grid > tbody > tr > td")
    private WebElement lblGuestUserNoRecord;
	public String getLblGuestUserNoRecordLabel() {
		String sTitle = null;
		try {
			lblGuestUserNoRecord.isDisplayed();
			sTitle = lblGuestUserNoRecord.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}
	
	@FindBys(@FindBy(xpath = "//table[@id='Guest_Grid']/tbody/tr/td[1]"))
	private List<WebElement> lstGuestUserGuestName;
	public List<WebElement> getLstGuestUserGuestName() {
        return lstGuestUserGuestName;
    }
	
	@FindBys(@FindBy(xpath = "//table[@id='Guest_Grid']/tbody/tr/td[2]"))
	private List<WebElement> lstGuestUserEmailAddress;
	public List<WebElement> getLstGuestUserEmailAddress() {
        return lstGuestUserEmailAddress;
    }
	
	@FindBys(@FindBy(xpath = "//table[@id='Guest_Grid']/tbody/tr/td[3]"))
	private List<WebElement> lstGuestUserRole;
	public List<WebElement> getLstGuestUserRole() {
        return lstGuestUserRole;
    }
	
	@FindBys(@FindBy(xpath = "//table[@id='Guest_Grid']/tbody/tr/td[4]"))
	private List<WebElement> lstGuestUserRequestDate;
	public List<WebElement> getLstGuestUserRequestDate() {
        return lstGuestUserRequestDate;
    }
	
	@FindBys(@FindBy(xpath = "//table[@id='Guest_Grid']/tbody/tr/td[5]"))
	private List<WebElement> lstGuestUserExpiration;
	public List<WebElement> getLstGuestUserExpiration() {
        return lstGuestUserExpiration;
    }
	
	@FindBys(@FindBy(xpath = "//table[@id='Guest_Grid']/tbody/tr/td[6]"))
	private List<WebElement> lstGuestUserStatus;
	public List<WebElement> getLstGuestUserStatus() {
        return lstGuestUserStatus;
    }
	
	@FindBy(css = "#crent")
    private WebElement lblOutagesCurrent;
	public Boolean isLblOutagesCurrentVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(lblOutagesCurrent));
        return isElementVisible(lblOutagesCurrent);
    }
	public String getLblOutagesCurrentAttributeValue(String attributeName) {
		String attributeValue = null;
		try {
			if (lblOutagesCurrent.isDisplayed()) {
				attributeValue = lblOutagesCurrent.getAttribute(attributeName);
				// System.out.println(attributeValue);
				log.info("User gets the value as  " + attributeValue + "for the webelement");
				
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.err.format("No Element Found to perform selection" + e);
			
		}
		return attributeValue;
	}
	
	
	@FindBy(css = "#plnd")
    private WebElement lblOutagesPlanned;
	public Boolean isLblOutagesPlannedVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(lblOutagesPlanned));
        return isElementVisible(lblOutagesPlanned);
    }
	
	@FindBy(xpath = "//button[contains(text(),'Map')]")
    private WebElement lblOutagesMap;
	public Boolean isLblOutagesMapVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(lblOutagesMap));
        return isElementVisible(lblOutagesMap);
    }
	public String getLblOutagesMapAttributeValue(String attributeName) {
		String attributeValue = null;
		try {
			if (lblOutagesMap.isDisplayed()) {
				attributeValue = lblOutagesMap.getAttribute(attributeName);
				// System.out.println(attributeValue);
				log.info("User gets the value as  " + attributeValue + "for the webelement");
				
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.err.format("No Element Found to perform selection" + e);
			
		}
		return attributeValue;
	}
	
	@FindBy(xpath = "//button[contains(text(),'Satellite')]")
    private WebElement lblOutagesSatellite;
	public Boolean isLblOutagesSatelliteVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(lblOutagesSatellite));
        return isElementVisible(lblOutagesSatellite);
    }
	public String getLblOutagesSatelliteAttributeValue(String attributeName) {
		String attributeValue = null;
		try {
			if (lblOutagesSatellite.isDisplayed()) {
				attributeValue = lblOutagesSatellite.getAttribute(attributeName);
				// System.out.println(attributeValue);
				log.info("User gets the value as  " + attributeValue + "for the webelement");
				
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.err.format("No Element Found to perform selection" + e);
			
		}
		return attributeValue;
	}
	
	@FindBys(@FindBy(xpath = "//div[@class='MessageContainer']//td[@class='blue']"))
	private List<WebElement> lstOutagesTiles;
	public List<WebElement> getLstOutagesTiles() {
        return lstOutagesTiles;
    }
	
	@FindBys(@FindBy(xpath = "//div[@class='MessageContainer']//*[text()='Date & Time Of Outage:']/parent::td"))
	private List<WebElement> lstOutagesDateTime;
	public List<WebElement> getLstOutagesDateTime() {
        return lstOutagesDateTime;
    }
	
	@FindBys(@FindBy(xpath = "//div[@class='MessageContainer']//*[text()='Estimated Restoration:']/parent::td"))
	private List<WebElement> lstOutagesEstimatedRestoration;
	public List<WebElement> getLstOutagesEstimatedRestoration() {
        return lstOutagesEstimatedRestoration;
    }
	
	@FindBys(@FindBy(xpath = "//div[@class='MessageContainer']//*[text()='Customers Affected:']/parent::td"))
	private List<WebElement> lstOutagesCustomerAffected;
	public List<WebElement> getLstOutagesCustomerAffected() {
        return lstOutagesCustomerAffected;
    }
	
	@FindBys(@FindBy(xpath = "//div[@class='MessageContainer']//*[text()='Community Affected:']/parent::td"))
	private List<WebElement> lstOutagesCommunityAffected;
	public List<WebElement> getLstOutagesCommunityAffected() {
        return lstOutagesCommunityAffected;
    }
	
	@FindBys(@FindBy(xpath = "//div[@class='MessageContainer']//*[text()='Report Info: ']/parent::td"))
	private List<WebElement> lstOutagesReportInfo;
	public List<WebElement> getLstOutagesReportInfo() {
        return lstOutagesReportInfo;
    }
	
	@FindBys(@FindBy(xpath = "//div[@class='MessageContainer']//*[text()='Status']/parent::td"))
	private List<WebElement> lstOutagesStatus;
	public List<WebElement> getLstOutagesStatus() {
        return lstOutagesStatus;
    }
	
	@FindBys(@FindBy(css = ".MessageContainer"))
	private List<WebElement> lstOutagesItems;
	public List<WebElement> getLstOutagesItems() {
        return lstOutagesItems;
    }
	
	public void selectByVisibleTxt(WebElement locator, String visibleText) {
		try {
			WebElement element = locator;
			if (element.isDisplayed()) {
				Select selObj = new Select(element);
				if (visibleText.equals("")) {
					selObj.selectByVisibleText("---Select---");
				} else {
					selObj.selectByVisibleText(visibleText);
				}
				log.info("User selects the visible text as  " + visibleText + "from Dropdown");
				
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.err.format("No Element Found to perform selection in the dropdown" + e);
			
		}
	}
	
	public void enterTextValue(WebElement locator, String expectedData) {
		try {
			WebElement element = locator;
			if (locator.isDisplayed())
			// element.isDisplayed())
			{
				element.clear();
				element.sendKeys(expectedData);
				log.info("User enters " + expectedData + " in the " + element.getText() + " text box");
				
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.err.format("No Element Found to enter text" + e);
			
		}
	}
	WebElement element;
	public void enterData(WebElement locator, String sEnterData) {
        try {
            WebElement element = locator;
            Actions actions = new Actions(driver);
            actions.moveToElement(element);
            actions.click();
            actions.sendKeys(sEnterData);
            actions.build().perform();
            log.info("The test data entered is: " + sEnterData);
        } catch (StaleElementReferenceException e) {
            /*
             * System.out.
             * println("Element is not attached to the page document " +
             * e.getStackTrace());
             */
            log.info("Element is not attached to the page document " + e.getStackTrace());
        } catch (NoSuchElementException e) {
            // System.out.println("Element " + element + " was not found in DOM
            // "
            // + e.getStackTrace());
            log.info("Element " + element + " was not found in DOM " + e.getStackTrace());
        } catch (Exception e) {
            log.info("The textbox  " + element + " is not interactable" + e.getStackTrace());
        }
    }
	
	@FindBy(css = "label#lblSCMUserName")
	private WebElement txtNameCustomerDetailsPt;
	public String getTxtNameCustomerDetailsPt() {
		String label = getText(txtNameCustomerDetailsPt);
		log.info("Customer Name text in Customer Details 360 Profile Tab is " + label);
		return label;
	}
	
	@FindBy(css = "label#lblCustAccountNumber")
	private WebElement txtCustomerAcctNoCustomerDetailsPt;
	public String getTxtCustomerAcctNoCustomerDetailsPt() {
		String label = getText(txtCustomerAcctNoCustomerDetailsPt);
		log.info("Customer Account Number text in Customer Details 360 Profile Tab is " + label);
		return label;
	}
	
	@FindBy(css = "label#lblCustEmailPhone")
	private WebElement txtCustomerEmailPhoneCustomerDetailsPt;
	public String getTxtCustomerEmailPhoneCustomerDetailsPt() {
		String label = getText(txtCustomerEmailPhoneCustomerDetailsPt);
		log.info("Customer Email and Phone Number text in Customer Details 360 Profile Tab is " + label);
		return label;
	}
	
	@FindBy(css = "#CustdetailsBackBtn")
	private WebElement lnkBackToCSRSearch;
	public void clickLnkBackToCSRSearch() {
		click(lnkBackToCSRSearch);
		log.info("Back To CSR Search Link clicked {}.");
	}
	
	public String getAttributeValue(WebElement element, String attributeName) {
		String attributeValue = null;
		try {
			if (element.isDisplayed()) {
				attributeValue = element.getAttribute(attributeName);
				// System.out.println(attributeValue);
				log.info("User gets the value as  " + attributeValue + "for the webelement");
				
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.err.format("No Element Found to perform selection" + e);
			
		}
		return attributeValue;
	}
	
	public List<String> getAllOptionsInListBox(WebElement element) {
		List<String> options = new ArrayList<String>();
		for (WebElement option : new Select(element).getOptions()) {
			String sTxt = option.getText();
			if (option.getAttribute("value") != "")
				options.add(option.getText());
		}
		log.info("The list box contains the following values : " + options);
		return options;
	}
	
	@FindBy(css = "img[src=\"../images/locked.png\"]")
	private WebElement imgUnlockUserIp;
	//By imgUnlockUserIp = By.cssSelector(Utils.getRbPage().getString("imgUnlockUserIpAbip"));
	
	@FindBy(css = "#contenttablejqxgrid span")
	private WebElement txtLblIpAbip;
	//By txtLblIpAbip = By.cssSelector(Utils.getRbPage().getString("txtLblIpAbip"));
	//By lblBlockedIpValAbip = By.xpath(Utils.getRbPage().getString("lblBlockedIpValXpathBeforeAbip")
		//	+ sSystemIp  + Utils.getRbPage().getString("lblBlockedIpValXpathAfterAbip"));
	
	public By getLblBlockedIpValAbip() throws UnknownHostException, SocketException {
		String systemIp = "40.83.171.213";     
		By lblBlockedIpValAbip = By.xpath("//span[text()='"
				+ systemIp  + "']");
		return lblBlockedIpValAbip;
	}
	
	@FindBy(css = ".toast.toast-success")
	private WebElement lblSuccessfulHeader;
	//By lblSuccessfulHeader = By.cssSelector(Utils.getRbPage().getString("lblSuccessfulHeaderAbip"));
	
	@FindBy(css = "#BlockedIP [data-toggle='dropdown']")
	private WebElement threeDotsToggleDropdown;
	//By threeDotsToggleDropdown = By.cssSelector(Utils.getRbPage().getString("threeDotsToggleDropdownAbip"));
	public void clickThreeDotsToggleDropdown() {
        click(threeDotsToggleDropdown);
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(css = ".status")
	private WebElement lblUnlockOption;
	//By lblUnlockOption = By.cssSelector(Utils.getRbPage().getString("lblUnlockOptionAbip"));
	public void explicitWaitForLblUnlockOption() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.visibilityOf(lblUnlockOption));
		} catch (NoSuchElementException e) {
			log.error("No Element Found after wait" + e);
		}
	}
	public void clickLblUnlockOption() {
        click(lblUnlockOption);
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(css = "#btnPopupUnlockIp")
	private WebElement btnYesUnlockConfirmationPopup;
	//By btnYesUnlockConfirmationPopup = By.cssSelector(Utils.getRbPage().getString("btnYesUnlockConfirmationPopupAbip"));	
	public boolean verifyElementVisible(By locator) {
		try {
			if (driver.findElement(locator).isDisplayed()) {
				log.info(locator + " : element is visible on the page");
				return true;
			} else {

				log.info(locator + " : element is not visible on the page");
				return false;
			}

		} catch (NoSuchElementException e) {
			log.info(locator + " : element is not visible on the page");
			// e.getStackTrace();
			return false;
		}
	}
	public void explicitWaitForBtnYesUnlockConfirmationPopup() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.visibilityOf(btnYesUnlockConfirmationPopup));
		} catch (NoSuchElementException e) {
			log.error("No Element Found after wait" + e);
		}
	}
	public void clickBtnYesUnlockConfirmationPopup() {
        click(btnYesUnlockConfirmationPopup);
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(css = "div.toast-message")
    private WebElement lblToastMsg;
    public String getLblToastMsgLabel() {
        String toast_msg = getText(lblToastMsg);
        log.info("Label on toast message {}: " + toast_msg);
        return toast_msg;
    }
    public WebElement getLblToastMsg() {
    	return lblToastMsg;
    }
    ///////////////////////////////////////
    @FindBy(css = "#txtFirstName")
    private WebElement txtFirstNameEdit;
    //By txtFirstName = By.cssSelector(Utils.getRbPage().getString("txtFirstNameAecp"));
    public void explicitWaitForTxtFirstNameEdit() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.visibilityOf(txtFirstNameEdit));
		} catch (NoSuchElementException e) {
			log.error("No Element Found after wait" + e);
		}
	}
    public boolean isTxtFirstNameEditDisplayed() {
		if (txtFirstNameEdit.isDisplayed()) {
			log.info(txtFirstNameEdit.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(txtFirstNameEdit + " is not Displayed on the Page");
			return false;
		}
	}
    public WebElement getTxtFirstNameEdit() {
    	return txtFirstNameEdit;
    }
    
    @FindBy(css = "#txtMiddleName")
    private WebElement txtMiddleNameEdit;
	//By txtMiddleName = By.cssSelector(Utils.getRbPage().getString("txtMiddleNameAecp"));
    public boolean isTxtMiddleNameEditDisplayed() {
		if (txtMiddleNameEdit.isDisplayed()) {
			log.info(txtMiddleNameEdit.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(txtMiddleNameEdit + " is not Displayed on the Page");
			return false;
		}
	}
    public WebElement getTxtMiddleNameEdit() {
    	return txtMiddleNameEdit;
    }
	
	@FindBy(css = "#txtLastName")
    private WebElement txtLastNameEdit;
	//By txtLastName = By.cssSelector(Utils.getRbPage().getString("txtLastNameAecp"));
	public WebElement getTxtLastNameEdit() {
		return txtLastNameEdit;
	}
	public boolean isTxtLastNameEditDisplayed() {
		if (txtLastNameEdit.isDisplayed()) {
			log.info(txtLastNameEdit.getText() + " is displayed on the Page");
			return true;
		} else {
			log.info(txtLastNameEdit + " is not Displayed on the Page");
			return false;
		}
	}
	
	@FindBy(css = "#txtEmailID")
    private WebElement txtEmailIDEdit;
	//By txtEmailID = By.cssSelector(Utils.getRbPage().getString("txtEmailIDAecp"));
	public WebElement getTxtEmailIDEdit() {
		return txtEmailIDEdit;
	}
	public void explicitWaitForTxtEmailIDEdit() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.visibilityOf(txtEmailIDEdit));
		} catch (NoSuchElementException e) {
			log.error("No Element Found after wait" + e);
		}
	}
	
	@FindBy(css = "#txtAltEmailId")
    private WebElement txtAlternateEmailIDEdit;
	//By txtAlternateEmailID = By.cssSelector(Utils.getRbPage().getString("txtAlternateEmailIDAecp"));
	public WebElement getTxtAlternateEmailIDEdit() {
		return txtAlternateEmailIDEdit;
	}
	
	@FindBy(css = "#txtMobile")
    private WebElement txtPrimaryContactNumberEdit;
	//By txtPrimaryContactNumber = By.cssSelector(Utils.getRbPage().getString("txtPrimaryContactNumberAecp"));
	public WebElement getTxtPrimaryContactNumberEdit() {
		return txtPrimaryContactNumberEdit;
	}
	
	@FindBy(css = "#txtLAndline")
    private WebElement txtSecondaryContactNumberEdit;
	//By txtSecondaryContactNumber = By.cssSelector(Utils.getRbPage().getString("txtSecondaryContactNumberAecp"));
	public WebElement getTxtSecondaryContactNumberEdit() {
		return txtSecondaryContactNumberEdit;
	}
	
	@FindBy(css = "#cancelButton")
    private WebElement btnCancelEdit;
	//By btnCancel = By.cssSelector(Utils.getRbPage().getString("btnCancelAecp"));
	public void explicitWaitForBtnCancelEdit() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.visibilityOf(btnCancelEdit));
		} catch (NoSuchElementException e) {
			log.error("No Element Found after wait" + e);
		}
	}
	public void clickBtnCancelEdit() {
        click(btnCancelEdit);
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(css = "#ResetBtn")
    private WebElement btnResetEdit;
	//By btnReset = By.cssSelector(Utils.getRbPage().getString("btnResetAecp"));
	public void clickBtnResetEdit() {
        click(btnResetEdit);
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(css = "#AddUserSaveBtn")
    private WebElement btnSaveEdit;
	//By btnSave = By.cssSelector(Utils.getRbPage().getString("btnSaveAecp"));
	public void clickBtnSaveEdit() {
        click(btnSaveEdit);
        log.info("SignIn Button clicked successfully.");
    }
	public WebElement getBtnSaveEdit() {
		return btnSaveEdit;
	}
	
	@FindBy(css = "#ddlContactType")
    private WebElement ddlPrimaryContactTypeEdit;
	//By ddlPrimaryContactType = By.cssSelector(Utils.getRbPage().getString("ddlPrimaryContactTypeAecp"));
	public WebElement getDdlPrimaryContactTypeEdit() {
		return ddlPrimaryContactTypeEdit;
	}
	
	@FindBy(xpath = "//div[@class='w2ui-box'] ")
    private WebElement alertsuccessfullyBodyEdit;
	//By alertsuccessfullyBody = By.xpath(Utils.getRbPage().getString("alertsuccessfullyBodyAecp"));
	
	@FindBy(xpath = "//div[@class='w2ui-popup-buttons']")
    private WebElement alertsuccessfullyOkBtnEdit;
	//By alertsuccessfullyOkBtn = By.xpath(Utils.getRbPage().getString("alertsuccessfullyOkBtnAecp"));

}

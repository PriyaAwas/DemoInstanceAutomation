package sew.ai.pageObjects.csp;

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

import java.util.List;

public class AdminNotificationPage extends HomePage {

    private static final Logger log = LogManager.getLogger(AdminNotificationPage.class);

    public AdminNotificationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#connectme")
    private WebElement lnkConnectMe;

    public Boolean isLnkConnectMeVisible() {
        boolean isVisible = isElementVisible(lnkConnectMe);
        log.info("Connect Me link visibility status {}" + isVisible);
        return isVisible;
    }

    public String getConnectMeLinkLbl() {
        String label = getText(lnkConnectMe);
        log.info("Connect Me link label is " + label);
        return label;
    }

    public void clickLinkConnectMe() {
        click(lnkConnectMe);
        log.info("Connect Me link clicked {}.");
    }

    @FindBy(css = "#unReadConnectMe")
    private WebElement lnkUnreadNotiConnectMe;

    public Boolean isLnkUnreadNotiConnectMeVisible() {
        boolean isVisible = isElementVisible(lnkUnreadNotiConnectMe);
        log.info("Connect Me link visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLnkUnreadNotiConnectMeLbl() {
        String label = getText(lnkUnreadNotiConnectMe);
        log.info("Connect Me link label is " + label);
        return label;
    }

    public void clickLnkUnreadNotiConnectMe() {
        click(lnkUnreadNotiConnectMe);
        log.info("Connect Me link clicked {}.");
    }

    @FindBy(css = "#billing")
    private WebElement lnkBilling;

    public Boolean isLnkBillingVisible() {
        boolean isVisible = isElementVisible(lnkBilling);
        log.info("Billing link visibility status {}" + isVisible);
        return isVisible;
    }

    public String getBillingLinkLbl() {
        String label = getText(lnkBilling);
        log.info("Billing link label is " + label);
        return label;
    }

    public void clickLinkBilling() {
        click(lnkBilling);
        log.info("Billing link clicked {}.");
    }

    @FindBy(css = "#unReadBilling")
    private WebElement lnkUnReadNotiBilling;

    public Boolean islnkUnReadNotiBillingVisible() {
        boolean isVisible = isElementVisible(lnkUnReadNotiBilling);
        log.info("Billing Unread link visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLnkUnreadNotiBillingLbl() {
        String label = getText(lnkUnReadNotiBilling);
        log.info("Billing Unread link label is " + label);
        return label;
    }

    public void clickLnkUnreadNotiBilling() {
        click(lnkUnReadNotiBilling);
        log.info("Billing Unread link clicked {}.");
    }

    @FindBy(css = "#outage")
    private WebElement lnkOutage;

    public Boolean isLnkOutageVisible() {
        boolean isVisible = isElementVisible(lnkOutage);
        log.info("Outage link visibility status {}" + isVisible);
        return isVisible;
    }

    public String getOutageLinkLbl() {
        String label = getText(lnkOutage);
        log.info("Outage link label is " + label);
        return label;
    }

    public void clickLinkOutage() {
        click(lnkOutage);
        log.info("Outage link clicked {}.");
    }

    @FindBy(css = "#unReadOutage")
    private WebElement lnkUnReadNotiOutage;

    public Boolean isLnkUnReadNotiOutageVisible() {
        boolean isVisible = isElementVisible(lnkUnReadNotiOutage);
        log.info("Outage Unread link visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLnkUnReadNotiOutageLbl() {
        String label = getText(lnkUnReadNotiOutage);
        log.info("Outage Unread link label is " + label);
        return label;
    }

    public void clickLnkUnReadNotiOutage() {
        click(lnkUnReadNotiOutage);
        log.info("Outage Unread link clicked {}.");
    }

    @FindBy(css = "#service")
    private WebElement lnkServices;

    public Boolean isLnkServicesVisible() {
        boolean isVisible = isElementVisible(lnkServices);
        log.info("Services link visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLnkServicesLbl() {
        String label = getText(lnkServices);
        log.info("Services link label is " + label);
        return label;
    }

    public void clickLnkServices() {
        click(lnkServices);
        log.info("Services link clicked {}.");
    }

    @FindBy(css = "#unReadService")
    private WebElement lnkUnReadNotiService;

    public Boolean isLnkUnReadNotiServiceVisible() {
        boolean isVisible = isElementVisible(lnkUnReadNotiService);
        log.info("Services Unread link visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLnkUnReadNotiServiceLbl() {
        String label = getText(lnkUnReadNotiService);
        log.info("Services Unread link label is " + label);
        return label;
    }

    public void clickLnkUnReadNotiService() {
        click(lnkUnReadNotiService);
        log.info("Services Unread link clicked {}.");
    }

    @FindBy(css = "#demandresponse")
    private WebElement lnkDemandResponse;

    public Boolean isLnkDemandResponseVisible() {
        boolean isVisible = isElementVisible(lnkDemandResponse);
        log.info("Demand Response link visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLnkDemandResponseLbl() {
        String label = getText(lnkDemandResponse);
        log.info("Demand Response link label is " + label);
        return label;
    }

    public void clickLnkDemandResponse() {
        click(lnkDemandResponse);
        log.info("Demand Response link clicked {}.");
    }

    @FindBy(css = "#unReadDemandResponse")
    private WebElement lnkUnReadNotiDemandResponse;

    public Boolean isLnkUnReadNotiDemandResponseVisible() {
        boolean isVisible = isElementVisible(lnkUnReadNotiDemandResponse);
        log.info("Demand Response Unread link visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLnkUnReadNotiDemandResponseLbl() {
        String label = getText(lnkUnReadNotiDemandResponse);
        log.info("Demand Response Unread link label is " + label);
        return label;
    }

    public void clickLnkUnReadNotiDemandResponse() {
        click(lnkUnReadNotiDemandResponse);
        log.info("Demand Response Unread link clicked {}.");
    }

    @FindBy(css = "#leakalert")
    private WebElement lnkLeakAlert;

    public Boolean isLnkLeakAlertVisible() {
        boolean isVisible = isElementVisible(lnkLeakAlert);
        log.info("Leak Alert link visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLnkLeakAlertLbl() {
        String label = getText(lnkLeakAlert);
        log.info("Leak Alert link label is " + label);
        return label;
    }

    public void clickLnkLeakAlert() {
        click(lnkLeakAlert);
        log.info("Leak Alert link clicked {}.");
    }

    @FindBy(css = "#unLeakAlert")
    private WebElement lnkUnReadNotiLeakAlert;

    public Boolean isLnkUnReadNotiLeakAlertVisible() {
        boolean isVisible = isElementVisible(lnkUnReadNotiLeakAlert);
        log.info("Leak Alert Unread link visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLnkUnReadNotiLeakAlertLbl() {
        String label = getText(lnkUnReadNotiLeakAlert);
        log.info("Leak Alert Unread link label is " + label);
        return label;
    }

    public void clickLnkUnReadNotiLeakAlert() {
        click(lnkUnReadNotiLeakAlert);
        log.info("Leak Alert Unread link clicked {}.");
    }

    @FindBy(css = "#loginissue")
    private WebElement lnkLoginIssues;

    public Boolean isLnkLoginIssuesVisible() {
        boolean isVisible = isElementVisible(lnkLoginIssues);
        log.info("Login Issues link visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLnkLoginIssuesLbl() {
        String label = getText(lnkLoginIssues);
        log.info("Login Issues link label is " + label);
        return label;
    }

    public void clickLnkLoginIssues() {
        click(lnkLoginIssues);
        log.info("Login Issues link clicked {}.");
    }

    @FindBy(css = "#unReadLoginIssues")
    private WebElement lnkUnReadNotiLoginIssue;

    public Boolean isLnkUnReadNotiLoginIssueVisible() {
        boolean isVisible = isElementVisible(lnkUnReadNotiLoginIssue);
        log.info("Login Issues Unread link visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLnkUnReadNotiLoginIssueLbl() {
        String label = getText(lnkUnReadNotiLoginIssue);
        log.info("Login Issues Unread link label is " + label);
        return label;
    }

    public void clickLnkUnReadNotiLoginIssue() {
        click(lnkUnReadNotiLoginIssue);
        log.info("Login Issues Unread link clicked {}.");
    }

    @FindBy(css = "#sentitem")
    private WebElement lnkSent;

    public Boolean isLnkSentVisible() {
        boolean isVisible = isElementVisible(lnkSent);
        log.info("Sent link visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLnkSentLbl() {
        String label = getText(lnkSent);
        log.info("Sent link label is " + label);
        return label;
    }

    public void clickLnkSent() {
        click(lnkSent);
        log.info("Sent link clicked {}.");
    }

    @FindBy(css = "#Doneitem")
    private WebElement lnkComplete;

    public Boolean isLnkCompleteVisible() {
        boolean isVisible = isElementVisible(lnkComplete);
        log.info("Complete link visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLnkCompleteLbl() {
        String label = getText(lnkComplete);
        log.info("Complete link label is " + label);
        return label;
    }

    public void clickLnkComplete() {
        click(lnkComplete);
        log.info("Complete link clicked {}.");
    }

    @FindBy(css = "a[id*='btndone']")
    private WebElement btnCompleteMessageHeader;

    public Boolean isBtnCompleteMessageHeaderVisible() {
        boolean isVisible = isElementVisible(btnCompleteMessageHeader);
        log.info("Complete message header button visibility status {}" + isVisible);
        return isVisible;
    }

    public String getBtnCompleteMessageHeaderMouseHoverText() {
        String mouseHoverText = getAttribute(btnCompleteMessageHeader,"title");
        log.info("Complete messages button mouse hover text is " + mouseHoverText);
        return mouseHoverText;
    }

    public void clickBtnCompleteMessageHeader() {
        click(btnCompleteMessageHeader);
        log.info("Complete messages button clicked {}.");
    }

    @FindBy(css = ".donehdrnoti")
    private List<WebElement> lstDoneSymbolNotificationGridColumn;

    public Boolean isLstDoneSymbolNotificationGridColumnVisible() {
        boolean isVisible = isElementVisibleAlt(lstDoneSymbolNotificationGridColumn);
        log.info("Complete or Done button visibility status on Notification Grid Table is {}" + isVisible);
        return isVisible;
    }

    public void clickDoneIconForRequestID(String requestID){
        WebElement ele = getWebElement(By.xpath("//span[@class='msgSubject'][contains(text(),'"+requestID+"')]/preceding::a[contains(@class,'donehdrnoti')][1]"));
        clickElementUsingJsExecutor(ele);
    }


    @FindBy(css = "#saved")
    private WebElement lnkSavedMail;

    public Boolean isLnkSavedMailVisible() {
        boolean isVisible = isElementVisible(lnkSavedMail);
        log.info("Saved Mail Link visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLnkSavedMailLbl() {
        String label = getText(lnkSavedMail);
        log.info("Saved Mail Link label is " + label);
        return label;
    }

    public void clickLnkSavedMail() {
        click(lnkSavedMail);
        log.info("Saved Mail Link clicked {}.");
    }

    @FindBy(css = ".lblSavedMail")
    private WebElement lblUnReadNotiSavedMail;

    public Boolean isLblUnReadNotiSavedMailVisible() {
        boolean isVisible = isElementVisible(lblUnReadNotiSavedMail);
        log.info("Saved Mail Unread Label visibility status {}" + isVisible);
        return isVisible;
    }

    public String geUnReadNotiSavedMailLbl() {
        String label = getText(lblUnReadNotiSavedMail);
        log.info("Saved Mail Unread Label is " + label);
        return label;
    }

    public void clickLblUnReadNotiSavedMail() {
        click(lblUnReadNotiSavedMail);
        log.info("Saved Mail Unread Label clicked {}.");
    }

    @FindBy(css = "#trash")
    private WebElement lnkTrash;

    public Boolean isLnkTrashVisible() {
        boolean isVisible = isElementVisible(lnkTrash);
        log.info("Trash Link visibility status {}" + isVisible);
        return isVisible;
    }

    public String geLnkTrashLbl() {
        String label = getText(lnkTrash);
        log.info("Trash Link Label is " + label);
        return label;
    }

    public void clickLnkTrash() {
        click(lnkTrash);
        log.info("Trash Link clicked {}.");
    }

    @FindBy(css = "#allmail")
    private WebElement lnkAllMails;

    public Boolean isLnkAllMailsVisible() {
        boolean isVisible = isElementVisible(lnkAllMails);
        log.info("All Mails Link visibility status {}" + isVisible);
        return isVisible;
    }

    public String geLnkAllMailsLbl() {
        String label = getText(lnkAllMails);
        log.info("All Mails Link Label is " + label);
        return label;
    }

    public void clickLnkAllMails() {
        click(lnkAllMails);
        log.info("All Mails Link clicked {}.");
    }

    @FindBy(css = "a#inbox")
    public WebElement lnkInbox;

    public Boolean isLnkInboxVisible() {
        boolean isVisible = isElementVisible(lnkInbox);
        log.info("Inbox Link visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLnkInboxLbl() {
        String label = getText(lnkInbox);
        log.info("Inbox Link Label is " + label);
        return label;
    }

    public void clickLnkInbox() {
        click(lnkInbox);
        log.info("Inbox Link clicked {}.");
    }

    @FindBy(css = "#unReadInbox")
    private WebElement lnkUnReadNotiAllMails;

    public Boolean isLnkUnReadNotiAllMailsVisible() {
        boolean isVisible = isElementVisible(lnkUnReadNotiAllMails);
        log.info("All Mails Unread Link visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLnkUnReadNotiAllMailsLbl() {
        String label = getText(lnkUnReadNotiAllMails);
        log.info("All Mails Unread Link Label is " + label);
        return label;
    }

    public void clickLnkUnReadNotiAllMails() {
        click(lnkUnReadNotiAllMails);
        log.info("All Mails Unread Link clicked {}.");
    }

    @FindBy(css = "#autoresponse")
    private WebElement lnkAutoResponse;

    public Boolean isLnkAutoResponseVisible() {
        boolean isVisible = isElementVisible(lnkAutoResponse);
        log.info("AutoResponse Link visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLnkAutoResponseLbl() {
        String label = getText(lnkAutoResponse);
        log.info("AutoResponse Link Label is " + label);
        return label;
    }

    public void clickLnkAutoResponse() {
        click(lnkAutoResponse);
        log.info("AutoResponse Link clicked {}.");
    }

    @FindBy(css = "#outbox")
    private WebElement lnkSendNotification;

    public Boolean isLnkSendNotificationVisible() {
        boolean isVisible = isElementVisible(lnkSendNotification);
        log.info("Send Notification Link visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLnkSendNotificationLbl() {
        String label = getText(lnkSendNotification);
        log.info("Send Notification Link Label is " + label);
        return label;
    }

    public void clickLnkSendNotification() {
        click(lnkSendNotification);
        log.info("Send Notification Link clicked {}.");
    }

    @FindBy(css="#DetailCustomer div > b:nth-child(1)")
    private WebElement lblCustomerNameNotifyHeader;

    public Boolean isLblCustomerNameNotifyHeaderVisible() {
        boolean isVisible = isElementVisible(lblCustomerNameNotifyHeader);
        log.info("Customer Name Label visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblCustomerNameNotifyHeaderLbl() {
        String label = getText(lblCustomerNameNotifyHeader);
        log.info("Customer Name Label is " + label);
        return label;
    }
    @FindBy(css="#DetailCustomer > div > b:nth-child(3)")
    private WebElement lblServiceAccountNotifyHeader;
    public Boolean isLblServiceAccountNotifyHeaderVisible() {
        boolean isVisible = isElementVisible(lblServiceAccountNotifyHeader);
        log.info("Service Account Label visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblServiceAccountNotifyHeaderLbl() {
        String label = getText(lblServiceAccountNotifyHeader);
        log.info("Customer Name Label is " + label);
        return label;
    }
    @FindBy(css="#DetailCustomer > div > a b")
    private WebElement lblMobileNumberNotifyHeader;
    public Boolean isLblMobileNumberNotifyHeaderVisible() {
        boolean isVisible = isElementVisible(lblMobileNumberNotifyHeader);
        log.info("Mobile Number Label visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblMobileNumberNotifyHeaderLbl() {
        String label = getText(lblMobileNumberNotifyHeader);
        log.info("Mobile Number Label is " + label);
        return label;
    }


    @FindBy(xpath = "//div[@id='DetailCustomer']//b[text()='Customer Name: ']/following::span[1]")
    private WebElement txtCustomerNameNotifyHeader;

    public WebElement getWebElementTxtCustomerNameNotifyHeader(){
        return txtCustomerNameNotifyHeader;
    }

    public Boolean isTxtCustomerNameNotifyHeaderVisible() {
        boolean isVisible = isElementVisible(txtCustomerNameNotifyHeader);
        log.info("Customer Name Text visibility status {}" + isVisible);
        return isVisible;
    }

    public String getTxtCustomerNameNotifyHeaderLbl() {
        String label = getText(txtCustomerNameNotifyHeader);
        log.info("Customer Name text is " + label);
        return label;
    }

    public void clickTxtCustomerNameNotifyHeader() {
        click(txtCustomerNameNotifyHeader);
        log.info("Customer Name Text clicked {}.");
    }

    @FindBy(css = "#DetailCustomer a[onclick='showCustomerDetailPopup()'] > span")
    private WebElement txtServiceAccountNotifyHeader;

    public Boolean isTxtServiceAccountNotifyHeaderVisible() {
        boolean isVisible = isElementVisible(txtServiceAccountNotifyHeader);
        log.info("Service Account Number Text visibility status {}" + isVisible);
        return isVisible;
    }

    public String getTxtServiceAccountNotifyHeaderLbl() {
        String label = getText(txtServiceAccountNotifyHeader);
        log.info("Service Account Number Text Label is " + label);
        return label;
    }

    public void clickTxtServiceAccountNoMail() {
        click(txtServiceAccountNotifyHeader);
        log.info("Service Account Number  Text clicked {}.");
    }

    @FindBy(xpath = "//div[@id='DetailCustomer']//b[text()='Mobile Number: ']/following::span[1]")
    private WebElement txtMobileNotifyHeader;

    public WebElement getWebElementTxtMobileNotifyHeader(){
        return txtMobileNotifyHeader;
    }

    public Boolean isTxtMobileNumberNotifyHeaderVisible() {
        boolean isVisible = isElementVisible(txtMobileNotifyHeader);
        log.info("Mobile Number Mail Text visibility status {}" + isVisible);
        return isVisible;
    }

    public String getTxtMobileNoMailLbl() {
        String label = getText(txtMobileNotifyHeader);
        log.info("Mobile Number Mail Text Label is " + label);
        return label;
    }

    public void clickTxtMobileNumberNotifyHeader() {
        click(txtMobileNotifyHeader);
        log.info("Mobile Number Text clicked {}.");
    }

    @FindBy(xpath = "//div[@class='top-header-area']//a[text()='Search']")
    private WebElement lnkSearch;

    public Boolean isLnkSearchVisible() {
        boolean isVisible = isElementVisible(lnkSearch);
        log.info("Search Link visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLnkSearchLbl() {
        String label = getText(lnkSearch);
        log.info("Search Link Label is " + label);
        return label;
    }

    public void clickLnkSearch() {
        click(lnkSearch);
        log.info("Search Link clicked {}.");
    }

    @FindBy(css = "#ddlSearch")
    private WebElement dropdownSearchBy;

    public WebElement getWebElementDropdownSearchBy(){
        return dropdownSearchBy;
    }

    public Boolean isDropdownSearchByVisible() {
        boolean isVisible = isElementVisible(dropdownSearchBy);
        log.info("SearchBy Dropdown visibility status {}" + isVisible);
        return isVisible;
    }

    public List<String> getLstSearchByDropdownOptions(){
        log.info("Search By Dropdown Values are "+ getAllOptionsInListBox(dropdownSearchBy));
        return  getAllOptionsInListBox(dropdownSearchBy);
    }

    public String getDropdownSearchByLbl() {
        String label = getText(dropdownSearchBy);
        log.info("SearchBy Dropdown Label is " + label);
        return label;
    }

    public void selectSearchByDropdown(String searchBy) {
        selectByVisibleText(dropdownSearchBy, searchBy);
        log.info("SearchBy Value is selected in SearchBy drop-down {}.");
    }

    public String getSelectOptionSearchBy() {
        String optionSelected =getSelectedValueInDropBox(dropdownSearchBy);
        log.info("Selected option in the SearchBy drop-down is "+optionSelected);
        return optionSelected;
    }

    public void clickDropdownSearchBy() {
        click(dropdownSearchBy);
        log.info("SearchBy Dropdown clicked {}.");
    }

    @FindBy(css = "#txtEmailorAccountno")
    private WebElement searchByTextBox;

    public Boolean isSearchByTextBoxVisible() {
        boolean isVisible = isElementVisible(searchByTextBox);
        log.info("SearchBy TextBox visibility status {}" + isVisible);
        return isVisible;
    }

    public void clearSearchByTextBox() {
        clear(searchByTextBox);
        log.info("SearchBy Textbox field is cleared.");
    }

    public void populateSearchByTextBox(String value) {
        log.info("Populating SearchBy TextBox {} :" + value);
        sendKeys(searchByTextBox, value);
        log.info("SearchBy TextBox populated successfully with Value " + value);
    }

    public String getSearchByTextBoxValue() {
        String label = getAttribute(searchByTextBox,"value");
        log.info("Search By TextBox Value is " + label);
        return label;
    }

    @FindBy(css = "#btnsearch")
    private WebElement btnSearch;

    public Boolean isBtnSearchVisible() {
        boolean isVisible = isElementVisible(btnSearch);
        log.info("Search Button visibility status {}" + isVisible);
        return isVisible;
    }

    public String getBtnSearchLbl() {
        String label = getText(btnSearch);
        log.info("Search Button Label is " + label);
        return label;
    }

    public void clickBtnSearch() {
        click(btnSearch);
        log.info("Search Button clicked {}.");
    }

    @FindBy(css = "div#nodata")
    private WebElement lblMsgNoMessages;

    public Boolean isLblMsgNoMessagesVisible() {
        boolean isVisible = isElementVisible(lblMsgNoMessages);
        log.info("No Messages Label visibility status {}" + isVisible);
        return isVisible;
    }

    public String getMsgNoMessagesLbl() {
        String label = getText(lblMsgNoMessages);
        log.info("No Messages Label is " + label);
        return label;
    }

    @FindBy(css = "#buttonClear")
    private WebElement btnClear;

    public Boolean isBtnClearVisible() {
        boolean isVisible = isElementVisible(btnClear);
        log.info("Clear Button visibility status {}" + isVisible);
        return isVisible;
    }

    public String getBtnClearLbl() {
        String label = getText(btnClear);
        log.info("Clear Button Label is " + label);
        return label;
    }

    public void clickBtnClear() {
        click(btnClear);
        log.info("Clear Button clicked {}.");
    }

    @FindBy(css = "#validateDivOverpageLoad")
    private WebElement lnkSearchSectionElements;

    public Boolean isLnkSearchSectionElementsVisible() {
        boolean isVisible = isElementVisible(lnkSearchSectionElements);
        log.info("Search Section Elements Link visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLnkSearchSectionElementsLbl() {
        String label = getText(lnkSearchSectionElements);
        log.info("Search Section Elements Link Label is " + label);
        return label;
    }

    public void clickLnkSearchSectionElements() {
        click(lnkSearchSectionElements);
        log.info("Search Section Elements Link clicked {}.");
    }


    @FindBy(xpath = "//h2[contains(text(),'Notification')]")
    private WebElement lblTextNotification;

    public Boolean isLblTextNotificationVisible() {
        boolean isVisible = isElementVisible(lblTextNotification);
        log.info("Text Notification Label visibility status {}" + isVisible);
        return isVisible;
    }

    public String getlblTextNotification() {
        String label = getText(lblTextNotification);
        log.info("Text Notification Label is " + label);
        return label;
    }

    public void clickLblTextNotification() {
        click(lblTextNotification);
        log.info("Text Notification Label clicked {}.");
    }

    @FindBy(xpath = "//div[@class='DetailsMessageHeader']/div[contains(@class,'subject')]/div[contains(text(),'Comments')]")
    private WebElement txtCommentsFieldValueNotifications;

    public Boolean isTxtCommentsFieldValueNotificationsVisible() {
        boolean isVisible = isElementVisible(txtCommentsFieldValueNotifications);
        log.info("Comments Field value Notifications visibility status {}" + isVisible);
        return isVisible;
    }

    public String getTxtCommentsFieldValueNotifications() {
        String label = getText(txtCommentsFieldValueNotifications);
        log.info("Comments Field value Notification text is " + label);
        return label;
    }

    @FindBy(css = "#pageLoadDisclaimer")
    private WebElement lblTextPageLoadDisclaimer;

    public Boolean isLblTextPageLoadDisclaimerVisible() {
        boolean isVisible = isElementVisible(lblTextPageLoadDisclaimer);
        log.info("Notifications Landing Page Disclaimer visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblTextPageLoadDisclaimer() {
        String label = getText(lblTextPageLoadDisclaimer);
        log.info("Notifications Landing Page Disclaimer text is " + label);
        return label;
    }

    @FindBy(css = "#chkall")
    private WebElement chkboxCheckAll;

    public Boolean isChkboxCheckAllVisible() {
        log.info("Notification CheckAll Checkbox visible status {}: " + chkboxCheckAll.isDisplayed());
        return chkboxCheckAll.isDisplayed();
    }

    public void checkAllNotifications() {
        check(chkboxCheckAll);
        log.info("All Notifications check box checked.");
    }

    public void uncheckAllNotifications() {
        unCheck(chkboxCheckAll);
        log.info("All Notifications check box unchecked.");
    }


    @FindBy(css = "#lblFromTo")
    private WebElement fromGridHeader;

    public Boolean isFromGridHeaderVisible() {
        boolean isVisible = isElementVisible(fromGridHeader);
        log.info("Notifications Table From Column Header visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblFromGridHeader() {
        String label = getText(fromGridHeader);
        log.info("Notifications Table From Column Header text is " + label);
        return label;
    }

    @FindBy(css = "#btnSortFrom")
    private WebElement lnkSortingInboxFrom;

    public Boolean isLnkSortingInboxFromVisible() {
        boolean isVisible = isElementVisible(lnkSortingInboxFrom);
        log.info("Notifications Table From Column Header Sort Icon visibility status {}" + isVisible);
        return isVisible;
    }

    public void clickLnkSortingInboxFrom() {
        click(lnkSortingInboxFrom);
        log.info("Notifications Table From Column Header Sort Icon clicked {}.");
    }

    @FindBy(xpath = "//span[contains(text(),'Subject')]")
    private WebElement subjectGridHeader;

    public Boolean isSubjectGridHeaderVisible() {
        boolean isVisible = isElementVisible(subjectGridHeader);
        log.info("Notifications Table Subject Column Header visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblSubjectGridHeader() {
        String label = getText(subjectGridHeader);
        log.info("Notifications Table Subject Column Header text is " + label);
        return label;
    }

    @FindBy(css = "#btnSubjectSort")
    private WebElement lnkSortingInboxSubject;

    public Boolean isLnkSortingInboxSubjectVisible() {
        boolean isVisible = isElementVisible(lnkSortingInboxSubject);
        log.info("Notifications Table Subject Column Header Sort Icon visibility status {}" + isVisible);
        return isVisible;
    }

    public void clickLnkSortingInboxSubject() {
        click(lnkSortingInboxSubject);
        log.info("Notifications Table Subject Column Header Sort Icon clicked {}.");
    }

    @FindBy(xpath = "//span[contains(text(),'Date')]")
    private WebElement dateGridHeader;

    public Boolean isDateGridHeaderVisible() {
        boolean isVisible = isElementVisible(dateGridHeader);
        log.info("Notifications Table Date Column Header visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblDateGridHeader() {
        String label = getText(dateGridHeader);
        log.info("Notifications Table Date Column Header text is " + label);
        return label;
    }

    @FindBy(css = "#btnDateSort")
    private WebElement lnkSortingDate;

    public Boolean isLnkSortingDateVisible() {
        boolean isVisible = isElementVisible(lnkSortingDate);
        log.info("Notifications Table Subject Column Header Sort Icon visibility status {}" + isVisible);
        return isVisible;
    }

    public void clickLnkSortingDate() {
        click(lnkSortingDate);
        log.info("Notifications Table Subject Column Header Sort Icon clicked {}.");
    }

    @FindBy(css = "#ddlPagesize")
    private WebElement lstPageSize;

    public WebElement getWebElementPageSize(){
        return lstPageSize;
    }

    public void scrollToPageSizeDropdown(){
        scrollToElement(lstPageSize);
        log.info("Page has been scrolled to the PageSize Element.");
    }

    public Boolean isPageSizeDropdownVisible() {
        log.info("Checking the visibility of PageSize dropdown on the page.");
        log.info("PageSize dropdown visibility status {}: " + isElementVisible(lstPageSize));
        return isElementVisible(lstPageSize);
    }

    public void clickPageSizeDropdown() {
        click(lstPageSize);
        log.info("PageSize drop-down clicked successfully.");
    }

    public String getPageSizeDropdownSelectedOption() {
        log.info("Checking the selected option in PageSize dropdown on the page.");
        String option = getText(lstPageSize);
        log.info("PageSize dropdown selected option value {}: " + option);
        return option;
    }

    public List<String> getAllOptionsinPageSizeDropdown() {
        List<String> pageSizeOptions = getAllOptionsInListBox(lstPageSize);
        log.info("Notification PageSize Dropdown Options {}: " + pageSizeOptions);
        return pageSizeOptions;
    }

    public void selectPageSizeDropdown(String pageSizeValue){
        selectByVisibleText(lstPageSize,pageSizeValue);
        log.info("PageSize dropdown has been Selected with the value "+pageSizeValue);
    }

    @FindBy(css = "#right")
    private WebElement btnNext;

    public WebElement getWebEleBtnNext(){
        return btnNext;
    }

    public void waitForNextButtonToBeVisibile() {
        log.info("Wait for Next Page visibility on Notification Page.");
        waitForElementToBeVisible(btnNext);
    }

    public Boolean isNextButtonVisible() {
        log.info("Checking the visibility of Next Button on the page.");
        log.info("PageSize dropdown visibility status {}: " + isElementVisible(btnNext));
        return isElementVisible(btnNext);
    }

    public void clickNextButton() {
        click(btnNext);
        log.info("Next Button clicked successfully.");
    }

    @FindBy(css = "#left")
    private WebElement btnPrev;

    public Boolean isPreviousButtonVisible() {
        log.info("Checking the visibility of Previous Button on the page.");
        log.info("PageSize dropdown visibility status {}: " + isElementVisible(btnPrev));
        return isElementVisible(btnPrev);
    }

    public void clickPreviousButton() {
        click(btnPrev);
        log.info("Previous Button clicked successfully.");
    }


    @FindBy(xpath = "//div[@class='SaveImageContainer']")
    private WebElement lstIconSaveMail;

    public Boolean isSaveIconNotificationVisible() {
        log.info("Checking the visibility of Save Icon on the page.");
        log.info("Save Icon visibility status {}: " + isElementVisible(lstIconSaveMail));
        return isElementVisible(lstIconSaveMail);
    }

    public void clickSaveIconNotification() {
        click(lstIconSaveMail);
        log.info("Save Icon clicked successfully.");
    }

    @FindBys(@FindBy(css = ".SelectContainer"))
    private List<WebElement> lstCheckboxesNotifications;

    public Boolean isLstCheckboxesNotificationsVisible() {
        boolean isVisible = isAllElementVisible(lstCheckboxesNotifications);
        log.info("Notifications CheckBox visibility status {}" + isVisible);
        return isVisible;
    }
    public List<WebElement> getLstCheckboxesNotifications() {
        return lstCheckboxesNotifications;
    }

    public void clickLstCheckboxesNotifications(int i) {
        click(lstCheckboxesNotifications.get(i));
        log.info("Clicked on Notification CheckBox List at the Index \"+i");
    }

    public void clickCheckBoxForRequestID(String requestID){
        WebElement ele =getWebElement(By.xpath("//span[@class='msgSubject'][contains(text(),'"+requestID+"')]/preceding::input[1]"));
        clickElementUsingJsExecutor(ele);
    }
    @FindBy(css = "#btnSave")
    private WebElement btnSaveHeader;

    public Boolean isBtnSaveHeaderVisible() {
        boolean isVisible = isElementVisible(btnSaveHeader);
        log.info("Notifications Save Header Button visibility status {}" + isVisible);
        return isVisible;
    }

    public void clickBtnSaveHeader() {
        click(btnSaveHeader);
        log.info("Notifications Save Header Button clicked {}.");
    }

    public void clickSaveIconForRequestID(String requestID){
        WebElement ele =getWebElement(By.xpath("//span[@class='msgSubject'][contains(text(),'"+requestID+"')]/preceding::div[@class='SaveImageContainer']/img"));
        clickElementUsingJsExecutor(ele);
    }

    public Integer getSaveIconAttributeForRequestID(String requestID){
        int value = Integer.parseInt(getAttribute(driver.findElement(By.xpath("//span[@class='msgSubject'][contains(text(),'"+requestID+"')]/preceding::div[@class='SaveImageContainer']/img")),"alt"));
        return value;
    }

    @FindBy(css = "#ContentPlaceHolder1_rightpanel_btnDelete")
    private WebElement btnDeleteHeader;

    public Boolean isbtnDeleteHeaderVisible() {
        boolean isVisible = isElementVisible(btnDeleteHeader);
        log.info("Notifications Delete Header Button visibility status {}" + isVisible);
        return isVisible;
    }

    public void clickBtnDeleteHeader() {
        click(btnDeleteHeader);
        log.info("Notifications Delete Header Button clicked {}.");
    }

    @FindBys(@FindBy(xpath = "//div[@id ='divHeader']/div"))
    private List<WebElement> lstGridHeaders;

    //    public Boolean isLstHeaderInboxVisible() {
//        boolean isVisible = isElementVisible(lstGridHeaders);
//        log.info("Notifications Table Headers List visibility status {}" + isVisible);
//        return isVisible;
//    }
//    public void clickLstHeaderInbox() {
//        click(lstGridHeaders);
//        log.info("Notifications Table Headers List clicked {}.");
//    }
    public List<WebElement> getLstGridHeaders() {
        return lstGridHeaders;
    }

    @FindBy(xpath = "//div[@class = 'TimeContainer']")
    private WebElement lstDateInbox;

    @FindBys(@FindBy(xpath = "//span[@class = 'msgSubject']"))
    private List<WebElement> lstSubject;
    public List<WebElement> getLstSubject() {
        return lstSubject;
    }
    public void waitForLstSubjectToBeVisible() {
        int size = lstSubject.size() -1;
        log.info("Wait for Allowed File Types Label visibility on Notification Page.");
        waitForElementToBeVisible(lstSubject.get(size));
    }

    public void clickLstSubject(int i){
        click(lstSubject.get(i));
        log.info("Clicked on Notification Subject List at the Index "+i);
    }

    public void clickSubjectForRequestID(String requestID){
        WebElement ele = getWebElement(By.xpath("//span[@class='msgSubject'][contains(text(),'"+requestID+"')]"));
        clickElementUsingJsExecutor(ele);
    }


    @FindBys(@FindBy(xpath = "//*[@id='ulNotificatons']/li[@class='unread']"))
    private List<WebElement> lstUnreadNotifications;

    public List<WebElement> getLstUnreadNotifications() {
        return lstUnreadNotifications;
    }

    public void clickLstUnreadNotifications(int i){
        click(lstUnreadNotifications.get(i));
        log.info("Clicked on Unread Notification at the Index "+i);
    }

    @FindBys(@FindBy(css = ".MailListing li:not([class^='unread'])"))
    private List<WebElement> lstReadNotificationsRows;
    public List<WebElement> getLstReadNotificationsRows() {
        return lstReadNotificationsRows;
    }

    @FindBys(@FindBy(css = "#ulNotificatons li.unread"))
    private List<WebElement> lstUnreadNotificationsRows;
    public List<WebElement> getLstUnreadNotificationsRows() {
        return lstUnreadNotificationsRows;
    }

    @FindBys(@FindBy(css = ".FromContainer"))
    private List<WebElement> lstFromColumnData;

    public List<WebElement> getLstFromColumnData() {
        return lstFromColumnData;
    }

    @FindBy(xpath = "//div[@class='notidash_btn']")
    private WebElement btnNotificationDashboard;

    @FindBy(css="#outboxmsg tr:nth-child(1) td:nth-child(1)>div.user-outbox-area label")
    private WebElement lblTypeOfMessage;
    public Boolean isLblTypeOfMessageVisible() {
        boolean isVisible = isElementVisible(lblTypeOfMessage);
        log.info("Type Of Message Label visibility status {}" + isVisible);
        ExtentLogger.logInfo("Type Of Message Label visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblTypeOfMessage() {
        String label = getText(lblTypeOfMessage);
        log.info("Type of Message label Text is " + label);
        ExtentLogger.logInfo("Type of Message label Text is " + label);
        return label;
    }
    @FindBy(css="#outboxmsg tr:nth-child(1) td:nth-child(2)>div.user-outbox-area label")
    private WebElement lblAccountType;
    public Boolean isLblAccountTypeVisible() {
        boolean isVisible = isElementVisible(lblAccountType);
        log.info("AccountType Label visibility status {}" + isVisible);
        ExtentLogger.logInfo("AccountType Label visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblAccountType() {
        String label = getText(lblAccountType);
        log.info("AccountType label Text is " + label);
        ExtentLogger.logInfo("AccountType label Text is " + label);
        return label;
    }

    @FindBy(css="#outboxmsg tr:nth-child(2) td:nth-child(1)>div.user-outbox-area label")
    private WebElement lblLocation;
    public Boolean isLblLocationVisible() {
        boolean isVisible = isElementVisible(lblLocation);
        log.info("Location Label visibility status {}" + isVisible);
        ExtentLogger.logInfo("Location Label visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblLocation() {
        String label = getText(lblLocation);
        log.info("Location label Text is " + label);
        ExtentLogger.logInfo("Location label Text is " + label);
        return label;
    }

    @FindBy(css="#outboxmsg tr:nth-child(2) td:nth-child(2)>div.user-outbox-area label")
    private WebElement lblModeOfMessage;
    public Boolean isLblModeOfMessageVisible() {
        boolean isVisible = isElementVisible(lblModeOfMessage);
        log.info("ModeOfMessage Label visibility status {}" + isVisible);
        ExtentLogger.logInfo("ModeOfMessage Label visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblModeOfMessage() {
        String label = getText(lblModeOfMessage);
        log.info("ModeOfMessage label Text is " + label);
        ExtentLogger.logInfo("ModeOfMessage label Text is " + label);
        return label;
    }



    @FindBy(css="#outboxmsg tr:nth-child(3) td:nth-child(1)>div.user-outbox-area label")
    private WebElement lblCustomerName;
    public Boolean isLblCustomerNameVisible() {
        boolean isVisible = isElementVisible(lblCustomerName);
        log.info("CustomerName Label visibility status {}" + isVisible);
        ExtentLogger.logInfo("CustomerName Label visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblCustomerName() {
        String label = getText(lblCustomerName);
        log.info("CustomerName label Text is " + label);
        ExtentLogger.logInfo("CustomerName label Text is " + label);
        return label;
    }

    @FindBy(css="div.subjectboxoutage.email label")
    private WebElement lblSubject;
    public Boolean isLblSubjectVisible() {
        boolean isVisible = isElementVisible(lblSubject);
        log.info("Subject Label visibility status {}" + isVisible);
        ExtentLogger.logInfo("Subject Label visibility status {}" + isVisible);
        return isVisible;
    }

    @FindBy(css=".Text-outbox-area #txtcustomername")
    private WebElement txtBoxCustomerName;

    public Boolean isTxtBoxCustomerNameVisible() {
        boolean isVisible = isElementVisible(txtBoxCustomerName);
        log.info("CustomerName Textbox visibility status {}" + isVisible);
        ExtentLogger.logInfo("CustomerName Textbox visibility status {}" + isVisible);
        return isVisible;
    }


    public void clearTxtBoxCustomerName() {
        clear(txtBoxCustomerName);
        log.info("CustomerName Textbox field is cleared.");
        ExtentLogger.logInfo("CustomerName Textbox field is cleared.");
    }

    public void populatetxtBoxCustomerName(String value) {
        log.info("Populating CustomerName TextBox {} :" + value);
        sendKeys(txtBoxCustomerName, value);
        log.info("CustomerName TextBox populated successfully with Value " + value);
        ExtentLogger.logInfo("CustomerName TextBox populated successfully with Value " + value);
    }
    public String getLblSubject() {
        String label = getText(lblSubject);
        log.info("Subject label Text is " + label);
        ExtentLogger.logInfo("Subject label Text is " + label);
        return label;
    }
    @FindBy(css = "#ddltypeofmessage")
    private WebElement ddTypeOfMessageSendNoti;

    public WebElement ddWebEleTypeOfMessageSendNoti(){
        return ddTypeOfMessageSendNoti;
    }

    public void waitForDdTypeOfMessageSendNotiToBeVisible() {
        log.info("Wait for Message Type Dropdwon visibility on Notification Page.");
        waitForElementToBeVisible(ddTypeOfMessageSendNoti);
    }

    public Boolean isDdTypeOfMessageSendNotiVisible() {
        boolean isVisible = isElementVisible(ddTypeOfMessageSendNoti);
        log.info("MessageType Dropdown visibility status {}" + isVisible);
        ExtentLogger.logInfo("MessageType Dropdown visibility status {}" + isVisible);
        return isVisible;
    }

    public void selectTypeOfMessageDropdown(String value){
        selectByVisibleText(ddTypeOfMessageSendNoti,value);
        log.info("Type of Message dropdown has been Selected with the value "+ddTypeOfMessageSendNoti);
    }

    public void selectDefaultTypeOfMessageDropdown(){
        selectByIndex(ddTypeOfMessageSendNoti,0);
        pause(500);
        log.info("Type of Message dropdown has been Selected with the default value");
    }

    public List<String> getSelectOptionsMessageDropdown(){

        List<String> selectOptionsMessageDropdown = getAllOptionsInListBox(ddTypeOfMessageSendNoti);
        log.info("The Select Options in Message Type Dropdown are "+selectOptionsMessageDropdown);
        return selectOptionsMessageDropdown;
    }


    @FindBy(css = "#radioAccountType")
    private WebElement ddAccountTypeSendNoti;

    public WebElement ddWebEleAccountTypeSendNoti(){
        return ddAccountTypeSendNoti;
    }

    public Boolean isDdAccountTypeSendNotiVisible() {
        boolean isVisible = isElementVisible(ddAccountTypeSendNoti);
        log.info("AccountType Dropdown visibility status {}" + isVisible);
        ExtentLogger.logInfo("AccountType Dropdown visibility status {}" + isVisible);
        return isVisible;
    }

    public void waitForDdAccountTypeSendNotiToBeVisible() {
        log.info("Wait for Account Type Dropdwon visibility on Notification Page.");
        waitForElementToBeVisible(ddAccountTypeSendNoti);
    }

    public void selectDefaultAccountTypeDropdown(){
        selectByIndex(ddAccountTypeSendNoti,0);
        pause(500);
        log.info("Account Type dropdown has been Selected with the default value");
    }


    public void selectAccountTypeDropdown(String value){
        selectByVisibleText(ddAccountTypeSendNoti,value);
        log.info("Account Type dropdown has been Selected with the value "+ddAccountTypeSendNoti);
    }

    public List<String> getSelectOptionsAccountDropdown(){

        List<String> selectOptionsAccountDropdown = getAllOptionsInListBox(ddAccountTypeSendNoti);
        log.info("The Select Options in Account Type Dropdown are "+selectOptionsAccountDropdown);
        return selectOptionsAccountDropdown;
    }


    @FindBy(css = "#ddlcity")
    private WebElement ddLocationSendNoti;

    public WebElement ddWebEleLocationSendNoti(){
        return ddLocationSendNoti;
    }

    public Boolean isDdLocationSendNotiVisible() {
        boolean isVisible = isElementVisible(ddLocationSendNoti);
        log.info("Location Dropdown visibility status {}" + isVisible);
        ExtentLogger.logInfo("Location Dropdown visibility status {}" + isVisible);
        return isVisible;
    }
    public void selectLocationDropdown(String value){
        selectByVisibleText(ddLocationSendNoti,value);
        log.info("Location dropdown has been Selected with the value "+ddLocationSendNoti);
    }

    public void selectDefaultLocationDropdown(){
        selectByIndex(ddLocationSendNoti,0);
        pause(500);
        log.info("Location dropdown has been Selected with the default value");
    }

    @FindBy(css = "#ddlMessageMode")
    private WebElement ddModeOfMessageSendNoti;

    public WebElement ddWebEleModeOfMessageSendNoti(){
        return ddModeOfMessageSendNoti;
    }

    public void waitForDdModeOfMessageSendNotiToBeVisible() {
        log.info("Wait for Mode Of Message Dropdown visibility on Notification Page.");
        waitForElementToBeVisible(ddModeOfMessageSendNoti);
    }

    public void selectDefaultModeOfMessageDropdown(){
        selectByIndex(ddModeOfMessageSendNoti,0);
        pause(500);
        log.info("Mode of Message dropdown has been Selected with the default value");
    }

    public Boolean isDdModeOfMessageSendNotiVisible() {
        boolean isVisible = isElementVisible(ddModeOfMessageSendNoti);
        log.info("ModeOfMessage Dropdown visibility status {}" + isVisible);
        ExtentLogger.logInfo("ModeOfMessage Dropdown visibility status {}" + isVisible);
        return isVisible;
    }

    public void selectModeOfMessageDropdown(String value){
        selectByVisibleText(ddModeOfMessageSendNoti,value);
        pause(500);
        log.info("Mode of Message has been Selected with the value"+ddModeOfMessageSendNoti);
    }

    public List<String> getSelectOptionsModeOfMessageDropdown(){

        List<String> selectOptionsModeOfMessageDropdown = getAllOptionsInListBox(ddModeOfMessageSendNoti);
        log.info("The Select Options in Mode Of Message Dropdown are "+selectOptionsModeOfMessageDropdown);
        return selectOptionsModeOfMessageDropdown;
    }

    @FindBy(css = "#txtmsgsubject")
    private WebElement txtBoxSubjectSendNoti;

    public Boolean isTxtBoxSubjectSendNotiVisible() {
        boolean isVisible = isElementVisible(txtBoxSubjectSendNoti);
        log.info("Subject TextBox visibility status {}" + isVisible);
        return isVisible;
    }

    public void clearTxtBoxSubjectSendNoti() {
        clear(txtBoxSubjectSendNoti);
        log.info("Subject Textbox field is cleared.");
        ExtentLogger.logInfo("Subject Textbox field is cleared.");
    }

    public void populateTxtBoxSubjectSendNoti(String value) {
        log.info("Populating Subject TextBox {} :" + value);
        sendKeys(txtBoxSubjectSendNoti, value);
        log.info("Subject TextBox populated successfully with Value " + value);
        ExtentLogger.logInfo("Subject TextBox populated successfully with Value " + value);
    }

    @FindBy(css = "#btnSubmitReply")
    private List<WebElement> lstBtnSendOnSendNoti;

    public Boolean isLstBtnSendOnSendNotiVisible() {
        boolean isVisible = isElementVisibleAlt(lstBtnSendOnSendNoti);
        log.info("Send Button on Send Notification visibility status {}" + isVisible);
        return isVisible;
    }

    @FindBy(css = "#btnSubmitReply")
    private WebElement btnSendOnSendNoti;

    public Boolean isBtnSendOnSendNotiVisible() {
        boolean isVisible = isElementEnabled(btnSendOnSendNoti);
        log.info("Send Button on Send Notification visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblBtnSendOnSendNoti() {
        String label = getAttribute(btnSendOnSendNoti,"value");
        log.info("Send Button on Send Notification label Text is " + label);
        return label;
    }


    public void clickBtnSendOnSendNoti(){
        click(btnSendOnSendNoti);
        log.info("Clicked on Send Button on Send Notification");
        ExtentLogger.logInfo("Clicked on Send Button on Send Notification");
    }

    public void waitForBtnSendOnSendNotiToBeVisible() {
        log.info("Wait for Send Button visibility on Send Notification Page");
        waitForElementToBeVisible(btnSendOnSendNoti);
    }



    @FindBy(css = ".note-editable")
    private WebElement txtBoxForEmailBodySendNoti;

    public Boolean isTxtBoxForEmailBodySendNotiVisible() {
        boolean isVisible = isElementVisible(txtBoxForEmailBodySendNoti);
        log.info("Email Body TextBox visibility status {}" + isVisible);
        return isVisible;
    }


    public void clearTxtBoxForEmailBodySendNoti() {
        clear(txtBoxForEmailBodySendNoti);
        pause(2000);
        log.info("Email Body Textbox field is cleared.");
        ExtentLogger.logInfo("Email Body Textbox field is cleared.");
    }

    public void populateTxtBoxForEmailBodySendNoti(String value) {
        log.info("Populating Email Body TextBox {} :" + value);
        sendKeys(txtBoxForEmailBodySendNoti, value);
        log.info("Email Body TextBox populated successfully with Value " + value);
        ExtentLogger.logInfo("Email Body TextBox populated successfully with Value " + value);
    }



    @FindBy(css = "textarea#txtMessage")
    private WebElement txtBoxForNonEmailBodySendNoti;

    public Boolean isTxtBoxForNonEmailBodySendNotiVisible() {
        boolean isVisible = isElementVisible(txtBoxForNonEmailBodySendNoti);
        log.info("Non-Email Body TextBox visibility status {}" + isVisible);
        return isVisible;
    }

    public void clearTxtBoxForNonEmailBodySendNoti() {
        clear(txtBoxForNonEmailBodySendNoti);
        pause(2000);
        log.info("Non-Email Body Textbox field is cleared.");
        ExtentLogger.logInfo("Non-Email Body Textbox field is cleared.");
    }

    public void waitForTxtBoxForNonEmailBodySendNotiToBeVisible() {
        log.info("Wait for NonEmail Body Textbox visibility on Notification Page.");
        waitForElementToBeVisible(txtBoxForNonEmailBodySendNoti);
    }

    public void populateTxtBoxForNonEmailBodySendNoti(String value) {
        log.info("Populating Non-Email Body TextBox {} :" + value);
        sendKeys(txtBoxForNonEmailBodySendNoti, value);
        log.info("Non-Email Body TextBox populated successfully with Value " + value);
        ExtentLogger.logInfo("Non-Email Body TextBox populated successfully with Value " + value);
    }

    public String getTxtForNonEmailBodySendNoti(){
        String label = getAttribute(txtBoxForNonEmailBodySendNoti,"value");
        log.info("Non-Email TextBox Text is " + label);
        return label;

    }


    @FindBy(css = "#btnBack")
    private WebElement btnBackMail;
    public void clickBtnBackMail() {
        click(btnBackMail);
        log.info("Back Button on Notification Message Details Screen clicked successfully.");
    }

    @FindBy(css = "#btnReply")
    private WebElement btnReplyNotification;
    public void clickBtnReplyNotification() {
        click(btnReplyNotification);
        log.info("Reply Button on Notification Header clicked successfully.");
    }

    @FindBy(xpath = "//div[@class='Attachment-mail']")
    private WebElement SectionAttachmentMail;

    @FindBy(css = ".note-editable")
    private WebElement txtBoxReplyNotification;

    public void clearReplyTextBoxNotification() {
        clear(txtBoxReplyNotification);
        log.info("Reply Notification Textbox field is cleared.");    }

    public void populateReplyTextBoxNotification(String value) {
        log.info("Populating Reply Notification TextBox {} :" + value);
        sendKeys(txtBoxReplyNotification, value);
        log.info("Reply Notification TextBox populated successfully with Value " + value);
    }

    @FindBy(css = "#fileupd")
    private List<WebElement> lstBtnChooseFileMail;

    public Boolean islstBtnChooseFileMailVisible() {
        boolean isVisible = isElementVisibleAlt(lstBtnChooseFileMail);
        log.info("Choose Button visibility status {}" + isVisible);
        return isVisible;
    }

    public void waitForLstBtnChooseFileMailToBeVisible() {
        log.info("Wait for List Choose File Button visibility on Notification Page.");
        waitForElementToBeVisible(btnChooseFileMail);
    }

    @FindBy(css = "#fileupd")
    private WebElement btnChooseFileMail;


    public Boolean isBtnChooseFileMailVisible() {
        boolean isVisible = isElementEnabled(btnChooseFileMail);
        log.info("Choose Button visibility status {}" + isVisible);
        return isVisible;
    }

    public void addAttachmentToChooseFile(String value) {
        sendKeysWithoutCheckingVisibility(btnChooseFileMail, value);
        log.info("Choose File Button added successfully with File Value " + value);
        ExtentLogger.logInfo("Choose File Button added successfully with File Value " + value);
    }

    @FindBy(xpath = "//input[@id='btnSubmitReply']")
    private WebElement btnSendReplyNotification;

    public void waitForSendReplyButtonToBeVisibile() {
        log.info("Wait for Send Reply BUtton visibility on Notification Page.");
        waitForElementToBeVisible(btnSendReplyNotification);
    }
    public void clickBtnSendReplyNotification() {
        click(btnSendReplyNotification);
        log.info("Send Reply Button clicked successfully.");
    }

    @FindBy(css = "#btnDiscard")
    private WebElement btnDiscardSendReply;

    public void clickBtnDiscardSendReply() {
        click(btnDiscardSendReply);
        log.info("Discard Button clicked successfully on Send Reply Page.");
    }

    @FindBy(css = ".Leftheader-Pannel h2")
    private WebElement lblAutoReponseSettingHeading;

    public Boolean isLblAutoReponseSettingHeadingVisible() {
        boolean isVisible = isElementVisible(lblAutoReponseSettingHeading);
        log.info("AutoResponse Setting Heading visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblAutoReponseSettingHeading() {
        String label = getText(lblAutoReponseSettingHeading);
        log.info("AutoResponse Setting label Text is " + label);
        return label;
    }
    @FindBy(css = "#lblAutoRespone")
    private WebElement lblEnableAutoResponse;
    public Boolean isLblEnableAutoResponseVisible() {
        boolean isVisible = isElementVisible(lblEnableAutoResponse);
        log.info("Enable AutoResponse Label visibility status {}" + isVisible);
        return isVisible;
    }
    public String getLblEnableAutoResponse() {
        String label = getText(lblEnableAutoResponse);
        log.info("Enable AutoResponse Setting label is " + label);
        return label;
    }
    @FindBy(css = "input#ContentPlaceHolder1_rightpanel_AutoResp")
    private WebElement chkBoxEnableAutoResponse;

    public Boolean isChkBoxEnableAutoResponseVisible() {
        boolean isVisible = isElementVisible(chkBoxEnableAutoResponse);
        log.info("Enable AutoResponse Checkbox visibility status {}" + isVisible);
        return isVisible;
    }
    public void clickChkBoxEnableAutoResponse() {
        clickElementUsingJsExecutor(chkBoxEnableAutoResponse);
        log.info("Enable AutoResponse Checkbox clicked successfully.");
    }

    public String getEnableCheckBoxStatusAutoResponse() {
        String label = getAttribute(chkBoxEnableAutoResponse,"checked");

        log.info("Enable CheckBox Status is " + label);
        return label;
    }

    @FindBy(css = ".note-editable")
    private WebElement txtBoxHTMLEditorAutoResponse;
    public Boolean isTxtBoxHTMLEditorAutoResponseVisible() {
        boolean isVisible = isElementVisible(txtBoxHTMLEditorAutoResponse);
        log.info("Enable AutoResponse TextBox visibility status {}" + isVisible);
        return isVisible;
    }
    public void clearTxtBoxAutoResponse() {
        clear(txtBoxHTMLEditorAutoResponse);
        log.info("AutoResponse Textbox field is cleared.");
    }
    public void populatetxtBoxAutoResponse(String value) {
        log.info("Populating AutoResponse TextBox {} :" + value);
        sendKeys(txtBoxHTMLEditorAutoResponse, value);
        log.info("AutoResponse TextBox populated successfully with Value " + value);
    }

    public String getValueTxtBoxAutoResponseEditor() {
        String label = getText(btnClearAutoResponse);
        log.info("AutoResponse Editor TextBox is having the value " + label);
        return label;
    }


    @FindBy(css = "input#btnClear")
    private WebElement btnClearAutoResponse;
    public Boolean isBtnClearAutoResponseVisible() {
        boolean isVisible = isElementVisible(btnClearAutoResponse);
        log.info("Clear Button AutoResponse visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblBtnClearAutoResponse() {
        String label = getAttribute(btnClearAutoResponse,"value");
        log.info("Clear Button label on AutoResponse Editor is " + label);
        return label;
    }

    @FindBy(css = "input#SendResponse")
    private WebElement btnSaveAutoResponse;
    public Boolean isBtnSaveAutoResponseVisible() {
        boolean isVisible = isElementVisible(btnSaveAutoResponse);
        log.info("Save Button AutoResponse visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblBtnSaveAutoResponse() {
        String label = getAttribute(btnSaveAutoResponse,"value");
        log.info("Save Button label on AutoResponse Editor is " + label);
        return label;
    }

    public void clickBtnSaveAutoResponse() {
        click(btnSaveAutoResponse);
        log.info("Auto Response Save Button Clicked Successfully {}.");
    }

    public void waitForAutoRespSaveButtonToBeVisible() {
        log.info("Wait for Save Button Visibility on Auto-Response Editor Page.");
        waitForElementToBeVisible(btnSaveAutoResponse);
    }

    @FindBy(css = ".redirect_button")
    private WebElement btnNotificationInboxOnSendNotification;

    public Boolean isBtnNotificationInboxOnSendNotificationVisible() {
        log.info("Checking the visibility of Notification Inbox Button on the Send Notification page.");
        log.info("Notification Inbox Button on the Send Notification visibility status {}: " + isElementVisible(btnNotificationInboxOnSendNotification));
        return isElementVisible(btnNotificationInboxOnSendNotification);
    }

    public String getLblBtnNotificationInboxOnSendNotification() {
        String label = getText(btnNotificationInboxOnSendNotification);
        log.info("Notification Inbox Button Text is " + label);
        return label;
    }

    public void clickBtnNotificationInboxOnSendNotification(){
        click(btnNotificationInboxOnSendNotification);
        log.info("Notification Inbox Button Clicked Successfully {}.");
    }

    @FindBy(css = "aside[role='navigation']")
    private WebElement leftNavigationPanel;

    public String getAttrLeftNavigationPanel() {
        String attrValue = getAttribute(leftNavigationPanel,"style");
        log.info("Left Navigation Panel Attribute Value is " + attrValue);
        return attrValue;
    }


    @FindBy(xpath = "//li[@id='notification']")
    private WebElement lnkNotification;

    @FindBy(css = ".DetailsMessageHeader > div:nth-child(1).from-section")
    private WebElement lblFromMessageDetails;
    public Boolean isLblFromMessageDetailsVisible() {
        log.info("Checking the visibility of From Label on the page.");
        log.info("From Label visibility status {}: " + isElementVisible(lblFromMessageDetails));
        return isElementVisible(lblFromMessageDetails);
    }

    public String getLblValueFromMessageDetails() {
        String label = getText(lblFromMessageDetails);
        log.info("From label Text is " + label);
        return label;
    }

    @FindBy(css = ".DetailsMessageHeader > div:nth-child(2).from-name-section")
    private WebElement fromFieldValueMsgDetail;
    public Boolean isFromFieldValueMsgDetailVisible() {
        log.info("Checking the visibility of From Field Value on the page.");
        log.info("From Field Value visibility status {}: " + isElementVisible(fromFieldValueMsgDetail));
        return isElementVisible(fromFieldValueMsgDetail);
    }

    public String getFromFieldValueMsgDetail() {
        String label = getText(fromFieldValueMsgDetail);
        log.info("From Field Value is " + label);
        return label;
    }

    public String getFromFieldValueForRepliedRequestID(String requestID){
        String label =getText(By.xpath("//span[@class='msgSubject'][contains(text(),'"+requestID+"')]/preceding::div[@class='FromContainer'][1]"));
        log.info("From Field label for Replied Notification is " + label);
        return label;
    }

    public void clickFromFieldValueForRepliedRequestID(String requestID){
        click(By.xpath("//span[@class='msgSubject'][contains(text(),'"+requestID+"')]/preceding::div[@class='FromContainer'][1]"));
        log.info("From Field label for Replied Notification is clicked successfully.");
    }


    @FindBy(css = ".DetailsMessageContainer div:nth-child(4) .DetailsMessageHeader .from-section:nth-of-type(4)")
    private WebElement lblTopicMsgDetail;

    @FindBy(css = ".DetailsMessageContainer.message-body-section > div:nth-of-type(3) > .DetailsMessageHeader > div:nth-of-type(5)")
    private WebElement lblTopicValueMsgDetail;

    @FindBy(css = ".DetailsMessageHeader div:nth-child(4).from-section")
    private WebElement lblSubjectMsgDetails;
    public Boolean isLblSubjectMessageDetailsVisible() {
        log.info("Checking the visibility of Subject Label on the page.");
        log.info("Subject Label visibility status {}: " + isElementVisible(lblSubjectMsgDetails));
        return isElementVisible(lblSubjectMsgDetails);
    }

    public String getLblSubjectMessageDetails() {
        String label = getText(lblSubjectMsgDetails);
        log.info("Subject label Text is " + label);
        return label;
    }

    @FindBy(css = ".DetailsMessageHeader div:nth-child(5).subject")
    private WebElement lblSubjectValueMsgDetail;
    public Boolean isSubjectFieldValueMsgDetailVisible() {
        log.info("Checking the visibility of Subject Field Value on the page.");
        log.info("Subject Field Value visibility status {}: " + isElementVisible(lblSubjectValueMsgDetail));
        return isElementVisible(lblSubjectValueMsgDetail);
    }

    public String getSubjectFieldValueMsgDetail() {
        String label = getText(lblSubjectValueMsgDetail);
        log.info("Subject Field Value is " + label);
        return label;
    }

    @FindBy(css = ".DetailsMessageHeader > div:nth-child(10).from-section")
    private WebElement lblDateMsgDetail;
    public Boolean isLblDateMessageDetailsVisible() {
        log.info("Checking the visibility of Date Label on the page.");
        log.info("Date Label visibility status {}: " + isElementVisible(lblDateMsgDetail));
        return isElementVisible(lblDateMsgDetail);
    }

    public String getLblDateMessageDetails() {
        String label = getText(lblDateMsgDetail);
        log.info("Date label Text is " + label);
        return label;
    }

    @FindBy(css = ".DetailsMessageHeader > div:nth-child(11).from-name-section")
    private WebElement lblDateValueMsgDetail;

    public void waitForDateFieldValueMsgDetailToBeVisible() {
        log.info("Wait for Date Field Value visibility on Notification Details Page.");
        waitForElementToBeVisible(lblDateValueMsgDetail);
    }

    public Boolean isDateFieldValueMsgDetailVisible() {
        log.info("Checking the visibility of Date Field Value on the page.");
        log.info("Date Field Value visibility status {}: " + isElementVisible(lblDateValueMsgDetail));
        return isElementVisible(lblDateValueMsgDetail);
    }

    public String getDateFieldValueMsgDetail() {
        String label = getText(lblDateValueMsgDetail);
        log.info("Date Field Value is " + label);
        return label;
    }

    @FindBy(css = ".DetailsMessageHeader > div:nth-child(7).from-section")
    private WebElement lblMessageMsgDetail;
    public Boolean isLblMessageMsgDetailsVisible() {
        log.info("Checking the visibility of Message Label on the page.");
        log.info("Message Label visibility status {}: " + isElementVisible(lblMessageMsgDetail));
        return isElementVisible(lblMessageMsgDetail);
    }

    public String getLblMessageMsgDetails() {
        String label = getText(lblMessageMsgDetail);
        log.info("Message label Text is " + label);
        return label;
    }

    @FindBy(css = ".DetailsMessageHeader > div:nth-child(8).from-name-section")
    private WebElement lblMessageValueMsgDetail;
    public Boolean isMessageFieldValueMsgDetailVisible() {
        log.info("Checking the visibility of Message Field Value on the page.");
        log.info("Message Field Value visibility status {}: " + isElementVisible(lblMessageValueMsgDetail));
        return isElementVisible(lblMessageValueMsgDetail);
    }

    public String getMessageFieldValueMsgDetail() {
        String label = getText(lblMessageValueMsgDetail);
        log.info("Message Field Value is " + label);
        return label;
    }


    @FindBy(css = ".DetailsMessageContainer.message-body-section > div:nth-of-type(3) > div:nth-of-type(4)")
    private WebElement lblAttachmentMsgDetail;

    @FindBy(css = ".Attachment-mail")
    private WebElement lnkAttachmentMsgDetail;

    @FindBy(css = "#nodata")
    private WebElement lblNoDataInModule;

    public Boolean isLblNoDataInModuleVisible() {
        log.info("Checking the visibility of NoDataInModule Label on the page.");
        log.info("No Data Label on Module visibility status {}: " + isElementVisible(lblNoDataInModule));
        return isElementVisible(lblNoDataInModule);
    }

    public String getLblNoDataInModuleDetail() {
        String label = getText(lblNoDataInModule);
        log.info("No Data Label is " + label);
        return label;
    }
    @FindBys(@FindBy(css = "#nodata"))
    private List<WebElement> lstLblNoDataInModule;

    public List<WebElement> getLstLblNoDataInModule() {
        return lstLblNoDataInModule;
    }
    public Boolean isLstLblNoDataInModuleVisible() {
        log.info("Checking the visibility of NoDataInModule Label on the page.");
        log.info("No Data Label on Module visibility status {}: " + isElementVisibleAlt(lstLblNoDataInModule));
        return isElementVisibleAlt(lstLblNoDataInModule);
    }

    @FindBy(xpath = "//div[@class='toast-message']")
    private WebElement lblToastMsg;

    @FindBy(css = "#lblFileAllowExtension")
    private WebElement lblAllowedFileTypes;

    public void waitForLblAllowedFileTypesToBeVisible() {
        log.info("Wait for Allowed File Types Label visibility on Notification Page.");
        waitForElementToBeVisible(lblAllowedFileTypes);
    }
    public Boolean isLblAllowedFileTypesVisible() {
        log.info("Checking the visibility of AllowedFileTypes Label on the page.");
        log.info("AllowedFileTypes Label  visibility status {}: " + isElementVisible(lblAllowedFileTypes));
        ExtentLogger.logInfo("AllowedFileTypes Label  visibility status {}: " + isElementVisible(lblAllowedFileTypes));
        return isElementVisible(lblAllowedFileTypes);
    }

    public String getLblAllowedFileTypes() {
        String label = getText(lblAllowedFileTypes);
        log.info("AllowedFileTypes Label is " + label);
        ExtentLogger.logInfo("AllowedFileTypes Label is " + label);
        return label;
    }


    @FindBy(css = "#spanTxt")
    private WebElement lblMaxCharsBodyAllowed;

    public String getLblMaxCharsBodyAllowedLbl() {
        String label = getTextWithoutCheckingVisibility(lblMaxCharsBodyAllowed);
        log.info("Max character Allowed Field label is " + label);
        return label;
    }



    @FindBy(css = ".btnputback")
    private WebElement btnPutBack;

    public void waitForBtnPutBackToBeVisible() {
        log.info("Wait for PutBack Button visibility on Notification Page.");
        waitForElementToBeVisible(btnPutBack);
    }
    public Boolean isBtnPutBackVisible() {
        log.info("Checking the visibility of PutBack Button on the Trash page Header.");
        log.info("PutBack button visibility status on Trash page Header{}: " + isElementVisible(btnPutBack));
        return isElementVisible(btnPutBack);
    }

    public void clickBtnPutBack() {
        click(btnPutBack);
        log.info("Put Back Button clicked {}.");
    }


    @FindBy(css = "#legends b")
    private WebElement lblNotificationCountPagination;

    public String getLblNotificationCountPagination() {
        String label = getTextWithoutCheckingVisibility(lblNotificationCountPagination);
        log.info("Notifications Pagination label is " + label);
        return label;
    }

    public void waitForLblNotificationCountPaginationToBeVisible() {
        log.info("Wait for Pagination Label visibility on Notification Page.");
        waitForElementToBeVisible(lblNotificationCountPagination);
    }

    @FindBy(xpath = "//input[@id='btnNext']")
    private WebElement btnNextMsg;

    @FindBy(xpath = "//input[@id='btnPrevious']")
    private WebElement btnPreviousMsg;

    @FindBy(xpath = "//textarea[@id='txtMessage']")
    private WebElement txtBoxTextMessageSendNoti;

    @FindBy(css = "#btnRemoveFile")
    private WebElement iconRemoveFile;

    public Boolean isIconRemoveFileVisible() {
        boolean isVisible = isElementVisible(iconRemoveFile);
        log.info("Remove File Icon visibility status {}" + isVisible);
        return isVisible;
    }

    @FindBy(css = "#nofile")
    private WebElement lblFileName;

    public String getlblFileName() {
        String label = getText(lblFileName);
        log.info("File Name Label is " + label);
        return label;
    }

    @FindBy(css = "#btnSortFrom")
    private WebElement fromGridSortIcon;

    public void clickFromGridSortIcon() {
        click(fromGridSortIcon);
        log.info("From Grid Sort Icon clicked {}.");
    }


    @FindBy(css = "#btnSubjectSort")
    private WebElement subjectGridSortIcon;
    
    public void clickSubjectGridSortIcon() {
        click(subjectGridSortIcon);
        log.info("Subject Grid Sort Icon clicked {}.");
    }

    @FindBy(css = "#btnDateSort")
    private WebElement dateGridSortIcon;

    public void clickSubjectSortIcon() {
        click(dateGridSortIcon);
        log.info("Date Grid Sort Icon clicked {}.");
    }

    @FindBys(@FindBy(css = ".sortingIco"))
    private List<WebElement> lstNotificationSortGridHeaders;

    public List<WebElement> getLstNotificationSortGridHeaders() {
        return lstNotificationSortGridHeaders;
    }
    public void clickLstNotificationSortGridHeaders(int i){
        click(lstNotificationSortGridHeaders.get(i));
        log.info("Clicked on Notification Grid Headers List at the Index "+i);
    }

    @FindBy(css=".switch > .slider.round")
    private WebElement tglBtnUnReadAllNotify;
    public void clickTglBtnUnReadAllNotify(){
        click(tglBtnUnReadAllNotify);
        log.info("Toggle Button for Unread or All Notification is clicked successfully.");
    }





}

package demo.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sew.ai.pageObjects.scp.HomePage;

public class NotificationsPage extends HomePage {
	private static final Logger log = LogManager.getLogger(NotificationsPage.class);

	public NotificationsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id='NotificationAlertCount']")
	private WebElement btnNotification;

	public void clickBtnNotification() {
		click(btnNotification);
		log.info("Notofication button has been Successfully clicked");
	}

	@FindBy(css = "h1#PageTitle")
	private WebElement lblPageHeading;

	public boolean isPageTitleVisible() {
		log.info("Page Title visibility status : " + lblPageHeading.isDisplayed());
		return isElementVisible(lblPageHeading);
	}

	public String getPageTitleLabel() {
		String label = getText(lblPageHeading);
		log.info("User Name Label  is : " + label);
		return label;
	}

	@FindBy(css = "span[globalize = 'ML_Set_Notification_Preferences']")
	private WebElement btnSetNotificationPreferences;

	public void clickSetNotificationPreferenceButton() {
		click(btnSetNotificationPreferences);
		log.info("Set Notofication Preference button has been Successfully clicked");
	}

	@FindBy(css = "[id='btnSave']")
	private WebElement btnStarSave;

	public void clickStarSaveButton() {
		click(btnStarSave);
		log.info("Star save Button Successfully clicked.");
	}

	public boolean isStarSaveVisible() {
		log.info("Star visibility status : " + btnStarSave.isDisplayed());
		return isElementVisible(btnStarSave);
	}

	public boolean isStarSaveNotVisible() {
		log.info("Star visibility status : " + btnStarSave.isDisplayed());
		return isElementNotVisible(btnStarSave);
	}

	@FindBy(css = "section >.btn-group [data-original-title = 'Delete']")
	private WebElement btnDelete;

	public void clickDeleteButton() {
		click(btnDelete);
		log.info("Delete Button Successfully clicked.");
	}

	public boolean isDeleteButtonVisible() {
		log.info("Delete visibility status : " + btnDelete.isDisplayed());
		return isElementVisible(btnDelete);
	}

	public boolean isDeleteButtonNotVisible() {
		log.info("Delete visibility status : " + btnDelete.isDisplayed());
		return isElementNotVisible(btnDelete);
	}

	@FindBy(css = "section >.btn-group [data-original-title = 'Refresh']")
	private WebElement btnRefresh;

	public void clickRefereshButton() {
		click(btnRefresh);
		log.info("Notification inbox got Refreshed.");
	}

	public boolean isRefreshButtonVisible() {
		log.info("Refresh visibility status : " + btnRefresh.isDisplayed());
		return isElementVisible(btnRefresh);
	}

	public boolean isRefreshButtonNotVisible() {
		log.info("Refresh visibility status : " + btnRefresh.isDisplayed());
		return isElementNotVisible(btnRefresh);
	}

	@FindBy(xpath = "//label[contains(text(),'Checkbox All')]")
	private WebElement btnAllDropdownLst;

	public void checkAllNotificationEmail() {
		click(btnAllDropdownLst);
		log.info("All Notification inbox got Checked.");
	}

	public boolean isAllDropdownLstVisible() {
		log.info("Dro pdown List visibility status : " + btnAllDropdownLst.isDisplayed());
		return isElementVisible(btnAllDropdownLst);
	}

	public boolean isAllDropdownLstNotVisible() {
		log.info("Dro pdown List visibility status : " + btnAllDropdownLst.isDisplayed());
		return isElementNotVisible(btnAllDropdownLst);
	}

	@FindBy(css = ".chk-all li>a")
	private WebElement lstItemsSelectNotifyDd;
	
	@FindBy(css = ".chk-all div>input[id ='chkall']")
	private WebElement chkBoxInputFieldSelectAll;
	
	
	
	@FindBy(css = ".chk-all label[for ='chkall']")
	private WebElement chkBoxLblFieldSelectNotifyDd;
	
	public void clickAllCheckBox() {
		click(chkBoxLblFieldSelectNotifyDd);
		log.info("All Checkbox clicked successfully.");
	}
	
	@FindBy(css = "#legends b")
	private WebElement lblRecordPagination;

	public String getPaginationLabel() {
		scrollToElement(lblRecordPagination);
		log.info("Fetching the label on Notification page.");
		String label = getText(lblRecordPagination);
		log.info("Pagination placeholder {}: " + label);
		return label;
	}
	
	@FindBy(css = "#legends b")
	private WebElement lblRecordPagination1;

	public String getPaginationLabel1() {
		scrollToElement(lblRecordPagination1);
		log.info("Fetching the label on Notification page.");
		String label = getText(lblRecordPagination1);
		log.info("Pagination placeholder {}: " + label);
		return label;
	}
	
	

	@FindBy(css = "#left")
	private WebElement btnPreviousPagination;
	@FindBy(css = "#right")
	private WebElement btnNextPagination;

	@FindBy(css = ".nav_left li[id = 'inbox']")
	private WebElement lnkInboxSideFolder;

	public void clickInboxSideFolder() {
		click(lnkInboxSideFolder);
		log.info("Inbox Side Folder Successfully clicked.");
	}

	public boolean isInboxSideFolderVisible() {
		log.info("Inbox Side Folder visibility status : " + lnkInboxSideFolder.isDisplayed());
		return isElementVisible(lnkInboxSideFolder);
	}

	@FindBy(css = ".nav_left li[id = 'inbox'] span#lblInbox")
	private WebElement lblInboxSideFolder;

	public String getInboxUnreadMsg() {
		log.info("Fetching the Number of Unread Msg in Notification Inbox Folder.");
		String label = getText(lblInboxSideFolder);
		log.info("The Number of unread msg in Notification inbox folder is {}: " + label);
		return label;
	}

	@FindBy(css = ".nav_left li[id = 'inbox'] span")
	private WebElement lblUnreadInboxMsgSideFolder;
	@FindBy(css = ".nav_left li[id = 'outage']")
	private WebElement lnkOutageSideFolder;
	@FindBy(css = "li:nth-of-type(2) > a > .checkbox > input")
	private WebElement lblOutageSideFolder;

	public void selectFolderByOutage() {
		selectByValue(lblOutageSideFolder, "Outages");
		log.info("Outages Folder has been Selected");
	}

	@FindBy(css = ".nav_left li[id = 'outage'] #lblOutage")
	private WebElement lblUnreadOutageMsgOutageSideFolder;
	@FindBy(css = ".nav_left li[id = 'billing']")
	private WebElement lnkBillingSideFolder;
	@FindBy(css = "li:nth-of-type(3) > a > .checkbox > input")
	private WebElement lblBillingSideFolder;

	public void selectFolderByBilling() {
		selectByValue(lblOutageSideFolder, "Billing");
		log.info("Billing Folder has been Selected");
	}

	@FindBy(css = ".nav_left li[id = 'billing'] #lblBilling")
	private WebElement lblUnreadBillingMsgBillingSideFolder;
	@FindBy(css = ".nav_left li[id = 'service']")
	private WebElement lnkServiceSideFolder;
	@FindBy(css = "li:nth-of-type(4) > a > .checkbox > input")
	private WebElement lblServiceSideFolder;

	public void selectFolderByServiceSide() {
		selectByValue(lblOutageSideFolder, "Services");
		log.info("Service Side Folder has been Selected");
	}

	@FindBy(css = ".nav_left li[id = 'service'] #lblService")
	private WebElement lblUnreadServiceMsgServiceSideFolder;
	@FindBy(css = ".nav_left li[id = 'connectme']")
	private WebElement lnkConnectMeSideFolder;
	@FindBy(css = "li:nth-of-type(5) > a > .checkbox > input")
	private WebElement lblConnectMeSideFolder;

	public void selectFolderByConnectMeSide() {
		selectByValue(lblOutageSideFolder, "ConnectMe");
		log.info("Connect Me Folder has been Selected");
	}

	@FindBy(css = ".nav_left li[id = 'connectme'] #lblConnectme")
	private WebElement lblUnreadConnectMeMsgSideFolder;
	@FindBy(css = ".nav_left li[id = 'demandresponse']")
	private WebElement lnkDemandResponseSideFolder;
	@FindBy(css = "li:nth-of-type(6) > a > .checkbox > input")
	private WebElement lblDemandResponseSideFolder;

	public void selectFolderByDemandResponseSide() {
		selectByValue(lblOutageSideFolder, "Demand Response");
		log.info("Demand Response Folder has been Selected");
	}

	@FindBy(css = ".nav_left li[id = 'demandresponse'] #lbldemandresponse")
	private WebElement lblUnreadDemandResponseMsgSideFolder;
	@FindBy(css = ".nav_left li[id = 'leakalert']")
	private WebElement lnkLeakAlertSideFolder;
	@FindBy(css = "li:nth-of-type(7) > a > .checkbox > input")
	private WebElement lblLeakAlertSideFolder;

	public void selectFolderByLeakageAlert() {
		selectByValue(lblOutageSideFolder, "Leak Alert");
		log.info("Leak Alert Folder has been Selected");
	}

	@FindBy(css = ".nav_left li[id = 'leakalert'] #lblleakalert")
	private WebElement lblUnreadLeakAlertMsgSideFolder;
	@FindBy(css = ".nav_left li[id = 'sentitem']")
	private WebElement lnkSentItemSideFolder;

	public boolean isSentSideFolderVisible() {
		log.info("Star visibility status : " + lnkSentItemSideFolder.isDisplayed());
		return isElementVisible(lnkSentItemSideFolder);
	}

	public boolean isSentlinkVisible() {
		log.info("Checking the Visibility of Sent Link on Right Side .");
		return isElementVisible(lnkSentItemSideFolder);
	}

	public void clickSentLink() {
		click(lnkSentItemSideFolder);
		log.info("Click on sent link Successfully .");
	}

	@FindBy(css = ".nav_left li[id = 'sentitem'] span")
	private WebElement lblSentItemSideFolder;

	public String getlblSentItem() {
		log.info("Fetching the lbl No Msg Available Error message");
		String label = getText(lblSentItemSideFolder);
		log.info("lbl Available message {}: " + label);
		return label;
	}

	@FindBy(css = ".nav_left li[id = 'saved']")
	private WebElement lnkSavedSideFolder;

	public boolean isSavedSideFolderVisible() {
		log.info("Checking the Visibility of Saved Link on Right Side .");
		return isElementVisible(lnkSavedSideFolder);
	}

	public void clickSavedLink() {
		click(lnkSavedSideFolder);
		log.info("Click on saved link Successfully .");
	}

	public String getlblSavedSideFolder() {
		log.info("Fetching the lbl save Folder");
		String label = getText(lnkSavedSideFolder);
		log.info("lbl save Side Folder message {}: " + label);
		return label;
	}

	@FindBy(css = "#DeleteNotificationMail .modal-content")
	private WebElement divDeleteMailConfirmPop;
	@FindBy(css = "#DeleteNotificationMail .modal-body")
	private WebElement lblDeleteMailConfirmPopUpMsg;
	@FindBy(css = "#btnDeleteNotificationMail")
	private WebElement btnContinueDeleteMailConfirmPopUpMsg;
	public void clickDeleteContinueButton() {
		click(btnContinueDeleteMailConfirmPopUpMsg);
		log.info("Click on saved link Successfully .");
	}

	@FindBy(css = "#DeleteNotificationMail .btn-danger:nth-child(1)")
	private WebElement btnCancelDeleteMailConfirmPopUpMsg;
	@FindBy(css = ".nav_left li[id = 'saved'] span")
	private WebElement lblSavedSideFolder;
	@FindBy(css = ".nav_left li[id = 'trash']")
	private WebElement lnkTrashSideFolder;

	@FindBy(css = ".nav_left li[id = 'trash'] span")
	private WebElement lblTrashSideFolder;

	public String getlblTrashSideFolder() {
		log.info("Fetching the lbl TrashSide Folder");
		String label = getText(lblTrashSideFolder);
		log.info("lbl Trash Side Folder message {}: " + label);
		return label;
	}

	public boolean isTrashSideFolderVisible() {
		log.info("Checking the Visibility of Trash Side Folder Link on Right Side .");
		return isElementVisible(lblTrashSideFolder);
	}

	public void clickTrashSideFolder() {
		click(lblTrashSideFolder);
		log.info("Click on Trash Side Folder link Successfully .");
	}

	@FindBy(css = ".nav_left li[id = 'allmail']")
	private WebElement lnkAllMailSideFolder;

	public boolean isAllMailSideFolderVisible() {
		log.info("Checking the Visibility of Trash Side Folder Link on Right Side .");
		return isElementVisible(lblTrashSideFolder);
	}

	public void clickAllMailSideFolder() {
		click(lnkAllMailSideFolder);
		log.info("Click on Trash Side Folder link Successfully .");
	}

	@FindBy(css = ".nav_left li[id = 'allmail'] span")
	private WebElement lblAllMailSideFolder;

	public String getlblAllMailFolder() {
		log.info("Fetching the All Mail Folder");
		String label = getText(lblAllMailSideFolder);
		log.info("lbl All Mail Folder message {}: " + label);
		return label;
	}

	@FindBy(css = "#nodata")
	private WebElement lblNoMsgAvailable;

	public boolean isNoMsgAvailableVisible() {
		log.info("Checking the Visibility of No Msg");
		return isElementVisible(lblNoMsgAvailable);
	}
		
		public String getNoMessage() {
			String label = getText(lblNoMsgAvailable);
			return label;
	}

	public String getlblNoMsgAvailable() {
		log.info("Fetching the lbl No Msg Available Error message");
		String label = getText(lblNoMsgAvailable);
		log.info("lbl No Msg Available message {}: " + label);
		return label;
	}

	@FindBy(css = "#ddldropdown #dLabel")
	private WebElement lstBoxAccountAddress;

	@FindBy(css = ".select_chech-box input[type ='checkbox']~label")
	private WebElement chkBoxMailTable;
	@FindBy(css = ".select_chech-box input[type ='checkbox']")
	private WebElement chkBoxInputMailTable;
	@FindBy(css = ".SaveImageContainer img")
	private WebElement btnSaveStarMailTable;
	@FindBy(css = ".liclick .select_from span:nth-child(1)")
	private WebElement lblFromMailTable;
	@FindBy(css = ".select_from>span.ucaccount")
	private WebElement lblAccountNumberMailTable;
	@FindBy(css = ".select_counter>span")
	private WebElement lblMailCategoryMailTable;
	@FindBy(css = ".liclick .select_subject span:nth-child(1)")
	private WebElement lblMailSubjectMailTable;
	@FindBy(css = "li .select_subject > span:nth-of-type(1)")
	private WebElement lblMailBodyMailTable;
	@FindBy(css = ".liclick .select_date")
	private WebElement lblMailDateMailTable;
	@FindBy(css = ".select_atachment>i")
	private WebElement attachmentMailTable;
	@FindBy(xpath = "//ul[@class='MailListing']//li[contains(@id,'lirowdiv')]//span[@class='msgSubject']")
	private WebElement lstSubjectMailTable;

	public void clickSubjectOfRequestId(String requestID) {
		click(By.xpath("//li[@class='unread']//span[@class='msgSubject'][contains(text(),'" + requestID + "')]"));
		log.info("Clicked on the Subject line for the RequestID " + requestID);
	}

	@FindBy(css = "#btnBack")
	private WebElement btnBackOpenMail;
	
	public boolean isBackButtonVisible() {
		return isElementVisible(btnBackOpenMail);
	}
	@FindBy(css = "#subj>span:nth-child(1)")
	private WebElement lblSubjectOpenMail;
	
	public boolean isSubjectVisible() {
		return isElementVisible(lblSubjectOpenMail);
	}
	
	@FindBy(css = "#subj span:nth-child(3)")
	private WebElement lblServiceAccountNumOpenMail;
	
	public boolean isServiceAccountNumVisible() {
		return isElementVisible(lblServiceAccountNumOpenMail);
	}
	
	@FindBy(css = "#subj span:nth-child(4)")
	private WebElement lblAccountNumValueOpeMail;
	
	public boolean isAccountNumVisible() {
		return isElementVisible(lblAccountNumValueOpeMail);
	}
	
	
	@FindBy(css = "#btnsReply")
	private WebElement btnReplyOpeMail;
	
	public void clickReplyButton() {
		click(btnReplyOpeMail);
		log.info("Reply Button clicked successfully.");
	}
	
	@FindBy(css = "button.btnDelete")
	private WebElement btnDeleteOpeMail;
	@FindBy(css = "button.btnsave")
	private WebElement btnSaveStarOpeMail;
	public boolean isSaveButtonVisible() {
		return isElementVisible(btnSaveStarOpeMail);
	}
	
	@FindBy(css = "p.details-sender>strong")
	private WebElement lblFromOpeMail;
	
	public boolean isFromOptionVisible() {
		return isElementVisible(lblFromOpeMail);
	}
	@FindBy(css = "p.details-sender>span")
	private WebElement lblFromIdValueOpeMail;
	
	public boolean isFromValueVisible() {
		return isElementVisible(lblFromIdValueOpeMail);
	}
	@FindBy(css = "p.date")
	private WebElement lblDateOpeMail;
	
	public boolean isDateVisible() {
		return isElementVisible(lblDateOpeMail);
	}
	
	@FindBy(css = ".view-mail")
	private WebElement lblMailBodyOpeMail;
	
	public boolean isMailBodyVisible() {
		return isElementVisible(lblMailBodyOpeMail);
	}

	@FindBy(css = ".reply_msg_wrap .DetailsMessageContainer div.view-mail")
	private WebElement txtRepliedNotificationBody;
	
	public boolean isReplyMailBodyVisible() {
		return isElementVisible(txtRepliedNotificationBody);
	}

	public void waitForTxtRepliedNotificationBodyToBeVisible() {
		log.info("Wait for Replied Notification Body visibility on Notification Page.");
		waitForElementToBeVisible(txtRepliedNotificationBody);
	}

	public String getTxtRepliedNotificationBody() {
		String label = getText(txtRepliedNotificationBody);
		log.info("Replied Notification Body Text: " + label);
		return label;
	}

	@FindBy(css = "a.liclick #mailCategory0 span")
	private WebElement lnkNotificationCategary;
	
	public void clickNotification() {
		click(lnkNotificationCategary);
		log.info("Notification clicked successfully.");
	}

	@FindBy(css = "#btnSubmitReply")
	private WebElement btnReplySendReplyMail;
	@FindBy(css = "#btnDiscard")
	private WebElement btnDiscardReplyMail;
	@FindBy(css = "#btnDiscard ~ div")
	private WebElement btnDeleteReplyMail;
	@FindBy(css = "span[globalize='ML_Notification_ComposeMail']")
	private WebElement lblComposeMailReplyMail;
	@FindBy(css = "a[class='SaveImageContainer'] img")
	private WebElement savedMailElements;
	@FindBy(css = "label[for='lblToUser']")
	private WebElement lblToUserTxtReplyMail;
	@FindBy(css = "input#lblToUser")
	private WebElement txtToUserValueReplyMail;
	@FindBy(css = "div.note-toolbar.panel-heading")
	private WebElement lblComposeMailToolBarReplyMail;
	
	@FindBy(css = "div.note-editable")
	private WebElement txtBoxMailEditableAreaReplyMail;
	
	public boolean isTxtBoxMessageBodyVisible() {
    	scrollToElement(txtBoxMailEditableAreaReplyMail);
    	log.info("Waiting For Visiblity status of MessageBody Text Feild");
    	waitForElementToBeVisible(txtBoxMailEditableAreaReplyMail);
    	log.info("Visiblity status of MessageBody Text Feild:" +txtBoxMailEditableAreaReplyMail.isDisplayed());
    	return txtBoxMailEditableAreaReplyMail.isDisplayed();
    	}
    
    public void populateTxtMessageBody(String Text) {
    	log.info("Waiting For Visiblity status of MessageBody Text Feild");
    	waitForElementToBeVisible(txtBoxMailEditableAreaReplyMail);
    	log.info("Visiblity status of MessageBody Text Feild:"+ txtBoxMailEditableAreaReplyMail.isDisplayed());
    	log.info("Populating Zip Code feild with" + Text);
    	clear(txtBoxMailEditableAreaReplyMail);
    	sendKeys(txtBoxMailEditableAreaReplyMail,Text);
    	}
    
    public WebElement elementTextMessageBody() {
       	log.info("Waiting For Visiblity status of Confirm Routing Number Text Feild");
    	waitForElementToBeVisible(txtBoxMailEditableAreaReplyMail);
    	log.info("Visiblity status of Confirm Routing Number Text Feild:"+ txtBoxMailEditableAreaReplyMail.isDisplayed());
    	return txtBoxMailEditableAreaReplyMail;
    	}
	
	
	@FindBy(css = "span[globalize= 'ML_SrvcRqust_ChooseF']")
	private WebElement btnChooseFileReplyMail;
	@FindBy(css = "#fileupd")
	private WebElement inputChooseFileReplyMail;
	@FindBy(css = "i.fa.fa-paperclip")
	private WebElement lblChooseFileNameReplyMail;
	// @FindBy (css= "img[id*='btnRemoveFile0']")
	// private WebElement btnRemoveAttachedFileReplyMail;
	@FindBy(css = "img[id*='btnRemoveFile']")
	private WebElement btnRemoveAttachedFileReplyMail;
	@FindBy(css = "#btnRemoveFile1")
	private WebElement btnRemoveAttachedFileRep;
	@FindBy(css = "div#msgReply > span#lblFileAllowExtension")
	private WebElement lblFileExtensionsAllowedReplyMail;
	@FindBy(css = ".Attachmenttext>a")
	private WebElement btnSentMailAttachmentReplyMail;
	
	@FindBy(css = "a#btnputback img")
	private WebElement btnPutBackTrash;
	
	@FindBy(css = "#btnputback")
	private WebElement btnPutBack;
	
	public void clickPutBackIcon() {
		click(btnPutBack);
		log.info("Put Back icon clicked successfully.");
	}
	
	
	@FindBy(css = "div.pull-left.mail-checkbox")
	private WebElement chkBoxAll;
	
	@FindBy(css = "#lblInbox")
	private WebElement lblInboxCount;
	
	public String getCountInbox() {
		String label = getText(lblInboxCount);
		return label;
}
	
	@FindBy(css = "#notificationBadgeCount")
	private WebElement lblNotificationCount;
	
	public String getCountNotifications() {
		String label = getText(lblNotificationCount);
		return label;
}
	
	
	@FindBy(css = ".MailListing li:first-child .select_subject span:nth-child(2)")
	private WebElement lblNotificationGridFirstNotifyComment;
	@FindBy(css = ".MailListing li:nth-child(2) .select_subject .msgSubject")
	private WebElement lblNotificationGridFirstNotifySub;
	@FindBy(css = ".nav_left .sidebar_sent")
	private WebElement sideMenuSent;
	@FindBy(css = ".nav_left .sidebar_sent a span")
	private WebElement lnkSentNotification;
	@FindBy(css = "#lblService")
	private WebElement lblServiceCount;
	@FindBy(css = "#lblConnectme")
	private WebElement lblConnectMeCount;

	@FindBy(css = ".nav_left .sidebar_all-mail a")
	private WebElement lnkAllEmailNotifications;
	@FindBy(css = ".table-inbox-wrap .MailListing li:first-child")
	private WebElement lnkFirstNotification;
	@FindBy(css = ".pull-left span span")
	private WebElement lblNotificationSubject;
	@FindBy(css = ".MailListing li:first-child .select_chech-box")
	private WebElement chkBoxNotificationGridFirstNotification;
	@FindBy(css = ".MailListing li:nth-child(2) .select_chech-box")
	private WebElement chkBoxNotificationGridSecondNotification;
	@FindBy(css = ".MailListing li:first-child .SaveImageContainer img")
	private WebElement imgStarHighLightFirst;
	@FindBy(css = ".MailListing li:nth-child(2) .SaveImageContainer img")
	private WebElement imgStarHighLightSecond;
	@FindBy(css = ".MailListing li:first-child .SaveImageContainer")
	private WebElement lnkNotificationGridFirstNotificationStar;
	@FindBy(css = ".MailListing li:first-child .select_from span:first-child")
	private WebElement lblNotificationGridFirstNotificationEmail;
	@FindBy(css = ".MailListing li:first-child .select_counter span")
	private WebElement lblNotificationGridFirstNotificationType;

	@FindBy(css = ".Attachmenttext a")
	private WebElement lblNotificationGridFirstNotificationAttachment;

	public String getLblNotificationGridFirstNotificationAttachmentLabel() {
		String label = getText(lblNotificationGridFirstNotificationAttachment);
		log.info("Current balance label is " + label);
		return label;
	}

	@FindBy(css = ".MailListing li:first-child .select_date")
	private WebElement lblNotificationGridFirstNotificationDate;
	@FindBy(css = ".row .pull-right .rp-btn")
	private WebElement btnNotificationGridFirstNotificationReplyButton;
	
	@FindBy(css = ".row .pull-right .fa-trash-o")
	private WebElement btnNotificationDeleteButton;
	
	public boolean isNotificationDeleteButtonVisible() {
		return isElementVisible(btnNotificationDeleteButton);
	}
	
	public void clickTrashIcon() {
		click(btnNotificationDeleteButton);
		log.info("Notification clicked successfully.");
	}
	
	@FindBy(css = ".BacktoInbox_flat_ico .BacktoInbox")
	private WebElement btnNotificationGridFirstNotificationBackButton;
	@FindBy(css = ".nav_left li:not([style='display:none;']) a")
	private WebElement lstLnkNotificationSideMenu;
	@FindBy(css = ".nav_left li:not([style='display:none;']) a span:not(.inbox-notification)")
	private WebElement lstLblNotificationSideMenu;
	@FindBy(css = ".Nodatadiv")
	private WebElement lblNoNotificationInSelectedTab;
	@FindBy(css = ".inbox-pagination li span")
	private WebElement lblTotalNotificationsInInbox;
	@FindBy(css = "[id='lblInbox']")
	private WebElement lblTotalUnReadNotificationsInInbox;

	// todo @FindBy (css= ".nav_left li:not([style='display:none;']) a.active
	// .inbox-notification")
	// todo private WebElement lblUnReadNotificationInOtherTabs;/
	@FindBy(css = ".nav_left li.active:not([style='display:none;']) a .inbox-notification")
	private WebElement lblUnReadNotificationInOtherTabs;
	@FindBy(css = ".MailListing li .select_counter span")
	private WebElement lstInboxNotifications;
	@FindBy(css = ".np-btn .pagination-right")
	private WebElement btnInboxPaginationRightButton;
	@FindBy(css = ".np-btn .pagination-left")
	private WebElement btnInboxPaginationLeftButton;
	@FindBy(css = ".MailListing li")
	private WebElement lstTotalNotificationsCountInInbox;
	@FindBy(css = ".MailListing li.unread")
	private WebElement lstUnreadNotifications;
	@FindBy(css = ".MailListing li:not(.unread)")
	private WebElement lstReadNotifications;
	@FindBy(css = ".w2ui-popup-buttons button:first-child")
	private WebElement btnNotificationPopupDelete;
	@FindBy(css = ".w2ui-popup-buttons button:nth-child(2)")
	private WebElement btnNotificationPopupCancel;
	@FindBy(css = ".btn-group .btnDelete .fa-trash-o")
	private WebElement btnNotificationGridFirstDeleteButton;
	
	@FindBy(css = ".nav_left .sidebar_trash")
	private WebElement lnkTrashLink;
	
	public void clickTrashFolder() {
		click(lnkTrashLink);
		log.info("Trash Folder clicked successfully.");
	}
	
	
	@FindBy(css = "a#btnputback img")
	private WebElement lnkPutBackTrash;
	@FindBy(css = ".nav_left .sidebar_saved-mail")
	private WebElement lnkSavedLink;
	
	@FindBy(css = "a#btnSave.btn.btnSave")
	private WebElement btnStar;
	
	public void clickSaveIcon() {
		click(btnStar);
		log.info("Save Icon clicked successfully.");
	}
	
	@FindBy(css = ".toast.toast-warning")
	private WebElement lblMsgHeader;
	
	public boolean isWarningToastDisplayed() {
		return isElementVisible(lblMsgHeader);
	}
	public String getWarningMessage() {
		String label = getText(lblMsgHeader);
		return label;
	}
	
	
	@FindBy(css = ".toast.toast-success")
	private WebElement lblMsgSuccessfulHeader;
	
	public boolean isDeleteSuccessToastDisplayed() {
		return isElementVisible(lblMsgSuccessfulHeader);
	}
	public String getSuccessMessage() {
		String label = getText(lblMsgSuccessfulHeader);
		return label;
	}
	
	@FindBy(css = ".nav_left .sidebar_inboxmains")
	private WebElement lnkInboxLink;
	@FindBy(css = "a#btnBack")
	private WebElement btnBackEmailDetails;
	@FindBy(css = "button.btn.btn-sm.tooltips.btnDelete")
	private WebElement btnDeleteEmailDetails;
	@FindBy(css = "div#nodata")
	private WebElement lblMsgNoMessages;
	@FindBy(css = "input#lblToUser")
	private WebElement lblToUserComposeEmail;
	@FindBy(css = "div.note-editable.panel-body")
	private WebElement txtBoxMailDetailsComposeEmail;
	
	
	@FindBy(css = "a#btnSubmitReply")
	private WebElement btnReplyComposeEmail;
	
	public boolean isReplyButtonDisplayed() {
		return isElementVisible(btnReplyComposeEmail);
	}
	
	public void clickReplySubmitButton() {
		click(btnReplyComposeEmail);
		log.info("Reply Button clicked successfully.");
	}
	
	
	
	@FindBy(css = "span#lblOutage")
	private WebElement lblUnreadOutage;
	@FindBy(css = ".nav_left #outage")
	private WebElement lnkOutageSideMenu;
	@FindBy(css = ".nav_left #billing")
	private WebElement lnkBillingSideMenu;
	@FindBy(css = ".nav_left #service")
	private WebElement lnkServicesSideMenu;
	@FindBy(css = ".nav_left #connectme")
	private WebElement lnkConnectMeSideMenu;
	@FindBy(css = ".nav_left #leakalert")
	private WebElement lnkLeakAlertSideMenu;

	@FindBy(css = "span#lblBilling")
	private WebElement lblUnreadBilling;
	@FindBy(css = "span#lblleakalert")
	private WebElement lblUnreadLeakAlert;

	@FindBy(xpath = "//div[@class='view-mail']/p")
	private WebElement lblMessageBody;

	public String getLblMessageBodyLabel() {
		String label = getText(lblMessageBody);
		log.info("Current balance label is " + label);
		return label;
	}

	@FindBy(css = ".nav_left .nitiF_acc_ico")
	private WebElement lnk_NotificationPreferenceLeftN;
	@FindBy(css = ".inner-address span")
	private WebElement lbl_PageHeadingN;

	@FindBy(css = ".multiselect.dropdown-toggle.btn.btn-default")
	private WebElement btnCategoriesNotificationDropDown;

	public boolean isbtnCategoriesNotificationDropDownVisible() {
		log.info("Checking the Visibility of Categories Notification Drop Down");
		return isElementVisible(btnCategoriesNotificationDropDown);
	}

	public void clickbtnCategoriesNotificationDropDown() {
		clickElementUsingJsExecutor(btnCategoriesNotificationDropDown);
		log.info("Click on Categories Notification DropDown Successfully .");
	}

	@FindBy(xpath = "(//li[@class='ddlItem active']//label)[3]")
	private WebElement lblBillingCategoriesNotificationDropDown;

	@FindBy(css = ".multiselect.dropdown-toggle")
	private WebElement btnCategoriesDropdown;

	@FindBy(css = ".dropdown-menu.multiselect-container>li")
	private WebElement lstCategoriesData;

	public String getDropdownClass() {
		String active = getAttribute(lstCategoriesData, "class");
		log.info("Max length of Customer Name field is {} " + active);
		return active;
	}

	@FindBy(css = "li:nth-of-type(1) > a > .checkbox >input")
	private WebElement chkBoxAllCategory;

	public void clickchkBoxAllCategory() {
		clickElementUsingJsExecutor(chkBoxAllCategory);
		log.info("Click on chk Box All Category Successfully .");
	}

	@FindBy(css = "li:nth-of-type(2) > a > .checkbox >input")
	private WebElement chkBoxOutageCat;

	public void clickchkBoxOutageCat() {
		clickElementUsingJsExecutor(chkBoxOutageCat);
		log.info("Click on Outage Category Successfully .");
	}

	public String getOutageClass() {
		String active = getAttribute(lstCategoriesData, "class");
		log.info("Max length of Customer Name field is {} " + active);
		return active;
	}

	@FindBy(css = "li:nth-of-type(3) > a > .checkbox >input")
	private WebElement chkBoxBillingCat;
	@FindBy(css = "li:nth-of-type(4) > a > .checkbox >input")
	private WebElement chkBoxServicesCat;
	@FindBy(css = "li:nth-of-type(5) > a > .checkbox >input")
	private WebElement chkBoxConnectMeCat;
	@FindBy(css = "li:nth-of-type(6) > a > .checkbox >input")
	private WebElement chkBoxDemandResponseCat;
	@FindBy(css = "li:nth-of-type(7) > a > .checkbox >input")
	private WebElement chkBoxLeakAlertCat;
	@FindBy(css = "li:nth-of-type(8) > a > .checkbox >input")
	private WebElement chkBoxLoginIssuesCat;
	@FindBy(css = ".dropdown-menu.multiselect-container > li:nth-of-type(1)")
	private WebElement chkBoxAllCat;
	@FindBy(xpath = "//input[@value ='Connect Me']")
	private WebElement chkStatusConnectMe;
	@FindBy(xpath = "//input[@value ='Billing']")
	private WebElement chkStatusBilling;
	@FindBy(xpath = "//input[@value ='Outages']")
	private WebElement chkStatusOutage;
	@FindBy(xpath = "//input[@value ='Services']")
	private WebElement chkStatusService;
	@FindBy(xpath = "//input[@value ='Demand Response']")
	private WebElement chkStatusDR;
	@FindBy(xpath = "//input[@value ='Leak Alert']")
	private WebElement chkStatusLeakAlert;
	@FindBy(xpath = "//input[@value ='Login Issues']")
	private WebElement chkStatusLoginIssues;
	@FindBy(css = "#lirowdiv0 #mailCategory0")
	private WebElement btnCategoryFirstrow;
	
	
	@FindBy(css = "#lirowdiv0 > div")
	private WebElement checkbox;

	public void clickCheckBox() {
		click(checkbox);
		log.info("Check Box  clicked successfully.");
	}
	
	@FindBy(css = "#lirowdiv1 > div")
	private WebElement checkbox1;

	public void clickCheckBox1() {
		click(checkbox1);
		log.info("Check Box1 clicked successfully.");
	}
	
	@FindBy(css = "#lirowdiv2 > div")
	private WebElement checkbox2;

	public void clickCheckBox2() {
		click(checkbox2);
		log.info("CheckBox2 clicked successfully.");
	}
	
	@FindBy(css = "#divConfirmbtnok")
	private WebElement btnContinueDeleteMailConfirmPopUpMsg1;
	public void clickDeleteContinueButton1() {
		click(btnContinueDeleteMailConfirmPopUpMsg1);
		log.info("Continue Button clicked Successfully .");
	}
	@FindBy(css = "#toast-container > div .toast-message")
	private WebElement putbackMsgSuccessfulHeader;
	
	public boolean isPutBackSuccessToastDisplayed() {
		return isElementVisible(putbackMsgSuccessfulHeader);
	}
	public String getPutBackSuccessMessage() {
		String label = getText(putbackMsgSuccessfulHeader);
		return label;
	}
	
	@FindBy(css = "#toast-container > div .toast-message")
	private WebElement ReplyMsgSuccessfulHeader;
	
	public boolean isReplySuccessToastDisplayed() {
		return isElementVisible(ReplyMsgSuccessfulHeader);
	}
	public String getReplySuccessMessage() {
		String label = getText(ReplyMsgSuccessfulHeader);
		return label;
	}
	
	@FindBy(css = ".toast-message")
	private WebElement enterMessageToast;
	
	public boolean isEnterMessageToastDisplayed() {
		return isElementVisible(enterMessageToast);
	}
	public String getEnterMessage() {
		String label = getText(enterMessageToast);
		return label;
	}
	
	
}

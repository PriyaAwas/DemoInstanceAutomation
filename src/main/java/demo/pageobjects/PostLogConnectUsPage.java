package demo.pageobjects;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.pageObjects.scp.HomePage;

public class PostLogConnectUsPage extends HomePage {
	private static final Logger log = LogManager.getLogger(PostLogConnectUsPage.class);

	public PostLogConnectUsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//li[@class='connect dropicon']//*[text()='Contact Us']/..")
	private WebElement lnk_connect_me;

	public void clickConnectMeLink() {
		click(lnk_connect_me);
		log.info("Connect Me link clicked successfully {}.");
	}

	@FindBy(css = ".nav_left li a")
	private WebElement lnk_SideBarSubModules;

	public void clickSideBarSubModulesLnk() {
		click(lnk_SideBarSubModules);
		log.info("Side Bar Sub Modules link clicked.");
	}

	@FindBy(css = ".nav_left .icon_contact']")
	private WebElement lnk_ContactUs;

	public void clickContactUs() {
		click(lnk_ContactUs);
		log.info("Contact Us link clicked.");
	}

	@FindBy(css = ".icon_SubmittedForms a")
	private WebElement lnk_TrackRequestCmp;

	public void clickTrackRequest() {
		click(lnk_TrackRequestCmp);
		log.info("Track Request clicked.");
	}

	public void waitForTrachRequestFieldVisibility() {
		waitForElementToBeVisible(lnk_TrackRequestCmp);
		log.info("Wait for Trach Request field to be displayed.");
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

	@FindBy(css = ".nav_left .icon_fb")
	private WebElement lnk_Facebook;

	public void clickFacebook() {
		click(lnk_Facebook);
		log.info("Facebook link clicked.");
	}

	@FindBy(css = ".nav_left .icon_twitter")
	private WebElement lnk_Twitter;

	public void clickTwitter() {
		click(lnk_Twitter);
		log.info("Twitter link clicked.");
	}

	@FindBy(css = ".nav_left .icon_youtube_new")
	private WebElement lnk_Youtube;

	public void clickYoutube() {
		click(lnk_Youtube);
		log.info("Youtube link clicked.");
	}

	@FindBy(css = ".nav_left .icon_instagram")
	private WebElement lnk_Instagram;

	public void clickInstagram() {
		click(lnk_Instagram);
		log.info("InstagramCmp link clicked.");
	}

	@FindBy(css = "#lnk_connectme")
	private WebElement lnkContactUsSideMenu;

	public void clickContactUsSideMenu() {
		click(lnkContactUsSideMenu);
		log.info("Contact U Side Menu link clicked.");
	}

	@FindBy(css = "[globalize='ML_ConnectMe_FB']")
	private WebElement tabFacebook;

	public void clickFacebookTab() {
		click(tabFacebook);
		log.info("Facebook Tab clicked.");
	}

	public boolean isFacebookTabVisible() {
		log.info("Facebook Tab Status :" + tabFacebook.isDisplayed());
		return tabFacebook.isDisplayed();
	}

	@FindBy(css = "[globalize='ML_ConnectMe_Youtube']")
	private WebElement tabYouTube;

	public void clickYouTubeTab() {
		click(tabYouTube);
		log.info("YouTube Side Menu link clicked.");
	}

	public boolean isYouTubeTabVisible() {
		log.info("YouTube Tab Status :" + tabYouTube.isDisplayed());
		return tabYouTube.isDisplayed();
	}

	@FindBy(css = "[globalize='ML_ConnectMe_tw']")
	private WebElement tabTwitter;

	public void clickTwitterTab() {
		click(tabTwitter);
		log.info("Twitter tab clicked.");
	}

	public boolean isTwitterTabVisible() {
		log.info("Twitter Tab Status :" + tabTwitter.isDisplayed());
		return tabTwitter.isDisplayed();
	}

	@FindBy(css = "a[class='editConnect']")
	private WebElement lnk_EditConnect;

	public void clickEditConnect() {
		click(lnk_EditConnect);
		log.info("Edit Connect clicked.");
	}

	@FindBy(css = "a[class='deleteConnect']")
	private WebElement lnk_DeleteConnect;

	public void clickDeleteConnect() {
		click(lnk_DeleteConnect);
		log.info("DeleteConnect Connect clicked.");
	}

	@FindBy(css = "#tblSavedForms_previous")
	private WebElement lnk_PreviousPagination;

	public void clickSavedFormsPreviousPagination() {
		click(lnk_PreviousPagination);
		log.info("Saved Forms Previous Pagination clicked.");
	}

	@FindBy(css = " #tblSavedForms_next")
	private WebElement lnk_NextPagination;

	public void clickNextFormsPreviousPagination() {
		click(lnk_NextPagination);
		log.info("Saved Forms Next Pagination clicked.");
	}

	@FindBy(css = "#tblSavedForms_paginate li a")
	private WebElement lnk_Pages;

	public void clickLnkPages() {
		click(lnk_Pages);
		log.info(" Lnk pages clicked.");
	}

	@FindBy(css = "#tblSavedForms_paginate li[class*='active'] a")
	private WebElement lnk_PageActive;

	public boolean isPageActiveVisible() {
		log.info("Page Active Status :" + lnk_PageActive.isDisplayed());
		return lnk_PageActive.isDisplayed();
	}

	@FindBy(css = ".Attachment-mail p span a")
	private WebElement lnk_TrackRequestAttachment;

	public boolean isTrackRequestAttachmentVisible() {
		log.info("Page Active Status :" + lnk_PageActive.isDisplayed());
		return lnk_PageActive.isDisplayed();
	}

	@FindBy(css = "tr:nth-child(1) #navbarDropdown i")
	private WebElement btn_SavedFormsMore;

	public void clickSavedFormsMore() {
		click(btn_SavedFormsMore);
		log.info("Saved Forms More pages clicked.");
	}

	@FindBy(css = ".nav-item.dropdown.open .listcard:nth-child(1) .editConnect")
	private WebElement btn_SavedFormsEditConnect;

	public void clickSavedFormsEditConnect() {
		click(btn_SavedFormsEditConnect);
		log.info("Saved Forms Edit Connect clicked.");
	}

	@FindBy(css = "#ContentPlaceHolder1_freqQues_faqBucket div a span:nth-child(2)")
	private WebElement lnk_ContactusFAQ;

	public void clickContactusFAQ() {
		click(lnk_ContactusFAQ);
		log.info("Contact Us FAQ clicked.");
	}

	@FindBy(css = ".breadcrumb li a")
	private WebElement lnkFaqHelp;

	public void clickFaqHelp() {
		click(lnkFaqHelp);
		log.info("Faq Help clicked.");
	}

	@FindBy(css = ".icon_contact.active a")
	private WebElement lnkContactus;

	public void clickContactus() {
		click(lnkContactus);
		log.info("Contact Us clicked.");
	}

	public boolean isContactusVisible() {
		log.info("Contact Us visibility Status :" + lnkContactus.isDisplayed());
		return lnkContactus.isDisplayed();
	}

	@FindBy(css = "ul #social_newtb")
	private WebElement lnkSocialMedia;

	public void clickSocialMedia() {
		click(lnkSocialMedia);
		log.info("Social Media clicked.");
	}

	public boolean isSocialMediaVisible() {
		log.info("Social Media visibility Status :" + lnkSocialMedia.isDisplayed());
		return lnkSocialMedia.isDisplayed();
	}

	@FindBy(css = ".social_media_content.commonlisting ul li a")
	private WebElement lnkSocialMediaContent;

	public void clickSocialMediaContent() {
		click(lnkSocialMediaContent);
		log.info("Social Media Content Clicked.");
	}

	@FindBy(css = ".social_media_content.commonlisting ul li:nth-child(1)")
	private WebElement lnkSocialMediaFb;

	public void clickSocialMediaFb() {
		click(lnkSocialMediaFb);
		log.info("Social Media FB Clicked.");
	}

	@FindBy(css = ".veri_txt #anchorResendOtp")
	private WebElement lnkResendOtpTrackRequest;

	public void clickResendOtpTrackRequest() {
		click(lnkResendOtpTrackRequest);
		log.info("Resend Otp Track Request Clicked.");
	}

	@FindBy(css = "#Tracklink")
	private WebElement lnkTracklink;

	public void clickTrackLink() {
		click(lnkTracklink);
		log.info("Track link Clicked.");
	}

	@FindBy(css = "a#contctus_newtb")
	private WebElement lnk_ContactusNew;

	public void clickContactusNew() {
		click(lnk_ContactusNew);
		log.info("Contactus New Clicked.");
	}

	@FindBy(css = "li.icon_contact a#lnk_connectme")
	private WebElement lnk_ContactusAnchor;

	public void clickContactusAnchor() {
		click(lnk_ContactusAnchor);
		log.info("Contactus Anchor Clicked.");
	}

//Labels

	@FindBy(css = ".hdngsidepopup")
	private WebElement lblTrackReqSidebarHeader;

	public String getTrackReqSidebarHeaderLbl() {
		log.info("Fetching the Track Request Sidebar Header.");
		String label = getText(lblTrackReqSidebarHeader);
		log.info("Track Request Sidebar Header is {}: " + label);
		return label;
	}

	public boolean isTrackReqSidebarHeaderLblVisible() {
		log.info("Track Request Sidebar Header visibility Status :" + lblTrackReqSidebarHeader.isDisplayed());
		return lblTrackReqSidebarHeader.isDisplayed();
	}

	@FindBy(css = ".casesummary-head")
	private WebElement lbl_TrackReqSidebarRequestId;

	public String getTrackReqSidebarRequestId() {
		log.info("Fetching the Track Request Side bar Request Id");
		String label = getText(lbl_TrackReqSidebarRequestId);
		log.info("Track Request Side bar Request Id is {}: " + label);
		return label;
	}

	public boolean isTrackReqSidebarRequestIdVisible() {
		log.info("Track Request Side bar Request Id visibility Status :" + lbl_TrackReqSidebarRequestId.isDisplayed());
		return lbl_TrackReqSidebarRequestId.isDisplayed();
	}

	@FindBy(css = "#cmAccountNumber")
	private WebElement lbl_TrackReqSidebarReqType;

	public String getTrackReqSidebarReqType() {
		log.info("Fetching the Track Reqest Sidebar Req Type");
		String label = getText(lbl_TrackReqSidebarReqType);
		log.info("Track Reqest Sidebar Req Type is {}: " + label);
		return label;
	}

	public boolean isTrackReqSidebarReqTypeVisible() {
		log.info("Track Reqest Sidebar Req Type visibility Status :" + lbl_TrackReqSidebarReqType.isDisplayed());
		return lbl_TrackReqSidebarReqType.isDisplayed();
	}

	@FindBy(css = ".enquiry-txt strong")
	private WebElement lbl_TrackReqSidebarReqSubject;

	public String getTrackReqSidebarReqSubject() {
		log.info("Fetching the Track Request Sidebar Req Subject");
		String label = getText(lbl_TrackReqSidebarReqSubject);
		log.info("Track Request Sidebar Req Subject is {}: " + label);
		return label;
	}

	public boolean isTrackReqSidebarReqSubjectVisible() {
		log.info("Track Request Sidebar Req Subject is visibility Status :"
				+ lbl_TrackReqSidebarReqSubject.isDisplayed());
		return lbl_TrackReqSidebarReqSubject.isDisplayed();
	}

	@FindBy(css = "#cmSubject")
	private WebElement lbl_TrackReqSidebarReqRaisedOn;

	public String getTrackReqSidebarReqRaisedOn() {
		log.info("Fetching the Track Request Sidebar Req Raised On");
		String label = getText(lbl_TrackReqSidebarReqRaisedOn);
		log.info("Track Request Sidebar Req Raised On is {}: " + label);
		return label;
	}

	public boolean isTrackReqSidebarReqRaisedOnVisible() {
		log.info("Track Request Sidebar Req Raised On visibility Status :"
				+ lbl_TrackReqSidebarReqRaisedOn.isDisplayed());
		return lbl_TrackReqSidebarReqRaisedOn.isDisplayed();
	}

	@FindBy(css = ".emailDetails_inner > span:nth-child(5)")
	private WebElement lbl_TrackReqSidebarFrom;

	public String getTrackReqSidebarFrom() {
		log.info("Fetching the Track Request Sidebar From");
		String label = getText(lbl_TrackReqSidebarFrom);
		log.info("Track Request Sidebar From is {}: " + label);
		return label;
	}

	public boolean isTrackReqSidebarFromVisible() {
		log.info("Track Request Sidebar From visibility Status :" + lbl_TrackReqSidebarFrom.isDisplayed());
		return lbl_TrackReqSidebarFrom.isDisplayed();
	}

	@FindBy(css = ".emailDetails_inner > span:nth-child(6)")
	private WebElement lbl_TrackReqSidebarDetails;

	public String getTrackReqSidebarDetails() {
		log.info("Fetching the Track Request Sidebar From");
		String label = getText(lbl_TrackReqSidebarDetails);
		log.info("Track Request Sidebar From is {}: " + label);
		return label;
	}

	public boolean isTrackReqSidebarDetailsVisible() {
		log.info("Track Request Sidebar From visibility Status :" + lbl_TrackReqSidebarDetails.isDisplayed());
		return lbl_TrackReqSidebarDetails.isDisplayed();
	}

	@FindBy(css = "div.w2ui-centered")
	private WebElement lblNotificationMessage;

	public String getNotificationMessage() {
		log.info("Fetching Notification Message");
		String label = getText(lblNotificationMessage);
		log.info("Notification Message is {}: " + label);
		return label;
	}

	public boolean isNotificationMessageVisible() {
		log.info("Notification Message visibility Status :" + lblNotificationMessage.isDisplayed());
		return lblNotificationMessage.isDisplayed();
	}

	@FindBy(css = "h1 span[title='Contact Us']")
	private WebElement lbl_PageHeader;

	public String getPageHeader() {
		log.info("Fetching Page Header");
		String label = getText(lbl_PageHeader);
		log.info("Page Header is {}: " + label);
		return label;
	}

	public Boolean isPageHeaderVisible() {
		log.info("Page Header visibility Status :" + lbl_PageHeader.isDisplayed());
		return lbl_PageHeader.isDisplayed();
	}

	@FindBy(css = "h1#PageTitle")
	private WebElement lbl_PageHeaderPost;

	public String getPageHeaderPostLabel() {
		log.info("Fetching Page Header Post");
		String label = getText(lbl_PageHeaderPost);
		log.info("Page Header Post is {}: " + label);
		return label;
	}

	public boolean isPageHeaderPostVisible() {
		log.info("Page Header Post visibility Status :" + lbl_PageHeaderPost.isDisplayed());
		return lbl_PageHeaderPost.isDisplayed();
	}

	@FindBy(css = "h1.headEfficiency span")
	private WebElement lblPageHeaderpreReportOutContact;

	public String getPageHeaderpreReportOutContact() {
		log.info("Fetching Page Header pre Report Out Contact");
		String label = getText(lblPageHeaderpreReportOutContact);
		log.info("Page Header pre Report Out Contact is {}: " + label);
		return label;
	}

	public boolean isPageHeaderpreReportOutContactVisible() {
		log.info("Page Header pre Report Out Contact visibility Status :"
				+ lblPageHeaderpreReportOutContact.isDisplayed());
		return lblPageHeaderpreReportOutContact.isDisplayed();
	}

	@FindBy(css = "[id='lnk_connectme']")
	private WebElement lbl_ConnectMePageHeader;

	public void clickConnectMe() {
		click(lbl_ConnectMePageHeader);
		log.info("Connect Me link Clicked.");
	}

	public boolean isConnectMeVisible() {
		log.info("Connect me visibility Status :" + lbl_ConnectMePageHeader.isDisplayed());
		return lbl_ConnectMePageHeader.isDisplayed();
	}

	@FindBy(xpath = "//label[text()='Topic']")
	private WebElement lbl_ContactUsTopic;

	public String getContactUsTopic() {
		log.info("Fetching Contact Us Topic");
		String label = getText(lbl_ContactUsTopic);
		log.info("Contact Us Topic is {}: " + label);
		return label;
	}

	public boolean isContactUsTopicVisible() {
		log.info("Contact Us Topic visibility Status :" + lbl_ContactUsTopic.isDisplayed());
		return lbl_ContactUsTopic.isDisplayed();
	}

	@FindBy(xpath = "//label[text()='Customer Name']")
	private WebElement lbl_ContactUsCustomerName;

	public String getContactUsCustomerName() {
		log.info("Fetching Contact Us Customer Name");
		String label = getText(lbl_ContactUsCustomerName);
		log.info("CContact Us Customer Name is {}: " + label);
		return label;
	}

	public boolean isContactUsCustomerNameVisible() {
		log.info("Contact Us Customer Name visibility Status :" + lbl_ContactUsCustomerName.isDisplayed());
		return lbl_ContactUsCustomerName.isDisplayed();
	}

	@FindBy(xpath = "//label[text()='Service Account Number']")
	private WebElement lbl_ContactUsServiceAccNo;

	public String getContactUsServiceAccNo() {
		log.info("Fetching Contact Us Service Account No");
		String label = getText(lbl_ContactUsServiceAccNo);
		log.info("Contact Us Service Account No is {}: " + label);
		return label;
	}

	public boolean isContactUsServiceAccNoVisible() {
		log.info("Contact Us Service Account No visibility Status :" + lbl_ContactUsServiceAccNo.isDisplayed());
		return lbl_ContactUsServiceAccNo.isDisplayed();
	}

	@FindBy(css = "#previewfixed h3")
	private WebElement lbl_ContactUsPreviewYourForm;

	public String getContactUsPreviewYourForm() {
		log.info("Fetching Contact Us Preview Your Form");
		String label = getText(lbl_ContactUsPreviewYourForm);
		log.info("Contact Us Preview Your Form is {}: " + label);
		return label;
	}

	public boolean isContactUsPreviewYourFormVisible() {
		log.info("Contact Us Preview Your Form visibility Status :" + lbl_ContactUsPreviewYourForm.isDisplayed());
		return lbl_ContactUsPreviewYourForm.isDisplayed();
	}

	@FindBy(css = "#txtHeader11")
	private WebElement lbl_ContactUsPopupTitle;

	public String getContactUsPopupTitle() {
		log.info("Fetching Contact Us Popup Title");
		String label = getText(lbl_ContactUsPopupTitle);
		log.info("Contact Us Popup Title is {}: " + label);
		return label;
	}

	public boolean isContactUsPopupTitleVisible() {
		log.info("Contact Us Popup Title visibility Status :" + lbl_ContactUsPopupTitle.isDisplayed());
		return lbl_ContactUsPopupTitle.isDisplayed();
	}

	@FindBy(css = ".modal-body #txtBody")
	private WebElement lbl_ContactUsPopupContent;

	public String getContactUsPopupContent() {
		log.info("Fetching Contact Us Popup Content");
		String label = getText(lbl_ContactUsPopupContent);
		log.info("Contact Us Popup Content is {}: " + label);
		return label;
	}

	public boolean isContactUsPopupContentVisible() {
		log.info("Contact Us Popup Content visibility Status :" + lbl_ContactUsPopupContent.isDisplayed());
		return lbl_ContactUsPopupContent.isDisplayed();
	}

	@FindBy(css = "#divRequestDelete .info_connect_txt")
	private WebElement lbl_ContactUsConfirmPopupContent;

	public String getContactUsConfirmPopupContent() {
		log.info("Fetching Contact Us Confirm Popup Content");
		String label = getText(lbl_ContactUsConfirmPopupContent);
		log.info("Contact Us Confirm Popup Content is {}: " + label);
		return label;
	}

	public boolean isContactUsConfirmPopupContentVisible() {
		log.info("Contact Us Popup Content visibility Status :" + lbl_ContactUsConfirmPopupContent.isDisplayed());
		return lbl_ContactUsConfirmPopupContent.isDisplayed();
	}

	@FindBy(xpath = "//label[text()='Email Address']")
	private WebElement lbl_ContactUsEmailAdd;

	public String getContactUsEmailAdd() {
		log.info("Fetching Contact Us Email Address");
		String label = getText(lbl_ContactUsEmailAdd);
		log.info("Contact Us Email Address is {}: " + label);
		return label;
	}

	public boolean isContactUsEmailAddVisible() {
		log.info("Contact Us Email Address visibility Status :" + lbl_ContactUsEmailAdd.isDisplayed());
		return lbl_ContactUsEmailAdd.isDisplayed();
	}

	@FindBy(xpath = "(//label[text()='Subject'])[2]")
	private WebElement lbl_ContactUsSubject;

	public String getContactUsSubject() {
		log.info("Fetching Contact Us Subject");
		String label = getText(lbl_ContactUsSubject);
		log.info("Contact Us Subject is {}: " + label);
		return label;
	}

	public boolean isContactUsSubjectVisible() {
		log.info("Contact Us Email Address visibility Status :" + lbl_ContactUsSubject.isDisplayed());
		return lbl_ContactUsSubject.isDisplayed();
	}

	@FindBy(xpath = "(//label[text()='Add Attachment'])")
	private WebElement ContactUsAddAttachment;

	public String getContactUsAddAttachment() {
		log.info("Fetching Contact Us Add Attachment");
		String label = getText(ContactUsAddAttachment);
		log.info("Contact Us Add Attachment is {}: " + label);
		return label;
	}

	public boolean isContactUsAddAttachmentVisible() {
		log.info("Contact UsAdd Attachment visibility Status :" + ContactUsAddAttachment.isDisplayed());
		return ContactUsAddAttachment.isDisplayed();
	}

	@FindBy(xpath = "(//textarea[@globalize='ML_CONNECTME_Lbl_Comments']")
	private WebElement lbl_ContactUsComments;

	public String getContactUsComments() {
		log.info("Fetching Contact Us Comments");
		String label = getText(lbl_ContactUsComments);
		log.info("Contact Us Comments is {}: " + label);
		return label;
	}

	public boolean isContactUsCommentsVisible() {
		log.info("Contact UsAdd Attachment visibility Status :" + lbl_ContactUsComments.isDisplayed());
		return lbl_ContactUsComments.isDisplayed();
	}

	@FindBys(@FindBy(css = "#dvPreviewForm .column"))
	private List<WebElement> lbl_PreviewYourFormColumn;

	public List<WebElement> listPreviewYourFormColumn() {
		return lbl_PreviewYourFormColumn;
	}

	@FindBys(@FindBy(xpath = "//a[contains(@id,'remove_Budget')]"))
	private List<WebElement> lstBudgetCloseIcons;

	public List<WebElement> listBudgetCloseIcons() {
		return lstBudgetCloseIcons;
	}

	@FindBys(@FindBy(css = "#dvPreviewForm .value"))
	private List<WebElement> lbl_PreviewYourFormValue;

	public List<WebElement> listPreviewYourFormValue() {
		return lbl_PreviewYourFormValue;
	}

	public List<WebElement> getWebElements() {
		return driver.findElements((By) lbl_PreviewYourFormValue);
	}

	@FindBy(css = "#cmSubject i")
	private WebElement lbl_ValueTrackReqSidebarReqRaisedOn;

	public String getValueTrackReqSidebarReqRaisedOn() {
		log.info("Fetching Value Track Request Sidebar Req Raised On");
		String label = getText(lbl_ValueTrackReqSidebarReqRaisedOn);
		log.info("Value Track Request Sidebar Req Raised On is {}: " + label);
		return label;
	}

	public boolean isValueTrackReqSidebarReqRaisedOnVisible() {
		log.info("Value Track Request Sidebar Req Raised On visibility Status :"
				+ lbl_ValueTrackReqSidebarReqRaisedOn.isDisplayed());
		return lbl_ValueTrackReqSidebarReqRaisedOn.isDisplayed();
	}

	@FindBy(css = ".w2ui-box1 .w2ui-centered")
	private WebElement lblMsgPopUp;

	public String getlblMsgPopUp() {
		log.info("Fetching Message Popu");
		String label = getText(lblMsgPopUp);
		log.info("Message Popu Req Raised On is {}: " + label);
		return label;
	}

	public boolean islblMsgPopUpVisible() {
		log.info("Message Popu visibility Status :" + lblMsgPopUp.isDisplayed());
		return lblMsgPopUp.isDisplayed();
	}

	@FindBy(css = "#tblSavedForms_info")
	private WebElement lbl_PaginationInfo;

	public String getPaginationInfo() {
		log.info("Fetching Pagination Info");
		String label = getText(lbl_PaginationInfo);
		log.info("Pagination Info is {}: " + label);
		return label;
	}

	public boolean islPaginationInfoVisible() {
		log.info("Pagination Info visibility Status :" + lbl_PaginationInfo.isDisplayed());
		return lbl_PaginationInfo.isDisplayed();
	}

	@FindBy(css = "#toast-container")
	private WebElement lbl_DeleteSuccessfullyToast;

	public String getDeleteSuccessfullyToast() {
		log.info("Fetching Delete Successfully Toast");
		String label = getText(lbl_DeleteSuccessfullyToast);
		log.info("Delete Successfully Toast is {}: " + label);
		return label;
	}

	public boolean isDeleteSuccessfullyToastVisible() {
		log.info("Delete Successfully Toast Status :" + lbl_DeleteSuccessfullyToast.isDisplayed());
		return lbl_DeleteSuccessfullyToast.isDisplayed();
	}

	@FindBy(css = "#toast-container .toast-message")
	private WebElement lbl_ValidationToastMessage;

	public String getValidationToastMessage() {
		log.info("Fetching Validation Toast Message");
		String label = getText(lbl_ValidationToastMessage);
		log.info("Validation Toast Message is {}: " + label);
		return label;
	}

	public boolean isValidationToastMessageVisible() {
		log.info("Validation Toast Message Status :" + lbl_ValidationToastMessage.isDisplayed());
		return lbl_ValidationToastMessage.isDisplayed();
	}

	@FindBy(css = ".w2ui-tag-body.w2ui-tag-right")
	private WebElement lbl_MoveOutDateValidationMsg;

	public String getMoveOutDateValidationMsg() {
		log.info("Fetching Move Out Date Validation Msg");
		String label = getText(lbl_MoveOutDateValidationMsg);
		log.info("Move Out Date Validation Msg is {}: " + label);
		return label;
	}

	public boolean isMoveOutDateValidationMsgVisible() {
		log.info("Move Out Date Validation Msg Status :" + lbl_MoveOutDateValidationMsg.isDisplayed());
		return lbl_MoveOutDateValidationMsg.isDisplayed();
	}

	@FindBy(xpath = "//label[text()='First Name']")
	private WebElement lbl_FirstNameCi;

	public String getFirstName() {
		log.info("Fetching First Name");
		String label = getText(lbl_FirstNameCi);
		log.info("First Name is {}: " + label);
		return label;
	}

	public boolean isFirstNameVisible() {
		log.info("First Name Status :" + lbl_FirstNameCi.isDisplayed());
		return lbl_FirstNameCi.isDisplayed();
	}

	@FindBy(xpath = " //label[text()='Last Name']")
	private WebElement lbl_LastNameCi;

	public String getLastName() {
		log.info("Fetching Last Name");
		String label = getText(lbl_LastNameCi);
		log.info("Last Name is {}: " + label);
		return label;
	}

	public boolean isLastNameVisible() {
		log.info("Last Name Status :" + lbl_LastNameCi.isDisplayed());
		return lbl_LastNameCi.isDisplayed();
	}

	@FindBy(xpath = "//label[text()='Primary Phone']")
	private WebElement lbl_PriContactNoCi;

	public String getPriContactNo() {
		log.info("Fetching Primary Phone");
		String label = getText(lbl_PriContactNoCi);
		log.info("Primary Phone is {}: " + label);
		return label;
	}

	public boolean isPriContactNoVisible() {
		log.info("Primary Phone Status :" + lbl_PriContactNoCi.isDisplayed());
		return lbl_PriContactNoCi.isDisplayed();
	}

	@FindBy(xpath = "//label[text()='Street Name']")
	private WebElement lbl_StreetNameLoc;

	public String getStreetNameLoc() {
		log.info("Fetching Street Name");
		String label = getText(lbl_StreetNameLoc);
		log.info("Street Name is {}: " + label);
		return label;
	}

	public boolean isStreetNameLocVisible() {
		log.info("Street Name Status :" + lbl_StreetNameLoc.isDisplayed());
		return lbl_StreetNameLoc.isDisplayed();
	}

	@FindBy(xpath = "//label[text()='City']")
	private WebElement lbl_CityLoc;

	public String getCityLoc() {
		log.info("Fetching City Name");
		String label = getText(lbl_CityLoc);
		log.info("City Name is {}: " + label);
		return label;
	}

	public boolean isCityLocVisible() {
		log.info("City Name Status :" + lbl_CityLoc.isDisplayed());
		return lbl_CityLoc.isDisplayed();
	}

	@FindBy(xpath = "//label[text()='Zip Code']")
	private WebElement lbl_ZipcodeLoc;

	public String getZipCode() {
		log.info("Fetching Zip Code");
		String label = getText(lbl_ZipcodeLoc);
		log.info(" Zip Cod is {}: " + label);
		return label;
	}

	public boolean isZipCodeVisible() {
		log.info("Zip Code Status :" + lbl_ZipcodeLoc.isDisplayed());
		return lbl_ZipcodeLoc.isDisplayed();
	}

	@FindBy(xpath = "//label[text()='Nearest Cross Street']")
	private WebElement lbl_NearestCrossStreetLoc;

	public String getNearestCrossStreetLoc() {
		log.info("Fetching Nearest Cross Street");
		String label = getText(lbl_NearestCrossStreetLoc);
		log.info("Nearest Cross Street is {}: " + label);
		return label;
	}

	public boolean isNearestCrossStreetLocVisible() {
		log.info("Nearest Cross Street Status :" + lbl_NearestCrossStreetLoc.isDisplayed());
		return lbl_NearestCrossStreetLoc.isDisplayed();
	}

	@FindBy(css = "input.UserName.input_effect")
	private WebElement txt_UserNameInput;

	public boolean isUserNameInputVisible() {
		log.info("Nearest Cross Street Status :" + txt_UserNameInput.isDisplayed());
		return txt_UserNameInput.isDisplayed();
	}

	@FindBy(css = ".choosetopictxt")
	private WebElement lbl_ChooseTopic;

	public String getChooseTopic() {
		log.info("Fetching Choose Topic");
		String label = getText(lbl_ChooseTopic);
		log.info("Choose Topic Street is {}: " + label);
		return label;
	}

	public boolean isChooseTopicVisible() {
		log.info("Choose Topic Status :" + lbl_ChooseTopic.isDisplayed());
		return lbl_ChooseTopic.isDisplayed();
	}

	@FindBy(css = ".cls_disclaimer")
	private WebElement lbl_StayConnected;

	public String getStayConnected() {
		log.info("Fetching Stay Connected ");
		String label = getText(lbl_StayConnected);
		log.info("Stay Connected is {}: " + label);
		return label;
	}

	public boolean isStayConnectedVisible() {
		log.info("Stay Connected Status :" + lbl_StayConnected.isDisplayed());
		return lbl_StayConnected.isDisplayed();
	}

	@FindBy(css = "[globalize=\"ML_OTP_txt_AcctNo\"]")
	private WebElement txt_AccountNumber;

	public String getAccountNumber() {
		log.info("Fetching Account Number");
		String label = getText(txt_AccountNumber);
		log.info("Account Number is {}: " + label);
		return label;
	}

	public boolean isAccountNumberVisible() {
		log.info("Account Number Status :" + txt_AccountNumber.isDisplayed());
		return txt_AccountNumber.isDisplayed();
	}

	@FindBy(css = "[globalize=\"ML_Master_lbl_CustName\"]")
	private WebElement txt_CustomerName;

	public String getCustomerName() {
		log.info("Fetching Customer Name");
		String label = getText(txt_CustomerName);
		log.info("Customer Name is {}: " + label);
		return label;
	}

	@FindBy(xpath = "//input[@globalize='ML_Master_lbl_CustName']")
	private WebElement txtCustomerNameValue;

	public String getCustomerNameValue() {
		log.info("Fetching Customer Name");
		String label = getAttribute(txtCustomerNameValue, "value");

		// String label = getText(txt_CustomerName);
		log.info("Customer Name is {}: " + label);
		return label;
	}

	@FindBy(xpath = "//input[@inputtype='Email']")
	private WebElement txtCustomerEmailValue;

	public String getCustomerEmailValue() {
		log.info("Fetching Customer email");
		String label = getAttribute(txtCustomerEmailValue, "value");
		log.info("Customer  is {}: " + label);
		return label;
	}

	public boolean isCustomerNameVisible() {
		log.info("Customer Name Status :" + txt_CustomerName.isDisplayed());
		return txt_CustomerName.isDisplayed();
	}

	@FindBy(css = "[globalize =\"ML_MakeOTP_txt_EmailId\"]")
	private WebElement txt_CustomerEmailId;

	public String getCustomerEmailId() {
		log.info("Fetching Customer Email Id");
		String label = getText(txt_CustomerEmailId);
		log.info("Customer Email Id is {}: " + label);
		return label;
	}

	public boolean isCustomerEmailIdVisible() {
		log.info("Customer Email Id Status :" + txt_CustomerEmailId.isDisplayed());
		return txt_CustomerEmailId.isDisplayed();
	}

	@FindBy(css = "[globalize =\"ML_SrvcRqust_Date\"]")
	private WebElement txt_ServiceRequestDate;

	public String getServiceRequestDate() {
		log.info("Fetching Service Request Date");
		String label = getText(txt_ServiceRequestDate);
		log.info("Service Request Date is {}: " + label);
		return label;
	}

	public boolean isServiceRequestDateVisible() {
		log.info("Service Request Date Status :" + txt_ServiceRequestDate.isDisplayed());
		return txt_ServiceRequestDate.isDisplayed();
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

	@FindBy(css = "#ContentPlaceHolder1_ContentPlaceHolderBody_liBEnq b span")
	private WebElement lbl_BillingEnquires;

	public String getBillingEnquires() {
		log.info("Fetching Billing Enquires");
		String label = getText(lbl_BillingEnquires);
		log.info("Billing Enquires is {}: " + label);
		return label;
	}

	public boolean isBillingEnquiresVisible() {
		log.info("Billing Enquires Status :" + lbl_BillingEnquires.isDisplayed());
		return lbl_BillingEnquires.isDisplayed();
	}

	@FindBy(css = "#dvPreviewForm span:nth-child(3)")
	private WebElement txt_PreviewForm;

	public String getPreviewForm() {
		log.info("Fetching Preview Form");
		String label = getText(txt_PreviewForm);
		log.info("Preview Form is {}: " + label);
		return label;
	}

	public boolean isPreviewFormVisible() {
		log.info("Preview Form Status :" + txt_PreviewForm.isDisplayed());
		return txt_PreviewForm.isDisplayed();
	}

	@FindBy(css = ".heading3_formpreview")
	private WebElement lbl_ReviewAndConfirm;

	public String getReviewAndConfirm() {
		log.info("Fetching Review And Confirm");
		String label = getText(lbl_ReviewAndConfirm);
		log.info("Review And Confirm is {}: " + label);
		return label;
	}

	public boolean isReviewAndConfirmVisible() {
		log.info("Review And Confirm Status :" + lbl_ReviewAndConfirm.isDisplayed());
		return lbl_ReviewAndConfirm.isDisplayed();
	}

	@FindBy(css = "[globalize=\"ML_Submitted_Forms\"]")
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

	@FindBy(css = "[globalize=\"ML_Saved_Forms\"]")
	private WebElement Btn_SavedForms;

	public String getSavedForms() {
		log.info("Fetching Saved Forms");
		String label = getText(Btn_SavedForms);
		log.info("Saved Forms is {}: " + label);
		return label;
	}

	public boolean isSavedFormsVisible() {
		log.info("Saved Forms Status :" + Btn_SavedForms.isDisplayed());
		return Btn_SavedForms.isDisplayed();
	}

	@FindBy(css = "#tblSubmittedForms_wrapper #tblSubmittedForms tbody td a")
	private WebElement lnkSubmittedForms;

	public String getlnkSubmittedForms() {
		log.info("Fetching lnk Submitted Forms");
		String label = getText(lnkSubmittedForms);
		log.info("lnk Submitted Forms is {}: " + label);
		return label;
	}

	public boolean islnkSubmittedFormsVisible() {
		log.info("lnk Submitted Forms Status :" + lnkSubmittedForms.isDisplayed());
		return lnkSubmittedForms.isDisplayed();
	}

	@FindBy(css = "#idSaveFormtab")
	private WebElement lblSaveFormtab;

	public String getlblSaveFormtab() {
		log.info("Fetching Save Form tab");
		String label = getText(lblSaveFormtab);
		log.info("Save Form tab is {}: " + label);
		return label;
	}

	public boolean islblSaveFormtabVisible() {
		log.info("Save Form tab Status :" + lblSaveFormtab.isDisplayed());
		return lblSaveFormtab.isDisplayed();
	}

	@FindBy(css = ".connect_tbsnew .icon_SavedForms")
	private WebElement lblPostLoginSaveFormtab;

	public String getlblPostLoginSaveFormtab() {
		log.info("Fetching lbl Post Login Save Form tab");
		String label = getText(lblPostLoginSaveFormtab);
		log.info("lbl Post Login Save Form tab is {}: " + label);
		return label;
	}

	public boolean islblPostLoginSaveFormtabVisible() {
		log.info("lbl Post Login Save Form tab Status :" + lblPostLoginSaveFormtab.isDisplayed());
		return lblPostLoginSaveFormtab.isDisplayed();
	}

	@FindBy(css = "#lblCService")
	private WebElement lblContactno;

	public String getlblContactno() {
		log.info("Fetching lbl Contact No");
		String label = getText(lblContactno);
		log.info("Contact No is {}: " + label);
		return label;
	}

	public boolean islblContactnoVisible() {
		log.info("Contact No Status :" + lblContactno.isDisplayed());
		return lblContactno.isDisplayed();
	}

	@FindBy(css = "#lblEmail a")
	private WebElement lblContactEmail;

	public String getlblContactEmail() {
		log.info("Fetching lbl Contact Email");
		String label = getText(lblContactEmail);
		log.info("lbl Contact Email is {}: " + label);
		return label;
	}

	public boolean islblContactEmailVisible() {
		log.info("lbl Contact Email Status :" + lblContactEmail.isDisplayed());
		return lblContactEmail.isDisplayed();
	}

	@FindBy(css = ".lft_contn_contc span span")
	private WebElement lblpostloginCustomerServiceContactNo;

	public String getPostloginCustomerServiceContactNolbl() {
		log.info("Fetching Post login Customer Service Contact No");
		String label = getText(lblpostloginCustomerServiceContactNo);
		log.info("Post login Customer Service Contact No is {}: " + label);
		return label;
	}

	@FindBy(css = "#ContentPlaceHolder1_ContentPlaceHolderBody_lblBEnq")
	private WebElement lblpostloginBillingEnquriesContactNo;

	public String getPostloginBillingEnquriesContactNolbl() {
		log.info("Fetching Post login Customer Service Billing No");
		String label = getText(lblpostloginBillingEnquriesContactNo);
		log.info("Post login Customer Service Contact No is {}: " + label);
		return label;
	}

	@FindBy(css = " #ContentPlaceHolder1_ContentPlaceHolderBody_lblBEmail")
	private WebElement lblpostloginBillingEnquriesEmailId;

	public String getPostloginBillingEnquriesEmaillbl() {
		log.info("Fetching Post login Customer Service email Id");
		String label = getText(lblpostloginBillingEnquriesEmailId);
		log.info("Post login billing Email Id Is {}: " + label);
		return label;
	}

	public boolean isPostloginCustomerServiceContactNoVisible() {
		log.info(
				"Post login Customer Service Contact No Status :" + lblpostloginCustomerServiceContactNo.isDisplayed());
		return lblpostloginCustomerServiceContactNo.isDisplayed();
	}

	@FindBy(css = "[globalize=ML_CONNECTME_Span_Emailtext] a ")
	private WebElement lblPostloginCustomerServiceEmailId;

	public String getPostloginCustomerServiceEmailId() {
		log.info("Fetching Post login Customer Service Email Id");
		String label = getText(lblPostloginCustomerServiceEmailId);
		log.info("Post login Customer Service Email Id is {}: " + label);
		return label;
	}

	public boolean isPostloginCustomerServiceEmailIdVisible() {
		log.info("Post login Customer Service Email Id Status :" + lblPostloginCustomerServiceEmailId.isDisplayed());
		return lblPostloginCustomerServiceEmailId.isDisplayed();
	}

	@FindBy(css = ".contn_wrpper.thirdstep h2")
	private WebElement lblAuthHeaderMsg;

	public String getlblAuthHeaderMsg() {
		log.info("Fetching lbl Auth Header Msg");
		String label = getText(lblAuthHeaderMsg);
		log.info("lbl Auth Header Msg is {}: " + label);
		return label;
	}

	public boolean islblAuthHeaderMsgVisible() {
		log.info("lbl Auth Header Msg Status :" + lblAuthHeaderMsg.isDisplayed());
		return lblAuthHeaderMsg.isDisplayed();
	}

	@FindBy(css = ".contn_wrpper.thirdstep p")
	private WebElement lblAuthmsg;

	public String getlblAuthmsg() {
		log.info("Fetching lbl Auth msg");
		String label = getText(lblAuthmsg);
		log.info("lbl Auth msg is {}: " + label);
		return label;
	}

	public boolean islblAuthmsgVisible() {
		log.info("lbl Auth msg Status :" + lblAuthmsg.isDisplayed());
		return lblAuthmsg.isDisplayed();
	}

	// Button

	@FindBy(css = ".wrapper_box_close a")
	private WebElement btn_TrackReqSidebarClose;

	@FindBy(css = "#closeRequestbox a")
	private WebElement btn_PreLogTrackReqSidebarClose;

	@FindBy(css = "input[globalize='ML_CONNECTME_Lbl_AddAttach']")
	private WebElement btnChooseFile;

	public void btnChooseFileSubmit() {
		click(btnChooseFile);
		log.info("Choose File Button clicked {}.");
	}

	public boolean isChooseFileBtnVisible() throws InterruptedException {
		scrollPageToElement(btnChooseFile);
		log.info("Choose File Button Status :" + btnChooseFile.isDisplayed());
		return btnChooseFile.isDisplayed();
	}

	public void populateChooseFile(String chooseFile) {
		log.info("Subject {} :" + chooseFile);
		sendKeys(btnChooseFile, chooseFile);
		log.info("choose File populated successfully.");
	}

	public void addAttachmentToChooseFile(String value) {
		sendKeysWithoutCheckingVisibility(btnChooseFile, value);
		log.info("Choose File Button added successfully with File Value " + value);
		ExtentLogger.logInfo("Choose File Button added successfully with File Value " + value);
	}

	@FindBy(css = "#BtnSubmitCommentNew")
	private WebElement btnSubmit;

	@FindBy(css = ".w2ui-popup-btn.btn")
	private WebElement btnOkOnNotification;

	@FindBy(css = ".buttons_area #BtnSaveCommentNew")
	private WebElement btnContactUsSave;

	public void btnClickSubmits() {
		click(btnContactUsSave);
		log.info("Submit Button clicked {}.");
	}

	public boolean isSubmitsBtnVisible() {
		log.info("Submit Button Status :" + btnContactUsSave.isDisplayed());
		return btnContactUsSave.isDisplayed();
	}

	@FindBy(css = "input#BtnNextCommentNew")
	private WebElement btnContactUsNext;

	public void btnClickNext() {
		click(btnContactUsNext);
		log.info("Next Button clicked {}.");
	}

	public boolean isNextBtnVisible() {
		log.info("Next Button Status :" + btnContactUsNext.isDisplayed());
		return btnContactUsNext.isDisplayed();
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

	@FindBy(css = "#BtnBackCommentNew")
	private WebElement btn_ContactUsBack;

	@FindBy(xpath = "(//button[@id=\"btnok\"])[2]")
	private WebElement btnContactUsPopupOk;

	public void clickContactUsPopupOk() {
		clickElementUsingJsExecutor(btnContactUsPopupOk);
		log.info("Ok Button clicked {}.");
	}

	@FindBy(css = "(//button[@id='btnok'])")
	private WebElement btn_PreLoginContactUsPopupOK;

	@FindBy(css = ".setting_save_box .submit-button")
	private WebElement btnSubmitButton;

	@FindBy(css = ".w2ui-popup-btn.btn")
	private WebElement btnOkMsgPopUp;

	@FindBy(css = "#toast-container button")
	private WebElement btn_CloseDeleteSuccessfullyToast;

	@FindBy(css = ".w2ui-popup-buttons #Yes")
	private WebElement btn_PopupYes;

	@FindBy(css = ".w2ui-popup-buttons #No")
	private WebElement btn_PopupNo;

	@FindBy(css = " #toast-container .toast-close-button")
	private WebElement btn_CloseValidationToast;

	@FindBy(css = ".tracking_area #TrackingBtn")
	private WebElement btn_TrackReqConnectMe_Cs;

	@FindBy(css = ".modal-body #txtBody")
	private WebElement txt_ContactuspopupCmp;

	@FindBy(css = "[globalize=\"ML_ContactUs_Back\"]")
	private WebElement btn_ContactUsback;

	@FindBy(css = ".modal-body #txtBody")
	private WebElement txtPopupThankYou;

	public String getlblPopupThankYou() {
		log.info("Fetching Thank You lbl");
		String label = getText(txtPopupThankYou);
		log.info("Thank You lbl is {}: " + label);
		return label;
	}

	public boolean isLblPopupThankYouVisible() {
		log.info("Thank you lbl Status is:" + txtPopupThankYou.isDisplayed());
		return txtPopupThankYou.isDisplayed();
	}

	@FindBy(xpath = "//div[text() ='Attachments']//following-sibling::i")
	private WebElement btn_TrackRequestAttachmentArrow;

	@FindBy(css = ".enquiry-txt + i")
	private WebElement btn_TrackRequestSubjectArrow;

	@FindBy(css = "#idContact")
	private WebElement btn_PreLoginContactUsAnchor;

	@FindBy(css = "#BtnSubmitOtp")
	private WebElement btn_SubmitPreLoginTrackRequestOTP;

	@FindBy(css = "input#BtnCancelOtp")
	private WebElement btn_CancelPreLoginTrackRequestOTP;

	@FindBy(xpath = "//a[contains(text(),'Enroll')]")
	private WebElement btnEnrolledDynamicXpathBefore;

	@FindBy(xpath = " //a[not(contains(@style, 'display: none'))][text()='Enrolled']")
	private WebElement btnEnrolledDynamicXpathAfter;

	// TextBox

	@FindBy(xpath = "//textarea")
	private WebElement txtBoxAdditionalInfo;

	@FindBy(xpath = "//p/input")
	private WebElement txtBoxAddress;

	@FindBy(css = "#02bad7fd-ece2-49bf-a7a9-9cef05dfa679")
	private WebElement txtBoxDate;

	@FindBy(css = "#e91a7c5e-3cb3-4bba-8ba4-80dc8bdb3343")
	private WebElement txtBoxCommnets;

	@FindBy(css = "input[globalize='ML_Master_lbl_CustName']")
	private WebElement txtContactUsCustomerName;

	public boolean isCustomerNameTxtVisible() {
		log.info("Checking that the Customer Name field is visible on the Contact Us page.");
		return isElementVisible(txtContactUsCustomerName);
	}

	public String getCustomerNameMaxLength() {
		String maxLen = getAttribute(txtContactUsCustomerName, "maxlength");
		log.info("Max length of Customer Name field is {} " + maxLen);
		return maxLen;
	}

	public String getCustomerNameLabel() {
		log.info("Fetching the Customer Name label.");
		String label = getText(txtContactUsCustomerName);
		log.info("Customer Name label is {}: " + label);
		return label;
	}

	@FindBy(xpath = "(//span[@title='Customer Name'])[1]")
	private WebElement txtCustomerName;

	public String getTextCustomerName() {
		log.info("Fetching the Customer Name .");
		String label = getText(txtContactUsCustomerName);
		log.info("Customer Name label is {}: " + label);
		return label;
	}

	@FindBy(css = "input[globalize='ML_OTP_txt_AcctNo']")
	private WebElement txtServiceAccNo;

	public boolean isServiceAccNoTxtVisible() {
		log.info("Checking that the Service Acc No field is visible on the Contact Us page.");
		return isElementVisible(txtServiceAccNo);
	}

	@FindBy(xpath = "//input[@inputtype='Account']")
	private WebElement txtAccNo;

	public String gettxtAccountNum() {
		log.info("Fetching the Account No");

		String label = getAttribute(txtAccNo, "value");
		log.info("Service Acc No page header is {}: " + label);
		return label;
	}

	public String getServiceAccNoMaxLength() {
		String maxLen = getAttribute(txtServiceAccNo, "maxlength");
		log.info("Max length of Service Acc No field is {} " + maxLen);
		return maxLen;
	}

	public String getServiceAccNoMinLength() {
		String minLen = getAttribute(txtServiceAccNo, "minlength");
		log.info("Min length of Service Acc No field is {} " + minLen);
		return minLen;
	}

	public String getServiceAccNoLabel() {
		log.info("Fetching the Forget Username page header.");
		String label = getText(txtServiceAccNo);
		log.info("Service Acc No page header is {}: " + label);
		return label;
	}

	@FindBy(xpath = "//label[text()='Email Address']/..//input")
	private WebElement txtEmailAddress;

	public boolean isEmailAddressTxtVisible() {
		log.info("Checking that the Email Address field is visible on the Contact Us page.");
		return isElementVisible(txtEmailAddress);
	}

	public String getEmailAddressMaxLength() {
		String maxLen = getAttribute(txtEmailAddress, "maxlength");
		log.info("Max length of Email address field is {} " + maxLen);
		return maxLen;
	}

	public String getEmailAddressMinLength() {
		String minLen = getAttribute(txtEmailAddress, "minlength");
		log.info("Min length of Email address field is {} " + minLen);
		return minLen;
	}

	public String getEmailAddressLabel() {
		log.info("Fetching the Email lAddress label.");
		String label = getText(txtEmailAddress);
		log.info("Email Address label is {}: " + label);
		return label;
	}

	@FindBy(xpath = "//a[@class='dropdown-item']//br[1]")
	private WebElement txtAccountNo;

	public String gettxtAccountNo() {
		log.info("Fetching the Account No");
		String label = getText(txtServiceAccNo);
		log.info("Service Acc No page header is {}: " + label);
		return label;
	}

	@FindBy(css = ".Subject.input_effect")
	private WebElement txtSubject;

	public boolean istxtSubjectTxtVisible() throws InterruptedException {
		log.info("Checking that the Subject field is visible on the Contact Us page.");
		pause(5000);
		return isElementVisible(txtSubject);
	}

	public String gettxtSubjectMaxLength() {
		String maxLen = getAttribute(txtSubject, "maxlength");
		log.info("Max length of Subject field is {} " + maxLen);
		return maxLen;
	}

	public void clearSubjectField() {
		clear(txtSubject);
		log.info("Subject field cleared {}");
	}

	public void populateSubject(String subject) {
		log.info("Subject {} :" + subject);
		sendKeys(txtSubject, subject);
		log.info("Subject populated successfully.");
	}

	@FindBy(xpath = "//input[contains(@id,'FileUpload1')]")
	private WebElement txt_ContactUsAddAttachment;

	@FindBy(xpath = "//label[contains(text(),'Comments')]/..//textarea[@globalize='ML_CONNECTME_Lbl_Comments']")
	private WebElement txtComments;

	public boolean isCommentsTxtVisible() {
		log.info("Checking that the Comments field is visible on the Contact Us page.");
		return isElementVisible(txtComments);
	}

	public String gettxtCommentsMaxLength() {
		String maxLen = getAttribute(txtComments, "maxlength");
		log.info("Max length of Comments field is {} " + maxLen);
		return maxLen;
	}

	public void clearCommentsField() {
		clear(txtComments);
		log.info("Comments field cleared {}");
	}

	public void populateComments(String Comments) {
		log.info("Subject {} :" + Comments);
		sendKeys(txtComments, Comments);
		log.info("Comments populated successfully.");
	}

	@FindBy(css = "#tblSavedForms_filter [type ='search']")
	private WebElement txt_SearchReqIdSavedForms;

	public void populateSearchTrackReq(String requestID) {
		log.info("Request ID {} :" + requestID);
		sendKeys(SearchTrackReq, requestID);
		log.info("Request ID populated successfully.");
	}

	@FindBy(css = "[title='Request ID']")
	private WebElement SearchTrackReq;

	public void populateSearchReqIdSavedForms(String requestID) {
		log.info("Request ID {} :" + requestID);
		sendKeys(SearchTrackReq, requestID);
		log.info("Request ID populated successfully.");
	}

	@FindBy(css = ".f-width:nth-child(8) p input")
	private WebElement txtBoxFirstName;

	@FindBy(css = ".f-width:nth-child(9) p input")
	private WebElement txtBoxLastName;

	@FindBy(css = ".f-width:nth-child(10) p input")
	private WebElement txtBoxMobileNumber;

	@FindBy(css = ".f-width:nth-child(12) p textarea")
	private WebElement txtBoxAdditionalInformation;

	@FindBy(css = "f-width:nth-child(17) p input")
	private WebElement txtBoxStreetNameCmp;

	@FindBy(css = ".f-width:nth-child(19) p input")
	private WebElement txtBoxCity;

	@FindBy(css = ".f-width:nth-child(20) p input")
	private WebElement txtBoxZip;

	@FindBy(css = ".Subject")
	private WebElement txtBoxSubjectReportOutage;

	@FindBy(css = ".f-width:nth-child(21) p input")
	private WebElement txtBoxNearestCrossStreet;

	@FindBy(css = ".tracking_area #txtTrackingId")
	private WebElement txt_PreLogTrackReqSearch;

	@FindBy(css = "#txtSaveId")
	private WebElement txt_PreLogSavedFormsSearch_Cs;

	@FindBy(css = "input[globalize='ML_CONNECTME_Lbl_FName']")
	private WebElement txt_FirstNameCi;

	@FindBy(css = "input[globalize='ML_Register_Lbl_LastName']")
	private WebElement txt_LastNameCiCmp;

	@FindBy(css = "input[globalize='ML_SrvcRqust_txtbx_Contact']")
	private WebElement txt_PriContactNoCi;

	@FindBy(css = "input[globalize='ML_SrvcRqust_txtbx_StreetN']")
	private WebElement txt_StreetNameLoc;

	@FindBy(css = "input[globalize='ML_SrvcRqust_txtbx_city7']")
	private WebElement txt_CityLoc;

	@FindBy(css = "input[globalize='ML_CONNECTME_Lbl_Zip']")
	private WebElement txt_ZipcodeLoc;

	@FindBy(css = "input[globalize='ML_CONNECTME_Lbl_NearestStreet']")
	private WebElement txt_NearestCrossStreetLoc;

	@FindBy(css = "#tblSubmittedForms_filter .form-control.input-sm")
	private WebElement txt_RequestId;

	@FindBy(css = "#tblSubmittedForms td:nth-child(1) .pointer")
	private WebElement txtCaseNumber;

	@FindBy(css = ".datestyleright")
	private WebElement txtTrackRequestDate;

	@FindBy(xpath = "//div[text() ='Billing']")
	private WebElement txtTrackRequestBilling;

	@FindBy(css = ".enquiry-txt")
	private WebElement txtTrackRequestSubjectEnquiry;

	@FindBy(css = "#collapse1 div:nth-child(2) p")
	private WebElement txtTrackRequestDescription;

	@FindBy(css = "#formfieldlist .error_messagecommon")
	private WebElement txtAlertMessage;

	@FindBy(css = "#topic")
	private WebElement lblFaqTopic;

	@FindBy(css = "#topic1")
	private WebElement lblFaqSecTopic;

	@FindBy(xpath = "//div[text() ='Smart Energy Water ']")
	private WebElement txtSocialMediaContent;

	@FindBy(css = "#caseNumber")
	private WebElement lblCaseNumber;

	@FindBy(css = ".lblPaymentReceivedValueBdp")
	private WebElement lblPaymentReceivedValue;

	@FindBy(css = ".Email.input_effect")
	private WebElement txtEmailAddressCmp;

	// TABLE

	@FindBys(@FindBy(css = "#tblSavedForms th"))
	private List<WebElement> th_SavedFormsHeaders;

	public List<WebElement> listSavedFormsHeaders() {
		return th_SavedFormsHeaders;
	}

	public List<String> getSavedFormsHeaders() {
		return getAllElementsTextInList(th_SavedFormsHeaders);
	}

	@FindBys(@FindBy(css = "#tblSubmittedForms th"))
	private List<WebElement> th_TrackReqGridHeader;

	public List<WebElement> listTrackReqGridHeader() {
		return th_TrackReqGridHeader;
	}

	public List<String> getSubmittedFormsHeaders() {
		return getAllElementsTextInList(th_TrackReqGridHeader);
	}

//    @FindBy(css = "#tblSubmittedForms tbody tr > td:nth-child(1) a")
//    private WebElement td_PostLoginTrackReqReqId;

	@FindBys(@FindBy(css = "#tblSubmittedForms tbody tr > td:nth-child(1) a"))
	private List<WebElement> td_PostLoginTrackReqReqId;

	public List<WebElement> listTrackReqID() {
		return td_PostLoginTrackReqReqId;
	}

	@FindBy(css = "#tblSubmittedForms tbody tr > td:nth-child(4) a")
	private WebElement td_PostLoginTrackReqStatus;

	@FindBy(css = "#tblSavedForms tbody tr > td:nth-child(1)")
	private WebElement td_PostLoginSavedFormsReqIdCol;

//    @FindBy(css = "#tblSubmittedForms  tbody tr > td:nth-child(3)")
//    private WebElement td_PostLogTrackReqRaisedOnCol;

	@FindBys(@FindBy(css = "#tblSubmittedForms  tbody tr > td:nth-child(3)"))
	private List<WebElement> td_RaisedOnDate;

	public List<WebElement> listRaisedOnDate() {
		return td_RaisedOnDate;
	}

	@FindBy(css = "#tblSavedForms .dataTables_empty")
	private WebElement td_NoMatchingResultFound;

	@FindBy(css = "select#ML_NCR_DDL_Topic")
	private WebElement dropdown;

	/**
	 * This method is used to get the selected value in the drop down.
	 *
	 * @param locator
	 * @return
	 */
	public String getSelectedValueInDropBox() {
		WebElement selectedOption = null;
		String selectedText = null;
		try {
			// WebElement element = driver.findElement(locator);
			if (dropdown.isDisplayed()) {
				Select selObj = new Select(dropdown);
				selectedOption = selObj.getFirstSelectedOption();
				selectedText = selectedOption.getText();
				log.info("User gets the selection as  " + selectedText + " from Dropdown");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.format("No Element Found to perform selection" + e);

		}
		return selectedText;
	}

	public boolean selectlstConnectMeOptions(String option) {
		boolean b = false;
		selectByVisibleText(dropdown, option);
		log.info("lstConnectMeOptions populated successfully.");
		return b;

	}

	@FindBy(css = "[class='error_messagecommon']")
	private WebElement lblGenericErrorMessage;

	public String getlblGenericErrorMessage() {
		log.info("Fetching the label Generic Error message");
		String label = getText(lblGenericErrorMessage);
		log.info("label Generic Error message {}: " + label);
		return label;
	}

	public boolean islblGenericErrorMessageVisible() {
		log.info("Generic Error message visibility Status :" + lblGenericErrorMessage.isDisplayed());
		return lblGenericErrorMessage.isDisplayed();

	}

	// FAQ

	@FindBy(css = "[class='myaccount_help commonmtr_dgn']")
	private WebElement faqAccountTab;

	public boolean isfaqAccountTabVisible() {
		log.info("FAQ Account Tab visibility Status :" + faqAccountTab.isDisplayed());
		return faqAccountTab.isDisplayed();
	}

	public void clickfaqAccountTab() {
		click(faqAccountTab);
		log.info("FAQ Account Tab clicked {}.");
	}

	@FindBy(css = "[class='billingpmt_help commonmtr_dgn']")
	private WebElement faqBillingTab;

	public boolean isfaqBillingTabVisible() {
		log.info("FAQ Billing Tab visibility Status :" + faqBillingTab.isDisplayed());
		return faqBillingTab.isDisplayed();
	}

	public void clickfaqBillingTab() {
		click(faqBillingTab);
		log.info("FAQ Billing Tab clicked {}.");
	}

	@FindBy(css = "[class='custo_registration commonmtr_dgn']")
	private WebElement faqregistrationTab;

	public boolean isfaqregistrationTabVisible() {
		log.info("FAQ registration Tab visibility Status :" + faqregistrationTab.isDisplayed());
		return faqregistrationTab.isDisplayed();
	}

	public void clickfaqregistrationTab() {
		click(faqregistrationTab);
		log.info("FAQ registration Tab clicked {}.");
	}

	@FindBy(css = "[class='gnrlsupprt_help commonmtr_dgn']")
	private WebElement faqHomeTab;

	public boolean isfaqHomeTabVisible() {
		log.info("FAQ registration Tab visibility Status :" + faqHomeTab.isDisplayed());
		return faqHomeTab.isDisplayed();
	}

	public void clickfaqHomeTab() {
		click(faqHomeTab);
		log.info("FAQ registration Tab clicked {}.");
	}

	@FindBy(css = "[class='outage_related_help commonmtr_dgn']")
	private WebElement faqOutageTab;

	public boolean isfaqOutageTabVisible() {
		log.info("FAQ Outage Tab visibility Status :" + faqOutageTab.isDisplayed());
		return faqOutageTab.isDisplayed();
	}

	public void clickfaqOutageTab() {
		click(faqOutageTab);
		log.info("FAQ Outage Tab clicked {}.");
	}

	@FindBy(css = "[class='prgroms_help commonmtr_dgn']")
	private WebElement faqServiceTab;

	public boolean isfaqServiceTabVisible() {
		log.info("FAQ Service Tab visibility Status :" + faqServiceTab.isDisplayed());
		return faqServiceTab.isDisplayed();
	}

	public void clickfaqServiceTab() {
		click(faqServiceTab);
		log.info("FAQ Service Tab clicked {}.");
	}

	@FindBy(css = "[class='top10faq commonmtr_dgn']")
	private WebElement faqTopTab;

	public boolean isfaqTopTabVisible() {
		log.info("FAQ Top Tab visibility Status :" + faqTopTab.isDisplayed());
		return faqTopTab.isDisplayed();
	}

	public void clickfaqTopTab() {
		click(faqTopTab);
		log.info("FAQ Top Tab clicked {}.");
	}

	// @FindBy(css = "[class='usagecard_help commonmtr_dgn']")
	@FindBy(css = "#ContentPlaceHolder1_freqQues_faqBucket > div:nth-child(11) > a > p > span")

	private WebElement faqUsageTab;

	public boolean isfaqUsageTabVisible() {
		log.info("FAQ Usage Tab visibility Status :" + faqUsageTab.isDisplayed());
		return faqUsageTab.isDisplayed();
	}

	public void clickfaqUsageTab() {
		click(faqUsageTab);
		log.info("FAQ Usage Tab clicked {}.");
	}

	@FindBy(css = "#ContentPlaceHolder1_freqQues_faqBucket > div:nth-child(5) > a > p > span")
	private WebElement faqWaysToSaveTab;

	public boolean isfaqWaysToSaveTabVisible() {
		log.info("FAQ Ways To Save Tab visibility Status :" + faqWaysToSaveTab.isDisplayed());
		return faqWaysToSaveTab.isDisplayed();
	}

	public void clickfaqWaysToSaveTab() {
		click(faqWaysToSaveTab);
		log.info("FAQ Ways To Save Tab clicked {}.");
	}

	@FindBy(css = "[id='topic']")
	private WebElement faqPageTopic;

	public String getFAQPageTopic() {
		log.info("FAQ Page Topic");
		String label = getText(faqPageTopic);
		log.info("FAQ Page Topic is {}: " + label);
		return label;
	}

	public boolean isFAQPageTopicVisible() {
		log.info("FAQ Page Topic visibility Status :" + faqPageTopic.isDisplayed());
		return faqPageTopic.isDisplayed();
	}

	@FindBy(css = ".breadcrumb li a")
	private WebElement lnkFAQPageHelp;

	public void clickFAQPageHelp() {
		click(lnkFAQPageHelp);
		log.info("FAQ Help tab clicked {}.");
	}

	@FindBy(css = "input[globalize = 'ML_CONNECTME_Lbl_AddressReporting']")
	private WebElement txtAdd;

	public boolean istxtAddTxtVisible() throws InterruptedException {
		log.info("Checking that the Address field is visible on the Contact Us page.");
		pause(5000);
		return isElementVisible(txtAdd);
	}

	public void clearAddField() {
		clear(txtAdd);
		log.info("Add field cleared {}");
	}

	public void populateAdd(String address) {
		log.info("address {} :" + address);
		sendKeys(txtAdd, address);
		log.info("Subject populated successfully.");
	}

	@FindBy(css = "input[globalize = 'ML_CONNECTME_Lbl_AddressReportingDesc']")
	private WebElement txtDec;

	public boolean istxtDesTxtVisible() throws InterruptedException {
		log.info("Checking that the Description field is visible on the Contact Us page.");
		pause(5000);
		return isElementVisible(txtDec);
	}

	public void clearDesField() {
		clear(txtDec);
		log.info("Description field cleared {}");
	}

	public void populateDescription(String Description) {
		log.info("Description {} :" + Description);
		sendKeys(txtDec, Description);
		log.info("Description populated successfully.");
	}

	@FindBy(css = "input[globalize = 'ML_CONNECTME_Lbl_YourName']")
	private WebElement txtName;

	public boolean istxtNameTxtVisible() throws InterruptedException {
		log.info("Checking that the Name field is visible on the Contact Us page.");
		pause(5000);
		return isElementVisible(txtName);
	}

	public void clearNameField() {
		clear(txtName);
		log.info("Description field cleared {}");
	}

	public void populateName(String Name) {
		log.info("Description {} :" + Name);
		sendKeys(txtName, Name);
		log.info("Name populated successfully.");
	}

	public boolean ispriContactTxtVisible() throws InterruptedException {
		log.info("Checking that the ConNo field is visible on the Contact Us page.");
		pause(5000);
		return isElementVisible(txt_PriContactNoCi);
	}

	public void clearPriContactNoField() {
		clear(txt_PriContactNoCi);
		log.info("ConNo field cleared {}");
	}

	public void populatePreContactNo(String ConNo) {
		log.info("ConNo {} :" + ConNo);
		sendKeys(txt_PriContactNoCi, ConNo);
		log.info("ConNo populated successfully.");
	}

	public void clearEmailAddField() {
		clear(txtEmailAddress);
		log.info("ConNo field cleared {}");
	}

	public void populateEmailAdd(String EmailAdd) {
		log.info("EmailAdd {} :" + EmailAdd);
		sendKeys(txtEmailAddress, EmailAdd);
		log.info("EmailAdd populated successfully.");
	}

	@FindBy(css = "#navbarDropdown > i")
	private WebElement lnk_threeDot;

	public void clickOnThreeDot() {
		click(lnk_threeDot);
	}

}

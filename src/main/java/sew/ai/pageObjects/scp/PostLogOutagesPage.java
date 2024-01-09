package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PostLogOutagesPage extends HomePage {
    private static final Logger log = LogManager.getLogger(PostLogOutagesPage.class);

    public PostLogOutagesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#PageTitle")
    private WebElement PageHeader;

    public String getOutagesHeader() {
        log.info("Fetching the Outages page header.");
        String label = getText(PageHeader);
        log.info("Login page header is {}: " + label);
        return label;
    }

    public boolean isOutagesVisible() {
        log.info("Outages Page header label visibility Status :" + PageHeader.isDisplayed());
        return PageHeader.isDisplayed();
    }

    @FindBy(css = "a[title='Report Outage'] span:nth-child(3)")
    private WebElement ReportOutage;

    public String getReportOutage() {
        log.info("Fetching the Report Outage");
        String label = getText(ReportOutage);
        log.info("Report Outage is {}: " + label);
        return label;
    }

    public boolean isReportOutageVisible() {
        log.info("Report Outage visibility Status :" + ReportOutage.isDisplayed());
        return ReportOutage.isDisplayed();
    }

    @FindBy(css = "#btnReportOutage")
    private WebElement ReportOutagePost;

    public String getReportOutagePostLabel() {
        log.info("Fetching the Report Outage Post");
        String label = getText(ReportOutagePost);
        log.info("Report Outage Post is {}: " + label);
        return label;
    }

    public boolean isReportOutagePostVisible() {
        log.info("ReportOutagePrOsp :" + ReportOutagePost.isDisplayed());
        return ReportOutagePost.isDisplayed();
    }

    @FindBy(css = ".manage_out_alert")
    private WebElement Notifications;
    public void clickNotificationLnk(){
        click(Notifications);
        log.info("Notification link has been clicked Successfully.");
    }

    public String getNotificationsLabel() {
        log.info("Fetching the Report Outage Post");
        String label = getText(Notifications);
        log.info("Report Outage Post is {}: " + label);
        return label;
    }

    public boolean isNotificationsVisible() {
        log.info("Report Outage Post visibility Status :" + Notifications.isDisplayed());
        return Notifications.isDisplayed();
    }

    @FindBy(css = ".crPrOutage .tbCurrent")
    private WebElement lnkCurrentOutagesPostLogin;
    public void clickCurrentOutagesLnk(){
        click(lnkCurrentOutagesPostLogin);
        log.info("Current Outage Link has been Clicked Successfully.");
    }

    public String getCurrentOutage() {
        log.info("Fetching the Report Outage Post");
        String label = getText(lnkCurrentOutagesPostLogin);
        log.info("Report Outage Post is {}: " + label);
        return label;
    }

    public boolean isCurrentOutageVisible() {
        log.info("Report Outage Post visibility Status :" + lnkCurrentOutagesPostLogin.isDisplayed());
        return isElementVisible(lnkCurrentOutagesPostLogin);
    }

    public String getCurrentOutagePostLabel() {
        log.info("Fetching the Current Outage Post");
        String label = getText(lnkCurrentOutagesPostLogin);
        log.info("Current Outage Post is {}: " + label);
        return label;
    }
    public String getCurrentOutageAttributePost() {
        log.info("Fetching the Current Outage Attribute PostLog");
        String attribute = getAttribute(lnkCurrentOutagesPostLogin,"class");
        log.info("Current Outage Post is {}: " + attribute);
        return attribute;
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
        log.info("Page Header pre Report Out Contact visibility Status :" + lblPageHeaderpreReportOutContact.isDisplayed());
        return lblPageHeaderpreReportOutContact.isDisplayed();
    }

    @FindBy(css = "#lnk_connectme")
    private WebElement lbl_ConnectMePageHeader;

    public void clickConnectMe() {
        click(lbl_ConnectMePageHeader);
        log.info("Connect Me link Clicked.");
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

    @FindBy(css = "#dvPreviewForm .column")
    private WebElement lbl_PreviewYourFormColumn;

    public String getPreviewYourFormColumn() {
        log.info("Fetching Preview Your Form Column");
        String label = getText(lbl_PreviewYourFormColumn);
        log.info("Preview Your Form Column is {}: " + label);
        return label;
    }

    public boolean isPreviewYourFormColumnVisible() {
        log.info("Preview Your Form Column visibility Status :" + lbl_PreviewYourFormColumn.isDisplayed());
        return lbl_PreviewYourFormColumn.isDisplayed();
    }

    @FindBy(css = "#dvPreviewForm .value")
    private WebElement lbl_PreviewYourFormValue;

    public String getPreviewYourFormValue() {
        log.info("Fetching Preview Your Form Value");
        String label = getText(lbl_PreviewYourFormValue);
        log.info("Preview Your Form Column value is {}: " + label);
        return label;
    }

    public boolean isPreviewYourFormValueVisible() {
        log.info("Preview Your Form Column visibility Status :" + lbl_PreviewYourFormValue.isDisplayed());
        return lbl_PreviewYourFormValue.isDisplayed();
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
        log.info("Value Track Request Sidebar Req Raised On visibility Status :" + lbl_ValueTrackReqSidebarReqRaisedOn.isDisplayed());
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

    /*public boolean isCurrentOutagePostVisible() {
        log.info("Current Outage Post visibility Status :" + CurrentOutagePost.isDisplayed());
        return CurrentOutagePost.isDisplayed();
    }*/

    @FindBy(css = ".crPrOutage .tbPlanned")
    private WebElement PlannedOutage;

    public String getPlannedOutage() {
        log.info("Fetching the Planned Outage");
        String label = getText(PlannedOutage);
        log.info("Planned Outage Post is {}: " + label);
        return label;
    }

    public boolean isPlannedOutageVisible() {
        log.info("Planned Outage visibility Status :" + PlannedOutage.isDisplayed());
        return PlannedOutage.isDisplayed();
    }

    @FindBy(css = ".crPrOutage .tbPlanned")
    private WebElement PlannedOutagePost;

    public String getPlannedOutagePostLabel() {
        log.info("Fetching the Planned Outage");
        String label = getText(PlannedOutage);
        log.info("Planned Outage Post is {}: " + label);
        return label;
    }

    public boolean isPlannedOutagePostVisible() {
        log.info("Planned Outage visibility Status :" + PlannedOutage.isDisplayed());
        return PlannedOutage.isDisplayed();
    }

    @FindBy(css = "#LeftPanel >  div:nth-child(1) > table:nth-child(4) td strong")
    private WebElement lblInfoOutageDetailsLb;

    public String getlblInfoOutageDetails() {
        log.info("Fetching the Info Outage Detail");
        String label = getText(lblInfoOutageDetailsLb);
        log.info("Info Outage Detail is {}: " + label);
        return label;
    }

    public boolean isInfoOutageDetailsLbVisible() {
        log.info("Info Outage Detail visibility Status :" + lblInfoOutageDetailsLb.isDisplayed());
        return lblInfoOutageDetailsLb.isDisplayed();
    }

    @FindBy(css = "#LeftPanel >  div:nth-child(1) > table:nth-child(4) td")
    private WebElement lblInfoOutageDetailsVal;

    public String getInfoOutageDetailsVal() {
        log.info("Fetching the Info Outage Details Val");
        String label = getText(lblInfoOutageDetailsVal);
        log.info("Info Outage Detail is {}: " + label);
        return label;
    }

    public boolean isInfoOutageDetailsValVisible() {
        log.info("Info Outage Detail visibility Status :" + lblInfoOutageDetailsVal.isDisplayed());
        return lblInfoOutageDetailsVal.isDisplayed();


    }

    @FindBy(css = "//*[@id='LeftPanel']/div[1]/table[2]/tbody/tr[4]/td")
    private WebElement lblCustomersAffFirst;

    public String getCustomersAffFirst() {
        log.info("Fetching the Info Customers Aff First");
        String label = getText(lblCustomersAffFirst);
        log.info("Customers Aff First is {}: " + label);
        return label;
    }

    public boolean isCustomersAffFirstVisible() {
        log.info("Info Customers Aff First visibility Status :" + lblCustomersAffFirst.isDisplayed());
        return lblCustomersAffFirst.isDisplayed();

    }

    @FindBy(css = ".toast.toast-error")
    private WebElement lblMsgSearchErr;

    public String getlblMsgSearchErr() {
        log.info("Fetching the lbl Msg Search Error");
        String label = getText(lblMsgSearchErr);
        log.info("lbl Msg Search Error is {}: " + label);
        return label;
    }

    public boolean islblMsgSearchErrVisible() {
        log.info("the lbl Msg Search Error visibility Status :" + lblMsgSearchErr.isDisplayed());
        return lblMsgSearchErr.isDisplayed();

    }

    @FindBy(css = "div#iw-container")
    private WebElement lblOutageMapContent;

    public String getlblOutageMapContent() {
        log.info("Fetching the lbl Outage Map Content");
        String label = getText(lblOutageMapContent);
        log.info("lbl Msg Search Error is {}: " + label);
        return label;
    }

    public boolean islblOutageMapContentVisible() {
        log.info("the lbl Msg Search Error visibility Status :" + lblOutageMapContent.isDisplayed());
        return lblOutageMapContent.isDisplayed();

    }

    @FindBy(css = "td.blue")
    private WebElement lblOutageTitle;

    public String getlblOutageTitle() {
        log.info("Fetching the lbl Outage Title");
        String label = getText(lblOutageTitle);
        log.info("lbl Outage Title is {}: " + label);
        return label;
    }

    public boolean islblOutageTitleVisible() {
        log.info("the lbl  Outage Title visibility Status :" + lblOutageTitle.isDisplayed());
        return lblOutageTitle.isDisplayed();

    }

    @FindBy(css = ".MessageContainer:first-child .border td")
    private WebElement lblOutageReportInfo;

    public String getlblOutageReportInfo() {
        log.info("Fetching the lbl Outage Report Info");
        String label = getText(lblOutageReportInfo);
        log.info("lbl Outage Report Info is {}: " + label);
        return label;
    }

    public boolean islblOutageReportInfoVisible() {
        log.info("lbl Outage Report Info visibility Status :" + lblOutageReportInfo.isDisplayed());
        return lblOutageReportInfo.isDisplayed();

    }

    @FindBy(css = ".MessageContainer:first-child tr:nth-child(2) td")
    private WebElement lblOutageDateTime;

    public String getlblOutageDateTime() {
        log.info("Fetching the lbl Outage Date Time");
        String label = getText(lblOutageDateTime);
        log.info("Outage Date Time is {}: " + label);
        return label;
    }

    public boolean islblOutageDateTimeVisible() {
        log.info("Outage Date Time  visibility Status :" + lblOutageDateTime.isDisplayed());
        return lblOutageDateTime.isDisplayed();

    }

    @FindBy(css = ".MessageContainer:first-child tr:nth-child(3) td")
    private WebElement lblOutageRestDateTime;

    public String getlblOutageRestDateTime() {
        log.info("Fetching the Outage Rest Date Time");
        String label = getText(lblOutageRestDateTime);
        log.info("Outage Rest Date Time is {}: " + label);
        return label;
    }

    public boolean islblOutageRestDateTimeVisible() {
        log.info("Outage Rest Date Time visibility Status :" + lblOutageRestDateTime.isDisplayed());
        return lblOutageRestDateTime.isDisplayed();

    }

    @FindBy(css = ".MessageContainer:first-child tr:nth-child(4) td")
    private WebElement lblOutageCustomerAffCount;

    public String getlblOutageCustomerAffCount() {
        log.info("Fetching the Outage Rest Date Time");
        String label = getText(lblOutageRestDateTime);
        log.info("Outage Rest Date Time is {}: " + label);
        return label;
    }

    public boolean islblOutageCustomerAffCountVisible() {
        log.info("Outage Rest Date Time visibility Status :" + lblOutageRestDateTime.isDisplayed());
        return lblOutageRestDateTime.isDisplayed();

    }

    @FindBy(css = ".MessageContainer:first-child tr:nth-child(5) td")
    private WebElement lblOutageCommunityAff;

    public String getlblOutageCommunityAff() {
        log.info("Fetching the lbl Outage Community Aff");
        String label = getText(lblOutageCommunityAff);
        log.info("lbl Outage Community Aff is {}: " + label);
        return label;
    }

    public boolean islblOutageCommunityAffVisible() {
        log.info("lbl Outage Community Aff visibility Status :" + lblOutageCommunityAff.isDisplayed());
        return lblOutageCommunityAff.isDisplayed();

    }

    @FindBy(css = ".iw-content")
    private WebElement MapPinContent;

    public String getMapPinContent() {
        log.info("Fetching the lbl Map Pin Content");
        String label = getText(MapPinContent);
        log.info("Map Pin Content  is {}: " + label);
        return label;
    }

    public boolean isMapPinContentVisible() {
        log.info("lbl Outage Community Aff visibility Status :" + MapPinContent.isDisplayed());
        return MapPinContent.isDisplayed();

    }

    @FindBy(css = "#ContentPlaceHolder1_ContentPlaceHolderSearch_lblNotificationOutage")
    private WebElement NotificationCount;

    public String getNotificationCount() {
        log.info("Fetching the lbl Map Pin Content");
        String label = getText(MapPinContent);
        log.info("Map Pin Content  is {}: " + label);
        return label;
    }

    public boolean isNotificationCountVisible() {
        log.info("lbl Outage Community Aff visibility Status :" + MapPinContent.isDisplayed());
        return MapPinContent.isDisplayed();

    }
    @FindBy(css = "#outage_map_canvas")
    private WebElement can_MapOutages;
    public boolean isMapCanvasVisible(){
        return isElementVisible(can_MapOutages);
    }
    @FindBy(css = "#btnReportOutage")
    private WebElement lnkReportOutagesPostLogin;
    public void clickReportOutages(){
        click(lnkReportOutagesPostLogin);
        log.info("Report outages link has been clicked Successfully.");
    }
    public boolean isReportOutageBtnVisible(){
        return isElementVisible(lnkReportOutagesPostLogin);
    }



    @FindBy(css = "#LeftPanel center")
    private WebElement lbl_NoOutagesMsg;
    @FindBy(css = "#LeftPanel >  div > table:nth-child(4)  tr:nth-child(5) td")
    private WebElement lbl_CommunityAffected;
    @FindBy(css = "#LeftPanel >  div > table:nth-child(4)  tr:nth-child(4) td")
    private WebElement lbl_CustomersAffected;
    @FindBy(css = "#LeftPanel >  div > table:nth-child(4)  tr:nth-child(2) td")
    private WebElement lbl_DateTimeOfOutage;
    @FindBy(css = "#LeftPanel >  div > table:nth-child(4)  tr:nth-child(3) td")
    private WebElement lbl_DateTimeOfRestoration;
    @FindBy(css = "#LeftPanel >  div > table:nth-child(4)  tr:nth-child(1) td")
    private WebElement lbl_OutageMessage;
    @FindBy(css = "button[title='Show street map']")
    private WebElement lbl_MapBtn;
    public String getMapButtonLabel() {
        log.info("Fetching the Label of --->>Map button ");
        String label = getText(lbl_MapBtn);
        log.info("Map Button Label is {}: " + label);
        return label;
    }
    public void mouseHoverMapBtn() {
        mouseHover(lbl_MapBtn);
    }
    @FindBy(css = "button[title='Show satellite imagery']")
    private WebElement lbl_Satellite;
    @FindBy(css = "li[title='Show street map with terrain'] label")
    private WebElement lbl_TerrainMapType;
    public String getTerrainMapLabel() {
        log.info("Fetching the Label of --->>Terrain Map button ");
        String label = getText(lbl_TerrainMapType);
        log.info("Terrain Map Button Label is {}: " + label);
        return label;
    }
    public boolean isTerrainMapVisibel(){
        log.info("Featching te visiblity if Terrain Map.");
        return  isElementVisible(lbl_TerrainMapType);
    }
    @FindBy(css = ".w2ui-tag-body.w2ui-tag-top")
    private WebElement lbl_BlankValidationTop;
    @FindBy(xpath = "//span[@id='totalOutageSpan']/following-sibling::label")
    private WebElement lblTotalNoOutages;
    public String getTotalNoOutagesLabel() {
        log.info("Fetching the Current Outage Post");
        String label = getText(lblTotalNoOutages);
        log.info("Total Outages Label is {}: " + label);
        return label;
    }

    public boolean isTotalNoOutagesVisible() {
        log.info("Total outages visibility Status :" + lblTotalNoOutages.isDisplayed());
        return isElementVisible(lblTotalNoOutages);
    }

    @FindBy(xpath = "//span[@id='totalAffectedSpan']/following-sibling::label")
    private WebElement lblTotalAffected;
    public String getTotalNoAffectedOutagesLabel() {
        log.info("Fetching the TotalNoAffectedOutages Label");
        String label = getText(lblTotalAffected);
        log.info("Total Outages Label is {}: " + label);
        return label;
    }
    public boolean isTotalNoAffectedOutagesVisible() {
        log.info("TotalNo of Affected Outages visibility Status :" + lblTotalAffected.isDisplayed());
        return isElementVisible(lblTotalAffected);
    }
    @FindBy(xpath = "//span[@id='totalServedSpan']/following-sibling::label")
    private WebElement lblTotalServed;
    public String getTotalNoOfServedOutagesLabel() {
        log.info("Fetching the TotalNoAffectedOutages Label");
        String label = getText(lblTotalServed);
        log.info("Total Served Outages Label is {}: " + label);
        return label;
    }
    public boolean isTotalNoOfServedOutagesVisible() {
        log.info("TotalNo of Served Outages visibility Status :" + lblTotalServed.isDisplayed());
        return isElementVisible(lblTotalServed);
    }
    @FindBy(css = "#totalOutageSpan")
    private WebElement valTotalNoOutage;
    public boolean isValTotalNoOutageVisible() {
        log.info("Fetching the Value of >> Total no of Outages visibility Status :" + valTotalNoOutage.isDisplayed());
        return isElementVisible(valTotalNoOutage);
    }
    @FindBy(css = "#totalPOutageSpan")
    private WebElement valTotalPNoOuta;
    public String getTotalPlannedOutageLabel(){
        String label=getText(valTotalPNoOuta);
        return label;
    }
    @FindBy(css = "#totalAffectedSpan")
    private WebElement valTotalAffectedOutage;
    public String getTotalAffectedOutageLabel(){
        String label=getText(valTotalAffectedOutage);
        return label;
    }
    public boolean isValTotalAffectedOutageVisible() {
        log.info("Fetching the Value of >> Total no of Affected Outages visibility Status :" + valTotalAffectedOutage.isDisplayed());
        return isElementVisible(valTotalAffectedOutage);
    }
    @FindBy(css = "#totalPAffectedSpan")
    private WebElement valTotalAffected;
    public String getTotalAffectedLabel(){
        String label= getText(valTotalAffected);
        return label;
    }
    @FindBy(css = "#totalServedSpan")
    private WebElement valTotalServedOutage;
    public boolean isValTotalServedOutageVisible() {
        log.info("Fetching the Value of >> Total no of Served Outages visibility Status :" + valTotalServedOutage.isDisplayed());
        return isElementVisible(valTotalServedOutage);
    }
    public String getTotalServedOutageLabel(){
        String label=getText(valTotalServedOutage);
        return label;
    }
    @FindBy(css = "#totalPServedSpan")
    private WebElement valTotalPSer;
    public String getTotalPlannedOutageServedLabel(){
        String label=getText(valTotalPSer);
        return label;
    }
    @FindBy(css = "#headingTwo1 a h5")
    private WebElement lblMapLegent;
    public boolean isMapLegentVisible(){
        return isElementVisible(lblMapLegent);
    }
    public String getMapLegentLabel(){
        String label=getText(lblMapLegent);
        return label;
    }


    @FindBy(css = "#accordion2 > div:nth-child(1)  a span")
    private WebElement lblMapLegendDdPre;
    @FindBy(css = "#headingTwo2 a h5")
    private WebElement lblWeatherTog;
    public boolean isWeatherToggleVisible(){
        return isElementVisible(lblWeatherTog);
    }
    public String getWeatherToggleLabel(){
        String label=getText(lblWeatherTog);
        return label;
    }
    @FindBy(css = "#accordion2 > div:nth-child(2)  a span")
    private WebElement lblWeatherDdPre;
    @FindBy(css = "#switch2lbl")
    private WebElement toggleWeather;
    @FindBy(xpath = "//li[@class='outershowContent'][@type='list']/ae")
    private WebElement toggleListView;
    @FindBy(xpath = "//li[@class='outershowContent'][@type='map']/a")
    private WebElement toggleMapView;

    @FindBy(css = "#County_Tab a ")
    private WebElement tabCountryListViewOsp;
    public String getCityListViewLabel(){
        String label=getText(tabCountryListViewOsp);
        return label;
    }
    @FindBy(css = "#Zip_Tab a ")
    private WebElement tabZipCodeListViewOsp;
    public void clickZipCode(){
        click(tabZipCodeListViewOsp);
        log.info("Zip code link is clicked Successfully.");
    }
    public String getZipCodeLabel(){
        String label=getText(tabZipCodeListViewOsp);
        return label;
    }
    @FindBy(css=".table_country #table1 th")
    private WebElement tabHeadCountryGrid1;
    public String getHeadCountryLabel(){
       String label= getText(tabHeadCountryGrid1);
       return label;
    }


    @FindBys(@FindBy(css = ".table_upper table thead th"))
    private List<WebElement> tabHeadCountryGrid;
    public List<WebElement> getHeadCountryGridElement(){
        return tabHeadCountryGrid;
    }
    
    @FindBy(xpath = ".//tbody[@id='tbodyid']//a/ancestor::tr")
    private WebElement tableMainRow;
    
  @FindBys(@FindBy(xpath = "//tbody[@id='tbodyid']/tr/td[1]"))
  private List<WebElement> tableCompleteColArea;
  public List<WebElement> getTableCompleteAreaElement(){
      return tableCompleteColArea;
  }
  public void clickJSTableCompleteArea(WebElement element){
      //click(element);
      clickWithJSExecutor(element);
  }
  public String getTableCompleteColLabel(WebElement element){
      String label=getText(element);
      return label;
  }

  @FindBys(@FindBy(css = "#tbodyid .order1.collapse.in td:first-child"))
  private List<WebElement> tableOrder1ColArea;
  public List<WebElement> getTableOrderElement(){
      return tableOrder1ColArea;
  }
  public String getTableOrderLabel(WebElement element){
      String label=getText(element);
      return label;
  }
//
//    @FindBy(xpath = "//tbody[@id='tbodyid']//a/ancestor::tr/td[3]")
//    private WebElement tableCompleteColArea;
//
//    @FindBy(xpath = "//tbody[@id='tbodyid']//a/ancestor::tr/td[2]")
//    private WebElement tableCompleteColArea;
//
//    @FindBy(xpath = "//div[contains(@style, 'url(\"images/')]")
//    private WebElement tableCompleteColArea;


    @FindBys(@FindBy(css = "div[role='button']"))
    private List<WebElement> isSinglePinMap;
  public List<WebElement> getSinglePinMapElement(){
      log.info("Featching the list of WebElement of Single Pin Map Outages.");
      return isSinglePinMap;
  }
    @FindBy(css = "#myModalLabel2")
    private WebElement headerOutageDetailsDrawer;
    public String outagesDetsilsLabel(){
        log.info("Fetching the label of Outages Details.");
        String label=getText(headerOutageDetailsDrawer);
        return label;
    }
    @FindBy(css = "#divEstimatedRestoration label")
    private WebElement lblEstResOutageDrawer;
    public String estResOutageDrawerLabel(){
        log.info("Fetching the label of EstResOutageDrawer.");
        String label=getText(lblEstResOutageDrawer);
        return label;
    }
    @FindBy(css = "#RestorationdateSpan")
    private WebElement valEstResOutageDrawer;
    public boolean isEstResOutageVisible(){
        return isElementVisible(valEstResOutageDrawer);
    }
    @FindBy(css = "#divCustomersOut label")
    private WebElement lblCustomersOutDrawer;
    public boolean isCustomerOutDrawerVisible(){
        return isElementVisible(lblCustomersOutDrawer);
    }
    public String customoerOutDrawerLabel(){
        String label=getText(lblCustomersOutDrawer);
        return label;
    }
    @FindBy(css = "#CustomerAffectedSpan")
    private WebElement valCustomersOutDrawer;
 public boolean isValCustomerOutDrawerVisible(){
     log.info("Featching the visiblity of Value Customer Out Drawer");
     return isElementVisible(valCustomersOutDrawer);
 }
    @FindBy(css = "#divOutageStart label")
    private WebElement lblOutageStartDrawer;
    public boolean isOutageStartDrawerVisible(){
        log.info("Featching the visiblity of Outage Start Drawer");
        return isElementVisible(lblOutageStartDrawer);
    }
    public String outageStartDrawerLabel(){
        log.info("Featching the lable of Outages Start Drawer .");
        String label=getText(lblOutageStartDrawer);
        return label;
    }
    @FindBy(css = "#OutagedateSpan")
    private WebElement valOutageStartDrawer;
    @FindBy(css = ".side_popup_detail.od_2 label")
    private WebElement lblNoOfOutDrawer;
    @FindBy(css = "#NoOfOutagesSpan")
    private WebElement valNoOfOutDrawer;
    @FindBy(css = ".zoom_btn")
    private WebElement btnZoomHereDrawer;
    @FindBy(css = "#myModal2 .close")
    private WebElement btnCloseDrawer;
    @FindBy(css = "#ZipOrCountySearch")
    private WebElement icoSearchOutage;

    @FindBy(css = "#ZipOrCountySearch")
    private WebElement lnkSearchIcon;
    public void clickSearchIcon(){
        click(lnkSearchIcon);
        log.info("Search icon has been clicked Successfully.");
    }
    
//    @FindBy(css = "a[globalize="ML_Outage_span_Report_Outage"]")
//    private WebElement lnk_ReportOutage;
    
    @FindBy(css = "a[title='Click to Report an Outage ']")
    private WebElement lnk_ReportOutagePost;
    @FindBy(css = ".manage_out_alert")
    private WebElement lnk_Notifications;
    @FindBy(css = "li[globalize='ML_Outage_span_Current'] a")
    private WebElement lnk_CurrentOutage;
    @FindBy(css = "a[globalize='ML_Outage_span_Current']")
    private WebElement lnk_CurrentOutagePost;
    @FindBy(css = "li[globalize='ML_Outage_span_Planned'] a")
    private WebElement lnk_PlannedOutage;


    @FindBy(css = "span.head_icon_flat.icon_current")
    private WebElement lnkCurrentLocationIcon;
    @FindBy(css = "[id='btnRefresh']")
    private WebElement lnkRefreshIcon;

    @FindBy(css = ".crPrOutage .tbPlanned")
    private WebElement lnkPlannedOutagesPostLogin;
    public void clickPlannedOutages(){
        click(lnkPlannedOutagesPostLogin);
        log.info("Planned Outage link clicked Successfully.");
    }
    @FindBy(css = "lblMsgBlankLoginLgp .cover_right_top_area li:nth-child(2) a")
    private WebElement lnkNotification;
    @FindBy(css = "#ZipOrCountySearch")
    private WebElement lnk_SearchIconPostLog;
    @FindBy(css = "a .lblBg.get_notification_outage")
    private WebElement lnkUnreadNotifications;
    
    @FindBy(css =".outershowContent a")
    private WebElement lnkOuterShowList;
    public void clickOutageShowList(){
        click(lnkOuterShowList);
        log.info("Outage show list has been clicked successfully.");
    }

    @FindBy(css = "#ZipOrCountyInputSearch")
    private WebElement txt_SearchByCityZip;
    public void populateSearchByCity(String zip ){
        sendKeys(txt_SearchByCityZip,zip);
        log.info("Zip Code has been populated successfully.");
    }
    public void clearSearchByCity(){
        clear(txt_SearchByCityZip);
        log.info("Seaarch by city has been cleared Successfully.");
    }
    public boolean isSearchByCityVisible(){
        log.info("Checking the visibility of Search By City Search box");
        return isElementVisible(txt_SearchByCityZip);
    }
    public String getSearchByCityZipAttribute(){
        log.info("Fetching the placeholder of zipcode input box");
        String attribute=getAttribute(txt_SearchByCityZip,"placeholder");
        return attribute;
    }
    public void clickSearchByCityZip(){
        click(txt_SearchByCityZip);
        log.info("Prelogging Outage link has been Successfully clicked");
    }
    public void populateSearchByCityZip(String zipcode){
        sendKeys(txt_SearchByCityZip,zipcode);
        log.info("ZipCode has been Successfully Populated");
    }
    public void clearSearchByCityZip(){
        clear(txt_SearchByCityZip);
        log.info("Search By City/Zip input txt box has been successfully Cleared.");
    }

    @FindBy(css = ".search-area")
    private WebElement txtSearchBox;
    @FindBy(css = ".map_address_area center")
    private WebElement txtOutageMessage;
    @FindBy(css = "input#TxtOutageText1")
    private WebElement txtOutageNotification;


    @FindBy(css = "srch")
    private WebElement btnSearchIcon;
    @FindBy(css = "#toast-container button")
    private WebElement btn_CloseToastMsg;
    @FindBy(css = "span[id='imgCurrent']")
    private WebElement ico_CurrentLocationPr;
    @FindBy(css = "#imgCurrent2")
    private WebElement ico_CurrentLocation;
    @FindBy(css = "span[id='btnRefresh']")
    private WebElement ico_RefreshMapPr;
    @FindBy(css = "#btnRefresh")
    private WebElement ico_RefreshMap;
    @FindBy(css = "div[style*='z-index: 4'] img[src='images/outages/user-pin-icon_M.png']")
    private WebElement ico_MapCurrentLocPin;

    @FindBy(css = ".map_address_area .MessageContainer")
    private WebElement lstOutages;
    @FindBy(css = "#LeftPanel > .MessageContainer:nth-child(1)")
    private WebElement div_FirstMessageContai;
    @FindBy(css = "#LeftPanel > .MessageContainer")
    private WebElement div_MessageContain;
    @FindBy(css = "#page_loader")
    private WebElement div_PageLoader;
    
    @FindBy(css = ".collapsed h5.heading i")    
    private WebElement divMapLegendWhetherDropdown;
    public boolean isMapLegentDropDownVisible(){
        return isElementVisible(divMapLegendWhetherDropdown);
    }
    
    @FindBy(xpath = "//div[@id='LeftPanel']/div[contains(@class, 'MessageContainer')]//tbody")
    private WebElement liOutageBox;
    @FindBy(css = "button[title='Toggle fullscreen view']")
    private WebElement btnToggleScreen;
    public void clickToggleBtn(){
        click(btnToggleScreen);
        log.info("Toggle Screen Btn is Successfully clicked.");
    }

    @FindBys(@FindBy(css = "#fixedinfo .left_side_sec *[mandatory='1']"))
    private List<WebElement> repOutageFormMandatoryFields;
    public List<WebElement> getOutageFormMandatoryFieldElement(){
        return repOutageFormMandatoryFields;
    }
    @FindBys(@FindBy(css = "#fixedinfo .left_side_sec *[mandatory='1'] ~ label[class='effect_lbl']"))
    private List<WebElement> repOutageFormMandatoryLbl;
    public List<WebElement> getOutageFormMandatoryLblElement(){
        return repOutageFormMandatoryLbl;
    }
    public String outagesFormMandatoryLabel(WebElement element){
        String label=getText(element);
        return label;
    }

    @FindBys(@FindBy(css = "#fixedinfo .left_side_sec .effect_lbl"))
    private List<WebElement> repOutageFormFieldsLbl;
public List<WebElement> getOutageFormFieldElement(){
    return repOutageFormFieldsLbl;
}
public String getOutagesFormFieldLabel(WebElement element){
    String label=getText(element);
    return label;
}
    @FindBys(@FindBy(css = "#fixedinfo .left_side_sec input:nth-child(1)"))
    private List<WebElement> repOutageFormInputFields;
public List<WebElement> getOutageFromInputElement(){
    return repOutageFormInputFields;
}

    @FindBys(@FindBy(css = "#fixedinfo .left_side_sec select:nth-child(1)"))
    private List<WebElement> repOutageFormSelectField;
    public List<WebElement> getOutageFromSelectElement(){
        return repOutageFormSelectField;
    }
    @FindBy(css = "#ui-id-1 .ui-menu-item div:first-child")
    private WebElement repOutageFormSelectState;
    public void clickOutageFormSelectState(){
        click(repOutageFormSelectState);
    }
    @FindBy(css = "#ui-id-1 .ui-menu-item a:first-child")
    private WebElement repOutageFormSelectStatePreLog;
    @FindBy(css = "#BtnNextCommentNew")
    private WebElement btnNextRepOutage;
    public void clickReportOutageNextBtn(){
        click(btnNextRepOutage);
    }
    @FindBy(css = "#btnNext")
    private WebElement btnNextRepOutPre;
    @FindBy(css = "#fixedinfo .left_side_sec textarea:nth-child(1)")
    private List<WebElement> repOutageFormTextArea;
    public List<WebElement> getOutageFromtextElement(){
        return repOutageFormTextArea;
    }
    @FindBy(css = "#BtnSubmitCommentNew")
    private WebElement btnSubmitRepOutage;
    public void clickSubmitReportOutage(){
        click(btnSubmitRepOutage);
        log.info("Report Outage submit btn clicked successfully.");
    }
    @FindBy(css = ".heading3_formpreview")
    private WebElement lblHeadingSubmitOutage;
    public String getHeadingSubmitOutageLabel(){
        String label=getText(lblHeadingSubmitOutage);
        return label;
    }
    @FindBy(css = "#btnok")
    private WebElement btnOkConfSubmitOutage;
    public void clickOkConfSubmitOutageBtn(){
        clickWithJSExecutor(btnOkConfSubmitOutage);
    }

    @FindBy(css = "#txtBody")
    private WebElement lblConfMsgSubmitOutage;
    public String confMsgSubmitOutageLabel(){
        String label=getText(lblConfMsgSubmitOutage);
        return label;
    }

    @FindBy(css = "#ContentPlaceHolder1_ContentPlaceHolderBody_OutageNotification  .accordion-toggle")
    private WebElement lnkOutageNotifPref;
    public void clickOutageNotifPref(){
        click(lnkOutageNotifPref);
        log.info("Outagae Notofication Preference Link clicked Successfully.");
    }
    @FindBy(css = "#ContentPlaceHolder1_ContentPlaceHolderBody_OutageNotification h5")
    private WebElement lblHeadOutageNotifPref;
    public String getHeadOutageNotifPrefLabel(){
        String label=getText(lblHeadOutageNotifPref);
        log.info("Head Outage notofication Preference Label.");
        return label;
    }
    @FindBy(css = "#ContentPlaceHolder1_ContentPlaceHolderBody_OutageNotification  a > span:nth-child(2)")
    private WebElement lblSubHeadOutageNotifPref;
    @FindBy(css = "#outageCancel")
    private WebElement btnCancelOutageNotifPref;
    @FindBy(css = "#BtnOutageNotificationPrefrence")
    private WebElement btnSaveOutageNotifPref;
    public void clickSaveOutageNotifPref(){
        click(btnSaveOutageNotifPref);
    }
    @FindBy(css = "#add_Outage")
    private WebElement btnAddOutageNotifPref;
    public void clickAddOutageNotifPref(){
        click(btnAddOutageNotifPref);
    }
    @FindBys(@FindBy(css = "//a[contains(@id, 'remove_Outage')]"))
    private List<WebElement> btnRemoveOutageNotifPref;
    public List<WebElement> getBtnRemoveOutageNotifPrefElement(){
        return btnRemoveOutageNotifPref;
    }
    @FindBys(@FindBy(css = "//select[contains(@id, 'ddlOutageOption')]"))
    private List<WebElement> selectChannelOutageNotifPref;
    public List<WebElement> getSelectChannelOutageElement(){
        return selectChannelOutageNotifPref;
    }
    public void clickChannelOutageNotifPref(WebElement element){
        click(element);
    }
    @FindBys(@FindBy(xpath = "//input[contains(@id, 'TxtOutageText')]"))
    private List<WebElement> txtChannelValOutageNotifPref;
    public List<WebElement> getChannelValOutageNotiPrefElement(){
        return txtChannelValOutageNotifPref;
    }
    public boolean isPostLogOutagePage(String url, String title){
        boolean postLogOutagePageStatus=false;
        if(getCurrentUrl().contains(url) && getCurrentTitle().contains(title)){
            postLogOutagePageStatus=true;
        }
        return postLogOutagePageStatus;
    }
}

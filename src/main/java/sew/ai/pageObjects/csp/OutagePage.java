package sew.ai.pageObjects.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sew.ai.pageObjects.scp.HomePage;

import java.util.List;

public class OutagePage extends HomePage {

    private static final Logger log = LogManager.getLogger(OutagePage.class);

    public OutagePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".toptb_lnk_outage > a[href='Outages.aspx']")
    private WebElement lblOutagePageHeading;

    public String getLblOutagePageHeading() {
        String label = getText(lblOutagePageHeading);
        log.info("Admin outage page heading is " + label);
        return label;
    }

    @FindBy(css = "a[onclick='openExportDataPopUp()']")
    private WebElement lnkExport;
    public Boolean isLnkExportVisible() {
        boolean isVisible = isElementVisible(lnkExport);
        log.info("Admin outage export link visibility status is" + isVisible);
        return isVisible;
    }

    public String getLblLnkExport() {
        String label = getText(lnkExport);
        log.info("Admin outage export link Text is " + label);
        return label;
    }
    @FindBy(id = "btnAdd")
    private WebElement lnkAddOutage;
    public Boolean isLnkAddOutageVisible() {
        boolean isVisible = isElementVisible(lnkAddOutage);
        log.info("Add outage link visibility status is" + isVisible);
        return isVisible;
    }

    public String getLblLnkAddOutage() {
        String label = getText(lnkAddOutage);
        log.info("Add outage link Text is " + label);
        return label;
    }
    @FindBy(id = "demandusagetext")
    private WebElement tabPlannedOutage;
    public String getLblTabPlannedOutage() {
        String label = getText(tabPlannedOutage);
        log.info("Planned outage tab Text is " + label);
        return label;
    }
    @FindBy(id = "unplannedtext")
    private WebElement tabUnplannedOutage;
    public String getLblTabUnplannedOutage() {
        String label = getText(tabUnplannedOutage);
        log.info("Unplanned outage tab Text is " + label);
        return label;
    }
    @FindBy(id = "totaltext")
    private WebElement tabTotalOutage;
    public String getLblTabTotalOutage() {
        String label = getText(tabTotalOutage);
        log.info("Total outage tab Text is " + label);
        return label;
    }
    @FindBy(id = "lblplanned")
    private WebElement lblCountPlannedOutages;
    public String getLblCountPlannedOutages() {
        String label = getText(lblCountPlannedOutages);
        log.info("Planned Outages Count is " + label);
        return label;
    }
    @FindBy(id = "lblunplanned")
    private WebElement lblCountUnplannedOutages;
    public String getLblCountUnplannedOutages() {
        String label = getTextWithoutCheckingVisibility(lblCountUnplannedOutages);
        log.info("Unplanned Outages Count is " + label);
        return label;
    }
    @FindBy(id = "lbltotal")
    private WebElement lblCountTotalOutages;
    public String getLblCountTotalOutages() {
        String label = getText(lblCountTotalOutages);
        log.info("Total Outages Count is " + label);
        return label;
    }
    @FindBy(id = "gridView")
    private WebElement lnkTabGridView;
    public Boolean isLnkTabGridViewVisible() {
        boolean isVisible = isElementVisible(lnkTabGridView);
        log.info("Grid view tab visibility status is" + isVisible);
        return isVisible;
    }
    public String getLblLnkTabGridView() {
        String label = getText(lnkTabGridView);
        log.info("Grid view tab Label is " + label);
        return label;
    }
    @FindBy(id = "mapView")
    private WebElement lnkTabMapView;

    public Boolean isLnkTabMapViewVisible() {
        boolean isVisible = isElementVisible(lnkTabMapView);
        log.info("Map view tab visibility status is" + isVisible);
        return isVisible;
    }
    public String getLblLnkTabMapView() {
        String label = getText(lnkTabMapView);
        log.info("Map view tab Label is " + label);
        return label;
    }
    @FindBy(id = "lnkPlanned")
    private WebElement lnkFilterPlannedOutage;
    public Boolean isLnkFilterPlannedOutageVisible() {
        boolean isVisible = isElementVisible(lnkFilterPlannedOutage);
        log.info("Filter Planned outage link on Grid visibility status is" + isVisible);
        return isVisible;
    }
    public String getLblLnkFilterPlannedOutage() {
        String label = getText(lnkFilterPlannedOutage);
        log.info("Filter Planned outage link text on Grid is " + label);
        return label;
    }
    public void clickLnkFilterPlannedOutage() {
        clickElementUsingJsExecutor(lnkFilterPlannedOutage);
        log.info("Filter Planned outage link clicked successfully.");
    }

    @FindBy(id = "lblPlan")
    private WebElement lblFilterPlannedOutage;
    @FindBy(id = "lnkCurrent")
    private WebElement lnkFilterCurrentOutage;
    public Boolean isLnkFilterCurrentOutageVisible() {
        boolean isVisible = isElementVisible(lnkFilterCurrentOutage);
        log.info("Filter Current outage link on Grid visibility status is" + isVisible);
        return isVisible;
    }
    public String getLblLnkFilterCurrentOutage() {
        String label = getText(lnkFilterCurrentOutage);
        log.info("Filter Current outage link text on Grid is " + label);
        return label;
    }
    public void clickLnkFilterCurrentOutage() {
        clickElementUsingJsExecutor(lnkFilterCurrentOutage);
        log.info("Filter Current outage link clicked successfully.");
    }

    @FindBy(id = "lnkUnplan")
    private WebElement lnkFilterUnplannedOut;
    public Boolean isLnkFilterUnplannedOutVisible() {
        boolean isVisible = isElementVisible(lnkFilterUnplannedOut);
        log.info("Filter Unplanned outage link on Grid visibility status is" + isVisible);
        return isVisible;
    }
    public String getLblLnkFilterUnplannedOut() {
        String label = getText(lnkFilterUnplannedOut);
        log.info("Filter Unplanned outage link text on Grid is " + label);
        return label;
    }

    public void clickLnkFilterUnplannedOut() {
        clickElementUsingJsExecutor(lnkFilterUnplannedOut);
        log.info("Filter Unplanned outage link clicked successfully.");
    }
    @FindBy(id = "lblUnplan")
    private WebElement lblFilterUnplannedOut;
    @FindBy(id = "lblCurr")
    private WebElement lblFilterCurrentOutageId;
    @FindBy(id = "lnkAll")
    private WebElement lnkShowAllOutage;
    public Boolean isLnkShowAllOutageVisible() {
        boolean isVisible = isElementVisible(lnkShowAllOutage);
        log.info("View all filter link on Grid visibility status is" + isVisible);
        return isVisible;
    }
    public String getLblLnkShowAllOutage() {
        String label = getText(lnkShowAllOutage);
        log.info("View all filter link text on Grid is " + label);
        return label;
    }
    public void clickLnkShowAllOutage() {
        clickElementUsingJsExecutor(lnkShowAllOutage);
        log.info("View all filter link clicked successfully.");
    }

    @FindBy(id = "lblView")
    private WebElement lblViewAllId;
    @FindBy(css = "a[aria-label*='kebab menu']")
    private List<WebElement> lstWebEleIcoThreeDotsGrid;
    public List<WebElement> getLstWebEleIcoThreeDotsGrid() {
        return lstWebEleIcoThreeDotsGrid;
    }
    public void clickLstWebEleIcoThreeDotsGrid(int index) {
        click(lstWebEleIcoThreeDotsGrid.get(index));
        log.info("Three Dots Kebab Options on Outages Grid is clicked successfully at the Index "+index);
    }
    @FindBy(css = ".custom_svc_acc.dropdown-menu.show a[data-target='#outageDetails']")
    private WebElement lnkOutageDetailsForOpenMenu;

    public void clicklnkOutageDetailsForOpenMenu() {
        click(lnkOutageDetailsForOpenMenu);
        log.info("Outage Details Link Clicked Succesfully.");
    }

    @FindBy(css = ".custom_svc_acc.dropdown-menu.show a[data-target='#outagehistrypopup']")
    private WebElement lnkOutageHistoryForOpenMenu;
    @FindBy(css = ".custom_svc_acc.dropdown-menu.show a[data-target='#outagecuslistpopup']")
    private WebElement lnkCustomerListForOpenMenu;
    @FindBy(xpath = "(//a[contains(text(),'Update Outage')])[1]")
    private WebElement lnkUpdateOutageForOpenMenu;
    @FindBy(css = "#otg_lbl_readr")
    private WebElement lblOutageDetailsHeader;
    @FindBy(css = "#otg_his_dtlbl")
    private WebElement lblOutageHistoryHeader;
    @FindBy(css = "#outg_listpopup_lbl")
    private WebElement lblCustomerListHeader;
    @FindBy(css = "#DetailData a")
    private WebElement lnkTabOutageDetailsCss;
    @FindBy(css = "#CustomerAffectedGrid th")
    private WebElement lblCustomerListGridHeader;

    //     ADD/UPDATE OUTAGE SCREEN #######
    @FindBy(css = "#ListOutageDetails a")
    private WebElement lnkDetailsTabUpdateOut;
    @FindBy(css = "#ListNotifyCust a")
    private WebElement lnkNotifyCustomersTabOut;
    @FindBy(css = "#btnNextOutageinfo")
    private WebElement btnNextUpdateOutage;

    @FindBy(css = "#OutageGrid_next")
    private WebElement btnNextOutage;
    public void waitForBtnNextOutageToBeVisible(){
        waitForElementToBeVisible(btnNextOutage);
        log.info("Next Button on the Outage List Page is visible");
    }

    @FindBy(css = "#btnClearOutageinfo")
    private WebElement btnClearUpdateOutage;
    @FindBy(css = "#btnfind")
    private WebElement btnFindLocUpdateOut;
    @FindBy(css = "#ZipOrCountyInputSearch")
    private WebElement txtSearchLocUpdateOut;
    @FindBy(css = "#ddlServiceType")
    private WebElement ddServiceTypeUpdateOut;
    @FindBy(css = "#ChkPlanned")
    private WebElement chbPlannedUpdateOut;
    @FindBy(css = "#div_serviceType label[for='ChkPlanned']")
    private WebElement lblPlannedUpdateOut;
    @FindBy(css = ".confi_txtinput > div:nth-child(3) .datearea")
    private WebElement txtStartDateUpdateOut;
    @FindBy(css = "#txtDate + a[class*='datepicker']")
    private WebElement lnkStartDateUpdateOut;
    @FindBy(css = "#txtTime")
    private WebElement txtStartTimeUpdateOut;
    @FindBy(css = ".confi_txtinput > div:nth-child(4) .datearea")
    private WebElement txtEndDateUpdateOut;
    @FindBy(css = "#txtEndDate + a[class*='datepicker']")
    private WebElement lnkEndDateUpdateOut;
    @FindBy(css = "#txtEndTime")
    private WebElement txtEndTimeUpdateOut;
    @FindBy(css = "#txtMessage")
    private WebElement txtTitleUpdateOut;
    @FindBy(css = "#txtCause")
    private WebElement txtCauseUpdateOut;
    @FindBy(css = "#txtReportInfo")
    private WebElement txtAreaDescUpdateOut;
    @FindBy(css = "#ddlResolution")
    private WebElement ddStatusUpdateOut;
    @FindBy(css = ".lbloutage strong")
    private WebElement lblAllFieldsUpdateOutage;
    @FindBy(css = ".gmnoprint button[title='Draw a shape']")
    private WebElement btnDrawPolygonUpdateOut;
    @FindBy(css = ".gmnoprint button[title='Add a marker']")
    private WebElement btnAddMarkerUpdateOut;
    @FindBy(css = ".gmnoprint button[title='Stop drawing']")
    private WebElement btnStopDrawingUpdateOut;

// NOTIFY CUSTOMERS SCREEN

    //    @FindBy(css = ".dataTables_scrollHeadInner th[aria-controls='affectedCustomersGrid']")
//    private WebElement tblCustomerNotifyGridHeader;
    @FindBy(css = ".dataTables_scrollHeadInner th")
    private WebElement tblCustomerNotifyGridHeader;
    @FindBy(css = "#affectedCustomersGrid_paginate .pagination")
    private WebElement liPagesCustomerNotify;
    @FindBy(css = ".outa_time_detil div")
    private WebElement lblOutDetailCustomerNotify;
    @FindBy(css = ".withoutbrdrmb20 div span strong")
    private WebElement lblModeCustomerNotify;
    @FindBy(css = "#ChkEmail")
    private WebElement chbEmailCustomerNotify;
    @FindBy(css = "label[for='ChkEmail']")
    private WebElement lblEmailCustomerNotify;
    @FindBy(css = "#Chktext")
    private WebElement chbTextCustomerNotify;
    @FindBy(css = "label[for='Chktext']")
    private WebElement lblTextCustomerNotify;
    @FindBy(css = "#ChkPush")
    private WebElement chbPushNotifyCustomerNotify;
    @FindBy(css = "label[for='ChkPush']")
    private WebElement lblPushNotifyCustomerNotify;
    @FindBy(css = "#ChkIVR")
    private WebElement chbIVRCustomerNotify;
    @FindBy(css = "label[for='ChkIVR']")
    private WebElement lblIVRCustomerNotify;
    @FindBy(css = "#divtextmsg div strong")
    private WebElement lblTextHeaderCustomerNotify;
    @FindBy(css = "#txtMessageText")
    private WebElement lblTextMsgCustomerNotify;
    @FindBy(css = "#divEmail div div strong")
    private WebElement lblEmailHeaderCustomerNotify;
    @FindBy(css = ".subj_lbl_outa")
    private WebElement lblSubCustomerNotify;
    @FindBy(css = "#txtmsgsubject")
    private WebElement valSubCustomerNotify;
    @FindBy(css = "#lblFileupload")
    private WebElement lblChooseFieldCustomerNotify;
    @FindBy(css = "#fileupd")
    private WebElement txtChooseFileCustomerNotify;
    @FindBy(css = "#nofile")
    private WebElement lblNoFileCustomerNotify;
    @FindBy(css = "#lblFileAllowExtension")
    private WebElement lblAllowedFileCustomerNotify;
    @FindBy(css = "#btnback")
    private WebElement btnBackCustomerNotify;
    @FindBy(css = "button#btnSubmitReply")
    private WebElement btnSubmitCustomerNotify;

    //    ##### OUTAGE HISTORY #####
    @FindBy(css = "#HistoryData a")
    private WebElement lnkTabOutageHistoryCss;
    @FindBy(css = "#CustomerData a")
    private WebElement lnkTabCustomerListCss;
    @FindBy(id = "btnEdit")
    private WebElement lnkUpdateOutageId;
    @FindBy(css = "#OutageGrid th")
    private List<WebElement> lstWebEleOutageGridViewHeaders;
    public List<WebElement> getLstWebEleOutageGridViewHeaders() {
        return lstWebEleOutageGridViewHeaders;
    }
    @FindBy(css = "#OutageGrid td")
    private WebElement lblOutageGridCompleteCellsCss;
    @FindBy(xpath = "//table[@id='OutageGrid']/tbody")
    private WebElement lblOutageGridRowWiseCellXp;
    public WebElement getWebEleLblOutageGridRowWiseCellXp(){
        return lblOutageGridRowWiseCellXp;
    }
    @FindBy(xpath = "//span[@id='lblOutageHeading']/ancestor::tr/td[1]")
    private WebElement lblOutageTypeOutDetailsXp;
    @FindBy(id = "lblOutageHeading")
    private WebElement lblValOutageTypeOutDetailsId;
    @FindBy(xpath = "//span[@id='lblCommunity']/ancestor::tr/td[1]")
    private WebElement lblCommAffectedOutDetailsXp;
    @FindBy(id = "lblCommunity")
    private WebElement lblValCommAffectedOutDetailsId;
    @FindBy(xpath = "//span[@id='lblcusteffected']/ancestor::tr/td[1]")
    private WebElement lblCustAffectedOutDetailsXp;
    @FindBy(id = "lblcusteffected")
    private WebElement lblValCustAffectedOutDetailsId;
    @FindBy(xpath = "//span[@id='lblstartdate']/ancestor::tr/td[1]")
    private WebElement lblStartTimeOutDetailsXp;
    @FindBy(id = "lblstartdate")
    private WebElement lblValStartTimeOutDetailsId;
    @FindBy(xpath = "//span[@id='lblenddate']/ancestor::tr/td[1]")
    private WebElement lblEndTimeOutDetailsXp;
    @FindBy(id = "lblenddate")
    private WebElement lblValEndTimeOutDetailsId;
    @FindBy(xpath = "//span[@id='lblOutageStatus']/ancestor::tr/td[1]")
    private WebElement lblOutStatusOutDetailsXp;
    @FindBy(id = "lblOutageStatus")
    private WebElement lblValOutStatusOutDetailsId;
    @FindBy(xpath = "//span[@id='lblOutageCause']/ancestor::tr/td[1]")
    private WebElement lblOutCauseOutDetailsXp;
    @FindBy(id = "lblOutageCause")
    private WebElement lblValOutCauseOutDetailsId;
    @FindBy(css = "#outageDetails button[class='close']")
    private WebElement btnCloseOutageDetails;
    @FindBy(css = "#OutageHistoryGrid")
    private WebElement tblOutageHistoryGrid;
    @FindBy(css = "#OutageHistoryGrid th")
    private WebElement lblOutHistoryGridHeader;
    @FindBy(css = "#OutageHistoryGrid td")
    private WebElement lblOutHistoryGridCell;
    @FindBy(xpath = "//table[@id='OutageHistoryGrid']/tbody")
    private WebElement lblOutHistoryGridRow;
    @FindBy(css = "#outagehistrypopup button[class='close']")
    private WebElement btnCloseOutHistoryPopUp;
    @FindBy(css = "#CustomerAffectedGrid th")
    private WebElement lblCustAffectedGridHeader;
    @FindBy(css = "#CustomerAffectedGrid td")
    private WebElement lblCustAffectedGridCell;
    @FindBy(xpath = "//table[@id='CustomerAffectedGrid']/tbody")
    private WebElement lblCustAffectedGridRow;
    @FindBy(css = "#ListOutageDetails a")
    private WebElement lnkDetailsTabAddUpdateOut;
    @FindBy(css = "#ListNotifyCust a")
    private WebElement lnkNotifyCustTabAddUpdOut;
    @FindBy(css = "#outagecuslistpopup button[class='close']")
    private WebElement btnCloseCustomerListPopUp;
    @FindBy(xpath = "//div[@id='sectionA']//div[contains(@class, 'mB20')][not(contains(@style, 'none'))]//strong")
    private WebElement lblAddUpdateOutForm;
    @FindBy(xpath = "//th[contains(text(), 'Outage ID')]")
    private WebElement thOutageIdOutGrid;
    @FindBy(xpath = "//table[@id='OutageHistoryGrid']//tbody")
    private WebElement lblOutageHistoryGridRowCont;
    @FindBy(xpath = "//table[@id='CustomerAffectedGrid']//tbody")
    private WebElement lblCustomerAffGridRowCont;
    @FindBy(id = "ContentPlaceHolder1_rightpanel_btnExcelExport")
    private WebElement lnkDownloadExcelId;
    @FindBy(css = "#export_docs_pop .modal-header button")
    private WebElement btnCloseExportOutWindowCss;
    @FindBy(css = "#OutageGrid_info")
    private WebElement lblNoOfEntriesInGrid;

    public String getLblNoOfEntriesInGrid() {
        String label = getText(lblNoOfEntriesInGrid);
        log.info("Showing Entries Label Value is " + label);
        return label;
    }

    @FindBy(css = ".dataTables_empty")
    private WebElement lblNoDataCustomerListGridCss;
}

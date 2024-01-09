package sew.ai.pageObjects.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sew.ai.pageObjects.scp.HomePage;

public class AnalyticsCustomerEngagementConnnectMePage extends HomePage {
	
	private static final Logger log = LogManager.getLogger(AnalyticsCustomerEngagementConnnectMePage.class);

	public AnalyticsCustomerEngagementConnnectMePage(WebDriver driver) {
		super(driver);
        PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".sidebar_connectme")
    private WebElement linkConnectMe;
	//By linkConnectMe = By.cssSelector(Utils.getRbPropValue("linkConnectMeAacmpp"));
	
	@FindBy(xpath = "//div[@class='defaulView_fields defaulView_fieldsFirst']/label")
    private WebElement lblTopic;
	//By lblTopic = By.xpath(Utils.getRbPropValue("lblTopicAacmpp"));
	
	@FindBy(xpath = "//div[@class='defaulView_emailForm defaulView_emailFormBlock']/div[2]/label")
    private WebElement lblDates;
	//By lblDates = By.xpath(Utils.getRbPropValue("lblDatesAacmpp"));
	
	@FindBy(css = "#topicSearch")
    private WebElement txtTopic;
	//By txtTopic = By.cssSelector(Utils.getRbPropValue("txtTopicAacmpp"));
	
	@FindBy(css = "div.reportrange.pull-right.date-tab-width")
    private WebElement txtDates;
	//By txtDates = By.cssSelector(Utils.getRbPropValue("txtDatesAacmpp"));
	
	@FindBy(xpath = "//li[@data-range-key='Today']")
    private WebElement lstToday;
	//By lstToday = By.xpath(Utils.getRbPropValue("lstTodayAacmpp"));
	
	@FindBy(xpath = "//li[@data-range-key='Yesterday']")
    private WebElement lstYesterday;
	//By lstYesterday= By.xpath(Utils.getRbPropValue("lstYesterdayAacmpp"));
	
	@FindBy(xpath = "//li[@data-range-key='Last 7 Days']")
    private WebElement lstLast7Days;
	//By lstLast7Days = By.xpath(Utils.getRbPropValue("lstLast7DaysAacmpp"));
	
	@FindBy(xpath = "//li[@data-range-key='Last 30 Days']")
    private WebElement lstLast30Days;
	//By lstLast30Days= By.xpath(Utils.getRbPropValue("lstLast30DaysAacmpp"));
	
	@FindBy(xpath = "//li[@data-range-key='This Month']")
    private WebElement lstThisMonth;
	//By lstThisMonth = By.xpath(Utils.getRbPropValue("lstThisMonthAacmpp"));
	
	@FindBy(xpath = "//li[@data-range-key='Last Month']")
    private WebElement lstLastMonth;
	//By lstLastMonth = By.xpath(Utils.getRbPropValue("lstLastMonthAacmpp"));
	
	@FindBy(xpath = "//li[@data-range-key='Custom Range']")
    private WebElement lstCustomRange;
	//By lstCustomRange = By.xpath(Utils.getRbPropValue("lstCustomRangeAacmpp"));
	
	@FindBy(xpath = "//button[@class='applyBtn btn btn-sm submit-button btn-primary']")
    private WebElement btnApplyDates;
	//By btnApplyDates = By.xpath(Utils.getRbPropValue("btnApplyDatesAacmpp"));
	
	@FindBy(xpath = "//button[@class='cancelBtn btn btn-sm btn-default']")
    private WebElement btnCancelDates;
	//By btnCancelDates = By.xpath(Utils.getRbPropValue("btnCancelDatesAacmpp"));
	
	@FindBy(css = "#btnAdvCancel")
    private WebElement btnClaer;
	//By btnClaer = By.cssSelector(Utils.getRbPropValue("btnClaerAacmpp"));
	
	@FindBy(css = "#btnSearch")
    private WebElement btnSearch;
	//By btnSearch = By.cssSelector(Utils.getRbPropValue("btnSearchAacmpp"));
	
	@FindBy(css = "#forSearch")
    private WebElement btnAdvanchSearch;
	//By btnAdvanchSearch = By.cssSelector(Utils.getRbPropValue("btnAdvanchSearchAacmpp"));
	
	@FindBy(css = "#ContentPlaceHolder1_rightpanel_btnexportexcelbanner")
    private WebElement btnExportToExcel;
	//By btnExportToExcel = By.cssSelector(Utils.getRbPropValue("btnExportToExcelAacmpp"));
	
	@FindBy(xpath = "//table[@id='connectme_report_Grid']/thead/tr/th")
    private WebElement lblHeaderGrid;
	//By lblHeaderGrid = By.xpath(Utils.getRbPropValue("lblHeaderGridAacmpp"));
	
	@FindBy(xpath = "//table[@id='connectme_report_Grid']/tbody/tr/td[1]")
    private WebElement txtGridDateTime;
	//By txtGridDateTime = By.xpath(Utils.getRbPropValue("txtGridDateTimeAacmpp"));
	
	@FindBy(xpath = "//table[@id='connectme_report_Grid']/tbody/tr/td[2]")
    private WebElement txtGridAccountNumber;
	//By txtGridAccountNumber = By.xpath(Utils.getRbPropValue("txtGridAccountNumberAacmpp"));
	
	@FindBy(xpath = "//table[@id='connectme_report_Grid']/tbody/tr/td[3]")
    private WebElement txtGridTopic;
	//By txtGridTopic = By.xpath(Utils.getRbPropValue("txtGridTopicAacmpp"));
	
	@FindBy(xpath = "//table[@id='connectme_report_Grid']/tbody/tr/td[4]")
    private WebElement txtGridStatus;
	//By txtGridStatus = By.xpath(Utils.getRbPropValue("txtGridStatusAacmpp"));
	
	@FindBy(xpath = "//table[@id='connectme_report_Grid']/tbody/tr/td[5]")
    private WebElement txtGridSubject;
	//By txtGridSubject = By.xpath(Utils.getRbPropValue("txtGridSubjectAacmpp"));
	
	@FindBy(xpath = "//table[@id='connectme_report_Grid']/tbody/tr/td[@class='sorting_1']")
    private WebElement txtGridDataSorted;
	//By txtGridDataSorted = By.xpath(Utils.getRbPropValue("txtGridDataSortedAacmpp"));
	
	@FindBy(css = "#connectme_report_Grid_next")
    private WebElement btnPaginationNext;
	//By btnPaginationNext = By.cssSelector(Utils.getRbPropValue("btnPaginationNextAacmpp"));
	
	@FindBy(css = "#connectme_report_Grid_previous")
    private WebElement btnPaginationPrevious;
	//By btnPaginationPrevious= By.cssSelector(Utils.getRbPropValue("btnPaginationPreviousAacmpp"));
	
	@FindBy(css = "#connectme_report_Grid_info")
    private WebElement lblPaginationCount;
	//By lblPaginationCount = By.cssSelector(Utils.getRbPropValue("lblPaginationCountAacmpp"));
	
	@FindBy(css = "#diDialog")
    private WebElement lblAdvanceSearchTitle;
	//By lblAdvanceSearchTitle = By.cssSelector(Utils.getRbPropValue("lblAdvanceSearchTitleAacmpp"));
	
	@FindBy(xpath = "//div[@class='emailDetails emailAdvanceSearch']/label")
    private WebElement lblDatesAdvanceSearch;
	//By lblDatesAdvanceSearch = By.xpath(Utils.getRbPropValue("lblDatesAdvanceSearchAacmpp"));
	
	@FindBy(css = "#advancesearchSidebar > div > div > div > div.emailDetails.emailAdvanceSearch > label:nth-child(3)")
    private WebElement lblAccountNumberAdvanceSearch;
	//By lblAccountNumberAdvanceSearch = By.cssSelector(Utils.getRbPropValue("lblAccountNumberAdvanceSearchAacmpp"));
	
	@FindBy(css = "#advancesearchSidebar > div > div > div > div.emailDetails.emailAdvanceSearch > label:nth-child(5)")
    private WebElement lblTopicAdvanceSearch;
	//By lblTopicAdvanceSearch = By.cssSelector(Utils.getRbPropValue("lblTopicAdvanceSearchAacmpp"));
	
	@FindBy(css = "#advancesearchSidebar > div > div > div > div.emailDetails.emailAdvanceSearch > label:nth-child(7)")
    private WebElement lblStatusAdvanceSearch;
	//By lblStatusAdvanceSearch= By.cssSelector(Utils.getRbPropValue("lblStatusAdvanceSearchAacmpp"));
	
	@FindBy(css = "#accountNumber")
    private WebElement txtAccountNumberAdvanceSearch;
	//By txtAccountNumberAdvanceSearch = By.cssSelector(Utils.getRbPropValue("txtAccountNumberAdvanceSearchAacmpp"));
	
	@FindBy(css = "#topic")
    private WebElement txtTopicAdvanceSearch;
	//By txtTopicAdvanceSearch = By.cssSelector(Utils.getRbPropValue("txtTopicAdvanceSearchAacmpp"));
	
	@FindBy(css = "#status")
    private WebElement txtStatusAdvanceSearch;
	//By txtStatusAdvanceSearch = By.cssSelector(Utils.getRbPropValue("txtStatusAdvanceSearchAacmpp"));
	
	@FindBy(css = "#btnAdvCancel")
    private WebElement btnCancelAdvanceSearch;
	//By btnCancelAdvanceSearch = By.cssSelector(Utils.getRbPropValue("btnCancelAdvanceSearchAacmpp"));
	
	@FindBy(css = "#btnAdvSearch")
    private WebElement btnSearchAdvanceSearch;
	//By btnSearchAdvanceSearch = By.cssSelector(Utils.getRbPropValue("btnSearchAdvanceSearchAacmpp"));
	
	@FindBy(xpath = "//a[@class='wrapper_box_close advancesearchclose']")
    private WebElement btnCloseAdvanceSearch;
	//By btnCloseAdvanceSearch = By.xpath(Utils.getRbPropValue("btnCloseAdvanceSearchAacmpp"));
	
	@FindBy(css = "#connecttitle")
    private WebElement lblHeaderConnectMeInformation;
	//By lblHeaderConnectMeInformation = By.cssSelector(Utils.getRbPropValue("lblHeaderConnectMeInformationAacmpp"));
	
	@FindBy(xpath = "//div[@class='emailDetails']/h4")
    private WebElement lblHeadersDetails;
	//By lblHeadersDetails= By.xpath(Utils.getRbPropValue("lblHeadersDetailsAacmpp"));
	
	@FindBy(xpath = "//span[@id='cmTopic']/b")
    private WebElement lblTopicDetails;
	//By lblTopicDetails = By.xpath(Utils.getRbPropValue("lblTopicDetailsAacmpp"));
	
	@FindBy(xpath = "//span[@id='cmTopic']/i")
    private WebElement txtTopicDetails;
	//By txtTopicDetails = By.xpath(Utils.getRbPropValue("txtTopicDetailsAacmpp"));
	
	@FindBy(css = "//span[@id='cmAccountNumber']/b")
    private WebElement lblAccountNumberDetails;
	//By lblAccountNumberDetails = By.xpath(Utils.getRbPropValue("lblAccountNumberDetailsAacmpp"));
	
	@FindBy(xpath = "//span[@id='cmAccountNumber']/i")
    private WebElement txtAccountNumberDetails;
	//By txtAccountNumberDetails = By.xpath(Utils.getRbPropValue("txtAccountNumberDetailsAacmpp"));
	
	@FindBy(xpath = "//span[@id='cmSubject']/b")
    private WebElement lblSubjectDetails;
	//By lblSubjectDetails= By.xpath(Utils.getRbPropValue("lblSubjectDetailsAacmpp"));
	
	@FindBy(xpath = "//span[@id='cmSubject']/i")
    private WebElement txtSubjectDetails;
	//By txtSubjectDetails = By.xpath(Utils.getRbPropValue("txtSubjectDetailsAacmpp"));
	
	@FindBy(xpath = "//span[@id='cmStatus']/b")
    private WebElement lblStatusDetails;
	//By lblStatusDetails = By.xpath(Utils.getRbPropValue("lblStatusDetailsAacmpp"));
	
	@FindBy(xpath = "//span[@id='cmStatus']/i")
    private WebElement txtStatusDetails;
	//By txtStatusDetails = By.xpath(Utils.getRbPropValue("txtStatusDetailsAacmpp"));
	
	@FindBy(xpath = "//span[@id='cmCreatedDate']/b")
    private WebElement lblDateTimeDetails;
	//By lblDateTimeDetails= By.xpath(Utils.getRbPropValue("lblDateTimeDetailsAacmpp"));
	
	@FindBy(xpath = "//span[@id='cmCreatedDate']/i")
    private WebElement txtDateTimeDetails;
	//By txtDateTimeDetails = By.xpath(Utils.getRbPropValue("txtDateTimeDetailsAacmpp"));
	
	@FindBy(xpath = "//span[@id='cmEmailID']/b")
    private WebElement lblEmailDetails;
	//By lblEmailDetails = By.xpath(Utils.getRbPropValue("lblEmailDetailsAacmpp"));
	
	@FindBy(xpath = "//span[@id='cmEmailID']/i")
    private WebElement txtEmailDetails;
	//By txtEmailDetails = By.xpath(Utils.getRbPropValue("txtEmailDetailsAacmpp"));
	
	@FindBy(xpath = "//span[@id='cmMobilePhone']/b")
    private WebElement lblPhoneDetails;
	//By lblPhoneDetails = By.xpath(Utils.getRbPropValue("lblPhoneDetailsAacmpp"));
	
	@FindBy(xpath = "//span[@id='cmMobilePhone']/i")
    private WebElement txtPhoneDetails;
	//By txtPhoneDetails= By.xpath(Utils.getRbPropValue("txtPhoneDetailsAacmpp"));
	
	@FindBy(xpath = "//span[@id='cmMessageDetails']/b")
    private WebElement lblMessageDetails;
	//By lblMessageDetails = By.xpath(Utils.getRbPropValue("lblMessageDetailsAacmpp"));
	
	@FindBy(xpath = "//span[@id='cmMessageDetails']/i")
    private WebElement txtMessageDetails;
	//By txtMessageDetails = By.xpath(Utils.getRbPropValue("txtMessageDetailsAacmpp"));
	
	@FindBy(xpath = "//span[@id='cmPrePostLogin']/b")
    private WebElement lblPrePostLoginDetails;
	//By lblPrePostLoginDetails = By.xpath(Utils.getRbPropValue("lblPrePostLoginDetailsAacmpp"));
	
	@FindBy(xpath = "//span[@id='cmPrePostLogin']/i")
    private WebElement txtPrePostLoginDetails;
	//By txtPrePostLoginDetails = By.xpath(Utils.getRbPropValue("txtPrePostLoginDetailsAacmpp"));
	
	@FindBy(css = "#closediv")
    private WebElement btnCloseConnectMeDetails;
	//By btnCloseConnectMeDetails = By.cssSelector(Utils.getRbPropValue("btnCloseConnectMeDetailsAacmpp"));
	
	@FindBy(xpath = "//div[@class='calendar left']//*[@class='yearselect']")
    private WebElement selYearleft;
	//By selYearleft= By.xpath(Utils.getRbPropValue("selYearleftAacmpp"));
	
	@FindBy(xpath = "//div[@class='calendar left']//*[@class='monthselect']")
    private WebElement selMonthLeft;
	//By selMonthLeft= By.xpath(Utils.getRbPropValue("selMonthLeftAacmpp"));
	
	@FindBy(xpath = "//td[@id='calendar_left-row_0-day_1']")
    private WebElement selfirstDayLeft;
	//By selfirstDayLeft = By.xpath(Utils.getRbPropValue("selfirstDayLeftAacmpp"));
	
	@FindBy(xpath = "//div[@class='calendar right']//*[@class='yearselect']")
    private WebElement selYearRight;
	//By selYearRight = By.xpath(Utils.getRbPropValue("selYearRightAacmpp"));
	
	@FindBy(xpath = "//div[@class='calendar right']//*[@class='monthselect']")
    private WebElement selMonthRightt;
	//By selMonthRightt = By.xpath(Utils.getRbPropValue("selMonthRighttAacmpp"));
	
	@FindBy(xpath = "//td[@id='calendar_right-row_4-day_5']")
    private WebElement selLastDayRight;
	//By selLastDayRight= By.xpath(Utils.getRbPropValue("selLastDayRightAacmpp"));
	
	@FindBy(css = ".dataTables_empty")
    private WebElement lblNoRecordsFound;
	//By lblNoRecordsFound = By.cssSelector(Utils.getRbPropValue("lblNoRecordsFoundAacmpp"));
	
	@FindBy(css = "#ContentPlaceHolder1_cea")
    private WebElement lnkCustomerAIandAnalytics;
	//By lnkCustomerAIandAnalytics= By.cssSelector(Utils.getRbPropValue("lnkCustomerAIandAnalyticsAacmpp"));
	
	@FindBy(xpath = "//a[contains(text(),'Export')]")
    private WebElement lnkExport;
	//By lnkExport= By.xpath(Utils.getRbPropValue("lnkExportAacmpp"));
	
	@FindBy(xpath = "//*[@id='exp_title_pop']/preceding-sibling::button[@class='close' and  @aria-label='Click to Close']")
    private WebElement btnCloseExport;
	//By btnCloseExport = By.xpath(Utils.getRbPropValue("btnCloseExportAacmpp"));
	
	@FindBy(xpath = "//input[@name='daterangepicker_start']")
    private WebElement txtToDate;
	//By txtToDate = By.xpath(Utils.getRbPropValue("txtToDateAacmpp"));
	
	@FindBy(xpath = "//input[@name='daterangepicker_end']")
    private WebElement txtFromDate;
	//By txtFromDate = By.xpath(Utils.getRbPropValue("txtFromDateAacmpp"));
	
	@FindBy(css = "#advdate")
    private WebElement txtAdvanceSearchDate;
	//By txtAdvanceSearchDate = By.cssSelector(Utils.getRbPropValue("txtAdvanceSearchDateAacmpp"));
	
	@FindBy(xpath = "(//input[@name='daterangepicker_start'])[2]")
    private WebElement txtLeftDateAdvance;
	//By txtLeftDateAdvance = By.xpath(Utils.getRbPropValue("txtLeftDateAdvanceAacmpp"));
	
	@FindBy(xpath = "(//input[@name='daterangepicker_end'])[2]")
    private WebElement txtRightAdvanceCalenderDate;
	//By txtRightAdvanceCalenderDate = By.xpath(Utils.getRbPropValue("txtRightAdvanceCalenderDateAacmpp"));
	
	@FindBy(xpath = "(//button[text()='Apply'])[2]")
    private WebElement btnApplyAdvance;
	//By btnApplyAdvance = By.xpath(Utils.getRbPropValue("btnApplyAdvanceAacmpp"));
	
	@FindBy(css = "#buttonClear")
    private WebElement btnClear;
	//By btnClear = By.cssSelector(Utils.getRbPropValue("btnClearAacmpp"));
	
	@FindBy(css = "#devices > div > div.top-header-area.top-header-areaCstEng > div:nth-child(1) > h2")
    private WebElement lblConnectMeTitle;
	//By lblConnectMeTitle = By.cssSelector(Utils.getRbPropValue("lblConnectMeTitleAacmpp"));

}

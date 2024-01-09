package sew.ai.pageObjects.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sew.ai.pageObjects.scp.HomePage;

public class AnalyticsCustomerEngagementServicesPage  extends HomePage {
	
	private static final Logger log = LogManager.getLogger(AnalyticsCustomerEngagementServicesPage.class);

	public AnalyticsCustomerEngagementServicesPage(WebDriver driver) {
		super(driver);
        PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".sidebar_service")
    private WebElement linkServices;
	//By linkServices = By.cssSelector(Utils.getRbPropValue("linkServicesAaspp"));
	
	@FindBy(xpath = "//div[@class='defaulView_fields defaulView_fieldsFirst']/label")
    private WebElement lblServiceType;
	//By lblServiceType = By.xpath(Utils.getRbPropValue("lblServiceTypeAaspp"));
	
	@FindBy(xpath = "//div[@class='defaulView_emailForm defaulView_emailFormBlock']/div[2]/label")
    private WebElement lblDates;
	//By lblDates = By.xpath(Utils.getRbPropValue("lblDatesAaspp"));
	
	@FindBy(css = "#topics")
    private WebElement txtServiceType;
	//By txtServiceType = By.cssSelector(Utils.getRbPropValue("txtServiceTypeAaspp"));
	
	@FindBy(css = "div.reportrange.pull-right.date-tab-width")
    private WebElement txtDates;
	//By txtDates = By.cssSelector(Utils.getRbPropValue("txtDatesAaspp"));
	
	@FindBy(xpath = "//li[@data-range-key='Today']")
    private WebElement lstToday;
	//By lstToday = By.xpath(Utils.getRbPropValue("lstTodayAaspp"));
	
	@FindBy(xpath = "//li[@data-range-key='Yesterday']")
    private WebElement lstYesterday;
	//By lstYesterday= By.xpath(Utils.getRbPropValue("lstYesterdayAaspp"));
	
	@FindBy(xpath = "//li[@data-range-key='Last 7 Days']")
    private WebElement lstLast7Days;
	//By lstLast7Days = By.xpath(Utils.getRbPropValue("lstLast7DaysAaspp"));
	
	@FindBy(xpath = "//li[@data-range-key='Last 30 Days']")
    private WebElement lstLast30Days;
	//By lstLast30Days= By.xpath(Utils.getRbPropValue("lstLast30DaysAaspp"));
	
	@FindBy(xpath = "//li[@data-range-key='This Month']")
    private WebElement lstThisMonth;
	//By lstThisMonth = By.xpath(Utils.getRbPropValue("lstThisMonthAaspp"));
	
	@FindBy(xpath = "//li[@data-range-key='Last Month']")
    private WebElement lstLastMonth;
	//By lstLastMonth = By.xpath(Utils.getRbPropValue("lstLastMonthAaspp"));
	
	@FindBy(xpath = "//li[@data-range-key='Custom Range']")
    private WebElement lstCustomRange;
	//By lstCustomRange = By.xpath(Utils.getRbPropValue("lstCustomRangeAaspp"));
	
	@FindBy(xpath = "//button[@class='applyBtn btn btn-sm submit-button btn-primary']")
    private WebElement btnApplyDates;
	//By btnApplyDates = By.xpath(Utils.getRbPropValue("btnApplyDatesAaspp"));
	
	@FindBy(xpath = "//button[@class='cancelBtn btn btn-sm btn-default']")
    private WebElement btnCancelDates;
	//By btnCancelDates = By.xpath(Utils.getRbPropValue("btnCancelDatesAaspp"));
	
	@FindBy(css = "#btnAdvCancel")
    private WebElement btnClaer;
	//By btnClaer = By.cssSelector(Utils.getRbPropValue("btnClaerAaspp"));
	
	@FindBy(css = "#btnSearch")
    private WebElement btnSearch;
	//By btnSearch = By.cssSelector(Utils.getRbPropValue("btnSearchAaspp"));
	
	@FindBy(css = "#forSearch")
    private WebElement btnAdvanchSearch;
	//By btnAdvanchSearch = By.cssSelector(Utils.getRbPropValue("btnAdvanchSearchAaspp"));
	
	@FindBy(css = "#ContentPlaceHolder1_rightpanel_btnexportexcel")
    private WebElement btnExportToExcel;
	//By btnExportToExcel = By.cssSelector(Utils.getRbPropValue("btnExportToExcelAaspp"));
	
	@FindBy(xpath = " //table[@id='service_report_Grid']/thead/tr/th")
    private WebElement lblHeaderGrid;
	//By lblHeaderGrid = By.xpath(Utils.getRbPropValue("lblHeaderGridAaspp"));
	
	@FindBy(xpath = "//table[@id='service_report_Grid']/tbody/tr/td[1]")
    private WebElement txtGridDateTime;
	//By txtGridDateTime = By.xpath(Utils.getRbPropValue("txtGridDateTimeAaspp"));
	
	@FindBy(css = "//table[@id='service_report_Grid']/tbody/tr/td[2]")
    private WebElement txtGridAccountNumber;
	//By txtGridAccountNumber = By.xpath(Utils.getRbPropValue("txtGridAccountNumberAaspp"));
	
	@FindBy(xpath = "//table[@id='service_report_Grid']/tbody/tr/td[3]")
    private WebElement txtGridServiceType;
	//By txtGridServiceType = By.xpath(Utils.getRbPropValue("txtGridServiceTypeAaspp"));
	
	@FindBy(xpath = "//table[@id='service_report_Grid']/tbody/tr/td[4]")
    private WebElement txtGridStatus;
	//By txtGridStatus = By.xpath(Utils.getRbPropValue("txtGridStatusAaspp"));
	
	@FindBy(xpath = "//table[@id='service_report_Grid']/tbody/tr/td[5]")
    private WebElement txtGridSubject;
	//By txtGridSubject = By.xpath(Utils.getRbPropValue("txtGridSubjectAaspp"));
	
	@FindBy(xpath = "//table[@id='service_report_Grid']/tbody/tr/td[@class='sorting_1']")
    private WebElement txtGridDataSorted;
	//By txtGridDataSorted = By.xpath(Utils.getRbPropValue("txtGridDataSortedAaspp"));
	
	@FindBy(css = "#service_report_Grid_next")
    private WebElement btnPaginationNext;
	//By btnPaginationNext = By.cssSelector(Utils.getRbPropValue("btnPaginationNextAaspp"));
	
	@FindBy(css = "#service_report_Grid_previous")
    private WebElement btnPaginationPrevious;
	//By btnPaginationPrevious= By.cssSelector(Utils.getRbPropValue("btnPaginationPreviousAaspp"));
	
	@FindBy(css = "#service_report_Grid_info")
    private WebElement lblPaginationCount;
	//By lblPaginationCount = By.cssSelector(Utils.getRbPropValue("lblPaginationCountAaspp"));
	
	@FindBy(css = "#hdngtitleadvance1")
    private WebElement lblAdvanceSearchTitle;
	//By lblAdvanceSearchTitle = By.cssSelector(Utils.getRbPropValue("lblAdvanceSearchTitleAaspp"));
	
	@FindBy(xpath = "//div[@class='emailDetails emailAdvanceSearch']/label")
    private WebElement lblDatesAdvanceSearch;
	//By lblDatesAdvanceSearch = By.xpath(Utils.getRbPropValue("lblDatesAdvanceSearchAaspp"));
	
	@FindBy(css = "#advancesearchSidebar > div > div > div > div.emailDetails.emailAdvanceSearch > label:nth-child(3)")
    private WebElement lblAccountNumberAdvanceSearch;
	//By lblAccountNumberAdvanceSearch = By.cssSelector(Utils.getRbPropValue("lblAccountNumberAdvanceSearchAaspp"));
	
	@FindBy(css = "#advancesearchSidebar > div > div > div > div.emailDetails.emailAdvanceSearch > label:nth-child(5)")
    private WebElement lblServiceTypeAdvanceSearch;
	//By lblServiceTypeAdvanceSearch = By.cssSelector(Utils.getRbPropValue("lblServiceTypeAdvanceSearchAaspp"));
	
	@FindBy(css = "#advancesearchSidebar > div > div > div > div.emailDetails.emailAdvanceSearch > label:nth-child(7)")
    private WebElement lblStatusAdvanceSearch;
	//By lblStatusAdvanceSearch= By.cssSelector(Utils.getRbPropValue("lblStatusAdvanceSearchAaspp"));
	
	@FindBy(css = "#accountNumber")
    private WebElement txtAccountNumberAdvanceSearch;
	//By txtAccountNumberAdvanceSearch = By.cssSelector(Utils.getRbPropValue("txtAccountNumberAdvanceSearchAaspp"));
	
	@FindBy(css = "#topic")
    private WebElement txtTopicAdvanceSearch;
	//By txtTopicAdvanceSearch = By.cssSelector(Utils.getRbPropValue("txtServiceTypeAdvanceSearchAaspp"));
	
	@FindBy(css = "#status")
    private WebElement txtStatusAdvanceSearch;
	//By txtStatusAdvanceSearch = By.cssSelector(Utils.getRbPropValue("txtStatusAdvanceSearchAaspp"));
	
	@FindBy(css = "#btnAdvCancel")
    private WebElement btnCancelAdvanceSearch;
	//By btnCancelAdvanceSearch = By.cssSelector(Utils.getRbPropValue("btnCancelAdvanceSearchAaspp"));
	
	@FindBy(css = "#btnAdvSearch")
    private WebElement btnSearchAdvanceSearch;
	//By btnSearchAdvanceSearch = By.cssSelector(Utils.getRbPropValue("btnSearchAdvanceSearchAaspp"));
	
	@FindBy(xpath = "//a[@class='wrapper_box_close advancesearchclose']")
    private WebElement btnCloseAdvanceSearch;
	//By btnCloseAdvanceSearch = By.xpath(Utils.getRbPropValue("btnCloseAdvanceSearchAaspp"));
	
	@FindBy(css = "#hdngtitleadvance")
    private WebElement lblHeaderConnectMeInformation;
	//By lblHeaderConnectMeInformation = By.cssSelector(Utils.getRbPropValue("lblHeaderConnectMeInformationAaspp"));
	
	@FindBy(xpath = "//div[@class='emailDetails']/h4")
    private WebElement lblHeadersDetails;
	//By lblHeadersDetails= By.xpath(Utils.getRbPropValue("lblHeadersDetailsAaspp"));
	
	@FindBy(xpath = "//span[@id='serTopic']/b")
    private WebElement lblServiceTypeDetails;
	//By lblServiceTypeDetails = By.xpath(Utils.getRbPropValue("lblServiceTypeDetailsAaspp"));
	
	@FindBy(xpath = "//span[@id='serTopic']/i")
    private WebElement txtServiceTypeDetails;
	//By txtServiceTypeDetails = By.xpath(Utils.getRbPropValue("txtServiceTypeDetailsAaspp"));
	
	@FindBy(xpath = "//span[@id='serAccountNumber']/b")
    private WebElement lblAccountNumberDetails;
	//By lblAccountNumberDetails = By.xpath(Utils.getRbPropValue("lblAccountNumberDetailsAaspp"));
	
	@FindBy(xpath = "//span[@id='serAccountNumber']/i")
    private WebElement txtAccountNumberDetails;
	//By txtAccountNumberDetails = By.xpath(Utils.getRbPropValue("txtAccountNumberDetailsAaspp"));
	
	@FindBy(xpath = "//span[@id='serSubject']/b")
    private WebElement lblSubjectDetails;
	//By lblSubjectDetails= By.xpath(Utils.getRbPropValue("lblSubjectDetailsAaspp"));
	
	@FindBy(xpath = "//span[@id='serSubject']/i")
    private WebElement txtSubjectDetails;
	//By txtSubjectDetails = By.xpath(Utils.getRbPropValue("txtSubjectDetailsAaspp"));
	
	@FindBy(xpath = "//span[@id='serStatus']/b")
    private WebElement lblStatusDetails;
	//By lblStatusDetails = By.xpath(Utils.getRbPropValue("lblStatusDetailsAaspp"));
	
	@FindBy(xpath = "//span[@id='serStatus']/i")
    private WebElement txtStatusDetails;
	//By txtStatusDetails = By.xpath(Utils.getRbPropValue("txtStatusDetailsAaspp"));
	
	@FindBy(xpath = "//span[@id='serCreatedDate']/b")
    private WebElement lblDateTimeDetails;
	//By lblDateTimeDetails= By.xpath(Utils.getRbPropValue("lblDateTimeDetailsAaspp"));
	
	@FindBy(xpath = "//span[@id='serCreatedDate']/i")
    private WebElement txtDateTimeDetails;
	//By txtDateTimeDetails = By.xpath(Utils.getRbPropValue("txtDateTimeDetailsAaspp"));
	
	@FindBy(xpath = "//span[@id='serEmailID']/b")
    private WebElement lblEmailDetails;
	//By lblEmailDetails = By.xpath(Utils.getRbPropValue("lblEmailDetailsAaspp"));
	
	@FindBy(xpath = "//span[@id='serEmailID']/i")
    private WebElement txtEmailDetails;
	//By txtEmailDetails = By.xpath(Utils.getRbPropValue("txtEmailDetailsAaspp"));
	
	@FindBy(xpath = "//span[@id='serMobilePhone']/b")
    private WebElement lblPhoneDetails;
	//By lblPhoneDetails = By.xpath(Utils.getRbPropValue("lblPhoneDetailsAaspp"));
	
	@FindBy(xpath = "//span[@id='serMobilePhone']/i")
    private WebElement txtPhoneDetails;
	//By txtPhoneDetails= By.xpath(Utils.getRbPropValue("txtPhoneDetailsAaspp"));
	
	@FindBy(xpath = "//span[@id='serMessageDetails']/b")
    private WebElement lblMessageDetails;
	//By lblMessageDetails = By.xpath(Utils.getRbPropValue("lblMessageDetailsAaspp"));
	
	@FindBy(xpath = "//span[@id='serMessageDetails']/i")
    private WebElement txtMessageDetails;
	//By txtMessageDetails = By.xpath(Utils.getRbPropValue("txtMessageDetailsAaspp"));
	
	@FindBy(xpath = "//span[@id='serPrePostLogin']/b")
    private WebElement lblPrePostLoginDetails;
	//By lblPrePostLoginDetails = By.xpath(Utils.getRbPropValue("lblPrePostLoginDetailsAaspp"));
	
	@FindBy(xpath = "//span[@id='serPrePostLogin']/i")
    private WebElement txtPrePostLoginDetails;
	//By txtPrePostLoginDetails = By.xpath(Utils.getRbPropValue("txtPrePostLoginDetailsAaspp"));
	
	@FindBy(css = "#closediv")
    private WebElement btnCloseConnectMeDetails;
	//By btnCloseConnectMeDetails = By.cssSelector(Utils.getRbPropValue("btnCloseConnectMeDetailsAaspp"));
	
	@FindBy(xpath = "//div[@class='calendar left']//*[@class='yearselect']")
    private WebElement selYearleft;
	//By selYearleft= By.xpath(Utils.getRbPropValue("selYearleftAaspp"));
	
	@FindBy(xpath = "//div[@class='calendar left']//*[@class='monthselect']")
    private WebElement selMonthLeft;
	//By selMonthLeft= By.xpath(Utils.getRbPropValue("selMonthLeftAaspp"));
	
	@FindBy(xpath = "//td[@id='calendar_left-row_0-day_1']")
    private WebElement selfirstDayLeft;
	//By selfirstDayLeft = By.xpath(Utils.getRbPropValue("selfirstDayLeftAaspp"));
	
	@FindBy(xpath = "//div[@class='calendar right']//*[@class='yearselect']")
    private WebElement selYearRight;
	//By selYearRight = By.xpath(Utils.getRbPropValue("selYearRightAaspp"));
	
	@FindBy(xpath = "//div[@class='calendar right']//*[@class='monthselect']")
    private WebElement selMonthRightt;
	//By selMonthRightt = By.xpath(Utils.getRbPropValue("selMonthRighttAaspp"));
	
	@FindBy(xpath = "//td[@id='calendar_right-row_4-day_5']")
    private WebElement selLastDayRight;
	//By selLastDayRight= By.xpath(Utils.getRbPropValue("selLastDayRightAaspp"));
	
	@FindBy(css = ".dataTables_empty")
    private WebElement lblNoRecordsFound;
	//By lblNoRecordsFound = By.cssSelector(Utils.getRbPropValue("lblNoRecordsFoundAaspp"));
	
	@FindBy(css = "#ContentPlaceHolder1_cea")
    private WebElement lnkCustomerAIandAnalytics;
	//By lnkCustomerAIandAnalytics= By.cssSelector(Utils.getRbPropValue("lnkCustomerAIandAnalyticsAaspp"));
	
	@FindBy(xpath = "//a[contains(text(),'Export')]")
    private WebElement lnkExport;
	//By lnkExport= By.xpath(Utils.getRbPropValue("lnkExportAaspp"));
	
	@FindBy(xpath = "//*[@id='exp_title_pop']/preceding-sibling::button[@class='close' and  @aria-label='Click to Close']")
    private WebElement btnCloseExport;
	//By btnCloseExport = By.xpath(Utils.getRbPropValue("btnCloseExportAaspp"));
	
	@FindBy(xpath = "//input[@name='daterangepicker_start']")
    private WebElement txtToDate;
	//By txtToDate = By.xpath(Utils.getRbPropValue("txtToDateAaspp"));
	
	@FindBy(xpath = "//input[@name='daterangepicker_end']")
    private WebElement txtFromDate;
	//By txtFromDate = By.xpath(Utils.getRbPropValue("txtFromDateAaspp"));
	
	@FindBy(css = "#advdate")
    private WebElement txtAdvanceSearchDate;
	//By txtAdvanceSearchDate = By.cssSelector(Utils.getRbPropValue("txtAdvanceSearchDateAaspp"));
	
	@FindBy(xpath = "(//input[@name='daterangepicker_start'])[2]")
    private WebElement txtLeftDateAdvance;
	//By txtLeftDateAdvance = By.xpath(Utils.getRbPropValue("txtLeftDateAdvanceAaspp"));
	
	@FindBy(xpath = "(//input[@name='daterangepicker_end'])[2]")
    private WebElement txtRightAdvanceCalenderDate;
	//By txtRightAdvanceCalenderDate = By.xpath(Utils.getRbPropValue("txtRightAdvanceCalenderDateAaspp"));
	
	@FindBy(xpath = "(//button[text()='Apply'])[2]")
    private WebElement btnApplyAdvance;
	//By btnApplyAdvance = By.xpath(Utils.getRbPropValue("btnApplyAdvanceAaspp"));
	
	@FindBy(css = "#buttonClear")
    private WebElement btnClear;
	//By btnClear = By.cssSelector(Utils.getRbPropValue("btnClearAaspp"));
	
	@FindBy(css = "#devices > div > div.top-header-area.top-header-areaCstEng > div:nth-child(1) > h2")
    private WebElement lblServicesTitle;
	//By lblServicesTitle = By.cssSelector(Utils.getRbPropValue("lblServicesTitleAaspp"));

}

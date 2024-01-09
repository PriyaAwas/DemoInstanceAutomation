package sew.ai.pageObjects.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sew.ai.pageObjects.scp.HomePage;

public class AnalyticsCustomerPreferencesMarketingPreferencePage extends HomePage {
	
	private static final Logger log = LogManager.getLogger(AnalyticsCustomerPreferencesMarketingPreferencePage.class);

	public AnalyticsCustomerPreferencesMarketingPreferencePage(WebDriver driver) {
		super(driver);
        PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "#ContentPlaceHolder1_cea")
    private WebElement lnkCustomerAIandAnalytics;
	//By lnkCustomerAIandAnalytics = By.cssSelector(Utils.getRbPropValue("lnkCustomerAIandAnalyticsAampp"));
	
	@FindBy(css = ".sidebar li[aria-label='Customer Preferences'] > a[href*='CustomerPreference']")
    private WebElement lnkCustomerPreference;
	//By lnkCustomerPreference = By.cssSelector(Utils.getRbPropValue("lnkCustomerPreferenceAampp"));
	
	@FindBy(xpath = "//a[contains(text(),' Marketing Preference')]")
    private WebElement lnkMarketingPreference;
	//By lnkMarketingPreference = By.xpath(Utils.getRbPropValue("lnkMarketingPreferenceAampp"));
	
	@FindBy(css = "#btnSearch")
    private WebElement btnSearch;
	//By btnSearch = By.cssSelector(Utils.getRbPropValue("btnSearchAampp"));
	
	@FindBy(css = "#buttonClear")
    private WebElement btnClear;
	//By btnClear = By.cssSelector(Utils.getRbPropValue("btnClearAampp"));
	
	@FindBy(css = "#emailSearch")
    private WebElement txtEmail;
	//By txtEmail = By.cssSelector(Utils.getRbPropValue("txtEmailAampp"));
	
	@FindBy(css = "#forSearch")
    private WebElement btnAdvanceSearch;
	//By btnAdvanceSearch = By.cssSelector(Utils.getRbPropValue("btnAdvanceSearchAampp"));
	
	@FindBy(css = "#marketingPreferenceReport> tbody > tr > td")
    private WebElement lblTableWhenNoData;
	//By lblTableWhenNoData = By.cssSelector(Utils.getRbPropValue("lblTableWhenNoDataAampp"));
	
	@FindBy(css = "div.defaulView_email.notification_dashboardBordered > div > div:nth-child(2) > div > div > span")
    private WebElement txtCalendar;
	//By txtCalendar = By.cssSelector(Utils.getRbPropValue("txtCalendarAampp"));
	
	@FindBy(xpath = "(//input[@name='daterangepicker_start'])[1]")
    private WebElement txtToDate;
	//By txtToDate = By.xpath(Utils.getRbPropValue("txtToDateAampp"));
	
	@FindBy(xpath = "(//input[@name='daterangepicker_end'])[1]")
    private WebElement txtFromDate;
	//By txtFromDate = By.xpath(Utils.getRbPropValue("txtFromDateAampp"));
	
	@FindBy(xpath = "//li[@data-range-key='Today']")
    private WebElement lstToday;
	//By lstToday= By.xpath(Utils.getRbPropValue("lstTodayAampp"));
	
	@FindBy(xpath = "//li[@data-range-key='Yesterday']")
    private WebElement lstYesterday;
	//By lstYesterday = By.xpath(Utils.getRbPropValue("lstYesterdayAampp"));
	
	@FindBy(xpath = "//li[@data-range-key='Last 7 Days']")
    private WebElement lstLast7Days;
	//By lstLast7Days = By.xpath(Utils.getRbPropValue("lstLast7DaysAampp"));
	
	@FindBy(xpath = "//li[@data-range-key='Last 30 Days']")
    private WebElement lstLast30Days;
	//By lstLast30Days = By.xpath(Utils.getRbPropValue("lstLast30DaysAampp"));
	
	@FindBy(xpath = "//li[@data-range-key='This Month']")
    private WebElement lstThisMonth;
	//By lstThisMonth = By.xpath(Utils.getRbPropValue("lstThisMonthAampp"));
	
	@FindBy(xpath = "//li[@data-range-key='Last Month']")
    private WebElement lstLastMonth;
	//By lstLastMonth = By.xpath(Utils.getRbPropValue("lstLastMonthAampp"));
	
	@FindBy(xpath = "//li[@data-range-key='Custom Range']")
    private WebElement lstCustomRange;
	//By lstCustomRange = By.xpath(Utils.getRbPropValue("lstCustomRangeAampp"));
	
	@FindBy(xpath = "//button[@class='applyBtn btn btn-sm submit-button btn-primary']")
    private WebElement btnApplyDates;
	//By btnApplyDates = By.xpath(Utils.getRbPropValue("btnApplyDatesAampp"));
	
	@FindBy(xpath = "//button[@class='cancelBtn btn btn-sm btn-default']")
    private WebElement btnCancelDates;
	//By btnCancelDates = By.xpath(Utils.getRbPropValue("btnCancelDatesAampp"));
	
	@FindBy(xpath = "//div[@class='defaulView_fields defaulView_fieldsFirst']/label")
    private WebElement lblModule;
	//By lblModule = By.xpath(Utils.getRbPropValue("lblModuleAampp"));
	
	@FindBy(xpath = " //div[@class='defaulView_emailForm defaulView_emailFormBlock']/div[2]/label")
    private WebElement lblDates;
	//By lblDates = By.xpath(Utils.getRbPropValue("lblDatesAampp"));
	
	@FindBy(css = "div.reportrange.pull-right.date-tab-width")
    private WebElement txtDates;
	//By txtDates = By.cssSelector(Utils.getRbPropValue("txtDatesAampp"));
	
	@FindBy(css = "#btnAdvCancel")
    private WebElement btnClearAdvace;
	//By btnClearAdvace = By.cssSelector(Utils.getRbPropValue("btnClearAdvaceAampp"));
	
	@FindBy(css = "#btnAdvCancel")
    private WebElement btnCancelAdvanceSearch;
	//By btnCancelAdvanceSearch = By.cssSelector(Utils.getRbPropValue("btnCancelAdvanceSearchAampp"));
	
	@FindBy(css = "#btnAdvSearch")
    private WebElement btnSearchAdvanceSearch;
	//By btnSearchAdvanceSearch = By.cssSelector(Utils.getRbPropValue("btnSearchAdvanceSearchAampp"));
	
	@FindBy(xpath = "//a[@class='wrapper_box_close advancesearchclose']")
    private WebElement btnCloseAdvanceSearch;
	//By btnCloseAdvanceSearch = By.xpath(Utils.getRbPropValue("btnCloseAdvanceSearchAampp"));
	
	@FindBy(xpath = "//div[@class='calendar left']//*[@class='yearselect']")
    private WebElement selYearleft;
	//By selYearleft = By.xpath(Utils.getRbPropValue("selYearleftAampp"));
	
	@FindBy(xpath = "//div[@class='calendar left']//*[@class='monthselect']")
    private WebElement selMonthLeft;
	//By selMonthLeft = By.xpath(Utils.getRbPropValue("selMonthLeftAampp"));
	
	@FindBy(xpath = "//td[@id='calendar_left-row_0-day_1']")
    private WebElement selfirstDayLeft;
	//By selfirstDayLeft = By.xpath(Utils.getRbPropValue("selfirstDayLeftAampp"));
	
	@FindBy(xpath = "//div[@class='calendar right']//*[@class='yearselect']")
    private WebElement selYearRight;
	//By selYearRight = By.xpath(Utils.getRbPropValue("selYearRightAampp"));
	
	@FindBy(xpath = "//div[@class='calendar right']//*[@class='monthselect']")
    private WebElement selMonthRight;
	//By selMonthRight= By.xpath(Utils.getRbPropValue("selMonthRighttAampp"));
	
	@FindBy(xpath = "//td[@id='calendar_right-row_4-day_5']")
    private WebElement selLastDayRight;
	//By selLastDayRight = By.xpath(Utils.getRbPropValue("selLastDayRightAampp"));
	
	@FindBy(xpath = "//a[contains(text(),'Export')]")
    private WebElement lnkExport;
	//By lnkExport = By.xpath(Utils.getRbPropValue("lnkExportAampp"));
	
	@FindBy(xpath = "//*[@id='exp_title_pop']/preceding-sibling::button[@class='close' and  @aria-label='Click to Close']")
    private WebElement btnCloseExport;
	//By btnCloseExport = By.xpath(Utils.getRbPropValue("btnCloseExportAampp"));
	
	@FindBy(css = "#ContentPlaceHolder1_rightpanel_btnexportexcelbanner")
    private WebElement btnExportToExcel;
	//By btnExportToExcel = By.cssSelector(Utils.getRbPropValue("btnExportToExcelAampp"));
	
	@FindBy(xpath = "//table[@id='marketingPreferenceReport']/thead/tr/th")
    private WebElement lblHeaderGrid;
	//By lblHeaderGrid = By.xpath(Utils.getRbPropValue("lblHeaderGridAampp"));
	
	@FindBy(xpath = "//table[@id='marketingPreferenceReport']/tbody/tr/td[1]")
    private WebElement txtGridDateTime;
	//By txtGridDateTime = By.xpath(Utils.getRbPropValue("txtGridDateTimeAampp"));
	
	@FindBy(xpath = "//table[@id='marketingPreferenceReport']/tbody/tr/td[2]")
    private WebElement txtGridPreference;
	//By txtGridPreference = By.xpath(Utils.getRbPropValue("txtGridPreferenceAampp"));
	
	@FindBy(xpath = "//table[@id='marketingPreferenceReport']/tbody/tr/td[3]")
    private WebElement txtGridCustomerName;
	//By txtGridCustomerName = By.xpath(Utils.getRbPropValue("txtGridCustomerNameAampp"));
	
	@FindBy(xpath = "//table[@id='marketingPreferenceReport']/tbody/tr/td[4]")
    private WebElement txtGridAccountNumber;
	//By txtGridAccountNumber = By.xpath(Utils.getRbPropValue("txtGridAccountNumberAampp"));
	
	@FindBy(xpath = "//table[@id='marketingPreferenceReport']/tbody/tr/td[5]")
    private WebElement txtGridEmail;
	//By txtGridEmail = By.xpath(Utils.getRbPropValue("txtGridEmailAampp"));
	
	@FindBy(xpath = "//table[@id='marketingPreferenceReport']/tbody/tr/td[@class='sorting_1']")
    private WebElement txtGridDataSorted;
	//By txtGridDataSorted = By.xpath(Utils.getRbPropValue("txtGridDataSortedAampp"));
	
	@FindBy(css = "#marketingPreferenceReport_next> a")
    private WebElement btnPaginationNext;
	//By btnPaginationNext = By.cssSelector(Utils.getRbPropValue("btnPaginationNextAampp"));
	
	@FindBy(css = "#marketingPreferenceReport_previous> a")
    private WebElement btnPaginationPrevious;
	//By btnPaginationPrevious = By.cssSelector(Utils.getRbPropValue("btnPaginationPreviousAampp"));
	
	@FindBy(css = "#marketingPreferenceReport_info")
    private WebElement lblPaginationCount;
	//By lblPaginationCount = By.cssSelector(Utils.getRbPropValue("lblPaginationCountAampp"));
	
	@FindBy(css = "#advancesearchSidebar > div > div > div > div.emailDetails.emailAdvanceSearch > div.chartValue.chartValueFull.dateadvance > div")
    private WebElement txtAdvanceSearchDate;
	//By txtAdvanceSearchDate = By.cssSelector(Utils.getRbPropValue("txtAdvanceSearchDateAampp"));
	
	@FindBy(xpath = "(//input[@name='daterangepicker_start'])[2]")
    private WebElement txtLeftDateAdvance;
	//By txtLeftDateAdvance = By.xpath(Utils.getRbPropValue("txtLeftDateAdvanceAampp"));
	
	@FindBy(xpath = "(//input[@name='daterangepicker_end'])[2]")
    private WebElement txtRightAdvanceCalenderDate;
	//By txtRightAdvanceCalenderDate = By.xpath(Utils.getRbPropValue("txtRightAdvanceCalenderDateAampp"));
	
	@FindBy(xpath = "(//button[text()='Apply'])[2]")
    private WebElement btnApplyAdvance;
	//By btnApplyAdvance = By.xpath(Utils.getRbPropValue("btnApplyAdvanceAampp"));
	
	@FindBy(css = "#devices > div > div.top-header-area.top-header-areaCstEng > div:nth-child(1) > h2")
    private WebElement lblMarketingPreferenceHeader;
	//By lblMarketingPreferenceHeader = By.cssSelector(Utils.getRbPropValue("lblMarketingPreferenceHeaderAampp"));
	
	@FindBy(css = ".dataTables_empty")
    private WebElement lblNoRecordsFound;
	//By lblNoRecordsFound = By.cssSelector(Utils.getRbPropValue("lblNoRecordsFoundAampp"));
	
	@FindBy(css = "#advancesearchSidebar > div > div > div > div.emailDetails.emailAdvanceSearch > label:nth-child(1)")
    private WebElement lblAdvanceSearchDates;
	//By lblAdvanceSearchDates = By.cssSelector(Utils.getRbPropValue("lblAdvanceSearchDatesAampp"));
	
	@FindBy(css = "#advancesearchSidebar > div > div > div > div.emailDetails.emailAdvanceSearch > label:nth-child(3)")
    private WebElement lblAdvanceSearchCustomerName;
	//By lblAdvanceSearchCustomerName = By.cssSelector(Utils.getRbPropValue("lblAdvanceSearchCustomerNameAampp"));
	
	@FindBy(css = "#advancesearchSidebar > div > div > div > div.emailDetails.emailAdvanceSearch > label:nth-child(5)")
    private WebElement lblAdvanceSearchPreference;
	//By lblAdvanceSearchPreference = By.cssSelector(Utils.getRbPropValue("lblAdvanceSearchPreferenceAampp"));
	
	@FindBy(css = "#advancesearchSidebar > div > div > div > div.emailDetails.emailAdvanceSearch > label:nth-child(7)")
    private WebElement lblAdvanceSearchAccountNumber;
	//By lblAdvanceSearchAccountNumber = By.cssSelector(Utils.getRbPropValue("lblAdvanceSearchAccountNumberAampp"));
	
	@FindBy(css = "#advancesearchSidebar > div > div > div > div.emailDetails.emailAdvanceSearch > label:nth-child(9)")
    private WebElement lblAdvanceSearchEmail;
	//By lblAdvanceSearchEmail = By.cssSelector(Utils.getRbPropValue("lblAdvanceSearchEmailAampp"));
	
	@FindBy(css = "#hdngtitleadvance1")
    private WebElement lblAdvanceSearchTitle;
	//By lblAdvanceSearchTitle = By.cssSelector(Utils.getRbPropValue("lblAdvanceSearchTitleAampp"));
	
	@FindBy(xpath = "//div[@class='emailDetails emailAdvanceSearch']/label")
    private WebElement lblDatesAdvanceSearch;
	//By lblDatesAdvanceSearch = By.xpath(Utils.getRbPropValue("lblDatesAdvanceSearchAampp"));
	
	@FindBy(css = "#username")
    private WebElement txtCustomerNameAdvanceSearch;
	//By txtCustomerNameAdvanceSearch = By.cssSelector(Utils.getRbPropValue("txtCustomerNameAdvanceSearchAampp"));
	
	@FindBy(css = "#preferenceName")
    private WebElement txtPreferenceAdvanceSearch;
	//By txtPreferenceAdvanceSearch = By.cssSelector(Utils.getRbPropValue("txtPreferenceAdvanceSearchAampp"));
	
	@FindBy(css = "#accountNumber")
    private WebElement txtAccountNumberAdvanceSearch;
	//By txtAccountNumberAdvanceSearch = By.cssSelector(Utils.getRbPropValue("txtAccountNumberAdvanceSearchAampp"));
	
	@FindBy(css = "#emailAddress")
    private WebElement txtEmailAdvanceSearch;
	//By txtEmailAdvanceSearch = By.cssSelector(Utils.getRbPropValue("txtEmailAdvanceSearchAampp"));

}

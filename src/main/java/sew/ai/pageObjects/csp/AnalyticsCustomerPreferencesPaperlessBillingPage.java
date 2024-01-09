package sew.ai.pageObjects.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sew.ai.pageObjects.scp.HomePage;

public class AnalyticsCustomerPreferencesPaperlessBillingPage extends HomePage {
	
	private static final Logger log = LogManager.getLogger(AnalyticsCustomerPreferencesPaperlessBillingPage.class);

	public AnalyticsCustomerPreferencesPaperlessBillingPage(WebDriver driver) {
		super(driver);
        PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "#ContentPlaceHolder1_cea")
    private WebElement lnkCustomerAIandAnalytics;
	//By lnkCustomerAIandAnalytics = By.cssSelector(Utils.getRbPropValue("lnkCustomerAIandAnalytics"));
	
	@FindBy(css = ".sidebar li[aria-label='Customer Preferences'] > a[href*='CustomerPreference']")
    private WebElement lnkCustomerPreference;
	//By lnkCustomerPreference = By.cssSelector(Utils.getRbPropValue("lnkCustomerPreference"));
	
	@FindBy(xpath = "//a[contains(text(),'Paperless Billing')]")
    private WebElement lnkPaperlessBilling;
	//By lnkPaperlessBilling = By.xpath(Utils.getRbPropValue("lnkPaperlessBilling"));
	
	@FindBy(css = "#btnSearch")
    private WebElement btnSearch;
	//By btnSearch = By.cssSelector(Utils.getRbPropValue("btnSearch"));
	
	@FindBy(css = "#buttonClear")
    private WebElement btnClear;
	//By btnClear = By.cssSelector(Utils.getRbPropValue("btnClear"));
	
	@FindBy(css = "#emailSearch")
    private WebElement txtEmail;
	//By txtEmail = By.cssSelector(Utils.getRbPropValue("txtEmail"));
	
	@FindBy(css = "#forSearch")
    private WebElement btnAdvanceSearch;
	//By btnAdvanceSearch = By.cssSelector(Utils.getRbPropValue("btnAdvanceSearch"));
	
	@FindBy(css = "#paperlessbilling > tbody > tr > td")
    private WebElement lblTableWhenNoData;
	//By lblTableWhenNoData = By.cssSelector(Utils.getRbPropValue("lblTableWhenNoData"));
	
	@FindBy(css = "div.defaulView_email.notification_dashboardBordered > div > div:nth-child(2) > div > div > span")
    private WebElement txtCalendar;
	//By txtCalendar = By.cssSelector(Utils.getRbPropValue("txtCalendar"));
	
	@FindBy(xpath = "(//input[@name='daterangepicker_start'])[1]")
    private WebElement txtToDate;
	//By txtToDate = By.xpath(Utils.getRbPropValue("txtToDate"));
	
	@FindBy(xpath = "(//input[@name='daterangepicker_end'])[1]")
    private WebElement txtFromDate;
	//By txtFromDate = By.xpath(Utils.getRbPropValue("txtFromDate"));
	
	@FindBy(xpath = "//li[@data-range-key='Today']")
    private WebElement lstToday;
	//By lstToday = By.xpath(Utils.getRbPropValue("lstToday"));
	
	@FindBy(xpath = "//li[@data-range-key='Yesterday']")
    private WebElement lstYesterday;
	//By lstYesterday = By.xpath(Utils.getRbPropValue("lstYesterday"));
	
	@FindBy(xpath = "//li[@data-range-key='Last 7 Days']")
    private WebElement lstLast7Days;
	//By lstLast7Days = By.xpath(Utils.getRbPropValue("lstLast7Days"));
	
	@FindBy(xpath = "//li[@data-range-key='Last 30 Days']")
    private WebElement lstLast30Days;
	//By lstLast30Days = By.xpath(Utils.getRbPropValue("lstLast30Days"));
	
	@FindBy(xpath = "//li[@data-range-key='This Month']")
    private WebElement lstThisMonth;
	//By lstThisMonth = By.xpath(Utils.getRbPropValue("lstThisMonth"));
	
	@FindBy(xpath = "//li[@data-range-key='Last Month']")
    private WebElement lstLastMonth;
	//By lstLastMonth = By.xpath(Utils.getRbPropValue("lstLastMonth"));
	
	@FindBy(xpath = "//li[@data-range-key='Custom Range']")
    private WebElement lstCustomRange;
	//By lstCustomRange = By.xpath(Utils.getRbPropValue("lstCustomRange"));
	
	@FindBy(xpath = "//button[@class='applyBtn btn btn-sm submit-button btn-primary']")
    private WebElement btnApplyDates;
	//By btnApplyDates = By.xpath(Utils.getRbPropValue("btnApplyDates"));
	
	@FindBy(xpath = "//button[@class='cancelBtn btn btn-sm btn-default']")
    private WebElement btnCancelDates;
	//By btnCancelDates = By.xpath(Utils.getRbPropValue("btnCancelDates"));
	
	@FindBy(xpath = "//div[@class='defaulView_fields defaulView_fieldsFirst']/label")
    private WebElement lblModule;
	//By lblModule = By.xpath(Utils.getRbPropValue("lblModule"));
	
	@FindBy(xpath = "//div[@class='defaulView_emailForm defaulView_emailFormBlock']/div[2]/label")
    private WebElement lblDates;
	//By lblDates = By.xpath(Utils.getRbPropValue("lblDates"));
	
	@FindBy(css = "div.reportrange.pull-right.date-tab-width")
    private WebElement txtDates;
	//By txtDates = By.cssSelector(Utils.getRbPropValue("txtDates"));
	
	@FindBy(css = "#btnAdvCancel")
    private WebElement btnClearAdvace;
	//By btnClearAdvace = By.cssSelector(Utils.getRbPropValue("btnClearAdvace"));
	
	@FindBy(css = "#btnAdvCancel")
    private WebElement btnCancelAdvanceSearch;
	//By btnCancelAdvanceSearch = By.cssSelector(Utils.getRbPropValue("btnCancelAdvanceSearch"));
	
	@FindBy(css = "#btnAdvSearch")
    private WebElement btnSearchAdvanceSearch;
	//By btnSearchAdvanceSearch = By.cssSelector(Utils.getRbPropValue("btnSearchAdvanceSearch"));
	
	@FindBy(xpath = "//a[@class='wrapper_box_close advancesearchclose']")
    private WebElement btnCloseAdvanceSearch;
	//By btnCloseAdvanceSearch = By.xpath(Utils.getRbPropValue("btnCloseAdvanceSearch"));
	
	@FindBy(xpath = "//div[@class='calendar left']//*[@class='yearselect']")
    private WebElement selYearleft;
	//By selYearleft = By.xpath(Utils.getRbPropValue("selYearleft"));
	
	@FindBy(xpath = "//div[@class='calendar left']//*[@class='monthselect']")
    private WebElement selMonthLeft;
	//By selMonthLeft = By.xpath(Utils.getRbPropValue("selMonthLeft"));
	
	@FindBy(xpath = "//td[@id='calendar_left-row_0-day_1']")
    private WebElement selfirstDayLeft;
	//By selfirstDayLeft = By.xpath(Utils.getRbPropValue("selfirstDayLeft"));
	
	@FindBy(xpath = "//div[@class='calendar right']//*[@class='yearselect']")
    private WebElement selYearRight;
	//By selYearRight = By.xpath(Utils.getRbPropValue("selYearRight"));
	
	@FindBy(xpath = "//div[@class='calendar right']//*[@class='monthselect']")
    private WebElement selMonthRightt;
	//By selMonthRightt = By.xpath(Utils.getRbPropValue("selMonthRightt"));
	
	@FindBy(xpath = "//td[@id='calendar_right-row_4-day_5']")
    private WebElement selLastDayRight;
	//By selLastDayRight = By.xpath(Utils.getRbPropValue("selLastDayRight"));
	
	@FindBy(xpath = "//a[contains(text(),'Export')]")
    private WebElement lnkExport;
	//By lnkExport = By.xpath(Utils.getRbPropValue("lnkExport"));
	
	@FindBy(xpath = "//*[@id='exp_title_pop']/preceding-sibling::button[@class='close' and  @aria-label='Click to Close']")
    private WebElement btnCloseExport;
	//By btnCloseExport = By.xpath(Utils.getRbPropValue("btnCloseExport"));
	
	@FindBy(css = "#ContentPlaceHolder1_rightpanel_btnexportexcel")
    private WebElement btnExportToExcel;
	//By btnExportToExcel = By.cssSelector(Utils.getRbPropValue("btnExportToExcel"));
	
	@FindBy(xpath = "//table[@id='paperlessbilling']/thead/tr/th")
    private WebElement lblHeaderGrid;
	//By lblHeaderGrid = By.xpath(Utils.getRbPropValue("lblHeaderGrid"));
	
	@FindBy(xpath = "//table[@id='paperlessbilling']/tbody/tr/td[1]")
    private WebElement txtGridDateTime;
	//By txtGridDateTime = By.xpath(Utils.getRbPropValue("txtGridDateTime"));
	
	@FindBy(xpath = "//table[@id='paperlessbilling']/tbody/tr/td[2]")
    private WebElement txtGridAccountNumber;
	//By txtGridAccountNumber = By.xpath(Utils.getRbPropValue("txtGridAccountNumber"));
	
	@FindBy(xpath = "//table[@id='paperlessbilling']/tbody/tr/td[3]")
    private WebElement txtGridCustomerName;
	//By txtGridCustomerName = By.xpath(Utils.getRbPropValue("txtGridCustomerName"));
	
	@FindBy(xpath = "//table[@id='paperlessbilling']/tbody/tr/td[4]")
    private WebElement txtGridEmail;
	//By txtGridEmail = By.xpath(Utils.getRbPropValue("txtGridEmail"));
	
	@FindBy(xpath = "//table[@id='paperlessbilling']/tbody/tr/td[5]")
    private WebElement txtGridBillType;
	//By txtGridBillType = By.xpath(Utils.getRbPropValue("txtGridBillType"));
	
	@FindBy(css = "//table[@id='paperlessbilling']/tbody/tr/td[@class='sorting_1']")
    private WebElement txtGridDataSorted;
	//By txtGridDataSorted = By.xpath(Utils.getRbPropValue("txtGridDataSorted"));
	
	@FindBy(css = "#paperlessbilling_next > a")
    private WebElement btnPaginationNext;
	//By btnPaginationNext = By.cssSelector(Utils.getRbPropValue("btnPaginationNext"));
	
	@FindBy(css = "#paperlessbilling_previous > a")
    private WebElement btnPaginationPrevious;
	//By btnPaginationPrevious = By.cssSelector(Utils.getRbPropValue("btnPaginationPrevious"));
	
	@FindBy(css = "#paperlessbilling_info")
    private WebElement lblPaginationCount;
	//By lblPaginationCount = By.cssSelector(Utils.getRbPropValue("lblPaginationCount"));
	
	@FindBy(css = "#advdate")
    private WebElement txtAdvanceSearchDate;
	//By txtAdvanceSearchDate = By.cssSelector(Utils.getRbPropValue("txtAdvanceSearchDate"));
	
	@FindBy(xpath = "(//input[@name='daterangepicker_start'])[2]")
    private WebElement txtLeftDateAdvance;
	//By txtLeftDateAdvance = By.xpath(Utils.getRbPropValue("txtLeftDateAdvance"));
	
	@FindBy(xpath = "(//input[@name='daterangepicker_end'])[2]")
    private WebElement txtRightAdvanceCalenderDate;
	//By txtRightAdvanceCalenderDate = By.xpath(Utils.getRbPropValue("txtRightAdvanceCalenderDate"));
	
	@FindBy(xpath = "(//button[text()='Apply'])[2]")
    private WebElement btnApplyAdvance;
	//By btnApplyAdvance = By.xpath(Utils.getRbPropValue("btnApplyAdvance"));
	
	@FindBy(css = "#devices > div > div.top-header-area.top-header-areaCstEng > div:nth-child(1) > h2")
    private WebElement lblPaperlessBillingHeader;
	//By lblPaperlessBillingHeader = By.cssSelector(Utils.getRbPropValue("lblPaperlessBillingHeader"));
	
	@FindBy(css = "#advncesrch1")
    private WebElement lblHeaderBannerDetails;
	//By lblHeaderBannerDetails = By.cssSelector(Utils.getRbPropValue("lblHeaderBannerDetails"));
	
	@FindBy(xpath = "//div[@class='emailDetails']/h4")
    private WebElement lblHeadersBannerDetails;
	//By lblHeadersBannerDetails = By.xpath(Utils.getRbPropValue("lblHeadersBannerDetails"));
	
	@FindBy(xpath = "//span[@id='bnDatetIme']/b")
    private WebElement lblDateTimeBannerDetails;
	//By lblDateTimeBannerDetails = By.xpath(Utils.getRbPropValue("lblDateTimeBannerDetails"));
	
	@FindBy(xpath = "//span[@id='bnDatetIme']/i")
    private WebElement txtDateTimeBannerDetails;
	//By txtDateTimeBannerDetails = By.xpath(Utils.getRbPropValue("txtDateTimeBannerDetails"));
	
	@FindBy(xpath = "//span[@id='bnTitle']/b")
    private WebElement lblBannerTitleBannerDetails;
	//By lblBannerTitleBannerDetails = By.xpath(Utils.getRbPropValue("lblBannerTitleBannerDetails"));
	
	@FindBy(xpath = "//span[@id='bnTitle']/i")
    private WebElement txtBannerTitleBannerDetails;
	//By txtBannerTitleBannerDetails = By.xpath(Utils.getRbPropValue("txtBannerTitleBannerDetails"));
	
	@FindBy(xpath = "//span[@id='bnModuleName']/b")
    private WebElement lblModuleBannerDetails;
	//By lblModuleBannerDetails = By.xpath(Utils.getRbPropValue("lblModuleBannerDetails"));
	
	@FindBy(xpath = "//span[@id='bnModuleName']/i")
    private WebElement txtModuleBannerDetails;
	//By txtModuleBannerDetails = By.xpath(Utils.getRbPropValue("txtModuleBannerDetails"));
	
	@FindBy(xpath = "//span[@id='bnFullName']/b")
    private WebElement lblCustomerNameBannerDetails;
	//By lblCustomerNameBannerDetails = By.xpath(Utils.getRbPropValue("lblCustomerNameBannerDetails"));
	
	@FindBy(xpath = "//span[@id='bnFullName']/i")
    private WebElement txtCustomerNameBannerDetails;
	//By txtCustomerNameBannerDetails = By.xpath(Utils.getRbPropValue("txtCustomerNameBannerDetails"));
	
	@FindBy(xpath = "//span[@id='bnEmail']/b")
    private WebElement lblEmailBannerDetails;
	//By lblEmailBannerDetails = By.xpath(Utils.getRbPropValue("lblEmailBannerDetails"));
	
	@FindBy(xpath = "//span[@id='bnEmail']/i")
    private WebElement txtEmailBannerDetails;
	//By txtEmailBannerDetails = By.xpath(Utils.getRbPropValue("txtEmailBannerDetails"));
	
	@FindBy(xpath = "//span[@id='bnAccountNumber']/b")
    private WebElement lblAccountNumberBannerDetails;
	//By lblAccountNumberBannerDetails = By.xpath(Utils.getRbPropValue("txtGridDataSorted"));
	
	@FindBy(xpath = "//span[@id='bnAccountNumber']/i")
    private WebElement txtAccountNumberBannerDetails;
	//By txtAccountNumberBannerDetails = By.xpath(Utils.getRbPropValue("lblAccountNumberBannerDetails"));
	
	@FindBy(css = "#bannerimage")
    private WebElement imgBannerDetails;
	//By imgBannerDetails = By.cssSelector(Utils.getRbPropValue("imgBannerDetails"));
	
	@FindBy(css = "#closediv")
    private WebElement btnCloseBannerDetails;
	//By btnCloseBannerDetails = By.cssSelector(Utils.getRbPropValue("btnCloseBannerDetails"));
	
	@FindBy(css = ".dataTables_empty")
    private WebElement lblNoRecordsFound;
	//By lblNoRecordsFound = By.cssSelector(Utils.getRbPropValue("lblNoRecordsFound"));
	
	@FindBy(css = "#diDialog")
    private WebElement lblAdvanceSearchTitle;
	//By lblAdvanceSearchTitle = By.cssSelector(Utils.getRbPropValue("lblAdvanceSearchTitle"));
	
	@FindBy(xpath = "//div[@class='emailDetails emailAdvanceSearch']/label")
    private WebElement lblDatesAdvanceSearch;
	//By lblDatesAdvanceSearch = By.xpath(Utils.getRbPropValue("lblDatesAdvanceSearch"));
	
	@FindBy(css = "#advancesearchSidebar > div > div > div > div.emailDetails.emailAdvanceSearch > label:nth-child(3)")
    private WebElement lblBannerTitleAdvanceSearch;
	//By lblBannerTitleAdvanceSearch = By.cssSelector(Utils.getRbPropValue("lblBannerTitleAdvanceSearch"));
	
	@FindBy(css = "#advancesearchSidebar > div > div > div > div.emailDetails.emailAdvanceSearch > label:nth-child(5)")
    private WebElement lblModuleAdvanceSearch;
	//By lblModuleAdvanceSearch = By.cssSelector(Utils.getRbPropValue("lblModuleAdvanceSearch"));
	
	@FindBy(css = "#advBannerTitle")
    private WebElement txtBannerTitleAdvanceSearch;
	//By txtBannerTitleAdvanceSearch = By.cssSelector(Utils.getRbPropValue("txtBannerTitleAdvanceSearch"));
	
	@FindBy(css = "#advModule")
    private WebElement txtModuleAdvanceSearch;
	//By txtModuleAdvanceSearch = By.cssSelector(Utils.getRbPropValue("txtModuleAdvanceSearch"));
	
	@FindBy(css = "#emailAddress")
    private WebElement txtEmailAdvanceSearch;
	//By txtEmailAdvanceSearch = By.cssSelector(Utils.getRbPropValue("txtEmailAdvanceSearch"));

}

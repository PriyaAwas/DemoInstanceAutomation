package sew.ai.pageObjects.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sew.ai.pageObjects.scp.HomePage;

public class AnalyticsCustomerEngageBannersPage extends HomePage {
	
	private static final Logger log = LogManager.getLogger(AnalyticsCustomerEngageBannersPage.class);

	public AnalyticsCustomerEngageBannersPage(WebDriver driver) {
		super(driver);
        PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "#ContentPlaceHolder1_cea")
    private WebElement lnkCustomerAI;
	//By lnkCustomerAI = By.cssSelector(Utils.getRbPropValue("lnkCustomerAIandAnalyticsAabp"));
	
	@FindBy(css = ".sidebar_bannerreport")
    private WebElement linkBanners;
    //By linkBanners = By.cssSelector(Utils.getRbPropValue("linkBannersAabp"));
    
    @FindBy(css = "#moduleSearch")
    private WebElement txtModule;
    //By txtModule = By.cssSelector(Utils.getRbPropValue("txtModuleAabp"));
    
    @FindBy(css = "div.reportrange.pull-right.date-tab-width")
    private WebElement txtDates;
    //By txtDates = By.cssSelector(Utils.getRbPropValue("txtDatesAabp"));
    
    @FindBy(css = "#btnAdvCancel")
    private WebElement btnClear;
    //By btnClear = By.cssSelector(Utils.getRbPropValue("btnClaerAabp"));
    
    @FindBy(css = "#btnSearch")
    private WebElement btnSearch;
    //By btnSearch = By.cssSelector(Utils.getRbPropValue("btnSearchAabp"));
    
    @FindBy(css = "#forSearch")
    private WebElement btnAdvanceSearch;
    //By btnAdvanceSearch = By.cssSelector(Utils.getRbPropValue("btnAdvanchSearchAabp"));
    
    @FindBy(css = "#ContentPlaceHolder1_rightpanel_btnexportexcelbanner")
    private WebElement btnExportToExcel;
    //By btnExportToExcel = By.cssSelector(Utils.getRbPropValue("btnExportToExcelAabp"));
    
    @FindBy(css = "#banner_report_Grid_next")
    private WebElement btnPaginationNext;
    //By btnPaginationNext = By.cssSelector(Utils.getRbPropValue("btnPaginationNextAabp"));
    
    @FindBy(css = "#banner_report_Grid_previous")
    private WebElement btnPaginationPrevious;
    //By btnPaginationPrevious = By.cssSelector(Utils.getRbPropValue("btnPaginationPreviousAabp"));
    
    @FindBy(css = "#banner_report_Grid_info")
    private WebElement lblPaginationCount;
    //By lblPaginationCount = By.cssSelector(Utils.getRbPropValue("lblPaginationCountAabp"));
    
    @FindBy(css = "#diDialog")
    private WebElement lblAdvanceSearchTitle;
    //By lblAdvanceSearchTitle = By.cssSelector(Utils.getRbPropValue("lblAdvanceSearchTitleAabp"));
    
    @FindBy(css = "#advBannerTitle")
    private WebElement txtBannerTitleAdvanceSearch;
    //By txtBannerTitleAdvanceSearch = By.cssSelector(Utils.getRbPropValue("txtBannerTitleAdvanceSearchAabp"));
    
    @FindBy(css = "#advModule")
    private WebElement txtModuleAdvanceSearch;
    //By txtModuleAdvanceSearch = By.cssSelector(Utils.getRbPropValue("txtModuleAdvanceSearchAabp"));
    
    @FindBy(css = "#btnAdvCancel")
    private WebElement btnCancelAdvanceSearch;
    //By btnCancelAdvanceSearch = By.cssSelector(Utils.getRbPropValue("btnCancelAdvanceSearchAabp"));
    
    @FindBy(css = "#btnAdvSearch")
    private WebElement btnSearchAdvanceSearch;
    //By btnSearchAdvanceSearch = By.cssSelector(Utils.getRbPropValue("btnSearchAdvanceSearchAabp"));
    
    @FindBy(css = "#advncesrch1")
    private WebElement lblHeaderBannerDetails;
    //By lblHeaderBannerDetails = By.cssSelector(Utils.getRbPropValue("lblHeaderBannerDetailsAabp"));
    
    @FindBy(css = "#bannerimage")
    private WebElement imgBannerDetails;
    //By imgBannerDetails = By.cssSelector(Utils.getRbPropValue("imgBannerDetailsAabp"));
    
    @FindBy(css = "#closediv")
    private WebElement btnCloseBannerDetails;
    //By btnCloseBannerDetails = By.cssSelector(Utils.getRbPropValue("btnCloseBannerDetailsAabp"));
    
    @FindBy(css = ".dataTables_empty")
    private WebElement lblNoRecordsFound;
    //By lblNoRecordsFound = By.cssSelector(Utils.getRbPropValue("lblNoRecordsFoundAabp"));
    
    @FindBy(xpath = "//div[@class='defaulView_fields defaulView_fieldsFirst']/label")
    private WebElement lblModule;
    //By lblModule = By.xpath(Utils.getRbPropValue("lblModuleAabp"));
    
    @FindBy(xpath = "//div[@class='defaulView_emailForm defaulView_emailFormBlock']/div[2]/label")
    private WebElement lblDates;
    //By lblDates = By.xpath(Utils.getRbPropValue("lblDatesAabp"));
    
    @FindBy(xpath = "//li[@data-range-key='Today']")
    private WebElement lstToday;
    //By lstToday = By.xpath(Utils.getRbPropValue("lstTodayAabp"));
    
    @FindBy(xpath = "//li[@data-range-key='Yesterday']")
    private WebElement lstYesterday;
    //By lstYesterday = By.xpath(Utils.getRbPropValue("lstYesterdayAabp"));
    
    @FindBy(xpath = "//li[@data-range-key='Last 7 Days']")
    private WebElement lstLast7Days;
    //By lstLast7Days = By.xpath(Utils.getRbPropValue("lstLast7DaysAabp"));
    
    @FindBy(xpath = "//li[@data-range-key='Last 30 Days']")
    private WebElement lstLast30Days;
    //By lstLast30Days = By.xpath(Utils.getRbPropValue("lstLast30DaysAabp"));
    
    @FindBy(xpath = "//li[@data-range-key='This Month']")
    private WebElement lstThisMonth;
    //By lstThisMonth = By.xpath(Utils.getRbPropValue("lstThisMonthAabp"));
    
    @FindBy(xpath = "//li[@data-range-key='Last Month']")
    private WebElement lstLastMonth;
    //By lstLastMonth = By.xpath(Utils.getRbPropValue("lstLastMonthAabp"));
    
    @FindBy(xpath = "//li[@data-range-key='Custom Range']")
    private WebElement lstCustomRange;
    //By lstCustomRange = By.xpath(Utils.getRbPropValue("lstCustomRangeAabp"));
    
    @FindBy(xpath = "//button[@class='applyBtn btn btn-sm submit-button btn-primary']")
    private WebElement btnApplyDates;
    //By btnApplyDates = By.xpath(Utils.getRbPropValue("btnApplyDatesAabp"));
    
    @FindBy(xpath = "//button[@class='cancelBtn btn btn-sm btn-default']")
    private WebElement btnCancelDates;
    //By btnCancelDates = By.xpath(Utils.getRbPropValue("btnCancelDatesAabp"));
    
    @FindBy(xpath = "//table[@id='banner_report_Grid']/thead/tr/th")
    private WebElement lblHeaderGrid;
    //By lblHeaderGrid = By.xpath(Utils.getRbPropValue("lblHeaderGridAabp"));
    
    @FindBy(xpath = "//table[@id='banner_report_Grid']/tbody/tr/td[1]")
    private WebElement txtGridDateTime;
    //By txtGridDateTime = By.xpath(Utils.getRbPropValue("txtGridDateTimeAabp"));
    
    @FindBy(xpath = "//table[@id='banner_report_Grid']/tbody/tr/td[2]")
    private WebElement txtGridModule;
    //By txtGridModule = By.xpath(Utils.getRbPropValue("txtGridModuleAabp"));
    
    @FindBy(xpath = "//table[@id='banner_report_Grid']/tbody/tr/td[3]")
    private WebElement txtGridBannerTitle;
    //By txtGridBannerTitle = By.xpath(Utils.getRbPropValue("txtGridBannerTtitleAabp"));
    
    @FindBy(xpath = "//div[@class='emailDetails emailAdvanceSearch']/label")
    private WebElement lblDatesAdvanceSearch;
    //By lblDatesAdvanceSearch = By.xpath(Utils.getRbPropValue("lblDatesAdvanceSearchAabp"));
    
    @FindBy(css = "#advancesearchSidebar > div > div > div > div.emailDetails.emailAdvanceSearch > label:nth-child(3)")
    private WebElement lblBannerTitleAdvanceSearch;
    //By lblBannerTitleAdvanceSearch = By.cssSelector(Utils.getRbPropValue("lblBannerTitleAdvanceSearchAabp"));
    
    @FindBy(css = "#advancesearchSidebar > div > div > div > div.emailDetails.emailAdvanceSearch > label:nth-child(5)")
    private WebElement lblModuleAdvanceSearch;
    //By lblModuleAdvanceSearch = By.cssSelector(Utils.getRbPropValue("lblModuleAdvanceSearchAabp"));
    
    @FindBy(xpath = "//a[@class='wrapper_box_close advancesearchclose']")
    private WebElement btnCloseAdvanceSearch;
    //By btnCloseAdvanceSearch = By.xpath(Utils.getRbPropValue("btnCloseAdvanceSearchAabp"));
    
    @FindBy(xpath = "//div[@class='emailDetails']/h4")
    private WebElement lblHeadersBannerDetails;
    //By lblHeadersBannerDetails = By.xpath(Utils.getRbPropValue("lblHeadersBannerDetailsAabp"));
    
    @FindBy(xpath = "//span[@id='bnDatetIme']/b")
    private WebElement lblDateTimeBannerDetails;
    //By lblDateTimeBannerDetails = By.xpath(Utils.getRbPropValue("lblDateTimeBannerDetailsAabp"));
    
    @FindBy(xpath = "//span[@id='bnDatetIme']/i")
    private WebElement txtDateTimeBannerDetails;
    //By txtDateTimeBannerDetails = By.xpath(Utils.getRbPropValue("txtDateTimeBannerDetailsAabp"));
    
    @FindBy(xpath = "//span[@id='bnTitle']/b")
    private WebElement lblBannerTitleBannerDetails;
    //By lblBannerTitleBannerDetails = By.xpath(Utils.getRbPropValue("lblBannerTitleBannerDetailsAabp"));
    
    @FindBy(xpath = "//span[@id='bnTitle']/i")
    private WebElement txtBannerTitleBannerDetails;
    //By txtBannerTitleBannerDetails = By.xpath(Utils.getRbPropValue("txtBannerTitleBannerDetailsAabp"));
    
    @FindBy(xpath = "//span[@id='bnModuleName']/b")
    private WebElement lblModuleBannerDetails;
    //By lblModuleBannerDetails = By.xpath(Utils.getRbPropValue("lblModuleBannerDetailsAabp"));
    
    @FindBy(xpath = "//span[@id='bnFullName']/b")
    private WebElement lblCustomerNameBannerDetails;
    //By lblCustomerNameBannerDetails = By.xpath(Utils.getRbPropValue("lblCustomerNameBannerDetailsAabp"));
    
    @FindBy(xpath = "//span[@id='bnFullName']/i")
    private WebElement txtCustomerNameBannerDetails;
    //By txtCustomerNameBannerDetails = By.xpath(Utils.getRbPropValue("txtCustomerNameBannerDetailsAabp"));
    
    @FindBy(xpath = "//span[@id='bnEmail']/b")
    private WebElement lblEmailBannerDetails;
    //By lblEmailBannerDetails = By.xpath(Utils.getRbPropValue("lblEmailBannerDetailsAabp"));
    
    @FindBy(xpath = "//span[@id='bnEmail']/i")
    private WebElement txtEmailBannerDetails;
    //By txtEmailBannerDetails = By.xpath(Utils.getRbPropValue("txtEmailBannerDetailsAabp"));
    
    @FindBy(xpath = "//span[@id='bnAccountNumber']/b")
    private WebElement lblAccountNumberBannerDetails;
    //By lblAccountNumberBannerDetails = By.xpath(Utils.getRbPropValue("lblAccountNumberBannerDetailsAabp"));
    
    @FindBy(xpath = "//span[@id='bnAccountNumber']/i")
    private WebElement txtAccountNumberBannerDetails;
    //By txtAccountNumberBannerDetails = By.xpath(Utils.getRbPropValue("txtAccountNumberBannerDetailsAabp"));
    
    @FindBy(xpath = "//span[@id='bnModuleName']/i")
    private WebElement txtModuleBannerDetails;
    //By txtModuleBannerDetails = By.xpath(Utils.getRbPropValue("txtModuleBannerDetailsAabp"));
    
    @FindBy(xpath = "//div[@class='calendar left']//*[@class='yearselect']")
    private WebElement selYearLeft;
    //By selYearLeft = By.xpath(Utils.getRbPropValue("selYearleftAabp"));
    
    @FindBy(xpath = "//div[@class='calendar left']//*[@class='monthselect']")
    private WebElement selMonthLeft;
    //By selMonthLeft = By.xpath(Utils.getRbPropValue("selMonthLeftAabp"));
    
    @FindBy(xpath = "//td[@id='calendar_left-row_0-day_1']")
    private WebElement selFirstDayLeft;
    //By selFirstDayLeft = By.xpath(Utils.getRbPropValue("selfirstDayLeftAabp"));
    
    @FindBy(xpath = "//div[@class='calendar right']//*[@class='yearselect']")
    private WebElement selYearRight;
    //By selYearRight = By.xpath(Utils.getRbPropValue("selYearRightAabp"));
    
    @FindBy(xpath = "//div[@class='calendar right']//*[@class='monthselect']")
    private WebElement selMonthRight;
    //By selMonthRight = By.xpath(Utils.getRbPropValue("selMonthRighttAabp"));
    
    @FindBy(xpath = "//td[@id='calendar_right-row_4-day_5']")
    private WebElement selLastDayRight;
    //By selLastDayRight = By.xpath(Utils.getRbPropValue("selLastDayRightAabp"));
    
    @FindBy(xpath = "//table[@id='banner_report_Grid']/tbody/tr/td[@class='sorting_1']")
    private WebElement txtGridDataSorted;
    //By txtGridDataSorted = By.xpath(Utils.getRbPropValue("txtGridDataSortedAabp"));
    
    @FindBy(xpath = "//a[contains(text(),'Export')]")
    private WebElement lnkExcel;
    //By lnkExcel = By.xpath(Utils.getRbPropValue("lnkExportAabp"));
    
    @FindBy(xpath = "//*[@id='exp_title_pop']/preceding-sibling::button[@class='close' and  @aria-label='Click to Close']")
    private WebElement btnCloseExport;
    //By btnCloseExport = By.xpath(Utils.getRbPropValue("btnCloseExportAabp"));
    
    @FindBy(xpath = "//input[@name='daterangepicker_start']")
    private WebElement txtLeftCalenderDate;
    //By txtLeftCalenderDate = By.xpath(Utils.getRbPropValue("txtToDateAabp"));
    
    @FindBy(xpath = "//input[@name='daterangepicker_end']")
    private WebElement txtRightCalenderDate;
    //By txtRightCalenderDate = By.xpath(Utils.getRbPropValue("txtFromDateAabp"));
    
    @FindBy(css = "#advdate")
    private WebElement txtAdvanceSearchDateAabp;
    //By txtAdvanceSearchDateAabp = By.cssSelector(Utils.getRbPropValue("txtAdvanceSearchDateAabp"));
    
    @FindBy(xpath = "(//input[@name='daterangepicker_start'])[2]")
    private WebElement txtLeftDateAdvanceAabp;
	//By txtLeftDateAdvanceAabp = By.xpath(Utils.getRbPropValue("txtLeftDateAdvanceAabp"));
	
	@FindBy(xpath = "(//input[@name='daterangepicker_end'])[2]")
    private WebElement txtRightAdvanceCalenderDate;
    //By txtRightAdvanceCalenderDate = By.xpath(Utils.getRbPropValue("txtRightAdvanceCalenderDateAabp"));
    
    @FindBy(xpath = "(//button[text()='Apply'])[2]")
    private WebElement btnApplyAdvanceAabp;
    //By btnApplyAdvanceAabp = By.xpath(Utils.getRbPropValue("btnApplyAdvanceAabp")); 
    
    @FindBy(css = "#buttonClear")
    private WebElement btnClearAabp;
    //By btnClearAabp = By.cssSelector(Utils.getRbPropValue("btnClearAabp"));

}

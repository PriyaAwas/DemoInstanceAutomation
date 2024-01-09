package sew.ai.pageObjects.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sew.ai.pageObjects.scp.HomePage;


public class AnalyticsCustomerEngagementDashboardPage extends HomePage {
	
	private static final Logger log = LogManager.getLogger(AnalyticsCustomerEngagementDashboardPage.class);

	public AnalyticsCustomerEngagementDashboardPage(WebDriver driver) {
		super(driver);
        PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "#ContentPlaceHolder1_cea")
    private WebElement lnkCustomerAIandAnalytics;
	//By lnkCustomerAIandAnalytics = By.cssSelector(Utils.getRbPropValue("lnkCustomerAIandAnalyticsCed"));
	
	@FindBy(xpath = "//li[@data-range-key='Today']")
    private WebElement lstToday;
	//By lstToday = By.xpath(Utils.getRbPropValue("lstTodayCed"));
	
	@FindBy(xpath = "//li[@data-range-key='Yesterday']")
    private WebElement lstYesterday;
	//By lstYesterday = By.xpath(Utils.getRbPropValue("lstYesterdayCed"));
	
	@FindBy(xpath = "//li[@data-range-key='Last 7 Days']")
    private WebElement lstLast7Days;
	//By lstLast7Days = By.xpath(Utils.getRbPropValue("lstLast7DaysCed"));
	
	@FindBy(xpath = "//li[@data-range-key='Last 30 Days']")
    private WebElement lstLast30Days;
	//By lstLast30Days = By.xpath(Utils.getRbPropValue("lstLast30DaysCed"));
	
	@FindBy(xpath = "//li[@data-range-key='This Month']")
    private WebElement lstThisMonth;
	//By lstThisMonth = By.xpath(Utils.getRbPropValue("lstThisMonthCed"));
	
	@FindBy(xpath = "//li[@data-range-key='Last Month']")
    private WebElement lstLastMonth;
	//By lstLastMonth = By.xpath(Utils.getRbPropValue("lstLastMonthCed"));
	
	@FindBy(xpath = "//li[@data-range-key='Custom Range']")
    private WebElement lstCustomRange;
	//By lstCustomRange = By.xpath(Utils.getRbPropValue("lstCustomRangeCed"));
	
	@FindBy(xpath = "//button[@class='applyBtn btn btn-sm submit-button btn-primary']")
    private WebElement btnApplyDates;
	//By btnApplyDates = By.xpath(Utils.getRbPropValue("btnApplyDatesCed"));
	
	@FindBy(xpath = "//button[@class='cancelBtn btn btn-sm btn-default']")
    private WebElement btnCancelDates;
	//By btnCancelDates = By.xpath(Utils.getRbPropValue("btnCancelDatesCed"));
	
	@FindBy(xpath = "//input[@name='daterangepicker_start']")
    private WebElement txtToDate;
	//By txtToDate = By.xpath(Utils.getRbPropValue("txtToDateCed"));
	
	@FindBy(xpath = "//input[@name='daterangepicker_end']")
    private WebElement txtFromDate;
	//By txtFromDate = By.xpath(Utils.getRbPropValue("txtFromDateCed"));
	
	@FindBy(css = "#devices > div > div.top-header-area.top-header-areaCstEng > div.chartValue > div")
    private WebElement txtDates;
	//By txtDates = By.cssSelector(Utils.getRbPropValue("txtDatesCed"));
	
	@FindBy(css = "#spnTotalBanner > b")
    private WebElement lblBannerClickCount;
	//By lblBannerClickCount = By.cssSelector(Utils.getRbPropValue("lblBannerClickCountCed"));
	
	@FindBy(css = "#spnTotalBanner > i")
    private WebElement lblBannerClickName;
	//By lblBannerClickName = By.cssSelector(Utils.getRbPropValue("lblBannerClickNameCed"));
	
	@FindBy(css = "#trigger1")
    private WebElement lblBannerClickBlock;
	//By lblBannerClickBlock = By.cssSelector(Utils.getRbPropValue("lblBannerClickBlockCed"));
	
	@FindBy(css = "g > path[fill='#0995e2']")
    private WebElement lblBannerClickGraph;
	//By lblBannerClickGraph = By.cssSelector(Utils.getRbPropValue("lblBannerClickGraphCed"));
	
	@FindBy(css = "#spnTotalConnectMe > b")
    private WebElement lblConnectMeRequestCount;
	//By lblConnectMeRequestCount = By.cssSelector(Utils.getRbPropValue("lblConnectMeRequestCountCed"));
	
	@FindBy(css = "#spnTotalConnectMe > i")
    private WebElement lblConnectMeRequestName;
	//By lblConnectMeRequestName = By.cssSelector(Utils.getRbPropValue("lblConnectMeRequestNameCed"));
	
	@FindBy(css = "#trigger2")
    private WebElement lblConnectMeRequestBlock;
	//By lblConnectMeRequestBlock = By.cssSelector(Utils.getRbPropValue("lblConnectMeRequestBlockCed"));
	
	@FindBy(css = "g > path[fill='#00d417']")
    private WebElement lblConnectMeRequestGraph;
	//By lblConnectMeRequestGraph = By.cssSelector(Utils.getRbPropValue("lblConnectMeRequestGraphCed"));
	
	@FindBy(css = "#spnTotalServiceRequest > b")
    private WebElement lblServiceRequestCount;
	//By lblServiceRequestCount = By.cssSelector(Utils.getRbPropValue("lblServiceRequestCountCed"));
	
	@FindBy(css = "#spnTotalServiceRequest > i")
    private WebElement lblServiceRequestName;
	//By lblServiceRequestName = By.cssSelector(Utils.getRbPropValue("lblServiceRequestNameCed"));
	
	@FindBy(css = "#trigger3")
    private WebElement lblServiceRequestBlock;
	//By lblServiceRequestBlock = By.cssSelector(Utils.getRbPropValue("lblServiceRequestBlockCed"));
	
	@FindBy(css = "g > path[fill='#e6cd41']")
    private WebElement lblServiceRequestGraph;
	//By lblServiceRequestGraph = By.cssSelector(Utils.getRbPropValue("lblServiceRequestGraphCed"));
	
	@FindBy(css = "svg > g.highcharts-label.highcharts-tooltip.highcharts-color-undefined > text > tspan:nth-child(4)")
    private WebElement lblGraphValue;
	//By lblGraphValue = By.cssSelector(Utils.getRbPropValue("lblGraphValueCed"));
	
	@FindBy(css = "#containertop_banner > div >svg > g.highcharts-series-group > g> path:not([visibility=\"hidden\"])")
    private WebElement bannerClickPie;
	//By bannerClickPie = By.cssSelector(Utils.getRbPropValue("bannerClickPieCed"));
	
	@FindBy(css = "#containertop_banner > div > svg >  g.highcharts-label.highcharts-tooltip > text > tspan:nth-child(2)")
    private WebElement bannerClickPieValue;
	//By bannerClickPieValue = By.cssSelector(Utils.getRbPropValue("bannerClickPieValueCed"));
	
	@FindBy(css = "//a[text()='Banner Clicks']")
    private WebElement lnkBannerClick;
	//By lnkBannerClick = By.xpath(Utils.getRbPropValue("lnkBannerClickCed"));
	
	@FindBy(css = "#voiceCallsChartDivCust> div >svg > g.highcharts-series-group > g> path:not([visibility=\"hidden\"])")
    private WebElement connectMeRequestPie;
	//By connectMeRequestPie = By.cssSelector(Utils.getRbPropValue("connectMeRequestPieCed"));
	
	@FindBy(css = "#voiceCallsChartDivCust> div > svg >  g.highcharts-label.highcharts-tooltip > text > tspan:nth-child(2)")
    private WebElement connectMeRequestPieValue;
	//By connectMeRequestPieValue = By.cssSelector(Utils.getRbPropValue("connectMeRequestPieValueCed"));
	
	@FindBy(xpath = "//a[text()='Connect me Requests']")
    private WebElement lnkConnectMeRequest;
	//By lnkConnectMeRequest = By.xpath(Utils.getRbPropValue("lnkConnectMeRequestCed"));
	
	@FindBy(css = "#screen_resolution_detailscust> div >svg > g.highcharts-series-group > g> path")
    private WebElement serviceRequestPie;
	//By serviceRequestPie = By.cssSelector(Utils.getRbPropValue("serviceRequestPieCed"));
	
	@FindBy(css = "#screen_resolution_detailscust> div > svg >  g.highcharts-label.highcharts-tooltip > text > tspan:nth-child(2)")
    private WebElement serviceRequestPieValue;
	//By serviceRequestPieValue = By.cssSelector(Utils.getRbPropValue("serviceRequestPieValueCed"));
	
	@FindBy(xpath = "//a[text()='Service Requests']")
    private WebElement lnkServiceRequest;
	//By lnkServiceRequest = By.xpath(Utils.getRbPropValue("lnkServiceRequestCed"));
	
	@FindBy(css = "#containertop_banner>div > svg > g.highcharts-legend.highcharts-no-tooltip > g > g>g > text")
    private WebElement bannerClickFooters;
	//By bannerClickFooters = By.cssSelector(Utils.getRbPropValue("bannerClickFootersCed"));
	
	@FindBy(css = "#voiceCallsChartDivCust>div > svg > g.highcharts-legend.highcharts-no-tooltip > g > g>g > text")
    private WebElement connectMeRequestFooters;
	//By connectMeRequestFooters = By.cssSelector(Utils.getRbPropValue("connectMeRequestFootersCed"));
	
	@FindBy(css = "#screen_resolution_detailscust>div > svg > g.highcharts-legend.highcharts-no-tooltip > g > g>g > text")
    private WebElement serviceRequestFooters;
	//By serviceRequestFooters = By.cssSelector(Utils.getRbPropValue("serviceRequestFootersCed"));

}

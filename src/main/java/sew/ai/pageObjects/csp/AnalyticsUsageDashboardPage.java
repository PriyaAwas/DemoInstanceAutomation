package sew.ai.pageObjects.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sew.ai.pageObjects.scp.HomePage;

public class AnalyticsUsageDashboardPage extends HomePage{
	
	private static final Logger log = LogManager.getLogger(AnalyticsUsageDashboardPage.class);

	public AnalyticsUsageDashboardPage(WebDriver driver) {
		super(driver);
        PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "#ContentPlaceHolder1_cea")
    private WebElement lnkCustomerAIandAnalytics;
	//By lnkCustomerAIandAnalytics = By.cssSelector(Utils.getRbPropValue("lnkCustomerAIandAnalyticsAud"));
	
	@FindBy(css = ".sidebar li[aria-label='Usage'] > a[href*='usage']")
    private WebElement lnkUsage;
	//By lnkUsage = By.cssSelector(Utils.getRbPropValue("lnkUsageAud"));
	
	@FindBy(css = "//a[contains(text(),'Solar')]")
    private WebElement lnkSolar;
	//By lnkSolar = By.xpath(Utils.getRbPropValue("lnkSolarAud"));
	
	@FindBy(xpath = "//a[contains(text(),'Power')]")
    private WebElement lnkPower;
	//By lnkPower = By.xpath(Utils.getRbPropValue("lnkPowerAud"));
	
	@FindBy(xpath = "//a[contains(text(),'Water')]")
    private WebElement lnkWater;
	//By lnkWater = By.xpath(Utils.getRbPropValue("lnkWaterAud"));
	
	@FindBy(xpath = "//a[contains(text(),'Gas')]")
    private WebElement lnkGas;
	//By lnkGas = By.xpath(Utils.getRbPropValue("lnkGasAud"));
	
	@FindBy(xpath = "//li[@data-range-key='Today']")
    private WebElement lstToday;
	//By lstToday = By.xpath(Utils.getRbPropValue("lstTodayAud"));
	
	@FindBy(xpath = "//li[@data-range-key='Yesterday']")
    private WebElement lstYesterday;
	//By lstYesterday = By.xpath(Utils.getRbPropValue("lstYesterdayAud"));
	
	@FindBy(xpath = "//li[@data-range-key='Last 7 Days']")
    private WebElement lstLast7Days;
	//By lstLast7Days = By.xpath(Utils.getRbPropValue("lstLast7DaysAud"));
	
	@FindBy(xpath = "//li[@data-range-key='Last 30 Days']")
    private WebElement lstLast30Days;
	//By lstLast30Days = By.xpath(Utils.getRbPropValue("lstLast30DaysAud"));
	
	@FindBy(xpath = "//li[@data-range-key='This Month']")
    private WebElement lstThisMonth;
	//By lstThisMonth = By.xpath(Utils.getRbPropValue("lstThisMonthAud"));
	
	@FindBy(xpath = "//li[@data-range-key='Last Month']")
    private WebElement lstLastMonth;
	//By lstLastMonth = By.xpath(Utils.getRbPropValue("lstLastMonthAud"));
	
	@FindBy(xpath = "//li[@data-range-key='Custom Range']")
    private WebElement lstCustomRange;
	//By lstCustomRange= By.xpath(Utils.getRbPropValue("lstCustomRangeAud"));
	
	@FindBy(xpath = "//button[@class='applyBtn btn btn-sm submit-button btn-primary']")
    private WebElement btnApplyDates;
	//By btnApplyDates = By.xpath(Utils.getRbPropValue("btnApplyDatesAud"));
	
	@FindBy(xpath = "//button[@class='cancelBtn btn btn-sm btn-default']")
    private WebElement btnCancelDates;
	//By btnCancelDates = By.xpath(Utils.getRbPropValue("btnCancelDatesAud"));
	
	@FindBy(xpath = "//input[@name='daterangepicker_start']")
    private WebElement txtToDate;
	//By txtToDate= By.xpath(Utils.getRbPropValue("txtToDateAud"));
	
	@FindBy(xpath = "//input[@name='daterangepicker_end']")
    private WebElement txtFromDate;
	//By txtFromDate = By.xpath(Utils.getRbPropValue("txtFromDateAud"));
	
	@FindBy(css = "#devices > div > div.top-header-area.top-header-areaCstEng > div.chartValue > div")
    private WebElement txtDates;
	//By txtDates = By.cssSelector(Utils.getRbPropValue("txtDatesAud"));
		
	@FindBy(css = "#spnSolar > b")
    private WebElement lblSolarGenerationCount;
	//By lblSolarGenerationCount = By.cssSelector(Utils.getRbPropValue("lblSolarGenerationCountAud"));
	
	@FindBy(css = "#spnSolar > i")
    private WebElement lblSolarGenerationName;
	//By lblSolarGenerationName = By.cssSelector(Utils.getRbPropValue("lblSolarGenerationNameAud"));
	
	@FindBy(css = "#trigger1")
    private WebElement lblSolarGenerationBlock;
	//By lblSolarGenerationBlock = By.cssSelector(Utils.getRbPropValue("lblSolarGenerationBlockAud"));
	
	@FindBy(css = "g > path[fill='#0995e2']")
    private WebElement lblSolarGenerationGraph;
	//By lblSolarGenerationGraph= By.cssSelector(Utils.getRbPropValue("lblSolarGenerationGraphAud"));
	
	@FindBy(css = "#spnPower> b")
    private WebElement lblPowerUsageCount;
	//By lblPowerUsageCount = By.cssSelector(Utils.getRbPropValue("lblPowerUsageCountAud"));
	
	@FindBy(css = "#spnPower> i")
    private WebElement lblPowerUsageName;
	//By lblPowerUsageName = By.cssSelector(Utils.getRbPropValue("lblPowerUsageNameAud"));
	
	@FindBy(css = "#trigger2")
    private WebElement lblPowerUsageBlock;
	//By lblPowerUsageBlock=By.cssSelector(Utils.getRbPropValue("lblPowerUsageBlockAud"));
	
	@FindBy(css = "g > path[fill='#00d417']")
    private WebElement lblPowerUsageGraph;
	//By lblPowerUsageGraph = By.cssSelector(Utils.getRbPropValue("lblPowerUsageGraphAud"));
	
	@FindBy(css = "#spnGas> b")
    private WebElement lblGasUsageCount;
	//By lblGasUsageCount = By.cssSelector(Utils.getRbPropValue("lblGasUsageCountAud"));
	
	@FindBy(css = "#spnGas> i")
    private WebElement lblGasUsageName;
	//By lblGasUsageName = By.cssSelector(Utils.getRbPropValue("lblGasUsageNameAud"));
	
	@FindBy(css = "#trigger3")
    private WebElement lblGasUsageBlock;
	//By lblGasUsageBlock = By.cssSelector(Utils.getRbPropValue("lblGasUsageBlockAud"));
	
	@FindBy(css = "g > path[fill='#e6cd41']")
    private WebElement lblGasUsageGraph;
	//By lblGasUsageGraph = By.cssSelector(Utils.getRbPropValue("lblGasUsageGraphAud"));
	
	@FindBy(css = "#spnWater> b")
    private WebElement lblWaterUsageCount;	
	//By lblWaterUsageCount = By.cssSelector(Utils.getRbPropValue("lblWaterUsageCountAud"));
	
	@FindBy(css = "#spnWater> i")
    private WebElement lblWaterUsageName;
	//By lblWaterUsageName= By.cssSelector(Utils.getRbPropValue("lblWaterUsageNameAud"));
	
	@FindBy(css = "#trigger4")
    private WebElement lblWaterUsageBlock;
	//By lblWaterUsageBlock = By.cssSelector(Utils.getRbPropValue("lblWaterUsageBlockAud"));
	
	@FindBy(css = "g > path[fill='#e06567']")
    private WebElement lblWaterUsageGraph;
	//By lblWaterUsageGraph= By.cssSelector(Utils.getRbPropValue("lblWaterUsageGraphAud"));
	
	@FindBy(css = "svg > g.highcharts-label.highcharts-tooltip.highcharts-color-undefined > text > tspan:nth-child(4)")
    private WebElement lblGraphValue;
	//By lblGraphValue = By.cssSelector(Utils.getRbPropValue("lblGraphValueAud"));

}

package sew.ai.pageObjects.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sew.ai.pageObjects.scp.HomePage;

public class AnalyticsCustomerPreferenceDashboardPage extends HomePage {
	
	private static final Logger log = LogManager.getLogger(AnalyticsCustomerPreferenceDashboardPage.class);

	public AnalyticsCustomerPreferenceDashboardPage(WebDriver driver) {
		super(driver);
        PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "#ContentPlaceHolder1_cea")
    private WebElement lnkCustomerAIandAnalytics;
	//By lnkCustomerAIandAnalytics = By.cssSelector(Utils.getRbPropValue("lnkCustomerAIandAnalyticsCpd"));
	
	@FindBy(css = ".sidebar li[aria-label='Customer Preferences'] > a[href*='CustomerPreference']")
    private WebElement lnkCustomerPreference;
	//By lnkCustomerPreference = By.cssSelector(Utils.getRbPropValue("lnkCustomerPreferenceCpd"));
	
	@FindBy(xpath = "//a[contains(text(),' Marketing Preference')]")
    private WebElement lnkMarketingPreference;
	//By lnkMarketingPreference = By.xpath(Utils.getRbPropValue("lnkMarketingPreferenceCpd"));
	
	@FindBy(xpath = "//li[@data-range-key='Today']")
    private WebElement lstToday;
	//By lstToday = By.xpath(Utils.getRbPropValue("lnkMarketingPreferenceCpd"));
	
	@FindBy(xpath = "//li[@data-range-key='Yesterday']")
    private WebElement lstYesterday;
	//By lstYesterday = By.xpath(Utils.getRbPropValue("lstYesterdayCpd"));
	
	@FindBy(xpath = "//li[@data-range-key='Last 7 Days']")
    private WebElement lstLast7Days;
	//By lstLast7Days = By.xpath(Utils.getRbPropValue("lstLast7DaysCpd"));
	
	@FindBy(xpath = "//li[@data-range-key='Last 30 Days']")
    private WebElement lstLast30Days;
	//By lstLast30Days = By.xpath(Utils.getRbPropValue("lstLast30DaysCpd"));
	
	@FindBy(xpath = "//li[@data-range-key='This Month']")
    private WebElement lstThisMonth;
	//By lstThisMonth = By.xpath(Utils.getRbPropValue("lstThisMonthCpd"));
	
	@FindBy(xpath = "//li[@data-range-key='Last Month']")
    private WebElement lstLastMonth;
	//By lstLastMonth = By.xpath(Utils.getRbPropValue("lstLastMonthCpd"));
	
	@FindBy(xpath = "//li[@data-range-key='Custom Range']")
    private WebElement lstCustomRange;
	//By lstCustomRange= By.xpath(Utils.getRbPropValue("lstCustomRangeCpd"));
	
	@FindBy(xpath = "//button[@class='applyBtn btn btn-sm submit-button btn-primary']")
    private WebElement btnApplyDates;
	//By btnApplyDates = By.xpath(Utils.getRbPropValue("btnApplyDatesCpd"));
	
	@FindBy(xpath = "//button[@class='cancelBtn btn btn-sm btn-default']")
    private WebElement btnCancelDates;
	//By btnCancelDates = By.xpath(Utils.getRbPropValue("btnCancelDatesCpd"));
	
	@FindBy(xpath = "//input[@name='daterangepicker_start']")
    private WebElement txtToDate;
	//By txtToDate= By.xpath(Utils.getRbPropValue("txtToDateCpd"));
	
	@FindBy(xpath = "//input[@name='daterangepicker_end']")
    private WebElement txtFromDate;
	//By txtFromDate = By.xpath(Utils.getRbPropValue("txtFromDateCpd"));
	
	@FindBy(css = "#devices > div > div.top-header-area.top-header-areaCstEng > div.chartValue > div")
    private WebElement txtDates;
	//By txtDates = By.cssSelector(Utils.getRbPropValue("txtDatesCpd"));
	
	@FindBy(css = "#spnTotalCustomer> b")
    private WebElement lblTotalAccountsCount;
	//By lblTotalAccountsCount = By.cssSelector(Utils.getRbPropValue("lblTotalAccountsCountCpd"));
	
	@FindBy(css = "#spnTotalCustomer> i")
    private WebElement lblTotalAccountsName;
	//By lblTotalAccountsName = By.cssSelector(Utils.getRbPropValue("lblTotalAccountsNameCpd"));
	
	@FindBy(css = "#trigger2")
    private WebElement lblTotalAccountsBlock;
	//By lblTotalAccountsBlock = By.cssSelector(Utils.getRbPropValue("lblTotalAccountsBlockCpd"));
	
	@FindBy(css = "g > path[fill='#00FF00']")
    private WebElement lblTotalAccountsGraph;
	//By lblTotalAccountsGraph= By.cssSelector(Utils.getRbPropValue("lblTotalAccountsGraphCpd"));
	
	@FindBy(css = "#spnPaperLessEnrolled> b")
    private WebElement lblPaperlessBillAccountsCount;
	//By lblPaperlessBillAccountsCount = By.cssSelector(Utils.getRbPropValue("lblPaperlessBillAccountsCountCpd"));
	
	@FindBy(css = "#spnPaperLessEnrolled> i")
    private WebElement lblPaperlessBillAccountsName;
	//By lblPaperlessBillAccountsName = By.cssSelector(Utils.getRbPropValue("lblPaperlessBillAccountsNameCpd"));
	
	@FindBy(css = "#trigger5")
    private WebElement lblPaperlessBillAccountsBlock;
	//By lblPaperlessBillAccountsBlock=By.cssSelector(Utils.getRbPropValue("lblPaperlessBillAccountsBlockCpd"));
	
	@FindBy(css = "g > path[fill='#0000FF']")
    private WebElement lblPaperlessBillAccountsGraph;
	//By lblPaperlessBillAccountsGraph = By.cssSelector(Utils.getRbPropValue("lblPaperlessBillAccountsGraphCpd"));
	
	@FindBy(css = "#spnPaperEnrolled> b")
    private WebElement lblPaperBillAccountsCount;
	//By lblPaperBillAccountsCount = By.cssSelector(Utils.getRbPropValue("lblPaperBillAccountsCountCpd"));
	
	@FindBy(css = "#spnPaperEnrolled> i")
    private WebElement lblPaperBillAccountsName;
	//By lblPaperBillAccountsName = By.cssSelector(Utils.getRbPropValue("lblPaperBillAccountsNameCpd"));
	
	@FindBy(css = "#trigger6")
    private WebElement lblPaperBillAccountsBlock;
	//By lblPaperBillAccountsBlock = By.cssSelector(Utils.getRbPropValue("lblPaperBillAccountsBlockCpd"));
	
	@FindBy(css = "g > path[fill='#a88e32']")
    private WebElement lblPaperBillAccountsGraph;
	//By lblPaperBillAccountsGraph = By.cssSelector(Utils.getRbPropValue("lblPaperBillAccountsGraphCpd"));
	
	@FindBy(css = "#spnBothEnrolled> b")
    private WebElement lblBothCount;
	//By lblBothCount = By.cssSelector(Utils.getRbPropValue("lblBothCountCpd"));
	
	@FindBy(css = "#spnBothEnrolled> i")
    private WebElement lblBothName;
	//By lblBothName= By.cssSelector(Utils.getRbPropValue("lblBothNameCpd"));
	
	@FindBy(css = "#trigger7")
    private WebElement lblBothBlock;
	//By lblBothBlock = By.cssSelector(Utils.getRbPropValue("lblBothBlockCpd"));
	
	@FindBy(css = "g > path[fill='#FF0000']")
    private WebElement lblBothGraph;
	//By lblBothGraph= By.cssSelector(Utils.getRbPropValue("lblBothGraphCpd"));
	
	@FindBy(css = "#spnMP> b")
    private WebElement lblMarketingPreferenceCount;
	//By lblMarketingPreferenceCount = By.cssSelector(Utils.getRbPropValue("lblMarketingPreferenceCountCpd"));
	
	@FindBy(css = "#spnMP> i")
    private WebElement lblMarketingPreferenceName;
	//By lblMarketingPreferenceName= By.cssSelector(Utils.getRbPropValue("lblMarketingPreferenceNameCpd"));
	
	@FindBy(css = "#trigger8")
    private WebElement lblMarketingPreferenceBlock;
	//By lblMarketingPreferenceBlock = By.cssSelector(Utils.getRbPropValue("lblMarketingPreferenceBlockCpd"));
	
	@FindBy(css = "g > path[fill='#FF9999']")
    private WebElement lblMarketingPreferenceGraph;
	//By lblMarketingPreferenceGraph= By.cssSelector(Utils.getRbPropValue("lblMarketingPreferenceGraphCpd"));
	
	@FindBy(css = "svg > g.highcharts-label.highcharts-tooltip.highcharts-color-undefined > text > tspan:nth-child(4)")
    private WebElement lblGraphValue;
	//By lblGraphValue = By.cssSelector(Utils.getRbPropValue("lblGraphValueCpd"));
	
	@FindBy(css = "#paperBill> div >svg > g.highcharts-series-group > g> path:not([visibility=\"hidden\"])")
    private WebElement billTypePie;
	//By billTypePie = By.cssSelector(Utils.getRbPropValue("billTypePieCpd"));
	
	@FindBy(css = "#paperBill > div > svg >  g.highcharts-label.highcharts-tooltip > text > tspan:nth-child(2)")
    private WebElement billTypePieValue;
	//By billTypePieValue = By.cssSelector(Utils.getRbPropValue("billTypePieValueCpd"));
	
	@FindBy(xpath = "//a[text()='Bill Type']")
    private WebElement lnkBillTypePie;
	//By lnkBillTypePie= By.xpath(Utils.getRbPropValue("lnkBillTypePieCpd"));
	
	@FindBy(css = "#marktingPrefrences> div >svg > g.highcharts-series-group > g> path:not([visibility=\"hidden\"])")
    private WebElement marketingPreferencePie;
	//By marketingPreferencePie = By.cssSelector(Utils.getRbPropValue("marketingPreferencePieCpd"));
	
	@FindBy(css = "#marktingPrefrences > div > svg >  g.highcharts-label.highcharts-tooltip > text > tspan:nth-child(2)")
    private WebElement marketingPreferencePieValue;
	//By marketingPreferencePieValue = By.cssSelector(Utils.getRbPropValue("marketingPreferencePieValueCpd"));
	
	@FindBy(xpath = "(//a[contains(text(),'Marketing Preference')])[2]")
    private WebElement lnkMarketingPreferencePie;
	//By lnkMarketingPreferencePie = By.xpath(Utils.getRbPropValue("lnkMarketingPreferencePieCpd"));
	
	@FindBy(css = "#paperBill >div > svg > g.highcharts-legend.highcharts-no-tooltip > g > g>g > text")
    private WebElement billTypeFooters;
	//By billTypeFooters= By.cssSelector(Utils.getRbPropValue("billTypeFootersCpd"));
	
	@FindBy(css = "#marktingPrefrences>div > svg > g.highcharts-legend.highcharts-no-tooltip > g > g>g > text")
    private WebElement marketingPreferenceFooters;
	//By marketingPreferenceFooters = By.cssSelector(Utils.getRbPropValue("marketingPreferenceFootersCpd"));

}

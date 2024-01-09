package sew.ai.pageObjects.scp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BudgetMyBillPage extends HomePage {
    private static final Logger log = LogManager.getLogger(BudgetMyBillPage.class);

    public BudgetMyBillPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input#btnSaveChanges.submit-button")
    private WebElement btnSetBudget;
    
    public boolean isbtnSetBudgetVisible() {
    	log.info("btn Set Budget Status :" + btnSetBudget.isDisplayed());
    	return btnSetBudget.isDisplayed();
   }
    
    @FindBy(css = ".TextBoxNoClass")
    private WebElement txtBoxManualSet;
    
    public boolean istxtBoxManualSetVisible() {
    	log.info("btn Set Budget Status :" + txtBoxManualSet.isDisplayed());
    	return txtBoxManualSet.isDisplayed();
   }
    
    @FindBy(css = "input.toggle")
    private WebElement btnNotifyMe;
    
    public boolean isbtnNotifyMeVisible() {
    	log.info("btn Notify Me Status :" + btnNotifyMe.isDisplayed());
    	return btnNotifyMe.isDisplayed();
   }
    
    @FindBy(css = ".ajax__slider_h_handle")
    private WebElement btnSliderMonthlyBudget;
    
    public boolean isbtnSliderMonthlyBudgetVisible() {
    	log.info("btn Slider Monthly Budget Status :" + btnSliderMonthlyBudget.isDisplayed());
    	return btnSliderMonthlyBudget.isDisplayed();
   }
    
    @FindBy(css = ".compare_graph g.highcharts-series.highcharts-tracker > rect")
    private WebElement lblHistogramBar;
    
    public boolean islblHistogramBarVisible() {
    	log.info("lbl Histogram Bar Status :" + lblHistogramBar.isDisplayed());
    	return lblHistogramBar.isDisplayed();
   }
    
    @FindBy(css = ".compare_graph g.highcharts-axis-labels.highcharts-xaxis-labels > text")
    private WebElement lblMonthNamesBar;
    
    public boolean islblMonthNamesBarVisible() {
    	log.info("lbl Histogram Bar Status :" + lblMonthNamesBar.isDisplayed());
    	return lblMonthNamesBar.isDisplayed();
   }
    
    @FindBy(css = ".compare_graph g.highcharts-data-labels.highcharts-tracker > text")
    private WebElement lblHistogramBarValue;
    
    public boolean islblHistogramBarValueVisible() {
    	log.info("lbl Histogram Bar Value Status :" + lblHistogramBarValue.isDisplayed());
    	return lblHistogramBarValue.isDisplayed();
   }
    @FindBy(css = ".compare_graph g.highcharts-tooltip > text > tspan")
    private WebElement lblHistogramBarTooltipValue;
    
    public boolean islblHistogramBarTooltipValueVisible() {
    	log.info("lbl Histogram Bar Tool tip Value Status :" + lblHistogramBarTooltipValue.isDisplayed());
    	return lblHistogramBarTooltipValue.isDisplayed();
   }
    
//    @FindBy(css = ".GraphLegend_data_low[title=" My Usage"]")
//    private WebElement lblMyUsage;
//    
//    public boolean islblMyUsageVisible() {
//    	log.info("lbl My Usage Status :" + lblMyUsage.isDisplayed());
//    	return lblMyUsage.isDisplayed();
//   }
//    
//    @FindBy(css = ".GraphLegend_data_low[title=" Zip Average"]")
//    private WebElement lblZipAverage;
//    
//    public boolean islblZipAverageVisible() {
//    	log.info("lbl Zip Average Status :" + lblZipAverage.isDisplayed());
//    	return lblZipAverage.isDisplayed();
//   }
//    
//    @FindBy(css = ".GraphLegend_data_low[title=" My Budget"]")
//    private WebElement lblMyBudget;
//    
//    public boolean islblMyBudgetVisible() {
//    	log.info("lbl My Budget Status :" + lblMyBudget.isDisplayed());
//    	return lblMyBudget.isDisplayed();
//   }
//    
//    @FindBy(css = "div.top_conte_box_mob > div[globalize=" ML_Budget_My_Lbl_Budget_Vs_Usage_Comparison"]")
//    private WebElement lblHeaderBudgetVsUsageComparison;
//    
//    public boolean islblHeaderBudgetVsUsageComparisonVisible() {
//    	log.info("lbl Header Budget Vs Usage Comparison Status :" + lblHeaderBudgetVsUsageComparison.isDisplayed());
//    	return lblHeaderBudgetVsUsageComparison.isDisplayed();
//   }
    
    @FindBy(css = ".toast-message")
    private WebElement lblMsgOnHeader;
    
    public boolean islblMsgOnHeaderVisible() {
    	log.info("lbl Msg On Header Status :" + lblMsgOnHeader.isDisplayed());
    	return lblMsgOnHeader.isDisplayed();
   }
    
    @FindBy(css = "a.ui-slider-handle.ui-state-default.ui-corner-all")
    private WebElement btnMonthlyBudgetSlider;
    
    public boolean isbtnMonthlyBudgetSliderVisible() {
    	log.info("lbl Msg On Header Status :" + btnMonthlyBudgetSlider.isDisplayed());
    	return btnMonthlyBudgetSlider.isDisplayed();
   }
    
    @FindBy(css = ".lbl_txtbudget")
    private WebElement lblMonthlyBudget;
    
    public boolean islblMonthlyBudgetVisible() {
    	log.info("lbl Monthly Budget Status :" + lblMonthlyBudget.isDisplayed());
    	return lblMonthlyBudget.isDisplayed();
   }
    
    @FindBy(css = ".SliderMin")
    private WebElement slideMinValue;
    
    public boolean isslideMinValueVisible() {
    	log.info("lbl Monthly Budget Status :" + slideMinValue.isDisplayed());
    	return slideMinValue.isDisplayed();
   }
    
    @FindBy(css = "div#slider")
    private WebElement slider;
    
    public boolean issliderVisible() {
    	log.info("lbl slider Status :" + slider.isDisplayed());
    	return slider.isDisplayed();
   }
    
    
    @FindBy(css = "div.ui-slider-range-max")
    private WebElement sliderSetPercentageValue;
    
    public boolean issliderSetPercentageValueVisible() {
    	log.info("lbl slider Status :" + sliderSetPercentageValue.isDisplayed());
    	return sliderSetPercentageValue.isDisplayed();
   }
    
    
    @FindBy(xpath = "(//div[@class = 'lbl_txt_curren'])[1]")
    private WebElement lblManualSet;
    
    public boolean islblManualSetVisible() {
    	log.info("lbl slider Status :" + lblManualSet.isDisplayed());
    	return lblManualSet.isDisplayed();
   }
    
    @FindBy(xpath = "(//div[@class = 'lbl_txt_curren'])[1]/span")
    private WebElement lblcurrencySymboy;
    
    public boolean islblcurrencySymboyVisible() {
    	log.info("lbl currency Symboy Status :" + lblcurrencySymboy.isDisplayed());
    	return lblcurrencySymboy.isDisplayed();
   }
    
    @FindBy(css = "input#txtSetBudget")
    private WebElement inputSetBudget;
    
    public boolean isinputSetBudgetVisible() {
    	log.info("input Set Budget Status :" + inputSetBudget.isDisplayed());
    	return inputSetBudget.isDisplayed();
   }
    
    @FindBy(css = "[globalize ='ML_BudgetBill_Lbl_NotifyMe']")
    private WebElement lblNotifyMeIfIGoOverMyBudget;
    

    public boolean islblNotifyMeIfIGoOverMyBudgetVisible() {
    	log.info("lbl Notify Me If I Go Over My Budget Status :" + lblNotifyMeIfIGoOverMyBudget.isDisplayed());
    	return lblNotifyMeIfIGoOverMyBudget.isDisplayed();
   }
    
    @FindBy(css = "span#budgethelplink")
    private WebElement infoIconBudgetHelp;
    
    public boolean isinfoIconBudgetHelpVisible() {
    	log.info("info Icon Budget Help Status :" + infoIconBudgetHelp.isDisplayed());
    	return infoIconBudgetHelp.isDisplayed();
   }
    
    @FindBy(css = "input.toggle")
    private WebElement inputNotifyOnOffButton;
    
    public boolean isinputNotifyOnOffButtonVisible() {
    	log.info("input Notify On Off Button Status :" + inputNotifyOnOffButton.isDisplayed());
    	return inputNotifyOnOffButton.isDisplayed();
   }
    
    @FindBy(css = "input#btnSaveChanges")
    private WebElement inputSetBudgetButton;
    
    public boolean isinputSetBudgetButtonVisible() {
    	log.info("input Set Budget Button Status :" + inputSetBudgetButton.isDisplayed());
    	return inputSetBudgetButton.isDisplayed();
   }
    
    @FindBy(css = "[globalize='ML_Budget_My_Lbl_Budget_Vs_Usage_Comparison']")
    private WebElement lblBudgetVsUsageComparisonHeading;
    
    public boolean islblBudgetVsUsageComparisonHeadingVisible() {
    	log.info("lbl Budget Vs Usage Comparison Heading Status :" + lblBudgetVsUsageComparisonHeading.isDisplayed());
    	return lblBudgetVsUsageComparisonHeading.isDisplayed();
   }
    @FindBy(css = "text.highcharts-axis-title tspan")
    private WebElement lblGraphYAxisText;
    
    public boolean islblGraphYAxisTextVisible() {
    	log.info("lbl Graph Y Axis Text Heading Status :" + lblGraphYAxisText.isDisplayed());
    	return lblGraphYAxisText.isDisplayed();
   }
    
    @FindBy(css = ".highcharts-axis-labels.highcharts-yaxis-labels [text-anchor = 'end']")
    private WebElement lblGraphYAxisCurrencyIntervals;
    
    public boolean islblGraphYAxisCurrencyIntervalsVisible() {
    	log.info("lbl Graph Y Axis Currency Intervals Status :" + lblGraphYAxisCurrencyIntervals.isDisplayed());
    	return lblGraphYAxisCurrencyIntervals.isDisplayed();
   }
    
    
    @FindBy(css = ".highcharts-axis-labels.highcharts-xaxis-labels [text-anchor = 'end']")
    private WebElement lblGraphXAxisMonths;
    
    public boolean islblGraphXAxisMonthsVisible() {
    	log.info("lbl Graph Y Axis Currency Intervals Status :" + lblGraphXAxisMonths.isDisplayed());
    	return lblGraphXAxisMonths.isDisplayed();
   }
    
    
    @FindBy(css = ".highcharts-series.highcharts-series-0.highcharts-column-series.highcharts-color-0.highcharts-tracker rect")
    private WebElement myUsageGraphBar;
    
    public boolean ismyUsageGraphBarVisible() {
    	log.info("My Usage Graph Bar Status :" + myUsageGraphBar.isDisplayed());
    	return myUsageGraphBar.isDisplayed();
   }
    
    
    @FindBy(css = ".highcharts-series.highcharts-series-1.highcharts-column-series.highcharts-color-1.highcharts-tracker rect")
    private WebElement myBudgetGraphBar;
    
    public boolean ismyBudgetGraphBarVisible() {
    	log.info("My Budget Graph Bar Status :" + myBudgetGraphBar.isDisplayed());
    	return myBudgetGraphBar.isDisplayed();
   }
    
    @FindBy(css = ".highcharts-series.highcharts-series-2.highcharts-column-series.highcharts-color-2.highcharts-tracker rect")
    private WebElement zipGraphBar;
    
    public boolean isZipGraphBarVisible() {
    	log.info("Zip GraphBar Status :" + zipGraphBar.isDisplayed());
    	return zipGraphBar.isDisplayed();
   }
    
    @FindBy(css = "span.GraphLegend_low")
    private WebElement legendColorMyUsage;
    
    public boolean islegendColorMyUsageVisible() {
    	log.info("legend Color My Usage Status :" + legendColorMyUsage.isDisplayed());
    	return legendColorMyUsage.isDisplayed();
   }
    
    @FindBy(css = "[globalize = 'ML_Budget_My_Lbl_My_Usage']")
    private WebElement lblMyUsages;
    
    public boolean islblMyUsagesVisible() {
    	log.info("lbl My Usages Status :" + lblMyUsages.isDisplayed());
    	return lblMyUsages.isDisplayed();
   }
       
    @FindBy(css = "span.GraphLegend_Avg")
    private WebElement legendColorMyBudget;
    
    public boolean islegendColorMyBudgetVisible() {
    	log.info("legend Color My Budget Status :" + legendColorMyBudget.isDisplayed());
    	return legendColorMyBudget.isDisplayed();
   }
    
    @FindBy(css = "[globalize = 'ML_Budget_My_Lbl_My_Budget']")
    private WebElement lblmyBudget;
    
    public boolean islblmyBudgetVisible() {
    	log.info("lbl m yBudget  Status :" + lblmyBudget.isDisplayed());
    	return lblmyBudget.isDisplayed();
   }
    
    @FindBy(css = "span.GraphLegend_High")
    private WebElement legendColorMyZipAvg;
    
    public boolean islegendColorMyZipAvgVisible() {
    	log.info("legend Color My Zip Avg Status :" + legendColorMyZipAvg.isDisplayed());
    	return legendColorMyZipAvg.isDisplayed();
   }
    
    @FindBy(css = "[globalize = 'ML_Budget_My_Lbl_Zip_Avg']")
    private WebElement lblMyZipAvg;
    
    public boolean islblMyZipAvgVisible() {
    	log.info("lbl My Zip Avg Status :" + lblMyZipAvg.isDisplayed());
    	return lblMyZipAvg.isDisplayed();
   }
}



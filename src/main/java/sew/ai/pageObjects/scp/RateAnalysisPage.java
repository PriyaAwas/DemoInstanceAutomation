package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RateAnalysisPage extends HomePage {
    private static final Logger log = LogManager.getLogger(RateAnalysisPage.class);

    public RateAnalysisPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
	
    @FindBy(css = ".submit-button#btnShow")
    private WebElement btnCompare;
    
    public boolean isbtnCompareVisible() {
    	log.info("btn Compare Status :" + btnCompare.isDisplayed());
    	return btnCompare.isDisplayed();
   }
    
    
//    @FindBy(css = "input[type=" radio"]")
//    private WebElement rdoBtnPlanName;
//    
//    public boolean isrdoBtnPlanNameVisible() {
//    	log.info("rdo Btn Plan Name Status :" + rdoBtnPlanName.isDisplayed());
//    	return rdoBtnPlanName.isDisplayed();
//   }
        
    @FindBy(css = ".modal-dialog")
    private WebElement mdlDialogueRateComparision;
    
    public boolean ismdlDialogueRateComparisionVisible() {
    	log.info("mdl Dialogue Rate Comparision Status :" + mdlDialogueRateComparision.isDisplayed());
    	return mdlDialogueRateComparision.isDisplayed();
   }    
    
    @FindBy(css = "#divAddressPopup_ChangePass .modal-title")
    private WebElement lblModalTitleRateComparision;
    
    public boolean islblModalTitleRateComparisionVisible() {
    	log.info("lbl Modal Title Rate Comparision Status :" + lblModalTitleRateComparision.isDisplayed());
    	return lblModalTitleRateComparision.isDisplayed();
   }
    
    @FindBy(css = ".modal-header .mailingaddressclose")
    private WebElement btnCloseModalRateComparision;
    
    public boolean isbtnCloseModalRateComparisionVisible() {
    	log.info("btn Close Modal Rate Comparision Status :" + btnCloseModalRateComparision.isDisplayed());
    	return btnCloseModalRateComparision.isDisplayed();
   }
       
    @FindBy(css = ".cmpr_value_box .highcharts-series.highcharts-tracker > rect")
    private WebElement grphRateComparison;
    
    public boolean isgrphRateComparisonVisible() {
    	log.info("grph Rate Comparison Status :" + grphRateComparison.isDisplayed());
    	return grphRateComparison.isDisplayed();
   }
       
    @FindBy(css = ".highcharts-tooltip > path")
    private WebElement lblChartToolTipOnGraph;
       
    public boolean islblChartToolTipOnGraphVisible() {
    	log.info("lbl Chart Tool Tip On Graph Status :" + lblChartToolTipOnGraph.isDisplayed());
    	return lblChartToolTipOnGraph.isDisplayed();
   }
    
    @FindBy(css = "g.highcharts-series-0 tspan")
    private WebElement lblCurrentPlanOnGraph;
    
    public boolean islblCurrentPlanOnGraphVisible() {
    	log.info("lbl Current Plan On Graph Status :" + lblCurrentPlanOnGraph.isDisplayed());
    	return lblCurrentPlanOnGraph.isDisplayed();
   }
    
    
    @FindBy(css = "g.highcharts-series-1 tspan")
    private WebElement lblSelectedPlanOnGraph;
    
    public boolean islblSelectedPlanOnGraphVisible() {
    	log.info("lbl Selected Plan On Graph Status :" + lblSelectedPlanOnGraph.isDisplayed());
    	return lblSelectedPlanOnGraph.isDisplayed();
   }
    
    @FindBy(css = ".highcharts-axis-labels.highcharts-xaxis-labels > text")
    private WebElement lblMonthNamesOnGraph;
    
    public boolean islblMonthNamesOnGraphVisible() {
    	log.info("lbl Month Names On Graph Status :" + lblMonthNamesOnGraph.isDisplayed());
    	return lblMonthNamesOnGraph.isDisplayed();
   }
    
    
    @FindBy(css = ".highcharts-root .highcharts-axis.highcharts-yaxis text")
    private WebElement lblCostOfUnitsConsumedGraph;
    
    public boolean islblCostOfUnitsConsumedGraphVisible() {
    	log.info("lbl Cost Of Units Consumed Graph Status :" + lblCostOfUnitsConsumedGraph.isDisplayed());
    	return lblCostOfUnitsConsumedGraph.isDisplayed();
   }
    
    
    @FindBy(css = "div.toast-message")
    private WebElement lblToastMsg;
    
    public boolean islblToastMsgVisible() {
    	log.info("lbl Toast Msg Status :" + lblToastMsg.isDisplayed());
    	return lblToastMsg.isDisplayed();
   }
    
    
    @FindBy(css = "#ContentPlaceHolder1_ContentPlaceHolderBody_rowpowerplan div.selector-text")
    private WebElement lblPowerPlan;
    
    public boolean islblPowerPlanVisible() {
    	log.info("lbl Power Plan Status :" + lblPowerPlan.isDisplayed());
    	return lblPowerPlan.isDisplayed();
   }
       
    @FindBy(css = "#ContentPlaceHolder1_ContentPlaceHolderBody_rowwaterplan div.selector-text")
    private WebElement lblWaterPlan;
    
    public boolean islblWaterPlanVisible() {
    	log.info("lbl Water Plan Status :" + lblWaterPlan.isDisplayed());
    	return lblWaterPlan.isDisplayed();
   }
      
    @FindBy(css = "#ContentPlaceHolder1_ContentPlaceHolderBody_rowgasplan div.selector-text")
    private WebElement lblGasPlan;
    
    public boolean islblGasPlanVisible() {
    	log.info("lbl Gas Plan Status :" + lblGasPlan.isDisplayed());
    	return lblGasPlan.isDisplayed();
   }
    
    @FindBy(css = "#ContentPlaceHolder1_ContentPlaceHolderBody_rowevplan div.selector-text")
    private WebElement lblElectricVehiclePlanPlan;
    
    public boolean islblElectricVehiclePlanPlanVisible() {
    	log.info("lbl Electric Vehicle Plan Status :" + lblElectricVehiclePlanPlan.isDisplayed());
    	return lblElectricVehiclePlanPlan.isDisplayed();
   }
    
//    @FindBy(css = "div[id*= " nobilldetails"]")
//    private WebElement lblNoRatePlans;
//    
//    public boolean islblNoRatePlansVisible() {
//    	log.info("lbl No Rate Plans Status :" + lblNoRatePlans.isDisplayed());
//    	return lblNoRatePlans.isDisplayed();
//   }
	
}



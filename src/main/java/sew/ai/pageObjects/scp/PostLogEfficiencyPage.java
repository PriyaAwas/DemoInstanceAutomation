package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PostLogEfficiencyPage extends HomePage {
    private static final Logger log = LogManager.getLogger(PostLogEfficiencyPage.class);

    public PostLogEfficiencyPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    
    @FindBys(@FindBy(css = "#rebatearea li div.content_energy_area"))
    private List <WebElement> lstPostLoginEfficiencyMenu;
    public List<WebElement> getObjectlstPostLoginEfficiencyMenu()
    {
    	return lstPostLoginEfficiencyMenu;
    }
    @FindBys(@FindBy(css = ".efficiency_area li.eff_register"))
    private List<WebElement> lstPostLoginEfficiencyTipCount;
    
    public List<WebElement>  getObjectPostLoginEfficiencyTipCount()
    {
    	return lstPostLoginEfficiencyTipCount;
    }
  
    @FindBys(@FindBy(css = ".efficiency_area .textdesc button"))
    private List<WebElement> lstPostLoginEfficiencyReadMore;
    public List<WebElement> getObjectlstPostLoginEfficiencyReadMore()
    {
    	return lstPostLoginEfficiencyReadMore;
    }

    public void clickReadMoreForWaysToSaveName(String name){
        WebElement ele =getWebElement(By.xpath("(//label[@id='desctext'][text()='"+name+"']/following::button)[1]"));
        clickElementUsingJsExecutor(ele);
    }


   @FindBys(@FindBy(css = "#rebatearea li div.content_energy_area h1"))
    private List<WebElement> lstPostLoginEfficiencyTipHeading;
    public List<WebElement> getObjectlstPostLoginEfficiencyTipHeading()
    {
    	return lstPostLoginEfficiencyTipHeading;
    }

    public List<String> getLstPostLoginEfficiencyHeading() {
        List<String> label = getAllElementsTextInList(lstPostLoginEfficiencyTipHeading);
        log.info("Efficiency Heading List on Ways To Save is " + label);
        return label;
    }
    @FindBys(@FindBy(xpath = "//a[not(contains(@style, 'display: none'))][text()='Enroll']/../../..//h1"))
    private List<WebElement> lstPostLoginEfficiencyTipHeadingEnroll;
    
    public List<WebElement> getObjectlstPostLoginEfficiencyTipHeadingEnroll()
    {
    	return lstPostLoginEfficiencyTipHeadingEnroll;
    }
    @FindBys(@FindBy(css = ".bottom_efficiency [id^='vc']"))
    private List<WebElement> lstPostLoginEfficiencySavingTipsViewedCountPle;
    
    public List<WebElement> getObjectlstPostLoginEfficiencySavingTipsViewedCountPle()
    {
    	return lstPostLoginEfficiencySavingTipsViewedCountPle;
    }
    
    @FindBy(css = ".modal-header .close")
    private WebElement lstPostLoginEfficiencyTipClose;
   
    
    @FindBys(@FindBy(css = ".registered_box .showborder .popup"))
    private List<WebElement> lstPostLoginEfficiencyTipViewedCount;
    public List<WebElement> getObjectlstPostLoginEfficiencyTipViewedCount()
    {
    	return lstPostLoginEfficiencyTipViewedCount;
    }
    
    @FindBys(@FindBy(css = ".added_view_sec [style='display: block']"))
    private List<WebElement> lstPostLoginEfficiencyTipLikesCount;
    public List<WebElement> getObjectlstPostLoginEfficiencyTipLikesCount()
    {
    	return lstPostLoginEfficiencyTipLikesCount;
    }
    @FindBy(css = ".icon_rebates")
    private WebElement lnkPostLoginEfficiencyRebatesTabIcon;
    
    @FindBys(@FindBy(css = ".register a:not(.ng-hide):not(.btn-disable DisEnrolledbtn)"))
    private List<WebElement> lstlnkPostLoginEfficiencyEnroll;
    
    public List<WebElement> getObjectlstlnkPostLoginEfficiencyEnroll()
    {
    	return lstlnkPostLoginEfficiencyEnroll;
    }
    @FindBy(xpath = "//*[@class='register']//a[not(contains(text(),'Enrolled'))][not(contains(text(),'Unenroll'))][not(contains(@class,'ng-hide'))]")
    private WebElement lstlnkPostLoginEfficiencyEnrollProgram;
    @FindBy(css = ".btn-disable.DisEnrolledbtn")
    private WebElement lstlnkPostLoginEfficiencyEnrolled;
    
    public boolean islstlnkPostLoginEfficiencyEnrolledVisible() {
        log.info("Post Login Efficiency Enrolled Visible :" + lstlnkPostLoginEfficiencyEnrolled.isDisplayed());
        return lstlnkPostLoginEfficiencyEnrolled.isDisplayed();
    }
    public String getlnkPostLoginEfficiencyEnrolled(String value) {
		String label = getAttribute(lstlnkPostLoginEfficiencyEnrolled, value);
		log.info("Post Login Efficiency Enrolled Label {}: " + label);
		return label;
	}
    
    
    @FindBy(css = ".TableCellHeaderSearch input")
    private WebElement txtSearchBox;
    
    public boolean istxtSearchBoxVisible() {
        log.info("txt Search Box Visible :" + txtSearchBox.isDisplayed());
        return txtSearchBox.isDisplayed();
    }
    
    @FindBy(css = ".printbtn")
    private WebElement btnPostLoginEfficiencyPrint;
   
    public void clickbtnPostLoginEfficiencyPrint() {
        click(btnPostLoginEfficiencyPrint);
        log.info("btn Post Login Efficiency Print clicked {}.");
    }
   
    @FindBy(css = ".nav_left .icon_dr_programes a[globalize='ML_Programs_Navigation_Programs']")
    private WebElement lnkPostLoginEfficiencyProgramsTab;

    public void clicklnkPostLoginEfficiencyProgramsTab() {
        click(lnkPostLoginEfficiencyProgramsTab);
        log.info("lnk Post Login Efficiency Programs Tab clicked {}.");
    }
    public boolean islnkPostLoginEfficiencyProgramsVisible() {
        log.info("lnk Post Login Efficiency Programs Tab Status :" + lnkPostLoginEfficiencyProgramsTab.isDisplayed());
        return lnkPostLoginEfficiencyProgramsTab.isDisplayed();
    }
    
    @FindBy(css = ".nav_left .icon_dr_programes")
    private WebElement lnkPostLoginEfficiencyProgramsTabIcon;

    public boolean islnkPostLoginEfficiencyProgramsTabIconVisible() {
        log.info("lnk Post Login Efficiency Programs Tab Visible :" + lnkPostLoginEfficiencyProgramsTabIcon.isDisplayed());
        return lnkPostLoginEfficiencyProgramsTabIcon.isDisplayed();
    }
    
    @FindBy(css = ".nav_left .icon_saving_tips a")
    private WebElement lnkPostLoginEfficiencySavingTipsTab;

    public boolean islnkPostLoginEfficiencySavingTipsTabVisible() {
        log.info("lnk Post Login Efficiency Saving Tips Tab Visible :" + lnkPostLoginEfficiencySavingTipsTab.isDisplayed());
        return lnkPostLoginEfficiencySavingTipsTab.isDisplayed();
    }

    public void clicklnkPostLoginEfficiencySavingTipsTab() {
        click(lnkPostLoginEfficiencySavingTipsTab);
        log.info("lnk Post Login Efficiency Saving Tips Tab clicked {}.");
    }
    public void clickLnkPostLoginEfficiencySavingTipsTabIcon() {
        clickElementUsingJsExecutor(lnkPostLoginEfficiencySavingTipsTab);
        log.info("Saving Tips Tab Link clicked successfully.");
    }
    
    @FindBy(css = ".list-group .icon_annual_goal.list-group-item a")
    private WebElement lnkPostLoginEfficiencyAnnualGoalTab;
    
    public void clicklnkPostLoginEfficiencyAnnualGoalTab() {
        click(lnkPostLoginEfficiencyAnnualGoalTab);
        log.info("lnk Post Login Efficiency Annual Goal Tab clicked {}.");
    }

    public boolean islnkPostLoginEfficiencyAnnualGoalTabVisible() {
        log.info("lnk Post Login Efficiency Saving Tips Tab Visible :" + lnkPostLoginEfficiencyAnnualGoalTab.isDisplayed());
        return lnkPostLoginEfficiencyAnnualGoalTab.isDisplayed();
    }
    
    @FindBy(css = ".current_area .average_usage_header [id*='lblachivedpercent']")
    private WebElement lblReachedGoalVal;
   
   

    public boolean islblReachedGoalValVisible() {
        log.info("lbl Reached Goal Val Visible :" + lblReachedGoalVal.isDisplayed());
        return lblReachedGoalVal.isDisplayed();
    }
    
    @FindBy(css = ".current_area .average_usage_header [id*='lblCurrentMonthlySaving']")
    private WebElement lblMonthlySavingVal;

    public boolean islblMonthlySavingValVisible() {
        log.info("lbl Monthly Saving Val Visible :" + lblMonthlySavingVal.isDisplayed());
        return lblMonthlySavingVal.isDisplayed();
    }
    @FindBy(css = ".current_area .average_usage_header [id*='lblTotalSaving']")
    private WebElement lblAnnualSavingsVal;
    
    public boolean isAnnualSavingsValVisible() {
        log.info("lnk Post Login Efficiency Saving Tips Tab Visible :" + lblAnnualSavingsVal.isDisplayed());
        return lblAnnualSavingsVal.isDisplayed();
    }

    @FindBy(css = ".current_area i[globalize='ML_BudgetBill_Lbl_Goal']")
    private WebElement lblTxtReachedGoal;

    public boolean islblTxtReachedGoalVisible() {
        log.info("lbl Txt Reached Goal Visible :" + lblTxtReachedGoal.isDisplayed());
        return lblTxtReachedGoal.isDisplayed();
    }
    @FindBy(css = ".current_area i[globalize='ML_BudgetBill_Lbl_MonthlySavings']")
    private WebElement lblTxtMonthlySavingsToDate;

   public boolean islblTxtMonthlySavingsToDateVisible() {
       log.info("lbl Txt Monthly Savings To Date Visible :" + lblTxtMonthlySavingsToDate.isDisplayed());
       return lblTxtMonthlySavingsToDate.isDisplayed();
    }
//    
   @FindBy(css = ".current_area i[globalize='ML_Budget_My_Lbl_Total_Annual_Saving_Goal']")
    private WebElement lblTxtAnnualSavings;
   
   

    public boolean islblTxtAnnualSavingsVisible() {
        log.info("lbl Txt Annual Savings Visible :" + lblTxtAnnualSavings.isDisplayed());
        return lblTxtAnnualSavings.isDisplayed();
    }
//    @FindBy(css = ".right_Bill_area span[title=" Annual Goal vs Actual Savings"]")
//    private WebElement lblAnnualGoalVsActualSavings;
//
//    public boolean islblAnnualGoalVsActualSavingsVisible() {
//        log.info("lbl Annual Goal Vs Actual Savings Visible :" + lblAnnualGoalVsActualSavings.isDisplayed());
//        return lblAnnualGoalVsActualSavings.isDisplayed();
//    }
//    
    @FindBy(css = "[id*='lblAnnualPd']")
    private WebElement lblPeriod;

    public boolean islblPeriodVisible() {
        log.info("lbl Period Visible :" + lblPeriod.isDisplayed());
        return lblPeriod.isDisplayed();
    }
    



@FindBy(css = ".EfficiencyGraphLabel[globalize='ML_Budget_My_Lbl_Less_Efficient']")
    private WebElement lblLessEfficient;

    public boolean islblLessEfficientVisible() {
        log.info("lbl Less Efficient Visible :" + lblLessEfficient.isDisplayed());
        return lblLessEfficient.isDisplayed();
   }
    
   @FindBy(css = ".EfficiencyGraphLabel[globalize='ML_Budget_My_Lbl_More_Efficient']")
    private WebElement lblMoreEfficient;

   public boolean isMoreEfficientVisible() {
       log.info("lbl More Efficient Visible :" + lblMoreEfficient.isDisplayed());
       return lblMoreEfficient.isDisplayed();
   }
    
   @FindBy(css = ".list-group .icon_efficiency_rank.list-group-item a")
   private WebElement lnkPostLoginEfficiencyRankTab;

    public boolean islblMoreEfficientVisible() {
        log.info("lnk Post Login Efficiency Rank Tab Visible :" + lnkPostLoginEfficiencyRankTab.isDisplayed());
        return lnkPostLoginEfficiencyRankTab.isDisplayed();
    }
    
    public void clickPostLoginEfficiencyRankTab() {
        click(lnkPostLoginEfficiencyRankTab);
        log.info("link Post Login Efficiency Rank Tab clicked {}.");
    }
    
    @FindBy(css = "i[globalize='ML_Budget_My_Lbl_Rank_Last_month']")
    private WebElement lnkBudgetMyLabelRankLastmonth;
    
    public boolean islinkBudgetMyLabelRankLastmonthVisible() {
        log.info("lnk Post Login link Budget My Label Rank Last month Visible :" + lnkBudgetMyLabelRankLastmonth.isDisplayed());
        return lnkBudgetMyLabelRankLastmonth.isDisplayed();
    }
    

   
    @FindBy(css = "[id*= 'lblCurrentMonthRank']")
    private WebElement lblValueRank;

    public boolean islblValueRankVisible() {
        log.info("lbl Value Rank Visible :" + lblValueRank.isDisplayed());
        return lblValueRank.isDisplayed();
    }
    
    public String getLabelValueRank() {
		log.info("Checking the Value Rank on the page.");
		String option = getText(lblValueRank);
		log.info("Value rank {}: " + option);
		return option;
	}
//    
//    @FindBy(css = "i[globalize=" ML_Budget_My_Lbl_Rank_Last_month"]")
//    private WebElement lblTxtYourRankLastMonth;
//
//    public boolean islblTxtYourRankLastMonthVisible() {
//        log.info("lbl Txt Your Rank Last Month Visible :" + lblTxtYourRankLastMonth.isDisplayed());
//        return lblTxtYourRankLastMonth.isDisplayed();
//    }
//    
    @FindBy(css = "[id*='lblTotalNeighbours']")
    private WebElement lblValueTotalNeighbours;

    public boolean islblValueTotalNeighboursVisible() {
       log.info("lbl Value Total Neighbours Visible :" + lblValueTotalNeighbours.isDisplayed());
        return lblValueTotalNeighbours.isDisplayed();
    }
    
    public String getLabelValueTotalNeighbours() {
		log.info("Checking the label Value Total Neighbours on the page.");
		String option = getText(lblValueTotalNeighbours);
		log.info("label Value Total Neighbours {}: " + option);
		return option;
	}
    
    @FindBy(css = "i#preText")
    private WebElement lblTxtTotal;

    public boolean islblTxtTotalVisible() {
        log.info("lbl Txt Total Visible :" + lblTxtTotal.isDisplayed());
        return lblTxtTotal.isDisplayed();
    }

 @FindBy(css = ".BudgetSet [globalize='ML_ENERGY_EFFICIENCY_Lbl_SavingsComparision']")
    private WebElement lblMonthlySavingDollarComparision;
    
   public boolean islblMonthlySavingDollarComparisionVisible() {
        log.info("lbl Monthly Saving Dollar Comparision Visible :" + lblTxtRankImprovement.isDisplayed());
        return lblTxtRankImprovement.isDisplayed();
    }
   
    @FindBy(css = ".home_zip_txt [id*='lblSquarefeet']")
   private WebElement lblSquareFootHome;
    
    public boolean islblSquareFootHomeVisible() {
        log.info("lbl Square Foot Home Visible :" + lblSquareFootHome.isDisplayed());
       return lblSquareFootHome.isDisplayed();
    }
    public String getLabelSquareFootHome() {
		log.info("Checking the label Square Foot Home on the page.");
		String option = getText(lblSquareFootHome);
		log.info("label Square Foot Home {}: " + option);
		return option;
	}
    
   @FindBy(css = ".home_zip_txt [id*='lblSimilarHomes']")
    private WebElement lblSimillarHome;
   
   public boolean islblSimillarHomeVisible() {
       log.info("lbl Simillar Home Visible :" + lblSimillarHome.isDisplayed());
       return lblSimillarHome.isDisplayed();
   }
   public String getLabelSimillarHome() {
		log.info("Checking the label Square Foot Home on the page.");
		String option = getText(lblSimillarHome);
		log.info("label Simillar Home {}: " + option);
		return option;
	}

  @FindBys(@FindBy(css = " .registered_box .eff_register .popup"))
    private List <WebElement> lstPostLoginEfficiencyTipEnrolledCount;
   
   public List<WebElement> getObjectslstPostLoginEfficiencyTipEnrolledCount()
   {
	   return lstPostLoginEfficiencyTipEnrolledCount;
   }



 public boolean islstEfficiencyTipEnrolledCountVisible() {
        log.info("lst Post Login Efficiency Tip Enrolled Count Visible :" + lstPostLoginEfficiencyTipEnrolledCount.get(0).isDisplayed());
        return lstPostLoginEfficiencyTipEnrolledCount.get(0).isDisplayed();
    }
    


  public void clickEfficiencyTipLikeUnlikeButtonCountLnk() {
		click(lstPostLoginEfficiencyTipLikeUnlikeButtonCount);
		log.info("Post Login Efficiency Tip Like Unlike Button Count clicked {}.");
	}



@FindBys(@FindBy(css = ".like_lnk_ro span"))
    private List<WebElement> lstPostLoginEfficiencySavingTipsLikeCount;
   
   public List<WebElement> getObjectslstPostLoginEfficiencySavingTipsLikeCount()
   {
	   return lstPostLoginEfficiencySavingTipsLikeCount;
   }
    
    
    
 



 @FindBy(xpath = "//a[@globalize='ML_DASHBOARD_Lbl_EnergyEfficiency']")
    private WebElement lnkEfficiency;
    
    public void clickLinkEfficiency() {
		log.info("Clicking the efficiency button.");
		click(lnkEfficiency);
		log.info("efficiency link clicked successfully.");
	}

    @FindBys(@FindBy(xpath = " //div[@class='add_tip_bottom'][not(contains(@style,'display: none'))]//input[@type='checkbox']/../../../..//h1"))
    private  List<WebElement> lstSavingTipsCheckBoxHeader1;
    
    public List<WebElement> getObjectlstSavingTipsCheckBoxHeader()
    {
    	return lstSavingTipsCheckBoxHeader1;
    }
   
    
   @FindBys(@FindBy(xpath = " //div[@class='add_tip_bottom'][not(contains(@style,'display: none'))]//input[@type='checkbox']"))
    private List <WebElement> lstSavingTipsCheckBox1;
   
   public List<WebElement> getObjectslstSavingTipsCheckBox()
   {
	   return lstSavingTipsCheckBox1;
   }





@FindBys(@FindBy(xpath = " //*[@class='delete_goal']"))
    private List<WebElement> lstDeleteSavingTips;
   
    public List<WebElement> getObjectslstDeleteSavingTips()
    {
    	return lstDeleteSavingTips;
    }
//    public boolean isllblAnnualSavingsValVisible() {
//        log.info("lbl Annual Savings Val Visible :" + lblAnnualSavingsVal.isDisplayed());
//        return lblAnnualSavingsVal.isDisplayed();
//    }
//    
//    @FindBy(css = ".current_area i[globalize="ML_BILLING_Label_You_have_reached"]")
//    private WebElement lblTxtReached;
//
//    public boolean islblTxtReachedVisible() {
//        log.info("is lbl Txt Reached Visible :" + lblTxtReached.isDisplayed());
//        return lblTxtReached.isDisplayed();
//    }
//    @FindBy(css = ".current_area i[globalize="ML_BudgetBill_Lbl_Goal"]")
//    private WebElement lblTxtReachedGoal;
//
//    public boolean islblTxtReachedGoalVisible() {
//        log.info("lbl Txt Reached Goal Visible :" + lblTxtReachedGoal.isDisplayed());
//        return lblTxtReachedGoal.isDisplayed();
//    }
//    @FindBy(css = ".current_area i[globalize="ML_BudgetBill_Lbl_MonthlySavings"]")
//    private WebElement lblTxtMonthlySavingsToDate;
//
//    public boolean islblTxtMonthlySavingsToDateVisible() {
//        log.info("lbl Txt Monthly Savings To Date Visible :" + lblTxtMonthlySavingsToDate.isDisplayed());
//        return lblTxtMonthlySavingsToDate.isDisplayed();
//    }
//    
//    @FindBy(css = ".current_area i[globalize="ML_Budget_My_Lbl_Total_Annual_Saving_Goal"]")
//    private WebElement lblTxtAnnualSavings;
//
//    public boolean islblTxtAnnualSavingsVisible() {
//        log.info("lbl Txt Annual Savings Visible :" + lblTxtAnnualSavings.isDisplayed());
//        return lblTxtAnnualSavings.isDisplayed();
//    }
//    @FindBy(css = ".right_Bill_area span[title=" Annual Goal vs Actual Savings"]")
//    private WebElement lblAnnualGoalVsActualSavings;
//
//    public boolean islblAnnualGoalVsActualSavingsVisible() {
//        log.info("lbl Annual Goal Vs Actual Savings Visible :" + lblAnnualGoalVsActualSavings.isDisplayed());
//        return lblAnnualGoalVsActualSavings.isDisplayed();
//    }
//    
//    @FindBy(css = "[id*="lblAnnualPd"]")
//    private WebElement lblPeriod;
//
//    public boolean islblPeriodVisible() {
//        log.info("lbl Period Visible :" + lblPeriod.isDisplayed());
//        return lblPeriod.isDisplayed();
//    }
    
    @FindBy(css = "span.GraphLegend_data_low")
    private WebElement lblSavingXaxis;

    public boolean islblSavingXaxisVisible() {
        log.info("lbl Saving Xaxis Visible :" + lblSavingXaxis.isDisplayed());
        return lblSavingXaxis.isDisplayed();
    }
    
    @FindBy(css = "span.GraphLegend_data_Avg")
    private WebElement lblTargetXaxis;

    public boolean islblTargetXaxisVisible() {
        log.info("lbl Target Xaxis Visible :" + lblTargetXaxis.isDisplayed());
        return lblTargetXaxis.isDisplayed();
    }
    
    @FindBy(css = "#chtsaving .highcharts-axis-title tspan")
    private WebElement lblAmountInDollar;

    public boolean islblAmountInDollarVisible() {
        log.info("lbl Amount In Dollar Visible :" + lblAmountInDollar.isDisplayed());
        return lblAmountInDollar.isDisplayed();
    }
    
    @FindBy(css = ".highcharts-axis-title tspan")
    private WebElement lblAmountInDollarRank;

    public boolean islblAmountInDollarRankVisible() {
        log.info("lbl Amount In Dollar Rank Visible :" + lblAmountInDollarRank.isDisplayed());
        return lblAmountInDollarRank.isDisplayed();
    }
    
    @FindBy(css = ".BudgetTitle")
    private WebElement lblNeighbourEfficiencyRank;

    public boolean islblNeighbourEfficiencyRankVisible() {
        log.info("lbl Neighbour Efficiency Rank Visible :" + lblNeighbourEfficiencyRank.isDisplayed());
        return lblNeighbourEfficiencyRank.isDisplayed();
    }
    
//    @FindBy(css = ".EfficiencyGraphLabel[globalize=" ML_Budget_My_Lbl_Less_Efficient"]")
//    private WebElement lblLessEfficient;
//
//    public boolean islblLessEfficientVisible() {
//        log.info("lbl Less Efficient Visible :" + lblLessEfficient.isDisplayed());
//        return lblLessEfficient.isDisplayed();
//    }
//    
//    @FindBy(css = ".EfficiencyGraphLabel[globalize=" ML_Budget_My_Lbl_More_Efficient"]")
//    private WebElement lblMoreEfficient;
//
//    public boolean islblMoreEfficientVisible() {
//        log.info("lbl More Efficient Visible :" + lblMoreEfficient.isDisplayed());
//        return lblMoreEfficient.isDisplayed();
//    }
//    
//    @FindBy(css = ".list-group .icon_efficiency_rank.list-group-item a")
//    private WebElement lnkPostLoginEfficiencyRankTab;
//
//    public boolean islblMoreEfficientVisible() {
//        log.info("lnk Post Login Efficiency Rank Tab Visible :" + lnkPostLoginEfficiencyRankTab.isDisplayed());
//        return lnkPostLoginEfficiencyRankTab.isDisplayed();
//    }
//    
//    @FindBy(css = "[id*=" lblCurrentMonthRank"]")
//    private WebElement lblValueRank;
//
//    public boolean islblValueRankVisible() {
//        log.info("lbl Value Rank Visible :" + lblValueRank.isDisplayed());
//        return lblValueRank.isDisplayed();
//    }
//    
//    @FindBy(css = "i[globalize=" ML_Budget_My_Lbl_Rank_Last_month"]")
//    private WebElement lblTxtYourRankLastMonth;
//
//    public boolean islblTxtYourRankLastMonthVisible() {
//        log.info("lbl Txt Your Rank Last Month Visible :" + lblTxtYourRankLastMonth.isDisplayed());
//        return lblTxtYourRankLastMonth.isDisplayed();
//    }
//    
//    @FindBy(css = "[id*=" lblTotalNeighbours"]")
//    private WebElement lblValueTotalNeighbours;
//
//    public boolean islblValueTotalNeighboursVisible() {
//        log.info("lbl Value Total Neighbours Visible :" + lblValueTotalNeighbours.isDisplayed());
//        return lblValueTotalNeighbours.isDisplayed();
//    }
    
    
    
    @FindBy(css = "i#spnNeigh")
    private WebElement lblTxtNeighbours;
    
    public boolean islblTxtNeighboursVisible() {
        log.info("lbl Txt Neighbours Visible :" + lblTxtNeighbours.isDisplayed());
        return lblTxtNeighbours.isDisplayed();
    }
    
    
    @FindBy(css = "span#lblimprovement")
    private WebElement lblTxtRankImprovement;
    
    public boolean islblTxtRankImprovementVisible() {
        log.info("lbl Txt Rank Improvement Visible :" + lblTxtRankImprovement.isDisplayed());
        return lblTxtRankImprovement.isDisplayed();
    }
    
//    @FindBy(css = ".BudgetSet [globalize=" ML_ENERGY_EFFICIENCY_Lbl_SavingsComparision"]")
//    private WebElement lblMonthlySavingDollarComparision;
//    
//    public boolean islblMonthlySavingDollarComparisionVisible() {
//        log.info("lbl Monthly Saving Dollar Comparision Visible :" + lblTxtRankImprovement.isDisplayed());
//        return lblTxtRankImprovement.isDisplayed();
//    }
//    
//    @FindBy(css = ".home_zip_txt [id*=" lblSquarefeet"]")
//    private WebElement lblSquareFootHome;
//    
//    public boolean islblSquareFootHomeVisible() {
//        log.info("lbl Square Foot Home Visible :" + lblSquareFootHome.isDisplayed());
//        return lblSquareFootHome.isDisplayed();
//    }
//    
//    @FindBy(css = ".home_zip_txt [id*=" lblSimilarHomes"]")
//    private WebElement lblSimillarHome;
//    
//    public boolean islblSimillarHomeVisible() {
//        log.info("lbl Simillar Home Visible :" + lblSimillarHome.isDisplayed());
//        return lblSimillarHome.isDisplayed();
//    }

    @FindBy(css = " a.cancel-button.backtobutton.hide_second_step2")
    private WebElement btnPostLoginBackToEfficiency;
    
    public boolean isbtnPostLoginBackToEfficiencyVisible() {
        log.info("btn Post Login Back To Efficiency Visible :" + btnPostLoginBackToEfficiency.isDisplayed());
        return btnPostLoginBackToEfficiency.isDisplayed();
    }
    public void clickPostLoginBackToEfficiencyBtn() throws InterruptedException {
    	scrollPageToElement(btnPostLoginBackToEfficiency);
		log.info("Clicking the Post Login Back To Efficiency button.");
		clickWithJSExecutor(btnPostLoginBackToEfficiency);
		log.info("Post Login Back To Efficiency button clicked successfully.");
	}

    @FindBy(css = " .right_content_box .savhide")
    private WebElement iconDollarRebateSign;

    public boolean isiconDollarRebateSignVisible() {
        log.info("icon Dollar Rebate Sign Visible :" + iconDollarRebateSign.isDisplayed());
        return iconDollarRebateSign.isDisplayed();
    }
    
    @FindBy(css = " .right_content_box #lbl_saveupto")
    private WebElement iconDollarRebateValue;
    
    public boolean isIconDollarRebateValueVisible() {
        log.info("Icon Dollar Rebate Value Visible :" + iconDollarRebateValue.isDisplayed());
        return iconDollarRebateValue.isDisplayed();
    }

    public String getIconDollarRebateValue() {
		log.info("Fetching the icon Dollar RebateValue.");
		String label = getText(iconDollarRebateValue);
		log.info("icon Dollar RebateValue {}: " + label);
		return label;
	}

    @FindBy(css = " input#addTipsReadMore.submit-button")
    private WebElement btnAddTipSavingTips;
    
    public boolean isbtnAddTipSavingTipsVisible() {
        log.info("btn Add Tip Saving Tips Visible :" + btnAddTipSavingTips.isDisplayed());
        return btnAddTipSavingTips.isDisplayed();
    }
    public void clickbtnAddTipSavingTips() {
		click(btnAddTipSavingTips);
		log.info("button Add Tip Saving Tips Button Count clicked {}.");
	}

       
    @FindBy(css = " .toast.toast-warning")
    private WebElement lblMsgWarning;
    
    public boolean islblMsgWarningVisible() {
        log.info("lbl Msg Warning Visible :" + lblMsgWarning.isDisplayed());
        return lblMsgWarning.isDisplayed();
    }
    
//    @FindBy(css = ".add_tip_bottom[style="float:right;display:block;margin-right:-10px;margin-top:5px;"] input[type=" checkbox"]")
//    private WebElement chkBoxAddTip;
//    
//    public boolean ischkBoxAddTipVisible() {
//        log.info("chk Box Add Tip Visible :" + chkBoxAddTip.isDisplayed());
//        return chkBoxAddTip.isDisplayed();
//    }
//    
//    @FindBy(css = " .add_tip_bottom[style="float:right;display:none;margin-right:-10px;margin-top:5px;"] input[type=" checkbox"]")
//    private WebElement chkBoxAlreadyAddedTip;
//    
//    public boolean ischkBoxAlreadyAddedTipVisible() {
//        log.info("chk Box Already Added Tip Visible :" + chkBoxAlreadyAddedTip.isDisplayed());
//        return chkBoxAlreadyAddedTip.isDisplayed();
//    }
      
    @FindBy(css = " .toast-message")
    private WebElement lblMsgSuccess;
    
    public boolean islblMsgSuccessVisible() {
        log.info("lbl Msg Success Visible :" + lblMsgSuccess.isDisplayed());
        return lblMsgSuccess.isDisplayed();
    }
    
    @FindBy(css = " toast-close-button")
    private WebElement btnCloseMessage;
    
    public boolean isbtnCloseMessageVisible() {
        log.info("btn Close Message Visible :" + btnCloseMessage.isDisplayed());
        return btnCloseMessage.isDisplayed();
    }
    
    @FindBy(css = " .added_view_sec li a")
    private WebElement lstPostLoginEfficiencyTipLikeUnlikeButtonCount;
    
    public boolean islstEfficiencyTipLikeUnlikeButtonCountVisible() {
        log.info("lst Post Login Efficiency Tip Like Unlike Button Count Visible :" + lstPostLoginEfficiencyTipLikeUnlikeButtonCount.isDisplayed());
        return lstPostLoginEfficiencyTipLikeUnlikeButtonCount.isDisplayed();
    }
    
    @FindBy(css = " .added_view_sec .like_lnk_ro")
    private WebElement lstbtnPostLoginEfficiencyTipUnLikeButton;
    
    public boolean islstbtnPostLoginEfficiencyTipUnLikeButtonVisible() {
        log.info("lst btn Post Login Efficiency Tip UnLike Button Visible :" + lstbtnPostLoginEfficiencyTipUnLikeButton.isDisplayed());
        return lstbtnPostLoginEfficiencyTipUnLikeButton.isDisplayed();
    }
    
     @FindBy(css = " span[class='.cntviews .popup ng-binding']")
    private WebElement lstEfficiencyEducationalTipsViewedCount;
    
    public boolean islstEfficiencyEducationalTipsViewedCountVisible() {
        log.info("lst Post Login Efficiency Saving Tips Like Count Visible :" + lstEfficiencyEducationalTipsViewedCount.isDisplayed());
        return lstEfficiencyEducationalTipsViewedCount.isDisplayed();
    }
    
    @FindBy(css = " .added_view_sec [style='display: block']:not(.eff_register) .ng-binding")
    private WebElement lstEducationalTipsLikeCount;
    
    public boolean islstEducationalTipsLikeCountVisible() {
        log.info("lst Educational Tips Like Count Visible :" + lstEducationalTipsLikeCount.isDisplayed());
        return lstEducationalTipsLikeCount.isDisplayed();
    }
    
    @FindBy(css = " .nav_left .icon_saving_tips")
    private WebElement lnkSavingTipsTabIcon;
    
    public boolean islnkSavingTipsTabIconVisible() {
        log.info("lnk Saving Tips Tab Icon Visible :" + lnkSavingTipsTabIcon.isDisplayed());
        return lnkSavingTipsTabIcon.isDisplayed();
    }
    
    @FindBy(css = ".nav_left .educational_tips a")
    private WebElement lnkEducationalTipsTab;
    
    public boolean islnkEducationalTipsTabVisible() {
        log.info("lnk Educational Tips Tab Visible :" + lnkEducationalTipsTab.isDisplayed());
        return lnkEducationalTipsTab.isDisplayed();
    }

    public void clickLnkPostLoginEfficiencyEducationalTipsTab() {
        clickElementUsingJsExecutor(lnkEducationalTipsTab);
        log.info("Educational Tips Tab Link clicked successfully.");
    }
    
    @FindBy(css = " .nav_left .educational_tips")
    private WebElement lnkTipsTabIcon;
    
    public boolean islnkTipsTabIconVisible() {
        log.info("lnk Tips Tab Icon Visible :" + lnkTipsTabIcon.isDisplayed());
        return lnkTipsTabIcon.isDisplayed();
    }
    
    @FindBy(xpath = " //div[@class='toast-message']")
    private WebElement toastMessage;
    
    public Boolean istoastMessageVisible() {
        log.info("toast Message Visible :" + toastMessage.isDisplayed());
        return toastMessage.isDisplayed();
    }
    
    @FindBy(css = " .headEfficiency")
    private WebElement lblHeader;
    
    public boolean islblHeaderVisible() {
        log.info("Efficiency lbl Header Visible :" + lblHeader.isDisplayed());
        return lblHeader.isDisplayed();
    }
    
    @FindBy(css = " .list-group .my_applications.list-group-item a")
    private WebElement lnkMyApplicationTab;
    
    public boolean islnkMyApplicationTabVisible() {
        log.info("lnk My Application Tab Visible :" + lnkMyApplicationTab.isDisplayed());
        return lnkMyApplicationTab.isDisplayed();
    }
    
    @FindBy(css = " #collapseEfficiency > div > ul > li.icon_aboutmyhome.list-group-item > a")
    private WebElement lnkAboutMyHomeTab;
    
    public boolean islnkAboutMyHomeTabVisible() {
        log.info("lnk About My Home Tab Visible :" + lnkAboutMyHomeTab.isDisplayed());
        return lnkAboutMyHomeTab.isDisplayed();
    }
    
    @FindBy(css = " #collapseEfficiency > div > ul > li.icon_energy_report.list-group-item > a")
    private WebElement lnkMyHomeReport;
    
    public boolean islnkMyHomeReportVisible() {
        log.info("lnk My Home Report Visible :" + lnkMyHomeReport.isDisplayed());
        return lnkMyHomeReport.isDisplayed();
    }
    
    @FindBy(css = " .icon_dr_effDemandResponse.list-group-item")
    private WebElement lnkDemandResponse;
    
    public boolean islnkDemandResponseVisible() {
        log.info("lnk Demand Response Visible :" + lnkDemandResponse.isDisplayed());
        return lnkDemandResponse.isDisplayed();
    }
    
    public void clicklnkDemandResponse() {
        click(lnkDemandResponse);
        log.info("lnk Demand Response clicked {}.");
    }
    
    @FindBy(xpath = " //a[@globalize='ML_Nav_EnergyAssistance']")
    private WebElement lnkEnergyAssistanceTab;
    
    public boolean islnkEnergyAssistanceTabVisible() {
        log.info("lnk Energy Assistance Tab Visible :" + lnkEnergyAssistanceTab.isDisplayed());
        return lnkEnergyAssistanceTab.isDisplayed();
    }
    
    public void clicklnkEnergyAssistanceTab() {
        click(lnkEnergyAssistanceTab);
        log.info("lnk Energy Assistance Tab clicked {}.");
    }
    
    @FindBy(css = " .nav_left .Marketplace_icon a")
    private WebElement lnkMarketPlaceTab;
    
    public boolean islnkMarketPlaceTabVisible() {
        log.info("lnk Market Place Tab Visible :" + lnkMarketPlaceTab.isDisplayed());
        return lnkMarketPlaceTab.isDisplayed();
    }
    
    public void clicklnkMarketPlaceTab() {
        click(lnkMarketPlaceTab);
        log.info("lnk Market Place Tab clicked {}.");
    }
    
    @FindBy(css = ".nav_left .icon_rebates a")
    private WebElement lnkRebatesTab;
    
    public boolean islnkRebatesTabVisible() {
        log.info("lnk Rebates Tab Visible :" + lnkRebatesTab.isDisplayed());
        return lnkRebatesTab.isDisplayed();
    }

    public void clickLnkPostLoginEfficiencyRebatesTab() {
        clickElementUsingJsExecutor(lnkRebatesTab);
        log.info("Rebates Tips Tab Link clicked successfully.");
    }
    
    public void clicklnkRebatesTab() {
        click(lnkRebatesTab);
        log.info("lnk Rebates Tab clicked {}.");
    }
    
    @FindBy(css = " .list-group .footprint.list-group-item a")
    private WebElement lnkFootprintTab;
    
    public boolean islnkFootprintTabVisible() {
        log.info("lnk Foot print Tab Visible :" + lnkFootprintTab.isDisplayed());
        return lnkFootprintTab.isDisplayed();
    }
    
    public void clicklnkFootprintTab() {
        click(lnkFootprintTab);
        log.info("lnk Foot print Tab clicked {}.");
    }
    
    @FindBy(xpath = " //div[@class='add_tip_bottom'][not(contains(@style,'display: none'))]//input[@type='checkbox']/../../../..//h1")
    private WebElement lstSavingTipsCheckBoxHeader;
    
    public boolean islstSavingTipsCheckBoxHeaderVisible() {
        log.info("lst Saving Tips Check Box Header Visible :" + lstSavingTipsCheckBoxHeader.isDisplayed());
        return lstSavingTipsCheckBoxHeader.isDisplayed();
    }
    
    public void clicklstSavingTipsCheckBoxHeader() {
        click(lstSavingTipsCheckBoxHeader);
        log.info("lst Saving Tips Check Box Header clicked {}.");
    }
    
    @FindBy(xpath = " //div[@class='add_tip_bottom'][not(contains(@style,'display: none'))]//input[@type='checkbox']")
    private WebElement lstSavingTipsCheckBox;
    
    public boolean islstSavingTipsCheckBoxVisible() {
        log.info("lst Saving Tips Check Box Visible :" + lstSavingTipsCheckBox.isDisplayed());
        return lstSavingTipsCheckBox.isDisplayed();
    }
    
    public void clicklstSavingTipsCheckBox() {
        click(lstSavingTipsCheckBox);
        log.info("lst Saving Tips Check Box clicked {}.");
    }
    
    @FindBy(xpath = " //a[@aria-label='Click to kebab menu']")
    private WebElement threedotsWaystoSave;
    
    public boolean isThreedotsWaystoSaveVisible() {
        log.info("three dots Ways to Save Visible :" + threedotsWaystoSave.isDisplayed());
        return threedotsWaystoSave.isDisplayed();
    }
    
    public void clickThreedotsWaystoSave() {
        click(threedotsWaystoSave);
        log.info("three dots Ways to Save clicked {}.");
    }
    
    @FindBy(xpath = " //a[text()='Edit']")
    private WebElement lnkEfficiencyEdit;
    
    public boolean isLnkEfficiencyEditVisible() {
        log.info("lnk Efficiency Edit Visible :" + lnkEfficiencyEdit.isDisplayed());
        return lnkEfficiencyEdit.isDisplayed();
    }
    
    public void clickLnkEfficiencyEdit() {
        click(lnkEfficiencyEdit);
        log.info("lnk Efficiency Edit clicked {}.");
    }
    
    @FindBy(xpath = " //i[text()='Delete']")
    private WebElement lnkEfficiencyDelete;
    
    public boolean islnkEfficiencyDeleteVisible() {
        log.info("lnk Efficiency Delete Visible :" + lnkEfficiencyDelete.isDisplayed());
        return lnkEfficiencyDelete.isDisplayed();
    }
    
    public void clicklnkEfficiencyDelete() {
        click(lnkEfficiencyDelete);
        log.info("lnk Efficiency Delete clicked {}.");
    }
    
          
    @FindBy(xpath = " //div[@class='modal-body'][contains(text(),'Are you sure you want to delete this saving tip from your annual goal?')]")
    private WebElement lblMsgConfirmationDeleteSavingTips;
    
    public boolean islblMsgConfirmationDeleteSavingTipsVisible() {
        log.info("lbl Msg Confirmation Delete Saving Tips Visible :" + lblMsgConfirmationDeleteSavingTips.isDisplayed());
        return lblMsgConfirmationDeleteSavingTips.isDisplayed();
    }
    
    @FindBy(css = " #btnDeleteSavingTips")
    private WebElement btnConfirmDeleteSavingTips;
    
    public boolean isbtnConfirmDeleteSavingTipsVisible() {
        log.info("btn Confirm Delete Saving Tips Visible :" + btnConfirmDeleteSavingTips.isDisplayed());
        return btnConfirmDeleteSavingTips.isDisplayed();
    }
        
    public void clickbtnConfirmDeleteSavingTips() {
        click(btnConfirmDeleteSavingTips);
        log.info("btn Confirm Delete Saving Tips clicked {}.");
    }
    
    @FindBy(xpath = " //h1[contains(text(),'")
    private WebElement chkBoxSavingTipsNameDynamicXpathBefore;
    
    public boolean ischkBoxSavingTipsNameDynamicXpathBeforeVisible() {
        log.info("chk Box Saving Tips Name Dynamic Xpath Before Visible :" + chkBoxSavingTipsNameDynamicXpathBefore.isDisplayed());
        return chkBoxSavingTipsNameDynamicXpathBefore.isDisplayed();
    }
        
    public void clickchkBoxSavingTipsNameDynamicXpathBefore() {
        click(chkBoxSavingTipsNameDynamicXpathBefore);
        log.info("chk Box Saving Tips Name Dynamic Xpath Before clicked {}.");
    }
    
    @FindBy(xpath = " ')]/../../..//div[@class='add_tip_bottom ng-hide']")
    private WebElement chkBoxSavingTipsNameDynamicXpathAfter;
    
    public boolean ischkBoxSavingTipsNameDynamicXpathAfterVisible() {
        log.info("chk Box Saving Tips Name Dynamic Xpath After Visible :" + chkBoxSavingTipsNameDynamicXpathAfter.isDisplayed());
        return chkBoxSavingTipsNameDynamicXpathAfter.isDisplayed();
    }
        
    public void clickchkBoxSavingTipsNameDynamicXpathAfter() {
        click(chkBoxSavingTipsNameDynamicXpathAfter);
        log.info("chk Box Saving Tips Name Dynamic Xpath After clicked {}.");
    }
    
    @FindBy(xpath = " //div[@class='details_box']//h5//span[@class='Popup']")
    private WebElement lstSavingTipsNameAnnualGoal;
    
    public boolean islstSavingTipsNameAnnualGoalVisible() {
        log.info("lst Saving Tips Name Annual Goal Visible :" + lstSavingTipsNameAnnualGoal.isDisplayed());
        return lstSavingTipsNameAnnualGoal.isDisplayed();
    }
         
    @FindBy(css = " //*[@class='register']//a[not(contains(text(),'Enrolled')) and not(contains(text(),'Unenroll'))][not(contains(@class,'ng-hide'))]/../../../../..//h1")
    private WebElement lstTipHeadingEnrollProgram;
    
    public boolean islstTipHeadingEnrollProgramVisible() {
        log.info("lst Tip Heading Enroll Program Visible :" + lstTipHeadingEnrollProgram.isDisplayed());
        return lstTipHeadingEnrollProgram.isDisplayed();
    }
    
    @FindBy(css = " #li_power a")
    private WebElement lnkPowerTab;
    
    public boolean islnkPowerTabVisible() {
        log.info("lnk Power Tab Visible :" + lnkPowerTab.isDisplayed());
        return lnkPowerTab.isDisplayed();
    }
    
    public void clicklnkPowerTab() {
        click(lnkPowerTab);
        log.info("ln kPower Tab clicked {}.");
    }
       
    @FindBy(css = " a[globalize='ML_POWERUSAGE_Navigation_Water']")
    private WebElement lnkWaterTab;
    
    public boolean islnkWaterTabVisible() {
        log.info("lnk Water Tab Visible :" + lnkWaterTab.isDisplayed());
        return lnkWaterTab.isDisplayed();
    }
    
    public void clicklnkWaterTab() {
        click(lnkWaterTab);
        log.info("lnk Water Tab clicked {}.");
    }
    
    @FindBy(xpath = " //div[@class='account_info']/div/ul/li[3]/p")
    private WebElement lblRemainingBalance;
    
    public boolean islblRemainingBalanceVisible() {
        log.info("lbl Remaining Balance Visible :" + lblRemainingBalance.isDisplayed());
        return lblRemainingBalance.isDisplayed();
    }
    
    @FindBy(css = " span[globalize='ML_div_Efficiency_HowYouRank']")
    private WebElement lblHowYouRank;
    
    public boolean islblHowYouRankVisible() {
        log.info("lbl How You Rank Visible :" + lblHowYouRank.isDisplayed());
        return lblHowYouRank.isDisplayed();
    }
    
    @FindBy(css = "span[globalize='ML_div_Efficiency_WantToKnowMore']")
    private WebElement lblWantToKnowMore;
    
    public boolean islblWantToKnowMoreVisible() {
        log.info("lbl Want To Know More Visible :" + lblWantToKnowMore.isDisplayed());
        return lblWantToKnowMore.isDisplayed();
    }
    
    @FindBy(css = " span[globalize='ML_div_Efficiency_HowAmIUsingPower']")
    private WebElement lblHowAmIUsingPower;
    
    public boolean islblHowAmIUsingPowerVisible() {
        log.info("lbl How AMI Using Power Visible :" + lblHowAmIUsingPower.isDisplayed());
        return lblHowAmIUsingPower.isDisplayed();
    }
    
    
    @FindBy(css = " span[globalize='ML_div_Efficiency_HowAmIUsingWater']")
    private WebElement lblHowAmIUsingWater;
    
    public boolean islblHowAmIUsingWaterVisible() {
        log.info("lbl How AMI Using Power Visible :" + lblHowAmIUsingWater.isDisplayed());
        return lblHowAmIUsingWater.isDisplayed();
    }
    
    @FindBy(css = " span[globalize='ML_div_Efficiency_PowerSavingTips']")
    private WebElement lblPowerSavingTips;
    
    public boolean islblPowerSavingTipsVisible() {
        log.info("lbl Power Saving Tips Visible :" + lblPowerSavingTips.isDisplayed());
        return lblPowerSavingTips.isDisplayed();
    }
    
    
    @FindBy(css = " span[globalize='ML_div_Efficiency_WaterSavingTips']")
    private WebElement lblWaterSavingTips;
    
    public boolean islblWaterSavingTipsVisible() {
        log.info("lbl Water Saving Tips Visible :" + lblWaterSavingTips.isDisplayed());
        return lblWaterSavingTips.isDisplayed();
    }
    
    @FindBy(xpath = " //a[contains(text(),'Recommendation')]")
    private WebElement btnEventsRecommendation;
    
    public boolean isbtnEventsRecommendationVisible() {
        log.info("btn Events Recommendation Visible :" + btnEventsRecommendation.isDisplayed());
        return btnEventsRecommendation.isDisplayed();
    }
    
    public void clickbtnEventsRecommendation() {
        click(btnEventsRecommendation);
        log.info("btn Events Recommendation clicked {}.");
    }

    @FindBy(xpath = " //div[@class='Left_inner_area']/div/ul/li[2]")
    private WebElement lnkRecommendation;
    
    public boolean islnkRecommendationVisible() {
        log.info("lnk Recommendation Visible :" + lnkRecommendation.isDisplayed());
        return lnkRecommendation.isDisplayed();
    }
    
    public void clicklnkRecommendation() {
        click(lnkRecommendation);
        log.info("lnk Recommendation clicked {}.");
    }
    
    @FindBy(xpath = " //div[@class='Left_inner_area']/div/ul/li[1]/a")
    private WebElement lnkQuestions;
    
    public boolean islnkQuestionsVisible() {
        log.info("lnk Questions Visible :" + lnkQuestions.isDisplayed());
        return lnkQuestions.isDisplayed();
    }
    
    public void clicklnkQuestions() {
        click(lnkRecommendation);
        log.info("lnk Recommendation clicked {}.");
    }
    
    @FindBy(css = " .upper_div h2")
    private WebElement lblAllQuestions;
       
    public boolean islblAllQuestionsVisible() {
        log.info("lnk Questions Visible :" + lblAllQuestions.isDisplayed());
        return lblAllQuestions.isDisplayed();
    }
    
    @FindBy(css = " #devices > div > div > div.lft_profile_sec > div.Right_inner_area > div:nth-child(2) > h2")
    private WebElement lnkHeatingFuel;
    
    public boolean islnkHeatingFuelVisible() {
        log.info("lnk Heating Fuel Visible :" + lnkHeatingFuel.isDisplayed());
        return lnkHeatingFuel.isDisplayed();
    }
    
    public void clicklnkHeatingFuel() {
        click(lnkHeatingFuel);
        log.info("lnk Heating Fuel clicked {}.");
    }
    
    @FindBy(css = " #devices > div > div > div.lft_profile_sec > div.Right_inner_area > div:nth-child(3) > h2")
    private WebElement lnkHighEfficiencyLighting;
    
    public boolean islnkHighEfficiencyLightingVisible() {
        log.info("lnk High Efficiency Lighting Visible :" + lnkHighEfficiencyLighting.isDisplayed());
        return lnkHighEfficiencyLighting.isDisplayed();
    }
    
    public void clicklnkHighEfficiencyLighting() {
        click(lnkHighEfficiencyLighting);
        log.info("lnk High Efficiency Lighting clicked {}.");
    }
    
    @FindBy(css = " #devices > div > div > div.lft_profile_sec > div.Right_inner_area > div:nth-child(4) > h2")
    private WebElement lnkCookFuel;
    
    public boolean islnkCookFuelVisible() {
        log.info("lnk Cook Fuel Visible :" + lnkCookFuel.isDisplayed());
        return lnkCookFuel.isDisplayed();
    }
    
    public void clicklnkCookFuel() {
        click(lnkHighEfficiencyLighting);
        log.info("lnk Cook Fuel clicked {}.");
    }
    
    @FindBy(css = " #devices > div > div > div.lft_profile_sec > div.Right_inner_area > div:nth-child(5) > h2")
    private WebElement lnkSolarPanel;
    
    public boolean islnkSolarPanelVisible() {
        log.info("lnk Cook Fuel Visible :" + lnkSolarPanel.isDisplayed());
        return lnkSolarPanel.isDisplayed();
    }
    
    public void clicklnkSolarPanel() {
        click(lnkSolarPanel);
        log.info("lnk Solar Panel clicked {}.");
    }
    
    @FindBy(css = " #devices > div > div > div.lft_profile_sec > div.Right_inner_area > div:nth-child(6) > h2")
    private WebElement lnkDescHomeAirConditioner;
    
    public boolean islnkDescHomeAirConditionerVisible() {
        log.info("lnk Desc Home Air Conditioner Visible :" + lnkDescHomeAirConditioner.isDisplayed());
        return lnkDescHomeAirConditioner.isDisplayed();
    }
    
    public void clicklnkDescHomeAirConditioner() {
        click(lnkDescHomeAirConditioner);
        log.info("lnk Desc Home Air Conditioner clicked {}.");
    }
    
    @FindBy(css = " #devices > div > div > div.lft_profile_sec > div.Right_inner_area > div:nth-child(7) > h2")
    private WebElement lnkTotalFloors;
    
    public boolean islnkTotalFloorsVisible() {
        log.info("lnk Total Floors Visible :" + lnkTotalFloors.isDisplayed());
        return lnkTotalFloors.isDisplayed();
    }
    
    public void clicklnkTotalFloors() {
        click(lnkTotalFloors);
        log.info("lnk Total Floors clicked {}.");
    }
    
    @FindBy(css = " #devices > div > div > div.lft_profile_sec > div.Right_inner_area > div:nth-child(11) > h2")
    private WebElement lnkHeaterFuel;
    
    public boolean islnkHeaterFuelVisible() {
        log.info("lnk Total Floors Visible :" + lnkHeaterFuel.isDisplayed());
        return lnkHeaterFuel.isDisplayed();
    }
    
    public void clicklnkHeaterFuel() {
        click(lnkHeaterFuel);
        log.info("lnk Total Floors clicked {}.");
    }
    
    @FindBy(css = " #devices > div > div > div.lft_profile_sec > div.Right_inner_area > div:nth-child(8) > h2")
    private WebElement lnkHowOldIsYourAC;
    
    public boolean islnkHowOldIsYourACVisible() {
        log.info("lnk How Old Is Your AC Visible :" + lnkHowOldIsYourAC.isDisplayed());
        return lnkHowOldIsYourAC.isDisplayed();
    }
    
    public void clicklnkHowOldIsYourAC() {
        click(lnkHowOldIsYourAC);
        log.info("lnk How Old Is Your AC clicked {}.");
    }
    
    @FindBy(css = " #devices > div > div > div.lft_profile_sec > div.Right_inner_area > div:nth-child(9) > h2")
    private WebElement lnkDoYouHavePool;
    
    public boolean islnkDoYouHavePoolVisible() {
        log.info("lnk How Old Is Your AC Visible :" + lnkHowOldIsYourAC.isDisplayed());
        return lnkHowOldIsYourAC.isDisplayed();
    }
    
    public void clicklnkDoYouHavePool() {
        click(lnkHowOldIsYourAC);
        log.info("lnk How Old Is Your AC clicked {}.");
    }
    
    @FindBy(css = " #devices > div > div > div.lft_profile_sec > div.Right_inner_area > div:nth-child(10) > h2")
    private WebElement lnkDescRefridgerator;
    
    public boolean islnkDescRefridgeratorVisible() {
        log.info("lnk Desc Refridgerator Visible :" + lnkDescRefridgerator.isDisplayed());
        return lnkDescRefridgerator.isDisplayed();
    }
    
    public void clicklnkDescRefridgerator() {
        click(lnkHowOldIsYourAC);
        log.info("lnk Desc Refridgerator clicked {}.");
    }
       
    @FindBy(css = " #devices > div > div > div.lft_profile_sec > div.Right_inner_area > div:nth-child(11) > h2")
    private WebElement lnkWaterHeaterFuel;
    
    public boolean islnkWaterHeaterFuelVisible() {
        log.info("lnk Water Heater Fuel Visible :" + lnkWaterHeaterFuel.isDisplayed());
        return lnkWaterHeaterFuel.isDisplayed();
    }
    
    public void clicklnkWaterHeaterFuel() {
        click(lnkWaterHeaterFuel);
        log.info("lnk Water Heater Fuel clicked {}.");
    }
    
    @FindBy(css = " #devices > div > div > div.lft_profile_sec > div.Right_inner_area > div:nth-child(12) > h2")
    private WebElement lnkHomeType;
    
    public boolean islnkHomeTypeVisible() {
        log.info("lnk Home Type Visible :" + lnkHomeType.isDisplayed());
        return lnkHomeType.isDisplayed();
    }
    
    public void clicklnkHomeType() {
        click(lnkHomeType);
        log.info("lnk Home Type clicked {}.");
    }
    
    @FindBy(css = " #devices > div > div > div.lft_profile_sec > div.Right_inner_area > div:nth-child(13) > h2")
    private WebElement lnkClothDryerFuel;
    
    public boolean isClothDryerFuelVisible() {
        log.info("lnk Post Login Efficiency Cloth Dryer Fuel :" + lnkClothDryerFuel.isDisplayed());
        return lnkClothDryerFuel.isDisplayed();
    }
    
    public void clicklnkClothDryerFuel() {
        click(lnkClothDryerFuel);
        log.info("lnk Post Login Efficiency Cloth Dryer clicked {}.");
    }
       
    @FindBy(css = " #devices > div > div > div.lft_profile_sec > div.Right_inner_area > div:nth-child(14) > h2")
    private WebElement lnkSanctionedLoad;
    
    public boolean islnkSanctionedLoadVisible() {
        log.info("lnk Sanctioned Load :" + lnkSanctionedLoad.isDisplayed());
        return lnkSanctionedLoad.isDisplayed();
    }
    
    public void clicklClothDryerFuel() {
        click(lnkSanctionedLoad);
        log.info("lnk Sanctioned Load clicked {}.");
    }
    
    @FindBy(css = " #btnSubmitQuestion")
    private WebElement lnkSubmitButton;
    
    public void clicklnkSubmitButton() {
        click(lnkSubmitButton);
        log.info("lnk Submit Button clicked {}.");
    }
       
    @FindBy(css = " .chart-out h2")
    private WebElement lblCompareToYourNeighbour;
    
    public void clicklblCompareToYourNeighbour() {
        click(lblCompareToYourNeighbour);
        log.info("lbl Compare To Your Neighbour clicked {}.");
    }
       
    @FindBy(css = ".chart-out1 h2")
    private WebElement lblCompareYourNeighbour12Months;
    
    public void clicklblCompareYourNeighbour12Months() {
        click(lblCompareYourNeighbour12Months);
        log.info("lbl Compare YourNeighbour12Months clicked {}.");
    }
    
    @FindBy(css = " #usageType")
    private WebElement selPostLoginEfficiencyEnergy;
    
    public void clickselPostLoginEfficiencyEnergy() {
        click(lblCompareYourNeighbour12Months);
        log.info("lbl Compare  YourNeighbour12Months clicked {}.");
    }
       
    @FindBy(css = " #ContentPlaceHolder1_ContentPlaceHolderBody_btnPdfExport")
    private WebElement btnExportCopmare;
    
    public void clickExportCopmare() {
        click(btnExportCopmare);
        log.info("btn Export Copmare clicked {}.");
    }
    
    @FindBy(css = " .heading_area h4")
    private WebElement lblHouseholdInfo;
    
    public void clicklblHouseholdInfo() {
        click(lblHouseholdInfo);
        log.info("lbl House hold Info clicked {}.");
    }
       
    @FindBy(css = " .zip_code_box")
    private WebElement lblZipCode;
    
    public void clicklblZipCode() {
        click(lblZipCode);
        log.info("lbl Zip Code clicked {}.");
    }
    
    @FindBy(css = "#ddlMonthlyEnergyBills")
    private WebElement selTypicalMonthlyBill;
    
    public void clickselTypicalMonthlyBill() {
        click(selTypicalMonthlyBill);
        log.info("sel Typical Monthly Bill Clicked {}.");
    }
    
    @FindBy(css = " #ddlHouseHoldSize")
    private WebElement selHouseHoldSize;
    
    public void clickselHouseHoldSize() {
        click(selHouseHoldSize);
        log.info("sel House Hold Size Clicked {}.");
    }
       
    @FindBy(css = " #ddlHouseHoldIncome")
    private WebElement selHouseHoldIncomw;
    
    public boolean islblSuccessfulSubmitVisibility() {
    	log.info("sel House Hold In comw Status :" + selHouseHoldIncomw.isDisplayed());
    	return selHouseHoldIncomw.isDisplayed();
    }
            
    @FindBy(css = " #btnNext")
    private WebElement btnnext;
    
    public boolean isbtnnextVisibility() {
    	log.info("btn next Status :" + selHouseHoldIncomw.isDisplayed());
    	return selHouseHoldIncomw.isDisplayed();
    }
       
    @FindBy(css = "#LblMessage")
    private WebElement lblAssistanceMsg;
    
    public boolean islblAssistanceMsgVisibility() {
    	log.info("lbl Assistance Msg Status :" + lblAssistanceMsg.isDisplayed());
    	return lblAssistanceMsg.isDisplayed();
    }
       
    @FindBy(css = "#IDRegistration")
    private WebElement lnkBillAssistance;
    
    public boolean islnkBillAssistanceVisibility() {
    	log.info("lnk Bill Assistance Status :" + lnkBillAssistance.isDisplayed());
    	return lnkBillAssistance.isDisplayed();
    }
        
    @FindBy(css = " .learn_icon")
    private WebElement lnkLearnMore;
    
    public boolean islnkLearnMoreVisibility() {
    	log.info("lnk Learn More Status :" + lnkLearnMore.isDisplayed());
    	return lnkLearnMore.isDisplayed();
    }
        
    @FindBy(css = " #AddUserSaveBtn")
    private WebElement btnAddUserSaveButton;
        
    public boolean isbtnAddUserSaveButtonVisibility() {
    	log.info("btn Add User Save Button Visibility Status :" + btnAddUserSaveButton.isDisplayed());
    	return btnAddUserSaveButton.isDisplayed();
    }
        
    @FindBy(css = " .cancel-button")
    private WebElement btnAddUserCancelButton;
    
    public boolean isbtnAddUserCancelButtonVisibility() {
    	log.info("btn Add User Cancel Button Visibility Status :" + btnAddUserCancelButton.isDisplayed());
    	return btnAddUserCancelButton.isDisplayed();
    }
       
    @FindBy(css = " #AddUserSaveBtn")
    private WebElement btnRegister;
    
    public boolean isbtnRegisterVisibility() {
    	log.info("btn Register Visibility Status :" + btnRegister.isDisplayed());
    	return btnRegister.isDisplayed();
    }
       
    @FindBy(css = " .cong_sec h3")
    private WebElement lblCongratulation;
    
    public boolean islblCongratulationVisibility() {
    	log.info("lbl Congratulation Visibility Status :" + lblCongratulation.isDisplayed());
    	return lblCongratulation.isDisplayed();
    }
       
    @FindBy(css = " #info")
    private WebElement lblCongratulationMsg;
    
    public boolean islblCongratulationMsgVisibility() {
    	log.info("lbl Congratulation Msg Visibility Status :" + lblCongratulationMsg.isDisplayed());
    	return lblCongratulationMsg.isDisplayed();
    }
    
    @FindBy(css = " #UpcomingEvent")
    private WebElement lnkUpcomingEvent;
    
    public boolean islnkUpcomingEventVisibility() {
    	log.info("lnk Upcoming Event Visibility Status :" + lnkUpcomingEvent.isDisplayed());
    	return lnkUpcomingEvent.isDisplayed();
    }
    
    
    @FindBy(css = " #ActiveEvent")
    private WebElement lnkActiveEvent;
    
    public boolean islnkActiveEventVisibility() {
    	log.info("lnk Active Event Visibility Status :" + lnkActiveEvent.isDisplayed());
    	return lnkActiveEvent.isDisplayed();
    }
        
    @FindBy(css = " #PastEvent")
    private WebElement lnkPastEvent;
    
    public boolean islnkPastEventVisibility() {
    	log.info("lnk Past Event Visibility Status :" + lnkPastEvent.isDisplayed());
    	return lnkPastEvent.isDisplayed();
    }
    
    @FindBy(css = " .dataTables_info")
    private WebElement lblPagination;
    
    public boolean islblPaginationVisibility() {
    	log.info("lbl Pagination Visibility Status :" + lblPagination.isDisplayed());
    	return lblPagination.isDisplayed();
    }
    
    
    @FindBy(css = " .dataTables_empty")
    private WebElement lblNoDataFound;
    
    public boolean islblNoDataFoundVisibility() {
    	log.info("lbl No Data Found Visibility Status :" + lblNoDataFound.isDisplayed());
    	return lblNoDataFound.isDisplayed();
    }
    
    
    @FindBy(css = " #Demand")
    private WebElement lnkEventName;
    
    public boolean islnkEventNameVisibility() {
    	log.info("lnk Event Name Visibility Status :" + lnkEventName.isDisplayed());
    	return lnkEventName.isDisplayed();
    }
    
    @FindBy(css = " #EventSaving")
    private WebElement lnkEventSavings;
    
    public boolean islnkEventSavingsVisibility() {
    	log.info("lnk Event Savings Visibility Status :" + lnkEventSavings.isDisplayed());
    	return lnkEventSavings.isDisplayed();
    }
        
    @FindBy(css = " #MonthlySaving")
    private WebElement lnkMonthlySaving;
    
    public boolean islnkMonthlySavingVisibility() {
    	log.info("lnk Monthly Saving Visibility Status :" + lnkMonthlySaving.isDisplayed());
    	return lnkMonthlySaving.isDisplayed();
    }
    
    @FindBy(css = " #YearlySaving")
    private WebElement lnkYearlySaving;
    
    public boolean islnkYearlySavingVisibility() {
    	log.info("lnk Monthly Saving Visibility Status :" + lnkYearlySaving.isDisplayed());
    	return lnkYearlySaving.isDisplayed();
    }
    
    
    @FindBy(css = " #Participation")
    private WebElement lnkPostLoginEfficiencyParticipation;
    @FindBy(css = " //div[@id='headerText']/label[1]")
    private WebElement lblPostLoginEfficiencySavingsKwh;
    @FindBy(css = " #ContentPlaceHolder1_ContentPlaceHolderBody_currencySaving")
    private WebElement lblPostLoginEfficiencySavings;
    @FindBy(css = " #ContentPlaceHolder1_ContentPlaceHolderBody_currencyIncentive")
    private WebElement lblPostLoginEfficiencyIncentivesEarned;
    @FindBy(css = " #donutchartcontainer")
    private WebElement lblPostLoginEfficiencyParticipationChart;
    @FindBy(css = " .close")
    private WebElement btnPostLoginEfficiencyEventsClose;
    @FindBy(css = " #FileUpload11")
    private WebElement btnPostLoginEfficiencyUploadF;
    @FindBy(xpath = " .list-group [globalize='ML_ways']")
    private WebElement waystoSave;
    
    public void clickWaysToSave() {
    	log.info("Clicking the ways to save button.");
		click(waystoSave);
		log.info("efficiency ways to save successfully.");
    }
}

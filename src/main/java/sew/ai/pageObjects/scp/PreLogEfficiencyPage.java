package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PreLogEfficiencyPage extends HomePage {
    private static final Logger log = LogManager.getLogger(PreLogEfficiencyPage.class);

    public PreLogEfficiencyPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = " .nav_left li a")
    private WebElement lstPreLoginEfficiencySideMenu;
    @FindBy(css = " .top_conte_box_mob #dvRebateContainer .repeat")
    private WebElement lstPreLoginEfficiencyTipCount;
    @FindBy(css = " #dvRebateContainer .repeat .textDesc a")
    private WebElement lstPreLoginEfficiencyReadMore;
    @FindBy(css = " #dvRebateContainer .repeat .content_energy_area h5")
    private WebElement lstPreLoginEfficiencyTipHeading;
//    @FindBy(css = " #dvRebateContainer .repeat ul li [id^=" VC"]")
//    private WebElement lstPreLoginEfficiencyTipViewedCount;
//    @FindBy(css = " #showdetails_login .modal-header .close")
//    private WebElement lstPreLoginEfficiencyTipClose;
//    @FindBy(css = " .cancel-button[title=" Back To Rebates"]")
//    private WebElement btnBackToRebates;
//    @FindBy(css = " .cancel-button[title=" Back To Programs"]")
//    private WebElement btnBackToPrograms;
//    @FindBy(css = " .cancel-button[title=" Back To Saving Tips"]")
//    private WebElement btnBackToSavingTips;
    @FindBy(css = " .cancel-button[title='Back To Educational Tips']")
    private WebElement btnBackToEducationalTips;
    @FindBy(css = " .added_view_sec [style='display: block'] .ng-binding")
    private WebElement lstPreLoginEfficiencyTipLikesCount;
    @FindBy(css = " li.icon_rebates")
    private WebElement lnkPreLoginEfficiencyRebatesTabIcon;
    @FindBy(css = " .bottom_efficiency .register a:not(.ng-hide):not(.btn-disable)")
    private WebElement lstlnkPreLoginEfficiencyEnroll;
    @FindBy(css = " .TableCellHeaderSearch input")
    private WebElement txtPreLoginEfficiencySearchBox;
    @FindBy(css = " .printbtn")
    private WebElement btnPreLoginEfficiencyPrint;
    @FindBy(css = " .icon_dr_programes a")
    private WebElement lnkPreLoginEfficiencyProgramsTab;
    @FindBy(css = " .icon_dr_programes")
    private WebElement lnkPreLoginEfficiencyProgramsTabIcon;
    @FindBy(css = " .icon_saving_tips a")
    private WebElement lnkPreLoginEfficiencySavingTipsTab;
    @FindBy(css = " (//span[@id='lbl_saveupto'])[1]")
    private WebElement iconDollarRebate;
    @FindBy(css = " .registered_box .eff_register .popup.ng-binding")
    private WebElement lstPreLoginEfficiencyTipEnrolledCount;
    @FindBy(css = " .added_view_sec li a")
    private WebElement lstPreLoginEfficiencyTipLikeUnlikeButtonCount;
    @FindBy(css = " .added_view_sec .like_lnk_ro")
    private WebElement lstbtnPreLoginEfficiencyTipUnLikeButton;
    @FindBy(css = "   #dvRebateContainer .repeat .textDesc a")
    private WebElement lnkPreLoginEfficiencyReadMoreRebateExternal;
    @FindBy(css = " .top_conte_box_mob #dvDrProgramsContainer .repeat")
    private WebElement lstPreLoginEfficiencyTipCountPrograms;
    @FindBy(css = " #dvDrProgramsContainer .repeat .textDesc a")
    private WebElement lstPreLoginEfficiencyReadMorePrograms;
    @FindBy(css = " #dvDrProgramsContainer .repeat .content_energy_area h5")
    private WebElement lstPreLoginEfficiencyTipHeadingPrograms;
    @FindBy(css = " #dvDrProgramsContainer .repeat ul li [id^='VC']")
    private WebElement lstPreLoginEfficiencyTipViewedCountPrograms;
//    @FindBy(css = "#dvDrProgramsContainer .repeat .textDesc a[isexternal="true"]")
//    private WebElement lstPreLoginEfficiencyReadMoreProgramsExternal;
    @FindBy(css = " .top_conte_box_mob #dvSavingTipsContainer .repeat")
    private WebElement lstPreLoginEfficiencyTipCountSavingTips;
    @FindBy(css = " #dvSavingTipsContainer .repeat .textDesc a")
    private WebElement lstPreLoginEfficiencyReadMoreSavingTips;
    @FindBy(css = " #dvSavingTipsContainer .repeat .content_energy_area h5")
    private WebElement lstPreLoginEfficiencyTipHeadingSavingTips;
    @FindBy(css = " #dvSavingTipsContainer .repeat ul li [id^='VC']")
    private WebElement lstPreLoginEfficiencyTipViewedCountSavingTips;
//    @FindBy(css = " #dvSavingTipsContainer .repeat .textDesc a[isexternal="true"]")
//    private WebElement lstPreLoginEfficiencyReadMoreSavingTipsExternal;
    @FindBy(css = " .top_conte_box_mob #dvEducationalTipsContainer .repeat")
    private WebElement lstPreLoginEfficiencyTipCountEducationalTips;
    @FindBy(css = " #dvEducationalTipsContainer .repeat .textDesc a")
    private WebElement lstPreLoginEfficiencyReadMoreEducationalTips;
    @FindBy(css = " #dvEducationalTipsContainer .repeat .content_energy_area h5")
    private WebElement lstPreLoginEfficiencyTipHeadingEducationalTips;
    @FindBy(css = " #dvEducationalTipsContainer .repeat ul li [id^='VC']")
    private WebElement lstPreLoginEfficiencyTipViewedCountEducationalTips;
//    @FindBy(css = " #dvEducationalTipsContainer .repeat .textDesc a[isexternal="true"]")
//    private WebElement lstPreLoginEfficiencyReadMoreEducationalTipsExternal;
    @FindBy(css = " .icon_saving_tips")
    private WebElement lnkPreLoginEfficiencySavingTipsTabIcon;
    @FindBy(css = " .educational_tips a")
    private WebElement lnkPreLoginEfficiencyEducationalTipsTab;
    @FindBy(css = " .educational_tips")
    private WebElement lnkPreLoginEfficiencyEducationalTipsTabIcon;
    @FindBy(css = " .bottom_efficiency  [style='border-left: 1px solid;'] .ng-binding")
    private WebElement lstPreLoginEfficiencySavingTipsViewedCount;
//    @FindBy(css = " .added_view_sec [style='display: block']:not(.eff_register) .ng-binding")
//    private WebElement lstPreLoginEfficiencySavingTipsLikeCount;
    @FindBy(css = " .added_view_sec li:not([style='height: 18px; display: block']) .ng-binding")
    private WebElement lstPreLoginEfficiencyEducationalTipsViewedCount;
    @FindBy(css = " .top_conte_box_mob #dvRebateContainer .repeat span[globalize='ML_SvngTips_span_Likes']")
    private WebElement lstPreLoginEfficiencyRebatesLikeLink;
    @FindBy(css = " .top_conte_box_mob #dvRebateContainer .repeat span[class='spnLikeCount ng-binding']")
    private WebElement lstPreLoginEfficiencyRebatesLikeCount;
    @FindBy(css = " //span[contains(@class,'cls_disclaimer')][not(contains(@style,'display: none;'))]")
    private WebElement lblPreLoginEfficiencyDisclaimer;
    @FindBy(css = " #no_rebates")
    private WebElement lblPreLoginEfficiencyNoRebate;
    @FindBy(css = " #no_programs")
    private WebElement lblPreLoginEfficiencyNoPrograms;
    @FindBy(css = " #no_save_tips")
    private WebElement lblPreLoginEfficiencyNoSavingTips;
    @FindBy(css = " #no_edu_tips")
    private WebElement lblPreLoginEfficiencyNoEducationalTips;
    @FindBy(css = " #dvSavingTipsContainer .discription_pro")
    private WebElement lblPreloginEfficiencyDescription;
    @FindBy(css = " #dvEducationalTipsContainer .discription_pro")
    private WebElement lblPreloginEfficiencyEducationalTipsDescription;
    @FindBy(css = " .top_conte_box_mob #dvSavingTipsContainer .repeat span[globalize='ML_SvngTips_span_Likes']")
    private WebElement lstPreLoginEfficiencySavingTipsLikeLink;
    @FindBy(css = " //div[@id='dvSavingTipsContainer']//*[@class='repeat']//div[@class='bottom_efficiency']//span[@class='ng-binding']")
    private WebElement lstPreLoginEfficiencySavingTipsLikeCount;
    @FindBy(css = " .top_conte_box_mob #dvEducationalTipsContainer .repeat span[globalize='ML_SvngTips_span_Likes']")
    private WebElement lstPreLoginEfficiencyEducationalTipsLikeLink;
    @FindBy(css = " //div[@id='dvEducationalTipsContainer']//*[@class='repeat']//div[@class='bottom_efficiency']//span[@class='ng-binding']")
    private WebElement lstPreLoginEfficiencyEducationalTipsLikeCount;





}

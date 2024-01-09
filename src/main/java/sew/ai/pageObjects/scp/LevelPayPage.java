package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LevelPayPage extends HomePage {
    private static final Logger log = LogManager.getLogger(LevelPayPage.class);

    public LevelPayPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "h1[id= 'PageTitle']")
    private WebElement lblLevelPay;
    
    public boolean islblLevelPayVisible() {
    	log.info("lbl LevelPay Status :" + lblLevelPay.isDisplayed());
    	return lblLevelPay.isDisplayed();
   }
      
    @FindBy(css = "#devices .txt_jcr>li")
    private WebElement lblDetailMsgTxt;
    
    public boolean islblDetailMsgTxtVisible() {
    	log.info("btn Compare Status :" + lblDetailMsgTxt.isDisplayed());
    	return lblDetailMsgTxt.isDisplayed();
   }
      
    @FindBy(css = "#SAValue1")
    private WebElement lblServiceAddressDetails;
    
    public boolean islblServiceAddressDetailsVisible() {
    	log.info("btn Compare Status :" + lblServiceAddressDetails.isDisplayed());
    	return lblServiceAddressDetails.isDisplayed();
   }
    
    
    @FindBy(css = "#ContentPlaceHolder1_ContentPlaceHolderBody_Label1")
    private WebElement lblServiceAcntNumber;
    
    public boolean islblServiceAcntNumberVisible() {
    	log.info("lbl Service Account Number Status :" + lblServiceAcntNumber.isDisplayed());
    	return lblServiceAcntNumber.isDisplayed();
   }
       
    @FindBy(css = "#lblAccntNoVal")
    private WebElement lblServiceAcntNumberValue;
    
    public boolean islblServiceAcntNumberValueVisible() {
    	log.info("lbl Service Account Number Value Status :" + lblServiceAcntNumberValue.isDisplayed());
    	return lblServiceAcntNumberValue.isDisplayed();
   }
    
    
    @FindBy(css = ".previewamount.wearesorry>h4")
    private WebElement lblWeAreSorry;
    
    public boolean islblWeAreSorryVisible() {
    	log.info("lbl We Are Sorry Status :" + lblWeAreSorry.isDisplayed());
    	return lblWeAreSorry.isDisplayed();
   }
    
    
    @FindBy(css = ".para_sorry")
    private WebElement lblSorryDetailMessage;
    
    public boolean islblSorryDetailMessageVisible() {
    	log.info("lbl We Are Sorry Status :" + lblSorryDetailMessage.isDisplayed());
    	return lblSorryDetailMessage.isDisplayed();
   }
      
    @FindBy(css = ".txt_amtlbl")
    private WebElement lblPayAmount;
    
    public boolean islblPayAmountVisible() {
    	log.info("lbl We Are Sorry Status :" + lblPayAmount.isDisplayed());
    	return lblPayAmount.isDisplayed();
   }
    
    @FindBy(css = "#lblAvgBalVal")
    private WebElement lblPayAmountValue;
    
    public boolean islblPayAmountValueVisible() {
    	log.info("lbl Pay Amount Value Status :" + lblPayAmountValue.isDisplayed());
    	return lblPayAmountValue.isDisplayed();
   }
    
    @FindBy(css = ".material-icons.lblPayAmountValueowlvlamt")
    private WebElement btnInputShowLvlPayAmount;
    
    public boolean isbtnInputShowLvlPayAmountVisible() {
    	log.info("btn Input Show Lvl Pay Amount Status :" + btnInputShowLvlPayAmount.isDisplayed());
    	return btnInputShowLvlPayAmount.isDisplayed();
   }
       
    @FindBy(css = "#btnEnroll")
    private WebElement btnInputEnrollInLevelPay;
    
    public boolean isbtnInputEnrollInLevelPayVisible() {
    	log.info("btn Input Enroll In Level Pay Status :" + btnInputEnrollInLevelPay.isDisplayed());
    	return btnInputEnrollInLevelPay.isDisplayed();
   }
       
    @FindBy(css = ".enrolledbtn")
    private WebElement lblEnrolledInLevelPay;
    
    public boolean islblEnrolledInLevelPayVisible() {
    	log.info("lbl Enrolled In Level Pay Status :" + lblEnrolledInLevelPay.isDisplayed());
    	return lblEnrolledInLevelPay.isDisplayed();
   }
    
    @FindBy(css = "#enroll_conf_popup .modal-title")
    private WebElement lblEnrollPopUpTitle;
    
    public boolean islblEnrollPopUpTitleVisible() {
    	log.info("lbl Enrolled In Level Pay Status :" + lblEnrolledInLevelPay.isDisplayed());
    	return lblEnrolledInLevelPay.isDisplayed();
   }
     
    @FindBy(css = "#enroll_conf_popup .modal-body>p:nth-child(1)")
    private WebElement lblEnrollPopupMsgLine1;
    
    public boolean islblEnrollPopupMsgLine1Visible() {
    	log.info("lbl Enroll Popup Msg Line1 Status :" + lblEnrollPopupMsgLine1.isDisplayed());
    	return lblEnrollPopupMsgLine1.isDisplayed();
   }
        
    @FindBy(css = "#enroll_conf_popup .modal-body>p:nth-child(1)>strong")
    private WebElement lblEnrollPopupLvlPayAmountValue;
    
    public boolean islblEnrollPopupLvlPayAmountValueVisible() {
    	log.info("lbl Enroll Popup Lvl Pay Amount Value Status :" + lblEnrollPopupLvlPayAmountValue.isDisplayed());
    	return lblEnrollPopupLvlPayAmountValue.isDisplayed();
   }
    
    
    @FindBy(css = "#enroll_conf_popup .modal-body>p:nth-child(2)")
    private WebElement lblEnrollPopupMsgLine2;
    
    public boolean islblEnrollPopupMsgLine2Visible() {
    	log.info("lbl Enroll Popup Lvl Pay Amount Value Status :" + lblEnrollPopupMsgLine2.isDisplayed());
    	return lblEnrollPopupMsgLine2.isDisplayed();
   }
    
   
    @FindBy(css = "#unenroll_conf_popup .modal-title")
    private WebElement lblUnEnrollPopUpTitle;
    
    public boolean islblUnEnrollPopUpTitleVisible() {
    	log.info("lbl UnEnroll Pop Up Title Status :" + lblUnEnrollPopUpTitle.isDisplayed());
    	return lblUnEnrollPopUpTitle.isDisplayed();
   }
    
    
    @FindBy(css = "#unenroll_conf_popup .modal-body>p:nth-child(1)")
    private WebElement lblUnEnrollPopupMsgLine1;
    
    public boolean islblUnEnrollPopupMsgLine1Visible() {
    	log.info("lbl UnEnroll Popup Msg Line1 Status :" + lblUnEnrollPopupMsgLine1.isDisplayed());
    	return lblUnEnrollPopupMsgLine1.isDisplayed();
   }
    
    
    @FindBy(css = "#unenroll_conf_popup .modal-body>p:nth-child(1)>strong")
    private WebElement lblUnEnrollPopupLvlPayAmountValue;
    
    public boolean islblUnEnrollPopupLvlPayAmountValueVisible() {
    	log.info("lbl UnEnroll Popup Lvl Pay Amount Value Status :" + lblUnEnrollPopupLvlPayAmountValue.isDisplayed());
    	return lblUnEnrollPopupLvlPayAmountValue.isDisplayed();
   }
    
    @FindBy(css = "#unenroll_conf_popup .modal-body>p:nth-child(2)")
    private WebElement lblUnEnrollPopupMsgLine2;
    
    public boolean islblUnEnrollPopupMsgLine2Visible() {
    	log.info("lbl UnEnroll Popup Msg Line2 Status :" + lblUnEnrollPopupMsgLine2.isDisplayed());
    	return lblUnEnrollPopupMsgLine2.isDisplayed();
   }
        
    @FindBy(css = "#chkterm")
    private WebElement chkBoxInputTermsCondition;
    
    public boolean ischkBoxInputTermsConditionVisible() {
    	log.info("chk Box Input Terms Condition Status :" + chkBoxInputTermsCondition.isDisplayed());
    	return chkBoxInputTermsCondition.isDisplayed();
   }
    
    @FindBy(css = "#chktermlabel")
    private WebElement lblTermsAndCondition;
    
    public boolean islblTermsAndConditionVisible() {
    	log.info("lbl Terms And Condition Status :" + lblTermsAndCondition.isDisplayed());
    	return lblTermsAndCondition.isDisplayed();
   }
    
    
    @FindBy(css = "#chktermlabel a")
    private WebElement lnkTermsAndCondition;
    
    public boolean islnkTermsAndConditionVisible() {
    	log.info("lnk Terms And Condition Status :" + lnkTermsAndCondition.isDisplayed());
    	return lnkTermsAndCondition.isDisplayed();
   }
    
    
    @FindBy(css = "#enroll_conf_popup .modal-footer>button:nth-child(1)")
    private WebElement btnEnrollPopupCancel;
    
    public boolean isbtnEnrollPopupCancelVisible() {
    	log.info("btn Enroll Popup Cancel Status :" + btnEnrollPopupCancel.isDisplayed());
    	return btnEnrollPopupCancel.isDisplayed();
   }
       
    @FindBy(css = "#enroll_conf_popup .modal-footer>button:nth-child(2)")
    private WebElement btnEnrollPopupContinue;
    
    public boolean isbtnEnrollPopupContinueVisible() {
    	log.info("btn Enroll Popup Continue Status :" + btnEnrollPopupContinue.isDisplayed());
    	return btnEnrollPopupContinue.isDisplayed();
   }
        
    @FindBy(css = "#unenroll_conf_popup .modal-footer>button:nth-child(1)")
    private WebElement btnUnEnrollPopupCancel;
    
    public boolean isbtnUnEnrollPopupCancelVisible() {
    	log.info("btn Un Enroll Popup Cancel Status :" + btnUnEnrollPopupCancel.isDisplayed());
    	return btnUnEnrollPopupCancel.isDisplayed();
   }
        
    @FindBy(css = "#unenroll_conf_popup .modal-footer>button:nth-child(2)")
    private WebElement btnUnEnrollPopupStopLevelPay;
    
    public boolean isbtnUnEnrollPopupStopLevelPayVisible() {
    	log.info("btn UnEnroll Popup Stop Level Pay Status :" + btnUnEnrollPopupStopLevelPay.isDisplayed());
    	return btnUnEnrollPopupStopLevelPay.isDisplayed();
   }
    

    @FindBy(css = "#trm_condi_popup .modal-title")
    private WebElement lblTrmCondPopupHeading;
    
    public boolean islblTrmCondPopupHeadingVisible() {
    	log.info("lbl Trm Cond Popup Heading  Status :" + lblTrmCondPopupHeading.isDisplayed());
    	return lblTrmCondPopupHeading.isDisplayed();
   }
       
    @FindBy(css = "#trm_condi_popup .close")
    private WebElement btnColseTrmCondPopup;
    
    public boolean isbtnColseTrmCondPopupVisible() {
    	log.info("btn Colse Tr mCondPopup Status :" + btnColseTrmCondPopup.isDisplayed());
    	return btnColseTrmCondPopup.isDisplayed();
   }
    
    
    @FindBy(css = ".term_Cond>span")
    private WebElement lblTrmCondPopupTextLine1;
    
    public boolean islblTrmCondPopupTextLine1Visible() {
    	log.info("lbl Trm Cond Popup Text Line1 Status :" + lblTrmCondPopupTextLine1.isDisplayed());
    	return lblTrmCondPopupTextLine1.isDisplayed();
   }
    
    
    @FindBy(css = ".term_Cond>p")
    private WebElement lblTrmCondPopupTextLine2;
    
    public boolean islblTrmCondPopupTextLine2Visible() {
    	log.info("lbl Term & Condition Popup Text Line2 Status :" + lblTrmCondPopupTextLine2.isDisplayed());
    	return lblTrmCondPopupTextLine2.isDisplayed();
   }
    
    @FindBy(css = ".	mdl-checkbox__box-outline")
    private WebElement chkboxClickHere;
    
    public boolean ischkboxClickHereVisible() {
    	log.info("chk box Click Here Status :" + chkboxClickHere.isDisplayed());
    	return chkboxClickHere.isDisplayed();
   }
    
    
//    @FindBy(css = "#btnEnroll[value=" Enroll"]")
//    private WebElement btnEnroll;
//    
//    public boolean isbtnEnrollVisible() {
//    	log.info("chk box Click Here Status :" + chkboxClickHere.isDisplayed());
//    	return chkboxClickHere.isDisplayed();
//   }
//    
//    
//    @FindBy(css = "#btnEnroll[value=" Unenroll"]")
//    private WebElement btnDisEnroll;
//    
//    public boolean isbtnDisEnrollVisible() {
//    	log.info("chk box Click Here Status :" + btnDisEnroll.isDisplayed());
//    	return btnDisEnroll.isDisplayed();
//   }

    @FindBy(css = ".toast.toast-success")
    private WebElement msgSuccessfulEnroll;
    
    public boolean ismsgSuccessfulEnrollVisible() {
    	log.info("msg Success ful Enroll Status :" + msgSuccessfulEnroll.isDisplayed());
    	return msgSuccessfulEnroll.isDisplayed();
   }
    
    
    @FindBy(css = ".toast.toast-success")
    private WebElement msgSuccessfulDisEnroll;
    
    public boolean isMsgSuccessfulDisEnrollVisible() {
    	log.info("msg Successfull Dis Enroll Status :" + msgSuccessfulDisEnroll.isDisplayed());
    	return msgSuccessfulDisEnroll.isDisplayed();
   }
       
    @FindBy(css = ".billing_lvl_pay")
    private WebElement lblStatic;
    
    public boolean islblStaticVisible() {
    	log.info("lbl Static Status :" + lblStatic.isDisplayed());
    	return lblStatic.isDisplayed();
   }
       
    @FindBy(css = ".billing_lvl_pay a[ data-target= '#lvlplan_info']")
    private WebElement lnkReadMore;
    
    public boolean islnkReadMoreVisible() {
    	log.info("lnk Read More Status :" + lnkReadMore.isDisplayed());
    	return lnkReadMore.isDisplayed();
   }
    
    
    @FindBy(css = "#BillingDialog")
    private WebElement lblHeadingPopup;
    
    public boolean islblHeadingPopupVisible() {
    	log.info("lbl Heading Popup Status :" + lblHeadingPopup.isDisplayed());
    	return lblHeadingPopup.isDisplayed();
   }
       
    @FindBy(css = "#lvlplan_info button[title='Close']")
    private WebElement btnClosePopup;
    
    public boolean isbtnClosePopupVisible() {
    	log.info("btn Close Popup Status :" + btnClosePopup.isDisplayed());
    	return btnClosePopup.isDisplayed();
   }
        
    @FindBy(css = "#BillingDialogDesc>ul")
    private WebElement lblInnerTextPopup;
    
    public boolean islblInnerTextPopupVisible() {
    	log.info("lbl Inner Text Popup Status :" + lblInnerTextPopup.isDisplayed());
    	return lblInnerTextPopup.isDisplayed();
   }
    
    @FindBy(css = "#SAValue")
    private WebElement lblServiceAccountAddr;
    
    public boolean islblServiceAccountAddrVisible() {
    	log.info("lbl Service Account Addr Status :" + lblServiceAccountAddr.isDisplayed());
    	return lblServiceAccountAddr.isDisplayed();
   }
       
    @FindBy(css = ".enrolledbtn")
    private WebElement lblEnrolledInLvlPay;
    
    public boolean islblEnrolledInLvlPayVisible() {
    	log.info("lbl Enrolled In Lvl Pay Status :" + lblEnrolledInLvlPay.isDisplayed());
    	return lblEnrolledInLvlPay.isDisplayed();
   }
    
    @FindBy(css = "#myModal_terms .modal-title")
    private WebElement lblTnCPopupHeading;
    
    public boolean islblTnCPopupHeadingVisible() {
    	log.info("lbl TnC Popup Heading Status :" + lblTnCPopupHeading.isDisplayed());
    	return lblTnCPopupHeading.isDisplayed();
   }
    
    
    @FindBy(css = "#myModal_terms .close")
    private WebElement btnCloseTnC;
    
    public boolean isbtnCloseTnCVisible() {
    	log.info("btn Close TnC Status :" + btnCloseTnC.isDisplayed());
    	return btnCloseTnC.isDisplayed();
   }
    
    
    
}

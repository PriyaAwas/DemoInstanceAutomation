package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BillDashboardPage extends HomePage {
    private static final Logger log = LogManager.getLogger(BillDashboardPage.class);

    public BillDashboardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".icon_billing")
    private WebElement iconBillingSideMenu;
    @FindBy(css = ".nav_left .icon_utility_bill")
    private WebElement iconUtilityBillSideMenu;
    @FindBy(css = "img#IDBannerBilling")
    private WebElement imgBillingBanner;
    @FindBy(css = "[globalize='ML_BILLING_Navigation_BillDashboard']")
    private WebElement lnkUtilityBillSideMenu;
    @FindBy(css = ".recurring_bill.list-group-item [globalize='ML_Billing_RecurringBill_Title']")
    private WebElement lnkAutoPaySideMenu;
    @FindBy(css = ".icon_history.list-group-item [globalize='ML_BILLING_Navigation_BillingHistory']")
    private WebElement lnkHistorySideMenu;

    @FindBy(xpath = "(//*[@id='budgetBill']/a)[1]")
    private WebElement lnkBudgetMyBillSideMenu;
    @FindBy(css = ".icon_dispute_bill.list-group-item [globalize='ML_BILLING_Navigation_ConnectMe']")
    private WebElement lnkBillingQueriesSideMenu;
    public Boolean islnkBudgetMyBillSideMenuVisible() {
        return lnkBudgetMyBillSideMenu.isDisplayed();
    }
   
    @FindBy(css = ".icon_payment_location.list-group-item [globalize='ML_BILLING_Navigation_PaymentLocationsMap']")
    private WebElement lnkPaymentLocationsSideMenu;
    @FindBy(css = ".nav_left .icon_rate_analysis>a")
    private WebElement lnkRateAnalysisSideMenu;
    @FindBy(css = ".icon_label_pay.list-group-item [globalize='ML_LevelPlay_Heading']")
    private WebElement lnkLevelPaySideMenu;
    @FindBy(css = "span#lblprvAmountH")
    private WebElement lblPreviousBalance;
    @FindBy(css = "[globalize='ML_BILLDASHBOARD_Lbl_TotalBilling']")
    private WebElement lblTotalBillThisPeriodHeader;
    @FindBy(css = "span[id$='lblTotalBillthisPdH']")
    private WebElement lblTotalBillThisPeriodValueHeader;
    @FindBy(css = "#paneDueDateHeader i")
    private WebElement lblDueDateHeader;
    @FindBy(css = "span[id*='lblduedateH']")
    private WebElement lblDueDateHeaderValue;
    @FindBy(css ="View Bill")
    private WebElement lnkViewBill;
    @FindBy(css = "a[globalize='ML_Mng_BillingAlert']")
    private WebElement btnSetAlert;
    @FindBy(css = "btnpaypowerTop")
    private WebElement btnPayNowTop;
    @FindBy(css = "#btnpaypower")
    private WebElement btnPayNow;
    @FindBy(css = "#divpaybutton #btnpaypower[value='Make Payment']")
    private WebElement btnRecharge;
    @FindBy(css = "a#LinkButton1")
    private WebElement lnkPaymentExtension;
    @FindBy(css = "a.submit-button.pay_now")
    private WebElement btnPayNowPaypal;

    @FindBy(css = "[id='myModalLabel']")
    private WebElement lblEnterPaymentModal;
    @FindBy(css = "#txtWater")
    private WebElement txtBoxWaterPayment;
    @FindBy(css = "[id='lblWater']")
    private WebElement lblWaterEnterPaymentModal;
    @FindBy(css = "#txtElectric")
    private WebElement txtBoxPowerPayment;
    @FindBy(css = "[id='lblPower']")
    private WebElement lblPowerEnterPaymentModal;
    @FindBy(css = "#txtSolid")
    private WebElement txtBoxSolidWastePayment;
    @FindBy(css = "[id='lblSolidWaste']")
    private WebElement lblSolidWasteEnterPaymentModal;
    @FindBy(css = "#txtGas")
    private WebElement txtBoxGasPayment;
    @FindBy(css = "[id='lblGas']")
    private WebElement lblGasEnterPaymentModal;
    @FindBy(css = "#btnCancel")
    private WebElement btnClear;
    @FindBy(css = "#btnPaymentSubmit")
    private WebElement btnSubmit;
    @FindBy(css = ".all_bill_box h3")
    private WebElement lblBillTypeNames;
    @FindBy(css = "[id='btnclosepopup']")
    private WebElement btnCloseEnterPaymentModal;
    @FindBy(css = "[id='myModalLabel']")
    private WebElement lblRechargeModal;
    @FindBy(css = "[id='btnclosepopup']")
    private WebElement btnCloseRechargeModal;
    @FindBy(css = "[id='txtRechargeAmount']")
    private WebElement txtBoxRechargeAmountRechargeModal;
    @FindBy(css = "[id='lblAmount1']")
    private WebElement lblRechargeAmountRechargeModal;
    @FindBy(css = "[id='ddlPaymentInfo']")
    private WebElement lstBoxSelectPaymentTypeRechargeModal;
    @FindBy(css = "[id='lblSelectPayment']")
    private WebElement lblSelectPaymentTypeRechargeModal;

    @FindBy(css = "[id='ChkTerm']")
    private WebElement chkBoxAcceptRechargeModal;
    @FindBy(css = "[id='txtNameOnCard']")
    private WebElement txtBoxNameOnCardRechargeModal;
    @FindBy(css = "[id='lblNameOnCard']")
    private WebElement lblNameOnCardRechargeModal;
    @FindBy(css = "[id='txtCardNumber']")
    private WebElement txtBoxCardNumberRechargeModal;
    @FindBy(css = "[id='lblCardNumber']")
    private WebElement lblCardNumberRechargeModal;
    @FindBy(css = "[id='txtExpiryDate']")
    private WebElement txtBoxCardExpiryRechargeModal;
    @FindBy(css = "[id='lblExpiryDate']")
    private WebElement lblCardExpiryRechargeModal;
    @FindBy(css = "[id='txtCVV']")
    private WebElement txtBoxCvvCodeRechargeModal;
    @FindBy(css = "[id='lblCVV']")
    private WebElement lblCvvCodeRechargeModal;
    @FindBy(css = "[id*='lblprvAmountH']")
    private WebElement lblLastRechargeAmountValue;
    @FindBy(css = ".billdashboard_first h5")
    private WebElement lblRemainingBalanceHeader;
    @FindBy(css = "span[id*=lblRemainingBal]")
    private WebElement lblRemainingBalanceValueRecharge;
    @FindBy(css = "span#lblprvAmountH")
    private WebElement lblRemainingBalanceValueHeader;
    @FindBy(css = "#btnEnroll")
    private WebElement btnEnrollButton;
    @FindBy(css = "i.NoAmount")
    private WebElement lblNoAmountDueAtThisTime;
    @FindBy(css = "a#gascalculation span")
    private WebElement iconInformationGas;
    @FindBy(xpath = "(//*[@class='leftsummary'][contains(text(),'Current Electricity Charges')])")
    private WebElement lblPowerBill;
    @FindBy(xpath = "(//*[@class='leftsummary'][contains(text(),'Current Water Charges')])")
    private WebElement lblWaterBill;
    @FindBy(xpath = "(//*[@class='leftsummary'][contains(text(),'Current Gas Charges')])")
    private WebElement lblGasBill;
    @FindBy(xpath = "(//*[@class='leftsummary'][contains(text(),'Current Solid Waste Charges')]")
    private WebElement lblSolidWasteBill;
    @FindBy (xpath ="//*[@class='all_bill_box']/h3[contains(text(),'Bill Details')]")
    private WebElement lblBillDetails;
    @FindBy(xpath = "(//*[@class='left-area - tabular'][contains(text(),'Power Usage This Period') or contains(text(),'Power Usage this Period')]")
    private WebElement lblPowerUsageThisPeriod;
    @FindBy (xpath ="//*[@class='leftsummary'][contains(text(),'Current Electricity Charges')])/following-sibling::span")
    private WebElement lblCurrentElectricityCharge;
    @FindBy(xpath = "(//*[@class='left-area - tabular'][contains(text(),'Total Power Charges')])")
    private WebElement lblTotalPowerCharges;


    @FindBy(xpath = "//*[@class='left-area - tabular'][contains(text(),'Water Usage this Period') or contains(text(),'Water Usage This Period')]")
    private WebElement lblWaterUsageThisPeriod;

    @FindBy(xpath = "(//*[@class='left-area - tabular'][contains(text(),'Total Water Charges')])")
    private WebElement lblTotalWaterCharges;

    @FindBy(xpath = "(//*[@class='left-area - tabular'][contains(text(),'Gas Usage This Period') or contains(text(),Gas Usage this Period)])")
    private WebElement lblGasUsageThisPeriod;

    @FindBy(xpath = "//*[@class='left-area - tabular'][contains(text(),'Total Gas Charges')]")
    private WebElement lblTotalGasCharges;

    @FindBy(css = "#billPeriod")
    private WebElement lblBillPeriodValue;

    @FindBy(css = "#ContentPlaceHolder1_ContentPlaceHolderBody_lblContentRemainingBalance")
    private WebElement lblTotalBillThisPeriodValue;

    @FindBy(xpath = "//*[@class='billsummarybody']//li/label[contains(text(),'Current Charges')]/following-sibling::span")
    private WebElement lblCurrentChargesValue;
    @FindBy(xpath = "//*[@class='billsummarybody']//li/label[contains(text(),'Previous Balance')]")
    private WebElement lblPreviousBalanceDue;
    @FindBy(xpath = "//*[@class='billsummarybody']//li/label[contains(text(),'Previous Balance')]/following-sibling::span")
    private WebElement lblPreviousBalanceDueValue;
    @FindBy(xpath = "//*[@class='leftsummary'][contains(text(),'Late Payment/Penalty Charges')]")
    private WebElement lblLatePaymentPenaltyCharges;
    @FindBy(xpath = "//*[@class='leftsummary'][contains(text(),'Late Payment/Penalty Charges')]/following-sibling::span")
    private WebElement lblLatePaymentPenaltyChargesValue;
    @FindBy(css = ".TotalBillSummary label")

    private WebElement lblTotalAmountDue;
    @FindBy(css = ".TotalBillSummary .rightsummary")
    private WebElement lblTotalAmountDueValue;

    @FindBy(xpath = "(//*[@class='leftsummary'][contains(text(),'Payments Received')])/following-sibling::span")
    private WebElement lblAmountPaidThisPeriodValue;

    @FindBy(xpath = "//*[@class='TotalBillSummary']//label[contains(text(),'Total Amount Due')]/following-sibling::span")
    private WebElement lblRemainingBalanceDueValue;
    @FindBy(xpath = "//*[@class='left-area - tabular'][contains(text(),'Due Date')]")
    private WebElement lblDueDate;
    @FindBy(css = "#ContentPlaceHolder1_ContentPlaceHolderBody_lblContentRemainingBalance")
    private WebElement lblDueDateValue;
    @FindBy(css = "#div_disclaimer span")
    private WebElement lblDisclaimer;
    @FindBy(css = "#myAnchor")
    private WebElement lnkViewBillPdf;

    @FindBy(css = ".billdashboard_first h5")
    private WebElement lblCurrentBalance;
    @FindBy(css = "#lblprvAmountH")
    private WebElement valueCurrentBalance;
    @FindBy(css = ".cancel-button.enrollbtn_brdr.UnEnroll")
    private WebElement btnEnrollUnenrollAutopay;

    @FindBy(css = ".billsummaryhead h5")
    private WebElement lblBillSummary;
    @FindBy(css = ".in .modal-body  .full_width_input_sec:first-child label")
    private WebElement lblDate;

    @FindBy(css = ".Subject.input_effect")
    private WebElement txtBoxSubject;

    @FindBy(css = ".Comment.textarea_effect")
    private WebElement txtBoxComment;
    @FindBy(css = ".in .modal-body  .full_width_input_sec:nth-child(2) label")
    private WebElement lblAddAttachments;
    @FindBy(xpath = "//span[text()='Choose File']")
    private WebElement btnChooseFile;
    @FindBy(css = "[id='lblFileAllowExtension']  b")
    private WebElement lblAllowedFileTypes;
    @FindBy(css = "[id='lblFileAllowExtension']  i")
    private WebElement lblTxtAllowedFileTypes;
    @FindBy(css = "[globalize='ML_ContactUs_Submit']")
    private WebElement btnSubmitBillingQueriesModal;

    @FindBy(css = "[globalize='ML_ContactUs_Next']")
    private WebElement btnNextBillingQueriesModal;

    @FindBy(css = "#BillQueryPopup h2.modal-title-changepwd")
    private WebElement lblBillingQueriesModal;
    @FindBy(xpath = "(//span[contains(., 'with maximum size of 5 MB')])[1]")
    private WebElement lblFileAllowExtension;

    @FindBy(xpath = "(//button[text()='OK'])[1]")
    private WebElement btnCloseToastMessage;

    @FindBy(css = ".MultipleFileUpload")
    private WebElement btnChoosFileBillingQueries;
    @FindBy(css = "[globalize='ML_ContactUs_Save']")
    private WebElement btnSaveBillingQueriesModal;
}

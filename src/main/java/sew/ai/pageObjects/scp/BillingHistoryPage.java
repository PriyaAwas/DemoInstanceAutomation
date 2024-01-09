package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BillingHistoryPage extends HomePage {
    private static final Logger log = LogManager.getLogger(BillingHistoryPage.class);

    public BillingHistoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".icon_bell_icon")
    private WebElement iconSetBillAlerts;
    @FindBy(css = "#bill_notify>span")
    private WebElement btnSetBillAlerts;
    @FindBy(css = "#lnkExporttoExcel")
    private WebElement iconExportToExcel;

    @FindBy(css = "#billpaybillingDetails > a")
    private WebElement lnkBilling;
    @FindBy(css = "#billpaymentDetails>a")
    private WebElement lnkPayments;
    @FindBy(css = "label.lbl_txt_clndr:first-child")
    private WebElement lblFromDate;
    @FindBy(css = "input#from")
    private WebElement inputFromDate;
    @FindBy(css = "label.lbl_txt_clndr:nth-child(3)")
    private WebElement lblToDate;
    @FindBy(css = "input#to")
    private WebElement inputToDate;
    @FindBy(css = "div.fltr_btn_history>a")
    private WebElement btnFilter;

    @FindBy(css = "#menu1 .hdrBillDate")
    private WebElement lblBillDateColumn;
    @FindBy(css = "#menu1 .hdrBillAmount")
    private WebElement lblBillAmountColumn;
    @FindBy(css = "#menu1 .hdrViewBill")
    private WebElement lblViewBillColumn;
    @FindBy(xpath = "//table[@id='tblBillingHistory']/tbody/tr/td[1]")
    private WebElement lblBillDateValue;
    @FindBy(css = "tbody>tr[role='row']>td:nth-child(2)")
    private WebElement lblBillAmountValue;
    @FindBy(css = "tbody>tr[role='row']>td:nth-child(3)")
    private WebElement lnkViewBillPdf;
    @FindBy(css = "#menu2 .hdrBillDate")
    private WebElement lblTransactionDateColumn;
    @FindBy(css = "#menu2 .hdrBillAmount")
    private WebElement lblTransactionAmountColumn;

    @FindBy(css = ".dataTables_info")
    private WebElement lblDataTableInfo;
    @FindBy(css = "a[id$='tblBillingHistory_previous']")
    private WebElement btnPrevPagination;
    @FindBy(css = "a[id$='tblPaymentHistory_previous']")
    private WebElement btnPrevPaginationPayments;
    @FindBy(css = "span>a.paginate_button")
    private WebElement btnPagePagination;
    @FindBy(css = "a[id$='tblBillingHistory_next']")
    private WebElement btnNextPagination;
    @FindBy(css = "a[id$='tblPaymentHistory_next']")
    private WebElement btnNextPaginationPayments;
    @FindBy(css = "a[id$='tblPaymentHistory_previous']")
    private WebElement btnPreviousPaginationPayments;

    @FindBy(css = ".datepicker-calendar.bootstrap3.default below")
    private WebElement divDatePicker;

    @FindBy(css = ".ui-datepicker-next")
    private WebElement btnNextDatePicker;
    @FindBy(css = ".ui-datepicker-month")
    private WebElement selectMonthDropdown;
    @FindBy(css = "#datepicker-month-from")
    private WebElement selectYearDropdown;
    @FindBy(css = "#datepicker-month-to")
    private WebElement selectToYearDropdown;
    @FindBy(css = ".day.selectable.curDay")
    private WebElement lblTodayDatePicker;
    @FindBy(xpath = "//*[@class ='ui-state-default' and text()='%s']/..")
    private WebElement btnGivenDate;
    @FindBy(css = "#divWithNoData .nodata_newstyle")
    private WebElement lblNoBillAvailable;
    @FindBy(css = ".dataTables_empty")
    private WebElement textNoBillAvaila;
    @FindBy(css = "#divWithNoPaymentData .nodata_newstyle")
    private WebElement lblNoPaymentsAvailable;
    @FindBy(css = ".dataTables_empty")
    private WebElement lblNoDataFoundFilterResult;
    @FindBy(css = "#BillDate0 > td:nth-child(5)")
    private WebElement btnexpandI;
    @FindBy(css = "#tblBillingHistory > tbody > tr:nth-child(2) > td > div > div > table > tbody > tr:nth-child(1) > td:nth-child(1)")
    private WebElement lblPreviousBalance;
    @FindBy(css = "#tblBillingHistory > tbody > tr:nth-child(2) > td > div > div > table > tbody > tr:nth-child(2) > td:nth-child(1)")
    private WebElement lblPaymentReceived;
    @FindBy(css = "#tblBillingHistory > tbody > tr:nth-child(2) > td > div > div > table > tbody > tr:nth-child(3) > td:nth-child(1)")
    private WebElement lblCurrentCharges;
    @FindBy(css = "#tblBillingHistory > tbody > tr:nth-child(2) > td > div > div > table > tbody > tr:nth-child(4) > td:nth-child(1) > a")
    private WebElement btnDetailedBillPdf;

    @FindBy(xpath = "//table[@id='tblPaymentHistory']/tbody/tr/td[1]")
    private WebElement lblTransactionDate;
    @FindBy(css = ".w2ui-col-header")
    private WebElement lblFirstBillRow;
    @FindBy(css = "li#billpaybillingDetails>a")
    private WebElement tabBillStateMents;
    @FindBy(css = "li#billpaymentDetails>a")
    private WebElement tabBillPayments;
    @FindBy(css = "#tblBillingHistory .hdrBillDate")
    private WebElement clmnBillDate;
    @FindBy(css = "#tblBillingHistory .hdrBillAmount")
    private WebElement clmnBillAmountDollar;
    @FindBy(css = "#tblBillingHistory .hdrViewBill")
    private WebElement clmnViewBill;
    @FindBy(css = "#tblPaymentHistory .hdrBillDate")
    private WebElement clmnTransactionDate;
    @FindBy(css = "#tblPaymentHistory .hdrBillAmount")
    private WebElement clmnTransactionAmount;
    @FindBy(css = "img[src='images/view_all_pdf.png']")
    private WebElement lnkPdfIcon;
    @FindBy(xpath = "//*[@id='tblBillingHistory']/tbody[1]/tr/td[2]")
    private WebElement lblFirstBillAmountRow;
    @FindBy(xpath = "//*[@id='tblPaymentHistory']/tbody[1]/tr/td[5]")
    private WebElement lblFirstBillTransactionRow;

    @FindBy(xpath = "//table[@id = 'tblBillingHistory']/tbody/tr/td[1]")
    private WebElement lblTotalStatementDatesBilling;
    @FindBy(xpath = "//table[@id = 'tblBillingHistory']/tbody/tr/td[2]")
    private WebElement lblTotalamountDueBilling;
    @FindBy(xpath = "//table[@id = 'tblBillingHistory']/tbody/tr/td[3]")
    private WebElement lblTotaldueDateBilling;
    @FindBy(xpath = "//table[@id = 'tblBillingHistory']/tbody/tr/td[5]")
    private WebElement lblExpandIconBilling;
    @FindBy(xpath = "//table[@id = 'tblPaymentHistory']/tbody/tr/td[1]")
    private WebElement lblDatePayment;
    @FindBy(xpath = "//table[@id = 'tblPaymentHistory']/tbody/tr/td[2]")
    private WebElement lblPaymentTypePayment;
    @FindBy(xpath = "//table[@id = 'tblPaymentHistory']/tbody/tr/td[3]")
    private WebElement lblChannelPayment;
    @FindBy(xpath = "//table[@id = 'tblPaymentHistory']/tbody/tr/td[4]")
    private WebElement lblStatusPayment;
    @FindBy(xpath = "//table[@id = 'tblPaymentHistory']/tbody/tr/td[5]")
    private WebElement lblAmountPayment;
    @FindBy(xpath = "//table[@id = 'jqxgridbill_Grid']/tbody/tr/td[1]")
    private WebElement lstLblBillingBillD;
    @FindBy(css = "div#jqxgridbill_Grid_info")
    private WebElement lblTotalEnteriesBill;

    @FindBy(xpath = "//table[@id = 'jqxgridbill_Grid']/tbody/tr/td[2]")
    private WebElement lstLblBillingBillAmount;
    @FindBy(xpath = "//table[@id = 'jqxgridbill_Grid']/tbody/tr/td[3]")
    private WebElement lstlnkBillingViewBillPdf;

    // PrePaid Billing History Page Locator from old Framework SCPRegressionTest7.0

    @FindBy(css = "#billpaybillingDetailsPre > a")
    private WebElement lnkPreBilling;
    @FindBy(css = "#lnkExporttoExcel")
    private WebElement btnPreExportToExcel;
    @FindBy(css = "#billpaymentDetailsPre>a")
    private WebElement lnkPreRecharge;
    @FindBy(css = "#menu3 .hdrBillDate")
    private WebElement lblPreBillDateColumn;
    @FindBy(css = "#menu3 .hdrBillAmount")
    private WebElement lblPreBillAmountColumn;
    @FindBy(css = "#menu3 .hdrCurrentCharges")
    private WebElement lblPreCurrentChargesColumn;
    @FindBy(css = "#menu3 .hdrConsumption")
    private WebElement lblPreConsumptionColumn;
    @FindBy(css = "div.fltr_btn_history>a")
    private WebElement btnPreFilter;
    @FindBy(css = "#menu4 .hdrTransactionDate")
    private WebElement lblPreRecTransactionDateColumn;
    @FindBy(css = "#menu4 .hdrTransactionId")
    private WebElement lblPreRecTransactionIdColumn;
    @FindBy(css = "#menu4 .hdrRechargeAmount")
    private WebElement lblPreRechargeAmountColumn;
    @FindBy(css = "#menu4 .hdrPaymentMethod")
    private WebElement lblPreRecPaymentMethodColumn;
    @FindBy(xpath = "//table[@id='tblBillingHistoryPre']/tbody/tr/td[1]")
    private WebElement lblPreBillDateValue;
    @FindBy(xpath = "//table[@id='tblPaymentHistorypre']/tbody/tr/td[1]")
    private WebElement lblPreRechargeDateValue;
    @FindBy(css = "a[id$='tblBillingHistoryPre_next']")
    private WebElement btnPreBillingNextPagination;

    @FindBy(xpath = "//table[@id = 'tblBillingHistoryPre']/tbody/tr/td[1]")
    private WebElement lblPreTotalStatementDatesBilling;
    @FindBy(xpath = "//table[@id = 'tblBillingHistoryPre']/tbody/tr/td[2]")
    private WebElement lblPreBillAmountBilling;
    @FindBy(xpath = "//table[@id = 'tblBillingHistoryPre']/tbody/tr/td[3]")
    private WebElement lblPreCurrentChargesBilling;
    @FindBy(xpath = "//table[@id = 'tblBillingHistoryPre']/tbody/tr/td[4]")
    private WebElement lblPreConsumptionBilling;
    @FindBy(css = "#tblBillingHistoryPre_paginate > span > a.paginate_button.current")
    private WebElement btnPreFirstCountBilling;
    @FindBy(xpath = "//table[@id = 'tblBillingHistoryPre']/tbody/tr/td[6]")
    private WebElement lblPreExpandIconBilling;

    @FindBy (css="a[id$='tblPaymentHistorypre_next']")
    private WebElement btnPreRechargeNextPagination;
    @FindBy(xpath = "//table[@id = 'tblPaymentHistorypre']/tbody/tr/td[2]")
    private WebElement lblPreTransactionDateRecharge;
    @FindBy(xpath = "//table[@id = 'tblPaymentHistorypre']/tbody/tr/td[1]")
    private WebElement lblPreTransactionIdRecharge;
    @FindBy(xpath = "//table[@id = 'tblPaymentHistorypre']/tbody/tr/td[3]")
    private WebElement lblPreRechargeAmount;
    @FindBy(xpath = "//table[@id = 'tblPaymentHistorypre']/tbody/tr/td[4]")
    private WebElement lblPrePaymentMethodRecharge;
}

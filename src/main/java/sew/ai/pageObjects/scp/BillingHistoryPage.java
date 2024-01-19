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

public class BillingHistoryPage extends HomePage {
	private static final Logger log = LogManager.getLogger(BillingHistoryPage.class);

	public BillingHistoryPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean isBillingHistoryPage(String url, String title) {
		boolean isBillingHistoryPage = false;
		log.info("Checking that the current page is Billing History Page.");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isBillingHistoryPage = true;
		log.info("The current page is My Profile {}: " + isBillingHistoryPage);
		return isBillingHistoryPage;
	}

	// Headers

	@FindBy(css = "#billpaybillingDetails > a")
	private WebElement lnkBilling;

	public boolean isBillStatementLnkVisible() {
		return isElementVisible(lnkBilling);
	}

	public String getBillingLnkLabel() {
		String label = getText(lnkBilling);
		return label;
	}

	@FindBy(css = "#billpaymentDetails>a")
	private WebElement lnkPayments;

	public boolean isPaymentLnkVisible() {
		return isElementVisible(lnkPayments);
	}

	public String getLnkPaymentLabel() {
		String label = getText(lnkPayments);
		return label;
	}

	public void clickPaymentLnk() {
		clickWithJSExecutor(lnkPayments);
	}

	@FindBy(css = "input#from")
	private WebElement inputFromDate;

	public boolean isFromDateVisible() {
		return isElementVisible(inputFromDate);
	}

	public String getFromDateInputLabel() {
		String label = getText(inputFromDate);
		return label;
	}

	public void clickInputFromDate() {
		click(inputFromDate);
	}

	public String getFromDateAttribute() {
		String attribute = getAttribute(inputFromDate, "placeholder");
		return attribute;
	}

	@FindBy(css = "input#to")
	private WebElement inputToDate;

	public boolean isToDateVisible() {
		return isElementVisible(inputToDate);
	}

	public String getToDateLabel() {
		String label = getText(inputToDate);
		return label;
	}

	public String getToDateAttribute() {
		String attribute = getAttribute(inputToDate, "placeholder");
		return attribute;
	}

	public void clickInputToDate() {
		click(inputToDate);
	}

	@FindBy(css = "div.fltr_btn_history>a")
	private WebElement btnFilter;

	public boolean isFliterBtnVisible() {
		return isElementVisible(btnFilter);
	}

	public String getFilterBtnLabel() {
		String label = getText(btnFilter);
		return label;
	}

	public void clickFilterBtn() {
		click(btnFilter);
	}

	// No Bill Available Label

	@FindBy(css = "#divWithNoData .nodata_newstyle")
	private WebElement lblNoBillAvailable1;

	public String getNoBillAvailLabel() {
		String label = getText(lblNoBillAvailable1);
		return label;
	}

	
	@FindBy(css = ".dataTables_empty")
	private WebElement textNoBillAvaila;

	public String getTextNoBillAvailLabel() {
		String label = getText(textNoBillAvaila);
		return label;
	}
	public boolean isNoBillAvaiTxtVisible() {
		boolean status=false;
		if(isElementVisible(textNoBillAvaila)) {
			status=true;
		}
		return status;
	}
	


	@FindBys(@FindBy(css = ".dataTables_empty"))
	private List<WebElement> lblNoBillAvailable;

	public int getLblNoBillAvailableSize() {
		int size = lblNoBillAvailable.size();
		return size;
	}

	public boolean isLblNoBillAvailableDisplay() {
		return isElementVisible(lblNoBillAvailable.get(0));
	}

	public String getLblNoBillAvailableLabel() {
		String label = getText(lblNoBillAvailable.get(0));
		return label;
	}

	@FindBy(css = "#divWithNoPaymentData .nodata_newstyle")
	private WebElement lblNoPaymentsAvailable;
	
	@FindBys(@FindBy(css="#tblBillingHistory td"))
	private List<WebElement> noBilAvailbleEle;
	
	
	public boolean isNoBillAvailbleVisible(){
		boolean status=true;
		if(noBilAvailbleEle.size()>1) {
			status=false;
		}
		return status;
	}

//Billing

	@FindBy(css = "#menu1 .hdrBillDate")
	private WebElement lnkBillStatementDate;

	public boolean islnkBillStatementDateVisible() {
		return isElementVisible(lnkBillStatementDate);
	}

	public String getlnkBillStatementDateLabel() {
		String label = getText(lnkBillStatementDate);
		return label;
	}

	public String getlnkBillStatementDateLnkAttribute() {
		String attribute = getAttribute(lnkBillStatementDate, "class");
		return attribute;
	}

	public void clickBillStatementDateLnk() {
		click(lnkBillStatementDate);
	}

	@FindBy(css = "#menu1 .hdrBillAmountDue")
	private WebElement lnkAmountDue;

	public boolean isBillAmountDueLnkVisible() {
		return isElementVisible(lnkAmountDue);
	}

	public String getBillAmountDueLnkLabel() {
		String label = getText(lnkAmountDue);
		return label;
	}

	@FindBy(css = ".hdrBillDueDate")
	private WebElement isDueDateLnk;

	public boolean isBillDueDateLnkVisible() {
		return isElementVisible(isDueDateLnk);
	}
	public String getBillDueDateLnkLabel() {
		String label = getText(isDueDateLnk);
		return label;
	}

	@FindBy(css = "#menu1 .hdrViewBill")
	private WebElement lblViewBillColumn;

	public boolean isBillColumnViewVisible() {
		return isElementVisible(lblViewBillColumn);
	}

	public String getViewBillColumnLabel() {
		String label = getText(lblViewBillColumn);
		return label;
	}

	@FindBy(css = "#tblBillingHistory .hdrBillDate")
	private WebElement clmnBillDate;
	@FindBy(css = "#tblBillingHistory .hdrBillAmount")
	private WebElement clmnBillAmountDollar;
	@FindBy(css = "#tblBillingHistory .hdrViewBill")
	private WebElement clmnViewBill;

	// Billing Cloumn

	@FindBys(@FindBy(xpath = "//table[@id='tblBillingHistory']/tbody/tr/td[1]"))
	private List<WebElement> lblBillDateValue;
	
	public List<WebElement> getBillStatDateElement(){
		return lblBillDateValue;
	}

	public String getBillStatDateFirstColumnLabel() {
		String label = getText(getBillStatDateElement().get(0));
		return label;
	}
	public String getBillStatDateColumnLabel(WebElement wb) {
		String label = getText(wb);
		return label;
	}
	
	@FindBys(@FindBy(xpath = "//table[@id = 'tblBillingHistory']/tbody/tr/td[1]"))
	private List<WebElement> lblTotalStatementDatesBilling;

	public List<WebElement> getlblTotalStatementDatesBillingElement() {
		return lblTotalStatementDatesBilling;
	}

	@FindBys(@FindBy(css = "#tblPaymentHistory tbody>tr>td:nth-child(1)"))
	private List<WebElement> LblBillDateValue;

	public List<WebElement> getLblBillDateValueElement() {
		return LblBillDateValue;
	}
//tbody>tr[role='row']>td:nth-child(2)
	@FindBys(@FindBy(css = "tbody>tr>td:nth-child(2)"))
	private List<WebElement> lblBillAmountValue;

	public List<WebElement> getBillAmountLblElement() {
		return lblBillAmountValue;
	}
	public String getBillAmountLabel(WebElement wb) {
		String label=getText(wb);
		return label;
	}

	public boolean isBillAmountDueColumnEleVisible(WebElement element) {
		return isElementVisible(element);
	}

	public String getBillAmountDueColumnEleLabel(WebElement element) {
		String label = getText(element);
		return label;
	}
	
	@FindBys(@FindBy(xpath = "//table[@id = 'tblBillingHistory']/tbody/tr/td[2]"))
	private List<WebElement> lblTotalamountDueBilling;

	public List<WebElement> getlblTotalamountDueBillingElement() {
		return lblTotalamountDueBilling;
	}
	public String getBillAmountDueFirstElement() {
		String label=getText(getlblTotalamountDueBillingElement().get(0));
		return label;
	}

	@FindBy(css = "tbody>tr[role='row']>td:nth-child(3)")
	private WebElement lnkDueDateColumnPdf;

	public boolean isBilDueDateColumnVisible() {
		return isElementVisible(lnkDueDateColumnPdf);
	}

	

	

	@FindBys(@FindBy(xpath = "//table[@id = 'tblBillingHistory']/tbody/tr/td[3]"))
	private List<WebElement> lblTotaldueDateBilling;

	public List<WebElement> getlblTotaldueDateBillingElement() {
		return lblTotaldueDateBilling;
	}

	@FindBys(@FindBy(xpath = "//table[@id = 'tblBillingHistory']/tbody/tr/td[5]"))
	private List<WebElement> lblExpandIconBilling;

	public List<WebElement> getlblExpandIconBillingElement() {
		return lblExpandIconBilling;
	}

	// inside Arrow lnk

	@FindBy(css = "#BillDate0 > td:nth-child(5)")
	private WebElement btnexpandI;

	@FindBys(@FindBy(css = ".arrowico"))
	private List<WebElement> viewBillPdfArrowLnk;

	public List<WebElement> viewBillPdfArrowLnk() {
		return viewBillPdfArrowLnk;
	}
	public void clickViewBillPdfFirstArrowLnk() {
		click(viewBillPdfArrowLnk.get(0));
	}
	public boolean isViewBillPdfArrowLnkVisible(WebElement element) {
		return isElementVisible(element);
	}

	public boolean isViewBillPdfFirstArrowLnkVisible() {

		return isElementVisible(viewBillPdfArrowLnk().get(0));
	}

	public void clickViewBillPdfArrowLnk(WebElement element) {
		click(element);
	}

	

	@FindBy(css = ".detailedPDF")
	private WebElement viewBillPdfLnk;

	public boolean isViewBillPdfLnkVisible() {
		return isElementVisible(viewBillPdfLnk);
	}

	public void clickViewBillPdfLnk() {
		click(viewBillPdfLnk);
	}

	@FindBy(css = ".nestedcontainer tbody tr:nth-child(1) td")
	private WebElement txtPreviousBal;

	public boolean isPrevBalTxtVisible() {
		return isElementVisible(txtPreviousBal);
	}

	public String getPrevBalLabel() {
		String label = getText(txtPreviousBal);
		return label;
	}

	@FindBy(css = ".nestedcontainer tbody tr:nth-child(1) td:nth-child(2)")
	private WebElement previousBalBillAmount;

	public boolean isPrevBalBillAmountTxtVisible() {
		return isElementVisible(previousBalBillAmount);
	}

	public String getPrevBalBillAmountLabel() {
		String label = getText(previousBalBillAmount);
		return label;
	}

	@FindBys(@FindBy(css = ".nestedcontainer tbody tr:nth-child(2) td"))
	private List<WebElement> txtCurrentCharge;

	public boolean isCurrentChargeTxtVisible() {
		return isElementVisible(txtCurrentCharge.get(0));
	}

	public String getCurrentChargeLabel() {
		String label = getText(txtCurrentCharge.get(0));
		return label;
	}

	public boolean isCurrentChargeAmountVisible() {
		return isElementVisible(txtCurrentCharge.get(1));
	}

	public String getCurrentChargeAmountLabel() {
		String label = getText(txtCurrentCharge.get(1));
		return label;
	}

	@FindBys(@FindBy(css = ".nestedcontainer tbody tr:nth-child(3) td"))
	private List<WebElement> totalAmountDue;

	public String getTotalAmountDueLabel() {
		String label = getText(totalAmountDue.get(1));
		return label;
	}

	public char getTotalAmountDueCurrenySymbol() {
		String totalAmount = getText(totalAmountDue.get(1));
		char[] ch = totalAmount.toCharArray();
		char ch1 = ch[0];
		return ch1;
	}

	public boolean isTotalAMountDueVisible() {
		return isElementVisible(totalAmountDue.get(1));
	}

	@FindBy(css = "#tblBillingHistory > tbody > tr:nth-child(2) > td > div > div > table > tbody > tr:nth-child(1) > td:nth-child(1)")
	private WebElement lblPreviousBalance;
	@FindBy(css = "#tblBillingHistory > tbody > tr:nth-child(2) > td > div > div > table > tbody > tr:nth-child(2) > td:nth-child(1)")
	private WebElement lblPaymentReceived;
	@FindBy(css = "#tblBillingHistory > tbody > tr:nth-child(2) > td > div > div > table > tbody > tr:nth-child(3) > td:nth-child(1)")
	private WebElement lblCurrentCharges;
	@FindBy(css = "#tblBillingHistory > tbody > tr:nth-child(2) > td > div > div > table > tbody > tr:nth-child(4) > td:nth-child(1) > a")
	private WebElement btnDetailedBillPdf;

	// Footer and Pagination on Billing Tab

	@FindBy(css = ".dataTables_info")
	private WebElement lblDataTableInfo;

	public boolean isDataTableInfoVisible() {
		return isElementVisible(lblDataTableInfo);
	}
	public String getDataTableInfoLabel() {
		String label = getText(lblDataTableInfo);
		return label;
	}
	@FindBy(css = "#tblBillingHistory_previous")
	private WebElement btnPrevious;

	public boolean isPreviousBtnVisible() {
		return isElementVisible(btnPrevious);
	}

	@FindBys(@FindBy(css = "span>a.paginate_button"))
	private List<WebElement> btnPagePagination;

	public List<WebElement> getpagePaginationElement() {
		return btnPagePagination;
	}
    public int billingHistoryPageCount() {
    	int noOfpage=getpagePaginationElement().size();
    	return noOfpage;
    }
	public boolean isPaginationBtnVisible(WebElement element) {
		return isElementVisible(element);
	}

	@FindBys(@FindBy(css=".paginate_button"))
	private List<WebElement> paymentHistoryPagination;
	public List<WebElement> getPaymentHistoryPaginationElement(){
		return paymentHistoryPagination;
	}
	
	@FindBy(css = "a[id$='tblBillingHistory_previous']")
	private WebElement btnPrevPagination;

	public String getPrevPaginationLabel() {
		String label = getText(btnPrevPagination);
		return label;
	}

	public String getPrevPaginationAttribute() {
		String att = getAttribute(btnPrevPagination, "class");
		return att;
	}
	@FindBys(@FindBy(css ="#tblPaymentHistory_info+tbody tr"))
	private List<WebElement> paymentPageList;
	
	public List<WebElement> getPaymentHistoryEntryElement(){
		return paymentPageList;
	}
	

	@FindBy(css = "#tblBillingHistory_next")
	private WebElement btnNextPagination;

	public boolean isNextPaginationBtnVisible() {
		return isElementVisible(btnNextPagination);
	}

	public String getNextPaginationLabel() {
		String label = getText(btnNextPagination);
		return label;
	}

	public void clickNextPaginationBtn() {
		click(btnNextPagination);
	}

	public String getNextPaginationAttribute() {
		String att = getAttribute(btnNextPagination, "class");
		return att;
	}
	
	@FindBy(css = "#tblPaymentHistory_next")
	private WebElement btnNextPaginationPayments;

	public String getNextPaginationPaymentAttribute() {
		String attribute = getAttribute(btnNextPaginationPayments, "class");
		return attribute;
	}

	public boolean isNextPaginationPaymentBtnVisible() {
		return isElementVisible(btnNextPaginationPayments);
	}

	@FindBy(css = "#lnkExporttoExcel")
	private WebElement btnPreExportToExcel;

	public String getExportToExcelLabel() {
		String label = getText(btnPreExportToExcel);
		return label;
	}

	public void clickExportToExcelLnk() {
		click(btnPreExportToExcel);
	}

	public boolean isExportToExcelLnkVisible() {
		return isElementVisible(btnPreExportToExcel);
	}

//Payment Lnk tab

	@FindBy(css = "#menu2 .hdrBillDate")
	private WebElement lblTransactionDateColumn;

	public boolean isTransactionDateColumVisible() {
		return isElementVisible(lblTransactionDateColumn);
	}

	public void clickTransactionDateColum() {
		click(lblTransactionDateColumn);
	}

	public String getTransxctionDateColumnLabel() {
		String label = getText(lblTransactionDateColumn);
		return label;
	}

	public String getTransxctionDateColumnAttribute() {
		String attribute = getAttribute(lblTransactionDateColumn, "class");
		return attribute;
	}

	@FindBy(css = "#menu2 .hdrBillAmount")
	private WebElement lblTransactionAmountColumn;

	public boolean isTransactionAmountColumVisible() {
		return isElementVisible(lblTransactionAmountColumn);
	}

	public String getTransxctionAmountColumnLabel() {
		String label = getText(lblTransactionAmountColumn);
		return label;
	}



	@FindBy(css = "#tblPaymentHistory .hdrBillDate")
	private WebElement PaymentTransactionDate;

	public void clickTransactionDateOnPayment() {
		click(PaymentTransactionDate);
		log.info("Transaction Date column is clicked Successfully.");
	}

	public String getTransactionDateTxtLabel() {
		String label = getText(PaymentTransactionDate);
		log.info("fetching the label of Transaction Date cloumn Txt");
		return label;
	}

	@FindBy(css = "#tblPaymentHistory .hdrPaymentType")
	private WebElement clmnPaymentPaymentType;

	public void clickPaymentTypeOnPayment() {
		click(clmnPaymentPaymentType);
		log.info("PaymentType column is clicked Successfully.");
	}

	public String getPaymentTypeTxtLabel() {
		String label = getText(clmnPaymentPaymentType);
		log.info("fetching the label of PaymentType cloumn Txt");
		return label;
	}

	@FindBy(css = "#tblPaymentHistory .hdrChannel")
	private WebElement clmnPaymentChannel;

	public void clickPaymentChannelOnPayment() {
		click(clmnPaymentChannel);
		log.info("PaymentChannel column is clicked Successfully.");
	}

	public String getPaymentChannelTxtLabel() {
		String label = getText(clmnPaymentChannel);
		log.info("fetching the label of PaymentChannel cloumn Txt");
		return label;
	}

	@FindBy(css = "#tblPaymentHistory .hdrStatus")
	private WebElement clmnPaymentStatus;

	public void clickPaymentStatusOnPayment() {
		click(clmnPaymentStatus);
		log.info("PaymentStatus column is clicked Successfully.");
	}

	public String getPaymentStatusTxtLabel() {
		String label = getText(clmnPaymentStatus);
		log.info("fetching the label of PaymentStatus cloumn Txt");
		return label;
	}

	@FindBy(css = "#tblPaymentHistory .hdrBillAmount")
	private WebElement clmnPaymentTransactionAmount;

	public void clickAmountOnPayment() {
		click(clmnPaymentTransactionAmount);
		log.info("TransactionAmount column is clicked Successfully.");
	}

	public String getPaymentAmountTxtLabel() {
		String label = getText(clmnPaymentTransactionAmount);
		log.info("fetching the label of TransactionAmount cloumn Txt");
		return label;
	}

	// Payment History Tab

	@FindBys(@FindBy(xpath = "//table[@id = 'tblPaymentHistory']/tbody/tr/td[1]"))
	private List<WebElement> lblDatePayment;

	public List<WebElement> getlblDatePaymentElement() {
		return lblDatePayment;
	}

	@FindBys(@FindBy(xpath = "//table[@id = 'tblPaymentHistory']/tbody/tr/td[2]"))
	private List<WebElement> lblPaymentTypePayment;

	public List<WebElement> getlblPaymentTypePaymentElement() {
		return lblPaymentTypePayment;
	}

	@FindBy(xpath = "//table[@id = 'tblPaymentHistory']/tbody/tr/td[3]")
	private List<WebElement> lblChannelPayment;

	public List<WebElement> getlblChannelPaymentElemtnt() {
		return lblChannelPayment;
	}

	@FindBys(@FindBy(xpath = "//table[@id = 'tblPaymentHistory']/tbody/tr/td[4]"))
	private List<WebElement> lblStatusPayment;

	public List<WebElement> getlblStatusPaymentElement() {
		return lblStatusPayment;
	}

	@FindBys(@FindBy(xpath = "//table[@id = 'tblPaymentHistory']/tbody/tr/td[5]"))
	private List<WebElement> lblAmountPayment;

	public List<WebElement> getlblAmountPaymentElement() {
		return lblAmountPayment;
	}

	// Footer and pagination on Payment Tab.

	@FindBy(css = "a[id$='tblPaymentHistory_previous']")
	private WebElement btnPrevPaginationPayments;

	@FindBy(css = ".dataTables_paginate #tblPaymentHistory_next")
	private WebElement btnNextPaginationOnPaymentPage;

	public String getNextPaginationPaymentOnPaymentAttribute() {
		String attribute = getAttribute(btnNextPaginationOnPaymentPage, "class");
		return attribute;
	}

	public boolean isNextPaginationPaymentBtnOnPaymentVisible() {
		return isElementVisible(btnNextPaginationOnPaymentPage);
	}

	public void clickNextPaginationBtnOnPayment() {
		clickWithJSExecutor(btnNextPaginationOnPaymentPage);
	}

	@FindBy(css="#tblPaymentHistory tbody tr")
	private List<WebElement> paymentpageobjecr;
	
	public List<WebElement> getPaymentHistoryObjList(){
		return paymentpageobjecr;
	}
	@FindBy(css = "a[id$='tblPaymentHistory_previous']")
	private WebElement btnPreviousPaginationPayments;

	@FindBy(css = ".datepicker-calendar.bootstrap3.default below")
	private WebElement divDatePicker;

	public boolean isDivDatePickerVisible() {
		return isElementVisible(divDatePicker);
	}
    
    
	// UnUsed Locator
	@FindBy(css = ".ui-datepicker-next")
	private WebElement btnNextDatePicker;
	@FindBy(css = "#datepicker-month-to")
	private WebElement selectToYearDropdown;
	@FindBy(xpath = "//*[@class ='ui-state-default' and text()='%s']/..")
	private WebElement btnGivenDate;
	@FindBy(css = ".dataTables_empty")
	private WebElement lblNoDataFoundFilterResult;
	@FindBy(xpath = "//table[@id='tblPaymentHistory']/tbody/tr/td[1]")
	private WebElement lblTransactionDate;
	@FindBy(css = ".w2ui-col-header")
	private WebElement lblFirstBillRow;
	@FindBy(css = "li#billpaybillingDetails>a")
	private WebElement tabBillStateMents;
	@FindBy(css = "li#billpaymentDetails>a")
	private WebElement tabBillPayments;
	@FindBy(css = "img[src='images/view_all_pdf.png']")
	private WebElement lnkPdfIcon;
	@FindBy(xpath = "//*[@id='tblBillingHistory']/tbody[1]/tr/td[2]")
	private WebElement lblFirstBillAmountRow;
	@FindBy(xpath = "//*[@id='tblPaymentHistory']/tbody[1]/tr/td[5]")
	private WebElement lblFirstBillTransactionRow;
	
	
	@FindBy(css = ".ui-datepicker-month")
	private WebElement selectMonthDropdown1;

	

	
// Calender Locator Input tab
	
	@FindBy(css = "#datepicker-month-from")
	private WebElement selectMonthDropdown;
	
	public void clickJSSelectMonthDropDown() {
		clickWithJSExecutor(selectMonthDropdown);
		log.info("Select Month Drop Down has been Successfully Clicked.");
	}
	
	public void clickSelectMonthDropDown() {
		click(selectMonthDropdown);
		log.info("Select Month Drop Down has been Successfully Clicked.");
	}

	public void clickSelectYearDropDown() {
		click(selectMonthDropdown);
		log.info("Select Year Drop Down has been Successfully Clicked.");
	}

	public void clickJSSelectYearDropDown() {
		clickWithJSExecutor(selectMonthDropdown);
		log.info("Select Year Drop Down has been Successfully Clicked.");
	}
	@FindBy(css = "#datepicker-month-to")
	private WebElement selectToMonthDropdown;
	
	public void clickJSSelectToMonthDropDown() {
		clickWithJSExecutor(selectToMonthDropdown);
		log.info("Select Month Drop Down has been Successfully Clicked.");
	}
	
	public void clickToSelectMonthDropDown() {
		click(selectToMonthDropdown);
		log.info("Select Month Drop Down has been Successfully Clicked.");
	}

	public void clickToSelectYearDropDown() {
		click(selectToMonthDropdown);
		log.info("Select Year Drop Down has been Successfully Clicked.");
	}

	public void clickJSToSelectYearDropDown() {
		clickWithJSExecutor(selectToMonthDropdown);
		log.info("Select Year Drop Down has been Successfully Clicked.");
	}

	@FindBys(@FindBy(xpath="//table[@aria-labelledby='datepicker-month-to']/tbody/tr/td[(@class='day selectable') or (@class='day selectable focus')]"))
	private List<WebElement> date;
	
	public List<WebElement> getDateElement(){
	return 	date;
	}
	public void clickDate(int date) {
		clickWithJSExecutor(getDateElement().get(date-1));
	}
	
	
	
	
	@FindBy(css = ".day.selectable.curDay")
	private WebElement lblTodayDatePicker;

	public boolean isTodayDatePickerVisible() {
		return isElementVisible(lblTodayDatePicker);
	}

	public void clickTodayDatePicker() {
		click(lblTodayDatePicker);
	}

	public String getTodayDatePickerLabel() {
		String label = getText(lblTodayDatePicker);
		return label;
	}

	

}

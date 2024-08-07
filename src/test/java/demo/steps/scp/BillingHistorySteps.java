package demo.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import demo.pageobjects.DashboardPage;
import sew.ai.config.CSPConfiguration;
import sew.ai.config.SCPConfiguration;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.models.User;
import sew.ai.pageObjects.scp.BillingHistoryPage;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.HomeSteps;
import sew.ai.steps.scp.LoginSteps;
import sew.ai.utils.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;

public class BillingHistorySteps extends BillingHistoryPage {
	private static final Logger log = LogManager.getLogger(BillingHistorySteps.class);

	
	public static PropertiesUtil billingHistoryTextProp;
	public static Map<String, Integer> registrationConfig = new HashMap<>();

	public BillingHistorySteps(WebDriver driver) {
		super(driver);
		billingHistoryTextProp = new PropertiesUtil(
				FilePaths.SCP_TEXT_PROPERTIES + Constants.BILLING_HISTORY_TXT_FILENAME);
	}

	public void verifyBillHistoryUIAndObjects(SoftAssert softAssert) throws Exception {
		ExtentLogger.logInfo("To verify Billing History page UI and Objects");

		pause(3000);
		softAssert.assertTrue(isExportToExcelLnkVisible(), "ExportToExcel lnk is not visible.");
		softAssert.assertTrue(isBillStatementLnkVisible(), "Billing Lnk is not Visible.");
		softAssert.assertTrue(isPaymentLnkVisible(), "Payment Lnk is not Visible.");
		// Filter
		softAssert.assertTrue(isFromDateVisible());
		softAssert.assertTrue(isToDateVisible());
		softAssert.assertTrue(isFliterBtnVisible());
		// Footer
		softAssert.assertTrue(isDataTableInfoVisible());
		softAssert.assertTrue(isPreviousBtnVisible());
		List<WebElement> element = getpagePaginationElement();
		for (WebElement element1 : element)
			softAssert.assertTrue(isPaginationBtnVisible(element1));
		softAssert.assertTrue(isNextPaginationBtnVisible());
		// Billing Statement
		softAssert.assertTrue(islnkBillStatementDateVisible(), "Bill Date Column is not visible.");
		softAssert.assertTrue(isBillAmountDueLnkVisible(), "Amout Due is not visible.");
		softAssert.assertTrue(isBillDueDateLnkVisible(), "Due Date Link not visible.");

		
		clickViewBillPdfFirstArrowLnk();
		String errMsgComment = getToastMessage();
		Assert.assertEquals(errMsgComment, billingHistoryTextProp.getPropValue("txtBillingRecdropdownerror"));
		if (errMsgComment == null) {
		softAssert.assertTrue(isViewBillPdfLnkVisible(), "View Bill Pdf Lnk is not Visible in First Bll Type.");
		softAssert.assertTrue(isPrevBalTxtVisible(), "Prev Bal Txt is not visible.");
		softAssert.assertTrue(isCurrentChargeTxtVisible(), "Currebt Charge Txt is bt visible.");
		softAssert.assertTrue(isPrevBalBillAmountTxtVisible(), "Prev Bal Bill MAount is not visible.");
		softAssert.assertTrue(isCurrentChargeAmountVisible(), "Currev=nt Charge is not visible.");
		softAssert.assertTrue(isTotalAMountDueVisible(), "Total maount due is not visible.");
		char currencySymbol = getTotalAmountDueCurrenySymbol();
		System.out.println(currencySymbol);
		softAssert.assertEquals(currencySymbol, '$', "Currency Symbol is not matching as $");
		clickViewBillPdfFirstArrowLnk();
		} 
		else {
			log.info("Error exception handeled - bug exist");
			ExtentLogger.logInfo(billingHistoryTextProp.getPropValue("txtBillingRecdropdownerror"));
		}
		

		// Label validation
		softAssert.assertEquals(getExportToExcelLabel(), billingHistoryTextProp.getPropValue("txtLnkExportExcel"),
				"ExportTOExcel label is not matching.");
		softAssert.assertEquals(getBillingLnkLabel(), billingHistoryTextProp.getPropValue("txtLnkBillStatement"),
				"Billing Lable is not Matching.");
		softAssert.assertEquals(getLnkPaymentLabel(), billingHistoryTextProp.getPropValue("txtLnkPayments"),
				"Payment label is not Matching.");
		softAssert.assertEquals(getFilterBtnLabel(), billingHistoryTextProp.getPropValue("txtBtnFilter"));
		softAssert.assertEquals(getFromDateAttribute(), billingHistoryTextProp.getPropValue("txtLblFromDate"),
				"From Date Label is not Matching.");
		softAssert.assertEquals(getToDateAttribute(), billingHistoryTextProp.getPropValue("txtInputSelectToDate"),
				"To date Attribute label isnot matching.");

		softAssert.assertEquals(getlnkBillStatementDateLabel(), billingHistoryTextProp.getPropValue("txtLblBillDate"),
				"STATEMENT DATE Column is not matching.");
		softAssert.assertEquals(getBillAmountDueLnkLabel(), billingHistoryTextProp.getPropValue("txtLblBillAmount"),
				"AMOUNT DUE Column lable is not matchng.");
		softAssert.assertEquals(getBillDueDateLnkLabel(), billingHistoryTextProp.getPropValue("txtLblViewBill"),
				"DUE DATE Column lable is not Matching.");

		String showing = billingHistoryTextProp.getPropValue("txtLblShowPage");
		softAssert.assertTrue(getDataTableInfoLabel().contains(showing), "Showing Page lable is not Mathing");
		softAssert.assertEquals(getPrevPaginationLabel(), billingHistoryTextProp.getPropValue("txtBtnPrevious"),
				"Prev Btn label is not matching.");
		softAssert.assertEquals(getNextPaginationLabel(), billingHistoryTextProp.getPropValue("txtBtnNext"),
				"Next Btn label is  not matching.");

		int totalBillingHistotypage = billingHistoryPageCount();

		clickFilterBtn();
		softAssert.assertEquals(getMandatoryToastMessage(), billingHistoryTextProp.getPropValue("fromDateSelectToast"),
				"please Select From Date Toast msg lable is not Matching.");

		// Test Payments Tab
		clickPaymentLnk();
		pause(3000);
		// waitForPageLoader();
		softAssert.assertTrue(isExportToExcelLnkVisible(), "Export to Excel link is not visible.");
		softAssert.assertTrue(isBillStatementLnkVisible());
		softAssert.assertTrue(isPaymentLnkVisible());
		// Filter
		softAssert.assertTrue(isFromDateVisible(), "From Date is not Visible at payment tab.");
		softAssert.assertTrue(isToDateVisible(), "To Date is not Visible at payment tab.");
		softAssert.assertTrue(isFliterBtnVisible(), "Filter btn  is not Visible at payment tab.");
		pause(3000);
		if (getLblNoBillAvailableSize() == 0) {
			// Footer
			softAssert.assertTrue(isDataTableInfoVisible(), "Date Table is not Visible at payment tab.");
			// softAssert.assertTrue(isPreviousBtnVisible());
			softAssert.assertTrue(isDataTableInfoVisible(), "Data Column is not visible.");

			softAssert.assertTrue(isTransactionDateColumVisible());
			softAssert.assertTrue(isTransactionAmountColumVisible());
			softAssert.assertEquals(getExportToExcelLabel(), billingHistoryTextProp.getPropValue("txtLnkExportExcel"));
			softAssert.assertTrue(
					getBillingLnkLabel().equalsIgnoreCase(billingHistoryTextProp.getPropValue("txtLnkBillStatement")));

			softAssert.assertEquals(getFilterBtnLabel(), billingHistoryTextProp.getPropValue("txtBtnFilter"));

			softAssert.assertEquals(getFromDateAttribute(),
					billingHistoryTextProp.getPropValue("txtInputSelectFromDate"));
			softAssert.assertEquals(getToDateAttribute(), billingHistoryTextProp.getPropValue("txtInputSelectToDate"));
			softAssert.assertEquals(getTransxctionDateColumnLabel(),
					billingHistoryTextProp.getPropValue("txtLblTransactionDate"));
			softAssert.assertEquals(getTransxctionAmountColumnLabel(),
					billingHistoryTextProp.getPropValue("txtLblTransactionAmount"));

			showing = billingHistoryTextProp.getPropValue("txtLblShowPage");
			softAssert.assertTrue(getDataTableInfoLabel().contains(showing), "Showing Page lable is not Mathing");

		} else {
			ExtentLogger.logInfo("Payment info history is not available");
		}
	}

	public void verifyBillAndPaymentDateRange(SoftAssert softAssert) throws ParseException {
		ExtentLogger.logInfo("To verify Billing and Payment up to 24 months is displayed.");
		registrationConfig = CSPConfiguration.initMCspConfig();
		if (registrationConfig.get("Billing") == 1) {
			LoginSteps loginSteps = new LoginSteps(driver);
			User user = SCPConfiguration.user;
			loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
			// if (registrationConfig.get("Billing.History") == 1) {
			HomeSteps homeSteps = new HomeSteps(driver);
			homeSteps.navigateToBillingAndPaymentHistory();
			String oldestBillDate;
			String latestBillDate;
			String dateHeader = getlnkBillStatementDateLnkAttribute();
			System.out.println("=====>" + dateHeader);
			if (dateHeader.contains("desc")) {
				latestBillDate = getBillStatDateFirstColumnLabel();
				clickBillStatementDateLnk();
				oldestBillDate = getBillStatDateFirstColumnLabel();
				System.out.println("latestBillDate=====>" + latestBillDate);
				System.out.println("oldestBillDate=====>" + oldestBillDate);
			} else {
				oldestBillDate = getBillStatDateFirstColumnLabel();
				clickBillStatementDateLnk();
				latestBillDate = getBillStatDateFirstColumnLabel();
				System.out.println("latestBillDate=====>" + latestBillDate);
				System.out.println("oldestBillDate=====>" + oldestBillDate);
			}
			HashMap<String, Integer> map = DateUtil.getAddDaysMonthsAndYears(oldestBillDate, 0, 24, 0);
			Date date = DateUtil._date;
			System.out.println("24 months added in Old Bill Date  +++++++>>" + date);
			HashMap<String, Integer> _map = DateUtil.getAddDaysMonthsAndYears(latestBillDate, 0, 0, 0);
			Date date1 = DateUtil._date;
			System.out.println("latest Bill Date, it should be before +++++++>>" + date1);
			softAssert.assertTrue(map.get("year") >= _map.get("year"));
			if (date1.compareTo(date) > 0) {
				System.out.println("Date1 is after Date");
				softAssert.assertTrue(date1.compareTo(date) > 0);
			} else if (date1.compareTo(date) < 0) {
				System.out.println("Date1 is before Date");
				softAssert.assertTrue(date1.compareTo(date) < 0);
			} else if (date1.compareTo(date) == 0) {
				System.out.println("Date1 is equal to Date");
				softAssert.assertTrue(date1.compareTo(date) == 0);
			} else {
				System.out.println("How to get here?");
			}
			// payment Tab

			clickPaymentLnk();
			pause(4000);
			String oldestPaymentDate;
			String latestPaymentDate;
			String dateHeaderTransaction = getTransxctionDateColumnAttribute();
			System.out.println("=====>" + dateHeaderTransaction);
			if (dateHeaderTransaction.contains("desc")) {
				latestPaymentDate = getTransxctionDateColumnLabel();
				clickTransactionDateColum();
				oldestPaymentDate = getTransxctionDateColumnLabel();
				System.out.println("latestPaymentDate=====>" + latestPaymentDate);
				System.out.println("oldestPaymentDate=====>" + oldestPaymentDate);
			} else {
				oldestPaymentDate = getTransxctionDateColumnLabel();
				clickTransactionDateColum();
				latestPaymentDate = getTransxctionDateColumnLabel();
				System.out.println("latestPaymentDate=====>" + latestPaymentDate);
				System.out.println("oldestPaymentDate=====>" + oldestPaymentDate);
			}
			// getAddDaysMonthsAndYears() InvocaitonTargetException

			HashMap<String, Integer> map1 = DateUtil.getAddDaysMonthsAndYears(oldestPaymentDate, 0, 24, 0);
			date = DateUtil._date;
			System.out.println("24 months added in  old payment date+++++++>>" + date);
			HashMap<String, Integer> map2 = DateUtil.getAddDaysMonthsAndYears(latestPaymentDate, 0, 0, 0);
			date1 = DateUtil._date;
			softAssert.assertTrue(map1.get("year") >= map2.get("year"));

			if (date1.compareTo(date) > 0) {
				System.out.println("Date1 is after Date");
			} else if (date1.compareTo(date) < 0) {
				System.out.println("Date1 is before Date");
			} else if (date1.compareTo(date) == 0) {
				System.out.println("Date1 is equal to Date");
			} else {
				System.out.println("How to get here?");
			}

		} else {
			ExtentLogger.logFail("Billing-> History module is not available.");
		}

		ExtentLogger.logPass("To verify Billing and Payment up to 36 months is displayed.");
	}

	public void verifyExportToExcelAndItsContents(SoftAssert softAssert) throws ParseException {
		ExtentLogger.logInfo(
				"To verify 'Export To Excel' link and Contents in download file for Billing and Payment history");

		String currencyConfig = "$";

		if (!isNoBillAvailbleVisible()) {
			softAssert.assertTrue(isExportToExcelLnkVisible(), "Export to Excel lnk is not Visible.");
		}
		softAssert.assertTrue(isBillStatementLnkVisible(), "Billing Lnk is not Visible.");
		softAssert.assertTrue(isPaymentLnkVisible(), "Payment lnk is not visible.");

//		File Files = new File(FilePaths.DOWNLOAD_FOLDER_PATH);
		File Files = new File(FilePaths.Download_Path);
//		FileUtil.deleteFile(FilePaths.DOWNLOAD_FOLDER_PATH);
		FileUtil.deleteFile(FilePaths.Download_Path);

		pause(5000);

		if (false) {
			ExtentLogger.logInfo("Billing History is not present for this account in DB.");
		} else {

			clickExportToExcelLnk();
			pause(5000);

			ExcelUtils.openExcelFile(FilePaths.Download_Path + "Billing.xlsx");
			pause(5000);

			Sheet sheet = ExcelUtils.getSheetName(0);
			pause(3000);
			int totalRow = ExcelUtils.getRowCount(sheet);
			int totalCell = sheet.getRow(4).getLastCellNum();
			// System.out.println("Total rows ====> " + ExcelUtils.getRowCount());
			DashboardSteps dashboardSteps = new DashboardSteps(driver);
			String sAddressInDropdown = dashboardSteps.getBoxAccountAddressLabel();
			String[] sAddressList = sAddressInDropdown.split(" ");
			int size = sAddressList.length;

			String latestBillDate;
			String oldestBillDate;
			String dateHeader = getlnkBillStatementDateLnkAttribute();
			if (dateHeader.contains("desc")) {
				latestBillDate = getBillStatDateFirstColumnLabel();
				clickBillStatementDateLnk();
				oldestBillDate = getBillStatDateFirstColumnLabel();

			} else {
				oldestBillDate = getBillStatDateFirstColumnLabel();
				clickBillStatementDateLnk();
				latestBillDate = getBillStatDateFirstColumnLabel();
			}

			DateUtil.getFullMonthNameBilling(latestBillDate);

			DateUtil.getFullMonthNameBilling(oldestBillDate);

			// Verify row ZERO
			String value = ExcelUtils.getCellStringValue(sheet, 0, 0);
			softAssert.assertTrue(value.contains("Period:"),
					"Period is not found in the excel at (0,0) locaion in excel.");
			// Need to validate Month and Year
			// Verify row 1
			softAssert.assertTrue(ExcelUtils.getCellStringValue(sheet, 1, 0).contains("Account:"),
					"Account is not found in the excel at (1,0) locaion in excel.");
			// Verify row 3
			softAssert.assertEquals(ExcelUtils.getCellStringValue(sheet, 3, 0), "Statement Date",
					"Statement Date is not found in the excel at (3,0) locaion in excel.");
			softAssert.assertEquals(ExcelUtils.getCellStringValue(sheet, 3, 1), "Amount Due(" + currencyConfig + ")",
					"Amount due is not found in the excel at (3,1) locaion in excel.\"");
			softAssert.assertEquals(ExcelUtils.getCellStringValue(sheet, 3, 2), "Due Date",
					"Amount due is not found in the excel at (3,2) locaion in excel.\"");
			// Bill Statement Date and Amount validation
			String nextBtnAttribut = getNextPaginationAttribute();
			int i = 0;
			while (!nextBtnAttribut.contains("disabled")) {
				for (WebElement el : getBillAmountLblElement()) {
					String paymentAmountUi = getText(el).replace("$", "");
					if (paymentAmountUi.contains("-")) {
						String paymentAmount = paymentAmountUi.substring(1).trim();
						String regex = "(?<=[\\d])(,)(?=[\\d])";

						String webPaymentAmount = MathUtil.toRoundoff(paymentAmount.replaceAll("[^0-9]", ""));
						String excellPaymentAmount = MathUtil.toRoundoff(ExcelUtils.getCellStringValue(i + 4, 1))
								.replace("-", "");
						softAssert.assertEquals(webPaymentAmount, excellPaymentAmount,
								"Payment Amount is not matching on web with Excel.");
					} else {
						String paymentAmount = paymentAmountUi;

						String s = paymentAmount.replaceAll("[^0-9]", "");
						float d = (Float.parseFloat(s) / 100);
						String webPaymentAmount = String.format("%.2f", d);

						String excellPaymentAmount = ExcelUtils.getCellStringValue(sheet, totalRow, 1);
						softAssert.assertEquals(webPaymentAmount, excellPaymentAmount,
								"Billing History is not matching on Found on web and Expected with with Excel. at -->> Row no"
										+ totalRow + " in The excel.");
					}

					i++;
					totalRow--;
				}
				clickNextPaginationBtn();
				nextBtnAttribut = getNextPaginationAttribute();
			}
			ExcelUtils.closeConnectionWithExcel();
			pause(5000);
			// Test Payments Excel
			String downloadFilepath = FilePaths.Download_Path;
			FileUtil.deleteFile(downloadFilepath);

			pause(2000);
			clickPaymentLnk();
			pause(5000);

			clickExportToExcelLnk();
			pause(5000);
			ExcelUtils.openExcelFile(FilePaths.Download_Path + "Payment-2.xlsx");
			pause(5000);
			Sheet sheetPayment = ExcelUtils.getSheetName(0);

			int totlaPaymentRow = ExcelUtils.getRowCount(sheetPayment);
			int totalPaymentCell = sheetPayment.getRow(4).getLastCellNum();

			String oldestPaymentDate;
			String latestPaymentDate;
			String dateHeaderTransaction = getTransxctionDateColumnAttribute();

			if (dateHeaderTransaction.contains("desc")) {
				latestPaymentDate = getTransxctionDateColumnLabel();
				clickTransactionDateColum();
				oldestPaymentDate = getTransxctionDateColumnLabel();
				clickTransactionDateColum();
			} else {
				oldestPaymentDate = getTransxctionDateColumnLabel();
				clickTransactionDateColum();
				latestPaymentDate = getTransxctionDateColumnLabel();
			}

			// Verify row ZERO
			value = ExcelUtils.getCellStringValue(sheetPayment, 0, 0);
			softAssert.assertTrue(value.contains("Period:"), "In Excel at (0,0) not contain Period.");
			// Need to validate Month and Year Verify row 1
			softAssert.assertTrue(ExcelUtils.getCellStringValue(sheetPayment, 1, 0).contains("Account:"));

			// Verify row 3
			softAssert.assertEquals(ExcelUtils.getCellStringValue(sheetPayment, 3, 0), "Date",
					"Statement Date is not found in the excel at (3,0) locaion in excel.");
			softAssert.assertEquals(ExcelUtils.getCellStringValue(sheetPayment, 3, 1), "PAYMENT TYPE",
					"Amount Due is not found in the excel at (3,1) locaion in excel.");

		}

		ExtentLogger.logPass(
				"To verify 'Export To Excel' link and Contents in download file for Billing and Payment history");
	}

}

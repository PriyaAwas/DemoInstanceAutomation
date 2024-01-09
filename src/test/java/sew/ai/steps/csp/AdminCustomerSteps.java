package sew.ai.steps.csp;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import sew.ai.api.endpoints.auth.RegistrationEndpoints;
import sew.ai.api.endpoints.outage.OutageEndpoints;
import sew.ai.config.CSPConfiguration;
import sew.ai.config.Configuration;
import sew.ai.config.SCPConfiguration;
import sew.ai.driver.DriverFactory;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.ResourcePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.models.CurrentOutage;
import sew.ai.models.Customer;
import sew.ai.models.GetBillHistoryData;
import sew.ai.models.GetPaymentHistoryData;
import sew.ai.pageObjects.csp.AdminCustomerPage;
import sew.ai.pageObjects.csp.AdminHomePage;
import sew.ai.pageObjects.scp.DashboardPage;
import sew.ai.pageObjects.scp.NotificationPage;
import sew.ai.steps.scp.LoginSteps;
import sew.ai.steps.scp.RegistrationSteps;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.DateUtil;
import sew.ai.utils.ExcelUtils;
import sew.ai.utils.PhoneNoUtil;
import sew.ai.utils.PropertiesUtil;
import sew.ai.utils.RandomUtil;
import sew.ai.utils.RandomUtil.Mode;

public class AdminCustomerSteps extends AdminCustomerPage {

	private static final Logger log = LogManager.getLogger(AdminCustomerSteps.class);
	public static PropertiesUtil adminCustomerTextProp;
	static String csrDataFileName = FilePaths.TEST_RESOURCE_PATH + Constants.CSRSEARCH_FILENAME;
	static String downloadUsageExcelPath = FilePaths.DOWNLOAD_PATH;
	public static String filePth = FilePaths.FILE_UPLOAD_PATH +"Test.jpg";

	public AdminCustomerSteps(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		adminCustomerTextProp = new PropertiesUtil(
				FilePaths.CSP_TEXT_PROPERTIES + Constants.ADMIN_CUSTOMER_TXT_FILENAME);
	}

	public void verifyCustomerDetailsPopUpTabs() {

		SoftAssert soft = new SoftAssert();
		// testadminloginPage.adminLoginReusable();

		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		// testHomePage.navigateCSRWorkBenchWidget();
		// Entering value in the search box
		// Thread.sleep(5000);
		enterValueSearchFilter(Configuration.toString("utilityAccountNumber"));
		// enterValueSearchFilter(Utils.getRbData().getString("serviceAccountNumber"));
		// pageUtil.explicitWaitForWebElementAttribute(driver,
		// adminCsrPage.getdivPageLoad(), "style", "display: none;");
		clickLnkServiceAccountNumber();
		// pageUtil.click(adminCsrPage.getLnkServiceAccountNumber());
		waitForImgCustomerDetailsLoadingToInvisible();
//		pageUtil.WaitForWebElementToInvisible(driver, adminCsrPage.getLoadingImg());
		switchToFrame(0);
		// pageUtil.switchToFrame(0);
		// Thread.sleep(3000);
		// 1-profile
		soft.assertTrue(isLnkProfileTabVisible());
		// pageUtil.verifyElementVisible(adminCsrPage.getlnkProfileTab());
		// 2-Billing
		soft.assertTrue(isLnkBillingTabVisible());
		// pageUtil.verifyElementVisible(adminCsrPage.getLnkBillingTab());
		// 3-Efficiency
		soft.assertTrue(isLnkEfficiencyTabVisible());
		// pageUtil.verifyElementVisible(adminCsrPage.getLnkEfficiencyTab());
		// 4-Interaction
		soft.assertTrue(isLnkBillingTabVisible());
		// pageUtil.verifyElementVisible(adminCsrPage.getlnkUserInteractionTab());
		// 5-Guest
		soft.assertTrue(isLnkUserGuestTabVisible());
		// pageUtil.verifyElementVisible(adminCsrPage.getlnkUserGuestTab());
		// 6-Usage
		soft.assertTrue(isLnkUsageTabVisible());
		// pageUtil.verifyElementVisible(adminCsrPage.getLnkUsageTab());
		// 7-Compare
		soft.assertTrue(isLnkCompareTabVisible());
		// pageUtil.verifyElementVisible(adminCsrPage.getLnkCompareTab());
		// 8-EV
		//soft.assertTrue(isLnkEVTabVisible());
		// pageUtil.verifyElementVisible(adminCsrPage.getlnkEVTab());
		// 9-Notifications
		soft.assertTrue(isLnkUserNotificationTabVisible());
		// pageUtil.verifyElementVisible(adminCsrPage.getlnkUserNotificationTab());
		// 10-Connect Me
		soft.assertTrue(isLnkConnectMeRequestTabVisible());
		// pageUtil.verifyElementVisible(adminCsrPage.getlnkConnectMeRequestTab());
		// 11-service
		soft.assertTrue(isLnkServiceRequestTabVisible());
		// pageUtil.verifyElementVisible(adminCsrPage.getlnkServiceRequestTab());
		// verify address drop down
		List<String> allOptionsInListBox = getAllDdlAddress();
		// List<String> allOptionsInListBox =
		// pageUtil.getAllOptionsInListBox(adminCsrPage.getddlAddress());
		soft.assertEquals(allOptionsInListBox.size(), 1);
		// pageUtil.verifyCount(allOptionsInListBox.size(), 1);
		soft.assertAll();

	}

	public void verifyCustomerInformation360View() {
		log.info(
				"Test Case execution for - Verify the Profile Tab Customer information for Customer in CSR - is initiated");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		enterValueSearchFilter(Configuration.toString("utilityAccountNumber"));
		// pageUtil.pause(15000);
		// Entering value in the search box
		// enterValueSearchFilter(Utils.getRbData().getString("sUtilityNumber"));
		// pageUtil.explicitWaitForWebElementAttribute(driver,
		// adminCsrPage.getdivPageLoad(), "style", "display: none;");
		clickLnkServiceAccountNumber();
		// pageUtil.click(adminCsrPage.getLnkServiceAccountNumber());
		waitForImgCustomerDetailsLoadingToInvisible();
		// pageUtil.WaitForWebElementToInvisible(driver, adminCsrPage.getLoadingImg());
		switchToFrame(0);
		// pageUtil.switchToFrame(0);
		// navigateTabCustomerDetailSection("Profile");
		waitForImgCustomerDetailsLoadingToInvisible();
		// verify the Details on the Customer Details Page
		SoftAssert softAssert = new SoftAssert();
		// pageUtil.explicitWaitForWebElement(driver,
		// adminCsrPage.getlblCustomerName());
		// customer name
		softAssert.assertTrue(isLblCustomerNameVisible());
		// softAssert.assertTrue(pageUtil.verifyElementPresent(adminCsrPage.getlblCustomerName()));
		// Runner.test.log(Status.INFO, "Customer Name :" +
		// pageUtil.getLocatorText(adminCsrPage.getlblCustomerName()));
		// zip code
		// softAssert.assertTrue(isLblZipCodeVisible());
		// softAssert.assertTrue(pageUtil.verifyElementPresent(adminCsrPage.getlblZipCode()));
		// Runner.test.log(Status.INFO, "Customer Zip Code :" +
		// pageUtil.getLocatorText(adminCsrPage.getlblZipCode()));
		// username
		softAssert.assertTrue(isLblLoginIdVisible());
		// softAssert.assertTrue(pageUtil.verifyElementPresent(adminCsrPage.getlblLoginId()));
		// Runner.test.log(Status.INFO, "Customer LoginId :" +
		// pageUtil.getLocatorText(adminCsrPage.getlblLoginId()));
		// status
		softAssert.assertTrue(isLblStatusUserVisible());
		// softAssert.assertTrue(pageUtil.verifyElementPresent(adminCsrPage.getlblStatusUser()));
		// Runner.test.log(Status.INFO, "Customer Status :" +
		// pageUtil.getLocatorText(adminCsrPage.getlblStatusUser()));
		// Primary Email
		softAssert.assertTrue(isLblEmailIdVisible());
		// softAssert.assertTrue(pageUtil.verifyElementPresent(adminCsrPage.getlblEmailId()));
		// Runner.test.log(Status.INFO, "Customer Email Id :" +
		// pageUtil.getLocatorText(adminCsrPage.getlblEmailId()));
		// Account Type
		softAssert.assertTrue(isLblAccountTypeVisible());
		// softAssert.assertTrue(pageUtil.verifyElementPresent(adminCsrPage.getlblAccountType()));
		// Runner.test.log(Status.INFO, "Customer Account Type :" +
		// pageUtil.getLocatorText(adminCsrPage.getlblAccountType()));
		// Secondary Email:
		softAssert.assertTrue(isLblAlternateEmailIdVisible());
		// softAssert.assertTrue(pageUtil.verifyElementPresent(adminCsrPage.getlblAlternateEmailId()));
		// Runner.test.log(Status.INFO, "Customer Alternate Email Id :" +
		// pageUtil.getLocatorText(adminCsrPage.getlblAlternateEmailId()));
		// Last Login IP & Time
		softAssert.assertTrue(isLblloginIPVisible());
		// softAssert.assertTrue(pageUtil.verifyElementPresent(adminCsrPage.getlblloginIP()));
		// Runner.test.log(Status.INFO, "Customer Login IP :" +
		// pageUtil.getLocatorText(adminCsrPage.getlblloginIP()));
		// Primary Phone Number
		softAssert.assertTrue(isLblMobileNumberVisible());
		// softAssert.assertTrue(pageUtil.verifyElementPresent(adminCsrPage.getlblMobileNumber()));
		// Runner.test.log(Status.INFO, "Customer Login IP :" +
		// pageUtil.getLocatorText(adminCsrPage.getlblMobileNumber()));
		// Paper bill status
		softAssert.assertTrue(isLblPaperBillVisible());
		// softAssert.assertTrue(pageUtil.verifyElementPresent(adminCsrPage.getlblPaperBill()));
		// Runner.test.log(Status.INFO, "Customer Paper Bill :" +
		// pageUtil.getLocatorText(adminCsrPage.getlblPaperBill()));
		// Secondary Phone Number
		softAssert.assertTrue(isLblAlternateNumberVisible());
		// softAssert.assertTrue(pageUtil.verifyElementPresent(adminCsrPage.getlblAlternateNumber()));
		// Runner.test.log(Status.INFO, "Customer AlternateNumber :" +
		// pageUtil.getLocatorText(adminCsrPage.getlblAlternateNumber()));
		// Bill type Enrolled Date
		softAssert.assertTrue(isLblCreateDateVisible());
		// softAssert.assertTrue(pageUtil.verifyElementPresent(adminCsrPage.getlblCreateDate()));
		// Runner.test.log(Status.INFO, "Customer Create Date :" +
		// pageUtil.getLocatorText(adminCsrPage.getlblCreateDate()));
		// City
		// softAssert.assertTrue(isLblCityVisible());
		// softAssert.assertTrue(pageUtil.verifyElementPresent(adminCsrPage.getlblCity()));
		// Runner.test.log(Status.INFO, "Customer City Name :" +
		// pageUtil.getLocatorText(adminCsrPage.getlblCity()));
		// Last Login Location
		softAssert.assertTrue(isLblloginlocationVisible());
		// softAssert.assertTrue(pageUtil.verifyElementPresent(adminCsrPage.getlblloginlocation()));
		// Runner.test.log(Status.INFO, "Customer login Location :" +
		// pageUtil.getLocatorText(adminCsrPage.getlblloginlocation()));
		// Time Zone
		softAssert.assertTrue(isLblUsertimezoneVisible());
		// softAssert.assertTrue(pageUtil.verifyElementPresent(adminCsrPage.getlblUsertimezone()));
		// Runner.test.log(Status.INFO, "Customer user TimeZone :" +
		// pageUtil.getLocatorText(adminCsrPage.getlblUsertimezone()));
		softAssert.assertAll();
		log.info(
				"Test Case execution for - Verify the Profile Tab Customer information for Customer in CSR - is Finished");

	}

	public void navigateTabCustomerDetailSection(String sTabName) {

		switch (sTabName) {
		case "Billing/Payments":
			isLnkBillingTabVisible();
			// pageUtil.explicitWaitForWebElement(driver, adminCsrPage.getLnkBillingTab());
			clickBillingTab();
			// pageUtil.click(adminCsrPage.getLnkBillingTab());
			break;
		case "Connect Me":
//			pageUtil.explicitWaitForWebElement(driver, adminCsrPage.getlnkConnectMeRequestTab());
//			pageUtil.click(adminCsrPage.getlnkConnectMeRequestTab());
//			pageUtil.expWaitForEleInvisibility(adminCsrPage.getImgLoadingSpinner());
//			pageUtil.expWaitForEleVisibility(adminCsrC360ConnectMePage.getBtnNext());
			break;
		case "Usage":
//			pageUtil.explicitWaitForWebElement(driver, adminCsrPage.getLnkUsageTab());
//			pageUtil.click(adminCsrPage.getLnkUsageTab());
			break;
		case "Compare":
//			pageUtil.explicitWaitForWebElement(driver, adminCsrPage.getLnkCompareTab());
//			pageUtil.click(adminCsrPage.getLnkCompareTab());
			break;
		case "Ways To Save":
			isLnkEfficiencyTabVisible();
			clickLnkEfficiencyTab();
			break;
		case "Profile":
			isLnkProfileTabVisible();
			// pageUtil.expWaitForElePresence(adminCsrPage.getlnkProfileTab());
			// pageUtil.expWaitForEleVisibility(adminCsrPage.getlnkProfileTab());
			// pageUtil.expWaitForEleClickable(adminCsrPage.getlnkProfileTab());
			clickProfileTab();
			// pageUtil.click(adminCsrPage.getlnkProfileTab());
			break;
		case "EV":
//			pageUtil.explicitWaitForWebElement(driver, adminCsrPage.getlnkProfileTab());
//			pageUtil.click(adminCsrPage.getlnkProfileTab());
			break;
		default:
			break;
		}
	}

	public void enterValueSearchFilter(String data) {
		waitForTxtSearchCustomer();
		// pageUtil.explicitWaitForWebElement(driver,
		// adminCsrPage.gettxtSearchCustomer());
		populateTxtSearchCustomer(data);
		// pageUtil.enterTextValue(adminCsrPage.gettxtSearchCustomer(), data);
		waitForPageLoaderInvisibility();
		// pageUtil.expWaitForEleInvisibility(adminCsrPage.getImgLoadingSpinner());
		clickBtnSearch();
		// pageUtil.click(adminCsrPage.getbtnSearch());
		waitForPageLoaderInvisibility();
		// pageUtil.expWaitForEleInvisibility(adminCsrPage.getImgLoadingSpinner());
		// pageUtil.pause(2000);
	}

	public void verifyBillTypeStatusesDetailsView() {
		log.info(
				"Test Case execution for - Verify the Profile Tab Customer information for different BillType Statues in CSR - is initiated");
		// Runner.test.log(Status.INFO, "C106832 - Verify the Profile Tab Customer
		// information for different BillType Statues in CSR");

		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		// Paperless data - 0
		ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getServiceAccountBillTypeData("0"));
		String paperBillData = null;
		try {
			rs.next();
			paperBillData = rs.getString("UtilityAccountNumber");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("The Paper Bill Data from the DB is " + paperBillData);
		// Runner.test.log(Status.INFO,"The Paper Bill Data from the DB is
		// "+paperBillData);
		verifyDiffBillTypeStatuses(paperBillData);

		// Paperless data - 1
		ResultSet rs1 = DataBaseUtils.getResultSet(SQLQueries.getServiceAccountBillTypeData("1"));
		String paperLessBillData = null;
		try {
			rs1.next();
			paperLessBillData = rs1.getString("UtilityAccountNumber");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("The PaperLess Bill Data from the DB is " + paperLessBillData);
		// Runner.test.log(Status.INFO,"The PaperLess Bill Data from the DB is
		// "+paperLessBillData);
		verifyDiffBillTypeStatuses(paperLessBillData);

		// Paperless data - 2
		ResultSet rs2 = DataBaseUtils.getResultSet(SQLQueries.getServiceAccountBillTypeData("2"));
		String bothpaperAndPaperlessBillData = null;
		try {
			rs2.next();
			bothpaperAndPaperlessBillData = rs2.getString("UtilityAccountNumber");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("The Both Paper and Paperless Bill Data from the DB is " + bothpaperAndPaperlessBillData);
		// Runner.test.log(Status.INFO,"The Both Paper and PaperBill Data from the DB is
		// "+bothpaperAndPaperlessBillData);
		verifyDiffBillTypeStatuses(bothpaperAndPaperlessBillData);

		log.info(
				"Test Case execution for - Verify the Profile Tab Customer information for Postpaid User in CSR - is Finished");

	}

	public void verifyDiffBillTypeStatuses(String utilityAccountNumber) {

		// AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
		// adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"),
		// Configuration.toString("adminPassword"));

		enterValueSearchFilter(utilityAccountNumber);

		// testadminloginPage.adminLoginReusable();
		// testHomePage.navigateCSRWorkBenchWidget();
		// pageUtil.waitForPageToLoad();//pageUtil.pause(14000);
		// Entering value in the search box
		// enterValueSearchFilter(utilityAccountNumber);
		clickLnkServiceAccountNumber();
		waitForImgCustomerDetailsLoadingToInvisible();
		switchToFrame(0);
		waitForImgCustomerDetailsLoadingToInvisible();
//		pageUtil.explicitWaitForWebElementAttribute(driver, adminCsrPage.getdivPageLoad(), "style", "display: none;");
//		pageUtil.click(adminCsrPage.getLnkServiceAccountNumber());
//		pageUtil.WaitForWebElementToInvisible(driver, adminCsrPage.getLoadingImg());
//		pageUtil.switchToFrame(0);
		// pageUtil.expWaitForEleInvisibility(adminCsrPage.getLoadingImg());pageUtil.waitForPageToLoad();
		navigateTabCustomerDetailSection("Profile");
		// verify the Details on the Customer Details Page
		SoftAssert softAssert = new SoftAssert();
		// pageUtil.explicitWaitForWebElement(driver,
		// adminCsrPage.getlblCustomerName());

		// Bill Type status
		softAssert.assertTrue(isValueBillTypeStatusVisible());
		// softAssert.assertTrue(pageUtil.verifyElementPresent(adminCsrPage.getValueBillTypeStatus()));
		String billTypeStatusUI = getValueBillTypeStatusFieldLabel();
		// String billTypeStatusUI =
		// pageUtil.getLocatorText(adminCsrPage.getValueBillTypeStatus());
		System.out.println(billTypeStatusUI);

		// Bill type Enrolled Date
		softAssert.assertTrue(isValueBillTypeEnrolledDateVisible());
		// softAssert.assertTrue(pageUtil.verifyElementPresent(adminCsrPage.getValueBillTypeEnrolledDate()));
		// getValueBillTypeEnrolledDateFieldLabel
		String billTypeEnrolledDateUI = getValueBillTypeEnrolledDateFieldLabel();
		// String billTypeEnrolledDateUI =
		// pageUtil.getLocatorText(adminCsrPage.getValueBillTypeEnrolledDate());
		System.out.println(billTypeEnrolledDateUI);

		String billTypeStatusDB = null;
		String billTypeEnrolledDateDB = null;
		try {
			ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getBillTypeCustomerDetails(utilityAccountNumber));
			rs.next();
			billTypeStatusDB = rs.getString("Paperless");
			if (billTypeStatusDB.equals("0") || billTypeStatusDB.equals("2"))
				billTypeEnrolledDateDB = rs.getString("PaperlessUpdateDate").substring(0, 10);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		if (billTypeStatusDB.equals("0")) {
			billTypeStatusDB = "Enrolled in Paper Bill";
		} else if (billTypeStatusDB.equals("1")) {
			billTypeStatusDB = "Enrolled in Paperless Bill";
			billTypeEnrolledDateDB = "";
		} else if (billTypeStatusDB.equals("2")) {
			billTypeStatusDB = "Enrolled in both Paper and Paperless";
		}
		System.out.println(billTypeStatusDB);
		System.out.println(billTypeEnrolledDateDB);
		softAssert.assertEquals(billTypeStatusUI, billTypeStatusDB);
		if (billTypeStatusDB.equals("0") || billTypeStatusDB.equals("2"))
			softAssert.assertEquals(billTypeEnrolledDateDB,
					DateUtil.changeDateFormatFromDateCSP(billTypeEnrolledDateUI, "yyyy-MM-dd"));
		softAssert.assertAll();

		clickLnkBackToSearch();
	}

	static Map<String, String> csrConfig = CSPConfiguration.dateMetricsConfig;
	static String currencyConfig = csrConfig.get("Currency");
	static String dateConfig = csrConfig.get("Date format");

	public void verifyBilling360View() throws Exception {
		log.info("Test Case execution for - Verify the Billing Tab in CSR- is initiated");
		// Runner.test.log(Status.INFO, "C72545,C72546, C72547Verify the Billing Tab in
		// CSR in CSR");
//		testadminloginPage.adminLoginReusable();
//		pageUtil.verifyCurrentPageTitle(Utils.getRbText().getString("expectedAdminHomePageTitle"));
//		pageUtil.assertCurrentPageUrl(Utils.getRbText().getString("expectedAdminHomePageUrl"));
//		testHomePage.navigateCSRWorkBenchWidget();
		// Entering value in the search box
		// Thread.sleep(5000);
		// enterValueSearchFilter(Utils.getRbData().getString("utilityANumber"));
		// pageUtil.explicitWaitForWebElementAttribute(driver,
		// adminCsrPage.getdivPageLoad(), "style", "display: none;");
		// pageUtil.click(adminCsrPage.getLnkServiceAccountNumber());
		// pageUtil.WaitForWebElementToInvisible(driver, adminCsrPage.getLoadingImg());
		// pageUtil.switchToFrame(0);
		// pageUtil.pause(3000);

		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		String accountNumber = "411004248679";
		enterValueSearchFilter(accountNumber);
		clickLnkServiceAccountNumber();
		waitForImgCustomerDetailsLoadingToInvisible();
		switchToFrame(0);
		waitForImgCustomerDetailsLoadingToInvisible();

		Assert.assertTrue(isLnkBillingTabVisible());
		clickBillingTab();
		// pageUtil.click(adminCsrPage.getLnkBillingTab());
		String result = getTotalDataCountOnBillStatement();
		int count = Integer.parseInt(result);
		ArrayList<String> statementDate_UI = new ArrayList<String>();
		ArrayList<String> amountDue_UI = new ArrayList<String>();
		ArrayList<String> dueDate_UI = new ArrayList<String>();
		ArrayList<Double> amountDueDOUBLE_UI = new ArrayList<Double>();

		System.out.println("Currency used is: " + currencyConfig);

		int page = 1;
		if (result.equals("0")) {
			// pageUtil.verifyObjectLabel(adminCsrPage.getLblNoRecordsFound(),Utils.getRbTextValue("txtTblBodyEmptyAcp"));
		} else {
			String billingHistoryQuery = SQLQueries.getBillingHistoryDetails(accountNumber);
			ResultSet rs = DataBaseUtils.getResultSet(billingHistoryQuery);
			ArrayList<GetBillHistoryData> billHistoryData = new ArrayList<GetBillHistoryData>();
			GetBillHistoryData obj1;
			while (rs.next()) {
				obj1 = new GetBillHistoryData(
						DateUtil.changeSrcToDestDateOnPaymentSuccess(rs.getString("statementdate"), "MMMM d, yyyy"),
						DateUtil.changeStringToDateInFormat(rs.getString("DueDate"), "MMMM d, yyyy"),
						rs.getString("PreviousBalance"), rs.getString("PaymentReceived"),
						rs.getString("currentcharges"), rs.getString("TotalAmountDue"));
				billHistoryData.add(obj1);
			}
			do {
				// all Statement Date
				List<WebElement> statementDate = getLstLblBillingBillDate();
				for (WebElement webElement : statementDate) {
					statementDate_UI.add(webElement.getText());
				}
				// all Amount Due
				List<WebElement> amountDue = getLstLblBillingBillAmount();
				for (WebElement webElement : amountDue) {
					String amount = webElement.getText().replace(currencyConfig, "").replace(",", "").trim();
					amountDue_UI.add(amount);
					amountDueDOUBLE_UI.add(Double.parseDouble(amount));
				}
				// all Due Date
				List<WebElement> dueDate = getLstlnkBillingViewBillPdf();
				for (WebElement webElement : dueDate) {
					dueDate_UI.add(webElement.getText());
				}
				String remaining = getRemainingBillingDataCount();
				int remainingcount = Integer.parseInt(remaining);
				page = page + 1;
				if (remainingcount < count) {
					driver.findElement(By.xpath("//li[contains(@class,'paginate_button')]//a[text()='" + page + "']"))
							.click();
					waitForImgCustomerDetailsLoadingToInvisible();// pageUtil.pause(6000);
				}
				if (remainingcount == count)
					break;

			} while (isNextButtonEnabled());
			for (int i = 0; i < count; i++) {
				Assert.assertEquals(statementDate_UI.get(i), billHistoryData.get(i).getStatementDate());
				Assert.assertEquals(amountDue_UI.get(i), billHistoryData.get(i).getTotalAmountDue());
				Assert.assertEquals(dueDate_UI.get(i), billHistoryData.get(i).getBillDueDate());
			}
			// click 1 to come back to Page 1
			clickBtnPg1Billing();
			List<WebElement> arrows = getArrowExpandBilling();
			for (int i = 0; i < 2; i++) {
				arrows.get(i).click();
				Thread.sleep(4000);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("javascript:window.scrollBy(0,300)");
			}
			List<WebElement> previousBalance = getLblPreviousBalanceBilling();
			List<WebElement> paymentReceived = getLblPaymentReceivedBilling();
			List<WebElement> currentCharges = getLblCurrentChargesBilling();
			List<WebElement> totalAmountDue = getLbltotalAmountDueBilling();
			ArrayList<String> PB = new ArrayList<>();
			ArrayList<String> PR = new ArrayList<>();
			ArrayList<String> CC = new ArrayList<>();
			ArrayList<String> TBD = new ArrayList<>();
			for (int i = 0; i < 2; i++) {
				System.out.println("------------" + i + "------------------");
				System.out.println("Previous Balance->"
						+ previousBalance.get(i).getText().replace(currencyConfig, "").replace("+", "").trim());
				PB.add(symbolConverterForUI(previousBalance.get(i).getText()));
				System.out.println("Payment Received->"
						+ paymentReceived.get(i).getText().replace(currencyConfig, "").replace("+", "").trim());
				PR.add(symbolConverterForUI(paymentReceived.get(i).getText()));
				System.out.println("Current Charges->"
						+ currentCharges.get(i).getText().replace(currencyConfig, "").replace("+", "").trim());
				CC.add(symbolConverterForUI(currentCharges.get(i).getText()));
				System.out.println("Total Amount Due->"
						+ totalAmountDue.get(i).getText().replace(currencyConfig, "").replace("+", "").trim());
				TBD.add(symbolConverterForUI(totalAmountDue.get(i).getText()));

			}
			for (int i = 0; i < 2; i++) {
				Assert.assertEquals(PB.get(i), billHistoryData.get(i).getPreviousBalance());
				Assert.assertEquals(PR.get(i), billHistoryData.get(i).getPaymentReceived());
				Assert.assertEquals(CC.get(i), billHistoryData.get(i).getCurrentCharges());
				Assert.assertEquals(TBD.get(i), billHistoryData.get(i).getTotalAmountDue());
			}
			// to validate Amount Due sorting is working fine
			clickLblAmountDueHeaderBilling();
			Thread.sleep(4000);
			ArrayList<Double> amountDueAFTERSORTING_UI = new ArrayList<Double>();
			int page1 = 1;
			do {
				// all Amount Due
				List<WebElement> amountDue = getLstLblBillingBillAmount();
				for (WebElement webElement : amountDue) {
					String amount = webElement.getText().replace(currencyConfig, "").replace(",", "").trim();
					amountDueAFTERSORTING_UI.add(Double.parseDouble(amount));
				}
				String remaining = getRemainingBillingDataCount();
				int remainingcount = Integer.parseInt(remaining);
				page1 = page1 + 1;
				if (remainingcount < count) {
					driver.findElement(By.xpath("//li[contains(@class,'paginate_button')]//a[text()='" + page1 + "']"))
							.click();
					waitForImgCustomerDetailsLoadingToInvisible();
				}
				if (remainingcount == count)
					break;

			} while (isNextButtonEnabled());
			// printing UI elements after clicking UI ARROW
			System.out.println("UI element after clicking ARROW");
			for (Double d : amountDueAFTERSORTING_UI) {
				System.out.print(d + " ,");
			}
			Collections.sort(amountDueDOUBLE_UI);
			// printing sorted array
			System.out.println("UI element after sorting");
			for (Double d : amountDueDOUBLE_UI) {
				System.out.print(d + " ,");
			}
			Assert.assertTrue(amountDueAFTERSORTING_UI.equals(amountDueDOUBLE_UI));

		}
		log.info("Test Case execution for -  verifyBilling360View - is Completed.");
	}

	public String getTotalDataCountOnBillStatement() {
		String pageRecords = getTotalEnteriesBilling();
		String[] records = pageRecords.split("f ");
		String filterCount = records[1];
		String[] count = filterCount.split("\\s");
		String record = count[0];// Satya 1 ->0
		if (record.split(",").length > 1) {
			String[] digits = record.split(",");
			record = digits[0] + digits[1];

		}
		return record;
	}

	public String getRemainingBillingDataCount() {
		String pageRecords = getTotalEnteriesBilling();
		String[] records = pageRecords.split(" ");
		String record = records[3];
		// String[] count = filterCount.split("\\s");
		// String record = count[0];// Satya 1 ->0
		return record;
	}

	public static String symbolConverterForUI(String number) {

		String balance;

		if (number.contains("+")) {
			// balance = number.replace("$", "").replace("₹", "").replace(",",
			// "").replace("+", "").replace("A", "").replace("ED", "").trim();
			balance = number.replace(currencyConfig, "").replace(",", "").replace("+", "").trim();
		}

		else if (number.contains("-")) {
			// balance = number.replace("$", "").replace("₹", "").replace(",", "").replace("
			// ", "").replace("A", "").replace("ED", "").trim();
			balance = number.replace(currencyConfig, "").replace(",", "").replace(" ", "").trim();

		}

		else {

			// balance = number.replace("$", "").replace("₹", "").replace(",",
			// "").replace("A", "").replace("ED", "").trim();
			balance = number.replace(currencyConfig, "").replace(",", "").trim();

		}
		System.out.println(balance);
		return balance;

	}

	public void verifyPayment360View() throws Exception {
		log.info("Test Case execution for - Verify the Billing Tab in CSR- is initiated");
		String accountNumber = "411007037392";// R100000112 2001079796 411007037392 2001079796
		// Runner.test.log(Status.INFO, "C72549, C72553-Verify the Billing Tab in CSR in
		// CSR");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();

		enterValueSearchFilter(accountNumber);
		clickLnkServiceAccountNumber();
		waitForImgCustomerDetailsLoadingToInvisible();
		switchToFrame(0);
		waitForImgCustomerDetailsLoadingToInvisible();
		waitForPageToLoad();
		Assert.assertTrue(isLnkBillingTabVisible());
		clickBillingTab();
		waitForImgCustomerDetailsLoadingToInvisible();
		explicitWaitForLnkPaymentSubTab();
		Assert.assertTrue(isLnkPaymentSubTabVisible());
		Assert.assertTrue(isLnkBillingStatementSubTabVisible());

		clickLnkPaymentSubTab();
		waitForImgCustomerDetailsLoadingToInvisible();
		ArrayList<String> date_UI = new ArrayList<String>();
		ArrayList<String> paymentType_UI = new ArrayList<String>();
		ArrayList<String> channel_UI = new ArrayList<String>();
		ArrayList<String> status_UI = new ArrayList<String>();
		ArrayList<String> amount_UI = new ArrayList<String>();
		int page = 1;
		String result = getTotalDataCountOnPayments();
		int count = Integer.parseInt(result);
		String paymentHistoryQuery = SQLQueries.getPaymentsHistoryDetails(accountNumber);
		ResultSet rs = DataBaseUtils.getResultSet(paymentHistoryQuery);
		ArrayList<GetPaymentHistoryData> paymentHistoryData = new ArrayList<GetPaymentHistoryData>();
		GetPaymentHistoryData obj;
		while (rs.next()) {
			String paymentType = rs.getString("PaymentType");
			if (paymentType == null) {
				paymentType = "N/A";
			}
			String channel = rs.getString("Channel");
			if (channel == null) {
				channel = "N/A";
			}
			obj = new GetPaymentHistoryData(
					DateUtil.changeSrcToDestDateOnPaymentSuccess(rs.getString("Date"), "MMMM d, yyyy"), paymentType,
					channel, rs.getString("status"), rs.getString("Amount"));
			paymentHistoryData.add(obj);
		}
		do {
			// all Date
			List<WebElement> date = getLblDatePayment();
			for (WebElement webElement : date) {
				date_UI.add(webElement.getText());
			}
			// all Payment Type
			List<WebElement> paymentType = getLblPaymentTypePayment();
			for (WebElement webElement : paymentType) {
				// String amount = webElement.getText().replace("$", "").trim();
				if (webElement.getText().equals("Saved Payment Method")) {
					paymentType_UI.add("Tokenized");
				} else {
					paymentType_UI.add(webElement.getText());
				}

			}
			// all Channel
			List<WebElement> channel = getLblChannelPayment();
			for (WebElement webElement : channel) {
				channel_UI.add(webElement.getText());
			}
			// all Status
			List<WebElement> status = getLblStatusPayment();
			for (WebElement webElement : status) {
				status_UI.add(webElement.getText());
			}
			// all Amount
			List<WebElement> amount = getLblAmountPayment();
			for (WebElement webElement : amount) {
				String amount1 = webElement.getText().replace(currencyConfig, " ").replace(",", "").trim();
				amount_UI.add(amount1);
			}
			String remaining = getRemainingPaymentDataCount();
			int remainingcount = Integer.parseInt(remaining);
			page = page + 1;
			if (remainingcount < count) {
				driver.findElement(By.xpath(
						"//div[@id='jqxgridpayment_Grid_paginate']//li[contains(@class,'paginate_button')]//a[text()='"
								+ page + "']"))
						.click();
				waitForImgCustomerDetailsLoadingToInvisible();

			}
			if (remainingcount == count)
				break;

		} while (isBtnNextPaymentEnabled());
		SoftAssert softAssert = new SoftAssert();
		if (status_UI.size() == paymentHistoryData.size()) {

			for (int i = 0; i < paymentHistoryData.size(); i++) {
				softAssert.assertEquals(date_UI.get(i), paymentHistoryData.get(i).getDate());
				softAssert.assertEquals(paymentType_UI.get(i), paymentHistoryData.get(i).getPaymentType());
				softAssert.assertEquals(channel_UI.get(i), paymentHistoryData.get(i).getChannel());
				softAssert.assertEquals(status_UI.get(i), paymentHistoryData.get(i).getStatus());
				softAssert.assertEquals(amount_UI.get(i), paymentHistoryData.get(i).getAmount());
				log.info(date_UI.get(i) + " : " + paymentType_UI.get(i) + " : " + channel_UI.get(i) + " : "
						+ status_UI.get(i) + " : " + amount_UI.get(i));

			}

		} else {
			Assert.assertTrue(false);
		}

		// Thread.sleep(2000);
		// Next button should be disabled if we are last page
		Assert.assertTrue(getBtnNextPaymentAttributeValue("class").contains("disabled"));
		// Previous button should be enabled
		Assert.assertFalse(getBtnPreviousPaymentAttributeValue("class").contains("disabled"));
		// click on 1 button
		clickBtnPg1Payment();
		waitForPageToLoad();
		// Previous should be disabled as we are on Pg1
		Assert.assertTrue(getBtnPreviousPaymentAttributeValue("class").contains("disabled"));
		// Next button should be enabled if we are 1st page
		Assert.assertFalse(getBtnNextPaymentAttributeValue("class").contains("disabled"));
		log.info("Test Case execution for -  verifyPayment360View - is Completed.");

	}

	public String getRemainingPaymentDataCount() {
		String pageRecords = getLblTotalEnteriesPaymentTransactions();
		String[] records = pageRecords.split(" ");
		String record = records[3];
		// String[] count = filterCount.split("\\s");
		// String record = count[0];// Satya 1 ->0
		return record;
	}

	public String getTotalDataCountOnPayments() {
		String pageRecords = getLblTotalEnteriesPaymentTransactions();
		String[] records = pageRecords.split("f ");
		String filterCount = records[1];
		String[] count = filterCount.split("\\s");
		String record = count[0];// Satya 1 ->0
		if (record.split(",").length > 1) {
			String[] digits = record.split(",");
			record = digits[0] + digits[1];

		}
		return record;
	}

	public void verifyLoginToCustomerPortal() throws Exception {
		log.info("Test Case execution for - verifyLoginToCustomerPortal  - is Initiated");
		// Runner.test.log(Status.INFO, "C72517, C72520 Verify user redirect to the User
		// in New tab from SCM Tab");

		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();

		enterValueSearchFilter(Configuration.toString("utilityAccountNumber"));
		clickLnkServiceAccountNumber();
		waitForImgCustomerDetailsLoadingToInvisible();
		switchToFrame(0);
		waitForImgCustomerDetailsLoadingToInvisible();
		waitForPageToLoad();

		// pageUtil.pause(5000);
		Assert.assertTrue(isBtnLoginToCustomerDisplayed(), "Login to customer portal is not appearing");
		clickBtnLoginToCustomer();
		waitForPageToLoad();
		// pageUtil.pause(10000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		String handleName = tabs.get(1);
		switchToWindow(handleName);
		waitForPageToLoad();

		String scpURL = driver.getCurrentUrl();
		Assert.assertTrue(scpURL.contains("SCP"), "Login to Customer portal is not redirecting in correct url ");
		// pageUtil.closeChildWindow();
		// driver.close();
		switchToWindow(tabs.get(0));
		closeAllOtherWindows(driver, driver.getWindowHandle());
		log.info("Test Case execution for -  verifyLoginToCustomerPortal - is Completed.");

	}

	public void verifyMarketingPreferences() throws InterruptedException, SQLException {

		log.info("Test Case execution for - Verify the Last Login Details for Customer in CSR - is initiated");
		// Runner.test.log(Status.INFO, "C72573 Verify the Last Login Details for
		// Customer in CSR");
		SoftAssert softAssert = new SoftAssert();
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();

		enterValueSearchFilter(Configuration.toString("utilityAccountNumber"));

		String userName = "abc@gmail.Com"; // abc@gmail.Com //bodhit+test
		selectByVisibleTextDdSearchItem("Username");
		populateTxtSearchCustomer(userName);
		clickBtnSearch();
		waitForPageToLoad();
		clickLnkServiceAccountNumber();
		waitForImgCustomerDetailsLoadingToInvisible();
		switchToFrame(0);
		waitForImgCustomerDetailsLoadingToInvisible();
		waitForPageToLoad();

		// Verify Marketing Pref UI
		assertTrue(verifyLblChkBoxNewsReleasesVisible());
		assertTrue(verifyLblChkBoxNewslettersVisible());
		assertTrue(verifyLblChkBoxServiceOfferingsVisible());
		assertTrue(verifyLblChkBoxEnergySavingsToolkitsVisible());
		assertTrue(verifyLblChkBoxCoolTipsBrochuresVisible());
		assertTrue(verifyLblChkBoxCommunityBenefitProgramsVisible());

		assertTrue(verifyChkBoxNewsReleasesVisible());
		assertTrue(verifyChkBoxNewslettersVisible());
		assertTrue(verifyChkBoxServiceOfferingsVisible());
		assertTrue(verifyChkBoxEnergySavingToolkitsVisible());
		assertTrue(verifyChkBoxCoolTipsBrochuresVisible());
		assertTrue(verifyChkBoxCommunityBenefitProgramsVisible());

		// Verify Marketing Pref labels value
		assertTrue(getLblChkBoxNewsReleasesLabel()
				.contains(adminCustomerTextProp.getPropValue("txtLblChkBoxNewsReleasesAmpp")));
		assertTrue(getLblChkBoxNewslettersLabel()
				.contains(adminCustomerTextProp.getPropValue("txtLblChkBoxNewslettersAmpp")));
		assertTrue(getLblChkBoxServiceOfferingsLabel()
				.contains(adminCustomerTextProp.getPropValue("txtLblChkBoxServiceOfferingsAmpp")));
		assertTrue(getLblChkBoxEnergySavingsToolkitsLabel()
				.contains(adminCustomerTextProp.getPropValue("txtLblChkBoxEnergySavingsToolkitsAmpp")));
		assertTrue(getLblChkBoxCoolTipsBrochuresLabel()
				.contains(adminCustomerTextProp.getPropValue("txtLblChkBoxCoolTipsBrochuresAmpp")));
		assertTrue(getLblChkBoxCommunityBenefitProgramsLabel()
				.contains(adminCustomerTextProp.getPropValue("txtLblChkBoxCommunityBenefitProgramsAmpp")));

		// Verify DB validation
		String query = SQLQueries.getMarketingPrefQuery(userName);
		ResultSet rs = DataBaseUtils.getResultSet(query);
		while (rs.next()) {
			String id = rs.getString("PreferenceId");
			if (id.equals("1")) {
				verifyChkBoxNewsReleasesSelected();
			} else if (id.equals("2")) {
				verifyChkBoxServiceOfferingsSelected();
			} else if (id.equals("3")) {
				verifyChkBoxNewslettersSelected();
			} else if (id.equals("4")) {
				verifyChkBoxEnergySavingToolkitsSelected();
			} else if (id.equals("5")) {
				verifyChkBoxCoolTipsBrochuresSelected();
			} else if (id.equals("6")) {
				verifyChkBoxCommunityBenefitProgramsSelected();
			}
		}

		if (getChkBoxNewslettersState() == false) {
			verifyChkBoxNewslettersNotSelected();
			clickChkBoxNewsletters();
			verifyChkBoxNewslettersSelected();
		} else {
			clickChkBoxNewsletters();
			verifyChkBoxNewslettersNotSelected();
		}

		if (getChkBoxNewsReleasesState() == false) {
			verifyChkBoxNewsReleasesNotSelected();
			clickChkBoxNewsReleases();
			verifyChkBoxNewsReleasesSelected();
		} else {
			clickChkBoxNewsReleases();
			verifyChkBoxNewsReleasesNotSelected();
		}

		clickBtnUpdateMarketingPrefernce();
		waitForPageToLoad();
		explicitWaitForAlertPopUp(driver);
		acceptAlert();

		query = SQLQueries.getMarketingPrefQuery(userName);
		rs = DataBaseUtils.getResultSet(query);
		while (rs.next()) {
			String id = rs.getString("PreferenceId");
			if (id.equals("1")) {
				verifyChkBoxNewsReleasesSelected();
			} else if (id.equals("2")) {
				verifyChkBoxServiceOfferingsSelected();
			} else if (id.equals("3")) {
				verifyChkBoxNewslettersSelected();
			} else if (id.equals("4")) {
				verifyChkBoxEnergySavingToolkitsSelected();
			} else if (id.equals("5")) {
				verifyChkBoxCoolTipsBrochuresSelected();
			} else if (id.equals("6")) {
				verifyChkBoxCommunityBenefitProgramsSelected();
			}
		}

		waitForPageToLoad();

		rs = DataBaseUtils.getResultSet("select * from [User] where UserName='" + userName + "'");
		rs.next();
		String emailId = rs.getString("EmailID");
		String firstName = rs.getString("FirstName");
		String lastName = rs.getString("LastName");

		query = SQLQueries.getMarketingPrefEmailQuery(emailId, "SCM- Marketing Preferences for");
		rs = DataBaseUtils.getResultSet(query);
		if (rs.next()) {
			String mailBody = rs.getString("Message");
			System.out.println("====> " + mailBody);
			String sUserName = "Dear " + firstName + " " + lastName;
			System.out.println("===> " + sUserName);

			assertTrue(mailBody.contains(sUserName));
			assertTrue(mailBody.contains(
					"We at SCM constantly look for ways to make our customers happy. This is to inform you that the below marketing preferences have been updated successfully."));
			// pageUtil.verifyStringValueContains(mailBody, "This is to inform you that you
			// have successfully");
			assertTrue(mailBody.contains("To subscribe/unsubscribe"));
			assertTrue(mailBody.contains("click here"));
			assertTrue(mailBody.contains("to visit the SCM portal."));
			assertTrue(mailBody.contains("DO NOT REPLY TO THIS MESSAGE"));
//			pageUtil.verifyStringValueContains(mailBody, "News Releases");
//			pageUtil.verifyStringValueContains(mailBody, "Newsletters");
//			pageUtil.verifyStringValueContains(mailBody, "News Releases");
			assertTrue(mailBody.contains(
					"If you need assistance please contact our customer service by email at Support@SEW.ai or by phone at (111) 111-1100 or chat online by visiting us at www.smartenergywater.com."));

		}
		log.info("Test Case execution for - Verify the Last Login Details for Customer in CSR - is Finished");
		softAssert.assertAll();
	}

	public void verifyUserDetails360View() throws SQLException {

		log.info("Test Case execution for - Verify the User Status Details for Customer in CSR - is initiated");
		// Runner.test.log(Status.INFO, "C72573 Verify the Guest Tab Details for
		// Customer in CSR");
		SoftAssert softAssert = new SoftAssert();

		String utilityAccountNumber = "411007037392";
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();

		enterValueSearchFilter(Configuration.toString("utilityAccountNumber"));
		clickLnkServiceAccountNumber();
		waitForImgCustomerDetailsLoadingToInvisible();
		switchToFrame(0);
		waitForImgCustomerDetailsLoadingToInvisible();
		waitForPageToLoad();

		isLnkProfileTabVisible();

		String query = "select * from account where  UtilityAccountNumber='" + utilityAccountNumber + "'";

		ResultSet resultSet = DataBaseUtils.getResultSet(query);
		String accountStatus;
		String status = null;

		while (resultSet.next()) {

			accountStatus = resultSet.getString("status");
			if (accountStatus.equals("0")) {
				status = "Pending Activation";
			} else if (accountStatus.equals("1")) {
				status = "Active";
			}

		}

		assertTrue(getLblAccountStatusLabel().contains(adminCustomerTextProp.getPropValue("txtLblAccountStatusAmpp")));
		assertTrue(getLblValAccountStatusLabel().contains(status));

		log.info("Test Case execution for - Verify the User Status Details for Customer in CSR - is Finished");
	}

	public void verifyLastLogin() throws SQLException {

		log.info("Test Case execution for - Verify the Last Login Details for Customer in CSR - is initiated");
		// Runner.test.log(Status.INFO, "C72573 Verify the Last Login Details for
		// Customer in CSR");
		SoftAssert softAssert = new SoftAssert();

		String utilityAccountNumber = "411002870300";

		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();

		enterValueSearchFilter(utilityAccountNumber);// enterValueSearchFilter(Configuration.toString("utilityAccountNumber"));
		clickLnkServiceAccountNumber();
		waitForImgCustomerDetailsLoadingToInvisible();
		switchToFrame(0);
		waitForImgCustomerDetailsLoadingToInvisible();
		waitForPageToLoad();

		String userName = "ranbir";
		// pageUtil.selectByVisibleText(adminCsrPage.getddSearchItem(), "Username");
		// pageUtil.enterTextValue(adminCsrPage.gettxtSearchCustomer(), userName);
		// pageUtil.pause(2000);
		// pageUtil.click(adminCsrPage.getbtnSearch());
		// pageUtil.pause(15000);

		clickLnkUserInteractionTab();
		waitForPageToLoad();
		// Assert.assertTrue(pageUtil.verifyElementVisible(By.xpath("//a[contains(text(),'Profile')]")));
		// pageUtil.assertAttributeValue(By.xpath("//a[contains(text(),'Profile')]"),
		// "class", "active");

		String query = "select * from [User] where UserName='" + userName + "'";

		ResultSet resultSet = DataBaseUtils.getResultSet(query);
		String lastLoginDateTime = null;
		String status = null;

		while (resultSet.next()) {

			lastLoginDateTime = resultSet.getString("LastLoginAttempt");

		}

		String cspFormatDateTime = DateUtil.converterDateFromDBtoCSP(lastLoginDateTime);

		ArrayList<String> expectedHeaders = new ArrayList<>();
		expectedHeaders.add("MODULE NAME");
		expectedHeaders.add("BY USER");
		expectedHeaders.add("EVENT NAME");
		expectedHeaders.add("EVENT DETAIL");
		expectedHeaders.add("IS CSR?");
		expectedHeaders.add("DATE/TIME");
		ArrayList<String> actualHeaders = new ArrayList<>();

		List<WebElement> headers = getLstInterationTableHeaders();
		for (WebElement header : headers) {
			actualHeaders.add(header.getText());
		}

		Assert.assertTrue(expectedHeaders.containsAll(actualHeaders), "All headers not match");

		String moduleNameExpected = "Login";
		String moduleNameActual = null;
		String byUserExpected = "Ranbir Kapoor";
		String byUserActual = null;
		String eventNameExpected = "Successfully Logged In.";
		String eventNameActual = null;
		String eventDetailExpected = "User has successfully logged in.";
		String eventDetailActual = null;
		String isCSRExpected = "No";
		String isCSRActual = null;

		int j = 0;

		List<WebElement> dateTimeUI = getLstDateTimeInterationTable();

		for (int i = 0; i < dateTimeUI.size(); i++) {

			if (dateTimeUI.get(i).getText().contains(cspFormatDateTime)) {
				j = i + 1;

				moduleNameActual = driver
						.findElement(By.xpath("//table[@id='jqxgridInteraction_Grid']/tbody/tr[" + j + "]/td[1]"))
						.getText();
				byUserActual = driver
						.findElement(By.xpath("//table[@id='jqxgridInteraction_Grid']/tbody/tr[" + j + "]/td[2]"))
						.getText();
				eventNameActual = driver
						.findElement(By.xpath("//table[@id='jqxgridInteraction_Grid']/tbody/tr[" + j + "]/td[3]"))
						.getText();
				eventDetailActual = driver
						.findElement(By.xpath("//table[@id='jqxgridInteraction_Grid']/tbody/tr[" + j + "]/td[4]"))
						.getText();
				isCSRActual = driver
						.findElement(By.xpath("//table[@id='jqxgridInteraction_Grid']/tbody/tr[" + j + "]/td[5]"))
						.getText();

				Assert.assertEquals(
						driver.findElement(By.xpath("//table[@id='jqxgridInteraction_Grid']/tbody/tr[" + j + "]/td[1]"))
								.getText(),
						moduleNameExpected);
				break;
			}
		}

		Assert.assertEquals(moduleNameExpected, moduleNameActual);
		Assert.assertEquals(byUserExpected, byUserActual);
		Assert.assertEquals(eventNameExpected, eventNameActual);
		Assert.assertEquals(eventDetailExpected, eventDetailActual);
		Assert.assertEquals(isCSRExpected, isCSRActual);

		log.info("Test Case execution for - Verify the Last Login Details for Customer in CSR - is Finished");
	}

	public void verifyBillTypeDetails360View() throws SQLException {

		log.info("Test Case execution for - Verify the Bill Type Details for Customer in CSR - is initiated");
		// Runner.test.log(Status.INFO, "C72573 Verify the Guest Tab Details for
		// Customer in CSR");
		SoftAssert softAssert = new SoftAssert();

		String utilityAccountNumber = Configuration.toString("utilityAccountNumber");// "411007037392";
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();

		enterValueSearchFilter(utilityAccountNumber);
		clickLnkServiceAccountNumber();
		waitForImgCustomerDetailsLoadingToInvisible();
		switchToFrame(0);
		waitForImgCustomerDetailsLoadingToInvisible();
		waitForPageToLoad();
		isLnkProfileTabVisible();

		assertTrue(getLblStatusLabel().contains(adminCustomerTextProp.getPropValue("txtLblStatusAmpp")));
		assertTrue(getLblUpdatedDateLabel().contains(adminCustomerTextProp.getPropValue("txtLblUpdatedDateAmpp")));

		String query = "select * from account where  UtilityAccountNumber='" + utilityAccountNumber + "'";

		ResultSet resultSet = DataBaseUtils.getResultSet(query);
		String paperless;
		String updatedDateDB = null;
		String paperStatus = null;
		String updatedDateCSPFormat = null;

		while (resultSet.next()) {

			paperless = resultSet.getString("paperless");
			if (paperless.equals("0")) {
				paperStatus = "Enrolled in Paper Bill";
			}
			if (paperless.equals("1")) {
				paperStatus = "Enrolled in Paperless Bill";
			}
			updatedDateDB = resultSet.getString("UpdatedDate").split(" ")[0];
			updatedDateCSPFormat = DateUtil.changeDateFormatOfDBAsPerCSP(updatedDateDB, "MMMM d, yyyy");

		}

		assertTrue(getLblPaperBillStatusLabel().contains(paperStatus));

		// pageUtil.assertObjectLabel(By.cssSelector("#lblEnrolledDate"),
		// updatedDateCSPFormat);

		log.info("Test Case execution for - Verify the Bill Type Details for Customer in CSR - is Finished");
	}

	public void verifyEfficiency360View() throws Exception {
		log.info("Test Case execution for - Verify the Efficiency Tab Details for Customer in CSR - is initiated");
		// Runner.test.log(Status.INFO, "C72573 Verify the Efficiency Tab Details for
		// Customer in CSR");
		SoftAssert softAssert = new SoftAssert();

		String accountNumber = Configuration.toString("utilityAccountNumber");// "411007037392";

		// String utilityAccountNumber =
		// Configuration.toString("utilityAccountNumber");// "411007037392";
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();

		enterValueSearchFilter(accountNumber);
		clickLnkServiceAccountNumber();
		waitForImgCustomerDetailsLoadingToInvisible();
		switchToFrame(0);
		waitForImgCustomerDetailsLoadingToInvisible();
		waitForPageToLoad();

		// getting value of efficiency from DB for Rebate
		ArrayList<String> titleRebateOpted = new ArrayList<String>();
		String optedEfficiencyByAccountNumber = SQLQueries.getOptedEfficiencyByAccountNumber(accountNumber, "3");
		ResultSet rs = DataBaseUtils.getResultSet(optedEfficiencyByAccountNumber);
		while (rs.next()) {
			titleRebateOpted.add(rs.getString("Title").toString().trim());
			log.info("Rebate Opted in DB :" + rs.getString("Title").toString().trim());
			System.out.println(titleRebateOpted);
		}
		// getting value of efficiency from DB for Saving tips
		ArrayList<String> titleSavingOpted = new ArrayList<String>();
		String optedEfficiencyByAccountNumberSavingTips = SQLQueries.getOptedEfficiencyByAccountNumber(accountNumber,
				"1");
		rs = DataBaseUtils.getResultSet(optedEfficiencyByAccountNumberSavingTips);
		while (rs.next()) {
			titleSavingOpted.add(rs.getString("Title").toString().trim());
			log.info("Saving Tips Opted in DB :" + rs.getString("Title").toString().trim());
			System.out.println(titleSavingOpted);
		}
		navigateTabCustomerDetailSection("Ways To Save");
		waitForPageToLoad();

		// Getting values from UI for Rebate opted
		ArrayList<String> titleRebateOptedUI = new ArrayList<String>();
		List<WebElement> rebateOpted = getLblRebateOpted();
		for (WebElement rebateUITitle : rebateOpted) {
			titleRebateOptedUI.add(rebateUITitle.getText().trim());
			log.info("Rebates Opted displayed in UI :" + rebateUITitle.getText().trim());
			System.out.println(titleRebateOptedUI);
		}
		// Getting values from UI for Rebate Not opted
		ArrayList<String> titleRebateNotOptedUI = new ArrayList<String>();
		List<WebElement> rebateNotOpted = getLblRebateNotOpted();
		for (WebElement rebateNotOptedUITitle : rebateNotOpted) {
			titleRebateNotOptedUI.add(rebateNotOptedUITitle.getText().trim());
			log.info("Rebates Not Opted displayed in UI :" + rebateNotOptedUITitle.getText().trim());
			System.out.println(titleRebateNotOptedUI);
		}
		// Getting values from UI for Saving Tips opted
		ArrayList<String> titleSavingtipsOptedUI = new ArrayList<String>();
		List<WebElement> savingTipsOpted = getLblSavingTipsOpted();
		for (WebElement savingTipsOptedUITitle : savingTipsOpted) {
			titleSavingtipsOptedUI.add(savingTipsOptedUITitle.getText().trim());
			log.info("Savings Tips Opted displayed in UI :" + savingTipsOptedUITitle.getText().trim().trim());
			System.out.println(titleSavingtipsOptedUI);
		}
		// Getting values from UI for Saving Tips Not opted
		ArrayList<String> titleSavingtipsNotOptedUI = new ArrayList<String>();
		List<WebElement> savingTipsNotOpted = getLblSavingTipsNotOpted();
		for (WebElement savingTipsNotOptedUITitle : savingTipsNotOpted) {
			titleSavingtipsNotOptedUI.add(savingTipsNotOptedUITitle.getText().trim());
			log.info("Savings Tips Not Opted displayed in UI :" + savingTipsNotOptedUITitle.getText().trim());
			System.out.println(titleSavingtipsNotOptedUI);
		}
		// Verify Opted Rebates
		Assert.assertTrue(titleRebateOpted.containsAll(titleRebateOptedUI));
		log.info("Verified Rebates from DB and UI :" + titleRebateOpted.toString() + " is equal with "
				+ titleRebateOptedUI.toString());
		// Verify Opted Saving Tips
		Assert.assertTrue(titleSavingOpted.containsAll(titleSavingtipsOptedUI));
		log.info("Verified Saving tips from DB and UI :" + titleSavingOpted.toString() + " is equal with "
				+ titleSavingtipsOptedUI.toString());
		// verify show details button
		List<WebElement> showButtons = getBtnShowDetailsEfficiency();
		for (WebElement showBtn : showButtons) {

			waitForElementToBeVisible(showBtn);
			scrollPageToElement(showBtn);
			click(showBtn);
			waitForPageToLoad();
			String headingEfficiency = getLblShowDetailsEfficiencyHeadingLabel().trim();// adminCsrPage.getlblShowDetailsEfficiencyHeading()
			waitForLblShowDetailsEfficiency();
			String despText = getLblShowDetailsEfficiencyLabel().trim();
			log.info("Description of " + headingEfficiency + "efficiency is :" + despText);
			softAssert.assertTrue(isBtnHideDetailsEfficiencyDisplayed());
			clickBtnHideDetailsEfficiency();
			// verify desc from DB
			String efficiencyDesc = SQLQueries.getEfficiencyDescription(headingEfficiency, "3");
			rs = DataBaseUtils.getResultSet(efficiencyDesc);
			// rs.next();
			while (rs.next()) {
				String efficiencyDB = rs.getString("Description");
				softAssert.assertEquals(efficiencyDB, despText);
				log.info("Description on DB : " + efficiencyDB + " and Description on UI :" + headingEfficiency
						+ " are Same");
			}
			// Verify the Likes Added and View for the efficiency
			rs = DataBaseUtils.getResultSet(SQLQueries.getEfficiencyLikes(headingEfficiency, "3"));
			// rs.next();
			while (rs.next()) {
				String likeCount_DB = rs.getString("LikeCount");
				softAssert.assertEquals(getLblLikesFirstEfficiencyLabel().trim(), likeCount_DB);
				log.info("Likes counts for First Efficiency on UI :" + getLblLikesFirstEfficiencyLabel().trim()
						+ "Likes counts for First Efficiency on DB :" + likeCount_DB);
			}
			rs = DataBaseUtils.getResultSet(SQLQueries.getEfficiencyAdded(headingEfficiency, "3"));
			rs.next();
			String addCount_DB = rs.getString("Added");
			softAssert.assertEquals(getLblAddedFirstEfficiency().trim(), addCount_DB);
			log.info("Added counts for First Efficiency on UI :" + getLblAddedFirstEfficiency().trim()
					+ "Added counts for First Efficiency on DB :" + addCount_DB);
			rs = DataBaseUtils.getResultSet(SQLQueries.getEfficiencyViews(headingEfficiency, "3"));
			// rs.next();
			while (rs.next()) {
				String views_DB = rs.getString("views");
				softAssert.assertEquals(getLblViewsFirstEfficiency().trim(), views_DB);
				log.info("Views counts for First Efficiency on UI :" + getLblViewsFirstEfficiency().trim()
						+ "Views counts for First Efficiency on DB :" + views_DB);
			}
			break;
		}
		softAssert.assertAll();
		log.info("Test Case execution for - Verify the Efficiency Tab Details for Customer in CSR - is Finished");

	}

	public void verifyGuest360View() throws Exception {

		log.info("Test Case execution for - Verify the Guest Tab Details for Customer in CSR - is initiated");
		// Runner.test.log(Status.INFO, "C72573 Verify the Guest Tab Details for
		// Customer in CSR");
		SoftAssert softAssert = new SoftAssert();

		String utilityAccountNumber = "411004241120";
		// enterValueSearchFilter(utilityAccountNumber);//
		// Utils.getRbData().getString("sUtilityNumber")

		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();

		enterValueSearchFilter(utilityAccountNumber);
		clickLnkServiceAccountNumber();
		waitForImgCustomerDetailsLoadingToInvisible();
		switchToFrame(0);
		waitForImgCustomerDetailsLoadingToInvisible();
		waitForPageToLoad();

		Assert.assertTrue(isLnkUserGuestTabVisible());
		clickLnkUserGuestTab();
		waitForPageToLoad();

		ArrayList<String> expectedHeaders = new ArrayList<>();
		expectedHeaders.add("GUEST NAME");
		expectedHeaders.add("EMAIL ADDRESS");
		expectedHeaders.add("ROLE");
		expectedHeaders.add("REQUEST DATE");
		expectedHeaders.add("EXPIRATION DATE");
		expectedHeaders.add("STATUS");
		ArrayList<String> actualHeaders = new ArrayList<>();

		List<WebElement> headers = getLstGuestUserTableHeaders();
		for (WebElement header : headers) {
			actualHeaders.add(header.getText());
		}

		Assert.assertTrue(expectedHeaders.containsAll(actualHeaders), "All headers not match");

		String totalEntries = getLblGuestUserEntriesLabel();
		final Pattern pattern = Pattern.compile("of(.*?)entries");
		String noOfRecords = null;
		final Matcher matcher = pattern.matcher(totalEntries);
		while (matcher.find()) {
			noOfRecords = matcher.group(1).trim();
		}
		int totalRecords = Integer.parseInt(noOfRecords);

		if (totalRecords == 0) {
			Assert.assertEquals(getLblGuestUserNoRecordLabel(),
					adminCustomerTextProp.getPropValue("txtTblBodyEmptyAcp"));
		} else {

			ArrayList<String> guestName_UI = new ArrayList<String>();
			List<WebElement> guestName = getLstGuestUserGuestName();
			for (WebElement webElement : guestName) {
				guestName_UI.add(webElement.getText().trim());
			}

			ArrayList<String> emailAddress_UI = new ArrayList<String>();
			List<WebElement> emailAddress = getLstGuestUserEmailAddress();
			for (WebElement webElement : emailAddress) {
				emailAddress_UI.add(webElement.getText().trim());
			}

			ArrayList<String> role_UI = new ArrayList<String>();
			List<WebElement> role = getLstGuestUserRole();
			for (WebElement webElement : role) {
				role_UI.add(webElement.getText().trim());
			}

			ArrayList<String> requestDate_UI = new ArrayList<String>();
			List<WebElement> requestDate = getLstGuestUserRequestDate();
			for (WebElement webElement : requestDate) {
				String[] dates = webElement.getText().trim().split("/");
				String newDate = dates[2] + "-" + dates[0] + "-" + dates[1];
				requestDate_UI.add(newDate);
			}

			ArrayList<String> expirationDate_UI = new ArrayList<String>();
			List<WebElement> expirationDate = getLstGuestUserExpiration();
			for (WebElement webElement : expirationDate) {
				String[] dates = webElement.getText().trim().split("/");
				String newDate = dates[2] + "-" + dates[0] + "-" + dates[1];
				expirationDate_UI.add(newDate);
			}

			ArrayList<String> status_UI = new ArrayList<String>();
			List<WebElement> status = getLstGuestUserStatus();
			for (WebElement webElement : status) {
				status_UI.add(webElement.getText().trim());
			}

			Assert.assertTrue(guestName_UI.containsAll(DataBaseUtils.getGuestNameCSP(utilityAccountNumber)));
			Assert.assertTrue(emailAddress_UI.containsAll(DataBaseUtils.getGuestEmailCSP(utilityAccountNumber)));

			Assert.assertTrue(role_UI.containsAll(DataBaseUtils.getGuestRoleCSP(utilityAccountNumber)));
			Assert.assertTrue(requestDate_UI.containsAll(DataBaseUtils.getGuestRequestDateCSP(utilityAccountNumber)));
			Assert.assertTrue(
					expirationDate_UI.containsAll(DataBaseUtils.getGuestExpirationDateCSP(utilityAccountNumber)));
			// Assert.assertTrue(status_UI.containsAll(DataBaseUtil.getGuestStatusCSP(utilityAccountNumber)));

		}

		log.info("Test Case execution for - Verify the Guest Tab Details for Customer in CSR - is Finished");

	}

	public void verifyOutage360View() throws SQLException {

		log.info("Test Case execution for - Verify the Outage Tab Details for Customer in CSR - is initiated");
		//Runner.test.log(Status.INFO, "C72573 Verify the Outage Tab Details for Customer in CSR");
		SoftAssert softAssert = new SoftAssert();
		String utilityAccountNumber = "411007037392"; // Utils.getRbData().getString("sUtilityNumber")
		
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();

		enterValueSearchFilter(utilityAccountNumber);
		clickLnkServiceAccountNumber();
		waitForImgCustomerDetailsLoadingToInvisible();
		switchToFrame(0);
		waitForImgCustomerDetailsLoadingToInvisible();
		waitForPageToLoad();
		
		
		Assert.assertTrue(isLnkOutageTabVisible());
		clickOutageTab();
		waitForPageToLoad();

		Assert.assertEquals(getLblOutagesCurrentAttributeValue("class"), "active");
		Assert.assertTrue(isLblOutagesCurrentVisible());
		Assert.assertTrue(isLblOutagesPlannedVisible());

		Assert.assertTrue(isLblOutagesMapVisible());
		Assert.assertTrue(isLblOutagesSatelliteVisible());
		Assert.assertEquals(getLblOutagesMapAttributeValue("aria-checked"), "true");
		Assert.assertEquals(getLblOutagesSatelliteAttributeValue("aria-checked"), "false");


		ResultSet resultSet;
		resultSet = DataBaseUtils.getResultSet("select * from CustomerInfo where UtilityAccountNumber = '" + utilityAccountNumber + "'");
		resultSet.next();
		String accountNumber = resultSet.getString("accountnumber");
		String searchStringPIN = resultSet.getString("ZipCode");

		resultSet = DataBaseUtils.getResultSet("select * from useraccount where Accountnumber='" + accountNumber + "'");
		resultSet.next();
		String userID = resultSet.getString("UserID");

		ArrayList<String> titleUI = new ArrayList<>();
		List<WebElement> titles = getLstOutagesTiles();
		for (WebElement e : titles) {
			titleUI.add(e.getText());
		}

		ArrayList<String> outageDateTimeUI = new ArrayList<>();
		List<WebElement> outageDateTimes = getLstOutagesDateTime();
		for (WebElement e : outageDateTimes) {
			outageDateTimeUI.add(e.getText().replace("Date & Time Of Outage:", "").trim());
		}

		ArrayList<String> estimatedRestorationUI = new ArrayList<>();
		List<WebElement> estimatedRestorations = getLstOutagesEstimatedRestoration();
		for (WebElement e : estimatedRestorations) {
			estimatedRestorationUI.add(e.getText().replace("Estimated Restoration:", "").trim());
		}

		ArrayList<Integer> customerAffectedUI = new ArrayList<>();
		List<WebElement> customerAffecteds = getLstOutagesCustomerAffected();
		for (WebElement e : customerAffecteds) {
			customerAffectedUI.add(Integer.parseInt(e.getText().replace("Customers Affected:", "").trim()));
		}

		ArrayList<String> communityAffectedUI = new ArrayList<>();
		List<WebElement> communityAffecteds = getLstOutagesCommunityAffected();
		for (WebElement e : communityAffecteds) {
			communityAffectedUI.add(e.getText().replace("Community Affected:", "").trim());
		}

		ArrayList<String> reportInfoUI = new ArrayList<>();
		List<WebElement> reportInfos = getLstOutagesReportInfo();
		for (WebElement e : reportInfos) {
			reportInfoUI.add(e.getText().replace("Report Info: ", "").trim());
		}

		ArrayList<String> statusUI = new ArrayList<>();
		List<WebElement> status = getLstOutagesStatus();
		for (WebElement e : status) {
			statusUI.add(e.getText().replace("Status", "").trim());
		}

		List<WebElement> uiItems = getLstOutagesItems();

		ArrayList<CurrentOutage> valuesUI = new ArrayList<CurrentOutage>();
		CurrentOutage obj;

		for (int i = 0; i < uiItems.size(); i++) {

			obj = new CurrentOutage(titleUI.get(i), outageDateTimeUI.get(i), estimatedRestorationUI.get(i),
					customerAffectedUI.get(i), communityAffectedUI.get(i), reportInfoUI.get(i), statusUI.get(i));
			valuesUI.add(obj);
		}

		// API

		OutageEndpoints outageAPI = new OutageEndpoints();
		outageAPI.buildRequestSpecForGettingOutage(1, 0, searchStringPIN, Integer.valueOf(userID), ResourcePaths.SCP_COMMON_PATH_URI);
		outageAPI.hitGetOutageAPI();
		List<Map> valuesAPI = outageAPI.getOutageDetails();

		ArrayList<String> titleAPI = new ArrayList<>();
		for (Map<String, String> currOutage : valuesAPI) {
			String value = String.valueOf(currOutage.get("Title"));
			titleAPI.add(value);
		}

		ArrayList<String> dateTimeOutageAPI = new ArrayList<>();
		for (Map<String, String> currOutage : valuesAPI) {
			String value = String.valueOf(currOutage.get("Outagedate"));
			dateTimeOutageAPI.add(value);
		}

		ArrayList<String> estimatedRestorationAPI = new ArrayList<>();
		for (Map<String, String> currOutage : valuesAPI) {
			String value = String.valueOf(currOutage.get("Restorationdate"));
			estimatedRestorationAPI.add(value);
		}

		ArrayList<Integer> customerAffectedAPI = new ArrayList<>();
		for (Map<String, String> currOutage : valuesAPI) {
			int customerAffected = Integer.parseInt(String.valueOf(currOutage.get("CustomerAffected")));
			customerAffectedAPI.add(customerAffected);
		}

		ArrayList<String> communityAffectedAPI = new ArrayList<>();
		for (Map<String, String> currOutage : valuesAPI) {
			String value = String.valueOf(currOutage.get("CityName"));
			communityAffectedAPI.add(value);
		}

		ArrayList<String> reportInfoAPI = new ArrayList<>();
		for (Map<String, String> currOutage : valuesAPI) {
			String value = String.valueOf(currOutage.get("OutageReportInfo"));
			reportInfoAPI.add(value);
		}

		ArrayList<String> statusAPI = new ArrayList<>();
		for (Map<String, String> currOutage : valuesAPI) {
			String value = String.valueOf(currOutage.get("STATUS"));
			statusAPI.add(value);
		}

		Assert.assertTrue(titleAPI.containsAll(titleUI));
		Assert.assertTrue(customerAffectedAPI.containsAll(customerAffectedUI));
		Assert.assertTrue(communityAffectedAPI.containsAll(communityAffectedUI));
		Assert.assertTrue(reportInfoAPI.containsAll(reportInfoUI));
		Assert.assertTrue(statusAPI.containsAll(statusUI));

		log.info("Test Case execution for - Verify the Outage Tab Details for Customer in CSR - is Finished");

	}
	
	public void verifyServicePlanProfileView() throws Exception {
		log.info("Test Case execution for - Verify the Service Plans for Customer in C360 Profile Tab in CSR  - is initiated");
		//Runner.test.log(Status.INFO, "C72568 Verify the Service Plans for Customer in C360 Profile Tab in CSR");
		String utilityAccountNumber = "R100000113"; 
		
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();

		enterValueSearchFilter(utilityAccountNumber);
		clickLnkServiceAccountNumber();
		waitForImgCustomerDetailsLoadingToInvisible();
		switchToFrame(0);
		waitForImgCustomerDetailsLoadingToInvisible();
		waitForPageToLoad();
		
		// verify the Details on the Customer Details Page
		SoftAssert softAssert = new SoftAssert();
		assertTrue(isLblCustomerNameVisible());

		String powerServicePlanUI = null;
		String waterServicePlanUI = null;
		String gasServicePlanUI = null;
		String solarServicePlanUI = null;

		if(verifyLblPowerServicePlanElementPresent())
		{
			powerServicePlanUI= getValuePowerServicePlanLocatorText();
			System.out.println(powerServicePlanUI);
		}
		if(verifyLblWaterServicePlanElementPresent())
		{
			waterServicePlanUI= getValueWaterServicePlanLocatorText();
			System.out.println(waterServicePlanUI);
		}
		if(verifyLblGasServicePlanElementPresent())
		{
			gasServicePlanUI= getValueGasServicePlanLocatorText();
			System.out.println(gasServicePlanUI);
		}
		//         if(pageUtil.verifyElementPresent(adminCsrPage.getLblSolarServicePlan()))
		//        {
		//        	 solarServicePlanUI=pageUtil.getLocatorText(adminCsrPage.getValueSolarServicePlan());
		//        	System.out.println(solarServicePlanUI);
		//        }
		ResultSet rs=DataBaseUtils.getResultSet(SQLQueries.getServicePlansofAccountNumber(utilityAccountNumber));
		rs.next();

		String powerPlanNameDB=rs.getString("PowerPlanName");
		String waterPlanNameDB=rs.getString("WaterPlanName");
		String gasPlanNameDB=rs.getString("GasPlanName");
		//         String solarPlanNameDB=rs.getString("SolarPlanName");

		System.out.println(powerPlanNameDB);
		System.out.println(waterPlanNameDB);
		System.out.println(gasPlanNameDB);
		//         System.out.println(solarPlanNameDB);

		softAssert.assertEquals(powerServicePlanUI, powerPlanNameDB);
		softAssert.assertEquals(waterServicePlanUI, waterPlanNameDB);
		softAssert.assertEquals(gasServicePlanUI, gasPlanNameDB);
		//         softAssert.assertEquals(solarServicePlanUI, solarPlanNameDB);
		softAssert.assertAll();

		log.info("Test Case execution for -  Verify the Service Plans for Customer in C360 Profile Tab in CSR  - is Finished");

	}
	
	public void verifyPostpaidUserDetailsView() throws Exception {
		log.info("Test Case execution for - Verify the Profile Tab Customer information for Postpaid User in CSR - is initiated");
		//Runner.test.log(Status.INFO, "C107373 Verify the Profile Tab Customer information for Postpaid User in CSR");
		
		String postpaidAccountNo = null;
		String billTypeStatusDB = null;
		String billTypeEnrolledDateDB = null;
		ResultSet rs=DataBaseUtils.getResultSet(SQLQueries.getPostpaidAccountNumber());
		while(rs.next()) {
			postpaidAccountNo=rs.getString("UtilityAccountNumber");
			 billTypeStatusDB = rs.getString("Paperless");
			 billTypeEnrolledDateDB= rs.getString("PaperlessUpdateDate").substring(0,10);
		}
		System.out.println("The Postpaid Service Account Number is "+postpaidAccountNo);
		
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();

		enterValueSearchFilter(postpaidAccountNo);
		clickLnkServiceAccountNumber();
		waitForImgCustomerDetailsLoadingToInvisible();
		switchToFrame(0);
		waitForImgCustomerDetailsLoadingToInvisible();
		waitForPageToLoad();
		
		
		// verify the Details on the Customer Details Page
		SoftAssert softAssert = new SoftAssert();

		// Bill Type status
		softAssert.assertTrue(isValueBillTypeStatusVisible());
		String billTypeStatusUI = getValueBillTypeStatusFieldLabel();
		System.out.println(billTypeStatusUI);


		// Bill type Enrolled Date
		softAssert.assertTrue(isValueBillTypeEnrolledDateVisible());
		String billTypeEnrolledDateUI = getValueBillTypeEnrolledDateFieldLabel();
		System.out.println(billTypeEnrolledDateUI);


//		ResultSet rs1=DataBaseUtil.getResultSet(SqlQuery.getBillTypeCustomerDetails(postpaidAccountNo));
//		rs1.next();
//
//		String billTypeStatusDB = rs1.getString("Paperless");

		if(billTypeStatusDB.equals("0"))
		{
			billTypeStatusDB = "Enrolled in Paper Bill";
		}
		else if(billTypeStatusDB.equals("1"))
		{
			billTypeStatusDB = "Enrolled in Paperless Bill";
		}
		else if(billTypeStatusDB.equals("2"))
		{
			billTypeStatusDB = "Enrolled in both Paper and Paperless";
		}
		System.out.println(billTypeStatusDB);
//		String billTypeEnrolledDateDB= rs1.getString("PaperlessUpdateDate").substring(0,10);
		System.out.println(billTypeEnrolledDateDB);
		softAssert.assertEquals(billTypeStatusUI, billTypeStatusDB);
		softAssert.assertEquals(DateUtil.changeDateFormatFromDateCSP(billTypeEnrolledDateUI, "yyyy-MM-dd"), billTypeEnrolledDateDB);

		//        softAssert.assertFalse(pageUtil.verifyElementPresent(adminCsrPage.getLblBillingMethod()),
		//        		"Billing Method Field is present on the C360 popup - Customer Details page.");
		//        String attributeValue = pageUtil.getAttributeValue(adminCsrPage.getLblBillingMethod(), "style");
		//softAssert.assertTrue(getLblBillingMethodAttributeValue("style").equals("display: none;"));

		softAssert.assertAll();
		log.info("Test Case execution for - Verify the Profile Tab Customer information for Postpaid User in CSR - is Finished");

	}
	
	public void verifyPrepaidUserDetailsView() throws Exception {
		log.info("Test Case execution for - Verify the Profile Tab Customer information for Prepaid User in CSR - is initiated");
		//Runner.test.log(Status.INFO, "C107374 Verify the Profile Tab Customer information for Prepaid User in CSR");
		
		String prepaidAccountNo = null;
		ResultSet rs=DataBaseUtils.getResultSet(SQLQueries.getPrePaidOwnerAccountNumber());
		while(rs.next()) {
			prepaidAccountNo=rs.getString("UtilityAccountNumber");
		}
		log.info("The Prepaid Service Account Number is "+prepaidAccountNo);
		//Runner.test.log(Status.INFO,"The Prepaid Service Account Number is " + prepaidAccountNo );
		
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();

		enterValueSearchFilter(prepaidAccountNo);
		clickLnkServiceAccountNumber();
		waitForImgCustomerDetailsLoadingToInvisible();
		switchToFrame(0);
		waitForImgCustomerDetailsLoadingToInvisible();
		waitForPageToLoad();
		
		// verify the Details on the Customer Details Page
		SoftAssert softAssert = new SoftAssert();

		// Bill Type status
		//        softAssert.assertFalse(pageUtil.verifyElementPresent(adminCsrPage.getValueBillTypeStatus()));
		//        softAssert.assertTrue(pageUtil.getAttributeValue(adminCsrPage.getValueBillTypeStatus(), "style").equals("display: none;"));
		//        softAssert.assertTrue(driver.findElement(adminCsrPage.getValueBillTypeStatus()).getAttribute("style").equals("display: none;"));

		// Bill type Enrolled Date
		//        softAssert.assertFalse(pageUtil.verifyElementPresent(adminCsrPage.getValueBillTypeEnrolledDate()));
		//        softAssert.assertTrue(pageUtil.getAttributeValue(adminCsrPage.getValueBillTypeEnrolledDate(), "style").equals("display: none;"));
		//        softAssert.assertTrue(driver.findElement(adminCsrPage.getValueBillTypeEnrolledDate()).getAttribute("style").equals("display: none;"));

		//Verify the Bill Type Status section is not visible.
		//softAssert.assertTrue(driver.findElement(adminCsrPage.getLblBillTypeDetailsSection()).getAttribute("style").equals("display: none;"));

		//Billing Method.
		softAssert.assertTrue(isValueBillingMethodVisible());
		String billingMethodUI = getValueBillingMethodLabel();
		System.out.println("The Billing Method From the UI is " + billingMethodUI);

		ResultSet rs1=DataBaseUtils.getResultSet(SQLQueries.getBillTypeCustomerDetails(prepaidAccountNo));
		rs1.next();

		String billingMethodDB = rs1.getString("DefaultPaymentType");

		if(billingMethodDB.equals("0"))
		{
			billingMethodDB = "Prepaid";
		}
		else if(billingMethodDB.equals("1"))
		{
			billingMethodDB = "Postpaid";
		}
		System.out.println("The Billing Method From the DB is " + billingMethodDB);
		softAssert.assertEquals(billingMethodUI, billingMethodDB);


		softAssert.assertAll();
		log.info("Test Case execution for - Verify the Profile Tab Customer information for Prepaid User in CSR - is Finished");

	}
	
	public void verifyAdvanceSearchFilterServiceAccountTab() throws Exception {
		log.info("Test Case execution for - verify CSR Advance Search filter Service Account - is Initiated");
		//Runner.test.log(Status.INFO, "C72498-Verify Advance Search Button on CSR workbench");
		
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		explicitWaitForbtnAdvanceSearch();
		
		Assert.assertTrue(isBtnAdvanceSearchVisible());
		log.info("C72499-Verify Advance Search filter fields in the Advance" + "search pop up for Service account Tab");
		// verify the fields for Advance Search filters
		
		clickBtnAdvanceSearch();
		waitForPageToLoad();
		Assert.assertTrue(isTxtServiceAccountNumberServiceVisible());
		
		Assert.assertTrue(isDdlAccountServiceAccountTypeVisible());
		Assert.assertTrue(isDdlAccountStatusVisible());//adminCsrPage.getDdlStatus()
		Assert.assertTrue(isTxtCustomerNumberServiceAccountVisible());
		Assert.assertTrue(isDdlPaperLessVisible());
		Assert.assertTrue(isDdlNoOfLinkedUserVisible());
		scrollToTheBottomOfPage();
		Assert.assertTrue(isTxtZipcodeServiceAccountVisible());
		Assert.assertTrue(isTxtCityServiceAccountVisible());
		Assert.assertTrue(isBtnAdvanceResetServiceAccountVisible());
		log.info("C78681-Verify Search button and cancel button in advance search");
		Assert.assertTrue(isBtnAdvanceSearchSearchVisible());
		Assert.assertTrue(isBtnAdvanceSearchCloseVisible());
		
		//Registered Accounts
		Assert.assertTrue(isLblRegisteredAcctAdvSearchServAcctVisible(),
				"Registered Accounts Label is not present on Advance Search for Service Account");
		Assert.assertTrue(isDdlRegisteredAcctAdvSearchServAcctVisible(),
				"Registered Accounts Dropdown is not present on Advance Search for Service Account");
		List<String> options = getAllOptionsInDdlRegisteredAcctAdvSearchServAcct();
		Assert.assertTrue(options.containsAll(getExpectedElementsTextList(adminCustomerTextProp.getPropValue("ddlValuesRegisteredAccountAdvSearchServAcctAcp"))),
				"Registered Accounts Field Dropdown values are not as Expected on Advance Search for Service Account");
		
		log.info("Test Case execution for -  verify Advance Filter Service Account Tab - is Completed.");
	}
	
	public List<String> getExpectedElementsTextList(String expectedEle) {
		ArrayList<String> eleList = new ArrayList<String>();
		String[] expEle = expectedEle.split(";");
		for (String value : expEle) {
			eleList.add(value);
		}
		return eleList;
	}
	
	public void verifyAdvanceSearchFilterSCMCustomerTab() throws Exception {
		log.info("Test Case execution for - verify CSR Advance Search filter SCM User Tab - is Initiated");
		log.info("C72500-Verify Advance Search filter fields in the Advance search pop up for Customer Tab");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		explicitWaitForbtnAdvanceSearch();
		
		clickLnkCustomerTab();
		waitForPageToLoad();
		
		explicitWaitForbtnAdvanceSearch();
		Assert.assertTrue(isBtnAdvanceSearchVisible());
		log.info("C72500-Verify Advance Search filter fields in the Advance search pop up for Customer Tab");
		// verify the fields for Advance Search filters
		
		clickBtnAdvanceSearch();
		waitForPageToLoad();
		Assert.assertTrue(isTxtServiceAccountNumberCustomerVisible());
		Assert.assertTrue(isDdlAccountTypeCustomerVisible());
		Assert.assertTrue(isTxtCustomerNumberCustomerVisible());
		scrollToTheBottomOfPage();
		//pageUtil.scrollToElement(adminCsrPage.gettxtZipcodeCustomer());
		Assert.assertTrue(isTxtZipcodeCustomerVisible());
		Assert.assertTrue(isTxtCityCustomerVisible());
		Assert.assertTrue(isTxtFirstNameCustomerVisible());
		Assert.assertTrue(isTxtLastNameCustomerVisible());
		Assert.assertTrue(isTxtEmailAdvanceCustomerVisible());
		log.info("Test Case execution for -  verify Advance Filter Service Account Tab - is Completed.");
	}
	
	public void verifyAdvanceSearchFilterSCMUserTab() throws Exception {
		log.info("Test Case execution for - verify CSR Advance Search filter SCM User Tab - is Initiated");
		log.info("C72501-Verify the Advance Search Service Account Tab filters");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		explicitWaitForbtnAdvanceSearch();
		
		// Navigate to the SCM user tab
		clickLnkSCMUserTab();
		waitForPageToLoad();
		// verification for the Advance Search button
		explicitWaitForbtnAdvanceSearch();
		Assert.assertTrue(isBtnAdvanceSearchVisible());
		log.info("C72501-Verify the Advance Search Service Account Tab filters fields");
		// verify the fields for Advance Search filters
		
		clickBtnAdvanceSearch();
		waitForPageToLoad();
		Assert.assertTrue(isTxtServiceAccountNumberUserVisible());
		Assert.assertTrue(isDdlAccountTypeUserVisible());
		Assert.assertTrue(isDdlStatusUsersVisible());
		Assert.assertTrue(isDdlLinkAccountVisible());
		scrollToDdlRole();
		Assert.assertTrue(isDdlRoleVisible());
		Assert.assertTrue(isDdlSocialMediaVisible());
		Assert.assertTrue(isTxtCustomerNumberUserVisible());
		scrollToTxtZipcodeUser();
		Assert.assertTrue(isTxtZipcodeUserVisible());
		Assert.assertTrue(isTxtCityUserVisible());
		Assert.assertTrue(isTxtFirstNameUserVisible());
		Assert.assertTrue(isTxtLastNameUserVisible());
		Assert.assertTrue(isTxtEmailAdvanceUserVisible());
		log.info("Test Case execution for -  verify Advance Filter Service Account Tab - is Completed.");
	}
	
	public void verifyActionsAdvSearchFilterServiceAcc() throws Exception {
		log.info("Test Case execution for - Verify Action Perform AdvanceSearch Filter ResultsServiceAccountTab - is Initiated");
		log.info("C72503-Verify action perform on Advance Search filter results in Service Accounts Tab");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		explicitWaitForbtnAdvanceSearch();
		SoftAssert sa= new SoftAssert();
		
		clickBtnAdvanceSearch();
		waitForPageToLoad();
		log.info("C72502-Verify Advance Search filter results in CSR workbench");
		setFilterTypeAdvanceSearchServiceAccountTab(csrDataFileName);
		sa.assertAll();
		log.info("Test Case execution for - Verify Action Perform AdvanceSearch Filter ResultsServiceAccountTab - is Completed.");
	}
	
	public void setFilterTypeAdvanceSearchServiceAccountTab(String csrDatatFileName) throws Exception {
		ExcelUtils.openExcelFile(csrDatatFileName);
		ExcelUtils.getSheetName("ServiceAccounts");
		
		for (int rowNum = 1; rowNum <= ExcelUtils.getRowCount(); rowNum++) {
			if (ExcelUtils.getCellValue(rowNum, 0).equals("Yes")) {
				pressTab();
				enterDataCsr(getTxtServiceAccountNumberService(), ExcelUtils.getCellValue(rowNum, 1));
				waitForPageToLoad();
				pressTab();
				waitForPageToLoad();
				selectByVisibleTxt(getDdlAccountServiceAccountType(), ExcelUtils.getCellValue(rowNum, 2));
				pressTab();
				waitForPageToLoad();
				// pageUtil.selectByVisibleText(adminCsrPage.getDdlStatus(),
						// ExcelUtil.getCellValue(rowNum, 3));
				selectByVisibleTxt(getDdlStatusService(), ExcelUtils.getCellValue(rowNum, 3));
				waitForPageToLoad();
				enterDataCsr(getTxtCustomerNumberServiceAccount(), ExcelUtils.getCellValue(rowNum, 4));
				waitForPageToLoad();
				pressTab();
				selectByVisibleTxt(getDdlPaperLess(), ExcelUtils.getCellValue(rowNum, 5));
				waitForPageToLoad();
				pressTab();
				waitForPageToLoad();
				selectByVisibleTxt(getDdlNoOfLinkedUser(), ExcelUtils.getCellValue(rowNum, 6));
				waitForPageToLoad();
				enterDataCsr(getTxtZipcodeServiceAccount(), ExcelUtils.getCellValue(rowNum, 7));
				waitForPageToLoad();
				enterDataCsr(getTxtCityServiceAccount(), ExcelUtils.getCellValue(rowNum, 8));
				boolean result = verifyAdvanceSearchResult(csrDatatFileName, rowNum);
				if (result = false) {
					log.error("Validation is failing on row number :" + rowNum);
				}
				clickBtnAdvanceSearch();
				waitForPageToLoad();
				clickBtnAdvanceResetServiceAccount();
			}
		}
		//sa.assertAll();
	}
	
	
	
	public boolean verifyAdvanceSearchResult(String csrDatatFileName, int rowNum) throws Exception {
		boolean flag = false;
		
		clickBtnAdvanceSearchSearch();
		waitForPageLoaderInvisibility();
		waitForPageToLoad();
		ExcelUtils.openExcelFile(csrDatatFileName);
		ExcelUtils.getSheetName("ServiceAccounts");
		
		String rowCount = DataBaseUtils.getCountRowServiceAccount(ExcelUtils.getCellValue(rowNum, 4),
				ExcelUtils.getCellValue(rowNum, 7), ExcelUtils.getCellValue(rowNum, 1), ExcelUtils.getCellValue(rowNum, 3),
				ExcelUtils.getCellValue(rowNum, 2), ExcelUtils.getCellValue(rowNum, 8), ExcelUtils.getCellValue(rowNum, 5),
				ExcelUtils.getCellValue(rowNum, 6));
		int count = Integer.parseInt(rowCount);
		String pageRecords = getLblRecordPaginationLabel();
		String[] records = pageRecords.split("f ");
		String filterCount = records[1];
		if (count == 0) {
			if (verifyObjectLabelEqualsCsr(getLblNoDataFoundLabel(),
					adminCustomerTextProp.getPropValue("lblNoDataFoundAdvanceSearchAhp"), rowNum) == true) {
				flag = true;
				//sa.assertAll();
			}

		}
		else {
			if (verifyObjectLabelEqualsCsr(rowCount, filterCount, rowNum) == true) {
				flag = true;
				//sa.assertAll();
				// Status
				List<String> uiDataStatus = new ArrayList<String>();
				List<WebElement> status = collectAllSimillarElements(driver, getLblAccountType(getColumnNumberOfHeader(getTableServiceAccount(), "STATUS")+1));
				for (WebElement webElement : status) {
					uiDataStatus.add(webElement.getText());
				}
				log.info("Verifying the Customer Status of the excel and UI");
				verifyStringValueContainsCsr(uiDataStatus.get(0).trim(), ExcelUtils.getCellValue(rowNum, 3));
				// Service Account Number
				List<String> uiDataServiceAccount = new ArrayList<String>();
				List<WebElement> serviceAccount = collectAllSimillarElements(driver, getLblAccountType(getColumnNumberOfHeader(getTableServiceAccount(), "SERVICE ACCOUNT")+1));
				for (WebElement webElement : serviceAccount) {
					uiDataServiceAccount.add(webElement.getText());
				}
				log.info("Verifying the Utility Account Number of the excel and UI");
				verifyStringValueContainsCsr(uiDataServiceAccount.get(0).trim(), ExcelUtils.getCellValue(rowNum, 1));
				// Customer Number
				List<String> uiDataCustomerNumber = new ArrayList<String>();
				List<WebElement> customerNumber = collectAllSimillarElements(driver, getLblAccountType(getColumnNumberOfHeader(getTableServiceAccount(), "CUSTOMER NUMBER")+1));
				for (WebElement webElement : customerNumber) {
					uiDataCustomerNumber.add(webElement.getText());
				}
				// Full Name
				List<String> uiDataFullName = new ArrayList<String>();
				List<WebElement> fullName = collectAllSimillarElements(driver, getLblAccountType(getColumnNumberOfHeader(getTableServiceAccount(), "CUSTOMER")+1));
				for (WebElement webElement : fullName) {
					uiDataFullName.add(webElement.getText());
				}
				// Phone and email
				List<String> uiDataContact = new ArrayList<String>();
				List<WebElement> contact = collectAllSimillarElements(driver, getLblAccountType(getColumnNumberOfHeader(getTableServiceAccount(), "CONTACT")+1));
				for (WebElement webElement : contact) {
					uiDataContact.add(webElement.getText());
				}
				// Account Type
				List<String> uiDataAccountType = new ArrayList<String>();
				List<WebElement> accountType = collectAllSimillarElements(driver, getLblAccountType(getColumnNumberOfHeader(getTableServiceAccount(), "ACCOUNT TYPE")+1));
				for (WebElement webElement : accountType) {
					uiDataAccountType.add(webElement.getText());
				}
				// Linked Accounts
				List<String> uiDataLinkedAccount = new ArrayList<String>();
				List<WebElement> linkedAccount = collectAllSimillarElements(driver, getLblAccountType(getColumnNumberOfHeader(getTableServiceAccount(), "LINKED USERS")+1));
				for (WebElement webElement : linkedAccount) {
					uiDataLinkedAccount.add(webElement.getText());
				}
				List<String> statusFromDb = DataBaseUtils.getAccountStatus(ExcelUtils.getCellValue(rowNum, 1),
						ExcelUtils.getCellValue(rowNum, 2), ExcelUtils.getCellValue(rowNum, 3),
						ExcelUtils.getCellValue(rowNum, 4), ExcelUtils.getCellValue(rowNum, 5),
						ExcelUtils.getCellValue(rowNum, 6), ExcelUtils.getCellValue(rowNum, 7),
						ExcelUtils.getCellValue(rowNum, 8));
				List<String> ServiceAccountNumber = DataBaseUtils.getServiceAccountNumber(
						ExcelUtils.getCellValue(rowNum, 1), ExcelUtils.getCellValue(rowNum, 2),
						ExcelUtils.getCellValue(rowNum, 3), ExcelUtils.getCellValue(rowNum, 4),
						ExcelUtils.getCellValue(rowNum, 5), ExcelUtils.getCellValue(rowNum, 6),
						ExcelUtils.getCellValue(rowNum, 7), ExcelUtils.getCellValue(rowNum, 8));
				List<String> CustomerNumber = DataBaseUtils.getCustomerNumber(ExcelUtils.getCellValue(rowNum, 1),
						ExcelUtils.getCellValue(rowNum, 2), ExcelUtils.getCellValue(rowNum, 3),
						ExcelUtils.getCellValue(rowNum, 4), ExcelUtils.getCellValue(rowNum, 5),
						ExcelUtils.getCellValue(rowNum, 6), ExcelUtils.getCellValue(rowNum, 7),
						ExcelUtils.getCellValue(rowNum, 8));
				List<String> FullName = DataBaseUtils.getFullName(ExcelUtils.getCellValue(rowNum, 1),
						ExcelUtils.getCellValue(rowNum, 2), ExcelUtils.getCellValue(rowNum, 3),
						ExcelUtils.getCellValue(rowNum, 4), ExcelUtils.getCellValue(rowNum, 5),
						ExcelUtils.getCellValue(rowNum, 6), ExcelUtils.getCellValue(rowNum, 7),
						ExcelUtils.getCellValue(rowNum, 8));
				List<String> MobilePhone = DataBaseUtils.getMobilePhone(ExcelUtils.getCellValue(rowNum, 1),
						ExcelUtils.getCellValue(rowNum, 2), ExcelUtils.getCellValue(rowNum, 3),
						ExcelUtils.getCellValue(rowNum, 4), ExcelUtils.getCellValue(rowNum, 5),
						ExcelUtils.getCellValue(rowNum, 6), ExcelUtils.getCellValue(rowNum, 7),
						ExcelUtils.getCellValue(rowNum, 8));
				List<String> EmailID = DataBaseUtils.getEmailID(ExcelUtils.getCellValue(rowNum, 1),
						ExcelUtils.getCellValue(rowNum, 2), ExcelUtils.getCellValue(rowNum, 3),
						ExcelUtils.getCellValue(rowNum, 4), ExcelUtils.getCellValue(rowNum, 5),
						ExcelUtils.getCellValue(rowNum, 6), ExcelUtils.getCellValue(rowNum, 7),
						ExcelUtils.getCellValue(rowNum, 8));
				List<String> AccountType = DataBaseUtils.getAccountType(ExcelUtils.getCellValue(rowNum, 1),
						ExcelUtils.getCellValue(rowNum, 2), ExcelUtils.getCellValue(rowNum, 3),
						ExcelUtils.getCellValue(rowNum, 4), ExcelUtils.getCellValue(rowNum, 5),
						ExcelUtils.getCellValue(rowNum, 6), ExcelUtils.getCellValue(rowNum, 7),
						ExcelUtils.getCellValue(rowNum, 8));
				for (int i = 0; i <= count; i++) {
					scrollToElement(status.get(i));
					verifyStringValueContainsCsr(uiDataStatus.get(i).trim(), statusFromDb.get(i));
					verifyStringValueContainsCsr(uiDataServiceAccount.get(i).trim(),
							ServiceAccountNumber.get(i));
					verifyStringValueContainsCsr(uiDataCustomerNumber.get(i).trim(), CustomerNumber.get(i));
					verifyStringValueContainsCsr(uiDataFullName.get(i).trim(), FullName.get(i));
					verifyStringValueContainsCsr(uiDataContact.get(i).trim(), MobilePhone.get(i));
					verifyStringValueContainsCsr(uiDataContact.get(i).trim(), EmailID.get(i));
					verifyStringValueContainsCsr(uiDataAccountType.get(i).trim(), AccountType.get(i));
					i = 9;
					break;

				}

			}
		}
		return flag;

	}
	
	public void verifyStringValueContainsCsr(String expected, String actual) {
		SoftAssert sa = new SoftAssert();
		if (actual.equals("---Select---")) {
			// Assert.assertTrue(expected.contains("Active"));
			sa.assertTrue(expected.contains("Active"));
		} else if (actual.equals("null")) {
			// Assert.assertTrue(expected.contains(""));
			sa.assertTrue(expected.contains(""));
		} else {
			// Assert.assertTrue(expected.contains(actual));
			sa.assertTrue(expected.contains(actual));
			log.info("the expected value: " + expected + " contains the actual value: " + actual);
		}

		log.info("the expected value: " + expected + " contains the actual value: " + actual);
	}
	
	public void verifyActionAdvSearchFilterForCustomerTab() throws Exception {
		log.info("Test Case execution for - Verify Action Perform AdvanceSearch Filter ResultsCustomerTab - is Initiated");
		log.info("C72506-Verify action perform on Advance Search filter results in Customer Tab");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		waitForPageLoaderInvisibility();
		explicitWaitForbtnAdvanceSearch();
		
		clickLnkCustomerTab();
		clickBtnAdvanceSearch();
		waitForPageToLoad();
		setFilterTypeAdvanceSearchCustomerTab(csrDataFileName);
		log.info("Test Case execution for - Verify Action Perform AdvanceSearch Filter ResultsCustomerTab - is Initiated");
	}
	public void setFilterTypeAdvanceSearchCustomerTab(String csrDatatFileName) throws Exception {
		ExcelUtils.openExcelFile(csrDatatFileName);
		ExcelUtils.getSheetName("Customers");
		SoftAssert softAssert = new SoftAssert();
		for (int rowNum = 1; rowNum <= ExcelUtils.getRowCount(); rowNum++) {
			if (ExcelUtils.getCellValue(rowNum, 0).equals("Yes")) {
				pressTab();
				enterDataCsr(getTxtServiceAccountNumberCustomer(), ExcelUtils.getCellValue(rowNum, 1));
				pressTab();
				
				selectByVisibleTxt(getDdlAccountTypeCustomer(),	ExcelUtils.getCellValue(rowNum, 2));
				enterDataCsr(getTxtCustomerNumberCustomer(), ExcelUtils.getCellValue(rowNum, 3));
				enterDataCsr(getTxtContactNoCustomer(), ExcelUtils.getCellValue(rowNum, 4));
				enterDataCsr(getTxtZipcodeCustomer(), ExcelUtils.getCellValue(rowNum, 5));
				enterDataCsr(getTxtCityCustomer(), ExcelUtils.getCellValue(rowNum, 6));
				enterDataCsr(getTxtFirstNameCustomer(), ExcelUtils.getCellValue(rowNum, 7));
				enterDataCsr(getTxtLastNameCustomer(), ExcelUtils.getCellValue(rowNum, 8));
				enterDataCsr(getTxtEmailAdvanceCustomer(), ExcelUtils.getCellValue(rowNum, 9));
				boolean result = verifyAdvancesearCustomer(csrDatatFileName, rowNum);
				if (result = false) {
					log.error("Validation is failing on row number :" + rowNum);
				}
				clickLnkCustomerTab();
				clickBtnAdvanceSearch();
				waitForPageLoaderInvisibility();
				waitForPageToLoad();
				clickBtnAdvanceResetCustomer();
				waitForPageLoaderInvisibility();
				scrollToTop();
			}
		}
		softAssert.assertAll();
	}
	
	public boolean verifyAdvancesearCustomer(String csrDatatFileName, int rowNum) throws Exception {
		boolean flag = false;
		waitForPageToLoad();
		clickBtnAdvanceSubmitCustomers();
		waitForPageLoaderInvisibility();
		waitForPageToLoad();
		ExcelUtils.openExcelFile(csrDatatFileName);
		ExcelUtils.getSheetName("Customers");
		SoftAssert sa= new SoftAssert();
		String rowCount = DataBaseUtils.getCountRowCustomer(ExcelUtils.getCellValue(rowNum, 3),
				ExcelUtils.getCellValue(rowNum, 5), ExcelUtils.getCellValue(rowNum, 1), ExcelUtils.getCellValue(rowNum, 4),
				ExcelUtils.getCellValue(rowNum, 2), ExcelUtils.getCellValue(rowNum, 6), ExcelUtils.getCellValue(rowNum, 7),
				ExcelUtils.getCellValue(rowNum, 8), ExcelUtils.getCellValue(rowNum, 9));
		int count = Integer.parseInt(rowCount);
		System.out.println(count);
		String pageRecords = getLblRecordPaginationLabel();
		String[] records = pageRecords.split("f ");
		String filterCount = records[1];
		if (count == 0) {
			if (verifyObjectLabelEqualsCsr(getLblNoDataFoundCustomerLabel(), adminCustomerTextProp.getPropValue("lblNoDataFoundAdvanceSearchAhp"), rowNum) == true) {
				flag = true;
				sa.assertAll();
			}
		}
		else {
			if (verifyObjectLabelEqualsCsr(rowCount, filterCount, rowNum) == true) {
				flag = true;
				
				// Customer Number
				List<String> uiDataCustomerNumber = new ArrayList<String>();
				List<WebElement> customerNumber = collectAllSimillarElements(driver, getLnkCustomerNameTable(getColumnNumberOfHeader(getTableCustomer(), "CUSTOMER NUMBER")));
				for (WebElement webElement : customerNumber) {
					uiDataCustomerNumber.add(webElement.getText());
				}
				// Full Name
				List<String> uiDataFullName = new ArrayList<String>();
				List<WebElement> fullName = collectAllSimillarElements(driver, getLnkCustomerNameTable(getColumnNumberOfHeader(getTableCustomer(), "CUSTOMER")));
				for (WebElement webElement : fullName) {
					uiDataFullName.add(webElement.getText());
				}
				// Phone and email
				List<String> uiDataContact = new ArrayList<String>();
				List<WebElement> contact = collectAllSimillarElements(driver, getLnkCustomerNameTable(getColumnNumberOfHeader(getTableCustomer(), "CONTACT")));
				for (WebElement webElement : contact) {
					uiDataContact.add(webElement.getText());
				}
				// Linked Accounts
				List<String> uiDataLinkedAccount = new ArrayList<String>();
				List<WebElement> linkedAccount = collectAllSimillarElements(driver, getLnkCustomerNameTable(getColumnNumberOfHeader(getTableCustomer(), "LINKED ACCOUNTS")));
				for (WebElement webElement : linkedAccount) {
					uiDataLinkedAccount.add(webElement.getText());
				}
				List<String> CustomerNumber = DataBaseUtils.getCustomerNumberCustomer(ExcelUtils.getCellValue(rowNum, 3),
						ExcelUtils.getCellValue(rowNum, 5), ExcelUtils.getCellValue(rowNum, 1),
						ExcelUtils.getCellValue(rowNum, 4), ExcelUtils.getCellValue(rowNum, 2),
						ExcelUtils.getCellValue(rowNum, 6), ExcelUtils.getCellValue(rowNum, 7),
						ExcelUtils.getCellValue(rowNum, 8), ExcelUtils.getCellValue(rowNum, 9));
				List<String> FullName = DataBaseUtils.getFullNameCustomer(ExcelUtils.getCellValue(rowNum, 3),
						ExcelUtils.getCellValue(rowNum, 5), ExcelUtils.getCellValue(rowNum, 1),
						ExcelUtils.getCellValue(rowNum, 4), ExcelUtils.getCellValue(rowNum, 2),
						ExcelUtils.getCellValue(rowNum, 6), ExcelUtils.getCellValue(rowNum, 7),
						ExcelUtils.getCellValue(rowNum, 8), ExcelUtils.getCellValue(rowNum, 9));
				List<String> MobilePhone = DataBaseUtils.getMobileNumberCustomer(ExcelUtils.getCellValue(rowNum, 3),
						ExcelUtils.getCellValue(rowNum, 5), ExcelUtils.getCellValue(rowNum, 1),
						ExcelUtils.getCellValue(rowNum, 4), ExcelUtils.getCellValue(rowNum, 2),
						ExcelUtils.getCellValue(rowNum, 6), ExcelUtils.getCellValue(rowNum, 7),
						ExcelUtils.getCellValue(rowNum, 8), ExcelUtils.getCellValue(rowNum, 9));
				List<String> EmailID = DataBaseUtils.getEmailIdCustomer(ExcelUtils.getCellValue(rowNum, 3),
						ExcelUtils.getCellValue(rowNum, 5), ExcelUtils.getCellValue(rowNum, 1),
						ExcelUtils.getCellValue(rowNum, 4),
						ExcelUtils.getCellValue(rowNum, 2), ExcelUtils.getCellValue(rowNum, 6),
						ExcelUtils.getCellValue(rowNum, 7), ExcelUtils.getCellValue(rowNum, 8),
						ExcelUtils.getCellValue(rowNum, 9));
				for (int i = 0; i <= count; i++) {
					verifyStringValueCsr(uiDataCustomerNumber.get(i), CustomerNumber.get(i));
					verifyStringValueContainsCsr(uiDataFullName.get(i), FullName.get(i));
					verifyStringValueContainsCsr(uiDataContact.get(i), MobilePhone.get(i));
					verifyStringValueContainsCsr(uiDataContact.get(i), EmailID.get(i));
					i = 9;
					break;

				}
			}
		}
		sa.assertAll();
		return flag;
	}
	
	public boolean verifyStringValueCsr(String expected, String actual) {
		boolean flag = false;
		try {
			Assert.assertEquals(expected, actual);
			log.info("the expected value: " + expected + " matches the actual value: " + actual);
			log.info("the expected value: " + expected + " matches the actual value: " + actual);
			// sAssert.assertAll();
			flag = true;
		} catch (Exception e) {

		}
		return flag;
	}
	
	WebElement element;
  	public void enterDataCsr(WebElement elements, String sEnterData) {
  		
		try {
			WebElement element = elements;
            Actions actions = new Actions(driver);
            //actions.moveToElement(element);
            //actions.click();
            //pressTab();
            actions.sendKeys(Keys.TAB).build().perform();
            if (sEnterData.equals("NULL")) {
                actions.sendKeys("");
                actions.build().perform();
            }
            else {
                actions.sendKeys(sEnterData);
                actions.build().perform();
            }
            log.info("The test data entered is: " + sEnterData);
        } catch (StaleElementReferenceException e) {
            /*
             * System.out.
             * println("Element is not attached to the page document " +
             * e.getStackTrace());
             */
            log.info("Element is not attached to the page document " + e.getStackTrace());
        } catch (NoSuchElementException e) {
            // System.out.println("Element " + element + " was not found in DOM
            // "
            // + e.getStackTrace());
            log.info("Element " + element + " was not found in DOM " + e.getStackTrace());
        } catch (Exception e) {
            log.info("The textbox  " + element + " is not interactable" + e.getStackTrace());
        }
    }
  	
  	public boolean verifyObjectLabelEqualsCsr(String expectedValue, String actualValue, int rowNum) {
		boolean flag = false;
		SoftAssert sa= new SoftAssert();
		sa.assertEquals(expectedValue, actualValue,
				"The " + expectedValue + " label contains the value" + actualValue);
		log.info("User verifies the test object Label contains: " + actualValue);

		if (expectedValue.equals(actualValue)) {
			log.info("The expected object label: " + expectedValue
					+ " matches with the actual object label: " + actualValue);
			flag = true;
		} else {
			log.error("The expected object label: " + expectedValue
					+ " does not matches with the actual object label: " + actualValue + " at Row number: " + rowNum);
			flag = false;
		}

		return flag;

	}
  	
  	public static ArrayList<WebElement> collectAllSimillarElements(WebDriver driver, By locator) {
		ArrayList<WebElement> elementList;
		elementList = (ArrayList<WebElement>) driver.findElements(locator);
		return elementList;
	}
  	
  	public int getColumnNumberOfHeader(WebElement locator, String headerName) {
		int indexOfHeader = 0;
		ArrayList<String> colList = new ArrayList<String>();
		List<WebElement> headerCol = locator.findElements(By.tagName("th"));
		for (WebElement colText : headerCol) {
			colList.add(colText.getText());
		}
		if (colList.contains(headerName)) {
			indexOfHeader = colList.indexOf(headerName);
		}
		return indexOfHeader;
	}
  	
  	public int getColumnNumberOfHeader(By locator, String headerName) {
		int indexOfHeader = 0;
		ArrayList<String> colList = new ArrayList<String>();
		List<WebElement> headerCol = driver.findElement(locator).findElements(By.tagName("th"));
		for (WebElement colText : headerCol) {
			colList.add(colText.getText());
		}
		if (colList.contains(headerName)) {
			indexOfHeader = colList.indexOf(headerName);
		}
		return indexOfHeader;
	}
  	
  	public void verifyAdvanceFilterCustomerWithAccountType() throws Exception {
		log.info("Test Case execution for -verifyAdvanceFilterCustomerWithAccountType- is Initiated");
		log.info("C72507-Verify Advance Search filter results in CSR workbench for customer Tab with Account Type");
		
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		waitForPageLoaderInvisibility();
		explicitWaitForbtnAdvanceSearch();
		
		clickLnkCustomerTab();
		
		clickBtnAdvanceSearch();
		waitForPageToLoad();
		//clickAdvanceSearchButtonCustomerTab();
		setAdvanceSearchCustomerTab("", "Commercial","Active", "", "", "", "", "", "");
		
		clickBtnAdvanceSubmitCustomers();
		waitForPageLoaderInvisibility();
		waitForPageToLoad();
		
		List<String> uiData = new ArrayList<String>();
		List<WebElement> ele = collectAllSimillarElements(driver, getLnkCustomerNameTable(getColumnNumberOfHeader(getTableCustomer(), "NAME")));
		for (WebElement webElement : ele) {
			System.out.println(webElement.getText());
			uiData.add(webElement.getText());
		}
		System.out.println(uiData);
		List<String> db_customerName = new ArrayList<String>();
		waitForPageToLoad();
		ResultSet rs;
		try {
			rs = DataBaseUtils
					.getResultSet(SQLQueries.getAdvanceSearchCustomer("", "Commercial", "Active", "", "", "", "", "", ""));
			while (rs.next()) {
				String name = rs.getString("FullName");
				db_customerName.add(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("No value configured");
		}
		List<String> subList = new ArrayList<String>();
		// We are verifying for 10 customer after sorting
		if (db_customerName.size() < 10) {
			subList = new ArrayList<>(db_customerName.subList(0, db_customerName.size()));
		}
		else {
			subList = new ArrayList<>(db_customerName.subList(0, 10));
		}
		System.out.println(subList);
		// Assert.assertEquals(uiData, subList);
		log.info("Test Case execution for -  verifyAdvanceFilterCustomerWithAccountType - is Completed.");
	}
  	
  	public void setAdvanceSearchCustomerTab(String serviceAccountNumber, String accountType, String status,
			String customerNumber, String zipCode, String city, String firstName, String lastName, String email)
					throws InterruptedException {
		// pageUtil.explicitWaitForWebElement(driver,
		// admincsrPage.getTxtServiceAccountNumber());
		if (!serviceAccountNumber.equalsIgnoreCase(""))
			// sync problem
		
		// pageUtil.enterTextValue(admincsrPage.getTxtServiceAccountNumber(),
		// serviceAccountNumber);
		enterData(getTxtServiceAccountNumberCustomer(), serviceAccountNumber);
		// JsExecutorUtil.enterDataTextBox(driver,
		// admincsrPage.getTxtServiceAccountNumber(), serviceAccountNumber);
		// pageUtil.pressTabKey(admincsrPage.getTxtServiceAccountNumber());
		if (!accountType.equalsIgnoreCase(""))
			selectByVisibleTxt(getDdlAccountTypeCustomer(), accountType);
		if (!status.equalsIgnoreCase(""))
			selectByVisibleTxt(getDdlStatus(), status);
		if (!customerNumber.equalsIgnoreCase(""))
			enterTextValue(getTxtCustomerNumberCustomer(), customerNumber);
		waitForPageToLoad();
		scrollToElement(getTxtZipcodeCustomer());
		if (!zipCode.equalsIgnoreCase(""))
			enterTextValue(getTxtZipcodeCustomer(), zipCode);
		if (!city.equalsIgnoreCase(""))
			enterTextValue(getTxtCityCustomer(), city);
		if (!firstName.equalsIgnoreCase(""))
			enterTextValue(getTxtFirstNameCustomer(), firstName);
		if (!lastName.equalsIgnoreCase(""))
			enterTextValue(getTxtLastNameCustomer(), lastName);
		waitForPageToLoad();
		if (!email.equalsIgnoreCase(""))
			enterTextValue(getTxtEmailAdvanceCustomer(), email);

	}
  	
  	public void verifyRegisteredServiceAccountDetailsC360AndSCPWithDB() throws Exception{
		log.info("Test Case execution for - Verify registered Service Account Details as N/A in C360 Page and in SCP with DB - is Initiated");
		log.info("C133397,C133401 - Verify registered Service Account Details as N/A in C360 Page and in SCP with DB");
		SoftAssert sAssert = new SoftAssert();
		
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		waitForPageLoaderInvisibility();
		explicitWaitForbtnAdvanceSearch();
		
		clickBtnAdvanceSearch();
		waitForPageLoaderInvisibility();
		waitForPageToLoad();
		
		Assert.assertTrue(isBtnAdvanceSearchServiceAccountVisible());
		selectByVisibleText(getDdlRegisteredAcctAdvSearchServAcct(), "Yes");
		clickBtnAdvanceSearchServiceAccount();
		waitForPageLoaderInvisibility();
		waitForPageToLoad();
		
		//Verification on CSR->Customer360 Profile Page.
		String utilityAcctNo = getTableServiceAcctServiceAccountNumberclnLabel();
		log.info("The UnRegistered Service Account Number for the C360Validation is "+utilityAcctNo);
		clickTableServiceAcctServiceAccountNumbercln();
		waitForPageLoaderInvisibility();
		waitForPageToLoad();
		switchToFrame(0);
		waitForPageLoaderInvisibility();
		waitForPageToLoad();
		
		String userName = getLblUsernamePtLabel();
		
		
		//DB
		ResultSet rs;
				rs = DataBaseUtils.getResultSet(SQLQueries.getMyAccountProfileInfo(userName));
				rs.next();
				String nameDB = rs.getString("FullName");
				String usernameDB = rs.getString("UserName");			
				String primaryEmailDB = rs.getString("EmailID");
				String secondaryEmailDB = rs.getString("AlternateEmailID");
				String primaryPhoneDB = rs.getString("MobilePhone");
				String secondaryPhoneDB = rs.getString("HomePhone");
				String lastLoginLocationDB;
				if(secondaryEmailDB.equals(""))
					secondaryEmailDB="N/A";
				if(secondaryPhoneDB.equals(""))
					secondaryPhoneDB="N/A";
				if(rs.getString("CityName")==null||rs.getString("Zipcode")==null)
					lastLoginLocationDB="N/A";
				else
					lastLoginLocationDB = rs.getString("CityName")+" "+rs.getString("Zipcode");
				
				rs = DataBaseUtils.getResultSet(SQLQueries.getTimeZoneDetailsOfUser(userName));
				rs.next();
				String timeZoneDB = rs.getString("TimeZoneCode");
				
				rs = DataBaseUtils.getResultSet(SQLQueries.getAllCustomerDetails(utilityAcctNo));
				rs.next();
				String accountTypeDB = rs.getString("AddressType");
				String cityNameDB = rs.getString("CityName");
				String userStatusDB = getStringUserStatus(userName);
				
				rs = DataBaseUtils.getResultSet(SQLQueries.getUserActivityTrailDetailsWithUsername(userName));
				rs.next();
				String lastLoginIPAndDateDB = rs.getString("IPAddress")+" & "+DateUtil.changeDateFormatOfDBAsPerCSP(rs.getString("ActivityDateTime"), "MMMM dd, yyyy");
				
				
		sAssert.assertTrue(getLblAccountstatusPtLabel().equals(userStatusDB),
				"User Status Field value on C360 Profile Tab Left Pane windows is not matching with DB for Registered User.");
		sAssert.assertTrue(getLblCustomerNamePtLabel().equals(nameDB),
				"Name Field value on C360 Profile Tab Left Pane windows is not matching with DB for Registered User.");
		sAssert.assertTrue(getLblUsernamePtLabel().equals(usernameDB),
				"Username Field value on C360 Profile Tab Left Pane windows is not matching with DB for Registered User.");
		sAssert.assertTrue(getLblAccountTypePtLabel().equals(accountTypeDB),
				"AccountType Field value on C360 Profile Tab Left Pane windows is not matching with DB for Registered User.");
		sAssert.assertTrue(getLblPrimaryEmailIDPtLabel().equals(primaryEmailDB),
				"Primary Email Field value on C360 Profile Tab Left Pane windows is not matching with DB for Registered User.");
		sAssert.assertTrue(getLblSecondaryEmailPtLabel().equals(secondaryEmailDB),
				"Secondary Email Field value on C360 Profile Tab Left Pane windows is not matching with DB for Registered User.");
		sAssert.assertTrue((getLblPrimaryPhoneNumberPtLabel().replace("-", "")).equals(primaryPhoneDB),
				"Primary PhoneNumber Field value on C360 Profile Tab Left Pane windows is not matching with DB for Registered User.");///PhoneNoUtil.getPhoneNumberAsPlainNumbers
		sAssert.assertTrue(PhoneNoUtil.getPhoneNumberAsPlainNumbers(getLblSecondaryPhoneNumberPtLabel()).equals(secondaryPhoneDB),
				"Secondary PhoneNumber Field value on C360 Profile Tab Left Pane windows is not matching with DB for Registered User.");
//		sAssert.assertTrue(getLblCityNamePtLabel().equals(cityNameDB),
//				"CityName Field value on C360 Profile Tab Left Pane windows is not matching with DB for Registered User.");
		sAssert.assertTrue(getLblTimeZonePtLabel().equals(timeZoneDB),
				"TimeZone Field value on C360 Profile Tab Left Pane windows is not matching with DB for Registered User.");
		sAssert.assertTrue(getLblLastLoginTimePtLabel().contains(lastLoginIPAndDateDB),
				"Last Login IP & Date/Time Field value on C360 Profile Tab Left Pane windows is not matching with DB for Registered User.");
		sAssert.assertTrue(getLblLastLoginLocationPtLabel().equals(lastLoginLocationDB),
				"Last Login Location Field value on C360 Profile Tab Left Pane windows is not matching with DB for Registered User.");
		
		//Verification on SCP Profile Page.
		Assert.assertTrue(isBtnLoginToCustomerDisplayed(),
				"Login to customer portal is not Present on Customer360 Popup.");
		clickBtnLoginToCustomer();
		waitForPageToLoad();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		String handleName = tabs.get(1);
		switchToWindow(handleName);
		waitForImgCustomerDetailsLoadingToInvisible();
		waitForPageToLoad();
		String scpURL = driver.getCurrentUrl();
		Assert.assertTrue(scpURL.contains("SCP"), "Login to Customer portal is not redirecting in correct url ");
//		pageUtil.click(dashboardPage.getLnkMyAccount());
//		pageUtil.expWaitForEleVisibility(dashboardPage.getLnkProfile());
//		pageUtil.click(dashboardPage.getLnkProfile());
//		pageUtil.expWaitForEleInvisibility(adminCsrPage.getDivPageLoaderBsp());
//		pageUtil.waitForPageToLoad();
//		pageUtil.expWaitForEleVisibility(myAccountProfilePage.getLnkDownloadDataApp());
//		
//		String nameSCP = pageUtil.getObjectLabel(myAccountProfilePage.getValueName());
//		String userNameSCP = pageUtil.getObjectLabel(myAccountProfilePage.getLblNonEditUsername());
//		String primaryPhoneSCP =pageUtil.getObjectLabel(myAccountProfilePage.getValuePrimaryPhone()).replaceAll("-", "");
//		String secondaryPhoneSCP = pageUtil.getObjectLabel(myAccountProfilePage.getValueSecondaryPhone()).replaceAll("-", "");
//		String primaryEmailSCP = pageUtil.getObjectLabel(myAccountProfilePage.getValuePrimaryEmail());
//		String secondaryEmailSCP = pageUtil.getObjectLabel(myAccountProfilePage.getValueSecondaryEmail());
//		if(secondaryPhoneSCP.equals(""))
//			secondaryPhoneSCP="N/A";
//		if(secondaryEmailSCP.equals(""))
//			secondaryEmailSCP="N/A";
//		
//		sAssert.assertTrue(nameSCP.equals(nameDB),
//				"Name Field value on SCP Profile Information Page is not matching with DB for Registered User.");
//		sAssert.assertTrue(userNameSCP.equals(usernameDB),
//				"Username Field value on SCP Profile Information Page is not blank for Unregistered User.");
//		sAssert.assertTrue(primaryPhoneSCP.equals(primaryPhoneDB),
//				"Primary PhoneNumber Field value on SCP Profile Information Page is not matching with DB for Registered User.");
//		sAssert.assertTrue(secondaryPhoneSCP.equals(secondaryPhoneDB),
//				"Secondary PhoneNumber Field value on SCP Profile Information Page is not matching with DB for Registered User.");
//		sAssert.assertTrue(primaryEmailSCP.equals(primaryEmailDB),
//				"Primary Email Field value on SCP Profile Information Page is not matching with DB for Registered User.");
//		sAssert.assertTrue(secondaryEmailSCP.equals(secondaryEmailDB),
//				"Secondary Email Field value on SCP Profile Information Page is not matching with DB for Registered User.");
		
		sAssert.assertAll();
		log.info("Test Case execution for -  verifyRegisteredServiceAccountDetailsC360AndSCP- is Completed.");
	}
  	
	public String getStringUserStatus(String userName) throws Exception {
		ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getMyAccountProfileInfo(userName));
		rs.next();
		Integer status = rs.getInt("Status");
		String result;
		switch (status) {
		case 0: {
			result = "Pending Activation";
			break;
		}
		case 1: {
			result = "Active";
			break;
		}
		case 2: {
			result = "Inactive";
			break;
		}
		case 5: {
			result = "Temp Locked";
			break;
		}
		default:
			result = "Locked";
			break;
		}
		return result;
	}
	
	public void verifyAdvanceFilterCustomerWithZipAndEmail() throws Exception {
		log.info("Test Case execution for -verifyAdvanceFilterCustomerWithZipAndEmail- is Initiated");
		ExtentLogger.logInfo("C72509-Verify Advance Search filter results in CSR workbench for customer Tab with Zip code and EmailID ");
		
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();

		clickLnkCustomerTab();
		
		clickBtnAdvanceSearch();
		waitForPageToLoad();
		setAdvanceSearchCustomerTab("", "", "", "", "91709", "", "", "", "");
		clickBtnAdvanceSubmitCustomers();
		waitForPageLoaderInvisibility();
		waitForPageToLoad();
		//clickSubmitButtonCustomerTab();
		
		// pageUtil.click(admincsrPage.getTableHeaderNameCustomer());
		
		List<String> uiData = new ArrayList<String>();
		List<WebElement> ele = collectAllSimillarElements(driver, getLnkCustomerNameTable(getColumnNumberOfHeader(getTableCustomer(), "CUSTOMER")));
		for (WebElement webElement : ele) {
			uiData.add(webElement.getText());
		}
		List<String> db_customerName = new ArrayList<String>();
		ResultSet rs;
		try {
			rs = DataBaseUtils.getResultSet(SQLQueries.getAdvanceSearchCustomer("", "", "", "",
					"91709", "", "", "", ""));
			while (rs.next()) {
				String name = rs.getString("FullName");
				db_customerName.add(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("No value configured");
		}
		List<String> subList = new ArrayList<String>();
		// We are verifying for 10 customer after sorting
		if (db_customerName.size() < 10) {
			subList = new ArrayList<>(db_customerName.subList(0, db_customerName.size()));
		}
		else {
			subList = new ArrayList<>(db_customerName.subList(0, 10));
		}
		// Assert.assertEquals(uiData, subList);
		// Re filter in the Advance filter
		clickBtnAdvanceSearch();
		waitForPageToLoad();
		setAdvanceSearchCustomerTab("", "", "", "", "91709", "", "", "", "");
		clickBtnAdvanceSubmitCustomers();
		waitForPageLoaderInvisibility();
		waitForPageToLoad();
		clickTableHeaderNameCustomer();
		waitForPageLoaderInvisibility();
		waitForPageToLoad();
		
		List<String> uiDataRe = new ArrayList<String>();
		List<WebElement> eleRe = collectAllSimillarElements(driver,	getLnkCustomerNameTable(getColumnNumberOfHeader(getTableCustomer(), "NAME")));
		for (WebElement webElement : eleRe) {
			uiDataRe.add(webElement.getText());
		}
		List<String> db_customerNameRe = new ArrayList<String>();
		try {
			rs = DataBaseUtils.getResultSet(SQLQueries.getAdvanceSearchCustomer("", "", "", "",
					"91709", "", "", "", ""));
			while (rs.next()) {
				String name = rs.getString("FullName");
				db_customerNameRe.add(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("No value configured");
		}
		List<String> subListRe = new ArrayList<String>();
		// We are verifying for 10 customer after sorting
		if (db_customerNameRe.size() < 10) {
			subListRe = new ArrayList<>(db_customerNameRe.subList(0, db_customerNameRe.size()));
		}
		else {
			subListRe = new ArrayList<>(db_customerNameRe.subList(0, 10));
		}
		// Assert.assertEquals(uiDataRe, subListRe);
		log.info("Test Case execution for -  verifyAdvanceFilterCustomerWithZipAndEmail - is Completed.");
	}
	
	public void verifyActionAdvSearchFilterForUsersTab() throws Exception {
		log.info("Test Case execution for - Verify Action Perform AdvanceSearch Filter ResultsCustomerTab - is Initiated");
		ExtentLogger.logInfo("C72506-Verify action perform on Advance Search filter results in Customer Tab");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		clickLnkSCMUserTab();
		waitForPageToLoad();
		clickBtnAdvanceSearch();
		waitForPageToLoad();
		setFilterTypeAdvanceSearchUsersTab(csrDataFileName);
		log.info("Test Case execution for - Verify Action Perform AdvanceSearch Filter ResultsCustomerTab - is Initiated");
	}
	
	public void setFilterTypeAdvanceSearchUsersTab(String csrDatatFileName) throws Exception {
		ExcelUtils.openExcelFile(csrDatatFileName);
		ExcelUtils.getSheetName("Users");
		
		for (int rowNum = 1; rowNum <= ExcelUtils.getRowCount(); rowNum++) {
			if (ExcelUtils.getCellValue(rowNum, 0).equals("Yes")) {
				pressTab();
				enterDataCsr(getTxtServiceAccountNumberUser(),	ExcelUtils.getCellValue(rowNum, 1));
				pressTab();
				selectByVisibleTxt(getDdlAccountTypeUser(), ExcelUtils.getCellValue(rowNum, 2));
				pressTab();
				selectByVisibleTxt(getDdlStatus(), ExcelUtils.getCellValue(rowNum, 3));
				pressTab();
				selectByVisibleTxt(getDdlUserType(), ExcelUtils.getCellValue(rowNum, 4));
				pressTab();
				selectByVisibleTxt(getDdlNoOfLinkedUser(), ExcelUtils.getCellValue(rowNum, 5));
				pressTab();
				selectByVisibleTxt(getDdlRole(), ExcelUtils.getCellValue(rowNum, 6));
				pressTab();
				selectByVisibleTxt(getDdlSocialMedia(), ExcelUtils.getCellValue(rowNum, 7));
				enterDataCsr(getTxtCustomerNumberUser(), ExcelUtils.getCellValue(rowNum, 8));
				enterDataCsr(getTxtContactNoUser(), ExcelUtils.getCellValue(rowNum, 9));
				enterDataCsr(getTxtZipcodeUser(), ExcelUtils.getCellValue(rowNum, 10));
				enterDataCsr(getTxtCityUser(), ExcelUtils.getCellValue(rowNum, 11));
				enterDataCsr(getTxtFirstNameUser(), ExcelUtils.getCellValue(rowNum, 12));
				enterDataCsr(getTxtLastNameUser(), ExcelUtils.getCellValue(rowNum, 13));
				enterDataCsr(getTxtEmailAdvanceUser(), ExcelUtils.getCellValue(rowNum, 14));
				boolean result = verifyAdvancesearUser(csrDatatFileName, rowNum);
				if (result = false) {
					log.error("Validation is failing on row number :" + rowNum);
				}
				clickLnkSCMUserTab();
				waitForPageToLoad();
				clickBtnAdvanceSearch();
				waitForPageToLoad();
				clickBtnAdvanceResetUser();
				//sa.assertAll();
			}
		}

	}
	
	public boolean verifyAdvancesearUser(String csrDatatFileName, int rowNum) throws Exception {
		boolean flag = false;
		
	    waitForPageToLoad();
	    clickbtnSearchAdvanceSerachPopup();
	    
	    waitForPageToLoad();
		ExcelUtils.openExcelFile(csrDatatFileName);
		ExcelUtils.getSheetName("Users");
		
		
		String rowCount = DataBaseUtils.getCountRowUser(ExcelUtils.getCellValue(rowNum, 1),
				ExcelUtils.getCellValue(rowNum, 2), ExcelUtils.getCellValueCsr(rowNum, 3),
				ExcelUtils.getCellValue(rowNum, 4), ExcelUtils.getCellValue(rowNum, 5), ExcelUtils.getCellValue(rowNum, 6),
				ExcelUtils.getCellValue(rowNum, 7), ExcelUtils.getCellValue(rowNum, 8), ExcelUtils.getCellValue(rowNum, 9),
				ExcelUtils.getCellValue(rowNum, 10), ExcelUtils.getCellValue(rowNum, 11),
				ExcelUtils.getCellValue(rowNum, 12), ExcelUtils.getCellValue(rowNum, 13),
				ExcelUtils.getCellValue(rowNum, 14));
		int count = Integer.parseInt(rowCount);
		scrollToTop();
		String pageRecords = getLblRecordPaginationLabel();
		String[] records = pageRecords.split("f ");
		String filterCount = records[1];
		if (count == 0) {
			if (verifyObjectLabelEqualsCsr(getLblNoDataFoundUserLabel(),
					adminCustomerTextProp.getPropValue("lblNoDataFoundAdvanceSearchAhp"), rowNum) == true) {
				flag = true;
				//sa.assertAll();
			}
		}
		else {
			if (verifyObjectLabelEqualsCsr(rowCount, filterCount, rowNum) == true) {
				flag = true;
				//sa.assertAll();
				// DB validation
				// Status
				List<String> uiDataStatus = new ArrayList<String>();
				List<WebElement> status = collectAllSimillarElements(driver, getLnkSCMUserTable(getColumnNumberOfHeader(getTableSCMUser(), "STATUS")));
				for (WebElement webElement : status) {
					uiDataStatus.add(webElement.getText());
				}
				ExtentLogger.logInfo("Verifying the Customer Status of the excel and UI");
				verifyStringValueContainsCsr(uiDataStatus.get(0).trim(), ExcelUtils.getCellValue(rowNum, 2));
				// Full Name
				List<String> uiDataFullName = new ArrayList<String>();
				List<WebElement> fullName = collectAllSimillarElements(driver, getLnkSCMUserTable(getColumnNumberOfHeader(getTableSCMUser(), "USER")));
				for (WebElement webElement : fullName) {
					uiDataFullName.add(webElement.getText());
				}
				// Phone and email
				List<String> uiDataContact = new ArrayList<String>();
				List<WebElement> contact = collectAllSimillarElements(driver, getLnkSCMUserTable(getColumnNumberOfHeader(getTableSCMUser(), "CONTACT")));
				for (WebElement webElement : contact) {
					uiDataContact.add(webElement.getText());
				}
				// Linked Accounts
				List<String> uiDataLinkedAccount = new ArrayList<String>();
				List<WebElement> linkedAccount = collectAllSimillarElements(driver,	getLnkSCMUserTable(getColumnNumberOfHeader(getTableSCMUser(), "LINKED ACCOUNTS")));
				for (WebElement webElement : linkedAccount) {
					uiDataLinkedAccount.add(webElement.getText());
				}
				List<String> statusFromDb = DataBaseUtils.getStatusUser(ExcelUtils.getCellValue(rowNum, 1),
						ExcelUtils.getCellValue(rowNum, 2), ExcelUtils.getCellValueCsr(rowNum, 3),
						ExcelUtils.getCellValue(rowNum, 4), ExcelUtils.getCellValue(rowNum, 5),
						ExcelUtils.getCellValue(rowNum, 6), ExcelUtils.getCellValue(rowNum, 7),
						ExcelUtils.getCellValue(rowNum, 8), ExcelUtils.getCellValue(rowNum, 9),
						ExcelUtils.getCellValue(rowNum, 10), ExcelUtils.getCellValue(rowNum, 11),
						ExcelUtils.getCellValue(rowNum, 12), ExcelUtils.getCellValue(rowNum, 13),
						ExcelUtils.getCellValue(rowNum, 14));
				List<String> FullName = DataBaseUtils.getFullNameUser(ExcelUtils.getCellValue(rowNum, 1),
						ExcelUtils.getCellValue(rowNum, 2), ExcelUtils.getCellValueCsr(rowNum, 3),
						ExcelUtils.getCellValue(rowNum, 4), ExcelUtils.getCellValue(rowNum, 5),
						ExcelUtils.getCellValue(rowNum, 6), ExcelUtils.getCellValue(rowNum, 7),
						ExcelUtils.getCellValue(rowNum, 8), ExcelUtils.getCellValue(rowNum, 9),
						ExcelUtils.getCellValue(rowNum, 10), ExcelUtils.getCellValue(rowNum, 11),
						ExcelUtils.getCellValue(rowNum, 12), ExcelUtils.getCellValue(rowNum, 13),
						ExcelUtils.getCellValue(rowNum, 14));
				List<String> MobilePhone = DataBaseUtils.getMobileNumberUser(ExcelUtils.getCellValue(rowNum, 1),
						ExcelUtils.getCellValue(rowNum, 2), ExcelUtils.getCellValueCsr(rowNum, 3),
						ExcelUtils.getCellValue(rowNum, 4), ExcelUtils.getCellValue(rowNum, 5),
						ExcelUtils.getCellValue(rowNum, 6), ExcelUtils.getCellValue(rowNum, 7),
						ExcelUtils.getCellValue(rowNum, 8), ExcelUtils.getCellValue(rowNum, 9),
						ExcelUtils.getCellValue(rowNum, 10), ExcelUtils.getCellValue(rowNum, 11),
						ExcelUtils.getCellValue(rowNum, 12), ExcelUtils.getCellValue(rowNum, 13),
						ExcelUtils.getCellValue(rowNum, 14));
				List<String> EmailID = DataBaseUtils.getEmailIdUser(ExcelUtils.getCellValue(rowNum, 1),
						ExcelUtils.getCellValue(rowNum, 2), ExcelUtils.getCellValueCsr(rowNum, 3),
						ExcelUtils.getCellValue(rowNum, 4), ExcelUtils.getCellValue(rowNum, 5),
						ExcelUtils.getCellValue(rowNum, 6), ExcelUtils.getCellValue(rowNum, 7),
						ExcelUtils.getCellValue(rowNum, 8), ExcelUtils.getCellValue(rowNum, 9),
						ExcelUtils.getCellValue(rowNum, 10), ExcelUtils.getCellValue(rowNum, 11),
						ExcelUtils.getCellValue(rowNum, 12), ExcelUtils.getCellValue(rowNum, 13),
						ExcelUtils.getCellValue(rowNum, 14));
				for (int i = 0; i <= count; i++) {
					scrollToElement(status.get(i));
					verifyStringValueContainsCsr(uiDataStatus.get(i), statusFromDb.get(i));
					verifyStringValueContainsCsr(uiDataFullName.get(i), FullName.get(i));
					verifyStringValueContainsCsr(uiDataContact.get(i), MobilePhone.get(i));
					verifyStringValueContainsCsr(uiDataContact.get(i), EmailID.get(i));
					i = 9;
					break;
				}

			}
		}
		return flag;
	}
	
	public void verifyAdvFilterServiceAccTab() throws Exception {
		log.info("Test Case execution for -verifyAdvanceFilterServiceTabWithServiceAccountNumber- is Initiated");
		ExtentLogger.logInfo("C34025-Verify Advance Search filter results in CSR workbench for Service Account Tab with Service Account Number");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		clickBtnAdvanceSearch();
		waitForPageToLoad();
		setAdvanceSearchServiceAccountTab("411005215818", "", "", "", "", "", "");
		clickBtnAdvanceSearchServiceAccount();
		waitForPageToLoad();
		
		List<String> uiData = new ArrayList<String>();
		List<WebElement> ele = collectAllSimillarElements(driver, getLblAccountType(
				getColumnNumberOfHeader(getTableServiceAccount(), "SERVICE ACCOUNT")));
		for (WebElement webElement : ele) {
			uiData.add(webElement.getText());
		}
		System.out.println("Element Value is " + ele);
		System.out.println(uiData);
		// Assert.assertEquals(Utils.getRbData().getString("serviceAccountNumber"),
		// uiData.get(0));
		Assert.assertEquals("Active", uiData.get(0));
		// Assert.assertEquals(Utils.getRbData().getString("serviceAccountNumberStatus"),
		// uiData.get(0));
		List<String> db_customerName = new ArrayList<String>();
		ResultSet rs;
		try {
			rs = DataBaseUtils.getResultSet(SQLQueries.getAdvanceSearchServiceAccountNumber(
					"411005215818", "", "", "", "", ""));
			while (rs.next()) {
				String name = rs.getString("ServiceAccountnumber");
				db_customerName.add(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ExtentLogger.logFail("No value configured");
			ExtentLogger.logException(e);
		}
		List<String> subList = new ArrayList<String>();
		// We are verifying for 10 customer after sorting
		if (db_customerName.size() < 10) {
			subList = new ArrayList<>(db_customerName.subList(0, db_customerName.size()));
		}
		else {
			subList = new ArrayList<>(db_customerName.subList(0, 10));
		}
		System.out.println(subList);
		// Assert.assertEquals(uiData, subList);
		log.info("Test Case execution for -  verifyAdvanceFilterServiceTabWithServiceAccountNumber - is Completed.");
		ExtentLogger.logInfo("Test Case execution for -  verifyAdvanceFilterServiceTabWithServiceAccountNumber - is Completed.");
	}
	
	public void setAdvanceSearchServiceAccountTab(String serviceAccountNumber, String accountType, String status,
			String customerNumber, String paperless, String zipCode, String city) throws InterruptedException {
		//WebElement el = driver.findElement(By.xpath("//div[@id='scmUsrAdvSearch']//label[text()='Service Account Number:']/following-sibling::input"));
		if (!serviceAccountNumber.equalsIgnoreCase(""))
			// sync problem
			
		// pageUtil.enterTextValue(admincsrPage.getTxtServiceAccountNumber(),
		// serviceAccountNumber);
		System.out.println(serviceAccountNumber);
		// pageUtil.enterTextValue(el, serviceAccountNumber);
		enterData(getTxtServiceAccountNumberService(), serviceAccountNumber);
		// pageUtil.pressTabKey(admincsrPage.getTxtServiceAccountNumber());
		if (!accountType.equalsIgnoreCase(""))
			selectByVisibleTxt(getDdlAccountServiceAccountType(), accountType);
		if (!status.equalsIgnoreCase(""))
			selectByVisibleTxt(getDdlStatus(), status);
		if (!customerNumber.equalsIgnoreCase(""))
			enterTextValue(getTxtCustomerNumberServiceAccount(), customerNumber);
		if (!paperless.equalsIgnoreCase(""))
			selectByVisibleTxt(getDdlPaperLess(), paperless);
		if (!zipCode.equalsIgnoreCase(""))
			enterTextValue(getTxtZipcodeServiceAccount(), zipCode);
		if (!city.equalsIgnoreCase(""))
			enterTextValue(getTxtCityServiceAccount(), city);

	}
	
	public void verifyResetButtonAdvanceSearchCustomer() throws InterruptedException {
		log.info("Test Case execution for -verifyResetButtonAdvanceSearchCustomer- is Initiated");
		ExtentLogger.logInfo("C72514-Verify Reset button on the Advance Filter Customer Tab");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		clickLnkCustomerTab();
		waitForPageToLoad();
		clickBtnAdvanceSearch();
		waitForPageToLoad();
		setAdvanceSearchCustomerTab("411005215818", "", "", "",
				"73301", "", "", "", "hitesh.arora@smartenergywater.in");
		clickBtnAdvanceResetCustomer();
		waitForPageToLoad();
		Assert.assertTrue(verifyResetAdvanceFilterCustomerTab());
		log.info("Test Case execution for - verifyResetButtonAdvanceSearchCustomer - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyResetButtonAdvanceSearchCustomer - is Completed.");
	}
	
	public boolean verifyResetAdvanceFilterCustomerTab() throws InterruptedException {
		boolean flag = false;
		// verifying the blank Service Account Number
		Assert.assertEquals(getAttributeValue(getTxtServiceAccountNumberCustomer(), "value"), "");
		// Verify the Account Type drop down
		Assert.assertEquals(getSelectedValueInDropBox(getDdlAccountTypeCustomer()),
				"---Select---");
		// verify the account status (Not in the scope now)
		// Assert.assertEquals(pageUtil.getSelectedValueInDropBox(admincsrPage.getDdlStatus()),
		// "---Select---");
		// Assert.assertEquals(pageUtil.getSelectedValueInDropBox(admincsrPage.getddlNoOfLinkedUser()),
		// "---Select---");
		// verify customer Number after reset
		Assert.assertEquals(getAttributeValue(getTxtCustomerNumberCustomer(), "value"), "");
		// Contact number
		Assert.assertEquals(getAttributeValue(getTxtContactNoCustomer(), "value"), "");
		// Zip code
		Assert.assertEquals(getAttributeValue(getTxtZipcodeCustomer(), "value"), "");
		// City
		Assert.assertEquals(getAttributeValue(getTxtCityCustomer(), "value"), "");
		// First Name
		Assert.assertEquals(getAttributeValue(getTxtFirstNameCustomer(), "value"), "");
		// Last Name
		Assert.assertEquals(getAttributeValue(getTxtLastNameCustomer(), "value"), "");
		// email
		Assert.assertEquals(getAttributeValue(getTxtEmailAdvanceCustomer(), "value"), "");
		flag = true;
		return flag;
	}
	
	public void verifyResetButtonAdvanceSearchServiceAccountTab() throws InterruptedException {
		log.info("Test Case execution for -verifyResetButtonAdvanceSearchServiceAccoount- is Initiated");
		ExtentLogger.logInfo("C72515-Verify Reset button on the Advance Filter Service Account Tab");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		clickBtnAdvanceSearch();
		waitForPageToLoad();
		// Enter values in all fields
		enterTextValue(getTxtServiceAccountNumberService(), "411003911996");
		selectByVisibleTxt(getDdlAccountServiceAccountType(), "Residential");
		selectByVisibleTxt(getDdlStatusService(), "Active");
		enterTextValue(getTxtCustomerNumberServiceAccount(), "411000344522");
		selectByVisibleTxt(getDdlPaperLess(), "Yes");
		selectByVisibleTxt(getDdlNoOfLinkedAccountService(), "0-1");
		enterTextValue(getTxtZipcodeServiceAccount(), "56680");
		enterTextValue(getTxtCityServiceAccount(), "New york");
		clickBtnAdvanceResetServiceAccount();
		waitForPageToLoad();
		Assert.assertTrue(verifyResetAdvanceFilterServiceAccountTab());
		log.info("Test Case execution for - verifyResetButtonAdvanceSearchCustomer - is Completed.");
	}
	
	public boolean verifyResetAdvanceFilterServiceAccountTab() throws InterruptedException {
		boolean flag = false;
		// Service Account Number (text)
		Assert.assertEquals(getAttributeValue(getTxtServiceAccountNumberService(), "value"), "");
		// Account Type (drpdwn)
		Assert.assertEquals(getSelectedValueInDropBox(getDdlAccountServiceAccountType()),
				"---Select---");
		// Account Status (drpdwn)
		Assert.assertEquals(getSelectedValueInDropBox(getDdlStatusService()), "---Select---");
		// Customer Number (text)
		Assert.assertEquals(getAttributeValue(getTxtCustomerNumberServiceAccount(), "value"),
				"");
		// Opted for Paperless (drpdwn)
		Assert.assertEquals(getSelectedValueInDropBox(getDdlPaperLess()), "---Select---");
		// No. of Linked Users (drpdwn)
		Assert.assertEquals(getSelectedValueInDropBox(getDdlNoOfLinkedAccountService()),
				"---Select---");
		// ZIP Code (text)
		Assert.assertEquals(getAttributeValue(getTxtZipcodeServiceAccount(), "value"), "");
		// City (text)
		Assert.assertEquals(getAttributeValue(getTxtCityServiceAccount(), "value"), "");
		// First Name
		// Assert.assertEquals(pageUtil.getAttributeValue(admincsrPage.getTxtFirstNameAdvance(),
		// "value"), "");
		// Last Name
		// Assert.assertEquals(pageUtil.getAttributeValue(admincsrPage.getTxtLastNameAdvance(),
		// "value"), "");
		// email
		// Assert.assertEquals(pageUtil.getAttributeValue(admincsrPage.getTxtEmailAdvance(),
		// "value"), "");
		flag = true;
		return flag;
	}
	
	public void verifyResetButtonAdvanceSearchUser() throws InterruptedException {
		log.info("Test Case execution for -verifyResetButtonAdvanceSearchCustomer- is Initiated");
		ExtentLogger.logInfo("C72516-Verify Reset button on the Advance Filter for SCM User Tab");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		clickLnkSCMUserTab();
		waitForPageToLoad();
		
		clickBtnAdvanceSearch();
		waitForPageToLoad();
		
		setAdvanceSearchUserTab("411005215818", "", "", "", "", "", "",
				"73301", "", "", "", "hitesh.arora@smartenergywater.in");
		/*
		 * setAdvanceSearchCustomerTab(Utils.getRbData().getString(
		 * "serviceAccountNumber"), "", "", "", Utils.getRbData().getString("zipCode"),
		 * "", "", "", Utils.getRbData().getString("emailID"));
		 */
		clickBtnAdvanceResetUser();
		waitForPageToLoad();
		Assert.assertTrue(verifyResetAdvanceFilterUserTab());
		log.info("Test Case execution for - verifyResetButtonAdvanceSearchCustomer - is Completed.");
	}
	
	public void setAdvanceSearchUserTab(String serviceAccountNumber, String accountType, String status,
			String noLinkAccount, String role, String isSocial, String customerNumber, String zipCode, String city,
			String firstName, String lastName, String email) throws InterruptedException {
		// pageUtil.explicitWaitForWebElement(driver,
		// admincsrPage.getTxtServiceAccountNumber());
		if (!serviceAccountNumber.equalsIgnoreCase(""))
			// sync problem
			
		// pageUtil.enterTextUsingJsExecutor(admincsrPage.getTxtServiceAccountNumber(),
		// serviceAccountNumber);
		enterTextValue(getTxtServiceAccountNumberUserAdv(), serviceAccountNumber);
		if (!accountType.equalsIgnoreCase(""))
			selectByVisibleTxt(getDdlAccountTypeUserAdv(), accountType);
		if (!status.equalsIgnoreCase(""))
			selectByVisibleTxt(getLstUserAdvanceSearchStatus(), status);
		if (!noLinkAccount.equalsIgnoreCase(""))
			selectByVisibleTxt(getDdlLinkAccount(), noLinkAccount);
		if (!role.equalsIgnoreCase(""))
			selectByVisibleTxt(getDdlRole(), role);
		if (!isSocial.equalsIgnoreCase(""))
			selectByVisibleTxt(getDdlSocialMedia(), isSocial);
		if (!customerNumber.equalsIgnoreCase(""))
			enterTextValue(getTxtCustomerNumberUserAdv(), customerNumber);
		if (!zipCode.equalsIgnoreCase(""))
			enterTextValue(getTxtZipcodeUserAdv(), zipCode);
		if (!city.equalsIgnoreCase(""))
			enterTextValue(getTxtCityUserAdv(), city);
		if (!firstName.equalsIgnoreCase(""))
			enterTextValue(getTxtFirstNameUserAdv(), firstName);
		if (!lastName.equalsIgnoreCase(""))
			enterTextValue(getTxtLastNameUserAdv(), lastName);
		if (!email.equalsIgnoreCase(""))
			enterTextValue(getTxtEmailUserAdv(), email);

	}
	
	public boolean verifyResetAdvanceFilterUserTab() throws InterruptedException {
		boolean flag = false;
		// verifying the blank Service Account Number
		Assert.assertEquals(getAttributeValue(getTxtServiceAccountNumberUser(), "value"), "");
		// Verify the Account Type drop down
		Assert.assertEquals(getSelectedValueInDropBox(getDdlAccountTypeUser()), "---Select---");
		// verify the account status
		Assert.assertEquals(getSelectedValueInDropBox(getDdlStatusOnly()), "---Select---");
		Assert.assertEquals(getSelectedValueInDropBox(getDdlLinkAccount()), "---Select---");
		// verify customer Number after reset
		Assert.assertEquals(getSelectedValueInDropBox(getDdlRole()), "---Select---");
		Assert.assertEquals(getSelectedValueInDropBox(getDdlSocialMedia()), "---Select---");
		Assert.assertEquals(getAttributeValue(getTxtCustomerNumberUser(), "value"), "");
		// Zip code
		Assert.assertEquals(getAttributeValue(getTxtZipcodeUser(), "value"), "");
		// City
		Assert.assertEquals(getAttributeValue(getTxtCityUser(), "value"), "");
		// First Name
		Assert.assertEquals(getAttributeValue(getTxtFirstNameUser(), "value"), "");
		// Last Name
		Assert.assertEquals(getAttributeValue(getTxtLastNameUser(), "value"), "");
		// email
		Assert.assertEquals(getAttributeValue(getTxtEmailAdvanceUser(), "value"), "");
		flag = true;
		return flag;
	}
	
	public void verifyCountRegUnRegAccountsAdvSearchWithDB() {
		log.info("Test Case execution for - Verify Registered and Unregistered search results from Advance Search with the DB - is Initiated");
		ExtentLogger.logInfo("C133398,C133398-Verify Registered and Unregistered search results from Advance Search with the DB.");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		// verification for the Advance Search button
	
		Assert.assertTrue(isBtnAdvanceSearchVisible());
		clickBtnAdvanceSearch();
		
		//Registered Accounts Field UI Validation
		Assert.assertTrue(isLblRegisteredAcctAdvSearchServAcctVisible(),
				"Registered Accounts Label is not present on Advance Search for Service Account");
		Assert.assertTrue(isDdlRegisteredAcctAdvSearchServAcctVisible(),
				"Registered Accounts Dropdown is not present on Advance Search for Service Account");
		Assert.assertTrue(getAllOptionsInListBox(getDdlRegisteredAcctAdvSearchServAcct()).containsAll
				(getExpectedElementsTextList(adminCustomerTextProp.getPropValue("ddlValuesRegisteredAccountAdvSearchServAcctAcp"))),
				"Registered Accounts Field Dropdown values are not as Expected on Advance Search for Service Account");
		
		//Registered Accounts
		ResultSet rs;
		List<Integer> linkedUsersList;
		selectByVisibleText(getDdlRegisteredAcctAdvSearchServAcct(), "Yes");
		clickBtnAdvanceSearchServiceAccount();
		waitForImgLoadingSpinnerToInvisible();
		waitForPageToLoad();
		int actRegisteredAccountCountUI = Integer.parseInt(getLblRecordPaginationLabel().split("of")[1].trim());
		int expRegisteredAccountCountDB = 0;
		try {
			rs = DataBaseUtils.getResultSet(SQLQueries.getCountOfRegUnRegAccounts("yes"));
			rs.next();
			expRegisteredAccountCountDB = Integer.parseInt(rs.getString("Count"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals(actRegisteredAccountCountUI, expRegisteredAccountCountDB,
				"Registered Accounts result count from Service Account Number Adv Search is not matching with the DB Count.");
		//Verify the Registered Accounts has LinkedUsers > 0
		boolean linkedUsersNonZero=true;
		linkedUsersList = getAllElementsTextInList(getTblServiceAcctLinkedUserscln())
				.stream().map(s->Integer.parseInt(s)).collect(Collectors.toList());
		for(Integer e: linkedUsersList) {
			if(e==0) {
				linkedUsersNonZero=false;
				break;
			}				
		}
		Assert.assertTrue(linkedUsersNonZero,"LinkedUsers Column values is Zero for Registered Accounts.");
		
		//UnRegistered accounts
		clickBtnAdvanceSearch();
		waitForPageToLoad();
		
		selectByVisibleText(getDdlRegisteredAcctAdvSearchServAcct(), "No");
		clickBtnAdvanceSearchServiceAccount();		
		waitForImgLoadingSpinnerToInvisible();
		waitForPageToLoad();
		int actUnRegisteredAccountCountUI = Integer.parseInt(getLblRecordPaginationLabel().split("of")[1].trim());
		int expUnRegisteredAccountCountDB = 0;
		try {
			rs = DataBaseUtils.getResultSet(SQLQueries.getCountOfRegUnRegAccounts("no"));
			rs.next();
			expUnRegisteredAccountCountDB = Integer.parseInt(rs.getString("Count"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals(actUnRegisteredAccountCountUI, expUnRegisteredAccountCountDB,
				"UnRegistered Accounts result count from Service Account Number Adv Search is not matching with the DB Count.");
		
		//Verify the UnRegistered Accounts has LinkedUsers = 0
		boolean linkedUsersZero=true;
		linkedUsersList = getAllElementsTextInList(getTblServiceAcctLinkedUserscln())
				.stream().map(s->Integer.parseInt(s)).collect(Collectors.toList());
		for(Integer e: linkedUsersList) {
			if(e>0) {
				linkedUsersZero=false;
				break;
				}				
			}
		Assert.assertTrue(linkedUsersZero, "LinkedUsers Column values is Non-Zero for UnRegistered Accounts.");
		
		log.info("Test Case execution for -  verifyCountRegUnRegAccountsAdvSearchWithDB- is Completed.");
	}
	
	public void verifyUnRegisteredServiceAccountDetailsC360AndSCP(){
		log.info("Test Case execution for - Verify Unregistered Service Account Details as N/A in C360 Page and in SCP as blank - is Initiated");
		ExtentLogger.logInfo("C133400 - Verify Unregistered Service Account Details as N/A in C360 Page and in SCP as blank");
		SoftAssert sAssert = new SoftAssert();
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		//UnRegistered accounts
		clickBtnAdvanceSearch();
		waitForPageToLoad();
		selectByVisibleText(getDdlRegisteredAcctAdvSearchServAcct(), "No");
		clickBtnAdvanceSearchServiceAccount();		
		waitForImgLoadingSpinnerToInvisible();
		waitForPageToLoad();
		
		//Verification on CSR->Customer360 Profile Page.
		ExtentLogger.logInfo("The UnRegistered Service Account Number for the C360Validation is: "
		         + getTableServiceAcctServiceAccountNumberclnLabel());
	    clickTableServiceAcctServiceAccountNumbercln();
	    waitForImgCustomerDetailsLoadingToInvisible();
		switchToFrame(0);
		waitForImgCustomerDetailsLoadingToInvisible();
		waitForPageToLoad();
		
		sAssert.assertTrue(getLblAccountstatusPtLabel().equals("N/A"),
				"User Status Field value on C360 Profile Tab Left Pane windows is not N/A for Unregistered User.");
		sAssert.assertTrue(getLblCustomerNamePtLabel().equals("N/A"),
				"Customer Name Field value on C360 Profile Tab Left Pane windows is not N/A for Unregistered User.");
		sAssert.assertTrue(getLblUsernamePtLabel().equals("N/A"),
				"Username Field value on C360 Profile Tab Left Pane windows is not N/A for Unregistered User.");
		sAssert.assertTrue(getLblPrimaryEmailIDPtLabel().equals("N/A"),
				"Primary Email Field value on C360 Profile Tab Left Pane windows is not N/A for Unregistered User.");
		sAssert.assertTrue(getLblSecondaryEmailPtLabel().equals("N/A"),
				"Secondary Email Field value on C360 Profile Tab Left Pane windows is not N/A for Unregistered User.");
		sAssert.assertTrue(getLblPrimaryPhoneNumberPtLabel().equals("N/A"),
				"Primary PhoneNumber Field value on C360 Profile Tab Left Pane windows is not N/A for Unregistered User.");
		sAssert.assertTrue(getLblSecondaryPhoneNumberPtLabel().equals("N/A"),
				"Secondary PhoneNumber Field value on C360 Profile Tab Left Pane windows is not N/A for Unregistered User.");
//		sAssert.assertTrue(!pageUtil.getObjectLabel(adminCsrPage.getlblCityNamePt()).equals("N/A"),
//				"CityName Field value on C360 Profile Tab Left Pane windows is not N/A for Unregistered User.");
		sAssert.assertTrue(getLblTimeZonePtLabel().equals("N/A"),
				"TimeZone Field value on C360 Profile Tab Left Pane windows is not N/A for Unregistered User.");
		sAssert.assertTrue(getLblLastLoginTimePtLabel().equals("N/A & N/A"),
				"Last Login IP & Date/Time Field value on C360 Profile Tab Left Pane windows is not N/A for Unregistered User.");
		sAssert.assertTrue(getLblLastLoginLocationPtLabel().equals("N/A"),
				"Last Login Location Field value on C360 Profile Tab Left Pane windows is not N/A for Unregistered User.");
		
		//Verification on SCP Profile Page.
		Assert.assertTrue(isBtnLoginToCustomerDisplayed(),
				"Login to customer portal is not Present on Customer360 Popup.");

		
		sAssert.assertAll();
		log.info("Test Case execution for -  verifyUnRegisteredServiceAccountDetailsC360AndSCP- is Completed.");
	}
	
	public void verifySearchServiceAccountTab() throws Exception {

		log.info("Test Case execution for - verify Search Service AccountTab with Service Account Number- is Initiated");
		ExtentLogger.logInfo("C72488 - Search by Entering Service Account Number in Service Account Tab");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		log.info("C72488 - Search by Entering Service Account Number in Service Account Tab");
		setUniversalSearch(csrDataFileName);

		log.info("Test Case execution for - Test Case execution for - verify Search Service AccountTab with Service Account Number - is Completed.");

		log.info("Test Case execution for - Test Case execution for - verify Search Service AccountTab with Customer phone number - is Completed.");

	}
	
	public void setUniversalSearch(String csrDatatFileName) throws Exception {
		ExcelUtils.openExcelFile(csrDatatFileName);
		ExcelUtils.getSheetName("UniversalSearchService");
		
		for (int rowNum = 1; rowNum <= ExcelUtils.getRowCount(); rowNum++) {
			if (ExcelUtils.getCellValue(rowNum, 0).equals("Yes")) {
				if (ExcelUtils.getCellValue(rowNum, 1).equals("ServiceAccount")) {
					selectByVisibleTextDdSearchItem("Service Account");//Service A/C Number
					
					populateTxtSearchCustomer(ExcelUtils.getCellValue(rowNum, 2));
					// actionUtil.enterDataUniversalSearch(admincsrPage.gettxtSearchCustomer(),
					// ExcelUtil.getCellValue(rowNum, 1));
					clickBtnSearch();
					waitForPageToLoad();
					verifyresultsofMode1Search(rowNum, 2);
					sa.assertAll();
				}
				else if (ExcelUtils.getCellValue(rowNum, 1).equals("ZipCode")) {
					//pageUtil.clearTextBox(adminCsrPage.gettxtSearchCustomer());
					// pageUtil.pageRefresh();
					selectByVisibleTextDdSearchItem("ZIP Code");
					populateTxtSearchCustomer(ExcelUtils.getCellValue(rowNum, 2));
					clickBtnSearch();
					waitForPageToLoad();
					verifyresultsofMode1Search(rowNum, 2);
					sa.assertAll();
				}
				else if (ExcelUtils.getCellValue(rowNum, 1).equals("CustomerName")) {
					//pageUtil.clearTextBox(adminCsrPage.gettxtSearchCustomer());
					// pageUtil.pageRefresh();
					selectByVisibleTextDdSearchItem("Customer Name");
					populateTxtSearchCustomer(ExcelUtils.getCellValue(rowNum, 2));
					clickBtnSearch();
					waitForPageToLoad();
					verifyresultsofMode1Search(rowNum, 2);
					sa.assertAll();
				}
				else if (ExcelUtils.getCellValue(rowNum, 1).equals("Username")) {
					//pageUtil.clearTextBox(adminCsrPage.gettxtSearchCustomer());
					// pageUtil.pageRefresh();
					selectByVisibleTextDdSearchItem("Username");
					populateTxtSearchCustomer(ExcelUtils.getCellValue(rowNum, 2));
					clickBtnSearch();
					waitForPageToLoad();
					verifyresultsofMode1Search(rowNum, 2);
					sa.assertAll();
				}
				else if (ExcelUtils.getCellValue(rowNum, 1).equals("CustomerEmail")) {
					//pageUtil.clearTextBox(adminCsrPage.gettxtSearchCustomer());
					// pageUtil.pageRefresh();
					selectByVisibleTextDdSearchItem("Primary Email");
					populateTxtSearchCustomer(ExcelUtils.getCellValue(rowNum, 2));
					clickBtnSearch();
					waitForPageToLoad();
					verifyresultsofMode1Search(rowNum, 2);
					sa.assertAll();
				}
				else if (ExcelUtils.getCellValue(rowNum, 1).equals("CustomerNumber")) {
					//pageUtil.clearTextBox(adminCsrPage.gettxtSearchCustomer());
					// pageUtil.pageRefresh();
					selectByVisibleTextDdSearchItem("Customer Number");
					populateTxtSearchCustomer(ExcelUtils.getCellValue(rowNum, 2));
					clickBtnSearch();
					waitForPageToLoad();
					verifyresultsofMode1Search(rowNum, 2);
					sa.assertAll();
				}
				else if (ExcelUtils.getCellValue(rowNum, 1).equals("PrimaryPhone")) {
					//pageUtil.clearTextBox(adminCsrPage.gettxtSearchCustomer());
					// pageUtil.pageRefresh();
					selectByVisibleTextDdSearchItem("Primary Phone");
					populateTxtSearchCustomer(ExcelUtils.getCellValue(rowNum, 2));
					clickBtnSearch();
					waitForPageToLoad();
					verifyresultsofMode1Search(rowNum, 2);
					sa.assertAll();
				}
			}
		}
	}
	SoftAssert sa = new SoftAssert();
	public void verifyresultsofMode1Search(int rowNum, int colNum) throws Exception {
		String rowCount = getCountRowMode1(ExcelUtils.getCellValue(rowNum, colNum));
		int count = Integer.parseInt(rowCount);
		String pageRecords = getLblRecordPaginationLabel();
		String[] records = pageRecords.split("f ");
		String filterCount = records[1];
		if (count == 0) {
			if (verifyObjectLabelEqualsCsr(getLblNoDataFoundLabel(),
					adminCustomerTextProp.getPropValue("lblNoDataFoundAdvanceSearchAhp"), rowNum) == true) {
				sa.assertAll();
			}

		}
		else {
			if (verifyObjectLabelEqualsCsr(rowCount, filterCount, rowNum) == true) {
				sa.assertAll();
				// Status
				List<String> uiDataStatus = new ArrayList<String>();
				List<WebElement> status = collectAllSimillarElements(driver, getLblAccountType(getColumnNumberOfHeader(getTableServiceAccount(), "STATUS")+1));
				for (WebElement webElement : status) {
					uiDataStatus.add(webElement.getText());
				}
				// Service Account Number
				List<String> uiDataServiceAccount = new ArrayList<String>();
				List<WebElement> serviceAccount = collectAllSimillarElements(driver, getLblAccountType(getColumnNumberOfHeader(getTableServiceAccount(), "SERVICE ACCOUNT")+1));
				for (WebElement webElement : serviceAccount) {
					uiDataServiceAccount.add(webElement.getText());
				}
				log.info("Verifying the Utility Account Number of the excel and UI");
				verifyStringValueContainsCsr(uiDataServiceAccount.get(0).trim(),
						ExcelUtils.getCellValue(rowNum, 1));//ExcelUtil.getCellValue(rowNum, 1)
				// Customer Number
				List<String> uiDataCustomerNumber = new ArrayList<String>();
				List<WebElement> customerNumber = collectAllSimillarElements(driver, getLblAccountType(getColumnNumberOfHeader(getTableServiceAccount(), "CUSTOMER NUMBER")+1));
				for (WebElement webElement : customerNumber) {
					uiDataCustomerNumber.add(webElement.getText());
				}
				// Full Name
				List<String> uiDataFullName = new ArrayList<String>();
				List<WebElement> fullName = collectAllSimillarElements(driver, getLblAccountType(getColumnNumberOfHeader(getTableServiceAccount(), "CUSTOMER")+1));
				for (WebElement webElement : fullName) {
					uiDataFullName.add(webElement.getText());
				}
				// Phone and email
				List<String> uiDataContact = new ArrayList<String>();
				List<WebElement> contact = collectAllSimillarElements(driver, getLblAccountType(getColumnNumberOfHeader(getTableServiceAccount(), "CONTACT")+1));
				for (WebElement webElement : contact) {
					uiDataContact.add(webElement.getText());
				}
				// Linked Accounts
				List<String> uiDataLinkedAccount = new ArrayList<String>();
				List<WebElement> linkedAccount = collectAllSimillarElements(driver,	getLblAccountType(getColumnNumberOfHeader(getTableServiceAccount(), "LINKED USERS")+1));
				for (WebElement webElement : linkedAccount) {
					uiDataLinkedAccount.add(webElement.getText());
				}
				// Account Type
				List<String> uiDataAccountType = new ArrayList<String>();
				List<WebElement> accountType = collectAllSimillarElements(driver, getLblAccountType(getColumnNumberOfHeader(getTableServiceAccount(), "ACCOUNT TYPE")+1));
				for (WebElement webElement : accountType) {
					uiDataAccountType.add(webElement.getText());
				}

				List<String> statusFromDb = DataBaseUtils
						.getAccountStatusUniversalSearch(ExcelUtils.getCellValue(rowNum, colNum));
				List<String> ServiceAccountNumber = DataBaseUtils
						.getServiceAccountNumberUniversalSearch(ExcelUtils.getCellValue(rowNum, colNum));
				List<String> CustomerNumber = DataBaseUtils
						.getCustomerNameUniversalSearch(ExcelUtils.getCellValue(rowNum, colNum));
				List<String> FullName = DataBaseUtils
						.getCustomerNameUniversalSearch(ExcelUtils.getCellValue(rowNum, colNum));
				List<String> MobilePhone = DataBaseUtils
						.getPrimaryPhoneUniversalSearch(ExcelUtils.getCellValue(rowNum, colNum));
				List<String> EmailID = DataBaseUtils
						.getCustomersemailUniversalSearch(ExcelUtils.getCellValue(rowNum, colNum));
				List<String> AccountType = DataBaseUtils
						.getAccountTypeUniversalSearch(ExcelUtils.getCellValue(rowNum, colNum));
				for (int i = 0; i <= count; i++) {
					scrollToElement(status.get(i));
					verifyStringValueContainsCsr(uiDataStatus.get(i).trim(), statusFromDb.get(i));
					verifyStringValueContainsCsr(uiDataServiceAccount.get(i).trim(),
							ServiceAccountNumber.get(i));
					verifyStringValueContainsCsr(uiDataCustomerNumber.get(i).trim(), CustomerNumber.get(i));
					verifyStringValueContainsCsr(uiDataFullName.get(i).trim(), FullName.get(i));
					verifyStringValueContainsCsr(uiDataContact.get(i).trim(), MobilePhone.get(i));
					verifyStringValueContainsCsr(uiDataContact.get(i).trim(), EmailID.get(i));
					verifyStringValueContainsCsr(uiDataAccountType.get(i).trim(), AccountType.get(i));
					i = 9;
					break;

				}

			}
		}

	}
	
	public static String getCountRowMode1(String itemToSearch) {
        String value = "";
        String sQuery = SQLQueries.getCountUniversalSearchResultMode1(itemToSearch);
        ResultSet rs = DataBaseUtils.getResultSet(sQuery);
        try {
            while (rs.next()) {
                value = rs.getString("Records");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
	
	public static List<String> getAccountTypeUniversalSearch(String itemToSearch) {
        String sQuery = SQLQueries.getUniversalSearchResultMode1(itemToSearch);
        ResultSet rs = DataBaseUtils.getResultSet(sQuery);
        List<String> list = new ArrayList();
        try {
            while (rs.next()) {
                String value = rs.getString("AccountType");
                list.add(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
	
	
	public void validateCustomer360View(int rowNum) throws Exception{
		//pageUtil.click(adminUniversalSearchPage.getLnkFirstInGridTable());
		//driver.findElement(By.xpath("//table[@id='Search_report_Grid']//tbody//tr//td[1]//a")).click();
		waitForImgCustomerDetailsLoadingToInvisible();
		switchToFrame(0);
		waitForImgCustomerDetailsLoadingToInvisible();
		waitForPageToLoad();
		Assert.assertTrue(isLnkProfileTabVisible());
		Assert.assertTrue(isLnkpropertyTabVisible());
		Assert.assertTrue(isLnkUserNotificationTabVisible());
		Assert.assertTrue(isLnkBillingTabVisible());
		Assert.assertTrue(isLnkConnectMeRequestTabVisible());
		Assert.assertTrue(isLnkServiceRequestTabVisible());
		Assert.assertTrue(isLnkEfficiencyTabVisible());
		Assert.assertTrue(isLnkUsageTabVisible());
		Assert.assertTrue(isLnkCompareTabVisible());
		//Assert.assertTrue(isLnkEVTabVisible());
		Assert.assertTrue(isLnkOutageTabVisible());
		Assert.assertTrue(isLnkUserGuestTabVisible());
		Assert.assertTrue(isLnkUserInteractionTabVisible());
		

		// verify address drop down
		List<String> allOptionsInListBox = getAllDdlAddress();
		Assert.assertEquals(allOptionsInListBox.size(), 1);


		navigateTabCustomerDetailSection("Profile");
		// verify the Details on the Customer Details Page
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertTrue(isLblCustomerNameVisible());
		ExtentLogger.logInfo("Customer Name :" + isLblCustomerNameVisible());

		//softAssert.assertTrue(isLblZipCodeVisible());
		//ExtentLogger.logInfo("Customer Zip Code :" + isLblZipCodeVisible());

		softAssert.assertTrue(isLblLoginIdVisible());
		ExtentLogger.logInfo("Customer LoginId :" + isLblLoginIdVisible());

		softAssert.assertTrue(isLblStatusUserVisible());
		ExtentLogger.logInfo("Customer Status :" + isLblStatusUserVisible());

		softAssert.assertTrue(isLblEmailIdVisible());
		ExtentLogger.logInfo("Customer Email Id :" + isLblEmailIdVisible());

		softAssert.assertTrue(isLblAccountTypeVisible());
		ExtentLogger.logInfo("Customer Account Type :" + isLblAccountTypeVisible());

		softAssert.assertTrue(isLblAlternateEmailIdVisible());
		ExtentLogger.logInfo("Customer Alternate Email Id :" + isLblAlternateEmailIdVisible());

		softAssert.assertTrue(isLblloginIPVisible());
		ExtentLogger.logInfo("Customer Login IP :" + isLblloginIPVisible());

		softAssert.assertTrue(isLblMobileNumberVisible());
		ExtentLogger.logInfo("Customer Login IP :" + isLblMobileNumberVisible());

		softAssert.assertTrue(isLblPaperBillVisible());
		ExtentLogger.logInfo("Customer Paper Bill :" + isLblPaperBillVisible());

		softAssert.assertTrue(isLblAlternateNumberVisible());
		ExtentLogger.logInfo("Customer AlternateNumber :" + isLblAlternateNumberVisible());

		softAssert.assertTrue(isLblCreateDateVisible());
		ExtentLogger.logInfo("Customer Create Date :" + isLblCreateDateVisible());

		//softAssert.assertTrue(isLblCityVisible());
		//ExtentLogger.logInfo("Customer City Name :" +isLblCityVisible());

		softAssert.assertTrue(isLblloginlocationVisible());
		ExtentLogger.logInfo("Customer login Location :" + isLblloginlocationVisible());

		softAssert.assertTrue(isLblUsertimezoneVisible());
		ExtentLogger.logInfo("Customer user TimeZone :" + isLblUsertimezoneVisible());
		
		clickLnkBackToCSRSearch();
		waitForPageToLoad();
		softAssert.assertAll();
	}
	
	public void verifyAddCustomerLinkCSR() throws Exception {
		log.info("Test Case execution for -verifyAddCustomerLinkCSR- is Initiated");
		ExtentLogger.logInfo("92048 - Verify the Add Customer Link in CSR");
		SoftAssert sAssert = new SoftAssert();
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		explicitWaitForLnkAddCustomer();
		scrollToTheBottomOfPage();
		clickLnkAddCustomer();
		String parentWindow = getParentWindow();
		switchToChildWindow();
		waitForPageToLoad();
		System.out.println(getCurrentUrl());
		sAssert.assertTrue(getCurrentUrl().contains(adminCustomerTextProp.getPropValue("expRegPageUrl").toLowerCase()));
		sAssert.assertEquals(getCurrentTitle(), adminCustomerTextProp.getPropValue("expRegPageTitle"));
//		sAssert.assertEquals(pageUtil.getLocatorText(customerRegistrationPage.getLblUserRegistrationHeading()),
//				adminCustomerTextProp.getPropValue("txtLblCustomerRegistrationHeadingCrp"),"Customer Registration Page Heading is not as Expected.");
//		sAssert.assertTrue(pageUtil.verifyElementVisible(customerRegistrationPage.getTxtBoxAccountNumber()),
//				"Account Number Text Box is not Visible on the Registration Page.");
//		sAssert.assertTrue(pageUtil.verifyElementVisible(customerRegistrationPage.getTxtBoxPostalCode()),
//				"Postal Code Text Box is not Visible on the Registration Page.");
		driver.close();
		switchMainWindow(parentWindow);
		sAssert.assertAll();;
		log.info("Test Case execution for - verifyAddCustomerLinkCSR - is Completed.");
	}
	
	public void switchToChildWindow() {
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> I1 = s1.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			// Here we will compare if parent window is not equal to child
			// window then we will close
			if (!getParentWindow().equals(child_window)) {
				driver.switchTo().window(child_window);

			}
		}
	}
	
	public String getParentWindow() {
		String parent = driver.getWindowHandle();
		return parent;
	}
	
	public void switchMainWindow(String wparent) {
		driver.switchTo().window(wparent);
	}
	
	public void verifyPropertyTab360View() throws Exception {
		log.info("Test Case execution for -Verify the Property Tab Details for Customer in CSR- is initiated");
		ExtentLogger.logInfo("C72569 Verify the Property Tab Details for Customer in CSR");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		// Entering value in the search box
		//enterValueSearchFilter(Configuration.toString("utilityAccountNumber"));
		String utilityNumber = "R022068069";
		enterValueSearchFilter(utilityNumber);
		
		waitForPageToLoad();
		clickLnkServiceAccountNumber();
		waitForImgCustomerDetailsLoadingToInvisible();
		switchToFrame(0);
		waitForImgCustomerDetailsLoadingToInvisible();
		waitForPageToLoad();
		
		clickLnkpropertyTab();
		Map<String, String> details = new HashMap<String, String>();
		List<String> key = new ArrayList<String>();
		List<String> value = new ArrayList<String>();
		WebElement mytable = getLblPropertyDetail();
		// To locate rows of table.
		List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
		// To calculate no of rows In table.
		int rows_count = rows_table.size();
		for (int row = 0; row < rows_count; row++) {
			// To locate columns(cells) of that specific row.
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			// To calculate no of columns (cells). In that specific row.
			int columns_count = Columns_row.size();
			System.out.println("Number of cells In Row " + row + " are " + columns_count);
			// Loop will execute till the last cell of that specific row.
			for (int column = 0; column < columns_count; column++) {
				// To retrieve text from that specific cell.
				String celtext = Columns_row.get(column).getText();
				System.out
				.println("Cell Value of row number " + row + " and column number " + column + " Is " + celtext);
				if (column % 2 == 0) {
					key.add(celtext);
				}
				else {
					value.add(celtext);
				}
			}
			System.out.println("-------------------------------------------------- ");
		}
		System.out.println(key);
		System.out.println(value);
		for (int i = 0; i < key.size(); i++) {
			details.put(key.get(i), value.get(i));
		}
		System.out.println(details);
		// DB validations
		ResultSet CustPropertyResultSet = DataBaseUtils
				.getResultSet(SQLQueries.getCustomerPropertyDetails(utilityNumber));
		CustPropertyResultSet.next();
		String serviceAccountNum_DB = CustPropertyResultSet.getString("UtilityAccountNumber");
		System.out.println(serviceAccountNum_DB);
		// pageUtil.verifyStringValue(details.get("Service Account Number:").trim(),
		// serviceAccountNum_DB);
		String cityName_DB = CustPropertyResultSet.getString("CityName");
		System.out.println(cityName_DB);
		// pageUtil.verifyStringValue(details.get("City Name:").trim(), cityName_DB);
		String accountType_DB = CustPropertyResultSet.getString("AddressType");
		System.out.println(accountType_DB);
		// pageUtil.verifyStringValue(details.get("Account Type:").trim(),
		// accountType_DB);
		String zipCode_DB = CustPropertyResultSet.getString("Zipcode");
		System.out.println(zipCode_DB);
		// pageUtil.verifyStringValue(details.get("Zip Code:").trim(), zipCode_DB);
	}
	
	public void verifyCSRworkbenchUI() throws Exception {
		log.info("Test Case execution for - verifyCSRWorkBenchUI - is Initiated");
		ExtentLogger.logInfo("C72449-Verify the UI for CSR workbench");
		SoftAssert sa = new SoftAssert();
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		log.info("Test Case execution for -  verifyCSRWorkBenchUI - is Completed.");
		// Verify tabs
		Assert.assertTrue(isLnkServiceAccountActiveTabDisplayed());
		Assert.assertTrue(isLnkServiceAccountOthersTabDisplayed());
		Assert.assertTrue(isLnkCustomerTabDisplayed());
		Assert.assertTrue(isLnkSCMUserActiveTabDisplayed());
		Assert.assertTrue(isLnkSCMUserOthersTabDisplayed());
		// verifications of the value in the tables(count of service account
		// customer and user)
		ExtentLogger.logInfo("C19433-Verify the Number Customer counts on the page");
		
		List<WebElement> allCounts = getLblCountTotal();
		List<WebElement> allCountsActive = getLblCountTotalActive();
		List<WebElement> allCountsInActive = getLblCountTotalInActive();
		// fetching all the value from the Service,Customer and SCM users
		int totalServiceAccountUI = Integer.parseInt(getText(allCounts.get(0)).replace(",", ""));
		// fetching value from DataBase with service account Number
		ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getServiceAccountNumberCount());
		rs.next();
		int totalServiceAccountNumberCountDB = Integer.parseInt(rs.getString("TotalServiceAccountNumberCount"));
		sa.assertEquals(totalServiceAccountUI, totalServiceAccountNumberCountDB,
				"The Count for Service Account is not match with DB");
		int totalCustomersUI = Integer.parseInt(getText(allCounts.get(1)).replace(",", ""));
		// fetching value from DataBase with service account Number
		rs = DataBaseUtils.getResultSet(SQLQueries.getCustomerCount());
		rs.next();
		int totalCustomerCountDB = Integer.parseInt(rs.getString("TotalCustomerNumber").replace(",", ""));
		Assert.assertEquals(totalCustomersUI, totalCustomerCountDB,
				"The Count for Customer numbers is not match with DB");
		String totalSCMUser = getText(allCounts.get(2));
		// verify the Active part for Service,Customer and SCM user
		int totalActiveServiceAccountUI = Integer.parseInt(getText(allCountsActive.get(0)).replace(",", ""));
		// fetching value from DataBase with service account Number
		rs = DataBaseUtils.getResultSet(SQLQueries.getServiceAccountNumberCountActive());
		rs.next();
		int totalServiceAccountNumberCountActiveDB = Integer.parseInt(rs.getString("ActiveServiceAccountNumberCount"));
		Assert.assertEquals(totalActiveServiceAccountUI, totalServiceAccountNumberCountActiveDB,
				"The Count for Service Account Active is not match with DB");
		int totalActiveCustomersUI = Integer.parseInt(getText(allCounts.get(1)).replace(",", ""));
		rs = DataBaseUtils.getResultSet(SQLQueries.getCustomerCountActive());
		rs.next();
		int totalCustomerCountActiveDB = Integer.parseInt(rs.getString("TotalCustomerNumberActive").replace(",", ""));
		sa.assertEquals(totalActiveCustomersUI, totalCustomerCountActiveDB,
				"The Count for Customer Active is not match with DB");
		String totalActiveSCMUser = getText(allCountsActive.get(0));
		// fetching all the value from the InActive ServiceAccount,Customer and
		// SCM user //DB table Need to understand
		int totalInActiveServiceAccountUI = Integer.parseInt(getText(allCountsInActive.get(0)));
		sa.assertEquals((totalServiceAccountUI - totalActiveServiceAccountUI), (totalInActiveServiceAccountUI));
		int totalInActiveCustomersUI = Integer.parseInt(getText(allCountsInActive.get(1)).replace(",", ""));
		sa.assertEquals((totalCustomersUI - totalActiveCustomersUI), (totalInActiveCustomersUI));
		String totalInActiveSCMUser = getText(allCountsInActive.get(0));
		// Verify Send Notification link
		Assert.assertTrue(isBtnSendNotificationDisplayed());
		// Verify global search fields
		Assert.assertTrue(isBtnSearchDisplayed());
		Assert.assertTrue(isTxtSearchCustomerDisplayed());
		Assert.assertTrue(isDdSearchItemDisplayed());
		Assert.assertTrue(isBtnAdvanceSearchVisible());
		// Verify header fields
		Assert.assertTrue(isLogoUtilityDisplayed());
		Assert.assertTrue(isIconProfileDisplayed());
		Assert.assertTrue(isTxtWelcomeDisplayed());
		Assert.assertTrue(isTxtAdminUserNameDisplayed());
		//Assert.assertTrue(isIconHamburgerDisplayed());
		scrollToBottom();
		// Verify Quick links
		Assert.assertTrue(isLnkSideBarBlockedIpDisplayed());
		
		// Verify pagination fields
		Assert.assertTrue(isDdlPageSizeDisplayed());
		Assert.assertTrue(isBtnPageNextDisplayed());
		Assert.assertTrue(isBtnPagePreviousDisplayed());
		// Verify footer links
		Assert.assertTrue(isLinkVersionDisplayed());
		Assert.assertTrue(isLinkCopyrightDisplayed());
		Assert.assertTrue(isTxtSEWDisplayed());
		sa.assertAll();
		ExtentLogger.logInfo("C19433-Execution is completed");
	}
	
	public void verifyCSRTabFunctionality() throws Exception {
		ExtentLogger.logInfo("Test Case execution for - VerifyCSRTabFunctionality - is Initiated");
		ExtentLogger.logInfo("C72459-Verify the UI for CSR workbench");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		ExtentLogger.logInfo("Test Case execution for -  VerifyCSRTabFunctionality - is Completed.");
		// Verify tabs
		SoftAssert sa= new SoftAssert();
		sa.assertTrue(isLnkServiceAccountActiveTabDisplayed());
		sa.assertTrue(isLnkServiceAccountOthersTabDisplayed());
		sa.assertTrue(isLnkCustomerTabDisplayed());
		sa.assertTrue(isLnkSCMUserActiveTabDisplayed());
		sa.assertTrue(isLnkSCMUserOthersTabDisplayed());
		// verifications of the value in the tables(count of service account
		// customer and user)
		ExtentLogger.logInfo("C19433-Verify the Number Customer counts on the page");
		
		List<WebElement> allCounts = getLblCountTotal();
		List<WebElement> allCountsActive = getLblCountTotalActive();
		List<WebElement> allCountsInActive = getLblCountTotalInActive();
		// fetching all the value from the Service,Customer and SCM users
		int totalServiceAccountUI = Integer.parseInt(getText(allCounts.get(0)).replace(",", ""));
		// fetching value from DataBase with service account Number
		ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getServiceAccountNumberCount());
		rs.next();
		int totalServiceAccountNumberCountDB = Integer.parseInt(rs.getString("TotalServiceAccountNumberCount"));
		sa.assertEquals(totalServiceAccountUI, totalServiceAccountNumberCountDB,
				"The Count for Total Service Account Count from UI is not match with DB");
		int totalCustomersUI = Integer.parseInt(getText(allCounts.get(1)).replace(",", ""));
		// fetching value from DataBase with service account Number
		rs = DataBaseUtils.getResultSet(SQLQueries.getCustomerCount());
		rs.next();
		int totalCustomerCountDB = Integer.parseInt(rs.getString("TotalCustomerNumber").replace(",", ""));
		sa.assertEquals(totalCustomersUI, totalCustomerCountDB,
				"The Count for Total Customer from UI is not match with DB");
		int totalSCMUsersUI = Integer.parseInt(getText(allCounts.get(2)).replace(",", ""));
		rs = DataBaseUtils.getResultSet(SQLQueries.getTotalUsersCount());
		rs.next();
		int totalUsersCountDB = Integer.parseInt(rs.getString("TotalUsers"));
		sa.assertEquals(totalSCMUsersUI, totalUsersCountDB,
				"The Count for Total Users from UI is not match with DB");
		// verify the Active part for Service and  SCM users
		int totalActiveServiceAccountUI = Integer.parseInt(getText(allCountsActive.get(0)).replace(",", ""));
		// fetching value from DataBase with service account Number
		rs = DataBaseUtils.getResultSet(SQLQueries.getServiceAccountNumberCountActive());
		rs.next();
		int totalServiceAccountNumberCountActiveDB = Integer.parseInt(rs.getString("ActiveServiceAccountNumberCount"));
		sa.assertEquals(totalActiveServiceAccountUI, totalServiceAccountNumberCountActiveDB,
				"The Count for Total Service Accounts Active from the UI is not match with DB");
		/*int totalActiveCustomersUI = Integer.parseInt(pageUtil.getLocatorText(allCountsActive.get(1)).replace(",", ""));
		rs = DataBaseUtil.getResultSet(SqlQuery.getCustomerCountActive());
		rs.next();
		int totalCustomerCountActiveDB = Integer.parseInt(rs.getString("TotalCustomerNumberActive").replace(",", ""));
		sa.assertEquals(totalActiveCustomersUI, totalCustomerCountActiveDB,
				"The Count for Customer Active is not match with DB");*/
		int totalActiveSCMUserUI = Integer.parseInt(getText(allCountsActive.get(1)).replace(",", ""));
		rs = DataBaseUtils.getResultSet(SQLQueries.getTotalActiveUsersCount());
		rs.next();
		int totalActiveUsersCountDB = Integer.parseInt(rs.getString("TotalActiveUsers"));
		sa.assertEquals(totalActiveSCMUserUI, totalActiveUsersCountDB,
				"The Count for Total Active Users from UI is not match with DB");
		// fetching all the value from the InActive ServiceAccount, SCM user
		int totalOthersServiceAccountUI = Integer.parseInt(getText(allCountsInActive.get(0)));
		rs = DataBaseUtils.getResultSet(SQLQueries.getServiceAccountNumberCountOthers());
		rs.next();
		int totalServiceAccountNumberCountOthersDB = Integer.parseInt(rs.getString("OtherServiceAccountNumberCount"));
		sa.assertEquals(totalOthersServiceAccountUI, totalServiceAccountNumberCountOthersDB,
				"The Count for Total Service Accounts Others from the UI is not match with DB");
		/*int totalInActiveCustomersUI = Integer
				.parseInt(pageUtil.getLocatorText(allCountsInActive.get(1)).replace(",", ""));
		sa.assertEquals((totalCustomersUI - totalActiveCustomersUI), (totalInActiveCustomersUI));*/
		int totalOtherSCMUserUI = Integer.parseInt(getText(allCountsInActive.get(1)).replace(",", ""));
//		rs = DataBaseUtil.getResultSet(SqlQuery.getTotalOtherUsersCount());
//		rs.next();
//		int totalOtherSCMUserDB = Integer.parseInt(rs.getString("TotalOtherUsers"));
		sa.assertEquals(totalOtherSCMUserUI, totalUsersCountDB-totalActiveUsersCountDB,
				"The Count for Total Other Users from the UI is not match with DB");

		//User Clicks on Individual Tab to check it.
		List<String> serviceAccountStatusList;
		List<String> usersStatusList;
		//Active Service Account tab.
		getLblCountTotalActive().get(0).click();
		waitForPageToLoad();
		serviceAccountStatusList = getAllElementsTextInList(getTblServiceAcctCustStatuscln());
		boolean activeServiceAccount = true;
		for(String e: serviceAccountStatusList) {
			if(!e.contains("Active"))
				activeServiceAccount =false;
		}
		sa.assertTrue(activeServiceAccount,"Active Service Account Tab contains the Inactive Service Account record.");
		//Inactive Service Account tab.
		
		getLblCountTotalInActive().get(0).click();
		waitForPageToLoad();
		serviceAccountStatusList = getAllElementsTextInList(getTblServiceAcctCustStatuscln());
		boolean InactiveServiceAccount = true;
		for(String e: serviceAccountStatusList) {
			if(!e.contains("Inactive"))
				InactiveServiceAccount =false;
		}
		sa.assertTrue(activeServiceAccount,"Others Service Account Tab contains the Active Service Account record.");

		//Active Users tab.
		
		getLblCountTotalActive().get(1).click();
		waitForPageToLoad();
		usersStatusList = getAllElementsTextInList(getTblSCMUsersStatusCln());
		boolean activeUsers = true;
		for(String e: usersStatusList) {
			if(!e.contains("Active"))
				activeUsers =false;
		}
		sa.assertTrue(activeUsers,"Active Users Tab contains the Users record of Others.");
		//Inactive Users tab.
		
		getLblCountTotalInActive().get(1).click();
		waitForPageToLoad();
		usersStatusList = getAllElementsTextInList(getTblSCMUsersStatusCln());
		boolean InactiveUsers = false;
		for(String e: usersStatusList) {
			if(!e.contains("Active"))
				InactiveUsers =true;
			else
			{
				InactiveUsers =false;
				break;
			}
		}
		sa.assertTrue(InactiveUsers,"Others Users Tab doesn't contains the Active Users record.");


		// Verify pagination fields
		sa.assertTrue(isDdlPageSizeDisplayed());
		sa.assertTrue(isBtnPageNextDisplayed());
		sa.assertTrue(isBtnPagePreviousDisplayed());
		sa.assertAll();
		ExtentLogger.logInfo("Test Case execution for -  VerifyCSRTabFunctionality - is Completed.");

	}
	
	public void verifyCSRTabSortingFeature() throws Exception {
		ExtentLogger.logInfo("Test Case execution for - VerifyCSRTabSortingFeature - is Initiated");
		ExtentLogger.logInfo("C72462-Verify the UI for CSR workbench");
		SoftAssert sa = new SoftAssert();
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		// Verify tabs
		sa.assertTrue(isLnkServiceAccountActiveTabDisplayed());
		sa.assertTrue(isLnkServiceAccountOthersTabDisplayed());
		sa.assertTrue(isLnkCustomerTabDisplayed());
		sa.assertTrue(isLnkSCMUserActiveTabDisplayed());
		sa.assertTrue(isLnkSCMUserOthersTabDisplayed());

		//Service Account Tab
		
		List<WebElement> serviceAccountTblSortableGridHeader = getTxtServiceAccountSortableGridHeaders();

		for (WebElement ele : serviceAccountTblSortableGridHeader) {
			sa.assertTrue(expectedElementsTextList(adminCustomerTextProp.getPropValue("txtServiceAccountSortableGridHeadersAcp")).contains(ele.getText().trim())
					,"The Expected Sortable Header Column is missing from the Service Account Grid ");
		}

		for (int i = 0; i < serviceAccountTblSortableGridHeader.size(); i++) {
			if (i != 0) {
				click(serviceAccountTblSortableGridHeader.get(i));
			}
			waitForPageToLoad();
			String qq1 = getAttributeValue(serviceAccountTblSortableGridHeader.get(i), "class");
			sa.assertTrue(qq1.contains("desc"), "Descending Sort is not working for Service Account Grid");
			click(serviceAccountTblSortableGridHeader.get(i));
			waitForPageToLoad();
			String qq = getAttributeValue(serviceAccountTblSortableGridHeader.get(i), "class");
			sa.assertTrue(qq.contains("asc"), "Ascending Sort is not working for Service Account Grid");			
		}
		


		//Customer Account Tab.
		clickLnkCustomerTab();
		waitForPageToLoad();

		List<WebElement> customerTblSortableGridHeader = getTxtCustomerTabSortableHeaders();

		for (WebElement ele : customerTblSortableGridHeader) {
			sa.assertTrue(expectedElementsTextList(adminCustomerTextProp.getPropValue("txtCustomersSortableGridHeadersAcp")).contains(ele.getText().trim())
					,"The Expected Sortable Header Column is missing from the Customers Grid ");
		}

		for (int i = 0; i < customerTblSortableGridHeader.size(); i++) {
			if (i != 0) {
				click(customerTblSortableGridHeader.get(i));
			}
			waitForPageToLoad();
			String qq1 = getAttributeValue(customerTblSortableGridHeader.get(i), "class");
			sa.assertTrue(qq1.contains("desc"), "Descending Sort is not working for Customers Grid");
			click(customerTblSortableGridHeader.get(i));
			waitForPageToLoad();
			String qq = getAttributeValue(customerTblSortableGridHeader.get(i), "class");
			sa.assertTrue(qq.contains("asc"), "Ascending Sort is not working for Customers Grid");			
		}


		//Users Tab.
		clickLnkSCMUserTab();
		waitForPageToLoad();
		
		List<WebElement> usersTblSortableGridHeader = getTblSCMUserSortableGridHeaders();

		for (WebElement ele : usersTblSortableGridHeader) {
			sa.assertTrue(expectedElementsTextList(adminCustomerTextProp.getPropValue("txtUsersSortableGridHeadersAcp")).contains(ele.getText().trim())
					,"The Expected Sortable Header Column is missing from the Users Grid ");
		}

		for (int i = 0; i < usersTblSortableGridHeader.size(); i++) {
			if (i != 0) {
				click(usersTblSortableGridHeader.get(i));
			}
			waitForPageToLoad();
			String qq1 = getAttributeValue(usersTblSortableGridHeader.get(i), "class");
			sa.assertTrue(qq1.contains("desc"), "Descending Sort is not working for Users Grid");
			click(usersTblSortableGridHeader.get(i));
			waitForPageToLoad();
			String qq = getAttributeValue(usersTblSortableGridHeader.get(i), "class");
			sa.assertTrue(qq.contains("asc"), "Ascending Sort is not working for Users Grid");			
		}


		sa.assertAll();
		ExtentLogger.logInfo("Test Case execution for -  VerifyCSRTabSortingFeature - is Completed.");

	}
	
	public ArrayList<String> expectedElementsTextList(String textList) {

		ArrayList<String> expectedText = new ArrayList<String>();
		String[] headerUI = textList.split(";");
		for (String header : headerUI) {
			System.out.println(header);
			expectedText.add(header);			
		}
		return expectedText;
	}
	
	public void verifyCSRTabPaginationFeature() throws Exception {
		ExtentLogger.logInfo("Test Case execution for - VerifyCSRTabPaginationFeature - is Initiated");
		ExtentLogger.logInfo("C72464,C72466-Verify the Pagination for CSR workbench");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		SoftAssert sa= new SoftAssert();
		// Verify tabs		
		sa.assertTrue(isLnkServiceAccountActiveTabDisplayed());
		sa.assertTrue(isLnkServiceAccountOthersTabDisplayed());
		sa.assertTrue(isLnkCustomerTabDisplayed());
		sa.assertTrue(isLnkSCMUserActiveTabDisplayed());
		sa.assertTrue(isLnkSCMUserOthersTabDisplayed());

		// Verifying the various page sizes options available
		scrollToBottom();
		List<String> pageSizes = getAllOptionsInListBox(getDdlPageSize());
		List<String> sizeList = Arrays.asList("10", "20", "30", "40", "50", "100", "200");
		sa.assertEquals(sizeList, pageSizes,"Page Size dropdown is not Correct in Service Account Tab");

		String paginationLabel,totalRecordCount,pageRecordCount;
		//Service Account Tab
		// Verify Record counts as per pagination count.
		paginationLabel = getLblRecordPaginationLabel();
		totalRecordCount = paginationLabel.split("of")[1].trim();
		pageRecordCount = paginationLabel.split("of")[0].trim().split("-")[1].trim();
		List<WebElement> noOfRowsService = getTblServiceAcctCustNamecln();
		sa.assertEquals(noOfRowsService.size(), Integer.parseInt(pageRecordCount),
				"Record count is not as per pagination on Service Account tab.");
		scrollToElement(getDdlPageSize());
		List<String> pageSizesService = getAllOptionsInListBox(getDdlPageSize());
		sa.assertEquals(sizeList, pageSizesService);
		scrollToElement(getDdlPageSize());
		if (Integer.parseInt(totalRecordCount) >= 10) {
			selectByVisibleText(getDdlPageSize(), "10");
			waitForPageToLoad();
			List<WebElement> noOfRowsServ = getTblServiceAcctCustNamecln();
			sa.assertEquals(noOfRowsServ.size(), 10, "Page size is not set as 10 By default in Service Account Tab");
		}
		scrollToElement(getDdlPageSize());
		if (Integer.parseInt(totalRecordCount) >= 100) {
			selectByVisibleText(getDdlPageSize(), "100");
			waitForPageToLoad();
			List<WebElement> noOfRows1Service = getTblServiceAcctCustNamecln();
			sa.assertEquals(noOfRows1Service.size(), 100, "Page size for 100 is not working in Service Account Tab");
			if (isBtnPageNextDisplayed()) {
				clickWithJSExecutor(getBtnPageNext());
				waitForPageToLoad();
			}
			sa.assertTrue(isBtnPagePreviousDisplayed(),	"Previous btn is not displayed ");
			if (isBtnPagePreviousDisplayed()) {
				clickWithJSExecutor(getBtnPagePrevious());
				waitForPageToLoad();
			}
		}

		//Customer Account Tab.
		clickLnkCustomerTab();
		waitForPageToLoad();
		scrollToBottom();
		List<String> pageSizesCustomers = getAllOptionsInListBox(getDdlPageSize());
		List<String> sizeListCustomers = Arrays.asList("10", "20", "30", "40", "50", "100", "200");
		sa.assertEquals(sizeListCustomers, pageSizesCustomers,"Page Size dropdown is not Correct in Customers Tab");
		paginationLabel = getLblRecordPaginationLabel();
		totalRecordCount = paginationLabel.split("of")[1].trim();
		pageRecordCount = paginationLabel.split("of")[0].trim().split("-")[1].trim();
		List<WebElement> noOfRowsCustomer = getTblCustomerCustNum();
		sa.assertEquals(noOfRowsCustomer.size(), Integer.parseInt(pageRecordCount),
				"Record count is not as per pagination on Customers tab.");
		scrollToElement(getDdlPageSize());
		if (Integer.parseInt(totalRecordCount) >= 10) {
			selectByVisibleText(getDdlPageSize(), "10");
			waitForPageToLoad();
			List<WebElement> noOfRowsCust = getTblCustomerCustNum();
			sa.assertEquals(noOfRowsCust.size(), 10, "Page size is not set as 10 By default in Customers Tab");
		}
		scrollToElement(getDdlPageSize());
		if (Integer.parseInt(totalRecordCount) >= 50) {
			selectByVisibleText(getDdlPageSize(), "50");
			waitForPageToLoad();
			List<WebElement> noOfRows1Customer = getTblCustomerCustNum();
			sa.assertEquals(noOfRows1Customer.size(), 50, "Page size for 50 is not working in Customers Tab");
			if (isBtnPageNextDisplayed()) {
				clickWithJSExecutor(getBtnPageNext());
				waitForPageToLoad();
			}
			sa.assertTrue(isBtnPagePreviousDisplayed(),
					"Previous btn is not displayed ");
			if (isBtnPagePreviousDisplayed()) {
				clickWithJSExecutor(getBtnPagePrevious());
				waitForPageToLoad();
			}
		}

		//Users Tab.
		clickLnkSCMUserTab();
		waitForPageToLoad();
		scrollToBottom();
		List<String> pageSizesUsers = getAllOptionsInListBox(getDdlPageSize());
		List<String> sizeListUsers = Arrays.asList("10", "20", "30", "40", "50", "100", "200");
		sa.assertEquals(sizeListUsers, pageSizesUsers,"Page Size dropdown is not Correct in Users Tab");
		paginationLabel = getLblRecordPaginationLabel();
		totalRecordCount = paginationLabel.split("of")[1].trim();
		pageRecordCount = paginationLabel.split("of")[0].trim().split("-")[1].trim();
		List<WebElement> noOfRowsUsers = getTblSCMUsersUser();
		waitForPageToLoad();
		sa.assertEquals(noOfRowsUsers.size(), Integer.parseInt(pageRecordCount),
				"Record count is not as per pagination on Users tab.");
		scrollToElement(getDdlPageSize());
		if (Integer.parseInt(totalRecordCount) >= 10) {
			selectByVisibleText(getDdlPageSize(), "10");
			waitForPageToLoad();
			List<WebElement> noOfRowsUser = getTblSCMUsersUser();
			sa.assertEquals(noOfRowsUser.size(), 10, "Page size is not set as 10 By default in Users Tab");
		}
		scrollToElement(getDdlPageSize());
		if (Integer.parseInt(totalRecordCount) >= 40) {
			selectByVisibleText(getDdlPageSize(), "40");
			waitForPageToLoad();
			List<WebElement> noOfRows1User = getTblSCMUsersUser();
			sa.assertEquals(noOfRows1User.size(), 40, "Page size for 40 is not working in Users Tab");
			if (isBtnPageNextDisplayed()) {
				clickWithJSExecutor(getBtnPageNext());
				waitForPageToLoad();
			}
			sa.assertTrue(isBtnPagePreviousDisplayed(),	"Previous btn is not displayed ");
			if (isBtnPagePreviousDisplayed()) {
				clickWithJSExecutor(getBtnPagePrevious());
				waitForPageToLoad();
			}
		}

		sa.assertAll();
		ExtentLogger.logInfo("Test Case execution for -  VerifyCSRTabPaginationFeature - is Completed.");

	}
	
	static String customerReportFileName;
	public void verifyExportCustomerFile() throws InterruptedException, IOException {
		ExtentLogger.logInfo("Test Case execution for - verifyExportCustomerFile - is Initiated");
		ExtentLogger.logInfo("C91423-Verify the Export Report excel for Customer from CSR Workbench Customer Tab");

		SoftAssert sa = new SoftAssert();
		deleteFiles(downloadUsageExcelPath);
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		clickLnkCustomerTab();
		waitForPageToLoad();
		
		clickBtnExport();
		explicitWaitForLnkExcelReport();
		clickLnkExcelReport();
		
		pageRefresh();
		
		clickLnkCustomerTab();
		waitForPageToLoad();
		// Fetching Customer Number
		List<String> uiDataCustNum = new ArrayList<String>();
		//		List<WebElement> eleCustNum = pageUtil.collectAllSimillarElements(driver, adminCsrPage
		//					.getLnkCustomerNameTable(getColumnNumberOfHeader(adminCsrPage.getTableCustomer(), "CUSTOMER")));
		List<WebElement> eleCustNum = getTblCustomerCustNum();
		for (WebElement webElement : eleCustNum) {
			uiDataCustNum.add(webElement.getText());
		}
		// Fetching Customer Name
		List<String> uiDataCustName = new ArrayList<String>();
		//		List<WebElement> eleCustName = pageUtil.collectAllSimillarElements(driver, adminCsrPage.getLnkCustomerNameTable(
		//				getColumnNumberOfHeader(adminCsrPage.getTableCustomer(), "CUSTOMER NUMBER")));
		List<WebElement> eleCustName = getTblCustomerCustomer();
		for (WebElement webElement : eleCustName) {
			uiDataCustName.add(webElement.getText());
		}
		// Fetching Mobile Phone
		List<String> uiDataCustContactNum = new ArrayList<String>();
		//	List<WebElement> eleCustContactNum = pageUtil.collectAllSimillarElements(driver, adminCsrPage
		//		.getLnkCustomerNameTable(getColumnNumberOfHeader(adminCsrPage.getTableCustomer(), "MOBILE PHONE")));
		List<WebElement> eleCustContactNum = getTblCustomerMobilePhone();
		for (WebElement webElement : eleCustContactNum) {
			uiDataCustContactNum.add(webElement.getText());
		}
		// Fetching Primary Email
		List<String> uiDataCustEmail = new ArrayList<String>();
		//		List<WebElement> eleCustEmail = pageUtil.collectAllSimillarElements(driver, adminCsrPage
		//				.getLnkCustomerNameTable(getColumnNumberOfHeader(adminCsrPage.getTableCustomer(), "PRIMARY EMAIL")));
		List<WebElement> eleCustEmail = getTblCustomerPrimaryEmail();
		for (WebElement webElement : eleCustEmail) {
			uiDataCustEmail.add(webElement.getText());
		}
		// fetching linked Accounts
		List<String> uiDataLinkAccount = new ArrayList<String>();
		//		List<WebElement> eleCustLinkAccount = pageUtil.collectAllSimillarElements(driver, adminCsrPage
		//				.getLnkCustomerNameTable(getColumnNumberOfHeader(adminCsrPage.getTableCustomer(), "LINKED ACCOUNTS")));
		List<WebElement> eleCustLinkAccount = getTblCustomerLinkAccts();
		for (WebElement webElement : eleCustLinkAccount) {
			uiDataLinkAccount.add(webElement.getText());
		}
		DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0);
		Date todate2 = cal.getTime();
		String todate = dateFormat.format(todate2);
		customerReportFileName = downloadUsageExcelPath + "CustomersReport_" + todate + ".xlsx";
		ExcelUtils.openExcelFile(customerReportFileName);
		Sheet sheet = ExcelUtils.getSheetName(0);
		for (int i = 3; i <= ExcelUtils.getRowCount(sheet); i++) {
			ExtentLogger.logInfo("Verifying the Customer Name of the excel and UI");
			String tt = uiDataCustName.get(i - 3).trim();
			System.out.println("uiDataCustName is" + tt);
			String rr = ExcelUtils.getValueByColName(sheet,i, "Customer Name");
			System.out.println("Customer Name is" + rr);
			sa.assertTrue(
					uiDataCustName.get(i - 3).trim().contains(ExcelUtils.getValueByColName(sheet,i, "Customer Name")),
					"Customer Name is not matched with excel data");
			ExtentLogger.logInfo("Verifying the Customer Number of the excel and UI");
			String aa = uiDataCustNum.get(i - 3).trim();
			System.out.println("uiDataCustNum " + aa);
			String we = ExcelUtils.getValueByColName(sheet,i, "Customer Number");
			System.out.println("Customer Number is" + we);
			sa.assertTrue(uiDataCustNum.get(i - 3).trim().contains(
					ExcelUtils.getValueByColName(sheet,i, "Customer Number")),
					"Customer Number is not matched with excel data");
			ExtentLogger.logInfo("Verifying the Mobile Number of the excel and UI");
			String cc = uiDataCustContactNum.get(i - 3).trim();
			System.out.println("uiDataCustContactNum " + cc);
			String dd = ExcelUtils.getValueByColName(sheet,i, "Primary Phone");
			System.out.println("Contact Number" + dd);
			sa.assertTrue(
					uiDataCustContactNum.get(i - 3).trim().contains(ExcelUtils.getValueByColName(sheet,i, "Primary Phone")),
					"Mobile Number is not matched with excel data");
			ExtentLogger.logInfo("Verifying the Email Address of the excel and UI");
			String ee = uiDataCustEmail.get(i - 3).trim();
			System.out.println("uiDataCustEmail " + ee);
			String ff = ExcelUtils.getValueByColName(sheet,i, "Primary Email");
			System.out.println("Customer Email" + ff);
			sa.assertTrue(
					uiDataCustEmail.get(i - 3).trim().contains(ExcelUtils.getValueByColName(sheet,i, "Primary Email")),
					"Email address is not matched with excel data");
			ExtentLogger.logInfo("Verifying the Linked Accounts of the excel and UI");
			String gg = uiDataLinkAccount.get(i - 3).trim();
			System.out.println("uiDataLinkAccount " + gg);
			String hh = ExcelUtils.getValueByColName(sheet,i, "Linked Accounts");
			System.out.println("Linked Account" + hh);
			sa.assertTrue(uiDataLinkAccount.get(i - 3).contains(ExcelUtils.getValueByColName(sheet,i, "Linked Accounts")),
					"Linked Accounts is not matched with excel data");

		}
		deleteFiles(downloadUsageExcelPath);
		sa.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyExportCustomerFile - is finished");

	}
	
	public static void deleteFiles(String filePath) {
		try {
			File directory = new File(filePath);
			// Get all files in directory
			File[] files = directory.listFiles();
			for (File file : files) {
				file.delete();
				// Delete each file
				if (file.exists()) {
					// Failed to delete file
					System.out.println("Failed to delete " + file);
				}
			}
			Thread.sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static String ServiceAccountsReportFileName;
	public void verifyExportCustomerFileServiceTab() throws Exception {
		// verifyHoverLinkUserServiceAccountTab();
		ExtentLogger.logInfo("Test Case execution for - verifyExportCustomerFile - is Initiated");
		ExtentLogger.logInfo("C72484-Verify Export button for service account ");
		deleteFiles(downloadUsageExcelPath);
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		explicitWaitForBtnExport();
		clickBtnExport();
		explicitWaitForLnkExcelReport();;
		clickLnkExcelReport();
		
		pageRefresh();
		
		DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0);
		Date todate2 = cal.getTime();
		String todate = dateFormat.format(date);
		ServiceAccountsReportFileName = downloadUsageExcelPath + "ServiceAccountsReport_" + todate + ".xlsx";
		System.out.println(ServiceAccountsReportFileName);
		// ExcelUtil.openExcelFile(ServiceAccountsReportFileName);
		ExcelUtils.openExcelFile(ServiceAccountsReportFileName);
		Sheet sheet = ExcelUtils.getSheetName(0);
		// fetching the values from the Table customer Name
		List<String> uiDataCustomerName = new ArrayList<String>();
	
		List<WebElement> ele = getTblServiceAcctCustNamecln();
		for (WebElement webElement : ele) {
			String custName = webElement.getText().split("\\R")[0];
			uiDataCustomerName.add(custName);
		}
		// fetching the value if emailID
		List<String> uiDataCustomerEmail = new ArrayList<String>();
		List<WebElement> eleEmail = getTblServiceAcctEmailAddress();
		for (WebElement webElement : eleEmail) {
			String emailAddress = webElement.getText().split("\\R")[1];
			uiDataCustomerEmail.add(emailAddress);
		}
		// fetching the value service account Number
		List<String> uiDataCustomerServiceAccountNum = new ArrayList<String>();
		List<WebElement> eleServiceAccount = getTblServiceAcccount();
		for (WebElement webElement : eleServiceAccount) {
			System.out.println(webElement.getText());
			uiDataCustomerServiceAccountNum.add(webElement.getText());
		}
		// fetching the customer Status
		List<String> uiDataCustomerStatus = new ArrayList<String>();
		List<WebElement> eleStatus = getTblServiceAcctCustStatuscln();
		for (WebElement webElement : eleStatus) {
			System.out.println(webElement.getText());
			uiDataCustomerStatus.add(webElement.getText());
		}
		// fetching the customer Account Type
		List<String> uiDataCustomerAccountType = new ArrayList<String>();
		List<WebElement> eleAccountType = getTblServiceAcctAccTypecln();
		for (WebElement webElement : eleAccountType) {
			System.out.println(webElement.getText());
			uiDataCustomerAccountType.add(webElement.getText());
		}
		ExtentLogger.logInfo("C72481-Verify the Status values on the Service Account tab from CSR");
		clickLnkServiceAccountOthersTab();
		
		explicitWaitForTxtInactiveValueServiceAccountOtherstab();
		
		for (int i = 3; i <= ExcelUtils.getRowCount(sheet); i++) {
			ExtentLogger.logInfo("Verifying the Customer Name of the excel and UI");
			Assert.assertTrue(
					uiDataCustomerName.get(i - 3).trim().contains(ExcelUtils.getValueByColName(sheet,i, "Customer Name")),
					"Customer name is not matched with excel data");
			ExtentLogger.logInfo("Verifying the emilID of the excel and UI");
			Assert.assertTrue(
					uiDataCustomerEmail.get(i - 3).trim().contains(ExcelUtils.getValueByColName(sheet,i, "Email Address")),
					"Customer name is not matched with excel data");
			ExtentLogger.logInfo("C72482-Verify the Premise Details of the Service Account Number");
			ExtentLogger.logInfo("Verifying the Utility Account Number of the excel and UI");
			verifyStringValue(uiDataCustomerServiceAccountNum.get(i - 3).trim(),
					ExcelUtils.getValueByColName(sheet,i, "Service Account"));
			ExtentLogger.logInfo("Verifying the Customer Status of the excel and UI");
			verifyStringValue(uiDataCustomerStatus.get(i - 3).trim(),
					ExcelUtils.getValueByColName(sheet,i, "Customer Status"));
			ExtentLogger.logInfo("Verifying the Account Type of the excel and UI");
			verifyStringValue(uiDataCustomerAccountType.get(i - 3).trim(),
					ExcelUtils.getValueByColName(sheet,i, "Account Type"));
			ExtentLogger.logInfo("C80059-Verify the Columns in the downloaded Excel");
			if (i == 3) {
				ArrayList serviceAccountExcelHeaders = null;
				serviceAccountExcelHeaders = new ArrayList();
				for (int j = 0; j < 8; j++) {
					System.out.println(ExcelUtils.getCellValueAnalytics(sheet,2, j));
					serviceAccountExcelHeaders.add(ExcelUtils.getCellValueAnalytics(sheet,2, j));
				}
				Assert.assertEquals(serviceAccountExcelHeaders, expectedServiceAccountExcelHeaders(),
						"Expected Service Account Columns was not present on the Excel");
			}

		}
		deleteFiles(downloadUsageExcelPath);
		ExtentLogger.logInfo("Test Case execution for - verifyExportSeriveAccountTab - is finished");
	}
	
	public void verifyStringValue(String expected, String actual) {
		if (actual.equals(expected.trim())) {
			ExtentLogger.logInfo("the expected value: " + expected + " matches the actual value: " + actual);
			
		} else {
			ExtentLogger.logFail("The expected value: " + expected
					+ " does not matches with the actual value: " + actual);
		}
	}
	
	public ArrayList expectedServiceAccountExcelHeaders() {
		ArrayList expectedServiceAccountExcelHeaders = new ArrayList();
		expectedServiceAccountExcelHeaders.add("Customer Name");
		expectedServiceAccountExcelHeaders.add("Customer Number");
		expectedServiceAccountExcelHeaders.add("Email Address");
		expectedServiceAccountExcelHeaders.add("Account Type");
		expectedServiceAccountExcelHeaders.add("Customer Status");
		expectedServiceAccountExcelHeaders.add("Service Account");
		expectedServiceAccountExcelHeaders.add("Contact Number");
		expectedServiceAccountExcelHeaders.add("Linked Users");
		return expectedServiceAccountExcelHeaders;
	}
	
	static String userReportFileName;
	public void verifyExportSCMUserFile() throws InterruptedException, IOException {
		ExtentLogger.logInfo("Test Case execution for - verifyExportCustomerFile - is Initiated");
		ExtentLogger.logInfo("C33935-Verify the Export Report excel for Customer from CSR Workbench SCM Tab");

		SoftAssert sa = new SoftAssert();
		deleteFiles(downloadUsageExcelPath);
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		clickLnkSCMUserTab();
		waitForPageToLoad();
		
		explicitWaitForBtnExport();
		clickBtnExport();
		explicitWaitForLnkExcelReport();
		clickLnkExcelReport();
//		pageUtil.pause(2000);
        pageRefresh();
//		clickSCMUserTab();
		
		DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0);
		Date todate2 = cal.getTime();
		String todate = dateFormat.format(todate2);
		userReportFileName = downloadUsageExcelPath + "UsersReport_" + todate + ".xlsx";
		ExtentLogger.logInfo("The Users Report File Name is "+userReportFileName);
		System.out.println("The Users Report File Name is "+userReportFileName);
		ExcelUtils.openExcelFile(userReportFileName);
		Sheet sheet = ExcelUtils.getSheetName(0);

		// Fetching Status
		List<String> uiDataUserStatus = new ArrayList<String>();
		//        List<WebElement> eleUserStatus = pageUtil.collectAllSimillarElements(driver,
		//                adminCsrPage.getLnkSCMUserTable(getColumnNumberOfHeader(adminCsrPage.getTableSCMUser(), "STATUS")));
		List<WebElement> eleUserStatus = getTblSCMUsersStatusCln();
		for (WebElement webElement : eleUserStatus) {
			System.out.println(webElement.getText());
			if (!webElement.getText().isEmpty()) {
				uiDataUserStatus.add(webElement.getText());
			}
		}
		// Fetching User
		List<String> uiDataName = new ArrayList<String>();
		//        List<WebElement> eleName = pageUtil.collectAllSimillarElements(driver,
		//                adminCsrPage.getLnkSCMUserTable(getColumnNumberOfHeader(adminCsrPage.getTableSCMUser(), "USER")));
		List<WebElement> eleName = getTblSCMUsersUser();
		for (WebElement webElement : eleName) {
			System.out.println(webElement.getText());
			if (!webElement.getText().isEmpty()) {
				uiDataName.add(webElement.getText());
			}
		}
		// Fetching User name
		List<String> uiDataUserName = new ArrayList<String>();
		//        List<WebElement> eleUserName = pageUtil.collectAllSimillarElements(driver,
		//                adminCsrPage.getLnkSCMUserTable(getColumnNumberOfHeader(adminCsrPage.getTableSCMUser(), "USERNAME")));
		List<WebElement> eleUserName = getTblSCMUsersUsername();
		for (WebElement webElement : eleUserName) {
			System.out.println(webElement.getText());
			if (!webElement.getText().isEmpty()) {
				uiDataUserName.add(webElement.getText());
			}
		}
		// Fetching Contact Number
		List<String> uiDataContactNo = new ArrayList<String>();
		//        List<WebElement> eleUserEmail = pageUtil.collectAllSimillarElements(driver,
		//                adminCsrPage.getLnkSCMUserTable(getColumnNumberOfHeader(adminCsrPage.getTableSCMUser(), "CONTACT")));
		List<WebElement> eleContactNo = getTblSCMUsersContact();
		for (WebElement webElement : eleContactNo) {
			String contactNumber = webElement.getText().split("\\R")[0].trim();
			System.out.println(contactNumber);
			if (!contactNumber.isEmpty()) {
				uiDataContactNo.add(contactNumber);
			}
		}

		// Fetching Email
		List<String> uiDataUserEmail = new ArrayList<String>();
		//        List<WebElement> eleUserEmail = pageUtil.collectAllSimillarElements(driver,
		//                adminCsrPage.getLnkSCMUserTable(getColumnNumberOfHeader(adminCsrPage.getTableSCMUser(), "CONTACT")));
		List<WebElement> eleUserEmail = getTblSCMUsersContact();
		String email = null;
		for (WebElement webElement : eleUserEmail) {
			
			if(webElement.getText().split("\\R").length>1) {
			 email = webElement.getText().split("\\R")[1].trim();
			System.out.println(email);
					if (!email.isEmpty()) {
						uiDataUserEmail.add(email);
					}
			}
			else {
				email = webElement.getText().trim();
				System.out.println(email);
					if (!email.isEmpty()) {
						uiDataUserEmail.add(email);
					}
			}
			}

		// Fetching Link Accounts
		List<String> uiDataLinkUser = new ArrayList<String>();
		//        List<WebElement> eleUserLinkUser = pageUtil.collectAllSimillarElements(driver, adminCsrPage
		//                .getLnkSCMUserTable(getColumnNumberOfHeader(adminCsrPage.getTableSCMUser(), "LINKED ACCOUNTS")));
		List<WebElement> eleUserLinkUser = getTblSCMUsersLinkedAccounts();
		for (WebElement webElement : eleUserLinkUser) {
			System.out.println(webElement.getText());
			if (!webElement.getText().isEmpty()) {
				uiDataLinkUser.add(webElement.getText());
			}
		}
		for (int i = 3; i <= ExcelUtils.getRowCount(sheet); i++) {
			ExtentLogger.logInfo("Verifying the User of the excel and UI");
			sa.assertTrue(uiDataName.get(i - 3).trim().contains(ExcelUtils.getValueByColName(sheet,i, "User")),
					"User is not matched with excel data");
			ExtentLogger.logInfo("Verifying the EmailID of the excel and UI");
			sa.assertTrue(uiDataUserEmail.get(i - 3).trim().contains(ExcelUtils.getValueByColName(sheet,i, "EmailID")),
					"Email is not matched with excel data");
			ExtentLogger.logInfo("Verifying the Primary Phone of the excel and UI");
			sa.assertTrue(
					uiDataContactNo.get(i - 3).trim().contains(ExcelUtils.getValueByColName(sheet,i, "Primary Phone")),
					"Phone number is not matched with excel data");
			ExtentLogger.logInfo("Verifying the User Name of the excel and UI");
			sa.assertTrue(uiDataUserName.get(i - 3).trim().contains(ExcelUtils.getValueByColName(sheet,i, "User Name")),
					"User Name is not matched with excel data");
			ExtentLogger.logInfo("Verifying the Status of the excel and UI");
			sa.assertTrue(uiDataUserStatus.get(i - 3).trim().contains(ExcelUtils.getValueByColName(sheet,i, "User Status")),
					"Status is not matched with excel data");
			ExtentLogger.logInfo("Verifying the User Link Accounts of the excel and UI");
			sa.assertTrue(uiDataLinkUser.get(i - 3).toString().trim().contains(ExcelUtils.getValueByColName(sheet,i, "Linked Accounts")),
					"Linked Accounts is not matched with excel data");
		}
		sa.assertAll();
		deleteFiles(downloadUsageExcelPath);
		ExtentLogger.logInfo("Test Case execution for - verifyExportCustomerFile - is finished");
	}
	
	public void checkRemoveIpLock(String sIp) throws Exception {

		AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
		adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
		
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		clickLnkSideBarBlockedIp();
		
		waitForPageToLoad();
		
		//pageUtil.explicitWaitForWebElement(driver, adminBlockedIpPage.getLblBlockedIpValAbip());

		if (verifyElementVisible(getLblBlockedIpValAbip())) {
			
			    ExtentLogger.logInfo("The user IP " + sIp + " is blocked");
//				pageUtil.click(adminBlockedIpPage.getLblBlockedIpValAbip());
//				assertTrue(pageUtil.isAlertPresent());
//				pageUtil.verifyAlertMessage(Utils.getRbText().getString("lblTxtAlertUnblockIpAbip"));
//				pageUtil.acceptAlert();
//				pageUtil.explicitWaitForWebElement(driver, adminBlockedIpPage.getLblSuccessfulHeader());
//				pageUtil.verifyObjectLabel(adminBlockedIpPage.getLblSuccessfulHeader(),
//						Utils.getRbText().getString("lblTxtAlertUnblockAccountSuccessAbip"));
			    clickThreeDotsToggleDropdown();
			    explicitWaitForLblUnlockOption();
			    clickLblUnlockOption();
			    explicitWaitForBtnYesUnlockConfirmationPopup();
				verifyTextPresentInPage(adminCustomerTextProp.getPropValue("lblTxtAlertUnblockIpAbip"));
				
				clickBtnYesUnlockConfirmationPopup();
				Assert.assertEquals(getToastMsgWithoutPageLoaderCloseButton(),adminCustomerTextProp.getPropValue("lblTxtAlertUnblockAccountSuccessAbip"));
//				sAssert.assertAll();
			
		} else {
			ExtentLogger.logInfo("The user account " + sIp + " is not blocked");
		}

	}
	
	public void verifyTextPresentInPage(String expectedText) {
		ExtentLogger.logInfo("The text value to be searched  inside page: " + expectedText);
		String bodyText = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue(bodyText.contains(expectedText), "Text not found!");
		ExtentLogger.logInfo("The text: " + expectedText + " appears in the page.");
	}
	
	public String getToastMsgWithoutPageLoaderCloseButton(){
        String toastMsg = null;
        
        expWaitForEleVisibility(getLblToastMsg());
        toastMsg = getLblToastMsgLabel();
        expWaitForEleInvisibility(getLblToastMsg()); 
        return toastMsg;
    }
	
	public void expWaitForEleVisibility(WebElement locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
			wait.until(ExpectedConditions.visibilityOf(locator));
		} catch (NoSuchElementException e) {
			log.error("No Element Found after wait" + e);
		}catch (TimeoutException e) {
			log.error("TimeOutException after wait" + e);
		}
	}
	
	public void expWaitForEleInvisibility(WebElement locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
			wait.until(ExpectedConditions.invisibilityOf(locator));
		} catch (TimeoutException e) {
			e.printStackTrace();
//			Runner.test.log(Status.FAIL, "No Element Found after wait" + e);
		}
	}
	
	public void verifySendNotification() throws Exception {
		ExtentLogger.logInfo("Test Case execution for - verifySendNotification - is Initiated");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		SoftAssert sa = new SoftAssert();

		ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.getDefaultAccQuery(Configuration.toString("userName")));
		resultSet.next();
		String utilityAccNo = resultSet.getString("utilityaccountnumber");

		//        enterValueSearchFilter(Utils.getRbData().getString("defaultAccount"));
		enterValueSearchFilter(utilityAccNo);
		
		ExtentLogger.logInfo("C80064-Verify Send Notification when no account is selected");
		sa.assertTrue(isBtnSendNotificationDisplayed(),	"Send notification button is not appearing ");
		clickBtnSendNotification();
		//		pageUtil.pause(1000);
		sa.assertEquals(getToastMsgWithoutCloseButton(), adminCustomerTextProp.getPropValue("txtNoAccountSelectedMessage"));
		ExtentLogger.logInfo("C72483-Verify Send Notification to Selected Service Account");
		check(getChkboxAllCheck());
		ExtentLogger.logInfo("C80065-Verify the UI of send notification");
		sa.assertTrue(isBtnSendNotificationDisplayed(), "Send notification button is not appearing ");
		clickBtnSendNotification();
		explicitWaitForDdlTypeOfMessage();
		selectByVisibleText(getDdlTypeOfMessage(), "Billing");
		selectByVisibleText(getDdlMessageMode(), "Email");
		
		String message = RandomUtil.generateRandomString(5, Mode.ALPHANUMERIC);
		clearTxtMessageReasonField();
		populateTxtMessageReason(message);
		clearTxtAreaMessageBodyField();
		populateTxtAreaMessageBody(message);
		scrollToElement(getBtnSendMessage());
		clickBtnSendMessage();
		//		if (pageUtil.isAlertPresent()) {
		//			pageUtil.verifyAlertMessage(Utils.getRbData().getString(("successfullyMessageMsgAnp")));
		//			pageUtil.acceptAlert();
		//		}
		sa.assertEquals(getToastMsgWithoutCloseButton(), adminCustomerTextProp.getPropValue("successfullyMessageMsgAnp"));
		// verification from SCP
		deleteCookies();
		
		DriverFactory.goToPage(Configuration.toString("scp.url"));
		LoginSteps loginSteps = new LoginSteps(driver);
		loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		//launchApplication();
		//        pageUtil.enterTextValue(loginPage.getTxtBoxUserName(),
		//                getUserIsLockStatusUserName(Utils.getRbData().getString("defaultAccount")));
		DashboardPage dashboardPage = new DashboardPage(driver);
		dashboardPage.waitForNotificationBadgeIcon();
		dashboardPage.clickIconNotificationBadge();
		
		waitForPageLoader();
		waitForImgLoadingInvisibility();
		
		click(driver.findElement(By.xpath("//span[@class='msgSubject'][text()='" + message + "']")));
		waitForImgLoadingInvisibility();
		
		NotificationPage notificationPage= new NotificationPage(driver);
		verifyStringValue(message, notificationPage.getLblMessageBodyLabel());
		// DB Verification
		Assert.assertTrue(getUserMessageDetails("Subject", message).contains(message),
				"The message from the UI is not matching with the DB for send notifications without attachments");
		//Verify Send Notification With Attachments.
		deleteCookies();
		
		DriverFactory.goToPage(Configuration.toString("csp.url"));
		
		AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		//        enterValueSearchFilter(Utils.getRbData().getString("defaultAccount"));
		enterValueSearchFilter(utilityAccNo);
		
		ExtentLogger.logInfo("C72483-Verify Send Notification to Selected Service Account");
		check(getChkboxAllCheck());
		ExtentLogger.logInfo("C80065-Verify the UI of send notification");
		sa.assertTrue(isBtnSendNotificationDisplayed(),
				"Send notification button is not appearing ");
		clickBtnSendNotification();
		explicitWaitForDdlTypeOfMessage();
		
		selectByVisibleText(getDdlTypeOfMessage(), "Billing");
		selectByVisibleText(getDdlMessageMode(), "Email");
		
		String message1 = RandomUtil.generateRandomString(5, Mode.ALPHANUMERIC);
		clearTxtMessageReasonField();
		populateTxtMessageReason(message1);
		
		clearTxtAreaMessageBodyField();
		populateTxtAreaMessageBody(message1);
		
		populateBtnChooseFileMail(filePth);
		scrollToElement(getBtnSendMessage());
		clickBtnSendMessage();
		sa.assertEquals(getToastMsgWithoutCloseButton(), adminCustomerTextProp.getPropValue("successfullyMessageMsgAnp"));
		
		// verification from SCP
		deleteCookies();
		
		DriverFactory.goToPage(Configuration.toString("scp.url"));
		loginSteps = new LoginSteps(driver);
		loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		
		dashboardPage = new DashboardPage(driver);
		dashboardPage.waitForNotificationBadgeIcon();
		dashboardPage.clickIconNotificationBadge();
		
		waitForPageLoader();
		waitForImgLoadingInvisibility();
		click(driver.findElement(By.xpath("//span[@class='msgSubject'][text()='" + message1 + "']")));
		waitForImgLoadingInvisibility();
		
		notificationPage= new NotificationPage(driver);
		verifyStringValue(message1, notificationPage.getLblMessageBodyLabel());
		
		String attachmentName= notificationPage.getLblNotificationGridFirstNotificationAttachmentLabel();
		sa.assertTrue(attachmentName.contains("Test")&&attachmentName.contains(".jpg"),
				"The Attachment name seen in SCP notification is not matching with the CSP attachment name.");
		// DB Verification
		Assert.assertTrue(getUserMessageDetails("Subject", message1).contains(message1),
				"The message from the UI is not matching with the DB for send notifications with attachments");
		
		sa.assertAll();
		ExtentLogger.logInfo("Test Case execution for -  verifySendNotification - is Completed.");
	}
	
	public String getToastMsgWithoutCloseButton() {
        String toastMsg = null;
        
        expWaitForEleVisibility(getLblToastMsg());
        toastMsg = getLblToastMsgLabel();
        expWaitForEleInvisibility(getLblToastMsg()); 
        return toastMsg;
    }
	
	public static String getUserMessageDetails(String fieldRequired, String reason) throws Exception {
		String iUserDetailsField = null;
		ResultSet rs;
		try {
			rs = DataBaseUtils.getResultSet(SQLQueries.getUserMessage(reason));
			rs.next();
			iUserDetailsField = rs.getString(fieldRequired);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("No value configured");
		}
		return iUserDetailsField;
	}
	
	public void verifyRegisteredUsertabsAndCount() throws Exception {
		ExtentLogger.logInfo("Test Case execution for -verifyRegisteredUserUIAndCount- is Initiated");
		ExtentLogger.logInfo("C72538-Verify the Sub Tabs for Registered User Tab");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		assertTrue(isLnkServiceAccountActiveTabDisplayed());
		assertTrue(isLnkServiceAccountOthersTabDisplayed());
		clickLiSCMUserTab();
		waitForPageToLoad();
		// verify the total count of accounts in UI and DB
		ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getRegisterUsersCount());
		rs.next();
		String totalUser_DB = rs.getString("TotalUser");
		String totalUser_UI = getTxtTotalCountUsersLabel().replace(",", "");
		Assert.assertEquals(totalUser_DB, totalUser_UI, "Total registered user are not matched with DB");
		// Verify the active accounts count
		ResultSet rs1 = DataBaseUtils.getResultSet(SQLQueries.getRegisterdActiveUserCount());
		rs1.next();
		String activeUser_DB = rs1.getString("ActiveUser");
		String activeUser_UI = getTxtCountActiveUserLabel().replace(",", "");
		Assert.assertEquals(activeUser_DB, activeUser_UI, "Active user count are not matched with DB");
		// Verify the other accounts count
		int otherUser_DB = Integer.parseInt(totalUser_DB) - Integer.parseInt(activeUser_DB);
		String otherUser_Exp = String.valueOf(otherUser_DB);
		String otherUser_Act = getTxtOtherCountUserLabel().replace(",", "");
		Assert.assertEquals(otherUser_Exp, otherUser_Act, "other user count are not matched with DB");
		List<WebElement> usersHeaders = getTxtUsersGridHeader();
		for (WebElement ele : usersHeaders) {
			Assert.assertEquals(ele.isDisplayed(), true, "Header is missing from users grid");
		}
		ExtentLogger.logInfo("Test Case execution for - verifyRegisteredUsertabsAndCount - is Completed.");
	}
	
	public void verifyRegisterUserGridUIFields() throws Exception {
		ExtentLogger.logInfo("Test Case execution for -verifyRegisterUserGridUIFields- is Initiated");
		ExtentLogger.logInfo("C72539- Verify the Fields under the Registered User tab for SCM user");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		assertTrue(isLnkServiceAccountActiveTabDisplayed());
		assertTrue(isLnkServiceAccountOthersTabDisplayed());

		clickTxtActiveUsers();
		waitForPageToLoad();
		List<WebElement> usersHeaders = getTxtUsersGridHeader();
		for (WebElement ele : usersHeaders) {
			Assert.assertEquals(ele.isDisplayed(), true, "Header is missing from users grid");
		}

		//FOR ENTERPRISE
		selectByVisibleText(getDdSearchItem(), "Username");
		populateTxtSearchCustomer("june17enterprise1");// AmehtaGuest //test@gmail.comrtert
		
		waitForPageLoaderInvisibility();
		clickBtnSearch();
		waitForPageLoaderInvisibility();

		ArrayList<String> explistActionEnterprise = new ArrayList<String>();
		explistActionEnterprise.add("Edit Record");
		explistActionEnterprise.add("Lock");
		explistActionEnterprise.add("Reset Password");//Create Portfolio
		explistActionEnterprise.add("Create Portfolio");
		explistActionEnterprise.add("Request Delete"); //Delete Record
		List<WebElement> iconActions = getLstIconsActionUser();
		ArrayList<String> listAction = new ArrayList<String>();
		for (int i = 0; i < 1; i++) {
			Assert.assertEquals(iconActions.get(i).isDisplayed(), true,
					"Action Icon is missing from users grid");
			
			scrollToElement(iconActions.get(i));
			click(iconActions.get(i));
			waitForPageLoaderInvisibility();
			waitForPageToLoad();
			List<WebElement> actionTexts = getLstActionsUser();
			for (WebElement act : actionTexts) {
				listAction.add(act.getText());
			}
		}
		Assert.assertEquals(listAction, explistActionEnterprise, "Action text is not matched for user");

		//FOR Residential/Commercial
		selectByVisibleText(getDdSearchItem(), "Username");
		populateTxtSearchCustomer("abc@gmail.Com");
		
		waitForPageLoaderInvisibility();
		clickBtnSearch();
		waitForPageLoaderInvisibility();

		ArrayList<String> explistAction = new ArrayList<String>();
		explistAction.add("Edit Record");
		explistAction.add("Lock");
		explistAction.add("Reset Password");
		explistAction.add("Request Delete"); //Delete Record
		List<WebElement> iconActionsRC = getLstIconsActionUser();
		ArrayList<String> listActionRC = new ArrayList<String>();
		for (int i = 0; i < 1; i++) {
			Assert.assertEquals(iconActionsRC.get(i).isDisplayed(), true,
					"Action Icon is missing from users grid");
			
			scrollToElement(iconActionsRC.get(i));
			click(iconActionsRC.get(i));
			waitForPageLoaderInvisibility();
			waitForPageToLoad();
			List<WebElement> actionTexts =getLstActionsUser();
			for (WebElement act : actionTexts) {
				listActionRC.add(act.getText());
			}
		}
		Assert.assertEquals(listActionRC, explistAction, "Action text is not matched for user");

		Assert.assertTrue(isBtnResetPasswordVisible(), "Reset Passwork link is not appearing");
		List<WebElement> activeList = getLstIconsActionUser();
		for (WebElement ele : activeList) {
			Assert.assertEquals(ele.isDisplayed(), true, "Active status is missing from users grid");
		}
		List<WebElement> userList = getLstLnkUser();
		for (WebElement ele : userList) {
			Assert.assertEquals(ele.isDisplayed(), true, "User is missing from users grid");
		}
		List<WebElement> usernameList = getLstTxtUsernameUser();
		for (WebElement ele : usernameList) {
			Assert.assertEquals(ele.isDisplayed(), true, "Username is missing from users grid");
		}
		List<WebElement> contactList = getLstTxtContactUser();
		for (WebElement ele : contactList) {
			Assert.assertEquals(ele.isDisplayed(), true, "Contact is missing from users grid");
		}
		List<WebElement> linkedAccountsList = getLstLnkLinkedUser();
		for (WebElement ele : linkedAccountsList) {
			Assert.assertEquals(ele.isDisplayed(), true, "Linked Account is missing from users grid");
		}
		ExtentLogger.logInfo("Test Case execution for - verifyRegisterUserGridUIFields - is Completed.");

	}
	
	public void verifyUserLinkedAccount() throws Exception {
		ExtentLogger.logInfo("Test Case execution for - verifyUserLinkedAccount - is Initiated");
		ExtentLogger.logInfo("C72543-Verify linked accounts in SCM user tab");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		clickLnkSCMUserTab();
		waitForPageToLoad();
		
		String userNameEditCust = "abc@gmail.Com";//abc@gmail.Com  //Bodhit+Test
		ResultSet res = DataBaseUtils.getResultSet(SQLQueries.getUserId(userNameEditCust));
		res.next();
		String userID = res.getString("userid");
		ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getCountLinkedAccount(userID));
		rs.next();
		String linkedAccountCount = rs.getString("LinkedAccountCount");
		String query = "SELECT UtilityAccountNumber FROM Account WHERE AccountNumber IN \r\n" + 
				"(--SELECT AccountNumber FROM AccountRecurringPayment WHERE AccountNumber IN \r\n" + 
				"(SELECT AccountNumber FROM UserAccount WHERE UserID = \r\n" + 
				"(SELECT UserID FROM [User] WHERE UserName = '"+userNameEditCust+"') \r\n" + 
				"AND RoleID in (3)\r\n" + 
				"))";
	
		//rs = DataBaseUtil.getResultSet(SqlQuery.getQueryAccEnrollForAutoPay(userNameEditCust));
		rs = DataBaseUtils.getResultSet(query);
		rs.next();
		String autoAcc = rs.getString("UtilityAccountNumber");
		clickBtnAdvanceSearch();
		setAdvanceSearchSCMTab(autoAcc, "", "", "", "", "", "", "", "", "", "", "");//Utils.getRbDataValue("utilityANumber")
		clickBtnAdvanceSubmit();
		waitForPageToLoad();
		List<WebElement> linkedAccountsList = getLstLnkLinkedUser();
		String noOfLinkedAccountsUI = null;
		for (WebElement ele : linkedAccountsList) {
			Assert.assertEquals(ele.isDisplayed(), true, "Linked Account is missing from users grid");
			noOfLinkedAccountsUI = ele.getText();
			click(ele);
			waitForPageToLoad();
			break;
		}
		List<WebElement> eleCustName = getLstRowServiceAccounts();
		Assert.assertTrue(eleCustName.size() == Integer.parseInt(noOfLinkedAccountsUI),
				"Linked account count is not matching");
		// Validation from DB
		Assert.assertTrue(linkedAccountCount.equals(noOfLinkedAccountsUI));
		ExtentLogger.logInfo("C72470-Verify the Customer details Pop up displayed in SCM users tab");
		List<WebElement> linkedUser = getLstLnkUser();
		Assert.assertTrue(linkedUser.get(0).isDisplayed());
		click(linkedUser.get(0));
		waitForPageToLoad();
		ExtentLogger.logInfo("Test Case execution for -  verifyUserLinkedAccount - is Completed.");

	}
	
	public void setAdvanceSearchSCMTab(String serviceAccountNumber, String accountType, String status,
			String noLinkAccount, String role, String isSocial, String customerNumber, String zipCode, String city,
			String firstName, String lastName, String email) throws InterruptedException {
		// pageUtil.explicitWaitForWebElement(driver,
		// admincsrPage.getTxtServiceAccountNumber());
		if (!serviceAccountNumber.equalsIgnoreCase(""))
			// sync problem
			
		// pageUtil.enterTextUsingJsExecutor(admincsrPage.getTxtServiceAccountNumber(),
		// serviceAccountNumber);
		enterTextValue(getTxtServiceAccountNumberUser(), serviceAccountNumber);
		if (!accountType.equalsIgnoreCase(""))
			selectByVisibleTxt(getDdlAccountTypeUser(), accountType);
		if (!status.equalsIgnoreCase(""))
			selectByVisibleTxt(getDdlStatus(), status);
		if (!noLinkAccount.equalsIgnoreCase(""))
			selectByVisibleTxt(getDdlLinkAccount(), noLinkAccount);
		if (!role.equalsIgnoreCase(""))
			selectByVisibleTxt(getDdlRole(), role);
		if (!isSocial.equalsIgnoreCase(""))
			selectByVisibleTxt(getDdlSocialMedia(), isSocial);
		if (!customerNumber.equalsIgnoreCase(""))
			enterTextValue(getTxtCustomerNumberUser(), customerNumber);
		if (!zipCode.equalsIgnoreCase(""))
			enterTextValue(getTxtZipcodeUser(), zipCode);
		if (!city.equalsIgnoreCase(""))
			enterTextValue(getTxtCityUser(), city);
		if (!firstName.equalsIgnoreCase(""))
			enterTextValue(getTxtFirstNameUser(), firstName);
		if (!lastName.equalsIgnoreCase(""))
			enterTextValue(getTxtLastNameUser(), lastName);
		if (!email.equalsIgnoreCase(""))
			enterTextValue(getTxtEmailAdvanceUser(), email);

	}
	
	public void verifyUserPageSize() throws Exception {
		ExtentLogger.logInfo("Test Case execution for - verifyUserPageSize  - is Initiated");
		ExtentLogger.logInfo("C72455-Verify the Customer Display Capacity from page size dropdown");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		clickLnkSCMUserTab();
		waitForPageToLoad();
		
		explicitWaitForBtnExport();
		scrollPageToElement(getDdlPageSize());
		Assert.assertTrue(isBtnPageNextDisplayed());
		int selectedValueInDropBox = Integer.parseInt(getSelectedValueInDropBox(getDdlPageSize()));
		List<WebElement> noOfData = getLstTxtUsernameUser();
		Assert.assertEquals(selectedValueInDropBox, noOfData.size(),
				"Selected pagesize data is not appearing on the page");
		clickDdlPageSize();
		List<WebElement> pageSizeCount = getTxtPageSize();
		Assert.assertTrue(pageSizeCount.size() == 7, "Pagesize data is missing");
		ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getRegisterUsersCount());
		rs.next();
		String totalUser_DB = rs.getString("TotalUser");
		String txtCountServiceAccountsCountPagination = getTxtTotalServiceAccountsPaginationLabel();
		Assert.assertTrue(txtCountServiceAccountsCountPagination.contains(totalUser_DB),
				"Pagination count Data is not matched with db");
		// verification for previous button
		scrollPageToElement(getDdlPageSize());
		Assert.assertTrue(isBtnPagePreviousDisplayed());
		ExtentLogger.logInfo("Test Case execution for -  verifyUserPageSize - is Completed.");

	}
	
	public void verifyUserStatusDBValueAndColor() throws Exception {
		ExtentLogger.logInfo("Test Case execution for - verifyUserStatusDBValueAndColor  - is Initiated");
		ExtentLogger.logInfo("C73855-Verify status along with its color representation and DB values");
        
        SQLQueries.getQueryUpdateUserStatus("2", "Mukesh"); //Inactive
        SQLQueries.getQueryUpdateUserStatus("4", "Gautam"); //Lock
        SQLQueries.getQueryUpdateUserStatus("0", "Sachin"); //Pending Activation
        SQLQueries.getQueryUpdateUserStatus("5", "Ranvijay"); //TempLock
        
        
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		clickLnkSCMUserTab();
        waitForPageToLoad();
        waitForImgLoadingSpinnerToInvisible();
        
        clickBtnAdvanceSearch();
        setAdvanceSearchUserTab("", "", "Active", "", "", "", "", "", "", "", "", "");
        clickBtnAdvanceSubmit();
        waitForPageToLoad();
        waitForImgLoadingSpinnerToInvisible();
        
        String aa = null;
        List<WebElement> activeStatus = getXpathStatus("Active");
        if (activeStatus.get(0).isDisplayed()) {
        	String activeStatusColor = "rgba(33, 94, 217, 1)"; //rgba(27, 126, 118, 1) 
            Assert.assertTrue(getCssValue(activeStatus.get(0),"background-color").contentEquals(activeStatusColor),
              "Active status color is not appearing correct");
            List<WebElement> usernameList = getLstTxtUsernameUser();
            String UserName = usernameList.get(0).getText();
            ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getStatusUser(UserName));
            rs.next();
            String statusID = rs.getString("Status");
            Assert.assertTrue(statusID.contentEquals("1"), "Active status is not correct in Db");

        }
        
        
        SQLQueries.getQueryUpdateUserStatus("4", "ytytyty"); //Lock
     
        clickBtnAdvanceSearch();
        setAdvanceSearchUserTab("", "", "Locked", "", "", "", "", "", "", "", "", "");
        clickBtnAdvanceSubmit();
        waitForPageToLoad();
        waitForImgLoadingSpinnerToInvisible();
       
        List<WebElement> lockedStatus = getXpathStatus("Locked");
        if (lockedStatus.get(0).isDisplayed()) {
            String lockedStatusColor = "rgba(241, 139, 5, 1)";
            Assert.assertTrue(getCssValue(lockedStatus.get(0), "background-color").contentEquals(lockedStatusColor),
                    "Locked status color is not appearing correct");
            List<WebElement> usernameList = getLstTxtUsernameUser();
            String UserName = usernameList.get(0).getText();
            ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getStatusUser(UserName));
            rs.next();
            String statusID = rs.getString("Status");
            Assert.assertTrue(statusID.contentEquals("4"), "Locked status is not correct in Db");
        }
        
        SQLQueries.getQueryUpdateUserStatus("2", "yusta123"); //Inactive
        SQLQueries.getQueryUpdateUserStatus("2", "impcosc45"); //Inactive
       
        clickBtnAdvanceSearch();
        setAdvanceSearchUserTab("", "", "Pending Activation", "", "", "", "", "", "", "", "", "");
        clickBtnAdvanceSubmit();
        waitForPageToLoad();
        waitForImgLoadingSpinnerToInvisible();
        
        List<WebElement> pendigActivationStatus = getXpathStatus("Pending Activation");
        if (pendigActivationStatus.get(0).isDisplayed()) {
            String pedingActivationStatusColor = "rgba(220, 187, 25, 1)";
            Assert.assertTrue(getCssValue(pendigActivationStatus.get(0), "background-color").contentEquals(pedingActivationStatusColor),
            		"Pending Activation status color is not appearing correct");
            List<WebElement> usernameList = getLstTxtUsernameUser();
            String UserName = usernameList.get(0).getText();
            ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getStatusUser(UserName));
            rs.next();
            String statusID = rs.getString("Status");
            Assert.assertTrue(statusID.contentEquals("0"), "Pending activation status is not correct in Db");
        }
        
        SQLQueries.getQueryUpdateUserStatus("2", "yusta123"); //Inactive
        SQLQueries.getQueryUpdateUserStatus("2", "impcosc45"); //Inactive
        
        clickBtnAdvanceSearch();
        setAdvanceSearchUserTab("", "", "Inactive", "", "", "", "", "", "", "", "", "");
        clickBtnAdvanceSubmit();
        waitForPageToLoad();
        waitForImgLoadingSpinnerToInvisible();

        List<WebElement> inActiveStatus = getXpathStatus("Inactive");
        if(inActiveStatus.size()>1) {
        	if (inActiveStatus.get(0).isDisplayed()) {
                String inActiveStatusColor = "rgba(222, 227, 226, 1)";
                Assert.assertTrue(getCssValue(inActiveStatus.get(0), "background-color").contentEquals(inActiveStatusColor),
                        "InActive status color is not appearing correct");
                List<WebElement> usernameList = getLstTxtUsernameUser();
                String UserName = usernameList.get(0).getText();
                ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getStatusUser(UserName));
                rs.next();
                String statusID = rs.getString("Status");
                Assert.assertTrue(statusID.contentEquals("2"), "Inactive status is not correct in Db");
            }
        }
        
		/*
		 * SqlQuery.getQueryUpdateUserStatus("5", "impcosc45"); //TempLock Zimmi
		 * SqlQuery.getQueryUpdateUserStatus("5", "Zimmi"); //pageUtil.pause(3000);
		 * pageUtil.click(adminCsrPage.getBtnAdvanceSearch());
		 * setAdvanceSearchUserTab("", "", "Temp Locked", "", "", "", "", "", "", "",
		 * "", ""); pageUtil.click(adminCsrPage.getBtnAdvanceSubmit());
		 * pageUtil.pause(1000);
		 * 
		 * List<WebElement> tempLockedStatus = getXpathStatus("Temp Locked"); if
		 * (pageUtil.isElementDisplayed(tempLockedStatus.get(0))) { String
		 * pedingActivationStatusColor = "rgba(0, 131, 208, 1)";
		 * Assert.assertTrue(pageUtil.getCssValue(tempLockedStatus.get(0),
		 * "background-color").contentEquals( pedingActivationStatusColor),
		 * "Temp Activation status color is not appearing correct"); List<WebElement>
		 * usernameList = PageUtil.collectAllSimillarElements(driver,
		 * adminCsrPage.getlstTxtUsernameUser()); String UserName =
		 * pageUtil.getLocatorText(usernameList.get(0)); ResultSet rs =
		 * DataBaseUtil.getResultSet(SqlQuery.getStatusUser(UserName)); rs.next();
		 * String statusID = rs.getString("Status");
		 * Assert.assertTrue(statusID.contentEquals("5"),
		 * "Temp locked status is not correct in Db"); }
		 */
		 
         
        ExtentLogger.logInfo("Test Case execution for -  verifyUserStatusDBValueAndColor - is Completed.");

    }
	
	public static List<WebElement> getXpathStatus(String statusType) {
		String xpathStatus = "//span[@class = '" + statusType + "']";
		List<WebElement> ele = driver.findElements(By.xpath(xpathStatus));
		return ele;
	}
	
	public void verifyFieldsCSRActiveInactiveUsersTab() throws Exception {
		ExtentLogger.logInfo("Test Case execution for - verifyFieldsCSRActiveInactiveUsersTab - is Initiated");
		ExtentLogger.logInfo("C99938,C99939 - Verify the fields in the Active/Inactive Users Tab");

		SoftAssert sa = new SoftAssert();
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		clickLnkSCMUserTab();
		waitForPageToLoad();
		waitForImgLoadingSpinnerToInvisible();
		
		Assert.assertTrue(isLnkSCMUserTabDisplayed(),
				"Users tab is not displayed");
		List<WebElement> usersTblGridHeaders;
		List<String> actualLstActions,expectedLstActions;
		WebElement firstElement;
		String actualLinkedAccountsCount;
		//Active Users Tab
		clickLnkSCMUserActiveTab();
		waitForImgLoadingSpinnerToInvisible();

		usersTblGridHeaders = getTblSCMUsersHeaders();

		for (WebElement ele : usersTblGridHeaders) {
			sa.assertTrue(expectedElementsTextList(adminCustomerTextProp.getPropValue("txtUsersTblGridHeadersAcp")).contains(ele.getText().trim()),
					"The Expected Header Column is missing from the Active Users Grid ");
		}
		explicitWaitForLnkActionSCMTab();
		clickLnkActionSCMTab();
		waitForPageToLoad();
		
		actualLstActions = getAllElementsTextInList(getLstActionsSCMActiveUsersTab());
		expectedLstActions = new ArrayList<String>();
		expectedLstActions.add("Edit Record");
		expectedLstActions.add("Lock");
		expectedLstActions.add("Reset Password");
		expectedLstActions.add("Create Portfolio");
//		expectedLstActions.add("Delete Record");		
		expectedLstActions.add("Request Delete");		
		sa.assertTrue(actualLstActions.containsAll(expectedLstActions),"Active Users doesnot have the expected list actions.");
		clickWithJSExecutor(getlnkActionSCMTab());
		waitForImgLoadingSpinnerToInvisible();

		firstElement = getTblSCMUsersLinkedAccounts().get(0);
		actualLinkedAccountsCount = firstElement.getText();
		clickWithJSExecutor(firstElement);
		waitForImgLoadingSpinnerToInvisible();
//		sa.assertEquals(Integer.parseInt(actualLinkedAccountsCount),getWebElements(driver, getTableServiceAcctServiceAccountNumbercln()).size(),
//				"Linked Accounts size in Active Users Table is not matching with Count of Service Account Number Records.");

		sa.assertEquals(Integer.parseInt(actualLinkedAccountsCount), getTblServiceAcccount().size(),
				"Linked Accounts size in Active Users Table is not matching with Count of Service Account Number Records.");


		//Pending Activation Users Tab
		clickLnkSCMUserOthersTab();
		waitForImgLoadingSpinnerToInvisible();

		clickBtnAdvanceSearch();
		setAdvanceSearchSCMTab("", "", "Pending Activation", "", "", "", "", "", "", "",
				"", "");
		clickBtnAdvanceSubmit();
		waitForImgLoadingSpinnerToInvisible();	
		usersTblGridHeaders = getTblSCMUsersHeaders();

		for (WebElement ele : usersTblGridHeaders) {
			sa.assertTrue(expectedElementsTextList(adminCustomerTextProp.getPropValue("txtUsersTblGridHeadersAcp")).contains(ele.getText().trim())
					,"The Expected Header Column is missing from the Pending Activation Users Grid ");
		}
		
		clickLnkActionSCMTab();
		waitForImgLoadingSpinnerToInvisible();
		sa.assertTrue(isLnkEditRecordActionUsersDisplayed(),"Edit Record Action is not present for the Pending Activation Users");
		sa.assertTrue(isLnkActivateUserActionUsersDisplayed(),"Activate User Action is not present for the Pending Activation Users");
		sa.assertTrue(isLnkResendActivationLinkActionUsersDisplayed(),"Resend Activation Link Action is not present for the Pending Activation Users");
		clickWithJSExecutor(getlnkActionSCMTab());

		firstElement = getTblSCMUsersLinkedAccounts().get(0);
		actualLinkedAccountsCount = firstElement.getText();
		firstElement.click();
		waitForImgLoadingSpinnerToInvisible();
//		sa.assertEquals(Integer.parseInt(actualLinkedAccountsCount),pageUtil.getWebElements(driver, adminCsrPage.getTableServiceAcctServiceAccountNumbercln()).size(),
//				"Linked Accounts size in Pending Activation Users Table is not matching with Count of Service Account Number Records.");
		sa.assertEquals(Integer.parseInt(actualLinkedAccountsCount),Integer.parseInt(getLblRecordPaginationLabel().split("of")[1].trim()),
				"Linked Accounts size in Pending Activation Users Table is not matching with Count of Service Account Number Records.");

		sa.assertAll();
		ExtentLogger.logInfo("Test Case execution for -  verifyFieldsCSRActiveInactiveUsersTab - is Completed.");

	}
	
	public void verifyLockUnLockUser() throws Exception {
		ExtentLogger.logInfo("Test Case execution for - VerifyLockUnLockUser - is Initiated");
		ExtentLogger.logInfo("C72541-Verify Lock/UnLock user in CSR workbench for SCM user");
		String actualToastMsg,expectedToastMsg;
		List<String> actualLstActions,expectedLstActions;
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		
		clickLnkSCMUserActiveTab();
		waitForImgLoadingSpinnerToInvisible();
		clickBtnAdvanceSearch();
		setAdvanceSearchSCMTab("411004248679", "", "", "", "Owner", "", "", "", "", "",
				"", "");
		clickBtnAdvanceSubmit();
		waitForPageToLoad();
		
		clickLnkActionSCMTab();
		waitForImgLoadingSpinnerToInvisible();
		clickLnkLockFirstCustomer();
		/*pageUtil.explicitWaitForAlertPopUp(driver);
		if (pageUtil.isAlertPresent()) {
			pageUtil.verifyAlertMessage(Utils.getRbData().getString("sBeforeLockingMsg"));
			pageUtil.acceptAlert();
		}
		pageUtil.explicitWaitForAlertPopUp(driver);
		if (pageUtil.isAlertPresent()) {
			pageUtil.verifyAlertMessage(Utils.getRbData().getString("sLocksuccessfullyMsg"));
			pageUtil.acceptAlert();
		}*/

		explicitWaitForBtnProceedLockUserPopup();
		verifyTextPresentInPage(adminCustomerTextProp.getPropValue("sBeforeLockingMsg"));
		clickBtnProceedLockUserPopup();
		actualToastMsg = getToastMsgWithoutCloseButton();
		expectedToastMsg = adminCustomerTextProp.getPropValue("sLocksuccessfullyMsg");
		sa.assertEquals(actualToastMsg, expectedToastMsg,"Lock User Toast Message is not as Expected.");
		sa.assertEquals("1", getUserIsLockStatus("411004248679")
				,"Locked User Status is not matching with the DB Status.");
		sa.assertEquals(getTblSCMUsersStatusCln().get(0).getText(),"Temp Locked","Temporary Locked Status is not displayed correctly in Users Table.");
		clickLnkActionSCMTab();
		waitForImgLoadingSpinnerToInvisible();
		
		actualLstActions = getAllElementsTextInList(getLstActionsSCMActiveUsersTab());
		expectedLstActions = new ArrayList<String>();
		expectedLstActions.add("Edit Record");
		expectedLstActions.add("Unlock");
		expectedLstActions.add("Reset Password");
		expectedLstActions.add("Request Delete");		
		sa.assertTrue(actualLstActions.containsAll(expectedLstActions),"Temporary Locked User Actions are not displayed as Expected.");
		clickLnkActionSCMTab();

		//Verify the Locked User in SCP.
		DriverFactory.goToPage(Configuration.toString("scp.url"));
		LoginSteps loginSteps = new LoginSteps(driver);
		actualToastMsg = loginSteps.loginWithDeactiveUser(getUserIsLockStatusUserName("411004248679"), "Smart$2021");
		
		expectedToastMsg = "Your Account has been temporarily locked due to security reasons. Please try again after 24 hour(s). For assistance, please contact Customer Service by email at Support@SEW1.in or by phone at 7370737777.";
		//expectedToastMsg = "Your Account has been temporarily locked due to security reasons. Please try again after 24 hour(s). For assistance, please contact Customer Service by email at Support@SEW.ai or by phone at 1111111800.";
		sa.assertEquals(actualToastMsg, expectedToastMsg,"Lock User Toast Error Message Validation in SCP is not as Expected.");
		
		
		//Unlocking the User.
        DriverFactory.goToPage(Configuration.toString("csp.url"));
		
		AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		
		clickLnkSCMUserActiveTab();
		waitForImgLoadingSpinnerToInvisible();
		clickBtnAdvanceSearch();
		
		ExtentLogger.logInfo("C72451-CSR-Workbench: Verify Unlock a user from CSR workbench");
		setAdvanceSearchSCMTab("411004248679", "", "", "", "Owner", "", "", "", "", "",
				"", "");
		clickBtnAdvanceSubmit();
		waitForPageToLoad();
		
		clickLnkActionSCMTab();
		waitForImgLoadingSpinnerToInvisible();
		clickLnkLockFirstCustomer();
		

		explicitWaitForBtnProceedLockUserPopup();
		verifyTextPresentInPage(adminCustomerTextProp.getPropValue("sBeforeUnLockingMsg"));
		clickBtnProceedLockUserPopup();
		actualToastMsg = getToastMsgWithoutCloseButton();
		expectedToastMsg = adminCustomerTextProp.getPropValue("sUnlocksuccessfullyMsg"); 
		sa.assertEquals(actualToastMsg, expectedToastMsg,"UnLock User Toast Message is not as Expected.");
		sa.assertEquals("0", getUserIsLockStatus("411004248679"),
				"UnLocked User Status is not matching with the DB Status.");

		//Verify Advance Search for Temp Locked User.
		clickBtnAdvanceSearch();
		setAdvanceSearchSCMTab("", "", "Temp Locked", "", "", "", "", "", "", "",
				"", "");
		clickBtnAdvanceSubmit();
		waitForPageToLoad();
		verifyTextPresentInPage("No data found");

		// Verifying the Unlocked User from SCP
		DriverFactory.goToPage(Configuration.toString("scp.url"));
		loginSteps = new LoginSteps(driver);
		loginSteps.loginIntoTheApplication(getUserIsLockStatusUserName("411004248679"), "Smart$2021");
		
		sa.assertAll();
		ExtentLogger.logInfo("Test Case execution for -  VerifyLockUnLockUser - is Completed.");
	}
	
	public static String getUserIsLockStatus(String sUtilityNum) throws Exception {
		String iUserDetailsField = null;
		ResultSet rs;
		try {
			rs = DataBaseUtils.getResultSet(SQLQueries.getUserIsLockStatus(sUtilityNum));
			rs.next();
			iUserDetailsField = rs.getString("IsLocked");
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("No value configured");
		}
		return iUserDetailsField;
	}
	
	public static String getUserIsLockStatusUserName(String sUtilityNum) throws Exception {
		String iUserDetailsField = null;
		ResultSet rs;
		try {
			rs = DataBaseUtils.getResultSet(SQLQueries.getUserIsLockStatus(sUtilityNum));
			rs.next();
			iUserDetailsField = rs.getString("UserName");
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("No value configured");
		}
		return iUserDetailsField;
	}
	
	public void verifyUsersDataGridSorting() throws Exception {
		ExtentLogger.logInfo("Test Case execution for - verifyUsersDataGridSorting - is Initiated");
		ExtentLogger.logInfo("C107206-Verify Sorting of Users in Users Tab");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		Assert.assertTrue(isTxtUsersVisible(),
				"Users tab is not displayed");
		clickLnkSCMUserTab();
		waitForPageToLoad();

		List<WebElement> userSortableGridHeader = getTblSCMUserSortableGridHeaders();

		for (WebElement ele : userSortableGridHeader) {
			Assert.assertEquals(ele.isDisplayed(), true, "Header is missing from Users grid");
		}

		for (int i = 0; i < userSortableGridHeader.size();i++) {
			if(i!=0) {
				click(userSortableGridHeader.get(i));
			}
			waitForPageToLoad();
			String qq1 = getAttributeValue(userSortableGridHeader.get(i), "class");
			Assert.assertTrue(qq1.contains("desc"),"Descending Sort is not working for Users Grid Column " + qq1);
			click(userSortableGridHeader.get(i));
			waitForPageToLoad();
			waitForImgLoadingSpinnerToInvisible();
			String qq = getAttributeValue(userSortableGridHeader.get(i), "class");
			Assert.assertTrue(qq.contains("asc"),"Ascending Sort is not working for Users Grid Column " + qq);

		}

		ExtentLogger.logInfo("Test Case execution for -  verifyUsersDataGridSorting - is Completed.");

	}
	
	public void verifySCMUserEditRecord() throws Exception {

		ExtentLogger.logInfo("Test Case execution for - verifySCMUserEditRecord - is Initiated");
		ExtentLogger.logInfo("C72540-Verify edit User in the SCM user in CSR workbench");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		clickLnkSCMUserTab();
		waitForImgLoadingSpinnerToInvisible();
		
		explicitWaitForBtnExport();

		ResultSet res = DataBaseUtils.getResultSet(SQLQueries.getUserId("abc@gmail.Com"));
		res.next();
		String userID = res.getString("userid");

		ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getPrimaryEmailID(userID));
		rs.next();
		String EmailId = rs.getString("EmailID");
		/*
		 * pageUtil.pause(2000); pageUtil.click(adminCsrPage.getBtnAdvanceSearch());
		 * setAdvanceSearchSCMTab(Utils.getRbDataValue("utilityANumber"), "", "", "",
		 * "", "", "", "", "", "", "", "");
		 * pageUtil.click(adminCsrPage.getBtnAdvanceSubmit()); pageUtil.pause(2000);
		 */


		selectByVisibleText(getDdSearchItem(), "Username");
		populateTxtSearchCustomer("abc@gmail.Com");
		waitForPageLoaderInvisibility();
		clickBtnSearch();
		waitForPageLoaderInvisibility();

		List<WebElement> iconActions = getLstIconsActionUser();
		click(iconActions.get(0));
		waitForImgLoadingSpinnerToInvisible();
		waitForPageToLoad();
		List<WebElement> actionTexts = getLstActionsUser();
		Assert.assertTrue(actionTexts.get(0).isDisplayed(), "Edit Record action is not displayed");
		click(actionTexts.get(0));
		waitForImgLoadingSpinnerToInvisible();
		waitForPageToLoad();

		// UI validations for Edit Record page
		Assert.assertTrue(verifyTxtEditUserFirstNameVisible(),
				"First name textbox is displayed on the page");
		Assert.assertTrue(verifyTxtEditUserLastNameVisible(),
				"Last name textbox is displayed on the page");
		Assert.assertTrue(verifyTxtEditUserEmailAddressVisible(),
				"Email Address name textbox is displayed on the page");
		Assert.assertTrue(verifyTxtEditUserMiddleNameVisible(),
				"Middle name textbox is displayed on the page");
		Assert.assertTrue(verifyTxtEditUserAlternateEmailAddVisible(),
				"Alternate Email Address name textbox is displayed on the page");
		Assert.assertTrue(verifyTxtEditUserPrimaryContactNumVisible(),
				"Primary Contact textbox is displayed on the page");
		Assert.assertTrue(verifyTxtEditUserSecondaryContactNumVisible(),
				"Secondary Contact Number textbox is displayed on the page");

		// verify the cancel button
		scrollToElement(getBtnEditUserCancel());
		click(getBtnEditUserCancel());
		waitForPageToLoad();
		Assert.assertTrue(
				getInnerText(getPopUp()).contains("Are you sure you want to cancel editing?"),
				"Cancel message is not displayed after clicking CANCEL button");
		clickBtnSubmitPopUp();
		waitForPageToLoad();
		waitForImgLoadingSpinnerToInvisible();

		Assert.assertTrue(isBtnAdvanceSearchVisible(),
				"Cancel button is not working");

		// Verify the edit data

		try {
			clickLnkSCMUserTab();
			waitForImgLoadingSpinnerToInvisible();

			/*
			 * pageUtil.click(adminCsrPage.getBtnAdvanceSearch());
			 * setAdvanceSearchSCMTab(Utils.getRbDataValue("utilityANumber"), "", "", "",
			 * "", "", "", "", "", "", "", "");
			 * pageUtil.click(adminCsrPage.getBtnAdvanceSubmit()); pageUtil.pause(2000);
			 */
			
		    selectByVisibleText(getDdSearchItem(), "Username");
		    populateTxtSearchCustomer("abc@gmail.Com");
		    waitForPageLoaderInvisibility();
			clickBtnSearch();
			waitForPageLoaderInvisibility();


			List<WebElement> iconAction = getLstIconsActionUser();
			click(iconAction.get(0));
			waitForImgLoadingSpinnerToInvisible();
			
			List<WebElement> actionText = getLstActionsUser();
			Assert.assertTrue(actionText.get(0).isDisplayed(), "Edit Record action is not displayed");
			click(actionText.get(0));
			waitForPageLoaderInvisibility();
			Assert.assertTrue(
					getAttributeValue(getTxtEditUserEmailAddress(), "value").contains(EmailId),
					"Email Id is not correct for data");
			populateTxtEditUserEmailAddress("ABC@gmail.com");
			clickBtnEditUserSave();
			waitForImgLoadingSpinnerToInvisible();
			waitForPageToLoad();
			Assert.assertTrue(
					getInnerText(getPopUp()).contains("User details have been updated successfully."),
					"Successfull message is not displayed after clicking SAVE button");
			clickBtnSubmitPopUp();
			waitForPageToLoad();

			ResultSet res1 = DataBaseUtils.getResultSet(SQLQueries.getUserId("abc@gmail.Com"));
			res1.next();
			String userID1 = res1.getString("userid");

			ResultSet rs1 = DataBaseUtils.getResultSet(SQLQueries.getPrimaryEmailID(userID1));
			rs1.next();
			String expEmailId = rs1.getString("EmailID");

			clickLnkSCMUserTab();
			waitForImgLoadingSpinnerToInvisible();
			/*
			 * pageUtil.click(adminCsrPage.getBtnAdvanceSearch());
			 * setAdvanceSearchSCMTab(Utils.getRbDataValue("utilityANumber"), "", "", "",
			 * "", "", "", "", "", "", "", "");
			 * pageUtil.click(adminCsrPage.getBtnAdvanceSubmit()); pageUtil.pause(2000);
			 * pageUtil.expWaitForElePresence(driver, adminCsrPage.getbtnExport());
			 */
			waitForPageToLoad();
			selectByVisibleText(getDdSearchItem(), "Username");
			populateTxtSearchCustomer("abc@gmail.Com");
			waitForPageLoaderInvisibility();
			clickBtnSearch();
			waitForPageLoaderInvisibility();

			List<WebElement> iconAction1 = getLstIconsActionUser();
			click(iconAction1.get(0));
			waitForImgLoadingSpinnerToInvisible();
			List<WebElement> actionText1 = getLstActionsUser();
			Assert.assertTrue(actionText1.get(0).isDisplayed(), "Edit Record action is not displayed");
			click(actionText1.get(0));
			waitForImgLoadingSpinnerToInvisible();

			String aa = getAttributeValue(getTxtEditUserEmailAddress(), "value");
			Assert.assertTrue(
					getAttributeValue(getTxtEditUserEmailAddress(), "value").contains(expEmailId),
					"Email Id is not correct for data");

			scrollToElement(getBtnEditUserCancel());
			clickBtnEditUserCancel();
			waitForImgLoadingSpinnerToInvisible();
			clickBtnSubmitPopUp();
			waitForPageToLoad();

			//CSR360 validation
			clickLnkSCMUserTab();
			waitForImgLoadingSpinnerToInvisible();
			
			selectByVisibleText(getDdSearchItem(), "Username");
			populateTxtSearchCustomer("abc@gmail.Com");
			waitForPageLoaderInvisibility();
			clickBtnSearch();
			waitForPageLoaderInvisibility();
			
			clickLnkServiceAccountNumber();
			waitForImgCustomerDetailsLoadingToInvisible();
			switchToFrame(0);
			waitForImgLoadingSpinnerToInvisible();
			waitForImgCustomerDetailsLoadingToInvisible();
			waitForPageToLoad();
			
			Assert.assertTrue(
					getInnerText(getLblPrimaryEmailIDPt()).contains(expEmailId),
					"Email Id is not correct in CSR360");
			//pageUtil.getAttributeValue(adminCsrPage.getlblPrimaryEmailIDPt(), "value")

		} finally {
			DataBaseUtils
			.updateSqlComm(SQLQueries.resetPrimaryEmailID("abc@gmail.Com", EmailId));
		}
		ExtentLogger.logInfo("Test Case execution for - verifySCMUserEditRecord - is finished");


	}
	
	public String getInnerText(WebElement locator) {
		String innerText = null;
		try {
			innerText = locator.getAttribute("innerText");
			log.info("Element Text is " + innerText);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			log.info(locator + " : This element is not found");
			
		}
		return innerText;
	}
	
	public void verifyEditCustomer() throws Exception {

		ExtentLogger.logInfo("Test Case execution for - Edit customer page- is initiated");
		ExtentLogger.logInfo("C26248,C33882,C33884,C33885,C33886,C33887,C33888,C33889,C33890,C33891-Verify Edit User Tab for Customer edit");

		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();

		clickLnkSCMUserTab();
		waitForImgLoadingSpinnerToInvisible();
		
		selectByVisibleText(getDdSearchItem(), "Username");
		populateTxtSearchCustomer(adminCustomerTextProp.getPropValue("updatingEmailID"));//this is username also 
		waitForPageLoaderInvisibility();
		clickBtnSearch();
		waitForPageLoaderInvisibility();
		waitForImgLoadingSpinnerToInvisible();

		// fetching Values from the data pro file
		String nameToSearch = adminCustomerTextProp.getPropValue("customerToEdit");
		String emailIDUpdate = adminCustomerTextProp.getPropValue("updatingEmailID");
		String emailIDAltUpdate = adminCustomerTextProp.getPropValue("updatingAltEmailID");
		String phoneNumberUpdate = adminCustomerTextProp.getPropValue("updatePhone");
		String phoneNumberAltUpdate = adminCustomerTextProp.getPropValue("updateAltPhone");


		List<WebElement> iconActions = getLstIconsActionUser();
		click(iconActions.get(0));
		waitForImgLoadingSpinnerToInvisible();
		waitForPageToLoad();
		List<WebElement> actionTexts = getLstActionsUser();
		Assert.assertTrue(actionTexts.get(0).isDisplayed(), "Edit Record action is not displayed");
		click(actionTexts.get(0));
		waitForImgLoadingSpinnerToInvisible();
		waitForPageToLoad();
		

		explicitWaitForTxtFirstNameEdit();

		// verifying the Non Editable fields in edit customer
		ExtentLogger.logInfo("Test Case execution for -verify Non Editable fields - is Initiated");
		ExtentLogger.logInfo("C33883-verify the Edit Customer in the Customer Tab Non editable fields");
		Assert.assertTrue(isTxtFirstNameEditDisplayed());
		assertAttributeValue(getTxtFirstNameEdit(), "readonly", "true");
		assertAttributeValue(getTxtLastNameEdit(), "readonly", "true");
		Assert.assertTrue(isTxtLastNameEditDisplayed());
		Assert.assertTrue(isTxtMiddleNameEditDisplayed());
		assertAttributeValue(getTxtMiddleNameEdit(), "readonly", "true");
		ExtentLogger.logInfo("Test Case execution for -verify Non Editable fields - is Completed");

		// getting all the text fields text
		String beforeEmail = getAttributeValue(getTxtEmailIDEdit(), "value");
		String beforePhone = getAttributeValue(getTxtPrimaryContactNumberEdit(), "value");
		String beforeAltEmail = getAttributeValue(getTxtAlternateEmailIDEdit(), "value");
		String beforeSecondary = getAttributeValue(getTxtSecondaryContactNumberEdit(), "value");

		ExtentLogger.logInfo("Test Case execution for -verify Reset customer edit fields - is Initiated");
		ExtentLogger.logInfo("C33890-verify the Reset function in the Edit customer page");
		// updating the text
		editCustomerDeatils(emailIDUpdate, emailIDAltUpdate, phoneNumberUpdate, phoneNumberAltUpdate, "Mobile");
		clickBtnResetEdit();
		waitForImgLoadingSpinnerToInvisible();

		// verifying values after reset in edit customer page.
		Assert.assertEquals(beforeEmail, getAttributeValue(getTxtEmailIDEdit(), "value"));
		Assert.assertEquals(beforePhone,
				getAttributeValue(getTxtPrimaryContactNumberEdit(), "value"));
		Assert.assertEquals(beforeAltEmail,
				getAttributeValue(getTxtAlternateEmailIDEdit(), "value"));
		Assert.assertEquals(beforeSecondary,
				getAttributeValue(getTxtSecondaryContactNumberEdit(), "value"));
		ExtentLogger.logInfo("Test Case execution for -verify Reset customer edit fields - is Completed");

		// Refilling value and saving the Values
		ExtentLogger.logInfo("Test Case execution for -verify editable fields in edit Cutomer - is Initiated");
		ExtentLogger.logInfo("C33882-verify the Edit Customer in the Customer Tab editable fields ");
		editCustomerDeatils(emailIDUpdate, emailIDAltUpdate, phoneNumberUpdate, phoneNumberAltUpdate, "Mobile");
		waitForImgLoadingSpinnerToInvisible();
		
		clickElementUsingJsExecutor(getBtnSaveEdit());
		ExtentLogger.logInfo("Test Case execution for -verify editable fields in edit Cutomer - is Completed");

		// Verifying the Alter text after clicking the Save button
		waitForImgLoadingSpinnerToInvisible();
		waitForPageToLoad();
		ExtentLogger.logInfo("Test Case execution for -verify SuccessFull edit Message - is Initiated");
		ExtentLogger.logInfo("C33884-verify the Success Message After Editing Customer in the Customer Tab");
		explicitWaitForPopUp();
		if (isPopUpDisplayed()) {
			String alertMessage = getInnerText(getPopUp());
			verifyStringValue("User details have been updated successfully.", alertMessage);
			//pageUtil.click(adminEditCustomer.getAlertsuccessfullyOkBtn());
			clickBtnSubmitPopUp();
			waitForImgLoadingSpinnerToInvisible();
			waitForPageToLoad();
		}
		ExtentLogger.logInfo("Test Case execution for -verify SuccessFull edit Message - is Completed.");

		// DB validations
		ExtentLogger.logInfo(
				"Test Case execution for -verify the Value after editing the Customer from Customer edit page_DB - is Initiated");
		ExtentLogger.logInfo("C33889-verify the Value after editing the Customer from Customer edit page_DB ");
		String email_DB = null;
		String phone_DB = null;
		ResultSet rs;

		try {
			rs = DataBaseUtils.getResultSet(SQLQueries.getCustomerDetailsByName(nameToSearch));
			rs.next();
			email_DB = rs.getString("EmailID");
			phone_DB = rs.getString("MobilePhone");

		} catch (SQLException e) {
			e.printStackTrace();
			ExtentLogger.logFail("No value configured");
			ExtentLogger.logException(e);
		}

		// verifying value with Data base after update
		verifyStringValue(email_DB, emailIDUpdate);
		verifyStringValue(phone_DB, phoneNumberUpdate);
		ExtentLogger.logInfo(
				"Test Case execution for -verify the Value after editing the Customer from Customer edit page_DB - is Completed");

		// verifying the DB value to Update in UI
		ExtentLogger.logInfo(
				"Test Case execution for -verify the Value after editing the Customer from Customer edit page_UI - is Initiated");
		ExtentLogger.logInfo("C33888-verify the Value after editing the Customer from Customer edit page_UI ");
		
		clickLnkSCMUserTab();		
        waitForImgLoadingSpinnerToInvisible();
		
		selectByVisibleText(getDdSearchItem(), "Username");
		populateTxtSearchCustomer(adminCustomerTextProp.getPropValue("updatingEmailID"));//this is username also 
		waitForPageLoaderInvisibility();
		clickBtnSearch();
		waitForPageLoaderInvisibility();
		waitForImgLoadingSpinnerToInvisible();

		List<WebElement> iconAction = getLstIconsActionUser();
		click(iconAction.get(0));
		waitForImgLoadingSpinnerToInvisible();
		waitForPageToLoad();
		
		List<WebElement> actionText = getLstActionsUser();
		Assert.assertTrue(actionText.get(0).isDisplayed(), "Edit Record action is not displayed");
		click(actionText.get(0));
		waitForImgLoadingSpinnerToInvisible();
		waitForPageToLoad();

		explicitWaitForTxtFirstNameEdit();
		String afterEmail = getAttributeValue(getTxtEmailIDEdit(), "value");
		String afterPhone = getAttributeValue(getTxtPrimaryContactNumberEdit(), "value");
		String afterAltEmail = getAttributeValue(getTxtAlternateEmailIDEdit(), "value");
		String afterSecondary = getAttributeValue(getTxtSecondaryContactNumberEdit(), "value");
		
		verifyStringValue(afterEmail, emailIDUpdate);
		verifyStringValue(afterPhone.replaceAll("[^a-zA-Z0-9]", "").trim(), phoneNumberUpdate);
		verifyStringValue(afterAltEmail, emailIDAltUpdate);
		verifyStringValue(afterSecondary.replaceAll("[^a-zA-Z0-9]", "").trim(), phoneNumberAltUpdate);
		ExtentLogger.logInfo(
				"Test Case execution for -verify the Value after editing the Customer from Customer edit page_UI - is Completed");

		// Verifying the * fields in edit sections with emailID
		ExtentLogger.logInfo(
				"Test Case execution for -verify the Error Message After Editing Customer in the Customer Tab with emailID blank - is Initiated");
		ExtentLogger.logInfo(
				"C33885-verify the Error Message After Editing Customer in the Customer Tab with emailID blank");
		editCustomerDeatils("", emailIDAltUpdate, phoneNumberUpdate, phoneNumberAltUpdate, "Mobile");
		clickBtnSaveEdit();
		waitForImgLoadingSpinnerToInvisible();
		waitForPageToLoad();
		
		explicitWaitForPopUp();
		if (isPopUpDisplayed()) {
			String alertMessageForEmail = getInnerText(getPopUp()).trim();
			System.out.println("Message in UI:->"+alertMessageForEmail);
			verifyStringValue("EmailID: is required.", alertMessageForEmail);
			clickBtnSubmitPopUp();
			waitForImgLoadingSpinnerToInvisible();
			waitForPageToLoad();

		}
		ExtentLogger.logInfo(
				"Test Case execution for -verify the Error Message After Editing Customer in the Customer Tab with emailID blank - is Completed");

		// verify the * mobile fields
		ExtentLogger.logInfo(
				"Test Case execution for -verify the Error Message After Editing Customer in the Customer Tab with Phone Number blank - is Initiated");
		ExtentLogger.logInfo(
				"C33886-verify the Error Message After Editing Customer in the Customer Tab with Phone Number blank ");
		editCustomerDeatils(emailIDUpdate, emailIDAltUpdate, "", phoneNumberAltUpdate, "Mobile");
		clickBtnSaveEdit();
		waitForImgLoadingSpinnerToInvisible();
		waitForPageToLoad();
		
		explicitWaitForPopUp();
		if (isPopUpDisplayed()) {
			String alertMessageForEmail = getLocatorText(getPopUp()).trim();
			verifyStringValue("MobileNumber: is required.", alertMessageForEmail);
			clickBtnSubmitPopUp();
			waitForImgLoadingSpinnerToInvisible();
			waitForPageToLoad();
		}
		ExtentLogger.logInfo(
				"Test Case execution for -verify the Error Message After Editing Customer in the Customer Tab with Phone Number blank - is Completed");

		// * verifying email and mobile message
		ExtentLogger.logInfo(
				"Test Case execution for -verify the Error Message After Editing Customer in the Customer Tab with EmailID and Phone Number blank - is Initiated");
		ExtentLogger.logInfo(
				"C33887-verify the Error Message After Editing Customer in the Customer Tab with EmailID and Phone Number blank ");
		editCustomerDeatils("", emailIDAltUpdate, "", phoneNumberAltUpdate, "Mobile");
		clickBtnSaveEdit();
		waitForImgLoadingSpinnerToInvisible();
		waitForPageToLoad();
		
		explicitWaitForPopUp();
		if (isPopUpDisplayed()) {
			String alertMessage = getLocatorText(getPopUp());
			String[] split = alertMessage.split("\\n");
			String alertMessageEmail = split[0];
			String alertMessagePhone = split[1];
			verifyStringValue(adminCustomerTextProp.getPropValue("txtemailErrorMessageAecp"), alertMessageEmail);
			verifyStringValue(adminCustomerTextProp.getPropValue("txtphoneErrorMessageAecp"), alertMessagePhone);

			clickBtnSubmitPopUp();
			waitForImgLoadingSpinnerToInvisible();
			waitForPageToLoad();
		}
		ExtentLogger.logInfo(
				"Test Case execution for -verify the Error Message After Editing Customer in the Customer Tab with EmailID and Phone Number blank - is Completed");

		// verify cancel button
		
		ExtentLogger.logInfo("Test Case execution for -verify the Cancel button in the Edit customer page- is Initiated");
		ExtentLogger.logInfo("C33891-verify the Cancel button in the Edit customer page");
		explicitWaitForBtnCancelEdit();

		clickBtnCancelEdit();
		waitForImgLoadingSpinnerToInvisible();
		waitForPageToLoad();
		//pageUtil.explicitWaitForAlertPopUp(driver);
		//pageUtil.verifyAlertMessage(Utils.getRbText().getString("txtCancelEditUserPopupAecp"));
		//pageUtil.acceptAlert();
		Assert.assertTrue(getInnerText(getPopUp()).contains("Are you sure you want to cancel editing?"), "Cancel message is not displayed after clicking CANCEL button");

		clickBtnSubmitPopUp();
		waitForImgLoadingSpinnerToInvisible();
		waitForPageToLoad();
		// Verify Navigating back to the Dashboard page after canceling the form
		verifyCurrentPageTitle("Dashboard");

		ExtentLogger.logInfo("Test Case execution for - Edit customer page- is Completed");

	}
	
	public void assertAttributeValue(WebElement locator, String attributeName, String expectedAttributeValue) {
		String actualAttributeValue = getAttributeValue(locator, attributeName);
		Assert.assertEquals(actualAttributeValue, expectedAttributeValue);
		log.info("the expected value: " + expectedAttributeValue + " matches the actual value: " + actualAttributeValue);
	}
	
	public void editCustomerDeatils(String emailID, String altEmailID, String phoneNumber, String altPhoneNumber, String primaryContactType) throws InterruptedException {
		
		explicitWaitForTxtEmailIDEdit();
		enterTextValue(getTxtEmailIDEdit(), emailID);
		enterTextValue(getTxtAlternateEmailIDEdit(), altEmailID);
	
		enterTextValue(getTxtPrimaryContactNumberEdit(), phoneNumber);
		enterTextValue(getTxtSecondaryContactNumberEdit(), altPhoneNumber);
		selectByVisibleText(getDdlPrimaryContactTypeEdit(), primaryContactType);
	}
	
	public String getLocatorText(WebElement locator) {
		String locatorText = null;
		try {
			locatorText = locator.getText();
			log.info("Element Text is " + locatorText);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			log.info(locator + " : This element is not found");
			
		}
		return locatorText.trim();
	}
	
	public void verifyCurrentPageTitle(String expectedTitle) {
		
	    String actualTitle = driver.getTitle();
		Assert.assertTrue(actualTitle.contentEquals(expectedTitle));
		if (actualTitle.equalsIgnoreCase(expectedTitle)) {
			ExtentLogger.logPass(
					"The expected Title: " + expectedTitle + " matches with the actual Title: " + actualTitle);
		} else {
			ExtentLogger.logFail(
					"The expected Title: " + expectedTitle + " does not match with the actual Title: " + actualTitle);
		}
		log.info("User verifies the current Page Title as " + expectedTitle);
		// sAssert.assertAll();
	}
	
	public void verifyRegistrationPendingActivationLink() throws Exception {
        ExtentLogger.logInfo("Test Case execution for - verifyRegistrationPendingActivationLink - is Intiated");
        SoftAssert softAssert = new SoftAssert();
        // Initializing registration type a). Residential or b). Commercial
        String addressType = "Residential";
        // Initializing registration data.
        
        Customer customer = SCPConfiguration.initCustomerConfig("John Doe","1");
        // Initialize dynamic data for customer config
        //testCustomerRegistrationPage.initDynamicDataCustomer(customer, addressType, "3");
        // Init registration endpoint
        RegistrationEndpoints registrationEndpoint = new RegistrationEndpoints();
        // Do register without activation using api
        registrationEndpoint.buildRequestSetRegistrationAdd(ResourcePaths.SCP_COMMON_PATH_URI,customer);
        registrationEndpoint.hitSetRegistration();
        
        // Init username and password
        String username = customer.getUserName();
        String password = customer.getPassword();
        System.out.println("Password is "+password);
        System.out.println("Email is "+customer.getEmailAddress());

        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
        selectByVisibleText(getDdSearchItem(), "Username");
          
		populateTxtSearchCustomer(username);//this is username also 
		waitForPageLoaderInvisibility();
		clickBtnSearch();
		waitForPageLoaderInvisibility();
		waitForImgLoadingSpinnerToInvisible();
        
		clickLnkToggle();
		explicitWaitForLnkResendActivationLinkActionUsers();
		clickLnkResendActivationLinkActionUsers();
		explicitWaitForBtnProceedResendActivationLnkPopup();
		clickBtnProceedResendActivationLnkPopup();

		String actTstMsg = getToastMsgWithoutPageLoaderCloseButton();
		softAssert.assertEquals(actTstMsg, "Activation link has been re-sent successfully.",
				"Resend Activation Link Success Message is not as Expected.");
		
        
        // Activate user by fetching the mail from contract account notify Email table and validate.
		RegistrationSteps registrationSteps = new RegistrationSteps(driver);
		registrationSteps.verifyAccountActivationAfterResendActivationLink(softAssert, customer);
        // Verify first login after activation.
		registrationSteps.verifyGenericMsgForMandatoryFields(softAssert);
 
        softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifyRegistrationPendingActivationLink - is completed");
    }
	
	public void verifyResetTemporaryPasswordCSP() {
        // Logging
		ExtentLogger.logInfo("This test method verifies below tests:" +
                "C92062, C92063, C92051, C92052, C92053, C92057, C92058, " +
                "C92059, C92060, C92061, C92473");
		ExtentLogger.logInfo("Test Case execution for - verifyResetTemporaryPasswordCSP - is Initiated");
        SoftAssert softAssert = new SoftAssert();
        DataBaseUtils.callStoredProcedureUnblockAccountIp();
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
        // Fetching the user details using for reset password
        Map<String, String> userForResetPwdDetails = getUserResetPassword();
        String userId = userForResetPwdDetails.get("userid");
        String userName = userForResetPwdDetails.get("username");
        String password = userForResetPwdDetails.get("password");
        String emailId = userForResetPwdDetails.get("emailid");
        // Filter out the user to perform reset password from CSP
        
        // Set filter to username
        selectByVisibleText(getDdSearchItem(), "Username");//User Name
        
        populateTxtSearchCustomer(userName);//this is username also 
        waitForImgLoadingSpinnerToInvisible();
		clickBtnSearch();
		waitForPageLoaderInvisibility();
		waitForImgLoadingSpinnerToInvisible();
        
        // Verify there should be a unique result for the search
        List<WebElement> rowCustomerTableList = getTblCSRCustomersRows();
        if (rowCustomerTableList.size() == 1) {
        	clickLnkThreeDotsMenu();
        	waitForImgLoadingSpinnerToInvisible();
            // Verify the reset password link is present for the user searched
            WebElement resetPassLinkEle = getWebElement(getLnkResetPassDynamic(userId));
            softAssert.assertTrue(resetPassLinkEle.isDisplayed(),
                    "Reset password link is not present for the user.");
            click(resetPassLinkEle);
            waitForImgLoadingSpinnerToInvisible();
            waitForPageToLoad();
            /**
             * Verifying the reset password confirmation pop-up
             */
            String actHeading = getLocatorText(getLblResetPwdPopUpHead());
            softAssert.assertEquals(actHeading, "Reset Password",
                    "Reset password pop-up heading not matched.");
            String actMsg = getLocatorText(getLblMsgResetPwdPopUp());
            softAssert.assertEquals(actMsg,
                    "Do you wish to send a reset password link to the customer. To continue please click 'Proceed'.",
                    "Reset password pop-up message not matched.");
            softAssert.assertTrue(isBtnCloseResetPwdPopUpVisible(),
                    "No close button present on the reset password screen.");
            softAssert.assertTrue(isBtnProceedResetPwdPopUpVisible(),
                    "No proceed button present on the reset password screen.");
            // Click proceed to reset the password
            clickBtnProceedResetPwdPopUp();
            waitForImgLoadingSpinnerToInvisible();
            waitForPageToLoad();
            //acceptAlert();
            // Waiting for the reset password mail
            
            String message = null, resetPwdLink = null;
            ResultSet resultSet;
            try {
                resultSet = DataBaseUtils.getResultSet(SQLQueries
                        .getEmailContentResetPwdCSP(emailId));//getEmailContentResetPwdCsp
                resultSet.next();
                message = resultSet.getString("Message");
                System.out.println(message);
                String startStr = "<p><a href='";
                String endStr = "'>Click here</a> to initiate the password reset process for your Smart Energy Water account.";
                resetPwdLink = message.substring(message.indexOf(startStr)+ startStr.length(), message.indexOf(endStr));
                System.out.println(resetPwdLink);
            } catch (Exception e) {
                e.printStackTrace();
            }
			// Navigate to reset password link.
			navigateToUrl(resetPwdLink);
            System.out.println("Link is: "+resetPwdLink);
			
            try {
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                updatePasswordWithUserId(userId, password);
            }
        } else {
        	ExtentLogger.logFail("There are multiple records for the single username.");
        }
        softAssert.assertAll();
        ExtentLogger.logInfo("This test method verifies below tests:" +
                "C92062, C92063, C92051, C92052, C92053, C92057, C92058, " +
                "C92059, C92060, C92061, C92473");
        ExtentLogger.logInfo("Test Case execution for - verifyResetTemporaryPasswordCSP - is completed");
    }
	
	public Map<String, String> getUserResetPassword() {
        Map<String, String> userDetails = new HashMap<>();
        try {
            String query = SQLQueries.getTopLatestUpdatedActiveUser();
            ResultSet resultSet = DataBaseUtils.getResultSet(query);
            resultSet.next();
            userDetails.put("userid", resultSet.getString("userid"));
            userDetails.put("username", resultSet.getString("username"));
            userDetails.put("password", resultSet.getString("password"));
            userDetails.put("emailid", resultSet.getString("emailid"));
            userDetails.put("status", resultSet.getString("status"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDetails;
    }
	
	public void updatePasswordWithUserId(String userId, String password) {
        String query = SQLQueries.updatePasswordWithUserId(userId, password);
        try {
            DataBaseUtils.updateSqlComm(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public String getCssValue(WebElement element, String sPropertyName) {
		String propertyValue = null;
		try {
			if (element.isDisplayed()) {
				propertyValue = element.getCssValue(sPropertyName);
				// System.out.println(propertyValue);
				log.info("User gets the value as  " + propertyValue + "for the webelement");
				
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.err.format("No Element Found to perform selection" + e);
			
		}
		return propertyValue;

	}

}

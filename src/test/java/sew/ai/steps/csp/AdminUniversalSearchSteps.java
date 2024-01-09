package sew.ai.steps.csp;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.pageObjects.csp.AdminUniversalSearchPage;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.ExcelUtils;
import sew.ai.utils.PropertiesUtil;

public class AdminUniversalSearchSteps extends AdminUniversalSearchPage {

	private static final Logger log = LogManager.getLogger(AdminUniversalSearchSteps.class);
	static String csrDataFileName = FilePaths.TEST_RESOURCE_PATH + Constants.CSRSEARCH_FILENAME;
	SoftAssert sAssert = new SoftAssert();
	public static PropertiesUtil adminUniversalSearchTextProp;

	public AdminUniversalSearchSteps(WebDriver driver) {
		super(driver);
		adminUniversalSearchTextProp = new PropertiesUtil(
				FilePaths.CSP_TEXT_PROPERTIES + Constants.ADMIN_UNIVERSALSEARCH_TXT_FILENAME);
	}

	public void verifyUniversalSearchField() throws Exception {
		log.info("Test Case execution for -verifyUniversalSearchField- is initiated");
		ExtentLogger.logInfo(
				"C84042,C84043,C84045,C84046,C84047,C84048,C84049,C84050,C84052,C84053-verifyUniversalSearchField");
		AdminUniversalSearchSteps adminUniversalSearchSteps = new AdminUniversalSearchSteps(driver);

		verifyUniversalSearch();
		verifySearchFields();
		clickBtnUniversalSearchIcon();
		SetUniversalSearchValue(csrDataFileName);
		clickBtnCloseUniversalSearch();

	}

	public void verifyUniversalSearch() {
		explicitWaitForBtnUniversalSearchIcon();
		Assert.assertTrue(isBtnUniversalSearchIconVisible());
		clickBtnUniversalSearchIcon();
		String strSearch = getLblUniversalSearchHeaderLabel();
		String[] split = strSearch.split("\\s");
		Assert.assertEquals(split[0], "Universal");
	}

	public void verifySearchFields() {
		explicitWaitForTxtCustomerNameUniversalSearch();
		Assert.assertTrue(isTxtCustomerNameUniversalSearchVisible());
		Assert.assertTrue(isTxtZipUniversalSearchVisible());
		Assert.assertTrue(isTxtAccountIDUniversalSearchVisible());
		Assert.assertTrue(isTxtEmailIDUniversalSearchVisible());
		Assert.assertTrue(isTxtMobileUniversalSearchVisible());
		Assert.assertTrue(isTxtAddressUniversalSearchVisible());
		Assert.assertTrue(isBtnSubmitUniversalSearchVisible());
		Assert.assertTrue(isBtnClearUniversalSearchVisible());
	}

	public void SetUniversalSearchValue(String csrDatatFileName) throws Exception {

		ExcelUtils.openExcelFile(csrDatatFileName);
		ExcelUtils.getSheetName("GlobalSearch");
		Thread.sleep(2000);
		for (int rowNum = 1; rowNum <= ExcelUtils.getRowCount(); rowNum++) {
			enterSearchData(rowNum);
			sAssert.assertAll();
			clickBtnClearUniversalSearch();
			//waitForPageLoader();
		}

	}

	public void enterSearchData(int rowNum) throws Exception {
//		String aa = ExcelUtil.getCellValue(rowNum, 0);
		if (ExcelUtils.getCellValue(rowNum, 0).equals("Yes")) {
			enterTextValueCsr(getTxtCustomerNameUniversalSearch(), ExcelUtils.getCellValue(rowNum, 1));
			enterTextValueCsr(getTxtZipUniversalSearch(), ExcelUtils.getCellValue(rowNum, 2));
			enterTextValueCsr(getTxtAccountIDUniversalSearch(), ExcelUtils.getCellValue(rowNum, 3));
			enterTextValueCsr(getTxtEmailIDUniversalSearch(), ExcelUtils.getCellValue(rowNum, 4));
			enterTextValueCsr(getTxtMobileUniversalSearch(), ExcelUtils.getCellValue(rowNum, 5));
			enterTextValueCsr(getTxtAddressUniversalSearch(), ExcelUtils.getCellValue(rowNum, 6));
			clickBtnSubmitUniversalSearch();
			waitForPageToLoad();
			// Assert.assertTrue(getExpectedHeaders().containsAll(getAllHeadersGrid()));
			String rowCount = DataBaseUtils.getCountFieldValueUniversalSearch(ExcelUtils.getCellValue(rowNum, 1),
					ExcelUtils.getCellValue(rowNum, 2), ExcelUtils.getCellValue(rowNum, 3),
					ExcelUtils.getCellValue(rowNum, 4), ExcelUtils.getCellValue(rowNum, 5),
					ExcelUtils.getCellValue(rowNum, 6));
			System.out.println("count from db " + rowCount);
			int count = Integer.parseInt(rowCount);
			String filterCount = getPaginationCount();
			System.out.println("count from db " + filterCount);
			if (count == 0) {
				if (verifyObjectLabelEqualsCsr(getLblNoDataFoundLabel(), adminUniversalSearchTextProp.getPropValue("lblNoDataFoundAdvanceSearchAhp"), rowNum) == true) {

					sAssert.assertAll();
				}

			} else {
				if (verifyObjectLabelEqualsCsr(rowCount, filterCount, rowNum) == true) {

					sAssert.assertAll();
					List<WebElement> headersEle = getListSearchGridResultHeaders();
					String attributeValue = getAttributeValue(headersEle.get(0), "aria-sort");
					if (!attributeValue.equals("ascending")) {
						click(headersEle.get(0));
						waitForPageToLoad();
					}
					// Customer Name from UI
					ArrayList<String> CustomerName_UI = new ArrayList<String>();
					List<WebElement> listCustEle = getLblFirstInGridTable();
					for (int i = 0; i < listCustEle.size(); i++) {
						CustomerName_UI.add(getLocatorText(getLnkFirstInGridTable()));
					}
					// Service Account Number from ui
					ArrayList<String> serviceAccountNumber_UI = new ArrayList<String>();
					List<WebElement> listserviceAccountNumberEle = getLblSecondInGridTable();
					for (int i = 0; i < listserviceAccountNumberEle.size(); i++) {
						serviceAccountNumber_UI.add(listserviceAccountNumberEle.get(i).getText());
					}
					// Primary Contact number from ui
					ArrayList<String> primaryContactNumber_UI = new ArrayList<String>();
					List<WebElement> listprimaryContactNumberEle = getLblThirdInGridTable();
					for (int i = 0; i < listprimaryContactNumberEle.size(); i++) {
						primaryContactNumber_UI.add(listprimaryContactNumberEle.get(i).getText());
					}
					// Email address from ui

					ArrayList<String> emailAddress_UI = new ArrayList<String>();
					List<WebElement> listemailAddressEle = 	getLblFourthInGridTable();
					for (int i = 0; i < listemailAddressEle.size(); i++) {
						emailAddress_UI.add(listemailAddressEle.get(i).getText());
					}
					scrollToElement(listemailAddressEle.get(0));
					// Customer Status from ui
					ArrayList<String> customerStatus_UI = new ArrayList<String>();
					List<WebElement> listcustomerStatusEle = getLblFifthInGridTable();
					for (int i = 0; i < listcustomerStatusEle.size(); i++) {
						customerStatus_UI.add(listcustomerStatusEle.get(i).getText());
					}
					// Account status from ui
					ArrayList<String> accountStatus_UI = new ArrayList<String>();
					List<WebElement> listaccountStatusEle = getLblSixthInGridTable();
					for (int i = 0; i < listaccountStatusEle.size(); i++) {
						accountStatus_UI.add(listaccountStatusEle.get(i).getText());
					}
					// DB Part

					List<String> CustomerName = DataBaseUtils.getCustomerNameGlobal(ExcelUtils.getCellValue(rowNum, 1),
							ExcelUtils.getCellValue(rowNum, 2), ExcelUtils.getCellValue(rowNum, 3),
							ExcelUtils.getCellValue(rowNum, 4), ExcelUtils.getCellValue(rowNum, 5),
							ExcelUtils.getCellValue(rowNum, 6));
					List<String> ServiceAccountNumber = DataBaseUtils.getServiceAccountNumberGlobal(
							ExcelUtils.getCellValue(rowNum, 1), ExcelUtils.getCellValue(rowNum, 2),
							ExcelUtils.getCellValue(rowNum, 3), ExcelUtils.getCellValue(rowNum, 4),
							ExcelUtils.getCellValue(rowNum, 5), ExcelUtils.getCellValue(rowNum, 6));
					List<String> MobilePhone = DataBaseUtils.getPrimaryContactNoGlobal(
							ExcelUtils.getCellValue(rowNum, 1), ExcelUtils.getCellValue(rowNum, 2),
							ExcelUtils.getCellValue(rowNum, 3), ExcelUtils.getCellValue(rowNum, 4),
							ExcelUtils.getCellValue(rowNum, 5), ExcelUtils.getCellValue(rowNum, 6));
					List<String> EmailID = DataBaseUtils.getEmailAddressGlobal(ExcelUtils.getCellValue(rowNum, 1),
							ExcelUtils.getCellValue(rowNum, 2), ExcelUtils.getCellValue(rowNum, 3),
							ExcelUtils.getCellValue(rowNum, 4), ExcelUtils.getCellValue(rowNum, 5),
							ExcelUtils.getCellValue(rowNum, 6));
					List<String> CustomerStatus = DataBaseUtils.getCustomerStatusGlobal(
							ExcelUtils.getCellValue(rowNum, 1), ExcelUtils.getCellValue(rowNum, 2),
							ExcelUtils.getCellValue(rowNum, 3), ExcelUtils.getCellValue(rowNum, 4),
							ExcelUtils.getCellValue(rowNum, 5), ExcelUtils.getCellValue(rowNum, 6));
					List<String> AccountStatus = DataBaseUtils.getAccountStatusGlobal(
							ExcelUtils.getCellValue(rowNum, 1), ExcelUtils.getCellValue(rowNum, 2),
							ExcelUtils.getCellValue(rowNum, 3), ExcelUtils.getCellValue(rowNum, 4),
							ExcelUtils.getCellValue(rowNum, 5), ExcelUtils.getCellValue(rowNum, 6));
					for (int i = 0; i <= count; i++) {
						scrollToElement(listCustEle.get(i));
						verifyStringValueContainsCsr(CustomerName_UI.get(i).trim(), CustomerName.get(i));

						verifyStringValueContainsCsr(serviceAccountNumber_UI.get(i).trim(),
								ServiceAccountNumber.get(i));

						verifyStringValueContainsCsr(primaryContactNumber_UI.get(i).trim(),
								MobilePhone.get(i));

						verifyStringValueContainsCsr(emailAddress_UI.get(i).trim(), EmailID.get(i));
						verifyStringValueContainsCsr(customerStatus_UI.get(i).trim(), CustomerStatus.get(i));
						verifyStringValueContainsCsr(accountStatus_UI.get(i).trim(), AccountStatus.get(i));

						i = 9;
						break;

					}
				}

			}
			sAssert.assertAll();
		}

	}

	public boolean verifyObjectLabelEqualsCsr(String expectedValue, String actualValue, int rowNum) {
		boolean flag = false;
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(expectedValue, actualValue, "The " + expectedValue + " label contains the value" + actualValue);
		log.info("User verifies the test object Label contains: " + actualValue);

		if (expectedValue.equals(actualValue)) {
			log.info("The expected object label: " + expectedValue + " matches with the actual object label: "
					+ actualValue);
			flag = true;
		} else {
			log.error("The expected object label: " + expectedValue + " does not matches with the actual object label: "
					+ actualValue + " at Row number: " + rowNum);
			flag = false;
		}

		return flag;

	}

	public String getPaginationCount() {
		String pagination = getLblPaginationLabel();
		String[] pagigationCount = pagination.split("f ");
		String count = pagigationCount[1];
		String[] count1 = count.split("\\s");
		String count2 = count1[0];
		return count2;
	}
	
	public static ArrayList<WebElement> collectAllSimillarElements(WebDriver driver, By locator) {
		ArrayList<WebElement> elementList;
		elementList = (ArrayList<WebElement>) driver.findElements(locator);
		return elementList;
	}
	
	public String getAttributeValue(WebElement element, String attributeName) {
		String attributeValue = null;
		try {
			if (element.isDisplayed()) {
				attributeValue = element.getAttribute(attributeName);
				// System.out.println(attributeValue);
				log.info("User gets the value as  " + attributeValue + "for the webelement");
				
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.err.format("No Element Found to perform selection" + e);
			
		}
		return attributeValue;
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

		
	}
	
	public void verifyBlankSearch() throws Exception {
		log.info("Test Case execution for -verifyBlankSearch- is initiated");
		ExtentLogger.logInfo("C84044-Verify that Blank search brings all the results");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();

		clickBtnUniversalSearchIcon();
		clickBtnSubmitUniversalSearch();
		waitForPageToLoad();
	

		/*
		 * String rowCount=DataBaseUtil.getCountEmptyFieldSearchUniversalSearch();
		 * System.out.println("count from db "+rowCount); //int
		 * count=Integer.parseInt(rowCount); String filterCount=getPaginationCount();
		 * int count=Integer.parseInt(filterCount);
		 * System.out.println("count from db "+filterCount);
		 * //pageUtil.verifyObjectLabelEquals(rowCount, filterCount);
		 */

		List<WebElement> headersEle = getListSearchGridResultHeaders();
		String attributeValue = getAttributeValue(headersEle.get(0), "aria-sort");
		if (!attributeValue.equals("ascending")) {
			click(headersEle.get(0));
			waitForPageToLoad();
		}
		// Customer Name from UI
		ArrayList<String> CustomerName_UI = new ArrayList<String>();
		List<WebElement> listCustEle = getLblFirstInGridTable();
		for (int i = 0; i < listCustEle.size(); i++) {
			CustomerName_UI.add(getLocatorText(getLnkFirstInGridTable()));
		}
		// Service Account Number from ui
		ArrayList<String> serviceAccountNumber_UI = new ArrayList<String>();
		List<WebElement> listserviceAccountNumberEle = getLblSecondInGridTable();
		for (int i = 0; i < listserviceAccountNumberEle.size(); i++) {
			serviceAccountNumber_UI.add(listserviceAccountNumberEle.get(i).getText());
		}
		// Primary Contact number from ui
		ArrayList<String> primaryContactNumber_UI = new ArrayList<String>();
		List<WebElement> listprimaryContactNumberEle = getLblThirdInGridTable();
		for (int i = 0; i < listprimaryContactNumberEle.size(); i++) {
			primaryContactNumber_UI.add(listprimaryContactNumberEle.get(i).getText());
		}
		// Email address from ui

		ArrayList<String> emailAddress_UI = new ArrayList<String>();
		List<WebElement> listemailAddressEle = 	getLblFourthInGridTable();
		for (int i = 0; i < listemailAddressEle.size(); i++) {
			emailAddress_UI.add(listemailAddressEle.get(i).getText());
		}
		scrollToElement(listemailAddressEle.get(0));
		// Customer Status from ui
		ArrayList<String> customerStatus_UI = new ArrayList<String>();
		List<WebElement> listcustomerStatusEle = getLblFifthInGridTable();
		for (int i = 0; i < listcustomerStatusEle.size(); i++) {
			customerStatus_UI.add(listcustomerStatusEle.get(i).getText());
		}
		// Account status from ui
		ArrayList<String> accountStatus_UI = new ArrayList<String>();
		List<WebElement> listaccountStatusEle = getLblSixthInGridTable();
		for (int i = 0; i < listaccountStatusEle.size(); i++) {
			accountStatus_UI.add(listaccountStatusEle.get(i).getText());
		}
        // DB Part

		List<String> CustomerName = DataBaseUtils.getCustomerNameGlobalEmptySearch();
		List<String> ServiceAccountNumber = DataBaseUtils.getServiceAccountNumberGlobalEmptySearch();
		List<String> MobilePhone = DataBaseUtils.getPrimaryContactNoGlobalEmptySearch();
		List<String> EmailID = DataBaseUtils.getEmailAddressGlobalEmptySearch();
		List<String> CustomerStatus = DataBaseUtils.getCustomerStatusGlobalEmptySearch();
		List<String> AccountStatus = DataBaseUtils.getAccountStatusGlobalEmptySearch();
		for (int i = 0; i <= 10; i++) {
			scrollToElement(listCustEle.get(i));
			verifyStringValueContainsCsr(CustomerName_UI.get(i).trim(), CustomerName.get(i));

			verifyStringValueContainsCsr(serviceAccountNumber_UI.get(i).trim(), ServiceAccountNumber.get(i));

			verifyStringValueContainsCsr(primaryContactNumber_UI.get(i).trim(), MobilePhone.get(i));

			verifyStringValueContainsCsr(emailAddress_UI.get(i).trim(), EmailID.get(i));
			verifyStringValueContainsCsr(customerStatus_UI.get(i).trim(), CustomerStatus.get(i));
			verifyStringValueContainsCsr(accountStatus_UI.get(i).trim(), AccountStatus.get(i));

			i = 9;
			break;

		}

	}
	
	public void verifyHyperLinkCustomerName() throws Exception {
		log.info("Test Case execution for -verifyHyperLinkCustomerName- is initiated");
		ExtentLogger.logInfo("C84054,C84055-verifyHyperLinkCustomerName");
		AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
		adminHomeSteps.navigateCSRWorkBenchWidget();
		
		clickBtnUniversalSearchIcon();
		//SetUniversalSearchValue(csrDataFileName);
		verify360View(csrDataFileName);
		
	}
	
	public void verify360View(String csrDatatFileName) throws Exception {

		ExcelUtils.openExcelFile(csrDatatFileName);
		ExcelUtils.getSheetName("GlobalSearch");
		AdminCustomerSteps adminCustomerSteps = new AdminCustomerSteps(driver);
		for (int rowNum = 1; rowNum <= ExcelUtils.getRowCount(); rowNum++) {
			if (ExcelUtils.getCellValue(rowNum, 0).equals("Yes")) {
				enterSearchData(rowNum);
				
				if (isLnkFirstInGridTableVisible()) {
					clickLnkFirstInGridTable();
					adminCustomerSteps.validateCustomer360View(rowNum);
				}
				sAssert.assertAll();
			}

		}

	}
	
	public void verifyUniversalSearchFieldsAndReportGridInfo() throws Exception {
        log.info("Test Case execution for - verifyUniversalSearchFieldsAndReportGridInfo - is Initiated");
        ExtentLogger.logInfo("62712, 62713 - Verifying the Universal search fields " +
                "and report info after clicking submit button, without entering the data.");
        
        // To verify that if user click on Universal search button
        explicitWaitForBtnUniversalSearchIcon();
        clickBtnUniversalSearchIcon();
        verifySearchFields();
        clickBtnSubmitUniversalSearch();
        waitForPageToLoad();
        // To verify that if user click on submit without enter any input in
        // universal search.
        
        Assert.assertTrue(getLblSearchReportGridInfoLabel().contains(adminUniversalSearchTextProp.getPropValue("lblTxtSearchReportGridInfoAhp")));
        log.info("Test Case execution for -  verifyUniversalSearchFieldsAndReportGridInfo - is Completed.");
    }
	
	

}

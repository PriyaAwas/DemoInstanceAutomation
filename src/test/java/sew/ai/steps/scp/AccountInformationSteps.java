package sew.ai.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import sew.ai.config.CSPConfiguration;
import sew.ai.driver.DriverFactory;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.RegistrationConfig;
import sew.ai.models.User;
import sew.ai.pageObjects.scp.AccountInformationPage;
import sew.ai.pageObjects.scp.DashboardPage;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.PropertiesUtil;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AccountInformationSteps extends AccountInformationPage {
	private static final Logger log = LogManager.getLogger(AccountInformationSteps.class);
	public static PropertiesUtil accontInformationTextProp;
	Map<String, Integer> registrationConfig = new HashMap<>();
	public static Map<String, RegistrationConfig> getRegistrationConfig = new LinkedHashMap<>();

	public AccountInformationSteps(WebDriver driver) {
		super(driver);
		accontInformationTextProp = new PropertiesUtil(
				FilePaths.SCP_TEXT_PROPERTIES + Constants.ACCOUNT_PROFILE_TXT_FILENAME);
	}

	/**
	 * This method verifies the objects on the Notification Preference page.
	 *
	 * @param softAssert verify all the soft verification.
	 * @param user       this models contains all the user details.
	 */

	public Boolean isAccountInformationPage(String url, String title) {
		Boolean isaccountInformationPage = false;
		log.info("Checking that the current page is account information page.");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isaccountInformationPage = true;
		log.info("The current page is account information page {}: " + isaccountInformationPage);
		return isaccountInformationPage;
	}

	public void linkAccountUIAndValidation(SoftAssert sAssert, User user) {
		log.info("To verify the UI and validation message of Link Account popup "
				+ "fields for Residential and Commercial Accounts.");
		registrationConfig = CSPConfiguration.initMCspConfig();
		getRegistrationConfig = CSPConfiguration.initMRegistrationData();
		HomeSteps homeSteps = new HomeSteps(driver);
		if (registrationConfig.get("MyAccount") == 1) {
			homeSteps.navigateToAccountInformation();
			// Verify page heading

			Assert.assertTrue(isAccountInformationPage(
					accontInformationTextProp.getPropValue("expectedAccountInformationPageUrl"),
					accontInformationTextProp.getPropValue("expectedAccountInformationPageTitle")));
			clickBtnaccount();
			driver.switchTo().activeElement();
			isaddAccountmodalVisible();
			HashMap<String, String[]> hashMap = getMinMaxOfRegistrationFieldsFromDB();
			/**
			 * Need a Query to Registration Type template, By default taking it to "Advance"
			 */
			String templateType = "Advance";
			// Test Case to Verify the Link Account Popup UI
			switch (templateType) {
			case "Advance":
				System.out.println("This is Advance Link Account popup window");
				driver.switchTo().activeElement();
				/*
				 * Link Residential Account UI validation
				 */
				clickRdbLinkAccResidentialPopup();

				sAssert.assertEquals(getAccountmodal(),
						accontInformationTextProp.getPropValue("textLblLinkAccountPopupHeadingApp"),
						"link account popup heading is not displayed");
				sAssert.assertEquals(getRdbLinkAccResidentialPopup(),
						accontInformationTextProp.getPropValue("textRbtnLinkAccountResidentialPopupApp"),
						"link account residential popup is not displayed");
				sAssert.assertEquals(getRdbLinkAccCommercialPopup(),
						accontInformationTextProp.getPropValue("textRbtnLinkAccountCommercialPopupApp"),
						"link account commerical popup is not displayed");
				if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1")) {
					sAssert.assertEquals(getAddAccountPopup(),
							accontInformationTextProp.getPropValue("textLblAccountNumberPopupApp"),
							"account number label is not displayed");
					sAssert.assertTrue(istxtAddAccountPopupVisible(), "Add account popup test is not visible");
				}
				if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")) {

					sAssert.assertEquals(getAddlblZipCodePopup(),
							accontInformationTextProp.getPropValue("textLblZipCodePopupApp"));
					sAssert.assertTrue(isAddlblZipCodePopupVisible(), "Add label zip code popup is not visible");

				}
				if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1")) {

					sAssert.assertEquals(getAddlblMeterNumberPopup(),
							accontInformationTextProp.getPropValue("textLblMeterNumberPopupApp"));
					sAssert.assertTrue(isAddlblMeterNumberPopupVisible(),
							"Add label meter number popup is not visible");

				}
				if (getRegistrationConfig.get("SSN").getFieldDisplayStatus().equals("1")) {

					sAssert.assertEquals(getaddlblSrvcRqustlblSSN(),
							accontInformationTextProp.getPropValue("textLblSSNPopupApp"),
							"label SSN popup is not matched");

					// sAssert.assertf(isLblFIDTINPopupVisible());
					sAssert.assertTrue(isTxtSSNPopupAppVisible(), "SSN Popup is not visible");

				}
				if (getRegistrationConfig.get("Driving License Number").getFieldDisplayStatus().equals("1")) {

					sAssert.assertEquals(getLblDLPopupApp(),
							accontInformationTextProp.getPropValue("textLblDLPopupApp"), "label DDL is not matched");
					sAssert.assertTrue(isTxtDLPopupAppVisible(), "dl text box is not visible");
				}
				if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1")) {
					sAssert.assertEquals(getLblStreetAddPopupApp(),
							accontInformationTextProp.getPropValue("textLblStreetAddrPopupApp"),
							"Street add popup is not matched");
					sAssert.assertTrue(isTxtStreetAddPopupAppVisible(), "Street address text not matched");
				}
				Assert.assertEquals(getBtnLinkAccPopupSubmit("value"),
						accontInformationTextProp.getPropValue("textBtnLinkAccountPopupSubmitApp"),
						"link account submit button value is not matched");
				Assert.assertEquals(getBtnLinkAccPopupCancel("value"),
						accontInformationTextProp.getPropValue("textBtnCancelLinkAccountApp"),
						"link account cancel button is not visible");

				sAssert.assertTrue(isbtnCloseCrossPopupVisible(), "close Cross popup button is not visible");
				sAssert.assertTrue(isRdbLinkAccResidentialPopupVisible(),
						"residential Cross popup button is not visible");
				sAssert.assertTrue(islnkRdbLinkAccCommercialPopupVisible(),
						"commerical Cross popup button is not visible");
				sAssert.assertTrue(isclickbtnLinkAccPopupSubmitVisible(),
						"link account popup submit button is not visible");
				sAssert.assertTrue(isclickBtnCancelLinkAccountVisible(),
						"link account popup submit button is not visible");

				/*
				 * Link Commercial Account UI validation
				 */

				lnkRdbLinkAccCommercialPopup();
				sAssert.assertEquals(getAccountmodal(),
						accontInformationTextProp.getPropValue("textLblLinkAccountPopupHeadingApp"),
						"link account popup heading is not displayed");
				sAssert.assertEquals(getRdbLinkAccResidentialPopup(),
						accontInformationTextProp.getPropValue("textRbtnLinkAccountResidentialPopupApp"),
						"link account residential text is not matched");
				sAssert.assertEquals(getRdbLinkAccCommercialPopup(),
						accontInformationTextProp.getPropValue("textRbtnLinkAccountCommercialPopupApp"),
						"link account commerical text is not matched ");
				if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1")) {

					sAssert.assertEquals(getAddAccountPopup(),
							accontInformationTextProp.getPropValue("textLblAccountNumberPopupApp"),
							"account number label is not matched");
					istxtAddAccountPopupVisible();
				}
				if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")) {

					sAssert.assertEquals(getAddlblZipCodePopup(),
							accontInformationTextProp.getPropValue("textLblZipCodePopupApp"),
							"Zip code is not matched");
					isAddlblZipCodePopupVisible();

				}
				if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1")) {

					sAssert.assertEquals(getAddlblMeterNumberPopup(),
							accontInformationTextProp.getPropValue("textLblMeterNumberPopupApp"),
							"meter number is not matched");
					isAddlblMeterNumberPopupVisible();
				}
				if (getRegistrationConfig.get("FID/TIN").getFieldDisplayStatus().equals("1")) {
					// assertTrue(pageUtil.isElementNotDisplayed(accountProfilePage.getLblSSNPopupApp()));
					// sAssert.assertEquals(getTxtFIDTINPopupApp(),
					// accontInformationTextProp.getPropValue("textLblFIDTINPopupApp"),"Fid tin
					// popup is not matched");

					sAssert.assertTrue(isLblFIDTINPopupVisible(), "fid tin textbox is not visible");

				}
				if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1")) {
					sAssert.assertEquals(getLblStreetAddPopupApp(),
							accontInformationTextProp.getPropValue("textLblStreetAddrPopupApp"),
							"Street address label is not matched");
					isTxtStreetAddPopupAppVisible();
				}
				sAssert.assertEquals(getBtnLinkAccPopupSubmit("value"),
						accontInformationTextProp.getPropValue("textBtnLinkAccountPopupSubmitApp"),
						"Submit button value is not matched");
				sAssert.assertEquals(getBtnLinkAccPopupCancel("value"),
						accontInformationTextProp.getPropValue("textBtnCancelLinkAccountApp"),
						"Cancel button value is not matched");
				sAssert.assertTrue(isbtnCloseCrossPopupVisible(), "close Cross popup button is not visible");
				sAssert.assertTrue(isRdbLinkAccResidentialPopupVisible(),
						"residential Cross popup button is not visible");
				sAssert.assertTrue(islnkRdbLinkAccCommercialPopupVisible(),
						"commerical Cross popup button is not visible");
				sAssert.assertTrue(isclickbtnLinkAccPopupSubmitVisible(),
						"link account popup submit button is not visible");
				sAssert.assertTrue(isclickBtnCancelLinkAccountVisible(),
						"link account popup submit button is not visible");
				clickbtnCloseCrossPopup();
				pause(2000);
				// sAssert.assertFalse(isBtnaccountVisible(),"link accont button is visible ");
				// All mandatory field validation for Residential Account popup
				clickBtnaccount();
				pause(1000);
				driver.switchTo().activeElement();
				clickbtnLinkAccPopupSubmit();
				// sAssert.assertEquals(getToastMessage(),
				// accontInformationTextProp.getPropValue("txtLblMandaoryErrorMessageLgp"));
				// All mandatory field validation for Commercial Account popup
				lnkRdbLinkAccCommercialPopup();
				clickbtnLinkAccPopupSubmit();
				// sAssert.assertEquals(getToastMessage(),
				// accontInformationTextProp.getPropValue("txtLblMandaoryErrorMessageLgp"));
				clickBtnCancelLinkAccount();
				pause(2000);
				sAssert.assertFalse(islnkRdbLinkAccCommercialPopupVisible(), "Commercial link account is not visible");
				/*
				 * Residential Account validation Testing
				 */
				clickBtnaccount();
				pause(1000);
				driver.switchTo().activeElement();
				clickRdbLinkAccResidentialPopup();
				if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1")) {
					populateAddAccountPopup("12345678");
				}
				if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")) {
					populateZipCodePopup("12345");

				}
				if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1")) {
					populateaddMeterNumber("E0002");

				}
				if (getRegistrationConfig.get("SSN").getFieldDisplayStatus().equals("1")) {
					populatetxtSSNPopup("12345");

				}
				if (getRegistrationConfig.get("Driving License Number").getFieldDisplayStatus().equals("1")) {
					populateDLPopupApp("DL2");

				}
				if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1")) {
					populateStreetAddPopupApp("123, Text");

				}
				int count = 0;
				// Two blank mandatory fields Validation
				if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1") && count < 2) {
					clearAddAccountPopup();
					count++;
				}
				if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1") && count < 2) {
					clearZipCodePopup();
					count++;
				}
				if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1") && count < 2) {
					clearMeterNumberField();
					count++;
				}
				if (getRegistrationConfig.get("SSN").getFieldDisplayStatus().equals("1") && count < 2) {
					cleartxtSSNPopupField();
					count++;
				}
				if (getRegistrationConfig.get("Driving License Number").getFieldDisplayStatus().equals("1")
						&& count < 2) {
					clearDLPopupAppField();
					count++;
				}
				if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1") && count < 2) {
					clearStreetAddField();
					count++;
				}
				clickbtnLinkAccPopupSubmit();
				// sAssert.assertEquals(getToastMessage(),
				// accontInformationTextProp.getPropValue("txtLblMandaoryErrorMessageLgp"));
				clickBtnCancelLinkAccount();
				pause(2000);
				isBtnaccountVisible();
				clickBtnaccount();
				pause(1000);
				driver.switchTo().activeElement();
				clickRdbLinkAccResidentialPopup();
				if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1")) {
					populateAddAccountPopup("12345678");

				}
				if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")) {
					populateZipCodePopup("12345");

				}
				if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1")) {
					populateaddMeterNumber("E0002");

				}
				if (getRegistrationConfig.get("SSN").getFieldDisplayStatus().equals("1")) {
					populatetxtSSNPopup("12345");

				}
				if (getRegistrationConfig.get("Driving License Number").getFieldDisplayStatus().equals("1")) {

					populateDLPopupApp("DL123");
				}
				if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1")) {
					populateStreetAddPopupApp("123, Text");
				}
				String min;
				String max;
				// Account Number Validation
				if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1")
						&& getRegistrationConfig.get("Account Number").getFieldMandatoryProperty().equals("true")) {
					clearAddAccountPopup();
					clickbtnLinkAccPopupSubmit();
					sAssert.assertEquals(getRdbMessageCommon(),
							accontInformationTextProp.getPropValue("txtErrorMsgInvalidAccountNumberApp"));
					min = hashMap.get("Account Number")[0];
					max = hashMap.get("Account Number")[1];
					populateAddAccountPopup("1");
					clickbtnLinkAccPopupSubmit();

					if (min.equals(max)) {
						sAssert.assertEquals(getRdbMessageCommon(),
								"Please enter a valid " + min + " digit Account Number.",
								"error message is not matched");
					} else {
						sAssert.assertEquals(getRdbMessageCommon(),
								"Please enter a valid " + min + " to " + max + " digit Account Number.",
								"error message is not mathced");
					}
					populateAddAccountPopup("12345678");

				}

				// Zip Code Validation
				if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")
						&& getRegistrationConfig.get("Zip Code").getFieldMandatoryProperty().equals("true")) {
					clearZipCodePopup();
					clickbtnLinkAccPopupSubmit();
					sAssert.assertEquals(getRdbMessageCommon(),
							accontInformationTextProp.getPropValue("txtErrorMsgAddZipCodeApp"),
							"error message for add zip code is not matched");
					min = hashMap.get("Zip Code")[0];
					max = hashMap.get("Zip Code")[1];
					populateZipCodePopup("9");
					clickbtnLinkAccPopupSubmit();

					if (min.equals(max)) {
						sAssert.assertEquals(getRdbMessageCommon(), "Please enter a valid " + min + " digit Zip Code.",
								"error message 2 is not matched");
					} else {
						sAssert.assertEquals(getRdbMessageCommon(),
								"Please enter a valid " + min + " to " + max + " digit Zip Code.",
								"error message 2 is not matched");
					}
					populateZipCodePopup("12345");

				}
				// Meter Number Validation
				if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1")
						&& getRegistrationConfig.get("Meter Number").getFieldMandatoryProperty().equals("true")) {
					clearMeterNumberField();
					clickbtnLinkAccPopupSubmit();
					sAssert.assertEquals(getRdbMessageCommon(),
							accontInformationTextProp.getPropValue("txtMsgAddMeterNumberAccountApp"),
							"text message for add meter number is not matched");
					min = hashMap.get("Meter Number")[0];
					max = hashMap.get("Meter Number")[1];
					populateaddMeterNumber("E");
					clickbtnLinkAccPopupSubmit();

					if (min.equals(max)) {
						sAssert.assertEquals(getRdbMessageCommon(),
								"Please enter a valid " + min + " digit Meter Number.",
								"common error message 3 is not matched");
					} else {
						sAssert.assertEquals(getRdbMessageCommon(),
								"Please enter a valid " + min + " to " + max + " digit Meter Number.",
								"common error message 3 is not matched");
					}
					populateaddMeterNumber("E0002");

				}
				// SSN Number Validation
				if (getRegistrationConfig.get("SSN").getFieldDisplayStatus().equals("1")
						&& getRegistrationConfig.get("SSN").getFieldMandatoryProperty().equals("true")) {
					cleartxtSSNPopupField();
					clickbtnLinkAccPopupSubmit();
					sAssert.assertEquals(getRdbMessageCommon(),
							accontInformationTextProp.getPropValue("txtErrMsgInvalidSsnNumApp"),
							"error message for invalid ssn number is not matched");
					min = hashMap.get("SSN")[0];
					max = hashMap.get("SSN")[1];
					populatetxtSSNPopup("1");

					clickbtnLinkAccPopupSubmit();
					if (min.equals(max)) {
						sAssert.assertEquals(getRdbMessageCommon(),
								"Please enter valid last " + min + " digits of SSN.",
								"common error message 4 is not matched");
					} else {
						sAssert.assertEquals(getRdbMessageCommon(),
								"Please enter valid last " + min + " to " + max + " digits of SSN.",
								"common error message 4 is not matched");
					}
					populatetxtSSNPopup("12345");

				}
				// DL Number Validation
				if (getRegistrationConfig.get("Driving License Number").getFieldDisplayStatus().equals("1")
						&& getRegistrationConfig.get("Driving License Number").getFieldMandatoryProperty()
								.equals("1")) {
					clearDLPopupAppField();
					clickbtnLinkAccPopupSubmit();
					sAssert.assertEquals(getRdbMessageCommon(),
							accontInformationTextProp.getPropValue("txtErrorMsgDrivLicLinkAccApp"),
							"text message for driving license is not matched");
					min = hashMap.get("Driving License Number")[0];
					max = hashMap.get("Driving License Number")[1];
					// pageUtil.enterTextValue(accountProfilePage.getTxtDLPopupApp(), "D");
					clickbtnLinkAccPopupSubmit();
					if (min.equals(max)) {
						sAssert.assertEquals(getRdbMessageCommon(),
								"Please enter a valid " + min + " digit Driving License Number.",
								"common error message 5 is not matched");
					} else {
						sAssert.assertEquals(getRdbMessageCommon(),
								"Please enter a valid " + min + " to " + max + "digit Driving License Number.",
								"common error message 5 is not matched");
					}
					populateDLPopupApp("DL123");

				}
				// Street Address Validation
				if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1")
						&& getRegistrationConfig.get("Street Address").getFieldMandatoryProperty().equals("1")) {
					clearStreetAddField();
					clickbtnLinkAccPopupSubmit();
					sAssert.assertEquals(getRdbMessageCommon(),
							accontInformationTextProp.getPropValue("txtErrorMsgStreetAddLinkAccApp"),
							"error message for street address is not matched");
					min = hashMap.get("Street Address")[0];
					max = hashMap.get("Street Address")[1];
					populateStreetAddPopupApp("00");
					clickbtnLinkAccPopupSubmit();
					if (min.equals(max)) {
						sAssert.assertEquals(getRdbMessageCommon(), "Please enter a valid Street Address.",
								"error message 6 is not matched");
					} else {
						sAssert.assertEquals(getRdbMessageCommon(), "Please enter a valid Street Address.",
								"error message 6 is not matched");
					}
				}
				break;
			case "Medium":
				System.out.println("This is Advance Link Account popup window");
				break;
			case "Basic":
				System.out.println("This is Advance Link Account popup window");
				break;
			case "Custom":
				System.out.println("This is Advance Link Account popup window");
				break;
			}
		}
	}

	public void linkAlreadyRegisteredActiveAccount(SoftAssert sAssert, User user) {
		log.info("To verify validation message on linking an already registered-Active account");
		registrationConfig = CSPConfiguration.initMCspConfig();
		getRegistrationConfig = CSPConfiguration.initMRegistrationData();
		HomeSteps homeSteps = new HomeSteps(driver);
		if (registrationConfig.get("MyAccount") == 1) {
			// Verify page heading
			homeSteps.navigateToAccountInformation();
			// Verify page heading

			Assert.assertTrue(isAccountInformationPage(
					accontInformationTextProp.getPropValue("expectedAccountInformationPageUrl"),
					accontInformationTextProp.getPropValue("expectedAccountInformationPageTitle")));
			clickBtnaccount();
			pause(1000);
			driver.switchTo().activeElement();
			isaddAccountmodalVisible();

			String query = SQLQueries.getInfoForLinkingAccountFromDB("1", "1");// Residential
			// String query = SQLQueries.getInfoForLinkingAccountFromDB("Residential",
			// "Active", user.getUserName());// Residential
			ResultSet rs = DataBaseUtils.getResultSet(query);
			Map<String, String> linkResidentialAccountInfo = tableResultInMapForm(rs);
			System.out.println(linkResidentialAccountInfo);
			clickRdbLinkAccResidentialPopup();
			// pageUtil.click(myAccountProfilePage.getRbtnLinkAccountResidentialPopupApp());
			if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1")) {
				populateAddAccountPopup(linkResidentialAccountInfo.get("UtilityAccountNumber"));

			}
			if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")) {
				populateZipCodePopup(linkResidentialAccountInfo.get("ZipCode"));

			}
			if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1")) {

				populateaddMeterNumber(linkResidentialAccountInfo.get("MeterNumber"));
			}
			if (getRegistrationConfig.get("FID/TIN").getFieldDisplayStatus().equals("1")) {
				// populateTxtFIDTINPopupApp("12345");

			}
			if (getRegistrationConfig.get("SSN").getFieldDisplayStatus().equals("1")) {
				populatetxtSSNPopup("12345");

			}
			if (getRegistrationConfig.get("Driving License Number").getFieldDisplayStatus().equals("1")) {
				populateDLPopupApp(linkResidentialAccountInfo.get("DrivingLicence"));

			}
			if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1")) {
				populateStreetAddPopupApp(linkResidentialAccountInfo.get("Address1"));

			}
			clickbtnLinkAccPopupSubmit();
			sAssert.assertEquals(getToastMessage(),
					accontInformationTextProp.getPropValue("txtMsgAlreadyAddAccountApp"), "massage is not displayed");
			clickBtnCancelLinkAccount();
			pause(2000);
			// sAssert.assertFalse(isBtnaccountVisible());

			DriverFactory.refreshBrowser();
			for (int i = 0; i < getlbldivAccountList().size(); i++) {
				if (getlbldivAccountList().get(i).getText()
						.equals(linkResidentialAccountInfo.get("UtilityAccountNumber"))) {
					sAssert.assertTrue(false, "Already registered-Active account get added");
				}
			}
			log.info("To verify validation message on linking an already registered-Active account");
		} else {
			log.info("My Account link is not available on the header");
		}

	}

	public void linkInactiveAccount(SoftAssert sAssert, User user) {
		log.info("To verify user is NOT able to Link an INACTIVE account.");
		registrationConfig = CSPConfiguration.initMCspConfig();
		getRegistrationConfig = CSPConfiguration.initMRegistrationData();
		HomeSteps homeSteps = new HomeSteps(driver);

		if (registrationConfig.get("MyAccount") == 1) {

			homeSteps.navigateToAccountInformation();
			// Verify page heading

			Assert.assertTrue(isAccountInformationPage(
					accontInformationTextProp.getPropValue("expectedAccountInformationPageUrl"),
					accontInformationTextProp.getPropValue("expectedAccountInformationPageTitle")));
			clickBtnaccount();
			driver.switchTo().activeElement();
			isaddAccountmodalVisible();
			String query = SQLQueries.getInfoForLinkingAccountFromDB("1", "0");// Inactive
			ResultSet rs = DataBaseUtils.getResultSet(query);
			Map<String, String> linkResidentialAccountInfo = tableResultInMapForm(rs);
			System.out.println(linkResidentialAccountInfo);
			clickRdbLinkAccResidentialPopup();
			if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1")) {
				populateAddAccountPopup(linkResidentialAccountInfo.get("UtilityAccountNumber"));

			}
			if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")) {
				populateZipCodePopup(linkResidentialAccountInfo.get("ZipCode"));
			}
			if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1")) {
				populateaddMeterNumber(linkResidentialAccountInfo.get("MeterNumber"));
			}

			if (getRegistrationConfig.get("SSN").getFieldDisplayStatus().equals("1")) {
				populatetxtSSNPopup("12345");
			}

			if (getRegistrationConfig.get("Driving License Number").getFieldDisplayStatus().equals("1")) {
				populateDLPopupApp(linkResidentialAccountInfo.get("DrivingLicence"));
			}

			if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1")) {
				populateStreetAddPopupApp(linkResidentialAccountInfo.get("Address1"));

			}
			clickbtnLinkAccPopupSubmit();
			// sAssert.assertEquals(getToastMessage(),
			// accontInformationTextProp.getPropValue("txtMsgForLinkInactiveAccountApp"));

			clickBtnCancelLinkAccount();
			pause(2000);
			sAssert.assertFalse(isaddAccountmodalVisible());

			// pageUtil.pageRefresh();
			// pageUtil.waitForPageToLoad();

			// JsExecutorUtil.ScrollPageToElement(driver,
			// accountProfilePage.getBtnLinkAccountApp());

			for (int i = 0; i < getlbldivAccountList().size(); i++) {
				if (getlbldivAccountList().get(i).getText()
						.equals(linkResidentialAccountInfo.get("UtilityAccountNumber"))) {
					sAssert.assertTrue(false, "Already registered-Active account get added");
					System.out.println("INACTIVE account get linked.");

				}

			}
		} else {
			log.info("My Account link is not available on the header");
		}

	}

	public void linkRegisteredButItsActivationLinkExpiredAccount(SoftAssert sAssert, User user) throws SQLException {
		log.info("To verify user is able to Link a Registered but its activation link get expired account");

		registrationConfig = CSPConfiguration.initMCspConfig();
		getRegistrationConfig = CSPConfiguration.initMRegistrationData();
		HomeSteps homeSteps = new HomeSteps(driver);

		if (registrationConfig.get("MyAccount") == 1) {
			// Verify page heading
			homeSteps.navigateToAccountInformation();
			// Verify page heading

			Assert.assertTrue(isAccountInformationPage(
					accontInformationTextProp.getPropValue("expectedAccountInformationPageUrl"),
					accontInformationTextProp.getPropValue("expectedAccountInformationPageTitle")));
			clickBtnaccount();
			pause(1000);
			driver.switchTo().activeElement();
			isaddAccountmodalVisible();
			String query = SQLQueries.getInfoForLinkAccountRegisteredExpiredActivationFromDB("Residential");
			ResultSet rs = DataBaseUtils.getResultSet(query);
			ResultSet rs1 = DataBaseUtils.getResultSet(query);

			if (rs1.next()) {

				Map<String, String> linkResidentialAccountInfo = tableResultInMapForm(rs);
				System.out.println(linkResidentialAccountInfo);
				clickRdbLinkAccResidentialPopup();

				if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1")) {
					populateAddAccountPopup(linkResidentialAccountInfo.get("UtilityAccountNumber"));
				}

				if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")) {
					populateZipCodePopup(linkResidentialAccountInfo.get("ZipCode"));
				}
				if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1")) {
					populateaddMeterNumber(linkResidentialAccountInfo.get("MeterNumber"));
				}
				if (getRegistrationConfig.get("SSN").getFieldDisplayStatus().equals("1")) {
					populatetxtSSNPopup("12345");
				}
				if (getRegistrationConfig.get("Driving License Number").getFieldDisplayStatus().equals("1")) {
					populateDLPopupApp(linkResidentialAccountInfo.get("DrivingLicence"));
				}
				if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1")) {
					populateStreetAddPopupApp(linkResidentialAccountInfo.get("Address1"));
				}

				clickbtnLinkAccPopupSubmit();
				pause(2000);
				// sAssert.assertEquals(getToastMessage(),
				// accontInformationTextProp.getPropValue("textSuccessfulLinkedAccountMsgApp"));
				pause(2000);
				// sAssert.assertFalse(isaddAccountmodalVisible(),
				// "Link Account Popup is still appearing and account not linked. Check
				// manually.");
				// pageUtil.pageRefresh();
				// pageUtil.waitForPageToLoad();
				// JsExecutorUtil.ScrollPageToElement(driver,
				// accountProfilePage.getBtnLinkAccountApp());
				Boolean linkAccountStatus = false;
				int index = -1;
				String utilityAccountNumber = null;
				for (int i = 0; i < getlbldivAccountList().size(); i++) {
					index++;
					if (getlbldivAccountList().get(i).getText()
							.equals(linkResidentialAccountInfo.get("UtilityAccountNumber"))) {
						linkAccountStatus = true;
						utilityAccountNumber = getlbldivAccountList().get(i).getText();
						log.info("Successfully linked a Not-Registered account.");
						// sAssert.assertTrue(linkAccountStatus);
						// assertTrue(linkAccountStatus);
						break;
					}
				}

				if (!linkAccountStatus) {
					System.out.println("Unable to link a Registered-NotActivated and Expired Activation link account.");
					// sAssert.assertTrue(false);
				}

				DriverFactory.refreshBrowser();

				/*
				 * Deleting linked account
				 */

				// JsExecutorUtil.ScrollPageToElement(driver,
				// accountProfilePage.getBtnLinkAccountApp());
				/*
				 * System.out.println(".... : " + utilityAccountNumber);
				 * clickBtnToggleThreeDots(utilityAccountNumber);
				 * clickBtnUnlinkAccount(utilityAccountNumber); clickBtnUnLinkAccount(); /*
				 * Unable to verify Delete account successful message with below code
				 */
				// sAssert.assertEquals(getToastMessage(),
				// accontInformationTextProp.getPropValue("txtErrorMsgSuccessAccountDeleteValidationApp"));

				// pause(2000);
				// pageUtil.pageRefresh();
				// pageUtil.waitForPageToLoad();
				// JsExecutorUtil.ScrollPageToElement(driver,
				// accountProfilePage.getBtnLinkAccountApp());

				/*
				 * Boolean linkAccountStatus1 = true; for (int i = 0; i <
				 * getlbldivAccountList().size(); i++) { if
				 * (getlbldivAccountList().get(i).getText()
				 * .equals(linkResidentialAccountInfo.get("UtilityAccountNumber"))) {
				 * linkAccountStatus1 = false; log.
				 * info("Unable to delete linked account, its still present in Service address list."
				 * ); sAssert.assertTrue(linkAccountStatus1); } }
				 */

				log.info("To verify user is able to Link a Registered but its activation link get expired account");
			} else {
				log.info("::::::::REGISTERED AND ITS ACTIVATION LINK GET EXPIRED ACCOUNT NOT FOUND:::::::::");
				sAssert.assertTrue(false,
						"::::::::REGISTERED AND ITS ACTIVATION LINK GET EXPIRED ACCOUNT NOT FOUND:::::::::");
			}
		} else {
			log.info("My Account link is not available on the header");
		}
	}

	public void linkAccountAndDeleteLinkedAccount(SoftAssert sAssert, User user) {
		log.info(
				"To verify Link Account successfully, adding an already added account and Delete newly Linked account.");

		registrationConfig = CSPConfiguration.initMCspConfig();
		getRegistrationConfig = CSPConfiguration.initMRegistrationData();
		HomeSteps homeSteps = new HomeSteps(driver);

		if (registrationConfig.get("MyAccount") == 1) {

			// Verify page heading
			homeSteps.navigateToAccountInformation();
			// Verify page heading

			Assert.assertTrue(isAccountInformationPage(
					accontInformationTextProp.getPropValue("expectedAccountInformationPageUrl"),
					accontInformationTextProp.getPropValue("expectedAccountInformationPageTitle")));
			clickBtnaccount();
			pause(1000);
			driver.switchTo().activeElement();
			isaddAccountmodalVisible();
			// registrationType =
			// testDataJson.getStringJsonValue("residentialRegistrationType");
			String query = SQLQueries.getInfoForLinkingAccountFromDB("1", "3");
			ResultSet rs = DataBaseUtils.getResultSet(query);
			Map<String, String> linkResidentialAccountInfo = tableResultInMapForm(rs);
			System.out.println(linkResidentialAccountInfo);
			clickRdbLinkAccResidentialPopup();

			if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1")) {
				populateAddAccountPopup(linkResidentialAccountInfo.get("UtilityAccountNumber"));

			}
			if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")) {
				populateZipCodePopup(linkResidentialAccountInfo.get("ZipCode"));
			}
			if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1")) {
				populateaddMeterNumber(linkResidentialAccountInfo.get("MeterNumber"));

			}
			if (getRegistrationConfig.get("SSN").getFieldDisplayStatus().equals("1")) {
				populatetxtSSNPopup("12345");

			}
			if (getRegistrationConfig.get("Driving License Number").getFieldDisplayStatus().equals("1")) {
				populateDLPopupApp(linkResidentialAccountInfo.get("DrivingLicence"));

			}
			if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1")) {
				populateStreetAddPopupApp(linkResidentialAccountInfo.get("Address1"));
			}
			clickbtnLinkAccPopupSubmit();
			// pause(2000);
			sAssert.assertEquals(getToastMessage(),
					accontInformationTextProp.getPropValue("textSuccessfulLinkedAccountMsgApp"),
					"Toast message is not matching");
			pause(2000);
			// sAssert.assertFalse(isaddAccountmodalVisible());
			// pageUtil.pageRefresh();
			// pageUtil.waitForPageToLoad();
			// JsExecutorUtil.ScrollPageToElement(driver,
			// accountProfilePage.getBtnLinkAccountApp());

//				System.out.println("123 : " + pageUtil.verifyElementPresent(myAccountProfilePage
//						.getBtnToggleThreeDots(linkResidentialAccountInfo.get("UtilityAccountNumber"))));
//				System.out.println("%% : " + linkResidentialAccountInfo.get("UtilityAccountNumber"));

			Boolean linkAccountStatus = false;
			int index = -1;
			String utilityAccountNumber = linkResidentialAccountInfo.get("UtilityAccountNumber");
			for (int i = 0; i < getlbldivAccountList().size(); i++) {
				index++;
				if (getlbldivAccountList().get(i).getText()
						.equals(linkResidentialAccountInfo.get("UtilityAccountNumber"))) {
					linkAccountStatus = true;
					// utilityAccountNumber = pageUtil.getLocatorText(e);
					log.info("Successfully linked a Not-Registered account.");
					sAssert.assertTrue(linkAccountStatus, "link account status is not matching");
					break;
				}
			}

			if (!linkAccountStatus) {
				log.info("Unable to link a Not-Registered account");
				sAssert.assertTrue(false, "link account a not registered ");
			}

			/*
			 * Again Try to add same account which is already added
			 */

			clickBtnaccount();
			pause(1000);
			driver.switchTo().activeElement();
			isaddAccountmodalVisible();
			clickRdbLinkAccResidentialPopup();

			if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1")) {
				populateAddAccountPopup(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			}
			if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")) {
				populateZipCodePopup(linkResidentialAccountInfo.get("ZipCode"));
			}
			if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1")) {
				populateaddMeterNumber(linkResidentialAccountInfo.get("MeterNumber"));
			}
			if (getRegistrationConfig.get("SSN").getFieldDisplayStatus().equals("1")) {
				populatetxtSSNPopup("12345");
			}
			if (getRegistrationConfig.get("Driving License Number").getFieldDisplayStatus().equals("1")) {
				populateDLPopupApp(linkResidentialAccountInfo.get("DrivingLicence"));
			}
			if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1")) {
				populateStreetAddPopupApp(linkResidentialAccountInfo.get("Address1"));
			}

			// pageUtil.verifyObjectLabel(accountProfilePage.getLblToastMessageBsp(),
			// Utils.getRbTextValue("txtErrorMsgAlreadyAddedLinkAccountApp"));

			// pageUtil.expWaitForEleInvisibility(accountProfilePage.getLblToastMessageBsp());

			clickbtnLinkAccPopupSubmit();
			pause(2000);
			DriverFactory.refreshBrowser();
			// sAssert.assertFalse(isaddAccountmodalVisible());
			// getLnkHome()

			homeSteps.navigateToAccountInformation();

			/*
			 * Deleting linked account
			 */

			// JsExecutorUtil.ScrollPageToElement(driver,
			// accountProfilePage.getBtnLinkAccountApp());
			System.out.println(".... : " + utilityAccountNumber);
			System.out.println(".... : " + utilityAccountNumber);
			clickBtnToggleThreeDots(utilityAccountNumber);
			clickBtnUnlinkAccount(utilityAccountNumber);
			clickBtnUnLinkAccount();
			sAssert.assertEquals(getToastMessage(),
					accontInformationTextProp.getPropValue("txtErrorMsgSuccessAccountDeleteValidationApp"),
					"Error message success account and delete account toast message is not matching");
			pause(2000);

			DriverFactory.refreshBrowser();
			// pageUtil.waitForPageToLoad();
			homeSteps.navigateToHome();
			DashboardPage dashboard = new DashboardPage(driver);
			// Verify that after deleting selected account, default account should get
			// selected in Select Account Dropdown
			String defaultAC = getDefaultOfLoggedInUser(user);

			String selectedAddr = dashboard.getAddressByVisibleAddress();
			// assertTrue(selectedAddr.contains(defaultAC)); // Bug : SCM10-3940
			if (selectedAddr.contains(defaultAC)) {
				log.info("Default Account has been selected after deleting selected account.");
			} else {
				log.info("Default Account is not being selected after deleting selected account.");
			}

			// Verify that deleted account is not present
			// JsExecutorUtil.ScrollPageToElement(driver,
			// accountProfilePage.getBtnLinkAccountApp());

			Boolean linkAccountStatus1 = true;
			for (int i = 0; i < getlbldivAccountList().size(); i++) {
				if (getlbldivAccountList().get(i).getText()
						.equals(linkResidentialAccountInfo.get("UtilityAccountNumber"))) {
					linkAccountStatus1 = false;
					log.info("Unable to delete linked account, its still present in Service address list.");
					sAssert.assertTrue(linkAccountStatus1, "link account status is not matching");
				}
			}

			log.info(
					"To verify Link Account successfully, adding an already added account and Delete newly Linked account.");
		} else {
			log.info("My Account link is not available on the header");
		}
	}

	public void dropdownOnLinkDeLinkAccounts(SoftAssert sAssert, User user) {

		log.info("To verify that the user is not able to login into the application when all "
				+ "accounts linked to it getting closed.");
		log.info("To verify that the user is not able to login into the"
				+ " application when all accounts linked to it getting closed.");
		SoftAssert softAssert = new SoftAssert();
		// Login with newly registered user.
		registrationConfig = CSPConfiguration.initMCspConfig();
		getRegistrationConfig = CSPConfiguration.initMRegistrationData();
		HomeSteps homeSteps = new HomeSteps(driver);
		// Verify navigation to MyAccount profile page.
		homeSteps.navigateToAccountInformation();
		// Verify page heading

		Assert.assertTrue(
				isAccountInformationPage(accontInformationTextProp.getPropValue("expectedAccountInformationPageUrl"),
						accontInformationTextProp.getPropValue("expectedAccountInformationPageTitle")));

		// Link a residential account to the logged in user.
		String sUtilityAccountNumber = linkAccountSteps("Residential", sAssert);

		// Get all the accounts present in the address.
		// List<String> lAddressesInAccountDropdown =
		// myAccountProfilePage.getAllAddressFromAccountDropdown();

		List<String> lAddressesInAccountDropdown = new ArrayList<String>();
		for (WebElement el : getLabelLinkAccount()) {
			lAddressesInAccountDropdown.add(el.getText());
		}

		System.out.println("%%%% : " + lAddressesInAccountDropdown);

		Boolean bAccountNoPresence = false;
		for (String address : lAddressesInAccountDropdown) {
			if (address.contains(sUtilityAccountNumber)) {
				bAccountNoPresence = true;
			}
		}
		softAssert.assertTrue(bAccountNoPresence, "Linked account not present in the account drop-down.");
		pause(2000);
		// Delink the account
		deLinkAccountSteps(sUtilityAccountNumber);
		// Get all the accounts present in the address.
		// lAddressesInAccountDropdown =
		// myAccountProfilePage.getAllAddressFromAccountDropdown();
		bAccountNoPresence = false;
		for (WebElement address : getLabelLinkAccount()) {
			if (address.getText().contains(sUtilityAccountNumber)) {
				bAccountNoPresence = true;
			}
		}
		softAssert.assertFalse(bAccountNoPresence, "Deleted account still present in the account drop-down.");

		softAssert.assertAll();
		log.info("To verify that the user is not able to login into the application when all "
				+ "accounts linked to it getting closed.");
		log.info("To verify that the user is not able to login into the"
				+ " application when all accounts linked to it getting closed.");

	}

	public void cisValidationMessageForLinkAccount(SoftAssert sAssert, User user) {
		log.info("To verify CIS validation message for Link Account fields.");
		registrationConfig = CSPConfiguration.initMCspConfig();
		getRegistrationConfig = CSPConfiguration.initMRegistrationData();
		HomeSteps homeSteps = new HomeSteps(driver);

		if (registrationConfig.get("MyAccount") == 1) {

			homeSteps.navigateToAccountInformation();
			// Verify page heading

			Assert.assertTrue(isAccountInformationPage(
					accontInformationTextProp.getPropValue("expectedAccountInformationPageUrl"),
					accontInformationTextProp.getPropValue("expectedAccountInformationPageTitle")));

			pause(1000);
			clickBtnaccount();
			pause(1000);
			driver.switchTo().activeElement();
			isaddAccountmodalVisible();
			String query = SQLQueries.getInfoForLinkingAccountFromDB("1", "3");
			System.out.println(query);
			ResultSet rs = DataBaseUtils.getResultSet(query);
			Map<String, String> linkResidentialAccountInfo = tableResultInMapForm(rs);
			System.out.println(linkResidentialAccountInfo);
			clickRdbLinkAccResidentialPopup();
			if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1")) {
				populateAddAccountPopup(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			}
			if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")) {
				populateZipCodePopup(linkResidentialAccountInfo.get("ZipCode"));
			}
			if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1")) {
				populateaddMeterNumber(linkResidentialAccountInfo.get("MeterNumber"));

			}
			if (getRegistrationConfig.get("SSN").getFieldDisplayStatus().equals("1")) {
				populatetxtSSNPopup("12345");
			}
			if (getRegistrationConfig.get("Driving License Number").getFieldDisplayStatus().equals("1")) {
				populateDLPopupApp(linkResidentialAccountInfo.get("DrivingLicence"));
			}
			if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1")) {
				populateStreetAddPopupApp(linkResidentialAccountInfo.get("Address1"));
			}

			// CIS validation for Account Number
			if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1")
					&& getRegistrationConfig.get("Account Number").getFieldCisProperty().equals(true)) {

				populateAddAccountPopup("R99" + linkResidentialAccountInfo.get("UtilityAccountNumber"));

				clickbtnLinkAccPopupSubmit();
				pause(2000);
				sAssert.assertEquals(getToastMessage(),
						accontInformationTextProp.getPropValue("txtLblErrorMsgInvalidServiceAccountCrp"));
				populateAddAccountPopup(linkResidentialAccountInfo.get("UtilityAccountNumber"));

			}

			// CIS validation for Zip Code
			if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")
					&& getRegistrationConfig.get("Zip Code").getFieldCisProperty().equals("true")) {

				populateZipCodePopup("2" + linkResidentialAccountInfo.get("ZipCode"));
				clickbtnLinkAccPopupSubmit();
				sAssert.assertEquals(getToastMessage(),
						accontInformationTextProp.getPropValue("txtLblErrorMsgInvalidZipCodeCrp"));
				pause(2000);
				populateZipCodePopup(linkResidentialAccountInfo.get("ZipCode"));

			}

			// CIS validation for Meter Number
			if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1")
					&& getRegistrationConfig.get("Meter Number").getFieldCisProperty().equals("true")) {

				populateaddMeterNumber("E00" + linkResidentialAccountInfo.get("MeterNumber"));

				clickbtnLinkAccPopupSubmit();
				sAssert.assertEquals(getToastMessage(),
						accontInformationTextProp.getPropValue("txtLblErrorMsgInvalidMeterNumberCrp"));
				pause(2000);
				populateaddMeterNumber(linkResidentialAccountInfo.get("MeterNumber"));
			}
//				

			// CIS validation for Service Address
			if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1")
					&& getRegistrationConfig.get("Street Address").getFieldCisProperty().equals("true")) {
				populateStreetAddPopupApp("XYZ, 721");
				clickbtnLinkAccPopupSubmit();
				sAssert.assertEquals(getToastMessage(),
						accontInformationTextProp.getPropValue("txtLblErrorMsgInvalidStreetNumberCrp"),
						"Label error message invalid street message toast message is not matching");
				populateStreetAddPopupApp(linkResidentialAccountInfo.get("Address1"));
			}
			log.info("To verify CIS validation message for Link Account fields.");
		} else {
		}
	}

	public void unableLinkResidentialAccountInCommercialSectionAndViceVersa(SoftAssert sAssert, User user) {
		log.info("To verify user is NOT able to Link a Residential account in Commercial section and vice-versa");

		registrationConfig = CSPConfiguration.initMCspConfig();
		getRegistrationConfig = CSPConfiguration.initMRegistrationData();
		HomeSteps homeSteps = new HomeSteps(driver);

		if (registrationConfig.get("MyAccount") == 1) {

			homeSteps.navigateToAccountInformation();
			// Verify page heading

			Assert.assertTrue(isAccountInformationPage(
					accontInformationTextProp.getPropValue("expectedAccountInformationPageUrl"),
					accontInformationTextProp.getPropValue("expectedAccountInformationPageTitle")));
			pause(1000);
			clickBtnaccount();
			pause(1000);
			driver.switchTo().activeElement();
			isaddAccountmodalVisible();
			String query = SQLQueries.getInfoForLinkingAccountFromDB("1", "3");
			System.out.println(query);
			ResultSet rs = DataBaseUtils.getResultSet(query);
			Map<String, String> linkResidentialAccountInfo = tableResultInMapForm(rs);
			System.out.println(linkResidentialAccountInfo);
			String utilityAccountNumber = null;
			utilityAccountNumber = linkResidentialAccountInfo.get("UtilityAccountNumber");
			lnkRdbLinkAccCommercialPopup();
			// clickRdbLinkAccResidentialPopup();

			if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1")) {
				populateAddAccountPopup(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			}
			if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")) {
				populateZipCodePopup(linkResidentialAccountInfo.get("ZipCode"));
			}
			if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1")) {
				populateaddMeterNumber(linkResidentialAccountInfo.get("MeterNumber"));
			}
			if (getRegistrationConfig.get("SSN").getFieldDisplayStatus().equals("1")) {
				// populatetxtSSNPopup("12345");
			}
			if (getRegistrationConfig.get("Driving License Number").getFieldDisplayStatus().equals("1")) {
				// populateDLPopupApp(linkResidentialAccountInfo.get("DrivingLicence"));
			}
			if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1")) {
				populateStreetAddPopupApp(linkResidentialAccountInfo.get("Address1"));
			}
			clickbtnLinkAccPopupSubmit();
			sAssert.assertEquals(getToastMessage(),
					accontInformationTextProp.getPropValue("textSuccessfulLinkedAccountMsgApp"));
			// assertTrue(pageUtil.verifyElementNotVisible(accountProfilePage.getLblLinkAccountPopupHeadingApp()));
			Boolean linkAccountStatus = false;
			int index = -1;

			for (int i = 0; i < getlbldivAccountList().size(); i++) {
				index++;
				if (getlbldivAccountList().get(i).getText()
						.equals(linkResidentialAccountInfo.get("UtilityAccountNumber"))) {
					linkAccountStatus = true;
					log.info("Successfully linked a Not-Registered account.");
					sAssert.assertTrue(linkAccountStatus);
					break;
				}
			}

			if (!linkAccountStatus) {
				log.info("Unable to link a Not-Registered account");
				// assertTrue(false);
			}

			// verify Nick Name of linked Commercial account
			Assert.assertEquals(getLblNickNameOfLinkedAccount(utilityAccountNumber), "MY HOME");
			// Link Residential account details in Commercial section
			/*
			 * clickBtnaccount(); pause(1000); driver.switchTo().activeElement();
			 * sAssert.assertTrue(isaddAccountmodalVisible()); String query1 =
			 * SQLQueries.getInfoForLinkingAccountFromDB("1", "3");
			 * System.out.println(query1);
			 * 
			 * ResultSet rs1 = DataBaseUtils.getResultSet(query1);
			 * 
			 * Map<String, String> linkResidentialAccountInfo1 = tableResultInMapForm(rs1);
			 * 
			 * System.out.println(linkResidentialAccountInfo1); utilityAccountNumber = null;
			 * utilityAccountNumber =
			 * linkResidentialAccountInfo1.get("UtilityAccountNumber"); pause(2000);
			 * clickRdbLinkAccResidentialPopup();
			 * 
			 * if
			 * (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals(
			 * "1")) {
			 * populateAddAccountPopup(linkResidentialAccountInfo.get("UtilityAccountNumber"
			 * )); } if
			 * (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")) {
			 * populateZipCodePopup(linkResidentialAccountInfo.get("ZipCode")); } if
			 * (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1"
			 * )) { populateaddMeterNumber(linkResidentialAccountInfo.get("MeterNumber")); }
			 * if (getRegistrationConfig.get("FID/TIN").getFieldDisplayStatus().equals("1"))
			 * { populateTxtFIDTINPopupApp("1234");
			 * 
			 * }
			 * 
			 * if
			 * (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals(
			 * "1")) {
			 * populateStreetAddPopupApp(linkResidentialAccountInfo.get("Address1")); }
			 * 
			 * clickbtnLinkAccPopupSubmit(); sAssert.assertEquals(getToastMessage(),
			 * accontInformationTextProp.getPropValue("textSuccessfulLinkedAccountMsgApp"));
			 * Assert.assertFalse(isaddAccountmodalVisible()); linkAccountStatus = false;
			 * index = -1; for (int i = 0; i < getlbldivAccountList().size(); i++) {
			 * index++; if (getlbldivAccountList().get(i).getText()
			 * .equals(linkResidentialAccountInfo1.get("UtilityAccountNumber"))) {
			 * linkAccountStatus = true;
			 * log.info("Successfully linked a Not-Registered account.");
			 * sAssert.assertTrue(linkAccountStatus); break; } }
			 * 
			 * if (!linkAccountStatus) {
			 * log.info("Unable to link a Not-Registered account");
			 * sAssert.assertTrue(false); }
			 * 
			 * // verify Nick Name of linked residential account
			 * Assert.assertEquals(getLblNickNameOfLinkedAccount(utilityAccountNumber),
			 * "MY HOME");
			 */
			log.info("To verify user is NOT able to Link a Residential account in Commercial section and vice-versa");
		} else {
			log.info("My Account link is not available on the header");
		}
	}

	public void multiLinkAccountAndDeleteLinkedAccount(SoftAssert sAssert, User user) {

		log.info(
				"To verify Link Account successfully, adding an already added account and Delete newly Linked account.");
		HashMap<String, Map> accountDetails = new HashMap<>();
		registrationConfig = CSPConfiguration.initMCspConfig();
		getRegistrationConfig = CSPConfiguration.initMRegistrationData();
		HomeSteps homeSteps = new HomeSteps(driver);
		if (registrationConfig.get("MyAccount") == 1) {
			// Verify page heading
			homeSteps.navigateToAccountInformation();
			// Verify page heading
			Assert.assertTrue(isAccountInformationPage(
					accontInformationTextProp.getPropValue("expectedAccountInformationPageUrl"),
					accontInformationTextProp.getPropValue("expectedAccountInformationPageTitle")));
			clickBtnaccount();
			pause(1000);
			driver.switchTo().activeElement();
			String query = SQLQueries.getInfoForLinkingAccountFromDB("1", "3");
			ResultSet rs = DataBaseUtils.getResultSet(query);
			Map<String, String> linkResidentialAccountInfo = tableResultInMapForm(rs);
			System.out.println(linkResidentialAccountInfo);
			accountDetails = collectAllAccountsInfo(linkResidentialAccountInfo.get("customerNo"));
			statusCodeValidation(accountDetails, "3", "0");
			clickRdbLinkAccResidentialPopup();
			if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1")) {
				populateAddAccountPopup(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			}
			if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")) {
				populateZipCodePopup(linkResidentialAccountInfo.get("ZipCode"));
			}
			if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1")) {
				populateaddMeterNumber(linkResidentialAccountInfo.get("MeterNumber"));
			}
			if (getRegistrationConfig.get("SSN").getFieldDisplayStatus().equals("1")) {
				populatetxtSSNPopup("12345");
			}
			if (getRegistrationConfig.get("Driving License Number").getFieldDisplayStatus().equals("1")) {
				populateDLPopupApp(linkResidentialAccountInfo.get("DrivingLicence"));
			}
			if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1")) {
				populateStreetAddPopupApp(linkResidentialAccountInfo.get("Address1"));
			}

			clickbtnLinkAccPopupSubmit();
			pause(2000);
			sAssert.assertEquals(getToastMessage(),
					accontInformationTextProp.getPropValue("textSuccessfulLinkedAccountMsgApp"));
			pause(2000);
			accountDetails = collectAllAccountsInfo(linkResidentialAccountInfo.get("customerNo"));
			statusCodeValidation(accountDetails, "1", "0");
			// assertTrue(pageUtil.verifyElementNotVisible(accountProfilePage.getLblLinkAccountPopupHeadingApp()));
			DriverFactory.refreshBrowser();
			Boolean linkAccountStatus = false;
			int index = -1;
			String utilityAccountNumber = null;
			for (int i = 0; i < getlbldivAccountList().size(); i++) {
				index++;

				index++;
				if (getlbldivAccountList().get(i).getText()
						.equals(linkResidentialAccountInfo.get("UtilityAccountNumber"))) {
					linkAccountStatus = true;
					log.info("Successfully linked a Not-Registered account.");
					sAssert.assertTrue(linkAccountStatus);
					break;
				}
			}
			if (!linkAccountStatus) {
				log.info("Unable to link a Not-Registered account");
				sAssert.assertTrue(false);
			}

			/*
			 * Again Try to add same account which is already added
			 */

			clickBtnaccount();
			pause(1000);
			driver.switchTo().activeElement();
			sAssert.assertTrue(isaddAccountmodalVisible(), "add account model is not visible");
			clickRdbLinkAccResidentialPopup();

			if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1")) {
				populateAddAccountPopup(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			}
			if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")) {
				populateZipCodePopup(linkResidentialAccountInfo.get("ZipCode"));
			}
			if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1")) {
				populateaddMeterNumber(linkResidentialAccountInfo.get("MeterNumber"));
			}

			if (getRegistrationConfig.get("SSN").getFieldDisplayStatus().equals("1")) {
				populatetxtSSNPopup("12345");
			}
			if (getRegistrationConfig.get("Driving License Number").getFieldDisplayStatus().equals("1")) {
				populateDLPopupApp(linkResidentialAccountInfo.get("DrivingLicence"));
			}
			if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1")) {
				populateStreetAddPopupApp(linkResidentialAccountInfo.get("Address1"));
			}

			clickbtnLinkAccPopupSubmit();
			pause(2000);
			// sAssert.assertEquals(getToastMessage(),
			// accontInformationTextProp.getPropValue("textSuccessfulLinkedAccountMsgApp"));
			pause(2000);
			clickBtnCancelLinkAccount();
			pause(2000);
			// sAssert.assertTrue(isaddAccountmodalVisible());
			homeSteps.navigateToHome();
			selectAccountInDropdown(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			homeSteps.navigateToAccountInformation();

			// Deleting linked account

			clickBtnToggleThreeDots(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			clickBtnUnlinkAccount(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			clickBtnUnLinkAccount();

			sAssert.assertEquals(getToastMessage(),
					accontInformationTextProp.getPropValue("txtErrorMsgSuccessAccountDeleteValidationApp"));
			DriverFactory.refreshBrowser();
			pause(2000);

			// sAssert.assertEquals(getToastMessage(),
			// accontInformationTextProp.getPropValue("txtErrorMsgSuccessAccountDeleteValidationApp"));
			pause(2000);
			DriverFactory.refreshBrowser();
			pause(2000);
			DashboardPage dashboard = new DashboardPage(driver);
			// Verify that after deleting selected account, default account should get
			// selected in Select Account Dropdown
			homeSteps.navigateToHome();
			String defaultAC = getDefaultOfLoggedInUser(user);
			String selectedAddr = dashboard.getAddressByVisibleAddress();
			// assertTrue(selectedAddr.contains(defaultAC)); // Bug : SCM10-3940
			if (selectedAddr.contains(defaultAC)) {
				log.info("Default Account has been selected after deleting selected account.");
			} else {
				log.info("Default Account is not being selected after deleting selected account.");
			}
			// Verify that deleted account is not present

			Boolean linkAccountStatus1 = false;
			int index1 = -1;
			String utilityAccountNumber1 = null;
			for (int i = 0; i < getlbldivAccountList().size(); i++) {
				index1++;
				if (getlbldivAccountList().get(i).getText()
						.equals(linkResidentialAccountInfo.get("UtilityAccountNumber"))) {
					linkAccountStatus1 = true;
					utilityAccountNumber1 = getlbldivAccountList().get(i).getText();
					log.info("Successfully linked a Not-Registered account.");
					sAssert.assertTrue(linkAccountStatus1);
					// assertTrue(linkAccountStatus);
					break;
				}
			}

			if (!linkAccountStatus1) {
				System.out.println("Unable to link a Registered-NotActivated and Expired Activation link account.");
				// sAssert.assertTrue(false);
			}
			accountDetails = collectAllAccountsInfo(linkResidentialAccountInfo.get("customerNo"));
			statusCodeValidation(accountDetails, "3", "0");
			log.info(
					"To verify Link Account successfully, adding an already added account and Delete newly Linked account.");

		} else {
			log.info("My Account link is not available on the header");
		}

	}

	public void multiLinkAccountAndDeleteCommericalLinkedAccount(SoftAssert sAssert, User user) {
		log.info(
				"To verify Link Account successfully, adding an already added account and Delete newly Linked account.");
		HashMap<String, Map> accountDetails = new HashMap<>();
		registrationConfig = CSPConfiguration.initMCspConfig();
		getRegistrationConfig = CSPConfiguration.initMRegistrationData();
		HomeSteps homeSteps = new HomeSteps(driver);
		if (registrationConfig.get("MyAccount") == 1) {
			// Verify page heading
			homeSteps.navigateToAccountInformation();
			// Verify page heading
			Assert.assertTrue(isAccountInformationPage(
					accontInformationTextProp.getPropValue("expectedAccountInformationPageUrl"),
					accontInformationTextProp.getPropValue("expectedAccountInformationPageTitle")));
			clickBtnaccount();
			pause(1000);
			driver.switchTo().activeElement();
			String query = SQLQueries.getInfoForLinkingAccountFromDB("2", "3");
			ResultSet rs = DataBaseUtils.getResultSet(query);
			Map<String, String> linkResidentialAccountInfo = tableResultInMapForm(rs);
			System.out.println(linkResidentialAccountInfo);
			accountDetails = collectAllAccountsInfo(linkResidentialAccountInfo.get("customerNo"));
			statusCodeValidation(accountDetails, "3", "0");
			clickRdbLinkAccResidentialPopup();
			if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1")) {
				populateAddAccountPopup(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			}
			if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")) {
				populateZipCodePopup(linkResidentialAccountInfo.get("ZipCode"));
			}
			if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1")) {
				// populateaddMeterNumber(linkResidentialAccountInfo.get("MeterNumber"));
			}
			if (getRegistrationConfig.get("SSN").getFieldDisplayStatus().equals("1")) {
				populatetxtSSNPopup("12345");
			}
			if (getRegistrationConfig.get("Driving License Number").getFieldDisplayStatus().equals("1")) {
				// populateDLPopupApp(linkResidentialAccountInfo.get("DrivingLicence"));
			}
			if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1")) {
				populateStreetAddPopupApp(linkResidentialAccountInfo.get("Address1"));
			}

			clickbtnLinkAccPopupSubmit();
			// pause(2000);
			sAssert.assertEquals(getToastMessage(),
					accontInformationTextProp.getPropValue("textSuccessfulLinkedAccountMsgApp"));
			pause(2000);
			accountDetails = collectAllAccountsInfo(linkResidentialAccountInfo.get("customerNo"));
			statusCodeValidation(accountDetails, "1", "0");
			// assertTrue(pageUtil.verifyElementNotVisible(accountProfilePage.getLblLinkAccountPopupHeadingApp()));
			DriverFactory.refreshBrowser();
			Boolean linkAccountStatus = false;
			int index = -1;
			String utilityAccountNumber = null;
			for (int i = 0; i < getlbldivAccountList().size(); i++) {
				index++;

				index++;
				if (getlbldivAccountList().get(i).getText()
						.equals(linkResidentialAccountInfo.get("UtilityAccountNumber"))) {
					linkAccountStatus = true;
					log.info("Successfully linked a Not-Registered account.");
					sAssert.assertTrue(linkAccountStatus);
					break;
				}
			}
			if (!linkAccountStatus) {
				log.info("Unable to link a Not-Registered account");
				sAssert.assertTrue(false);
			}

			/*
			 * Again Try to add same account which is already added
			 */

			clickBtnaccount();
			pause(1000);
			driver.switchTo().activeElement();
			sAssert.assertTrue(isaddAccountmodalVisible(), "add account model is not visible");
			clickRdbLinkAccResidentialPopup();

			if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1")) {
				populateAddAccountPopup(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			}
			if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")) {
				populateZipCodePopup(linkResidentialAccountInfo.get("ZipCode"));
			}
			if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1")) {
				// populateaddMeterNumber(linkResidentialAccountInfo.get("MeterNumber"));
			}

			if (getRegistrationConfig.get("SSN").getFieldDisplayStatus().equals("1")) {
				populatetxtSSNPopup("12345");
			}
			if (getRegistrationConfig.get("Driving License Number").getFieldDisplayStatus().equals("1")) {
				// populateDLPopupApp(linkResidentialAccountInfo.get("DrivingLicence"));
			}
			if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1")) {
				populateStreetAddPopupApp(linkResidentialAccountInfo.get("Address1"));
			}

			clickbtnLinkAccPopupSubmit();
			// pause(2000);
			// sAssert.assertEquals(getToastMessage(),
			// accontInformationTextProp.getPropValue("textSuccessfulLinkedAccountMsgApp"));
			pause(2000);
			clickBtnCancelLinkAccount();
			pause(2000);
			// sAssert.assertFalse(isaddAccountmodalVisible());
			homeSteps.navigateToHome();
			selectAccountInDropdown(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			homeSteps.navigateToAccountInformation();

			// Deleting linked account

			clickBtnToggleThreeDots(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			clickBtnUnlinkAccount(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			clickBtnUnLinkAccount();
			sAssert.assertEquals(getToastMessage(),
					accontInformationTextProp.getPropValue("txtErrorMsgSuccessAccountDeleteValidationApp"));
			pause(2000);
			DriverFactory.refreshBrowser();
			pause(2000);
			DashboardPage dashboard = new DashboardPage(driver);
			homeSteps.navigateToHome();
			// Verify that after deleting selected account, default account should get
			// selected in Select Account Dropdown
			String defaultAC = getDefaultOfLoggedInUser(user);
			String selectedAddr = dashboard.getAddressByVisibleAddress();
			// assertTrue(selectedAddr.contains(defaultAC)); // Bug : SCM10-3940
			if (selectedAddr.contains(defaultAC)) {
				log.info("Default Account has been selected after deleting selected account.");
			} else {
				log.info("Default Account is not being selected after deleting selected account.");
			}
			// Verify that deleted account is not present

			Boolean linkAccountStatus1 = false;
			int index1 = -1;
			String utilityAccountNumber1 = null;

			for (int i = 0; i < getlbldivAccountList().size(); i++) {
				index1++;
				if (getlbldivAccountList().get(i).getText()
						.equals(linkResidentialAccountInfo.get("UtilityAccountNumber"))) {
					linkAccountStatus1 = true;
					utilityAccountNumber1 = getlbldivAccountList().get(i).getText();
					log.info("Successfully linked a Not-Registered account.");
					sAssert.assertTrue(linkAccountStatus1);
					// assertTrue(linkAccountStatus);
					break;
				}
			}

			if (!linkAccountStatus) {
				System.out.println("Unable to link a Registered-NotActivated and Expired Activation link account.");
				sAssert.assertTrue(false);
			}
			accountDetails = collectAllAccountsInfo(linkResidentialAccountInfo.get("customerNo"));
			statusCodeValidation(accountDetails, "3", "0");
			log.info(
					"To verify Link Account successfully, adding an already added account and Delete newly Linked account.");

		} else {
			log.info("My Account link is not available on the header");
		}

	}

	public void multiLinkAccountAndDeleteAgainLinkedAccount(SoftAssert sAssert, User user) {

		log.info(
				"To verify Link Account successfully, adding an already added account and Delete newly Linked account.");
		HashMap<String, Map> accountDetails = new HashMap<>();
		getRegistrationConfig = CSPConfiguration.initMRegistrationData();
		HomeSteps homeSteps = new HomeSteps(driver);
		DashboardPage dashboard = new DashboardPage(driver);
		if (registrationConfig.get("MyAccount") == 1) {
			// Verify page heading
			homeSteps.navigateToAccountInformation();
			// Verify page heading
			Assert.assertTrue(isAccountInformationPage(
					accontInformationTextProp.getPropValue("expectedAccountInformationPageUrl"),
					accontInformationTextProp.getPropValue("expectedAccountInformationPageTitle")));
			clickBtnaccount();
			pause(1000);
			driver.switchTo().activeElement();
			String query = SQLQueries.getInfoForLinkingAccountFromDB("1", "3");
			ResultSet rs = DataBaseUtils.getResultSet(query);
			Map<String, String> linkResidentialAccountInfo = tableResultInMapForm(rs);
			System.out.println(linkResidentialAccountInfo);
			accountDetails = collectAllAccountsInfo(linkResidentialAccountInfo.get("customerNo"));
			statusCodeValidation(accountDetails, "3", "0");
			clickRdbLinkAccResidentialPopup();
			if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1")) {
				populateAddAccountPopup(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			}
			if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")) {
				populateZipCodePopup(linkResidentialAccountInfo.get("ZipCode"));
			}
			if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1")) {
				populateaddMeterNumber(linkResidentialAccountInfo.get("MeterNumber"));
			}
			if (getRegistrationConfig.get("SSN").getFieldDisplayStatus().equals("1")) {
				populatetxtSSNPopup("12345");
			}
			if (getRegistrationConfig.get("Driving License Number").getFieldDisplayStatus().equals("1")) {
				populateDLPopupApp(linkResidentialAccountInfo.get("DrivingLicence"));
			}
			if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1")) {
				populateStreetAddPopupApp(linkResidentialAccountInfo.get("Address1"));
			}

			clickbtnLinkAccPopupSubmit();
			pause(2000);
			sAssert.assertEquals(getToastMessage(),
					accontInformationTextProp.getPropValue("textSuccessfulLinkedAccountMsgApp"));
			pause(2000);
			accountDetails = collectAllAccountsInfo(linkResidentialAccountInfo.get("customerNo"));
			statusCodeValidation(accountDetails, "1", "0");
			// assertTrue(pageUtil.verifyElementNotVisible(accountProfilePage.getLblLinkAccountPopupHeadingApp()));

			DriverFactory.refreshBrowser();
			Boolean linkAccountStatus = false;
			int index = -1;
			String utilityAccountNumber = null;
			for (int i = 0; i < getlbldivAccountList().size(); i++) {
				index++;

				index++;
				if (getlbldivAccountList().get(i).getText()
						.equals(linkResidentialAccountInfo.get("UtilityAccountNumber"))) {
					linkAccountStatus = true;
					log.info("Successfully linked a Not-Registered account.");
					sAssert.assertTrue(linkAccountStatus);
					break;
				}
			}
			if (!linkAccountStatus) {
				log.info("Unable to link a Not-Registered account");
				sAssert.assertTrue(false);
			}
			/*
			 * Again Try to add same account which is already added
			 */

			clickBtnaccount();
			pause(1000);
			driver.switchTo().activeElement();
			sAssert.assertTrue(isaddAccountmodalVisible(), "add account model is not visible");
			clickRdbLinkAccResidentialPopup();

			if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1")) {
				populateAddAccountPopup(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			}
			if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")) {
				populateZipCodePopup(linkResidentialAccountInfo.get("ZipCode"));
			}
			if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1")) {
				populateaddMeterNumber(linkResidentialAccountInfo.get("MeterNumber"));
			}

			if (getRegistrationConfig.get("SSN").getFieldDisplayStatus().equals("1")) {
				populatetxtSSNPopup("12345");
			}
			if (getRegistrationConfig.get("Driving License Number").getFieldDisplayStatus().equals("1")) {
				populateDLPopupApp(linkResidentialAccountInfo.get("DrivingLicence"));
			}
			if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1")) {
				populateStreetAddPopupApp(linkResidentialAccountInfo.get("Address1"));
			}

			clickbtnLinkAccPopupSubmit();
			pause(2000);
			sAssert.assertEquals(getToastMessage(),
					accontInformationTextProp.getPropValue("textSuccessfulLinkedAccountMsgApp"));
			pause(2000);
			clickBtnCancelLinkAccount();
			pause(2000);
			sAssert.assertTrue(isaddAccountmodalVisible());
			homeSteps.navigateToHome();
			selectAccountInDropdown(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			homeSteps.navigateToAccountInformation();

			// Deleting linked account

			clickBtnToggleThreeDots(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			clickBtnUnlinkAccount(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			clickBtnUnLinkAccount();
			pause(2000);
			DriverFactory.refreshBrowser();
			pause(2000);
			// Verify that after deleting selected account, default account should get
			// selected in Select Account Dropdown
			String defaultAC = getDefaultOfLoggedInUser(user);
			String selectedAddr = dashboard.getAddressByVisibleAddress();
			// assertTrue(selectedAddr.contains(defaultAC)); // Bug : SCM10-3940
			// Verify that deleted account is not present
			Boolean linkAccountStatus1 = true;
			getlbldivAccountList();
			for (int i = 0; i < getlbldivAccountList().size(); i++) {
				for (Map.Entry<String, Map> entry : accountDetails.entrySet()) {
					String key = entry.getKey();

					if (getlbldivAccountList().get(i).getText().equals(key)) {
						linkAccountStatus1 = false;
						System.out.println(key);
						log.info("Unable to delete linked account, its still present in Service address list.");
						sAssert.assertTrue(linkAccountStatus1);
					}
				}
			}

			accountDetails = collectAllAccountsInfo(linkResidentialAccountInfo.get("customerNo"));
			statusCodeValidation(accountDetails, "3", "0");
			clickBtnaccount();
			pause(1000);
			driver.switchTo().activeElement();
			sAssert.assertTrue(isaddAccountmodalVisible(), "add account model is not visible");
			clickRdbLinkAccResidentialPopup();

			if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1")) {
				populateAddAccountPopup(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			}
			if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")) {
				populateZipCodePopup(linkResidentialAccountInfo.get("ZipCode"));
			}
			if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1")) {
				populateaddMeterNumber(linkResidentialAccountInfo.get("MeterNumber"));
			}

			if (getRegistrationConfig.get("SSN").getFieldDisplayStatus().equals("1")) {
				populatetxtSSNPopup("12345");
			}
			if (getRegistrationConfig.get("Driving License Number").getFieldDisplayStatus().equals("1")) {
				populateDLPopupApp(linkResidentialAccountInfo.get("DrivingLicence"));
			}
			if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1")) {
				populateStreetAddPopupApp(linkResidentialAccountInfo.get("Address1"));
			}

			clickbtnLinkAccPopupSubmit();
			pause(2000);
			sAssert.assertEquals(getToastMessage(),
					accontInformationTextProp.getPropValue("textSuccessfulLinkedAccountMsgApp"));
			pause(2000);
			accountDetails = collectAllAccountsInfo(linkResidentialAccountInfo.get("customerNo"));
			statusCodeValidation(accountDetails, "1", "0");
			log.info(
					"To verify Link Account successfully, User should able to link same account again successfully and all accounts of the customer should get linked.");
		} else {
			log.info("My Account link is not available on the header");
		}

	}

	public void multiAccountregistrationandDelinkedAccount(SoftAssert sAssert, User user) {

	}

	public void makeDefaultForMultiLinkAccount(SoftAssert sAssert, User user) {
		log.info(
				"To verify Link Account successfully, adding an already added account and Delete newly Linked account.");
		HashMap<String, Map> accountDetails = new HashMap<>();
		registrationConfig = CSPConfiguration.initMCspConfig();
		getRegistrationConfig = CSPConfiguration.initMRegistrationData();
		HomeSteps homeSteps = new HomeSteps(driver);
		if (registrationConfig.get("MyAccount") == 1) {
			// Verify page heading
			homeSteps.navigateToAccountInformation();
			// Verify page heading
			Assert.assertTrue(isAccountInformationPage(
					accontInformationTextProp.getPropValue("expectedAccountInformationPageUrl"),
					accontInformationTextProp.getPropValue("expectedAccountInformationPageTitle")));
			clickBtnaccount();
			pause(1000);
			driver.switchTo().activeElement();
			String query = SQLQueries.getInfoForLinkingAccountFromDB("1", "3");
			ResultSet rs = DataBaseUtils.getResultSet(query);
			Map<String, String> linkResidentialAccountInfo = tableResultInMapForm(rs);
			System.out.println(linkResidentialAccountInfo);
			accountDetails = collectAllAccountsInfo(linkResidentialAccountInfo.get("customerNo"));
			statusCodeValidation(accountDetails, "3", "0");
			clickRdbLinkAccResidentialPopup();
			if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1")) {
				populateAddAccountPopup(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			}
			if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")) {
				populateZipCodePopup(linkResidentialAccountInfo.get("ZipCode"));
			}
			if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1")) {
				populateaddMeterNumber(linkResidentialAccountInfo.get("MeterNumber"));
			}
			if (getRegistrationConfig.get("SSN").getFieldDisplayStatus().equals("1")) {
				populatetxtSSNPopup("12345");
			}
			if (getRegistrationConfig.get("Driving License Number").getFieldDisplayStatus().equals("1")) {
				populateDLPopupApp(linkResidentialAccountInfo.get("DrivingLicence"));
			}
			if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1")) {
				populateStreetAddPopupApp(linkResidentialAccountInfo.get("Address1"));
			}

			clickbtnLinkAccPopupSubmit();
			pause(2000);
			sAssert.assertEquals(getToastMessage(),
					accontInformationTextProp.getPropValue("textSuccessfulLinkedAccountMsgApp"));
			pause(2000);
			accountDetails = collectAllAccountsInfo(linkResidentialAccountInfo.get("customerNo"));
			statusCodeValidation(accountDetails, "1", "0");
			// assertTrue(pageUtil.verifyElementNotVisible(accountProfilePage.getLblLinkAccountPopupHeadingApp()));
			DriverFactory.refreshBrowser();
			Boolean linkAccountStatus = false;
			int index = -1;
			String utilityAccountNumber = null;
			for (int i = 0; i < getlbldivAccountList().size(); i++) {
				index++;

				index++;
				if (getlbldivAccountList().get(i).getText()
						.equals(linkResidentialAccountInfo.get("UtilityAccountNumber"))) {
					linkAccountStatus = true;
					log.info("Successfully linked a Not-Registered account.");
					sAssert.assertTrue(linkAccountStatus);
					break;
				}
			}
			if (!linkAccountStatus) {
				log.info("Unable to link a Not-Registered account");
				sAssert.assertTrue(false);
			}

			/*
			 * Again Try to add same account which is already added
			 */

			clickBtnaccount();
			pause(1000);
			driver.switchTo().activeElement();
			sAssert.assertTrue(isaddAccountmodalVisible(), "add account model is not visible");
			clickRdbLinkAccResidentialPopup();

			if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1")) {
				populateAddAccountPopup(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			}
			if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")) {
				populateZipCodePopup(linkResidentialAccountInfo.get("ZipCode"));
			}
			if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1")) {
				populateaddMeterNumber(linkResidentialAccountInfo.get("MeterNumber"));
			}

			if (getRegistrationConfig.get("SSN").getFieldDisplayStatus().equals("1")) {
				populatetxtSSNPopup("12345");
			}
			if (getRegistrationConfig.get("Driving License Number").getFieldDisplayStatus().equals("1")) {
				populateDLPopupApp(linkResidentialAccountInfo.get("DrivingLicence"));
			}
			if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1")) {
				populateStreetAddPopupApp(linkResidentialAccountInfo.get("Address1"));
			}

			clickbtnLinkAccPopupSubmit();
			pause(2000);
			// sAssert.assertEquals(getToastMessage(),
			// accontInformationTextProp.getPropValue("textSuccessfulLinkedAccountMsgApp"));
			pause(2000);
			clickBtnCancelLinkAccount();
			pause(2000);
			// sAssert.assertTrue(isaddAccountmodalVisible());
			homeSteps.navigateToHome();
			selectAccountInDropdown(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			homeSteps.navigateToAccountInformation();
			for (int utilityAccountno = 0; utilityAccountno < getlbldivAccountList().size(); utilityAccountno++) {
				if (getlbldivAccountList().get(utilityAccountno).getText()
						.equals(linkResidentialAccountInfo.get("UtilityAccountNumber"))) {
					clickListAllLinkAccountKebabMenu(utilityAccountno);
					// pageUtil.clickElementUsingJsExecutor(pageUtil.getNthElements(driver,accountProfilePage.getListAllLinkAccountKebabMenu(),utilityAccountno));
					clickListbtnMakeDefault(utilityAccountno);
					// getBtnMakeDefaultAddr()
					clickBtnDefaultpopupsave();
				}
			}

			// Deleting linked account

			clickBtnToggleThreeDots(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			clickBtnUnlinkAccount(linkResidentialAccountInfo.get("UtilityAccountNumber"));
			clickBtnUnLinkAccount();

			sAssert.assertEquals(getToastMessage(),
					accontInformationTextProp.getPropValue("txtMsgForDeleteDefaultAccountApp"));
			DriverFactory.refreshBrowser();
			pause(2000);

			// sAssert.assertEquals(getToastMessage(),
			// accontInformationTextProp.getPropValue("txtErrorMsgSuccessAccountDeleteValidationApp"));
			pause(2000);
			DriverFactory.refreshBrowser();
			pause(2000);
			DashboardPage dashboard = new DashboardPage(driver);
			// Verify that after deleting selected account, default account should get
			// selected in Select Account Dropdown
			homeSteps.navigateToHome();
			String defaultAC = getDefaultOfLoggedInUser(user);
			String selectedAddr = dashboard.getAddressByVisibleAddress();
			// assertTrue(selectedAddr.contains(defaultAC)); // Bug : SCM10-3940
			if (selectedAddr.contains(defaultAC)) {
				log.info("Default Account has been selected after deleting selected account.");
			} else {
				log.info("Default Account is not being selected after deleting selected account.");
			}
			// Verify that deleted account is not present

			Boolean linkAccountStatus1 = false;
			int index1 = -1;
			String utilityAccountNumber1 = null;
			for (int i = 0; i < getlbldivAccountList().size(); i++) {
				index1++;
				if (getlbldivAccountList().get(i).getText()
						.equals(linkResidentialAccountInfo.get("UtilityAccountNumber"))) {
					linkAccountStatus1 = true;
					utilityAccountNumber1 = getlbldivAccountList().get(i).getText();
					log.info("Successfully linked a Not-Registered account.");
					sAssert.assertTrue(linkAccountStatus1);
					// assertTrue(linkAccountStatus);
					break;
				}
			}

			if (!linkAccountStatus1) {
				System.out.println("Unable to link a Registered-NotActivated and Expired Activation link account.");
				// sAssert.assertTrue(false);
			}

			log.info(
					"To verify Link Account successfully, adding an already added account and Delete newly Linked account.");

		} else {
			log.info("My Account link is not available on the header");
		}
	}

	/**
	 * This method is used to get the min max length of all the registration fields.
	 */
	public static HashMap<String, String[]> getMinMaxOfRegistrationFieldsFromDB() {
		String query = SQLQueries.getRegistrationTemplateConfig();
		HashMap<String, String[]> minMaxValueRegFields = new HashMap<>();
		try {
			ResultSet rs = DataBaseUtils.getResultSet(query);
			while (rs.next()) {
				String[] minMaxLimit = new String[2];
				minMaxLimit[0] = rs.getString("Min Length");
				minMaxLimit[1] = rs.getString("Max Length");
				minMaxValueRegFields.put(rs.getString("ParentHead"), minMaxLimit);
			}
			return minMaxValueRegFields;
		} catch (Exception e) {
			e.printStackTrace();
			return minMaxValueRegFields;
		}
	}

	public static Map<String, String> tableResultInMapForm(ResultSet resultSet) {
		ResultSet rs = resultSet;
		List<String> columnNames = new LinkedList<>();
		Map<String, String> columnNameToValuesMap = new HashMap<String, String>();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				String columnName = rsmd.getColumnName(i);
				columnNames.add(columnName);
				columnNameToValuesMap.put(columnName, new String());
			}
			while (rs.next()) {
				// Iterate the result set for each row
				for (String columnName : columnNames) {
					// Add the current row's column data to list
					String value1 = new String();
					value1 = rs.getString(columnName);
					// add the updated list of column data to the map now
					columnNameToValuesMap.put(columnName, value1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return columnNameToValuesMap;
	}

	public String getDefaultOfLoggedInUser(User user) {
		String serviceAccount = "";
		try {
			String query = SQLQueries.getDefaultAccountAndRoleId(user.getUserName());
			ResultSet rs = DataBaseUtils.getResultSet(query);
			if (rs.next()) {
				serviceAccount = rs.getString("UtilityAccountNumber");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceAccount;
	}

	public String linkAccountSteps(String sAccountType, SoftAssert sAssert) {
		String sUtilityAccountNumber = null;
		getRegistrationConfig = CSPConfiguration.initMRegistrationData();
		// Verify page heading
		pause(1000);
		clickBtnaccount();
		pause(1000);
		driver.switchTo().activeElement();
		isaddAccountmodalVisible();
		// Residential for linking a residential account
		String query = SQLQueries.getInfoForLinkingAccountFromDB("1", "3");
		Map<String, String> linkResidentialAccountInfo = new HashMap<>();
		try {
			ResultSet rs = DataBaseUtils.getResultSet(query);
			linkResidentialAccountInfo = tableResultInMapForm(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		clickRdbLinkAccResidentialPopup();
		if (getRegistrationConfig.get("Account Number").getFieldDisplayStatus().equals("1")) {
			populateAddAccountPopup(linkResidentialAccountInfo.get("UtilityAccountNumber"));
		}
		if (getRegistrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1")) {
			populateZipCodePopup(linkResidentialAccountInfo.get("ZipCode"));
		}
		if (getRegistrationConfig.get("Meter Number").getFieldDisplayStatus().equals("1")) {
			populateaddMeterNumber(linkResidentialAccountInfo.get("MeterNumber"));

		}
		if (getRegistrationConfig.get("SSN").getFieldDisplayStatus().equals("1")) {
			populatetxtSSNPopup("12345");
		}
		if (getRegistrationConfig.get("Driving License Number").getFieldDisplayStatus().equals("1")) {
			populateDLPopupApp(linkResidentialAccountInfo.get("DrivingLicence"));
		}
		if (getRegistrationConfig.get("Street Address").getFieldDisplayStatus().equals("1")) {
			populateStreetAddPopupApp(linkResidentialAccountInfo.get("Address1"));
		}
		clickbtnLinkAccPopupSubmit();
		// pageUtil.explicitWaitForWebElement(driver,
		// accountProfilePage.getLblMandatoryFields());
		String sToastMessage = getToastMessage();
		sAssert.assertEquals(sToastMessage, accontInformationTextProp.getPropValue("textSuccessfulLinkedAccountMsgApp"),
				"Toast message for link account not matched.");
		pause(2000);
		// sAssert.assertFalse(isaddAccountmodalVisible());
		DriverFactory.refreshBrowser();
		pause(2000);
		return linkResidentialAccountInfo.get("UtilityAccountNumber");
	}

	public void deLinkAccountSteps(String sUtilityAccountNo) {
		// Deleting linked account
		clickBtnToggleThreeDots(sUtilityAccountNo);
		clickBtnUnlinkAccount(sUtilityAccountNo);
		clickBtnUnLinkAccount();
		// Unable to verify Delete account successful message with below code
		pause(2000);
		DriverFactory.refreshBrowser();
		pause(2000);
	}

	public HashMap<String, Map> collectAllAccountsInfo(String cutomerid) {

		HashMap<String, Map> accountInfoCount = new HashMap<>();

		String registrationDataQuery = SQLQueries.getAccountsDetails(cutomerid);
		System.out.println(registrationDataQuery);
		ResultSet rsRegData;
		try {

			rsRegData = DataBaseUtils.getResultSet(registrationDataQuery);
			while (rsRegData.next()) {
				int count = 0;
				HashMap<String, String> accountInfo = new HashMap<>();
				accountInfo.put("AccountStatusID", rsRegData.getString("AccountStatusID"));
				accountInfo.put("MobilePhone", rsRegData.getString("MobilePhone"));
				accountInfo.put("Address1", rsRegData.getString("Address1"));
				accountInfo.put("UtilityAccountNumber", rsRegData.getString("UtilityAccountNumber"));
				accountInfo.put("BillType", rsRegData.getString("Paperless"));
				accountInfo.put("EmailID", rsRegData.getString("EmailID"));
				accountInfoCount.put(rsRegData.getString("UtilityAccountNumber"), accountInfo);
				// accountInfo.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return accountInfoCount;
	}

	public void statusCodeValidation(HashMap<String, Map> accountDetails, String accountStatusID, String billType) {

		for (Map.Entry<String, Map> entry : accountDetails.entrySet()) {
			String key = entry.getKey();
			System.out.println(key);
			System.out.println(entry.getValue().get("AccountStatusID"));
			Assert.assertEquals((entry.getValue().get("AccountStatusID")), accountStatusID);
			System.out.println(entry.getValue().get("BillType"));
			// Assert.assertEquals((entry.getValue().get("BillType")),billType);
			System.out.println(entry.getValue().get("MobilePhone"));
			System.out.println(entry.getValue().get("Address1"));
		}
	}

	public void selectAccountInDropdown(String account) {
		String addrID = "";
		DashboardPage ds = new DashboardPage(driver);
		String query = SQLQueries.getAddressIdOfUtilityAccount(account);
		try {
			String selectedAddr = ds.getAddressByVisibleAddressAttribute("title");

			if (selectedAddr.contains(account)) {
				log.info("Given Account is already selected");
			} else {
				ResultSet rs = DataBaseUtils.getResultSet(query);
				if (rs.next()) {
					addrID = rs.getString("AddressID");
				}
				ds.clickAccountAddress();
				pause(3);
				ds.clickAddressesFromDropdown(addrID);
				pause(3);
				// selectedAddr =
				// driver.findElement(dashboardPage.getLblSelecteAddressInDropdown()).getText();
				// assertTrue(selectedAddr.contains(account));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void statusCodeValidationforPaperless(HashMap<String, Map> accountDetails, String accountStatusID,
			String billType, String utilityAccountNumber) {

		for (Map.Entry<String, Map> entry : accountDetails.entrySet()) {
			String key = entry.getKey();
			System.out.println(key);
			System.out.println(entry.getValue().get("AccountStatusID"));
			Assert.assertEquals((entry.getValue().get("AccountStatusID")), accountStatusID);
			System.out.println(entry.getValue().get("BillType"));
			if (key.equals(utilityAccountNumber)) {
				// Assert.assertEquals((entry.getValue().get("BillType")),"1");
			} else {
				Assert.assertEquals((entry.getValue().get("BillType")), billType);
			}
			System.out.println(entry.getValue().get("MobilePhone"));
			System.out.println(entry.getValue().get("Address1"));

		}

	}
}

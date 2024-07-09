package demo.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import demo.pageobjects.PreLoginServicePage;
import sew.ai.api.endpoints.service.ServiceEndpoint;
import sew.ai.config.CSPConfiguration;
import sew.ai.config.Configuration;
import sew.ai.config.SCPConfiguration;
import sew.ai.driver.DriverFactory;
import sew.ai.driver.Page;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.models.RegistrationConfig;
import sew.ai.models.User;
import sew.ai.pageObjects.scp.PreLogStartServicePage;
import sew.ai.steps.scp.HomeSteps;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.DateUtil;
import sew.ai.utils.JsonUtil;
import sew.ai.utils.PropertiesUtil;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;
import static sew.ai.helpers.props.FilePaths.FILE_UPLOAD_PATH;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PreLoginServiceRequestSteps extends PreLoginServicePage {
	private static final Logger log = LogManager.getLogger(PreLoginServiceRequestSteps.class);
	public static PropertiesUtil serviceRequestTextProp;
	Map<String, Integer> registrationConfig = new HashMap<>();
	public static Map<String, RegistrationConfig> getRegistrationConfig = new LinkedHashMap<>();
	ResultSet rs;

	public PreLoginServiceRequestSteps(WebDriver driver) {
		super(driver);
		serviceRequestTextProp = new PropertiesUtil(
				FilePaths.SCP_TEXT_PROPERTIES + Constants.PRE_LOG_SERVICE_REQUEST_TEXT_FILENAME);
	}

	public Boolean isServiceRequestPage(String url, String title) {
		Boolean isServiceRequestPage = false;
		log.info("Checking that the current page is service request  page.");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isServiceRequestPage = true;
		log.info("The current page is service request page {}: " + isServiceRequestPage);
		return isServiceRequestPage;
	}

	public void preLogServiceReqObject(SoftAssert sAssert) throws SQLException {
		log.info("Test Case execution for - validatePreLoginServiceRequestPage - is Initiated");

		// Navigate to service .
		clickLinkServices();
		pause(1000);
		isServiceRequestPage(serviceRequestTextProp.getPropValue("expServiceUrl"),
				serviceRequestTextProp.getPropValue("expServiceTitle"));

		log.info("Verify that the Service Request Landing page should contain all the required details");
		sAssert.assertTrue(isServicesBannerVisible(), "Service banner is not visible");
		sAssert.assertEquals(getServicesBanner("src"), serviceRequestTextProp.getPropValue("urlAppendedTextSrp"));
		sAssert.assertTrue(isNewRequestTabVisible(), "New request tab is not visible");
		sAssert.assertEquals(getNewRequestTab(), serviceRequestTextProp.getPropValue("expNewRequestLabelSrp"));
		String defaultTab = getAtrributeNewRequestTab();
		Assert.assertTrue(defaultTab.contains("active"));
		sAssert.assertTrue(isSavedFormsTabVisible(), "Save form is not visible");
		sAssert.assertEquals(getSavedFormsTab(), serviceRequestTextProp.getPropValue("expSavedFormLabelSrp"));
		List<WebElement> serviceRequestsTiles = getObjectsServicesTiles();
		for (int serviceRequestTile = 0; serviceRequestTile < serviceRequestsTiles.size(); serviceRequestTile++) {
			serviceRequestsTiles = getObjectsServicesTiles();
			isObjectServicesImgTilesVisible(serviceRequestTile);
			isObjectChildLabelServicesTilesVisible(serviceRequestTile);
			isObjectSubTitleLabelServicesTilesVisible(serviceRequestTile);
			getObjectServicesImgTilesVisible(serviceRequestTile);
			getObjectChildLabelServicesTilesVisible(serviceRequestTile);
			getObjectSubTitleLabelServicesTilesVisible(serviceRequestTile);
			clickObjectServicesImgTilesVisible(serviceRequestTile);
			DriverFactory.navigateBack();
			pause(5000);
		}
		clickTrackRequestBtn1();
		populateTrackReqSearch("12345678");
		clickTrackRequestBtn();
		String errMsg = getToastMessage();
		Assert.assertEquals(errMsg, serviceRequestTextProp.getPropValue("expTrackRequestIderrormsgSrp"));
		waitForToastMessageInvisibility();
	}

	public void verifyMoveInPageObjectAndCreateReq(SoftAssert sAssert) throws InterruptedException {
		log.info("Test Case execution for - ValidateMoveInPageObjectAndCreateReq - is Initiated");
		pause(500);
		clickLinkServices();
		pause(1000);
		isServiceRequestPage(serviceRequestTextProp.getPropValue("expServiceUrl"),
				serviceRequestTextProp.getPropValue("expServiceTitle"));
		List<WebElement> serviceRequestsTiles = getObjectsServicesTiles();
		for (int serviceRequestTile = 0; serviceRequestTile < serviceRequestsTiles.size(); serviceRequestTile++) {
			serviceRequestsTiles = getObjectsServicesTiles();
			isObjectServicesImgTilesVisible(serviceRequestTile);
			isObjectChildLabelServicesTilesVisible(serviceRequestTile);
			isObjectSubTitleLabelServicesTilesVisible(serviceRequestTile);
			getObjectServicesImgTilesVisible(serviceRequestTile);
			getObjectChildLabelServicesTilesVisible(serviceRequestTile);

			if (getObjectChildLabelServicesTilesVisible(serviceRequestTile).equals("MOVE IN")) {
				clickObjectChildLabelServicesTilesVisible(serviceRequestTile);

				Assert.assertTrue(isPreLoginMoveInAccountNumberVisible(), "Account Number is not visiblle");
				Assert.assertTrue(isPreLoginMoveInDateVisible(), "Date is not visible");
				Assert.assertTrue(isPreLoginSerMoveInFirstNameVisible(), "First name is not visible");
				Assert.assertTrue(istxtPreLoginSerMoveInLastNameVisible(), "Last name is not visible");
				Assert.assertTrue(isPreLoginSerMoveInEmailVisible(), "Email Field is not visible");
				Assert.assertTrue(istxtPreLoginSerMoveInSSLVisible(), "SSN Fiels is not visible");
				Assert.assertTrue(isPreLoginSerMoveInContactTypeVisibleVisible(), "Contact Type is not Visible");
				Assert.assertTrue(isPreLoginSerMoveInPrimaryContactNoVisible(), "Primary Contact No is not visible");
				Assert.assertTrue(isPreLoginSerMoveInDateOfBirthVisible(), "Birth date is not visible");

				// CURRENT ADDRESS
				Assert.assertTrue(isPreLoginSerMoveInCurrentAddDateOfBirthVisible(),
						"Current Add date of Birth is not visible");
				Assert.assertTrue(isPreLoginSerMoveInCurrentAddStreetNumVisible(), "Current Add Sno. is not visible");
				Assert.assertTrue(isPreLoginSerMoveInCurrentAddStreetNameVisible(), "Current Add Sname is not visible");

				Assert.assertTrue(isPreLoginSerMoveInTypeFieldIsVisible(), "Type is not visible ");
				Assert.assertTrue(isPreLoginSerMoveInAptAndUnitNumFieldIsVisible(), "Unit Num is not visible");
				Assert.assertTrue(isPreLoginSerMoveInCurrentAddCityVisible(), "Add City is not visible");
				Assert.assertTrue(isPreLoginSerMoveInCurrentAddStateVisible(), "Current Add state is not visible");
				Assert.assertTrue(isPreLoginSerMoveInCurrentAddZipCodeVisible(), "Current Add Zip code is not visible");
				Assert.assertTrue(isadulttoggleVisible(), "Adult toggle is not visible");

				// THE ADDRESS WHERE YOU'D LIKE TO START SERVICE.

				Assert.assertTrue(isPreLoginSerMoveInRequestStartDateVisible());
				Assert.assertTrue(isPreLoginSerMoveInStartServiceStreetNoVisible());
				Assert.assertTrue(isPreLoginSerMoveInRequestStartStreetNameVisible());
				Assert.assertTrue(isPreLoginSerMoveInRequestStartApartmentVisible());
				Assert.assertTrue(isPreLoginSerMoveInBillingAddVisible());
				Assert.assertTrue(isPreLoginSerMoveInRequestStartCity());
				Assert.assertTrue(isPreLoginSerMoveInStartServiceStateVisible());
				Assert.assertTrue(isPreLoginSerMoveInRequestStartZipCodeVisible());

				// Verify Disclaimer on Move In Page
				sAssert.assertEquals(gettxtContactInfoDisclaimer(),
						serviceRequestTextProp.getPropValue("txtContactInfoDisclaimer"), "Disclamer is not correct");
				sAssert.assertEquals(gettxtadultBeLivingDisclairmer(),
						serviceRequestTextProp.getPropValue("txtadultBeLivingDisclairmer"), "Disclamer is not correct");
				sAssert.assertEquals(gettxtUsethisFormDisclaimer(),
						serviceRequestTextProp.getPropValue("txtUsethisFormDisclaimer"), "Disclamer is not correct");

				// Cancel,Save,Next Button
				Assert.assertTrue(isPreLoginSerMoveInPageSaveBtnVisible());
				Assert.assertTrue(isPreLoginSerMoveInPageNexBtnVisible());
				Assert.assertTrue(isPreLoginSerMoveInPageCancelBtnVisible());

				// Enter All mandatory Field Verification

				clcknextbtn();
				String errMsg = getToastMessage();
				Assert.assertEquals(errMsg, serviceRequestTextProp.getPropValue("expValidationMsgSrp"));

				// mandatory Field Verification when user left with more than two fields
				String acc = Configuration.toString("accountNumber");
				populateAccountNum(acc);
				populateFname("Test");
				populateLname("Quality");
				populateSSN("1234");
				SelectAddedContactType("Home");
				selectdate("8");

				// CurrentAdd
				selectDateCurrentAdd();
				populatePrimarySreetName("Fleet Street");
				SelectHomeType("Apartment");
				populatecity("hongKONG");
				populatestate("newyork");
				populatezipcode("12345");

				clicktoggle();
				// Create Request
				selectStartServicedate();
				populatestreetname("street");
				populatereqcity("china");
				populatereqstate("germany");
				populatereqzipcode("332132");

				selectYesNOforBillingAdd("No");

				selectstreetAdd();
				populatedMoveInBillingCity("hello");
				populatedMoveInBillingState("dfnbdn");
				populatedMoveInBillingZipcode("123456");
				populatedComments("Comments Added");

				// Create Request

				String acc1 = Configuration.toString("accountNumber");
				populateAccountNum(acc1);
				populateFname("Test");
				populateLname("Quality");
				populateEmailAddress("demo@mailinator.com");
				populateSSN("1234");
				SelectAddedContactType("Home");
				selectdate("8");
				populatePrimaryContactNo("12345678912");

				// CurrentAdd
				selectDateCurrentAdd();
				populatePrimarySreetName("Fleet Street");
				SelectHomeType("Apartment");
				populatecity("hongKONG");
				populatestate("newyork");
				populatezipcode("12345");

				clicktoggle();
				// Create Request
				selectStartServicedate();
				populatestreetname("street");
				populatereqcity("china");
				populatereqstate("germany");
				populatereqzipcode("332132");

				selectYesNOforBillingAdd("No");
				selectstreetAdd();
				populatedMoveInBillingCity("hello");
				populatedMoveInBillingState("dfnbdn");
				populatedMoveInBillingZipcode("123456");
				populatedComments("Comments Added");
				pause(200);
				clcknextbtn();
			}

		}
	}

	public void verifyMoveInFeildLevelValidation(SoftAssert sAssert) throws InterruptedException {
		log.info("Test Case execution for - verifyMoveInFeildsErrorMessages - is Initiated");
		pause(500);
		clickLinkServices();

		List<WebElement> serviceRequestsTiles = getObjectsServicesTiles();
		for (int serviceRequestTile = 0; serviceRequestTile < serviceRequestsTiles.size(); serviceRequestTile++) {
			serviceRequestsTiles = getObjectsServicesTiles();
			isObjectServicesImgTilesVisible(serviceRequestTile);
			isObjectChildLabelServicesTilesVisible(serviceRequestTile);
			isObjectSubTitleLabelServicesTilesVisible(serviceRequestTile);
			getObjectServicesImgTilesVisible(serviceRequestTile);
			getObjectChildLabelServicesTilesVisible(serviceRequestTile);

			if (getObjectChildLabelServicesTilesVisible(serviceRequestTile).equals("MOVE IN")) {
				clickObjectChildLabelServicesTilesVisible(serviceRequestTile);

				String currentdate = DateUtil.getCurrentSystemDate("d-MMM-yy");
				String portaldate = getPrepopulatedDate();
				Assert.assertEquals(currentdate, portaldate);

				// Bug exist
			}
		}

	}

	public void verifyMoveOutPageObjectAndCreateReq(SoftAssert sAssert) throws InterruptedException {
		log.info("Test Case execution for - ValidateMoveInPageObjectAndCreateReq - is Initiated");
		pause(500);
		clickLinkServices();
		pause(1000);
		isServiceRequestPage(serviceRequestTextProp.getPropValue("expServiceUrl"),
				serviceRequestTextProp.getPropValue("expServiceTitle"));
		List<WebElement> serviceRequestsTiles = getObjectsServicesTiles();
		for (int serviceRequestTile = 0; serviceRequestTile < serviceRequestsTiles.size(); serviceRequestTile++) {
			serviceRequestsTiles = getObjectsServicesTiles();
			isObjectServicesImgTilesVisible(serviceRequestTile);
			isObjectChildLabelServicesTilesVisible(serviceRequestTile);
			isObjectSubTitleLabelServicesTilesVisible(serviceRequestTile);
			getObjectServicesImgTilesVisible(serviceRequestTile);
			getObjectChildLabelServicesTilesVisible(serviceRequestTile);

			if (getObjectChildLabelServicesTilesVisible(serviceRequestTile).equals("MOVE OUT")) {

				clickObjectChildLabelServicesTilesVisible(serviceRequestTile);

				Assert.assertTrue(isPreLoginMoveInAccountNumberVisible(), "Account Number is not visiblle");
				Assert.assertTrue(isPreLoginMoveInDateVisible(), "Date is not visible");

				// WHEN?
				isPreLoginMoveOutDateVisible();

				// CONTACT INFORMATION
				Assert.assertTrue(isPreLoginSerMoveInFirstNameVisible(), "First name is not visible");
				Assert.assertTrue(istxtPreLoginSerMoveInLastNameVisible(), "Last name is not visible");
				Assert.assertTrue(isPreLoginSerMoveInPrimaryContactNoVisible(), "Primary Contact No is not visible");
				Assert.assertTrue(isPreLoginSerMoveOutSecondaryContactNumVisible());
				Assert.assertTrue(isPreLoginSerMoveInEmailVisible(), "Email Field is not visible");
				Assert.assertTrue(isPreLoginSerMoveInContactTypeVisibleVisible(), "Contact Type is not Visible");

				// MAILING ADDRESS
				Assert.assertTrue(isPreLoginSerMoveInCurrentAddStreetNumVisible());
				Assert.assertTrue(isPreLoginSerMoveInCurrentAddStreetNameVisible());
				Assert.assertTrue(isPreLoginSerMoveInCurrentAddUnitNoVisible());
				Assert.assertTrue(isPreLoginSerMoveInCurrentAddCityVisible());
				Assert.assertTrue(isPreLoginSerMoveInRequestStartZipCodeVisible());
				Assert.assertTrue(isPreLoginSerMoveInCurrentAddStateVisible(), "Current Add state is not visible");

				// Verify Disclaimer on Move Out Page

				sAssert.assertEquals(gettxtProcessedReqDisclaimer(),
						serviceRequestTextProp.getPropValue("txtProcessedReq"), "Disclamer is not correct");
				sAssert.assertEquals(gettxtWhenDisclaimer(), serviceRequestTextProp.getPropValue("txtWhenDisclaimer"),
						"Disclamer is not correct");

				sAssert.assertEquals(gettxtContcttInfoDisclaimer(),
						serviceRequestTextProp.getPropValue("txtContcttInfoDisclaimer"), "Disclamer is not correct");

				sAssert.assertEquals(gettxtMailingAddDisclaimer(),
						serviceRequestTextProp.getPropValue("txtMailingAddDisclaimer"), "Disclamer is not correct");

				String distext = serviceRequestTextProp.getPropValue("txtAttachmentDisclaimer");
				sAssert.assertTrue(gettxtAttachmentDisclaimer().contains(distext));

				sAssert.assertEquals(gettxtUsethisFormDisclaimer(),
						serviceRequestTextProp.getPropValue("txtUsethisFormDisclaimer"), "Disclamer is not correct");

				// Cancel,Save,Next Button

				Assert.assertTrue(isPreLoginSerMoveInPageSaveBtnVisible());
				Assert.assertTrue(isPreLoginSerMoveInPageNexBtnVisible());
				Assert.assertTrue(isPreLoginSerMoveInPageCancelBtnVisible());

				// Enter All mandatory Field Verification pause(2000);
				clcknextbtn();
				String errMsg = getToastMessage();
				Assert.assertEquals(errMsg, serviceRequestTextProp.getPropValue("expValidationMsgSrp"));

				// create MoveOut Req
				pause(1000);
				String acc = Configuration.toString("accountNumber");
				populateAccountNum(acc);

				String currentdate = DateUtil.getCurrentSystemDate("d-MMM-yy");
				String portaldate = getPrepopulatedDate();
				Assert.assertEquals(currentdate, portaldate);

				selectMoveOutDate();

				populateFname("Test");
				populateLname("Quality");

				populatePrimaryContactNo("1234567890");
				populateSecondoryContactNo("1123452626");
				populateEmailAddress("demo@mailinator.com");
				SelectAddedContactType("Home");

				populatePrimarySreetName("Fleet Street");

				populatecity("L");
				// pause(200);

				populatestate("A");
				populatereqzipcode("22222");
				pause(1000);

				clcknextbtn();
				sAssert.assertEquals(getFormpreview(), "Review and Confirm");
				Assert.assertEquals(getObjectsFormpreviewValue().get(0).getText(), acc);
				Assert.assertEquals(getObjectsFormpreviewValue().get(1).getText(), portaldate);
				Assert.assertEquals(getObjectsFormpreviewValue().get(7).getText(), "demo@mailinator.com");
				Assert.assertEquals(getObjectsFormpreviewValue().get(8).getText(), "Home");

				log.info("get Req Id from the popup");
				clcksubmit();
				String popup = serviceRequestTextProp.getPropValue("txtpopup");
				sAssert.assertTrue(getPreLoginSavedFormPopup().contains(popup));
				clickOkbutton();
				pause(5000);

			}
		}
	}

	public void verifySavedForm(SoftAssert sAssert) throws InterruptedException {
		log.info("Test Case execution for - ValidateMoveInPageObjectAndCreateReq - is Initiated");
		pause(500);
		clickLinkServices();
		pause(1000);
		isServiceRequestPage(serviceRequestTextProp.getPropValue("expServiceUrl"),
				serviceRequestTextProp.getPropValue("expServiceTitle"));
		List<WebElement> serviceRequestsTiles = getObjectsServicesTiles();
		for (int serviceRequestTile = 0; serviceRequestTile < serviceRequestsTiles.size(); serviceRequestTile++) {
			serviceRequestsTiles = getObjectsServicesTiles();
			isObjectServicesImgTilesVisible(serviceRequestTile);
			isObjectChildLabelServicesTilesVisible(serviceRequestTile);
			isObjectSubTitleLabelServicesTilesVisible(serviceRequestTile);
			getObjectServicesImgTilesVisible(serviceRequestTile);
			getObjectChildLabelServicesTilesVisible(serviceRequestTile);

			if (getObjectChildLabelServicesTilesVisible(serviceRequestTile).equals("MOVE OUT")) {
				clickObjectChildLabelServicesTilesVisible(serviceRequestTile);

				// create MoveOut Req
				pause(1000);
				String acc = Configuration.toString("accountNumber");
				populateAccountNum(acc);
				String currentdate = DateUtil.getCurrentSystemDate("d-MMM-yy");
				String portaldate = getPrepopulatedDate();
				Assert.assertEquals(currentdate, portaldate);
				selectMoveOutDate();
				populateFname("Test");
				populateLname("Quality");
				populatePrimaryContactNo("1234567890");
				populateSecondoryContactNo("1123452626");
				populateEmailAddress("demo@mailinator.com");
				SelectAddedContactType("Home");
				populatePrimarySreetName("Fleet Street");
				populatecity("L");
				populatestate("A");
				populatereqzipcode("22222");
				pause(1000);

				log.info("click save button to save form");
				clickSaveForm();
				String actualSaveFormsTrackId = getPreLoginSavedFormPopup().split(":")[1].trim();
				clickOkbutton();
				pause(5000);

				clickSaveFormsBtn();
				log.info("verify please enter valid req id");

				populatePreLoginSavedFormTrackReqSearch("12345678");
				clickPreLoginSaveFormsSubmitBtn();
				String errMsg = getToastMessage();
				Assert.assertEquals(errMsg, serviceRequestTextProp.getPropValue("expTrackRequestIderrormsgSrp"));
				waitForToastMessageInvisibility();

				log.info("seach from the track Id in saved form");

				populatePreLoginSavedFormTrackReqSearch(actualSaveFormsTrackId);
				clickPreLoginSaveFormsSubmitBtn();
				pause(2000);
				break;
			}
		}
	}

	public void verifyMoveOutFeildLevelValidation(SoftAssert sAssert) throws InterruptedException {
		log.info("Test Case execution for - verifyMoveOutFeildsErrorMessages - is Initiated");
		pause(500);
		clickLinkServices();

		List<WebElement> serviceRequestsTiles = getObjectsServicesTiles();
		for (int serviceRequestTile = 0; serviceRequestTile < serviceRequestsTiles.size(); serviceRequestTile++) {
			serviceRequestsTiles = getObjectsServicesTiles();
			isObjectServicesImgTilesVisible(serviceRequestTile);
			isObjectChildLabelServicesTilesVisible(serviceRequestTile);
			isObjectSubTitleLabelServicesTilesVisible(serviceRequestTile);
			getObjectServicesImgTilesVisible(serviceRequestTile);
			getObjectChildLabelServicesTilesVisible(serviceRequestTile);

			if (getObjectChildLabelServicesTilesVisible(serviceRequestTile).equals("MOVE OUT")) {
				clickObjectChildLabelServicesTilesVisible(serviceRequestTile);

				// create MoveOut Req
				pause(1000);
				String acc = Configuration.toString("accountNumber");
				populateAccountNum(acc);

				String currentdate = DateUtil.getCurrentSystemDate("d-MMM-yy");
				String portaldate = getPrepopulatedDate();
				Assert.assertEquals(currentdate, portaldate);
				selectMoveOutDate();
				populateFname("Test");
				populateLname("Quality");
				populatePrimaryContactNo("1234567890");
				populateSecondoryContactNo("1123452626");
				populateEmailAddress("demo@mailinator.com");
				SelectAddedContactType("Home");
				populatePrimarySreetName("Fleet Street");
				populatecity("L");
				pause(5000);
				populatestate("A");
				// verify blank Zipcode msg
				clcknextbtn();
				sAssert.assertEquals(getErrMsgZipCode(), serviceRequestTextProp.getPropValue("txtErrorZipCodeMsg"),
						"Blank Error Msg do not match");
				populatereqzipcode("22222");

				// verify blank email add
				clear(elementEmailAdd());
				clcknextbtn();
				sAssert.assertEquals(getErrMsgEmail(), serviceRequestTextProp.getPropValue("expEmailValidationMsgSrp"),
						"Blank/Invalid Error Msg do not match");
				populateEmailAddress("demo@mailinator.com");

				// verify blank Firstname
				clear(elementFirstName());
				clcknextbtn();
				sAssert.assertEquals(getErrMsgFisrtName(), serviceRequestTextProp.getPropValue("txtFnameErrMsg"),
						"Blank Error Msg do not match");
				populateFname("Test");

				// verify blank Lastname
				clear(elementLastName());
				clcknextbtn();
				sAssert.assertEquals(getErrMsgLastName(), serviceRequestTextProp.getPropValue("txtLnameErrMsg"),
						"Blank Error Msg do not match");
				populateLname("Quality");

				String invalidAttachmentFileName = "index.html";
				addAttachmentToChooseFile(FILE_UPLOAD_PATH + invalidAttachmentFileName);
				clcknextbtn();
				String invalidAttachedFileName = getToastMessage();
				Assert.assertEquals(invalidAttachedFileName,
						serviceRequestTextProp.getPropValue("txtLblInvalidFileFormatMsgSrp"));

				// Bug exist in error msg
			}
		}

	}

	public void preLogServiceServiceTransferReq(SoftAssert sAssert) throws InterruptedException {
		log.info("Test Case execution for - preLogServiceServiceTransferReq - is Initiated");
		pause(500);
		clickLinkServices();
		pause(1000);
		isServiceRequestPage(serviceRequestTextProp.getPropValue("expServiceUrl"),
				serviceRequestTextProp.getPropValue("expServiceTitle"));
		List<WebElement> serviceRequestsTiles = getObjectsServicesTiles();
		for (int serviceRequestTile = 0; serviceRequestTile < serviceRequestsTiles.size(); serviceRequestTile++) {
			serviceRequestsTiles = getObjectsServicesTiles();
			isObjectServicesImgTilesVisible(serviceRequestTile);
			isObjectChildLabelServicesTilesVisible(serviceRequestTile);
			isObjectSubTitleLabelServicesTilesVisible(serviceRequestTile);
			getObjectServicesImgTilesVisible(serviceRequestTile);
			getObjectChildLabelServicesTilesVisible(serviceRequestTile);

			if (getObjectChildLabelServicesTilesVisible(serviceRequestTile).equals("SERVICE TRANSFER")) {
				clickObjectChildLabelServicesTilesVisible(serviceRequestTile);

				Assert.assertTrue(isPreLoginMoveInAccountNumberVisible(), "Account Number is not visiblle");
				Assert.assertTrue(isPreLoginMoveInDateVisible(), "Date is not visible");

				// WHEN?
				Assert.assertTrue(isPreLoginMoveOutDateVisible());

				// WHERE ARE YOU MOVING TO?
				Assert.assertTrue(isPreLoginSerMoveInCurrentAddStreetNumVisible());
				Assert.assertTrue(isPreLoginSerMoveInCurrentAddStreetNameVisible());
				Assert.assertTrue(isPreLoginSerMoveInCurrentAddUnitNoVisible());
				Assert.assertTrue(isPreLoginSerMoveInCurrentAddCityVisible());
				Assert.assertTrue(isPreLoginSerMoveInRequestStartZipCodeVisible());
				Assert.assertTrue(isPreLoginSerMoveInCurrentAddStateVisible(), "Current Add state is not visible");

				// CONTACT INFORMATION
				Assert.assertTrue(isPreLoginSerMoveInFirstNameVisible(), "First name is not visible");
				Assert.assertTrue(istxtPreLoginSerMoveInLastNameVisible(), "Last name is not visible");
				Assert.assertTrue(isPreLoginSerMoveInPrimaryContactNoVisible(), "Primary Contact No is not visible");
				Assert.assertTrue(isPreLoginSerMoveInEmailVisible(), "Email Field is not visible");
				Assert.assertTrue(isPreLoginSerMoveInContactTypeVisibleVisible(), "Contact Type is not Visible");
				Assert.assertTrue(isPreLoginSTDateVisible(), "MoveIn Date is not Visible");
				Assert.assertTrue(isPreLoginOtherScheduleDateVisible());

				// Bug Exist as email is not accepting @.
			}

		}
	}

	public void preLogServiceOtherFeildsVeification(SoftAssert sAssert) {
		// Logging to the extent reports & logger
		pause(500);
		clickLinkServices();
		pause(1000);
		isServiceRequestPage(serviceRequestTextProp.getPropValue("expServiceUrl"),
				serviceRequestTextProp.getPropValue("expServiceTitle"));
		List<WebElement> serviceRequestsTiles = getObjectsServicesTiles();
		for (int serviceRequestTile = 0; serviceRequestTile < serviceRequestsTiles.size(); serviceRequestTile++) {
			serviceRequestsTiles = getObjectsServicesTiles();
			isObjectServicesImgTilesVisible(serviceRequestTile);
			isObjectChildLabelServicesTilesVisible(serviceRequestTile);
			isObjectSubTitleLabelServicesTilesVisible(serviceRequestTile);
			getObjectServicesImgTilesVisible(serviceRequestTile);
			getObjectChildLabelServicesTilesVisible(serviceRequestTile);

			if (getObjectChildLabelServicesTilesVisible(serviceRequestTile).equals("OTHERS")) {
				clickObjectChildLabelServicesTilesVisible(serviceRequestTile);
				String currentdate = DateUtil.getCurrentSystemDate("d-MMM-yy");
				String portaldate = getPrepopulatedDate();
				Assert.assertEquals(currentdate, portaldate);

				Assert.assertTrue(isPreLoginMoveInAccountNumberVisible());
				Assert.assertTrue(isPreLoginSerMoveInFirstNameVisible());
				Assert.assertTrue(istxtPreLoginSerMoveInLastNameVisible());
				Assert.assertTrue(isPreLoginSerMoveInEmailVisible());
				Assert.assertTrue(isLockedGates());
				Assert.assertTrue(isPetToggleisvisible());
				Assert.assertTrue(isOtherFormPersonAvailableFeildVisible());
				Assert.assertTrue(isPreLoginSerMoveInPrimaryContactNoVisible());

				// verify Disclaimer
				String distext = serviceRequestTextProp.getPropValue("txtAttachmentDisclaimer");
				sAssert.assertTrue(gettxtAttachmentDisclaimer().contains(distext));

				sAssert.assertEquals(gettxtUsethisFormDisclaimer(),
						serviceRequestTextProp.getPropValue("txtUsethisFormDisclaimer"), "Disclamer is not correct");

				// Cancel,Save,Next Button

				Assert.assertTrue(isPreLoginSerMoveInPageSaveBtnVisible());
				Assert.assertTrue(isPreLoginSerMoveInPageNexBtnVisible());
				Assert.assertTrue(isPreLoginSerMoveInPageCancelBtnVisible());
			}

		}

	}

}
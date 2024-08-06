package demo.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.ExtendedLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import demo.pageobjects.PostLogServicespage;
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

public class PostLogServicesSteps extends PostLogServicespage {
	private static final Logger log = LogManager.getLogger(PostLogServicesSteps.class);
	public static PropertiesUtil serviceRequestTextProp;
	Map<String, Integer> registrationConfig = new HashMap<>();
	public static Map<String, RegistrationConfig> getRegistrationConfig = new LinkedHashMap<>();
	ResultSet rs;
	private String portaldate;

	public PostLogServicesSteps(WebDriver driver) {
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

	public void PostLogServiceReqObject(SoftAssert sAssert) throws SQLException {
		log.info("Test Case execution for - validatePreLoginServiceRequestPage - is Initiated");

		// Navigate to service .
		clickServices();
		pause(1000);
		clickServicesoverview();
		isServiceRequestPage(serviceRequestTextProp.getPropValue("PostlogUrl"),
				serviceRequestTextProp.getPropValue("expServiceTitle"));

		log.info("Verify that the Service Request Landing page should contain all the required details");
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
		clicSearchBtn();
		populateTrackReqSearch("12345678");
		String errMsg = getNoRecordtxt();
		Assert.assertEquals(errMsg, serviceRequestTextProp.getPropValue("txtNoRecords"));
	}

	public void verifyPostMoveInPageObjectAndCreateReq(SoftAssert sAssert) throws InterruptedException {
		log.info("Test Case execution for - ValidateMoveInPageObjectAndCreateReq - is Initiated");
		pause(500);
		clickServices();
		pause(1000);
		clickServicesoverview();
		isServiceRequestPage(serviceRequestTextProp.getPropValue("PostlogUrl"),
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

				Assert.assertTrue(ispostLoginMoveInAccountNumberVisible(), "Account Number is not visiblle");
				Assert.assertTrue(ispostLoginMoveInDateVisible(), "Date is not visible");
				Assert.assertTrue(ispostLoginSerMoveInFirstNameVisible(), "First name is not visible");
				Assert.assertTrue(istxtpostLoginSerMoveInSSLVisible(), "Last name is not visible");
				Assert.assertTrue(ispostLoginSerMoveInEmailVisible(), "Email Field is not visible");
				Assert.assertTrue(istxtpostLoginSerMoveInSSLVisible(), "SSN Fiels is not visible");
				Assert.assertTrue(ispostLoginSerMoveInContactTypeVisibleVisible(), "Contact Type is not Visible");
				Assert.assertTrue(ispostLoginSerMoveInPrimaryContactNoVisible(), "Primary Contact No is not visible");
				Assert.assertTrue(ispostLoginSerMoveInDateOfBirthVisible(), "Birth date is not visible");

				// CURRENT ADDRESS
				Assert.assertTrue(ispostLoginSerMoveInCurrentAddDateOfBirthVisible(),
						"Current Add date of Birth is not visible");
				Assert.assertTrue(ispostLoginSerMoveInCurrentAddStreetNumVisible(), "Current Add Sno. is not visible");
				Assert.assertTrue(ispostLoginSerMoveInCurrentAddStreetNameVisible(),
						"Current Add Sname is not visible");

				Assert.assertTrue(ispostLoginSerMoveInTypeFieldIsVisible(), "Type is not visible ");
				Assert.assertTrue(ispostLoginSerMoveInAptAndUnitNumFieldIsVisible(), "Unit Num is not visible");
				Assert.assertTrue(ispostLoginSerMoveInCurrentAddCityVisible(), "Add City is not visible");
				Assert.assertTrue(ispostLoginSerMoveInCurrentAddStateVisible(), "Current Add state is not visible");
				Assert.assertTrue(ispostLoginSerMoveInCurrentAddZipCodeVisible(),
						"Current Add Zip code is not visible");
				Assert.assertTrue(isadulttoggleVisible(), "Adult toggle is not visible");

				// THE ADDRESS WHERE YOU'D LIKE TO START SERVICE.

				Assert.assertTrue(ispostLoginSerMoveInRequestStartDateVisible());
				Assert.assertTrue(ispostLoginSerMoveInStartServiceStreetNoVisible());
				Assert.assertTrue(ispostLoginSerMoveInRequestStartStreetNameVisible());
				Assert.assertTrue(ispostLoginSerMoveInRequestStartApartmentVisible());
				Assert.assertTrue(ispostLoginSerMoveInBillingAddVisible());
				Assert.assertTrue(ispostLoginSerMoveInRequestStartCity());
				Assert.assertTrue(ispostLoginSerMoveInStartServiceStateVisible());
				Assert.assertTrue(ispostLoginSerMoveInRequestStartZipCodeVisible());

				// Verify Disclaimer on Move In Page
				sAssert.assertEquals(gettxtContactInfoDisclaimer(),
						serviceRequestTextProp.getPropValue("txtContactInfoDisclaimer"), "Disclamer is not correct");
				sAssert.assertEquals(gettxtadultBeLivingDisclairmer(),
						serviceRequestTextProp.getPropValue("txtadultBeLivingDisclairmer"), "Disclamer is not correct");

				// Cancel,Save,Next Button
				Assert.assertTrue(ispostLoginSerMoveInPageSaveBtnVisible());
				Assert.assertTrue(ispostLoginSerMoveInPageNexBtnVisible());
				Assert.assertTrue(ispostLoginSerMoveInPageCancelBtnVisible());

				// Enter All mandatory Field Verification

				clcknextbtn();
				String errMsg = getToastMessage();
				Assert.assertEquals(errMsg, serviceRequestTextProp.getPropValue("expValidationMsgSrp"));

				// mandatory Field Verification when user left with more than two fields
				String acc = Configuration.toString("accountNumber");
				populateSSN("1234");
				SelectAddedContactType("Home");
				selectdate("8");

				// CurrentAdd
				selectDateCurrentAdd();
				populatePrimarySreetName("Fleet Street");
				ispostLoginSerMoveInTypeFieldoptnsVisible("Town Home");
				ispostLoginSerMoveInTypeFieldoptnsVisible("Single Family Residence");
				ispostLoginSerMoveInTypeFieldoptnsVisible("Others");
				SelectHomeType("Apartment");
				populatecity("L");
				populatestate("A");
				populatereqzipcode("22222");
				populatezipcode("12345");
				clicktoggle();

				// Create Request
				selectStartServicedate();
				populatestreetname("street");
				populatereqcity("C");
				populatereqstate("A");
				populatereqzipcode("332132");
				selectYesNOforBillingAdd("No");
				selectstreetAdd();
				clickstreetname2();
				populatestreetname2("street");
				populatedMoveInBillingCity("L");
				populatedMoveInBillingState("A");
				populatedMoveInBillingZipcode("123456");
				populatedComments("Comments Added");
				clcknextbtn();
				sAssert.assertEquals(getFormpostview(), "Review and Confirm");
				Assert.assertEquals(getObjectsFormpostviewValue().get(0).getText(), acc);
				Assert.assertEquals(getObjectsFormpostviewValue().get(7).getText(), "Jul 12,2006");
				Assert.assertEquals(getObjectsFormpostviewValue().get(8).getText(), "(424) 271-4361");
				log.info("get Req Id from the popup");
				clcksubmit();
				String popup = serviceRequestTextProp.getPropValue("txtpopup");
				sAssert.assertTrue(getpostLoginSavedFormPopup().contains(popup));
				clickOkbutton();
				pause(5000);
				ExtentLogger.logInfo("Move in Request submitted successfully ");
			}
		}
	}

	public void verifyPostMoveInPageSavedforms(SoftAssert sAssert) throws InterruptedException {
		log.info("Test Case execution for - ValidateMoveInPageObjectAndCreateReq - is Initiated");
		pause(500);
		clickServices();
		pause(1000);
		clickServicesoverview();

		// Saved forms check
		clckmovein();
		String accs = Configuration.toString("accountNumber");
		populateSSN("1234");
		SelectAddedContactType("Home");
		selectdate("8");

		// CurrentAdd
		selectDateCurrentAdd();
		populatePrimarySreetName("Fleet Street");
		ispostLoginSerMoveInTypeFieldoptnsVisible("Town Home");
		ispostLoginSerMoveInTypeFieldoptnsVisible("Single Family Residence");
		ispostLoginSerMoveInTypeFieldoptnsVisible("Others");
		SelectHomeType("Apartment");
		populatecity("L");
		populatestate("A");
		populatereqzipcode("22222");
		populatezipcode("12345");
		clicktoggle();

		// Create Request
		selectStartServicedate();
		populatestreetname("street");
		populatereqcity("C");
		populatereqstate("A");
		populatereqzipcode("332132");
		selectYesNOforBillingAdd("No");
		selectstreetAdd();
		clickstreetname2();
		populatestreetname2("street");
		populatedMoveInBillingCity("L");
		populatedMoveInBillingState("A");
		populatedMoveInBillingZipcode("123456");
		populatedComments("Comments Added");
		log.info("click save button to save form");
		clickSaveForm();
		String actualSaveFormsTrackId = getpostLoginSavedFormPopup().split(":")[1].trim();
		clickOkbutton();
		pause(5000);
		clickSaveFormsBtn();
		log.info("verify please enter valid req id");
		clickSavedformsBtn();
		populatepostLoginSavedFormTrackReqSearch("12345678");
		String errprMsg = getNoRecordstxt();
		Assert.assertEquals(errprMsg, serviceRequestTextProp.getPropValue("txtNoRecords"));
		clearpostLoginSavedFormTrackReqSearch();
		populatepostLoginSavedFormTrackReqSearch(actualSaveFormsTrackId);
		isSavedFormsinforVisible();
		pause(2000);
		clickthreedotBtn();
		clickeditBtn();
		ExtentLogger.logInfo("Clicked edit button");

		// Saved forms validation
		istxt1234Visible("1234");
		istxtStreetNameVisible("Fleet Street");
		istxtcityVisible("Oakland");
		istxtStateVisible("CALIFORNIA");
		isHomeoptnVisible("Apartment");
		isZipcodeVisible("22222");
		ispostStreetVisible("street");
		clearstreet();
		populatestreetname("Cat");
		istxtcityVisible("Luke Air Force Base");
		istxtStatesVisible("Alberta");
		istxtZipcodeVisible("22222");
		clickSave();
		String actualSaveFormTrackId = getpostLoginSavedFormPopup().split(":")[1].trim();
		clickOkbutton();
		pause(5000);
		ExtentLogger.logInfo("Validated the content");
		ExtentLogger.logInfo("Saved forms for Move in validated");

	}

	public void verifyMoveOutPageObjectAndCreateReq(SoftAssert sAssert) throws InterruptedException {
		log.info("Test Case execution for - ValidateMoveInPageObjectAndCreateReq - is Initiated");
		pause(500);
		clickServices();
		pause(1000);
		clickServicesoverview();
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

				Assert.assertTrue(ispostLoginMoveInAccountNumberVisible(), "Account Number is not visiblle");
				Assert.assertTrue(ispostLoginMoveInDateVisible(), "Date is not visible");

				// WHEN?
				ispostLoginMoveOutDateVisible();

				// CONTACT INFORMATION
				Assert.assertTrue(ispostLoginSerMoveInFirstNameVisible(), "First name is not visible");
				Assert.assertTrue(istxtpostLoginSerMoveInLastNameVisible(), "Last name is not visible");
				Assert.assertTrue(ispostLoginSerMoveInPrimaryContactNoVisible(), "Primary Contact No is not visible");
				Assert.assertTrue(ispostLoginSerMoveOutSecondaryContactNumVisible());
				Assert.assertTrue(ispostLoginSerMoveInEmailVisible(), "Email Field is not visible");
				Assert.assertTrue(ispostLoginSerMoveInContactTypeVisibleVisible(), "Contact Type is not Visible");

				// MAILING ADDRESS
				Assert.assertTrue(ispostLoginSerMoveInCurrentAddStreetNumVisible());
				Assert.assertTrue(ispostLoginSerMoveInCurrentAddStreetNameVisible());
				Assert.assertTrue(ispostLoginSerMoveInCurrentAddUnitNoVisible());
				Assert.assertTrue(ispostLoginSerMoveInCurrentAddCityVisible());
				Assert.assertTrue(ispostLoginSerMoveInRequestStartZipCodeVisible());
				Assert.assertTrue(ispostLoginSerMoveInCurrentAddStateVisible(), "Current Add state is not visible");

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

				Assert.assertTrue(ispostLoginSerMoveInPageSaveBtnVisible());
				Assert.assertTrue(ispostLoginSerMoveInPageNexBtnVisible());
				Assert.assertTrue(ispostLoginSerMoveInPageCancelBtnVisible());

				// Enter All mandatory Field Verification pause(2000);
				clcknextbtn();
				String errMsg = getToastMessage();
				Assert.assertEquals(errMsg, serviceRequestTextProp.getPropValue("expValidationMsgSrp"));

				// create MoveOut Req
				pause(1000);
				String acc = Configuration.toString("accountNumber");
				String currentdate = DateUtil.getCurrentSystemDate("d-MMM-yy");
				String portaldate = getpostpopulatedDate();
				Assert.assertEquals(currentdate, portaldate);

				selectMoveOutDate();
				SelectAddedContactType("Home");

				populatePrimarySreetName("Fleet Street");

				populatecity("L");
				// pause(200);

				populatestate("A");
				populatereqzipcode("22222");
				pause(1000);

				clcknextbtn();
				sAssert.assertEquals(getFormpostview(), "Review and Confirm");
				Assert.assertEquals(getObjectsFormpostviewValue().get(0).getText(), acc);
				Assert.assertEquals(getObjectsFormpostviewValue().get(1).getText(), portaldate);
				Assert.assertEquals(getObjectsFormpostviewValue().get(7).getText(), "DemoInstance@yopmail.com");
				Assert.assertEquals(getObjectsFormpostviewValue().get(8).getText(), "Home");

				log.info("get Req Id from the popup");
				clcksubmit();
				String popup = serviceRequestTextProp.getPropValue("txtpopup");
				sAssert.assertTrue(getpostLoginSavedFormPopup().contains(popup));
				clickOkbutton();
				pause(5000);

			}
		}
	}

	public void verifySavedForm(SoftAssert sAssert) throws InterruptedException {
		log.info("Test Case execution for - ValidateMoveInPageObjectAndCreateReq - is Initiated");
		pause(500);
		clickServices();
		pause(1000);
		clickServicesoverview();
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
				String currentdate = DateUtil.getCurrentSystemDate("d-MMM-yy");
				String portaldate = getpostpopulatedDate();
				Assert.assertEquals(currentdate, portaldate);
				selectMoveOutDate();
				SelectAddedContactType("Home");
				populatePrimarySreetName("Fleet Street");
				populatecity("L");
				populatestate("A");
				populatereqzipcode("22222");
				pause(1000);

				log.info("click save button to save form");
				clickSaveForm();
				String actualSaveFormsTrackId = getpostLoginSavedFormPopup().split(":")[1].trim();
				clickOkbutton();
				pause(5000);

				clickSaveFormsBtn();
				log.info("verify please enter valid req id");
				clickSavedformsBtn();
				populatepostLoginSavedFormTrackReqSearch("12345678");
				String errMsg = getNoRecordstxt();
				Assert.assertEquals(errMsg, serviceRequestTextProp.getPropValue("txtNoRecords"));

//validate texts and edit info

				clearpostLoginSavedFormTrackReqSearch();
				populatepostLoginSavedFormTrackReqSearch(actualSaveFormsTrackId);
				isSavedFormsinforVisible();
				pause(2000);
				clickthreedotBtn();
				clickeditBtn();
				ExtentLogger.logInfo("Clicked edit button");
				getAddedContactType("Home");
				getPrimarySreetName("Fleet Street");
				getcity("Luke Air Force Base");
				getstate("Alberta");
				getreqzipcode("22222");
				clearreqzipcode();
				populatereqzipcode("22278");
				log.info("click save button to save form");
				clickSaveForm();
				String actualSaveFormTrackId = getpostLoginSavedFormPopup().split(":")[1].trim();
				clickOkbutton();
				pause(5000);
				ExtentLogger.logInfo("Same information exists also was able to edit the form");

			}
		}
	}

	public void verifyMoveOutFeildLevelValidation(SoftAssert sAssert) throws InterruptedException {
		log.info("Test Case execution for - verifyMoveOutFeildsErrorMessages - is Initiated");
		pause(500);
		clickServices();
		pause(1000);
		clickServicesoverview();

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

				String currentdate = DateUtil.getCurrentSystemDate("d-MMM-yy");
				String portaldate = getpostpopulatedDate();
				Assert.assertEquals(currentdate, portaldate);
				selectMoveOutDate();

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

			}
		}

	}

	public void PostLogServiceServiceTransferReq(SoftAssert sAssert) throws InterruptedException {
		log.info("Test Case execution for - preLogServiceServiceTransferReq - is Initiated");
		pause(500);
		clickServices();
		pause(1000);
		clickServicesoverview();
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

				Assert.assertTrue(ispostLoginMoveInAccountNumberVisible(), "Account Number is not visiblle");
				Assert.assertTrue(ispostLoginMoveInDateVisible(), "Date is not visible");

				// WHEN?
				Assert.assertTrue(ispostLoginMoveOutDateVisible());

				// WHERE ARE YOU MOVING TO?
				Assert.assertTrue(ispostLoginSerMoveInCurrentAddStreetNumVisible());
				Assert.assertTrue(ispostLoginSerMoveInCurrentAddStreetNameVisible());
				Assert.assertTrue(ispostLoginSerMoveInCurrentAddUnitNoVisible());
				Assert.assertTrue(ispostLoginSerMoveInCurrentAddCityVisible());
				Assert.assertTrue(ispostLoginSerMoveInRequestStartZipCodeVisible());
				Assert.assertTrue(ispostLoginSerMoveInCurrentAddStateVisible(), "Current Add state is not visible");

				// CONTACT INFORMATION
				Assert.assertTrue(ispostLoginSerMoveInFirstNameVisible(), "First name is not visible");
				Assert.assertTrue(istxtpostLoginSerMoveInLastNameVisible(), "Last name is not visible");
				Assert.assertTrue(ispostLoginSerMoveInPrimaryContactNoVisible(), "Primary Contact No is not visible");
				Assert.assertTrue(ispostLoginSerMoveInEmailVisible(), "Email Field is not visible");
				Assert.assertTrue(ispostLoginSerMoveInContactTypeVisibleVisible(), "Contact Type is not Visible");
				Assert.assertTrue(ispostLoginSTDateVisible(), "MoveIn Date is not Visible");

				// Mailing address
				isMailingCheckVisible();
				isStreetNumberVisible();
				isStreetNameVisible();
				isAptUnitVisible();
				isCityVisible();
				isStateVisible();
				isZipcodeVisible();
				isAdditionalCommentsVisible();
				isAttachmentVisible();

				// Create Transfer request
				if (getObjectChildLabelServicesTilesVisible(serviceRequestTile).equals("SERVICE TRANSFER")) {
					clickObjectChildLabelServicesTilesVisible(serviceRequestTile);
					String currentdate = DateUtil.getCurrentSystemDate("d-MMM-yy");
					String portaldate = getpostpopulatedDate();
					Assert.assertEquals(currentdate, portaldate);
					selectMoveOutDate();
					populateStreetno("2355");
					populateStname("Street");
					populateAptunit("2222");
					// populatecity1("L");
					populatestate1("A");
					selectdatein("28");
					populateZip("23442");
					SelectAddedContactType("Home");
					clickCheckbox();
					// populateStreetnum("45");
					populateStreetname("Street");
					populateAptUnit("22");
					populatestate2("A");
					populateZipcode("23444");
					clcknextbtn();
					sAssert.assertEquals(getFormpostview(), "Review and Confirm");
					pause(1000);
					String acc = Configuration.toString("accountNumber");
//			populateAccountNum(acc);
					Assert.assertEquals(getObjectsFormpostviewValue().get(0).getText(), acc);
					Assert.assertEquals(getObjectsFormpostviewValue().get(1).getText(), portaldate);
					Assert.assertEquals(getObjectsFormpostviewValue().get(7).getText(), "DemoInstance@yopmail.com");
					Assert.assertEquals(getObjectsFormpostviewValue().get(8).getText(), "Home");

					log.info("get Req Id from the popup");
					clcksubmit();
					String popup = serviceRequestTextProp.getPropValue("txtpopup");
					sAssert.assertTrue(getpostLoginSavedFormPopup().contains(popup));
					clickOkbutton();
					pause(5000);
					ExtentLogger.logInfo("Successfully Submitted a form");

					// Saved forms

					if (getObjectChildLabelServicesTilesVisible(serviceRequestTile).equals("SERVICE TRANSFER")) {
						clickObjectChildLabelServicesTilesVisible(serviceRequestTile);
						selectMoveOutDate();
						populateStreetno("2355");
						populateStname("Street");
						populateAptunit("2222");
						// populatecity1("L");
						populatestate1("A");
						selectdatein("28");
						populateZip("23442");
						SelectAddedContactType("Home");
						clickCheckbox();
						// populateStreetnum("45");
						populateStreetname("Street");
						populateAptUnit("22");
						populatestate2("A");
						populateZipcode("23444");
						log.info("click save button to save form");
						clickSaveForm();
						String actualSaveFormsTrackId = getpostLoginSavedFormPopup().split(":")[1].trim();
						clickOkbutton();
						pause(5000);
						ExtentLogger.logInfo("Saved forms functionality validated sucessfully");

//validate texts and edit info

						clearpostLoginSavedFormTrackReqSearch();
						populatepostLoginSavedFormTrackReqSearch(actualSaveFormsTrackId);
						isSavedFormsinforVisible();
						pause(2000);
						clickthreedotBtn();
						clickeditBtn();
						ExtentLogger.logInfo("Clicked edit button");
						getStreetno("2355");
						getStname("Street");
						getAptunit("2222");
						getAddedContactType("Home");
						getStreetname("Street");
						getAptUnit("22");
						getstate("Alberta");
						getreqzipcode("22222");
						clearreqzipcode();
						populateZipcode("23490");
						log.info("click save button to save form");
						clickSaveForm();
						String actualSaveFormTrackId = getpostLoginSavedFormPopup().split(":")[1].trim();
						clickOkbutton();
						pause(5000);
						ExtentLogger.logInfo("Viewed that same info exists and edited transfer saved forms");
					}

				}
			}
		}
	}

	public void PostLogServiceOtherFeildsVeification(SoftAssert sAssert) throws InterruptedException {
		// Logging to the extent reports & logger
		pause(500);
		clickServices();
		pause(1000);
		clickServicesoverview();
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
				String portaldate = getpostpopulatedDate();
				Assert.assertEquals(currentdate, portaldate);

				Assert.assertTrue(ispostLoginMoveInAccountNumberVisible());
				Assert.assertTrue(ispostLoginSerMoveInFirstNameVisible());
				Assert.assertTrue(istxtpostLoginSerMoveInLastNameVisible());
				Assert.assertTrue(ispostLoginSerMoveInEmailVisible());
				Assert.assertTrue(isLockedGates());
				Assert.assertTrue(isPetToggleisvisible());
				Assert.assertTrue(isOtherFormPersonAvailableFeildVisible());
				Assert.assertTrue(ispostLoginSerMoveInPrimaryContactNoVisible());

				Assert.assertTrue(ispostLoginSerMoveInPrimaryContactNoVisible());

				// verify Disclaimer
				String distext = serviceRequestTextProp.getPropValue("txtAttachmentDisclaimer");
				sAssert.assertTrue(gettxtAttachmentDisclaimer().contains(distext));

				// Cancel,Save,Next Button

				Assert.assertTrue(ispostLoginSerMoveInPageSaveBtnVisible());
				Assert.assertTrue(ispostLoginSerMoveInPageNexBtnVisible());
				Assert.assertTrue(ispostLoginSerMoveInPageCancelBtnVisible());

//			error message 
				clcknextbtn();
				String errMsg = getToastMessage();
				Assert.assertEquals(errMsg, serviceRequestTextProp.getPropValue("expValidationMsgSrp"));

				// Send a Other Service Request
				String currentdates = DateUtil.getCurrentSystemDate("d-MMM-yy");
				String portaldates = getpostpopulatedDate();
				Assert.assertEquals(currentdate, portaldates);
				selectDates();
				populatePersonAvailable("Owner");
				clickscheduledTime();
				clickscheduledTime1();
				pause(5000);
				clickLockedGates();
				clickPetToggle();
				clcknextbtn();
				sAssert.assertEquals(getFormpostview(), "Review and Confirm");
				pause(1000);
				String acc = Configuration.toString("accountNumber");
//			populateAccountNum(acc);
				Assert.assertEquals(getObjectsFormpostviewValue().get(0).getText(), acc);
				Assert.assertEquals(getObjectsFormpostviewValue().get(1).getText(), portaldates);
				Assert.assertEquals(getObjectsFormpostviewValue().get(7).getText(), "Owner");
				Assert.assertEquals(getObjectsFormpostviewValue().get(8).getText(), "(424) 271-4361");

				log.info("get Req Id from the popup");
				clcksubmit();
				String popup = serviceRequestTextProp.getPropValue("txtpopup");
				sAssert.assertTrue(getpostLoginSavedFormPopup().contains(popup));
				clickOkbutton();
				pause(5000);

				// saved forms
				if (getObjectChildLabelServicesTilesVisible(serviceRequestTile).equals("OTHERS")) {
					clickObjectChildLabelServicesTilesVisible(serviceRequestTile);
					selectDates();
					populatePersonAvailable("Owner");
					clickscheduledTime();
					clickscheduledTime1();
					pause(5000);
					clickLockedGates();
					clickPetToggle();

					log.info("click save button to save form");
					clickSaveForm();
					String actualSaveFormsTrackId = getpostLoginSavedFormPopup().split(":")[1].trim();
					clickOkbutton();
					pause(5000);

					clickSaveFormsBtn();
					log.info("verify please enter valid req id");
					clickSavedformsBtn();

					clearpostLoginSavedFormTrackReqSearch();
					populatepostLoginSavedFormTrackReqSearch(actualSaveFormsTrackId);
					isSavedFormsinforVisible();
					pause(2000);
					break;

				}

			}

		}
	}
}

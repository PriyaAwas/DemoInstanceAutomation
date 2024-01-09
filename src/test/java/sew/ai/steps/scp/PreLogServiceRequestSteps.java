package sew.ai.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import sew.ai.api.endpoints.service.ServiceEndpoint;
import sew.ai.config.CSPConfiguration;
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
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.DateUtil;
import sew.ai.utils.JsonUtil;
import sew.ai.utils.PropertiesUtil;

import static sew.ai.helpers.props.FilePaths.FILE_UPLOAD_PATH;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PreLogServiceRequestSteps extends PreLogStartServicePage {
	private static final Logger log = LogManager.getLogger(PreLogServiceRequestSteps.class);
	public static PropertiesUtil serviceRequestTextProp;
	Map<String, Integer> registrationConfig = new HashMap<>();
	public static Map<String, RegistrationConfig> getRegistrationConfig = new LinkedHashMap<>();
	ResultSet rs;

	public PreLogServiceRequestSteps(WebDriver driver) {
		super(driver);
		serviceRequestTextProp = new PropertiesUtil(
				FilePaths.SCP_TEXT_PROPERTIES + Constants.PRE_LOG_SERVICE_REQUEST_TEXT_FILENAME);
	}

	/**
	 * This method verifies the objects on the Notification Preference page.
	 *
	 * @param softAssert verify all the soft verification.
	 * @param user       this models contains all the user details.
	 */

	public Boolean isServiceRequestPage(String url, String title) {
		Boolean isServiceRequestPage = false;
		log.info("Checking that the current page is service request  page.");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isServiceRequestPage = true;
		log.info("The current page is service request page {}: " + isServiceRequestPage);
		return isServiceRequestPage;
	}

	public void preLogServiceReqObject(SoftAssert sAssert, User user) throws SQLException {
		SoftAssert softAssert = new SoftAssert();
		String primaryEmailId = "";
		ResultSet resultSet;
		ArrayList OtpList = new ArrayList<>();
		String trackingId = "";
		registrationConfig = CSPConfiguration.initMCspConfig();
		String expFeatureName = serviceRequestTextProp.getPropValue("featureNameSrp");
		log.info("Test Case execution for - validatePreLoginServiceRequestPage - is Initiated");
		// Verifying whether the services module is displaying or not.
		if (registrationConfig.get(expFeatureName) == 1) {
			// Navigate to service turn on/off page and verify the header.
			clickLinkServices();
			pause(1000);
			isServiceRequestPage(serviceRequestTextProp.getPropValue("expServiceUrl"),
					serviceRequestTextProp.getPropValue("expServiceTitle"));
			ServiceEndpoint sc = new ServiceEndpoint();
			rs = DataBaseUtils.getResultSet(SQLQueries.getPrimaryEmailID(user.getUserName()));
			while (rs.next()) {
				primaryEmailId = rs.getString("EmailID");
			}
			trackingId = sc.getPreLoginServiceTrackRequestID(Integer.parseInt(user.getAccountNumber()),
					user.getDefaultUtilityAccNum(), primaryEmailId, Integer.parseInt(user.getUserId()));
			log.info("T62081 - Verify that the Service Request Landing page should contain all the required details");
			sAssert.assertTrue(isServicesBannerVisible(), "Service banner is visible");
			sAssert.assertEquals(getServicesBanner("src"), serviceRequestTextProp.getPropValue("urlAppendedTextSrp"));
			sAssert.assertTrue(isNewRequestTabVisible(), "New request tab is not visible");
			sAssert.assertEquals(getNewRequestTab(), serviceRequestTextProp.getPropValue("expNewRequestLabelSrp"));
			String defaultTab = getAtrributeNewRequestTab(serviceRequestTextProp.getPropValue("class"));
			Assert.assertTrue(defaultTab.contains("active"));
			sAssert.assertTrue(isSavedFormsTabVisible(), "Save form is not visible");
			sAssert.assertEquals(getSavedFormsTab(), serviceRequestTextProp.getPropValue("expSavedFormLabelSrp"));
			List<String> serviceRequestTopicLabels = new ArrayList<String>();
			List<String> serviceRequestSubtitleLabels = new ArrayList<String>();
			List<String> expReasonNameList = new ArrayList<String>();
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
			try {
				expReasonNameList = getConfiguredEVListIFromDB();
				expReasonNameList.replaceAll(String::toUpperCase);

			} catch (Exception e) {
				e.printStackTrace();
			}
			int counter = 0;
			for (String actReasonName : serviceRequestTopicLabels) {
				softAssert.assertTrue(expReasonNameList.contains(actReasonName), "Service topic name not matched");
				counter++;
			}
			clickTrackRequestBtn1();
			populateTrackReqSearch("12345678");
			clickTrackRequestBtn();
			String errMsg = getToastMessage();
			Assert.assertEquals(errMsg, serviceRequestTextProp.getPropValue("expTrackRequestIderrormsgSrp"));
			waitForToastMessageInvisibility();
			populateTrackReqSearch(trackingId);
			clickTrackRequestBtn();
			if (istxtBoxMfaOtpVisible()) {
				clickbtnCancelTrackRequestOTP();
				populateTrackReqSearch(trackingId);
				clickTrackRequestBtn();
				String errBlankOTPMsg = getToastMessage();
				Assert.assertEquals(errBlankOTPMsg,
						serviceRequestTextProp.getPropValue("expectedTrackrequestBlankotperrormsgSrp "));
				pause(5000);
				for (int maxotp = 0; maxotp < 5; maxotp++) {
					populateRequestIdBoxOTP("000000");
					clickbtnSubmitTrackRequestOTP();
					pause(5000);
					if (isTrackLinkVisible()) {
						Assert.assertEquals(getlblAuthHeaderMsg(), "Authentication Failed");
						Assert.assertEquals(getlblAuthmsg(),
								"You have exceeded the maximum number of verification attempts.");
						break;
					}
				}
				clickTrackLink();
				populateTrackReqSearch(trackingId);
				clickTrackRequestBtn();
				pause(5000);
				while (!getTxtOtpTimer().equals("0")) {
				}
				try {
					resultSet = DataBaseUtils.getResultSet(getPreLoginConnectmeTrackRequestOtp());
					while (resultSet.next()) {
						OtpList.add(resultSet.getString("otp"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				populateRequestIdBoxOTP(OtpList.get(0).toString());
				clickbtnSubmitTrackRequestOTP();
				pause(2000);
			}
		}
	}

	public void preLogServiceTrackReq(SoftAssert sAssert, User user) throws SQLException {
		registrationConfig = CSPConfiguration.initMCspConfig();
		HomeSteps homesteps = new HomeSteps(driver);
		String expFeatureName = serviceRequestTextProp.getPropValue("featureNameSrp");
		String primaryEmailId = "";
		// Logging to the extent reports & logger
		log.info(
				"C78435 | C79282 | C79294 | C79296 |C79297| C79659 | C79298|C79294| C79295| C78429 - Verify that the 'Track Request' tab shall display expected Objects");
		log.info("Test Case execution for - validateTrackRequestPage - is Initiated");
		// Verifying whether the services module is displaying or not.
		if (registrationConfig.get(expFeatureName) == 1) {
			// Navigate to service turn on/off page and verify the header.
			ServiceEndpoint sc = new ServiceEndpoint();
			rs = DataBaseUtils.getResultSet(SQLQueries.getPrimaryEmailID(user.getUserName()));
			while (rs.next()) {
				primaryEmailId = rs.getString("EmailID");
			}
			String moveInSavetrackingId = sc.getPreLoginSaveMoveInRequestID(Integer.parseInt(user.getAccountNumber()),
					user.getDefaultUtilityAccNum(), primaryEmailId, Integer.parseInt(user.getUserId()));
			clickLinkServices();
			pause(1000);
			isServiceRequestPage(serviceRequestTextProp.getPropValue("expServiceUrl"),
					serviceRequestTextProp.getPropValue("expServiceTitle"));
			List<String> serviceRequestTopicLabels = new ArrayList<String>();
			List<String> serviceRequestSubtitleLabels = new ArrayList<String>();
			List<String> expReasonNameList = new ArrayList<String>();
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
			try {
				expReasonNameList = getConfiguredEVListIFromDB();
				expReasonNameList.replaceAll(String::toUpperCase);

			} catch (Exception e) {
				e.printStackTrace();
			}
			int counter = 0;
			for (String actReasonName : serviceRequestTopicLabels) {
				Assert.assertEquals(expReasonNameList.contains(actReasonName), "Service topic name not matched");
				counter++;
			}
			Assert.assertTrue(isSavesrVisible());
			Assert.assertTrue(isMoveInNextVisible());
			clickMoveInNextBtn();
			Assert.assertEquals(getToastMessage(),
					serviceRequestTextProp.getPropValue("txtLblMandaoryErrorMessageLgp"));
			Assert.assertTrue(isServiceReasonVisible());
			Assert.assertEquals(getFirstOptionServiceReason(), "Move In");
			Assert.assertTrue(isPreLoginMoveInAccountNumberVisible());
			Assert.assertTrue(isPreLoginMoveInDateVisible());
			Assert.assertTrue(isPreLoginSerMoveInFirstNameVisible());
			Assert.assertTrue(istxtPreLoginSerMoveInLastNameVisible());
			Assert.assertTrue(isPreLoginSerMoveInEmailVisible());
			Assert.assertTrue(istxtPreLoginSerMoveInSSLVisible());
			Assert.assertTrue(isPreLoginSerMoveInContactTypeVisibleVisible());
			Assert.assertTrue(isPreLoginSerMoveInContactTypeVisible());
			Assert.assertTrue(isPreLoginSerMoveInPrimaryContactNoVisible());
			Assert.assertTrue(isPreLoginSerMoveInCurrentAddStreetNumVisible());
			driver.navigate().back();
			clickPreLoginSaveFormsSubmitBtn();
			populatePreLoginSavedFormTrackReqSearch(moveInSavetrackingId);
			clickPreLoginSaveFormsSubmitBtn();
		}
	}

	public void preLogServiceSaveReq(SoftAssert sAssert, User user) throws SQLException {
		SoftAssert softAssert = new SoftAssert();
		String primaryEmailId = "";
		registrationConfig = CSPConfiguration.initMCspConfig();
		HomeSteps homesteps = new HomeSteps(driver);
		String expFeatureName = serviceRequestTextProp.getPropValue("featureNameSrp");
		// Verifying whether the services module is displaying or not.
		if (registrationConfig.get(expFeatureName) == 1) {
			// Navigate to service turn on/off page and verify the header.
			homesteps.navigateToServicesOverview();
			isServiceRequestPage(serviceRequestTextProp.getPropValue("expServiceUrl"),
					serviceRequestTextProp.getPropValue("expServiceTitle"));
			ServiceEndpoint sc = new ServiceEndpoint();
			String saveRequesttrackingId = sc.getPreLoginSaveServiceTrackRequestID(6473, "411002248606",
					"nikhil.test@mailinator.com", 3301);
			rs = DataBaseUtils.getResultSet(SQLQueries.getPrimaryEmailID(user.getUserName()));
			while (rs.next()) {
				primaryEmailId = rs.getString("EmailID");
			}
			String trackingId = sc.getPreLoginSaveServiceTrackRequestID(Integer.parseInt(user.getAccountNumber()),
					user.getDefaultUtilityAccNum(), primaryEmailId, Integer.parseInt(user.getUserId()));
			log.info(
					"Service link on mid screen slider menu takes user to service request page verified successfully.");
			log.info("T62081 - Verify that the Service Request Landing page should contain all the required details");
			Assert.assertTrue(isServicesBannerVisible());
			sAssert.assertEquals(getServicesBanner("src"), serviceRequestTextProp.getPropValue("urlAppendedTextSrp"));
			Assert.assertTrue(isNewRequestTabVisible());
			sAssert.assertEquals(getNewRequestTab(), serviceRequestTextProp.getPropValue("expNewRequestLabelSrp"));
			Assert.assertTrue(isTrackRequestTabVisible());
			sAssert.assertEquals(isTrackRequestTabVisible(),
					serviceRequestTextProp.getPropValue("expTrackRequestLabelSrp"));
			Assert.assertTrue(isSavedFormsTabVisible());
			sAssert.assertEquals(getSavedFormsTab(), serviceRequestTextProp.getPropValue("expSavedFormLabelSrp"));
			clickSaveFormsBtn();
			populatePreLoginSavedFormTrackReqSearch("SR-565230343");
			clickPreLoginSaveFormsSubmitBtn();
			populatePreLoginSavedFormTrackReqSearch(trackingId);
			clickPreLoginSaveFormsSubmitBtn();
			populateEmailAddress(primaryEmailId);
			clickPreLoginSaveFormsNextBtn();
			pause(10000);
			sAssert.assertEquals(getFormpreview(), "Review and Confirm");
			Assert.assertEquals(getObjectsFormpreviewValue().get(0), "defaultUtilityAccNo");
			clickPreLoginSaveFormSubmit();
			pause(5000);
			String actualSaveFormsTrackId = getPreLoginSavedFormPopup().split(":")[1].trim();
			Assert.assertEquals(actualSaveFormsTrackId, trackingId);
		}
	}

	public void preLogServiceMoveOutReq(SoftAssert sAssert, User user) throws SQLException {
		String primaryEmailId = "";
		registrationConfig = CSPConfiguration.initMCspConfig();
		HomeSteps homesteps = new HomeSteps(driver);
		String expFeatureName = serviceRequestTextProp.getPropValue("featureNameSrp");
		if (registrationConfig.get(expFeatureName) == 1) {
			// Navigate to service turn on/off page and verify the header.
			ServiceEndpoint sc = new ServiceEndpoint();
			String moveOuttrackingId = sc.getPreLoginServiceTrackRequestID(6473, "411002248606",
					"nikhil.test@mailinator.com", 3301);
			rs = DataBaseUtils.getResultSet(SQLQueries.getPrimaryEmailID(user.getUserName()));
			while (rs.next()) {
				primaryEmailId = rs.getString("EmailID");
			}
			String moveInSavetrackingId = sc.getPreLoginSaveMoveInRequestID(Integer.parseInt(user.getAccountNumber()),
					user.getDefaultUtilityAccNum(), primaryEmailId, Integer.parseInt(user.getUserId()));
			homesteps.navigateToServicesOverview();
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
				if (getObjectChildLabelServicesTilesVisible(serviceRequestTile).equals("Move In")) {
					clickObjectChildLabelServicesTilesVisible(serviceRequestTile);
				}
				break;
			}
		}
		Assert.assertTrue(isServiceReasonVisible());
		Assert.assertEquals(getFirstOptionServiceReason(), "Move In");
		Assert.assertTrue(isPreLoginMoveInAccountNumberVisible());
		Assert.assertTrue(isPreLoginMoveInDateVisible());
		Assert.assertTrue(isPreLoginSerMoveInFirstNameVisible());
		Assert.assertTrue(istxtPreLoginSerMoveInLastNameVisible());
		Assert.assertTrue(isPreLoginSerMoveInContactTypeVisibleVisible());
		Assert.assertTrue(isPreLoginSerMoveInPrimaryContactNoVisible());
		Assert.assertTrue(isPreLoginSerMoveInCurrentAddStreetNumVisible());
		Assert.assertTrue(isPreLoginSerMoveInCurrentAddStreetNameVisible());
		Assert.assertTrue(isPreLoginSerMoveInCurrentAddUnitNoVisible());
		Assert.assertTrue(isPreLoginSerMoveInCurrentAddCityVisible());
		Assert.assertTrue(isPreLoginSerMoveInCurrentAddZipCodeVisible());
		DriverFactory.navigateBack();
		clickSaveFormsBtn();
		populatePreLoginSavedFormTrackReqSearch("saveMoveOuttrackingId");
		clickPreLoginSaveFormsSubmitBtn();
		clickPreLoginSaveFormsNextBtn();
		pause(10000);
	}

	public void preLogServiceServiceTransferReq(SoftAssert sAssert, User user) throws SQLException {
		SoftAssert softAssert = new SoftAssert();
		// Logging to the extent reports & logger
		String primaryEmailId = "";
		ResultSet resultSet;
		ArrayList OtpList = new ArrayList<>();
		String trackingId = "";
		registrationConfig = CSPConfiguration.initMCspConfig();
		HomeSteps homesteps = new HomeSteps(driver);
		Map<String, Integer> mCspConfig = new LinkedHashMap<>();
		String expFeatureName = serviceRequestTextProp.getPropValue("featureNameSrp");
		if (mCspConfig.get(expFeatureName) == 1) {
			// Navigate to service turn on/off page and verify the header.
			ServiceEndpoint sc = new ServiceEndpoint();
			String serviceTransfertrackingId = sc.getPreLoginServiceTrackRequestID(6473, "411002248606",
					"nikhil.test@mailinator.com", 3301);
			rs = DataBaseUtils.getResultSet(SQLQueries.getPrimaryEmailID(user.getUserId()));
			while (rs.next()) {
				primaryEmailId = rs.getString("EmailID");
			}
			clickLinkServices();
			List<WebElement> serviceRequestsTiles = getObjectsServicesTiles();
			for (WebElement serviceRequestTile : serviceRequestsTiles) {
				WebElement childImgLeftIconElement = objectChildimgLeftIcon();
				WebElement childLblTopicElement = getChildLblTopicLabel();
				WebElement childLblSubtitle = getchildLblSubTitle();

				if (childLblTopicElement.getText().equalsIgnoreCase("Service Transfer")) {
					click(childLblTopicElement);
					clickMoveInNextBtn();
					break;
				}
				isServiceReasonVisible();
				Assert.assertEquals(getFirstOptionServiceReason(), "Service Transfer");
				Assert.assertTrue(isPreLoginMoveInAccountNumberVisible());
				Assert.assertTrue(isPreLoginSerMoveInFirstNameVisible());
				Assert.assertTrue(istxtPreLoginSerMoveInLastNameVisible());
				Assert.assertTrue(isPreLoginSerMoveInEmailVisible());
				Assert.assertTrue(isPreLoginSerMoveInContactTypeVisibleVisible());
				Assert.assertTrue(isPreLoginSerMoveInPrimaryContactNoVisible());
				driver.navigate().back();
				clickSaveFormsBtn();
				populatePreLoginSavedFormTrackReqSearch(serviceTransfertrackingId);
				// Invalid Attachment
				String invalidAttachmentFileName = "Invalid";
				addAttachmentToChooseFile(FILE_UPLOAD_PATH + invalidAttachmentFileName);
				clickPreLoginSaveFormsNextBtn();
				String invalidAttachedFileName = getToastMessage();
				Assert.assertEquals(invalidAttachedFileName,
						serviceRequestTextProp.getPropValue("txtLblInvalidFileFormatMsgSrp"));
				waitForToastMessageInvisibility();
				// Attach valid file type
				populatePreLoginSavedFormTrackReqSearch(serviceTransfertrackingId);
				String validAttachmentFileName = "meter-reading.jpg";
				addAttachmentToChooseFile(FILE_UPLOAD_PATH + validAttachmentFileName);
				clickPreLoginSaveFormsNextBtn();
				pause(10000);
				String actualSaveFormsTrackId = getPreLoginSavedFormPopup().split(":")[1].trim();
				Assert.assertEquals(actualSaveFormsTrackId, serviceTransfertrackingId);
			}
		}
	}

	public void preLogServiceOtherReq(SoftAssert sAssert, User user) throws SQLException {
		SoftAssert softAssert = new SoftAssert();
		// Logging to the extent reports & logger
		String primaryEmailId = "";
		ResultSet resultSet;
		ArrayList OtpList = new ArrayList<>();
		String trackingId = "";
		registrationConfig = CSPConfiguration.initMCspConfig();
		HomeSteps homesteps = new HomeSteps(driver);
		Map<String, Integer> mCspConfig = new LinkedHashMap<>();
		String expFeatureName = serviceRequestTextProp.getPropValue("featureNameSrp");
		if (mCspConfig.get(expFeatureName) == 1) {
			// Navigate to service turn on/off page and verify the header.
			ServiceEndpoint sc = new ServiceEndpoint();
			String otherReqtrackingId = sc.getPreLoginServiceTrackRequestID(6473, "411002248606",
					"nikhil.test@mailinator.com", 3301);
			rs = DataBaseUtils.getResultSet(SQLQueries.getPrimaryEmailID(user.getUserId()));
			while (rs.next()) {
				primaryEmailId = rs.getString("EmailID");
			}
			// String saveServiceTranfertrackingId =
			// sc.getPreLoginSaveServiceTrackRequestID(Integer.parseInt(accountNumber),defaultUtilityAccNo,primaryEmailId,Integer.parseInt(userId));
			clickLinkServices();
			List<WebElement> serviceRequestsTiles = getObjectsServicesTiles();
			for (WebElement serviceRequestTile : serviceRequestsTiles) {
				WebElement childImgLeftIconElement = objectChildimgLeftIcon();
				WebElement childLblTopicElement = getChildLblTopicLabel();
				WebElement childLblSubtitle = getchildLblSubTitle();
				if (childLblTopicElement.getText().equalsIgnoreCase("Others")) {
					click(childLblTopicElement);
					clickMoveInNextBtn();
					break;
				}
				isServiceReasonVisible();
				Assert.assertEquals(getFirstOptionServiceReason(), "Others");
				Assert.assertTrue(isPreLoginMoveInAccountNumberVisible());
				Assert.assertTrue(isPreLoginSerMoveInFirstNameVisible());
				Assert.assertTrue(istxtPreLoginSerMoveInLastNameVisible());
				Assert.assertTrue(isPreLoginSerMoveInEmailVisible());
				driver.navigate().back();
				clickSaveFormsBtn();
			}
		}
	}

	public static String getPreLoginConnectmeTrackRequestOtp() {
		// String regDataQuery = "select module_name FROM MODULE WHERE STATUS = 1";
		String regDataQuery = "Select top 1 * from Otp_request Order by created_date DESC";
		return regDataQuery;
	}

	/**
	 * This method is used to get the reason name list from the database.
	 *
	 * @return
	 * @throws Exception
	 */
	public List<String> getConfiguredEVListIFromDB() throws Exception {
		String serviceReasonNameQuery = SQLQueries.getPreLoginReasonNameList();
		ResultSet reasonNameResultSet = DataBaseUtils.getResultSet(serviceReasonNameQuery);
		List<String> serviceReasonName = new ArrayList<String>();
		while (reasonNameResultSet.next()) {
			String evName = reasonNameResultSet.getString(1).trim();
			serviceReasonName.add(evName);
		}
		return serviceReasonName;
	}
}

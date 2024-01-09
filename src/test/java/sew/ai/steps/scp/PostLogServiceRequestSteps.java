package sew.ai.steps.scp;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static sew.ai.helpers.props.FilePaths.FILE_UPLOAD_PATH;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.reactivex.rxjava3.internal.operators.maybe.MaybeContains;
import sew.ai.api.endpoints.service.ServiceEndpoint;
import sew.ai.config.CSPConfiguration;
import sew.ai.driver.DriverFactory;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.RegistrationConfig;
import sew.ai.models.User;
import sew.ai.pageObjects.scp.PostLogStartServicePage;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.PropertiesUtil;

public class PostLogServiceRequestSteps extends PostLogStartServicePage {
	private static final Logger log = LogManager.getLogger(PostLogServiceRequestSteps.class);
	public static PropertiesUtil serviceRequestTextProp;
	Map<String, Integer> registrationConfig = new HashMap<>();
	public static Map<String, RegistrationConfig> getRegistrationConfig = new LinkedHashMap<>();
	ResultSet rs;

	public PostLogServiceRequestSteps(WebDriver driver) {
		super(driver);
		serviceRequestTextProp = new PropertiesUtil(
				FilePaths.SCP_TEXT_PROPERTIES + Constants.POST_LOG_SERVICE_REQUEST_TEXT_FILENAME);
	}

	/**
	 * This method verifies the objects on the Notification postference page.
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

	public void postLogServiceReqObject(SoftAssert sAssert, User user) throws SQLException {
		SoftAssert softAssert = new SoftAssert();
		// Logging to the extent reports & logger
		String primaryEmailId = "";
		registrationConfig = CSPConfiguration.initMCspConfig();
		String expFeatureName = serviceRequestTextProp.getPropValue("featureNameSrp");
		log.info("Test Case execution for - validatepostLoginServiceRequestPage - is Initiated");
		// Verifying whether the services module is displaying or not.
		if (registrationConfig.get(expFeatureName) == 1) {
			// Navigate to service turn on/off page and verify the header.
			navigateToServicesOverview();
			isServiceRequestPage(serviceRequestTextProp.getPropValue("expServiceUrl"),
					serviceRequestTextProp.getPropValue("expServiceTitle"));
			ServiceEndpoint sc = new ServiceEndpoint();
			rs = DataBaseUtils.getResultSet(SQLQueries.getPrimaryEmailID(user.getUserId()));
			while (rs.next()) {
				primaryEmailId = rs.getString("EmailID");
			}
			String trackingId = sc.getServiceTrackRequestID(user.getJwtToken(),
					Integer.parseInt(user.getAccountNumber()), user.getDefaultUtilityAccNum(), primaryEmailId,
					Integer.parseInt(user.getUserId()));
			log.info("T62081 - Verify that the Service Request Landing page should contain all the required details");
			sAssert.assertTrue(isNewRequestTabVisible(), "New request tab is not visible");
			sAssert.assertEquals(getNewRequestTab(), serviceRequestTextProp.getPropValue("expNewRequestLabelSrp"));
			String defaultTab = getAtrributeNewRequestTab();
			Assert.assertTrue(defaultTab.contains("active"));
			sAssert.assertTrue(isSavedFormsTabVisible(), "Save form is not visible");
			sAssert.assertEquals(getSavedFormsTab(), serviceRequestTextProp.getPropValue("expSavedFormLabelSrp"));
			List<String> serviceRequestTopicLabels = new ArrayList<String>();
			List<String> serviceRequestSubtitleLabels = new ArrayList<String>();
			List<String> expReasonNameList = new ArrayList<String>();
			List<WebElement> serviceRequestsTiles = getObjectsServicesTiles();
			for (WebElement serviceRequestTile : serviceRequestsTiles) {
				WebElement childImgLeftIconElement = objectChildimgLeftIcon();
				WebElement childLblTopicElement = serviceRequestTile.findElement(By.cssSelector("h2"));
				WebElement childLblSubtitle = getChildLblTopicLabel();
				childImgLeftIconElement.isDisplayed();
				childLblTopicElement.isDisplayed();
				childLblSubtitle.isDisplayed();
				serviceRequestTopicLabels.add(childLblTopicElement.getText().trim());
				serviceRequestSubtitleLabels.add(childLblSubtitle.getText().trim());
				pause(5000);
			}
			try {
				expReasonNameList = getConfiguredEVListIFromDB();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int counter = 0;
			boolean found = false;
			for (String actReasonName : serviceRequestTopicLabels) {
				for (String expReasonName : expReasonNameList) {
					if (actReasonName.equals(expReasonName.toUpperCase())) {
						found = true;
						break;
					}
				}
				softAssert.assertTrue(found, "Service topic name found on ui not found in the database");
				counter++;
			}
			DriverFactory.navigateBack();
			clickSaveFormsBtn();
			populatepostLoginSavedFormTrackReqSearch(trackingId);
			String requestId = gettdSaveformRequestId();
			Assert.assertEquals(requestId, trackingId);
		}
	}

	public void postLogTrackReqPage(SoftAssert sAssert, User user) throws SQLException {
		registrationConfig = CSPConfiguration.initMCspConfig();
		String expFeatureName = serviceRequestTextProp.getPropValue("featureNameSrp");
		String primaryEmailId = "";
		log.info("Test Case execution for - validateTrackRequestPage - is Initiated");
		// Verifying whether the services module is displaying or not.
		if (registrationConfig.get(expFeatureName) == 1) {
			// Navigate to service turn on/off page and verify the header.
			ServiceEndpoint sc = new ServiceEndpoint();
			rs = DataBaseUtils.getResultSet(SQLQueries.getPrimaryEmailID(user.getUserName()));
			while (rs.next()) {
				primaryEmailId = rs.getString("EmailID");
			}
			String trackingId = sc.getServiceTrackRequestID(user.getJwtToken(),
					Integer.parseInt(user.getAccountNumber()), user.getDefaultUtilityAccNum(), primaryEmailId,
					Integer.parseInt(user.getUserId()));
			navigateToServicesOverview();
			isServiceRequestPage(serviceRequestTextProp.getPropValue("expServiceUrl"),
					serviceRequestTextProp.getPropValue("expServiceTitle"));
			List<WebElement> serviceRequestsTiles = getObjectsServicesTiles();
			// Verifying whether the services module is displaying or not.
			if (registrationConfig.get(expFeatureName) == 1) {
				// Navigate to track request tab
				clickTrackRequestTab();
				String attributeClassActive = getTrackRequestTabAttribute();
				getTrackRequestTabAttribute().contains("class");
				// Validate track request page.
				istxtSearchRequestVisible();
				populatetxtSearchRequest(trackingId);
				// Verifying track request grid header.getTdRequestId()
				List<WebElement> gridHeaderTextElements = getGridHeader();
				for (WebElement gridHeaderElement : gridHeaderTextElements) {
					String actGridHeaderLabel = gridHeaderElement.getText().trim();
					boolean found = false;
					List<String> expGridHeaderLabels = serviceRequestTextProp
							.getMultipleRbTextValues("gridHeaderLabelsSrp");
					for (String expGridHeaderLabel : expGridHeaderLabels) {
						if (expGridHeaderLabel.toUpperCase().trim().equals(actGridHeaderLabel)) {
							found = true;
							break;
						}
					}
					assertTrue(found, "Column header : " + actGridHeaderLabel + " not found");
				}
			}
		}
	}

	public void postLogServiceSaveReq(SoftAssert sAssert, User user) throws SQLException {
		SoftAssert softAssert = new SoftAssert();
		String primaryEmailId = "";
		registrationConfig = CSPConfiguration.initMCspConfig();
		String expFeatureName = serviceRequestTextProp.getPropValue("featureNameSrp");
		// Verifying whether the services module is displaying or not.
		if (registrationConfig.get(expFeatureName) == 1) {
			// Navigate to service turn on/off page and verify the header.
			navigateToServicesOverview();
			isServiceRequestPage(serviceRequestTextProp.getPropValue("expServiceUrl"),
					serviceRequestTextProp.getPropValue("expServiceTitle"));
			ServiceEndpoint sc = new ServiceEndpoint();
			rs = DataBaseUtils.getResultSet(SQLQueries.getPrimaryEmailID(user.getUserId()));
			while (rs.next()) {
				primaryEmailId = rs.getString("EmailID");
			}
			String trackingId = sc.getSaveServiceTrackRequestID(user.getJwtToken(),
					Integer.parseInt(user.getAccountNumber()), user.getDefaultUtilityAccNum(), primaryEmailId,
					Integer.parseInt(user.getUserId()));
			navigateToServicesOverview();
			log.info(
					"Service link on mid screen slider menu takes user to service request page verified successfully.");
			Assert.assertTrue(isNewRequestTabVisible());
			sAssert.assertEquals(getNewRequestTab(), serviceRequestTextProp.getPropValue("expNewRequestLabelSrp"));
			Assert.assertTrue(isTrackRequestTabVisible());
			sAssert.assertEquals(isTrackRequestTabVisible(),
					serviceRequestTextProp.getPropValue("expTrackRequestLabelSrp"));
			Assert.assertTrue(isSavedFormsTabVisible());
			sAssert.assertEquals(getSavedFormsTab(), serviceRequestTextProp.getPropValue("expSavedFormLabelSrp"));
			clickSaveFormsBtn();
			populatepostLoginSavedFormTrackReqSearch("SR-565230343");
			populatepostLoginSavedFormTrackReqSearch(trackingId);
			populateEmailAddress(primaryEmailId);
			clickpostLoginSaveFormsNextBtn();
			pause(10000);
			sAssert.assertEquals(getFormpostview(), "Review and Confirm");
			Assert.assertEquals(getObjectsFormpostviewValue().get(0), "defaultUtilityAccNo");
			clickpostLoginSaveFormSubmit();
			pause(5000);
			String actualSaveFormsTrackId = getpostLoginSavedFormPopup().split(":")[1].trim();
			Assert.assertEquals(actualSaveFormsTrackId, trackingId);
		}
	}

	public void postLogServiceMoveOutReq(SoftAssert sAssert, User user) throws SQLException {
		String primaryEmailId = "";
		registrationConfig = CSPConfiguration.initMCspConfig();
		String expFeatureName = serviceRequestTextProp.getPropValue("featureNameSrp");
		// Verifying whether the services module is displaying or not.
		if (registrationConfig.get(expFeatureName) == 1) {
			// Navigate to service turn on/off page and verify the header.
			ServiceEndpoint sc = new ServiceEndpoint();
			rs = DataBaseUtils.getResultSet(SQLQueries.getPrimaryEmailID(user.getUserId()));
			while (rs.next()) {
				primaryEmailId = rs.getString("EmailID");
			}
			String moveOutSavetrackingRequestId = sc.getPostLoginSaveMoveOutRequestID(user.getJwtToken(),
					Integer.parseInt(user.getAccountNumber()), user.getDefaultUtilityAccNum(), primaryEmailId,
					Integer.parseInt(user.getUserId()));
			String moveOuttrackingRequestId = sc.getPostLoginMoveOutRequestID(user.getJwtToken(),
					Integer.parseInt(user.getAccountNumber()), user.getDefaultUtilityAccNum(), primaryEmailId,
					Integer.parseInt(user.getUserId()));
			navigateToServicesOverview();
			isServiceRequestPage(serviceRequestTextProp.getPropValue("expServiceUrl"),
					serviceRequestTextProp.getPropValue("expServiceTitle"));
			sAssert.assertTrue(isNewRequestTabVisible(), "New request tab is not visible");
			sAssert.assertEquals(getNewRequestTab(), serviceRequestTextProp.getPropValue("expNewRequestLabelSrp"));
			String defaultTab = getAtrributeNewRequestTab();
			Assert.assertTrue(defaultTab.contains("active"));
			sAssert.assertTrue(isSavedFormsTabVisible(), "Save form is not visible");
			sAssert.assertEquals(getSavedFormsTab(), serviceRequestTextProp.getPropValue("expSavedFormLabelSrp"));
			;
			List<String> expReasonNameList = new ArrayList<String>();
			List<WebElement> serviceRequestsTiles = getObjectsServicesTiles();
			for (WebElement serviceRequestTile : serviceRequestsTiles) {
				WebElement childImgLeftIconElement = objectChildimgLeftIcon();
				WebElement childLblTopicElement = serviceRequestTile.findElement(By.cssSelector("h2"));
				WebElement childLblSubtitle = getChildLblTopicLabel();
				childImgLeftIconElement.isDisplayed();
				childLblTopicElement.isDisplayed();
				childLblSubtitle.isDisplayed();
				if (childLblTopicElement.getText().equalsIgnoreCase("Move Out")) {
					click(childLblTopicElement);
					clickMoveInNextBtn();
					break;
				}
				pause(5000);
			}
			try {
				expReasonNameList = getConfiguredEVListIFromDB();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Assert.assertTrue(isServiceReasonVisible());
			Assert.assertEquals(getFirstOptionServiceReason(), "Move Out");
			Assert.assertTrue(ispostLoginMoveInAccountNumberVisible());
			Assert.assertTrue(ispostLoginMoveInDateVisible());
			Assert.assertTrue(ispostLoginSerMoveInFirstNameVisible());
			Assert.assertTrue(istxtpostLoginSerMoveInLastNameVisible());
			Assert.assertTrue(ispostLoginSerMoveInContactTypeVisibleVisible());
			Assert.assertTrue(ispostLoginSerMoveInPrimaryContactNoVisible());
			Assert.assertTrue(ispostLoginSerMoveInCurrentAddStreetNumVisible());
			Assert.assertTrue(ispostLoginSerMoveInCurrentAddStreetNameVisible());
			Assert.assertTrue(ispostLoginSerMoveInCurrentAddUnitNoVisible());
			Assert.assertTrue(ispostLoginSerMoveInCurrentAddCityVisible());
			Assert.assertTrue(ispostLoginSerMoveInCurrentAddZipCodeVisible());
			DriverFactory.navigateBack();
			clickSaveFormsBtn();
			populatepostLoginSavedFormTrackReqSearch(moveOutSavetrackingRequestId);
			String requestId = gettdSaveformRequestId();
			Assert.assertEquals(requestId, moveOutSavetrackingRequestId);
			pause(1000);
			clickTrackRequestTab();
			populatetxtTrackReqSearchSearch(moveOuttrackingRequestId);
		}
	}

	public void postLogServiceServiceTransferReq(SoftAssert sAssert, User user) throws SQLException {
		SoftAssert softAssert = new SoftAssert();
		// Logging to the extent reports & logger
		String primaryEmailId = "";
		registrationConfig = CSPConfiguration.initMCspConfig();
		Map<String, Integer> mCspConfig = new LinkedHashMap<>();
		String expFeatureName = serviceRequestTextProp.getPropValue("featureNameSrp");
		if (registrationConfig.get(expFeatureName) == 1) {
			// Navigate to service turn on/off page and verify the header.
			ServiceEndpoint sc = new ServiceEndpoint();
			rs = DataBaseUtils.getResultSet(SQLQueries.getPrimaryEmailID(user.getUserId()));
			while (rs.next()) {
				primaryEmailId = rs.getString("EmailID");
			}
			String serviceTransferSavetrackingRequestId = sc.getPostLoginSaveServiceTransferRequestID(
					user.getJwtToken(), Integer.parseInt(user.getAccountNumber()), user.getDefaultUtilityAccNum(),
					primaryEmailId, Integer.parseInt(user.getUserId()));
			String serviceTransfertrackingRequestId = sc.getPostLoginServiceTransferRequestID(user.getJwtToken(),
					Integer.parseInt(user.getAccountNumber()), user.getDefaultUtilityAccNum(), primaryEmailId,
					Integer.parseInt(user.getUserId()));
			navigateToServicesOverview();
			isServiceRequestPage(serviceRequestTextProp.getPropValue("expServiceUrl"),
					serviceRequestTextProp.getPropValue("expServiceTitle"));
			sAssert.assertTrue(isNewRequestTabVisible(), "New request tab is not visible");
			sAssert.assertEquals(getNewRequestTab(), serviceRequestTextProp.getPropValue("expNewRequestLabelSrp"));
			String defaultTab = getAtrributeNewRequestTab();
			Assert.assertTrue(defaultTab.contains("active"));
			sAssert.assertTrue(isSavedFormsTabVisible(), "Save form is not visible");
			sAssert.assertEquals(getSavedFormsTab(), serviceRequestTextProp.getPropValue("expSavedFormLabelSrp"));
			List<String> serviceRequestTopicLabels = new ArrayList<String>();
			List<String> serviceRequestSubtitleLabels = new ArrayList<String>();
			List<String> expReasonNameList = new ArrayList<String>();
			List<WebElement> serviceRequestsTiles = getObjectsServicesTiles();
			for (WebElement serviceRequestTile : serviceRequestsTiles) {
				WebElement childImgLeftIconElement = objectChildimgLeftIcon();
				WebElement childLblTopicElement = serviceRequestTile.findElement(By.cssSelector("h2"));
				WebElement childLblSubtitle = getChildLblTopicLabel();
				childImgLeftIconElement.isDisplayed();
				childLblTopicElement.isDisplayed();
				childLblSubtitle.isDisplayed();
				if (childLblTopicElement.getText().equalsIgnoreCase("Service Transfer")) {
					click(childLblTopicElement);
					clickMoveInNextBtn();
					break;
				}
				pause(5000);
				serviceRequestTopicLabels.add(childLblTopicElement.getText().trim());
				serviceRequestSubtitleLabels.add(childLblSubtitle.getText().trim());
			}
			try {
				expReasonNameList = getConfiguredEVListIFromDB();
			} catch (Exception e) {
				e.printStackTrace();
			}
			isServiceReasonVisible();
			Assert.assertEquals(getFirstOptionServiceReason(), "Service Transfer");
			Assert.assertTrue(ispostLoginMoveInAccountNumberVisible());
			Assert.assertTrue(ispostLoginSerMoveInFirstNameVisible());
			Assert.assertTrue(istxtpostLoginSerMoveInLastNameVisible());
			Assert.assertTrue(ispostLoginSerMoveInEmailVisible());
			Assert.assertTrue(ispostLoginSerMoveInContactTypeVisibleVisible());
			Assert.assertTrue(ispostLoginSerMoveInPrimaryContactNoVisible());
			driver.navigate().back();
			clickSaveFormsBtn();
			populatepostLoginSavedFormTrackReqSearch(serviceTransferSavetrackingRequestId);
			clickBtnMorevertnavbar();
			clickbtnEditConnect();
			pause(1000);
			// Invalid Attachment
//				String invalidAttachmentFileName = "Invalid";
//	 	        addAttachmentToChooseFile(FILE_UPLOAD_PATH + invalidAttachmentFileName);
//	 	        clickpostLoginSaveFormsNextBtn();
//	 	         String invalidAttachedFileName = getToastMessage();
//	 	     	Assert.assertEquals(invalidAttachedFileName , serviceRequestTextProp.getPropValue("txtLblInvalidFileFormatMsgSrp"));
//	 	     	waitForToastMessageInvisibility();
			// Attach valid file type
			String validAttachmentFileName = "meter-reading.jpg";
			addAttachmentToChooseFile(FILE_UPLOAD_PATH + validAttachmentFileName);
			clickpostLoginSaveFormsNextBtn();
			clickSaveFormsSubmitBtn();
			pause(10000);
			String actualSaveFormsTrackId = getpostLoginSavedFormPopup().split(":")[1].trim();
			Assert.assertEquals(actualSaveFormsTrackId, serviceTransferSavetrackingRequestId);
		}
	}

	public void postLogServiceOtherReq(SoftAssert sAssert, User user) throws SQLException {
		String primaryEmailId = "";
		registrationConfig = CSPConfiguration.initMCspConfig();
		String expFeatureName = serviceRequestTextProp.getPropValue("featureNameSrp");
		// Verifying whether the services module is displaying or not.
		if (registrationConfig.get(expFeatureName) == 1) {
			// Navigate to service turn on/off page and verify the header.
			ServiceEndpoint sc = new ServiceEndpoint();
			rs = DataBaseUtils.getResultSet(SQLQueries.getPrimaryEmailID(user.getUserId()));
			while (rs.next()) {
				primaryEmailId = rs.getString("EmailID");
			}
			String othersSavetrackingRequestId = sc.getSaveServiceTrackRequestID(user.getJwtToken(),
					Integer.parseInt(user.getAccountNumber()), user.getDefaultUtilityAccNum(), primaryEmailId,
					Integer.parseInt(user.getUserId()));
			String otherstrackingRequestId = sc.getServiceTrackRequestID(user.getJwtToken(),
					Integer.parseInt(user.getAccountNumber()), user.getDefaultUtilityAccNum(), primaryEmailId,
					Integer.parseInt(user.getUserId()));
			navigateToServicesOverview();
			isServiceRequestPage(serviceRequestTextProp.getPropValue("expServiceUrl"),
					serviceRequestTextProp.getPropValue("expServiceTitle"));
			sAssert.assertTrue(isNewRequestTabVisible(), "New request tab is not visible");
			sAssert.assertEquals(getNewRequestTab(), serviceRequestTextProp.getPropValue("expNewRequestLabelSrp"));
			String defaultTab = getAtrributeNewRequestTab();
			Assert.assertTrue(defaultTab.contains("active"));
			sAssert.assertTrue(isSavedFormsTabVisible(), "Save form is not visible");
			sAssert.assertEquals(getSavedFormsTab(), serviceRequestTextProp.getPropValue("expSavedFormLabelSrp"));
			List<String> expReasonNameList = new ArrayList<String>();
			List<String> serviceRequestTopicLabels = new ArrayList<String>();
			List<String> serviceRequestSubtitleLabels = new ArrayList<String>();
			List<WebElement> serviceRequestsTiles = getObjectsServicesTiles();
			for (WebElement serviceRequestTile : serviceRequestsTiles) {
				WebElement childImgLeftIconElement = objectChildimgLeftIcon();
				WebElement childLblTopicElement = serviceRequestTile.findElement(By.cssSelector("h2"));
				WebElement childLblSubtitle = getChildLblTopicLabel();
				childImgLeftIconElement.isDisplayed();
				childLblTopicElement.isDisplayed();
				childLblSubtitle.isDisplayed();
				if (childLblTopicElement.getText().equalsIgnoreCase("Others")) {
					click(childLblTopicElement);
					pause(1000);
					clickMoveInNextBtn();
					break;
				}
				serviceRequestTopicLabels.add(childLblTopicElement.getText().trim());
				serviceRequestSubtitleLabels.add(childLblSubtitle.getText().trim());
				pause(5000);
			}
			try {
				expReasonNameList = getConfiguredEVListIFromDB();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int counter = 0;
			boolean found = false;
			for (String actReasonName : serviceRequestTopicLabels) {
				for (String expReasonName : expReasonNameList) {
					if (actReasonName.equals(expReasonName.toUpperCase())) {
						found = true;
						break;
					}
				}
				Assert.assertEquals(found, "Service topic name found on ui not found in the database");
				counter++;
			}
			isServiceReasonVisible();
			Assert.assertEquals(getFirstOptionServiceReason(), "Others");
			Assert.assertTrue(ispostLoginMoveInAccountNumberVisible());
			Assert.assertTrue(ispostLoginSerMoveInFirstNameVisible());
			Assert.assertTrue(istxtpostLoginSerMoveInLastNameVisible());
			Assert.assertTrue(ispostLoginSerMoveInEmailVisible());
			driver.navigate().back();
			clickSaveFormsBtn();
			DriverFactory.navigateBack();
			clickSaveFormsBtn();
			populatepostLoginSavedFormTrackReqSearch(othersSavetrackingRequestId);
			String requestId = gettdSaveformRequestId();
			Assert.assertEquals(requestId, othersSavetrackingRequestId);
			pause(1000);
			clickTrackRequestTab();
			populatetxtTrackReqSearchSearch(otherstrackingRequestId);
		}
	}

	public void postLoginMoveInPage(SoftAssert sAssert, User user) throws SQLException {
		String primaryEmailId = "";
		registrationConfig = CSPConfiguration.initMCspConfig();
		HomeSteps homesteps = new HomeSteps(driver);
		String expFeatureName = serviceRequestTextProp.getPropValue("featureNameSrp");
		// Verifying whether the services module is displaying or not.
		if (registrationConfig.get(expFeatureName) == 1) {
			// Navigate to service turn on/off page and verify the header.
			ServiceEndpoint sc = new ServiceEndpoint();
			rs = DataBaseUtils.getResultSet(SQLQueries.getPrimaryEmailID(user.getUserId()));
			while (rs.next()) {
				primaryEmailId = rs.getString("EmailID");
			}
			String moveInSavetrackingRequestId = sc.getPostLoginSaveMoveInRequestID(user.getJwtToken(),
					Integer.parseInt(user.getAccountNumber()), user.getDefaultUtilityAccNum(), primaryEmailId,
					Integer.parseInt(user.getUserId()));
			String moveIntrackingRequestId = sc.getPostLoginMoveInRequestID(user.getJwtToken(),
					Integer.parseInt(user.getAccountNumber()), user.getDefaultUtilityAccNum(), primaryEmailId,
					Integer.parseInt(user.getUserId()));
			navigateToServicesOverview();
			isServiceRequestPage(serviceRequestTextProp.getPropValue("expServiceUrl"),
					serviceRequestTextProp.getPropValue("expServiceTitle"));
			sAssert.assertTrue(isNewRequestTabVisible(), "New request tab is not visible");
			sAssert.assertEquals(getNewRequestTab(), serviceRequestTextProp.getPropValue("expNewRequestLabelSrp"));
			String defaultTab = getAtrributeNewRequestTab();
			Assert.assertTrue(defaultTab.contains("active"));
			sAssert.assertTrue(isSavedFormsTabVisible(), "Save form is not visible");
			sAssert.assertEquals(getSavedFormsTab(), serviceRequestTextProp.getPropValue("expSavedFormLabelSrp"));
			List<String> serviceRequestTopicLabels = new ArrayList<String>();
			List<String> serviceRequestSubtitleLabels = new ArrayList<String>();
			List<String> expReasonNameList = new ArrayList<String>();
			List<WebElement> serviceRequestsTiles = getObjectsServicesTiles();
			for (WebElement serviceRequestTile : serviceRequestsTiles) {
				WebElement childImgLeftIconElement = objectChildimgLeftIcon();
				WebElement childLblTopicElement = serviceRequestTile.findElement(By.cssSelector("h2"));
				WebElement childLblSubtitle = getChildLblTopicLabel();
				childImgLeftIconElement.isDisplayed();
				childLblTopicElement.isDisplayed();
				childLblSubtitle.isDisplayed();
				if (childLblTopicElement.getText().equalsIgnoreCase("Move In")) {
					click(childLblTopicElement);
					clickMoveInNextBtn();
					break;
				}
				childLblSubtitle.isDisplayed();
				serviceRequestTopicLabels.add(childLblTopicElement.getText().trim());
				serviceRequestSubtitleLabels.add(childLblSubtitle.getText().trim());
			}
			try {
				expReasonNameList = getConfiguredEVListIFromDB();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int counter = 0;
			boolean found = false;
			for (String actReasonName : serviceRequestTopicLabels) {
				for (String expReasonName : expReasonNameList) {
					if (actReasonName.equals(expReasonName.toUpperCase())) {
						found = true;
						break;
					}
				}
				Assert.assertEquals(found, "Service topic name found on ui not found in the database");
				counter++;
			}
			isServiceReasonVisible();
			Assert.assertEquals(getFirstOptionServiceReason(), "Move In");
			Assert.assertTrue(ispostLoginMoveInAccountNumberVisible());
			Assert.assertTrue(ispostLoginSerMoveInFirstNameVisible());
			Assert.assertTrue(istxtpostLoginSerMoveInLastNameVisible());
			Assert.assertTrue(ispostLoginSerMoveInEmailVisible());
			ispostLoginMoveInDateVisible();
			ispostLoginSerMoveInEmailVisible();
			ispostLoginSerMoveInContactTypeVisibleVisible();
			ispostLoginSerMoveInContactTypeVisible();
			DriverFactory.navigateBack();
			clickSaveFormsBtn();
			populatepostLoginSavedFormTrackReqSearch(moveInSavetrackingRequestId);
			String requestId = gettdSaveformRequestId();
			Assert.assertEquals(requestId, moveInSavetrackingRequestId);
			pause(1000);
			clickTrackRequestTab();
			populatetxtTrackReqSearchSearch(moveIntrackingRequestId);
		}
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

	public static String getpostLoginConnectmeTrackRequestOtp() {
		// String regDataQuery = "select module_name FROM MODULE WHERE STATUS = 1";
		String regDataQuery = "Select top 1 * from Otp_request Order by created_date DESC";
		return regDataQuery;
	}

	// Services Navigation
	public void navigateToServicesOverview() {
		log.info("Navigating to the Services Overview page.");
		clickServiceHeaderLink();
		pause(2000);
		clickServiceOverviewLink();
		log.info("Navigated to the Services Overview page.");
	}
}

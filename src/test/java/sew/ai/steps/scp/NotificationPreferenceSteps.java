package sew.ai.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import sew.ai.config.CSPConfiguration;
import sew.ai.driver.DriverFactory;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.Customer;
import sew.ai.models.RegistrationConfig;
import sew.ai.models.User;
import sew.ai.pageObjects.scp.DashboardPage;
import sew.ai.pageObjects.scp.NotificationPreferencePage;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.DateUtil;
import sew.ai.utils.PropertiesUtil;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static sew.ai.steps.scp.SignOutSteps.signOutTextProp;

public class NotificationPreferenceSteps extends NotificationPreferencePage {
	private static final Logger log = LogManager.getLogger(NotificationPreferenceSteps.class);
	public static PropertiesUtil NotificationpreferenceTextProp;
	Map<String, Integer> registrationConfig = new HashMap<>();

	public NotificationPreferenceSteps(WebDriver driver) {
		super(driver);
		NotificationpreferenceTextProp = new PropertiesUtil(
				FilePaths.SCP_TEXT_PROPERTIES + Constants.NOTIFICATION_PREFERENCE_TXT_FILENAME);
	}

	/**
	 * This method verifies the objects on the Notification Preference page.
	 *
	 * @param softAssert verify all the soft verification.
	 * @param user       this models contains all the user details.
	 */

	public Boolean isNotificationPreferencePage(String url, String title) {
		Boolean isNotificationPreferencePage = false;
		log.info("Checking that the current page is Notification preference page.");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isNotificationPreferencePage = true;
		log.info("The current page is Notification preference page {}: " + isNotificationPreferencePage);
		return isNotificationPreferencePage;
	}

	public void NotificationPreferencePageObjects(SoftAssert sAssert) {

		registrationConfig = CSPConfiguration.initMCspConfig();
		HomeSteps homeSteps = new HomeSteps(driver);
		if (registrationConfig.get("MyAccount") == 1) {

			if (registrationConfig.get("MyAccount.NotificationPrefrence") == 1) {
				// To verify the navigation to notification preferences page.
				log.info("To verify the redirection on " + "Notification Preference page");
				homeSteps.navigateToNotificationPreferences();
				Assert.assertTrue(isNotificationPreferencePage(
						NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageUrl"),
						NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageTitle")));
				// To verify the object on the notification preference page.
				log.info("C11479 - To verify the object on " + "Notification Preference.");
				isLblNotificationsVisible();
				sAssert.assertEquals(getLevelNotification(),
						NotificationpreferenceTextProp.getPropValue("txtLblNotificationsNpp"));

				// Outage

				if (registrationConfig.get("Outages") == 1) {
					isLblOutageVisible();
					sAssert.assertEquals(getLevelOutage(),
							NotificationpreferenceTextProp.getPropValue("txtLblOutageNpp"));
					clickLevelOutage();

					sAssert.assertTrue(isIconOutageStatusVisible(), "Icon Outage Status is not visible");
					// sAssert.assertTrue(registerCommercialLnk());
					sAssert.assertTrue(isChkBoxOutageVisible(), "Check Box Outage is not visible");
					// sAssert.assertTrue(islstOutageChannelVisible());
					sAssert.assertTrue(isIconOutageAddVisible(), "Icon Outage Add is not visible");
					sAssert.assertTrue(isBtnOutageCancelVisible(), "Outage cancel button is not visible");
					sAssert.assertTrue(isBtnOutageSaveVisible(), "Outage save button is not visible");
					clickLevelOutage();
				}

				// Billing
				if (registrationConfig.get("Billing") == 1) {

					sAssert.assertTrue(isLblBillingTextVisible(), "Billing text label is not visible");
					sAssert.assertEquals(getLevelBilling(),
							NotificationpreferenceTextProp.getPropValue("txtLblBillingNpp"));
					linkLblBillingText();
					pause(2000);
					sAssert.assertTrue(isIconBillingStatusVisible(), "Billing icon status is not visible");
					sAssert.assertTrue(isBtnBillingCancelVisible(), "Billing cancel button is not visible");
					sAssert.assertTrue(isBtnBillingSaveVisible(), "Billing save button is not visible");
					sAssert.assertTrue(isChkBoxBillingVisible(), "Billing checkbox is not visible");
					linkLblBillingText();

				}

				// Budget
				if (registrationConfig.get("Billing.BudgetMyBill") == 1) {

					sAssert.assertEquals(getLevelBudget(),
							NotificationpreferenceTextProp.getPropValue("txtLblBudgetNpp"));
					linklevelBudget();
					pause(2000);
					sAssert.assertTrue(isLblBudgetTextVisible(), "Budget text label is not visible");
					sAssert.assertTrue(isBtnBudgetCancelVisible(), "Budget cancel button is not visible");
					// sAssert.assertTrue(islstBudgetOption1Visible(), "Budget first option is not
					// visible");
					sAssert.assertTrue(isBtnBudgetCancelVisible(), "Budget cancel button is not visible");
					sAssert.assertTrue(isBtnBudgetSaveVisible(), "Budget save button is not visible");
					sAssert.assertTrue(isIconBudgetAddVisible(), "Budget icon Add button is not visible");
					linklevelBudget();
				}

				// Demand Response Not in the scope
				/*
				 * pageUtil.scrollToElement(notificationPreferencePage.getlblDRText());
				 * pageUtil.click(notificationPreferencePage.getlblDRText());
				 * pageUtil.pause(1000);
				 * assertTrue(pageUtil.verifyElementVisible(notificationPreferencePage.
				 * getlblDRText()));
				 * pageUtil.verifyObjectLabel(notificationPreferencePage.getlblDRt(),
				 * Utils.getRbTextValue("txtLblDemandResponseNpp"));
				 * 
				 * assertTrue(pageUtil.verifyElementVisible(notificationPreferencePage.
				 * getIconDRStatus()));
				 * assertTrue(pageUtil.verifyElementVisible(notificationPreferencePage.
				 * getchkBoxDR()));
				 * assertTrue(pageUtil.verifyElementVisible(notificationPreferencePage.
				 * getlstDROptions()));
				 * assertTrue(pageUtil.verifyElementVisible(notificationPreferencePage.
				 * getlstDROptionsTxt()));
				 * assertTrue(pageUtil.verifyElementVisible(notificationPreferencePage.
				 * geticonDRAdd()));
				 * assertTrue(pageUtil.verifyElementVisible(notificationPreferencePage.
				 * getbtnDRCancel()));
				 * assertTrue(pageUtil.verifyElementVisible(notificationPreferencePage.
				 * getbtnDRSave()));
				 */
				// Connect Me
				if (registrationConfig.get("ConnectMe") == 1) {
					isLblConnectMeTextVisible();
					sAssert.assertEquals(getLevelConnectMeText(),
							NotificationpreferenceTextProp.getPropValue("txtLblConnectNpp"));
					clickLevelConnectMeText();
					pause(2000);
					sAssert.assertTrue(isIconConnectMeStatusVisible(), "connect me icon status is not visible");
					sAssert.assertTrue(ischkBoxConnectMeVisible(), "connect me checkbox is not visible");
					sAssert.assertTrue(isLblConnectMeTextVisible(), "connect me text label is not visible");
					sAssert.assertTrue(islstConnectMeOptionsVisible(), "connect me first options is not visible");
					sAssert.assertTrue(islstConnectMeOptionsTxtVisible(),
							"connect me first options text is not visible");
					sAssert.assertTrue(isIconConnectMeAddVisible(), "connect me add icon is not visible");
					sAssert.assertTrue(isBtnConnectMeCancel(), "connect me cancel button is not visible");
					sAssert.assertTrue(isBtnConnectMeSave(), "connect me save button is not visible");
					clickLevelConnectMeText();
					// isBtnConnectMeClose();

				}

			} else {
				log.info("Notification Preference link is not available " + "in the My Account Page");
			}
		} else {
			log.info("My Account link is not available on the header");
		}
		log.info("Test Case execution for - verifyNotificationPreferencePageObjects - is Completed.");

	}

	public void TcpaCompliancePopup(SoftAssert sAssert) {

		registrationConfig = CSPConfiguration.initMCspConfig();
		HomeSteps homeSteps = new HomeSteps(driver);
		String sTextValueName = "Text", sIvrValueName = "IVR";
		if (registrationConfig.get("MyAccount") == 1) {

			if (registrationConfig.get("MyAccount.NotificationPrefrence") == 1) {
				// Navigating to the notification preferences module.

				log.info("To verify the redirection on " + "Notification Preference page");
				homeSteps.navigateToNotificationPreferences();
				Assert.assertTrue(isNotificationPreferencePage(
						NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageUrl"),
						NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageTitle")));
				if (getLblIconOutageStatus().equalsIgnoreCase("On")) {
					clickLevelOutage();
					for (int i = 0; i < getlstOutageRemoveIcons().size(); i++) {
						getlstOutageRemoveIcons().get(i).click();
					}
					selectlstOutageChannel("Email");

					unCheckChkBoxOutage();
					btnOutageSaveBtn();
				}
				pause(8000);
				clickLevelOutage();
				saveOutageNotificationBtn();
				Assert.assertEquals(getToastMessage(),
						NotificationpreferenceTextProp.getPropValue("txtLblNotificationPrefSavedAos"),
						"Notification preferences save successfully message not matching.");
				// Verify on clicking save button TCPA compliance pop up is not
				// displaying.
				pause(5000);
				Assert.assertFalse(isLblTCPAacceptNotificatinTerms());
				clickLevelOutage();
				// Verify the on enable and disable of TEXT checkboxes TCPA
				// popup is coming
				if (registrationConfig.get("MyAccount.Settings.Text") == 1) {
					selectlstOutageChannel("Text");
					// pageUtil.check(notificationPreferencePage.getchkBoxOutage());
					checkChkBoxOutage();
					populateFirstMobilenumber("9999999999");
					btnOutageSaveBtn();
					pause(5000);
					sAssert.assertTrue(isLblTCPAacceptNotificatinTerms(),
							"TCPA Compliance Terms and Conditions popup is not appearing ");
					sAssert.assertEquals(
							NotificationpreferenceTextProp.getPropValue("txtLblAcceptNotificationPopupHeadingAos"),
							getLblTCPAacceptNotificatinTerms(), "TCPA pop up heading is not matched");
					sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue("txtLblNotificationBodyTitleAos"),
							getLbltcpaPopupBody(), "Tcpa popup body title is not matched");
					// Verify that On disgree TCPA, changes are not saving for
					// TEXT
					lnkTCPAagreeBtn();
					pause(5000);
					clickLevelOutage();
					pause(5000);
					unCheckChkBoxOutage();
					btnOutageSaveBtn();
					pause(5000);
					sAssert.assertTrue(isLblTCPAacceptNotificatinTerms(),
							"TCPA Compliance Terms and Conditions popup is not appearing ");
					lnkTCPDisAagreeBtn();
					// clickLevelOutage();
					// sAssert.assertTrue(isSelectedcheckChkBoxOutage());

				}

			} else {
				log.info("Notification Preference link is not available in the " + "MyAccount page");
			}
		} else {
			log.info("My Account link is not available on the header");
		}
	}

	public void notificationMobileNumberEmailAddress(SoftAssert sAssert, User user) throws SQLException {
		ResultSet rs;
		LinkedHashMap<String, String> mUserAccountDetails = new LinkedHashMap<String, String>();
		HomeSteps homeSteps = new HomeSteps(driver);
		registrationConfig = CSPConfiguration.initMCspConfig();
		log.info("C74663 | C74664 | C74665 - To verify by default email address "
				+ "provided under my account is displayed when notification option is checked.");
		log.info("Test Case execution for - verifyNotificationMobileNumberEmailAddress - is Initiated");
		rs = DataBaseUtils.getResultSet(SQLQueries.getMyAccountProfileInfo(user.getUserName()));
		ResultSetMetaData metadata = rs.getMetaData();
		int columnCount = metadata.getColumnCount();
		ArrayList<String> columns = new ArrayList<>();
		for (int i = 1; i < columnCount; i++) {
			String columnName = metadata.getColumnName(i);
			columns.add(columnName);
		}
		mUserAccountDetails.clear();
		while (rs.next()) {
			for (String columnName : columns) {
				String value = rs.getString(columnName);
				mUserAccountDetails.put(columnName, value);
			}
		}

		if (registrationConfig.get("MyAccount") == 1) {

			if (registrationConfig.get("MyAccount.NotificationPrefrence") == 1) {
				log.info("To verify the redirection on " + "Notification Preference page");
				homeSteps.navigateToNotificationPreferences();
				Assert.assertTrue(isNotificationPreferencePage(
						NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageUrl"),
						NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageTitle")));

				if (getIconBillingStatus().equalsIgnoreCase("On")) {
					getLevelBilling();
					linkLblBillingText();
					for (int i = 0; i < listBillingClose().size(); i++) {
						listBillingClose().get(i).click();
					}
					selectlstBillingChannel("Email");
					unCheckChkBoxBilling();
					billingSaveBtn();
					pause(5000);
				}

				if (getIconBudgetStatus().equalsIgnoreCase("On")) {
					linklevelBudget();
					for (int i = 0; i < listBudgetCloseIcons().size(); i++) {
						listBudgetCloseIcons().get(i).click();
					}
					selectBudgetOptions("Email");
					unCheckBoxBudget();
					BudgetSaveBtn();
					pause(5000);

				}

				log.info("C74663 - To verify by default Email Address provided under my "
						+ "account is displayed when notification option is checked.");
				// Verify Email address
				// TO DO
				pause(5000);
				linkServicesText();
				// pageUtil.scrollToElement(notificationPreferencePage.getlblServicesText());
				selectlstServicesOptions("Email");

				String serviceEmailTypeID = "10";
				rs = DataBaseUtils.getResultSet(
						SQLQueries.getNotificationAlertStatus(user.getAccountNumber(), serviceEmailTypeID));
				rs.next();
				String emailOptedForService = rs.getString("EmailORPhone");
				String s = getServicesOptionsTxt("value");
				sAssert.assertEquals(getServicesOptionsTxt("value"), emailOptedForService);
				log.info("C74664 - To verify that application will prepopulate TEXT "
						+ "number only if provided Primary contact number type is of Mobile");
				linklevelBudget();
				selectBudgetOptions("Text");
				if (Integer.parseInt(mUserAccountDetails.get("MobilePhoneType")) == 2) {
					sAssert.assertEquals(verifyTextlstBudgetOptionsTxt("value").replaceAll("[^\\d.]", ""),
							(mUserAccountDetails.get("MobilePhone")));
				} else {
					sAssert.assertEquals(verifyTextlstBudgetOptionsTxt("value"), "");
					log.info("The Contact Type is not selected as Mobile in My Account Profile Section");
				}
				log.info("C74665 - To verify that Primary Contact Number populate in " + "IVR field");
				if (Integer.parseInt(mUserAccountDetails.get("MobilePhoneType")) == 2) {
					selectBudgetOptions("IVR");
					sAssert.assertEquals(verifyTextlstBudgetOptionsTxt("value").replaceAll("[^\\d.]", ""),
							(mUserAccountDetails.get("MobilePhone")));

				} else {
					sAssert.assertEquals(verifyTextlstBudgetOptionsTxt("value"), "");
					log.info("The Contact Type is not selected as Mobile in My Account Profile Section");
				}

			} else {
				log.info("MyAccount.NotificationPrefrence is not selected in CSP");
			}
		} else {
			log.info("MyAccount is not selected in CSP");
		}
		log.info("Test Case execution for - verifyNotificationMobileNumberEmailAddress - is finished");

	}

	public void quietHoursFunctionalityAndUI(SoftAssert sAssert, User user) throws SQLException {
		ResultSet rs;
		LinkedHashMap<String, String> mUserAccountDetails = new LinkedHashMap<String, String>();
		HomeSteps homeSteps = new HomeSteps(driver);
		registrationConfig = CSPConfiguration.initMCspConfig();

		log.info("Test Case execution for - verifyQuietHoursSave - is Initiated");
		rs = DataBaseUtils.getResultSet(SQLQueries.getMyAccountProfileInfo(user.getUserName()));
		ResultSetMetaData metadata = rs.getMetaData();
		int columnCount = metadata.getColumnCount();
		ArrayList<String> columns = new ArrayList<>();
		for (int i = 1; i < columnCount; i++) {
			String columnName = metadata.getColumnName(i);
			columns.add(columnName);
		}
		mUserAccountDetails.clear();
		while (rs.next()) {
			for (String columnName : columns) {
				String value = rs.getString(columnName);
				mUserAccountDetails.put(columnName, value);
			}
		}
		// Login into the application

		if (registrationConfig.get("MyAccount") == 1) {

			if (registrationConfig.get("MyAccount.NotificationPrefrence") == 1) {
				log.info("To verify the redirection on " + "Notification Preference page");
				homeSteps.navigateToNotificationPreferences();
				Assert.assertTrue(isNotificationPreferencePage(
						NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageUrl"),
						NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageTitle")));
				if (registrationConfig.get("MyAccount.QuiteHours") == 1) {
					log.info("C78000-To Verify that Quiet hours UI and Success messages");
					sAssert.assertTrue(isLblQuietHours(), "Quiet hours lebal is not appeearing");
					sAssert.assertTrue(islnkQuietHoursEdit(), "Quiet hours edit link is not appeearing");
					sAssert.assertTrue(isLblQUietHoursRange(), "Quiet hours range or status is not appeearing");
					clicklnkQuietHoursEdit();
					sAssert.assertEquals(getTxtQuietHours(),
							NotificationpreferenceTextProp.getPropValue("txtLblQuietHoursNpp"));
					sAssert.assertTrue(isChkBoxEnableQuietHours(), "checkboxnot enabled for quiethours");
					sAssert.assertTrue(islstStartTimeQuietHours(), "first start time not visible for quiet hours");
					sAssert.assertTrue(islstEndTimeQuietHours(), "end time not visible for quiet hours");
					sAssert.assertTrue(islstTimeZoneQuietHours(), "first time zone is not visible for quit hours");
					sAssert.assertTrue(isBtnCancelQuietHours(), "cancel quiet button is not visible");
					sAssert.assertTrue(isBtnSaveQuietHours(), "save quiet button is not visible");
					log.info("C74667-To verify that application will not allow user to set Same From and To time");
					selectlstStartTimeQuietHours("10:00 PM");
					selectlstEndTimeQuietHours("10:00 PM");
					SaveQuietHoursBtn();
					sAssert.assertEquals(getLblToastMessage(),
							NotificationpreferenceTextProp.getPropValue("txtLblFromToDateMessageNpp"));
					log.info("C78001-To Verify If the Quite Hours are Active then the set Start Time and End "
							+ "Time will be displayed on the Quite Hours tile otherwise text will be displayed as “Not Active”");
					if (verifyChkboxOuietHours("class").contains("checked") == false) {
						ChkBoxEnableQuietHoursBtn();
						if (!verifyChkboxOuietHours("class").contains("checked") == true) {
							ChkBoxEnableQuietHoursBtn();
						}

					}
					selectlstStartTimeQuietHours("8:00 PM");
					selectlstEndTimeQuietHours("7:00 AM");
					selectlstTimeZoneQuietHours("(UTC+05:30)India Standard Time");
					SaveQuietHoursBtn();

					// String successMsg = notificationPreferencePage.getToastMsg();
					// assertEquals(successMsg,
					// Utils.getRbTextValue("txtLblNotificationPrefSavedAos"),
					// "Notification preferences save successfully message not matching.");

					String actTimeRange = getLblQUietHoursRange();
					String expTimeRange = "8:00 PM-7:00 AM";
					sAssert.assertEquals(actTimeRange, expTimeRange,
							"Time Range is appearing wrong for the active quiet hours");
					rs = DataBaseUtils
							.getResultSet(SQLQueries.getQuietHoursStatus(user.getAccountNumber(), user.getUserId()));
					rs.next();
					String quietHoursStatus = rs.getString("IsQuietHours");
					String expTimeRangeDB = rs.getString("HoursFrom") + "-" + rs.getString("HoursTo");
					// assertTrue(expTimeRangeDB.contentEquals(expTimeRange), "Quiet hours Range is
					// not correct in DB");
					pause(5000);
					clicklnkQuietHoursEdit();
					if (verifyChkboxOuietHours("class").contains("checked") == true) {
						ChkBoxEnableQuietHoursBtn();
						if (verifyChkboxOuietHours("class").contains("checked") == true) {
							ChkBoxEnableQuietHoursBtn();
						}
					}
					// pageUtil.unCheckCheckbox(notificationPreferencePage.getchkBoxEnableQuietHours());
					SaveQuietHoursBtn();
					// Assert.assertEquals("Notification preferences save successfully message not
					// matching.",
					// notificationPreferencePage.getToastMsg(),
					// Utils.getRbTextValue("txtLblNotificationPrefSavedAos"));
					pause(5000);
					sAssert.assertTrue(quietHoursStatus.contentEquals("1"), "Quiet hours Status is not correct in DB");
					// Db Validation for Quiet hours after inactive
					rs = DataBaseUtils
							.getResultSet(SQLQueries.getQuietHoursStatus(user.getAccountNumber(), user.getUserId()));
					rs.next();
					String quietHoursStatusAfterInactive = rs.getString("IsQuietHours");
					sAssert.assertTrue(quietHoursStatusAfterInactive.contentEquals("0"),
							"Quiet hours Status is not correct in DB");

					/*
					 * String actTextInActive = pageUtil .getLocatorText(notificationPreferencePage.
					 * getlblQUietHoursRange()); String expText = "Not Active";
					 * assertEquals(actTextInActive, expText,
					 * "Text is appearing wrong for inactive quiet hours");
					 */

				} else {
					log.info("The MyAccount.QuiteHours is not confgured from CSP");

				}
			} else {
				log.info("The MyAccount.NotificationPrefrence is not confgured from CSP");

			}
		} else {
			log.info("The MyAccount is not confgured from CSP");

		}
		log.info("Test Case execution for - verifyQuietHoursFunctionalityAndUI - is completed");

	}

	public void notificationMaintainOnAccountLevel(SoftAssert sAssert, User user) throws SQLException {
		ResultSet rs;
		registrationConfig = CSPConfiguration.initMCspConfig();
		LinkedHashMap<String, String> mUserAccountDetails = new LinkedHashMap<String, String>();
		HomeSteps homeSteps = new HomeSteps(driver);

		log.info("C78253 - To Verify that Notification Preferences must be maintained at User + Account level.");
		log.info(
				"C79414 - To Verify that On enabling any notification preference channel, Notification type status should be changed");
		log.info("Test Case execution for - verifyNotificationMaintainOnAccountLevel - is Initiated");

		// This sql query executed to bring MyAccount profile data.
		rs = DataBaseUtils.getResultSet(SQLQueries.getMyAccountProfileInfo(user.getUserName()));
		ResultSetMetaData metadata = rs.getMetaData();
		int columnCount = metadata.getColumnCount();
		ArrayList<String> columns = new ArrayList<>();
		for (int i = 1; i < columnCount; i++) {
			String columnName = metadata.getColumnName(i);
			columns.add(columnName);
		}
		mUserAccountDetails.clear();
		while (rs.next()) {
			for (String columnName : columns) {
				String value = rs.getString(columnName);
				mUserAccountDetails.put(columnName, value);
			}
		}

		if (registrationConfig.get("MyAccount") == 1) {

			if (registrationConfig.get("MyAccount.NotificationPrefrence") == 1) {
				log.info("To verify the redirection on " + "Notification Preference page");
				homeSteps.navigateToNotificationPreferences();
				Assert.assertTrue(isNotificationPreferencePage(
						NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageUrl"),
						NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageTitle")));
				Assert.assertTrue(isLblNotificationsVisible());

				if (getLblIconOutageStatus().equalsIgnoreCase("On")) {
					// pageUtil.scrollToElement(notificationPreferencePage.getlblOutageText());

					clickLevelOutage();
					for (int i = 0; i < getlstOutageRemoveIcons().size(); i++) {
						getlstOutageRemoveIcons().get(i).click();
					}
					selectlstOutageChannel("Email");
					unCheckChkBoxOutage();
					btnOutageSaveBtn();
				}
				if (getIconBillingStatus().equalsIgnoreCase("On")) {

					linkLblBillingText();

					for (int i = 0; i < listBillingClose().size(); i++) {
						listBillingClose().get(i).click();
					}
					selectlstBillingChannel("Email");
					unCheckChkBoxBilling();
					billingSaveBtn();

				}

				if (getIconServicesStatus().equalsIgnoreCase("On")) {
					linkServicesText();

					for (int i = 0; i < getlstServicesCloseIcons().size(); i++) {
						getlstServicesCloseIcons().get(i).click();
						;
					}

					selectlstServicesOptions("Email");
					unCheckChkBoxServices();
					clickServicesSave();

				}

				linkServicesText();
				selectlstServicesOptions("Email");

				String serviceEmailTypeID = "25";
				rs = DataBaseUtils.getResultSet(
						SQLQueries.getNotificationAlertStatus(user.getAccountNumber(), serviceEmailTypeID));
				rs.next();
				String emailOptedForService = rs.getString("EmailORPhone");
				// String actEmail = getServicesOptionsTxt();
				// sAssert.assertEquals(actEmail, emailOptedForService, "Primary Email id is not
				// appearing by default ");
				checkChkBoxServices();
				clickServicesSave();
				// assertEquals(notificationPreferencePage.getToastMsg(),
				// Utils.getRbTextValue("txtLblNotificationPrefSavedAos"),
				// "Notification preferences save successfully message not matching.");

				// Db validation

				String serviceEmailTypeID1 = "25";
				rs = DataBaseUtils.getResultSet(
						SQLQueries.getNotificationAlertStatus(user.getAccountNumber(), serviceEmailTypeID1));
				rs.next();
				String notiStatusEmail = rs.getString("NotifyStatus");
				sAssert.assertTrue(notiStatusEmail.contentEquals("0"),
						"Notification Alert status is not correct in Db ");
				sAssert.assertTrue(getIconServicesStatus().contentEquals("On"), "Service Status is not appearing ON ");
				clickLevelOutage();
				clickIconOutageAdd();
				selectlstOutageOptions2Row("IVR");
				// String actIVRNumber = getlblOutageOptionText2Row();
				String expIVRNumber = mUserAccountDetails.get("MobilePhone");
				// sAssert.assertEquals(expIVRNumber, actIVRNumber, "Primary IVR number is not
				// appearing by default ");
				unChkBoxOutage2Row();
				btnOutageSaveBtn();

				sAssert.assertTrue(isLblTCPAacceptNotificatinTerms(),
						"TCPA Compliance Terms and Conditions popup is not appearing ");

				lnkTCPAagreeBtn();
				// assertEquals(notificationPreferencePage.getToastMsg(),
				// Utils.getRbTextValue("txtLblNotificationPrefSavedAos"),
				// "Notification preferences save successfully message not matching.");
				// DB Validation for IVR
				String outageIVRTypeID = "4";
				rs = DataBaseUtils
						.getResultSet(SQLQueries.getNotificationAlertStatus(user.getAccountNumber(), outageIVRTypeID));
				rs.next();
				String notiStatusIVR = rs.getString("NotifyStatus");
				sAssert.assertTrue(notiStatusIVR.contentEquals("1"), "Notification Alert status is not correct in Db");

				String outageTextTypeID = "1";
				rs = DataBaseUtils
						.getResultSet(SQLQueries.getNotificationAlertStatus(user.getAccountNumber(), outageTextTypeID));
				rs.next();
				String notiStatusText = rs.getString("NotifyStatus");
				sAssert.assertTrue(notiStatusText.contentEquals("0"),
						"Notification Alert status is not correct in Db ");
				sAssert.assertTrue(getLblIconOutageStatus().contentEquals("On"), "Outage Status is not appearing ON ");

				// UI validation for outage status check
				linkLblBillingText();
				// boolean actCheckboxStatusOutageEmail = verifyChkBoxOutage();
				// sAssert.assertFalse(actCheckboxStatusOutageEmail, "Outage status is not
				// correct");
				linkLblBillingText();
				linkLblBillingText();
				selectlstBillingChannel("Text");
				checkBoxBilling();
				billingSaveBtn();
				pause(5000);
				lnkTCPAagreeBtn();
				// assertEquals(notificationPreferencePage.getToastMsg(),
				// Utils.getRbTextValue("txtLblNotificationPrefSavedAos"),
				// "Notification preferences save successfully message not matching.");
				// pageUtil.pause(2000);
				linkLblBillingText();
				selectlstBillingChannel("Text");
				String actTextNumber = getlstBillingOptionsTxt();
				String billingTextTypeId = "5";

				rs = DataBaseUtils.getResultSet(
						SQLQueries.getNotificationAlertStatus(user.getAccountNumber(), billingTextTypeId));
				rs.next();
				String txtNumberBilling = rs.getString("EmailORPhone");

				sAssert.assertEquals(txtNumberBilling, actTextNumber,
						"Primary Text number is not appearing by default ");
				// UI validation for Billing status check

				linkLblBillingText();
				boolean actCheckboxStatusBilling = verifycheckkBoxBilling();
				sAssert.assertTrue(actCheckboxStatusBilling, "Billing status is not correct");

				// DB Validation for IVR
				String billingTextTypeID = "5";
				rs = DataBaseUtils.getResultSet(
						SQLQueries.getNotificationAlertStatus(user.getAccountNumber(), billingTextTypeID));
				rs.next();
				String notiStatusBillingText = rs.getString("NotifyStatus");
				sAssert.assertTrue(notiStatusBillingText.contentEquals("1"),
						"Notification Alert status is not correct in Db ");
				sAssert.assertTrue(getIconBillingStatus().contentEquals("On"), "Outage Status is not appearing ON ");

			} else {
				log.info("MyAccount.NotificationPrefrence is not selected in CSP");
			}
		} else {
			log.info("MyAccount is not selected in CSP");
		}

		log.info("Test Case execution for - verifyNotificationMaintainOnAccountLevel - is finished");

	}

	public void notificationAlertStatusAndNoImpactOfQuietHOursOnEmail(SoftAssert sAssert, User user) throws SQLException

	{
		log.info("C78005 - Verify that Email Notifications will never stop, even when Quite Hours are active.");
		log.info("Test Case execution for - verifyEmailNotificationNotImpactedBYQuietHours - is Initiated");
		ResultSet rs;
		LinkedHashMap<String, String> mUserAccountDetails = new LinkedHashMap<String, String>();
		registrationConfig = CSPConfiguration.initMCspConfig();
		HomeSteps homeSteps = new HomeSteps(driver);
		rs = DataBaseUtils.getResultSet(SQLQueries.getMyAccountProfileInfo(user.getUserName()));
		ResultSetMetaData metadata = rs.getMetaData();
		int columnCount = metadata.getColumnCount();
		ArrayList<String> columns = new ArrayList<>();
		for (int i = 1; i < columnCount; i++) {
			String columnName = metadata.getColumnName(i);
			columns.add(columnName);
		}
		mUserAccountDetails.clear();
		while (rs.next()) {
			for (String columnName : columns) {
				String value = rs.getString(columnName);
				mUserAccountDetails.put(columnName, value);
			}
		}

		if (registrationConfig.get("MyAccount") == 1) {

			if (registrationConfig.get("MyAccount.NotificationPrefrence") == 1) {
				log.info("To verify the redirection on " + "Notification Preference page");
				homeSteps.navigateToNotificationPreferences();
				Assert.assertTrue(isNotificationPreferencePage(
						NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageUrl"),
						NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageTitle")));
				Assert.assertTrue(isLblNotificationsVisible());

				if (getLblIconOutageStatus().equalsIgnoreCase("On")) {
					// pageUtil.scrollToElement(notificationPreferencePage.getlblOutageText());

					clickLevelOutage();
					for (int i = 0; i < getlstOutageRemoveIcons().size(); i++) {
						getlstOutageRemoveIcons().get(i).click();
					}
					selectlstOutageChannel("Email");
					unCheckChkBoxOutage();
					btnOutageSaveBtn();
					pause(5000);

				}
				if (getIconBillingStatus().equalsIgnoreCase("On")) {

					linkLblBillingText();

					for (int i = 0; i < listBillingClose().size(); i++) {
						listBillingClose().get(i).click();
					}
					selectlstBillingChannel("Email");
					unCheckChkBoxBilling();
					billingSaveBtn();
					pause(5000);

				}

				if (registrationConfig.get("Notification.LeakAlert") == 1) {

					if (getIconLeakAlertStatus().equalsIgnoreCase("On")) {
						getlblLeakAlertText();
						pause(5000);
						linklblLeakAlertText();

						for (int i = 0; i < lstLeakAlertCloseIcons().size(); i++) {
							lstLeakAlertCloseIcons().get(i).click();
						}
						selectlstLeakAlertOptions("Email");

						unCheckchkBoxLeakAlert();
						leakAlertSaveBtn();
						pause(5000);

					}
				}

				if (registrationConfig.get("MyAccount.QuiteHours") == 1) {
					sAssert.assertTrue(isLblQuietHours(), "Quiet hours lebal is not appeearing");
					sAssert.assertTrue(islnkQuietHoursEdit(), "Quiet hours edit link is not appeearing");
					sAssert.assertTrue(isLblQUietHoursRange(), "Quiet hours range or status is not appeearing");
					pause(5000);
					clicklnkQuietHoursEdit();
					ChkBoxEnableQuietHoursBtn();
					selectlstStartTimeQuietHours("8:00 PM");
					selectlstEndTimeQuietHours("7:00 PM");
					selectlstTimeZoneQuietHours("(UTC+05:30)India Standard Time");
					SaveQuietHoursBtn();
					// assertEquals(notificationPreferencePage.getToastMsg(),
					// Utils.getRbTextValue("txtLblNotificationPrefSavedAos"),
					// "Notification preferences save successfully message not matching.");

					String actTimeRange = getLblQUietHoursRange();
					String expTimeRange = "8:00 PM-7:00 PM";
					sAssert.assertEquals(actTimeRange, expTimeRange,
							"Time Range is appearing wrong for the active quiet hours");

					if (registrationConfig.get("Notification.LeakAlert") == 1) {
						if (getIconLeakAlertStatus().equalsIgnoreCase("On")) {
							getlblLeakAlertText();
							linklblLeakAlertText();
							selectlstLeakAlertOptions("Email");
							checkchkBoxLeakAlert();
							leakAlertSaveBtn();

						}

						log.info("C78004 - Verify that When a user makes any changes for any of "
								+ "the Notification preferences for different notification alerts, "
								+ "an Email Notification shall be triggered to the user’s primary Email Address");

						// Verify the received status of notification in DB
						rs = DataBaseUtils.getResultSet(SQLQueries.getNotificationReceivedStatus(user.getEmailId()));
						rs.next();
						String notiReceivedStatus = rs.getString("IsNotify");
						String subjectEamil = rs.getString("Subject");
						String emailBody = rs.getString("Message");

						sAssert.assertTrue(notiReceivedStatus.contentEquals("1"), "Email is not recieved to the user ");
						// Assert.assertTrue("Email subject is not correct",
						// subjectEamil.contains("SCM Notification Preference"));
						// sAssert.assertTrue(emailBody.contains("LeakAlert"), "Email body is not
						// correct");
						log.info(
								"C74683 - When the user check the notification pref. check box and saves the Preferences,"
										+ " verify the same in DB table (Accountnotificationdetail)");

						getlblLeakAlertText();
						pause(5000);
						linklblLeakAlertText();
						selectlstLeakAlertOptions("Email");
						populatelstLeakAlertOptionsTxt(NotificationpreferenceTextProp.getPropValue("txtTestMailNpp"));
						unCheckchkBoxLeakAlert();
						checkchkBoxLeakAlert();
						leakAlertSaveBtn();
						pause(5000);
						// Verify the status of notification alert and configured
						// email id

						// leakalert Email notificationtypeid = 26
						String notificationTypeID = "26";
						rs = DataBaseUtils.getResultSet(
								SQLQueries.getNotificationAlertStatus(user.getAccountNumber(), notificationTypeID));

						rs.next();
						String notiStatus = rs.getString("NotifyStatus");
						String emailAddressAftercheck = rs.getString("EmailORPhone");

						sAssert.assertTrue(
								emailAddressAftercheck
										.contentEquals(NotificationpreferenceTextProp.getPropValue("txtTestMailNpp")),
								"Configured Email is not appearing for notification");

						sAssert.assertTrue(notiStatus.contentEquals("1"),
								"Notification Alert status is not correct in Db ");

						log.info("C74676 - Email |To verify that Upon changing the Notification preferences, "
								+ "the user shall receive a notification on the registered Email Address regarding the same.");
						getlblLeakAlertText();
						pause(8000);
						linklblLeakAlertText();
						selectlstLeakAlertOptions("Text");
						populatelstLeakAlertOptionsTxt(
								NotificationpreferenceTextProp.getPropValue("txtPhoneNumberNpp"));
						unCheckchkBoxLeakAlert();
						checkchkBoxLeakAlert();
						leakAlertSaveBtn();
						lnkTCPAagreeBtn();
						pause(5000);

						// leakalert text notificationtypeid = 25
						String leakAlertTextTypeID = "25";
						rs = DataBaseUtils.getResultSet(
								SQLQueries.getNotificationAlertStatus(user.getAccountNumber(), leakAlertTextTypeID));
						rs.next();
						String notiStatusPhone = rs.getString("NotifyStatus");

						sAssert.assertTrue(notiStatusPhone.contentEquals("1"),
								"Notification Alert status is not correct in Db ");

						getlblLeakAlertText();
						pause(5000);
						linklblLeakAlertText();
						selectlstLeakAlertOptions("Email");
						populatelstLeakAlertOptionsTxt(
								NotificationpreferenceTextProp.getPropValue("txtPhoneNumberNpp"));
						checkchkBoxLeakAlert();
						leakAlertSaveBtn();
						pause(5000);

						rs = DataBaseUtils.getResultSet(
								SQLQueries.getNotificationAlertStatus(user.getAccountNumber(), notificationTypeID));
						rs.next();
						String notiStatusAfterUncheck = rs.getString("NotifyStatus");
						String emailAddressAfterUncheck = rs.getString("EmailORPhone");

						// Assert.assertTrue("Notification Alert status is not correct in Db ",
						// notiStatusAfterUncheck.contentEquals("0"));
						// Assert.assertTrue("Configured Email is not appearing for notification",
						// emailAddressAfterUncheck.contentEquals(mUserAccountDetails.get("EmailID")));
					}
				} else {
					log.info("The MyAccount.QuiteHours is not confgured from CSP");

				}

			} else {
				log.info("MyAccount.NotificationPrefrence is not selected in CSP");
			}
		} else {
			log.info("MyAccount is not selected in CSP");
		}
		log.info("Test Case execution for - verifyNotificationAlertStatusAndNoImpactOfQuietHOursOnEmail - is finished");
	}

	public void addNotificationChannelFunctionality(SoftAssert sAssert, User user) {
		log.info(
				"C79589 - To Verify that User is not able to add same type of notification channels for a particular module");
		log.info("Test Case execution for - verifyNotificationsChannelsValidations - is Initiated");
		ResultSet rs;
		registrationConfig = CSPConfiguration.initMCspConfig();
		LinkedHashMap<String, String> mUserAccountDetails = new LinkedHashMap<String, String>();
		HomeSteps homeSteps = new HomeSteps(driver);
		if (registrationConfig.get("MyAccount") == 1) {

			if (registrationConfig.get("MyAccount.NotificationPrefrence") == 1) {
				log.info("To verify the redirection on " + "Notification Preference page");
				homeSteps.navigateToNotificationPreferences();
				Assert.assertTrue(isNotificationPreferencePage(
						NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageUrl"),
						NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageTitle")));
				Assert.assertTrue(isLblNotificationsVisible());
				if (getIconBudgetStatus().equalsIgnoreCase("On")) {
					getLevelBudget();

					for (int i = 0; i < listBudgetCloseIcons().size(); i++) {
						listBudgetCloseIcons().get(i).click();
						;
					}
					selectBudgetOptions("Email");
					unCheckBoxBudget();
					BudgetSaveBtn();
					pause(5000);

				}
				linklevelBudget();
				selectBudgetOptions("Email");
				checkBoxBudget();
				IconBudgetAdd();
				selectlstBudgetOption("Email");
				checkChkbox2Budget();
				BudgetSaveBtn();
				sAssert.assertEquals(getLblToastMessage(),
						NotificationpreferenceTextProp.getPropValue("txtSameEmailChannelNpp"),
						"Toast message is appearing wrong for same email channel ");
				pause(3000);
				clickbtnCloseMsgOnHeader();
				pause(3000);
				selectBudgetOptions("Text");
				selectlstBudgetOption("Text");
				BudgetSaveBtn();
				sAssert.assertEquals(getLblToastMessage(),
						NotificationpreferenceTextProp.getPropValue("txtSameTextNumberChannelNpp"),
						"Toast message is appearing wrong for same Text channel ");
				clickbtnCloseMsgOnHeader();
				pause(3000);
				selectBudgetOptions("IVR");
				selectlstBudgetOption("IVR");
				BudgetSaveBtn();
				sAssert.assertEquals(getLblToastMessage(),
						NotificationpreferenceTextProp.getPropValue("txtSameIVRNumberChannelNpp"),
						"Toast message is appearing wrong for same IVR channel ");
				clickbtnCloseMsgOnHeader();
				pause(3000);
				selectBudgetOptions("Whatsapp");
				selectlstBudgetOption("Whatsapp");
				populateBugetFirstMobilenumber("9999999990");
				populateBugetSecondMobilenumber("9999999998");
				BudgetSaveBtn();
				sAssert.assertEquals(getLblToastMessage(),
						NotificationpreferenceTextProp.getPropValue("txtSameWhatsappNumberChannelNpp"),
						"Toast message is appearing wrong for same whatsapp channel ");

				// pageUtil.click(notificationPreferencePage.geticonBudgetClose1());

			} else {
				log.info("MyAccount.NotificationPrefrence is not selected in CSP");
			}
		} else {
			log.info("MyAccount is not selected in CSP");
		}
		log.info("Test Case execution for - verifyNotificationsChannelsValidations - is finished");

	}

	public void notificationsChannelsValidations(SoftAssert sAssert, User user) {
		ResultSet rs;
		LinkedHashMap<String, String> mUserAccountDetails = new LinkedHashMap<String, String>();
		registrationConfig = CSPConfiguration.initMCspConfig();
		HomeSteps homeSteps = new HomeSteps(driver);
		log.info(
				"C79589 - To Verify that User is not able to add same type of notification channels for a particular module");
		log.info("Test Case execution for - verifyNotificationsChannelsValidations - is Initiated");

		if (registrationConfig.get("MyAccount") == 1) {

			if (registrationConfig.get("MyAccount.NotificationPrefrence") == 1) {
				log.info("To verify the redirection on " + "Notification Preference page");
				homeSteps.navigateToNotificationPreferences();
				Assert.assertTrue(isNotificationPreferencePage(
						NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageUrl"),
						NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageTitle")));
				Assert.assertTrue(isLblNotificationsVisible());
				if (getIconBudgetStatus().equalsIgnoreCase("On")) {
					linklevelBudget();

					for (int i = 0; i < listBudgetCloseIcons().size(); i++) {
						listBudgetCloseIcons().get(i).click();
					}
					selectBudgetOptions("Email");
					unCheckBoxBudget();
					BudgetSaveBtn();
				}
				pause(8000);
				linklevelBudget();
				selectBudgetOptions("Email");
				pause(3000);
				checkBoxBudget();
				BudgetSaveBtn();
				pause(5000);
				linklevelBudget();
				pause(2000);
				clickIconBudgetDisabled();
				selectlstBudgetOption("Email");
				checkChkbox2Budget();
				BudgetSaveBtn();
				pause(2000);
				sAssert.assertEquals(getLblToastMessage(),
						NotificationpreferenceTextProp.getPropValue("txtSameEmailChannelNpp"),
						"Toast message is appearing wrong for same email channel ");
				clickbtnCloseMsgOnHeader();
				pause(3000);
				selectBudgetOptions("Text");
				selectlstBudgetOption("Text");
				BudgetSaveBtn();
				sAssert.assertEquals(getLblToastMessage(),
						NotificationpreferenceTextProp.getPropValue("txtSameTextNumberChannelNpp"),
						"Toast message is appearing wrong for same Text channel ");
				clickbtnCloseMsgOnHeader();
				pause(3000);
				selectBudgetOptions("IVR");
				selectlstBudgetOption("IVR");
				BudgetSaveBtn();
				pause(2000);
				sAssert.assertEquals(getLblToastMessage(),
						NotificationpreferenceTextProp.getPropValue("txtSameIVRNumberChannelNpp"),
						"Toast message is appearing wrong for same IVR channel ");
				clickbtnCloseMsgOnHeader();
				pause(3000);
				selectBudgetOptions("Whatsapp");
				selectlstBudgetOption("Whatsapp");
				populateBugetFirstMobilenumber("9999999990");
				populateBugetSecondMobilenumber("9999999998");

				BudgetSaveBtn();
				sAssert.assertEquals(getLblToastMessage(),
						NotificationpreferenceTextProp.getPropValue("txtSameWhatsappNumberChannelNpp"),
						"Toast message is appearing wrong for same whatsapp channel ");
				clickbtnCloseMsgOnHeader();
				pause(3000);
				// pageUtil.click(notificationPreferencePage.geticonBudgetClose1());

			} else {
				log.info("MyAccount.NotificationPrefrence is not selected in CSP");
			}
		} else {
			log.info("MyAccount is not selected in CSP");
		}
		log.info("Test Case execution for - verifyNotificationsChannelsValidations - is finished");

	}

	public void notificationAlertsValidationMessages(SoftAssert sAssert, User user) throws SQLException {
		ResultSet rs;
		LinkedHashMap<String, String> mUserAccountDetails = new LinkedHashMap<String, String>();
		LinkedHashMap<String, RegistrationConfig> mRegistrationConfig = new LinkedHashMap<String, RegistrationConfig>();
		registrationConfig = CSPConfiguration.initMCspConfig();
		String sPrimaryContactNumberMinLength = "";
		String sPrimaryContactNumberMaxLength = "";
		HomeSteps homeSteps = new HomeSteps(driver);
		log.info("C74673 | C74677 | C74680 | C74681 | "
				+ "To Verify that application will display common message if more than one mandatory field "
				+ "left blank");
		log.info("Test Case execution for - verifyNotificationAlertsValidationMessages - is Initiated");
		// This sql query executed to bring the MyAccount profile data
		rs = DataBaseUtils.getResultSet(SQLQueries.getRegistrationTemplateConfig());
		mRegistrationConfig.clear();
		while (rs.next()) {
			mRegistrationConfig.put(rs.getString("ParentHead"),
					new RegistrationConfig(rs.getString("ScpStatus"), rs.getString("Min Length"),
							rs.getString("Max Length"), rs.getString("Mandatory"),
							rs.getString("Validation Against CIS"), rs.getString("Type")));
		}
		sPrimaryContactNumberMinLength = mRegistrationConfig.get("Primary Contact Number").getFieldMinLength();
		sPrimaryContactNumberMaxLength = mRegistrationConfig.get("Primary Contact Number").getFieldMaxLength();
		if (registrationConfig.get("MyAccount") == 1) {

			if (registrationConfig.get("MyAccount.NotificationPrefrence") == 1) {
				log.info("To verify the redirection on " + "Notification Preference page");
				homeSteps.navigateToNotificationPreferences();
				Assert.assertTrue(isNotificationPreferencePage(
						NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageUrl"),
						NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageTitle")));
				Assert.assertTrue(isLblNotificationsVisible());
				if (getIconBillingStatus().equalsIgnoreCase("On")) {
					linkLblBillingText();

					for (int i = 0; i < listBillingClose().size(); i++) {
						listBillingClose().get(i).click();
						;
					}
					selectlstBillingChannel("Email");
					unCheckChkBoxBilling();
					billingSaveBtn();
					pause(8000);

				}

				if (getIconConnectMeStatus().equalsIgnoreCase("On")) {
					clickLevelConnectMeText();

					for (int i = 0; i < getlstConnectMeCloseIcons().size(); i++) {
						getlstConnectMeCloseIcons().get(i).click();
					}
					selectlstConnectMeOptions("Email");
					unCheckConnectMe();
					clickBtnConnectMeSave();

				}

				linkLblBillingText();
				log.info(
						"C74673 - To verify that application will display validation message if user left any one mandatory field blank");
				selectlstBillingChannel("Email");
				clearlstBillingOptionsTxt();
				checkBoxBilling();
				billingSaveBtn();

				sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue("txtEmailBlankNpp"),
						getLblToastMessage(), "Email blank validation message is not correct");
				clickbtnCloseMsgOnHeader();
				pause(3000);
				selectlstBillingChannel("Text");
				clearlstBillingOptionsTxt();
				checkBoxBilling();
				billingSaveBtn();
				sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue("txtTextBlankNpp"),
						getLblToastMessage(), "Text blank validation message is not correct");
				clickbtnCloseMsgOnHeader();
				pause(3000);
				selectlstBillingChannel("IVR");
				clearlstBillingOptionsTxt();
				checkBoxBilling();
				billingSaveBtn();
				sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue("txtIVRBlankNpp"),
						getLblToastMessage(), "IVR blank validation message is not correct");
				clickbtnCloseMsgOnHeader();
				pause(3000);
				selectlstBillingChannel("Whatsapp");
				clearlstBillingOptionsTxt();
				checkBoxBilling();
				billingSaveBtn();

				sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue("txtTextBlankNpp"),
						getLblToastMessage(), "Whatsapp blank validation message is not correct");
				clickbtnCloseMsgOnHeader();
				pause(3000);
				log.info(
						"C74677 - To verify that application will display message 'Please enter a valid Email Address.'"
								+ "when user provide invalid Email Address(format)");
				selectlstBillingChannel("Email");
				getlstBillingOptionsTxt();
				populatelstBillingOptionsTxt("abc.com");
				checkBoxBilling();
				billingSaveBtn();
				sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue("txtEmailWrongFormatNpp"),
						getLblToastMessage(), "Email wrong format validation message is not correct");
				clickbtnCloseMsgOnHeader();
				pause(3000);
				selectlstBillingChannel("Email");
				getlstBillingOptionsTxt();
				populatelstBillingOptionsTxt("abc@abccom");
				checkBoxBilling();
				billingSaveBtn();
				sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue("txtEmailWrongFormatNpp"),
						getLblToastMessage(), "Email wrong format validation message is not correct");
				clickbtnCloseMsgOnHeader();
				pause(3000);
				log.info(
						"C74681 - Verify the validation message in case of field minimum length=maximum length and provided value as all Zero");
				selectlstBillingChannel("Text");
				clearlstBillingOptionsTxt();
				populatelstBillingOptionsTxt("0000000000");
				checkBoxBilling();
				billingSaveBtn();
				sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue("txtTextValidationMessageNpp"),
						getLblToastMessage(), "Mobile Number All Zero validation message is not correct");
				clickbtnCloseMsgOnHeader();
				pause(3000);
				selectlstBillingChannel("Text");
				clearlstBillingOptionsTxt();
				populatelstBillingOptionsTxt("0000111111");
				checkBoxBilling();
				billingSaveBtn();
				sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue("txtTextValidationMessageNpp"),
						getLblToastMessage(), "Mobile Number incorrect format validation message is not correct");
				clickbtnCloseMsgOnHeader();
				pause(5000);

				selectlstBillingChannel("IVR");
				clearlstBillingOptionsTxt();
				populatelstBillingOptionsTxt("0000111111");
				checkBoxBilling();
				billingSaveBtn();
				sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue("txtMobile10_digitVaidationNpp"),
						getLblToastMessage(), "Mobile Number incorrect format validation message is not correct");
				clickbtnCloseMsgOnHeader();
				pause(3000);
				selectlstBillingChannel("IVR");
				clearlstBillingOptionsTxt();
				populatelstBillingOptionsTxt("0000000000");
				checkBoxBilling();
				billingSaveBtn();

				sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue("txtMobile10_digitVaidationNpp"),
						getLblToastMessage(), "Mobile Number All Zero validation message is not correct");
				clickbtnCloseMsgOnHeader();
				pause(3000);

				log.info(
						"C74680 - Verify the validation message in case of field minimum length=maximum length and provided value length is less than the required");
				clickLevelConnectMeText();
				selectlstBillingChannel("Text");
				clearlstBillingOptionsTxt();
				populatelstBillingOptionsTxt("123456789");
				checkBoxBilling();
				billingSaveBtn();
				sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue("txtTextValidationMessageNpp"),
						getLblToastMessage(), "Mobile Number 10-Digit validation message is not correct");
				clickbtnCloseMsgOnHeader();
				pause(3000);
				clickLevelConnectMeText();
				selectlstBillingChannel("IVR");
				clearlstBillingOptionsTxt();
				populatelstBillingOptionsTxt("123456789");
				checkBoxBilling();
				billingSaveBtn();
				sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue("txtMobile10_digitVaidationNpp"),
						getLblToastMessage(), "Mobile Number 10-Digit validation message is not correct");
				clickbtnCloseMsgOnHeader();
				pause(3000);

			} else {
				log.info("MyAccount.NotificationPrefrence is not selected in CSP");
			}
		} else {
			log.info("MyAccount is not selected in CSP");
		}
		log.info("Test Case execution for - verifyNotificationAlertsValidationMessages - is finished");
	}

	public void notificationInboxOnNoPreferenceAlert(SoftAssert sAssert, User user) throws SQLException {
		log.info("C78252 - To Verify that If user doesn’t opt-in for receiving notifications, "
				+ "user shall still receive notifications in their Notification Inbox of SCM Application");
		log.info("Test Case execution for - verifyNotificationInboxOnNoPreferenceAlert - is Initiated");
		ResultSet rs;
		LinkedHashMap<String, String> mUserAccountDetails = new LinkedHashMap<String, String>();
		registrationConfig = CSPConfiguration.initMCspConfig();
		HomeSteps homeSteps = new HomeSteps(driver);
		rs = DataBaseUtils.getResultSet(SQLQueries.getMyAccountProfileInfo(user.getUserName()));
		ResultSetMetaData metadata = rs.getMetaData();
		int columnCount = metadata.getColumnCount();
		ArrayList<String> columns = new ArrayList<>();
		for (int i = 1; i < columnCount; i++) {
			String columnName = metadata.getColumnName(i);
			columns.add(columnName);
		}
		mUserAccountDetails.clear();
		while (rs.next()) {
			for (String columnName : columns) {
				String value = rs.getString(columnName);
				mUserAccountDetails.put(columnName, value);
			}
		}
		if (registrationConfig.get("MyAccount") == 1) {

			if (registrationConfig.get("MyAccount.NotificationPrefrence") == 1) {
				homeSteps.navigateToNotificationPreferences();
				Assert.assertTrue(isNotificationPreferencePage(
						NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageUrl"),
						NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageTitle")));
				Assert.assertTrue(isLblNotificationsVisible());

				if (getLblIconOutageStatus().equalsIgnoreCase("On")) {
					linkLblBillingText();

					for (int i = 0; i < getlstOutageRemoveIcons().size(); i++) {
						getlstOutageRemoveIcons().get(i).click();
					}
					selectlstOutageOptionsTxt("Email");
					checkChkBoxOutage();
					btnOutageSaveBtn();
				}
				if (getIconBillingStatus().equalsIgnoreCase("On")) {

					linkLblBillingText();

					for (int i = 0; i < listBudgetCloseIcons().size(); i++) {
						listBudgetCloseIcons().get(i).click();

					}
					selectlstBillingChannel("Email");
					unCheckChkBoxBilling();
					billingSaveBtn();
				}
				if (getIconBudgetStatus().equalsIgnoreCase("On")) {
					linklevelBudget();
					for (int i = 0; i < listBillingClose().size(); i++) {
						listBillingClose().get(i).click();
					}
					selectBudgetOptions("Email");
					unCheckBoxBudget();
					BudgetSaveBtn();
				}
				if (getIconConnectMeStatus().equalsIgnoreCase("On")) {
					clickLevelConnectMeText();
					for (int i = 0; i < getlstConnectMeCloseIcons().size(); i++) {
						getlstConnectMeCloseIcons().get(i).click();
					}
					selectlstConnectMeOptions("Email");
					unCheckConnectMe();
					clickBtnConnectMeSave();

				}
				if (registrationConfig.get("Notification.LeakAlert") == 1) {
					if (getIconLeakAlertStatus().equalsIgnoreCase("On")) {
						linklblLeakAlertText();
						for (int i = 0; i < lstLeakAlertCloseIcons().size(); i++) {
							lstLeakAlertCloseIcons().get(i).click();
						}
						selectlstLeakAlertOptions("Email");
						pause(2000);
						unCheckchkBoxLeakAlert();
						leakAlertSaveBtn();
					}
				}

				if (getIconServicesStatus().equalsIgnoreCase("On")) {
					linkServicesText();
					for (int i = 0; i < getlstServicesCloseIcons().size(); i++) {
						getlstServicesCloseIcons().get(i).click();
					}
					selectlstServicesOptions("Email");
					unCheckChkBoxServices();
					clickServicesSave();
				}

			} else {
				log.info("MyAccount.NotificationPrefrence is not selected in CSP");
			}
		} else {
			log.info("MyAccount is not selected in CSP");
		}
		log.info("Test Case execution for - verifyNotificationsChannelsValidations - is finished");
	}

	public void notificationPreferenceForRegisteredUser(SoftAssert sAssert, User user, Customer customer)
			throws SQLException {
		log.info(
				"C79589 - To Verify that User is not able to add same type of notification channels for a particular module");
		log.info("Test Case execution for - verifyNotificationsChannelsValidations - is Initiated");

		log.info("This test method verifies below tests:"
				+ "C74939, C74941, C74942, C74943, C74947, C74948, C74950, C74953, C74953, \n"
				+ "C74955, C74956, C74957, C74974, C74987");

		log.info("Test Case execution for - verifyWelcomeScreenResidentialUser - is Completed");

		RegistrationSteps registrationSteps = new RegistrationSteps(driver);
		registrationSteps.verifyRegByOptingPaperlessPreferences(sAssert, customer);
		notificationMobileNumberEmailAddress(sAssert, user);

	}

	public void quietHoursFunctionalityAndUIforEmailAddress(SoftAssert sAssert, User user) throws SQLException {
		log.info(
				"C74667 | C78001 | C78000 -To verify that user will allow to set quiet hours after enabling quiet hour check box");
		log.info("Test Case execution for - verifyQuietHoursSave - is Initiated");
		quietHoursFunctionalityAndUI(sAssert, user);
		notificationMobileNumberEmailAddress(sAssert, user);
	}

}

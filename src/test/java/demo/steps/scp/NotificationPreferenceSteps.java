package demo.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import demo.pageobjects.NotificationPreferencePage;
import sew.ai.steps.scp.HomeSteps;
import sew.ai.utils.PropertiesUtil;
import java.sql.SQLException;
public class NotificationPreferenceSteps extends NotificationPreferencePage {
	private static final Logger log = LogManager.getLogger(NotificationPreferenceSteps.class);
	public static PropertiesUtil NotificationpreferenceTextProp;
	HomeSteps homeSteps = new HomeSteps(driver);
	
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

	public void notificationPreferencePageObjects(SoftAssert sAssert) {

		HomeSteps homeSteps = new HomeSteps(driver);

		// To verify the navigation to notification preferences page.
		log.info("To verify the redirection on " + "Notification Preference page");
		homeSteps.navigateToNotificationPreferences();
		Assert.assertTrue(isNotificationPreferencePage(
				NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageUrl"),
				NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageTitle")));
		// To verify the object on the notification preference page.
		isLblNotificationsVisible();
		sAssert.assertEquals(getLevelNotification(),
				NotificationpreferenceTextProp.getPropValue("txtLblNotificationsNpp"));

		// Outage
		isLblOutageVisible();
		sAssert.assertEquals(getLevelOutage(), NotificationpreferenceTextProp.getPropValue("txtLblOutageNpp"));
		clickLevelOutage();

		sAssert.assertTrue(isIconOutageStatusVisible(), "Icon Outage Status is not visible");
		// sAssert.assertTrue(registerCommercialLnk());
		sAssert.assertTrue(isChkBoxOutageVisible(), "Check Box Outage is not visible");
		// sAssert.assertTrue(islstOutageChannelVisible());
		sAssert.assertTrue(isIconOutageAddVisible(), "Icon Outage Add is not visible");
		sAssert.assertTrue(isBtnOutageCancelVisible(), "Outage cancel button is not visible");
		sAssert.assertTrue(isBtnOutageSaveVisible(), "Outage save button is not visible");
		clickLevelOutage();

		// Billing
		sAssert.assertTrue(isLblBillingTextVisible(), "Billing text label is not visible");
		sAssert.assertEquals(getLevelBilling(), NotificationpreferenceTextProp.getPropValue("txtLblBillingNpp"));
		linkLblBillingText();
		pause(2000);
		sAssert.assertTrue(isIconBillingStatusVisible(), "Billing icon status is not visible");
		sAssert.assertTrue(isBtnBillingCancelVisible(), "Billing cancel button is not visible");
		sAssert.assertTrue(isBtnBillingSaveVisible(), "Billing save button is not visible");
		sAssert.assertTrue(isChkBoxBillingVisible(), "Billing checkbox is not visible");
		linkLblBillingText();
		// Budget
		/*
		 * sAssert.assertEquals(getLevelBudget(),
		 * NotificationpreferenceTextProp.getPropValue("txtLblBudgetNpp"));
		 * linklevelBudget(); pause(2000); sAssert.assertTrue(isLblBudgetTextVisible(),
		 * "Budget text label is not visible");
		 * sAssert.assertTrue(isBtnBudgetCancelVisible(),
		 * "Budget cancel button is not visible"); //
		 * sAssert.assertTrue(islstBudgetOption1Visible(), "Budget first option is not
		 * // visible"); sAssert.assertTrue(isBtnBudgetCancelVisible(),
		 * "Budget cancel button is not visible");
		 * sAssert.assertTrue(isBtnBudgetSaveVisible(),
		 * "Budget save button is not visible");
		 * sAssert.assertTrue(isIconBudgetAddVisible(),
		 * "Budget icon Add button is not visible"); linklevelBudget();
		 */

		// Connect Me
		isLblContactUsTextVisible();
		sAssert.assertEquals(getLevelContactUsText(), NotificationpreferenceTextProp.getPropValue("txtLblConnectNpp"));
		clickLevelContactUsText();
		pause(2000);
		sAssert.assertTrue(isIconConnectMeStatusVisible(), "connect me icon status is not visible");
		sAssert.assertTrue(ischkBoxConnectMeVisible(), "connect me checkbox is not visible");
		sAssert.assertTrue(isLblContactUsTextVisible(), "connect me text label is not visible");
		sAssert.assertTrue(islstConnectMeOptionsVisible(), "connect me first options is not visible");
		sAssert.assertTrue(islstConnectMeOptionsTxtVisible(), "connect me first options text is not visible");
		sAssert.assertTrue(isIconContactUsAddVisible(), "connect me add icon is not visible");
		sAssert.assertTrue(isBtnContactUsCancel(), "connect me cancel button is not visible");
		sAssert.assertTrue(isBtnContactUsSave(), "connect me save button is not visible");
		clickLevelContactUsText();
		// isBtnConnectMeClose();

		// Service
		isLblServiceTextVisible();
		sAssert.assertEquals(getLevelServiceText(), NotificationpreferenceTextProp.getPropValue("txtLblServiceNpp"));
		clickLevelServiceText();
		pause(2000);
		sAssert.assertTrue(isIconServiceStatusVisible(), "connect me icon status is not visible");
		sAssert.assertTrue(ischkBoxServiceVisible(), "connect me checkbox is not visible");
		sAssert.assertTrue(isLblServiceTextVisible(), "connect me text label is not visible");
		sAssert.assertTrue(islstServiceOptionsVisible(), "connect me first options is not visible");
		sAssert.assertTrue(islstServiceOptionsTxtVisible(), "connect me first options text is not visible");
		sAssert.assertTrue(isBtnServiceCancel(), "connect me cancel button is not visible");
		clickLevelServiceText();
		log.info("To Verify that notification PreferencePageObjects are visible on page");
	}

	public void tcpaCompliancePopup(SoftAssert sAssert) {
		HomeSteps homeSteps = new HomeSteps(driver);

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
		selectlstOutageChannel("Text");
		checkChkBoxOutage();
		populateFirstMobilenumber("9999999999");
		btnOutageSaveBtn();
		pause(5000);
		sAssert.assertTrue(isLblTCPAacceptNotificatinTerms(),
				"TCPA Compliance Terms and Conditions popup is not appearing ");
		sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue("txtLblAcceptNotificationPopupHeadingAos"),
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

	}

	public void quietHoursFunctionalityAndUI(SoftAssert sAssert) throws SQLException {

		log.info("Test Case execution for - verifyQuietHoursSave - is Initiated");
		log.info("To verify the redirection on " + "Notification Preference page");
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToNotificationPreferences();
		Assert.assertTrue(isNotificationPreferencePage(
				NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageUrl"),
				NotificationpreferenceTextProp.getPropValue("expectedNotificationPrePageTitle")));

		log.info("To Verify that Quiet hours UI and Success messages");
		sAssert.assertTrue(isLblQuietHours(), "Quiet hours lebal is not appeearing");
		sAssert.assertTrue(islnkQuietHoursEdit(), "Quiet hours edit link is not appeearing");
		sAssert.assertTrue(isLblQUietHoursRange(), "Quiet hours range or status is not appeearing");
		clicklnkQuietHoursEdit();
		sAssert.assertEquals(getTxtQuietHours(), NotificationpreferenceTextProp.getPropValue("txtLblQuietHoursNpp"));
		sAssert.assertTrue(isChkBoxEnableQuietHours(), "checkboxnot enabled for quiethours");
		sAssert.assertTrue(islstStartTimeQuietHours(), "first start time not visible for quiet hours");
		sAssert.assertTrue(islstEndTimeQuietHours(), "end time not visible for quiet hours");
		sAssert.assertTrue(islstTimeZoneQuietHours(), "first time zone is not visible for quit hours");
		sAssert.assertTrue(isBtnCancelQuietHours(), "cancel quiet button is not visible");
		sAssert.assertTrue(isBtnSaveQuietHours(), "save quiet button is not visible");
		log.info("To verify that application will not allow user to set Same From and To time");
		selectlstStartTimeQuietHours("10:00 PM");
		selectlstEndTimeQuietHours("10:00 PM");
		SaveQuietHoursBtn();
		sAssert.assertEquals(getLblToastMessage(),
				NotificationpreferenceTextProp.getPropValue("txtLblFromToDateMessageNpp"));
		log.info("To Verify If the Quite Hours are Active then the set Start Time and End ");

		log.info("Test Case execution for - verifyQuietHoursFunctionalityAndUI - is completed");

	}

	public void addNotificationChannelFunctionality(SoftAssert sAssert) {
		log.info(" To Verify that User is not able to add same type of notification channels for a particular module");
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

		log.info("Test Case execution for - verifyNotificationsChannelsValidations - is finished");
	}

	public void notificationsChannelsValidations(SoftAssert sAssert) {
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
		log.info("Test Case execution for - verifyNotificationsChannelsValidations - is finished");

	}

	public void notificationAlertsValidationMessages(SoftAssert sAssert) throws SQLException {
		log.info("To Verify that application will display common message if more than one mandatory field "
				+ "left blank");
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
			}
			selectlstBillingChannel("Email");
			unCheckChkBoxBilling();
			billingSaveBtn();
			pause(8000);
		}

		if (getIconConnectMeStatus().equalsIgnoreCase("On")) {
			clickLevelContactUsText();
			for (int i = 0; i < getlstConnectMeCloseIcons().size(); i++) {
				getlstConnectMeCloseIcons().get(i).click();
			}
			selectlstConnectMeOptions("Email");
			unCheckConnectMe();
			clickBtnConnectMeSave();
			pause(8000);
		}
		linkLblBillingText();
		log.info(
				" To verify that application will display validation message if user left any one mandatory field blank");
		selectlstBillingChannel("Email");
		clearlstBillingOptionsTxt();
		checkBoxBilling();
		billingSaveBtn();

		sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue("txtEmailBlankNpp"), getLblToastMessage(),
				"Email blank validation message is not correct");
		clickbtnCloseMsgOnHeader();
		pause(3000);
		selectlstBillingChannel("Text");
		clearlstBillingOptionsTxt();
		checkBoxBilling();
		billingSaveBtn();
		sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue("txtTextBlankNpp"), getLblToastMessage(),
				"Text blank validation message is not correct");
		clickbtnCloseMsgOnHeader();
		pause(3000);
		selectlstBillingChannel("IVR");
		clearlstBillingOptionsTxt();
		checkBoxBilling();
		billingSaveBtn();
		sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue("txtIVRBlankNpp"), getLblToastMessage(),
				"IVR blank validation message is not correct");
		clickbtnCloseMsgOnHeader();
		pause(3000);
		selectlstBillingChannel("Whatsapp");
		clearlstBillingOptionsTxt();
		checkBoxBilling();
		billingSaveBtn();

		sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue("txtTextBlankNpp"), getLblToastMessage(),
				"Whatsapp blank validation message is not correct");
		clickbtnCloseMsgOnHeader();
		pause(3000);
		log.info(" To verify that application will display message 'Please enter a valid Email Address.'"
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
				" - Verify the validation message in case of field minimum length=maximum length and provided value as all Zero");
		selectlstBillingChannel("Text");
		clearlstBillingOptionsTxt();
		populatelstBillingOptionsTxt("0000000000");
		checkBoxBilling();
		billingSaveBtn();
		sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue("txtTextValidationMessageNpp"),
				getLblToastMessage(), "Mobile Number All Zero validation message is not correct");
		clickbtnCloseMsgOnHeader();
		pause(3000);
		/*
		 * selectlstBillingChannel("Text"); clearlstBillingOptionsTxt();
		 * populatelstBillingOptionsTxt("0000111111"); checkBoxBilling();
		 * billingSaveBtn();
		 * sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue(
		 * "txtTextValidationMessageNpp"), getLblToastMessage(),
		 * "Mobile Number incorrect format validation message is not correct");
		 * clickbtnCloseMsgOnHeader(); pause(5000);
		 */

		/*
		 * selectlstBillingChannel("IVR"); clearlstBillingOptionsTxt();
		 * populatelstBillingOptionsTxt("0000111111");
		 * 
		 * checkBoxBilling(); billingSaveBtn();
		 * sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue(
		 * "txtMobile10_digitVaidationNpp"), getLblToastMessage(),
		 * "Mobile Number incorrect format validation message is not correct");
		 * clickbtnCloseMsgOnHeader(); pause(3000);
		 */
		selectlstBillingChannel("IVR");
		clearlstBillingOptionsTxt();
		populatelstBillingOptionsTxt("0000000000");
		checkBoxBilling();
		billingSaveBtn();

		sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue("txtMobile10_digitVaidationNpp"),
				getLblToastMessage(), "Mobile Number All Zero validation message is not correct");
		clickbtnCloseMsgOnHeader();
		pause(3000);

		log.info("- Verify the validation message in case of field minimum length=maximum length and provided value length is less than the required");
		// clickLevelContactMeText();
		selectlstBillingChannel("Text");
		clearlstBillingOptionsTxt();
		populatelstBillingOptionsTxt("123456789");
		checkBoxBilling();
		billingSaveBtn();
		sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue("txtTextValidationMessageNpp"),
				getLblToastMessage(), "Mobile Number 10-Digit validation message is not correct");
		clickbtnCloseMsgOnHeader();
		pause(3000);
		selectlstBillingChannel("IVR");
		clearlstBillingOptionsTxt();
		populatelstBillingOptionsTxt("123456789");
		checkBoxBilling();
		billingSaveBtn();
		sAssert.assertEquals(NotificationpreferenceTextProp.getPropValue("txtMobile10_digitVaidationNpp"),
				getLblToastMessage(), "Mobile Number 10-Digit validation message is not correct");
		clickbtnCloseMsgOnHeader();
		pause(3000);
		log.info("Test Case execution for - verifyNotificationAlertsValidationMessages - is finished");
	}

}
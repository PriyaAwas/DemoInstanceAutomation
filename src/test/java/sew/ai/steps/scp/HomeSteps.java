package sew.ai.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.pageObjects.scp.HomePage;
import sew.ai.utils.PropertiesUtil;

public class HomeSteps extends HomePage {
	private static final Logger log = LogManager.getLogger(HomeSteps.class);
	public static PropertiesUtil homeTextProp;

	public HomeSteps(WebDriver driver) {
		super(driver);
		homeTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.HOME_TXT_FILENAME);
	}

	public void navigateToFAQS() {
		log.info("Navigating to the FAQS page.");
		clickFAQSLink();
		waitForPageLoader();
		log.info("Navigated to the FAQS page.");
	}

	// Home Navigation
	public void navigateToHome() {
		log.info("Navigating to the Home page.");
		clickHomeLink();
		// waitForPageLoader();
		pause(2000);
		log.info("Navigated to the Home page.");
	}

	// My Account Navigation
	public void navigateToMyAccountProfile() {
		log.info("Navigating to the My Account Profile page.");
		clickMyAccountLink();
		pause(2000);
		clickProfileLink();
		waitForPageLoader();
		log.info("Navigated to the My Account Profile page.");
	}

	public void navigateToPaymentInfo() {
		log.info("Navigating to the Payment Information page.");
		clickMyAccountLink();
		pause(2000);
		clickPaymentInfoLink();
		pause(2000);
		//waitForPageLoader();
		log.info("Navigated to the Payment Information page.");
	}

	public void navigateToAboutMyHomeBusiness() {
		log.info("Navigating to the About My Home and Business page.");
		clickMyAccountLink();
		pause(2000);
		clickAboutMyHomeBusinessLink();
		waitForPageLoader();
		log.info("Navigated to the About My Home and Business page.");
	}

	public void navigateToNotificationPreferences() {
		log.info("Navigating to the Notification Preferences page.");
		pause(3000);
		clickMyAccountLink();
		pause(3000);
		clickNotificationPreferenceLink();
		pause(2000);
		// waitForPageLoader();
		log.info("Navigated to the Notification Preferences page.");
	}

	public void navigateToMarketingPreferencePage() {
		log.info("Navigating to the Marketing Preferences page.");
		clickMyAccountLink();
		pause(2000);
		clickMyProfileLink();//
		// waitForPageLoader();
		pause(3000);
		log.info("Navigated to the My Profile/ Marketing Preferences page.");
	}

	public void navigateToMyProfilePage() {
		log.info("Navigating to the My Profile page.");
		waitForPageToLoad();
		pause(5000);
		clickMyAccountLink();
		pause(3000);
		clickMyProfileLink();
		// waitForPageLoader();
		waitForPageToLoad();
		pause(3000);
		log.info("Navigated to the My Profile page.");
	}

	public void navigateToAboutMyHomePage() {
		log.info("Navigating to the About My Home page.");
		clickMyAccountLink();
		pause(3000);
		clickAboutMyHomeLink();
		// waitForPageLoader();
		pause(3000);
		log.info("Navigated to the  About My Home page.");
	}

	public void navigateToGuestUser() {
		log.info("Navigating to the Guest User page.");
		pause(2000);
		clickMyAccountLink();
		pause(2000);
		clickGuestUserLink();
		// waitForPageLoader();
		log.info("Navigated to the Guest User page.");
	}

	public void navigateToAccountSettings() {
		log.info("Navigating to the Account Settings page.");
		clickMyAccountLink();
		pause(2000);
		clickAccountSettingsLink();
		waitForPageLoader();
		log.info("Navigated to the Account Settings page.");
	}

	public void navigateToAccountInformation() {
		log.info("Navigating to the Account Information page.");
		clickMyAccountLink();
		pause(2000);
		clickAccountInfoLink();
		pause(2000);
		log.info("Navigated to the Account Information page.");
	}

	// Billing and Payments Navigation
	public void navigateToCurrentBill() {
		log.info("Navigating to the Current Bill page.");
		clickBillingLink();
		pause(2000);
		clickCurrentBillLink();
		waitForPageLoader();
		log.info("Navigated to the Current Bill page.");
	}

	public void navigateToAutoPayment() {
		log.info("Navigating to the Autopay page.");
		clickBillingLink();
		pause(2000);
		clickAutopayLink();
		pause(2000);
		//waitForPageLoader();
		log.info("Navigated to the Autopay page.");
	}

	public void navigateToPaymentLocations() {
		log.info("Navigating to the Payment Location page.");
		clickBillingLink();
		pause(2000);
		clickPaymentLocationLink();
		waitForPageLoader();
		log.info("Navigated to the Payment Location page.");
	}

	public void navigateToBillingAndPaymentHistory() {
		log.info("Navigating to the Billing History page.");
		clickBillingLink();
		pause(2000);
		clickPaymentLocationLink();
		waitForPageLoader();
		log.info("Navigated to the Payment Location page.");
	}

	public void navigateToBudgetMyBill() {
		log.info("Navigating to the Budget My Bill page.");
		clickBillingLink();
		pause(2000);
		clickBudgetMyBillLink();
		// waitForPageLoader();
		log.info("Navigated to the Budget My Bill page.");
	}

	public void navigateToRateAnalysis() {
		log.info("Navigating to the Rate Analysis page.");
		clickBillingLink();
		pause(2000);
		clickRateAnalysisLink();
		waitForPageLoader();
		log.info("Navigated to the Rate Analysis page.");
	}

	public void navigateToLevelPay() {
		log.info("Navigating to the Rate Analysis page.");
		clickBillingLink();
		pause(2000);
		clickLevelPayLink();
		waitForPageLoader();
		log.info("Navigated to the Rate Analysis page.");
	}

	public void navigateToTextToPay() {
		log.info("Navigating to the Text To Pay page.");
		clickBillingLink();
		pause(3000);
		clickTextToPayLink();
		waitForPageLoader();
		log.info("Navigated to the Text To Pay page.");
	}

	// Usage Navigation
	public void navigateToUsageOverview() {
		ExtentLogger.logInfo("Navigating to the Usage Overview page.");
		log.info("Navigating to the Usage Overview page.");
		clickUsageLink();
		pause(2000);
		clickUsageOverviewLink();
		waitForPageLoader();
		log.info("Navigated to the Usage Overview page.");
		ExtentLogger.logInfo("Navigated to the Usage Overview page.");
	}

	public void navigateToCompare() {
		ExtentLogger.logInfo("Navigating to the Compare Spending page.");
		log.info("Navigating to the Compare page.");
		clickUsageLink();
		pause(2000);
		clickUsageOverviewLink();
		waitForPageLoader();
		log.info("Navigated to the Compare page.");
		ExtentLogger.logInfo("Navigated to the Compare Spending page.");
	}

	// Services Navigation
	public void navigateToServicesOverview() {
		log.info("Navigating to the Services Overview page.");
		clickServiceHeaderLink();
		pause(2000);
		clickServiceOverviewLink();
		waitForPageLoader();
		log.info("Navigated to the Services Overview page.");
	}

	public void navigateToMoveInServices() {
		log.info("Navigating to the Move In Services page.");
		clickServiceHeaderLink();
		pause(2000);
		clickMoveInServiceLink();
		waitForPageLoader();
		log.info("Navigated to the Move In Services page.");
	}

	public void navigateToMoveOutServices() {
		log.info("Navigating to the Move Out Services page.");
		clickServiceHeaderLink();
		pause(2000);
		clickMoveOutServiceLink();
		waitForPageLoader();
		log.info("Navigated to the Move Out Services page.");
	}

	public void navigateToServiceTransfer() {
		log.info("Navigating to the Service Transfer page.");
		clickServiceHeaderLink();
		pause(2000);
		clickServiceTransferLink();
		waitForPageLoader();
		log.info("Navigated to the Service Transfer page.");
	}

	public void navigateToOtherService() {
		log.info("Navigating to the Others Service page.");
		clickServiceHeaderLink();
		pause(2000);
		clickOtherServicesLink();
		waitForPageLoader();
		log.info("Navigated to the Others Services page.");
	}

	public void navigateToSmartCitiesRequests() {
		log.info("Navigating to the Smart Cities Request page.");
		clickServiceHeaderLink();
		pause(2000);
		clickSmartCitiesLink();
		waitForPageLoader();
		log.info("Navigated to the Smart Cities Request page.");
	}

	// Navigation to the Outages
	public void navigateToOutagesPage() {
		log.info("Navigating to the Outages page.");
		clickOutagesLink();
		waitForPageLoader();
		log.info("Navigated to the Outages page.");
	}

	// Navigation Contact Us.
	public void navigateToContactUs() {
		log.info("Navigating to the Outages page.");
		clickContactUsLink();
		waitForPageLoader();
		log.info("Navigated to the Outages page.");
	}

	// Navigation Ways To Save
	public void navigateToWaysToSaveOverview() {
		log.info("Navigating to the Smart Cities Request page.");
		clickServiceHeaderLink();
		pause(2000);
		clickSmartCitiesLink();
		waitForPageLoader();
		log.info("Navigated to the Smart Cities Request page.");
	}

	public void navigateToFootPrintPage() {
		log.info("Navigation to the Foot Print Page.");
		clickWaysToSaveLink();
		clickFootPrintLink();
		ExtentLogger.logInfo("Navigation to FootPrint Page Redirect Successfully.");
	}

	public void verifyBillingAndPaymentsNavigation(SoftAssert softAssert) {
	}
}

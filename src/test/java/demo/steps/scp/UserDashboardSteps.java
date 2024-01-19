package demo.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import sew.ai.driver.DriverFactory;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.User;
import demo.pageobjects.DashboardPage;
import sew.ai.pageObjects.scp.LoginPage;
import sew.ai.steps.scp.BillDashboardSteps;
import sew.ai.steps.scp.BillPaymentSteps;
import sew.ai.steps.scp.CompareSteps;
import sew.ai.steps.scp.SignOutSteps;
import sew.ai.steps.scp.UsageSteps;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.DateUtil;
import sew.ai.utils.MathUtil;
import sew.ai.utils.PropertiesUtil;

import java.sql.ResultSet;
import java.util.List;

import static sew.ai.steps.scp.BillDashboardSteps.billDashboardTextProp;
import static sew.ai.steps.scp.BillPaymentSteps.paymentTextProp;
import static sew.ai.steps.scp.CompareSteps.compareTextProp;
import static sew.ai.steps.scp.SignOutSteps.signOutTextProp;
import static sew.ai.steps.scp.UsageSteps.usageTextProp;

public class UserDashboardSteps extends DashboardPage {
	private static final Logger log = LogManager.getLogger(UserDashboardSteps.class);
	public static PropertiesUtil dashboardTextProp;

	public UserDashboardSteps(WebDriver driver) {
		super(driver);
		dashboardTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.DASHBOARD_TXT_FILENAME);
	}

	/**
	 * This method verify the salutation on the dashboard page.
	 *
	 * @param softAssert this verifies all the soft assertion.
	 */
	public void verifySalutationOnTheDashboard(SoftAssert softAssert) {
		double CurrentTime = DateUtil.getCurrentTimeInHourFormate();
		if (CurrentTime >= 04.00 && CurrentTime <= 11.59) {
			softAssert.assertEquals(getWishSalutation(), dashboardTextProp.getPropValue("lblGoodMorningSalutation"));
		} else if (CurrentTime >= 12.00 && CurrentTime <= 16.59) {
			softAssert.assertEquals(getWishSalutation(), dashboardTextProp.getPropValue("lblGoodAfternoonSalutation"));
		} else if (CurrentTime >= 17.00 && CurrentTime <= 3.59) {
			softAssert.assertEquals(getWishSalutation(), dashboardTextProp.getPropValue("lblGoodEveningSalutation"));
		}
	}

	/**
	 * This method verifies the objects on the dashboard page.
	 *
	 * @param softAssert verify all the soft verification.
	 * @param user  this models contains all the user details.
	 */
	public void verifyTheDashboardPageObjectSteps(SoftAssert softAssert) {
		// Verify the salutation
		verifySalutationOnTheDashboard(softAssert);
		// Verify Notification badge
		waitForNotificationBadgeIcon();
		softAssert.assertTrue(isNotificationAlertBadgeVisible(),
				"Notification alert badge is not visible on the page.");
		// Verify utility icon
		softAssert.assertTrue(isUtilityIconOnDashboardVisible(), "Utility Icon on the page is not visible.");
		// Verify customer name in drop-down
		softAssert.assertTrue(isCustomerNameNavDropDownVisible(),
				"Customer Name drop-down is not visible in the top navbar.");
		//Verify GoodMorning Text visible on Dashboard
		softAssert.assertTrue(isGoodMorningLabelVisible(), "GoodMorning Visible .");
		clickCustomerNameNavDropDown();
		// Verify change password link
		isChangePasswordLinkVisible();
		softAssert.assertEquals(getChangePasswordLabel(), dashboardTextProp.getPropValue("lblChangePasswordLink"),
				"Change password link label not matched.");
		// Verify sign-out link
		softAssert.assertTrue(isSignOutLnkVisible(), "Sign out link is not visible.");
		softAssert.assertEquals(getSignOutLinkLabel(), dashboardTextProp.getPropValue("lblSignOutLink"),
				"Sign-out link label not matched.");
		// Verify dashboard page footer
		scrollToTheBottomOfPage();
		// Verify product version
		softAssert.assertTrue(isLblProductVersionVisible(), "Product version is not visible.");
		softAssert.assertEquals(getProductVersionLabel(), dashboardTextProp.getPropValue("lblProductVersion"),
				"Product version label not matched.");
		// Verify contact us hyperlink in footer
		softAssert.assertTrue(isContactUsLinkVisible(), "Contact Us link in the footer is not visible.");
		softAssert.assertEquals(getContactUsLinkLabel(), dashboardTextProp.getPropValue("lblContactUsLink"),
				"Contact us label not matched.");
		// Verify terms and conditions hyperlink
		softAssert.assertTrue(isTermsAndConditions(), "Terms and conditions link is not visible on the page.");
		softAssert.assertEquals(getTermsAndConditionsLinkLabel(),
				dashboardTextProp.getPropValue("lblTermsAndConditions"),
				"Terms and Conditions link label not matched.");
		// Verify privacy policy hyperlink
		softAssert.assertTrue(isPrivacyPoliciesLinkVisible(), "Privacy Policies link is not visible on the page.");
		softAssert.assertEquals(getPrivacyPolicyLinkLabel(), dashboardTextProp.getPropValue("lblPrivacyPolicy"),
				"Privacy Policies link label not matched.");
		// Verify powered by logo
		softAssert.assertTrue(isPoweredByLogoVisible(), "Powered By logo is not present.");
		// Verify the sign-out feature
		scrollToTheTopOfPage();
		// Log out from the application.
		clickCustomerNameNavDropDown();
		SignOutAndBackToLoginPage(driver);
		// waitForPageLoader();
		pause(5000);
		SignOutSteps signOutSteps = new SignOutSteps(driver);
		softAssert.assertEquals(signOutSteps.getSignOutSuccessfullyLbl(),
				signOutTextProp.getPropValue("lblYouHaveSignedOut"),
				"You have signed out successfully message not matched.");
	}

	/**
	 * This method verify billing summary section steps
	 *
	 * @param softAssert verify all the assertions
	 */
	public void verifyBillingSummarySectionSteps(SoftAssert softAssert) {

		softAssert.assertTrue(isRemainingBalanceLabelVisible(), "Remaining balance label not visible.");
		softAssert.assertTrue(isRemainingBalanceIconVisible(), "Remaining balance icon is not visible.");
		hoverRemainingBalanceIcon();
		softAssert.assertTrue(isDueDateLabelVisible(), "Due date label not matched.");
		softAssert.assertTrue(isMakePaymentLnkVisible(), "Make payment link is not visible.");
		// Click make payment link
		clickMakePaymentLink();
		pause(15000);
		BillPaymentSteps billPaymentSteps = new BillPaymentSteps(driver);
		// Verifying bill payment page.
		Assert.assertTrue(billPaymentSteps.isPaymentPage(paymentTextProp.getPropValue("paymentPageUrl"),
				paymentTextProp.getPropValue("paymentPageTitle")), "Not navigated to bill payment page.");
		DriverFactory.navigateBack();
		pause(7000);
		Assert.assertTrue(isDashboardPage(dashboardTextProp.getPropValue("dashboardPageUrl"),
				dashboardTextProp.getPropValue("dashboardPageHeader")), "Not navigated to dashboard page.");
		softAssert.assertTrue(isViewBillLinkVisible(), "View Bill link not visible.");
		clickViewBillLink();
		// homeSteps.waitForPageLoader();
		pause(7000);
		BillDashboardSteps billDashboardSteps = new BillDashboardSteps();
		// Verifying bill dashboard page
		Assert.assertTrue(
				billDashboardSteps.isBillDashboardPage(billDashboardTextProp.getPropValue("billDashboardPageUrl"),
						billDashboardTextProp.getPropValue("billDashboardPageTitle")),
				"Not navigated to bill dashboard page.");
		DriverFactory.navigateBack();
		// homeSteps.waitForPageLoader();
		pause(7000);
		Assert.assertTrue(isDashboardPage(dashboardTextProp.getPropValue("dashboardPageUrl"),
				dashboardTextProp.getPropValue("dashboardPageHeader")), "Not navigated to dashboard page.");
	}

	/**
	 * This method verifies dashboard usage section.
	 *
	 * @param softAssert verify all the assertions
	 */
	public void verifyDashboardUsages(SoftAssert softAssert) {
		// Verify how u doing
		isHowYouAreDoingSoFarVisible();
		softAssert.assertEquals(getHowYouAreDoingSoFar(),
				dashboardTextProp.getPropValue("lblHowYouDoing"), "How You Doing not matched.");	
		// Verifying Last Bill
		softAssert.assertEquals(getProjectedBillLabel(), dashboardTextProp.getPropValue("lblLastBill"),
				"Projected bill label not matched");
		// Verifying Month before Last Bill
				softAssert.assertEquals(getLastBillLabel(), dashboardTextProp.getPropValue("lblMonthBeforeLast"),
						"Last bill label not matched.");		
		// Verifying last year
		softAssert.assertEquals(getLastYearLabel(), dashboardTextProp.getPropValue("lblLastYear"),
				"Last year label not matched");

	}

	/**
	 * This method verifies AutoPay carousel.
	 *
	 * @param softAssert verify all the assertions
	 */
	public void verifyAutoPayCarousel(SoftAssert softAssert) {
		// Scroll to bottom
		scrollToBottom();
		pause(1000);
		// Check if AutoPay button is visible
		if (isAutoPayCarouselSectionVisible()) {	
			clickNextSwiperBtn();
			pause(2000);
			softAssert.assertEquals(getAutoPayTitleLabel(), dashboardTextProp.getPropValue("lblAutoPayTitle"),
					"AutoPay title not matched.");
			softAssert.assertEquals(getAutoPayTitleLabel(), dashboardTextProp.getPropValue("lblAutoPayHeader"),
					"AutoPay header not matched.");
			softAssert.assertEquals(getAutoPayTitleLabel(), dashboardTextProp.getPropValue("lblAutoPaySubTitle"),
					"AutoPay sub title not matched.");
		 softAssert.assertEquals(getAutoPayBtnLabel(), dashboardTextProp.getPropValue("lblEnrollAutoPay"));	
						}
			}
		

	/**
	 * This method verifies Smart Home carousels
	 *
	 * @param softAssert verify all the assertions
	 */
	public void verifySmartHomeCarousel(SoftAssert softAssert) {
		// Scroll to bottom
		scrollToBottom();
		pause(1000);
		if (isSmartDeviceSectionVisible()) {
			softAssert.assertEquals(getSmartDevicesConnectedLabel(),
					dashboardTextProp.getPropValue("lblSmartDevicesConnected"),
					"Your smart devices connected label not matched.");
			
			softAssert.assertEquals(getAddedSmartDevices(), dashboardTextProp.getPropValue("lblSmartDeviceName"),
					"Smart device name label not matched.");
			int count = 0;
			String[] expLabelCombined = dashboardTextProp.getPropValue("lblSmartDeviceOptions").split(",");
		
			List<WebElement> smartHomeIconElements = getSmartHomeLinksElement().findElements(getSmartHomeLinksIcon());
			List<WebElement> smartHomeLabelElements = getSmartHomeLinksElement().findElements(getSmartHomeLinksLabel());
			for (WebElement element : smartHomeIconElements) {
				softAssert.assertTrue(isElementVisible(element), "Icon is not displayed on the page.");
				String actLabel = getText(smartHomeLabelElements.get(count));
				String expLabel = expLabelCombined[count].trim();
				softAssert.assertEquals(actLabel, expLabel, "Buttons label not matched at index {}: " + count);
				count++;
			}
		
		}
	}
	
	/**
	 * Check whether it is dashboard page or not.
	 *
	 * @param url   Dashboard page URL.
	 * @param title Dashboard page Title.
	 * @return status of navigation if it is dashboard page.
	 */
	public Boolean isDashboardPage(String url, String title) {
		Boolean isDashboardPage = false;
		log.info("Checking that the current page is dashboard page.");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isDashboardPage = true;
		log.info("The current page is dashboard page {}: " + isDashboardPage);
		return isDashboardPage;
	}
	
	public void SignOutAndBackToLoginPage(WebDriver driver) {
		DashboardPage dashboardpage = new DashboardPage(driver);
		LoginPage loginpage = new LoginPage(driver);
		pause(5000);
		dashboardpage.clickSignOutDdLink();
		pause(5000);
		dashboardpage.clickSignOutLnk();
		pause(2000);

	}
}

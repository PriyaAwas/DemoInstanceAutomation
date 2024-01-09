package sew.ai.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import sew.ai.api.endpoints.usage.UsageEndpoints;
import sew.ai.config.CSPConfiguration;
import sew.ai.config.SCPConfiguration;
import sew.ai.driver.DriverFactory;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.Meter;
import sew.ai.models.User;
import sew.ai.pageObjects.scp.DashboardPage;
import sew.ai.pageObjects.scp.LoginPage;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.DateUtil;
import sew.ai.utils.MathUtil;
import sew.ai.utils.PropertiesUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static sew.ai.steps.scp.BillDashboardSteps.billDashboardTextProp;
import static sew.ai.steps.scp.BillPaymentSteps.paymentTextProp;
import static sew.ai.steps.scp.CompareSteps.compareTextProp;
import static sew.ai.steps.scp.SignOutSteps.signOutTextProp;
import static sew.ai.steps.scp.UsageSteps.usageTextProp;

public class DashboardSteps extends DashboardPage {
	private static final Logger log = LogManager.getLogger(DashboardSteps.class);
	public static PropertiesUtil dashboardTextProp;

	public DashboardSteps(WebDriver driver) {
		super(driver);
		dashboardTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.DASHBOARD_TXT_FILENAME);
	}

	/**
	 * This method bypass the About My Home page if popped up.
	 */
	public void bypassTheAboutMyHomePage() {
		pause(4000);
		if (isCancelAboutMyHomeBtnDisplayed())
			clickCancelAboutMyHomeBtn();
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
	 * @param user       this models contains all the user details.
	 */
	public void verifyTheDashboardPageObjectSteps(SoftAssert softAssert, User user) {
		// Verify the salutation
		verifySalutationOnTheDashboard(softAssert);
		// Verify customer name
		softAssert.assertEquals(getSalutationCustomerName(), user.getFirstName(),
				"First name in the salutation not matched.");
		// Verify Notification badge
		waitForNotificationBadgeIcon();
		softAssert.assertTrue(isNotificationAlertBadgeVisible(),
				"Notification alert badge is not visible on the page.");
		// Verify utility icon
		softAssert.assertTrue(isUtilityIconOnDashboardVisible(), "Utility Icon on the page is not visible.");
		// Verify customer name in drop-down
		softAssert.assertTrue(isCustomerNameNavDropDownVisible(),
				"Customer Name drop-down is not visible in the top navbar.");
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
		// Verify powered by SEW logo
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
	 * @param user       this model contains all the user details.
	 */
	public void verifyBillingSummarySectionSteps(SoftAssert softAssert, User user) {
		String paymentType = null;
		String billDate = null;
		Map<String, String> utilityBillDetails = new LinkedHashMap<>();
		ResultSet resultSet = DataBaseUtils
				.getResultSet(SQLQueries.getDefaultPaymentType(user.getDefaultUtilityAccNum()));
		ResultSet resultSet1 = DataBaseUtils.getResultSet(SQLQueries.getBillingDate(user.getDefaultUtilityAccNum()));
		try {
			resultSet.next();
			paymentType = resultSet.getString("DefaultPaymentType");
			resultSet1.next();
			billDate = resultSet1.getString("BillingDate");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (paymentType.equals("1")) {
			if (billDate == null) {
				softAssert.assertEquals(getNoBillDataLabel(), dashboardTextProp.getPropValue("lblNoDataMessage"),
						"No bill data label not matched.");
			} else {
				resultSet = DataBaseUtils
						.getResultSet(SQLQueries.getDetailsUtilityBillPage(user.getDefaultUtilityAccNum(), "EN"));
				try {
					while (resultSet.next()) {
						utilityBillDetails.put(resultSet.getString("HeaderName"), resultSet.getString("Value"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				String remainingBalDue = utilityBillDetails.get("Remaining Balance Due");
				String currencyConfig = CSPConfiguration.initDateMetricsConfig().get("Currency");
				if (remainingBalDue.length() >= 0 && !(remainingBalDue == "0") && !remainingBalDue.contains("-")) {
					softAssert.assertTrue(isRemainingBalanceLabelVisible(), "Remaining balance label not visible.");
					// softAssert.assertEquals(getRemainingBalanceLabel(), dashboardTextProp
					// .getPropValue("lblRemainingBalance"), "Remaining balance label not
					// matched.");
					softAssert.assertTrue(isRemainingBalanceIconVisible(), "Remaining balance icon is not visible.");
					hoverRemainingBalanceIcon();
					/*
					 * softAssert.assertTrue(getRemainingBalanceIconLabel().contains(
					 * dashboardTextProp .getPropValue("toolTipRemainingBalance")),
					 * "Remaining balance icon tooltip not matched.");
					 */
					softAssert.assertTrue(isDueDateLabelVisible(), "Due date label not matched.");
					softAssert.assertTrue(isMakePaymentLnkVisible(), "Make payment link is not visible.");
					// Click make payment link
					clickMakePaymentLink();
					// homeSteps.waitForPageLoader();
					pause(7000);
					BillPaymentSteps billPaymentSteps = new BillPaymentSteps(driver);
					// Verifying bill payment page.
					Assert.assertTrue(
							billPaymentSteps.isPaymentPage(paymentTextProp.getPropValue("paymentPageUrl"),
									paymentTextProp.getPropValue("paymentPageTitle")),
							"Not navigated to bill payment page.");
					DriverFactory.navigateBack();
					// homeSteps.waitForPageLoader();
					pause(7000);
					Assert.assertTrue(
							isDashboardPage(dashboardTextProp.getPropValue("dashboardPageUrl"),
									dashboardTextProp.getPropValue("dashboardPageHeader")),
							"Not navigated to dashboard page.");
					softAssert.assertTrue(isViewBillLinkVisible(), "View Bill link not visible.");
					clickViewBillLink();
					// homeSteps.waitForPageLoader();
					pause(7000);
					BillDashboardSteps billDashboardSteps = new BillDashboardSteps();
					// Verifying bill dashboard page
					Assert.assertTrue(
							billDashboardSteps.isBillDashboardPage(
									billDashboardTextProp.getPropValue("billDashboardPageUrl"),
									billDashboardTextProp.getPropValue("billDashboardPageTitle")),
							"Not navigated to bill dashboard page.");
					DriverFactory.navigateBack();
					// homeSteps.waitForPageLoader();
					pause(7000);
					Assert.assertTrue(
							isDashboardPage(dashboardTextProp.getPropValue("dashboardPageUrl"),
									dashboardTextProp.getPropValue("dashboardPageHeader")),
							"Not navigated to dashboard page.");
				} else if (remainingBalDue.length() >= 0 && !(remainingBalDue == "0")
						&& remainingBalDue.contains("-")) {
					Assert.assertEquals(
							getCurrentBalanceLabel().replace(",", "").replace("CR", "").replace(currencyConfig, "")
									.replace("-", "").trim(),
							utilityBillDetails.get("Remaining Balance Due").replace("-", "").trim());
					softAssert.assertEquals(getRemainingBalanceLabel(),
							dashboardTextProp.getPropValue("lblNoAmountDue"));
				} else if (remainingBalDue.equals("0")) {
					Assert.assertEquals(getCurrentBalanceLabel(),
							currencyConfig + utilityBillDetails.get("Remaining Balance Due"));
					softAssert.assertEquals(getRemainingBalanceLabel(),
							dashboardTextProp.getPropValue("lblNoAmountDue"));
				}
			}
		}
	}

	/**
	 * This method verifies billing section with multiple accounts.
	 *
	 * @param softAssert verify all the assertions
	 * @param user       this model contains all the user details.
	 */
	public void verifyBillingSectionWithMultipleAcc(SoftAssert softAssert, User user) {
		String paymentType = null;
		String billDate = null;
		Map<String, String> utilityBillDetails = new LinkedHashMap<>();
		ResultSet resultSet = DataBaseUtils
				.getResultSet(SQLQueries.getDefaultPaymentType(user.getDefaultUtilityAccNum()));
		ResultSet resultSet1 = DataBaseUtils.getResultSet(SQLQueries.getBillingDate(user.getDefaultUtilityAccNum()));
		try {
			resultSet.next();
			paymentType = resultSet.getString("DefaultPaymentType");
			resultSet1.next();
			billDate = resultSet1.getString("BillingDate");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (paymentType.equals("1")) {
			if (billDate == null) {
				softAssert.assertEquals(getNoBillDataLabel(), dashboardTextProp.getPropValue("lblNoDataMessage"),
						"No bill data label not matched.");
			} else {
				if (selectDifferentAccountThanSelectedOneInDropDown(0) != null) {
					resultSet = DataBaseUtils
							.getResultSet(SQLQueries.getDetailsUtilityBillPage(user.getDefaultUtilityAccNum(), "EN"));
					try {
						while (resultSet.next()) {
							utilityBillDetails.put(resultSet.getString("HeaderName"), resultSet.getString("Value"));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					String remainingBalDue = utilityBillDetails.get("Remaining Balance Due");
					String currencyConfig = CSPConfiguration.initDateMetricsConfig().get("Currency");
					if (remainingBalDue.length() >= 0 && !(remainingBalDue == "0") && !remainingBalDue.contains("-")) {
						softAssert.assertTrue(isRemainingBalanceLabelVisible(), "Remaining balance label not matched.");
						// softAssert.assertEquals(getRemainingBalanceLabel(), dashboardTextProp
						// .getPropValue("lblRemainingBalance"), "Remaining balance label not
						// matched.");
						softAssert.assertTrue(isRemainingBalanceIconVisible(),
								"Remaining balance icon is not visible.");
						hoverRemainingBalanceIcon();
						/*
						 * softAssert.assertTrue(getRemainingBalanceIconLabel().contains(
						 * dashboardTextProp .getPropValue("toolTipRemainingBalance")),
						 * "Remaining balance icon tooltip not matched.");
						 */
						softAssert.assertTrue(isDueDateLabelVisible(), "Due date label not matched.");
						softAssert.assertTrue(isMakePaymentLnkVisible(), "Make payment link is not visible.");
						// Click make payment link
						clickMakePaymentLink();
						// homeSteps.waitForPageLoader();
						pause(7000);
						BillPaymentSteps billPaymentSteps = new BillPaymentSteps(driver);
						// Verifying bill payment page.
						Assert.assertTrue(
								billPaymentSteps.isPaymentPage(paymentTextProp.getPropValue("paymentPageUrl"),
										paymentTextProp.getPropValue("paymentPageTitle")),
								"Not navigated to bill payment page.");
						DriverFactory.navigateBack();
						// homeSteps.waitForPageLoader();
						pause(7000);
						Assert.assertTrue(
								isDashboardPage(dashboardTextProp.getPropValue("dashboardPageUrl"),
										dashboardTextProp.getPropValue("dashboardPageHeader")),
								"Not navigated to dashboard page.");
						softAssert.assertTrue(isViewBillLinkVisible(), "View Bill link not visible.");
						clickViewBillLink();
						// homeSteps.waitForPageLoader();
						pause(7000);
						BillDashboardSteps billDashboardSteps = new BillDashboardSteps();
						// Verifying bill dashboard page
						Assert.assertTrue(
								billDashboardSteps.isBillDashboardPage(
										billDashboardTextProp.getPropValue("billDashboardPageUrl"),
										billDashboardTextProp.getPropValue("billDashboardPageTitle")),
								"Not navigated to bill dashboard page.");
						DriverFactory.navigateBack();
						// homeSteps.waitForPageLoader();
						pause(7000);
						Assert.assertTrue(
								isDashboardPage(dashboardTextProp.getPropValue("dashboardPageUrl"),
										dashboardTextProp.getPropValue("dashboardPageHeader")),
								"Not navigated to dashboard page.");
					} else if (remainingBalDue.length() >= 0 && !(remainingBalDue == "0")
							&& remainingBalDue.contains("-")) {
						Assert.assertEquals(
								getCurrentBalanceLabel().replace(",", "").replace("CR", "").replace(currencyConfig, "")
										.replace("-", "").trim(),
								utilityBillDetails.get("Remaining Balance Due").replace("-", "").trim());
						softAssert.assertEquals(getRemainingBalanceLabel(),
								dashboardTextProp.getPropValue("lblNoAmountDue"));
					} else if (remainingBalDue.equals("0")) {
						Assert.assertEquals(getCurrentBalanceLabel(),
								currencyConfig + utilityBillDetails.get("Remaining Balance Due"));
						softAssert.assertEquals(getRemainingBalanceLabel(),
								dashboardTextProp.getPropValue("lblNoAmountDue"));
					}
				}
			}
		}
	}

	/**
	 * This method verifies billing section for pay as you go user.
	 *
	 * @param softAssert verify all the assertions
	 * @param user       this model contains all the user details.
	 */
	public void verifyBillingSectionForPayAsYouGo(SoftAssert softAssert, User user) {
		String paymentType = null;
		Map<String, String> utilityBillDetails = new LinkedHashMap<>();
		ResultSet resultSet = DataBaseUtils
				.getResultSet(SQLQueries.getDefaultPaymentType(user.getDefaultUtilityAccNum()));
		try {
			resultSet.next();
			paymentType = resultSet.getString("DefaultPaymentType");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String remainingBalDue = utilityBillDetails.get("Remaining Balance Due");
		String currencyConfig = CSPConfiguration.initDateMetricsConfig().get("Currency");
		if (paymentType.equals("0")) {
			softAssert.assertTrue(isRemainingBalanceLabelVisible(), "Remaining balance due label not present.");
			softAssert.assertEquals(getRemainingBalanceLabel(), dashboardTextProp.getPropValue("lblRemainingBalance"),
					"Remaining balance due label not matched.");
			softAssert.assertTrue(isRemainingBalanceIconVisible());
			resultSet = DataBaseUtils
					.getResultSet(SQLQueries.getDetailsUtilityBillPage(user.getDefaultUtilityAccNum(), "EN"));
			try {
				while (resultSet.next()) {
					utilityBillDetails.put(resultSet.getString("HeaderName"), resultSet.getString("Value"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			softAssert.assertEquals(getCurrentBalanceLabel().replace(",", ""), currencyConfig + remainingBalDue,
					"Remaining balance due not matched");
			softAssert.assertTrue(isRechargeBtnVisible(), "Recharge button is not visible.");
			clickRechargeBtn();
			HomeSteps homeSteps = new HomeSteps(driver);
			homeSteps.waitForPageLoader();
			BillPaymentSteps billPaymentSteps = new BillPaymentSteps(driver);
			// Verifying bill payment page.
			Assert.assertTrue(billPaymentSteps.isPaymentPage(paymentTextProp.getPropValue("paymentPageUrl"),
					paymentTextProp.getPropValue("paymentPageTitle")), "Not navigated to bill payment page.");
			DriverFactory.navigateBack();
			// homeSteps.waitForPageLoader();
			pause(7000);
			Assert.assertTrue(isDashboardPage(dashboardTextProp.getPropValue("dashboardPageUrl"),
					dashboardTextProp.getPropValue("dashboardPageHeader")), "Not navigated to dashboard page.");
		}
	}

	/**
	 * This method verifies dashboard usage section.
	 *
	 * @param softAssert verify all the assertions
	 * @param user       this model contains all the user details.
	 */
	public void verifyDashboardUsages(SoftAssert softAssert, User user) {
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		Map<String, String> dashboardUsageData;
		UsageEndpoints usageEndpoints = new UsageEndpoints();
		// Init currency config
		String currencyConfig = CSPConfiguration.initDateMetricsConfig().get("Currency");
		// Init collection AMI status
		Boolean isCollectionAMI = true;
		// Init meters linked
		Meter[] meters = SCPConfiguration.initMeterConfiguration(user.getAccountNumber()).getMeters();
		for (Meter meter : meters) {
			if (meter.getIsAmi() == 0) {
				isCollectionAMI = false;
				break;
			}
		}
		// Verify navigation to usage page from dashboard page
		HomeSteps homeSteps = new HomeSteps(driver);
		// Init dashboard old usage data
		dashboardUsageData = usageEndpoints.getDashboardUsageData(user);
		if (isCollectionAMI) {
			// Init dashboard new usage data
			dashboardUsageData = usageEndpoints.getProjectedAllUsageData(user, dashboardUsageData);
			// Verify the section
			softAssert.assertEquals(getBillForecastDisclaimer(),
					dashboardTextProp.getPropValue("lblBillForecastDisclaimer"),
					"Bill forecast disclaimer not matched.");
			// Verifying projected bill
			Double actValue = Double.valueOf(getProjectedBillValue().replace(currencyConfig, ""));
			Double projectedBill = actValue;
			Double expValue = Double
					.valueOf(decimalFormat.format(Double.valueOf(dashboardUsageData.get("projectedAmount"))));
			MathUtil.assertDifference(actValue, expValue);
			softAssert.assertEquals(getProjectedBillText(), dashboardTextProp.getPropValue("txtProjectedBill"),
					"Projected bill text not matched");
			softAssert.assertEquals(getProjectedBillLabel(), dashboardTextProp.getPropValue("lblProjectedBill"),
					"Projected bill label not matched");
			// Verify so far this month
			actValue = Double.valueOf(getSoFarThisMonthValue().replace(currencyConfig, ""));
			expValue = Double.valueOf(decimalFormat.format(Double.valueOf(dashboardUsageData.get("soFarThisMonth"))));
			MathUtil.assertDifference(actValue, expValue);
			softAssert.assertTrue(
					getSoFarThisMonthLabel().contains(dashboardTextProp.getPropValue("lblSoFarThisMonth")),
					"So far this month label not matched");
			// Verifying Last Bill
			actValue = Double.valueOf(getLastBillAMIValue().replace(currencyConfig, ""));
			Double lastBill = actValue;
			expValue = Double.valueOf(decimalFormat.format(Double.valueOf(dashboardUsageData.get("lastBill"))));
			MathUtil.assertDifference(actValue, expValue);
			softAssert.assertEquals(getLastBillAMIText(),
					DateUtil.getMonthName(Integer.parseInt(dashboardUsageData.get("lastBillMonth"))).toUpperCase() + " "
							+ dashboardUsageData.get("lastBillYear"),
					"Last bill text not matched.");
			softAssert.assertEquals(getLastBillAMILabel(), dashboardTextProp.getPropValue("lblLastBill"),
					"Last bill label not matched.");
			// Verifying last year
			actValue = Double.valueOf(getLastYearValue().replace(currencyConfig, ""));
			expValue = Double.valueOf(decimalFormat.format(Double.valueOf(dashboardUsageData.get("lastYear"))));
			MathUtil.assertDifference(actValue, expValue);
			softAssert.assertEquals(getLastYearText(),
					DateUtil.getMonthName(Integer.parseInt(dashboardUsageData.get("lastYearBillMonth"))).toUpperCase()
							+ " " + dashboardUsageData.get("lastYearBillYear"),
					"Last year bill not matched for AMI combo.");
			softAssert.assertEquals(getLastYearLabel(), dashboardTextProp.getPropValue("lblLastYear"),
					"Last year label not matched for the AMI combo.");
			Double difference;
			if (projectedBill < lastBill) {
				String actLabel = getProjectedBillComparisonLabel();
				String expLabel = dashboardTextProp.getPropValue("lblProjectedIsLess");
				difference = lastBill - projectedBill;
				expLabel.replace("{$amount}", String.valueOf(difference));
				softAssert.assertEquals(actLabel, expLabel, "Projected comparison with last month if less.");
			} else {
				String actLabel = getProjectedBillComparisonLabel();
				String expLabel = dashboardTextProp.getPropValue("lblProjectedIsMore");
				difference = projectedBill - lastBill;
				expLabel.replace("{$amount}", String.valueOf(difference));
				softAssert.assertEquals(actLabel, expLabel, "Projected comparison with last month if more.");
			}
			// Verify the usage redirection from the dashboard.
			scrollToBottom();
			pause(1000);
			clickViewYourUsagesLink();
			pause(6000);
			homeSteps.waitForPageLoader();
			UsageSteps usageSteps = new UsageSteps(driver);
			Assert.assertTrue(usageSteps.isUsagePage(usageTextProp.getPropValue("usagePageUrl"),
					usageTextProp.getPropValue("usagePageTitle")), "Not usage page.");
			DriverFactory.navigateBack();
			homeSteps.waitForPageLoader();
			Assert.assertTrue(isDashboardPage(dashboardTextProp.getPropValue("dashboardPageUrl"),
					dashboardTextProp.getPropValue("dashboardPageHeader")), "Not dashboard page.");
		} else {
			// Verifying last bill
			Double actValue = Double.valueOf(getLastBillNonAMIValue().replace(currencyConfig, ""));
			Double expValue = Double.valueOf(decimalFormat.format(Double.valueOf(dashboardUsageData.get("lastBill"))));
			MathUtil.assertDifference(actValue, expValue);
			softAssert.assertEquals(getLastBillNonAMIText(),
					DateUtil.getMonthName(Integer.parseInt(dashboardUsageData.get("lastBillMonth"))).toUpperCase() + " "
							+ dashboardUsageData.get("lastBillYear"),
					"Last bill text not matched for Non AMI.");
			softAssert.assertEquals(getLastBillNonAMILabel(), dashboardTextProp.getPropValue("lblLastBill"),
					"Last bill label not matched");
			// Verifying Month Before Last Bill
			actValue = Double.valueOf(getMonthBeforeLastValue().replace(currencyConfig, ""));
			expValue = Double.valueOf(decimalFormat.format(Double.valueOf(dashboardUsageData.get("monthBeforeLast"))));
			MathUtil.assertDifference(actValue, expValue);
			softAssert.assertEquals(getMonthBeforeLastText(),
					DateUtil.getMonthName(Integer.parseInt(dashboardUsageData.get("monthBeforeLastMonth")))
							.toUpperCase() + " " + dashboardUsageData.get("monthBeforeLastYear"),
					"Month before last date not matched.");
			softAssert.assertEquals(getMonthBeforeLastLabel(), dashboardTextProp.getPropValue("lblMonthBeforeLast"),
					"Month before last label not matched.");
			// Verifying Last Year
			actValue = Double.valueOf(getLastYearValue().replace(currencyConfig, ""));
			expValue = Double.valueOf(decimalFormat.format(Double.valueOf(dashboardUsageData.get("lastYear"))));
			MathUtil.assertDifference(actValue, expValue);
			softAssert.assertEquals(getLastYearText(),
					DateUtil.getMonthName(Integer.parseInt(dashboardUsageData.get("lastYearBillMonth"))).toUpperCase()
							+ " " + dashboardUsageData.get("lastYearBillYear"),
					"Last year bill date not matched for AMI combo.");
			softAssert.assertEquals(getLastYearLabel(), dashboardTextProp.getPropValue("lblLastYear"),
					"Last year label not matched for the AMI combo.");
		}
		// Verify navigation to compare page from dashboard page.
		scrollToBottom();
		pause(1000);
		clickCompareLink();
		pause(6000);
		CompareSteps compareSteps = new CompareSteps(driver);
		Assert.assertTrue(compareSteps.isComparePage(compareTextProp.getPropValue("comparePageUrl"),
				compareTextProp.getPropValue("comparePageTitle")), "Not compare page.");
		DriverFactory.navigateBack();
		pause(6000);
		Assert.assertTrue(isDashboardPage(dashboardTextProp.getPropValue("dashboardPageUrl"),
				dashboardTextProp.getPropValue("dashboardPageHeader")), "Not dashboard page.");
	}

	/**
	 * This method verifies AutoPay carousel.
	 *
	 * @param softAssert verify all the assertions
	 * @param user       this model contains all the user details.
	 */
	public void verifyAutoPayCarousel(SoftAssert softAssert, User user) {
		// Scroll to bottom
		scrollToBottom();
		pause(1000);
		// Check if AutoPay button is visible
		if (isAutoPayCarouselSectionVisible()) {
			ResultSet resultSet = DataBaseUtils
					.getResultSet(SQLQueries.getAutoPayEnrollStatus(user.getAccountNumber()));
			String autoPayStatus = null;
			try {
				autoPayStatus = resultSet.getString("PayTypeId");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			clickNextSwiperBtn();
			pause(2000);
			softAssert.assertEquals(getAutoPayTitleLabel(), dashboardTextProp.getPropValue("lblAutoPayTitle"),
					"AutoPay title not matched.");
			softAssert.assertEquals(getAutoPayTitleLabel(), dashboardTextProp.getPropValue("lblAutoPayHeader"),
					"AutoPay header not matched.");
			softAssert.assertEquals(getAutoPayTitleLabel(), dashboardTextProp.getPropValue("lblAutoPaySubTitle"),
					"AutoPay sub title not matched.");
			if (autoPayStatus.equals("1")) {
				softAssert.assertEquals(getAutoPayBtnLabel(), dashboardTextProp.getPropValue("lblEnrollAutoPay"));
			} else {
				softAssert.assertEquals(getAutoPayBtnLabel(), dashboardTextProp.getPropValue("lblUnEnrollAutoPay"));
			}
		}
		// Scroll to top
		scrollToTop();
		pause(1000);
		String account = selectDifferentAccountThanSelectedOneInDropDown(1);
		if (account != null) {
			Assert.assertTrue(isDashboardPage(dashboardTextProp.getPropValue("dashboardPageUrl"),
					dashboardTextProp.getPropValue("dashboardPageHeader")), "Not dashboard page.");
			// Again scroll to bottom
			scrollToBottom();
			pause(1000);
			if (isAutoPayCarouselSectionVisible()) {
				ResultSet resultSet = DataBaseUtils
						.getResultSet(SQLQueries.getAutoPayEnrollStatus(user.getAccountNumber()));
				String autoPayStatus = null;
				try {
					autoPayStatus = resultSet.getString("PayTypeId");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				clickNextSwiperBtn();
				pause(2000);
				softAssert.assertEquals(getAutoPayTitleLabel(), dashboardTextProp.getPropValue("lblAutoPayTitle"),
						"AutoPay title not matched.");
				softAssert.assertEquals(getAutoPayTitleLabel(), dashboardTextProp.getPropValue("lblAutoPayHeader"),
						"AutoPay header not matched.");
				softAssert.assertEquals(getAutoPayTitleLabel(), dashboardTextProp.getPropValue("lblAutoPaySubTitle"),
						"AutoPay sub title not matched.");
				if (autoPayStatus.equals("1")) {
					softAssert.assertEquals(getAutoPayBtnLabel(), dashboardTextProp.getPropValue("lblEnrollAutoPay"));
				} else {
					softAssert.assertEquals(getAutoPayBtnLabel(), dashboardTextProp.getPropValue("lblUnEnrollAutoPay"));
				}
			}
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
			softAssert.assertEquals(getAddRemoveDeviceLink(), dashboardTextProp.getPropValue("lblAddRemoveDeviceLink"),
					"Add and remove link label not matched.");
		}
	}

	/**
	 * This method selects the different account than the selected one if user have
	 * multiples accounts linked.
	 *
	 * @param index second account index
	 * @return account if having multiple account otherwise returns null
	 */
	public String selectDifferentAccountThanSelectedOneInDropDown(int index) {
		String selectAccount = null;
		if (isAccountDropDownListVisible()) {
			clickAddressDropDownButton();
			List<WebElement> accountDropDownOptionsEle = getAccountDropDownOptionsEle();
			if (accountDropDownOptionsEle.size() > 1) {
				click(accountDropDownOptionsEle.get(index));
				pause(5000);
				String addresses = getAccountAddressLabel();
				String[] account = addresses.split("\\s");
				selectAccount = account[account.length - 1];
			}
		}
		return selectAccount;
	}

	/**
	 * This method gives projected usage.
	 *
	 * @param accountNumber against which account need to calculate projected usage.
	 * @return projected usage
	 */
	public String getProjectedUsage(String accountNumber) {
		double projectedPowerUsage;
		double projectedWaterUsage;
		double projectedGasUsage;
		double projectedSolarUsage;
		String powerUsage = getProjectedPowerUsage(accountNumber);
		if (powerUsage.isEmpty()) {
			projectedPowerUsage = 0.0;
		} else {
			projectedPowerUsage = Double.parseDouble(powerUsage);
		}
		String waterUsage = getWaterExpectedUsage(accountNumber);
		if (waterUsage.isEmpty()) {
			projectedWaterUsage = 0.0;
		} else {
			projectedWaterUsage = Double.parseDouble(waterUsage);
		}
		String gasUsage = getGasExpectedUsage(accountNumber);
		if (gasUsage.isEmpty()) {
			projectedGasUsage = 0.0;
		} else {
			projectedGasUsage = Double.parseDouble(gasUsage);
		}
		String solarUsage = getSolarExpectedUsage(accountNumber);
		if (solarUsage.isEmpty()) {
			projectedSolarUsage = 0.0;
		} else {
			projectedSolarUsage = Double.parseDouble(solarUsage);
		}
		double projectedUsage = (projectedPowerUsage + projectedWaterUsage + projectedGasUsage) - projectedSolarUsage;
		String projectedUsageMonthly = String.valueOf(projectedUsage);
		return projectedUsageMonthly;
	}

	/**
	 * This method gives projected usage for electric meter.
	 *
	 * @param accountNumber against which account need to calculate projected usage.
	 * @return projected usage for electric meter
	 */
	public static String getProjectedPowerUsage(String accountNumber) {
		String query = SQLQueries.getProjectedUsageForAMI(accountNumber);
		ResultSet rs = DataBaseUtils.getResultSet(query);
		String powerUsage = "";
		try {
			while (rs.next()) {
				powerUsage = rs.getString("PowerExpectedUsage");
				MathUtil.localeNumberFormatting(powerUsage, 2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return powerUsage;
	}

	/**
	 * This method gives projected usage for water meter.
	 *
	 * @param accountNumber against which account need to calculate projected usage.
	 * @return projected usage for water meter
	 */
	public static String getWaterExpectedUsage(String accountNumber) {
		String query = SQLQueries.getProjectedUsageForAMI(accountNumber);
		ResultSet rs = DataBaseUtils.getResultSet(query);
		String waterUsage = "";
		try {
			while (rs.next()) {
				waterUsage = rs.getString("WaterExpectedUsage");
				MathUtil.localeNumberFormatting(waterUsage, 2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return waterUsage;
	}

	/**
	 * This method gives projected usage for gas meter.
	 *
	 * @param accountNumber against which account need to calculate projected usage.
	 * @return projected usage for gas meter
	 */
	public static String getGasExpectedUsage(String accountNumber) {
		String query = SQLQueries.getProjectedUsageForAMI(accountNumber);
		ResultSet rs = DataBaseUtils.getResultSet(query);
		String gasUsage = "";
		try {
			while (rs.next()) {
				gasUsage = rs.getString("GasExpectedUsage");
				MathUtil.localeNumberFormatting(gasUsage, 2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gasUsage;
	}

	/**
	 * This method gives projected usage for solar meter.
	 *
	 * @param accountNumber against which account need to calculate projected usage.
	 * @return projected usage for solar meter
	 */
	public static String getSolarExpectedUsage(String accountNumber) {
		String query = SQLQueries.getProjectedUsageForAMI(accountNumber);
		ResultSet rs = DataBaseUtils.getResultSet(query);
		String solarUsage = "";
		try {
			while (rs.next()) {
				solarUsage = rs.getString("SolarExpectedUsage");
				MathUtil.localeNumberFormatting(solarUsage, 2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return solarUsage;
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

	public String getDefaultAccountSelected(String userName) {
		String defaultAccount = null;
		try {
			ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getDefaultAccount(userName));
			while (rs.next()) {
				defaultAccount = rs.getString("UtilityAccountNumber");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultAccount;
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

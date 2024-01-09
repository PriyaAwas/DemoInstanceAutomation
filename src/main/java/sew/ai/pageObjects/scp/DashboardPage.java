package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends HomePage {
	private static final Logger log = LogManager.getLogger(DashboardPage.class);

	public DashboardPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".pageheading-box .godomornig")
	private WebElement lbl_salutation;

	public Boolean isSalutationLabelVisible() {
		log.info("Dashboard salutation label visibility status :" + lbl_salutation.isDisplayed());
		return lbl_salutation.isDisplayed();
	}

	public String getSalutation() {
		String salutation = null;
		salutation = getText(lbl_salutation);
		return salutation;
	}

	@FindBy(css = "#HeaderMenu_lblCustName1")
	private WebElement lbl_salutation_customer_name;

	public Boolean isCustomerNameSalutationVisible() {
		log.info("Customer name salutation label visibility status :" + lbl_salutation_customer_name.isDisplayed());
		return lbl_salutation_customer_name.isDisplayed();
	}

	public String getSalutationCustomerName() {
		String customerName = null;
		customerName = getText(lbl_salutation_customer_name);
		return customerName;
	}

	@FindBy(xpath = "//h1[@class='godomornig']/span[1]")
	private WebElement lbl_salutation_wish;

	public Boolean isSalutationWishLabelVisible() {
		log.info("Customer name salutation label visibility status :" + lbl_salutation_wish.isDisplayed());
		return lbl_salutation_wish.isDisplayed();
	}

	public String getWishSalutation() {
		String wish = null;
		wish = getText(lbl_salutation_wish);
		return wish;
	}

	@FindBy(css = "#lbllistbox")
	private WebElement lbl_account;

	public Boolean isAccountLabelVisible() {
		log.info("Account label visibility status :" + lbl_account.isDisplayed());
		return lbl_account.isDisplayed();
	}

	public String getAccountLabel() {
		String label = null;
		label = getText(lbl_account);
		return label;
	}

	@FindBy(css = "#PageTitle")
	private WebElement lbl_dashboard_header;

	public Boolean isDashboardHeaderLabel() {
		log.info("Dashboard header label visibility status :" + lbl_dashboard_header.isDisplayed());
		return lbl_dashboard_header.isDisplayed();
	}

	public String getDashboardHeaderLabel() {
		String label = getText(lbl_dashboard_header);
		log.info("Dashboard header label is " + label);
		return label;
	}

	@FindBy(css = "#lblCustName")
	private WebElement dd_customer_name_nav;

	public Boolean isCustomerNameNavDropDownVisible() {
		log.info("Customer Name drop-down in nav display status {}: " + dd_customer_name_nav.isDisplayed());
		return dd_customer_name_nav.isDisplayed();
	}

	public void clickCustomerNameNavDropDown() {
		click(dd_customer_name_nav);
		log.info("Customer Name drop-down in nav clicked {}.");
	}

	public String getCustomerNameNavDropDownLabel() {
		String label = getText(dd_customer_name_nav);
		log.info("Customer name drop-down nav label is " + label);
		return label;
	}

	@FindBy(css = "#NotificationAlertCount")
	private WebElement icon_notification_badge;

	public void waitForNotificationBadgeIcon() {
		waitForElementToBeVisible(icon_notification_badge);
		log.info("Notification alert badge is present on the page.");
	}

	public Boolean isNotificationAlertBadgeVisible() {
		log.info("Notification alert badge visibility status {}" + icon_notification_badge.isDisplayed());
		return icon_notification_badge.isDisplayed();
	}

	public void clickIconNotificationBadge() {
		click(icon_notification_badge);
		log.info("SignIn Button clicked successfully.");
	}

	@FindBy(css = "#dvCrDr")
	private WebElement lbl_remaining_balance;

	public Boolean isRemainingBalanceLabelVisible() {
		log.info("Notification alert badge visibility status {}" + lbl_remaining_balance.isDisplayed());
		return lbl_remaining_balance.isDisplayed();
	}

	public String getRemainingBalanceLabel() {
		String label = getText(lbl_remaining_balance);
		log.info("Remaining balance label is " + label);
		return label;
	}

	@FindBy(css = "#lblTotalPayableAmount")
	private WebElement lbl_current_balance;

	public Boolean isCurrentBalanceLabelVisible() {
		log.info("Current balance label visibility status {}" + lbl_current_balance.isDisplayed());
		return lbl_current_balance.isDisplayed();
	}

	public String getCurrentBalanceLabel() {
		String label = getText(lbl_current_balance);
		log.info("Current balance label is " + label);
		return label;
	}

	@FindBy(css = "#currentBalanceMessage")
	private WebElement lbl_due_date;

	public Boolean isDueDateLabelVisible() {
		log.info("Due date label visibility status {}" + lbl_due_date.isDisplayed());
		return lbl_due_date.isDisplayed();
	}

	public String getDueDateLabel() {
		String label = getText(lbl_due_date);
		log.info("Due date label is " + label);
		return label;
	}

	@FindBy(css = "#homeIconLink")
	private WebElement utility_icon;

	public Boolean isUtilityIconOnDashboardVisible() {
		log.info("Utility Icon on dashboard visibility status {} " + utility_icon.isDisplayed());
		return utility_icon.isDisplayed();
	}

	public String getUtilityIconOnDashboardLabel() {
		String label = getText(utility_icon);
		log.info("Utility icon label is " + label);
		return label;
	}

	@FindBy(css = "#CurrentinfoIcon")
	private WebElement icon_remaining_bal;

	public Boolean isRemainingBalanceIconVisible() {
		log.info("Remaining balance icon visibility status {}" + lbl_due_date.isDisplayed());
		return lbl_due_date.isDisplayed();
	}

	public void hoverRemainingBalanceIcon() {
		log.info("Hovering over remaining balance icon.");
		mouseHover(icon_remaining_bal);
	}

	@FindBy(css = "#CurrentinfoIcon div[role='tooltip']")
	private WebElement lbl_icon_remaining_balance;

	public String getRemainingBalanceIconLabel() {
		log.info("Remaining balance icon visibility status {}");
		return getText(lbl_icon_remaining_balance);
	}

	@FindBy(css = "#download")
	private WebElement icon_download_bill_pdf;

	public Boolean isDownloadBillPdfIconVisible() {
		log.info("Download bill PDF visibility status {}" + icon_download_bill_pdf.isDisplayed());
		return icon_download_bill_pdf.isDisplayed();
	}

	@FindBy(css = ".tooltip-inner")
	private WebElement lbl_version;

	public Boolean isVersionLabelVisible() {
		log.info("Version label visibility status {}" + lbl_version.isDisplayed());
		return lbl_version.isDisplayed();
	}

	public String getVersionLabel() {
		String label = getText(lbl_version);
		log.info("Version label is " + label);
		return label;
	}

	@FindBy(css = ".copyright")
	private WebElement lbl_copyright;

	public Boolean isCopyrightTextVisible() {
		log.info("Copyright text visibility status {}" + lbl_copyright.isDisplayed());
		return lbl_copyright.isDisplayed();
	}

	public String getCopyrightLabel() {
		String label = getText(lbl_copyright);
		log.info("Copyright label is " + label);
		return label;
	}

	@FindBy(css = ".pw_logo_foot")
	private WebElement logo_powered_by_footer;

	public Boolean isPoweredByLogoVisible() {
		return logo_powered_by_footer.isDisplayed();
	}

	public void clickPoweredByLogo() {
		click(logo_powered_by_footer);
	}

	@FindBy(css = "div.log_out_area  > h1")
	private WebElement lbl_heading_logout;

	public Boolean isLogoutHeadingVisible() {
		log.info("Copyright text visibility status {}" + lbl_heading_logout.isDisplayed());
		return lbl_heading_logout.isDisplayed();
	}

	public String getLogoutHeadingLabel() {
		String label = getText(lbl_heading_logout);
		log.info("Copyright label is " + label);
		return label;
	}

	@FindBy(css = "li > span")
	private WebElement lbl_good_morning;

	public Boolean isGoodMorningLabelVisible() {
		log.info("Copyright text visibility status {}" + lbl_good_morning.isDisplayed());
		return lbl_good_morning.isDisplayed();
	}

	public String getGoodMorningLabel() {
		String label = getText(lbl_good_morning);
		log.info("Copyright label is " + label);
		return label;
	}

	@FindBy(css = ".wlcm_to_scm h3")
	private WebElement lbl_welcome_popup_heading;

	public Boolean isWelcomePopupHeadingVisible() {
		log.info("Welcome popup heading visibility status {}" + lbl_welcome_popup_heading.isDisplayed());
		return lbl_welcome_popup_heading.isDisplayed();
	}

	public String getWelcomePopupHeadingLabel() {
		String label = getText(lbl_welcome_popup_heading);
		log.info("Welcome popup heading label is " + label);
		return label;
	}

	@FindBy(css = ".wlcm_text h5")
	private WebElement lbl_welcome;

	public Boolean isWelcomeLabelVisible() {
		log.info("Welcome label visibility status {}" + lbl_copyright.isDisplayed());
		return lbl_copyright.isDisplayed();
	}

	public String getWelcomeLabel() {
		String label = getText(lbl_copyright);
		log.info("Welcome label is " + label);
		return label;
	}

	@FindBy(css = "div.nodata_dash_box >span")
	private WebElement lbl_no_bill_data;

	public Boolean isNoBillDataVisible() {
		log.info("No bill data visibility status {}" + lbl_no_bill_data.isDisplayed());
		return lbl_no_bill_data.isDisplayed();
	}

	public String getNoBillDataLabel() {
		String label = getText(lbl_no_bill_data);
		log.info("No bill data label is " + label);
		return label;
	}

	@FindBy(css = "//div[@class='noti_sec_popup']/div/div/h3")
	private WebElement lbl_set_your_alert;

	public Boolean isSetYourAlertVisible() {
		log.info("Copyright text visibility status {}" + lbl_set_your_alert.isDisplayed());
		return lbl_set_your_alert.isDisplayed();
	}

	public String getSetYourAlertLabel() {
		String label = getText(lbl_set_your_alert);
		log.info("Copyright label is " + label);
		return label;
	}

	@FindBy(css = "//button[@id='dLabel']")
	private WebElement lbl_default_selected_account;

	public Boolean isDefaultSelectedAccountVisible() {
		log.info("Copyright text visibility status {}" + lbl_default_selected_account.isDisplayed());
		return lbl_default_selected_account.isDisplayed();
	}

	public String getDefaultAccountLabel() {
		String label = getText(lbl_default_selected_account);
		log.info("Copyright label is " + label);
		return label;
	}

	@FindBy(css = ".footer [globalize='ML_CorporateHQ']")
	private WebElement lbl_corporate_footer;

	public Boolean isCorporateFooterVisible() {
		log.info("Corporate footer visibility status {}" + lbl_corporate_footer.isDisplayed());
		return lbl_corporate_footer.isDisplayed();
	}

	public String getCorporateFooterLabel() {
		String label = getText(lbl_corporate_footer);
		log.info("Corporate footer label is " + label);
		return label;
	}

	/*
	 * MONTH BEFORE LAST LOCATORS
	 */
	@FindBy(css = "#lastMonthval")
	private WebElement val_month_before_last;

	public Boolean isMonthBeforeLastValueVisible() {
		log.info("Last year text visibility status {}" + val_month_before_last.isDisplayed());
		return val_month_before_last.isDisplayed();
	}

	public String getMonthBeforeLastValue() {
		String label = getText(val_month_before_last);
		log.info("Last year bill label is " + label);
		return label;
	}

	@FindBy(css = "#lastMonthvalMonthYear")
	private WebElement txt_month_before_last;

	public Boolean isMonthBeforeLastTextVisible() {
		log.info("Last month value visibility status {}" + txt_month_before_last.isDisplayed());
		return txt_month_before_last.isDisplayed();
	}

	public String getMonthBeforeLastText() {
		String label = getText(txt_month_before_last);
		log.info("Last year month label is " + label);
		return label;
	}

	@FindBy(css = "#Last_Bill")
	private WebElement lbl_month_before_last;

	public Boolean isMonthBeforeLastLabelVisible() {
		log.info("Last year label visibility status {}" + lbl_month_before_last.isDisplayed());
		return lbl_month_before_last.isDisplayed();
	}

	public String getMonthBeforeLastLabel() {
		String label = getText(lbl_month_before_last);
		log.info("Last year label is " + label);
		return label;
	}

	/*
	 * SO FAR THIS MONTH
	 */
	@FindBy(css = "#lblCurrentUsage")
	private WebElement val_so_far_this_month;

	public Boolean isSoFarThisMonthValueVisible() {
		log.info("So far this month value visibility status {}" + val_so_far_this_month.isDisplayed());
		return val_so_far_this_month.isDisplayed();
	}

	public String getSoFarThisMonthValue() {
		String label = getText(val_so_far_this_month);
		log.info("So far this month label is " + label);
		return label;
	}

	@FindBy(css = "#lblDaysAsofToday")
	private WebElement lbl_so_far_this_month;

	public Boolean isSoFarThisMonthLabelVisible() {
		log.info("So far this month label visibility status {}" + lbl_so_far_this_month.isDisplayed());
		return lbl_so_far_this_month.isDisplayed();
	}

	public String getSoFarThisMonthLabel() {
		String label = getText(lbl_so_far_this_month);
		log.info("So far this month label is " + label);
		return label;
	}

	/*
	 * PROJECTED BILL LOCATORS
	 */
	@FindBy(css = "#lblEstimatedUsage")
	private WebElement val_projected_bill;

	public Boolean isProjectedBillValueVisible() {
		log.info("Projected bill text visibility status {}" + val_projected_bill.isDisplayed());
		return val_projected_bill.isDisplayed();
	}

	public String getProjectedBillValue() {
		String label = getText(val_projected_bill);
		log.info("Last year bill label is " + label);
		return label;
	}

	@FindBy(css = "#lblEstimatedUsageMonthYear")
	private WebElement txt_projected_bill;

	public Boolean isProjectedBillTextVisible() {
		log.info("Projected bill text visibility status {}" + txt_projected_bill.isDisplayed());
		return txt_projected_bill.isDisplayed();
	}

	public String getProjectedBillText() {
		String label = getText(txt_projected_bill);
		log.info("Last year bill label is " + label);
		return label;
	}

	@FindBy(css = "#Projected_Bill")
	private WebElement lbl_projected_bill;

	public Boolean isProjectedBillLabelVisible() {
		log.info("Projected bill visibility status {}" + lbl_projected_bill.isDisplayed());
		return lbl_projected_bill.isDisplayed();
	}

	public String getProjectedBillLabel() {
		String label = getText(lbl_projected_bill);
		log.info("Copyright label is " + label);
		return label;
	}

	/*
	 * LAST BILL AMI LOCATORS
	 */
	@FindBy(css = "#lastMonthval")
	private WebElement val_last_bill_ami;

	public Boolean isLastBillAMIValueVisible() {
		log.info("Last year text visibility status {}" + val_last_bill_ami.isDisplayed());
		return val_last_bill_ami.isDisplayed();
	}

	public String getLastBillAMIValue() {
		String label = getText(val_last_bill_ami);
		log.info("Last year bill label is " + label);
		return label;
	}

	@FindBy(css = "#lastMonthvalMonthYear")
	private WebElement txt_last_bill_ami;

	public Boolean isLastBillAMITextVisible() {
		log.info("Last month value visibility status {}" + txt_last_bill_ami.isDisplayed());
		return txt_last_bill_ami.isDisplayed();
	}

	public String getLastBillAMIText() {
		String label = getText(txt_last_bill_ami);
		log.info("Last year month label is " + label);
		return label;
	}

	@FindBy(css = "#Last_Bill")
	private WebElement lbl_last_bill_ami;

	public Boolean isLastBillAMILabelVisible() {
		log.info("Last year label visibility status {}" + lbl_last_bill_ami.isDisplayed());
		return lbl_last_bill_ami.isDisplayed();
	}

	public String getLastBillAMILabel() {
		String label = getText(lbl_last_bill_ami);
		log.info("Last year label is " + label);
		return label;
	}

	/*
	 * LAST BILL NON-AMI LOCATORS
	 */
	@FindBy(css = "#lblEstimatedUsage")
	private WebElement val_last_bill_noami;

	public Boolean isLastBillNoAMIValueVisible() {
		log.info("Projected bill text visibility status {}" + val_last_bill_noami.isDisplayed());
		return val_last_bill_noami.isDisplayed();
	}

	public String getLastBillNonAMIValue() {
		String label = getText(val_last_bill_noami);
		log.info("Last year bill label is " + label);
		return label;
	}

	@FindBy(css = "#lblEstimatedUsageMonthYear")
	private WebElement txt_last_bill_noami;

	public Boolean isLastBillNonAMITextVisible() {
		log.info("Projected bill text visibility status {}" + txt_last_bill_noami.isDisplayed());
		return txt_last_bill_noami.isDisplayed();
	}

	public String getLastBillNonAMIText() {
		String label = getText(txt_last_bill_noami);
		log.info("Last year bill label is " + label);
		return label;
	}

	@FindBy(css = "#Projected_Bill")
	private WebElement lbl_last_bill_noami;

	public Boolean isLastBillNonAMILabelVisible() {
		log.info("Projected bill visibility status {}" + lbl_last_bill_noami.isDisplayed());
		return lbl_last_bill_noami.isDisplayed();
	}

	public String getLastBillNonAMILabel() {
		String label = getText(lbl_last_bill_noami);
		log.info("Copyright label is " + label);
		return label;
	}

	/*
	 * LAST YEAR LOCATORS
	 */
	@FindBy(css = "#lastYearMonthval")
	private WebElement val_last_year;

	public Boolean isLastYearValueVisible() {
		log.info("Last year month text visibility status {}" + val_last_year.isDisplayed());
		return val_last_year.isDisplayed();
	}

	public String getLastYearValue() {
		String label = getText(val_last_year);
		log.info("Last year month label is " + label);
		return label;
	}

	@FindBy(css = "#lastYearvalMonthYear")
	private WebElement txt_last_year;

	public Boolean isLastYearTextVisible() {
		log.info("Last year value text visibility status {}" + txt_last_year.isDisplayed());
		return txt_last_year.isDisplayed();
	}

	public String getLastYearText() {
		String label = getText(txt_last_year);
		log.info("Last year value label is " + label);
		return label;
	}

	@FindBy(css = "#lbl_LastYear")
	private WebElement lbl_last_year;

	public Boolean isLastYearLabelVisible() {
		log.info("Last year value text visibility status {}" + lbl_last_year.isDisplayed());
		return lbl_last_year.isDisplayed();
	}

	public String getLastYearLabel() {
		String label = getText(lbl_last_year);
		log.info("Last year value label is " + label);
		return label;
	}

	@FindBy(css = "#projectUpcomContent")
	private WebElement lbl_projected_bill_comparison;

	public String getProjectedBillComparisonLabel() {
		String label = getText(lbl_projected_bill_comparison);
		log.info("Compare your projected with last year label is " + label);
		return label;
	}

	@FindBy(css = "#BillForecast>span")
	private WebElement lbl_compare_your_consumption;

	public Boolean isCompareYourConsumptionLabelVisible() {
		log.info("Compare your consumption label visibility status {}" + lbl_compare_your_consumption.isDisplayed());
		return lbl_compare_your_consumption.isDisplayed();
	}

	public String getCompareYourConsumptionLabel() {
		String label = getText(lbl_compare_your_consumption);
		log.info("Compare your consumption label is " + label);
		return label;
	}

	@FindBy(css = "#billForecastlastLabel")
	private WebElement lbl_bill_forecast_disclaimer;

	public Boolean isDisclaimerVisible() {
		Boolean status = lbl_bill_forecast_disclaimer.isDisplayed();
		log.info("Last year text visibility status {}" + status);
		return lbl_bill_forecast_disclaimer.isDisplayed();
	}

	public String getBillForecastDisclaimer() {
		String label = getTextWithoutCheckingVisibility(lbl_bill_forecast_disclaimer);
		log.info("Last year value label is " + label);
		return label;
	}

	@FindBy(css = "h5[globalize = 'ML_DASHBOARD_Lbl_CurrentBalance']")
	private WebElement lbl_how_you_are_doing_so_far;

	public Boolean isHowYouAreDoingSoFarVisible() {
		log.info("How you are doing so far visibility status {}" + lbl_how_you_are_doing_so_far.isDisplayed());
		return lbl_how_you_are_doing_so_far.isDisplayed();
	}

	public String getHowYouAreDoingSoFar() {
		String label = getText(lbl_how_you_are_doing_so_far);
		log.info("How you are doing so far label is " + label);
		return label;
	}

	public void scrollToHowYouAreDoingSoFar() {
		scrollToElement(lbl_how_you_are_doing_so_far);
		log.info("Scroll to how you are doing so far label.");
	}

	@FindBy(css = ".blueEnrollBox #titleRR1")
	private WebElement lbl_want_predictable_bill;

	public Boolean isWantPredictableBillVisible() {
		log.info("Want predictable bill visibility status {}" + lbl_want_predictable_bill.isDisplayed());
		return lbl_want_predictable_bill.isDisplayed();
	}

	public String getWantPredictableBill() {
		String label = getText(lbl_want_predictable_bill);
		log.info("Want predictable bill label is " + label);
		return label;
	}

	@FindBy(css = "div.blueEnrollBox > h5 div#msgRR1")
	private WebElement lbl_enroll_in_autopay;

	public Boolean isEnrollInAutoPayLblVisible() {
		log.info("Enroll in autopay visibility status {}" + lbl_enroll_in_autopay.isDisplayed());
		return lbl_enroll_in_autopay.isDisplayed();
	}

	public String getEnrollInAutopayLbl() {
		String label = getText(lbl_enroll_in_autopay);
		log.info("Enroll in autopay label is " + label);
		return label;
	}

	@FindBy(css = "div.estimatedBox > span")
	private WebElement lbl_estimated_monthly_bill;

	public Boolean isEstimatedMonthlyBillLblVisible() {
		log.info("Estimated monthly bill visibility status {}" + lbl_estimated_monthly_bill.isDisplayed());
		return lbl_estimated_monthly_bill.isDisplayed();
	}

	public String getEstimatedMonthlyBillLbl() {
		String label = getText(lbl_estimated_monthly_bill);
		log.info("Estimated monthly bill label is " + label);
		return label;
	}

	@FindBy(css = "div#msgRR1 span")
	private WebElement lbl_level_pay_description;

	public Boolean isLevelPayDescriptionLblVisible() {
		log.info("Level pay description label visibility status {}" + lbl_level_pay_description.isDisplayed());
		return lbl_level_pay_description.isDisplayed();
	}

	public String getLevelPayDescriptionLbl() {
		String label = getText(lbl_level_pay_description);
		log.info("Level pay description label is " + label);
		return label;
	}

	@FindBy(css = "#titleRR2")
	private WebElement lbl_saving_alert;

	public Boolean isSavingAlertLblVisible() {
		log.info("Saving alert label visibility status {}" + lbl_saving_alert.isDisplayed());
		return lbl_saving_alert.isDisplayed();
	}

	public String getSavingAlertLbl() {
		String label = getText(lbl_saving_alert);
		log.info("Saving alert label is " + label);
		return label;
	}

	@FindBy(css = "div#msgRR2>span")
	private WebElement lbl_savings_description;

	public Boolean isSavingsDescriptionLblVisible() {
		log.info("Savings description label visibility status {}" + lbl_savings_description.isDisplayed());
		return lbl_savings_description.isDisplayed();
	}

	public String getSavingsDescriptionLbl() {
		String label = getText(lbl_savings_description);
		log.info("Savings description label is " + label);
		return label;
	}

	@FindBy(css = "//h3[text()='Auto Pay']")
	private WebElement lbl_autopay;

	public Boolean isAutopayLblVisible() {
		log.info("Autopay label visibility status {}" + lbl_autopay.isDisplayed());
		return lbl_autopay.isDisplayed();
	}

	public String getAutopayLbl() {
		String label = getText(lbl_autopay);
		log.info("Autopay label is " + label);
		return label;
	}

	@FindBy(css = "#msgCC1")
	private WebElement lbl_autopay_description;

	public Boolean isAutopayDescriptionLblVisible() {
		log.info("Last year text visibility status {}" + lbl_autopay_description.isDisplayed());
		return lbl_autopay_description.isDisplayed();
	}

	public String getAutopayDescriptionLbl() {
		String label = getText(lbl_autopay_description);
		log.info("Last year value label is " + label);
		return label;
	}

	@FindBy(css = "div.left_ev_dash > span")
	private WebElement lbl_added_car_info;

	public Boolean isAddedCarInfoLblVisible() {
		log.info("Added car info label visibility status {}" + lbl_added_car_info.isDisplayed());
		return lbl_added_car_info.isDisplayed();
	}

	public String getAddedCarInfoLbl() {
		String label = getText(lbl_added_car_info);
		log.info("Added car info label is " + label);
		return label;
	}

	@FindBy(css = "#lblCarname")
	private WebElement lbl_car_name;

	public Boolean isCarNameLblVisible() {
		log.info("Car name label visibility status {}" + lbl_car_name.isDisplayed());
		return lbl_car_name.isDisplayed();
	}

	public String getCarNameLbl() {
		String label = getText(lbl_car_name);
		log.info("Car name label is " + label);
		return label;
	}

	@FindBy(css = "div.avg_cost_charge > div")
	private WebElement lbl_ev_usage;

	public Boolean isEVUsageLblVisible() {
		log.info("EV Usage label visibility status {}" + lbl_ev_usage.isDisplayed());
		return lbl_ev_usage.isDisplayed();
	}

	public String getEVUsageLbl() {
		String label = getText(lbl_ev_usage);
		log.info("EV Usage label is " + label);
		return label;
	}

	@FindBy(css = "div.avg_chrge_time > div")
	private WebElement lbl_avg_daily_charging_duration;

	public Boolean isAverageDailyChargingStatusVisible() {
		log.info("Average daily charging status visibility status {}" + lbl_avg_daily_charging_duration.isDisplayed());
		return lbl_avg_daily_charging_duration.isDisplayed();
	}

	public String getAverageDailyChargingStatus() {
		String label = getText(lbl_avg_daily_charging_duration);
		log.info("Average daily charging status label is " + label);
		return label;
	}

	@FindBy(css = "#lblUsage")
	private WebElement lbl_ev_usage_value;

	public Boolean isEVUsageValueVisible() {
		Boolean isVisible = isElementVisible(lbl_ev_usage_value);
		log.info("EV Usage value visibility status {}" + isVisible);
		return isVisible;
	}

	public String getEVUsageValue() {
		String label = getText(lbl_ev_usage_value);
		log.info("EV Usage value is " + label);
		return label;
	}

	@FindBy(css = "#lblChrRem")
	private WebElement lbl_avg_charging_duration;

	public Boolean isAverageChargingStationVisible() {
		Boolean isVisible = isElementVisible(lbl_avg_charging_duration);
		log.info("Average charging station visibility status {}" + isVisible);
		return isVisible;
	}

	public String getAverageChargingStation() {
		String label = getText(lbl_avg_charging_duration);
		log.info("Average charging station is " + label);
		return label;
	}

	@FindBy(css = "#titleCC3")
	private WebElement lbl_smart_device_info;

	public Boolean isSmartDevicesInfoVisible() {
		Boolean isVisible = isElementVisible(lbl_smart_device_info);
		log.info("Smart devices info visibility status {}" + isVisible);
		return isVisible;
	}

	public String getSmartDevicesInfo() {
		String label = getText(lbl_smart_device_info);
		log.info("Smart devices info is " + label);
		return label;
	}

	@FindBy(css = "#CC3Redirect .galleryCont h3")
	private WebElement lbl_added_smart_device;

	public Boolean isAddedSmartDevicesVisible() {
		Boolean isVisible = isElementVisible(lbl_added_smart_device);
		log.info("Added smart devices visibility status {}" + isVisible);
		return isVisible;
	}

	public String getAddedSmartDevices() {
		String label = getText(lbl_added_smart_device);
		log.info("Added smart devices value is " + label);
		return label;
	}

	@FindBy(css = ".NestLinks .PowerChecked")
	private WebElement on_off_icon;

	public Boolean isOnOffIconVisible() {
		Boolean isVisible = isElementVisible(on_off_icon);
		log.info("Added smart devices visibility status {}" + isVisible);
		return isVisible;
	}

	@FindBy(css = ".NestLinks li:first-child .CheckedLabel")
	private WebElement lbl_on_off_icon;

	public Boolean isOnOffIconLabelVisible() {
		Boolean isVisible = isElementVisible(lbl_on_off_icon);
		log.info("Added smart devices visibility status {}" + isVisible);
		return isVisible;
	}

	public String getOnOffIconLabel() {
		String label = getText(lbl_on_off_icon);
		log.info("Added smart devices value is " + label);
		return label;
	}

	@FindBy(css = "#CC3Redirect .NestLinks li")
	private WebElement smart_home_links;

	public WebElement getSmartHomeLinksElement() {
		return smart_home_links;
	}

	private By smartHomeLinksIcon = By.xpath("//span[contains(@class, 'CustomCheck')]");

	public By getSmartHomeLinksIcon() {
		return smartHomeLinksIcon;
	}

	private By smartHomeLinksLabel = By.xpath("//span[@class='CheckedLabel']");

	public By getSmartHomeLinksLabel() {
		return smartHomeLinksLabel;
	}

	@FindBy(css = "#CC3 .lnk_effi_bottom a")
	private WebElement addRemoveDeviceLink;

	public String getAddRemoveDeviceLink() {
		log.info("Getting the label add and remove device link.");
		return getText(addRemoveDeviceLink);
	}

	@FindBy(css = ".swiper-button-next")
	private WebElement btn_swiper_next;

	public Boolean isNextWindowBtnVisible() {
		Boolean isVisible = isElementVisible(btn_swiper_next);
		log.info("Added smart devices visibility status {}" + isVisible);
		return isVisible;
	}

	public String getNextWindowLbl() {
		String label = getText(btn_swiper_next);
		log.info("Added smart devices value is " + label);
		return label;
	}

	public void clickNextBtn() {
		click(btn_swiper_next);
		log.info("Next window button clicked successfully.");
	}

	@FindBy(css = "button#btnclosepopup_abthome.close")
	private WebElement btn_close_about_my_home;

	public Boolean isCloseAboutMyHomeBtnVisible() {
		Boolean isVisible = isElementVisible(btn_close_about_my_home);
		log.info("Close about my home button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getCloseAboutMyHomeBtnLbl() {
		String label = getText(btn_close_about_my_home);
		log.info("Close about my home button label is " + label);
		return label;
	}

	public void clickCloseAboutMyHomeBtn() {
		clickWithJSExecutor(btn_close_about_my_home);
		log.info("Close about my home button clicked successfully.");
	}

	public void waitForAboutMyHomeCloseBtn() {
		waitForElementToBeVisible(btn_close_about_my_home);
		log.info("Wait for close about my home button visibility.");
	}

	@FindBys(@FindBy(css = "input[globalize = ML_BillPayment_Button_Cancel]"))
	private List<WebElement> cancel_about_my_home_btns;

	public Boolean isCancelAboutMyHomeBtnDisplayed() {
		return cancel_about_my_home_btns.size() > 0;
	}

	@FindBy(css = "input[globalize = ML_BillPayment_Button_Cancel]")
	private WebElement btn_cancel_about_my_home;

	public Boolean isCancelAboutMyHomeBtnVisible() {
		Boolean isVisible = isElementVisible(btn_cancel_about_my_home);
		log.info("Cancel about my home button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getCancelAboutMyHomeBtnLbl() {
		String label = getText(btn_cancel_about_my_home);
		log.info("Cancel about my home button label is " + label);
		return label;
	}

	public void clickCancelAboutMyHomeBtn() {
		click(btn_cancel_about_my_home);
		log.info("About my home page cancel button clicked {}.");
	}

	@FindBy(css = "#BtnPayBill")
	private WebElement btn_recharge;

	public Boolean isRechargeBtnVisible() {
		Boolean isVisible = isElementVisible(btn_recharge);
		log.info("Pay bill button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getRechargeBtnLbl() {
		String label = getText(btn_recharge);
		log.info("Pay bill button label is " + label);
		return label;
	}

	public void clickRechargeBtn() {
		click(btn_recharge);
		log.info("Pay bill button clicked {}.");
	}

	@FindBy(css = ".userbtn.logout_header")
	private WebElement btn_sign_out;

	public Boolean isSignOutBtnVisible() {
		Boolean isVisible = isElementVisible(btn_sign_out);
		log.info("Pay bill button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getSignOutBtnLbl() {
		String label = getText(btn_sign_out);
		log.info("Pay bill button label is " + label);
		return label;
	}

	public void clickSignOutBtn() {
		click(btn_sign_out);
		log.info("Pay bill button clicked {}.");
	}

	@FindBy(css = ".enrollBlue")
	private WebElement btn_level_pay_click_here;

	public Boolean isLevelPayClickHereBtnVisible() {
		Boolean isVisible = isElementVisible(btn_level_pay_click_here);
		log.info("Level pay click here button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getLevelPayClickHereBtnLbl() {
		String label = getText(btn_level_pay_click_here);
		log.info("Level pay click here button label is " + label);
		return label;
	}

	public void clickLevelPayClickHereBtn() {
		click(btn_level_pay_click_here);
		log.info("Level pay click here button clicked {}.");
	}

	@FindBy(css = "#btnEnroll")
	private WebElement btn_enroll_level_pay;

	public Boolean isLevelPayEnrollBtnVisible() {
		Boolean isVisible = isElementVisible(btn_enroll_level_pay);
		log.info("Level pay enroll here button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getLevelPayEnrollBtnLbl() {
		String label = getText(btn_enroll_level_pay);
		log.info("Level pay enroll button label is " + label);
		return label;
	}

	public void clickLevelPayEnrollBtn() {
		click(btn_enroll_level_pay);
		log.info("Level pay enroll here button clicked {}.");
	}

	@FindBy(css = "#CC1")
	private WebElement autopayCarouselSection;

	public Boolean isAutoPayCarouselSectionVisible() {
		Boolean status = isElementVisible(autopayCarouselSection);
		log.info("AutoPay section visibility status {} " + status);
		return status;
	}

	@FindBy(css = "#titleCC1")
	private WebElement labelAutoPayTitle;

	public String getAutoPayTitleLabel() {
		String title = getText(labelAutoPayTitle);
		log.info("AutoPay page title is {} " + title);
		return title;
	}

	@FindBy(css = "#msgCC1 p")
	private WebElement labelAutoPaySubTitle;

	public String getAutoPaySubTitleLabel() {
		String title = getText(labelAutoPaySubTitle);
		log.info("AutoPay page title is {} " + title);
		return title;
	}

	@FindBy(css = "#CC1Redirect .galleryCont h3")
	private WebElement labelAutoPayHeader;

	public String getAutoPayHeaderLabel() {
		String label = getText(labelAutoPayHeader);
		log.info("AutoPay page title is {} " + label);
		return label;
	}

	@FindBy(css = "button.btn_new_white.UnEnrollautopay")
	private WebElement btn_enroll_unenroll_autopay;

	public Boolean isAutoPayBtnVisible() {
		Boolean isVisible = isElementVisible(btn_enroll_unenroll_autopay);
		log.info("Level pay unenroll button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getAutoPayBtnLabel() {
		String label = getText(btn_enroll_unenroll_autopay);
		log.info("Level pay click here button label is " + label);
		return label;
	}

	public void clickAutoPayBtn() {
		click(btn_enroll_unenroll_autopay);
		log.info("Level pay click here button clicked {}.");
	}

	@FindBy(css = ".swiper-button-next.swiper-button-white")
	private WebElement btn_next_swiper;

	public Boolean isNextSwiperBtnVisible() {
		Boolean isVisible = isElementVisible(btn_next_swiper);
		log.info("Next swiper button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getNextSwiperBtnLbl() {
		String label = getText(btn_next_swiper);
		log.info("Next swiper button label is " + label);
		return label;
	}

	public void clickNextSwiperBtn() {
		click(btn_next_swiper);
		log.info("Next swiper button clicked {}.");
	}

	@FindBy(css = ".swiper-button-next.swiper-button-white")
	private WebElement btn_prev_swiper;

	public Boolean isPrevSwiperBtnVisible() {
		Boolean isVisible = isElementVisible(btn_prev_swiper);
		log.info("Previous swiper button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getPrevSwiperBtnLbl() {
		String label = getText(btn_prev_swiper);
		log.info("Previous swiper button label is " + label);
		return label;
	}

	public void clickPrevSwiperBtn() {
		click(btn_prev_swiper);
		log.info("Previous swiper button clicked {}.");
	}

	@FindBy(css = "#CC3")
	private WebElement smartDeviceSection;

	public Boolean isSmartDeviceSectionVisible() {
		return isElementVisible(smartDeviceSection);
	}

	@FindBy(css = "#titleCC3")
	private WebElement smartDevicesConnectedLabel;

	public String getSmartDevicesConnectedLabel() {
		return getText(smartDevicesConnectedLabel);
	}

	@FindBy(css = "div.swiper - button - next")
	private WebElement btn_next_carousel;

	public Boolean isNextCarouselBtnVisible() {
		Boolean isVisible = isElementVisible(btn_next_carousel);
		log.info("Next carousel button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getNextCarouselBtnLbl() {
		String label = getText(btn_next_carousel);
		log.info("Next carousel button label is " + label);
		return label;
	}

	public String getNextCarouselBtnAttribute(String attribute) {
		String attr = getAttribute(btn_next_carousel, attribute);
		log.info("Next carousel attribute is {}: " + attr);
		return attr;
	}

	public void clickNextCarouselBtn() {
		click(btn_next_carousel);
		log.info("Next carousel button clicked {}.");
	}

	@FindBy(css = "div.swiper - button - prev")
	private WebElement btn_previous_carousel;

	public Boolean isPreviousCarouselBtnVisible() {
		Boolean isVisible = isElementVisible(btn_previous_carousel);
		log.info("Previous carousel button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getPreviousCarouselBtnLbl() {
		String label = getText(btn_previous_carousel);
		log.info("Previous carousel button label is " + label);
		return label;
	}

	public void clickPreviousCarouselBtn() {
		click(btn_previous_carousel);
		log.info("Previous carousel button clicked {}.");
	}

	@FindBy(css = ".PowerChecked")
	private WebElement btn_on_off_smart_device;

	public Boolean isOnOffSmartDevicesBtnVisible() {
		Boolean isVisible = isElementVisible(btn_on_off_smart_device);
		log.info("On off smart devices visibility status {}" + isVisible);
		return isVisible;
	}

	public String getOnOffSmartDevicesBtnLbl() {
		String label = getText(btn_on_off_smart_device);
		log.info("On off smart devices button label is " + label);
		return label;
	}

	public void clickOnOffSmartDevicesBtn() {
		click(btn_on_off_smart_device);
		log.info("On off smart devices button clicked {}.");
	}

	@FindBy(css = "span[area - label = 'Click to eco on off']")
	private WebElement btn_eco;

	public Boolean isEcoBtnVisible() {
		Boolean isVisible = isElementVisible(btn_eco);
		log.info("Level pay click here button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getEcoBtnLbl() {
		String label = getText(btn_eco);
		log.info("Level pay click here button label is " + label);
		return label;
	}

	public void clickEcoBtn() {
		click(btn_eco);
		log.info("Level pay click here button clicked {}.");
	}

	@FindBy(css = "span[area - label = 'Click to Cool on off']")
	private WebElement btn_cool;

	public Boolean isCoolBtnVisible() {
		Boolean isVisible = isElementVisible(btn_cool);
		log.info("Cool button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getCoolBtnLbl() {
		String label = getText(btn_cool);
		log.info("Cool button label is " + label);
		return label;
	}

	public void clickCoolBtn() {
		click(btn_cool);
		log.info("Cool button clicked {}.");
	}

	@FindBy(css = "span[aria - label = 'Click to Heat on off']")
	private WebElement btn_heat;

	public Boolean isHeatBtnVisible() {
		Boolean isVisible = isElementVisible(btn_heat);
		log.info("Level pay click here button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getHeatBtnLbl() {
		String label = getText(btn_heat);
		log.info("Level pay click here button label is " + label);
		return label;
	}

	public void clickHeatBtn() {
		click(btn_heat);
		log.info("Level pay click here button clicked {}.");
	}

	@FindBy(css = "span[aria - label = 'Click to Fan on off']")
	private WebElement btn_fan;

	public Boolean isFanBtnVisible() {
		Boolean isVisible = isElementVisible(btn_fan);
		log.info("Fan button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getFanBtnLbl() {
		String label = getText(btn_fan);
		log.info("Fan button label is " + label);
		return label;
	}

	public void clickFanBtn() {
		click(btn_fan);
		log.info("Fan button clicked {}: ");
	}

	@FindBy(css = "div.NestLinks > ul > li > label > span")
	private WebElement btn_smart_home;

	public Boolean isSmartHomeBtnVisible() {
		Boolean isVisible = isElementVisible(btn_smart_home);
		log.info("Smart button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getSmartHomeBtnLbl() {
		String label = getText(btn_smart_home);
		log.info("Smart button label is " + label);
		return label;
	}

	public void clickSmartHomeBtn() {
		click(btn_smart_home);
		log.info("Smart button clicked {}.");
	}

	@FindBy(css = "#BtnPayBill")
	private WebElement lnk_make_payment;

	public Boolean isMakePaymentLnkVisible() {
		Boolean isVisible = isElementVisible(lnk_make_payment);
		log.info("Make payment link visibility status {}" + isVisible);
		return isVisible;
	}

	public String getMakePaymentLinkLbl() {
		String label = getText(lnk_make_payment);
		log.info("Make payment link label is " + label);
		return label;
	}

	public void clickMakePaymentLink() {
		click(lnk_make_payment);
		log.info("Make payment link clicked {}.");
	}

	@FindBy(css = "a[globalize = 'ML_DASHBOARD_Anchor_ViewBill']")
	private WebElement lnk_view_bill;

	public Boolean isViewBillLinkVisible() {
		Boolean isVisible = isElementVisible(lnk_view_bill);
		log.info("View bill link visibility status {}" + isVisible);
		return isVisible;
	}

	public String getViewBillLinkLbl() {
		String label = getText(lnk_view_bill);
		log.info("View bill link label is " + label);
		return label;
	}

	public void clickViewBillLink() {
		click(lnk_view_bill);
		log.info("View bill link clicked {}.");
	}

	@FindBy(css = "button.userbtn.logout_header")
	private WebElement lnk_sign_out;

	public Boolean isSignOutLnkVisible() {
		return lnk_sign_out.isDisplayed();
	}

	public String getSignOutLinkLabel() {
		return getText(lnk_sign_out);
	}

	public void clickSignOutLnk() {
		pause(500);
		click(lnk_sign_out);
		log.info("Sign out button is clicked.");
	}

	@FindBy(css = "#dropdownMenuButton")
	private WebElement lnk_sign_out_dd;

	public Boolean isSignOutDdLinkVisible() {
		Boolean isVisible = isElementVisible(lnk_sign_out_dd);
		log.info("Sign out drop down link visibility status {}" + isVisible);
		return isVisible;
	}

	public String getSignOutDdLinkLbl() {
		String label = getText(lnk_sign_out_dd);
		log.info("Sign out drop down link label is " + label);
		return label;
	}

	public void clickSignOutDdLink() {
		click(lnk_sign_out_dd);
		log.info("Sign out drop down link clicked {}.");
	}

	@FindBy(css = "a.chang_pwd_header")
	private WebElement lnk_change_password;

	public Boolean isChangePasswordLinkVisible() {
		log.info("Change password link visibility status: " + lnk_change_password.isDisplayed());
		return lnk_change_password.isDisplayed();
	}

	public void clickChangePasswordLink() {
		click(lnk_change_password);
		log.info("Change password link clicked successfully.");
	}

	public String getChangePasswordLabel() {
		return getText(lnk_change_password);
	}

	@FindBy(css = ".chang_uid_header")
	private WebElement lnk_change_username;

	public Boolean isChangeUsernameLinkVisible() {
		Boolean isVisible = isElementVisible(lnk_change_username);
		log.info("Change username link visibility status {}" + isVisible);
		return isVisible;
	}

	public String getChangeUsernameLinkLbl() {
		String label = getText(lnk_change_username);
		log.info("Change username link label is " + label);
		return label;
	}

	public void clickChangeUsernameLink() {
		click(lnk_change_username);
		log.info("Change username link clicked {}.");
	}

	@FindBy(css = "#headerlogo img")
	private WebElement img_logo;

	public Boolean isImageLogoVisible() {
		Boolean isVisible = isElementVisible(img_logo);
		log.info("Image logo visibility status {}" + isVisible);
		return isVisible;
	}

	public String getImageLogoLbl() {
		String label = getText(img_logo);
		log.info("Image logo label is " + label);
		return label;
	}

	public void clickImageLogBtn() {
		click(img_logo);
		log.info("Image logo clicked {}.");
	}

	@FindBy(css = "#dropdownMenuButton")
	private WebElement img_profile_icon;

	public void clickImageProfileIco() {
		click(img_profile_icon);
		log.info("Image profile icon clicked {}.");
	}

	public void waitForImageProfileIcon() {
		waitForElementToBeVisible(img_profile_icon);
		log.info("Image profile icon is visible {} on the page.");
	}

	public Boolean isProfileIconVisible() {
		log.info("Profile image visibility status on the dashboard status {}: " + img_profile_icon.isDisplayed());
		return img_profile_icon.isDisplayed();
	}

	@FindBy(css = "img[alt = 'Smart Home']")
	private WebElement img_smart_home;

	public Boolean isSmartHomeVisible() {
		Boolean isVisible = isElementVisible(img_smart_home);
		log.info("Image logo visibility status {}" + isVisible);
		return isVisible;
	}

	public String getSmartHomeLbl() {
		String label = getText(img_smart_home);
		log.info("Image logo label is " + label);
		return label;
	}

	public void clickSmartHomeIcon() {
		click(img_smart_home);
		log.info("Image logo clicked {}.");
	}

	@FindBy(css = ".lan_txt_hide")
	private WebElement lst_select_language;

	public Boolean isSelectLanguageDdVisible() {
		Boolean isVisible = isElementVisible(lst_select_language);
		log.info("Image logo visibility status {}" + isVisible);
		return isVisible;
	}

	public String getSelectLanguageDdLbl() {
		String label = getText(lst_select_language);
		log.info("Image logo label is " + label);
		return label;
	}

	public void selectLanguageInDropdown(String languageOption) {
		selectByVisibleText(lst_select_language, languageOption);
		log.info("Language is selected in language drop-down {}.");
	}

	@FindBy(css = "#dLabel")
	private WebElement ele_text;

	@FindBy(css = "#ddldropdown #dLabel")
	private WebElement lst_account_address;

	public Boolean isAccountAddressDdVisible() {
		Boolean isVisible = isElementVisible(lst_account_address);
		log.info("Account address visibility status {}" + isVisible);
		return isVisible;
	}

	public String getAccountAddressLabel() {
		String label = getText(lst_account_address);
		log.info("Account address label is " + label);
		return label;
	}

	public void clickAccountAddress() {
		click(lst_account_address);
		log.info("Account address drop-down clicked.");
	}

	public void selectAccountAddressInDropdown(String accountAddress) {
		selectByVisibleText(lst_account_address, accountAddress);
		log.info("Account address selected in account address drop-down {}.");
	}

	@FindBys(@FindBy(css = ".dropdown.open #UlddlAddress li a"))
	private List<WebElement> lst_acc_add_not_selected;

	public List<WebElement> getLstServiceAddDropdownElement() {

		return lst_acc_add_not_selected;
	}

	@FindBys(@FindBy(xpath = "//ul[@id='UlddlAddress']/li/a"))
	private List<WebElement> account_addresses;

	public Boolean isAccountAddressesVisible() {
		Boolean isVisible = isAllElementVisible(account_addresses);
		log.info("Account address visibility status {}" + isVisible);
		return isVisible;
	}

	public List<WebElement> getAccountAddressElements() {
		return account_addresses;
	}

	public List<String> getAccountAddresses() {
		List<String> accountAddresses = new ArrayList<>();
		for (WebElement element : account_addresses) {
			accountAddresses.add(getText(element));
		}
		return accountAddresses;
	}

	@FindBy(css = "#AddressInfo span.caret")
	private WebElement lst_symbol_acc_add;

	@FindBy(css = "#AddressInfo button")
	private WebElement accountDropDown;

	public Boolean isAccountDropDownVisible() {
		Boolean status = isElementVisible(accountDropDown);
		log.info("Is account dropdown visible {}" + status);
		return status;
	}

	public String getBoxAccountAddressLabel() {
		String label = getText(accountDropDown);
		return label;
	}

	public void clickAccountDropDownBtn() {
		click(accountDropDown);
	}

	@FindBys(@FindBy(css = "#UlddlAddress li a"))
	private List<WebElement> accountDropDownOptionEle;

	public List<WebElement> getAccountDropDownOptionsEle() {
		return accountDropDownOptionEle;
	}

	@FindBy(css = ".dropdown-menu li a")
	private WebElement lst_address_available;

	@FindBy(css = "#ddlAddress")
	private WebElement lst_accounts;

	@FindBy(css = "div.header-top")
	private WebElement div_top_header;

	public Boolean isNavbarVisible() {
		Boolean isVisible = isElementVisible(div_top_header);
		log.info("Navbar visibility status {}" + isVisible);
		return isVisible;
	}

	@FindBy(css = ".main_header")
	private WebElement div_main_header;

	public Boolean isHeaderSectionVisible() {
		Boolean isVisible = isElementVisible(div_main_header);
		log.info("Main Header visibility status {}" + isVisible);
		return isVisible;
	}

	@FindBy(css = ".mid-section.dashboard_new")
	private WebElement div_mid_all_section;

	public Boolean isMidSectionVisible() {
		Boolean isVisible = isElementVisible(div_mid_all_section);
		log.info("Mid section visibility status {}" + isVisible);
		return isVisible;
	}

	@FindBy(css = ".footer.container")
	private WebElement div_footer;

	public Boolean isFooterSectionVisible() {
		Boolean isVisible = isElementVisible(div_footer);
		log.info("Footer section visibility status {}" + isVisible);
		return isVisible;
	}

	@FindBy(css = ".MuiGrid-root.DashboardFooter.FooterMain")
	private WebElement div_footer_bottom;

	public Boolean isFooterBottomSectionVisible() {
		Boolean isVisible = isElementVisible(div_footer_bottom);
		log.info("Footer bottom section visibility status {}" + isVisible);
		return isVisible;
	}

	@FindBy(css = "div#PopUpField")
	private WebElement mdl_about_my_home;

	public Boolean isAboutMyHomePopupVisible() {
		Boolean isVisible = isElementVisible(mdl_about_my_home);
		log.info("AboutMyHome Popup visibility status {}" + isVisible);
		return isVisible;
	}

	@FindBy(css = ".dropdown-menu.Userfunc li a.forget_me_header")
	private WebElement lnk_delete_my_profile;

	public Boolean isDeleteMyProfileLinkVisible() {
		Boolean isVisible = isElementVisible(lnk_delete_my_profile);
		log.info("Delete my profile link visibility status {}" + isVisible);
		return isVisible;
	}

	public String getDeleteMyProfileLinkLbl() {
		String label = getText(lnk_delete_my_profile);
		log.info("Delete my profile link label is " + label);
		return label;
	}

	public void clickDeleteMyProfileLink() {
		click(lnk_delete_my_profile);
		log.info("Delete my profile link clicked {}.");
	}

	@FindBy(css = "#forgetMePopup.close")
	private WebElement btn_close_delete_profile;

	public Boolean isCloseDeleteProfileBtnVisible() {
		Boolean isVisible = isElementVisible(btn_close_delete_profile);
		log.info("Close delete my profile button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getCloseDeleteProfileBtnLbl() {
		String label = getText(btn_close_delete_profile);
		log.info("Close delete my profile button label is " + label);
		return label;
	}

	public void clickCloseDeleteProfileBtn() {
		click(btn_close_delete_profile);
		log.info("Close delete my profile button clicked {}.");
	}

	@FindBy(css = "#btnProceed")
	private WebElement btn_proceed_delete_profile;

	public Boolean isProceedDeleteProfileBtnVisible() {
		Boolean isVisible = isElementVisible(btn_proceed_delete_profile);
		log.info("Proceed delete profile button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getProceedDeleteProfileBtnLbl() {
		String label = getText(btn_proceed_delete_profile);
		log.info("Proceed delete profile button label is " + label);
		return label;
	}

	public void clickProceedDeleteProfileBtn() {
		click(btn_proceed_delete_profile);
		log.info("Proceed delete profile button clicked {}.");
	}

	@FindBy(css = "#btnDecline")
	private WebElement btn_decline_delete_profile;

	public Boolean isDeclineButtonVisible() {
		Boolean isVisible = isElementVisible(btn_decline_delete_profile);
		log.info("Decline delete profile button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getDeclineButtonLbl() {
		String label = getText(btn_decline_delete_profile);
		log.info("Decline delete profile label is " + label);
		return label;
	}

	public void clickDeclineButton() {
		click(btn_decline_delete_profile);
		log.info("Decline delete profile button clicked {}.");
	}

	@FindBy(xpath = "//*[@id = 'forgetMePopup']//h4[1]")
	private WebElement heading_delete_profile;

	public Boolean isDeleteProfileHeaderVisible() {
		Boolean isVisible = isElementVisible(heading_delete_profile);
		log.info("Header delete profile button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getDeleteProfileHeader() {
		String label = getText(heading_delete_profile);
		log.info("Header delete profile label is " + label);
		return label;
	}

	@FindBy(xpath = "//*[@id='deleteWarning']/p[1]")
	private WebElement lbl_line1_delete_profile;

	public Boolean isLine1DeleteProfileVisible() {
		Boolean isVisible = isElementVisible(lbl_line1_delete_profile);
		log.info("Line1 delete profile button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getLine1DeleteProfileLbl() {
		String label = getText(lbl_line1_delete_profile);
		log.info("Line1 delete profile label is " + label);
		return label;
	}

	@FindBy(xpath = "//*[@id='deleteWarning']/p[2]")
	private WebElement lbl_line2_delete_profile;

	public Boolean isLine2DeleteProfileVisible() {
		Boolean isVisible = isElementVisible(lbl_line2_delete_profile);
		log.info("Line2 delete profile button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getLine2DeleteProfileLbl() {
		String label = getText(lbl_line2_delete_profile);
		log.info("Line2 Delete Profile label is " + label);
		return label;
	}

	@FindBy(css = "//*[@id='deleteWarning']//b")
	private WebElement lbl_points_delete_profile;

	public Boolean isPointsDeleteProfileVisible() {
		Boolean isVisible = isElementVisible(lbl_points_delete_profile);
		log.info("Points delete profile button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getPointsDeleteProfileLbl() {
		String label = getText(lbl_points_delete_profile);
		log.info("Points delete profile label is " + label);
		return label;
	}

	@FindBy(css = "//*[@id='deleteWarning']/p[3]")
	private WebElement lbl_line3_delete_profile;

	public Boolean isLine3DeleteProfileVisible() {
		Boolean isVisible = isElementVisible(lbl_line3_delete_profile);
		log.info("Line3 delete profile button visibility status {}" + isVisible);
		return isVisible;
	}

	public String getLine3DeleteProfileLbl() {
		String label = getText(lbl_line3_delete_profile);
		log.info("Line3 delete profile label is " + label);
		return label;
	}

	// New Changes for 10s
	@FindBy(css = "li.usage.dropicon a[globalize = 'ML_HeaderMenu_span_Usage']")
	private WebElement lnk_usage_drop_icon;

	public Boolean isUsageDropIconLinkVisible() {
		Boolean isVisible = isElementVisible(lnk_usage_drop_icon);
		log.info("Usage drop icon link visibility status {}" + isVisible);
		return isVisible;
	}

	public String getUsageDropIconLinkLbl() {
		String label = getText(lnk_usage_drop_icon);
		log.info("Usage drop icon link label is " + label);
		return label;
	}

	public void clickUsageDropIconLink() {
		click(lnk_usage_drop_icon);
		log.info("Usage drop icon link clicked {}.");
	}

	@FindBy(css = "li.usage.dropicon a[globalize = 'ML_POWERUSAGE_Navigation_Power']")
	private WebElement lnk_power_overview;

	public Boolean isPowerOverviewLinkVisible() {
		Boolean isVisible = isElementVisible(lnk_power_overview);
		log.info("Power overview link visibility status {}" + isVisible);
		return isVisible;
	}

	public String getPowerOverviewLbl() {
		String label = getText(lnk_power_overview);
		log.info("Power overview link label is " + label);
		return label;
	}

	public void clickPowerOverviewLink() {
		click(lnk_power_overview);
		log.info("Power overview link clicked {}.");
	}

	@FindBys(@FindBy(css = "button#dLabel"))
	private List<WebElement> addressDropDownButtons;

	public Boolean isAddressDropDownButtonsVisible() {
		return addressDropDownButtons.size() > 0;
	}

	@FindBy(css = "button#dLabel")
	private WebElement addressDropDownButton;

	public Boolean isAccountDropDownButtonVisible() {
		Boolean status = isElementVisible(addressDropDownButton);
		log.info("Address DropDown Button visibility status {}" + status);
		return status;
	}

	public void clickAddressDropDownButton() {
		clickElementUsingJsExecutor(addressDropDownButton);
		log.info("Address drop-down button clicked.");
	}

	@FindBy(xpath = "//*[@id= 'ddldropdown']/button[@id ='dLabel']")
	private WebElement lbl_select_address_dd;

	public Boolean isAddressDropdownVisible() {
		Boolean isVisible = isElementVisible(lbl_select_address_dd);
		log.info("Address drop-down visibility status {}" + isVisible);
		return isVisible;
	}

	public void selectAddressByVisibleAddress(String address) {
		selectByVisibleText(lbl_select_address_dd, address);
	}

	public String getAddressByVisibleAddressAttribute(String attribute) {
		String attr = getAttribute(lbl_select_address_dd, attribute);
		log.info("Account address attribute is {}: " + attr);
		return attr;
	}

	public String getAddressByVisibleAddress() {
		log.info("Fetching the address by visible address.");
		scrollToElement(lbl_select_address_dd);
		String label = getText(lbl_select_address_dd);
		log.info("Notification page header is {}: " + label);
		return label;
	}

	@FindBys(@FindBy(xpath = "//ul[@id ='UlddlAddress']/li[@addressid= '%s']"))
	private List<WebElement> lst_option_service_account;

	public String getAddressesFromDropdown(String s) {

		String accountAddress = driver.findElement(By.xpath("//ul[@id ='UlddlAddress']/li[@addressid= '" + s + "']"))
				.getText();
		return accountAddress;
	}

	public void clickAddressesFromDropdown(String s) {

		driver.findElement(By.xpath("//ul[@id ='UlddlAddress']/li[@addressid= '" + s + "']")).click();

	}

	public List<String> getAddressesValueFromDropdown() {
		List<String> addresses = new ArrayList<>();
		for (WebElement element : lst_option_service_account) {
			addresses.add(getText(element));
		}
		return addresses;
	}

	@FindBy(css = "div.ver_no_login  > p")
	private WebElement lbl_product_version;

	public Boolean isLblProductVersionVisible() {
		return lbl_product_version.isDisplayed();
	}

	public String getProductVersionLabel() {
		return getText(lbl_product_version);
	}

	@FindBy(css = "ul.group-links > li > a[globalize='ML_CONNECTMEMaster_Anchor_ContactUs']")
	private WebElement lnk_contact_us_footer;

	public Boolean isContactUsFooterLinkVisible() {
		Boolean isVisible = isElementVisible(lnk_contact_us_footer);
		log.info("Contact us footer link visibility status {}" + isVisible);
		return isVisible;
	}

	public String getContactUsFooterLinkLbl() {
		String label = getText(lnk_contact_us_footer);
		log.info("Contact us footer link label is " + label);
		return label;
	}

	public void clickContactUsFooterLink() {
		click(lnk_contact_us_footer);
		log.info("Contact us footer link clicked {}.");
	}

	public Boolean isContactUsLinkVisible() {
		return lnk_contact_us_footer.isDisplayed();
	}

	public String getContactUsLinkLabel() {
		return getText(lnk_contact_us_footer);
	}

	public void clickContactUsLink() {
		click(lnk_contact_us_footer);
	}

	@FindBy(css = "ul.group-links > li >a[globalize='ML_CONNECTME_Lbl_Terms']")
	private WebElement lnk_terms_and_conditions;

	public Boolean isTermsAndConditions() {
		return lnk_terms_and_conditions.isDisplayed();
	}

	public void clickTermsAndConditionsLnk() {
		click(lnk_terms_and_conditions);
	}

	public String getTermsAndConditionsLinkLabel() {
		return getText(lnk_terms_and_conditions);
	}

	@FindBy(css = "ul.group-links > li > a[globalize='ML_Msg_PrivacyPolicy']")
	private WebElement lnk_privacy_policy;

	public Boolean isPrivacyPoliciesLinkVisible() {
		return lnk_privacy_policy.isDisplayed();
	}

	public void clickPrivacyPolicyLink() {
		click(lnk_privacy_policy);
	}

	public String getPrivacyPolicyLinkLabel() {
		return getText(lnk_privacy_policy);
	}

	public WebElement getSelectAccountOption(String sAccountNumber) {
		By selectAccountOption = By
				.xpath("//ul[@id='UlddlAddress']//a//text()[contains(.,'" + sAccountNumber + "')]/..");
		return driver.findElement(selectAccountOption);
	}

	@FindBy(xpath = "//a[@globalize='ML_DASHBOARD_Lbl_EnergyEfficiency']")
	private WebElement lnkWaysToSave;

	public void waitForLnkWaysToSaveToBeVisible() {
		log.info("Wait for Ways To Save Link visibility on dashboard Page.");
		waitForElementToBeVisible(lnkWaysToSave);
	}

	public void clickLnkWaysToSave() {
		click(lnkWaysToSave);
		log.info("Ways To Save link clicked successfully {}.");
	}

	@FindBy(css = "#collapseEfficiency #Save_Overview a")
	private WebElement lnkWaysToSaveOverview;

	public void waitForLnkWaysToSaveOverviewToBeVisible() {
		log.info("Wait for Ways To Save Overview Link visibility on Ways To Save dropdown on dashboard Page.");
		waitForElementToBeVisible(lnkWaysToSaveOverview);
	}

	public void clickLnkWaysToSaveOverview() {
		click(lnkWaysToSaveOverview);
		log.info("Ways To Save Overview Link clicked successfully.");
	}

	@FindBys(@FindBy(css = "#ddldropdown [role='listitem']"))
	private List<WebElement> selectAccountDropDownList;

	public List<WebElement> getSelectAccountDropDownListElement() {
		return selectAccountDropDownList;
	}

	@FindBy(css = "submit-button nxt_btn_abt_popup")
	private WebElement welcomePopupNextBtn;

	public void clickNextBtnOnWelcomePopup() {
		click(welcomePopupNextBtn);
		log.info("welcome Popup Next Btn Link clicked successfully.");
	}

	@FindBy(css = "submit-button save_cont_btn")
	private WebElement saveAndSkipNotificationPrefOnWelScreen;

	public void clickSaveAndNextNotifPref() {
		click(saveAndSkipNotificationPrefOnWelScreen);
		log.info("Save And Skip Notificaiton Pref on Welcome Screen is  Link clicked successfully.");
	}

	@FindBy(css = ".buttons_area #btnSaveHomeInfo")
	private WebElement saveMyHomePopupBtn;

	public void clickSaveMyHomeBtn() {
		click(saveMyHomePopupBtn);
		log.info("Saved About My Home btn is clicked successfully.");
	}

	@FindBy(css = "#formfieldlist .select_effect")
	private WebElement selectHomeType;

	public void selectApartment() {
		selectByVisibleText(selectHomeType, "Apartment");
	}

	public void selectHomeType(String homeType) {
		selectByVisibleText(selectHomeType, homeType);
	}

	@FindBy(css = "#ddldropdown #dLabel")
	private WebElement lst_account_address1;

	public boolean isDefaultAccountAddressVisible() {
		boolean isVisible = isElementVisible(lst_account_address1);
		log.info("Account address visibility status {}" + isVisible);
		return isVisible;
	}

	public String getDefaultAccountAddressLabel() {
		String label = getText(lst_account_address1);
		log.info("Account address label is " + label);
		return label;
	}

	public Boolean isAccountDropDownBtnVisible() {
		Boolean status = isElementVisible(accountDropDown);
		log.info("Is account dropdown visible {}" + status);
		return status;
	}

}

package demo.steps.scp;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import sew.ai.api.endpoints.usage.UsageEndpoints;
import sew.ai.api.pojos.usage.UsageParams;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.models.Meter;
import sew.ai.pageObjects.scp.UsagePage;
import sew.ai.steps.scp.HomeSteps;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.PropertiesUtil;

public class UsageSteps extends UsagePage{

	private static final Logger log = LogManager.getLogger(UsageSteps.class);
    public static PropertiesUtil usageTextProp;
    private String periodicity = null, fromDate = null, toDate = null, meter = null;
    Meter[] amiMeters, nonAMIMeters, electricMeters, waterMeters, gasMeters, solarMeters;
    
    Boolean isComboAMI = true;
    UsageParams usageParams;
    String rateLink;
    public static String currencyConfig = "$";


    public UsageSteps(WebDriver driver) {
        super(driver);
        usageTextProp = new PropertiesUtil(
                FilePaths.SCP_TEXT_PROPERTIES + Constants.USAGE_TXT_FILENAME
        );
    }
    
    public void verifyElectricUsagesObjects(SoftAssert softAssert, Meter[] meters) {

        UsageEndpoints usageEndpoints = new UsageEndpoints();
        // Segregate electric meters
        usageEndpoints.segregateElectricMeters(meters);
        electricMeters = UsageEndpoints.electricMeters.toArray(new Meter[0]);
        // Segregate solar meters
        usageEndpoints.segregateSolarMeters(meters);
        solarMeters = UsageEndpoints.solarMeters.toArray(new Meter[0]);
        // Init home steps
        HomeSteps homeSteps = new HomeSteps(driver);

        // Check whether having electric meter or not
        if (electricMeters.length > 0) {
            ExtentLogger.logInfo("Electric meters are linked to the account.");
            // Verify electric tab should visible and selected by default
            Assert.assertTrue(isElectricTabVisible(),
                    "Electric tab is not visible.");
            Assert.assertTrue(isElectricTabActive(),
                    "Electric tab is not active by default.");
            // Init currency config
            
            // Verifying Monthly Interval Tab
           
            softAssert.assertTrue(isMonthlyIntervalLinkVisible(),
                    "Monthly interval is not visible.");
            softAssert.assertTrue(isMonthlyIntervalLinkActive(),
                    "Monthly interval is not active.");
            // Verifying the Units tabs
            softAssert.assertTrue(isMeasuringUnitLinkVisible(),
                    "Measuring unit tab not visible.");
            softAssert.assertTrue(isCurrencyUnitLinkVisible(),
                    "Currency unit tab not visible.");
            softAssert.assertTrue(isCurrencyUnitLinkActive(),
                    "Currency unit tab is not active.");
            // Verifying the period selection for the monthly interval
            softAssert.assertTrue(isCalendarLabelVisible(),
                    "Calendar label not visible");
            softAssert.assertTrue(getCalendarLabel().contains("Period: From"));
            softAssert.assertTrue(getCalendarLabel().contains("To"));
            // Verify no data present label visibility
            Boolean isDataNotAvailable = isDataNotAvailableLabelVisible();
            if (!isDataNotAvailable) {
                // Verify average this year tile.
                softAssert.assertEquals(getAverageThisYearLabel(),
                        usageTextProp.getPropValue("lblAverageThisYear"),
                        "Average this year label not matched.");
                softAssert.assertTrue(isAverageThisYearValueVisible(),
                        "Average this year is not visible.");
                softAssert.assertTrue(getAverageThisYearValue().contains(currencyConfig),
                        "Average This Year currency config is not same as CSP Currency config.");
                // Verify highest this year tile.
                softAssert.assertEquals(getHighestThisYearLabel(),
                        usageTextProp.getPropValue("lblHighestThisYear"),
                        "Highest this year label not matched.");
                softAssert.assertTrue(isHighestThisYearValueVisible(),
                        "Highest this year is not visible.");
                softAssert.assertTrue(getHighestThisYearValue().contains(currencyConfig),
                        "Highest this Year currency config is not same as CSP Currency config.");
                // Verify monthly electric usage data for ALL meter scenario
                if (electricMeters.length > 1) {
                    ExtentLogger.logInfo("Number of electric meters linked to the account {} " + electricMeters.length);
                    // Verifying Meter Drop-Down
                    softAssert.assertEquals(
                            getMeterNumberLabel(),
                            usageTextProp.getPropValue("lblMeterNumber"),
                            "Meter number label not matched.");
                    // Checking the collection type means all meter are ami or combo
                    for (int i = 0; i < electricMeters.length; i++) {
                        if (electricMeters[i].getIsAmi() == 0)
                            isComboAMI = false;
                    }
                    softAssert.assertTrue(isMeterDropDownVisible(),
                            "Meter drop-down is not visible for multiple meters.");
                    // Verifying for monthly intervals
                    softAssert.assertTrue(isMonthlyIntervalLinkActive(),
                            "Monthly interval by default not selected.");
                    verifyElectricBarChartSection(softAssert, currencyConfig, "monthly");
                    // Verifying whether the combo is AMI or not
                    if (isComboAMI) {
                        ExtentLogger.logInfo("Meters combo is AMI because all meters linked are AMI");
                        // Verifying other interval tabs than monthly.
                        softAssert.assertTrue(isDailyIntervalLinkVisible(),
                                "Daily interval is not visible for AMI combo.");
                        softAssert.assertTrue(isHourlyIntervalLinkVisible(),
                                "Hourly interval is not visible for AMI combo.");
                        softAssert.assertTrue(isMinutesIntervalLinkVisible(),
                                "Minutes interval is not visible for AMI combo.");
                        ExtentLogger.logInfo("Verifying so far this month tile.");
                        // Verify so far this month tile
                        softAssert.assertTrue(isSoFarThisMonthTileVisible(),
                                "So far this month tile not visible.");
                        softAssert.assertEquals(getSoFarThisLabelMonth(),
                                usageTextProp.getPropValue("lblSoFarThisMonth"),
                                "So far this month label not matched.");
                        softAssert.assertTrue(getSoFarThisValueMonth().contains(currencyConfig),
                                "Currency config is not matching for so far this month.");
                        ExtentLogger.logPass("Verified so far this month tile successfully.");
                        ExtentLogger.logInfo("Verifying projected usage tile.");
                        // Verify projected usage tile
                        softAssert.assertTrue(isProjectedUsageTileVisible(),
                                "Projected usage tile not visible.");
                        softAssert.assertEquals(getProjectedUsageLabelMonth(),
                                usageTextProp.getPropValue("lblProjectedUsage"),
                                "Projected usage label not matched.");
                        softAssert.assertTrue(getProjectedUsageValue().contains(currencyConfig),
                                "Currency config is not matching for projected usage.");
                        ExtentLogger.logPass("Verified so far this month tile successfully.");
                        clickDailyIntervalLink();
                        pause(2000);
                        pause(1000);
                        // Verifying for daily intervals
                        if (isDailyIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                softAssert.assertTrue(isWeatherOverlayToggleVisible(),
                                        "Weather overlay toggle is not visible.");
                                softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                        usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                        "Weather overlay toggle label not matched.");
                                verifyElectricBarChartSection(softAssert, currencyConfig, "daily");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched.");
                            }
                        }
                        clickHourlyIntervalLink();
                        pause(2000);
                        pause(1000);
                        // Verifying for hourly intervals
                        if (isHourlyIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                softAssert.assertTrue(isWeatherOverlayToggleVisible(),
                                        "Weather overlay toggle is not visible.");
                                softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                        usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                        "Weather overlay toggle label not matched.");
                                verifyElectricBarChartSection(softAssert, currencyConfig, "hourly");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched.");
                            }
                        }
                        clickMinutesIntervalLink();
                        pause(2000);
                        pause(1000);
                        // Verifying for minutes intervals
                        if (isMinutesIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                /*softAssert.assertTrue(isWeatherOverlayToggleVisible(),
                                        "Weather overlay toggle is not visible.");
                                softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                        usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                        "Weather overlay toggle label not matched.");*/
                                verifyElectricBarChartSection(softAssert, currencyConfig, "intervals");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched.");
                            }
                        }
                    }
                    // If meters combo is Non AMI
                    else {
                        ExtentLogger.logInfo("Meters combo is Non AMI because one or meters linked are Non AMI");
                        // Verifying other interval tabs than monthly.
                        softAssert.assertFalse(isDailyIntervalLinkVisible(),
                                "Daily interval is visible for non AMI combo.");
                        softAssert.assertFalse(isHourlyIntervalLinkVisible(),
                                "Hourly interval is visible for non AMI combo.");
                        softAssert.assertFalse(isMinutesIntervalLinkVisible(),
                                "Minutes interval is visible for non AMI combo.");
                        ExtentLogger.logPass("Verifications for Non AMI combo is passed successfully.");
                    }
                    // Check for single meter as well if its Multi-Meter case
                    String amiMeter = null;
                    String nonAmiMeter = null;
                    for (Meter meter : electricMeters) {
                        if (meter.getIsAmi() == 1)
                            amiMeter = meter.getMeterNumber();
                        else if (meter.getIsAmi() == 0)
                            nonAmiMeter = meter.getMeterNumber();
                    }
                    if (amiMeter != null) {
                        // Select AMI Meter
                        selectMeterByVisibleText(amiMeter);
                        pause(2000);
                        pause(1000);
                        ExtentLogger.logInfo("The meter linked is AMI. Verifying the UI as per AMI meter.");
                        // Verifying for monthly intervals
                        softAssert.assertTrue(isMonthlyIntervalLinkActive(),
                                "Monthly interval by default not selected.");
                        verifyElectricBarChartSection(softAssert, currencyConfig, "monthly");
                        // Verifying other interval tabs than monthly.
                        softAssert.assertTrue(isDailyIntervalLinkVisible(),
                                "Daily interval is not visible for AMI combo.");
                        softAssert.assertTrue(isHourlyIntervalLinkVisible(),
                                "Hourly interval is not visible for AMI combo.");
                        softAssert.assertTrue(isMinutesIntervalLinkVisible(),
                                "Minutes interval is not visible for AMI combo.");
                        ExtentLogger.logInfo("Verifying so far this month tile.");
                        // Verify so far this month tile
                        softAssert.assertTrue(isSoFarThisMonthTileVisible(),
                                "So far this month tile not visible.");
                        softAssert.assertEquals(getSoFarThisLabelMonth(),
                                usageTextProp.getPropValue("lblSoFarThisMonth"),
                                "So far this month label not matched."
                        );
                        softAssert.assertTrue(getSoFarThisValueMonth().contains(currencyConfig),
                                "Currency config is not matching for so far this month.");
                        ExtentLogger.logPass("Verified so far this month tile successfully.");
                        ExtentLogger.logInfo("Verifying projected usage tile.");
                        // Verify projected usage tile
                        softAssert.assertTrue(isProjectedUsageTileVisible(),
                                "Projected usage tile not visible.");
                        softAssert.assertEquals(getProjectedUsageLabelMonth(),
                                usageTextProp.getPropValue("lblProjectedUsage"),
                                "Projected usage label not matched."
                        );
                        softAssert.assertTrue(getProjectedUsageValue().contains(currencyConfig),
                                "Currency config is not matching for projected usage.");
                        ExtentLogger.logPass("Verified so far this month tile successfully.");
                        clickDailyIntervalLink();
                        pause(2000);
                        pause(1000);
                        // Verifying for daily intervals
                        if (isDailyIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                softAssert.assertTrue(isWeatherOverlayToggleVisible(),
                                        "Weather overlay toggle is not visible.");
                                softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                        usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                        "Weather overlay toggle label not matched.");
                                verifyElectricBarChartSection(softAssert, currencyConfig, "daily");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched."
                                );
                            }
                        }
                        clickHourlyIntervalLink();
                        pause(2000);
                        pause(1000);
                        // Verifying for hourly intervals
                        if (isHourlyIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                softAssert.assertTrue(isWeatherOverlayToggleVisible(),
                                        "Weather overlay toggle is not visible.");
                                softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                        usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                        "Weather overlay toggle label not matched.");
                                verifyElectricBarChartSection(softAssert, currencyConfig, "hourly");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched."
                                );
                            }
                        }
                        clickMinutesIntervalLink();
                        pause(2000);
                        pause(1000);
                        // Verifying for minutes intervals
                        if (isMinutesIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                /*softAssert.assertTrue(isWeatherOverlayToggleVisible(),
                                        "Weather overlay toggle is not visible.");
                                softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                        usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                        "Weather overlay toggle label not matched.");*/
                                // verifyElectricBarChartSection(softAssert, currencyConfig, "intervals");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched."
                                );
                            }
                        }
                    }
                    if (nonAmiMeter != null) {
                        // Select Non AMI Meter
                        selectMeterByVisibleText(nonAmiMeter);
                        pause(2000);
                        pause(1000);
                        ExtentLogger.logInfo("The meter linked is Non AMI. Verifying the UI as per Non AMI meter.");
                        // Verifying for monthly intervals
                        softAssert.assertTrue(isMonthlyIntervalLinkActive(),
                                "Monthly interval by default not selected.");
                        verifyElectricBarChartSection(softAssert, currencyConfig, "monthly");
                        // Verifying other interval tabs than monthly.
                        softAssert.assertFalse(isDailyIntervalLinkVisible(),
                                "Daily interval is visible for non AMI combo.");
                        softAssert.assertFalse(isHourlyIntervalLinkVisible(),
                                "Hourly interval is visible for non AMI combo.");
                        softAssert.assertFalse(isMinutesIntervalLinkVisible(),
                                "Minutes interval is visible for non AMI combo.");
                    }
                }
                // Check if there is single meter linked to the account
                else if (electricMeters.length == 1) {
                    ExtentLogger.logInfo("There is only 1 meter linked to the account.");
                    // Check if the single meter linked is AMI
                    if (electricMeters[0].getIsAmi() == 1) {
                        ExtentLogger.logInfo("The meter linked is AMI. Verifying the UI as per AMI meter.");
                        // Verifying for monthly intervals
                        softAssert.assertTrue(isMonthlyIntervalLinkActive(),
                                "Monthly interval by default not selected.");
                        verifyElectricBarChartSection(softAssert, currencyConfig, "monthly");
                        // Verifying other interval tabs than monthly.
                        softAssert.assertTrue(isDailyIntervalLinkVisible(),
                                "Daily interval is not visible for AMI combo.");
                        softAssert.assertTrue(isHourlyIntervalLinkVisible(),
                                "Hourly interval is not visible for AMI combo.");
                        softAssert.assertTrue(isMinutesIntervalLinkVisible(),
                                "Minutes interval is not visible for AMI combo.");
                        ExtentLogger.logInfo("Verifying so far this month tile.");
                        // Verify so far this month tile
                        softAssert.assertTrue(isSoFarThisMonthTileVisible(),
                                "So far this month tile not visible.");
                        softAssert.assertEquals(getSoFarThisLabelMonth(),
                                usageTextProp.getPropValue("lblSoFarThisMonth"),
                                "So far this month label not matched."
                        );
                        softAssert.assertTrue(getSoFarThisValueMonth().contains(currencyConfig),
                                "Currency config is not matching for so far this month.");
                        ExtentLogger.logPass("Verified so far this month tile successfully.");
                        ExtentLogger.logInfo("Verifying projected usage tile.");
                        // Verify projected usage tile
                        softAssert.assertTrue(isProjectedUsageTileVisible(),
                                "Projected usage tile not visible.");
                        softAssert.assertEquals(getProjectedUsageLabelMonth(),
                                usageTextProp.getPropValue("lblProjectedUsage"),
                                "Projected usage label not matched."
                        );
                        softAssert.assertTrue(getProjectedUsageValue().contains(currencyConfig),
                                "Currency config is not matching for projected usage.");
                        ExtentLogger.logPass("Verified so far this month tile successfully.");
                        clickDailyIntervalLink();
                        pause(2000);
                        pause(1000);
                        // Verifying for daily intervals
                        if (isDailyIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                softAssert.assertTrue(isWeatherOverlayToggleVisible(),
                                        "Weather overlay toggle is not visible.");
                                softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                        usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                        "Weather overlay toggle label not matched.");
                                verifyElectricBarChartSection(softAssert, currencyConfig, "daily");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched."
                                );
                            }
                        }
                        clickHourlyIntervalLink();
                        pause(2000);
                        pause(1000);
                        // Verifying for hourly intervals
                        if (isHourlyIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                softAssert.assertTrue(isWeatherOverlayToggleVisible(),
                                        "Weather overlay toggle is not visible.");
                                softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                        usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                        "Weather overlay toggle label not matched.");
                                verifyElectricBarChartSection(softAssert, currencyConfig, "hourly");
                            }
                        }
                        clickMinutesIntervalLink();
                        pause(2000);
                        pause(1000);
                        // Verifying for minutes intervals
                        if (isMinutesIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                verifyElectricBarChartSection(softAssert, currencyConfig, "intervals");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched."
                                );
                            }
                        }
                    }
                    // Check if the single meter linked is Non AMI
                    else if (electricMeters[0].getIsAmi() == 0) {
                        ExtentLogger.logInfo("The meter linked is Non AMI. Verifying the UI as per Non AMI meter.");
                        // Verifying for monthly intervals
                        softAssert.assertTrue(isMonthlyIntervalLinkActive(),
                                "Monthly interval by default not selected.");
                        verifyElectricBarChartSection(softAssert, currencyConfig, "monthly");
                        // Verifying other interval tabs than monthly.
                        softAssert.assertFalse(isDailyIntervalLinkVisible(),
                                "Daily interval is visible for non AMI combo.");
                        softAssert.assertFalse(isHourlyIntervalLinkVisible(),
                                "Hourly interval is visible for non AMI combo.");
                        softAssert.assertFalse(isMinutesIntervalLinkVisible(),
                                "Minutes interval is visible for non AMI combo.");
                    }
                }
            }
            // If there is no data present for the meter linked to the account.
            else {
                softAssert.assertEquals(getDataNotAvailableLabel(),
                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                        "No usage data label not matched."
                );
                softAssert.assertFalse(isAverageThisYearValueVisible(),
                        "Average this year is visible even if the data is not there.");
                softAssert.assertFalse(isHighestThisYearValueVisible(),
                        "Highest this year is visible even if the data is not there.");
                softAssert.assertFalse(isSoFarThisMonthTileVisible(),
                        "So Far This month is visible even if the data is not there.");
                softAssert.assertFalse(isProjectedUsageTileVisible(),
                        "Projected usage tile is visible even if the data is not there.");
                ExtentLogger.logInfo("There is no data present for the linked electric meter/meters.");
            }
        }
        // If there is no electric meter linked
        else {
            ExtentLogger.logSkip("There is no Electric meter linked to the account.");
        }
    }
    
    public void verifyElectricBarChartSection(SoftAssert softAssert, String currencyConfig, String interval) {
        ExtentLogger.logInfo("Verifying the bar chart section for " + interval + " interval.");
        ExtentLogger.logInfo("Verifying the Usage Bar chart X axis and Y axis labels");
        // Verifying Y Axis
        softAssert.assertEquals(getYAxisLabel(),
                usageTextProp.getPropValue("lblYAxisUsage").replace("{$CurrencyOrUnit}",
                        currencyConfig),
                "Y Axis label not matched."
        );
        // Verifying X Axis
        List<String> xAxisLabels = getXAxisLabels();
        if (interval.equalsIgnoreCase("monthly")) {
            for (String month : xAxisLabels) {
                month = month.trim();
                //softAssert.assertTrue(DateUtil.isValidDateInFormat(month, "MMM yyyy"));
            }
        } else if (interval.equalsIgnoreCase("daily")) {
            for (String month : xAxisLabels) {
                month = month.trim();
                //softAssert.assertTrue(DateUtil.isValidDateInFormat(month, "MMM d, yyyy"));
            }
        } else if (interval.equalsIgnoreCase("hourly")) {
            for (String month : xAxisLabels) {
                month = month.trim();
                //softAssert.assertTrue(DateUtil.isValidDateInFormat(month, "HH:mm aa"));
            }
        } else if (interval.equalsIgnoreCase("minutes")) {
            for (String month : xAxisLabels) {
                month = month.trim();
                //softAssert.assertTrue(DateUtil.isValidDateInFormat(month, "HH:mm aa"));
            }
        }
        ExtentLogger.logPass("The Usage Bar chart X axis and Y axis labels verified successfully.");
        // Verifying Legends Labels and Colors
        ExtentLogger.logInfo("Verifying the Usage Bar chart legends label and colors");
        softAssert.assertEquals(
                getElectricLowUsageLabel(),
                usageTextProp.getPropValue("lblLowUsageLegend"),
                "Low usage label not matched."
        );
        softAssert.assertEquals(
                getLowUsageLegendColor(),
                usageTextProp.getPropValue("lowUsageLegendColor"),
                "Low usage legend color not matched."
        );
        softAssert.assertEquals(
                getAverageUsageLabel(),
                usageTextProp.getPropValue("lblAverageUsageLegend"),
                "Average usage label not matched."
        );
        softAssert.assertEquals(
                getAverageUsageLegendColor(),
                usageTextProp.getPropValue("avgUsageLegendColor"),
                "Average usage legend color not matched."
        );
        softAssert.assertEquals(
                getHighUsageLabel(),
                usageTextProp.getPropValue("lblHighUsageLegend"),
                "High usage label not matched."
        );
        softAssert.assertEquals(
                getHighUsageLegendColor(),
                usageTextProp.getPropValue("highUsageLegendColor"),
                "High usage legend color not matched."
        );
        ExtentLogger.logPass("Usage Bar chart legends label and colors verified successfully.");
        // Verify Bar Chart Footer HyperLinks
        ExtentLogger.logInfo("Verifying Bar Chart footer hyperlinks.");
        // Verify rates link
        softAssert.assertEquals(getRatesLinkLabel(),
                usageTextProp.getPropValue("lblRatesLink"),
                "Rates link label not matched."
        );
        softAssert.assertEquals(getRatesIconLabel(),
                currencyConfig,
                "Rates currency symbol not matched."
        );
        // Verify export usages link
        softAssert.assertEquals(getExportUsageLabel(),
                usageTextProp.getPropValue("lblExportUsagesLink"),
                "Export usages link label not matched."
        );
        // Verify set usage alert link
        softAssert.assertEquals(getSetUsageAlertsLabel(),
                usageTextProp.getPropValue("lblSetUsageAlertsLink"),
                "Set usage alerts link label not matched."
        );
        // Verify green button link
        softAssert.assertEquals(getGreenButtonLabel(),
                usageTextProp.getPropValue("lblGreenButton"),
                "Green button label not matched."
        );
        ExtentLogger.logInfo("Verifying the Net Usage toggle");
        if (solarMeters.length > 0) {
            waitForLblNetUsageToggleToBeVisible();
            // Verify Net Usage toggle
            softAssert.assertTrue(isNetUsageToggleVisible(),
                    "Net Usage toggle not visible.");
            softAssert.assertEquals(getNetUsageToggleLabel(),
                    usageTextProp.getPropValue("lblNetUsageToggle"),
                    "Net usage toggle label not matched.");
            //softAssert.assertFalse(isNetUsageToggleSelected(),
               //     "Net usage toggle is selected by default.");
            softAssert.assertTrue(isNetUsageToggleButtonVisible(),
                    "Net usage toggle button not visible.");
            ExtentLogger.logPass("Net usage toggle verified successfully.");
        } else {
            ExtentLogger.logSkip("No solar meter linked to the account.");
        }
        ExtentLogger.logPass("Bar Chart footer hyperlinks verified successfully.");
        ExtentLogger.logInfo("Verifying usage Disclaimer.");
        softAssert.assertEquals(getUsageDisclaimerLabel(),
                usageTextProp.getPropValue("lblUsageDisclaimer"),
                "Usage disclaimer not matched."
        );
        ExtentLogger.logPass("Usage disclaimer verified successfully.");
        ExtentLogger.logInfo("Verifying efficiency programs message on usage page.");
        softAssert.assertEquals(getEfficiencyProgramLabel(),
                usageTextProp.getPropValue("lblEnergyEfficiencyProgram"),
                "efficiency programs disclaimer not matched."
        );
        softAssert.assertTrue(isEfficiencyProgramClickHereLinkVisible(),
                "Click here link is not visible.");
        ExtentLogger.logPass("Efficiency programs message on usage page verified successfully.");
        ExtentLogger.logInfo("Bar chart section for " + interval + " interval verified successfully.");
    }
    
    public void verifyWaterUsagesObjects(SoftAssert softAssert, Meter[] meters) {
        // Init home steps
        HomeSteps homeSteps = new HomeSteps(driver);
        // Init usage endpoints
        UsageEndpoints usageEndpoints = new UsageEndpoints();
        // Segregate water meters
        usageEndpoints.segregateWaterMeters(meters);
        waterMeters = UsageEndpoints.waterMeters.toArray(new Meter[0]);
        // Check whether having water meter or not
        if (waterMeters.length > 0) {
            ExtentLogger.logInfo("There are 1 or more water meter linked to the account.");
            // Verifying the water tab
            softAssert.assertTrue(isWaterTabLinkVisible(),
                    "Water tab is not visible.");
            clickWaterTabLink();
            pause(4000);
            ExtentLogger.logInfo("Water meters are linked to the account.");
     
            // Verifying Monthly Interval Tab
            softAssert.assertTrue(isMeterDropDownVisible(),
                    "Meter drop-down is not visible for multiple meters.");
            softAssert.assertTrue(isMonthlyIntervalLinkVisible(),
                    "Monthly interval is not visible.");
            //softAssert.assertTrue(isMonthlyIntervalLinkActive(),
            //       "Monthly interval is not active for meter length >0.");
            // Verifying the Units tabs
            softAssert.assertTrue(isMeasuringUnitLinkVisible(),
                    "Measuring unit tab not visible.");
            softAssert.assertTrue(isCurrencyUnitLinkVisible(),
                    "Currency unit tab not visible.");
            softAssert.assertTrue(isCurrencyUnitLinkActive(),
                    "Currency unit tab is not active.");
            // Verifying the period selection for the monthly interval
            softAssert.assertTrue(isCalendarLabelVisible(),
                    "Calendar label not visible");
            pause(4000);
            String calenderlabel = getCalendarLabel();
            softAssert.assertTrue(calenderlabel.contains("Period: From"), "Calendar label 'From' is not matching.");
            softAssert.assertTrue(calenderlabel.contains("To"), "Calendar label 'TO' is not matching ");
            // Verify no data present label visibility
            Boolean isDataNotAvailable = isDataNotAvailableLabelVisible();
            if (!isDataNotAvailable) {
                // Verify average this year tile.
                softAssert.assertEquals(
                        getAverageThisYearLabel(),
                        usageTextProp.getPropValue("lblAverageThisYear"),
                        "Average this year label not matched."
                );
                softAssert.assertTrue(isAverageThisYearValueVisible(),
                        "Average this year is not visible.");
                softAssert.assertTrue(getAverageThisYearValue().contains(currencyConfig),
                        "Average This Year currency config is not same as CSP Currency config.");
                // Verify highest this year tile.
                softAssert.assertEquals(
                        getHighestThisYearLabel(),
                        usageTextProp.getPropValue("lblHighestThisYear"),
                        "Highest this year label not matched."
                );
                softAssert.assertTrue(isHighestThisYearValueVisible(),
                        "Highest this year is not visible.");
                softAssert.assertTrue(getHighestThisYearValue().contains(currencyConfig),
                        "Highest this Year currency config is not same as CSP Currency config.");
                // Verify monthly water usage data for ALL meter scenario
                if (waterMeters.length > 1) {
                    ExtentLogger.logInfo("Number of water meters linked to the account {} " + waterMeters.length);
                    // Verifying Meter Drop-Down
                    softAssert.assertEquals(
                            getMeterNumberLabel(),
                            usageTextProp.getPropValue("lblMeterNumber"),
                            "Meter number label not matched."
                    );
                    // Checking the collection type
                    for (int i = 0; i < waterMeters.length; i++) {
                        if (waterMeters[i].getIsAmi() == 0)
                            isComboAMI = false;
                    }
                    // Verifying for monthly intervals
                    softAssert.assertTrue(isMonthlyIntervalLinkActive(),
                            "Monthly interval by default not selected for meter length>1.");
                    verifyWaterBarChartSection(softAssert, currencyConfig, "monthly");
                    // Verifying whether the combo is AMI or not
                    if (isComboAMI) {
                        ExtentLogger.logInfo("Meters combo is AMI because all meters linked are AMI");
                        // Verifying other interval tabs than monthly.
                        softAssert.assertTrue(isDailyIntervalLinkVisible(),
                                "Daily interval is not visible for AMI combo.");
                        softAssert.assertTrue(isHourlyIntervalLinkVisible(),
                                "Hourly interval is not visible for AMI combo.");
                        softAssert.assertTrue(isMinutesIntervalLinkVisible(),
                                "Minutes interval is not visible for AMI combo.");
                        ExtentLogger.logInfo("Verifying so far this month tile.");
                        // Verify so far this month tile
                        softAssert.assertTrue(isSoFarThisMonthTileVisible(),
                                "So far this month tile not visible.");
                        softAssert.assertEquals(getSoFarThisLabelMonth(),
                                usageTextProp.getPropValue("lblSoFarThisMonth"),
                                "So far this month label not matched."
                        );
                        softAssert.assertTrue(getSoFarThisValueMonth().contains(currencyConfig),
                                "Currency config is not matching for so far this month.");
                        ExtentLogger.logPass("Verified so far this month tile successfully.");
                        ExtentLogger.logInfo("Verifying projected usage tile.");
                        // Verify projected usage tile
                        softAssert.assertTrue(isProjectedUsageTileVisible(),
                                "Projected usage tile not visible.");
                        softAssert.assertEquals(getProjectedUsageLabelMonth(),
                                usageTextProp.getPropValue("lblProjectedUsage"),
                                "Projected usage label not matched."
                        );
                        softAssert.assertTrue(getProjectedUsageValue().contains(currencyConfig),
                                "Currency config is not matching for projected usage.");
                        ExtentLogger.logPass("Verified so far this month tile successfully.");
                        clickDailyIntervalLink();
                        pause(2000);
                        pause(1000);
                        // Verifying for daily intervals
                        if (isDailyIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                softAssert.assertTrue(isWeatherOverlayToggleVisible(),
                                        "Weather overlay toggle is not visible.");
                                softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                        usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                        "Weather overlay toggle label not matched.");
                                verifyWaterBarChartSection(softAssert, currencyConfig, "daily");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched."
                                );
                            }
                        }
                        clickHourlyIntervalLink();
                        pause(2000);
                        pause(1000);
                        // Verifying for hourly intervals
                        if (isHourlyIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                softAssert.assertTrue(isWeatherOverlayToggleVisible(),
                                        "Weather overlay toggle is not visible.");
                                softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                        usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                        "Weather overlay toggle label not matched.");
                                verifyWaterBarChartSection(softAssert, currencyConfig, "hourly");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched."
                                );
                            }
                        }
                        clickMinutesIntervalLink();
                        pause(2000);
                        pause(1000);
                        // Verifying for minutes intervals
                        if (isMinutesIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                
                                verifyWaterBarChartSection(softAssert, currencyConfig, "intervals");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched."
                                );
                            }
                        }
                    }
                    // If meters combo is Non AMI
                    else {
                        ExtentLogger.logInfo("Meters combo is Non AMI because one or meters linked are Non AMI");
                        // Verifying other interval tabs than monthly.
                        softAssert.assertFalse(isDailyIntervalLinkVisible(),
                                "Daily interval is visible for non AMI combo.");
                        softAssert.assertFalse(isHourlyIntervalLinkVisible(),
                                "Hourly interval is visible for non AMI combo.");
                        softAssert.assertFalse(isMinutesIntervalLinkVisible(),
                                "Minutes interval is visible for non AMI combo.");
                        ExtentLogger.logPass("Verifications for Non AMI combo is passed successfully.");
                    }
                    // Check for single meters
                    String amiMeter = null;
                    String nonAmiMeter = null;
                    for (Meter meter : waterMeters) {
                        if (meter.getIsAmi() == 1)
                            amiMeter = meter.getMeterNumber();
                        else if (meter.getIsAmi() == 0)
                            nonAmiMeter = meter.getMeterNumber();
                    }
                    if (amiMeter != null) {
                        // Select AMI Meter
                        selectMeterByVisibleText(amiMeter);
                        pause(3000);
                        ExtentLogger.logInfo("The meter linked is AMI. Verifying the UI as per AMI meter.");
                        // Verifying for monthly intervals
                        softAssert.assertTrue(isMonthlyIntervalLinkActive(),
                                "Monthly interval by default not selected for AMI Meter.");
                        verifyWaterBarChartSection(softAssert, currencyConfig, "monthly");
                        // Verifying other interval tabs than monthly.
                        softAssert.assertTrue(isDailyIntervalLinkVisible(),
                                "Daily interval is not visible for AMI combo.");
                        softAssert.assertTrue(isHourlyIntervalLinkVisible(),
                                "Hourly interval is not visible for AMI combo.");
                        softAssert.assertTrue(isMinutesIntervalLinkVisible(),
                                "Minutes interval is not visible for AMI combo.");
                        ExtentLogger.logInfo("Verifying so far this month tile.");
                        // Verify so far this month tile
                        softAssert.assertTrue(isSoFarThisMonthTileVisible(),
                                "So far this month tile not visible.");
                        softAssert.assertEquals(getSoFarThisLabelMonth(),
                                usageTextProp.getPropValue("lblSoFarThisMonth"),
                                "So far this month label not matched."
                        );
                        softAssert.assertTrue(getSoFarThisValueMonth().contains(currencyConfig),
                                "Currency config is not matching for so far this month.");
                        ExtentLogger.logPass("Verified so far this month tile successfully.");
                        ExtentLogger.logInfo("Verifying projected usage tile.");
                        // Verify projected usage tile
                        softAssert.assertTrue(isProjectedUsageTileVisible(),
                                "Projected usage tile not visible.");
                        softAssert.assertEquals(getProjectedUsageLabelMonth(),
                                usageTextProp.getPropValue("lblProjectedUsage"),
                                "Projected usage label not matched."
                        );
                        softAssert.assertTrue(getProjectedUsageValue().contains(currencyConfig),
                                "Currency config is not matching for projected usage.");
                        ExtentLogger.logPass("Verified so far this month tile successfully.");
                        clickDailyIntervalLink();
                        pause(2000);
                        pause(1000);
                        // Verifying for daily intervals
                        if (isDailyIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                softAssert.assertTrue(isWeatherOverlayToggleVisible(),
                                        "Weather overlay toggle is not visible.");
                                softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                        usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                        "Weather overlay toggle label not matched.");
                                verifyWaterBarChartSection(softAssert, currencyConfig, "daily");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched."
                                );
                            }
                        }
                        clickHourlyIntervalLink();
                        pause(3000);
                        // Verifying for hourly intervals
                        if (isHourlyIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                softAssert.assertTrue(isWeatherOverlayToggleVisible(),
                                        "Weather overlay toggle is not visible.");
                                softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                        usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                        "Weather overlay toggle label not matched.");
                                verifyWaterBarChartSection(softAssert, currencyConfig, "hourly");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched."
                                );
                            }
                        }
                        clickMinutesIntervalLink();
                        pause(3000);
                        // Verifying for minutes intervals
                        if (isMinutesIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                
                                verifyWaterBarChartSection(softAssert, currencyConfig, "intervals");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched."
                                );
                            }
                        }
                    }
                    if (nonAmiMeter != null) {
                        // Select Non AMI Meter
                        selectMeterByVisibleText(nonAmiMeter);
                        pause(2000);
                        pause(1000);
                        ExtentLogger.logInfo("The meter linked is Non AMI. Verifying the UI as per Non AMI meter.");
                        // Verifying for monthly intervals
                        softAssert.assertTrue(isMonthlyIntervalLinkActive(),
                                "Monthly interval by default not selected for non Ami meter.");
                        verifyWaterBarChartSection(softAssert, currencyConfig, "monthly");
                        // Verifying other interval tabs than monthly.
                        softAssert.assertFalse(isDailyIntervalLinkVisible(),
                                "Daily interval is visible for non AMI combo.");
                        softAssert.assertFalse(isHourlyIntervalLinkVisible(),
                                "Hourly interval is visible for non AMI combo.");
                        softAssert.assertFalse(isMinutesIntervalLinkVisible(),
                                "Minutes interval is visible for non AMI combo.");
                    }
                }
                // Check if there is single meter linked to the account
                else if (waterMeters.length == 1) {
                    ExtentLogger.logInfo("There is only 1 meter linked to the account.");
                    // Check if the single meter linked is AMI
                    if (waterMeters[0].getIsAmi() == 1) {
                        ExtentLogger.logInfo("The meter linked is AMI. Verifying the UI as per AMI meter.");
                        // Verifying for monthly intervals
                        softAssert.assertTrue(isMonthlyIntervalLinkActive(),
                                "Monthly interval by default not selected for Meter lenght =1.");
                        verifyWaterBarChartSection(softAssert, currencyConfig, "monthly");
                        // Verifying other interval tabs than monthly.
                        softAssert.assertTrue(isDailyIntervalLinkVisible(),
                                "Daily interval is not visible for AMI combo.");
                        softAssert.assertTrue(isHourlyIntervalLinkVisible(),
                                "Hourly interval is not visible for AMI combo.");
                        softAssert.assertTrue(isMinutesIntervalLinkVisible(),
                                "Minutes interval is not visible for AMI combo.");
                        ExtentLogger.logInfo("Verifying so far this month tile.");
                        // Verify so far this month tile
                        softAssert.assertTrue(isSoFarThisMonthTileVisible(),
                                "So far this month tile not visible.");
                        softAssert.assertEquals(getSoFarThisLabelMonth(),
                                usageTextProp.getPropValue("lblSoFarThisMonth"),
                                "So far this month label not matched."
                        );
                        softAssert.assertTrue(getSoFarThisValueMonth().contains(currencyConfig),
                                "Currency config is not matching for so far this month.");
                        ExtentLogger.logPass("Verified so far this month tile successfully.");
                        ExtentLogger.logInfo("Verifying projected usage tile.");
                        // Verify projected usage tile
                        softAssert.assertTrue(isProjectedUsageTileVisible(),
                                "Projected usage tile not visible.");
                        softAssert.assertEquals(getProjectedUsageLabelMonth(),
                                usageTextProp.getPropValue("lblProjectedUsage"),
                                "Projected usage label not matched."
                        );
                        softAssert.assertTrue(getProjectedUsageValue().contains(currencyConfig),
                                "Currency config is not matching for projected usage.");
                        ExtentLogger.logPass("Verified so far this month tile successfully.");
                        clickDailyIntervalLink();
                        pause(2000);
                        pause(1000);
                        // Verifying for daily intervals
                        if (isDailyIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                softAssert.assertTrue(isWeatherOverlayToggleVisible(),
                                        "Weather overlay toggle is not visible.");
                                softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                        usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                        "Weather overlay toggle label not matched.");
                                verifyWaterBarChartSection(softAssert, currencyConfig, "daily");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched."
                                );
                            }
                        }
                        clickHourlyIntervalLink();
                        pause(2000);
                        pause(1000);
                        // Verifying for hourly intervals
                        if (isHourlyIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                softAssert.assertTrue(isWeatherOverlayToggleVisible(),
                                        "Weather overlay toggle is not visible.");
                                softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                        usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                        "Weather overlay toggle label not matched.");
                                verifyWaterBarChartSection(softAssert, currencyConfig, "hourly");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched."
                                );
                            }
                        }
                        clickMinutesIntervalLink();
                        pause(2000);
                        pause(1000);
                        // Verifying for minutes intervals
                        if (isMinutesIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {                           
                                verifyWaterBarChartSection(softAssert, currencyConfig, "intervals");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched."
                                );
                            }
                        }
                    }
                    // Check if the single meter linked is Non AMI
                    else if (waterMeters[0].getIsAmi() == 0) {
                        ExtentLogger.logInfo("The meter linked is Non AMI. Verifying the UI as per Non AMI meter.");
                        // Verifying for monthly intervals
                        softAssert.assertTrue(isMonthlyIntervalLinkActive(),
                                "Monthly interval by default not selected for single non ami meter.");
                        verifyWaterBarChartSection(softAssert, currencyConfig, "monthly");
                        // Verifying other interval tabs than monthly.
                        softAssert.assertFalse(isDailyIntervalLinkVisible(),
                                "Daily interval is visible for non AMI combo.");
                        softAssert.assertFalse(isHourlyIntervalLinkVisible(),
                                "Hourly interval is visible for non AMI combo.");
                        softAssert.assertFalse(isMinutesIntervalLinkVisible(),
                                "Minutes interval is visible for non AMI combo.");
                    }
                }
            }
            // If there is no data present for the meter linked to the account.
            else {
                softAssert.assertEquals(getDataNotAvailableLabel(),
                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                        "No usage data label not matched."
                );
                softAssert.assertFalse(isAverageThisYearValueVisible(),
                        "Average this year is visible even if the data is not there.");
                softAssert.assertFalse(isHighestThisYearValueVisible(),
                        "Highest this year is visible even if the data is not there.");
                softAssert.assertFalse(isSoFarThisMonthTileVisible(),
                        "So Far This month is visible even if the data is not there.");
                softAssert.assertFalse(isProjectedUsageTileVisible(),
                        "Projected usage tile is visible even if the data is not there.");
                ExtentLogger.logInfo("There is no data present for the linked water meter/meters.");
            }
        }
        // If there is no water meter linked
        else {
            ExtentLogger.logSkip("There is no Water meter linked to the account.");
        }
    }
    
    public void verifyWaterBarChartSection(SoftAssert softAssert, String currencyConfig, String interval) {
        ExtentLogger.logInfo("Verifying the water bar chart section for " + interval + " interval.");
        ExtentLogger.logInfo("Verifying the water usage Bar chart X axis and Y axis labels");
        // Verifying Y Axis
        softAssert.assertEquals(getYAxisLabel(),
                usageTextProp.getPropValue("lblYAxisUsage").replace("{$CurrencyOrUnit}",
                        currencyConfig),
                "Y Axis label not matched."
        );
        // Verifying X Axis
        List<String> xAxisLabels = getXAxisLabels();
        if (interval.equalsIgnoreCase("monthly")) {
            for (String month : xAxisLabels) {
                month = month.trim();
                //softAssert.assertTrue(DateUtil.isValidDateInFormat(month, "MMM yyyy"));
            }
        } else if (interval.equalsIgnoreCase("daily")) {
            for (String month : xAxisLabels) {
                month = month.trim();
                // softAssert.assertTrue(DateUtil.isValidDateInFormat(month, "MMM d, yyyy"));
            }
        } else if (interval.equalsIgnoreCase("hourly")) {
            for (String month : xAxisLabels) {
                month = month.trim();
                //softAssert.assertTrue(DateUtil.isValidDateInFormat(month, "HH:mm aa"));
            }
        } else if (interval.equalsIgnoreCase("minutes")) {
            for (String month : xAxisLabels) {
                month = month.trim();
                //softAssert.assertTrue(DateUtil.isValidDateInFormat(month, "HH:mm aa"));
            }
        }
        ExtentLogger.logPass("The Usage Bar chart X axis and Y axis labels verified successfully.");
        // Verifying Legends Labels and Colors
        ExtentLogger.logInfo("Verifying the Usage Bar chart legends label and colors");
        softAssert.assertEquals(
                getWaterLowUsageLabel(),
                usageTextProp.getPropValue("lblLowUsageLegend"),
                "Low usage label not matched."
        );
        softAssert.assertEquals(
                getLowUsageWaterLegColor(),
                usageTextProp.getPropValue("lowUsageLegendColor"),
                "Water Low usage legend color not matched.");
        if (interval.equalsIgnoreCase("monthly") || interval.equalsIgnoreCase("daily")) {
            clickGallonUnitLink();
            pause(3000);
            if (isGallonUnitLinkActive()) {
            	
//                softAssert.assertEquals(
//                        getWaterAllocationLegendColor(),
//                        usageTextProp.getPropValue("waterAllocationLegendColor"),
//                        "Water allocation legend color not matched.");
            }
            clickCurrencyUnitLink();
            pause(3000);
        } else {
            softAssert.assertFalse(isWaterAllocationLabelVisible(),
                    "Water Allocaation Should be Visible for Monthly /Daily Interval not for other interval");

        }
        softAssert.assertEquals(
                getHighUsageLabel(),
                usageTextProp.getPropValue("lblHighUsageLegend"),
                "High usage label not matched."
        );
        softAssert.assertEquals(
                getHighUsageLegendColor(),
                usageTextProp.getPropValue("highUsageLegendColor"),
                "High usage legend color not matched."
        );
        ExtentLogger.logPass("Usage Bar chart legends label and colors verified successfully.");
        // Verify Bar Chart Footer HyperLinks
        ExtentLogger.logInfo("Verifying Bar Chart footer hyperlinks.");
        // Verify rates link
        softAssert.assertEquals(getRatesLinkLabel(),
                usageTextProp.getPropValue("lblRatesLink"),
                "Rates link label not matched."
        );
        softAssert.assertEquals(getRatesIconLabel(),
                currencyConfig,
                "Rates currency symbol not matched."
        );
        // Verify export usages link
        softAssert.assertEquals(getExportUsageLabel(),
                usageTextProp.getPropValue("lblExportUsagesLink"),
                "Export usages link label not matched."
        );
        // Verify set usage alert link
        softAssert.assertEquals(getSetUsageAlertsLabel(),
                usageTextProp.getPropValue("lblSetUsageAlertsLink"),
                "Set usage alerts link label not matched."
        );
        // Verify green button link
        softAssert.assertEquals(getGreenButtonLabel(),
                usageTextProp.getPropValue("lblGreenButton"),
                "Green button label not matched."
        );
        ExtentLogger.logPass("Bar Chart footer hyperlinks verified successfully.");
        ExtentLogger.logInfo("Verifying usage Disclaimer.");
        softAssert.assertEquals(getUsageDisclaimerLabel(),
                usageTextProp.getPropValue("lblWaterUsageDisclaimer"),
                "Water usage disclaimer not matched."
        );
        ExtentLogger.logPass("Usage disclaimer verified successfully.");
        ExtentLogger.logInfo("Verifying efficiency programs message on usage page.");
        softAssert.assertEquals(getEfficiencyProgramLabel(),
                usageTextProp.getPropValue("lblEnergyEfficiencyProgram"),
                "efficiency programs disclaimer not matched."
        );
        softAssert.assertTrue(isEfficiencyProgramClickHereLinkVisible(),
                "Click here link is not visible.");
        ExtentLogger.logPass("Efficiency programs message on usage page verified successfully.");
        ExtentLogger.logInfo("Water usage Bar chart section for " + interval + " interval verified successfully.");
    }
    
    public void verifyGasUsagesObjects(SoftAssert softAssert, Meter[] meters) {
        // Init home steps
        HomeSteps homeSteps = new HomeSteps(driver);
        // Init usage endpoints
        UsageEndpoints usageEndpoints = new UsageEndpoints();
        // Segregate gas meters
        usageEndpoints.segregateGasMeters(meters);
        gasMeters = UsageEndpoints.gasMeters.toArray(new Meter[0]);
        // Check whether having gas meter or not
        if (gasMeters.length > 0) {
            ExtentLogger.logInfo("There are 1 or more gas meter linked to the account.");
            // Verifying the gas tab
            softAssert.assertTrue(isGasTabLinkVisible(),
                    "Gas tab is not visible.");
            clickGasTabLink();
            pause(2000);
            ExtentLogger.logInfo("Gas meters are linked to the account.");
            
            // Verifying Monthly interval Tab
            softAssert.assertTrue(isMeterDropDownVisible(),
                    "Meter drop-down is not visible for multiple meters.");
            softAssert.assertTrue(isMonthlyIntervalLinkVisible(),
                    "Monthly interval is not visible.");
            //softAssert.assertTrue(isMonthlyIntervalLinkActive(),  "Monthly interval is not active for Gas meter length >0");
            // Verifying the Units tabs
            softAssert.assertTrue(isMeasuringUnitLinkVisible(),
                    "Measuring unit tab not visible.");
            softAssert.assertTrue(isCurrencyUnitLinkVisible(),
                    "Currency unit tab not visible.");
            softAssert.assertTrue(isCurrencyUnitLinkActive(),
                    "Currency unit tab is not active.");
            // Verifying the period selection for the monthly interval
            softAssert.assertTrue(isCalendarLabelVisible(),
                    "Calendar label not visible");
            pause(2000);
            softAssert.assertTrue(getCalendarLabel().contains("Period: From"), "calender Form label is not contain as expected");
            //softAssert.assertTrue(getCalendarLabel().contains("To"),"Calender To lable is not Contain as expected.");
            // Verify no data present label visibility
            Boolean isDataNotAvailable = isDataNotAvailableLabelVisible();
            if (!isDataNotAvailable) {
                // Verify average this year tile.
                softAssert.assertEquals(getAverageThisYearLabel(),
                        usageTextProp.getPropValue("lblAverageThisYear"),
                        "Average this year label not matched."
                );
                softAssert.assertTrue(isAverageThisYearValueVisible(),
                        "Average this year is not visible.");
                softAssert.assertTrue(getAverageThisYearValue().contains(currencyConfig),
                        "Average This Year currency config is not same as CSP Currency config.");
                // Verify highest this year tile.
                softAssert.assertEquals(getHighestThisYearLabel(),
                        usageTextProp.getPropValue("lblHighestThisYear"),
                        "Highest this year label not matched."
                );
                softAssert.assertTrue(isHighestThisYearValueVisible(),
                        "Highest this year is not visible.");
                softAssert.assertTrue(getHighestThisYearValue().contains(currencyConfig),
                        "Highest this Year currency config is not same as CSP Currency config.");
                // Verify monthly gas usage data for ALL meter scenario
                if (gasMeters.length > 1) {
                    ExtentLogger.logInfo("Number of gas meters linked to the account {} " + gasMeters.length);
                    // Verifying Meter Drop-Down
                    softAssert.assertEquals(getMeterNumberLabel(),
                            usageTextProp.getPropValue("lblMeterNumber"),
                            "Meter number label not matched."
                    );
                    // Checking the collection type
                    for (int i = 0; i < gasMeters.length; i++) {
                        if (gasMeters[i].getIsAmi() == 0)
                            isComboAMI = false;
                    }
                    // Verifying for monthly intervals
                    softAssert.assertTrue(isMonthlyIntervalLinkActive(),
                            "Monthly interval by default not selected.");
                    verifyGasBarChartSection(softAssert, currencyConfig, "monthly");
                    // Verifying whether the combo is AMI or not
                    if (isComboAMI) {
                        ExtentLogger.logInfo("Meters combo is AMI because all meters linked are AMI");
                        // Verifying other interval tabs than monthly.
                        softAssert.assertTrue(isDailyIntervalLinkVisible(),
                                "Daily interval is not visible for AMI combo.");
                        softAssert.assertTrue(isHourlyIntervalLinkVisible(),
                                "Hourly interval is not visible for AMI combo.");
                        softAssert.assertTrue(isMinutesIntervalLinkVisible(),
                                "Minutes interval is not visible for AMI combo.");
                        ExtentLogger.logInfo("Verifying so far this month tile.");
                        // Verify so far this month tile
                        softAssert.assertTrue(isSoFarThisMonthTileVisible(),
                                "So far this month tile not visible.");
                        softAssert.assertEquals(getSoFarThisLabelMonth(),
                                usageTextProp.getPropValue("lblSoFarThisMonth"),
                                "So far this month label not matched."
                        );
                        softAssert.assertTrue(getSoFarThisValueMonth().contains(currencyConfig),
                                "Currency config is not matching for so far this month.");
                        ExtentLogger.logPass("Verified so far this month tile successfully.");
                        ExtentLogger.logInfo("Verifying projected usage tile.");
                        // Verify projected usage tile
                        softAssert.assertTrue(isProjectedUsageTileVisible(),
                                "Projected usage tile not visible.");
                        softAssert.assertEquals(getProjectedUsageLabelMonth(),
                                usageTextProp.getPropValue("lblProjectedUsage"),
                                "Projected usage label not matched."
                        );
                        softAssert.assertTrue(getProjectedUsageValue().contains(currencyConfig),
                                "Currency config is not matching for projected usage.");
                        ExtentLogger.logPass("Verified so far this month tile successfully.");
                        clickDailyIntervalLink();
                        pause(2000);
                        pause(1000);
                        // Verifying for daily intervals
                        if (isDailyIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                softAssert.assertTrue(isWeatherOverlayToggleVisible(),
                                        "Weather overlay toggle is not visible.");
                                softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                        usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                        "Weather overlay toggle label not matched.");
                                verifyGasBarChartSection(softAssert, currencyConfig, "daily");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched."
                                );
                            }
                        }
                        clickHourlyIntervalLink();
                        pause(2000);
                        pause(1000);
                        // Verifying for hourly intervals
                        if (isHourlyIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                softAssert.assertTrue(isWeatherOverlayToggleVisible(),
                                        "Weather overlay toggle is not visible.");
                                softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                        usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                        "Weather overlay toggle label not matched.");
                                verifyGasBarChartSection(softAssert, currencyConfig, "hourly");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched."
                                );
                            }
                        }
                        clickMinutesIntervalLink();
                        pause(2000);
                        pause(1000);
                        // Verifying for minutes intervals
                        if (isMinutesIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                
                                softAssert.assertFalse(isWeatherOverlayToggleVisible(),
                                        "Weather overlay toggle is visible it s.");
                                softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                        usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                        "Weather overlay toggle label not matched.");
                                verifyGasBarChartSection(softAssert, currencyConfig, "intervals");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched."
                                );
                            }
                        }
                    }
                    // If meters combo is Non AMI
                    else {
                        ExtentLogger.logInfo("Meters combo is Non AMI because one or meters linked are Non AMI");
                        // Verifying other interval tabs than monthly.
                        softAssert.assertFalse(isDailyIntervalLinkVisible(),
                                "Daily interval is visible for non AMI combo.");
                        softAssert.assertFalse(isHourlyIntervalLinkVisible(),
                                "Hourly interval is visible for non AMI combo.");
//                        softAssert.assertFalse(isMinutesIntervalLinkVisible(),
//                                "Minutes interval is visible for non AMI combo.");
                        ExtentLogger.logPass("Verifications for Non AMI combo is passed successfully.");
                    }
                    // Check for single meters
                    String amiMeter = null;
                    String nonAmiMeter = null;
                    for (Meter meter : gasMeters) {
                        if (meter.getIsAmi() == 1)
                            amiMeter = meter.getMeterNumber();
                        else if (meter.getIsAmi() == 0)
                            nonAmiMeter = meter.getMeterNumber();
                    }
                    if (amiMeter != null) {
                        // Select AMI Meter
                        selectMeterByVisibleText(amiMeter);
                        pause(2000);
                        pause(1000);
                        ExtentLogger.logInfo("The meter linked is AMI. Verifying the UI as per AMI meter.");
                        // Verifying for monthly intervals
                        softAssert.assertTrue(isMonthlyIntervalLinkActive(),
                                "Monthly interval by default not selected.");
                        verifyGasBarChartSection(softAssert, currencyConfig, "monthly");
                        // Verifying other interval tabs than monthly.
                        softAssert.assertTrue(isDailyIntervalLinkVisible(),
                                "Daily interval is not visible for AMI combo.");
                        softAssert.assertTrue(isHourlyIntervalLinkVisible(),
                                "Hourly interval is not visible for AMI combo.");
//                        softAssert.assertTrue(isMinutesIntervalLinkVisible(),
//                                "Minutes interval is not visible for AMI combo.");
                        ExtentLogger.logInfo("Verifying so far this month tile.");
                        // Verify so far this month tile
                        softAssert.assertTrue(isSoFarThisMonthTileVisible(),
                                "So far this month tile not visible.");
                        softAssert.assertEquals(getSoFarThisLabelMonth(),
                                usageTextProp.getPropValue("lblSoFarThisMonth"),
                                "So far this month label not matched."
                        );
                        softAssert.assertTrue(getSoFarThisValueMonth().contains(currencyConfig),
                                "Currency config is not matching for so far this month.");
                        ExtentLogger.logPass("Verified so far this month tile successfully.");
                        ExtentLogger.logInfo("Verifying projected usage tile.");
                        // Verify projected usage tile
                        softAssert.assertTrue(isProjectedUsageTileVisible(),
                                "Projected usage tile not visible.");
                        softAssert.assertEquals(getProjectedUsageLabelMonth(),
                                usageTextProp.getPropValue("lblProjectedUsage"),
                                "Projected usage label not matched."
                        );
                        softAssert.assertTrue(getProjectedUsageValue().contains(currencyConfig),
                                "Currency config is not matching for projected usage.");
                        ExtentLogger.logPass("Verified so far this month tile successfully.");
                        clickDailyIntervalLink();
                        pause(2000);
                        pause(1000);
                        // Verifying for daily intervals
                        if (isDailyIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                softAssert.assertTrue(isWeatherOverlayToggleVisible(),
                                        "Weather overlay toggle is not visible.");
                                softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                        usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                        "Weather overlay toggle label not matched.");
                                verifyGasBarChartSection(softAssert, currencyConfig, "daily");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched."
                                );
                            }
                        }
                        clickHourlyIntervalLink();
                        pause(2000);
                        pause(1000);
                        // Verifying for hourly intervals
                        if (isHourlyIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                softAssert.assertTrue(isWeatherOverlayToggleVisible(),
                                        "Weather overlay toggle is not visible.");
                                softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                        usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                        "Weather overlay toggle label not matched.");
                                verifyGasBarChartSection(softAssert, currencyConfig, "hourly");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched."
                                );
                            }
                        }
//                        clickMinutesIntervalLink();
//                        pause(2000);
//                        pause(1000);
//                        // Verifying for minutes intervals
//                        if (isMinutesIntervalLinkActive()) {
//                            isDataNotAvailable = isDataNotAvailableLabelVisible();
//                            if (!isDataNotAvailable) {
//                                
//                                softAssert.assertFalse(isWeatherOverlayToggleVisible(),
//                                        "Weather overlay toggle is visible for interval, It shoild not be visible.");
//                                //softAssert.assertEquals(getWeatherOverlayToggleLabel(),usageTextProp.getPropValue("lblWeatherOverlayToggle"),
//                                //"Weather overlay toggle label not matched.");
//                                verifyGasBarChartSection(softAssert, currencyConfig, "intervals");
//                            } else {
//                                softAssert.assertEquals(getDataNotAvailableLabel(),
//                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
//                                        "No usage data label not matched."
//                                );
//                            }
//                        }
                    }
                    if (nonAmiMeter != null) {
                        // Select Non AMI Meter
                        selectMeterByVisibleText(nonAmiMeter);
                        pause(3000);
                        ExtentLogger.logInfo("The meter linked is Non AMI. Verifying the UI as per Non AMI meter.");
                        // Verifying for monthly intervals
                        softAssert.assertTrue(isMonthlyIntervalLinkActive(),
                                "Monthly interval by default not selected.");
                        pause(3000);
                        verifyGasBarChartSection(softAssert, currencyConfig, "monthly");
                        // Verifying other interval tabs than monthly.
                        softAssert.assertFalse(isDailyIntervalLinkVisible(),
                                "Daily interval is visible for non AMI combo.");
                        softAssert.assertFalse(isHourlyIntervalLinkVisible(),
                                "Hourly interval is visible for non AMI combo.");
//                        softAssert.assertFalse(isMinutesIntervalLinkVisible(),
//                                "Minutes interval is visible for non AMI combo.");
                    }
                }
                // Check if there is single meter linked to the account
                else if (gasMeters.length == 1) {
                    ExtentLogger.logInfo("There is only 1 meter linked to the account.");
                    // Check if the single meter linked is AMI
                    if (gasMeters[0].getIsAmi() == 1) {
                        ExtentLogger.logInfo("The meter linked is AMI. Verifying the UI as per AMI meter.");
                        // Verifying for monthly intervals
                        softAssert.assertTrue(isMonthlyIntervalLinkActive(),
                                "Monthly interval by default not selected.");
                        verifyGasBarChartSection(softAssert, currencyConfig, "monthly");
                        // Verifying other interval tabs than monthly.
                        softAssert.assertTrue(isDailyIntervalLinkVisible(),
                                "Daily interval is not visible for AMI combo.");
                        softAssert.assertTrue(isHourlyIntervalLinkVisible(),
                                "Hourly interval is not visible for AMI combo.");
//                        softAssert.assertTrue(isMinutesIntervalLinkVisible(),
//                                "Minutes interval is not visible for AMI combo.");
                        ExtentLogger.logInfo("Verifying so far this month tile.");
                        // Verify so far this month tile
                        softAssert.assertTrue(isSoFarThisMonthTileVisible(),
                                "So far this month tile not visible.");
                        softAssert.assertEquals(getSoFarThisLabelMonth(),
                                usageTextProp.getPropValue("lblSoFarThisMonth"),
                                "So far this month label not matched."
                        );
                        softAssert.assertTrue(getSoFarThisValueMonth().contains(currencyConfig),
                                "Currency config is not matching for so far this month.");
                        ExtentLogger.logPass("Verified so far this month tile successfully.");
                        ExtentLogger.logInfo("Verifying projected usage tile.");
                        // Verify projected usage tile
                        softAssert.assertTrue(isProjectedUsageTileVisible(),
                                "Projected usage tile not visible.");
                        softAssert.assertEquals(getProjectedUsageLabelMonth(),
                                usageTextProp.getPropValue("lblProjectedUsage"),
                                "Projected usage label not matched."
                        );
                        softAssert.assertTrue(getProjectedUsageValue().contains(currencyConfig),
                                "Currency config is not matching for projected usage.");
                        ExtentLogger.logPass("Verified so far this month tile successfully.");
                        clickDailyIntervalLink();
                        pause(2000);
                        pause(1000);
                        // Verifying for daily intervals
                        if (isDailyIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                softAssert.assertTrue(isWeatherOverlayToggleVisible(),
                                        "Weather overlay toggle is not visible.");
                                softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                        usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                        "Weather overlay toggle label not matched.");
                                verifyGasBarChartSection(softAssert, currencyConfig, "daily");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched."
                                );
                            }
                        }
                        clickHourlyIntervalLink();
                        pause(2000);
                        pause(1000);
                        // Verifying for hourly intervals
                        if (isHourlyIntervalLinkActive()) {
                            isDataNotAvailable = isDataNotAvailableLabelVisible();
                            if (!isDataNotAvailable) {
                                softAssert.assertTrue(isWeatherOverlayToggleVisible(),
                                        "Weather overlay toggle is not visible.");
                                softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                        usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                        "Weather overlay toggle label not matched.");
                                verifyGasBarChartSection(softAssert, currencyConfig, "hourly");
                            } else {
                                softAssert.assertEquals(getDataNotAvailableLabel(),
                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                                        "No usage data label not matched."
                                );
                            }
                        }
//                        clickMinutesIntervalLink();
//                        pause(2000);
//                        pause(1000);
//                        // Verifying for minutes intervals
//                        if (isMinutesIntervalLinkActive()) {
//                            isDataNotAvailable = isDataNotAvailableLabelVisible();
//                            if (!isDataNotAvailable) {
//                                
//                                /*softAssert.assertTrue(isWeatherOverlayToggleVisible(),
//                                        "Weather overlay toggle is not visible.");
//                                softAssert.assertEquals(getWeatherOverlayToggleLabel(),
//                                        usageTextProp.getPropValue("lblWeatherOverlayToggle"),
//                                        "Weather overlay toggle label not matched.");*/
//                                verifyGasBarChartSection(softAssert, currencyConfig, "intervals");
//                            } else {
//                                softAssert.assertEquals(getDataNotAvailableLabel(),
//                                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
//                                        "No usage data label not matched."
//                                );
//                            }
//                        }
                    }
                    // Check if the single meter linked is Non AMI
                    else if (gasMeters[0].getIsAmi() == 0) {
                        ExtentLogger.logInfo("The meter linked is Non AMI. Verifying the UI as per Non AMI meter.");
                        // Verifying for monthly intervals
                        softAssert.assertTrue(isMonthlyIntervalLinkActive(),
                                "Monthly interval by default not selected.");
                        verifyGasBarChartSection(softAssert, currencyConfig, "monthly");
                        // Verifying other interval tabs than monthly.
                        softAssert.assertFalse(isDailyIntervalLinkVisible(),
                                "Daily interval is visible for non AMI combo.");
                        softAssert.assertFalse(isHourlyIntervalLinkVisible(),
                                "Hourly interval is visible for non AMI combo.");
//                        softAssert.assertFalse(isMinutesIntervalLinkVisible(),
//                                "Minutes interval is visible for non AMI combo.");
                    }
                }
            }
            // If there is no data present for the meter linked to the account.
            else {
                softAssert.assertEquals(getDataNotAvailableLabel(),
                        usageTextProp.getPropValue("lblNoUsageDataPresent"),
                        "No usage data label not matched."
                );
                softAssert.assertFalse(isAverageThisYearValueVisible(),
                        "Average this year is visible even if the data is not there.");
                softAssert.assertFalse(isHighestThisYearValueVisible(),
                        "Highest this year is visible even if the data is not there.");
                softAssert.assertFalse(isSoFarThisMonthTileVisible(),
                        "So Far This month is visible even if the data is not there.");
                softAssert.assertFalse(isProjectedUsageTileVisible(),
                        "Projected usage tile is visible even if the data is not there.");
                ExtentLogger.logInfo("There is no data present for the linked gas meter/meters.");
            }
        }
        // If there is no gas meter linked
        else {
            ExtentLogger.logSkip("There is no Gas meter linked to the account.");
        }
    }
    
    public void verifyGasBarChartSection(SoftAssert softAssert, String currencyConfig, String interval) {
        ExtentLogger.logInfo("Verifying the gas bar chart section for " + interval + " interval.");
        ExtentLogger.logInfo("Verifying the gas usage Bar chart X axis and Y axis labels");
        // Verifying Y Axis
        softAssert.assertEquals(getYAxisLabel(),
                usageTextProp.getPropValue("lblYAxisUsage").replace("{$CurrencyOrUnit}",
                        currencyConfig),
                "Y Axis label not matched."
        );
        // Verifying X Axis
        List<String> xAxisLabels = getXAxisLabels();
        if (interval.equalsIgnoreCase("monthly")) {
            for (String month : xAxisLabels) {
                month = month.trim();
                //softAssert.assertTrue(DateUtil.isValidDateInFormat(month, "MMM yyyy"));
            }
        } else if (interval.equalsIgnoreCase("daily")) {
            for (String month : xAxisLabels) {
                month = month.trim();
                //softAssert.assertTrue(DateUtil.isValidDateInFormat(month, "MMM d, yyyy"));
            }
        } else if (interval.equalsIgnoreCase("hourly")) {
            for (String month : xAxisLabels) {
                month = month.trim();
                //softAssert.assertTrue(DateUtil.isValidDateInFormat(month, "HH:mm aa"));
            }
        } else if (interval.equalsIgnoreCase("minutes")) {
            for (String month : xAxisLabels) {
                month = month.trim();
                //softAssert.assertTrue(DateUtil.isValidDateInFormat(month, "HH:mm aa"));
            }
        }
        ExtentLogger.logPass("The Usage Bar chart X axis and Y axis labels verified successfully.");
        // Verifying Legends labels and colors
        ExtentLogger.logInfo("Verifying the Usage Bar chart legends label and colors");
        softAssert.assertEquals(getElectricLowUsageLabel(),
                usageTextProp.getPropValue("lblLowUsageLegend"),
                "Low usage label not matched."
        );
        softAssert.assertEquals(getLowUsageLegendColor(),
                usageTextProp.getPropValue("lowUsageLegendColor"),
                "Low usage legend color not matched."
        );
        softAssert.assertEquals(getAverageUsageLabel(),
                usageTextProp.getPropValue("lblAverageUsageLegend"),
                "Gas usage label not matched."
        );
        softAssert.assertEquals(getAverageUsageLegendColor(),
                usageTextProp.getPropValue("avgUsageLegendColor"),
                "Gas usage legend color not matched."
        );
        softAssert.assertEquals(getHighUsageLabel(),
                usageTextProp.getPropValue("lblHighUsageLegend"),
                "High usage label not matched."
        );
        softAssert.assertEquals(getHighUsageLegendColor(),
                usageTextProp.getPropValue("highUsageLegendColor"),
                "High usage legend color not matched."
        );
        ExtentLogger.logPass("Usage Bar chart legends label and colors verified successfully.");
        // Verify Bar Chart Footer HyperLinks
        ExtentLogger.logInfo("Verifying Bar Chart footer hyperlinks.");
        // Verify rates link
        softAssert.assertEquals(getRatesLinkLabel(),
                usageTextProp.getPropValue("lblRatesLink"),
                "Rates link label not matched."
        );
        softAssert.assertEquals(getRatesIconLabel(),
                currencyConfig,
                "Rates currency symbol not matched."
        );
        // Verify export usages link
        softAssert.assertEquals(getExportUsageLabel(),
                usageTextProp.getPropValue("lblExportUsagesLink"),
                "Export usages link label not matched."
        );
        // Verify set usage alert link
        softAssert.assertEquals(getSetUsageAlertsLabel(),
                usageTextProp.getPropValue("lblSetUsageAlertsLink"),
                "Set usage alerts link label not matched."
        );
        // Verify green button link
        softAssert.assertEquals(getGreenButtonLabel(),
                usageTextProp.getPropValue("lblGreenButton"),
                "Green button label not matched."
        );
        ExtentLogger.logPass("Bar Chart footer hyperlinks verified successfully.");
        ExtentLogger.logInfo("Verifying usage Disclaimer.");
        softAssert.assertEquals(getUsageDisclaimerLabel(),
                usageTextProp.getPropValue("lblUsageDisclaimer"),
                "Usage disclaimer not matched."
        );
        ExtentLogger.logPass("Usage disclaimer verified successfully.");
        ExtentLogger.logInfo("Verifying efficiency programs message on usage page.");
        softAssert.assertEquals(getEfficiencyProgramLabel(),
                usageTextProp.getPropValue("lblEnergyEfficiencyProgram"),
                "efficiency programs disclaimer not matched."
        );
        softAssert.assertTrue(isEfficiencyProgramClickHereLinkVisible(),
                "Click here link is not visible.");
        ExtentLogger.logPass("Efficiency programs message on usage page verified successfully.");
        ExtentLogger.logInfo("Gas usage Bar chart section for " + interval + " interval verified successfully.");
    }
    
    public void verifySolarUsagesObjects(SoftAssert softAssert, Meter[] meters) {
        // Init home steps
        HomeSteps homeSteps = new HomeSteps(driver);
        // Init usage endpoints
        UsageEndpoints usageEndpoints = new UsageEndpoints();
        // Segregate solar meters
        usageEndpoints.segregateSolarMeters(meters);
        solarMeters = UsageEndpoints.solarMeters.toArray(new Meter[0]);
        // Check whether having solar meter or not
        if (solarMeters.length > 0) {
            ExtentLogger.logInfo("There are 1 or more solar meter linked to the account.");
            // Verifying the solar tab
            softAssert.assertTrue(isSolarTabLinkVisible(),
                    "Solar tab is not visible.");
            clickSolarTabLink();
            pause(2000);
            ExtentLogger.logInfo("Solar meters are linked to the account.");
            
            // Verifying the Units tabs
            softAssert.assertTrue(isMeasuringUnitLinkVisible(),
                    "Measuring unit tab not visible.");
            softAssert.assertTrue(isCurrencyUnitLinkVisible(),
                    "Currency unit tab not visible.");
            softAssert.assertTrue(isCurrencyUnitLinkActive(),
                    "Currency unit tab is not active.");
            // Verifying the period selection for the monthly interval
            softAssert.assertTrue(isCalendarLabelVisible(),
                    "Calendar label not visible");
            pause(3000);
            softAssert.assertTrue(getCalendarLabel().contains("Period: From"), "Period : from Label is not contain in Calender drop down or calender not visible.");
            softAssert.assertTrue(getCalendarLabel().contains("To"), "To Label is not contain in Calender drop down or calender not visible.");
            // Verify no data present label visibility
            Boolean isDataNotAvailable = isDataNotAvailableLabelVisible();
            if (!isDataNotAvailable) {
                // Verify highest in 30 days tile.
//                softAssert.assertEquals(getHighestIn30DaysLabel(),
//                        usageTextProp.getPropValue("lblHighestThisYear"),
//                        "Highest ThisYear label not matched.");
//                softAssert.assertTrue(isHighestIn30DaysLabelVisible(),
//                        "Highest ThisYear is not visible.");
               // softAssert.assertTrue(getHighestThisYearValue().contains(currencyConfig),
                 //       "Highest in 30 days currency config is not same as CSP Currency config.");
                // Verify projected generation tile.
//                softAssert.assertEquals(getProjectedGenerationLabel(),
//                        usageTextProp.getPropValue("lblProjectedGeneration"),
//                        "Projected generation label not matched."
//                );
//                softAssert.assertTrue(isProjectedGenerationValueVisible(),
//                        "Projected generation is not visible.");
//                softAssert.assertTrue(getProjectedGenerationValue().contains(currencyConfig),
//                        "Projected generation currency config is not same as CSP Currency config.");
                // Verify monthly solar usage data for ALL meter scenario
                if (solarMeters.length > 1) {
                    ExtentLogger.logInfo("Number of solar meters linked to the account {} " + solarMeters.length);
                    // Verifying Meter Drop-Down
                    softAssert.assertEquals(getMeterNumberLabel(),
                            usageTextProp.getPropValue("lblMeterNumber"),
                            "Meter number label not matched."
                    );
                    // Verifying Monthly interval Tab
                    softAssert.assertTrue(isMeterDropDownVisible(),
                            "Meter drop-down is not visible for multiple meters.");
                    // Verify no data present label visibility
                    isDataNotAvailable = isDataNotAvailableLabelVisible();
                    if (!isDataNotAvailable) {
                        ExtentLogger.logInfo("Meters combo is AMI because all meters linked are AMI");
                        softAssert.assertTrue(isWeatherOverlayToggleVisible(),
                                "Weather overlay toggle is not visible.");
                        softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                "Weather overlay toggle label not matched.");
                        verifySolarBarChartSection(softAssert, currencyConfig, "daily");
                    } else {
                        softAssert.assertEquals(getDataNotAvailableLabel(),
                                usageTextProp.getPropValue("lblNoGenerationDataPresent"),
                                "No generation data label not matched."
                        );
                    }
                    // Check for single meters
                    String amiMeter = null;
                    for (Meter meter : solarMeters) {
                        if (meter.getIsAmi() == 1)
                            amiMeter = meter.getMeterNumber();
                    }
                    if (amiMeter != null) {
                        // Select AMI Meter
                        selectMeterByVisibleText(amiMeter);
                        pause(2000);
                        pause(1000);
                        ExtentLogger.logInfo("The meter linked is AMI. Verifying the UI as per AMI meter.");
                        isDataNotAvailable = isDataNotAvailableLabelVisible();
                        if (!isDataNotAvailable) {
                            // Verifying for daily intervals
                            softAssert.assertTrue(isWeatherOverlayToggleVisible(),
                                    "Weather overlay toggle is not visible.");
                            softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                    usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                    "Weather overlay toggle label not matched.");
                            verifySolarBarChartSection(softAssert, currencyConfig, "daily");
                        } else {
                            softAssert.assertEquals(getDataNotAvailableLabel(),
                                    usageTextProp.getPropValue("lblNoGenerationDataPresent"),
                                    "No generation data label not matched."
                            );
                        }
                    }
                }
                // Check if there is single meter linked to the account
                else if (solarMeters.length == 1) {
                    ExtentLogger.logInfo("There is only 1 meter linked to the account.");
                    // Check if the single meter linked is AMI
                    if (solarMeters[0].getIsAmi() == 1) {
                        ExtentLogger.logInfo("The meter linked is AMI. Verifying the UI as per AMI meter.");
                        clickDailyIntervalLink();
                        isDataNotAvailable = isDataNotAvailableLabelVisible();
                        if (!isDataNotAvailable) {
                            // Verifying for daily intervals
                            softAssert.assertTrue(isWeatherOverlayToggleVisible(),
                                    "Weather overlay toggle is not visible.");
                            softAssert.assertEquals(getWeatherOverlayToggleLabel(),
                                    usageTextProp.getPropValue("lblWeatherOverlayToggle"),
                                    "Weather overlay toggle label not matched.");
                            verifySolarBarChartSection(softAssert, currencyConfig, "daily");
                        } else {
                            softAssert.assertEquals(getDataNotAvailableLabel(),
                                    usageTextProp.getPropValue("lblNoGenerationDataPresent"),
                                    "No generation data label not matched."
                            );
                        }
                    }
                }
            }
            // If there is no data present for the meter linked to the account.
            else {
                softAssert.assertEquals(getDataNotAvailableLabel(),
                        usageTextProp.getPropValue("lblNoGenerationDataPresent"),
                        "No generation data label not matched."
                );
                softAssert.assertFalse(isHighestIn30DaysValueVisible(),
                        "Highest in 30 days is visible even if the data is not there.");
                softAssert.assertFalse(isProjectedGenerationValueVisible(),
                        "Projected generation tile is visible even if the data is not there.");
                ExtentLogger.logInfo("There is no data present for the linked gas meter/meters.");
            }
        }
        // If there is no solar meter linked
        else {
            ExtentLogger.logSkip("There is no Solar meter linked to the account.");
        }
    }
    
    public void verifySolarBarChartSection(SoftAssert softAssert, String currencyConfig, String interval) {
        ExtentLogger.logInfo("Verifying the solar generation bar chart section.");
        ExtentLogger.logInfo("Verifying the solar generation bar chart X axis and Y axis labels");
        // Verifying Y Axis
        softAssert.assertEquals(getYAxisLabel(),
                usageTextProp.getPropValue("lblYAxisGeneration").replace("{$CurrencyOrUnit}",
                        currencyConfig),
                "Y Axis label not matched."
        );
        // Verifying X Axis
        List<String> xAxisLabels = getXAxisLabels();
        if (interval.equalsIgnoreCase("monthly")) {
            for (String month : xAxisLabels) {
                month = month.trim();
                //softAssert.assertTrue(DateUtil.isValidDateInFormat(month, "MMM yyyy"));
            }
        } else if (interval.equalsIgnoreCase("daily")) {
            for (String month : xAxisLabels) {
                month = month.trim();
                //softAssert.assertTrue(DateUtil.isValidDateInFormat(month, "MMM d, yyyy"));
            }
        } else if (interval.equalsIgnoreCase("hourly")) {
            for (String month : xAxisLabels) {
                month = month.trim();
                //softAssert.assertTrue(DateUtil.isValidDateInFormat(month, "HH:mm aa"));
            }
        } else if (interval.equalsIgnoreCase("minutes")) {
            for (String month : xAxisLabels) {
                month = month.trim();
                //softAssert.assertTrue(DateUtil.isValidDateInFormat(month, "HH:mm aa"));
            }
        }
        ExtentLogger.logPass("The Generation Bar chart X axis and Y axis labels verified successfully.");
        // Verifying Legends labels and colors
        ExtentLogger.logInfo("Verifying the Generation Bar chart legends label and colors");
        softAssert.assertEquals(getGenerationLegendLabel(),
                usageTextProp.getPropValue("lblGenerationLegend"),
                "Generation legend label not matched."
        );
        softAssert.assertEquals(getGenerationLegendColor(),
                usageTextProp.getPropValue("generationLegendColor"),
                "Generation legend color not matched."
        );
        ExtentLogger.logPass("Generation Bar chart legends label and colors verified successfully.");
        ExtentLogger.logInfo("Verifying usage Disclaimer.");
        softAssert.assertEquals(getUsageDisclaimerLabel(),
                usageTextProp.getPropValue("lblGenerationDisclaimer"),
                "Generation disclaimer not matched."
        );
        ExtentLogger.logPass("Generation disclaimer verified successfully.");
        ExtentLogger.logInfo("Verifying efficiency programs message on usage page.");
        softAssert.assertEquals(getEfficiencyProgramLabel(),
                usageTextProp.getPropValue("lblEnergyEfficiencyProgram"),
                "efficiency programs disclaimer not matched."
        );
        softAssert.assertTrue(isEfficiencyProgramClickHereLinkVisible(),
                "Click here link is not visible.");
        ExtentLogger.logPass("Efficiency programs message on usage page verified successfully.");
        ExtentLogger.logPass("Solar generation bar chart section verified successfully.");
    }

}

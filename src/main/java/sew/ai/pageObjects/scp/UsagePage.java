package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import sew.ai.helpers.reporters.ExtentLogger;

import java.util.ArrayList;
import java.util.List;

public class UsagePage extends HomePage {

    private static final Logger log = LogManager.getLogger(UsagePage.class);

    public UsagePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[id*='HeaderUsageUI_PU']")
    private WebElement lnkPowerSideMenu;

    public boolean isPowerLinkVisible() {
        log.info("Checking the Visibility of Power link on the Usages Page." + lnkPowerSideMenu.isDisplayed());
        return isElementVisible(lnkPowerSideMenu);
    }

    public void clickPowerLink() {
        click(lnkPowerSideMenu);
        log.info("Power Link has been Successfully Clicked .");
    }

    public String getPowerLinkLabel() {
        String label = getText(lnkPowerSideMenu);
        log.info("Label of the Power link on the usages Page: " + label);
        return label;
    }

    @FindBy(css = "a[id*='HeaderUsageUI_WU']")
    private WebElement lnkWaterSideMenu;

    public boolean isWaterLinkVisible() {
        log.info("Checking the Visibility of Water link on the Usages Page." + lnkWaterSideMenu.isDisplayed());
        return isElementVisible(lnkWaterSideMenu);
    }

    public void clickWaterLink() {
        click(lnkWaterSideMenu);
        log.info("Water Link has been Successfully Clicked .");
    }

    public String getWaterLinkLabel() {
        String label = getText(lnkWaterSideMenu);
        log.info("Label of the Water link on the usages Page: " + label);
        return label;
    }

    @FindBy(css = "a[id*='HeaderUsageUI_GU']")
    private WebElement lnkGasSideMenu;

    public boolean isGasLinkVisible() {
        log.info("Checking the Visibility of Gas link on the Usages Page." + lnkGasSideMenu.isDisplayed());
        return isElementVisible(lnkGasSideMenu);
    }

    public void clicGasLink() {
        click(lnkGasSideMenu);
        log.info("Gas Link has been Successfully Clicked .");
    }

    public String getGasLinkLabel() {
        String label = getText(lnkGasSideMenu);
        log.info("Label of the Gas link on the usages Page: " + label);
        return label;
    }

    @FindBy(css = "a[id*='HeaderUsageUI_SU']")
    private WebElement lnkSolarSideMenu;

    public boolean isSolarLinkVisible() {
        log.info("Checking the Visibility of Solar link on the Usages Page." + lnkSolarSideMenu.isDisplayed());
        return isElementVisible(lnkSolarSideMenu);
    }

    public void clickSolarLink() {
        click(lnkSolarSideMenu);
        log.info("Solar Link has been Successfully Clicked .");
    }

    public String getSolarLinkLabel() {
        String label = getText(lnkSolarSideMenu);
        log.info("Label of the Solar link on the usages Page: " + label);
        return label;
    }

    @FindBy(css = "span.head_icon_flat.icon_usage")
    private WebElement iconUsage;

    @FindBy(css = "#IDBannerUsage")
    private WebElement imgUsageBanner;

    @FindBys(@FindBy(css = ".radius.chart_span_cls span"))
    private List<WebElement> lblDataNotAvailable;

    public Boolean isDataNotAvailableLabelVisible() {
        Boolean status = isElementVisibleAlt(lblDataNotAvailable);
        log.info("Data not available label visibility status {}: " + status);
        ExtentLogger.logInfo("Data not available label visibility status {}: " + status);
        return status;
    }

    public String getDataNotAvailableLabel() {
        String text = getText(lblDataNotAvailable.get(0));
        log.info("Data not available label : " + text);
        ExtentLogger.logInfo("Data not available label : " + text);
        return text;
    }

    @FindBy(css = "#averageval")
    private WebElement valAverageThisYear;

    public Boolean isAverageThisYearValueVisible() {
        Boolean status = isElementVisible(valAverageThisYear);
        log.info("Average this year label visibility status {}: " + status);
        ExtentLogger.logInfo("Average this year label visibility status {}: " + status);
        return status;
    }

    public String getAverageThisYearValue() {
        String text = getText(valAverageThisYear);
        log.info("Average this year label : " + text);
        ExtentLogger.logInfo("Average this year label : " + text);
        return text;
    }

    @FindBy(css = "#monthlyAvg #averagevaltext")
    private WebElement lblAverageThisYear;

    public Boolean isAverageThisYearLabelVisible() {
        Boolean status = isElementVisible(lblAverageThisYear);
        log.info("Average this year label visibility status {}: " + status);
        ExtentLogger.logInfo("Average this year label visibility status {}: " + status);
        return status;
    }

    public String getAverageThisYearLabel() {
        String text = getText(lblAverageThisYear);
        log.info("Average this year label : " + text);
        ExtentLogger.logInfo("Average this year label : " + text);
        return text;
    }

    @FindBy(css = "span#highestval")
    private WebElement valHighestThisYear;

    public Boolean isHighestThisYearValueVisible() {
        Boolean status = isElementVisible(valHighestThisYear);
        log.info("Highest this year label visibility status {}: " + status);
        ExtentLogger.logInfo("Highest this year label visibility status {}: " + status);
        return status;
    }

    public String getHighestThisYearValue() {
        String text = getText(valHighestThisYear);
        log.info("Highest this year label : " + text);
        ExtentLogger.logInfo("Highest this year label : " + text);
        return text;
    }

    @FindBy(css = ".average_usage_header #highestvalue")
    private WebElement valHighestIn30DaysYear;

    public Boolean isHighestIn30DaysValueVisible() {
        Boolean status = isElementVisible(valHighestIn30DaysYear);
        log.info("Highest in 30 days value visibility status {}: " + status);
        ExtentLogger.logInfo("Highest in 30 days value visibility status {}: " + status);
        return status;
    }

    public String getHighestIn30DaysValue() {
        String text = getText(valHighestIn30DaysYear);
        log.info("Highest in 30 days value : " + text);
        ExtentLogger.logInfo("Highest in 30 days value : " + text);
        return text;
    }

    @FindBy(css = "li#soFarUsage")
    private WebElement divSoFarThisMonthUsage;

    public Boolean isSoFarThisMonthTileVisible() {
        Boolean status = isElementVisible(divSoFarThisMonthUsage);
        log.info("So Far This Month tile visibility status {}: " + status);
        ExtentLogger.logInfo("So Far This Month tile visibility status {}: " + status);
        return status;
    }

    @FindBy(css = "li#soFarUsage i[globalize='ML_CompareSpending_lblYourUsage']")
    private WebElement lblSoFarThisMonthUsage;

    public String getSoFarThisLabelMonth() {
        String text = getText(lblSoFarThisMonthUsage);
        log.info("So Far This Month label : " + text);
        ExtentLogger.logInfo("So Far This Month label : " + text);
        return text;
    }

    @FindBy(css = "li#soFarUsage span[globalize='ML_PowerUSAGE_LBL_lblCurrentUsage']")
    private WebElement valSoFarThisMonthUsage;

    public String getSoFarThisValueMonth() {
        String text = getText(valSoFarThisMonthUsage);
        log.info("So Far This Month value : " + text);
        ExtentLogger.logInfo("So Far This Month value : " + text);
        return text;
    }

    @FindBy(css = "li#projectedUsage")
    private WebElement divProjectedUsage;

    public Boolean isProjectedUsageTileVisible() {
        Boolean status = isElementVisible(divProjectedUsage);
        log.info("Projected usage tile visibility status {} : " + status);
        ExtentLogger.logInfo("Projected usage tile visibility status {}: " + status);
        return status;
    }

    @FindBy(css = "li#projectedUsage i[globalize='ML_WU_SPAN_PREDICTED_THIS']")
    private WebElement lblProjectedUsage;

    public String getProjectedUsageLabelMonth() {
        String text = getText(lblProjectedUsage);
        log.info("Projected usage label : " + text);
        ExtentLogger.logInfo("Projected usage label : " + text);
        return text;
    }

    @FindBy(css = "li#projectedUsage span[globalize='ML_PowerUSAGE_LBL_lblEstimatedUsage']")
    private WebElement valProjectedUsage;

    public String getProjectedUsageValue() {
        String text = getText(valProjectedUsage);
        log.info("Projected usage value : " + text);
        ExtentLogger.logInfo("Projected usage value : " + text);
        return text;
    }

    @FindBy(css = "i[title='PROJECTED GENERATION']")
    private WebElement lblProjectedGeneration;

    public String getProjectedGenerationLabel() {
        String text = getText(lblProjectedGeneration);
        log.info("Projected generation label : " + text);
        ExtentLogger.logInfo("Projected generation label : " + text);
        return text;
    }

    @FindBy(css = "#lblEstimatedUsages")
    private WebElement valProjectedGeneration;

    public Boolean isProjectedGenerationValueVisible() {
        Boolean status = isElementVisible(valProjectedGeneration);
        log.info("Projected generation value visibility status {}: " + status);
        ExtentLogger.logInfo("Projected generation value visibility status {}: " + status);
        return status;
    }

    public String getProjectedGenerationValue() {
        String text = getText(valProjectedGeneration);
        log.info("Projected generation value : " + text);
        ExtentLogger.logInfo("Projected generation value : " + text);
        return text;
    }

    @FindBy(css = "#ModeText")
    private WebElement lblHighestThisYear;

    public Boolean isHighestThisYearLabelVisible() {
        Boolean status = isElementVisible(lblHighestThisYear);
        log.info("Highest this year label visibility status {}: " + status);
        ExtentLogger.logInfo("Highest this year label visibility status {}: " + status);
        return status;
    }

    public String getHighestThisYearLabel() {
        String text = getText(lblHighestThisYear);
        log.info("Highest this year label : " + text);
        ExtentLogger.logInfo("Highest this year label : " + text);
        return text;
    }

    @FindBy(css = "i[title='HIGHEST IN 30 DAYS']")
    private WebElement lblHighestIn30Days;

    public Boolean isHighestIn30DaysLabelVisible() {
        Boolean status = isElementVisible(lblHighestIn30Days);
        log.info("Highest in 30 days label visibility status {}: " + status);
        ExtentLogger.logInfo("Highest in 30 days label visibility status {}: " + status);
        return status;
    }

    public String getHighestIn30DaysLabel() {
        String text = getText(lblHighestIn30Days);
        log.info("Highest in 30 days label : " + text);
        ExtentLogger.logInfo("Highest in 30 days label : " + text);
        return text;
    }

    @FindBy(css = "li#liPU")
    private WebElement tabElectric;

    public Boolean isElectricTabVisible() {
        Boolean status = isElementVisible(tabElectric);
        log.info("Electric tab visibility status {}: " + status);
        ExtentLogger.logInfo("Electric tab label visibility status {}: " + status);
        return status;
    }

    public Boolean isElectricTabActive() {
        String classAttr = getAttribute(tabElectric, "class");
        log.info("Class attribute of electric tab is : " + classAttr);
        Boolean status = classAttr.equals("active");
        log.info("Status of electric tab active or not : " + status);
        return status;
    }

    @FindBy(css = "#liPU #ContentPlaceHolder1_HeaderUsageUI_PU")
    private WebElement lnkElectricTab;

    public Boolean isElectricTabLinkVisible() {
        Boolean status = isElementVisible(lnkElectricTab);
        log.info("Electric tab link visibility status {}: " + status);
        ExtentLogger.logInfo("Electric tab link label visibility status {}: " + status);
        return status;
    }

    public String getElectricTabLabel() {
        String text = getText(lnkElectricTab);
        log.info("Electric tab link label : " + text);
        ExtentLogger.logInfo("Electric tab linklabel : " + text);
        return text;
    }

    public void clickElectricTabLink() {
        click(lnkElectricTab);
        log.info("Electric tab link clicked successfully");
        ExtentLogger.logInfo("Electric tab link clicked successfully");
    }

    @FindBy(css = "li#liWU")
    private WebElement tabWater;

    public Boolean isWaterTabVisible() {
        Boolean status = isElementVisible(tabWater);
        log.info("Water tab visibility status {}: " + status);
        ExtentLogger.logInfo("Water tab label visibility status {}: " + status);
        return status;
    }

    public Boolean isWaterTabActive() {
        String classAttr = getAttribute(tabWater, "class");
        log.info("Class attribute of water tab is : " + classAttr);
        Boolean status = classAttr.equals("active");
        log.info("Status of water tab active or not : " + status);
        return status;
    }

    @FindBy(css = "#liWU #ContentPlaceHolder1_HeaderUsageUI_WU")
    private WebElement lnkWaterTab;

    public Boolean isWaterTabLinkVisible() {
        Boolean status = isElementVisible(lnkWaterTab);
        log.info("Water tab link visibility status {}: " + status);
        ExtentLogger.logInfo("Water tab link label visibility status {}: " + status);
        return status;
    }

    public String getWaterTabLabel() {
        String text = getText(lnkWaterTab);
        log.info("Water tab link label : " + text);
        ExtentLogger.logInfo("Water tab linklabel : " + text);
        return text;
    }

    public void clickWaterTabLink() {
        click(lnkWaterTab);
        log.info("Water tab link clicked successfully");
        ExtentLogger.logInfo("Water tab link clicked successfully");
    }

    @FindBy(css = "li#liGU")
    private WebElement tabGas;

    public Boolean isGasTabVisible() {
        Boolean status = isElementVisible(tabGas);
        log.info("Gas tab visibility status {}: " + status);
        ExtentLogger.logInfo("Gas tab label visibility status {}: " + status);
        return status;
    }

    public Boolean isGasTabActive() {
        String classAttr = getAttribute(tabGas, "class");
        log.info("Class attribute of gas tab is : " + classAttr);
        Boolean status = classAttr.equals("active");
        log.info("Status of gas tab active or not : " + status);
        return status;
    }

    @FindBy(css = "#liGU #ContentPlaceHolder1_HeaderUsageUI_GU")
    private WebElement lnkGasTab;

    public Boolean isGasTabLinkVisible() {
        Boolean status = isElementVisible(lnkGasTab);
        log.info("Gas tab link visibility status {}: " + status);
        ExtentLogger.logInfo("Gas tab link label visibility status {}: " + status);
        return status;
    }

    public String getGasTabLabel() {
        String text = getText(lnkGasTab);
        log.info("Gas tab link label : " + text);
        ExtentLogger.logInfo("Gas tab linklabel : " + text);
        return text;
    }

    public void clickGasTabLink() {
        click(lnkGasTab);
        log.info("Gas tab link clicked successfully");
        ExtentLogger.logInfo("Gas tab link clicked successfully");
    }

    @FindBy(css = "li#liSU")
    private WebElement tabSolar;

    public Boolean isSolarTabVisible() {
        Boolean status = isElementVisible(tabSolar);
        log.info("Solar tab visibility status {}: " + status);
        ExtentLogger.logInfo("Solar tab label visibility status {}: " + status);
        return status;
    }

    public Boolean isSolarTabActive() {
        String classAttr = getAttribute(tabSolar, "class");
        log.info("Class attribute of solar tab is : " + classAttr);
        Boolean status = classAttr.equals("active");
        log.info("Status of solar tab active or not : " + status);
        return status;
    }

    @FindBy(css = "#liSU #ContentPlaceHolder1_HeaderUsageUI_SU")
    private WebElement lnkSolarTab;

    public Boolean isSolarTabLinkVisible() {
        Boolean status = isElementVisible(lnkSolarTab);
        log.info("Solar tab link visibility status {}: " + status);
        ExtentLogger.logInfo("Solar tab link label visibility status {}: " + status);
        return status;
    }

    public String getSolarTabLabel() {
        String text = getText(lnkSolarTab);
        log.info("Solar tab link label : " + text);
        ExtentLogger.logInfo("Solar tab linklabel : " + text);
        return text;
    }

    public void clickSolarTabLink() {
        click(lnkSolarTab);
        log.info("Solar tab link clicked successfully");
        ExtentLogger.logInfo("Solar tab link clicked successfully");
    }

    @FindBy(css = "span#MeterNumlabel")
    private WebElement lblMeterNumber;

    public Boolean isMeterNumberLabelVisible() {
        Boolean status = isElementVisible(lblMeterNumber);
        log.info("Meter Number label visibility status {}: " + status);
        ExtentLogger.logInfo("Meter Number label visibility status {}: " + status);
        return status;
    }

    public String getMeterNumberLabel() {
        String text = getText(lblMeterNumber);
        log.info("Meter Number label : " + text);
        ExtentLogger.logInfo("Meter Number label : " + text);
        return text;
    }

    @FindBy(css = "select#ddlMultiMeter")
    private WebElement lstMeterNumber;

    public Boolean isMeterDropDownVisible() {
        Boolean status = isElementVisible(lstMeterNumber);
        log.info("Meter DropDown visibility status {}: " + status);
        ExtentLogger.logInfo("Meter DropDown visibility status {}: " + status);
        return status;
    }

    public void selectMeterByVisibleText(String meterNumber) {
        selectByVisibleText(lstMeterNumber, meterNumber);
        log.info("Meter : " + meterNumber + " is selected successfully in the meter drop-down.");
    }

    @FindBy(css = "#ddlMultiMeter option:first-child")
    private WebElement lblMeterDropdownFirstOption;

    public Boolean isMeterDropDownOptionVisible() {
        Boolean status = isElementVisible(lblMeterDropdownFirstOption);
        log.info("Meter DropDown Option label visibility status {}: " + status);
        ExtentLogger.logInfo("Meter DropDown Option label visibility status {}: " + status);
        return status;
    }

    public String getMeterDropDownOptionLabel() {
        String text = getText(lblMeterDropdownFirstOption);
        log.info("Meter DropDown Option label : " + text);
        ExtentLogger.logInfo("Meter DropDown Option label : " + text);
        return text;
    }

    @FindBy(css = "li#ContentPlaceHolder1_HeaderUsageUI_SEW_UNIQUE_ID_ChartUsageUI_li_monthly a")
    private WebElement lnkMonthlyInterval;

    public Boolean isMonthlyIntervalLinkVisible() {
        Boolean status = isElementVisible(lnkMonthlyInterval);
        log.info("Monthly interval link visibility status {}: " + status);
        return status;
    }

    public Boolean isMonthlyIntervalLinkActive() {
        String classAttr = getAttribute(lnkMonthlyInterval, "class");
        log.info("Class attribute of monthly interval link is : " + classAttr);
        Boolean status = classAttr.equals("active");
        log.info("Status of monthly interval active or not : " + status);
        return status;
    }

    public String getMonthlyIntervalLinkLabel() {
        String text = getText(lnkMonthlyInterval);
        log.info("Monthly interval label : " + text);
        return text;
    }

    public void clickMonthlyIntervalLink() {
        click(lnkMonthlyInterval);
        log.info("Monthly interval tab link clicked successfully");
    }

    @FindBy(css = "li#ContentPlaceHolder1_HeaderUsageUI_SEW_UNIQUE_ID_ChartUsageUI_li_daily a")
    private WebElement lnkDailyInterval;

    public Boolean isDailyIntervalLinkVisible() {
        Boolean status = isElementVisible(lnkDailyInterval);
        log.info("Daily interval link visibility status {}: " + status);
        return status;
    }

    public Boolean isDailyIntervalLinkActive() {
        String classAttr = getAttribute(lnkDailyInterval, "class");
        log.info("Class attribute of Daily interval link is : " + classAttr);
        Boolean status = classAttr.equals("active");
        log.info("Status of Daily interval active or not : " + status);
        return status;
    }

    public String getDailyIntervalLinkLabel() {
        String text = getText(lnkDailyInterval);
        log.info("Daily interval label : " + text);
        return text;
    }

    public void clickDailyIntervalLink() {
        click(lnkDailyInterval);
        log.info("Daily interval tab link clicked successfully");
    }

    @FindBy(css = "li#ContentPlaceHolder1_HeaderUsageUI_SEW_UNIQUE_ID_ChartUsageUI_li_hourly a")
    private WebElement lnkHourlyInterval;

    public Boolean isHourlyIntervalLinkVisible() {
        Boolean status = isElementVisible(lnkHourlyInterval);
        log.info("Hourly interval link visibility status {}: " + status);
        return status;
    }

    public Boolean isHourlyIntervalLinkActive() {
        String classAttr = getAttribute(lnkHourlyInterval, "class");
        log.info("Class attribute of Hourly interval link is : " + classAttr);
        Boolean status = classAttr.equals("active");
        log.info("Status of Hourly interval active or not : " + status);
        return status;
    }

    public String getHourlyIntervalLinkLabel() {
        String text = getText(lnkHourlyInterval);
        log.info("Hourly interval label : " + text);
        return text;
    }

    public void clickHourlyIntervalLink() {
        click(lnkHourlyInterval);
        log.info("Hourly interval tab link clicked successfully");
    }

    @FindBy(css = "li#ContentPlaceHolder1_HeaderUsageUI_SEW_UNIQUE_ID_ChartUsageUI_li_15min a")
    private WebElement lnkMinutesInterval;

    public Boolean isMinutesIntervalLinkVisible() {
        Boolean status = isElementVisible(lnkMinutesInterval);
        log.info("Minutes interval link visibility status {}: " + status);
        return status;
    }

    public Boolean isMinutesIntervalLinkActive() {
        String classAttr = getAttribute(lnkMinutesInterval, "class");
        log.info("Class attribute of Minutes interval link is : " + classAttr);
        Boolean status = classAttr.equals("active");
        log.info("Status of Minutes interval active or not : " + status);
        return status;
    }

    public String getMinutesIntervalLinkLabel() {
        String text = getText(lnkMinutesInterval);
        log.info("Minutes interval label : " + text);
        return text;
    }

    public void clickMinutesIntervalLink() {
        click(lnkMinutesInterval);
        log.info("Minutes interval tab link clicked successfully");
    }

    @FindBy(css = "li#ContentPlaceHolder1_HeaderUsageUI_SEW_UNIQUE_ID_ChartUsageUI_li_kwh #typeK")
    private WebElement lnkMeasuringUnit;

    public Boolean isMeasuringUnitLinkVisible() {
        Boolean status = isElementVisible(lnkMeasuringUnit);
        log.info("Measuring unit link visibility status {}: " + status);
        return status;
    }

    public Boolean isMeasuringUnitLinkActive() {
        String classAttr = getAttribute(lnkMeasuringUnit, "class");
        log.info("Class attribute of Measuring unit interval link is : " + classAttr);
        Boolean status = classAttr.equals("active");
        log.info("Status of Measuring unit interval active or not : " + status);
        return status;
    }

    public String getMeasuringUnitLinkLabel() {
        String text = getText(lnkMeasuringUnit);
        log.info("Measuring unit label : " + text);
        return text;
    }

    public void scrollMeasuringUnitIntoView() {
        scrollToElement(lnkMeasuringUnit);
        log.info("Scrolled currency unit into the view.");
    }

    public void clickMeasuringUnitLink() {
        click(lnkMeasuringUnit);
        log.info("Measuring unit tab link clicked successfully");
    }

    @FindBy(css = "li#ContentPlaceHolder1_HeaderUsageUI_SEW_UNIQUE_ID_ChartUsageUI_li_dollar #typeD")
    private WebElement lnkCurrencyUnit;

    public Boolean isCurrencyUnitLinkVisible() {
        Boolean status = isElementVisible(lnkCurrencyUnit);
        log.info("Currency unit link visibility status {}: " + status);
        return status;
    }

    public Boolean isCurrencyUnitLinkActive() {
        String classAttr = getAttribute(lnkCurrencyUnit, "class");
        log.info("Class attribute of Currency unit interval link is : " + classAttr);
        Boolean status = classAttr.equals("active");
        log.info("Status of Currency unit interval active or not : " + status);
        return status;
    }

    public void scrollCurrencyUnitIntoView() {
        scrollToElement(lnkCurrencyUnit);
        log.info("Scrolled currency unit into the view.");
    }

    public String getCurrencyUnitLinkLabel() {
        String text = getText(lnkCurrencyUnit);
        log.info("Currency unit label : " + text);
        return text;
    }

    public void clickCurrencyUnitLink() {
        click(lnkCurrencyUnit);
        log.info("Currency unit tab link clicked successfully");
    }

    @FindBy(css = "li#ContentPlaceHolder1_HeaderUsageUI_SEW_UNIQUE_ID_ChartUsageUI_li_Gallon #typeG")
    private WebElement lnkGallonUnit;

    public Boolean isGallonUnitLinkVisible() {
        Boolean status = isElementVisible(lnkGallonUnit);
        log.info("Gallon unit link visibility status {}: " + status);
        return status;
    }

    public Boolean isGallonUnitLinkActive() {
        String classAttr = getAttribute(lnkGallonUnit, "class");
        log.info("Class attribute of Gallon unit interval link is : " + classAttr);
        Boolean status = classAttr.equals("active");
        log.info("Status of Gallon unit interval active or not : " + status);
        return status;
    }

    public String getGallonUnitLinkLabel() {
        String text = getText(lnkGallonUnit);
        log.info("Gallon unit label : " + text);
        return text;
    }

    public void clickGallonUnitLink() {
        click(lnkGallonUnit);
        log.info("Gallon unit tab link clicked successfully");
    }

    @FindBy(css = "label#lblCharttitle")
    private WebElement lblCalendar;

    public Boolean isCalendarLabelVisible() {
        Boolean status = isElementVisible(lblCalendar);
        log.info("Calendar label visibility status {}: " + status);
        return status;
    }

    public String getCalendarLabel() {
        String text = getText(lblCalendar);
        log.info("Calendar label is : " + text);
        return text;
    }

    public void clickCalendarLabel() {
        click(lblCalendar);
        log.info("Calendar label clicked successfully.");
    }

    @FindBy(css = "div.calendar_wrp a[aria-labelledby='datepicker-bn-open-label-lblCharttitle']")
    private WebElement lnkCalendar;

    public Boolean isCalendarLinkVisible() {
        Boolean status = isElementVisible(lnkCalendar);
        log.info("Calendar Link visibility status {}: " + status);
        return status;
    }

    public void clickCalendarLink() {
        click(lnkCalendar);
        log.info("Calendar link clicked successfully.");
    }

    @FindBy(css = "div[aria-labelledby='datepicker-bn-prev-label-lblCharttitle']")
    private WebElement btnCalendarPrev;

    public void clickCalendarPrevButton() {
        click(btnCalendarPrev);
        log.info("Calendar previous button clicked successfully.");
    }

    @FindBy(css = "div[aria-labelledby='datepicker-bn-next-label-lblCharttitle']")
    private WebElement btnCalendarNext;

    public void clickCalendarNextButton() {
        click(btnCalendarNext);
        log.info("Calendar next button clicked successfully.");
    }

    @FindBy(css = ".datepicker-button.datepicker-month")
    private WebElement monthOnCalendar;

    public String getMonthOnCalendarLabel() {
        String text = getTextWithoutCheckingVisibility(monthOnCalendar);
        log.info("Month on calendar label is : " + text);
        return text;
    }

    @FindBy(css = ".highcharts-axis.highcharts-yaxis text tspan")
    private WebElement yAxisLabel;

    public Boolean isYAxisLabelVisible() {
        Boolean status = isElementVisible(yAxisLabel);
        log.info("Y axis label visibility status {}: " + status);
        return status;
    }

    public String getYAxisLabel() {
        String text = getTextWithoutCheckingVisibility(yAxisLabel);
        log.info("Y axis label is : " + text);
        return text;
    }

    @FindBys(@FindBy(css = ".highcharts-axis-labels.highcharts-xaxis-labels text tspan"))
    private List<WebElement> xAxisLabelsElements;

    public List<WebElement> getXAxisLabelElements() {
        return xAxisLabelsElements;
    }

    public List<String> getXAxisLabels() {
        List<String> xAxisLabels = new ArrayList<>();
        for (WebElement element : xAxisLabelsElements) {
            xAxisLabels.add(getText(element));
        }
        return xAxisLabels;
    }

    @FindBy(css = ".graphlegend_Databox span#LowUsage")
    private WebElement lblElectricLowUsage;

    public Boolean isElectricLowUsageLabelVisible() {
        Boolean status = isElementVisible(lblElectricLowUsage);
        log.info("Electric Low usage label visibility status {}: " + status);
        ExtentLogger.logInfo("Low usage label visibility status {}: " + status);
        return status;
    }

    public String getElectricLowUsageLabel() {
        String text = getText(lblElectricLowUsage);
        log.info("Electric Low usage label : " + text);
        ExtentLogger.logInfo("Electric Low usage label : " + text);
        return text;
    }

    @FindBy(css = "span[id='LowUsage'][style='display: block;']")
    private WebElement lblWaterLowUsage;

    public Boolean isWaterLowUsageLabelVisible() {
        Boolean status = isElementVisible(lblWaterLowUsage);
        log.info("Water Low usage label visibility status {}: " + status);
        ExtentLogger.logInfo("Water Low usage label visibility status {}: " + status);
        return status;
    }

    public String getWaterLowUsageLabel() {
        String text = getText(lblWaterLowUsage);
        log.info("Water Low usage label : " + text);
        ExtentLogger.logInfo("Water Low usage label : " + text);
        return text;
    }

    @FindBy(css = ".GraphLegend_low.bulletcommon")
    private WebElement lowUsageElectricLegColor;

    public String getLowUsageLegendColor() {
        String color = getAttribute(lowUsageElectricLegColor, "style");
        log.info("Electric low usage style attribute value : " + color);
        String startStr = "background-color:";
        String endStr = ";";
        color = color.substring(color.indexOf(startStr) + startStr.length(), color.indexOf(";")).trim();
        log.info("Electric low usage final color value : " + color);
        return color;
    }

    @FindBy(css = ".GraphLegend_Usage.bulletcommon")
    private WebElement lowUsageWaterLegColor;

    public String getLowUsageWaterLegColor() {
        String color = getAttribute(lowUsageWaterLegColor, "style");
        log.info("Water low usage style attribute value : " + color);
        String startStr = "background-color:";
        String endStr = ";";
        color = color.substring(color.indexOf(startStr) + startStr.length(), color.indexOf(";")).trim();
        log.info("Water low usage final color value : " + color);
        return color;
    }

    @FindBy(css = ".GraphLegend_Avg.bulletcommon")
    private WebElement avgUsageLegendColor;

    public String getAverageUsageLegendColor() {
        String color = getAttribute(avgUsageLegendColor, "style");
        log.info("average usage style attribute value : " + color);
        String startStr = "background-color:";
        String endStr = ";";
        color = color.substring(color.indexOf(startStr) + startStr.length(), color.indexOf(";")).trim();
        log.info("average usage final color value : " + color);
        return color;
    }

    @FindBy(css = ".graphlegend_Databox span#AvgUsage")
    private WebElement lblAvgUsage;

    public Boolean isAverageUsageLabelVisible() {
        Boolean status = isElementVisible(lblAvgUsage);
        log.info("Average usage label visibility status {}: " + status);
        ExtentLogger.logInfo("Average usage label visibility status {}: " + status);
        return status;
    }

    public String getAverageUsageLabel() {
        String text = getText(lblAvgUsage);
        log.info("Average usage label : " + text);
        ExtentLogger.logInfo("Average usage label : " + text);
        return text;
    }

    @FindBy(css = ".GraphLegend_High.bulletcommon")
    private WebElement highUsageLegendColor;

    public String getHighUsageLegendColor() {
        String color = getAttribute(highUsageLegendColor, "style");
        log.info("high usage style attribute value : " + color);
        String startStr = "background-color:";
        String endStr = ";";
        color = color.substring(color.indexOf(startStr) + startStr.length(), color.indexOf(";")).trim();
        log.info("high usage final color value : " + color);
        return color;
    }

    @FindBy(css = ".graphlegend_Databox span#HighUsage")
    private WebElement lblHighUsage;

    public Boolean isHighUsageLabelVisible() {
        Boolean status = isElementVisible(lblHighUsage);
        log.info("High usage label visibility status {}: " + status);
        ExtentLogger.logInfo("High usage label visibility status {}: " + status);
        return status;
    }

    public String getHighUsageLabel() {
        String text = getText(lblHighUsage);
        log.info("High usage label : " + text);
        ExtentLogger.logInfo("High usage label : " + text);
        return text;
    }

    @FindBy(css = "div#UsagesID .GraphLegend_WaterAlloc")
    private WebElement waterAllocationLegendColor;

    public String getWaterAllocationLegendColor() {
        String color = getAttribute(waterAllocationLegendColor, "style");
        log.info("Water allocation style attribute value : " + color);
        String startStr = "background-color:";
        String endStr = ";";
        color = color.substring(color.indexOf(startStr) + startStr.length(), color.indexOf(";")).trim();
        log.info("Water allocation final color value : " + color);
        return color;
    }

    @FindBy(css = ".graphlegend_Databox span[title='Water Allocation']")
    private WebElement lblWaterAllocation;

    public Boolean isWaterAllocationLabelVisible() {
        Boolean status = isElementVisible(lblWaterAllocation);
        log.info("Water allocation label visibility status {}: " + status);
        ExtentLogger.logInfo("Water allocation label visibility status {}: " + status);
        return status;
    }

    public String getWaterAllocationLabel() {
        String text = getText(lblWaterAllocation);
        log.info("Water allocation label : " + text);
        ExtentLogger.logInfo("Water allocation label : " + text);
        return text;
    }

    @FindBy(css = ".GraphLegend_solar.bulletcommon")
    private WebElement generationLegendColor;

    public String getGenerationLegendColor() {
        String color = getAttribute(generationLegendColor, "style");
        log.info("Generation style attribute value : " + color);
        String startStr = "background-color:";
        String endStr = ";";
        color = color.substring(color.indexOf(startStr) + startStr.length(), color.indexOf(";")).trim();
        log.info("Generation final color value : " + color);
        return color;
    }

    @FindBy(css = ".GraphLegend_data_solar.lgnd_common")
    private WebElement lblGenerationLegend;

    public Boolean isGenerationLegendLabelVisible() {
        Boolean status = isElementVisible(lblGenerationLegend);
        log.info("Generation legend label visibility status {}: " + status);
        ExtentLogger.logInfo("Generation legend label visibility status {}: " + status);
        return status;
    }

    public String getGenerationLegendLabel() {
        String text = getText(lblGenerationLegend);
        log.info("Generation legend label : " + text);
        ExtentLogger.logInfo("Generation legend label : " + text);
        return text;
    }

    @FindBy(css = ".add-card.rateid a[title='Click to view Usage rates']")
    private WebElement lnkRates;

    public String getRatesLinkLabel() {
        String label = getText(lnkRates);
        log.info("Rates link label is : " + label);
        return label;
    }

    public void clickRatesLink() {
        click(lnkRates);
        log.info("Rates link clicked successfully.");
    }

    @FindBy(css = ".add-card.rateid i[class='material-icons']")
    private WebElement iconRates;

    public String getRatesIconLabel() {
        String label = getText(iconRates);
        log.info("Rates icon label is : " + label);
        return label;
    }

    @FindBy(css = "#usagechartdiv span#lblRateText")
    private WebElement lblRatesPopUpHeader;

    public String getRatesPopUpHeaderLabel() {
        String label = getText(lblRatesPopUpHeader);
        log.info("Rates popup header label is : " + label);
        return label;
    }

    @FindBy(css = "a.fancybox - item.fancybox - close[title = 'Close']")
    private WebElement btnCloseRatesPopUp;

    @FindBy(css = ".currency.highcharts-yaxis tspan")
    private WebElement lblRatesInDollarRatesPopUp;

    @FindBy(css = "a#ExportTypefile")
    private WebElement lnkExportUsage;

    public String getExportUsageLabel() {
        String label = getText(lnkExportUsage);
        log.info("Export usages link label is : " + label);
        return label;
    }

    public void clickExportUsageLink() {
        click(lnkExportUsage);
        log.info("Export usages link clicked successfully.");
    }

    @FindBy(css = "#lnkExporttoExcel")
    private WebElement lnkExportToExcel;

    public String getExportToExcelLinkLabel() {
        String label = getText(lnkExportToExcel);
        log.info("Export usages link label is : " + label);
        return label;
    }

    public void clickExportToExcelLink() {
        click(lnkExportToExcel);
        log.info("Export usages link clicked successfully.");
    }

    @FindBy(css = "#lnkExporttoCSV")
    private WebElement lnkExportToCSV;

    public String getExportToCSVLinkLabel() {
        String label = getText(lnkExportToCSV);
        log.info("Export to CSV link label is : " + label);
        return label;
    }

    public void clickExportToCSVLink() {
        click(lnkExportToCSV);
        log.info("Export to CSV link clicked successfully.");
    }

    @FindBy(css = "#exportModal #btndclosepopup")
    private WebElement lnkCloseExportUsagePop;

    public String getCloseExportUsageLabel() {
        String label = getText(lnkCloseExportUsagePop);
        log.info("Close export usage link label is : " + label);
        return label;
    }

    public void clickCloseExportUsageLink() {
        click(lnkCloseExportUsagePop);
        log.info("Close export usage link clicked successfully.");
    }

    @FindBy(css = "a#usagePopup")
    private WebElement lnkSetUsageAlerts;

    public String getSetUsageAlertsLabel() {
        String label = getText(lnkSetUsageAlerts);
        log.info("Set usage alerts link label is : " + label);
        return label;
    }

    public void clickSetUsageAlertsLink() {
        click(lnkSetUsageAlerts);
        log.info("Set usage alerts link clicked successfully.");
    }

    @FindBy(css = "a#lnkGreenBtn")
    private WebElement lnkGreenButton;

    public void clickGreenButtonLink() {
        click(lnkGreenButton);
        log.info("Green button link clicked successfully.");
    }

    @FindBy(css = "a#lnkGreenBtn span")
    private WebElement lblGreenButton;

    public String getGreenButtonLabel() {
        String label = getText(lblGreenButton);
        log.info("Green button label is : " + label);
        return label;
    }

    @FindBy(css = "div#divconfigusage")
    private WebElement divNetUsageToggle;

    public Boolean isNetUsageToggleVisible() {
        Boolean status = isElementVisible(divNetUsageToggle);
        log.info("Net usage toggle visibility status {}: " + status);
        return status;
    }

    @FindBy(css = "div#divconfigusage span[title='Net Usage']")
    private WebElement lblNetUsageToggle;

    public String getNetUsageToggleLabel() {
        String label = getText(lblNetUsageToggle);
        log.info("Net usage toggle label is : " + label);
        return label;
    }

    @FindBy(css = "input#imgUsage ")
    private WebElement txtNetUsageToggle;

    public Boolean isNetUsageToggleSelected() {
        Boolean status = isElementSelected(txtNetUsageToggle);
        log.info("Net usage toggle selected status : " + status);
        return status;
    }

    @FindBy(css = "#divconfigusage label[title='Net Usage']")
    private WebElement btnNetUsageToggle;

    public Boolean isNetUsageToggleButtonVisible() {
        Boolean status = isElementVisible(btnNetUsageToggle);
        log.info("Net usage toggle button visibility status {}: " + status);
        return status;
    }

    @FindBy(css = "div#showWeatherOverlay")
    private WebElement divWeatherOverlayToggle;

    public Boolean isWeatherOverlayToggleVisible() {
        Boolean status = isElementVisible(divWeatherOverlayToggle);
        log.info("Net usage toggle visibility status {}: " + status);
        return status;
    }

    @FindBy(css = "div#showWeatherOverlay span[title='Weather Overlay']")
    private WebElement lblWeatherOverlayToggle;

    public String getWeatherOverlayToggleLabel() {
        String label = getText(lblWeatherOverlayToggle);
        log.info("Net usage toggle label is : " + label);
        return label;
    }

    @FindBy(css = "input#imgIson")
    private WebElement txtWeatherOverlayToggle;

    public Boolean isWeatherOverlayToggleSelected() {
        Boolean status = isElementSelected(txtWeatherOverlayToggle);
        log.info("Net usage toggle selected status : " + status);
        return status;
    }

    @FindBy(css = "div#showWeatherOverlay label[title='Weather Overlay']")
    private WebElement btnWeatherOverlayToggle;

    public Boolean isWeatherOverlayToggleButtonVisible() {
        Boolean status = isElementVisible(btnWeatherOverlayToggle);
        log.info("Net usage toggle button visibility status {}: " + status);
        return status;
    }

    @FindBy(css = "#disclaimer span#DisclaimerText")
    private WebElement lblUsageDisclaimer;

    public String getUsageDisclaimerLabel() {
        String label = getText(lblUsageDisclaimer);
        log.info("Usage disclaimer label is : " + label);
        return label;
    }

    @FindBy(css = "span#spnkLinkToEfficiency")
    private WebElement lblEfficiencyProgram;

    public String getEfficiencyProgramLabel() {
        String label = getText(lblEfficiencyProgram);
        log.info("Efficiency Program label is : " + label);
        return label;
    }

    @FindBy(css = "a#hlnkLinkToEfficiency")
    private WebElement lnkEfficiencyProgramClickHere;

    public Boolean isEfficiencyProgramClickHereLinkVisible() {
        Boolean status = isElementVisible(lnkEfficiencyProgramClickHere);
        log.info("Net usage toggle visibility status {}: " + status);
        return status;
    }

    public void clickEfficiencyProgramClickHereLink() {
        click(lnkEfficiencyProgramClickHere);
        log.info("Efficiency Program ClickHere link clicked successfully.");
    }

    @FindBy(css = "#lblCurrentUsage")
    private WebElement lblCurrentUsage;

    @FindBy(css = "#lblEstimatedUsage")
    private WebElement lblEstimatedUsage;

    @FindBy(css = "#lblEstimatedUsage")
    private WebElement lblValProjectedUsage;

    @FindBy(css = "#lblMaxDemand")
    private WebElement lblValMaxDemand;

    @FindBy(css = "#MaxDemandVal")
    private WebElement lblMaxDemand;

    @FindBy(css = "#LoadFactorVal")
    private WebElement lblLoadFactor;

    @FindBy(css = "#lblLoadFactor")
    private WebElement lblValLoadFactor;

    @FindBy(css = ".highcharts-data-labels.highcharts-series-1.highcharts-column-series.highcharts-tracker texttspan:not(.highcharts-text-outline)")
    private WebElement lblUnitsGeneratedInGraph;

    @FindBy(css = ".highcharts-series.highcharts-series-1.highcharts-column-series.highcharts-tracker path")
    private WebElement lblUnitsGeneratedInGraphBar;

    @FindBy(css = ".compare_graph g.highcharts-data-labels.highcharts-tracker >text tspan.highcharts-text-outline")
    private WebElement lblUnitsConsumedInGraph;

    @FindBy(css = ".compare_graph.highcharts-data-label.highcharts-data-label-color-0.highcharts-tracker tspan.highcharts-text-outline")
    private WebElement lblUsageConsumedInGraph;

    @FindBy(xpath = "(//*[@class='highcharts-axis-title'])[2]")
    private WebElement lblMaxDemandInKw;

    @FindBy(css = ".highcharts - markers.highcharts - series - 2.highcharts-line-series.highcharts-tracker path.highcharts-point")
    private WebElement imgMaxDemandHighChart;

    @FindBy(css = "div.highcharts - color - undefineddiv div")
    private WebElement lblMaxDemandHighChart;

    @FindBy(css = ".highcharts-tooltip div.tooltipBody ul li")
    private WebElement lblToolTipConsumedInGraph;

    @FindBy(css = ".highcharts-tooltip.tooltipHead")
    private WebElement lblToolTipConsumedInGraphHead;

    @FindBy(css = ".highcharts-tooltip .tooltipBody ul li")
    private WebElement lblToolTipConsumedInGraphBody;

    public String getHistogramBarToolTip() {
        String label = getText(lblToolTipConsumedInGraphBody);
        log.info("Histogram bar tool tip is : " + label);
        return label;
    }

    @FindBy(css = "div.highcharts-label span div div")
    private WebElement lblToolTipWeatherData;

    public String getWeatherDataToolTip() {
        String label = getText(lblToolTipWeatherData);
        log.info("Weather data tool tip is : " + label);
        return label;
    }

    @FindBy(css = ".compare_graph g.highcharts-series.highcharts-tracker >rect")
    private WebElement lblHistogramGraph;

    @FindBys(@FindBy(css = ".highcharts-series.highcharts-series-0.highcharts-column-series.highcharts-tracker path"))
    private List<WebElement> lblHistogramBarUnits;

    public List<WebElement> getHistogramBarUnitLabelElements() {
        log.info("Found list of elements in size : " + lblHistogramBarUnits.size());
        return lblHistogramBarUnits;
    }

    @FindBys(@FindBy(css = ".compare_graph g.highcharts-axis-labels.highcharts-xaxis-labels >text >tspan"))
    private List<WebElement> lblHistogramBarMonths;

    public List<WebElement> getHistogramBarMonthLabelElements() {
        log.info("Found list of elements in size : " + lblHistogramBarMonths.size());
        return lblHistogramBarMonths;
    }

    public List<String> getHistogramMonthsLabel() {
        List<String> histogramMonths = new ArrayList<>();
        for (WebElement element : lblHistogramBarMonths) {
            histogramMonths.add(getText(element));
        }
        return histogramMonths;
    }

    @FindBy(css = ".compare_graph g.highcharts-axis-labels.highcharts-xaxis-labels >texton")
    private WebElement lblBiMonthNamesHistogram;

    @FindBy(css = ".compare_graph g.highcharts-axis-labels.highcharts-xaxis-labels >text")
    private WebElement lblHourNamesHistogram;

    @FindBy(css = ".compare_graph g.highcharts-axis-labels.highcharts-xaxis-labels >text")
    private WebElement lblFifteenMinuteNamesHistogram;

    @FindBy(css = ".compare_graph g.highcharts-axis-labels.highcharts-xaxis-labels >text")
    private WebElement lblDateNamesHistogram;

    @FindBy(css = ".compare_graph g.highcharts-axis-labels.highcharts-xaxis-labels >text")
    private WebElement lblSeasonNamesHistogram;

    @FindBy(css = ".compare_graph g.highcharts-axis-labels.highcharts-xaxis-labels >text")
    private WebElement lblSeasonMonthNamesHistogram;

    @FindBy(css = ".compare_graph g.highcharts-data-labels.highcharts-tracker >text")
    private WebElement lblHistogramGraphSolarTextValue;

    @FindBy(css = ".highcharts-axis-title")
    private WebElement lblUnitsConsumedInYaxis;

    @FindBy(css = "a[title = 'Click to view Daily usage']")
    private WebElement btnDaily;

    @FindBy(css = "a[title='Click to view Hourly usage']")
    private WebElement btnHourly;

    @FindBy(css = "a[globalize='ML_Usage_Btn_Monthly']")
    private WebElement btnMonthly;

    @FindBy(css = "a[globalize='ML_Usage_Btn_Seasonal']")
    private WebElement btnSeasonal;

    @FindBy(css = ".li_15min a")
    private WebElement btn15Min;

    @FindBy(css = "[id='lblCharttitle']")
    private WebElement lblHourlyUsage;

    @FindBy(css = "span#lblCharttitle")
    private WebElement lblPeriodFromTo;

    @FindBy(css = "[id='lnkGreenBtn']")
    private WebElement lnkGreenBtn;

    @FindBy(xpath = "//*[@id='averagevaltext'][@title='DAILY AVERAGE']")
    private WebElement lblDailyAverage;

    @FindBy(xpath = " //*[@id='averagevaltext'][@title='HOURLY AVERAGE']")
    private WebElement lblHourlyAverage;

    @FindBy(xpath = "//*[@id='averagevaltext'][@title='MONTHLY AVERAGE']")
    private WebElement lblMonthlyAverage;

    @FindBy(xpath = " //*[@id='averagevaltext'][@title='SEASONAL AVERAGE']")
    private WebElement lblSeasonalAverage;

    @FindBy(css = "#averageval")
    private WebElement lblValDailyAverage;

    @FindBy(css = "i#ModeText")
    private WebElement lblHighestThisMonth;

    @FindBy(css = " #highestval")
    private WebElement lblValHighestThisMonth;

    @FindBy(css = " #soFarUsage [globalize='ML_CompareSpending_lblYourUsage']")
    private WebElement lblSoFarThisMonth;

    @FindBy(css = "span#lblCurrentUsage")
    private WebElement lblValSoFarThisMonth;

    @FindBy(css = ".wthr_usages.toggle_lbl_class")
    private WebElement btnWeatherOverlay;

    @FindBy(css = ".wthr_usages.divconfigusage_usage.toggle_lbl_class")
    private WebElement btnNetUsage;

    @FindBy(css = "#averageval")
    private WebElement lblValHourlyAverage;

    @FindBy(css = "#ModeText")
    private WebElement lblHighestThisDay;

    @FindBy(css = "#highestval")
    private WebElement lblValHighestThisDay;

    @FindBy(css = "[id='lblCharttitle']")
    private WebElement lblHourlyUsageForDate;

    @FindBy(css = "#averagevaltext")
    private WebElement lbl15MinAverage;

    @FindBy(css = "#averageval")
    private WebElement lblVal15MinAverage;

    @FindBy(css = "[id='lblCharttitle']")
    private WebElement lbl15MinUsageForDate;

    @FindBy(xpath = "//*[@id='averagevaltext'][@title='SEASONAL AVERAGE']")
    private WebElement lblSeasonAverage;

    @FindBy(css = "#averageval")
    private WebElement lblValSeasonAverage;

    @FindBy(css = "i#ModeText")
    private WebElement lblHighestThisPeriod;

    @FindBy(css = "#highestval")
    private WebElement lblValHighestThisPeriod;

    @FindBy(css = "h4.modal - title")
    private WebElement lblPopUpGreenButton;

    @FindBy(css = "[id='lnkGreenBtn-popup']")
    private WebElement btnDownloadDataGBPopUp;

    @FindBy(css = "[id='btnFromCalender']")
    private WebElement calFromGreenButtonPopUp;

    @FindBy(css = "[id='btnToCalender']")
    private WebElement calToGreenButtonPopUp;

    @FindBy(css = ".pop_para_footer")
    private WebElement lblDescriptionGreenButtonPopUp;

    @FindBy(css = "#greenPopup button.closepopup")
    private WebElement btnCloseGreenPop;

    @FindBy(css = "#greenPopup.choose_per_box")
    private WebElement lblPleaseChooseGreenPopUp;

    @FindBy(css = "#greenPopup #ddlServiceType")
    private WebElement dropDownServiceTypeGreenPopUp;

    @FindBy(css = "#greenPopup button.multiselect")
    private WebElement dropDownMeterNumberGreenPopUp;

    @FindBy(css = "#averagevaltext")
    private WebElement lblMonthlyAverageWater;

    @FindBy(css = ".GraphLegend_data_solar.lgnd_common")
    private WebElement lblSolarGeneration;

    @FindBy(css = "#averagevalue")
    private WebElement lblVal30DaysAverageSolar;

    @FindBy(css = "#highestvalue")
    private WebElement lblValHighestIn30DaysSolar;

    @FindBy(css = "#lblCurrentUsages")
    private WebElement lblValSoFarThisMonthSolar;

    @FindBy(css = "[id='lblEstimatedUsages']")
    private WebElement lblValProjectedGenerationSolar;

    @FindBy(css = "i[globalize='ML_Solar_SPAN_AVERAGE']")
    private WebElement lbl30DaysAverageSolar;

    @FindBy(css = "i[globalize='ML_Solar_SPAN_HIGHEST_THIS']")
    private WebElement lblHighestIn30DaysSolar;

    @FindBy(css = "#WaterDiv lii[globalize='ML_CompareSpending_lblYourUsage']")
    private WebElement lblSoFarThisMonthSolar;
    @FindBy(css = "#WaterDiv lii[globalize='ML_Solar_SPAN_PREDICTED_THIS']")
    private WebElement lblProjectedGenerationSolar;
    @FindBy(css = ".highcharts-label.highcharts-tooltip.highcharts-color-undefined span")
    private WebElement lblWeatherOverLayTooltipOnHistogram;

    @FindBy(css = "#btnAM")
    private WebElement lnkAmRatesPopUp;
    @FindBy(css = "#btnPM")
    private WebElement lnkPmRatesPopUp;
    @FindBy(css = "#usageRateDiv.highcharts-xaxis-")
    private WebElement lblTiersRatesPopUp;
    @FindBy(css = "#usageRateDiv.highcharts-tracker rect")
    private WebElement texthistogramTiersRatesPopUp;
    @FindBy(css = ".currency.highcharts-xaxis-")
    private WebElement lblTimeRatesPopUp;
    @FindBy(css = ".currency.highcharts-tracker rect")
    private WebElement texttspanhistogramRatesPopUp;
    @FindBy(css = ".cal_img_btn")
    private WebElement btnCalendarSelectDate;
    @FindBy(css = "a#usagePopup")
    private WebElement btnSetUsage;
    @FindBy(css = "h4.modal - title > span")
    private WebElement lblUsageNotificationsSetUsageModal;
    @FindBy(css = "span[globalize='ML_ErrMsg_MeterNumber']")
    private WebElement lblMeterNumberSetUsageModal;
    @FindBy(xpath = "//tbody[@id='tbDraggable']/tr/td")
    private WebElement lblMeterNumberOneSetUsageModal;
    @FindBy(xpath = "//tbody[@id='tbDraggable']/tr[2]/td")
    private WebElement lblMeterNumberTwoSetUsageModal;
    @FindBy(css = ".meterData")
    private WebElement lblMeterNumberValueSetUsageModal;
    @FindBy(css = "input#btnPaymentType.submit-button.frightbtn")
    private WebElement btnSaveSetUsageModal;
    @FindBy(css = "#HighUsage1 button.closepopup")
    private WebElement btnCloseSetUsageModal;
    @FindBy(css = ".toast-message")
    private WebElement lblSuccessMsgOnHeader;
    @FindBy(xpath = "//table[@id='user_table']/thead/tr/th[2]/span")
    private WebElement lblDailyAlertSetUsageModal;
    @FindBy(xpath = "//table[@id='user_table']/thead/tr/th[3]/span")
    private WebElement lblMonthlyAlertSetUsageModal;
    @FindBy(css = ".HighUsage_usage.editMain.modal-lg")
    private WebElement mdlSetUsageAlert;
    @FindBy(css = "input[id$ = 'chk_Daily'']")
    private WebElement chkBoxDailyAlertSetUsageModal;
    @FindBy(css = "label[for$='chk_Daily']")
    private WebElement lblChkBoxDailyAlertSetUsageModal;
    @FindBy(css = "input[id$='chk_Monthly']")
    private WebElement chkBoxMonthlyAlertSetUsageModal;
    @FindBy(css = "label[for$='chk_Monthly']")
    private WebElement lblChkBoxMonthlyAlertSetUsageModal;
    @FindBy(css = "#user_table input[id$='txt_Daily']")
    private WebElement txtBoxDailyAlertSetUsageModal;
    @FindBy(css = "#user_table input[id$='txt_Monthly']")
    private WebElement txtBoxMonthlyAlertSetUsageModal;

    @FindBy(css = "span#lblCharttitle.lblCharttitle_icon")
    private WebElement clndrPeriod;
    @FindBy(css = " td[data - handler = 'selectDay']")
    private WebElement lblDatesOnCalendar;
    @FindBy(css = ".compare_graph.highcharts-tracker rect")
    private WebElement lblHourlyHistogram;
    @FindBy(css = " #chart span[style='color:black;']")
    private WebElement lblNoUsageData;
    @FindBy(css = "div.w2ui-tag-body.w2ui-tag-right")
    private WebElement lblEnterIndividualUsageSetUsageModal;
    @FindBy(xpath = "//span[@class='error_messagecommon']")
    private WebElement lblErrorMessageCommon;
    @FindBy(css = "div.toast - message")
    private WebElement lblEnterAllUsageSetUsageModal;
    @FindBy(css = "[id*='lbl_Daily']")
    private WebElement lstUnitDailyAlertSetUsageModal;
    @FindBy(css = "[id*='lbl_Monthly']")
    private WebElement lstUnitMonthlyAlertSetUsageModal;
    @FindBy(css = "[id*='ddlUnit_Daily']")
    private WebElement lstUnitDailyAlertWaterSetUsageModal;
    @FindBy(css = "[id*='ddlUnit_Monthly']")
    private WebElement lstUnitMonthlyAlertWaterSetUsageModal;
    @FindBy(css = "#spnkLinkToEfficiency")
    private WebElement lblEfficiency;
    @FindBy(css = "#hlnkLinkToEfficiency")
    private WebElement lnkClickHereEfficiency;
    @FindBy(xpath = "//a[@title='Click to view Usage rates']")
    private WebElement lnkRateUsage;
    @FindBy(xpath = "//span[@class='ui-icon ui-icon-circle-triangle-w']")
    private WebElement btnPreCal;
    @FindBy(xpath = "//span[@class='ui-datepicker-year']")
    private WebElement lblYearCalender;
    @FindBy(xpath = "//span[@class='ui-datepicker-month']")
    private WebElement lblMonthCal;
    @FindBy(css = "li#liPU")
    private WebElement lnkPowerUsageTab;
    @FindBy(css = "li#liWU")
    private WebElement lnkWaterUsageTab;
    @FindBy(css = "li#liGU")
    private WebElement lnkGasUsageTab;
    @FindBy(css = "li#liSU")
    private WebElement lnkSolarUsageTab;
}

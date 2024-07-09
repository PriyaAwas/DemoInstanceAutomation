package demo.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import demo.pageobjects.PreLoginOutagesPage;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.utils.PropertiesUtil;

import static org.testng.Assert.assertTrue;

public class PreLoginOutagesSteps extends PreLoginOutagesPage {

	private static final Logger log = LogManager.getLogger(PreLoginOutagesSteps.class);
	public static PropertiesUtil preLoginOutagesProp;
	public static PropertiesUtil preLogConnectMeTextProp;

	public PreLoginOutagesSteps(WebDriver driver) {
		super(driver);
		preLoginOutagesProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.SCM_OUTAGES_TXT_FILENAME);
		preLogConnectMeTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.PRE_LOG_CONNECT_ME_TXT_FILENAME);
	}

	public void navigateToPreLoginOutages() {
		clickPreLoginOutagesBtn();
		waitForPageToLoad();
		Assert.assertTrue(isPreLoginOutagesPage(preLoginOutagesProp.getPropValue("preLoginPageUrl"),
				preLoginOutagesProp.getPropValue("preLoginPageTitle")));
	}

	public void verifyThePreLoginObjectsPageObject(SoftAssert softAssert) {
		waitForPageToLoad();

		softAssert.assertTrue(isCompanyLogoVisible(), "Company logo is not present.");
		softAssert.assertTrue(isLanguageDropdownVisible(), "Language dropdown option is not visible.");
		softAssert.assertTrue(isSearchBoxVisible(), "Searchbox text field is not visible.");
		softAssert.assertEquals(getMapLabel(), preLoginOutagesProp.getPropValue("lblMap"),
				"Label for map button is not matched.");
		softAssert.assertEquals(getSatelliteLabel(), preLoginOutagesProp.getPropValue("lblSatellite"),
				"Label for satellite button is not matched.");
		softAssert.assertEquals(getCurrentLabel(), preLoginOutagesProp.getPropValue("lblCurrent"),
				"Label for current button is not matched.");
		softAssert.assertEquals(getPlannedLabel(), preLoginOutagesProp.getPropValue("lblPlanned"),
				"Label for planned button is not matched.");
		softAssert.assertEquals(getOutagesLabel(), preLoginOutagesProp.getPropValue("lblOutages"),
				"Label for outages button is not matched.");
		softAssert.assertEquals(getAffectedLabel(), preLoginOutagesProp.getPropValue("lblAffected"),
				"Label for affected button is not matched.");
		softAssert.assertEquals(getAccountsServedLabel(), preLoginOutagesProp.getPropValue("lblAccountsServed"),
				"Label for accounts served button is not matched.");
		softAssert.assertEquals(getMapLegendLabel(), preLoginOutagesProp.getPropValue("lblMapLegend"),
				"Label for accounts map legend is not matched.");
		softAssert.assertEquals(getWeatherLabel(), preLoginOutagesProp.getPropValue("lblWeather"),
				"Label for accounts map legend is not matched.");
		softAssert.assertEquals(getReportOutageLabel(), preLoginOutagesProp.getPropValue("lblReportOutage"),
				"Label for accounts map legend is not matched.");
		softAssert.assertEquals(getInformationUpdatesLabel(), preLoginOutagesProp.getPropValue("lblInformationUpdates"),
				"Label for accounts map legend is not matched.");
		softAssert.assertTrue(isCurrentActive(), "current tab is active");
		softAssert.assertTrue(isFullscreenButtonVisible(), "fullscreen button not visible");
		softAssert.assertTrue(isZoomInButtonVisible(), "zoomin button not visible");
		softAssert.assertTrue(isZoomOutButtonVisible(), "zoomout button not visible");
		softAssert.assertTrue(isGridViewButtonVisible(), "grid view button not visible");
	}

	public void verifyPreLoginPlannedTab(SoftAssert softAssert) {
		waitForPageToLoad();
		clickPlannedBtn();
		softAssert.assertTrue(isPlannedActive(), "planned tab is active");
	}

	public void clickOnListAndMap(SoftAssert softAssert) {
		waitForPageToLoad();
		clickListViewBtn();
		pause(2000);
		softAssert.assertEquals(getCityLabel(), preLoginOutagesProp.getPropValue("lblCity"),
				"Label for city is not matched.");
		softAssert.assertEquals(getZipCodeLabel(), preLoginOutagesProp.getPropValue("lblZipCode"),
				"Label for Zip COde is not matched.");
		softAssert.assertEquals(getAreaLabel(), preLoginOutagesProp.getPropValue("lblArea"),
				"Label for city is not matched.");
		softAssert.assertEquals(getOutLabel(), preLoginOutagesProp.getPropValue("lblOut"),
				"Label for Zip COde is not matched.");
		softAssert.assertEquals(getServedLabel(), preLoginOutagesProp.getPropValue("lblServed"),
				"Label for city is not matched.");
		clickPlannedBtn();
		softAssert.assertEquals(getAreaLabel(), preLoginOutagesProp.getPropValue("lblArea"),
				"Label for city is not matched.");
		softAssert.assertEquals(getOutLabel(), preLoginOutagesProp.getPropValue("lblOut"),
				"Label for Zip COde is not matched.");
		softAssert.assertEquals(getServedLabel(), preLoginOutagesProp.getPropValue("lblServed"),
				"Label for city is not matched.");
		softAssert.assertFalse(isMapLegendVisible(), "map legend is active");
		softAssert.assertFalse(isWeatherVisible(), "weather is active");
		clickMapViewBtn();
		// add assertion for map
	}

	public void clickOnFullScreen(SoftAssert softAssert) {
		waitForPageToLoad();
		pause(1000);
		clickFullScreenBtn();
		pause(1000);
		clickFullScreenBtn();
	}

	public void clickOnZoomButtons(SoftAssert softAssert) {
		waitForPageToLoad();
		pause(1000);
		clickZoomInBtn();
		pause(1000);
		clickZoomOutBtn();
		pause(1000);
	}

	public void searchWithInvalidZip(SoftAssert softAssert) {
		waitForPageToLoad();
		enterInvalidZipInSearch("11");
		pause(1000);
		clickSearchBtn();
		softAssert.assertEquals(getEmptySearchLabel(), preLoginOutagesProp.getPropValue("lblInvalidSearch"),
				"Label for city is not matched.");
		pause(1000);
	}

	public void clickOnEmptySearch(SoftAssert softAssert) {
		clickSearchBtn();
		pause(3000);
		softAssert.assertEquals(getEmptySearchLabel(), preLoginOutagesProp.getPropValue("lblEmptySearch"),
				"Label for city is not matched.");
		pause(1000);
	}

	public void validateMapIcons(SoftAssert softAssert) {
		waitForPageToLoad();
		pause(1000);
		clickMapLegendBtn();
		pause(1000);
		clickWeatherBtn();
		pause(1000);
		softAssert.assertEquals(getMultipleLabel(), preLoginOutagesProp.getPropValue("lblMultiple"),
				"Label for city is not matched.");
		softAssert.assertEquals(get1Label(), preLoginOutagesProp.getPropValue("lbl1"),
				"Label for city is not matched.");
		softAssert.assertEquals(get2Label(), preLoginOutagesProp.getPropValue("lbl2"),
				"Label for city is not matched.");
		softAssert.assertEquals(get101Label(), preLoginOutagesProp.getPropValue("lbl101"),
				"Label for city is not matched.");
		softAssert.assertEquals(get501Label(), preLoginOutagesProp.getPropValue("lbl501"),
				"Label for city is not matched.");
		softAssert.assertEquals(get2000Label(), preLoginOutagesProp.getPropValue("lbl2000"),
				"Label for city is not matched.");
		softAssert.assertEquals(getSABLabel(), preLoginOutagesProp.getPropValue("lblSAB"),
				"Label for city is not matched.");
		softAssert.assertEquals(getRainLabel(), preLoginOutagesProp.getPropValue("lblRain"),
				"Label for city is not matched.");
		softAssert.assertEquals(getFrozenLabel(), preLoginOutagesProp.getPropValue("lblFrozen"),
				"Label for city is not matched.");
		softAssert.assertEquals(getSnowLabel(), preLoginOutagesProp.getPropValue("lblSnow"),
				"Label for city is not matched.");
		softAssert.assertEquals(getLightLabel(), preLoginOutagesProp.getPropValue("lblLight"),
				"Label for city is not matched.");
		softAssert.assertEquals(getHeavyLabel(), preLoginOutagesProp.getPropValue("lblHeavy"),
				"Label for city is not matched.");
	}

	public void navigateToReportOutage(SoftAssert softAssert) {
		clickPreLoginOutagesBtn();
		waitForPageToLoad();
		Assert.assertTrue(isPreLoginOutagesPage(preLoginOutagesProp.getPropValue("preLoginPageUrl"),
				preLoginOutagesProp.getPropValue("preLoginPageTitle")));
		clickReportOutageBtn();
		waitForPageToLoad();
		Assert.assertTrue(isPreLogConnectMePage(preLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
						(preLogConnectMeTextProp.getPropValue("ConnectMePageTitleTxt"))),
				"Page Title & URL does not Match");
		ExtentLogger.logInfo("Customer is able to Navigate to report outage page");
		//Enter only mandatory Details and submit
		enterEmail("test@test.com");
		enterFName("test");
		enterLName("test");
		enterPrimaryPhoneNumber("1234567890");
		enterStreetName("test");
		enterZipCodeForm("12345");
		populatestate("CA");
		enterNCrossStreet("test");
		pause(1000);
		btnClickNext();
		pause(2000);
		btnClickSubmit();
		pause(4000);
		btnClickOk();
		waitForPageToLoad();
		Assert.assertTrue(isPreLoginOutagesPage(preLoginOutagesProp.getPropValue("preLoginPageUrl"),
				preLoginOutagesProp.getPropValue("preLoginPageTitle")));
	}

	public boolean isPreLogConnectMePage(String url, String title) {
		Boolean isForgetPasswordPage = false;
		log.info("Checking that the current page is ForgetPassword Page");
		log.info("testing" + getCurrentUrl().substring(0,75));
		log.info("testing2" + getCurrentTitle());
		if (getCurrentUrl().substring(0,75).contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isForgetPasswordPage = true;
		log.info("The current page is ForgetPassword Page {}: " + isForgetPasswordPage);
		return isForgetPasswordPage;
	}

	public void clickFromOptions(SoftAssert softAssert) {
		clickPreLoginOutagesBtn();
		waitForPageToLoad();
		Assert.assertTrue(isPreLoginOutagesPage(preLoginOutagesProp.getPropValue("preLoginPageUrl"),
				preLoginOutagesProp.getPropValue("preLoginPageTitle")));
		enterBangInSearch("bang");
		pause(2000);
		clickOption();
		softAssert.assertEquals(getOptionLabel(), preLoginOutagesProp.getPropValue("option"),
				"Label for city is not matched.");
	}

}

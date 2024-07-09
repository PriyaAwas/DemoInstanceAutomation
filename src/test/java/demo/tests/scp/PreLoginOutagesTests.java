package demo.tests.scp;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.runner.TestRunner;
import sew.ai.utils.PropertiesUtil;
import demo.steps.scp.PreLoginOutagesSteps;

public class PreLoginOutagesTests extends TestRunner {

	private static final Logger log = LogManager.getLogger(PreLoginOutagesTests.class);
	private PreLoginOutagesSteps preLoginOutagesSteps;
	public static PropertiesUtil loginTextProp;

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY, CategoryType.SCP_OUTAGES })
	@Test(priority = 1, description = "To verify the pre-login outages page objects.")
	public void verifyPreLoginOutagesPageObjects() {
		ExtentLogger.logInfo("Test Case execution for - verifyPreLoginOutagesPageObjects.");
		SoftAssert softAssert = new SoftAssert();
		preLoginOutagesSteps = new PreLoginOutagesSteps(driver);
		preLoginOutagesSteps.navigateToPreLoginOutages();
		ExtentLogger.logInfo("navigateToPreLoginOutages Complete");
		preLoginOutagesSteps.verifyThePreLoginObjectsPageObject(softAssert);
		ExtentLogger.logInfo("verifyThePreLoginObjectsPageObject Complete");
		preLoginOutagesSteps.verifyPreLoginPlannedTab(softAssert);
		ExtentLogger.logInfo("verifyPreLoginPlannedTab Complete");
		// Assert all the soft verification.
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyPreLoginOutagesPageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY, CategoryType.SCP_OUTAGES })
	@Test(priority = 2, description = "To verify the different functionalities in Outage page")
	public void verifyOutageFunctionalities() {
		ExtentLogger.logInfo("Test Case execution for - verifyOutageFunctionalities.");
		SoftAssert softAssert = new SoftAssert();
		preLoginOutagesSteps = new PreLoginOutagesSteps(driver);
		// Go to the application and verify objects
		preLoginOutagesSteps.navigateToPreLoginOutages();
		ExtentLogger.logInfo("navigateToPreLoginOutages Complete");
		preLoginOutagesSteps.clickOnListAndMap(softAssert);
		ExtentLogger.logInfo("clickOnListAndMap Complete");
		preLoginOutagesSteps.clickOnFullScreen(softAssert);
		ExtentLogger.logInfo("clickOnFullScreen Complete");
		preLoginOutagesSteps.clickOnZoomButtons(softAssert);
		ExtentLogger.logInfo("clickOnZoomButtons Complete");
		preLoginOutagesSteps.clickOnEmptySearch(softAssert);
		ExtentLogger.logInfo("clickOnEmptySearch Complete");
		//preLoginOutagesSteps.searchWithInvalidZip(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyOutageFunctionalities - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY, CategoryType.SCP_OUTAGES })
	@Test(priority = 3, description = "To verify the Map Legend and Weather functionality.")
	public void verifyClickingMapLegendAndWeatherButtons() {
		ExtentLogger.logInfo("Test Case execution for - verifyClickingMapLegendAndWeatherButtons.");
		SoftAssert softAssert = new SoftAssert();
		preLoginOutagesSteps = new PreLoginOutagesSteps(driver);
		preLoginOutagesSteps.navigateToPreLoginOutages();
		ExtentLogger.logInfo("Navigation to Pre-Login outages complete.");
		preLoginOutagesSteps.validateMapIcons(softAssert);
		ExtentLogger.logInfo("validateMapIcons Complete");
		// Assert all the soft verification.
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyClickingMapLegendAndWeatherButtons - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY, CategoryType.SCP_OUTAGES })
	@Test(priority = 3, description = "To verify is the customer is able to submit a request")
	public void verifySubmitContactRequestForOutages() {
		ExtentLogger.logInfo("Test Case execution for - verifySubmitContactRequestForOutages.");
		SoftAssert softAssert = new SoftAssert();
		preLoginOutagesSteps = new PreLoginOutagesSteps(driver);
		preLoginOutagesSteps.navigateToReportOutage(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifySubmitContactRequestForOutages - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY, CategoryType.SCP_OUTAGES })
	@Test(priority = 3, description = "To verify clicking the dropdown options in search box")
	public void verifyClickFromOptions() {
		ExtentLogger.logInfo("Test Case execution for - verifyClickFromOptions");
		SoftAssert softAssert = new SoftAssert();
		preLoginOutagesSteps = new PreLoginOutagesSteps(driver);
		preLoginOutagesSteps.clickFromOptions(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyClickFromOptions - is Completed.");
	}

}

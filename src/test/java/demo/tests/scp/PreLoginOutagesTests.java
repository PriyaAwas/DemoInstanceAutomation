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
		String[] tc_id = {"Outages_01", "Outages_02", "Outages_03"};
		ExtentLogger.logInfo("Test Case execution for - verifyPreLoginOutagesPageObjects. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		preLoginOutagesSteps = new PreLoginOutagesSteps(driver);
		preLoginOutagesSteps.navigateToPreLoginOutages();
		preLoginOutagesSteps.verifyThePreLoginObjectsPageObject(softAssert);
		preLoginOutagesSteps.verifyPreLoginPlannedTab(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyPreLoginOutagesPageObjects - is Completed.");
		log.info("Test Case execution for - verifyPreLoginOutagesPageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY, CategoryType.SCP_OUTAGES })
	@Test(priority = 2, description = "To verify the toggle icon to switch the view between map view and list view.")
	public void verifyOutageFunctionalities() {
		String[] tc_id = {"Outages_04", "Outages_10", "Outages_12"};
		ExtentLogger.logInfo("Test Case execution for - verifyPreLoginOutagesPageObjects. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		preLoginOutagesSteps = new PreLoginOutagesSteps(driver);
		// Go to the application and verify objects
		preLoginOutagesSteps.navigateToPreLoginOutages();
		preLoginOutagesSteps.clickOnListAndMap(softAssert);
		preLoginOutagesSteps.clickOnFullScreen(softAssert);
		preLoginOutagesSteps.clickOnZoomButtons(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyPreLoginListViewPageObjects - is Completed.");
		log.info("Test Case execution for - verifyPreLoginListViewPageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY, CategoryType.SCP_OUTAGES })
	@Test(priority = 3, description = "To verify the Map Legend and Weather functionality.")
	public void verifyClickingMapLegendAndWeatherButtons() {
		String[] tc_id = {"Outages_09"};
		ExtentLogger.logInfo("Test Case execution for - verifyPreLoginOutagesPageObjects. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		preLoginOutagesSteps = new PreLoginOutagesSteps(driver);
		preLoginOutagesSteps.navigateToPreLoginOutages();
		preLoginOutagesSteps.validateMapIcons(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyClickingMapLegendAndWeatherButtons - is Completed.");
		log.info("Test Case execution for - verifyClickingMapLegendAndWeatherButtons - is Completed.");
	}

}

package demo.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import demo.steps.scp.PreLoginPayACHSteps;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.runner.TestRunner;

public class PreLoginPayACHTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(PreLoginPayACHTests.class);
	private PreLoginPayACHSteps preLoginPayACHSteps;

	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SMOKE, CategoryType.SANITY })
	@Test(priority = 1, description = "To perform object verification of Pre-Login Pay Bill page.")
	public void verifyPrePayBillUIAndObjtsStepOne() throws Exception {
		log.info("To verify objects on Pre-Login Pay Bill page.");
		SoftAssert softAssert = new SoftAssert();
		// Init Forget Username Steps
		preLoginPayACHSteps = new PreLoginPayACHSteps(driver);
		preLoginPayACHSteps.verifyPrePayBillUIAndObjtsStepOne(softAssert);
		log.info("Test Case execution for - verifyPrePayBillUIAndObjtsStepOne - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyPrePayBillUIAndObjtsStepOne - is Completed.");
	}	

	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SMOKE, CategoryType.SANITY })
	@Test(priority = 2, description = "To move to Step 2 of pre-login page successfully and perform UI Vald.")
	public void verifyMoveToStpTwoAndUIVal() throws Exception {
		log.info("To verify move to Step 2 of pre-login page successfully and perfom UI Vald.");
		SoftAssert softAssert = new SoftAssert();
		// Init Forget Username Steps
		preLoginPayACHSteps = new PreLoginPayACHSteps(driver);
		preLoginPayACHSteps.verifyMoveToStpTwoAndUIVal(softAssert);
		log.info("Test Case execution for - verifyMoveToStpTwoAndUIVal - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyMoveToStpTwoAndUIValverifyMoveToStpTwoAndUIVal - is Completed.");
	}
	
	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SMOKE, CategoryType.SANITY })
	@Test(priority = 3, description = "To perform UI Vald. on Step 2 of PayBill Page")
	public void verifyUIOnStepTwoPreLogPayBill() throws Exception {
		log.info("To verify move to Step 2 of pre-login page successfully and perfom UI Vald.");
		SoftAssert softAssert = new SoftAssert();
		// Init Forget Username Steps
		preLoginPayACHSteps = new PreLoginPayACHSteps(driver);
		preLoginPayACHSteps.verifyUIOnStepTwoPreLogPayBill(softAssert);
		log.info("Test Case execution for - verifyUIOnStepTwoPreLogPayBill - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyUIOnStepTwoPreLogPayBill - is Completed.");
	}
	
	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SMOKE, CategoryType.SANITY })
	@Test(priority = 6, description = "To perform error message validation on Step 1 of PayBill Page")
	public void verifyStpOneErrorMssgs() throws Exception {
		log.info("To perform error message validation on Step 1 of PayBill Page");
		SoftAssert softAssert = new SoftAssert();
		// Init Forget Username Steps
		preLoginPayACHSteps = new PreLoginPayACHSteps(driver);
		preLoginPayACHSteps.verifyStpOneErrorMssgs(softAssert);
		log.info("Test Case execution for - verifyStpOneErrorMssgs - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyStpOneErrorMssgs - is Completed.");
	}
	
	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SMOKE, CategoryType.SANITY })
	@Test(priority = 7, description = "To perform and validate cancel bttn functionality on step 1")
	public void verifyCancelBttnFunStepOne() throws Exception {
		log.info("To perform and validate cancel bttn functionality on step 1");
		SoftAssert softAssert = new SoftAssert();
		// Init Forget Username Steps
		preLoginPayACHSteps = new PreLoginPayACHSteps(driver);
		preLoginPayACHSteps.verifyCancelBttnFunStepOne(softAssert);
		log.info("Test Case execution for - verifyCancelBttnFunStepOne - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyCancelBttnFunStepOne - is Completed.");
	}
	
	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SMOKE, CategoryType.SANITY })
	@Test(priority = 8, description = "To perform error message validation on step 2")
	public void verifyBlnkErrorMsgStepTwoPreLogPayBill() throws Exception {
		log.info("To perform error message validation on step 2");
		SoftAssert softAssert = new SoftAssert();
		// Init Forget Username Steps
		preLoginPayACHSteps = new PreLoginPayACHSteps(driver);
		preLoginPayACHSteps.verifyBlnkErrorMsgStepTwoPreLogPayBill(softAssert);
		log.info("Test Case execution for - verifyBlnkErrorMsgStepTwoPreLogPayBill - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyBlnkErrorMsgStepTwoPreLogPayBill - is Completed.");
	}
	
	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SMOKE, CategoryType.SANITY })
	@Test(priority = 4, description = "To perform UI validation on step 3")
	public void verifyUIOnStepThreePreLogPayBill() throws Exception {
		log.info("To perform UI validation on step 3");
		SoftAssert softAssert = new SoftAssert();
		// Init Forget Username Steps
		preLoginPayACHSteps = new PreLoginPayACHSteps(driver);
		preLoginPayACHSteps.verifyUIOnStepThreePreLogPayBill(softAssert);
		log.info("Test Case execution for - verifyUIOnStepThreePreLogPayBill - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyUIOnStepThreePreLogPayBill - is Completed.");
	}
	
	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SMOKE, CategoryType.SANITY })
	@Test(priority = 5, description = "To perform UI Vald. on Step 4 of PayBill Page")
	public void verifyUIOnStepFourPreLogPayBill() throws Exception {
		log.info("To perform UI Vald. on Step 4 of PayBill Page");
		SoftAssert softAssert = new SoftAssert();
		// Init Forget Username Steps
		preLoginPayACHSteps = new PreLoginPayACHSteps(driver);
		preLoginPayACHSteps.verifyUIOnStepFourPreLogPayBill(softAssert);
		log.info("Test Case execution for - verifyUIOnStepFourPreLogPayBill - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyUIOnStepFourPreLogPayBill - is Completed.");
	}

}

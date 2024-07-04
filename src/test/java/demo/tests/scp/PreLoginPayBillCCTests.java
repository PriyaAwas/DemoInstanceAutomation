package demo.tests.scp;

import demo.steps.scp.PreLoginPayBillCCSteps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.config.Configuration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.utils.PropertiesUtil;

import java.sql.SQLException;
import java.util.Arrays;

public class PreLoginPayBillCCTests extends TestRunner {

	private static final Logger log = LogManager.getLogger(PreLoginPayBillCCTests.class);
	private PreLoginPayBillCCSteps PreLoginPayBillCCSteps;
	public static PropertiesUtil loginTextProp;

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SMOKE, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To verify the pay bill page two for CC objects.")
	public void verifyPageTwoCCPayBillPageObjects() {
		String[] tc_id = { "Pre_Login_03" };
		ExtentLogger.logInfo(
				"Test Case execution for - verifyLoginPageObjects. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		PreLoginPayBillCCSteps = new PreLoginPayBillCCSteps(driver);
		// Go to the application and verify objects
		PreLoginPayBillCCSteps.verifyMoveToStpTwoAndUIVal(softAssert);
		PreLoginPayBillCCSteps.pageTwoCCPageObjects(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyLoginPageObjects - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyLoginPageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SMOKE, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To verify the pay bill page two for CC objects.")
	public void verifyZeroPaymentValue() {
		String[] tc_id = { "Pre_Login_03" };
		ExtentLogger.logInfo(
				"Test Case execution for - verifyLoginPageObjects. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		PreLoginPayBillCCSteps = new PreLoginPayBillCCSteps(driver);
		// Go to the application and verify objects
		PreLoginPayBillCCSteps.verifyMoveToStpTwoAndUIVal(softAssert);
		ExtentLogger.logInfo(
				"Test Case execution for - Payment with 0 as value");
		PreLoginPayBillCCSteps.zeroPaymentValue(softAssert);
		ExtentLogger.logInfo(
				"Test Case execution for - Payment with decimal value");
		PreLoginPayBillCCSteps.decimalPaymentValue(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyLoginPageObjects - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyLoginPageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SMOKE, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To verify the pay bill page two for CC objects.")
	public void verifyFieldSpecificValidationMessage() {
		String[] tc_id = { "Pre_Login_03" };
		ExtentLogger.logInfo(
				"Test Case execution for - verifyLoginPageObjects. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		PreLoginPayBillCCSteps = new PreLoginPayBillCCSteps(driver);
		// Go to the application and verify objects
		PreLoginPayBillCCSteps.verifyMoveToStpTwoAndUIVal(softAssert);
		ExtentLogger.logInfo(
				"Test Case execution for - ");
		PreLoginPayBillCCSteps.fieldSpecificValidationMessage(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyLoginPageObjects - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyLoginPageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SMOKE, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To verify the pay bill page two for CC objects.")
	public void verifyAddDefinedCards() {
		String[] tc_id = { "Pre_Login_03" };
		ExtentLogger.logInfo(
				"Test Case execution for - verifyLoginPageObjects. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		PreLoginPayBillCCSteps = new PreLoginPayBillCCSteps(driver);
		PreLoginPayBillCCSteps.verifyMoveToStpTwoAndUIVal(softAssert);
		ExtentLogger.logInfo(
				"Test Case execution for - ");
		PreLoginPayBillCCSteps.creditCardIcons(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyLoginPageObjects - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyLoginPageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SMOKE, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To verify the pay bill page two for CC objects.")
	public void verifyMakePaymentCards() {
		String[] tc_id = { "Pre_Login_03" };
		ExtentLogger.logInfo(
				"Test Case execution for - verifyLoginPageObjects. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		PreLoginPayBillCCSteps = new PreLoginPayBillCCSteps(driver);
		PreLoginPayBillCCSteps.verifyUIOnStepFourPreLogPayBill(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyLoginPageObjects - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyLoginPageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SMOKE, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To verify the pay bill page two for CC objects.")
	public void verifyExceedingPayment() {
		String[] tc_id = { "Pre_Login_03" };
		ExtentLogger.logInfo(
				"Test Case execution for - verifyLoginPageObjects. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		PreLoginPayBillCCSteps = new PreLoginPayBillCCSteps(driver);
		PreLoginPayBillCCSteps.verifyMoveToStpTwoAndUIVal(softAssert);
		ExtentLogger.logInfo(
				"Test Case execution for - ");
		PreLoginPayBillCCSteps.exceedingPaymentValidationMessage(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyLoginPageObjects - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyLoginPageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SMOKE, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To verify the pay bill page two for CC objects.")
	public void verifyPastYearExpiry() {
		String[] tc_id = { "Pre_Login_03" };
		ExtentLogger.logInfo(
				"Test Case execution for - verifyLoginPageObjects. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		PreLoginPayBillCCSteps = new PreLoginPayBillCCSteps(driver);
		PreLoginPayBillCCSteps.verifyMoveToStpTwoAndUIVal(softAssert);
		ExtentLogger.logInfo(
				"Test Case execution for - ");
		PreLoginPayBillCCSteps.pastYearExpiry(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyLoginPageObjects - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyLoginPageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SMOKE, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To verify the pay bill page two for CC objects.")
	public void verifyPageClearWhileToggle() {
		String[] tc_id = { "Pre_Login_03" };
		ExtentLogger.logInfo(
				"Test Case execution for - verifyLoginPageObjects. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		PreLoginPayBillCCSteps = new PreLoginPayBillCCSteps(driver);
		PreLoginPayBillCCSteps.verifyMoveToStpTwoAndUIVal(softAssert);
		ExtentLogger.logInfo(
				"Test Case execution for - ");
		PreLoginPayBillCCSteps.pageClearWhileToggle(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyLoginPageObjects - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyLoginPageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SMOKE, CategoryType.SCP_PAYMENT })
	@Test(priority = 3, description = "To verify the payment functionality with invalid details.")
	public void verifyInvalidPaymentDetails() {
		String[] tc_id = { "Pre_Login_08" };
		ExtentLogger.logInfo(
				"Test Case execution for - verifyInvalidPaymentDetails. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		PreLoginPayBillCCSteps = new PreLoginPayBillCCSteps(driver);
		PreLoginPayBillCCSteps.payTheApplicationWrongCreds(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyInvalidPaymentDetails - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyInvalidPaymentDetails - is Completed.");
	}

}

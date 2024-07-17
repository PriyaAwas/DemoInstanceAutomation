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
				"Test Case execution for - verifyPageTwoCCPayBillPageObjects.");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		PreLoginPayBillCCSteps = new PreLoginPayBillCCSteps(driver);
		// Go to the application and verify objects
		PreLoginPayBillCCSteps.verifyMoveToStpTwoAndUIVal(softAssert);
		ExtentLogger.logInfo("Step-1 Complete");
		PreLoginPayBillCCSteps.pageTwoCCPageObjects(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyPageTwoCCPayBillPageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SMOKE, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To verify pay bill with zero and decimal payment value")
	public void verifyZeroPaymentValue() {
		ExtentLogger.logInfo(
				"Test Case execution for - verifyZeroPaymentValue.");
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
		ExtentLogger.logInfo("Test Case execution for - verifyZeroPaymentValue - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SMOKE, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To verify the pay bill page two for CC objects.")
	public void verifyFieldSpecificValidationMessage() {
		ExtentLogger.logInfo(
				"Test Case execution for - verifyFieldSpecificValidationMessage.");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		PreLoginPayBillCCSteps = new PreLoginPayBillCCSteps(driver);
		// Go to the application and verify objects
		PreLoginPayBillCCSteps.verifyMoveToStpTwoAndUIVal(softAssert);
		ExtentLogger.logInfo("Step-1 Complete");
		PreLoginPayBillCCSteps.fieldSpecificValidationMessage(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyFieldSpecificValidationMessage - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SMOKE, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To verify if the user is able to add the defined cards")
	public void verifyAddDefinedCards() {
		ExtentLogger.logInfo(
				"Test Case execution for - verifyAddDefinedCards.");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		PreLoginPayBillCCSteps = new PreLoginPayBillCCSteps(driver);
		PreLoginPayBillCCSteps.verifyMoveToStpTwoAndUIVal(softAssert);
		ExtentLogger.logInfo("Step-1 Complete");
		PreLoginPayBillCCSteps.creditCardIcons(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyAddDefinedCards - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SMOKE, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To verify if the user is able to make payments using Card")
	public void verifyMakePaymentCards() {
		ExtentLogger.logInfo(
				"Test Case execution for - verifyMakePaymentCards.");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		PreLoginPayBillCCSteps = new PreLoginPayBillCCSteps(driver);
		PreLoginPayBillCCSteps.verifyMoveToStpTwoAndUIVal(softAssert);
		ExtentLogger.logInfo("Step-1 Complete");
		PreLoginPayBillCCSteps.verifyUIOnStepFourPreLogPayBill(softAssert);
		ExtentLogger.logInfo("verifyUIOnStepFourPreLogPayBill Complete");
		// Assert all the soft verification.
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyMakePaymentCards - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SMOKE, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To verify the pay bill page two for CC objects.")
	public void verifyExceedingPayment() {
		ExtentLogger.logInfo(
				"Test Case execution for - verifyExceedingPayment.");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		PreLoginPayBillCCSteps = new PreLoginPayBillCCSteps(driver);
		PreLoginPayBillCCSteps.verifyMoveToStpTwoAndUIVal(softAssert);
		ExtentLogger.logInfo("Step-1 Complete");
		PreLoginPayBillCCSteps.exceedingPaymentValidationMessage(softAssert);
		ExtentLogger.logInfo("exceedingPaymentValidationMessage Complete");
		// Assert all the soft verification.
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyExceedingPayment - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SMOKE, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To verify if customer is able to select previous year for Card expiry year.")
	public void verifyPastYearExpiry() {
		ExtentLogger.logInfo(
				"Test Case execution for - verifyLoginPageObjects.");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		PreLoginPayBillCCSteps = new PreLoginPayBillCCSteps(driver);
		PreLoginPayBillCCSteps.verifyMoveToStpTwoAndUIVal(softAssert);
		ExtentLogger.logInfo("Step-1 Complete");
		PreLoginPayBillCCSteps.pastYearExpiry(softAssert);
		ExtentLogger.logInfo("pastYearExpiry Complete");
		// Assert all the soft verification.
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyLoginPageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SMOKE, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To verify if the given details are cleared while switching between Bank and Card")
	public void verifyPageClearWhileToggle() {
		ExtentLogger.logInfo(
				"Test Case execution for - verifyPageClearWhileToggle.");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		PreLoginPayBillCCSteps = new PreLoginPayBillCCSteps(driver);
		PreLoginPayBillCCSteps.verifyMoveToStpTwoAndUIVal(softAssert);
		ExtentLogger.logInfo("Step-1 Complete");
		PreLoginPayBillCCSteps.pageClearWhileToggle(softAssert);
		ExtentLogger.logInfo("pageClearWhileToggle Complete");
		// Assert all the soft verification.
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyPageClearWhileToggle - is Completed.");
	}

}

package demo.tests.scp;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import demo.steps.scp.PreLoginServiceRequestSteps;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;

public class PreLoginServiceRequestTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(PreLoginServiceRequestTests.class);
	private PreLoginServiceRequestSteps servicerequestSteps;
	private DashboardSteps dashboardSteps;

	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the service page objects after login.")
	public void validatePreLogServiceReqPage() throws SQLException {
		log.info("To verify the Pre Login Service page objects");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		servicerequestSteps = new PreLoginServiceRequestSteps(driver);
		servicerequestSteps.preLogServiceReqObject(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - preLogServiceReqObject - is Completed.");
	}

	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the Move In page objects and create MoveIn Request.")

	public void validateMoveInPageObjectAndCreateReq() throws InterruptedException {
		log.info("To verify the Pre Login  page objects");
		SoftAssert softAssert = new SoftAssert();
		servicerequestSteps = new PreLoginServiceRequestSteps(driver);
		servicerequestSteps.verifyMoveInPageObjectAndCreateReq(softAssert);

		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyMoveInPageObjectAndCreateReq - is Completed.");

	}

	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the Move In page objects and create MoveIn Request.")

	public void verifyMoveInFeildsValidation() throws InterruptedException {
		log.info("verifyMoveInFeildLevelValidation");
		SoftAssert softAssert = new SoftAssert();
		servicerequestSteps = new PreLoginServiceRequestSteps(driver);
		servicerequestSteps.verifyMoveInFeildLevelValidation(softAssert);

		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyMoveInFeildLevelValidation - is Completed.");
	}

	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the Move In page objects and create MoveIn Request.")

	public void validateMoveOutPageObjectAndCreateReq() throws InterruptedException {
		log.info("To verify verifyMoveOutPageObjectAndCreateReq");
		SoftAssert softAssert = new SoftAssert();
		servicerequestSteps = new PreLoginServiceRequestSteps(driver);
		servicerequestSteps.verifyMoveOutFeildLevelValidation(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyMoveOutPageObjectAndCreateReq - is Completed.");

	}

	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the Move In page objects and create MoveIn Request.")

	public void validateMoveOutSavedformFunctionality() throws InterruptedException {
		log.info("To verify the validateMoveOutSavedformFunctionality");
		SoftAssert softAssert = new SoftAssert();
		servicerequestSteps = new PreLoginServiceRequestSteps(driver);
		servicerequestSteps.verifySavedForm(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - validateMoveOutSavedformFunctionality - is Completed.");
	}

	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the Move In page objects and create MoveIn Request.")

	public void verifyMoveOutFeildsValidation() throws InterruptedException {
		log.info("verifyMoveInFeildLevelValidation");
		SoftAssert softAssert = new SoftAssert();
		servicerequestSteps = new PreLoginServiceRequestSteps(driver);
		servicerequestSteps.verifyMoveOutFeildLevelValidation(softAssert);

		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyMoveInFeildLevelValidation - is Completed.");
	}

	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the Move In page objects and create MoveIn Request.")

	public void validateServiceTransferPageObjectAndCreateReq() throws InterruptedException {
		log.info("To verify the Pre Login  page objects");
		SoftAssert softAssert = new SoftAssert();
		servicerequestSteps = new PreLoginServiceRequestSteps(driver);
		servicerequestSteps.preLogServiceServiceTransferReq(softAssert);

		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyMoveInPageObjectAndCreateReq - is Completed.");

	}

	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the Move In page objects and create MoveIn Request.")
	public void validateFieldObjectsInOthersform() throws InterruptedException {
		log.info("To verify the Pre Login  page objects");
		SoftAssert softAssert = new SoftAssert();
		servicerequestSteps = new PreLoginServiceRequestSteps(driver);
		servicerequestSteps.preLogServiceOtherFeildsVeification(softAssert);

		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - validateFieldObjectsInOthersform - is Completed.");
	}
}

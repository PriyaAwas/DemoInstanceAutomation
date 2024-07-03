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
import sew.ai.steps.scp.LoginSteps;

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
		servicerequestSteps.verifyMoveOutPageObjectAndCreateReq(softAssert);
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
}


/*
 * @TestRail(testCaseId = { 78435, 79282, 79294, 79296, 79297, 79659, 79298,
 * 79294, 79295, 78429, 78500, 78503, 78506, 78517 })
 * 
 * @FrameworkAnnotations(author = { "Varad Nevase" }, category = {
 * CategoryType.SANITY })
 * 
 * @Test(priority = 1, description =
 * "To verify the service pre-login track request.") public void
 * validatePreLogTrackReqPage() throws SQLException {
 * log.info("To verify the tests with the below test case id's: " +
 * "C74657, C74658"); SoftAssert softAssert = new SoftAssert(); // Init login
 * steps LoginSteps loginSteps = new LoginSteps(driver); servicerequestSteps =
 * new PreLogServiceRequestSteps(driver); dashboardSteps = new
 * DashboardSteps(driver); // Init user model User user = SCPConfiguration.user;
 * // Login into the application dashboardSteps =
 * loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
 * // Verify the user landed to the dashboard page
 * servicerequestSteps.preLogServiceTrackReq(softAssert, user); // Assert all
 * the soft verification. softAssert.assertAll(); log.
 * info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
 * }
 * 
 * @TestRail(testCaseId = { 78091, 78430, 78433, 78449, 78602, 78441, 80062,
 * 78443, 78444, 78456, 78511, 78512 })
 * 
 * @FrameworkAnnotations(author = { "Varad Nevase" }, category = {
 * CategoryType.SANITY })
 * 
 * @Test(priority = 1, description =
 * "To verify the service pre-login save request.") public void
 * validatePreLogSaveServiceReqPage() throws SQLException {
 * log.info("To verify the tests with the below test case id's: " +
 * "C74657, C74658"); SoftAssert softAssert = new SoftAssert(); // Init login
 * steps LoginSteps loginSteps = new LoginSteps(driver); servicerequestSteps =
 * new PreLogServiceRequestSteps(driver); dashboardSteps = new
 * DashboardSteps(driver); // Init user model User user = SCPConfiguration.user;
 * // Login into the application dashboardSteps =
 * loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
 * // Verify the user landed to the dashboard page
 * servicerequestSteps.preLogServiceSaveReq(softAssert, user); // Assert all the
 * soft verification. softAssert.assertAll(); log.
 * info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
 * }
 * 
 * @TestRail(testCaseId = { 78445, 78446, 78447, 79330, 79386, 78599, 78600,
 * 79331, 79332, 79390 })
 * 
 * @FrameworkAnnotations(author = { "Varad Nevase" }, category = {
 * CategoryType.SANITY })
 * 
 * @Test(priority = 1, description =
 * "To verify the service pre-login Move Out request.") public void
 * validatePreLogMoveOutReqPage() throws SQLException {
 * log.info("To verify the tests with the below test case id's: " +
 * "C74657, C74658"); SoftAssert softAssert = new SoftAssert(); // Init login
 * steps LoginSteps loginSteps = new LoginSteps(driver); servicerequestSteps =
 * new PreLogServiceRequestSteps(driver); dashboardSteps = new
 * DashboardSteps(driver); // Init user model User user = SCPConfiguration.user;
 * // Login into the application dashboardSteps =
 * loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
 * // Verify the user landed to the dashboard page
 * servicerequestSteps.preLogServiceMoveOutReq(softAssert, user); // Assert all
 * the soft verification. softAssert.assertAll(); log.
 * info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
 * }
 * 
 * @TestRail(testCaseId = { 78484, 78504, 79291, 78505, 78508, 78509, 78513,
 * 78515, 78516, 79396, 79397, 79398, 129286, 129287 })
 * 
 * @FrameworkAnnotations(author = { "Varad Nevase" }, category = {
 * CategoryType.SANITY })
 * 
 * @Test(priority = 1, description =
 * "To verify the service pre-login Move Out request.") public void
 * validatePreLogServiceTransferReqPage() throws Exception {
 * log.info("To verify the tests with the below test case id's: " +
 * "C74657, C74658"); SoftAssert softAssert = new SoftAssert(); // Init login
 * steps LoginSteps loginSteps = new LoginSteps(driver); servicerequestSteps =
 * new PreLogServiceRequestSteps(driver); dashboardSteps = new
 * DashboardSteps(driver); // Init user model User user = SCPConfiguration.user;
 * // Login into the application dashboardSteps =
 * loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
 * // Verify the user landed to the dashboard page
 * servicerequestSteps.preLogServiceServiceTransferReq(softAssert, user); //
 * Assert all the soft verification. softAssert.assertAll(); log.
 * info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
 * }
 * 
 * @TestRail(testCaseId = { 78484, 78504, 79291, 78505, 78508, 78509, 78513,
 * 78515, 78516, 79396, 79397, 79398, 129286, 129287 })
 * 
 * @FrameworkAnnotations(author = { "Varad Nevase" }, category = {
 * CategoryType.SANITY })
 * 
 * @Test(priority = 1, description =
 * "To verify the service pre-login Other request.") public void
 * validatePreLogOthersReqPage() throws Exception {
 * log.info("To verify the tests with the below test case id's: " +
 * "C74657, C74658"); SoftAssert softAssert = new SoftAssert(); // Init login
 * steps LoginSteps loginSteps = new LoginSteps(driver); servicerequestSteps =
 * new PreLogServiceRequestSteps(driver); dashboardSteps = new
 * DashboardSteps(driver); // Init user model User user = SCPConfiguration.user;
 * // Login into the application dashboardSteps =
 * loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
 * // Verify the user landed to the dashboard page
 * servicerequestSteps.preLogServiceOtherReq(softAssert, user); // Assert all
 * the soft verification. softAssert.assertAll(); log.
 * info("Test Case execution for - verifyDashboardPageObjects - is Completed.");
 * }
 * 
 * }
 */
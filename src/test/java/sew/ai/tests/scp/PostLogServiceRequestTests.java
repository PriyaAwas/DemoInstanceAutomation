package sew.ai.tests.scp;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import sew.ai.config.SCPConfiguration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.testrail.TestRail;
import sew.ai.models.User;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.LoginSteps;
import sew.ai.steps.scp.PostLogServiceRequestSteps;
import sew.ai.steps.scp.PreLogServiceRequestSteps;

public class PostLogServiceRequestTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(PostLogServiceRequestTests.class);
	private PostLogServiceRequestSteps postLogservicerequestSteps;
	private DashboardSteps dashboardSteps;

	@TestRail(testCaseId = { 78685, 78688 })
	@FrameworkAnnotations(author = { "Varad Nevase" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the service page objects after login.")
	public void validatePostLogServiceReqPage() throws SQLException {
		log.info("To verify the tests with the below test case id's: " + "C74657, C74658");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		postLogservicerequestSteps = new PostLogServiceRequestSteps(driver);
		dashboardSteps = new DashboardSteps(driver);
		// Init user model
		User user = SCPConfiguration.user;
		// Login into the application
		dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
		// Verify the user landed to the dashboard page
		postLogservicerequestSteps.postLogServiceReqObject(softAssert, user);
		log.info("Test Case execution for - validatePostLogServiceReqPage - is Completed.");
	}

	@TestRail(testCaseId = { 79645, 80090, 79646, 79648, 79649, 79650, 79651, 79652, 79652, 79654, 79655, 79656, 79658,
			79653 })
	@FrameworkAnnotations(author = { "Varad Nevase" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the service page objects after login.")
	public void validatePostLoginMoveOutPage() throws SQLException {
		log.info("To verify the tests with the below test case id's: " + "C74657, C74658");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		postLogservicerequestSteps = new PostLogServiceRequestSteps(driver);
		dashboardSteps = new DashboardSteps(driver);
		// Init user model
		User user = SCPConfiguration.user;
		// Login into the application
		dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
		// Verify the user landed to the dashboard page
		postLogservicerequestSteps.postLogServiceMoveOutReq(softAssert, user);
		log.info("Test Case execution for - validatePostLoginMoveOutPage - is Completed.");
	}

	@TestRail(testCaseId = { 78501, 78250, 78698, 78705, 79393, 100466, 78687 })
	@FrameworkAnnotations(author = { "Varad Nevase" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the service page objects after login.")
	public void validatePostLogTrackReqPage() throws SQLException {
		log.info("To verify the tests with the below test case id's: " + "C74657, C74658");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		postLogservicerequestSteps = new PostLogServiceRequestSteps(driver);
		dashboardSteps = new DashboardSteps(driver);
		// Init user model
		User user = SCPConfiguration.user;
		// Login into the application
		dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
		// Verify the user landed to the dashboard page
		postLogservicerequestSteps.postLogTrackReqPage(softAssert, user);
		log.info("Test Case execution for - validatePostLogTrackReqPage - is Completed.");
	}

	@TestRail(testCaseId = { 80091 })
	@FrameworkAnnotations(author = { "Varad Nevase" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the service pre-login Other request.")
	public void validatePostLogOthersReqPage() throws Exception {
		log.info("To verify the tests with the below test case id's: " + "C74657, C74658");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		postLogservicerequestSteps = new PostLogServiceRequestSteps(driver);
		dashboardSteps = new DashboardSteps(driver);
		// Init user model
		User user = SCPConfiguration.user;
		// Login into the application
		dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
		// Verify the user landed to the dashboard page
		postLogservicerequestSteps.postLogServiceOtherReq(softAssert, user);
		log.info("Test Case execution for - validatePostLogOthersReqPage - is Completed.");
	}

	@TestRail(testCaseId = { 78551, 78558, 78559, 78560, 78561, 78562, 78563, 78564, 78565, 78566, 78567, 78568, })
	@FrameworkAnnotations(author = { "Varad Nevase" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the service pre-login Move Out request.")
	public void validatePostLoginServiceTransferReqPage() throws Exception {
		log.info("To verify the tests with the below test case id's: " + "C74657, C74658");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		postLogservicerequestSteps = new PostLogServiceRequestSteps(driver);
		dashboardSteps = new DashboardSteps(driver);
		// Init user model
		User user = SCPConfiguration.user;
		// Login into the application
		dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
		// Verify the user landed to the dashboard page
		postLogservicerequestSteps.postLogServiceServiceTransferReq(softAssert, user);
		log.info("Test Case execution for - validatePostLoginServiceTransferReqPage - is Completed.");
	}

	@TestRail(testCaseId = { 78699, 78700, 78704, 78701, 78702, 78707, 80054 })
	@FrameworkAnnotations(author = { "Varad Nevase" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the service pre-login save request.")
	public void validatePostLogSavedServiceReq() throws SQLException {
		log.info("To verify the tests with the below test case id's: " + "C74657, C74658");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		postLogservicerequestSteps = new PostLogServiceRequestSteps(driver);
		dashboardSteps = new DashboardSteps(driver);
		// Init user model
		User user = SCPConfiguration.user;
		// Login into the application
		dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
		// Verify the user landed to the dashboard page
		postLogservicerequestSteps.postLogServiceSaveReq(softAssert, user);
		log.info("Test Case execution for - validatePostLogSavedServiceReq - is Completed.");
	}

	@TestRail(testCaseId = { 80089, 78710, 78711, 78712, 78713, 78715, 78716, 78717, 78721, 78722, 79639, 79640, 79641,
			79642, 79643, 79644, 79644 })
	@FrameworkAnnotations(author = { "Varad Nevase" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the service pre-login save request.")
	public void validatePostLoginMoveInPage() throws SQLException {
		log.info("To verify the tests with the below test case id's: " + "C74657, C74658");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		postLogservicerequestSteps = new PostLogServiceRequestSteps(driver);
		dashboardSteps = new DashboardSteps(driver);
		// Init user model
		User user = SCPConfiguration.user;
		// Login into the application
		dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
		// Verify the user landed to the dashboard page
		postLogservicerequestSteps.postLoginMoveInPage(softAssert, user);
		log.info("Test Case execution for - validatePostLoginMoveInPage - is Completed.");
	}

}

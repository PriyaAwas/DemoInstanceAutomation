package demo.tests.scp;

import java.io.IOException;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import demo.steps.scp.GuestUserSteps;
import sew.ai.config.Configuration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.HomeSteps;
import sew.ai.steps.scp.LoginSteps;

public class GuestUserTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(GuestUserTests.class);
	private GuestUserSteps guestuserSteps;


	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SMOKE,CategoryType.SCP_MYACCOUNT })
	@Test(description = "To verify Guest User page UI.")
	public void verifyGuestUserPageUI() throws SQLException, InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToGuestUser();
		// into AboutMyHomePageSteps
		guestuserSteps = new GuestUserSteps(driver);
		// Call verifyGuestUserPageUI Method
		guestuserSteps.verifyGuestUserPageUI(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test case execution for - verifyGuestUserPageUI - is completed.");
	}

	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(description = "To verify invite new guest user functionality.")
	public void verifyInviteNewGuestUserFunctionnality() throws InterruptedException, IOException {
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToGuestUser();
		// into AboutMyHomePageSteps
		guestuserSteps = new GuestUserSteps(driver);
		// Call verifyInviteNewGuestUserFunction Method
		guestuserSteps.verifyInviteNewGuestUserFunctionnality(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test case execution for - verifyInviteNewGuestUserFunction - is completed.");
	}

	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(description = "To verify resend activation link functionality.")
	public void verifyResendActivationLinkFunction() throws SQLException, InterruptedException, IOException {
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToGuestUser();
		// into AboutMyHomePageSteps
		guestuserSteps = new GuestUserSteps(driver);
		// Call verifyResendActivationLinkFunction Method
		guestuserSteps.verifyResendActivationLinkFunction(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test case execution for - verifyResendActivationLinkFunction - is completed.");

	}

	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(description = "To verify edit guest user functionality.")
	public void verifyEditGuestDetailsFunction() throws SQLException, InterruptedException, IOException {
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToGuestUser();
		// into AboutMyHomePageSteps
		guestuserSteps = new GuestUserSteps(driver);
		// Call verifyEditGuestDetailsFunction Method
		guestuserSteps.verifyEditGuestDetailsFunction(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test case execution for - verifyEditGuestDetailsFunction - is completed.");
	}
}
package demo.tests.scp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import demo.steps.scp.GuestUserSteps;
import sew.ai.config.Configuration;
import sew.ai.config.SCPConfiguration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.models.User;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.GuestuserSteps;
import sew.ai.steps.scp.HomeSteps;
import sew.ai.steps.scp.LoginSteps;

public class GuestUserTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(GuestUserTests.class);
	private GuestUserSteps guestuserSteps;
	private DashboardSteps dashboardSteps;

	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SMOKE,CategoryType.SCP_MYACCOUNT })
	@Test(description = "To verify Guest User page UI.")
	public void verifyGuestUserPageUI() throws SQLException, InterruptedException {
		String[] tc_id = {"Guest_User_01"};
		ExtentLogger.logInfo("Test Case execution for - verifyGuestUserPageUI. Test Case id's -->  " + Arrays.toString(tc_id));
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
	@Test(description = "To verify resend activation link functionality.")
	public void verifyResendActivationLinkFunction() throws SQLException, InterruptedException, IOException {
		String[] tc_id = {"Guest_User_03","Guest_User_05","Guest_User_04"};
		ExtentLogger.logInfo("Test Case execution for - verifyResendActivationLinkFunction. Test Case id's -->  " + Arrays.toString(tc_id));
		
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
		String[] tc_id = {"Guest_User_02","Guest_User_05","Guest_User_04"};
		ExtentLogger.logInfo("Test Case execution for - verifyEditGuestDetailsFunction. Test Case id's -->  " + Arrays.toString(tc_id));
		
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
	
	@FrameworkAnnotations(author = {"Neethu"}, category = {CategoryType.SANITY, CategoryType.SCP_MYACCOUNT})
    @Test(priority = 1, description = "To verify the error message for invite user pop-up.")
    public void errorMsgForInviteUserPopup() {
        log.info("To verify the tests with the below test case id's: " +
                "C74657, C74658");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        LoginSteps loginSteps = new LoginSteps(driver);
        guestuserSteps = new GuestUserSteps(driver);
        dashboardSteps = new DashboardSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
        // Verify the user landed to the dashboard page
        guestuserSteps.errorMsgForInviteUserPopup(softAssert,user);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - errorMsgForInviteUserPopup - is Completed.");
    }
	
	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(description = "To verify owner account in account dropdown.")
	public void verifyOwnerAccInAccNoDrpDown() throws SQLException, InterruptedException, IOException {
		String[] tc_id = {"Guest_User_02","Guest_User_05","Guest_User_04"};
		ExtentLogger.logInfo("Test Case execution for - verifyOwnerAccInAccNoDrpDown. Test Case id's -->  " + Arrays.toString(tc_id));
		
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToAccountInformation();
		homeSteps.navigateToGuestUser();
		// into AboutMyHomePageSteps
		guestuserSteps = new GuestUserSteps(driver);
		// Call verifyEditGuestDetailsFunction Method
		guestuserSteps.verifyOwnerAccInAccNoDrpDown(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		
		log.info("Test case execution for - verifyOwnerAccInAccNoDrpDown - is completed.");
	}
	
	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(description = "To verify error message if you asssign multiple role to same user")
	public void verifyErrMsgAssignMultipleRoleToSameUser() throws SQLException, InterruptedException, IOException {
		String[] tc_id = {"Guest_User_02","Guest_User_05","Guest_User_04"};
		ExtentLogger.logInfo("Test Case execution for - verifyErrMsgAssignMultipleRoleToSameUser. Test Case id's -->  " + Arrays.toString(tc_id));
		
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		//homeSteps.navigateToAccountInformation();
		homeSteps.navigateToGuestUser();
		// into AboutMyHomePageSteps
		guestuserSteps = new GuestUserSteps(driver);
		// Call verifyEditGuestDetailsFunction Method
		guestuserSteps.verifyErrMsgAssignMultipleRoleToSameUser(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		
		log.info("Test case execution for - verifyErrMsgAssignMultipleRoleToSameUser - is completed.");
	}
	
	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(description = "To verify invite guest user functionality.")
	public void verifyInviteUserSubmitSuccessfully() throws SQLException, InterruptedException, IOException {
		String[] tc_id = {"Guest_User_02","Guest_User_05","Guest_User_04"};
		ExtentLogger.logInfo("Test Case execution for - verifyInviteUserSubmitSuccessfully. Test Case id's -->  " + Arrays.toString(tc_id));
		
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		//homeSteps.navigateToAccountInformation();
		homeSteps.navigateToGuestUser();
		// into AboutMyHomePageSteps
		guestuserSteps = new GuestUserSteps(driver);
		// Call verifyEditGuestDetailsFunction Method
		guestuserSteps.verifyInviteUserSubmitSuccessfully(softAssert, null, null);
		// Assert all the soft verification.
		softAssert.assertAll();
		
		log.info("Test case execution for - verifyInviteUserSubmitSuccessfully - is completed.");
	}
	
	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(description = "To verify close invite guest user pop-up functionality.")
	public void verifyCloseInviteUserPopup() throws SQLException, InterruptedException, IOException {
		String[] tc_id = {"Guest_User_02","Guest_User_05","Guest_User_04"};
		ExtentLogger.logInfo("Test Case execution for - verifyCloseInviteUserPopup. Test Case id's -->  " + Arrays.toString(tc_id));
		
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToAccountInformation();
		homeSteps.navigateToGuestUser();
		// into AboutMyHomePageSteps
		guestuserSteps = new GuestUserSteps(driver);
		// Call verifyEditGuestDetailsFunction Method
		guestuserSteps.verifyCloseInviteUserPopup();
		// Assert all the soft verification.
		softAssert.assertAll();
		
		log.info("Test case execution for - verifyCloseInviteUserPopup - is completed.");
	}
	
	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(description = "To verify error message for mandatory fields.")
	public void verifyErrMsgForMandatoryFields() throws InterruptedException, IOException {
		String[] tc_id = {"Guest_User_05","Guest_User_04"};
		ExtentLogger.logInfo("Test Case execution for - verifyErrMsgForMandatoryFields. Test Case id's -->  " + Arrays.toString(tc_id));
		
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
		guestuserSteps.verifyErrMsgForMandatoryFields(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test case execution for - verifyErrMsgForMandatoryFields - is completed.");
	
	}
}